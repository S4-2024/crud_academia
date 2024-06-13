package br.com.gym;


import java.sql.Date;

public class Agendamentos {
    private int id;
    private Date dia;
    private int idFuncionario;
    private int idCliente;

    // Construtores
    public Agendamentos() {}

    public Agendamentos(int id, Date dia, int idFuncionario, int idCliente) {
        this.id = id;
        this.dia = dia;
        this.idFuncionario = idFuncionario;
        this.idCliente = idCliente;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Agendamentos{" +
                "id=" + id +
                ", dia=" + dia +
                ", idFuncionario=" + idFuncionario +
                ", idCliente=" + idCliente +
                '}';
    }
}
