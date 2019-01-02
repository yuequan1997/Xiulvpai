package com.yuequan.xiulvpai.web.factory;

import com.yuequan.xiulvpai.common.domain.entity.User;

import java.util.List;

/**
 * @author yuequan
 * @since
 **/
public class UserFactory extends BeanFactory {

    public static final String[] EXCLUDE_ATTR = {"createdAt", "updatedAt", "id"};

    public static User getUser(){
       return get(User.class, EXCLUDE_ATTR);
    }

    public static List<User> getUsers(int number){
        return get(number, User.class, EXCLUDE_ATTR);
    }
}
