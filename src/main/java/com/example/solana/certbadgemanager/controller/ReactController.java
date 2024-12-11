package com.example.solana.certbadgemanager.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class ReactController {
    @RequestMapping(value = "/{path:[^\\.]*}")
    public String forwardToIndex(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        if (requestURI.startsWith("/v3/api-docs") ||
                requestURI.startsWith("/swagger-ui") ||
                requestURI.equals("/swagger-ui.html")) {
            return null;
        }
        return "forward:/index.html";
    }
}
