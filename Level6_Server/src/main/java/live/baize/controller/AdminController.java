package live.baize.controller;

import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.service.AdminService;
import live.baize.service.PaperService;
import live.baize.service.StudentService;
import live.baize.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

//    依次实现这些功能
//    - 管理员注册 （这里做权限校验）
//    - 查看考生信息
//    - 发布考试信息
//    - 上传试卷信息
//    - 增删 教师
//    - 管理员登录
//    - 管理员退出

}
