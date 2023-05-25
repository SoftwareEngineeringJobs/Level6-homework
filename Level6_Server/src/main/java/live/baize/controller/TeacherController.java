package live.baize.controller;

import live.baize.service.RegistrationService;
import live.baize.service.TeacherService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * TeacherController
 *
 * @author CodeXS
 * @since 2023-05-10
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private TeacherService teacherService;
    @Resource
    private RegistrationService registrationService;

//  - 查看学生道题（翻译或写作）
//  - 打分
//  - 登录

}
