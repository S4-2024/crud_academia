package br.com.dao;

import br.com.enums.Pagamento;
import br.com.gym.Agendamentos;
import br.com.gym.FichaTreinamento;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.IFuncionarioDAO;
import br.com.models.Cliente;
import br.com.models.Funcionario;
import br.com.enums.Type;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

public class FuncionarioDAO implements IFuncionarioDAO {

    private ClienteDAO clienteDAO;

    public FuncionarioDAO() {
        this.clienteDAO = new ClienteDAO(); 
    }

    @Override
    public Funcionario save(Funcionario funcionario) {
        try (Connection connection = ConnectionFactory.getConnection()) {
            String sql = "INSERT INTO Funcionarios(nome,cpf,senha,email) VALUES(?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

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
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");

                funcionario = new Funcionario(id, nome, cpf);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(funcionario);
    }

    public List<Cliente> accessCustomerList(int idClienteFicha) throws SQLException {
        return clienteDAO.findAll();
    }

    public void accessCustomerFile(String customerId) throws SQLException {
        Optional<Cliente> optionalCliente = clienteDAO.findById(Integer.parseInt(customerId));
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            JOptionPane.showMessageDialog(null, cliente.toString());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
        }
    }

    public void adicionarFicha() {
        try {
            int id = 0; 
            String nomeAluno = JOptionPane.showInputDialog("Digite o nome do aluno:");
            String nomeTreinador = JOptionPane.showInputDialog("Digite o nome do treinador:");
            double pesoInicial = Double.parseDouble(JOptionPane.showInputDialog("Digite o peso inicial:"));
            Date dataCriacao = Date.valueOf(JOptionPane.showInputDialog("Digite a data de criação (AAAA-MM-DD):"));
            Type tipo = Type.valueOf(JOptionPane.showInputDialog("Digite o tipo de ficha:"));
            String[] exerciciosInferiores = JOptionPane
                    .showInputDialog("Digite os exercícios inferiores separados por vírgula:").split(",");
            String[] exerciciosPosteriores = JOptionPane
                    .showInputDialog("Digite os exercícios posteriores separados por vírgula:").split(",");
            String[] exerciciosSuperiores = JOptionPane
                    .showInputDialog("Digite os exercícios superiores separados por vírgula:").split(",");
            String[] exerciciosCore = JOptionPane.showInputDialog("Digite os exercícios core separados por vírgula:")
                    .split(",");
            int idCliente = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));

            FichaTreinamento fichaCliente = new FichaTreinamento(id, nomeAluno, nomeTreinador, pesoInicial, dataCriacao, tipo,
                    exerciciosInferiores, exerciciosPosteriores, exerciciosSuperiores, exerciciosCore, idCliente);

