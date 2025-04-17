package ru.spring.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.spring.app.service.UserService;

@Controller
public class StartController {
    private static final Logger log = LoggerFactory.getLogger(StartController.class);
    private UserService userService;

    @RequestMapping("/index")
    public String bike(Model model) {

        model.addAttribute("user", "test@test.ru");

        log.info("UserService initialized! This is index.html");
        return "index";
    }

}
