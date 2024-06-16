package br.com.interfaces;

import br.com.models.Cliente;
import br.com.models.Funcionario;

import java.util.Optional;

public interface IFuncionarioDAO {

    Funcionario save(Funcionario funcionario);
    Funcionario update(Funcionario funcionario);
    void delete(int id);
    Optional<Funcionario> findByEmailAndPassword(String email, String senha);

}
