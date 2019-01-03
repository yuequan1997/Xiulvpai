package org.yuequan.xiulvpai.web.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.yuequan.xiulvpai.web.factory.RoleFactory;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    public void save() {
        var role = RoleFactory.getRole();
        roleService.save(role);
        assertNotNull(role.getId());
    }

    @Test
    public void getRoles() {
        var roles = RoleFactory.getRoles(10);
        roles.forEach(role -> roleService.save(role));
        assertEquals(roles.size(), roleService.getRoles(PageRequest.of(0, 10)).getSize());
    }

    @Test
    public void findById() {
        var role = RoleFactory.getRole();
        roleService.save(role);
        assertNotNull(roleService.findById(role.getId()));
        assertThrows(ResourceNotFoundException.class, () -> roleService.findById(1000));
    }

    @Test
    public void delete() {
        var role = RoleFactory.getRole();
        roleService.save(role);
        roleService.delete(role);
        assertThrows(ResourceNotFoundException.class, () -> roleService.findById(1000));
    }

    @Test
    public void findByIdIn() {
        var roles = RoleFactory.getRoles(10);
        roles.forEach(role -> roleService.save(role));
        var ids = roles.stream().map(role -> role.getId()).collect(Collectors.toList());
        assertEquals(roles.size(), roleService.findByIdIn(ids.toArray(Integer[]::new)).size());
    }
}
