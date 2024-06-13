package br.com.interfaces;

import br.com.gym.Agendamentos;

import java.util.List;

public interface IAgendamentosDAO {

    void create(Agendamentos agendamento) throws Exception;
    Agendamentos read(int id) throws Exception;
    void update(Agendamentos agendamento) throws Exception;
    void delete(int id) throws Exception;
    List<Agendamentos> listAll() throws Exception;
}
