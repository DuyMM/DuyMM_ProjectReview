package com.rootrbook.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rootrbook.maimanhduy.rbook.MainActivity;
import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/24/2016.
 */

public class ListNewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrListNew = new ArrayList<>();
    private Context context;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private callBackFormListNew mListener;
     ListNewAdapter(ArrayList<BookInFireBase> arrListNew, Context context) {
        this.arrListNew = arrListNew;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity) parent.getContext();
       if (arrListNew.size()==0){
           return new ListLoading(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent,false));
       }else {
           return new ListNewViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_list_item, parent,false));
       }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
       if (arrListNew.size()!=0){
           ListNewViewHolder viewHolder = (ListNewViewHolder)holder;
           storageReference = storage.getReference(arrListNew.get(position).getLinkImage());
           viewHolder.tvTitleHotItem.setText(arrListNew.get(position).getTitleBook());
           viewHolder.tvAuthorHotItem.setText(arrListNew.get(position).getAuthorName());
           Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).placeholder(R.drawable.ic_sync).centerCrop().crossFade().into(viewHolder.imgListHotItem);
           viewHolder.imgListHotItem.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mListener.openInforBookListNew(arrListNew.get(holder.getAdapterPosition()).getBookCategory(),arrListNew.get(holder.getAdapterPosition()).getId());
               }
           });
       }
    }

    @Override
    public int getItemCount() {
        if (arrListNew.size()==0){
            return 1;
        }else {
            return arrListNew.size();
        }

    }
    private class ListNewViewHolder extends RecyclerView.ViewHolder{
         TextView tvTitleHotItem;
         TextView tvAuthorHotItem;
         ImageView imgListHotItem;
         ListNewViewHolder(View itemView) {
            super(itemView);
            tvTitleHotItem = (TextView)itemView.findViewById(R.id.tvListNewItemTitle);
            tvAuthorHotItem = (TextView)itemView.findViewById(R.id.tvListNewItemAuthor);
            imgListHotItem = (ImageView)itemView.findViewById(R.id.imgListNewItem);
        }
    }
    private class ListLoading extends RecyclerView.ViewHolder{

        public ListLoading(View itemView) {
            super(itemView);
        }
    }
    public interface callBackFormListNew{
        void openInforBookListNew(String category,String position);
    }
}
