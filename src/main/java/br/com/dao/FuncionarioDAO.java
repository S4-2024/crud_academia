package br.com.dao;

import br.com.infra.ConnectionFactory;
import br.com.models.Funcionario;

import java.sql.*;

public class FuncionarioDAO implements IFuncionarioDAO{


    @Override
    public Funcionario save(Funcionario funcionario) {
        try(Connection connection = ConnectionFactory.getConnection()){
           String sql =  "INSERT INTO  Funcionarios(nome,cpf,senha,email) VALUES(?,?,?,?)";
           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1, funcionario.getNome());
           preparedStatement.setString(2, funcionario.getCpf());
           preparedStatement.setString(3, funcionario.getSenha());
           preparedStatement.setString(4, funcionario.getEmail());

           int affectedRows = preparedStatement.executeUpdate();

           if(affectedRows == 0){
               throw new SQLException("Erro ao inserir funcionario");
           }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    funcionario.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException(" nenhum ID obtido.");
                }
            }



        }catch (SQLException e){
            throw new RuntimeException(e);
        }

        return funcionario;
    }

    @Override
    public Funcionario update(Funcionario funcionario) {
        return null;
    }

    @Override
    public void delete(Funcionario funcionario) {

    }
}
