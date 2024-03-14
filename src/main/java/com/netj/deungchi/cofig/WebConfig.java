package com.netj.deungchi.cofig;

import com.netj.deungchi.provider.jwt.AccessTokenInterceptor;
import com.netj.deungchi.provider.jwt.RefreshTokenInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final AccessTokenInterceptor accessTokenInterceptor;
    private final RefreshTokenInterceptor refreshTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!11");
        registry.addInterceptor(refreshTokenInterceptor).addPathPatterns("/user/access-token");
        registry.addInterceptor(accessTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(Arrays.asList(
                        new String[]{"/**"}));
    }
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
        //                .allowedOrigins("http://localhost:8080", "http://localhost:8081");

    }
}