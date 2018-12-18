package com.yuequan.xiulvpai.common.respository;

import com.yuequan.xiulvpai.common.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * 用户持久化操作类
 * @author yuequan
 * @since 1.0
 **/
public interface UserRepository extends JpaRepository<User, String> {
    /**
     * 根据username字段查找{@link User}
     * @param username
     * @return
     */
    Optional<User> findByUsername(String username);
}
