package com.example.maimanhduy.rbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 1/8/2017.
 */

public class ListFavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListFavoriteBookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_list_recylerview,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }
    public class ListFavoriteBookViewHolder extends RecyclerView.ViewHolder{
        public ListFavoriteBookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
