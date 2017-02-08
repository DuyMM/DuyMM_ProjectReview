package com.rootrbook.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.activities.AllBookActivity;
import com.rootrbook.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/21/2017.
 */

public class ListAllBookAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrAllBook = new ArrayList<>();
    private Context mContext;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;
    private OnCallBackFormListAllBookAdapter mListener;
    public ListAllBookAdapter(ArrayList<BookInFireBase> arrAllBook, Context mContext) {
        this.arrAllBook = arrAllBook;
        this.mContext = mContext;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (AllBookActivity)parent.getContext();
        return new ListAllBookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_all_book_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        storageRef = storage.getReference(arrAllBook.get(position).getLinkImage());
        ListAllBookViewHolder viewHolder = (ListAllBookViewHolder) holder;
        viewHolder.tvTitle.setText(arrAllBook.get(position).getTitleBook());
        viewHolder.tvAuthor.setText(arrAllBook.get(position).getAuthorName());
        Glide.with(mContext).using(new FirebaseImageLoader()).load(storageRef).placeholder(R.drawable.ic_sync).centerCrop().crossFade().into(viewHolder.imgAllBook);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            mListener.openBookFormAllBook(arrAllBook.get(position).getBookCategory(),arrAllBook.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrAllBook.size();
    }
    private class ListAllBookViewHolder extends RecyclerView.ViewHolder{
        ImageView imgAllBook;
        TextView tvTitle;
        TextView tvAuthor;
         ListAllBookViewHolder(View itemView) {
            super(itemView);
             imgAllBook = (ImageView)itemView.findViewById(R.id.imgAllBook);
             tvAuthor = (TextView)itemView.findViewById(R.id.tvAuthorAllBookItem);
             tvTitle = (TextView)itemView.findViewById(R.id.tvTitleAllBookItem);
        }
    }
    public interface OnCallBackFormListAllBookAdapter{
        void openBookFormAllBook(String category,String pos);
    }
}
