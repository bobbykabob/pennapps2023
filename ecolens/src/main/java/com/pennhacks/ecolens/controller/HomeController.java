package com.pennhacks.ecolens.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "index"; // This maps to your HTML file (index.html)
    }
}
