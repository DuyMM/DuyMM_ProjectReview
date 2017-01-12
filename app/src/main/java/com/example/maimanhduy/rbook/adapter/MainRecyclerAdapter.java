package com.example.maimanhduy.rbook.adapter;

import android.content.Context;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.hado.indicatorlibrary.IndicatorView;
import com.hado.indicatorlibrary.PagesLessException;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

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
    public MainRecyclerAdapter(Context mContext,ArrayList<BookInFireBase> arrLightNovel, ArrayList<BookInFireBase> arrComic, ArrayList<BookInFireBase> arrOther, ArrayList<BookInFireBase> arrNew, ArrayList<BookInFireBase> arrHot){
        this.mContext = mContext;
        this.arrLightNovel = arrLightNovel;
        this.arrComic = arrComic;
        this.arrOther = arrOther;
        this.arrNew = arrNew;
        this.arrHot = arrHot;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case 0: return new SliderViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_slide_show, parent,false));
            case 1: return new ListNewMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_new_list_recylerview,parent,false));
            case 2: return new ListHotMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_hot_list_recylerview,parent,false));
            case 3: return new ListLightNovelMainViewHoler(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_light_novel_list_recyclerview,parent,false));
            case 4: return new ListComicMainViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_comic_list_recylerview,parent,false));
            case 5: return new ListOtherGenresMainViewHodlder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_other_genres_list_list_recylerview,parent,false));
        }
      return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
       switch (position){
           case 0:     final SliderViewHolder viewholder = (SliderViewHolder) holder;
               viewholder.viewPager.setAdapter(viewholder.mSliderSwiperAdapter);
               viewholder.mSliderSwiperAdapter = new SlideSwipeAdapter(mContext);
               try {
                   viewholder.mIndicatorView.setViewPager(viewholder.viewPager);
               } catch (PagesLessException e) {
                   e.printStackTrace();
               }
              final Handler handler = new Handler();

               final Runnable update = new Runnable() {
                   public void run() {
                       if (viewholder.viewPager.getCurrentItem() == 4) {
                           viewholder.viewPager.setCurrentItem(0);
                       }else {
                           int nextpager = viewholder.viewPager.getCurrentItem()+1;
                           viewholder.viewPager.setCurrentItem(nextpager, true);
                       }
                   }
               };
               new Timer().schedule(new TimerTask() {

                   @Override
                   public void run() {
                       handler.post(update);
                   }
               }, 10000, 10000); break;
           case 1:
               LinearLayoutManager linearListNew = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
               ListNewMainViewHolder listNewViewHolder = (ListNewMainViewHolder) holder;
               listNewViewHolder.mRecylerViewListNew.setHasFixedSize(true);
               listNewViewHolder.mRecylerViewListNew.setLayoutManager(linearListNew);
               ListNewAdapter adapter = new ListNewAdapter(arrNew,mContext);
               listNewViewHolder.mRecylerViewListNew.setAdapter(adapter);
               listNewViewHolder.mRecylerViewListNew.setItemAnimator(new DefaultItemAnimator());
              // listHotViewHolder.mRecylerViewListHot.scrollToPosition(0);
               break;
           case 2:
               LinearLayoutManager linearListHot  = new LinearLayoutManager(mContext);
               ListHotMainViewHolder listHotMainViewHolder = (ListHotMainViewHolder)holder;
               listHotMainViewHolder.mRecylerViewListHot.setHasFixedSize(true);
               listHotMainViewHolder.mRecylerViewListHot.setLayoutManager(linearListHot);
               ListHotAdapter adapter1 = new ListHotAdapter(arrHot,mContext);
               listHotMainViewHolder.mRecylerViewListHot.setAdapter(adapter1);
               listHotMainViewHolder.mRecylerViewListHot.setItemAnimator(new DefaultItemAnimator());
               break;
           case 3:
               LinearLayoutManager linearListLightNovel = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
               ListLightNovelMainViewHoler listLightNovelMainViewHoler = (ListLightNovelMainViewHoler) holder;
               listLightNovelMainViewHoler.mRecylerViewListLightNovel.setHasFixedSize(true);
               listLightNovelMainViewHoler.mRecylerViewListLightNovel.setLayoutManager(linearListLightNovel);
               listLightNovelMainViewHoler.mRecylerViewListLightNovel.setAdapter(listLightNovelMainViewHoler.adapter2);
               listLightNovelMainViewHoler.mRecylerViewListLightNovel.setItemAnimator(new DefaultItemAnimator());
               break;
           case 4:
               LinearLayoutManager linearListComic = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
               ListComicMainViewHolder listComicMainViewHolder = (ListComicMainViewHolder)holder;
               listComicMainViewHolder.mRecylerViewListComic.setHasFixedSize(true);
               listComicMainViewHolder.mRecylerViewListComic.setLayoutManager(linearListComic);
               ListLightNovelAdapter adapter3 = new ListLightNovelAdapter(arrComic,mContext);
               listComicMainViewHolder.mRecylerViewListComic.setAdapter(adapter3);
               listComicMainViewHolder.mRecylerViewListComic.setItemAnimator(new DefaultItemAnimator());
               break;
           case 5:
               LinearLayoutManager linearListOther = new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL,false);
               ListOtherGenresMainViewHodlder listOtherGenresMainViewHodlder = (ListOtherGenresMainViewHodlder)holder;
               listOtherGenresMainViewHodlder.mRecylerViewListOther.setHasFixedSize(true);
               listOtherGenresMainViewHodlder.mRecylerViewListOther.setLayoutManager(linearListOther);
               ListLightNovelAdapter adapter4 = new ListLightNovelAdapter(arrOther,mContext);
               listOtherGenresMainViewHodlder.mRecylerViewListOther.setAdapter(adapter4);
               listOtherGenresMainViewHodlder.mRecylerViewListOther.setItemAnimator(new DefaultItemAnimator());
               break;
       }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private class SliderViewHolder extends RecyclerView.ViewHolder{
         ViewPager viewPager;
         SlideSwipeAdapter mSliderSwiperAdapter;
         IndicatorView mIndicatorView;
         SliderViewHolder(View itemView) {
            super(itemView);
            viewPager= (ViewPager)itemView.findViewById(R.id.viewPagerSlideMain);
            mSliderSwiperAdapter = new SlideSwipeAdapter(mContext);
            mIndicatorView = (IndicatorView)itemView.findViewById(R.id.indicator);
        }
    }
    private class ListNewMainViewHolder extends RecyclerView.ViewHolder{
         RecyclerView mRecylerViewListNew;
         TextView mTvListHotTitle;
         ListNewMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListNew = (RecyclerView)itemView.findViewById(R.id.recyclerViewListNew);
            mTvListHotTitle = (TextView)itemView.findViewById(R.id.tvListNewTitle);
        }
    }
    private class ListHotMainViewHolder extends  RecyclerView.ViewHolder{
        RecyclerView mRecylerViewListHot;
         ListHotMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListHot = (RecyclerView)itemView.findViewById(R.id.recyclerViewListHot);
        }
    }
    private class ListLightNovelMainViewHoler extends RecyclerView.ViewHolder{
        RecyclerView mRecylerViewListLightNovel;
        ListLightNovelAdapter adapter2;
         ListLightNovelMainViewHoler(View itemView) {
            super(itemView);
            mRecylerViewListLightNovel = (RecyclerView)itemView.findViewById(R.id.recyclerViewListLightNovel);
             adapter2 =new ListLightNovelAdapter(arrLightNovel,mContext);
        }
    }
    private class ListComicMainViewHolder extends RecyclerView.ViewHolder{
         RecyclerView mRecylerViewListComic;
         ListComicMainViewHolder(View itemView) {
            super(itemView);
            mRecylerViewListComic = (RecyclerView)itemView.findViewById(R.id.recyclerViewListComic);
        }
    }
    private class ListOtherGenresMainViewHodlder extends RecyclerView.ViewHolder{
        RecyclerView mRecylerViewListOther;
        ListOtherGenresMainViewHodlder(View itemView) {
            super(itemView);
            mRecylerViewListOther = (RecyclerView)itemView.findViewById(R.id.recyclerViewListOther);
        }
    }
}
