package br.com.dao;

import br.com.functions.Agendamentos;
import br.com.interfaces.IAgendamentosDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AgendamentosDAO implements IAgendamentosDAO {
    private Connection connection;

    public AgendamentosDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Agendamentos agendamento) throws Exception {
        String sql = "INSERT INTO agendamentos (dia, idFuncionario, idCliente) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(agendamento.getDia().getTime()));
            stmt.setInt(2, agendamento.getIdFuncionario());
            stmt.setInt(3, agendamento.getIdCliente());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Agendamento criado com sucesso.");
            } else {
                System.out.println("Falha ao agendar.");
            }
        } catch (SQLException e) {
            System.out.println("Falha ao agendar devido a: " + e.getMessage());
            throw e;
        }
    }

    @Override
    public Agendamentos read(int id) throws Exception {
        String sql = "SELECT * FROM agendamentos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Agendamentos agendamento = new Agendamentos();
                    agendamento.setId(rs.getInt("id"));
                    agendamento.setDia(rs.getDate("dia"));
                    agendamento.setIdFuncionario(rs.getInt("idFuncionario"));
                    agendamento.setIdCliente(rs.getInt("idCliente"));
                    return agendamento;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void update(Agendamentos agendamento) throws Exception {
        String sql = "UPDATE agendamentos SET dia = ?, idFuncionario = ?, idCliente = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setDate(1, new java.sql.Date(agendamento.getDia().getTime()));
            stmt.setInt(2, agendamento.getIdFuncionario());
            stmt.setInt(3, agendamento.getIdCliente());
            stmt.setInt(4, agendamento.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM agendamentos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("O agendamento foi cancelado.");
            } else {
                System.out.println("Nenhum agendamento encontrado com o ID fornecido.");
            }
        }
    }


    @Override
    public List<Agendamentos> listAll() throws Exception {
        List<Agendamentos> agendamentos = new ArrayList<>();
        String sql = "SELECT * FROM agendamentos";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setId(rs.getInt("id"));
                agendamento.setDia(rs.getDate("dia"));
                agendamento.setIdFuncionario(rs.getInt("idFuncionario"));
                agendamento.setIdCliente(rs.getInt("idCliente"));
                agendamentos.add(agendamento);
            }
        }
        return agendamentos;
    }
}
