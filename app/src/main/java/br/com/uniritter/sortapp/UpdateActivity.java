package br.com.uniritter.sortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateActivity extends AppCompatActivity {

    EditText nome_item, categoria_item, quantidade_item;
    Button btn_update;
    String id, nome, categoria, quantidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        nome_item = findViewById(R.id.nomeInput2);
        categoria_item = findViewById(R.id.categoriaInput2);
        quantidade_item = findViewById(R.id.quantidadeInput2);
        btn_update = findViewById(R.id.btn_updateItem);
        getAndSetIntentData();
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(UpdateActivity.this);
                db.updateData(id, nome, categoria, quantidade);
            }
        });
    }

    void getAndSetIntentData(){
        if (getIntent().hasExtra("id") && getIntent().hasExtra("nome")
                && getIntent().hasExtra("categoria") && getIntent().hasExtra("quantidade")){
            id = getIntent().getStringExtra("id");
            nome = getIntent().getStringExtra("nome");
            categoria = getIntent().getStringExtra("categoria");
            quantidade = getIntent().getStringExtra("quantidade");


            nome_item.setText(nome);
            categoria_item.setText(categoria);
            quantidade_item.setText(quantidade);

        } else {
            Toast.makeText(this, "Sem itens.", Toast.LENGTH_SHORT).show();
        }
    }
}