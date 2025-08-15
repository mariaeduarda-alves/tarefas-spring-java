package com.ifsc.tarefas.services;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ifsc.tarefas.model.Categoria;
import com.ifsc.tarefas.repository.CategoriaRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/categorias")
public class CategoriaServices {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServices(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @GetMapping("/buscar-categorias")
    public ResponseEntity<?> buscarCategorias() {
        return ResponseEntity.ok(categoriaRepository.findAll());
    }

    @PostMapping("/inserir")
    public ResponseEntity<Categoria> criarNovaCategoria(@RequestBody Categoria categoria) {
        
        return ResponseEntity.ok(categoriaRepository.save(categoria));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Categoria> editarCategorias(@PathVariable Long id, @RequestBody Categoria novaCategoria) {
        System.out.println("id que vou salvar" + id);
        return categoriaRepository.findById(id).map(
            categoria -> {
                categoria.setNome(novaCategoria.getNome());

                return ResponseEntity.ok(categoriaRepository.save(categoria));
            }
        ).orElse(ResponseEntity.notFound().build());
    }
    
    
    
}
