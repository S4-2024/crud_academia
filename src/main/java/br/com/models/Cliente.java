package br.com.models;

import br.com.enums.Pagamento;

public class Cliente extends Pessoa{

    private Pagamento pagamento;


    public Cliente(String nome, String email, String senha,Pagamento pagamento) {
        super(nome, email, senha);

        this.pagamento = pagamento;
    }

    public Cliente(int id,String nome, String email, String senha,Pagamento pagamento) {
        super(id,nome, email, senha);

        this.pagamento = pagamento;
    }


    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "pagamento=" + pagamento +
                '}';
    }
}
