package com.example.maimanhduy.rbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 12/24/2016.
 */

public class ListNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListNewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_list_item, parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListNewViewHolder viewHolder = (ListNewViewHolder)holder;
        viewHolder.imgListHotItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return 10;
    }
    public class ListNewViewHolder extends RecyclerView.ViewHolder{
        public TextView tvTitleHotItem;
        public TextView tvAuthorHotItem;
        public ImageView imgListHotItem;
        public ListNewViewHolder(View itemView) {
            super(itemView);
            tvTitleHotItem = (TextView)itemView.findViewById(R.id.tvListNewItemTitle);
            tvAuthorHotItem = (TextView)itemView.findViewById(R.id.tvListNewItemAuthor);
            imgListHotItem = (ImageView)itemView.findViewById(R.id.imgListNewItem);
        }
    }
}
