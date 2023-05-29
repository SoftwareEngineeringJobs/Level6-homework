package live.baize.config;

import live.baize.dto.ResponseEnum;
import live.baize.entity.Student;
import live.baize.entity.Teacher;
import live.baize.exception.BusinessException;
import live.baize.exception.SystemException;
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
public class TeacherLogin implements HandlerInterceptor {

    @Resource
    SessionUtil sessionUtil;

    // StudentAPI 白名单
    final static HashSet<String> TeacherAPI;

    static {
        TeacherAPI = new HashSet<>();

        TeacherAPI.add("/teacher/login");
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 白名单直接过
        if (TeacherAPI.contains(request.getRequestURI())) {
            return true;
        }
        Teacher teacher = sessionUtil.getTeacherFromSession();
        // 没有登录 扔出异常
        if (teacher == null) {
            throw new BusinessException(ResponseEnum.Teacher_Not_Login);
        }
        return true;
    }
}
