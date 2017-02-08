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
 * Created by MaiManhDuy on 12/25/2016.
 */

public class ListLightNovelAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<BookInFireBase> arrBookInFireBase = new ArrayList<>();
    private Context context;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private CallBackMainFormListLightNovel mListener;

    ListLightNovelAdapter(ArrayList<BookInFireBase> arrBookInFireBase, Context context) {
        this.arrBookInFireBase = arrBookInFireBase;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity) parent.getContext();
        if (arrBookInFireBase.size() == 0) {
            return new Loading(LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_item, parent, false));
        } else {
            return new ListLightNovelViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_light_novel_list_item, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
      if (arrBookInFireBase.size()!=0){
          storageReference = storage.getReference(arrBookInFireBase.get(position).getLinkImage());
          ListLightNovelViewHolder viewHolder = (ListLightNovelViewHolder) holder;
          viewHolder.tvListLightNovelItemTitle.setText(arrBookInFireBase.get(position).getTitleBook());
          viewHolder.tvListLightNovelItemAuthor.setText(arrBookInFireBase.get(position).getAuthorName());
          Glide.with(context).using(new FirebaseImageLoader()).load(storageReference).centerCrop().placeholder(R.drawable.ic_sync).crossFade().into(viewHolder.imgListLightNovel);
          viewHolder.imgListLightNovel.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  mListener.openInfoBookFormLightNovel(arrBookInFireBase.get(holder.getAdapterPosition()).getId(), arrBookInFireBase.get(holder.getAdapterPosition()).getBookCategory());
              }
          });
      }
    }

    @Override
    public int getItemCount() {
        if (arrBookInFireBase.size()==0){
            return 1;
        }else {
            return arrBookInFireBase.size();
        }
    }

    private class ListLightNovelViewHolder extends RecyclerView.ViewHolder {
        ImageView imgListLightNovel;
        TextView tvListLightNovelItemTitle;
        TextView tvListLightNovelItemAuthor;

        private ListLightNovelViewHolder(View itemView) {
            super(itemView);
            imgListLightNovel = (ImageView) itemView.findViewById(R.id.imgListLightNovelItem);
            tvListLightNovelItemTitle = (TextView) itemView.findViewById(R.id.tvListLightNovelItemTitle);
            tvListLightNovelItemAuthor = (TextView) itemView.findViewById(R.id.tvListLightNovelItemAuthor);
        }
    }

    private class Loading extends RecyclerView.ViewHolder {

        Loading(View itemView) {
            super(itemView);
        }
    }

    public interface CallBackMainFormListLightNovel {
        void openInfoBookFormLightNovel(String pos, String category);
    }
}
