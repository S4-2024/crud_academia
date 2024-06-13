package br.com.dao;

import br.com.enums.Pagamento;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.IFuncionarioDAO;
import br.com.models.Cliente;
import br.com.models.Funcionario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
    public void accesClienteList() {
        String sql = "SELECT id,nome,email,senha,pagamento FROM Clientes ";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.executeQuery();
            ResultSet rs = preparedStatement.getResultSet();

            while (rs.next()) {
                int id =   rs.getInt("id");
                String nome =   rs.getString("nome");
                String email =   rs.getString("email");
                String senha =   rs.getString("senha");
                Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

                Cliente  cliente  = new Cliente(id,nome,email,senha,pagamento);
                clientes.add(cliente);

            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void accesClienteFicha() {

    }
}