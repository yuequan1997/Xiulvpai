package org.yuequan.xiulvpai.web.admin.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.yuequan.xiulvpai.common.domain.entity.Role;
import org.yuequan.xiulvpai.common.domain.entity.User;
import org.yuequan.xiulvpai.web.service.PermissionService;
import org.yuequan.xiulvpai.web.service.RoleService;
import org.yuequan.xiulvpai.web.service.UserService;

import java.util.HashSet;
import java.util.Set;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class TestController {

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private WebApplicationContext context;

    protected MockMvc authenticatedMockMvc;

    @Autowired
    protected MockMvc mockMvc;

    protected static final String ADMIN_USERNAME = "admin";
    protected static final String ADMIN_PASSWORD = "123456";

    @BeforeEach
    public void setup(){
        var role = new Role();
        role.setName("admin");
        var permissions = permissionService.getPermissions();
        role.setPermissions(new HashSet<>(permissions));
        roleService.save(role);

        var user = new User();
        user.setUsername(ADMIN_USERNAME);
        user.setPassword(passwordEncoder.encode(ADMIN_PASSWORD));
        user.setRoles(Set.of(role));
        userService.save(user);

        authenticatedMockMvc = MockMvcBuilders.webAppContextSetup(context).defaultRequest(get("/").with(user(ADMIN_USERNAME).password(ADMIN_PASSWORD))).build();
    }
}
