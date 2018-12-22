package com.yuequan.xiulvpai.web.admin.controller;

import com.yuequan.xiulvpai.common.domain.entity.User;
import com.yuequan.xiulvpai.web.admin.annotation.AdminController;
import com.yuequan.xiulvpai.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * User {@link AdminController}
 * @author yuequan
 * @since 1.0
 **/
@AdminController(path = "users")
public class UserController extends AdminBaseController{

    @Autowired
    private UserService userService;

    @GetMapping
    public String index(Pageable pageable, Model model){
        model.addAttribute("page", userService.getUsers(pageable));
        return "admin/users/index";
    }

    @GetMapping("/new")
    public String newUser(Model model){
        model.addAttribute("user", new User());
        return "admin/users/form";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable  String id, Model model){
        model.addAttribute("user", userService.findById(id));
        return "admin/users/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable String id, User user){
        userService.save(user);
        return "redirect:./";
    }

    @PostMapping
    public String create(User user){
        userService.save(user);
        return "redirect:./";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable String id){
        userService.destroy(id);
        return "redirect:./";
    }
}
