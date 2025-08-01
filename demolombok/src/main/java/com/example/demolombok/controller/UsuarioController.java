package com.example.demolombok.controller;

import com.example.demolombok.model.Usuario;
import com.example.demolombok.model.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public List<Usuario> lista(){
        return usuarioService.obtenerUsuarios();
    }
}
