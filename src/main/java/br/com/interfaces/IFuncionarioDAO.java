package br.com.interfaces;

import br.com.models.Funcionario;

public interface IFuncionarioDAO {

    Funcionario save(Funcionario funcionario);
    Funcionario update(Funcionario funcionario);
    void delete(int id);


    void accesClienteList();
    void accesClienteFicha();
}
