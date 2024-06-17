package br.com.gym;

import java.util.Date;

import br.com.enums.Type;

public class FichaCliente {
    private int id;
    private int idCliente;
    private Date data;
    private String notas;

    // Construtores, getters e setters
    public FichaCliente(int id, String nomeAluno, String nomeTreinador, double pesoInicial, Date dataCriacao, Type tipo, String[] exerciciosInferiores, String[] exerciciosPosteriores, String[] exerciciosSuperiores, String[] exerciciosCore, int idCliente) {
        this.id = id;
        this.nomeAluno = nomeAluno;
        this.nomeTreinador = nomeTreinador;
        this.pesoInicial = pesoInicial;
        this.dataCriacao = dataCriacao;
        this.tipo = tipo;
        this.exerciciosInferiores = exerciciosInferiores;
        this.exerciciosPosteriores = exerciciosPosteriores;
        this.exerciciosSuperiores = exerciciosSuperiores;
        this.exerciciosCore = exerciciosCore;
        this.idCliente = idCliente;
    }
    

    public FichaCliente(int idCliente, Date data, String notas) {
        this.idCliente = idCliente;
        this.data = data;
        this.notas = notas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "FichaCliente{" +
                "id=" + id +
                ", idCliente=" + idCliente +
                ", data=" + data +
                ", notas='" + notas + '\'' +
                '}';
    }
}
