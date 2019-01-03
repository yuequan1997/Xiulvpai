package org.yuequan.xiulvpai.web.factory;

import org.yuequan.xiulvpai.common.domain.entity.Permission;

import java.util.List;

/**
 * @author yuequan
 * @since
 **/
public class PermissionFactory extends BeanFactory {
    private static final String[] excludes = {"id", "ancestors", "parent", "children"};
    public static Permission getPermission(){
        return get(Permission.class, excludes);
    }

    public static List<Permission> getPermissions(int number){
        return get(number, Permission.class, excludes);
    }
}
