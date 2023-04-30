package com.example.gtics23ilab520203368.Controller;

import com.example.gtics23ilab520203368.Repository.DepartmentRepository;
import com.example.gtics23ilab520203368.Repository.EmployeeRepository;
import com.example.gtics23ilab520203368.Repository.JobRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@Controller
public class HomeController {
    final EmployeeRepository employeeRepository;
    final JobRepository jobRepository;
    final DepartmentRepository departmentRepository;

    public HomeController(EmployeeRepository employeeRepository, JobRepository jobRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.jobRepository = jobRepository;
        this.departmentRepository = departmentRepository;
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
        model.addAttribute("bosses", employeeRepository.showBoss());
        model.addAttribute("department", departmentRepository.findAll());
        return"nuevoempleado";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("texto") String texto, Model model){
        model.addAttribute("listemp",employeeRepository.search(texto));
        return "listempleados";
    }

    @Transactional
    @GetMapping(value = "/deleteEmploye")
    public String delete(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes){
        System.out.println("id: " + id);
        employeeRepository.deleteEmployee(id);
        redirectAttributes.addFlashAttribute("msj", "Empleado borrado exitosamente");
        return "redirect:/empleados";
    }

    @Transactional
    @PostMapping("/guardar")
    public String guardarEmployee(RedirectAttributes redirectAttributes,
                                  @RequestParam("nombre") String nombre,
                                  @RequestParam("apellido") String apellido,
                                  @RequestParam("email") String email,
                                  @RequestParam("contrasena") String contrasena,
                                  @RequestParam("puesto") String puesto,
                                  @RequestParam("sueldo")BigDecimal sueldo,
                                  @RequestParam("jefe") Integer jefeid,
                                  @RequestParam("departamento") Integer departamento){


        employeeRepository.newEmployee(nombre,apellido,email,contrasena,puesto,sueldo,jefeid,departamento);
        redirectAttributes.addFlashAttribute("msj", "Empleado creado exitosamente");
        return"redirect:/empleados";
    }
}
