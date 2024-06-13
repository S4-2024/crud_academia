package br.com.gym;

import br.com.enums.Sexo;

public class Avaliacao {
    private int id;
    private Sexo sexo;
    private int idade;
    private double peso;
    private double altura;
    private double IMC;
    private double TMB;
    private int idAgendamento;

    // Construtores, getters e setters
    public Avaliacao() {
    }

    public Avaliacao(Sexo sexo, int idade, double peso, double altura, double IMC, double TMB, int idAgendamento) {
        this.sexo = sexo;
        this.idade = idade;
        this.peso = peso;
        this.altura = altura;
        this.IMC = IMC;
        this.TMB = TMB;
        this.idAgendamento = idAgendamento;
    }

    // Getters e setters omitidos para brevidade


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSexo() {
        return String.valueOf(sexo);
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getTMB() {
        return TMB;
    }

    public void setTMB(double TMB) {
        this.TMB = TMB;
    }

    public int getIdAgendamento() {
        return idAgendamento;
    }

    public void setIdAgendamento(int idAgendamento) {
        this.idAgendamento = idAgendamento;
    }

    @Override
    public String toString() {
        return "Avaliacao{" +
                "id=" + id +
                ", sexo=" + sexo +
                ", idade=" + idade +
                ", peso=" + peso +
                ", altura=" + altura +
                ", IMC=" + IMC +
                ", TMB=" + TMB +
                ", idAgendamento=" + idAgendamento +
                '}';
    }
}
