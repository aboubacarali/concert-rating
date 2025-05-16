package fr.rating.concert.controller;

import fr.rating.concert.dto.LoginRequestDto;
import fr.rating.concert.dto.RegisterRequestDto;
import fr.rating.concert.dto.UserResponseDto;
import fr.rating.concert.entity.User;
import fr.rating.concert.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }
    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registerRequestDto", new RegisterRequestDto());
        return "register";
    }

    @PostMapping("/register")
    public String registerAction(RegisterRequestDto registerRequestDto, Model model, HttpSession session) {
       try {
           UserResponseDto registeredUser = userService.register(registerRequestDto);
           model.addAttribute("user", registeredUser);
           session.setAttribute("user", registeredUser);

           return "redirect:/user/dashboard";
       } catch (Exception e) {
           return "register";
       }
    }

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("loginRequestDto", new LoginRequestDto());
        return "login";
    }

    @PostMapping("/login")
    public String loginAction(LoginRequestDto loginRequestDto, Model model, HttpSession session) {
        try {
            UserResponseDto loggedUser = userService.authenticate(loginRequestDto);
            model.addAttribute("user", loggedUser);
            session.setAttribute("user", loggedUser);
            return "redirect:/user/dashboard";
        }
        catch (Exception e) {
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }
}
