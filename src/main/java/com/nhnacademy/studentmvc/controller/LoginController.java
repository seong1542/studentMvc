package com.nhnacademy.studentmvc.controller;

import com.nhnacademy.studentmvc.domain.LoginRequest;
import com.nhnacademy.studentmvc.domain.User;
import com.nhnacademy.studentmvc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Objects;

@Slf4j
@Controller
public class LoginController implements BaseController{
    private final UserRepository userRepository;
    private final User user;
    public LoginController(UserRepository userRepository,User user){
        this.userRepository = userRepository;
        this.user = user;
    }

    @GetMapping(value = {"/", "/login"})
    public String login(Model model, User user){
        if(Objects.nonNull(user)){
            return "redirect:/";
        }
        model.addAttribute("loginRequest", new LoginRequest());
        return "loginForm";
    }
    @PostMapping(value={"/","/login"})
    public String doLogin(Model model, @Valid LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request, RedirectAttributes redirectAttributes){
        if(bindingResult.hasFieldErrors()){
            return "loginForm";
        }
        if(userRepository.matches(loginRequest)){
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            return "redirect:/student/list";
        }
        redirectAttributes.addFlashAttribute("message", "로그인에 실패하였습니다.");
        return "redirect:/login";
    }
    @PostMapping(value="/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(Objects.nonNull(session)){
            session.invalidate();
            Cookie cookie = new Cookie("JESSIONID", "");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        return "redirect:/login";
    }
}
