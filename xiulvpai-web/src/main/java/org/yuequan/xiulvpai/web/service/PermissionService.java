package org.yuequan.xiulvpai.web.service;

import org.yuequan.xiulvpai.common.domain.entity.Permission;
import org.yuequan.xiulvpai.common.respository.PermissionRepository;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * {@link Permission} Service
 * @author yuequan
 * @since 1.0
 */
@Service
@Transactional(readOnly = true)
public class PermissionService {

    public static final String ANCESTOR_DELIMITER = "/";

    @Autowired
    private PermissionRepository permissionRepository;

    @Transactional
    public Permission save(Permission permission){
        permissionRepository.save(permission);
        populateAncestors(permission);
        permissionRepository.save(permission);
        return permission;
    }

    public List<Permission> getChildren(Permission root){
        return permissionRepository.findByAncestorsStartingWithOrderByAncestorsAsc(root.getAncestors());
    }

    @Transactional
    public void delete(Integer id){
        var permission = permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
        if(getChildren(permission).size() == 1){
            permissionRepository.delete(permission);
        }
    }

    public Permission findById(Integer id){
        return permissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(""));
    }

    public List<Permission> getPermissions(){
        var flatPermissions = permissionRepository.findAllByOrderByAncestorsAsc();
        Map<Integer, Permission> flatTreeMap = new HashMap<>();

        var treePermissions = new ArrayList<Permission>();
        flatPermissions.forEach(permission -> {
            flatTreeMap.put(permission.getId(), permission);
            if(permission.getParent() == null){
                treePermissions.add(permission);
            }else{
                var parent =  flatTreeMap.get(permission.getParent().getId());
                if(parent.getChildren() == null)
                    parent.setChildren(new HashSet<>());
                parent.getChildren().add(permission);
            }
        });
        return treePermissions;
    }

    private void populateAncestors(Permission permission){
        var parent = permission.getParent();
        if(parent != null && parent.getId() != null){
            parent = permissionRepository.findById(permission.getParent().getId()).orElse(null);
        }else{
            parent = null;
        }
        permission.setParent(parent);
        permission.setAncestors(generateAncestors(permission, parent));
    }

    public String generateAncestors(Permission permission, Permission parent){
        if(parent == null){
            return ANCESTOR_DELIMITER + permission.getId() + ANCESTOR_DELIMITER;
        }else{
            return parent.getAncestors() + permission.getId() + ANCESTOR_DELIMITER;
        }
    }
}
