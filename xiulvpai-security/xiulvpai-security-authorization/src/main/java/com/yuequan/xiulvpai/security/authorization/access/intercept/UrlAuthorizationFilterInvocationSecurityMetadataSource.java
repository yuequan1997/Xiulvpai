package com.yuequan.xiulvpai.security.authorization.access.intercept;

import com.yuequan.xiulvpai.security.authorization.permission.AuthorizationDecisionMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

import java.util.Collection;

/**
 * url授权过滤
 * @author yuequan
 * @since 1.0
 **/
public class UrlAuthorizationFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private AuthorizationDecisionMaker authorizationDecisionMaker;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        var filterInvocation = FilterInvocation.class.cast(o);
        String requestUrl = filterInvocation.getRequestUrl();
        if(authorizationDecisionMaker.isIgnoreAuthorizationUrl(requestUrl)){
            return null;
        }
        var roles = authorizationDecisionMaker.hasMapping(requestUrl);
        if(roles != null){
            return SecurityConfig.createList(roles.toArray(new String[roles.size()]));
        }
        return SecurityConfig.createList("ROLE_USER");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return FilterInvocation.class.isAssignableFrom(aClass);
    }
}
