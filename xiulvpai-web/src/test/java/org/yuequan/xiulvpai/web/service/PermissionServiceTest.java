package org.yuequan.xiulvpai.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.yuequan.xiulvpai.common.domain.entity.Permission;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.yuequan.xiulvpai.web.factory.PermissionFactory;

import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
@PersistenceContext
public class PermissionServiceTest {

    @Autowired
    private PermissionService permissionService;

    @Test
    public void save() {
        var permission = PermissionFactory.getPermission();
        permissionService.save(permission);
        assertNotNull(permission.getId());
        assertTrue(checkAncestors(permission));

        var permissionTreeRoot = PermissionFactory.getPermission();
        permissionService.save(permissionTreeRoot);

        var permissionTreeLeftN = PermissionFactory.getPermission();
        permissionTreeLeftN.setParent(permissionTreeRoot);

        var permissionTreeRightN = PermissionFactory.getPermission();
        permissionTreeRightN.setParent(permissionTreeRoot);

        permissionService.save(permissionTreeLeftN);
        permissionService.save(permissionTreeRightN);

        assertTrue(checkAncestors(permissionTreeRoot));
        assertTrue(checkAncestors(permissionTreeLeftN));
        assertTrue(checkAncestors(permissionTreeRightN));
    }

    @Test
    public void getChildren() {
        var permissionTreeRoot = PermissionFactory.getPermission();
        permissionService.save(permissionTreeRoot);

        var permissionTreeLeftN = PermissionFactory.getPermission();
        permissionTreeLeftN.setParent(permissionTreeRoot);

        var permissionTreeRightN = PermissionFactory.getPermission();
        permissionTreeRightN.setParent(permissionTreeRoot);

        permissionService.save(permissionTreeLeftN);
        permissionService.save(permissionTreeRightN);

        assertEquals(3, permissionService.getChildren(permissionTreeRoot).size());
    }

    @Test
    public void delete() {
        var permissionTreeRoot = PermissionFactory.getPermission();
        permissionService.save(permissionTreeRoot);

        var permissionTreeLeftN = PermissionFactory.getPermission();
        permissionTreeLeftN.setParent(permissionTreeRoot);

        var permissionTreeRightN = PermissionFactory.getPermission();
        permissionTreeRightN.setParent(permissionTreeRoot);

        permissionService.save(permissionTreeLeftN);
        permissionService.save(permissionTreeRightN);

        permissionService.delete(permissionTreeRightN.getId());
        assertThrows(ResourceNotFoundException.class, () -> permissionService.findById(permissionTreeRightN.getId()));

        permissionService.delete(permissionTreeRoot.getId());
        assertNotNull(permissionService.findById(permissionTreeRoot.getId()));
    }

    @Test
    public void findById() {
        var permission = PermissionFactory.getPermission();
        permissionService.save(permission);
        assertNotNull(permissionService.findById(permission.getId()));
        assertThrows(ResourceNotFoundException.class, () -> permissionService.findById(10000));
    }

    @Test
    public void getPermissions() {
        var permissionTreeRoot = PermissionFactory.getPermission();
        permissionService.save(permissionTreeRoot);

        var permissionTreeLeftN = PermissionFactory.getPermission();
        permissionTreeLeftN.setParent(permissionTreeRoot);

        var permissionTreeRightN = PermissionFactory.getPermission();
        permissionTreeRightN.setParent(permissionTreeRoot);

        permissionService.save(permissionTreeLeftN);
        permissionService.save(permissionTreeRightN);

        assertEquals(1, permissionService.getPermissions().size());
        assertEquals(2, permissionService.getPermissions().get(0).getChildren().size());
    }

    private boolean checkAncestors(Permission permission){
       if(permissionService.generateAncestors(permission, permission.getParent()).equals(permission.getAncestors())){
           return true;
       }
       return false;
    }
}
