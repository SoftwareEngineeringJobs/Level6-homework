package live.baize.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import live.baize.dto.ResponseEnum;
import live.baize.entity.Student;
import live.baize.exception.BusinessException;
import live.baize.service.StudentService;
import live.baize.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;

@Slf4j
@Component
public class StudentLogin implements HandlerInterceptor {

    // StudentAPI 白名单
    final static HashSet<String> StudentAPI;

    static {
        StudentAPI = new HashSet<>();

        StudentAPI.add("/student/login");
        StudentAPI.add("/student/register");
    }

    @Resource
    SessionUtil sessionUtil;
    @Resource
    private StudentService studentService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 白名单直接过
        if (StudentAPI.contains(request.getRequestURI())) {
            return true;
        }
        Student student = sessionUtil.getStudentFromSession();
        // 没有登录
        if (student == null) {
            // 尝试从cookies中获得
            student = sessionUtil.getStudentFromCookies();
            if (student != null) {
                Student one = studentService.getOne(new LambdaQueryWrapper<Student>()
                        .eq(Student::getIdCard, student.getIdCard())
                        .eq(Student::getPassword, student.getPassword()));
                // 获得成功
                if (one != null) {
                    sessionUtil.setStudentToSession(one);
                    return true;
                }
            }
            throw new BusinessException(ResponseEnum.Student_Not_Login);
        }
        return true;
    }
}
