package com.yuequan.xiulvpai.common.respository;

import com.yuequan.xiulvpai.common.domain.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 权限持久化操作类
 * @author yuequan
 * @since 1.0
 **/
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findAllByOrderByAncestorsAsc();
    List<Permission> findByAncestorsStartingWithOrderByAncestorsAsc(String rootAncestors);
    List<Permission> findByIdIn(Integer[] ids);
}