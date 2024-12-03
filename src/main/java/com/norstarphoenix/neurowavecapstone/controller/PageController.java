package com.norstarphoenix.neurowavecapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String homePage() {
        return "/index"; // Refers to home.html in src/main/resources/templates/
    }

    @GetMapping("/adventures")
    public String adventuresPage() {
        return "/adventures"; // Refers to adventures.html in src/main/resources/templates/
    }

    @GetMapping("/investors")
    public String investorsPage() {
        return "/investors"; // Refers to investors.html in src/main/resources/templates/
    }
}