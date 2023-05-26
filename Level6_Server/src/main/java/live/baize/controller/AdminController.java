package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Admin;
import live.baize.entity.Exam;
import live.baize.entity.Student;
import live.baize.exception.SystemException;
import live.baize.service.*;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * AdminController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private AdminService adminService;
    @Resource
    private PaperService paperService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;
    @Resource
    private ExamService examService;

    /**
     * 管理员登录
     */
    @GetMapping("/login")
    public Response login(@RequestParam String email, @RequestParam String password) {
        // 根据邮箱和密码(加密)查询
        Admin admin = adminService.getOne(
                new QueryWrapper<Admin>()
                        .eq("email", email)
                        .eq("password", PasswdUtil.generatePassword(password))
                        .select("admin_id", "email", "authority")
        );

        // 登录失败
        if (admin == null) {
            return new Response(ResponseEnum.Admin_Login_Failure);
        }

        // 登录成功
        sessionUtil.setAdminToSession(admin);
        return new Response(ResponseEnum.Admin_Login_Success);
    }

    /**
     * 退出登录
     */
    @GetMapping("/logout")
    public Response logout() {
        sessionUtil.delAdminFromSession();
        return new Response(ResponseEnum.Admin_Logout_Success);
    }

    /**
     * 管理员注册
     */
    @PostMapping("/signup")
    public Response signup(@RequestParam String email, @RequestParam String name, @RequestParam Boolean gender,
                           @RequestParam String password, @RequestParam Integer authority) {
        // 验证当前管理员权限 adminCurr只保存了 id和邮箱
        Admin adminCurr = sessionUtil.getAdminFromSession();
        if (adminCurr.getAuthority() != 1) {
            // 权限不足
            throw new SystemException(ResponseEnum.Admin_Authority_Low);
        }

        // 只有权限为1的超级管理员可以注册其他管理员账号
        Admin admin = new Admin(email, name, gender, PasswdUtil.generatePassword(password), authority);
        try {
            adminService.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Signup_Failure);
        }
        return new Response(ResponseEnum.Signup_Success);
    }

    /**
     * （根据搜索条件）查看考生信息
     */
    @GetMapping("/stuInfo")
    public Response stuInfo(Integer stuId, String idCard, String name, Boolean gender, String school) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        if (stuId != null)
            wrapper.eq("stu_id", stuId);
        if (idCard != null)
            wrapper.eq("id_card", idCard);
        if (name != null)
            wrapper.eq("name", name);
        if (gender != null)
            wrapper.eq("gender", gender);
        if (school != null)
            wrapper.eq("school", school);
        List<Student> stuList = studentService.list(wrapper);
        if (stuList == null)
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Stu_Info, stuList.toString());
    }

    /**
     * （根据搜索条件）查看考试信息
     */
    @GetMapping("/examInfo")
    public Response examInfo(Integer examId, Date registerTime, Date testTime, Date scoreTime,
                            Integer paperA, Integer paperB, Integer paperC) {
        QueryWrapper<Exam> wrapper = new QueryWrapper<Exam>();
        if (examId != null)
            wrapper.eq("exam_id", examId);
        if (registerTime != null)
            wrapper.eq("register_time", registerTime);
        if (testTime != null)
            wrapper.eq("test_time", testTime);
        if (scoreTime != null)
            wrapper.eq("score_time", scoreTime);
        List<Exam> examList = examService.list(wrapper);
        if (examList == null)
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Exam_Info, examList.toString());
    }

    /**
     * 发布考试信息
     */
    @GetMapping("/publishExam")
    public Response publishExam(@RequestParam Date registerTime, @RequestParam Date testTime,
                                @RequestParam Date scoreTime, @RequestParam Integer paperA,
                                @RequestParam Integer paperB, @RequestParam Integer paperC) {
        Exam exam = new Exam(registerTime, testTime, scoreTime, paperA, paperB, paperC);
        try {
            examService.save(exam);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Publish_Exam_Failure);
        }
        return new Response(ResponseEnum.Publish_Exam_Success);
    }

    /**
     * 修改考试信息
     */
    @PostMapping("/modifyExam")
    public Response modifyExam(@RequestParam Integer examId, @RequestParam Date registerTime,
                               @RequestParam Date testTime, @RequestParam Date scoreTime,
                               @RequestParam Integer paperA, @RequestParam Integer paperB,
                               @RequestParam Integer paperC) {
        Exam exam = new Exam(registerTime, testTime, scoreTime, paperA, paperB, paperC);
        exam.setId(examId);
        try {
            examService.updateById(exam);   // 根据ID更新，ID永远不变
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Modify_Exam_Failure);
        }
        return new Response(ResponseEnum.Modify_Exam_Success);
    }

//    依次实现这些功能
//    - 管理员注册 （这里做权限校验）
//    - 查看考生信息
//    - 发布考试信息
//    - 上传试卷信息
//    - 增删 教师
//    - 管理员登录
//    - 管理员退出

}
