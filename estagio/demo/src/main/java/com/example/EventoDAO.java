package com.example;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventoDAO {
    private final String url = "jdbc:sqlite:eventos.db";

    public Connection conectar() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
            return null;
        }
    }

    public void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS eventos (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nome TEXT NOT NULL," +
                "descricao TEXT," +
                "participantes INTEGER NOT NULL," +
                "data TEXT NOT NULL" +
                ");";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    public void inserir(Evento e) {
        String sql = "INSERT INTO eventos(nome, descricao, participantes, data) VALUES (?, ?, ?, ?)";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, e.getNome());
            pstmt.setString(2, e.getDescricao());
            pstmt.setInt(3, e.getParticipantes());
            pstmt.setString(4, e.getData().toString());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao inserir: " + ex.getMessage());
        }
    }

    public List<Evento> listarTodos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection conn = conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                lista.add(new Evento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("participantes"),
                        LocalDate.parse(rs.getString("data"))
                ));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

    public Evento buscarPorId(int id) {
        String sql = "SELECT * FROM eventos WHERE id = ?";
        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Evento(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getInt("participantes"),
                        LocalDate.parse(rs.getString("data"))
                );
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }
        return null;
    }

    public void atualizar(Evento e) {
        String sql = "UPDATE eventos SET nome = ?, descricao = ?, participantes = ?, data = ? WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, e.getNome());
            pstmt.setString(2, e.getDescricao());
            pstmt.setInt(3, e.getParticipantes());
            pstmt.setString(4, e.getData().toString());
            pstmt.setInt(5, e.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Erro ao atualizar: " + ex.getMessage());
        }
    }

    public void deletar(int id) {
        String sql = "DELETE FROM eventos WHERE id = ?";

        try (Connection conn = conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
