package com.rootrbook.maimanhduy.rbook.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.adapter.ListAllBookAdapter;
import com.rootrbook.maimanhduy.rbook.model.BookInFireBase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class AllBookActivity extends AppCompatActivity implements ListAllBookAdapter.OnCallBackFormListAllBookAdapter {
    private RecyclerView mRecyclerview;
    private ArrayList<BookInFireBase> arrAllBook = new ArrayList<>();
    private DatabaseReference database;
    ListAllBookAdapter allBookAdapter;
    private TextView tvTitle;
    private ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_book);
        mRecyclerview = (RecyclerView)findViewById(R.id.recycerViewAllBook);
        tvTitle = (TextView)findViewById(R.id.tvTitleAllBookActivity);
        imgBack = (ImageView)findViewById(R.id.imgBackFormAllBook);
        LinearLayoutManager lln = new LinearLayoutManager(this);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(lln);
         allBookAdapter = new ListAllBookAdapter(arrAllBook,this);
        mRecyclerview.setAdapter(allBookAdapter);
        loadData();
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  startActivity(new Intent(AllBookActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK) );
finish();
            }
        });
    }
    private void loadData(){
        database = FirebaseDatabase.getInstance().getReference();
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");

        if (getString(R.string.lightnovel).equals(category)){
            tvTitle.setText("Short Stories");
        }
        if (getString(R.string.comic).equals(category)){
            tvTitle.setText("Kids");
        }
        if (getString(R.string.other).equals(category)){
            tvTitle.setText("Other");
        }
        database.child("vi").child(category).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                arrAllBook.clear();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    String title = (String) snap.child(getString(R.string.title)).getValue();
                    String author = (String) snap.child(getString(R.string.author)).getValue();
                    String linkImage = (String) snap.child(getString(R.string.linkimage)).getValue();
                    String linkBook = (String) snap.child(getString(R.string.linkbook)).getValue();
                    String id = snap.getKey();
                    String views = snap.child(getString(R.string.views)).getValue().toString();
                    arrAllBook.add(new BookInFireBase(title, author, linkImage, linkBook, id, views,dataSnapshot.getKey()));
                }
                Collections.reverse(arrAllBook);
                allBookAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void openBookFormAllBook(String category, String pos) {
        Intent intent = new Intent(AllBookActivity.this, InfoBookActivity.class);
        intent.putExtra("id",pos);
        intent.putExtra("category",category);
        startActivity(intent);
    }
}
