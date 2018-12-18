package com.yuequan.xiulvpai.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 符合HTTP异常行为的异常基类
 * @author yuequan
 * @since 1.0
 **/
public class HttpException extends RuntimeException{
    private HttpStatus status;
    public HttpException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public Integer getStatusCode() {
        return status.value();
    }
}
