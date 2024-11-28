package pt.ipleiria.estg.dei.books.modelo;

import android.content.Context;

import java.util.ArrayList;

public class SingletonGestorLivros {

    private ArrayList<Livro> livros;
    private static SingletonGestorLivros instance = null;

    private LivroBDHelper livroBDHelper = null;


    public static synchronized SingletonGestorLivros getInstance(Context context) {
        if (instance == null) {
            instance = new SingletonGestorLivros(context);
        }
        return instance;
    }

    //Ex 6.1
    private SingletonGestorLivros(Context context) {
        //gerarDadosDinamico();
        livros = new ArrayList<>();
        livroBDHelper = new LivroBDHelper(context);
    }

   /* private void gerarDadosDinamico() {


        livros.add(new Livro(R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 1", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 2", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.logoipl, 2024, "Programar em Android AMSI - 3", "2ª Temporada", " AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 4", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 5", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.logoipl, 2024, "Programar em Android AMSI - 6", "2ª Temporada", " AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 7", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid1, 2024, "Programar em Android AMSI - 8", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.logoipl, 2024, "Programar em Android AMSI - 9", "2ª Temporada", " AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid2, 2024, "Programar em Android AMSI - 10", "2ª Temporada", "AMSI TEAM"));
        livros.add(new Livro(R.drawable.programarandroid2, 2020, "Harry Potter", "2ª Temporada", "JK Rolling"));
    }*/

    // EX 6.2
    public ArrayList<Livro> getLivrosBD() {
        livros = livroBDHelper.getAllLivrosBD();
        return new ArrayList<>(livros);
    }

    // Recebe um id como parametro para no Detalhes Livros Activity receber o livro específico
    public Livro getLivro(int id){
        for (Livro l: livros) {
            if (l.getId() == id) {
                return l;
            }
        }
            return null;
    }

    public void adicionarLivroBD(Livro livro){
        Livro auxLivro = livroBDHelper.adicionarLivroBD(livro);
        if(auxLivro != null){
            livros.add(livro);
        }
    }

    public void editarLivroBD(Livro livro){
        Livro l = getLivro(livro.getId());
        if(l != null){
            livroBDHelper.editarLivroDB(livro);
        }
    }

    public void removerLivroBD(int idLivro){
        Livro l = getLivro(idLivro);
        if(l != null){
            if(livroBDHelper.removerLivroDB(l.getId())){
                livros.remove(l);
            }

        }
    }

}

