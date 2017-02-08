package com.rootrbook.maimanhduy.rbook.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rootrbook.maimanhduy.rbook.MainActivity;
import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.model.BookInFireBase;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.hado.indicatorlibrary.IndicatorView;
import com.hado.indicatorlibrary.PagesLessException;

import java.util.ArrayList;

/**
 * Created by MaiManhDuy on 12/23/2016.
 */

public class MainRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private ArrayList<BookInFireBase> arrLightNovel = new ArrayList<>();
    private ArrayList<BookInFireBase> arrComic = new ArrayList<>();
    private ArrayList<BookInFireBase> arrOther = new ArrayList<>();
    private ArrayList<BookInFireBase> arrNew = new ArrayList<>();
    private ArrayList<BookInFireBase> arrHot = new ArrayList<>();
    private ArrayList<BookInFireBase> arrSpecial = new ArrayList<>();
    private onCallBackFormMainAdapter mListener;
    public MainRecyclerAdapter(Context mContext, ArrayList<BookInFireBase> arrLightNovel, ArrayList<BookInFireBase> arrComic, ArrayList<BookInFireBase> arrOther, ArrayList<BookInFireBase> arrNew, ArrayList<BookInFireBase> arrHot, ArrayList<BookInFireBase> arrSpecial) {
        this.mContext = mContext;
        this.arrLightNovel = arrLightNovel;
        this.arrComic = arrComic;
        this.arrOther = arrOther;
        this.arrSpecial = arrSpecial;
        this.arrNew = arrNew;
        this.arrHot = arrHot;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mListener = (MainActivity) parent.getContext();
        switch (viewType) {
            case 0:
                return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_slide_show, parent, false));
            case 1:
                return new ListNewMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_list_recylerview, parent, false));
            case 3:
                return new ListHotMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hot_list_recylerview, parent, false));
            case 4:
                return new ListLightNovelMainViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_light_novel_list_recyclerview, parent, false));
            case 5:
                return new ListComicMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comic_list_recylerview, parent, false));
            case 6:
                return new ListOtherGenresMainViewHodlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_other_genres_list_list_recylerview, parent, false));
            case 2:
                return new AdsBanner(LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_banner_item,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (position) {
            case 0:
                final SliderViewHolder viewholder = (SliderViewHolder) holder;
                viewholder.mSliderSwiperAdapter = new SlideSwipeAdapter(mContext, arrSpecial);
                viewholder.viewPager.setAdapter(viewholder.mSliderSwiperAdapter);
                try {
                    viewholder.mIndicatorView.setViewPager(viewholder.viewPager);
                } catch (PagesLessException e) {
                    e.printStackTrace();
                }
                break;
            case 1:
                LinearLayoutManager linearListNew = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                ListNewMainViewHolder listNewViewHolder = (ListNewMainViewHolder) holder;
                listNewViewHolder.mRecylerViewListNew.setHasFixedSize(true);
                listNewViewHolder.mRecylerViewListNew.setLayoutManager(linearListNew);
                ListNewAdapter adapter = new ListNewAdapter(arrNew, mContext);
                listNewViewHolder.mRecylerViewListNew.setAdapter(adapter);
                listNewViewHolder.mRecylerViewListNew.setItemAnimator(new DefaultItemAnimator());
                break;
            case 3:
                LinearLayoutManager linearListHot = new LinearLayoutManager(mContext);
                ListHotMainViewHolder listHotMainViewHolder = (ListHotMainViewHolder) holder;
                listHotMainViewHolder.mRecylerViewListHot.setHasFixedSize(true);
                listHotMainViewHolder.mRecylerViewListHot.setLayoutManager(linearListHot);
                ListHotAdapter adapter1 = new ListHotAdapter(arrHot, mContext);
                listHotMainViewHolder.mRecylerViewListHot.setAdapter(adapter1);
                listHotMainViewHolder.mRecylerViewListHot.setItemAnimator(new DefaultItemAnimator());
                break;
            case 4:
                LinearLayoutManager linearListLightNovel = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                ListLightNovelMainViewHoler listLightNovelMainViewHoler = (ListLightNovelMainViewHoler) holder;
                listLightNovelMainViewHoler.mRecylerViewListLightNovel.setHasFixedSize(true);
                listLightNovelMainViewHoler.mRecylerViewListLightNovel.setLayoutManager(linearListLightNovel);
                listLightNovelMainViewHoler.mRecylerViewListLightNovel.setAdapter(listLightNovelMainViewHoler.adapter2);
                listLightNovelMainViewHoler.mRecylerViewListLightNovel.setItemAnimator(new DefaultItemAnimator());
                listLightNovelMainViewHoler.imgMoreBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.openAllBookFormCategory("LightNovel");
                    }
                });
                break;
            case 5:
                LinearLayoutManager linearListComic = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                ListComicMainViewHolder listComicMainViewHolder = (ListComicMainViewHolder) holder;
                listComicMainViewHolder.mRecylerViewListComic.setHasFixedSize(true);
                listComicMainViewHolder.mRecylerViewListComic.setLayoutManager(linearListComic);
                ListLightNovelAdapter adapter3 = new ListLightNovelAdapter(arrComic, mContext);
                listComicMainViewHolder.mRecylerViewListComic.setAdapter(adapter3);
                listComicMainViewHolder.mRecylerViewListComic.setItemAnimator(new DefaultItemAnimator());
                listComicMainViewHolder.imgMoreBook1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.openAllBookFormCategory("Comic");
                    }
                });
                break;
            case 6:
                LinearLayoutManager linearListOther = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false);
                ListOtherGenresMainViewHodlder listOtherGenresMainViewHodlder = (ListOtherGenresMainViewHodlder) holder;
                listOtherGenresMainViewHodlder.mRecylerViewListOther.setHasFixedSize(true);
                listOtherGenresMainViewHodlder.mRecylerViewListOther.setLayoutManager(linearListOther);
                ListLightNovelAdapter adapter4 = new ListLightNovelAdapter(arrOther, mContext);
                listOtherGenresMainViewHodlder.mRecylerViewListOther.setAdapter(adapter4);
                listOtherGenresMainViewHodlder.mRecylerViewListOther.setItemAnimator(new DefaultItemAnimator());
                listOtherGenresMainViewHodlder.imgMoreBook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mListener.openAllBookFormCategory("Other");
                    }
                });
                break;
            case 2:
                AdsBanner adsBannerViewHolder =(AdsBanner)holder;
                AdRequest adRequest = new AdRequest.Builder().build();
                adsBannerViewHolder.mAdView.loadAd(adRequest);
                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    private class SliderViewHolder extends RecyclerView.ViewHolder {
        ViewPager viewPager;
        SlideSwipeAdapter mSliderSwiperAdapter;
        IndicatorView mIndicatorView;

        SliderViewHolder(View itemView) {
            super(itemView);
            viewPager = (ViewPager) itemView.findViewById(R.id.viewPagerSlideMain);
            mSliderSwiperAdapter = new SlideSwipeAdapter(mContext, arrSpecial);
            mIndicatorView = (IndicatorView) itemView.findViewById(R.id.indicator);
        }
    }

    private class ListNewMainViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecylerViewListNew;
        TextView mTvListHotTitle;

        ListNewMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListNew = (RecyclerView) itemView.findViewById(R.id.recyclerViewListNew);
            mTvListHotTitle = (TextView) itemView.findViewById(R.id.tvListNewTitle);
        }
    }

    private class ListHotMainViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecylerViewListHot;
        ListHotMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListHot = (RecyclerView) itemView.findViewById(R.id.recyclerViewListHot);
        }
    }

    private class ListLightNovelMainViewHoler extends RecyclerView.ViewHolder {
        RecyclerView mRecylerViewListLightNovel;
        ListLightNovelAdapter adapter2;
        ImageView imgMoreBook;
        ListLightNovelMainViewHoler(View itemView) {
            super(itemView);
            mRecylerViewListLightNovel = (RecyclerView) itemView.findViewById(R.id.recyclerViewListLightNovel);
            adapter2 = new ListLightNovelAdapter(arrLightNovel, mContext);
            imgMoreBook = (ImageView)itemView.findViewById(R.id.imgMoreBook);
        }
    }

    private class ListComicMainViewHolder extends RecyclerView.ViewHolder {
        RecyclerView mRecylerViewListComic;
        ImageView imgMoreBook1;
        ListComicMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListComic = (RecyclerView) itemView.findViewById(R.id.recyclerViewListComic);
            imgMoreBook1 = (ImageView)itemView.findViewById(R.id.imgMoreBookComic);
        }
    }

    private class ListOtherGenresMainViewHodlder extends RecyclerView.ViewHolder {
        RecyclerView mRecylerViewListOther;
        ImageView imgMoreBook;
        ListOtherGenresMainViewHodlder(View itemView) {
            super(itemView);
            mRecylerViewListOther = (RecyclerView) itemView.findViewById(R.id.recyclerViewListOther);
            imgMoreBook = (ImageView)itemView.findViewById(R.id.imgMoreBookOther);
        }
    }
    private class AdsBanner extends RecyclerView.ViewHolder{
        AdView mAdView;
         AdsBanner(View itemView) {
            super(itemView);
            mAdView = (AdView)itemView.findViewById(R.id.adView);

         }

    }
    public interface onCallBackFormMainAdapter{
        void openAllBookFormCategory(String category);
    }
}
