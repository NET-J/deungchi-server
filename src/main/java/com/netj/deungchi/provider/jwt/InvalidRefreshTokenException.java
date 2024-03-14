package com.netj.deungchi.provider.jwt;

public class InvalidRefreshTokenException extends RuntimeException{
    public InvalidRefreshTokenException() {
        super("사용할 수 없는 refreshToken 입니다");
    }
}
