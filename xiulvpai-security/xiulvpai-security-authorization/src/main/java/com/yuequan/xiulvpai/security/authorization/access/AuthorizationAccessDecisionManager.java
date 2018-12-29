package com.yuequan.xiulvpai.security.authorization.access;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 授权访问决策管理器
 * @author yuequan
 * @since 1.0
 **/
public class AuthorizationAccessDecisionManager implements AccessDecisionManager {
    public static final String SUPER_MANAGER = "admin";
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {

        if(authentication == null){
            throw new AccessDeniedException("Unauthorized");
        }

        for (ConfigAttribute configAttribute : collection) {
            String needRole = configAttribute.getAttribute();
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                if(authority.getAuthority().equals(needRole) || authority.getAuthority().equals(SUPER_MANAGER)){
                    return;
                }
            }
        }

        throw new AccessDeniedException("Forbidden");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
