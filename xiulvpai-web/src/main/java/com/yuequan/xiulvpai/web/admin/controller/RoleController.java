package com.yuequan.xiulvpai.web.admin.controller;

import com.yuequan.xiulvpai.common.domain.entity.Role;
import com.yuequan.xiulvpai.web.admin.annotation.AdminController;
import com.yuequan.xiulvpai.web.service.RoleService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Role {@link AdminController}
 * @author yuequan
 * @since 1.0
 */
@AdminController(path = "roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String index(Pageable pageable, Model model){
        model.addAttribute("page", roleService.getRoles(pageable));
        return "admin/roles/index";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Integer id, Model model){
        model.addAttribute("role", roleService.findById(id));
        return "admin/roles/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Role role, Model model){
        return create(role);
    }

    @GetMapping("/new")
    public String newRole(Role role, Model model){
        model.addAttribute("role", new Role());
        return "admin/roles/form";
    }

    @PostMapping
    public String create(Role role){
        roleService.save(role);
        return "redirect:./";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable Integer id){
        roleService.delete(id);
        return "redirect:./";
    }
}
