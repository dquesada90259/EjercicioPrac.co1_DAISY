package com.ejercicio.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/quejas")
public class FormularioController {

    @GetMapping
    public String mostrarFormulario(Model model) {
        model.addAttribute("titulo", "Formulario de Quejas");
        model.addAttribute("contenido", "quejasContent");
        return "index";
    }

    @PostMapping("/enviar")
    public String procesarFormulario(
            @RequestParam String nombre,
            @RequestParam String correo,
            @RequestParam String mensaje,
            Model model) {

        System.out.println("Nombre: " + nombre);
        System.out.println("Correo: " + correo);
        System.out.println("Mensaje: " + mensaje);

        model.addAttribute("titulo", "Formulario enviado");
        model.addAttribute("contenido", "confirmacionQueja");
        return "index";
    }
}
