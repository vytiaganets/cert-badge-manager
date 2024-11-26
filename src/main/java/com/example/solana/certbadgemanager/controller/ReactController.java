package com.example.solana.certbadgemanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReactController {

    @GetMapping(value = {"/login", "/register", "/dashboard", "/{path:^(?!api).*}"})
    public String forwardToReact() {
        return "forward:/index.html";
    }
}