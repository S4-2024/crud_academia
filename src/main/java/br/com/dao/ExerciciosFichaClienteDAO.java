package br.com.dao;

import br.com.gym.ExercicioFichaCliente;
import br.com.interfaces.IExerciciosFichaClienteDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ExerciciosFichaClienteDAO implements IExerciciosFichaClienteDAO {

    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public ExerciciosFichaClienteDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ExercicioFichaCliente exercicioFichaCliente) throws Exception {
        String sql = "INSERT INTO ExerciciosFichaCliente (idFichaCliente, idExercicio, series, repeticoes) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, exercicioFichaCliente.getIdFichaCliente());
            stmt.setInt(2, exercicioFichaCliente.getIdExercicio());
            stmt.setInt(3, exercicioFichaCliente.getSeries());
            stmt.setInt(4, exercicioFichaCliente.getRepeticoes());
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                exercicioFichaCliente.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public ExercicioFichaCliente getById(int id) throws Exception {
        String sql = "SELECT * FROM ExerciciosFichaCliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractExercicioFichaClienteFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<ExercicioFichaCliente> getAll() throws Exception {
        List<ExercicioFichaCliente> exerciciosFichaCliente = new ArrayList<>();
        String sql = "SELECT * FROM ExerciciosFichaCliente";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ExercicioFichaCliente exercicioFichaCliente = extractExercicioFichaClienteFromResultSet(rs);
                exerciciosFichaCliente.add(exercicioFichaCliente);
            }
        }
        return exerciciosFichaCliente;
    }

    @Override
    public void update(ExercicioFichaCliente exercicioFichaCliente) throws Exception {
        String sql = "UPDATE ExerciciosFichaCliente SET idFichaCliente = ?, idExercicio = ?, series = ?, repeticoes = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, exercicioFichaCliente.getIdFichaCliente());
            stmt.setInt(2, exercicioFichaCliente.getIdExercicio());
            stmt.setInt(3, exercicioFichaCliente.getSeries());
            stmt.setInt(4, exercicioFichaCliente.getRepeticoes());
            stmt.setInt(5, exercicioFichaCliente.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM ExerciciosFichaCliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair um ExercicioFichaCliente do ResultSet atual
    private ExercicioFichaCliente extractExercicioFichaClienteFromResultSet(ResultSet rs) throws Exception {
        ExercicioFichaCliente exercicioFichaCliente = new ExercicioFichaCliente();
        exercicioFichaCliente.setId(rs.getInt("id"));
        exercicioFichaCliente.setIdFichaCliente(rs.getInt("idFichaCliente"));
        exercicioFichaCliente.setIdExercicio(rs.getInt("idExercicio"));
        exercicioFichaCliente.setSeries(rs.getInt("series"));
        exercicioFichaCliente.setRepeticoes(rs.getInt("repeticoes"));
        return exercicioFichaCliente;
    }
}
