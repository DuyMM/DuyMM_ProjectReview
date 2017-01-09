package com.example.maimanhduy.rbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 12/28/2016.
 */

public class ListInfoMoreBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new listInfoMoreBookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_info_more_list_item,parent,false)) ;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    class listInfoMoreBookViewHolder extends RecyclerView.ViewHolder{

        public listInfoMoreBookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
