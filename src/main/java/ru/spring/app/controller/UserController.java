package ru.spring.app.controller;

import org.springframework.http.MediaType;
import ru.spring.app.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.spring.app.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/users")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        log.info("UserController initialized!");
        this.userService = userService;
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        model.addAttribute("pageTitle", "User List");
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }

    @GetMapping(value = "/create", produces = MediaType.TEXT_HTML_VALUE)
    public String showCreateForm(Model model) {
        log.debug("Accessing create form");
        model.addAttribute("user", new UserDTO());
        return "users/create";
    }

//    @PostMapping("/create")
//    public String createUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO,
//                             BindingResult result,
//                             RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "users/create";
//        }
//
//        try {
//            userService.registerUser(userDTO);
//            redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
//        } catch (Exception e) {
//            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
//            return "redirect:/users/create";
//        }
//
//        return "redirect:/users";
//    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute("user") @Valid UserDTO userDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "users/create";
        }

        try {
            userService.createUser(userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User created successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users/create";
        }

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            UserDTO userDTO = userService.getUserById(id);
            model.addAttribute("userDTO", userDTO);
            return "users/edit";
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "redirect:/users";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateUser(@PathVariable Long id,
                             @Valid @ModelAttribute("userDTO") UserDTO userDTO,
                             BindingResult result,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "users/edit";
        }

        try {
            userService.updateUser(id, userDTO);
            redirectAttributes.addFlashAttribute("successMessage", "User updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/users/edit/" + id;
        }

        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteUser(id);
            redirectAttributes.addFlashAttribute("successMessage", "User deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
        }
        return "redirect:/users";
    }
}