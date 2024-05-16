package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/register")
    public String register(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "register";
    }
    @PostMapping("/register")
    public String postRegister(@ModelAttribute("user") User user){
        userService.register(user);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login(){
        User user = new User();
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user){

        return "redirect:/";
    }
}
