package br.com.models;

import java.sql.Timestamp;

public class CartaoCredito {
    private int id;
    private int idUsuario;
    private String numero;
    private String nomeTitular;
    private String dataValidade;
    private String cvv;
    private Timestamp dataCriacao;

    // Construtores, getters e setters
    public CartaoCredito() {
    }

    public CartaoCredito(int idUsuario, String numero, String nomeTitular, String dataValidade, String cvv) {
        this.idUsuario = idUsuario;
        this.numero = numero;
        this.nomeTitular = nomeTitular;
        this.dataValidade = dataValidade;
        this.cvv = cvv;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(String dataValidade) {
        this.dataValidade = dataValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    // Sobrescreva o método toString() se desejar uma representação textual personalizada
    @Override
    public String toString() {
        return "CartaoCredito{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", numero='" + numero + '\'' +
                ", nomeTitular='" + nomeTitular + '\'' +
                ", dataValidade='" + dataValidade + '\'' +
                ", cvv='" + cvv + '\'' +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
