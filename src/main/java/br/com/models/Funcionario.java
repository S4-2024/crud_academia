package br.com.models;

import java.io.Serializable;

public class Funcionario extends Pessoa{
    private  String cpf;
    private boolean isLoggedIn = false;

    public Funcionario( String nome, String email, String senha, String cpf,boolean isLoggedIn) {
        super( nome, email, senha);
        this.cpf = cpf;
        this.isLoggedIn = isLoggedIn;
    }




    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }
}
