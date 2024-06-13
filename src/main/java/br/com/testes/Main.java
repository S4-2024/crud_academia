package br.com.testes;

import br.com.dao.FichasClienteDAO;
import br.com.gym.FichaCliente;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.IFichasClienteDAO;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Exemplo de utilização do FichasClienteDAO
        IFichasClienteDAO fichasClienteDAO = new FichasClienteDAO(ConnectionFactory.getConnection());


        // Exemplo de busca de uma ficha de cliente por ID
        try {
            FichaCliente fichaBuscada = fichasClienteDAO.getById(1);
            if (fichaBuscada != null) {
                System.out.println("Ficha de cliente encontrada: " + fichaBuscada);
            } else {
                System.out.println("Nenhuma ficha de cliente encontrada com o ID especificado.");
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar ficha de cliente por ID: " + e.getMessage());
        }

        // Outros exemplos: getAll, update, delete, etc.
    }
}
