package com.conversordivisas.conversormoneda.controller;
import com.conversordivisas.conversormoneda.model.ConversionLong;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ConversorLongController {

        @GetMapping("/convertL")
    public String showForm(Model model) {
        model.addAttribute("conversionLong", new ConversionLong());
        return "convertlong";
    }
    @PostMapping("/convertL")
    public String convert(@ModelAttribute("conversionLong") ConversionLong conversion, Model model) {
        conversion.convert();
        model.addAttribute("result", conversion.getResult());
        return "convertlong";
    }

    @PostMapping("/convertL2")
    public String convert(@ModelAttribute("conversionLong") ConversionLong conversion) {
        conversion.convert();
        return "convertlong";
    }
}
