package com.example.gtics23ilab520203368.Controller;

import com.example.gtics23ilab520203368.Repository.EmployeeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    final EmployeeRepository employeeRepository;

    public HomeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(value = "/home")
    public String home(){
        return "recursoshumanos";
    }

    @GetMapping(value = "/empleados")
    public String employees(Model model){
        model.addAttribute("listemp", employeeRepository.findAll());
        return "listempleados";
    }
    @GetMapping(value = "/nuevoempleado")
    public String nuevo(){

        return"nuevoempleado";
    }
}
