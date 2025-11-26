package com.exemplo.gerenciador_tarefas.model;

import jakarta.persistence.*;

@Entity
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String status; // "pendente" ou "concluído"

    public Tarefa() {
        this.status = "pendente";
    }

    public Tarefa(String titulo) {
        this.titulo = titulo;
        this.status = "pendente";
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}