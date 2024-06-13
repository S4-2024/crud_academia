package br.com.interfaces;

import br.com.models.CartaoCredito;

import java.util.List;

public interface ICartoesCreditoDAO {
    void create(CartaoCredito cartaoCredito) throws Exception;
    CartaoCredito getById(int id) throws Exception;
    List<CartaoCredito> getAll() throws Exception;
    void update(CartaoCredito cartaoCredito) throws Exception;
    void delete(int id) throws Exception;
}
