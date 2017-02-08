package com.rootrbook.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
 * Created by MaiManhDuy on 12/23/2016.
 */

public class SlideSwipeAdapter extends PagerAdapter {
    private int pos;
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<BookInFireBase> arraySpecial  = new ArrayList<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
    private onCallBackFormSlide mListener;
     SlideSwipeAdapter(Context mContext, ArrayList<BookInFireBase> arraySpecial){
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
         this.arraySpecial = arraySpecial;
    }

    @Override
    public int getCount() {
   return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container,final int position) {
        mListener = (MainActivity)container.getContext();
        mLayoutInflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =mLayoutInflater.inflate(R.layout.custom_slide_item,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imgSlideItem);
        TextView tvTitle = (TextView)view.findViewById(R.id.tvSlideTitle);
        TextView tvAuthor = (TextView)view.findViewById(R.id.tvSlideAuthor);
        if (arraySpecial.size()==0){
            imageView.setImageResource(R.drawable.ic_sync);
        }else {
            storageReference = storage.getReference(arraySpecial.get(position).getLinkImage());
            Glide.with(mContext).using(new FirebaseImageLoader()).load(storageReference).crossFade().centerCrop().into(imageView);
            tvTitle.setText(arraySpecial.get(position).getTitleBook());
            tvAuthor.setText(arraySpecial.get(position).getAuthorName());
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.openBookOnSlide(arraySpecial.get(position).getBookCategory(),arraySpecial.get(position).getId());
                }
            });
        }
        container.addView(view);
        return view;
        //RelativeLayout layout = (RelativeLayout) mLayoutInflater.inflate(R.layout.custom_slide_show, container, false);

    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout)object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return (view ==(RelativeLayout)object);
    }
    public interface onCallBackFormSlide{
        void openBookOnSlide(String category,String position);
    }
}
