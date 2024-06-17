package br.com.dao;

import br.com.infra.ConnectionFactory;
import br.com.interfaces.IClienteDAO;
import br.com.models.Cliente;
import br.com.enums.Pagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteDAO implements IClienteDAO {
    private static Connection connection;

    @SuppressWarnings("static-access")
    public ClienteDAO() {
        this.connection = ConnectionFactory.getConnection();
    }

    @Override
    public Cliente save(Cliente cliente) {
        String query = "INSERT INTO clientes (nome, email, senha, pagamento) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getPagamento().toString());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                cliente.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public Cliente update(Cliente cliente) {
        String query = "UPDATE clientes SET nome = ?, email = ?, senha = ?, pagamento = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getEmail());
            stmt.setString(3, cliente.getSenha());
            stmt.setString(4, cliente.getPagamento().toString());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public void delete(int id) {
        String query = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Optional<Cliente> findByEmailAndPassword(String email, String senha) {
        String query = "SELECT * FROM clientes WHERE email = ? AND senha = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        Pagamento.valueOf(rs.getString("estado_pagamento")));
                return Optional.of(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Cliente> findAll() {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha"),
                        Pagamento.valueOf(rs.getString("pagamento")));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public List<Cliente> ordenarPorNome() {
        List<Cliente> clientes = findAll();
        quickSort(clientes, 0, clientes.size() - 1);
        return clientes;
    }

    private void quickSort(List<Cliente> clientes, int low, int high) {
        if (low < high) {
            int pi = partition(clientes, low, high);
            quickSort(clientes, low, pi - 1);
            quickSort(clientes, pi + 1, high);
        }
    }

    private int partition(List<Cliente> clientes, int low, int high) {
        Cliente pivot = clientes.get(high);
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (clientes.get(j).getNome().compareToIgnoreCase(pivot.getNome()) < 0) {
                i++;
                Cliente temp = clientes.get(i);
                clientes.set(i, clientes.get(j));
                clientes.set(j, temp);
            }
        }
        Cliente temp = clientes.get(i + 1);
        clientes.set(i + 1, clientes.get(high));
        clientes.set(high, temp);
        return i + 1;
    }

    public List<Cliente> ordenarPorId() {
        List<Cliente> clientes = findAll();
        int n = clientes.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clientes.get(j).getId() > clientes.get(j + 1).getId()) {
                    Cliente temp = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, temp);
                }
            }
        }

        return clientes;
    }

    public List<Cliente> buscarPorNome(String nomeBusca) {
        List<Cliente> todosClientes = findAll();
        List<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente cliente : todosClientes) {
            if (cliente.getNome().toLowerCase().contains(nomeBusca.toLowerCase())) {
                clientesEncontrados.add(cliente);
            }
        }
        return clientesEncontrados;
    }

    public Optional<Cliente> findById(int id) {
        List<Cliente> todosClientes = findAll();

        for (Cliente cliente : todosClientes) {
            if (cliente.getId() == id) {
                return Optional.of(cliente);
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> findByNome(String nomeBusca) {
        List<Cliente> todosClientes = findAll();
        List<Cliente> clientesEncontrados = new ArrayList<>();

        for (Cliente cliente : todosClientes) {
            if (cliente.getNome().toLowerCase().contains(nomeBusca.toLowerCase())) {
                clientesEncontrados.add(cliente);
            }
        }
        return clientesEncontrados;
    }

    public List<Cliente> orderedByBubbleSortName() {
        List<Cliente> clientes = findAll();
        int n = clientes.size();
        boolean swapped;
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (clientes.get(j).getNome().compareToIgnoreCase(clientes.get(j + 1).getNome()) > 0) {
                    Cliente temp = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }
        return clientes;
    }

    public List<Cliente> findByEmail(String email) {
        List<Cliente> todosClientes = findAll();
        List<Cliente> clientesEncontrados = new ArrayList<>();
        for (Cliente cliente : todosClientes) {
            if (cliente.getEmail().equals(email)) {
                clientesEncontrados.add(cliente);
            }
        }
        return clientesEncontrados;
    }
}
