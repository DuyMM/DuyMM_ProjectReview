package com.example.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/23/2016.
 */

 class SlideSwipeAdapter extends PagerAdapter {
    private int[] image_resources ={R.drawable.image1,R.drawable.image2,R.drawable.image3,R.drawable.image4,R.drawable.image5 };
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private ArrayList<BookInFireBase> arraySpecial  = new ArrayList<>();
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageReference;
     SlideSwipeAdapter(Context mContext, ArrayList<BookInFireBase> arraySpecial){
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
         this.arraySpecial = arraySpecial;
    }

    @Override
    public int getCount() {

      //  return image_resources.length;
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        mLayoutInflater =(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =mLayoutInflater.inflate(R.layout.custom_slide_item,container,false);
        ImageView imageView = (ImageView)view.findViewById(R.id.imgSlideItem);
        if (arraySpecial.size()==0){
            imageView.setImageResource(image_resources[position]);
        }else {
            storageReference = storage.getReference(arraySpecial.get(position).getLinkImage());
            Glide.with(mContext).using(new FirebaseImageLoader()).load(storageReference).crossFade().centerCrop().into(imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("pos",position+"");
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
}
