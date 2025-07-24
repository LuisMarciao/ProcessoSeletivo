package com.example;

import java.time.LocalDate;

public class Evento {
    private int id;
    private String nome;
    private String descricao;
    private int participantes;
    private LocalDate data;

    public Evento(String nome, String descricao, int participantes, LocalDate data) {
        this.nome = nome;
        this.descricao = descricao;
        this.participantes = participantes;
        this.data = data;

    }

    public Evento(int id, String nome, String descricao, int participantes, LocalDate data) {
        this(nome, descricao, participantes, data);
        this.id = id;
    }

    // Getters e Setters

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }
    public int getParticipantes() { return participantes; }
    public LocalDate getData() { return data; }

    public void setId(int id) { this.id = id; }

    @Override
    public String toString() {
        return String.format("%d - %s (%s) | %d participantes | Data: %s",
                id, nome, descricao != null ? descricao : "sem descrição", participantes, data);
    }
}
