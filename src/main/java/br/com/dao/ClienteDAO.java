package br.com.dao;

import br.com.infra.ConnectionFactory;
import br.com.models.Cliente;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements IClienteDAO {


    @Override
    public Cliente save(Cliente cliente) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Clientes(nome,email,senha,status_pagamento) VALUES(?,?,?,?)";
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
    public List<Cliente> finAll() {
        return null;
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
