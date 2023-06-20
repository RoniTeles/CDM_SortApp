package br.com.uniritter.sortapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ConsultaActivity extends AppCompatActivity {

    String api = "http://localhost:3000/produto";
    ArrayList<ProductModel> allProductList;
    RecyclerView rcvConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        rcvConsulta = findViewById(R.id.rcvConsulta);
        rcvConsulta.setLayoutManager(new LinearLayoutManager(this));

        getDados();
        allProductList = new ArrayList<>();

    }

    private void getDados() {
        RequestQueue queue = Volley.newRequestQueue(this);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, api,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++){
                                JSONObject singleObject = array.getJSONObject(i);
                                ProductModel singleProduct = new ProductModel(
                                        singleObject.getString("nome"),
                                        singleObject.getString("url")
                                );
                                allProductList.add(singleProduct);

                            }

                            rcvConsulta.setAdapter(new ProductAdapter(ConsultaActivity.this, allProductList));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("api", "onErrorResponse: " + error.getLocalizedMessage());

            }
        });

        queue.add(stringRequest);
    }
}