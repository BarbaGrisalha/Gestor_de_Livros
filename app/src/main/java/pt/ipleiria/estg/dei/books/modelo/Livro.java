package pt.ipleiria.estg.dei.books.modelo;

public class Livro {
    private int id, capa, ano;
    private String titulo, serie, autor;
    private static int autoIncrementId = 1;

    //Contrutores  1.7.1. foram feitas alteraçãos como a imclusao d int id.
    public Livro(int id,int capa, int ano, String titulo, String serie, String autor) {
        this.id = id;
        this.capa = capa;
        this.ano = ano;
        this.titulo = titulo;
        this.serie = serie;
        this.autor = autor;
    }
    //1.7.2. Exc.
    public void setId(int id) {
        this.id = id;
    }

    //Métodos Getters
    public int getId() {
        return id;
    }

    public int getCapa() {
        return capa;
    }

    public int getAno() {
        return ano;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getSerie() {
        return serie;
    }

    public String getAutor() {
        return autor;
    }

    //Métodos Setters
    public void setCapa(int capa) {
        this.capa = capa;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
