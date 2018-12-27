package com.yuequan.xiulvpai.security.authorization.configuration;

import com.yuequan.xiulvpai.security.common.configuration.support.registry.AuthorizationRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * 授权配置
 * @author yuequan
 * @since 1.0
 **/
@Configuration
public class AuthorizationConfiguration implements AuthorizationRegistry {
    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        try {
            authorizeRequests.anyRequest().permitAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
