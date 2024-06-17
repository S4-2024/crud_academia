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
    public Cliente update(Cliente cliente) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = " UPDATE Clientes SET nome = ?, email = ?, senha = ?, pagamento = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, cliente.getNome());
            preparedStatement.setString(2, cliente.getEmail());
            preparedStatement.setString(3, cliente.getSenha());
            preparedStatement.setString(4, String.valueOf(cliente.getPagamento()));
            preparedStatement.setInt(5, cliente.getId());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = " DELETE FROM Clientes WHERE id = ? ";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Cliente> findByEmailAndPassword(String email, String senha) {
        String sql = "SELECT id, nome, email, senha, pagamento FROM Clientes WHERE email=? AND senha=?";

        Cliente cliente = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

                cliente = new Cliente(id, nome, email, senha, pagamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.ofNullable(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        String sql = "SELECT id,nome,email,senha,pagamento FROM Clientes ";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = preparedStatement.executeQuery();

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
        return clientes;
    }

    @Override
    public Optional<Cliente> findById(int id) {
        String sql = "SELECT id,nome,email,senha,pagamento FROM Clientes  WHERE id=?";

        Cliente  cliente = null;

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int Pid =   rs.getInt("id");
                String nome =   rs.getString("nome");
                String email =   rs.getString("email");
                String senha =   rs.getString("senha");
                Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

                cliente  = new Cliente(Pid,nome,email,senha,pagamento);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return  Optional.ofNullable(cliente);
    }

    @Override
    public List<Cliente> findByNome(String nome) {
        String sql = "SELECT id, nome, email, senha, pagamento FROM Clientes WHERE nome LIKE ?";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + nome + "%"); // Use % to allow partial matches

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String Pnome = rs.getString("nome");
                String email = rs.getString("email");
                String senha = rs.getString("senha");
                Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

                Cliente cliente = new Cliente(id, Pnome, email, senha, pagamento);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public List<Cliente> orderedByBubbleSortName() {
        List<Cliente> clientes = findAll(); // Busca todos os clientes do banco de dados

        int n = clientes.size();
        Cliente temp;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (clientes.get(j).getNome().compareToIgnoreCase(clientes.get(j + 1).getNome()) > 0) {
                    temp = clientes.get(j);
                    clientes.set(j, clientes.get(j + 1));
                    clientes.set(j + 1, temp);
                }
            }
        }

        return clientes;
    }

    @Override
    public List<Cliente> findByEmail(String email) {
        String sql = "SELECT id, nome, email, senha, pagamento FROM Clientes WHERE email LIKE ?";

        List<Cliente> clientes = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + email + "%"); // Use % to allow partial matches

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String emailResultado = rs.getString("email");
                String senha = rs.getString("senha");
                Pagamento pagamento = Pagamento.valueOf(rs.getString("pagamento"));

                Cliente cliente = new Cliente(id, nome, emailResultado, senha, pagamento);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }
}
