package br.com.testes;

import br.com.dao.FuncionarioDAO;
import br.com.models.Funcionario;

public class AdicionarFuncionario {
    public static void main(String[] args) {

        FuncionarioDAO dao = new FuncionarioDAO();

        String nome = "Roberto Carlos";
        String cpf = "123.345.356-67";
        String senha = "12356";
        String email = "rob@gmail.com";

        Funcionario funcionario = new Funcionario(nome, cpf, senha, email);

        dao.save(funcionario);

        System.out.println("Funcionario adicionado com sucesso!");

    }
}
