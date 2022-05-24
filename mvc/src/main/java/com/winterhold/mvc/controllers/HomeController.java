package com.winterhold.mvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("home")
public class HomeController {

    @GetMapping("index")
    public String home(Model model){
        model.addAttribute("breadCrumbs", "Home / Index");
        return "home/home-index";
    }
}
