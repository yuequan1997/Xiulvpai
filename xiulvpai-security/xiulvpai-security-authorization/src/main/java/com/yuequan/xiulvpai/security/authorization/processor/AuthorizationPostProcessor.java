package com.yuequan.xiulvpai.security.authorization.processor;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 授权处理器
 * @author yuequan
 * @since 1.0
 **/
public class AuthorizationPostProcessor implements ObjectPostProcessor<FilterSecurityInterceptor> {

    private AccessDecisionManager accessDecisionManager;
    private FilterInvocationSecurityMetadataSource metadataSource;

    public AuthorizationPostProcessor(AccessDecisionManager accessDecisionManager, FilterInvocationSecurityMetadataSource metadataSource) {
        this.accessDecisionManager = accessDecisionManager;
        this.metadataSource = metadataSource;
    }

    @Override
    public <O extends FilterSecurityInterceptor> O postProcess(O filterSecurityInterceptor) {
        filterSecurityInterceptor.setSecurityMetadataSource(metadataSource);
        filterSecurityInterceptor.setAccessDecisionManager(accessDecisionManager);
        return filterSecurityInterceptor;
    }
}
