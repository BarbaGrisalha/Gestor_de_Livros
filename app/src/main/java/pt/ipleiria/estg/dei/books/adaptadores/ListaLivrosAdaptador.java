package pt.ipleiria.estg.dei.books.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import pt.ipleiria.estg.dei.books.R;
import pt.ipleiria.estg.dei.books.modelo.Livro;

// Classe adaptador personalizada que estende BaseAdapter para mostrar uma lista de livros
public class ListaLivrosAdaptador extends BaseAdapter {

    private Context context;  // Contexto da aplicação ou da Activity para acessar recursos do sistema
    private LayoutInflater inflater;  // Inflater para criar views a partir de arquivos XML
    private ArrayList<Livro> livros;  // Lista de objetos Livro a serem exibidos

    // Construtor do adaptador que recebe o contexto e a lista de livros
    public ListaLivrosAdaptador(Context context, ArrayList<Livro> livros) {
        this.context = context;  // Armazena o contexto
        this.livros = livros;    // Armazena a lista de livros
    }

    // Retorna a quantidade de itens na lista
    @Override
    public int getCount() {
        return livros.size();
    }

    // Retorna o objeto Livro na posição 'i'
    @Override
    public Object getItem(int i) {
        return livros.get(i);
    }

    // Retorna o ID do item na posição 'i' (baseado no ID do Livro)
    @Override
    public long getItemId(int i) {
        return livros.get(i).getId();
    }

    // Método principal que cria e retorna a view para cada item da lista
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Inicializa o inflater se ainda não foi inicializado
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        // Se a view ainda não foi criada, infla o layout do item da lista
        if(view == null){
            view = inflater.inflate(R.layout.item_lista_livro, null);
        }

        // Tenta obter o ViewHolder associado à view atual (usando a tag da view)
        ViewHolderLista viewHolderLista = (ViewHolderLista) view.getTag();

        // Se o ViewHolder ainda não existe (primeira vez que esta view é usada), cria um novo
        if(viewHolderLista == null){
            viewHolderLista = new ViewHolderLista(view);
            view.setTag(viewHolderLista);  // Associa o ViewHolder à view usando setTag
        }

        // Atualiza o ViewHolder com os dados do livro atual
        viewHolderLista.update(livros.get(i));

        // Retorna a view para exibir na lista
        return view;
    }

    // Classe interna ViewHolder para armazenar as referências dos elementos da view
    private class ViewHolderLista{
        private TextView tvTitulo, tvSerie, tvAno, tvAutor;  // Elementos de texto para mostrar informações do livro
        private ImageView imgCapa;  // Elemento de imagem para mostrar a capa do livro

        // Construtor do ViewHolder que inicializa as referências dos elementos da view
        public ViewHolderLista(View view){
            tvTitulo = view.findViewById(R.id.tvTitulo);  // Referência para o título do livro
            tvSerie = view.findViewById(R.id.tvSerie);    // Referência para a série do livro
            tvAno = view.findViewById(R.id.tvAno);        // Referência para o ano do livro
            tvAutor = view.findViewById(R.id.tvAutor);    // Referência para o autor do livro
            imgCapa = view.findViewById(R.id.imgCapa);    // Referência para a imagem da capa do livro
        }

        // Método para atualizar os dados da view com as informações do livro
        public void update(Livro livro){
            tvTitulo.setText(livro.getTitulo());  // Define o título do livro
            tvSerie.setText(livro.getSerie());    // Define a série do livro
            tvAutor.setText(livro.getAutor());    // Define o autor do livro
            tvAno.setText(""+livro.getAno());     // Define o ano do livro (convertido para string)
            imgCapa.setImageResource(livro.getCapa());  // Define a imagem da capa do livro
        }
    }
}
