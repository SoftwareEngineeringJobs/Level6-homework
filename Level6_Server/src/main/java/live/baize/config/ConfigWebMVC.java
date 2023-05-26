package live.baize.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class ConfigWebMVC implements WebMvcConfigurer {

    @Resource
    private EntryPoint entryPoint;
    @Resource
    private AdminLogin adminLogin;
    @Resource
    private TeacherLogin teacherLogin;
    @Resource
    private StudentLogin studentLogin;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(entryPoint).addPathPatterns("/**");
        registry.addInterceptor(adminLogin).addPathPatterns("/admin/*");
        registry.addInterceptor(teacherLogin).addPathPatterns("/teacher/*");
        registry.addInterceptor(studentLogin).addPathPatterns("/student/*");
    }

}
