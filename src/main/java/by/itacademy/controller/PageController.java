package by.itacademy.controller;

import by.itacademy.security.service.SecurityContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class PageController {

    @GetMapping(value = "/main")
    public ModelAndView mainPage() {
        return new ModelAndView("main");
    }

    @GetMapping(value = {"/", "/login"})
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

}
