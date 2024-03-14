package com.netj.deungchi.provider.jwt;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@RequiredArgsConstructor
public class AccessTokenInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(AccessTokenInterceptor.class);
//    private final JwtProvider jwtProvider;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        System.out.println("prehandle 진입");
        log.info("prehandle진입");
        return true;
    }
//        if(request.getMethod().equals("OPTIONS")) return true;
//        log.debug("request URI : {} ", request.getRequestURI());
//        log.debug("request Method : {} " , request.getMethod());
//        if(request.getMethod().equals("POST") && request.getRequestURI().equals("/user")) return true;
//        try {
//            String accessToken = getAccessToken(request.getHeader(HttpHeaders.AUTHORIZATION));
//            request.setAttribute("userSeq", jwtProvider.getUserSeqFromAccessToken(accessToken));
//            System.out.println("interceptor : "+ request.getAttribute("userSeq"));
//            return true;
//        }catch (Exception e) {
//            throw new InvalidAccessTokenException();
//        }

    private String getAccessToken(String authorization){
        return authorization.split(" ")[1];
    }
}