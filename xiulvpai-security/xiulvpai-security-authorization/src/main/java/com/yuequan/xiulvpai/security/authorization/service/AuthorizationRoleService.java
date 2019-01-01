package com.yuequan.xiulvpai.security.authorization.service;

import com.yuequan.xiulvpai.common.domain.entity.Role;
import com.yuequan.xiulvpai.common.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author yuequan
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class AuthorizationRoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getRoles(){
        return roleRepository.findAll();
    }
}
