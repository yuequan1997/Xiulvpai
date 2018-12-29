package com.yuequan.xiulvpai.security.authorization.permission.cache;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

/**
 * @author yuequan
 * @since
 **/
@Getter
@Setter
public class RolePermission {
    private String path;
    private Set<String> roles;

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof RolePermission){
            var rolePermission = (RolePermission) obj;
            if(this.path.equals(rolePermission.getPath())){
                return true;
            }else{
                return false;
            }
        }
        return super.equals(obj);
    }
}
