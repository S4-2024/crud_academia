package br.com.gym;

import java.sql.Date;

import br.com.enums.Type;

public class FichaTreinamento {
    private int id;
    private int clienteId;
    private String clienteNome;
    private String exercicioNome;
    private int exercicioId;
    private String anotacao;

    public FichaTreinamento(int id2, String nomeAluno, String nomeTreinador, double pesoInicial, Date dataCriacao,
            Type tipo, String[] exerciciosInferiores, String[] exerciciosPosteriores, String[] exerciciosSuperiores,
            String[] exerciciosCore, int idCliente) {

    }

    public FichaTreinamento() {
        
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
    }

    public String getExercicioNome() {
        return exercicioNome;
    }

    public void setExercicioNome(String exercicioNome) {
        this.exercicioNome = exercicioNome;
    }

    public int getExercicioId() {
        return exercicioId;
    }

    public void setExercicioId(int exercicioId) {
        this.exercicioId = exercicioId;
    }

    public String getAnotacao() {
        return anotacao;
    }

    public void setAnotacao(String anotacao) {
        this.anotacao = anotacao;
    }
}
