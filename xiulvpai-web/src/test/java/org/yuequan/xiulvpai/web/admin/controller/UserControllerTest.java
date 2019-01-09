package org.yuequan.xiulvpai.web.admin.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.yuequan.xiulvpai.web.factory.UserFactory;
import org.yuequan.xiulvpai.web.service.UserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


class UserControllerTest extends TestController{

    @Autowired
    private UserService userService;

    @Test
    void index() throws Exception {
        authenticatedMockMvc.perform(get("/admin/users")).andExpect(status().isOk());
        UserFactory.getUsers(10).forEach(user -> userService.save(user));
        authenticatedMockMvc.perform(get("/admin/users")).andExpect(status().isOk());
    }

    @Test
    void newUser() throws Exception {
        authenticatedMockMvc.perform(get("/admin/users/new")).andExpect(status().isOk());
    }

    @Test
    void edit() throws Exception {
        var user = userService.save(UserFactory.getUser());
        authenticatedMockMvc.perform(get("/admin/users/"+user.getId())).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        var user = userService.save(UserFactory.getUser());
        authenticatedMockMvc.perform(put("/admin/users/"+user.getId())
                .param("username", user.getUsername())
                .param("name", "test").param("password", ""))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("./"));
    }

    @Test
    void create() throws Exception {
        authenticatedMockMvc.perform(post("/admin/users")
                .param("username", "test")
                .param("name", "test").param("password", "123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("./"));
        // TODO: ConflictException
        var user = userService.save(UserFactory.getUser());
        authenticatedMockMvc.perform(post("/admin/users")
                .param("username", user.getUsername())
                .param("name", "test").param("password", "123456"))
                .andExpect(status().isConflict())
                .andExpect(redirectedUrl("./"));
    }

    @Test
    void destroy() throws Exception {
        var user = userService.save(UserFactory.getUser());
        authenticatedMockMvc.perform(delete("/admin/users/"+user.getId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("./"));
    }
}