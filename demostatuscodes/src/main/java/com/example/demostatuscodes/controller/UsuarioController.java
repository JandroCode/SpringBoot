package com.example.demostatuscodes.controller;

import com.example.demostatuscodes.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UsuarioController {


    private List<Usuario> usuarios = List.of(
            Usuario.builder().id(1L).username("Juan").edad(34).build(),
            Usuario.builder().id(2L).username("Luisa").edad(12).build()

    );

    // 200 OK
    @GetMapping
    public ResponseEntity<List<Usuario>> obtenerUsuarios(){
        return ResponseEntity.ok(usuarios);
    }

    // 201 CREATED
    @PostMapping
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario usuario){
        // Aquí vendría la lógica del alta del usuario en bd

        return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
    }

    // 404 NOT FOUND
    @GetMapping("/{id}")
    public  ResponseEntity<Usuario> obtenerPorId(@PathVariable Long id){

        Optional<Usuario> usuario = usuarios.stream()
                .filter( u -> u.getId().equals(id))
                .findFirst();

        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    // 400 BAD REQUEST
    @PostMapping("/validar")
    public ResponseEntity<String> validarEdad(@RequestBody Usuario usuario){

        if(usuario.getEdad() < 18){
            return ResponseEntity
                    .badRequest()
                    .body("La edad mínima es 18 años");
        }

        return ResponseEntity.ok("Edad válida");

    }


}
