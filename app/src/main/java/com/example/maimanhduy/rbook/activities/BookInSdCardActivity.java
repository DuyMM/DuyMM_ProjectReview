package com.example.maimanhduy.rbook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.ListBookOnCardAdapter;

public class BookInSdCardActivity extends AppCompatActivity {
private RecyclerView mRecyclerViewBookInCard;
    private ImageView mImgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_in_sd_card);
        mRecyclerViewBookInCard = (RecyclerView)findViewById(R.id.recycerViewSDCard);
        mImgBack = (ImageView)findViewById(R.id.imgBackFormSDcard);
        ListBookOnCardAdapter adapter = new ListBookOnCardAdapter();
        LinearLayoutManager lln = new LinearLayoutManager(this);
        mRecyclerViewBookInCard.setHasFixedSize(true);
        mRecyclerViewBookInCard.setLayoutManager(lln);
        mRecyclerViewBookInCard.setAdapter(adapter);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
