package br.com.gym;

public class Exercicio {
    private int id;
    private String nome;
    private String musculo;
    private String categoria;

    // Construtores, getters e setters
    public Exercicio() {
    }

    public Exercicio(String nome, String musculo, String categoria) {
        this.nome = nome;
        this.musculo = musculo;
        this.categoria = categoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMusculo() {
        return musculo;
    }

    public void setMusculo(String musculo) {
        this.musculo = musculo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}

