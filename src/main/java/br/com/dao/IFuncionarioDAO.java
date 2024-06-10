package br.com.dao;

import br.com.models.Funcionario;

public interface IFuncionarioDAO {

    Funcionario save(Funcionario funcionario);
    Funcionario update(Funcionario funcionario);
    void delete(Funcionario funcionario);


    void setLoggedIn(boolean loggedIn);
    void logOut();
    void accesClienteList();
    void accesClienteFicha();
}
