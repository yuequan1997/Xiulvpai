package com.yuequan.xiulvpai.security.authorization.configuration;

import com.yuequan.xiulvpai.common.respository.RoleRepository;
import com.yuequan.xiulvpai.security.authorization.access.AuthorizationAccessDecisionManager;
import com.yuequan.xiulvpai.security.authorization.access.intercept.UrlAuthorizationFilterInvocationSecurityMetadataSource;
import com.yuequan.xiulvpai.security.authorization.permission.cache.RolePermission;
import com.yuequan.xiulvpai.security.authorization.processor.AuthorizationPostProcessor;
import com.yuequan.xiulvpai.security.common.configuration.support.registry.AuthorizationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.web.FilterInvocation;

import java.util.HashSet;
import java.util.Set;

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

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry authorizeRequests) {
        try {
            authorizeRequests
                    .withObjectPostProcessor(new AuthorizationPostProcessor(accessDecisionManager, metadataSource))
                    .antMatchers("/", "/assets/**")
                    .permitAll()
                    .anyRequest()
                    .authenticated();
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

    @Cacheable
    public Set<RolePermission> cacheRolePermissions(){
        var rolePermissions = new HashSet<RolePermission>();
        roleRepository.findAll().forEach(role -> {

        });
        return rolePermissions;
    }
}
