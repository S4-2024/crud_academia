package br.com.dao;

import br.com.gym.Exercicio;
import br.com.interfaces.IExerciciosDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciciosDAO implements IExerciciosDAO {

    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public ExerciciosDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Exercicio exercicio) throws Exception {
        String sql = "INSERT INTO Exercicios (nome, musculo, categoria) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getMusculo());
            stmt.setString(3, exercicio.getCategoria());
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                exercicio.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public Exercicio getById(int id) throws Exception {
        String sql = "SELECT * FROM Exercicios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractExercicioFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<Exercicio> getAll() throws Exception {
        List<Exercicio> exercicios = new ArrayList<>();
        String sql = "SELECT * FROM Exercicios";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exercicio exercicio = extractExercicioFromResultSet(rs);
                exercicios.add(exercicio);
            }
        }
        return exercicios;
    }

    @Override
    public void update(Exercicio exercicio) throws Exception {
        String sql = "UPDATE Exercicios SET nome = ?, musculo = ?, categoria = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, exercicio.getNome());
            stmt.setString(2, exercicio.getMusculo());
            stmt.setString(3, exercicio.getCategoria());
            stmt.setInt(4, exercicio.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM Exercicios WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair um Exercicio do ResultSet atual
    private Exercicio extractExercicioFromResultSet(ResultSet rs) throws Exception {
        Exercicio exercicio = new Exercicio();
        exercicio.setId(rs.getInt("id"));
        exercicio.setNome(rs.getString("nome"));
        exercicio.setMusculo(rs.getString("musculo"));
        exercicio.setCategoria(rs.getString("categoria"));
        return exercicio;
    }
}
