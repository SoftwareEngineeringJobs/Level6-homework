package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Admin;
import live.baize.entity.Student;
import live.baize.service.AdminService;
import live.baize.service.PaperService;
import live.baize.service.StudentService;
import live.baize.service.TeacherService;
import live.baize.utils.PasswdUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    private AdminService adminService;
    @Resource
    private PaperService paperService;
    @Resource
    private StudentService studentService;
    @Resource
    private TeacherService teacherService;

    /**
     * 管理员登录
     */
    @GetMapping("/login")
    public Response login() {
        return new Response(ResponseEnum.TEST_TEST);
    }

    @GetMapping("/logout")
    public Response logout() {
        return new Response(ResponseEnum.TEST_TEST);
    }

    @PostMapping("/hasLogin/signup")
    public Response signup(@RequestParam String email, @RequestParam String name, @RequestParam Boolean gender, @RequestParam String password, @RequestParam Integer authority) {
        // Todo:只有权限为1的超级管理员可以注册其他管理员账号
        Admin admin = new Admin(email, name, gender, PasswdUtil.generatePassword(password), authority);
        try {
            adminService.save(admin);
        } catch (Exception e) {
            e.printStackTrace();
            return new Response(ResponseEnum.Signup_Failure);
        }
        return new Response(ResponseEnum.Signup_Success);
    }

    @GetMapping("/hasLogin/stuInfo")
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
            // Todo: 重新tooString方法
            return new Response(ResponseEnum.Stu_Info, stuList.toString());
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
