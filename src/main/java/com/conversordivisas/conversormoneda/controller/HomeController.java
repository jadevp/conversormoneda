package com.conversordivisas.conversormoneda.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/convert")
    public String convert(@RequestParam String converter) {
        if (converter.equals("currency")) {
            return "redirect:/ConvertD";
        } else if (converter.equals("length")) {
            return "redirect:/convertL";
        } else {
            return "redirect:/";
        }
    }
}