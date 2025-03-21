package com.netj.deungchi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ResponseDto<T> {
    private int code;
    private String status;
    private T data;

    public static <T> ResponseDto<T> success(T data) {
        return new ResponseDto<>(200, "Success",  data);
    }
    public static <T> ResponseDto<T> fail(int errorCode, String status, T message) {
        return new ResponseDto<>(errorCode, status, message);
    }
}
