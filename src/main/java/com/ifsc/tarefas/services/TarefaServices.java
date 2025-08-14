package com.ifsc.tarefas.services;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsc.tarefas.model.Tarefa;
import com.ifsc.tarefas.repository.TarefaRepository;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


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


    // API para editar uma tarefa
    // Link do put
    // Preciso informar a anotação @RequestBody para informar que vou enviar um body e informar o id para saber qual tarefa eu quero editar
    @PutMapping("/editar/{id}")
    // Path variable para informar qual id eu quero editar
    public ResponseEntity<Tarefa> editarTarefa(@PathVariable Long id, @RequestBody Tarefa novaTarefa) {
        // Recebi um id
        // Quero procurar no banco esse id
        return tarefaRepository.findById(id).map(
            // Procuro pelo id e o que eu achar eu altero os atributos
            tarefa -> {
                // Pego o atrbuto antigo e coloco o que veio no novo
                tarefa.setTitulo(novaTarefa.getTitulo());
                // Coloca todo o resto que veio novo
                // Nova descrição
                tarefa.setDescricao(novaTarefa.getDescricao());
                // Novo responsável e etc
                tarefa.setResponsavel(novaTarefa.getResponsavel());
                tarefa.setDataLimite(novaTarefa.getDataLimite());
                tarefa.setStatus(novaTarefa.getStatus());
                tarefa.setPrioridade(novaTarefa.getPrioridade());
                // erro 200
                return ResponseEntity.ok(tarefaRepository.save(tarefa));




            }
        // Se não achar a tarefa retorna que não encontrou nada
        // erro 404
        ).orElse(ResponseEntity.notFound().build());
        
        
    } 

    // API para deletar uma tarefa
    // Recebe um id para saber qual tarefa eu quero deletar
    @DeleteMapping("deletar/{id}")
    public ResponseEntity<Tarefa> deletarTarefa(@PathVariable Long id) {
        // Verifico se esse id existe no meu banco
        // ! <-- negação
        // se NÃO existe
        if(!tarefaRepository.existsById(id)) {
            // Deu ruim, erro 404 - não encontrou
            return ResponseEntity.notFound().build();

        }

        // Vou no banco e deleto só o cara que mandei na URL
        tarefaRepository.deleteById(id);
        // gato 200 - deu bom!
        return ResponseEntity.ok().build();
        
    }

    


    
}
