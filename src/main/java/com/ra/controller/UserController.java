package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
    public String login(Model model){
        User user = new User();
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user, HttpServletRequest request, RedirectAttributes redirectAttributes){
        User userLogin = userService.login(user);
        if(userLogin != null){
            //lu vao session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userLogin",userLogin);
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err","Sai thong tin tai khoan");
        return "redirect:/login";

    }
}
