package com.ra.controller;

import com.ra.model.entity.User;
import com.ra.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

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
    public String login(Model model,
                        @CookieValue(required = false,name = "userCookie") String emailCookie){
        User user = new User();
        System.out.println(emailCookie);
        if(emailCookie != null){
            user.setEmail(emailCookie);
            model.addAttribute("checked",true);
        }
        model.addAttribute("user",user);
        return "login";
    }

    @PostMapping("/login")
    public String postLogin(@ModelAttribute("user") User user,
                            @RequestParam(value = "check",defaultValue = "false") Boolean check,
                            HttpServletRequest request,
                            RedirectAttributes redirectAttributes,
                            HttpServletResponse response
                            ){
        User userLogin = userService.login(user);
        System.out.println(check);
        if(userLogin != null){
            //lu vao session
            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userLogin",userLogin);
            if(check){
                Cookie cookieEmail = new Cookie("userCookie",userLogin.getEmail());
                cookieEmail.setMaxAge(24*60*60);
                response.addCookie(cookieEmail);
            } else {
                Cookie[] cookies = request.getCookies();
//                for (int i = 0; i < cookies.length; i++) {
//                    if(cookies[i].getName().equals("userCookie")){
//                        cookies[i].setMaxAge(0);
//                        response.addCookie(cookies[i]);
//                    }
//                }
               Cookie cookieNew = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals("userCookie")).findFirst().get();
               cookieNew.setMaxAge(0);
               response.addCookie(cookieNew);

            }
            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("err","Sai thong tin tai khoan");
        return "redirect:/login";

    }
    @GetMapping("/logout")
    public String logout(HttpSession httpSession){
        httpSession.removeAttribute("userLogin");
        return "redirect:/";
    }
}
