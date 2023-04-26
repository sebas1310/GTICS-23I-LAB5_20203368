package com.example.gtics23ilab520203368.Controller;

import com.example.gtics23ilab520203368.Repository.EmployeeRepository;
import com.example.gtics23ilab520203368.Repository.JobRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;

    public HomeController(EmployeeRepository employeeRepository, JobRepository jobRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
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
    public String nuevo(Model model){
        model.addAttribute("puestos",jobRepository.findAll());
        return"nuevoempleado";
    }
}
