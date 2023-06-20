package br.com.uniritter.sortapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.useHolder> {

    ConsultaActivity consultaActivity;
    List<ProductModel> allProductList;

    public ProductAdapter(ConsultaActivity consultaActivity, ArrayList<ProductModel> allProductList) {
    }

    @NonNull
    @Override
    public useHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        return new useHolder(LayoutInflater.from(consultaActivity).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.useHolder holder, int position) {
        holder.itemTxt.setText(allProductList.get(position).getNome());
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class useHolder extends RecyclerView.ViewHolder {
        TextView itemTxt;

        public useHolder(@NonNull @NotNull View itemView){
            super(itemView);
            itemTxt = itemView.findViewById(R.id.itemTxt);
        }
    }
}
