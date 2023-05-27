package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.*;
import live.baize.exception.SystemException;
import live.baize.service.*;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import lombok.Data;
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
@CrossOrigin
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
    public Response signup(@RequestParam String email, @RequestParam String name,
                           @RequestParam Boolean gender, @RequestParam Integer authority) {
        // 验证当前管理员权限 adminCurr只保存了 id和邮箱
        Admin adminCurr = sessionUtil.getAdminFromSession();
        if (adminCurr.getAuthority() != 1) {
            // 权限不足
            throw new SystemException(ResponseEnum.Admin_Authority_Low);
        }

        // 只有权限为1的超级管理员可以注册其他管理员账号
        Admin admin = new Admin(email, name, gender, PasswdUtil.generatePassword("12345678"), authority);
        try {
            adminService.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Signup_Failure);
        }
        return new Response(ResponseEnum.Signup_Success);
    }

    /**
     * 重置管理员密码为12345678
     */
    @GetMapping("/resetAdmin")
    public Response resetAdmin(@RequestParam Integer adminId) {
        // 验证当前管理员权限，超级管理员才可以重置管理员密码
        Admin adminCurr = sessionUtil.getAdminFromSession();
        if (adminCurr.getAuthority() != 1) {
            // 权限不足
            throw new SystemException(ResponseEnum.Admin_Authority_Low);
        }

        Admin admin = adminService.getOne(
                new QueryWrapper<Admin>().eq("admin_id", adminId)
        );
        admin.setPasswd(PasswdUtil.generatePassword("12345678"));
        try {
            adminService.updateById(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
    }

    /**
     * （根据搜索条件）查看考生信息
     */
    @GetMapping("/stuInfo")
    public Response stuInfo(Integer stuId, String idCard, String name, Boolean gender, String school) {
        QueryWrapper<Student> wrapper = new QueryWrapper<Student>();
        if (stuId != null)
            wrapper.eq("stu_id", stuId);
        if (idCard != null && !idCard.equals(""))
            wrapper.eq("id_card", idCard);
        if (name != null && !name.equals(""))
            wrapper.eq("name", name);
        if (gender != null)
            wrapper.eq("gender", gender);
        if (school != null && !school.equals(""))
            wrapper.eq("school", school);
        wrapper.select("stu_id", "id_card", "name", "gender", "school", "cet4", "cet6");
        List<Student> stuList = studentService.list(wrapper);
        if (stuList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Stu_Info, stuList);
    }

    /**
     * 重置考生密码为12345678
     */
    @GetMapping("/resetStudent")
    public Response resetStudent(@RequestParam Integer stuId) {
        Student student = studentService.getOne(
                new QueryWrapper<Student>().eq("stu_id", stuId)
        );
        student.setPasswd(PasswdUtil.generatePassword("12345678"));
        try {
            studentService.updateById(student);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
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
        if (examList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Exam_Info, examList);
    }

    /**
     * 发布考试信息
     */
    @PostMapping("/publishExam")
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

    /**
     * 查看试卷信息
     */
    @GetMapping("/paperInfo")
    public Response paperInfo(Integer paperId, Integer questionId) {
        QueryWrapper<Paper> wrapper = new QueryWrapper<Paper>();
        if (paperId != null)
            wrapper.eq("paper_id", paperId);
        if (questionId != null)
            wrapper.eq("question_id", questionId);
        List<Paper> paperList = paperService.list(wrapper);
        if (paperList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Paper_Info, paperList);
    }

    /**
     * 上传试卷信息
     */
    @PostMapping("/uploadPaper")
    public Response uploadPaper(@RequestParam Integer paperId, @RequestParam Integer questionId,
                                @RequestParam String question, @RequestParam String answer) {
        Paper paper = new Paper(paperId, questionId, question, answer);
        try {
            paperService.save(paper);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Upload_Paper_Failure);
        }
        return new Response(ResponseEnum.Upload_Paper_Success);
    }

    /**
     * 修改试卷信息
     */
    @PostMapping("/modifyPaper")
    public Response modifyPaper(@RequestParam Integer paperId, @RequestParam Integer questionId,
                                @RequestParam String question, @RequestParam String answer) {
        Paper paper = new Paper(paperId, questionId, question, answer);
        try {
            paperService.updateById(paper);     // 根据ID更新，ID永远不变
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Modify_Paper_Failure);
        }
        return new Response(ResponseEnum.Modify_Paper_Success);
    }

    /**
     * 查看教师信息
     */
    @GetMapping("/teacherInfo")
    public Response teacherInfo(Integer teacherId, String email, String name, Boolean gender) {
        QueryWrapper<Teacher> wrapper = new QueryWrapper<Teacher>();
        if (teacherId != null)
            wrapper.eq("teacher_id", teacherId);
        if (email != null && !email.equals(""))
            wrapper.eq("email", email);
        if (name != null && !name.equals(""))
            wrapper.eq("name", name);
        if (gender != null)
            wrapper.eq("gender", gender);
        wrapper.select("teacher_id", "email", "name", "gender");
        List<Teacher> teacherList = teacherService.list(wrapper);
        if (teacherList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Teacher_Info, teacherList);
    }

    /**
     * 增加教师
     */
    @PostMapping("/addTeacher")
    public Response addTeacher(@RequestParam String eamil, @RequestParam String name, @RequestParam Boolean gender) {
        // 增加教师，默认密码设为12345678
        Teacher teacher = new Teacher(eamil, name, gender, PasswdUtil.generatePassword("12345678"));
        try {
            teacherService.save(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Add_Teacher_Failure);
        }
        return new Response(ResponseEnum.Add_Teacher_Success);
    }

    /**
     * 删除教师
     */
    @GetMapping("/deleteTeacher")
    public Response deleteTeacher(@RequestParam Integer teacherId) {
        try {
            teacherService.removeById(teacherId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Delete_Teacher_Failure);
        }
        return new Response(ResponseEnum.Delete_Teacher_Success);
    }

    /**
     * 重置教师密码为12345678
     */
    @GetMapping("/resetTeacher")
    public Response resetTeacher(@RequestParam Integer teacherId) {
        Teacher teacher = teacherService.getOne(
                new QueryWrapper<Teacher>().eq("teacher_id", teacherId)
        );
        teacher.setPasswd(PasswdUtil.generatePassword("12345678"));
        try {
            teacherService.updateById(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
    }
}
