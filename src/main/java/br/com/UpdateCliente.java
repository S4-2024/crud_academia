package br.com;

import br.com.dao.ClienteDAO;
import br.com.models.Cliente;

import java.util.Optional;

public class UpdateCliente {
    public static void main(String[] args) {

        ClienteDAO dao = new ClienteDAO();
        Optional<Cliente> optionalCliente = dao.findById(20);

        Cliente cliente = optionalCliente.get();
        System.out.println("==OLD: ===");
        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Senha: " + cliente.getSenha());
        System.out.println("Email: " + cliente.getEmail());

        System.out.println("==Atualização===");
        cliente.setEmail("Josimar@gmail.com");
        cliente.setNome("Josimar");
        cliente.setSenha("123");
        dao.update(cliente);

        System.out.println("ID: " + cliente.getId());
        System.out.println("Nome: " + cliente.getNome());
        System.out.println("Senha: " + cliente.getSenha());
        System.out.println("Email: " + cliente.getEmail());


    }
}
