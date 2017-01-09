package com.example.maimanhduy.rbook.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.maimanhduy.rbook.fragments.FavoriteListLightNovelFragment;

/**
 * Created by MaiManhDuy on 1/9/2017.
 */

public class FavoriteViewpagerAdapter extends FragmentStatePagerAdapter {
    public FavoriteViewpagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment newInstance = FavoriteListLightNovelFragment.newInstance(position+"","");
        return newInstance;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
