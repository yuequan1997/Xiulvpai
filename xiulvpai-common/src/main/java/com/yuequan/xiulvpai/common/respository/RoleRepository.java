package com.yuequan.xiulvpai.common.respository;

import com.yuequan.xiulvpai.common.domain.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 角色持久化操作类
 * @author yuequan
 * @since 1.0
 **/
public interface RoleRepository extends JpaRepository<Role, Integer> {
}