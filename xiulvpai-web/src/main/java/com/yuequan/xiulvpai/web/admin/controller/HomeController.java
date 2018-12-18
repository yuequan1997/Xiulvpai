package com.yuequan.xiulvpai.web.admin.controller;

import com.yuequan.xiulvpai.web.admin.annotation.AdminController;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Home {@link AdminController}
 * @author yuequan
 * @since
 **/
@AdminController
public class HomeController {
    @GetMapping(value = {"/", ""})
    public String index(){
        return "admin/index";
    }
}
