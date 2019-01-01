package com.yuequan.xiulvpai.common.respository;

import com.yuequan.xiulvpai.common.domain.entity.Role;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 角色持久化操作类
 * @author yuequan
 * @since 1.0
 **/
public interface RoleRepository extends JpaRepository<Role, Integer> {
    List<Role> findByIdIn(Integer[] roleIds);

    @Override
    @EntityGraph(value = "roles.all", type = EntityGraph.EntityGraphType.FETCH)
    List<Role> findAll();
}