package br.com.interfaces;

import br.com.gym.FichaCliente;

import java.util.List;

public interface IFichasClienteDAO {
    void create(FichaCliente fichaCliente) throws Exception;
    FichaCliente getById(int id) throws Exception;
    List<FichaCliente> getAll() throws Exception;
    void update(FichaCliente fichaCliente) throws Exception;
    void delete(int id) throws Exception;
}
