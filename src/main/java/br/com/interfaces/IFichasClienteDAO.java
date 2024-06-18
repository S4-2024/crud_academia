package br.com.interfaces;

import br.com.gym.FichaTreinamento;

import java.util.List;

public interface IFichasClienteDAO {
    void create(FichaTreinamento fichaCliente) throws Exception;
    FichaTreinamento getById(int id) throws Exception;
    List<FichaTreinamento> getAll() throws Exception;
    void update(FichaTreinamento fichaCliente) throws Exception;
    void delete(int id) throws Exception;
}
