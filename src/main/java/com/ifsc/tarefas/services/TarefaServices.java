package com.ifsc.tarefas.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsc.tarefas.model.Tarefa;
import com.ifsc.tarefas.repository.TarefaRepository;

@RestController // Anotação que indica que essa classe é um service
@RequestMapping("/tarefas") // Anotação que define padrão url exemplo: /tarefas/inserir
public class TarefaServices {


    // Injetando o repositório de tarefa para usar no service e buscar coisas do banco
    private final TarefaRepository tarefaRepository;

    public TarefaServices(TarefaRepository tarefaRepository) {
        this.tarefaRepository = tarefaRepository;
    }

    // Anotação para GET
    // Para chamar minha API de buscar todas eu uso --> /tarefas/buscar-todos <-- 
    @GetMapping("/buscar-todos")
    public ResponseEntity<?> buscarTodas() {
        // Uso o repository para buscar todas as tarefas
        return ResponseEntity.ok(tarefaRepository.findAll());
    }


    // API para criar uma nova tarefa
    // ANotação pra post --> /tarefas/inserir;
    // preciso informar a anotação @RequestBody para informar que vou enviar um body
    @PostMapping("/inserir")
    public ResponseEntity<Tarefa> criarNovaTarefa(@RequestBody Tarefa tarefa) {

        return ResponseEntity.ok(tarefaRepository.save(tarefa));
    }

    


    
}
