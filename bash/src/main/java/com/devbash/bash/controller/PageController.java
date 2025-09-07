package com.devbash.bash.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/home-en")
    public String homeEn() {
        return "index-en";
    }

    @GetMapping("/contact")
    public String contact() {
        return "contact";
    }

    @GetMapping("/home-ru")
    public String ruHome() {
        return "index-ru";
    }

    @GetMapping("/contact-ru")
    public String ruContact() {
        return "contact-ru";
    }
    @GetMapping("/home-de")
    public String deHome() {
        return "index-de";
    }

    @GetMapping("/contact-de")
    public String deContact() {
        return "contact-de";
    }
    @GetMapping("/home-az")
    public String azHome() {
        return "index-az";
    }

    @GetMapping("/contact-az")
    public String azContact() {
        return "contact-az";
    }
}