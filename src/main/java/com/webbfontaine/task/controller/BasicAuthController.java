package com.webbfontaine.task.controller;

import com.webbfontaine.task.model.AuthenticationBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/")
public class BasicAuthController {

    @GetMapping
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }

}
