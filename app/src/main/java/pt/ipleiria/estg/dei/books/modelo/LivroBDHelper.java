package pt.ipleiria.estg.dei.books.modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LivroBDHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "dblivros";
    private static final int DB_VERSION = 1;

    private static final String TABLE_NAME = "livros";
    private static final String TITULO = "titulo";
    private static final String SERIE = "serie";
    private static final String AUTOR = "autor";
    private static final String ANO = "ano";
    private static final String CAPA = "capa";
    private static final String ID = "id";


    private final SQLiteDatabase db;//não passei por parâmetro e coloquei dentro do construtor.

    public LivroBDHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.db = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {//aqui criamos a tabela
        String createLivroTable = "CREATE TABLE "+ TABLE_NAME +
                "(" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TITULO + " TEXT NOT NULL, " +
                SERIE + " TEXT NOT NULL, " +
                AUTOR + " TEXT NOT NULL, " +
                ANO + " INTEGER NOT NULL, " +
                CAPA + " INTEGER" +
                ");";
        sqLiteDatabase.execSQL(createLivroTable);//aqui ele cria a tabela
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }
    //1.7.3. adicionarLivroBD
    public Livro adicionarLivroBD(Livro l){
        ContentValues values = new ContentValues();
        values.put(TITULO, l.getTitulo());
        values.put(SERIE,l.getSerie());
        values.put(AUTOR,l.getAutor());
        values.put(ANO,l.getAno());
        values.put(CAPA,l.getCapa());

        long id = this.db.insert(TABLE_NAME,null,values);
        if(id > -1){
            l.setId((int)id);
            return l;
        }
        return null;
    }

    public boolean editarLivroDB(Livro l){
        ContentValues values = new ContentValues();
        values.put(TITULO, l.getTitulo());
        values.put(SERIE,l.getSerie());
        values.put(AUTOR,l.getAutor());
        values.put(ANO,l.getAno());
        values.put(CAPA,l.getCapa());

        return this.db.update(TABLE_NAME, values,ID + " =?",new String[]{""+l.getId()})>0; //o interrogação protege de sql injection.

    }

    public boolean removerLivroDB(int id){
        return (this.db.delete(TABLE_NAME, ID +  " = ?",new String[]{""+ id})==1);
    }

    public ArrayList<Livro> getAllLivrosBD(){
        ArrayList<Livro> livros = new ArrayList<>();

        Cursor cursor = this.db.query(TABLE_NAME,new String[]{ID,TITULO,SERIE,AUTOR,ANO,CAPA},
        null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                Livro auxLivro = new Livro(cursor.getInt(0),
                        cursor.getInt(5),
                        cursor.getInt(4),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3));

                livros.add(auxLivro);

            }while(cursor.moveToNext());
        }
        return livros;
    }
}
