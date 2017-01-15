package com.example.maimanhduy.rbook.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.FavoriteViewpagerAdapter;
import com.example.maimanhduy.rbook.adapter.ListFavoriteAdapter;
import com.example.maimanhduy.rbook.fragments.FavoriteListLightNovelFragment;

public class FavoriteBookActivity extends AppCompatActivity implements View.OnClickListener,FavoriteListLightNovelFragment.OnFragmentInteractionListener, ListFavoriteAdapter.OnCallBackFormListFavoriteAdapter {
    private Button btnLightNovel;
    private Button btnComic;
    private Button btnOther;
    private ViewPager mViewpagerFavorite;
    private ImageView mImgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_book);
        btnLightNovel = (Button) findViewById(R.id.btnFavoriteLightNovel);
        btnComic = (Button) findViewById(R.id.btnFavoriteComic);
        btnOther = (Button) findViewById(R.id.btnFavoriteOther);
        mViewpagerFavorite = (ViewPager)findViewById(R.id.viewpagerFavorite);
        mImgBack = (ImageView)findViewById(R.id.imgBackFormFavorate);
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new FavoriteViewpagerAdapter(manager);
        mViewpagerFavorite.setAdapter(adapter);
        btnLightNovel.setOnClickListener(this);
        btnComic.setOnClickListener(this);
        btnOther.setOnClickListener(this);
        mViewpagerFavorite.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:setBtnLightNovelSelect();break;
                    case 1:setBtnComicSelect();break;
                    case 2:setBtnOtherSelect();break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnFavoriteLightNovel:
               setBtnLightNovelSelect();
                break;
            case R.id.btnFavoriteComic:
               setBtnComicSelect();
                break;
            case R.id.btnFavoriteOther:
               setBtnOtherSelect();
                break;
        }
    }
    private void setBtnLightNovelSelect(){ btnLightNovel.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia_selecter));
        btnLightNovel.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorWhite));
        setDefaultBackgroundButton(2);
        setDefaultBackgroundButton(3);
        mViewpagerFavorite.setCurrentItem(0);}
    private void setBtnComicSelect(){ btnComic.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia_selecter));
        btnComic.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorWhite));
        setDefaultBackgroundButton(1);
        setDefaultBackgroundButton(3);
        mViewpagerFavorite.setCurrentItem(1);}
    private void setBtnOtherSelect(){ btnOther.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia_selecter));
        btnOther.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorWhite));
        setDefaultBackgroundButton(1);
        setDefaultBackgroundButton(2);
        mViewpagerFavorite.setCurrentItem(2);}
    private void setDefaultBackgroundButton(int i) {
        switch (i) {
            case 1:
                btnLightNovel.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia));
                btnLightNovel.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorGrey));
                break;
            case 2:
                btnComic.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia));
                btnComic.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorGrey));
                break;
            case 3:
                btnOther.setBackground(ContextCompat.getDrawable(getApplication(), R.drawable.cricle_button_sepia));
                btnOther.setTextColor(ContextCompat.getColor(getApplication(), R.color.colorGrey));
                break;
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void openBook(String position, String linkBook) {
        Intent intent = new Intent(FavoriteBookActivity.this,ReadBookActivity.class);
        intent.putExtra("id",position);
        intent.putExtra("linkBook",linkBook);
        startActivity(intent);
    }
}
