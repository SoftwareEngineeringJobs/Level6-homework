package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Exam;
import live.baize.entity.Paper;
import live.baize.entity.Registration;
import live.baize.entity.Student;
import live.baize.service.ExamService;
import live.baize.service.PaperService;
import live.baize.service.RegistrationService;
import live.baize.service.StudentService;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * StudentController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@CrossOrigin
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private StudentService studentService;
    @Resource
    private ExamService examService;
    @Resource
    private PaperService paperService;
    @Resource
    private RegistrationService registrationService;

    /**
     * 学生注册
     * 收集学生的身份证 姓名 性别 学校 邮箱 等信息
     *
     * @param idCard   身份证
     * @param name     姓名
     * @param gender   性别
     * @param school   学校
     * @param password 密码
     * @return 三种 已经注册/注册成功/注册失败
     */
    @PostMapping(value = "/register")
    public Response register(String idCard, String name, boolean gender, String school, String password) {
        // 注册过
        if (studentService.getOne(new QueryWrapper<Student>().eq("id_card", idCard)) != null) {
            return new Response(ResponseEnum.Student_Has_Registered);
        }

        // 注册
        try {
            Student student = new Student().setIdCard(idCard).setName(name).setGender(gender).setSchool(school)
                    .setPassword(PasswdUtil.generatePassword(password)).setCet4(425);
            studentService.save(student);
        } catch (Exception e) {
            return new Response(ResponseEnum.Register_Failure);
        }
        return new Response(ResponseEnum.Register_Success);
    }

    /**
     * 学生登录
     * 用于学生登录的API
     *
     * @param idCard   身份证
     * @param password 密码
     * @return 登录成功/登录失败
     */
    @GetMapping(value = "/login")
    public Response login(@RequestParam String idCard, @RequestParam String password) {
        Student student = studentService.getOne(
                new QueryWrapper<Student>()
                        .eq("id_card", idCard)
                        .eq("password", PasswdUtil.generatePassword(password))
        );

        if (student == null) {
            return new Response(ResponseEnum.Student_Login_Failure);
        }

        // 登录信息
        sessionUtil.setStudentToSession(student);
        return new Response(ResponseEnum.Student_Login_Success);
    }


    /**
     * 退出登录
     *
     * @return 退出登录成功
     */
    @GetMapping(value = "/logout")
    public Response logout() {
        sessionUtil.delStudentFromSession();
        return new Response(ResponseEnum.Student_Logout_Success);
    }

    /**
     * 查看考试场次
     * 查看所有 未开始考试 同时 开始报名的场次
     *
     * @return 查询成功
     */
    @GetMapping(value = "/lookupExam")
    public Response lookupExam() {
        Date date = new Date();
        List<Exam> collect = examService.list(
                        new QueryWrapper<Exam>().select("exam_id", "register_time", "test_time")
                )
                .stream()
                // 未开始考试 同时 开始报名的场次
                .filter(exam -> date.before(exam.getTestTime()) && date.after(exam.getRegisterTime()))
                .collect(Collectors.toList());
        return new Response(ResponseEnum.Lookup_Exam_Success, collect);
    }

    /**
     * 报名并缴费
     *
     * @param examId 考试场次
     */
    @PostMapping(value = "/registration")
    public Response registration(String examId) {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        // 考虑重复报名 简单起见 只要有信息 就认为报过名
        if (registrationService.getOne(new QueryWrapper<Registration>().eq("stu_id", student.getStuId())) != null) {
            return new Response(ResponseEnum.Has_Registration);
        }

        // 根据考试ID 获得三张试卷ID
        Exam exam = examService.getById(examId);
        // 随机一张试卷ID
        int paperId;
        switch (new Random().nextInt(3)) {
            case 0:
                paperId = exam.getPaperA();
                break;
            case 1:
                paperId = exam.getPaperB();
                break;
            default:
                paperId = exam.getPaperC();
        }
        // 构造报名信息 默认完成缴费
        Registration registration = new Registration().setExamId(exam.getExamId()).setPaperId(paperId)
                .setStuId(student.getStuId()).setRegisterTime(new Date()).setPaid(true);
        registrationService.save(registration);
        return new Response(ResponseEnum.Registration_Success);
    }

    /**
     * 查看试卷题目
     */
    @GetMapping(value = "/getPaperInfo")
    public Response getPaperInfo() {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        Registration registration = registrationService.getOne(new QueryWrapper<Registration>().eq("stu_id", student.getStuId()));
        if (registration == null) {
            // 没有报名
            return new Response(ResponseEnum.Not_Registration);
        }

        // 是否到了考试时间
        Exam exam = examService.getById(registration.getExamId());
        if (exam.getTestTime().after(new Date())) {
            // 不到考试时间
            return new Response(ResponseEnum.Test_Time_Not_Arrived);
        }

        // 试卷列表
        List<Paper> list = paperService.list(
                new QueryWrapper<Paper>().eq("paper_id", registration.getPaperId())
                        .select("question_id", "question")
        );
        return new Response(ResponseEnum.Get_PaperInfo_Success, list);
    }

//    - 注册
//    - 登录
//    - 退出
//    - 查看考试场次 （查询exam 表，根据时间判断）
//    - 报名并缴费（registration 随机一个paper_id）
//    - 获得试卷信息（依据 registration 查看paper_id, 然后返回对应的试卷信息)
//    - 上传答题结果 （自动算选择题的分数）
//    - 查看分数（在时间上做拦截）

}
