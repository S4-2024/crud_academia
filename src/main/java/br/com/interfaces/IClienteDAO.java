package br.com.interfaces;

import br.com.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteDAO {
    Cliente save(Cliente cliente);
    Cliente  update(Cliente cliente);
    void delete(int id);


    List<Cliente> findAll();
    Optional<Cliente> findById(int id);
    List<Cliente> findByNome(String nome);
}
