package br.com.gym;
public class ExercicioFichaCliente {
    private int id;
    private int idFichaCliente;
    private int idExercicio;
    private int series;
    private int repeticoes;

    // Construtores, getters e setters
    public ExercicioFichaCliente() {
    }

    public ExercicioFichaCliente(int idFichaCliente, int idExercicio, int series, int repeticoes) {
        this.idFichaCliente = idFichaCliente;
        this.idExercicio = idExercicio;
        this.series = series;
        this.repeticoes = repeticoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdFichaCliente() {
        return idFichaCliente;
    }

    public void setIdFichaCliente(int idFichaCliente) {
        this.idFichaCliente = idFichaCliente;
    }

    public int getIdExercicio() {
        return idExercicio;
    }

    public void setIdExercicio(int idExercicio) {
        this.idExercicio = idExercicio;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    public int getRepeticoes() {
        return repeticoes;
    }

    public void setRepeticoes(int repeticoes) {
        this.repeticoes = repeticoes;
    }

    @Override
    public String toString() {
        return "ExercicioFichaCliente{" +
                "id=" + id +
                ", idFichaCliente=" + idFichaCliente +
                ", idExercicio=" + idExercicio +
                ", series=" + series +
                ", repeticoes=" + repeticoes +
                '}';
    }
}
