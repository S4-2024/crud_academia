package br.com.interfaces;

import br.com.models.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteDAO {
    Cliente save(Cliente cliente);
    Cliente  update(Cliente cliente);
    void delete(int id);
    Optional<Cliente> findByEmailAndPassword(String email, String senha);

    List<Cliente> findAll();
    Optional<Cliente> findById(int id);
    List<Cliente> findByNome(String nome);
    List<Cliente> orderedByBubbleSortName();
}
