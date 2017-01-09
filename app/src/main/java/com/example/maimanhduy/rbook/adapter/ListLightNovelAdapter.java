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

public class ListLightNovelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListLightNovelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_light_novel_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public class ListLightNovelViewHolder extends RecyclerView.ViewHolder{
    public ImageView imgListLightNovel;
        public TextView tvListLightNovelItemTitle;
        public TextView getTvListLightNovelItemAuthor;
        public ListLightNovelViewHolder(View itemView) {
            super(itemView);
        }
    }
}
