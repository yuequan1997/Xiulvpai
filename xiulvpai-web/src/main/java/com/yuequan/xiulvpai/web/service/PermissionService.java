package com.yuequan.xiulvpai.web.service;

import com.yuequan.xiulvpai.common.domain.entity.Permission;
import com.yuequan.xiulvpai.common.respository.PermissionRepository;
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
        Permission parent = permission.getParent();
        if(parent == null){
            permission.setAncestors(ANCESTOR_DELIMITER + permission.getId() + ANCESTOR_DELIMITER);
        }else{
            permission.setAncestors(parent.getAncestors() + permission.getId() + ANCESTOR_DELIMITER);
        }
    }
}
