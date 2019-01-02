package org.yuequan.xiulvpai.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 资源冲突异常
 * @see HttpStatus#CONFLICT
 * @author yuequan
 * @since 1.0
 **/
public class ResourceConflictException extends HttpException{
    public ResourceConflictException(String message) {
        super(HttpStatus.CONFLICT, message);
    }
}
