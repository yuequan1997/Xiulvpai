package com.yuequan.xiulvpai;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Web 启动类
 * @author yuequan
 * @since 1.0
 **/
@SpringBootApplication
public class XiulvpaiWebApplication {
    public static void main(String... args){
        SpringApplicationBuilder builder = new SpringApplicationBuilder(XiulvpaiWebApplication.class);
        builder.run(args);
    }
}
