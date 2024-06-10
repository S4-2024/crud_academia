package br.com;

import br.com.dao.ClienteDAO;

public class DeleteCliente {
    public static void main(String[] args) {

        ClienteDAO dao = new ClienteDAO();
        dao.delete(1);

    }
}
