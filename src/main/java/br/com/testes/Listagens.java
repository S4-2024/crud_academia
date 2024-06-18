package br.com.testes;

import br.com.dao.ClienteDAO;
import br.com.models.Cliente;


import java.util.List;

public class Listagens {

    public static void main(String[] args) {

        ClienteDAO clienteDAO = new ClienteDAO();
        List<Cliente> cliente = clienteDAO.findAll();

        for (Cliente cliente1 : cliente) {
            System.out.println("id" + cliente1.getId());
            System.out.println("nome" + cliente1.getNome());
            System.out.println("email" + cliente1.getEmail());
            System.out.println("senha: " + cliente1.getSenha());
            System.out.println("Pagamneto: " + cliente1.getPagamento());

        }
    }
}


