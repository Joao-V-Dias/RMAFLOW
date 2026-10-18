package com.joao.RMAFlow.controller;

import com.joao.RMAFlow.service.ModeloService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/modelos")
public class ModeloController {
    private final ModeloService service;

    public ModeloController(ModeloService service) {
        this.service = service;
    }

    @GetMapping
    public String listar(Model model){
        model.addAttribute("modelos", service.listar());
        return "modelo/listar";
    }
}
