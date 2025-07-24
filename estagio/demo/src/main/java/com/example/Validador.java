package com.example;

import java.time.LocalDate;
import java.util.Scanner;

public class Validador {
    private final static Scanner scanner = new Scanner(System.in);

    public static String lerTextoObrigatorio(String mensagem) {
        String input;
        do {
            System.out.print(mensagem);
            input = scanner.nextLine().trim();
        } while (input.isEmpty());
        return input;
    }

    public static String lerTextoOpcional(String mensagem) {
        System.out.print(mensagem);
        return scanner.nextLine().trim();
    }

    public static int lerInteiro(String mensagem) {
        int valor;
        while (true) {
            System.out.print(mensagem);
            try {
                valor = Integer.parseInt(scanner.nextLine());
                if (valor > 0) return valor;
                System.out.println("Número deve ser positivo.");
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida.");
            }
        }
    }

    public static LocalDate lerData(String mensagem) {
        LocalDate data = null;
        while (data == null) {
            System.out.print(mensagem + " (formato: AAAA-MM-DD): ");
            try {
                data = LocalDate.parse(scanner.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Data inválida.");
            }
        }
        return data;
    }
}