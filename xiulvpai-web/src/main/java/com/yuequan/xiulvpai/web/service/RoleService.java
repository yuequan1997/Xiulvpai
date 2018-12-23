package com.yuequan.xiulvpai.web.service;

import com.yuequan.xiulvpai.common.domain.entity.Role;
import com.yuequan.xiulvpai.common.respository.RoleRepository;
import com.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * {@link Role} Service
 * @author yuequan
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public Role save(Role role){
        return roleRepository.save(role);
    }

    public Page<Role> getRoles(Pageable pageable){
        return roleRepository.findAll(pageable);
    }

    public Role findById(Integer id){
        return roleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    @Transactional
    public void delete(Integer id){
        roleRepository.delete(findById(id));
    }

    @Transactional
    public void delete(Role role){
        roleRepository.delete(role);
    }
}
