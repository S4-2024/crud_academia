package br.com.dao;
import br.com.interfaces.ICartoesCreditoDAO;
import br.com.models.CartaoCredito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CartoesCreditoDAO implements ICartoesCreditoDAO {

    private Connection connection;

    // Construtor que recebe a conexão com o banco de dados
    public CartoesCreditoDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(CartaoCredito cartaoCredito) throws Exception {
        String sql = "INSERT INTO CartoesCredito (idUsuario, numero, nomeTitular, dataValidade, cvv) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, cartaoCredito.getIdUsuario());
            stmt.setString(2, cartaoCredito.getNumero());
            stmt.setString(3, cartaoCredito.getNomeTitular());
            stmt.setString(4, cartaoCredito.getDataValidade());
            stmt.setString(5, cartaoCredito.getCvv());
            stmt.executeUpdate();

            // Recuperando o ID gerado automaticamente
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cartaoCredito.setId(rs.getInt(1));
            }
        }
    }

    @Override
    public CartaoCredito getById(int id) throws Exception {
        String sql = "SELECT * FROM CartoesCredito WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractCartaoCreditoFromResultSet(rs);
            }
            return null;
        }
    }

    @Override
    public List<CartaoCredito> getAll() throws Exception {
        List<CartaoCredito> cartoesCredito = new ArrayList<>();
        String sql = "SELECT * FROM CartoesCredito";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                CartaoCredito cartaoCredito = extractCartaoCreditoFromResultSet(rs);
                cartoesCredito.add(cartaoCredito);
            }
        }
        return cartoesCredito;
    }

    @Override
    public void update(CartaoCredito cartaoCredito) throws Exception {
        String sql = "UPDATE CartoesCredito SET idUsuario = ?, numero = ?, nomeTitular = ?, dataValidade = ?, cvv = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, cartaoCredito.getIdUsuario());
            stmt.setString(2, cartaoCredito.getNumero());
            stmt.setString(3, cartaoCredito.getNomeTitular());
            stmt.setString(4, cartaoCredito.getDataValidade());
            stmt.setString(5, cartaoCredito.getCvv());
            stmt.setInt(6, cartaoCredito.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM CartoesCredito WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Método auxiliar para extrair um CartaoCredito do ResultSet atual
    private CartaoCredito extractCartaoCreditoFromResultSet(ResultSet rs) throws Exception {
        CartaoCredito cartaoCredito = new CartaoCredito();
        cartaoCredito.setId(rs.getInt("id"));
        cartaoCredito.setIdUsuario(rs.getInt("idUsuario"));
        cartaoCredito.setNumero(rs.getString("numero"));
        cartaoCredito.setNomeTitular(rs.getString("nomeTitular"));
        cartaoCredito.setDataValidade(rs.getString("dataValidade"));
        cartaoCredito.setCvv(rs.getString("cvv"));
        cartaoCredito.setDataCriacao(rs.getTimestamp("dataCriacao"));
        return cartaoCredito;
    }
}
