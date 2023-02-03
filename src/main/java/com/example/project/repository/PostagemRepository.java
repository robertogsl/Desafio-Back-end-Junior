package com.example.project.repository;

import com.example.project.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Integer> {

    List<Postagem> findAllByOrderByCreatedAtDesc();
}
