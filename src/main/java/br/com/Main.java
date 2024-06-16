package br.com;
import br.com.dao.*;
import br.com.models.Cliente;
import br.com.models.Funcionario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Optional;

public class Main{
    private static JFrame frame;
    private static JPanel panel;

    public static void main(String[] args) {
        frame = new JFrame("Menu Example");
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

    private static void loginCliente() {


        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:");

        ClienteDAO dao = new ClienteDAO();

        Optional<Cliente> cliente = dao.findByEmailAndPassword(email,senha);

        if (cliente.isPresent()) {
            panel.removeAll();
            panel.add(new JLabel("Bem-vindo, " + cliente.get().getNome()));
            panel.add(new JButton("Consultar Ficha"));
            panel.add(new JButton("Consultar Treino"));
            panel.add(new JButton("Voltar para o menu inicial"));
            frame.pack();
        } else {
            JOptionPane.showMessageDialog(frame, "Email ou senha inválidos.");
        }
    }

    private static void loginFuncionario() {
        String email = JOptionPane.showInputDialog(frame, "Digite seu email:");
        String senha = JOptionPane.showInputDialog(frame, "Digite sua senha:");

        FuncionarioDAO dao = new FuncionarioDAO();
        Optional<Funcionario> funcionario = dao.findByEmailAndPassword(email, senha);

        if (funcionario.isPresent()) {
            panel.removeAll();
            panel.add(new JLabel("Bem-vindo, " + funcionario.get().getNome()));
            panel.add(new JButton("Consultar Clientes"));
            panel.add(new JButton("Ordenar Clientes por Nome"));
            panel.add(new JButton("Pesquisar Cliente por Nome"));
            panel.add(new JButton("Buscar Cliente por Email"));
            panel.add(new JButton("Voltar para o menu inicial"));
            frame.pack();
        } else {
            JOptionPane.showMessageDialog(frame, "Email ou senha inválidos.");
        }
    }
}