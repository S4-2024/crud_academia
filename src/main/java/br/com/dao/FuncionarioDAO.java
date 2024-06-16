package br.com.dao;

import br.com.enums.Pagamento;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.IFuncionarioDAO;
import br.com.models.Cliente;
import br.com.models.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FuncionarioDAO implements IFuncionarioDAO {

    @Override
    public Funcionario save(Funcionario funcionario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Funcionarios(nome,cpf,senha,email) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //retornando o id para gnt  Statement.RETURN_GENERATED_KEYS

             preparedStatement.setString(1, funcionario.getNome());
             preparedStatement.setString(2, funcionario.getCpf());
             preparedStatement.setString(3, funcionario.getSenha());
             preparedStatement.setString(4, funcionario.getEmail());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Falha ao inserir funcionario, nenhuma linha afetada.");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Falha ao inserir funcionario, nenhum ID obtido.");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return funcionario;
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = " UPDATE Funcionarios SET nome = ?, cpf = ?, senha = ?, email = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, funcionario.getNome());
            preparedStatement.setString(2, funcionario.getCpf());
            preparedStatement.setString(3, funcionario.getSenha());
            preparedStatement.setString(4, funcionario.getEmail());
            preparedStatement.setInt(5, funcionario.getId());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return funcionario;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = " DELETE FROM Funcionarios WHERE id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public Optional<Funcionario> findByEmailAndPassword(String email, String senha) {
        String sql = "SELECT id,nome,cpf,senha FROM Funcionarios WHERE email = ? AND senha = ?";
        Funcionario funcionario = null;
        try(Connection connection = ConnectionFactory.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()){
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                funcionario = new Funcionario(id,nome,cpf);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(funcionario);
    }




}