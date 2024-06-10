package br.com;

import br.com.dao.ClienteDAO;
import br.com.enums.Pagamento;
import br.com.models.Cliente;

import static br.com.enums.Pagamento.valueOf;

public class AdicionarCliente {
    public static void main(String[] args) {
        ClienteDAO dao = new ClienteDAO();
//
        String nome = "Jose Maria";
        String email = "josemara@gmail.com";
        String senha = "123456";
        Pagamento pagamento = valueOf("PAGO");

        Cliente cliente = new Cliente(nome,email,senha,pagamento);

        dao.save(cliente);

        System.out.println("Cliente adicionado com sucesso!"+cliente.toString());
    }
}

