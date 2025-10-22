package com.ejercicio.demo.controllers;

import com.ejercicio.demo.domain.Libro;
import com.ejercicio.demo.service.LibroService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService libroService;

    public LibroController(LibroService libroService) {
        this.libroService = libroService;
    }

    @GetMapping
    public String listarLibros(Model model) {
        model.addAttribute("libros", libroService.listarTodos());
        return "libros/lista"; // Vista Thymeleaf
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoLibro(Model model) {
        model.addAttribute("libro", new Libro());
        return "libros/formulario"; // Vista Thymeleaf
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro) {
        libroService.guardar(libro);
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
    var optionalLibro = libroService.buscarPorId(id);

    if (optionalLibro.isEmpty()) {
        // Redirige a lista o muestra error 404
        return "redirect:/libros"; // O donde prefieras
    }

    model.addAttribute("libro", optionalLibro.get());
    model.addAttribute("titulo", "Editar Libro");
    model.addAttribute("contenido", "libros/formularioContenido");

    return "index";
}



    @GetMapping("/eliminar/{id}")
    public String eliminarLibro(@PathVariable Long id) {
        libroService.eliminar(id);
        return "redirect:/libros";
    }
}