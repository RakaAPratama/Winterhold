package com.winterhold.mvc.controllers;

import com.winterhold.mvc.dtos.register.RegisterDTO;
import com.winterhold.mvc.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("account")
public class AccountController {

    private AccountService accountService;

    @Autowired
    public AccountController(AccountService service) {
        this.accountService = service;
    }

    @GetMapping("login-form")
    public String login(Model model){
        return "account/login-form";
    }

    @GetMapping("register-form")
    public String registerForm(Model model) {
        RegisterDTO dto = new RegisterDTO();
        model.addAttribute("account", dto);
        return "account/register-form";
    }

    @PostMapping("register")
    public String register(@Valid @ModelAttribute("account") RegisterDTO dto,
                           BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("account", dto);
            return "account/register-form";
        }
        accountService.registerAccount(dto);
        return "redirect:/account/login-form";
    }
}
