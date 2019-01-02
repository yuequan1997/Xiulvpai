package org.yuequan.xiulvpai;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Web 启动类
 * @author yuequan
 * @since 1.0
 **/
@SpringBootApplication
@EnableAspectJAutoProxy
public class XiulvpaiWebApplication {
    public static void main(String... args){
        SpringApplicationBuilder builder = new SpringApplicationBuilder(XiulvpaiWebApplication.class);
        builder.run(args);
    }
}
