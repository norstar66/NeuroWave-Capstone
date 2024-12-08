package com.norstarphoenix.neurowavecapstone.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    // Mapping for the home page
    @GetMapping("/")
    public String homePage() {
        return "index"; // Refers to index.html in src/main/resources/templates
    }

    // Mapping for the adventures page
    @GetMapping("/adventures")
    public String adventuresPage() {
        return "adventures"; // Refers to adventures.html in src/main/resources/templates
    }

    // Mapping for the investors page
    @GetMapping("/investors")
    public String investorsPage() {
        return "investors"; // Refers to investors.html in src/main/resources/templates
    }
}