package com.example.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maimanhduy.rbook.MainActivity;
import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/25/2016.
 */

public class ListHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrListHot = new ArrayList<>();
    private Context context;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private callBackFormListHot mListener;
    public ListHotAdapter(ArrayList<BookInFireBase> arrListHot, Context context) {
        this.arrListHot = arrListHot;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity)parent.getContext();
        return new ListHotViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hot_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder( final RecyclerView.ViewHolder holder, int position) {
        ListHotViewHolder viewHolder = (ListHotViewHolder)holder;
        storageReference = storage.getReference(arrListHot.get(position).getLinkImage());
           viewHolder.tvTopItem.setText(position+1+"");
        viewHolder.tvTitleItem.setText(arrListHot.get(position).getTitleBook());
        viewHolder.tvAuthorItem.setText(arrListHot.get(position).getAuthorName());
        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).placeholder(R.drawable.ic_sync).centerCrop().crossFade().into(viewHolder.imgHotItem);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openInfoBookFormListHot(arrListHot.get(holder.getAdapterPosition()).getId(),arrListHot.get(holder.getAdapterPosition()).getBookCategory());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (arrListHot.size()>5){
            return 5;
        }else {
            return arrListHot.size();
        }
    }
    private class ListHotViewHolder extends RecyclerView.ViewHolder{
        ImageView imgHotItem;
        TextView tvTopItem;
        TextView tvTitleItem;
        TextView tvAuthorItem;
         ListHotViewHolder(View itemView) {
            super(itemView);
        imgHotItem = (ImageView) itemView.findViewById(R.id.imgListHotItem);
            tvTopItem = (TextView)itemView.findViewById(R.id.tvListHotItemTop);
            tvTitleItem = (TextView)itemView.findViewById(R.id.tvListHotItemTitle);
            tvAuthorItem  = (TextView)itemView.findViewById(R.id.tvListhotItemAuthor);
        }
    }
    public interface callBackFormListHot{
        void openInfoBookFormListHot(String pos, String category);
    }
}
