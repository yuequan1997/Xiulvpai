package com.yuequan.xiulvpai.web.admin.controller;

import com.yuequan.xiulvpai.common.domain.entity.Permission;
import com.yuequan.xiulvpai.common.domain.entity.Role;
import com.yuequan.xiulvpai.web.admin.annotation.AdminController;
import com.yuequan.xiulvpai.web.service.PermissionService;
import com.yuequan.xiulvpai.web.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Role {@link AdminController}
 * @author yuequan
 * @since 1.0
 */
@AdminController(path = "roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String index(Pageable pageable, Model model){
        model.addAttribute("page", roleService.getRoles(pageable));
        return "admin/roles/index";
    }

    @GetMapping("/{id}")
    public String edit(@PathVariable Integer id, Model model){
        var role = roleService.findById(id);
        model.addAttribute("role", role);
        model.addAttribute("permissions", permissionService.getPermissions());
        if(role.getPermissions() == null){
            model.addAttribute("hadPermissions", new ArrayList<>());
        }else{
            model.addAttribute("hadPermissions", role.getPermissions().stream().map(Permission::getId).collect(Collectors.toList()));
        }
        return "admin/roles/form";
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Integer id, Role role, Integer[] permissionIds, Model model){
        return create(role, permissionIds);
    }

    @GetMapping("/new")
    public String newRole(Role role, Model model){
        model.addAttribute("role", new Role());
        model.addAttribute("permissions", permissionService.getPermissions());
        model.addAttribute("hadPermissions", new ArrayList<>());
        return "admin/roles/form";
    }

    @PostMapping
    public String create(Role role, Integer[] permissionIds){
        roleService.save(role, permissionIds);
        return "redirect:./";
    }

    @DeleteMapping("/{id}")
    public String destroy(@PathVariable Integer id){
        roleService.delete(id);
        return "redirect:./";
    }
}
