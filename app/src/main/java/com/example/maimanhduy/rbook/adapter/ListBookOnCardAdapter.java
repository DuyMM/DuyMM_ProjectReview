package com.example.maimanhduy.rbook.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.model.BookOnSdCard;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 1/6/2017.
 */

public class ListBookOnCardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookOnSdCard> arrBookOnSDcard = new ArrayList<>();
    private Context context;

    public ListBookOnCardAdapter(ArrayList<BookOnSdCard> arrBookOnSDcard, Context context) {
        this.arrBookOnSDcard = arrBookOnSDcard;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListbookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_book_on_sd_card_item,parent,false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ListbookViewHolder viewHolder = (ListbookViewHolder) holder;
        viewHolder.tvTitle.setText(arrBookOnSDcard.get(position).getTitle());
        Bitmap bitmap = BitmapFactory.decodeFile(arrBookOnSDcard.get(position).getLinkImage());
        viewHolder.imgBook.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return arrBookOnSDcard.size();
    }
    private class ListbookViewHolder extends RecyclerView.ViewHolder{
        private TextView tvTitle;
        private ImageView imgBook;
        private TextView tvAuthor;
         ListbookViewHolder(View itemView) {
            super(itemView);
             tvTitle = (TextView)itemView.findViewById(R.id.tvBookOnSdCardTitleItem);
             imgBook = (ImageView)itemView.findViewById(R.id.imgBookOnSDcardItem);
        }
    }
}
