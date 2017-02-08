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
import com.rootrbook.maimanhduy.rbook.activities.BookInSdCardActivity;
import com.rootrbook.maimanhduy.rbook.model.BookOnSdCard;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/6/2017.
 */

public class ListBookOnCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookOnSdCard> arrBookOnSDcard = new ArrayList<>();
    private Context context;
    private onCallBackFormListBookSDCardAdapter mListener;
    public ListBookOnCardAdapter(ArrayList<BookOnSdCard> arrBookOnSDcard, Context context) {
        this.arrBookOnSDcard = arrBookOnSDcard;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (BookInSdCardActivity)parent.getContext();
       if (arrBookOnSDcard.size()==0){
           return new EmptyItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_item,parent,false));
       }else {
           return new ListbookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_book_on_sd_card_item,parent,false));
       }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder,  int position) {
       if (arrBookOnSDcard.size()>0){
           final ListbookViewHolder viewHolder = (ListbookViewHolder) holder;
           viewHolder.tvTitle.setText(arrBookOnSDcard.get(position).getTitle());
           //Bitmap bitmap = BitmapFactory.decodeFile(arrBookOnSDcard.get(position).getLinkImage());
           //viewHolder.imgBook.setImageBitmap(bitmap);
           Glide.with(context).load(new File(arrBookOnSDcard.get(position).getLinkImage())).centerCrop().crossFade().into(viewHolder.imgBook);
           viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mListener.openBookSdCard(arrBookOnSDcard.get(viewHolder.getAdapterPosition()).getLinkBook());
               }
           });
           viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
               @Override
               public boolean onLongClick(View view) {
                   mListener.updateRecylerView(viewHolder.getAdapterPosition());
                   return false;
               }
           });
           viewHolder.tvCategory.setText(arrBookOnSDcard.get(position).getCategory());
       }
    }

    @Override
    public int getItemCount() {
        if (arrBookOnSDcard.size()==0){
            return 1;
        }else {
            return arrBookOnSDcard.size();
        }
    }
    private class ListbookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView imgBook;
        private TextView tvCategory;
         ListbookViewHolder(View itemView) {
            super(itemView);
             tvTitle = (TextView)itemView.findViewById(R.id.tvBookOnSdCardTitleItem);
             imgBook = (ImageView)itemView.findViewById(R.id.imgBookOnSDcardItem);
             tvCategory = (TextView)itemView.findViewById(R.id.tvBookOnSdCardCategoryItem);
        }
    }
    public interface onCallBackFormListBookSDCardAdapter{
        void openBookSdCard(String linkBook);
        void updateRecylerView(int position);
    }

    private class EmptyItemViewHolder extends RecyclerView.ViewHolder{
         EmptyItemViewHolder(View itemView) {
            super(itemView);
        }
    }
}
