package live.baize.config;

import live.baize.dto.ResponseEnum;
import live.baize.entity.Admin;
import live.baize.exception.BusinessException;
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
public class AdminLogin implements HandlerInterceptor {

    // AdminAPI 白名单
    final static HashSet<String> AdminAPI;

    static {
        AdminAPI = new HashSet<>();

        AdminAPI.add("/admin/login");
    }

    @Resource
    SessionUtil sessionUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 白名单直接过
        if (AdminAPI.contains(request.getRequestURI())) {
            return true;
        }
        Admin admin = sessionUtil.getAdminFromSession();
        // 没有登录 扔出异常
        if (admin == null) {
            throw new BusinessException(ResponseEnum.Admin_Not_Login);
        }
        return true;
    }
}
