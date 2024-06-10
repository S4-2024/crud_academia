package br.com.dao;

import br.com.infra.ConnectionFactory;
import br.com.models.Funcionario;

import java.sql.*;

public class FuncionarioDAO implements IFuncionarioDAO {


    @Override
    public Funcionario save(Funcionario funcionario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
                String sql = "INSERT INTO Funcio(nome,email,senha,pagamento) VALUES(?,?,?,?)";
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
    public Funcionario update(Funcionario funcionario) {
        return null;
    }

    @Override
    public void delete(Funcionario funcionario) {

    }

    @Override
    public void setLoggedIn(boolean loggedIn) {

    }

    @Override
    public void logOut() {

    }

    @Override
    public void accesClienteList() {

    }

    @Override
    public void accesClienteFicha() {

    }
}