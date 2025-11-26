package com.exemplo.gerenciador_tarefas.controller;

import com.exemplo.gerenciador_tarefas.model.Tarefa;
import com.exemplo.gerenciador_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.HashMap;

@Controller
public class TarefaController {

    @Autowired
    private TarefaRepository repository;

    // --- ROTA 1: TELA PRINCIPAL (READ) ---
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("tarefas", repository.findAll());
        return "index";
    }

    // --- ROTA 2: CRIAR TAREFA (CREATE) ---
    @PostMapping("/add")
    public String add(@RequestParam String titulo) {
        if (titulo != null && !titulo.isBlank()) {
            repository.save(new Tarefa(titulo));
        }
        return "redirect:/";
    }

    // --- ROTA 3: EXCLUIR TAREFA (DELETE) ---
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }

    // --- ROTA 4: API REST PARA ATUALIZAR STATUS (SEM RELOAD) ---
    @PostMapping("/api/toggle/{id}")
    @ResponseBody // Indica que o retorno é JSON, não uma tela HTML
    public ResponseEntity<Map<String, Object>> toggleStatus(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();

        return repository.findById(id).map(tarefa -> {
            String novoStatus = tarefa.getStatus().equals("pendente") ? "concluído" : "pendente";
            tarefa.setStatus(novoStatus);
            repository.save(tarefa);

            response.put("success", true);
            response.put("novo_status", novoStatus);
            return ResponseEntity.ok(response);
        }).orElse(ResponseEntity.notFound().build());
    }
}