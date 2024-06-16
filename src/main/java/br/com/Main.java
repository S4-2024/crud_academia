package br.com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
        JFrame frame = new JFrame("Login Cliente");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("Login Cliente");
        panel.add(label);

        JButton consultarFichaButton = new JButton("Consultar Ficha");
        panel.add(consultarFichaButton);

        JButton consultarTreinoButton = new JButton("Consultar Treino");
        panel.add(consultarTreinoButton);

        JButton voltarButton = new JButton("Voltar para o menu inicial");
        panel.add(voltarButton);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }

    private static void loginFuncionario() {
        JFrame frame = new JFrame("Login Funcionário");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        frame.getContentPane().add(panel);

        JLabel label = new JLabel("Login Funcionário");
        panel.add(label);

        JButton consultarClientesButton = new JButton("Consultar Clientes");
        panel.add(consultarClientesButton);

        JButton ordenarClientesButton = new JButton("Ordenar Clientes por Nome");
        panel.add(ordenarClientesButton);

        JButton pesquisarClienteButton = new JButton("Pesquisar Cliente por Nome");
        panel.add(pesquisarClienteButton);

        JButton buscarClienteButton = new JButton("Buscar Cliente por Email");
        panel.add(buscarClienteButton);

        JButton voltarButton = new JButton("Voltar para o menu inicial");
        panel.add(voltarButton);

        frame.setSize(300, 300);
        frame.setVisible(true);
    }
}