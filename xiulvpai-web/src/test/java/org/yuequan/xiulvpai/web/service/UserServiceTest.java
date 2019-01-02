package org.yuequan.xiulvpai.web.service;

import org.junit.jupiter.api.DisplayName;
import org.yuequan.xiulvpai.web.exception.ResourceNotFoundException;
import org.yuequan.xiulvpai.web.factory.RoleFactory;
import org.yuequan.xiulvpai.web.factory.UserFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Test
    public void save() {
        var user = UserFactory.getUser();
        userService.save(user);
        assertNotNull(user.getId());
    }

    @Test
    public void findById() {
        var user = UserFactory.getUser();
        userService.save(user);
        assertNotNull(userService.findById(user.getId()));
        assertThrows(ResourceNotFoundException.class, () -> userService.findById("test"));
    }

    @Test
    public void destroy() {
        var user = UserFactory.getUser();
        userService.save(user);
        userService.destroy(user);
        assertThrows(ResourceNotFoundException.class, () -> userService.findById(user.getId()));
    }

    @Test
    public void getUsers() {
        var users = UserFactory.getUsers(10);
        users.forEach(user -> userService.save(user));
        assertEquals(users.size(), userService.getUsers(PageRequest.of(0, 10)).getContent().size());
    }

    @Test
    @DisplayName("test user grant roles")
    public void grantRole(){
        var roles = RoleFactory.getRoles(10);
        roles.forEach(role -> roleService.save(role));
        var user = UserFactory.getUser();
        userService.save(user, roles.stream().map(role -> role.getId()).collect(Collectors.toList()).toArray(Integer[]::new));
        assertEquals(user.getRoles().size(), roles.size());
    }
}