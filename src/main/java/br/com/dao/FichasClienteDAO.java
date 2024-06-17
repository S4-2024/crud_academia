package br.com.dao;

import br.com.enums.Type;
import br.com.gym.FichaCliente;
import br.com.interfaces.IFichasClienteDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FichasClienteDAO implements IFichasClienteDAO {

    private static Connection connection;
    private int id;
    private String nomeAluno;
    private String nomeTreinador;
    private double pesoInicial;
    private Date dataCriacao;
    private Type tipo;
    private String[] exerciciosInferiores;
    private String[] exerciciosPosteriores;
    private String[] exerciciosSuperiores;
    private String[] exerciciosCore;
    private int idCliente;

    // Construtor padrão
    public FichasClienteDAO(Connection connection) {
        this.connection = connection;
    }

    // Novo construtor para aceitar os parâmetros necessários
    public FichasClienteDAO(int id, String nomeAluno, String nomeTreinador, double pesoInicial, Date dataCriacao, Type tipo, String[] exerciciosInferiores, String[] exerciciosPosteriores, String[] exerciciosSuperiores, String[] exerciciosCore, int idCliente) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.nomeTreinador = nomeTreinador;
        this.pesoInicial = pesoInicial;
        this.dataCriacao = dataCriacao;
        this.tipo = tipo;
        this.exerciciosInferiores = exerciciosInferiores;
        this.exerciciosPosteriores = exerciciosPosteriores;
        this.exerciciosSuperiores = exerciciosSuperiores;
        this.exerciciosCore = exerciciosCore;
        this.idCliente = idCliente;
    }

    @Override
    public void create(FichaCliente fichaCliente) throws Exception {
        String sql = "INSERT INTO FichasCliente (idCliente, data, notas) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, fichaCliente.getIdCliente());
            stmt.setDate(2, new java.sql.Date(fichaCliente.getData().getTime()));
            stmt.setString(3, fichaCliente.getNotas());
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                fichaCliente.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public FichaCliente getById(int id) throws Exception {
        String sql = "SELECT * FROM FichasCliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractFichaClienteFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<FichaCliente> getAll() throws Exception {
        List<FichaCliente> fichasClientes = new ArrayList<>();
        String sql = "SELECT * FROM FichasCliente";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                FichaCliente fichaCliente = extractFichaClienteFromResultSet(rs);
                fichasClientes.add(fichaCliente);
            }
        }
        return fichasClientes;
    }

    @Override
    public void update(FichaCliente fichaCliente) throws Exception {
        String sql = "UPDATE FichasCliente SET idCliente = ?, data = ?, notas = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, fichaCliente.getIdCliente());
            stmt.setDate(2, new java.sql.Date(fichaCliente.getData().getTime()));
            stmt.setString(3, fichaCliente.getNotas());
            stmt.setInt(4, fichaCliente.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM FichasCliente WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair uma FichaCliente do ResultSet atual
    private FichaCliente extractFichaClienteFromResultSet(ResultSet rs) throws Exception {
        FichaCliente fichaCliente = new FichaCliente();
        fichaCliente.setId(rs.getInt("id"));
        fichaCliente.setIdCliente(rs.getInt("idCliente"));
        fichaCliente.setData(rs.getDate("data"));
        fichaCliente.setNotas(rs.getString("notas"));
        return fichaCliente;
    }
}
