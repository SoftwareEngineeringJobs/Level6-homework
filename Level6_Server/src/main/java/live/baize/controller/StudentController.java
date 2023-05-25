package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Student;
import live.baize.service.PaperService;
import live.baize.service.StudentService;
import live.baize.utils.PasswdUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * StudentController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private PaperService paperService;
    @Resource
    private StudentService studentService;

    @GetMapping(value = "/login")
    public Response login(@RequestParam String idCard, @RequestParam String password) {
        Student student = studentService.getOne(
                new QueryWrapper<Student>()
                        .eq("id_card", idCard)
                        .eq("password", PasswdUtil.generatePassword(password))
        );

        if (student == null) {
            return new Response(ResponseEnum.Login_Failure);
        }

        //
        return new Response(ResponseEnum.Login_Success);
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