            FichaTreinamentoDAO fichasClienteDAO = new FichaTreinamentoDAO(ConnectionFactory.getConnection());
            fichasClienteDAO.adicionar(fichaCliente);
            JOptionPane.showMessageDialog(null, "Ficha adicionada com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar ficha: " + e.getMessage());
        }
    }

    public void adicionarCliente() {
        try {
            String nome = JOptionPane.showInputDialog("Digite o nome do cliente:");
            String email = JOptionPane.showInputDialog("Digite o email do cliente:");
            String senha = JOptionPane.showInputDialog("Digite a senha do cliente:");
            Pagamento pagamento = Pagamento.valueOf(JOptionPane.showInputDialog("Digite o tipo de pagamento:"));

            Cliente cliente = new Cliente(0, nome, email, senha, pagamento);
            clienteDAO.save(cliente);
            JOptionPane.showMessageDialog(null, "Cliente adicionado com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    public void atualizarCliente() {

        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
            Optional<Cliente> optionalCliente = clienteDAO.findById(id);
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                String nome = JOptionPane.showInputDialog("Digite o novo nome do cliente:", cliente.getNome());
                String email = JOptionPane.showInputDialog("Digite o novo email do cliente:", cliente.getEmail());
                String senha = JOptionPane.showInputDialog("Digite a nova senha do cliente:", cliente.getSenha());
                Pagamento pagamento = Pagamento.valueOf(JOptionPane.showInputDialog("Digite o novo tipo de pagamento:",
                        cliente.getPagamento().toString()));

                cliente.setNome(nome);
                cliente.setEmail(email);
                cliente.setSenha(senha);
                cliente.setPagamento(pagamento);
                clienteDAO.update(cliente);
                JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar cliente: " + e.getMessage());
        }
    }

    public void excluirCliente() {

        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog("Digite o ID do cliente:"));
            clienteDAO.delete(id);
            JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir cliente: " + e.getMessage());
        }
    }

    public void agendarAvaliacao(Funcionario funcionario, Date dia, int idCliente) {
        AgendamentosDAO agendamentoDao = new AgendamentosDAO(ConnectionFactory.getConnection());
        if (funcionario != null) {
            try {
                Agendamentos agendamento = new Agendamentos();
                agendamento.setDia(dia);
                agendamento.setIdFuncionario(funcionario.getId());
                agendamento.setIdCliente(idCliente);
                agendamentoDao.create(agendamento);
                JOptionPane.showMessageDialog(null, "Avaliação agendada com sucesso!");
            } catch (Exception e) {
                showError("Erro ao agendar avaliação: " + e.getMessage());
            }
        } else {
            showError("Você precisa estar logado para agendar a avaliação!");
        }
    }
                
    public List<Agendamentos> visualizarAgenda(Funcionario funcionario) {
        List<Agendamentos> agenda = new ArrayList<>();
        if (funcionario != null) {
            AgendamentosDAO agendamentoDao = new AgendamentosDAO(ConnectionFactory.getConnection());
            try {
                agenda = agendamentoDao.findByFuncionario(funcionario);
                if (!agenda.isEmpty()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Agenda do Funcionário ").append(funcionario.getNome()).append(":\n\n");
    
                    for (Agendamentos agendamento : agenda) {
                        sb.append("ID: ").append(agendamento.getId());
    
                        if (agendamento.getDia() != null) {
                            sb.append(", Data: ").append(agendamento.getDia());
                        } else {
                            sb.append(", Data: Data não especificada");
                        }
    
                        sb.append(", ID Cliente: ").append(agendamento.getIdCliente()).append("\n");
                    }
    
                    JOptionPane.showMessageDialog(null, sb.toString(), "Agenda", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum agendamento encontrado para o funcionário.", "Agenda",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                showError("Erro ao visualizar a agenda: " + e.getMessage());
            }
        } else {
            showError("Você precisa estar logado para visualizar a agenda!");
        }
        return agenda;
    }
            
    public void adicionarAvaliacao(Funcionario funcionario, AgendamentosDAO agendamentoDao) {
        if (funcionario != null) {
            try (Connection connection = ConnectionFactory.getConnection()) {
                Agendamentos novoAgendamento = new Agendamentos();
                novoAgendamento.setIdFuncionario(funcionario.getId());

                agendamentoDao.create(novoAgendamento);

                JOptionPane.showMessageDialog(null, "Avaliação agendada com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                showError("Erro ao agendar avaliação: " + e.getMessage());
            }
        } else {
            showError("Funcionário não encontrado.");
        }
    }

    public void visualizarAvaliacoes(Funcionario funcionario, AgendamentosDAO agendamentoDao) {
        if (funcionario != null) {
            try (Connection connection = ConnectionFactory.getConnection()) {
                List<Agendamentos> avaliacoes = new ArrayList<>();

                String sql = "SELECT * FROM Agendamentos WHERE idFuncionario = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, funcionario.getId());

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    Agendamentos avaliacao = new Agendamentos(id);
                    avaliacoes.add(avaliacao);
                }

                if (!avaliacoes.isEmpty()) {
                    for (Agendamentos avaliacao : avaliacoes) {
                        System.out.println("ID da avaliação: " + avaliacao.getId());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhuma avaliação encontrada.", "Aviso",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (SQLException e) {
                showError("Erro ao visualizar avaliações: " + e.getMessage());
            }
        } else {
            showError("Funcionário não encontrado.");
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(null, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    public Optional<Funcionario> findById(int id) {
        String sql = "SELECT id, nome, cpf, email, senha FROM Funcionarios WHERE id = ?";
        Funcionario funcionario = null;
        try (Connection connection = ConnectionFactory.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String senha = rs.getString("senha");

                funcionario = new Funcionario(id, nome, cpf, email, senha);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(funcionario);
    }

}
