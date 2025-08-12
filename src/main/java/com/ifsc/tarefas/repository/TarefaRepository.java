package com.ifsc.tarefas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ifsc.tarefas.model.Tarefa;

// Fala direto com o nosso banco de dados
// Com o JpaRepository já criamos o CRUD inicial
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {}
// Com o Jpa não é preciso criar: insert, select, select por id, delete

