package com.example;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EventoDAO dao = new EventoDAO();

    public static void main(String[] args) {
        dao.criarTabela();

        int opcao;
        do {
            System.out.println("\n==== MENU ====");
            System.out.println("1. Listar eventos");
            System.out.println("2. Buscar evento por ID");
            System.out.println("3. Cadastrar novo evento");
            System.out.println("4. Atualizar evento");
            System.out.println("5. Deletar evento");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1 -> listar();
                case 2 -> buscarPorId();
                case 3 -> cadastrar();
                case 4 -> atualizar();
                case 5 -> deletar();
                case 0 -> System.out.println("Encerrando...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private static void listar() {
        List<Evento> eventos = dao.listarTodos();
        eventos.forEach(System.out::println);
    }

    private static void buscarPorId() {
        int id = Validador.lerInteiro("Digite o ID do evento: ");
        Evento evento = dao.buscarPorId(id);
        System.out.println(evento != null ? evento : "Evento não encontrado.");
    }

    private static void cadastrar() {
        String nome = Validador.lerTextoObrigatorio("Nome do evento: ");
        String descricao = Validador.lerTextoOpcional("Descrição (opcional): ");
        int participantes = Validador.lerInteiro("Número de participantes: ");
        LocalDate data = Validador.lerData("Data do evento");


        Evento evento = new Evento(nome, descricao, participantes, data);
        dao.inserir(evento);
        System.out.println("Evento cadastrado!");
        System.out.println();
    }

    private static void atualizar() {
        int id = Validador.lerInteiro("ID do evento a atualizar: ");
        Evento evento = dao.buscarPorId(id);

        if (evento == null) {
            System.out.println("Evento não encontrado.");
            return;
        }

        String nome = Validador.lerTextoObrigatorio("Novo nome: ");
        String descricao = Validador.lerTextoOpcional("Nova descrição (opcional): ");
        int participantes = Validador.lerInteiro("Novo número de participantes: ");
        LocalDate data = Validador.lerData("Nova data");

        evento = new Evento(id, nome, descricao, participantes, data);
        dao.atualizar(evento);
        System.out.println("Evento atualizado.");
    }

    private static void deletar() {
        int id = Validador.lerInteiro("ID do evento a deletar: ");
        dao.deletar(id);
        System.out.println("Evento deletado.");
    }
}
