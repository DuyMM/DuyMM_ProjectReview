package com.example.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.activities.FavoriteBookActivity;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/8/2017.
 */

public class ListFavoriteAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrListFavorite = new ArrayList<>();
    private Context context;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;
    private OnCallBackFormListFavoriteAdapter mListener;
    public ListFavoriteAdapter(ArrayList<BookInFireBase> arrListFavorite, Context context) {
        this.arrListFavorite = arrListFavorite;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (FavoriteBookActivity)parent.getContext();
        return new ListFavoriteBookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_favorite_list_recylerview,parent,false));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        storageRef = storage.getReference(arrListFavorite.get(position).getLinkImage());
        ListFavoriteBookViewHolder viewHolder = (ListFavoriteBookViewHolder)holder;
        viewHolder.tvAuthor.setText(arrListFavorite.get(position).getAuthorName());
        viewHolder.tvTitle.setText(arrListFavorite.get(position).getTitleBook());
        Glide.with(context).using(new FirebaseImageLoader()).load(storageRef).placeholder(R.drawable.ic_sync).centerCrop().crossFade().into(viewHolder.imgFavorite);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.openBook(arrListFavorite.get(holder.getAdapterPosition()).getId(),arrListFavorite.get(holder.getAdapterPosition()).getLinkBook());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrListFavorite.size();
    }
    private class ListFavoriteBookViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFavorite;
        TextView tvTitle;
        TextView tvAuthor;
         ListFavoriteBookViewHolder(View itemView) {
            super(itemView);
             imgFavorite = (ImageView)itemView.findViewById(R.id.imgFavoriteRecylerView);
             tvTitle = (TextView)itemView.findViewById(R.id.tvTitleFavoriteRecylerView);
             tvAuthor = (TextView)itemView.findViewById(R.id.tvAuthorFavoriteRecylerview);
        }
    }
    public interface OnCallBackFormListFavoriteAdapter{
        void openBook(String position,String linkBook);
    }
}
