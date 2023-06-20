package br.com.uniritter.sortapp;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private Context context;
    private ArrayList id_item, nome_item, categoria_item, quantidade_item;

    Activity activity;
    CustomAdapter(Activity activity,
                  Context context,
                  ArrayList id_item,
                  ArrayList nome_item,
                  ArrayList categoria_item,
                  ArrayList quantidade_item) {
        this.activity = activity;
        this.context = context;
        this.id_item = id_item;
        this.nome_item = nome_item;
        this.categoria_item = categoria_item;
        this.quantidade_item = quantidade_item;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.linha_itens, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.nome_item_txt.setText(String.valueOf(nome_item.get(position)));
        holder.categoria_item_txt.setText(String.valueOf(categoria_item.get(position)));
        holder.quantidade_item_txt.setText(String.valueOf(quantidade_item.get(position)));
        holder.mainLayout.setOnClickListener(view ->  {
            Intent intent = new Intent(context, UpdateActivity.class);
            intent.putExtra("id", String.valueOf(id_item.get(position)));
            intent.putExtra("nome", String.valueOf(nome_item.get(position)));
            intent.putExtra("categoria", String.valueOf(categoria_item.get(position)));
            intent.putExtra("quantidade", String.valueOf(quantidade_item.get(position)));
            activity.startActivityForResult(intent, 1);
        });
    }

    @Override
    public int getItemCount() {
        return id_item.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nome_item_txt, categoria_item_txt, quantidade_item_txt;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nome_item_txt = itemView.findViewById(R.id.nomeItem);
            categoria_item_txt = itemView.findViewById(R.id.categoriaItem);
            quantidade_item_txt = itemView.findViewById(R.id.quantidadeItem);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }

}
