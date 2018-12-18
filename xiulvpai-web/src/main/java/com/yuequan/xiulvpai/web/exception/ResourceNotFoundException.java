package com.yuequan.xiulvpai.web.exception;

import org.springframework.http.HttpStatus;

/**
 * 当资源找不到时抛出
 * @see HttpStatus#NOT_FOUND
 * @author yuequan
 * @since
 **/
public class ResourceNotFoundException extends HttpException {
    public ResourceNotFoundException(String message) {
        super(HttpStatus.NOT_FOUND, message);
    }
}
