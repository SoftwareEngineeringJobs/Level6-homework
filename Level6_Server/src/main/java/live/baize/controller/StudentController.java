package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Exam;
import live.baize.entity.Paper;
import live.baize.entity.Registration;
import live.baize.entity.Student;
import live.baize.exception.BusinessException;
import live.baize.service.ExamService;
import live.baize.service.PaperService;
import live.baize.service.RegistrationService;
import live.baize.service.StudentService;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * StudentController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@Slf4j
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
     * @param student 学生 需要传递 身份证, 姓名, 性别, 学校, 密码
     * @return 三种 已经注册/注册成功/注册失败
     */
    @PostMapping(value = "/register")
    public Response register(@RequestBody Student student) {
        if (student == null) {
            throw new BusinessException(ResponseEnum.Param_Missing);
        }

        // 注册过
        if (studentService.getOne(new LambdaQueryWrapper<Student>().eq(Student::getIdCard, student.getIdCard())) != null) {
            return new Response(ResponseEnum.Student_Has_Registered);
        }

        // 注册
        try {
            student.setPassword(PasswdUtil.generatePassword(student.getPassword())).setCet4(425);
            studentService.save(student);
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.Register_Failure);
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
    public Response login(String idCard, String password) {
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
        sessionUtil.setStudentToCookies(student);
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
        sessionUtil.delStudentFromCookies();
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
                        new LambdaQueryWrapper<Exam>()
                                .select(Exam::getExamId, Exam::getRegisterTime, Exam::getTestTime)
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
    public Response registration(@RequestParam String examId) {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        // 考虑重复报名 简单起见 只要有信息 就认为报过名
        if (registrationService.getOne(new QueryWrapper<Registration>().eq("stu_id", student.getStuId())) != null) {
            return new Response(ResponseEnum.Has_Registration);
        }

        // 根据考试ID 获得三张试卷ID
        Exam exam = examService.getById(examId);
        if (exam == null) {
            throw new BusinessException(ResponseEnum.Not_This_Exam);
        }

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
                .setStuId(student.getStuId()).setRegisterTime(new Date()).setPaid(true).setScoreWrite(-1);
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

        Registration registration = registrationService.getOne(
                new LambdaQueryWrapper<Registration>().eq(Registration::getStuId, student.getStuId())
        );
        if (registration == null) {
            // 没有报名
            return new Response(ResponseEnum.Not_Registration);
        }

        // 是否到了考试时间范围
        Exam exam = examService.getById(registration.getExamId());
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(exam.getTestTime());
        Cal.add(Calendar.MINUTE, 135);

        // 不在考试时间范围
        if (exam.getTestTime().after(new Date()) || new Date().after(Cal.getTime())) {
            return new Response(ResponseEnum.Not_Test_Time_Range);
        }

        // 计算考试剩余时间
        Date date1 = new Date();
        Date date2 = Cal.getTime();
        long remainTime = 0;
        remainTime = date2.getTime() - date1.getTime();
        long remainHour = remainTime / (60 * 60 * 1000) % 24;
        long remainMin = remainTime / (60 * 1000) % 60;
        long remainSec = remainTime / 1000 % 60;

        // 试卷列表
        List<Paper> list = paperService.list(
                new LambdaQueryWrapper<Paper>()
                        .eq(Paper::getPaperId, registration.getPaperId())
                        .select(Paper::getQuestionId, Paper::getQuestion)
        );

        HashMap<String, Object> js = new HashMap<>();
        js.put("paper", list);
        js.put("remainTime", new long[] {remainHour, remainMin, remainSec});
        js.put("listening", exam.getListening());

        return new Response(ResponseEnum.Get_PaperInfo_Success, js);
    }

    /**
     * 上传答题结果
     */
    @PostMapping("uploadAnswer")
    public Response uploadAnswer(@RequestParam(required = false) String choice,
                                 @RequestParam(required = false) String writing,
                                 @RequestParam(required = false) String translation) {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        // 判断是否报名
        Registration registration = registrationService.getOne(
                new LambdaQueryWrapper<Registration>().eq(Registration::getStuId, student.getStuId())
        );
        if (registration == null) {
            // 没有报名
            return new Response(ResponseEnum.Not_Registration);
        }

        // 是否到了考试时间范围
        Exam exam = examService.getById(registration.getExamId());
        Calendar Cal = Calendar.getInstance();
        Cal.setTime(exam.getTestTime());
        Cal.add(Calendar.MINUTE, 135);

        // 不在考试时间范围
        if (exam.getTestTime().after(new Date()) || new Date().after(Cal.getTime())) {
            return new Response(ResponseEnum.Not_Test_Time_Range);
        }

        // 答案全空
        if (StringUtils.isEmpty(choice) && StringUtils.isEmpty(writing) && StringUtils.isEmpty(translation)) {
            throw new BusinessException(ResponseEnum.Param_Missing);
        }

        // 保存答案
        LambdaUpdateWrapper<Registration> wrapper = new LambdaUpdateWrapper<>();
        if (StringUtils.isNotEmpty(choice)) {
            wrapper.set(Registration::getChoice, choice);

            // 全部作答开始判分
            if (choice.length() == 55) {
                // 选择题答案字符串
                String collect = paperService.list(new LambdaQueryWrapper<Paper>()
                                .eq(Paper::getPaperId, registration.getPaperId())
                                .between(Paper::getQuestionId, 1, 55)
                                .select(Paper::getQuestionId, Paper::getAnswer))
                        .stream()
                        .sorted(Comparator.comparingInt(Paper::getQuestionId))
                        .map(Paper::getAnswer)
                        .collect(Collectors.joining());
                float scoreRead = 0f;
                float scoreListen = 0f;
                // 听力
                // 8  0  - 7    7
                // 7  8  - 14   7
                for (int i = 0; i < 15; ++i) {
                    if (choice.charAt(i) == collect.charAt(i)) {
                        scoreListen += 7.1;
                    }
                }
                // 10 15 - 24   14
                for (int i = 15; i < 25; ++i) {
                    if (choice.charAt(i) == collect.charAt(i)) {
                        scoreListen += 14.2;
                    }
                }

                // 阅读
                // 10 25 - 34   3.5
                for (int i = 25; i < 35; ++i) {
                    if (choice.charAt(i) == collect.charAt(i)) {
                        scoreRead += 3.55;
                    }
                }
                // 10 35 - 44   7
                for (int i = 35; i < 45; ++i) {
                    if (choice.charAt(i) == collect.charAt(i)) {
                        scoreRead += 7.1;
                    }
                }
                // 10 45 - 54   14
                for (int i = 45; i < 55; ++i) {
                    if (choice.charAt(i) == collect.charAt(i)) {
                        scoreRead += 14.2;
                    }
                }

                wrapper.set(Registration::getScoreRead, scoreRead);
                wrapper.set(Registration::getScoreListen, scoreListen);
            }

        }
        if (StringUtils.isNotEmpty(writing)) {
            wrapper.set(Registration::getWriting, writing);
        }
        if (StringUtils.isNotEmpty(translation)) {
            wrapper.set(Registration::getTranslation, translation);
        }

        wrapper.eq(Registration::getRegistrationId, registration.getRegistrationId());
        registrationService.update(wrapper);

        return new Response(ResponseEnum.Save_Answer_Success);
    }

    /**
     * 查看分数（在时间上做拦截）
     */
    @GetMapping(value = "/lookupScore")
    public Response lookupScore() {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        // 判断是否报名
        Registration registration = registrationService.getOne(
                new LambdaQueryWrapper<Registration>().eq(Registration::getStuId, student.getStuId())
                        .select(Registration::getExamId,
                                Registration::getScoreRead, Registration::getScoreWrite, Registration::getScoreListen)
        );
        if (registration == null) {
            // 没有报名
            return new Response(ResponseEnum.Not_Registration);
        }
        // 是否到了出分时间
        Exam exam = examService.getById(registration.getExamId());
        if (exam.getScoreTime().after(new Date())) {
            return new Response(ResponseEnum.Not_Score_Time_Range);
        }
        return new Response(ResponseEnum.Lookup_Score_Success, registration);

    }

    /**
     * 获得当前用户的信息
     */
    @GetMapping(value = "/getStudentInfo")
    public Response getStudentInfo() {
        // 考生信息
        Student student = sessionUtil.getStudentFromSession();

        student.setPassword(null);

        return new Response(ResponseEnum.Get_Person_Info, student);

    }

    @PostMapping(value = "/resetStudentPasswd")
    public Response resetStudentPasswd(@RequestParam String oldPasswd, @RequestParam String newPasswd) {
        Integer stuId = sessionUtil.getStudentFromSession().getStuId();
        Student student = studentService.getOne(
                new QueryWrapper<Student>().eq("stu_id", stuId)
                        .eq("password", PasswdUtil.generatePassword(oldPasswd)));
        if (student == null) {
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        student.setPassword(PasswdUtil.generatePassword(newPasswd));
        try {
            studentService.updateById(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
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
