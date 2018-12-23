package com.yuequan.xiulvpai.web.controller;

import com.yuequan.xiulvpai.common.domain.entity.Permission;
import com.yuequan.xiulvpai.web.admin.annotation.AdminController;
import com.yuequan.xiulvpai.web.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * Permission {@link AdminController}
 * @author yuequan
 * @since 1.0
 */
@AdminController(path = "permissions")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("permissions", permissionService.getPermissions());
        return "admin/permissions/index";
    }

    @PostMapping
    public String create(Permission permission){
        permissionService.save(permission);
        return "redirect:./";
    }

}
