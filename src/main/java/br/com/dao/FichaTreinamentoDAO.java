package br.com.dao;

import br.com.infra.ConnectionFactory;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import br.com.gym.FichaTreinamento;

public class FichaTreinamentoDAO {

    public FichaTreinamentoDAO(Connection connection) {
    }

    public FichaTreinamentoDAO() {
        
    }

    public void adicionar(FichaTreinamento ficha) throws SQLException {
        String sql = "INSERT INTO fichatreinamento (cliente_id, cliente_nome, exercicio_nome, exercicio_id, anotacao) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ficha.getClienteId());
            stmt.setString(2, ficha.getClienteNome());
            stmt.setString(3, ficha.getExercicioNome());
            stmt.setInt(4, ficha.getExercicioId());
            stmt.setString(5, ficha.getAnotacao());
            stmt.executeUpdate();
        }
    }

    public void atualizar(FichaTreinamento ficha) throws SQLException {
        String sql = "UPDATE fichatreinamento SET cliente_id = ?, cliente_nome = ?, exercicio_nome = ?, exercicio_id = ?, anotacao = ? WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, ficha.getClienteId());
            stmt.setString(2, ficha.getClienteNome());
            stmt.setString(3, ficha.getExercicioNome());
            stmt.setInt(4, ficha.getExercicioId());
            stmt.setString(5, ficha.getAnotacao());
            stmt.setInt(6, ficha.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM fichatreinamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public FichaTreinamento buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM fichatreinamento WHERE id = ?";
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    FichaTreinamento ficha = new FichaTreinamento();
                    ficha.setId(rs.getInt("id"));
                    ficha.setClienteId(rs.getInt("cliente_id"));
                    ficha.setClienteNome(rs.getString("cliente_nome"));
                    ficha.setExercicioNome(rs.getString("exercicio_nome"));
                    ficha.setExercicioId(rs.getInt("exercicio_id"));
                    ficha.setAnotacao(rs.getString("anotacao"));
                    return ficha;
                }
            }
        }
        return null;
    }

    public List<FichaTreinamento> listarTodos() throws SQLException {
        String sql = "SELECT * FROM fichatreinamento";
        List<FichaTreinamento> lista = new ArrayList<>();
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                FichaTreinamento ficha = new FichaTreinamento();
                ficha.setId(rs.getInt("id"));
                ficha.setClienteId(rs.getInt("cliente_id"));
                ficha.setClienteNome(rs.getString("cliente_nome"));
                ficha.setExercicioNome(rs.getString("exercicio_nome"));
                ficha.setExercicioId(rs.getInt("exercicio_id"));
                ficha.setAnotacao(rs.getString("anotacao"));
                lista.add(ficha);
            }
        }
        return lista;
    }
}
