package com.example.project.controller;

import com.example.project.model.Postagem;
import com.example.project.model.Usuario;
import com.example.project.repository.PostagemRepository;
import com.example.project.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    PostagemRepository postagemRepository;

    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity registrarUsuario(@RequestBody Usuario newUsuario) {

        List<Usuario> users = usuarioRepository.findAll();

        for (Usuario u : users) {
            if (newUsuario.getNome() == u.getNome() && newUsuario.getSenha() == u.getSenha()) {
                return ResponseEntity.status(400).build();
            }
        }

        usuarioRepository.save(newUsuario);

        return ResponseEntity.status(201).build();
    }

    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity login() {

        return ResponseEntity.status(200).build();
    }

    @CrossOrigin
    @PostMapping("/posts")
    public ResponseEntity criarPost(@RequestBody Postagem newPost, HttpServletRequest request) {

        Usuario usuario = (Usuario) request.getAttribute("usuario");

        newPost.setAutor(usuario);
        newPost.setCreatedAt(LocalDateTime.now());

        postagemRepository.save(newPost);

        return ResponseEntity.status(201).build();
    }

    @CrossOrigin
    @PutMapping("/posts/{id}")
    public ResponseEntity editarPost(@PathVariable int id, @RequestBody Postagem editedPost, HttpServletRequest request) {

        List<Postagem> postagemList = postagemRepository.findAll();
        editedPost.setId(id);

        if (postagemRepository.existsById(id)) {

            Optional<Postagem> oldPost = postagemRepository.findById(id);

            editedPost.setCreatedAt(oldPost.get().getCreatedAt());

            Usuario usuario = (Usuario) request.getAttribute("usuario");
            editedPost.setAutor(usuario);

            if (usuario.getId() != oldPost.get().getAutor().getId()) {
                return ResponseEntity.status(401).build();
            }

            postagemRepository.save(editedPost);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }

    @CrossOrigin
    @GetMapping("posts")
    public ResponseEntity listarPosts() {

        List<Postagem> postagemList = postagemRepository.findAllByOrderByCreatedAtDesc();

        if (postagemList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(postagemList);
    }

    @CrossOrigin
    @GetMapping("posts/{id}")
    public ResponseEntity exibirPost(@PathVariable int id) {

        List<Postagem> postagemList = postagemRepository.findAll();

        if (postagemList.isEmpty()) {
            return ResponseEntity.status(204).build();
        }

        for (Postagem p : postagemList) {
            if (p.getId() == id) {
                return ResponseEntity.status(200).body(p);
            }
        }

        return ResponseEntity.status(404).build();
    }

    @CrossOrigin
    @DeleteMapping("/posts/{id}")
    public ResponseEntity deletarPost(@PathVariable int id) {

        if (postagemRepository.existsById(id)) {

            postagemRepository.deleteById(id);

            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }
}
