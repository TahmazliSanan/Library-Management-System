package org.pronet.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.pronet.entities.User;
import org.pronet.payloads.RegistrationDto;
import org.pronet.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final UserService userService;

    @GetMapping("/auth/register")
    public String registerUserView(
            Model model) {
        RegistrationDto registrationDto = new RegistrationDto();
        model.addAttribute("registrationDto", registrationDto);
        return "auth/register-user";
    }

    @PostMapping("/auth/register")
    public String registerUser(
            @Valid @ModelAttribute("registrationDto") RegistrationDto registrationDto,
            BindingResult bindingResult) {
        User existUser = userService.findUserByUsername(registrationDto.getUsername());
        if (existUser != null &&
                existUser.getUsername() != null &&
                !existUser.getUsername().isEmpty()) {
            return "redirect:/auth/register?fail";
        }
        if (bindingResult.hasErrors()) {
            return "auth/register-user";
        }
        userService.registerUser(registrationDto);
        return "redirect:/auth/register?success";
    }

    @GetMapping("/auth/login")
    public String loginUserView() {
        return "auth/login-user";
    }
}