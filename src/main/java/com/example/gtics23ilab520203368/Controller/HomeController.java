package com.example.gtics23ilab520203368.Controller;

import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/home")
public class HomeController {
    @GetMapping(value = "")
    public String home(){
        return "recursoshumanos";
    }
}
