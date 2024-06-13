package br.com.dao;

import br.com.functions.Avaliacao;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.IAvaliacaoDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO implements IAvaliacaoDAO {

    // Usando a ConnectionFactory para obter a conexão
    private Connection connection = ConnectionFactory.getConnection();

    @Override
    public void create(Avaliacao avaliacao) throws Exception {
        String sql = "INSERT INTO Avaliacoes (sexo, idade, peso, altura, IMC, TMB, idAgendamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, avaliacao.getSexo());
            stmt.setInt(2, avaliacao.getIdade());
            stmt.setDouble(3, avaliacao.getPeso());
            stmt.setDouble(4, avaliacao.getAltura());
            stmt.setDouble(5, avaliacao.getIMC());
            stmt.setDouble(6, avaliacao.getTMB());
            stmt.setInt(7, avaliacao.getIdAgendamento());
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                avaliacao.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public Avaliacao getById(int id) throws Exception {
        String sql = "SELECT * FROM Avaliacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractAvaliacaoFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<Avaliacao> getAll() throws Exception {
        List<Avaliacao> avaliacoes = new ArrayList<>();
        String sql = "SELECT * FROM Avaliacoes";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Avaliacao avaliacao = extractAvaliacaoFromResultSet(rs);
                avaliacoes.add(avaliacao);
            }
        }
        return avaliacoes;
    }

    @Override
    public void update(Avaliacao avaliacao) throws Exception {
        String sql = "UPDATE Avaliacoes SET sexo = ?, idade = ?, peso = ?, altura = ?, IMC = ?, TMB = ?, idAgendamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, avaliacao.getSexo());
            stmt.setInt(2, avaliacao.getIdade());
            stmt.setDouble(3, avaliacao.getPeso());
            stmt.setDouble(4, avaliacao.getAltura());
            stmt.setDouble(5, avaliacao.getIMC());
            stmt.setDouble(6, avaliacao.getTMB());
            stmt.setInt(7, avaliacao.getIdAgendamento());
            stmt.setInt(8, avaliacao.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Avaliacoes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair uma Avaliacao do ResultSet atual
    private Avaliacao extractAvaliacaoFromResultSet(ResultSet rs) throws Exception {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setId(rs.getInt("id"));
        avaliacao.setSexo(rs.getString("sexo"));
        avaliacao.setIdade(rs.getInt("idade"));
        avaliacao.setPeso(rs.getDouble("peso"));
        avaliacao.setAltura(rs.getDouble("altura"));
        avaliacao.setIMC(rs.getDouble("IMC"));
        avaliacao.setTMB(rs.getDouble("TMB"));
        avaliacao.setIdAgendamento(rs.getInt("idAgendamento"));
        return avaliacao;
    }
}