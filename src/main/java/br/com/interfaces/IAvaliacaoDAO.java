package br.com.interfaces;

import br.com.gym.Avaliacao;

import java.util.List;

public interface IAvaliacaoDAO {
    void create(Avaliacao avaliacao) throws Exception;
    Avaliacao getById(int id) throws Exception;
    List<Avaliacao> getAll() throws Exception;
    void update(Avaliacao avaliacao) throws Exception;
    void delete(int id) throws Exception;
}
