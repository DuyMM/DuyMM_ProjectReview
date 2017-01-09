package com.example.maimanhduy.rbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 1/6/2017.
 */

public class ListBookOnCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListbookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_book_on_sd_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class ListbookViewHolder extends RecyclerView.ViewHolder{

        public ListbookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
