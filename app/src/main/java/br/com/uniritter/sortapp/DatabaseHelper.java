package br.com.uniritter.sortapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    public static final String DATABASE_NAME = "ListaItens.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "meus_itens";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "nome_item";
    public static final String COLUMN_DIV = "categoria_item";
    public static final String COLUMN_QTD = "quantidade_item";



    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME +  " TEXT, " +
                COLUMN_DIV + " TEXT, " +
                COLUMN_QTD + " INTEGER)";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addItem(String nome, String categoria, int quantidade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_DIV, categoria);
        cv.put(COLUMN_QTD, quantidade);
        long result = db.insert(TABLE_NAME, null, cv);

        if (result == -1){
            Toast.makeText(context, "Falha!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Item adicionado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String nome, String categoria, String quantidade){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME, nome);
        cv.put(COLUMN_DIV, categoria);
        cv.put(COLUMN_QTD, quantidade);

        long result = db.update(TABLE_NAME, cv, "id=?", new String[]{row_id});

        if(result == -1){
            Toast.makeText(context, "Falha na atualização", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Sucesso na atualização", Toast.LENGTH_SHORT).show();
        }
    }
}
