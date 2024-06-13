package br.com.testes;

import br.com.dao.CartoesCreditoDAO;
import br.com.dao.ExerciciosFichaClienteDAO;
import br.com.dao.FichasClienteDAO;
import br.com.gym.ExercicioFichaCliente;
import br.com.gym.FichaCliente;
import br.com.infra.ConnectionFactory;
import br.com.interfaces.ICartoesCreditoDAO;
import br.com.interfaces.IExerciciosFichaClienteDAO;
import br.com.interfaces.IFichasClienteDAO;
import br.com.models.CartaoCredito;

import java.sql.Date;

public class Main {
    public static void main(String[] args) {
        // Exemplo de utilização do CartoesCreditoDAO
        ICartoesCreditoDAO cartoesCreditoDAO = new CartoesCreditoDAO(ConnectionFactory.getConnection());

        // Exemplo de criação de um cartão de crédito associado a um usuário
        try {
            CartaoCredito novoCartaoCredito = new CartaoCredito(1, "1234567812345678", "João da Silva", "12/2024", "123");
            cartoesCreditoDAO.create(novoCartaoCredito);
            System.out.println("Cartão de crédito associado ao usuário criado com sucesso. ID: " + novoCartaoCredito.getId());
        } catch (Exception e) {
            System.err.println("Erro ao criar cartão de crédito associado ao usuário: " + e.getMessage());
        }

        // Exemplo de busca de um cartão de crédito por ID
        try {
            CartaoCredito cartaoCreditoBuscado = cartoesCreditoDAO.getById(1);
            if (cartaoCreditoBuscado != null) {
                System.out.println("Cartão de crédito encontrado: " + cartaoCreditoBuscado);
            } else {
                System.out.println("Nenhum cartão de crédito encontrado com o ID especificado.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } 
    }}

