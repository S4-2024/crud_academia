package br.com.interfaces;

import br.com.gym.ExercicioFichaCliente;

import java.util.List;

public interface IExerciciosFichaClienteDAO {
    void create(ExercicioFichaCliente exercicioFichaCliente) throws Exception;
    ExercicioFichaCliente getById(int id) throws Exception;
    List<ExercicioFichaCliente> getAll() throws Exception;
    void update(ExercicioFichaCliente exercicioFichaCliente) throws Exception;
    void delete(int id) throws Exception;
}
