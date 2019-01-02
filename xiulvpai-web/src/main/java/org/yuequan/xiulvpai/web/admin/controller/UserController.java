package org.yuequan.xiulvpai.web.admin.controller;

import org.yuequan.xiulvpai.common.domain.entity.Role;
import org.yuequan.xiulvpai.common.domain.entity.User;
import org.yuequan.xiulvpai.web.admin.annotation.AdminController;
import org.yuequan.xiulvpai.web.service.RoleService;
import org.yuequan.xiulvpai.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * User {@link AdminController}
 * @author yuequan
 * @since 1.0
 **/
@AdminController(path = "users")
public class UserController extends AdminBaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Pageable pageable, Model model){
        model.addAttribute("page", userService.getUsers(pageable));
        return "admin/users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("hasRoles", new ArrayList<>(0));
        return "admin/users/form";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable  String id, Model model){
        var user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("roles", roleService.getRoles());
        model.addAttribute("hasRoles", user.getRoles().stream().map(Role::getId).collect(Collectors.toList()));
        return "admin/users/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, User user, Integer[] roleIds){
        create(user, roleIds);
        return "redirect:./";
    }

    @PostMapping
    public String create(User user, Integer[] roleIds){
        userService.save(user, roleIds);
        return "redirect:./";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable String id){
        userService.destroy(id);
        return "redirect:./";
    }
}
