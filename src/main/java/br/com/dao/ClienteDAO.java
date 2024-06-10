package br.com.dao;

import br.com.infra.ConnectionFactory;
import br.com.models.Cliente;
import br.com.enums.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements IClienteDAO {

    @Override
    public Cliente save(Cliente cliente) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Clientes(nome,email,senha,pagamento) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //retornando o id para gnt  Statement.RETURN_GENERATED_KEYS

            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getSenha());
            preparedStatement.setString(4, String.valueOf(cliente.getPagamento()));

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir cliente, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    cliente.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir cliente, nenhum ID obtido.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return cliente;
    }

    @Override
    public Cliente login(Cliente cliente) {
        return null;
    }

    @Override
    public Cliente update(Cliente cliente) {
        return null;
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT id,nome,email,senha,pagamento FROM Clientes ";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();

            while (rs.next()) {
              int id =   rs.getInt("id");
              String nome =   rs.getString("nome");
              String email =   rs.getString("email");
              String senha =   rs.getString("senha");
              Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

              Cliente  cliente  = new Cliente(nome,email,senha,pagamento);
              clientes.add(cliente);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(int id) {
        return Optional.empty();
    }

    @Override
    public List<Cliente> finByNome(String nome) {
        return null;
    }


}
