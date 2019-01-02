package org.yuequan.xiulvpai.security.authorization.permission;

import org.yuequan.xiulvpai.common.domain.entity.Permission;
import org.yuequan.xiulvpai.common.domain.entity.Role;
import org.yuequan.xiulvpai.security.authorization.permission.cache.RolePermission;
import org.yuequan.xiulvpai.security.authorization.service.AuthorizationRoleService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.*;

/**
 * 授权决策者
 * @author yuequan
 * @since 1.0
 **/
@Component
public class AuthorizationDecisionMaker implements InitializingBean {
    @Autowired
    private AuthorizationRoleService authorizationRoleService;

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    private Set<RolePermission> rolePermissions;

    private static final String[] IGNORE_AUTHORIZATION_URLS = {"/assets/**", "/favicon.ico", "/public/**"};

    /**
     * has mapping?
     * @return
     */
    public Set<String> hasMapping(String url){
        url = trimSlashSuffix(url);
        for (RolePermission rolePermission : rolePermissions) {
            if(antPathMatcher.match(rolePermission.getPath(), url)){
                return rolePermission.getRoles();
            }
        }
        return null;
    }

    public boolean isIgnoreAuthorizationUrl(String url){
        for (String ignoreAuthorizationUrl : IGNORE_AUTHORIZATION_URLS) {
            if(antPathMatcher.match(ignoreAuthorizationUrl, url)){
                return true;
            }
        }
        return false;
    }


    private Set<RolePermission> cachePathMappingRoles(){
        var rolePermissions = new HashSet<RolePermission>();
        var pathMappingRoles = new HashMap<String, Set<String>>();
        var allRoles = authorizationRoleService.getRoles();

        for (Role role : allRoles) {
            for (Permission permission : role.getPermissions()) {
                var paths = permission.getPath().split(",");
                for (String path : paths) {
                    path = trimSlashSuffix(path);
                    if(!pathMappingRoles.containsKey(path)){
                        pathMappingRoles.put(path, new HashSet<>());
                    }
                    pathMappingRoles.get(path).add(role.getName());
                }
            }
        }
        pathMappingRoles.forEach((path, roles) -> {
            rolePermissions.add(new RolePermission(path, roles));
        });
        return rolePermissions;
    }

    public void refreshCache() throws Exception {
        afterPropertiesSet();
    }

    /**
     * 去除最后一个斜杠，降低校验的负复杂性
     * trimSlashSuffix("/admin/") => "/admin"
     * trimSlashSuffix("/admin") => "/admin"
     */
    public String trimSlashSuffix(String url){
        return url.endsWith("/") ? url.substring(0, url.length() - 1) : url;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.rolePermissions = cachePathMappingRoles();
    }
}
