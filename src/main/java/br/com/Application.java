package br.com;

import br.com.dao.ClienteDAO;
import br.com.models.Cliente;
import br.com.models.Pagamento;
import static br.com.models.Pagamento.valueOf;

public class Application {

    public static void main(String[] args) {
    ClienteDAO clienteDAO = new ClienteDAO();


    String nome = "rosaSADSsd";
    String email = "asdas@asdas.com";
    String senha = "12dasdad";
    String pagamento = "PENDENTE";
    Pagamento pagamento1 = valueOf(pagamento);

    Cliente cliente = new Cliente(nome,email,senha, pagamento1);


    Cliente cliente_salvo = clienteDAO.save(cliente);

        System.out.println("ID do cliente: " + cliente.getId());
    }



}
