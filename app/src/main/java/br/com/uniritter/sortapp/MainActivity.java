package br.com.uniritter.sortapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

import br.com.uniritter.sortapp.adapters.CustomAdapter;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    Button logout_button;
    FirebaseUser user;

    RecyclerView recyclerView;
    FloatingActionButton add_button;

    FloatingActionButton scan_button;
    DatabaseHelper DB;
    ArrayList<String>   id_item, nome_item, categoria_item, quantidade_item;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        logout_button = findViewById(R.id.btn_logout);
        user = auth.getCurrentUser();
        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.floatingAddButton);
        scan_button = findViewById(R.id.floatingScan);

        if (user == null){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        add_button.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, AdicionaItemActivity.class);
            startActivity(intent);
        });

        scan_button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ConsultaActivity.class);
            startActivity(intent);
        });

        DB = new DatabaseHelper(MainActivity.this);
        id_item = new ArrayList<>();
        nome_item = new ArrayList<>();
        categoria_item = new ArrayList<>();
        quantidade_item = new ArrayList<>();

        carregaDadosArray();
        customAdapter = new CustomAdapter(MainActivity.this, MainActivity.this, id_item, nome_item, categoria_item, quantidade_item);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1){
            recreate();
        }
    }

    void carregaDadosArray(){
        Cursor cursor = DB.readAllData();
        if(cursor.getCount() == 0){
            Toast.makeText(this, "Sem itens", Toast.LENGTH_SHORT).show();
        }else {
            while (cursor.moveToNext()){
                id_item.add(cursor.getString(0));
                nome_item.add(cursor.getString(1));
                categoria_item.add(cursor.getString(2));
                quantidade_item.add(cursor.getString(3));
            }
        }
    }
}