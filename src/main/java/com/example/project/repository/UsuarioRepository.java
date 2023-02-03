package com.example.project.repository;

import com.example.project.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByNomeAndSenha(String nome, String senha);
}
