package com.bupt.proj.config;
import com.bupt.proj.interceptor.HostInoInterceptor;
import com.bupt.proj.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ContactWebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private HostInoInterceptor hostInoInterceptor;

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        return new WebMvcConfigurer() {
        @Override
                public void addInterceptors(InterceptorRegistry registry){

            registry.addInterceptor(hostInoInterceptor).addPathPatterns("/**");
            registry.addInterceptor(loginInterceptor).addPathPatterns("/contact/**");
        }
    };
}

}
