package com.yuequan.xiulvpai.security.authorization.configuration;

import com.yuequan.xiulvpai.security.authorization.access.AuthorizationAccessDecisionManager;
import com.yuequan.xiulvpai.security.authorization.access.intercept.UrlAuthorizationFilterInvocationSecurityMetadataSource;
import com.yuequan.xiulvpai.security.authorization.processor.AuthorizationPostProcessor;
import com.yuequan.xiulvpai.security.common.configuration.support.registry.AuthorizationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.FilterInvocation;

/**
 * 授权配置
 * @author yuequan
 * @since 1.0
 **/
@Configuration
public class AuthorizationConfiguration implements AuthorizationRegistry {

    @Autowired
    private AuthorizationAccessDecisionManager accessDecisionManager;

    @Autowired
    private UrlAuthorizationFilterInvocationSecurityMetadataSource metadataSource;

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        try {
            authorizeRequests
                    .antMatchers("/")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .withObjectPostProcessor(new AuthorizationPostProcessor(accessDecisionManager, metadataSource));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Bean
    public AuthorizationAccessDecisionManager authorizationAccessDecisionManager(){
        return new AuthorizationAccessDecisionManager();
    }

    @Bean
    public UrlAuthorizationFilterInvocationSecurityMetadataSource urlAuthorizationFilterInvocationSecurityMetadataSource(){
        return new UrlAuthorizationFilterInvocationSecurityMetadataSource();
    }
}
