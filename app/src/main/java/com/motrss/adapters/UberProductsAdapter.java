package com.motrss.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.motrss.R;
import com.motrss.core.retrofit.response.Products;
import com.motrss.viewholders.UberProductsViewHolder;

import java.util.ArrayList;

/**
 * @Author Dhananjay Kulkarni.
 */

public class UberProductsAdapter extends RecyclerView.Adapter<UberProductsViewHolder> {

    private ArrayList<Products> uberProducts;
    private onClickListener listener;

    public void setData(ArrayList<Products> list){
        this.uberProducts = list;
    }

    public void setClickListener(onClickListener listener){
        this.listener = listener;
    }

    @Override
    public UberProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.component_uber_products, parent, false);
        return new UberProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UberProductsViewHolder holder, final int position) {
        Glide.with(holder.getIvProduct().getContext()).load(uberProducts.get(position).getImage()).into(holder.getIvProduct());
        holder.getTvProductName().setText(uberProducts.get(position).getDisplay_name());
        holder.getLlContainer().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onUberProductClick(uberProducts.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return uberProducts.size();
    }

    public interface onClickListener{
        void onUberProductClick(Products product);
    }
}
