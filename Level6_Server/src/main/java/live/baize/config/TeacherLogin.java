package live.baize.config;

import live.baize.dto.ResponseEnum;
import live.baize.entity.Student;
import live.baize.entity.Teacher;
import live.baize.exception.SystemException;
import live.baize.utils.SessionUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class TeacherLogin implements HandlerInterceptor {

    @Resource
    SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        Teacher teacher = sessionUtil.getTeacherFromSession();
        // 没有登录 扔出异常
        if (teacher == null) {
            throw new SystemException(ResponseEnum.Teacher_Not_Login);
        }
        return true;
    }
}
