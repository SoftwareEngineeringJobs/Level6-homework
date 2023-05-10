package live.baize.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class ConfigWebMVC implements WebMvcConfigurer {

    @Resource
    private EntryPoint entryPoint;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(entryPoint)
                .addPathPatterns("/**");
    }

}
