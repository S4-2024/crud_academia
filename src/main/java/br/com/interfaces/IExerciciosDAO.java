package br.com.interfaces;

import br.com.gym.Exercicio;

import java.util.List;

public interface IExerciciosDAO {
    void create(Exercicio exercicio) throws Exception;
    Exercicio getById(int id) throws Exception;
    List<Exercicio> getAll() throws Exception;
    void update(Exercicio exercicio) throws Exception;
    void delete(int id) throws Exception;
}
