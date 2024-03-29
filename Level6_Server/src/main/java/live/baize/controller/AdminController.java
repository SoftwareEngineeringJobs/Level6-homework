package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.*;
import live.baize.exception.BusinessException;
import live.baize.service.*;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import org.springframework.format.annotation.DateTimeFormat;
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
                new LambdaQueryWrapper<Admin>()
                        .eq(Admin::getEmail, email)
                        .eq(Admin::getPassword, PasswdUtil.generatePassword(password))
                        .select(Admin::getAdminId, Admin::getEmail, Admin::getName, Admin::getAuthority)
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
     * 查看管理员信息
     */
    @GetMapping("/adminInfo")
    public Response adminInfo(@RequestParam(required = false) Integer adminId, @RequestParam(required = false) String email,
                              @RequestParam(required = false) String name, @RequestParam(required = false) Boolean gender,
                              @RequestParam(required = false) Integer authority) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<Admin>();
        if (adminId != null)
            wrapper.eq("admin_id", adminId);
        if (email != null && !email.equals(""))
            wrapper.eq("email", email);
        if (name != null && !name.equals(""))
            wrapper.eq("name", name);
        if (gender != null)
            wrapper.eq("gender", gender);
        if (authority != null)
            wrapper.eq("authority", authority);
        wrapper.select("admin_id", "email", "name", "gender", "authority");
        List<Admin> adminList = adminService.list(wrapper);
        if (adminList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Admin_Info, adminList);
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
            throw new BusinessException(ResponseEnum.Admin_Authority_Low);
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

    @GetMapping("/deleteAdmin")
    public Response deleteAdmin(@RequestParam Integer adminId) {
        Admin adminCurr = sessionUtil.getAdminFromSession();
        if (adminCurr.getAuthority() != 1) {
            // 权限不足
            throw new BusinessException(ResponseEnum.Admin_Authority_Low);
        }
        try {
            adminService.removeById(adminId);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Delete_Admin_Failure);
        }
        return new Response(ResponseEnum.Delete_Admin_Success);
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
            throw new BusinessException(ResponseEnum.Admin_Authority_Low);
        }

        Admin admin = adminService.getOne(
                new QueryWrapper<Admin>().eq("admin_id", adminId)
        );
        admin.setPassword(PasswdUtil.generatePassword("12345678"));
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
    public Response stuInfo(@RequestParam(required = false) Integer stuId, @RequestParam(required = false) String idCard,
                            @RequestParam(required = false) String name, @RequestParam(required = false) Boolean gender,
                            @RequestParam(required = false) String school) {
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
        student.setPassword(PasswdUtil.generatePassword("12345678"));
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
    public Response examInfo() {
        List<Exam> examList = examService.list();
        if (examList.isEmpty())
            return new Response(ResponseEnum.Res_Not_Found);
        else
            return new Response(ResponseEnum.Exam_Info, examList);
    }

    /**
     * 发布考试信息
     */
    @PostMapping("/publishExam")
    public Response publishExam(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date registerTime,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date testTime,
                                @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date scoreTime,
                                @RequestParam Integer paperA,
                                @RequestParam Integer paperB,
                                @RequestParam Integer paperC,
                                @RequestParam String listening) {
        Exam exam = new Exam(registerTime, testTime, scoreTime, paperA, paperB, paperC, listening);
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
    public Response modifyExam(@RequestParam Integer examId,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date registerTime,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date testTime,
                               @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date scoreTime,
                               @RequestParam Integer paperA,
                               @RequestParam Integer paperB,
                               @RequestParam Integer paperC,
                               @RequestParam String listening) {
        Exam exam = new Exam(examId, registerTime, testTime, scoreTime, paperA, paperB, paperC, listening);
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
    public Response paperInfo(@RequestParam(required = false) Integer paperId, @RequestParam(required = false) Integer questionId) {
        QueryWrapper<Paper> wrapper = new QueryWrapper<Paper>();
        if (paperId != null) {
            wrapper.eq("paper_id", paperId);
        }
        if (questionId != null) {
            wrapper.eq("question_id", questionId);
        }
        List<Paper> paperList = paperService.list(wrapper);
        if (paperList.isEmpty()) {
            return new Response(ResponseEnum.Res_Not_Found);
        }
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
        UpdateWrapper<Paper> wrapper = new UpdateWrapper<Paper>();
        wrapper.eq("paper_id", paperId);
        wrapper.eq("question_id", questionId);
        wrapper.set("question", question);
        wrapper.set("answer", answer);
        try {
            paperService.update(wrapper);   // paper表双主键，无法根据单ID更新
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
    public Response teacherInfo(@RequestParam(required = false) Integer teacherId, @RequestParam(required = false) String email,
                                @RequestParam(required = false) String name, @RequestParam(required = false) Boolean gender) {
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
    public Response addTeacher(@RequestParam String email, @RequestParam String name, @RequestParam Boolean gender) {
        // 增加教师，默认密码设为12345678
        Teacher teacher = new Teacher(email, name, gender, PasswdUtil.generatePassword("12345678"));
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
        teacher.setPassword(PasswdUtil.generatePassword("12345678"));
        try {
            teacherService.updateById(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
    }

    @GetMapping(value = "/getAdminInfo")
    public Response getAdminInfo() {
        Admin admin = sessionUtil.getAdminFromSession();
        admin.setPassword(null);
        return new Response(ResponseEnum.Get_Person_Info, admin);
    }

    @PostMapping(value = "/resetAdminPasswd")
    public Response resetAdminPasswd(@RequestParam String oldPasswd, @RequestParam String newPasswd) {
        Integer adminId = sessionUtil.getAdminFromSession().getAdminId();
        Admin admin = adminService.getOne(
                new QueryWrapper<Admin>().eq("admin_id", adminId)
                        .eq("password", PasswdUtil.generatePassword(oldPasswd)));
        if (admin == null) {
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        admin.setPassword(PasswdUtil.generatePassword(newPasswd));
        try {
            adminService.updateById(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Reset_Passwd_Failure);
        }
        return new Response(ResponseEnum.Reset_Passwd_Success);
    }
}
