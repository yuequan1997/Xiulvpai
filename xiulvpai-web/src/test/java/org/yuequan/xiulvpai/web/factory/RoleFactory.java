package org.yuequan.xiulvpai.web.factory;

import org.yuequan.xiulvpai.common.domain.entity.Role;

import java.util.List;

/**
 * @author yuequan
 * @since 1.0
 */
public class RoleFactory extends BeanFactory{

    public final static String[] excludes = {"id", "permissions"};

    public static Role getRole(){
        return get(Role.class, excludes);
    }

    public static List<Role> getRoles(int number){
        return get(number, Role.class, excludes);
    }
}
