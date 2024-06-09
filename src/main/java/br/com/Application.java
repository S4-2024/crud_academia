package br.com;

import br.com.dao.ClienteDAO;
import br.com.dao.FuncionarioDAO;
import br.com.models.Cliente;
import br.com.models.Funcionario;
import br.com.models.Pagamento;


import java.util.List;

import static br.com.models.Pagamento.valueOf;

public class Application {

    public static void main(String[] args) {
//        ClienteDAO clienteDAO = new ClienteDAO();
//        List<Cliente> cliente = clienteDAO.findAll();
//
//        for(Cliente cliente1 : cliente) {
//            System.out.println("id" + cliente1.getId());
//            System.out.println("nome" + cliente1.getNome());
//            System.out.println("email" + cliente1.getEmail());
//        }




        FuncionarioDAO dao = new FuncionarioDAO();

        String nome = "Jose Maria";
        String email = "josemara@gmail.com";
        String senha = "123456";
        String cpf = "123.456.789-00";

        Funcionario funcionario = new Funcionario(nome,email,senha,cpf);

        dao.save(funcionario);

        System.out.println("Funcionario ID: " + funcionario.getId());
        System.out.println("Inserido: " + funcionario);


    }


}
