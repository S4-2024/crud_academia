package br.com.models;

import java.io.Serializable;

public class Funcionario extends Pessoa{
    private  String cpf;

    public Funcionario( String nome, String email, String senha, String cpf) {
        super( nome, email, senha);
        this.cpf = cpf;
    }



    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }



}
