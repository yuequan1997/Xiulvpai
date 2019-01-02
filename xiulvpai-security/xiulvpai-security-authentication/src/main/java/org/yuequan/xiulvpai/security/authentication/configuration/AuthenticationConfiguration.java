package org.yuequan.xiulvpai.security.authentication.configuration;

import org.yuequan.xiulvpai.security.common.configuration.support.registry.HttpSecurityRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * 认证相关配置
 * @author yuequan
 * @since 1.0
 **/
@Configuration
public class AuthenticationConfiguration implements HttpSecurityRegistry {
    @Override
    public void configure(HttpSecurity http){
        try {
            http.formLogin().and();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
