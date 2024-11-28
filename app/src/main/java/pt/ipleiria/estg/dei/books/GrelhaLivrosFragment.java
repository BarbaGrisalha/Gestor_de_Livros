package pt.ipleiria.estg.dei.books;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.books.adaptadores.GrelhaLivrosAdaptador;
import pt.ipleiria.estg.dei.books.modelo.Livro;
import pt.ipleiria.estg.dei.books.modelo.SingletonGestorLivros;

public class GrelhaLivrosFragment extends Fragment {

    private GridView gvLivros;
    private ArrayList<Livro> livros;

    public GrelhaLivrosFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_grelha_livros, container, false);

        gvLivros = view.findViewById(R.id.gridLivros);

        livros = SingletonGestorLivros.getInstance(getContext()).getLivrosBD();

        gvLivros.setAdapter(new GrelhaLivrosAdaptador(getContext(), livros));


        gvLivros.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        //Toast.makeText(getContext(), livros.get(i).getTitulo(), Toast.LENGTH_SHORT).show();

                        //Fazer codigo para ir para os detalhes livro
                        Intent intent = new Intent(getContext(), DetalhesLivroActivity.class);
                        intent.putExtra(DetalhesLivroActivity.ID_LIVRO, (int) l);
                        startActivity(intent);
                    }
                });

        return view;
    }
}