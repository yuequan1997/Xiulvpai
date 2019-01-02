package org.yuequan.xiulvpai.security.authorization.service;

import org.yuequan.xiulvpai.common.domain.entity.Role;
import org.yuequan.xiulvpai.common.respository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
