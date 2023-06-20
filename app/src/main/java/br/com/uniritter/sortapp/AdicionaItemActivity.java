package br.com.uniritter.sortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AdicionaItemActivity extends AppCompatActivity {

    EditText nome, categoria, quantidade;
    Button salvar;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adiciona_item);

        nome = findViewById(R.id.nomeInput);
        categoria = findViewById(R.id.categoriaInput);
        quantidade = findViewById(R.id.quantidadeInput);
        salvar = findViewById(R.id.btn_salvarItem);

        salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper db = new DatabaseHelper(AdicionaItemActivity.this);
                db.addItem(nome.getText().toString().trim(), categoria.getText().toString().trim(), Integer.parseInt(quantidade.getText().toString().trim()));
            }
        });
    }
}