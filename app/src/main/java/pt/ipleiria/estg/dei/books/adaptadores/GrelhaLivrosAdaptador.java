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

public class GrelhaLivrosAdaptador extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private ArrayList<Livro> livros;

    public GrelhaLivrosAdaptador(Context context, ArrayList<Livro> livros) {
        this.context = context;
        this.livros = livros;
    }


    @Override
    public int getCount() {
        return livros.size();
    }

    @Override
    public Object getItem(int i) {
        return livros.get(i);
    }

    @Override
    public long getItemId(int i) {
        return livros.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if(view == null){
            view = inflater.inflate(R.layout.item_grelha_livro, null);
        }

        ViewHolderGrelha viewHolderGrelha = (ViewHolderGrelha) view.getTag(); //explicação da pulseira
        if(viewHolderGrelha == null){
            viewHolderGrelha= new ViewHolderGrelha(view);
            view.setTag(viewHolderGrelha); //toma o carimbo
        }
        viewHolderGrelha.update(livros.get(i));

        return view;
    }

    private class ViewHolderGrelha{

        private ImageView imgCapa;

        public ViewHolderGrelha(View view){

            imgCapa = view.findViewById(R.id.imgCapa);
        }

        public void update(Livro livro){

            imgCapa.setImageResource(livro.getCapa());
        }
    }
}
