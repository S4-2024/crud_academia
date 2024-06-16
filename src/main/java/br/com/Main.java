package br.com;
import br.com.dao.*;
import br.com.models.Cliente;
import br.com.models.Funcionario;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Optional;

public class Main {
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("Fitten");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        frame.getContentPane().add(panel);

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
        frame.setVisible(true);
    }


    //


    private static void loginCliente() {

        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:");

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


    //

    private static void loginFuncionario() {
        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:");

        FuncionarioDAO dao = new FuncionarioDAO();
        Optional<Funcionario> funcionario = dao.findByEmailAndPassword(email, senha);

        if (funcionario.isPresent()) {
            panel.removeAll();
            panel.setLayout(new BorderLayout());

            JPanel buttonsPanel = new JPanel(); // Initialize the panel here
            buttonsPanel.add(new JLabel("Bem-vindo, " + funcionario.get().getNome()));
            JButton consultarClientesButton = new JButton("Consultar Clientes");
            buttonsPanel.add(consultarClientesButton);
            JButton ordenarPorNomeButton = new JButton("Ordenar Clientes por Nome");
            buttonsPanel.add(ordenarPorNomeButton);
            JButton pesquisarClientePorNomeButton = new JButton("Pesquisar Cliente por Nome");
            buttonsPanel.add(pesquisarClientePorNomeButton);
          

            panel.add(buttonsPanel, BorderLayout.NORTH);

            consultarClientesButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ClienteDAO dao = new ClienteDAO();
                    List<Cliente> clientes = dao.findAll();

                    String[] columnNames = {"ID", "Nome", "Email", "Pagamento"};
                    Object[][] data = new Object[clientes.size()][4];

                    for (int i = 0; i < clientes.size(); i++) {
                        Cliente cliente = clientes.get(i);
                        data[i][0] = cliente.getId();
                        data[i][1] = cliente.getNome();
                        data[i][2] = cliente.getEmail();
                        data[i][3] = cliente.getPagamento().toString();
                    }

                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);

                    panel.add(scrollPane, BorderLayout.CENTER);
                    frame.pack();
                }
            });

            ordenarPorNomeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    ClienteDAO dao = new ClienteDAO();
                    List<Cliente> clientes = dao.orderedByBubbleSortName();

                    String[] columnNames = {"ID", "Nome", "Email", "Pagamento"};
                    Object[][] data = new Object[clientes.size()][4];

                    for (int i = 0; i < clientes.size(); i++) {
                        Cliente cliente = clientes.get(i);
                        data[i][0] = cliente.getId();
                        data[i][1] = cliente.getNome();
                        data[i][2] = cliente.getEmail();
                        data[i][3] = cliente.getPagamento().toString();
                    }

                    JTable table = new JTable(data, columnNames);
                    JScrollPane scrollPane = new JScrollPane(table);

                    panel.add(scrollPane, BorderLayout.CENTER);
                    frame.pack();
                }
            });

            pesquisarClientePorNomeButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String nome = JOptionPane.showInputDialog(frame, "Digite o nome do cliente:");
                    if (nome != null && !nome.isEmpty()) {
                        ClienteDAO dao = new ClienteDAO();
                        List<Cliente> clientes = dao.findByNome(nome);

                        if (!clientes.isEmpty()) {
                            String[] columnNames = {"ID", "Nome", "Email", "Pagamento"};
                            Object[][] data = new Object[clientes.size()][4];

                            for (int i = 0; i < clientes.size(); i++) {
                                Cliente cliente = clientes.get(i);
                                data[i][0] = cliente.getId();
                                data[i][1] = cliente.getNome();
                                data[i][2] = cliente.getEmail();
                                data[i][3] = cliente.getPagamento().toString();
                            }

                            JTable table = new JTable(data, columnNames);
                            JScrollPane scrollPane = new JScrollPane(table);

                            panel.add(scrollPane, BorderLayout.CENTER);
                            frame.pack();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Nenhum cliente encontrado com o nome '" + nome + "'");
                        }
                    }
                }
            });





            frame.pack();
        } else {
            JOptionPane.showMessageDialog(frame, "Email ou senha inválidos.");
        }


    }
}