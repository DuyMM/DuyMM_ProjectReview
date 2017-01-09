package com.example.maimanhduy.rbook.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 12/25/2016.
 */

public class ListHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListHotViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hot_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListHotViewHolder viewHolder = (ListHotViewHolder)holder;
           viewHolder.tvTopItem.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class ListHotViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHotItem;
        TextView tvTopItem;
        TextView tvTitleItem;
        TextView tvAuthorItem;
        public ListHotViewHolder(View itemView) {
            super(itemView);
        imgHotItem = (ImageView) itemView.findViewById(R.id.imgListHotItem);
            tvTopItem = (TextView)itemView.findViewById(R.id.tvListHotItemTop);
        }
    }
}
