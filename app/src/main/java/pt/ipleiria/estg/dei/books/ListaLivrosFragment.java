package pt.ipleiria.estg.dei.books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.books.adaptadores.ListaLivrosAdaptador;
import pt.ipleiria.estg.dei.books.modelo.Livro;
import pt.ipleiria.estg.dei.books.modelo.SingletonGestorLivros;

// Classe Fragment que exibe uma lista de livros
public class ListaLivrosFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView lvLivros;  // ListView para exibir a lista de livros
    private ArrayList<Livro> livros;  // ArrayList para armazenar a lista de objetos Livro
    private FloatingActionButton fabLista;
    private SearchView searchView;
    private SwipeRefreshLayout swipeRefreshLayout;

    // Construtor padrão vazio (necessário para o Fragment)
    public ListaLivrosFragment() {
        // Required empty public constructor
    }

    // Método que cria e infla a View do Fragment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Infla o layout para este Fragment e o converte em uma View
        View view = inflater.inflate(R.layout.fragment_lista_livros, container, false);

        setHasOptionsMenu(true);

        // Inicializa o ListView obtendo sua referência a partir da View inflada
        lvLivros = view.findViewById(R.id.lvLivros);

        // Obtém a lista de livros usando o SingletonGestorLivros para manter dados consistentes
        livros = SingletonGestorLivros.getInstance(getContext()).getLivrosBD();

        // Define o adaptador personalizado para o ListView
        // Passa o contexto atual e a lista de livros para o adaptador
        lvLivros.setAdapter(new ListaLivrosAdaptador(getContext(), livros));

        // Define o comportamento para quando um item da lista for clicado
        lvLivros.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        // Toast.makeText(getContext(), livros.get(i).getTitulo(), Toast.LENGTH_SHORT).show();

                        // Cria uma Intent para abrir a atividade DetalhesLivroActivity
                        Intent intent = new Intent(getContext(), DetalhesLivroActivity.class);

                        // Passa o ID do livro clicado como um extra na Intent
                        // O ID é convertido para int, pois a Intent espera um int (detalhe de implementação)
                        intent.putExtra(DetalhesLivroActivity.ID_LIVRO, (int) l);

                        // Inicia a atividade DetalhesLivroActivity com a Intent criada
                        //startActivity(intent);
                        startActivityForResult(intent, MenuMainActivity.EDIT);
                    }
                });

        fabLista = view.findViewById(R.id.fabLista);
        fabLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getContext(), "teste", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), DetalhesLivroActivity.class);
                //startActivity(intent);
                //START ACTIVITY FOR RESULT
                startActivityForResult(intent, MenuMainActivity.ADD);
            }
        });

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        // Retorna a View do Fragment com o layout configurado
        return view;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_pesquisa, menu);
        MenuItem itemPesquisa = menu.findItem(R.id.itemPesquisa);
        searchView = (SearchView) itemPesquisa.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Livro> tempLivros = new ArrayList<>();

                for(Livro l: SingletonGestorLivros.getInstance(getContext()).getLivrosBD()){
                    if(l.getTitulo().toLowerCase().contains(s.toLowerCase())){
                        tempLivros.add(l);
                    }
                }

                lvLivros.setAdapter(new ListaLivrosAdaptador(getContext(), tempLivros));
                return true;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == MenuMainActivity.ADD || requestCode == MenuMainActivity.EDIT){
                livros = SingletonGestorLivros.getInstance(getContext()).getLivrosBD();
                lvLivros.setAdapter(new ListaLivrosAdaptador(getContext(), livros));

                switch(requestCode){
                    case MenuMainActivity.ADD:
                        Snackbar.make(getView(), "Livro Adicionado com Sucesso", Snackbar.LENGTH_SHORT).show();
                        break;

                    case MenuMainActivity.EDIT:
                        if(data.getIntExtra(MenuMainActivity.OP_CODE,0) == MenuMainActivity.DELETE){
                            Snackbar.make(getView(), "Livro Removido com Sucesso", Snackbar.LENGTH_SHORT).show();
                        }else{
                            Snackbar.make(getView(), "Livro Editado com Sucesso", Snackbar.LENGTH_SHORT).show();
                        }
                        break;

                    default:
                        break;
                }
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRefresh() {
        livros=SingletonGestorLivros.getInstance(getContext()).getLivrosBD();
        lvLivros.setAdapter(new ListaLivrosAdaptador(getContext(), livros));
        swipeRefreshLayout.setRefreshing(false);

    }


}
