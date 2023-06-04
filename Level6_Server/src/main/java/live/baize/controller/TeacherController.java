package live.baize.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import live.baize.dto.Response;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Registration;
import live.baize.entity.Teacher;
import live.baize.service.RegistrationService;
import live.baize.service.TeacherService;
import live.baize.utils.PasswdUtil;
import live.baize.utils.SessionUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * TeacherController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@CrossOrigin
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private SessionUtil sessionUtil;
    @Resource
    private TeacherService teacherService;
    @Resource
    private RegistrationService registrationService;

    /**
     * 老师登录
     */
    @GetMapping("/login")
    public Response login(@RequestParam String email, @RequestParam String password) {
        // 根据邮箱和密码(加密)查询
        Teacher teacher = teacherService.getOne(
                new LambdaQueryWrapper<Teacher>()
                        .eq(Teacher::getEmail, email)
                        .eq(Teacher::getPassword, PasswdUtil.generatePassword(password))
        );

        // 登录失败
        if (teacher == null) {
            return new Response(ResponseEnum.Teacher_Login_Failure);
        }

        // 登录成功
        sessionUtil.setTeacherToSession(teacher);
        return new Response(ResponseEnum.Teacher_Login_Success);
    }

    /**
     * 获得试卷信息
     */
    @GetMapping("/getPaperInfo")
    public Response getPaperInfo(String examId) {
        Page<Registration> page = registrationService.page(
                new Page<>(0, 1),
                new LambdaQueryWrapper<Registration>()
                        .eq(Registration::getExamId, examId)
                        .eq(Registration::getScoreWrite, -1)
                        .select(Registration::getRegistrationId, Registration::getWriting, Registration::getTranslation)
        );
        if (page == null || page.getRecords().isEmpty()) {
            return new Response(ResponseEnum.Without_Paper);
        }
        return new Response(ResponseEnum.Get_Paper_Success, page.getRecords().get(0));
    }

    /**
     * 打分
     */
    @GetMapping("/scoring")
    public Response scoring(String registrationId, int scores) {
        registrationService.update(
                new LambdaUpdateWrapper<Registration>()
                        .eq(Registration::getRegistrationId, registrationId)
                        .set(Registration::getScoreWrite, scores)
        );
        return new Response(ResponseEnum.Scoring_Success);
    }


//  - 查看学生道题（翻译或写作）
//  - 打分
//  - 登录

}
