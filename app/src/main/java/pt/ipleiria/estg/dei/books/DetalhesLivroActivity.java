package pt.ipleiria.estg.dei.books;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pt.ipleiria.estg.dei.books.modelo.Livro;
import pt.ipleiria.estg.dei.books.modelo.SingletonGestorLivros;

public class DetalhesLivroActivity extends AppCompatActivity {
    public static final String ID_LIVRO = "ID_LIVRO";
    private Livro livro;

    private EditText etTitulo, etSerie, etAno, etAutor;
    private ImageView imgCapa;
    private FloatingActionButton fabGuardar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes_livro);

        int id = getIntent().getIntExtra(ID_LIVRO, 0);
        livro = SingletonGestorLivros.getInstance(getApplicationContext()).getLivro(id);

        etTitulo = findViewById(R.id.etTitulo);
        etSerie = findViewById(R.id.etSerie);
        etAno = findViewById(R.id.etAno);
        etAutor = findViewById(R.id.etAutor);
        imgCapa = findViewById(R.id.imgCapa);

        fabGuardar = findViewById(R.id.fabGuardar);

        if(livro != null){
            carregarLivro();
            fabGuardar.setImageResource(R.drawable.ic_action_guardar);
        }else{
            setTitle(getString(R.string.guardar));
            fabGuardar.setImageResource(R.drawable.ic_action_adicionar);

        }

        fabGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(livro != null){
                    //GUARDAR

                    if (!isLivroValido()) {
                        // Se os campos não são válidos, mostra uma mensagem
                        Toast.makeText(DetalhesLivroActivity.this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show();
                        return; // Sai do método sem salvar
                    }
                    //IF O LIVRO QUE ESTOU A GRAVAR É VALIDO
                    //VERIFICAR SE OS CAMPOS ESTÃO PREENCHIDOS
                    //FAZER UMA FUNÇÃO QUE DEVOLVE TRUE OU FALSE
                    //CHAMAR NESSA FUNÇAOIF

                    livro.setTitulo(etTitulo.getText().toString());
                    livro.setSerie(etSerie.getText().toString());
                    livro.setAutor(etAutor.getText().toString());
                    livro.setAno(Integer.parseInt(etAno.getText().toString()));

                    SingletonGestorLivros.getInstance(getApplicationContext()).editarLivroBD(livro);

                    // Ex 10.2
                    Intent intent = new Intent();
                    intent.putExtra(MenuMainActivity.OP_CODE, MenuMainActivity.EDIT);
                    setResult(RESULT_OK, intent);
                    finish();

                }else{
                    //ADICIONAR
                    if (!isLivroValido()) {
                        // Se os campos não são válidos, mostra uma mensagem
                        Toast.makeText(DetalhesLivroActivity.this, "Preencha todos os campos corretamente!", Toast.LENGTH_SHORT).show();
                        return; // Sai do método sem salvar
                    }

                    //IF O LIVRO QUE ESTOU A GRAVAR É VALIDO
                    //VERIFICAR SE OS CAMPOS ESTÃO PREENCHIDOS
                    //FAZER UMA FUNÇÃO QUE DEVOLVE TRUE OU FALSE
                    //CHAMAR NESSA FUNÇAO

                    livro =  new Livro(0,R.drawable.logoipl, //introduzi aqui = 1.7.2.  Ex.
                            Integer.parseInt(etAno.getText().toString()),
                            etTitulo.getText().toString(),
                            etSerie.getText().toString(),
                            etAutor.getText().toString());


                    SingletonGestorLivros.getInstance(getApplicationContext()).adicionarLivroBD(livro);

                    //Ex 10.2
                    Intent intent = new Intent();
                    intent.putExtra(MenuMainActivity.OP_CODE, MenuMainActivity.ADD);
                    setResult(RESULT_OK, intent);
                    finish();

                }
            }
        });

    }

    // Função para verificar se o livro é válido
    private boolean isLivroValido() {
        // Verifica se os campos não estão vazios
        if (etTitulo.getText().toString().trim().isEmpty()) return false;
        if (etSerie.getText().toString().trim().isEmpty()) return false;
        if (etAutor.getText().toString().trim().isEmpty()) return false;

        // Verifica se o ano é válido
        String anoTexto = etAno.getText().toString().trim();
        if (anoTexto.isEmpty()) return false;

        try {
            int ano = Integer.parseInt(anoTexto);
            if (ano <= 0) return false; // Ano não pode ser negativo ou zero
        } catch (NumberFormatException e) {
            return false; // O ano não é um número válido
        }

        // Todos os campos são válidos
        return true;
    }

    private void carregarLivro(){
        //setTitle("Detalhes: " + livro.getTitulo());
        setTitle(livro.getTitulo());
        etTitulo.setText(livro.getTitulo());
        etSerie.setText(livro.getSerie());
        etAno.setText(""+livro.getAno());
        etAutor.setText(livro.getAutor());
        imgCapa.setImageResource(livro.getCapa());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       if(livro != null){
           getMenuInflater().inflate(R.menu.menu_remover, menu);
           return super.onCreateOptionsMenu(menu);
       }
       return false;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.itemRemover){
            dialogRemover();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void dialogRemover() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.txt_titulo_remover_livro)
                .setMessage(R.string.txt_texto_remover_livro_livro)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SingletonGestorLivros.getInstance(getApplicationContext()).removerLivroBD(livro.getId());
                        Intent intent = new Intent();
                        intent.putExtra(MenuMainActivity.OP_CODE,MenuMainActivity.DELETE);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setIcon(android.R.drawable.ic_delete)
                .show();


    }
}