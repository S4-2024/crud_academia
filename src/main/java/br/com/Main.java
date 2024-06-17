package br.com;

import br.com.dao.ClienteDAO;
import br.com.dao.FuncionarioDAO;
import br.com.gym.Agendamentos;
import br.com.models.Cliente;
import br.com.models.Funcionario;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class Main {
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("Fitten");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu loginMenu = new JMenu("Login");
        menuBar.add(loginMenu);

        JMenuItem clienteItem = new JMenuItem("Login Cliente");
        JMenuItem funcionarioItem = new JMenuItem("Login Funcionário");

        loginMenu.add(clienteItem);
        loginMenu.add(funcionarioItem);

        clienteItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginCliente();
            }
        });

        funcionarioItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                loginFuncionario();
            }
        });

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loginCliente() {
        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        JPasswordField passwordField = new JPasswordField();
        int result = JOptionPane.showConfirmDialog(frame, passwordField, "Digite sua senha:",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            ClienteDAO dao = new ClienteDAO();
            Optional<Cliente> cliente = dao.findByEmailAndPassword(email, senha);

            if (cliente.isPresent()) {
                panel.removeAll();
                panel.add(new JLabel("Bem-vindo, " + cliente.get().getNome()));
                panel.add(new JButton("Consultar Ficha"));
                panel.add(new JButton("Consultar Treino"));
                frame.pack();
            } else {
                JOptionPane.showMessageDialog(frame, "Email ou senha inválidos.");
            }
        }
    }

    private static void loginFuncionario() {
        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        JPasswordField passwordField = new JPasswordField();
        int result = JOptionPane.showConfirmDialog(frame, passwordField, "Digite sua senha:",
                JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String senha = new String(passwordField.getPassword());
            FuncionarioDAO dao = new FuncionarioDAO();
            Optional<Funcionario> funcionario = dao.findByEmailAndPassword(email, senha);

            if (funcionario.isPresent()) {
                Funcionario logadoFuncionario = funcionario.get();

                panel.removeAll();
                panel.setLayout(new BorderLayout());

                JPanel buttonsPanel = new JPanel();
                buttonsPanel.add(new JLabel("Bem-vindo, " + logadoFuncionario.getNome()));
                buttonsPanel.setLayout(new GridLayout(0, 1));

                JButton listarClientesButton = new JButton("Listar Clientes");
                JButton acessarFichaButton = new JButton("Acessar Ficha do Cliente");
                JButton agendarAvaliacaoButton = new JButton("Agendar Avaliação");
                JButton visualizarAgendaButton = new JButton("Visualizar Agenda");
                JButton adicionarFichaButton = new JButton("Adicionar Ficha");
                JButton adicionarClienteButton = new JButton("Adicionar Cliente");
                JButton atualizarClienteButton = new JButton("Atualizar Cliente");
                JButton excluirClienteButton = new JButton("Excluir Cliente");
                JButton adicionarAvaliacaoButton = new JButton("Adicionar Avaliação");
                JButton visualizarAvaliacoesButton = new JButton("Visualizar Avaliações");
                JButton adicionarTreinoButton = new JButton("Adicionar Treino");
                JButton visualizarTreinosButton = new JButton("Visualizar Treinos");

                buttonsPanel.add(listarClientesButton);
                buttonsPanel.add(acessarFichaButton);
                buttonsPanel.add(agendarAvaliacaoButton);
                buttonsPanel.add(visualizarAgendaButton);
                buttonsPanel.add(adicionarFichaButton);
                buttonsPanel.add(adicionarClienteButton);
                buttonsPanel.add(atualizarClienteButton);
                buttonsPanel.add(excluirClienteButton);
                buttonsPanel.add(adicionarAvaliacaoButton);
                buttonsPanel.add(visualizarAvaliacoesButton);
                buttonsPanel.add(adicionarTreinoButton);
                buttonsPanel.add(visualizarTreinosButton);

                panel.add(buttonsPanel, BorderLayout.NORTH);

                listarClientesButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        listarClientes();
                    }
                });

                acessarFichaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        int idClienteFicha = Integer
                                .parseInt(JOptionPane.showInputDialog(frame, "Digite o ID do cliente:"));
                        try {
                            dao.accessCustomerList(idClienteFicha);
                        } catch (Exception ex) {
                            showError("Erro ao acessar ficha do cliente: " + ex.getMessage());
                        }
                    }
                });

                agendarAvaliacaoButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            String dataStr = JOptionPane.showInputDialog("Digite a data do agendamento (yyyy-mm-dd):");
                            Date dia = Date.valueOf(dataStr);

                            String idClienteStr = JOptionPane.showInputDialog("Digite o ID do cliente:");
                            int idCliente = Integer.parseInt(idClienteStr);

                            if (validarFuncionario(logadoFuncionario.getId()) && validarCliente(idCliente)) {
                                dao.agendarAvaliacao(logadoFuncionario, dia, idCliente);
                                JOptionPane.showMessageDialog(frame, "Avaliação agendada com sucesso.");
                            } else {
                                showError("ID do funcionário ou cliente inválido.");
                            }
                        } catch (Exception ex) {
                            showError("Erro ao agendar avaliação: " + ex.getMessage());
                        }
                    }
                });
                
                visualizarAgendaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            List<Agendamentos> agenda = dao.visualizarAgenda(logadoFuncionario);
                            if (!agenda.isEmpty()) {
                                DefaultTableModel model = new DefaultTableModel();
                                model.addColumn("ID");
                                model.addColumn("Data");
                                model.addColumn("ID Cliente");

                                for (Agendamentos agendamento : agenda) {
                                    model.addRow(new Object[] { agendamento.getId(), agendamento.getDia(),
                                            agendamento.getIdCliente() });
                                }

                                JTable table = new JTable(model);
                                JScrollPane scrollPane = new JScrollPane(table);

                                JPanel newPanel = new JPanel();
                                newPanel.setLayout(new BorderLayout());
                                newPanel.add(scrollPane, BorderLayout.CENTER);

                                frame.getContentPane().removeAll();
                                frame.getContentPane().add(newPanel);
                                frame.revalidate();
                                frame.repaint();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Nenhum agendamento encontrado.");
                            }
                        } catch (Exception ex) {
                            showError("Erro ao visualizar agenda: " + ex.getMessage());
                        }
                    }
                });

                adicionarFichaButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.adicionarFicha();
                        } catch (Exception ex) {
                            showError("Erro ao adicionar ficha: " + ex.getMessage());
                        }
                    }
                });

                adicionarClienteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.adicionarCliente();
                        } catch (Exception ex) {
                            showError("Erro ao adicionar cliente: " + ex.getMessage());
                        }
                    }
                });

                atualizarClienteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.atualizarCliente();
                        } catch (Exception ex) {
                            showError("Erro ao atualizar cliente: " + ex.getMessage());
                        }
                    }
                });

                excluirClienteButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.excluirCliente();
                        } catch (Exception ex) {
                            showError("Erro ao excluir cliente: " + ex.getMessage());
                        }
                    }
                });

                adicionarAvaliacaoButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.adicionarAvaliacao(null, null);
                        } catch (Exception ex) {
                            showError("Erro ao adicionar avaliação: " + ex.getMessage());
                        }
                    }
                });

                visualizarAvaliacoesButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.visualizarAvaliacoes(null, null);
                        } catch (Exception ex) {
                            showError("Erro ao visualizar avaliações: " + ex.getMessage());
                        }
                    }
                });

                adicionarTreinoButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.adicionarTreino();
                        } catch (Exception ex) {
                            showError("Erro ao adicionar treino: " + ex.getMessage());
                        }
                    }
                });

                visualizarTreinosButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        try {
                            dao.visualizarTreinos();
                        } catch (Exception ex) {
                            showError("Erro ao visualizar treinos: " + ex.getMessage());
                        }
                    }
                });

                frame.pack();
            } else {
                JOptionPane.showMessageDialog(frame, "Email ou senha inválidos.");
            }
        }
    }

    private static void listarClientes() {
        try {
          ClienteDAO clienteDAO = new ClienteDAO();
          List<Cliente> clientes = clienteDAO.findAll();
      
          DefaultTableModel model = new DefaultTableModel();
          model.addColumn("ID");
          model.addColumn("Nome");
          model.addColumn("Email");
          model.addColumn("Estado de Pagamento");
      
          for (Cliente cliente : clientes) {
            model.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getEmail(),
                cliente.getPagamento()});
          }
      
          JTable table = new JTable(model);
      
          JPanel tablePanel = new JPanel(new BorderLayout());
          tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
      
          JButton ordenarPorNomeButton = new JButton("Ordenar por Nome");
          JButton ordenarPorIdButton = new JButton("Ordenar por ID");
          JButton buscarButton = new JButton("Buscar por Nome");
          JButton voltarButton = new JButton("Voltar ao Menu Principal");
      
          JPanel buttonsPanel = new JPanel();
          buttonsPanel.add(ordenarPorNomeButton);
          buttonsPanel.add(ordenarPorIdButton);
          buttonsPanel.add(buscarButton);
          buttonsPanel.add(voltarButton);
      
          ordenarPorNomeButton.addActionListener(e -> ordenarClientes(clienteDAO, model));
          ordenarPorIdButton.addActionListener(e -> ordenarClientesPorId(clienteDAO, model));
          buscarButton.addActionListener(e -> buscarClientes(clienteDAO, model));
          voltarButton.addActionListener(e -> returnToMainMenu());
      
          panel.removeAll();
          panel.setLayout(new BorderLayout());
          panel.add(buttonsPanel, BorderLayout.NORTH);
          panel.add(tablePanel, BorderLayout.CENTER);
      
          frame.pack();
        } catch (Exception ex) {
          showError("Erro ao listar clientes: " + ex.getMessage());
        }
    }      

    private static void ordenarClientesPorId(ClienteDAO clienteDAO, DefaultTableModel model) {
        try {
          List<Cliente> clientesOrdenados = clienteDAO.ordenarPorId(); // Fetch clients sorted by ID
      
          model.setRowCount(0); // Clear existing rows
      
          for (Cliente cliente : clientesOrdenados) {
            model.addRow(new Object[]{cliente.getId(), cliente.getNome(), cliente.getEmail(),
                cliente.getPagamento()});
          }
      
          frame.pack();
        } catch (Exception ex) {
          showError("Erro ao ordenar clientes por ID: " + ex.getMessage());
        }
    }
      
    private static void returnToMainMenu() {
        panel.removeAll();

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu loginMenu = new JMenu("Login");
        menuBar.add(loginMenu);

        JMenuItem clienteItem = new JMenuItem("Login Cliente");
        JMenuItem funcionarioItem = new JMenuItem("Login Funcionário");

        loginMenu.add(clienteItem);
        loginMenu.add(funcionarioItem);

        clienteItem.addActionListener(e -> loginCliente());
        funcionarioItem.addActionListener(e -> loginFuncionario());

        panel.setLayout(new FlowLayout());
        panel.add(new JLabel("Bem-vindo ao Fitten"));

        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.revalidate();
        panel.repaint();
    }

    private static void showError(String message) {
        JOptionPane.showMessageDialog(frame, message, "Erro", JOptionPane.ERROR_MESSAGE);
    }

    private static void ordenarClientes(ClienteDAO clienteDAO, DefaultTableModel model) {
        try {
            List<Cliente> clientesOrdenados = clienteDAO.ordenarPorNome();
            model.setRowCount(0); // Clear existing rows
            for (Cliente cliente : clientesOrdenados) {
                model.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getEmail(),
                        cliente.getPagamento() });
            }
        } catch (Exception ex) {
            showError("Erro ao ordenar clientes: " + ex.getMessage());
        }
    }

    private static void buscarClientes(ClienteDAO clienteDAO, DefaultTableModel model) {
        try {
            String nomeBusca = JOptionPane.showInputDialog(frame, "Digite o nome do cliente para buscar:");
            List<Cliente> clientesEncontrados = clienteDAO.buscarPorNome(nomeBusca);
            model.setRowCount(0); // Clear existing rows
            for (Cliente cliente : clientesEncontrados) {
                model.addRow(new Object[] { cliente.getId(), cliente.getNome(), cliente.getEmail(),
                        cliente.getPagamento() });
            }
        } catch (Exception ex) {
            showError("Erro ao buscar clientes: " + ex.getMessage());
        }
    }

    private static boolean validarFuncionario(int idFuncionario) {
        FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
        return funcionarioDAO.findById(idFuncionario).isPresent();
    }

    private static boolean validarCliente(int idCliente) {
        ClienteDAO clienteDAO = new ClienteDAO();
        return clienteDAO.findById(idCliente).isPresent();
    }
}
