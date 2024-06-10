package br.com;

import br.com.dao.ClienteDAO;
import br.com.models.Cliente;


import java.util.List;

import static br.com.enums.Pagamento.valueOf;

public class Listagens {

    public static void main(String[] args) {
//
//        ClienteDAO clienteDAO = new ClienteDAO();
//        List<Cliente> cliente = clienteDAO.findAll();
//
//        for(Cliente cliente1 : cliente) {
//            System.out.println("id" + cliente1.getId());
//            System.out.println("nome" + cliente1.getNome());
//            System.out.println("email" + cliente1.getEmail());
//        }

//        FuncionarioDAO dao = new FuncionarioDAO();
//
//        String nome = "Jose Maria";
//        String email = "josemara@gmail.com";
//        String senha = "123456";
//        String cpf = "123.456.789-00";
//
//        Funcionario funcionario = new Funcionario(nome,email,senha,cpf);
//
//        dao.save(funcionario);
//
//        System.out.println("Funcionario ID: " + funcionario.getId());
//        System.out.println("Inserido: " + funcionario);

//        ClienteDAO dao = new ClienteDAO();
//
//        String nome = "Jose Maria";
//        String email = "josemara@gmail.com";
//        String senha = "123456";
//        Pagamento pagamento = valueOf("PAGO");
//
//        Cliente cliente = new Cliente(nome,email,senha,pagamento);
//
//        dao.save(cliente);

        // -------------------- teste findAll

//        ClienteDAO  dao = new ClienteDAO();
//        List<Cliente> clientes = dao.findAll();
//        int i = 1;
//
//        for(Cliente cliente : clientes){
//
//            System.out.println("Cliente #"+i);
//            System.out.println("ID: " + cliente.getId());
//            System.out.println("Nome: " + cliente.getNome());
//            System.out.println("Email: " + cliente.getEmail());
//            System.out.println("senha: " + cliente.getSenha());
//            System.out.println("Pagamneto: " + cliente.getPagamento());
//            System.out.println(" ");
//
//            i = i + 1 ;
//    }


        // -------------------- teste findByID

//        ClienteDAO  dao = new ClienteDAO();
//         Optional<Cliente> clienteOptional = dao.findById(2);
//        if (clienteOptional.isPresent()) {
//            Cliente cliente = clienteOptional.get();
//            System.out.println("ID: " + cliente.getId());
//            System.out.println("Nome: " + cliente.getNome());
//        } else {
//            System.out.println("Cliente com o esse ID não existe!");
//        }

        // -------------------- teste findByName

        ClienteDAO dao = new ClienteDAO();
        List<Cliente> clientes = dao.findByNome("rosaSADSsd");

        int i = 1;
        for (Cliente cliente : clientes) {

            System.out.println("Cliente #" + i);
            System.out.println("ID: " + cliente.getId());
            System.out.println("Nome: " + cliente.getNome());
            System.out.println("Email: " + cliente.getEmail());
            System.out.println("senha: " + cliente.getSenha());
            System.out.println("Pagamneto: " + cliente.getPagamento());
            System.out.println(" ");

            i = i + 1;
        }
    }






}


