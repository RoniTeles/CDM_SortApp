package br.com.uniritter.sortapp.adapters;

import androidx.annotation.NonNull;

public class ProductAdapter {
    private int id;
    private String name;

    public ProductAdapter(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @NonNull
    @Override
    public String toString() {
        return "Nome:"+name;
    }
}
