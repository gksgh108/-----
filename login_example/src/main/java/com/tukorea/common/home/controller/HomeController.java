package com.tukorea.common.home.controller;

import com.tukorea.common.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private LoginService service;

    @GetMapping("/")
    public String home() {
        return"common/home/home";
    }
}
