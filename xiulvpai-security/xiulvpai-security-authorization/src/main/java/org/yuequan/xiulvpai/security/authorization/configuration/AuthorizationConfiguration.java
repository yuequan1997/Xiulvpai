package org.yuequan.xiulvpai.security.authorization.configuration;

import org.yuequan.xiulvpai.security.authorization.access.AuthorizationAccessDecisionManager;
import org.yuequan.xiulvpai.security.authorization.access.intercept.UrlAuthorizationFilterInvocationSecurityMetadataSource;
import org.yuequan.xiulvpai.security.authorization.processor.AuthorizationPostProcessor;
import org.yuequan.xiulvpai.security.common.configuration.support.registry.AuthorizationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private AuthorizationAccessDecisionManager accessDecisionManager;

    @Autowired
    private UrlAuthorizationFilterInvocationSecurityMetadataSource metadataSource;

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        try {
            authorizeRequests
                    .antMatchers("/", "/assets/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .withObjectPostProcessor(new AuthorizationPostProcessor(accessDecisionManager, metadataSource));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
