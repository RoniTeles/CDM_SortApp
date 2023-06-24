package br.com.uniritter.sortapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONException;

import br.com.uniritter.sortapp.adapters.ProductAdapter;

public class ConsultaActivity extends AppCompatActivity {
    private EditText editText;
    private TextView text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        editText = findViewById(R.id.editText);
        text = findViewById(R.id.nomeProduto);
        Button btn_consultar = findViewById(R.id.button_consultar);
        btn_consultar.setOnClickListener(this::onClick);
    }

    private void onClick(View view) {
        if (TextUtils.isEmpty(String.valueOf(editText.getText()))) {
            Toast.makeText(this, "Você precisa informar o código", Toast.LENGTH_SHORT).show();
        } else {
            String url = "http://www.eanpictures.com.br:9000/api/desc/" + editText.getText();

            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                    url, null,
                    response -> {
                        ProductAdapter productAdapter;
                        try {
                            productAdapter = new ProductAdapter(
                                    response.getString("Nome"));
                            text.setText(productAdapter.getName());
                        } catch (JSONException e) {
                            System.out.println("erro no Json." + e.getMessage());
                        }

                    }, error -> {
                System.out.println("erro no Json." + error.getMessage());
                Toast.makeText(this, "Produto não encontrado", Toast.LENGTH_LONG).show();
            });
            RequestQueue queue = Volley.newRequestQueue(this);
            queue.add(request);
        }
    }
}