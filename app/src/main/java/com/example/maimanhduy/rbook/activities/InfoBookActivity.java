package com.example.maimanhduy.rbook.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ScrollView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.ListInfoMoreBookAdapter;

public class InfoBookActivity extends AppCompatActivity {
    private RecyclerView mRecylerViewMore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_book);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        init();
        LinearLayoutManager lln = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false);
        mRecylerViewMore.setHasFixedSize(true);
        mRecylerViewMore.setLayoutManager(lln);
        ListInfoMoreBookAdapter adapter = new ListInfoMoreBookAdapter();
        mRecylerViewMore.setAdapter(adapter);
    }
    public void init(){
        mRecylerViewMore = (RecyclerView)findViewById(R.id.recyclerViewListInfoMore);
        ScrollView scrollView = (ScrollView)findViewById(R.id.scrollViewInforBook);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}
