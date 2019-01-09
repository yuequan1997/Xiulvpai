package org.yuequan.xiulvpai.web.admin.support;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yuequan.xiulvpai.web.exception.HttpException;

@ControllerAdvice(basePackages = "org.yuequan.xiulvpai.web.admin.controller")
public class AdminControllerAdvice {

    @ExceptionHandler
    public void handleException(Exception e){
        if(e instanceof HttpException){
            //TODO: 
        }
    }
}
