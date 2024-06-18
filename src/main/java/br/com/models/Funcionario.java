package br.com.models;


public class Funcionario extends Pessoa{
    private  String cpf;



    public Funcionario( String nome,String cpf, String senha, String email) {
        super( nome, senha,email);
        this.cpf = cpf;

    }

    public Funcionario(String senha,String cpf) {
        super(senha);
        this.cpf = cpf;
    }

    public Funcionario(int id, String nome, String cpf) {
        super(nome);
    }


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public String toString() {
        return "Funcionario{" +
                "cpf='" + cpf + '\'' +
                '}';
    }
}
