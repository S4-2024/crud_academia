package br.com.testes;

import br.com.dao.AvaliacaoDAO;
import br.com.dao.ClienteDAO;
import br.com.enums.Pagamento;
import br.com.functions.Avaliacao;
import br.com.interfaces.IAvaliacaoDAO;
import br.com.models.Cliente;

import java.util.Scanner;

import static br.com.enums.Pagamento.valueOf;

public class AdicionarCliente {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Seu nome: ");
        String nome = sc.nextLine();
        System.out.println("Seu email: ");
        String email = sc.nextLine();
        System.out.println("Digite uma senha: ");
        String senha = sc.nextLine();
        System.out.println("Estado de pagamento: PAGO/ ATRASADO/PENDENTE");
        Pagamento pagamento = valueOf(sc.nextLine());

        Cliente cliente = new Cliente(nome,email,senha,pagamento);

        dao.save(cliente);

        System.out.println("Cliente adicionado com sucesso!"+cliente.toString());



    }
    }


