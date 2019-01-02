package org.yuequan.xiulvpai.web.service;

import org.yuequan.xiulvpai.common.domain.entity.Role;
import org.yuequan.xiulvpai.common.respository.PermissionRepository;
import org.yuequan.xiulvpai.common.respository.RoleRepository;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;

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

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Role save(Role role){
        return roleRepository.save(role);
    }

    @Transactional
    public Role save(Role role, Integer[] permissionIds){
        role.setPermissions(null);
        if(permissionIds != null && permissionIds.length > 0){
            role.setPermissions(new HashSet<>(permissionRepository.findByIdIn(permissionIds)));
        }
        return save(role);
    }

    public List<Role> getRoles(){
        return roleRepository.findAll();
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

    public List<Role> findByIdIn(Integer[] roleIds) {
        return roleRepository.findByIdIn(roleIds);
    }
}
