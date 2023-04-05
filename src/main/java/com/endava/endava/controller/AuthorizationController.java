package com.endava.endava.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.endava.endava.model.UserEntity;
import com.endava.endava.service.UserService;

import java.nio.file.AccessDeniedException;

@Controller
public class AuthorizationController {

    private final UserService userService;

    AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegisterForm(UserEntity userDetails) {
        return "sign-up";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/register")
    public String registerUser(@Valid UserEntity userEntity, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "sign-up";
        }
        userService.addUser(userEntity);
        return "redirect:login";
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        String message = "You do not have permission to perform this action.";
        return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

    @GetMapping("/exception")
    public String handleAccessDeniedException() {
        return "exception";
    }

}