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
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/25/2016.
 */

public class ListLightNovelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrBookInFireBase = new ArrayList<>();
    private Context context;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference;
    public ListLightNovelAdapter(ArrayList<BookInFireBase> arrBookInFireBase, Context context) {
        this.arrBookInFireBase = arrBookInFireBase;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListLightNovelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_light_novel_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        storageReference = storage.getReference(arrBookInFireBase.get(position).getLinkImage());
        ListLightNovelViewHolder viewHolder= (ListLightNovelViewHolder)holder;
        viewHolder.tvListLightNovelItemTitle.setText(arrBookInFireBase.get(position).getTitleBook());
        viewHolder.tvListLightNovelItemAuthor.setText(arrBookInFireBase.get(position).getAuthorName());
        Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).centerCrop().placeholder(R.drawable.image1).crossFade().into(viewHolder.imgListLightNovel);
    }

    @Override
    public int getItemCount() {
        return arrBookInFireBase.size();
    }
    private class ListLightNovelViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgListLightNovel;
        public TextView tvListLightNovelItemTitle;
        public TextView tvListLightNovelItemAuthor;
        private ListLightNovelViewHolder(View itemView) {
            super(itemView);
            imgListLightNovel = (ImageView)itemView.findViewById(R.id.imgListLightNovelItem);
            tvListLightNovelItemTitle = (TextView)itemView.findViewById(R.id.tvListLightNovelItemTitle);
            tvListLightNovelItemAuthor = (TextView)itemView.findViewById(R.id.tvListLightNovelItemAuthor);
        }
    }
}
