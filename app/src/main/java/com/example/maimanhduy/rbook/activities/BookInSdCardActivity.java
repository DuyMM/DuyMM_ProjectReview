package com.example.maimanhduy.rbook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.ListBookOnCardAdapter;

public class BookInSdCardActivity extends AppCompatActivity {
private RecyclerView mRecyclerViewBookInCard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_in_sd_card);
        mRecyclerViewBookInCard = (RecyclerView)findViewById(R.id.recycerViewSDCard);
        ListBookOnCardAdapter adapter = new ListBookOnCardAdapter();
        LinearLayoutManager lln = new LinearLayoutManager(this);
        mRecyclerViewBookInCard.setHasFixedSize(true);
        mRecyclerViewBookInCard.setLayoutManager(lln);
        mRecyclerViewBookInCard.setAdapter(adapter);
    }
}
