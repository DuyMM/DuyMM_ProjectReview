package com.example.maimanhduy.rbook.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.adapter.ListInfoMoreBookAdapter;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class InfoBookActivity extends AppCompatActivity {
    private RecyclerView mRecylerViewMore;
    private DatabaseReference databaseReference;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference stogareReference;
    private TextView mTvInfoTitle;
    private ImageView mImgBook;
    private TextView mTvInfoDescription;
    private Button mBtnReadBook;
    private ImageView mImgBack;
    private String mLinkBook;
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
        Intent intent = getIntent();
        String category = intent.getStringExtra("category");
        String position = intent.getStringExtra("id");
        loadBook(category,position);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mBtnReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mLinkBook.equals("")){
                    Toast.makeText(InfoBookActivity.this, mLinkBook+"", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void loadBook(String child,String position){
        databaseReference.child(child).child(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTvInfoTitle.setText(dataSnapshot.child("Title").getValue().toString());
                mTvInfoDescription.setText(dataSnapshot.child("Author").getValue().toString());
                stogareReference = storage.getReference(dataSnapshot.child("LinkImage").getValue().toString());
                Glide.with(InfoBookActivity.this).using(new FirebaseImageLoader()).load(stogareReference).centerCrop().crossFade().into(mImgBook);
                mLinkBook = dataSnapshot.child("LinkBook").getValue().toString();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
    public void init(){
        mRecylerViewMore = (RecyclerView)findViewById(R.id.recyclerViewListInfoMore);
        ScrollView scrollView = (ScrollView)findViewById(R.id.scrollViewInforBook);
        scrollView.fullScroll(View.FOCUS_DOWN);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        mTvInfoTitle = (TextView)findViewById(R.id.tvInfoTitle);
        mTvInfoDescription = (TextView)findViewById(R.id.tvInfoDescription);
        mImgBook = (ImageView)findViewById(R.id.imgInfoBook);
        mImgBack = (ImageView)findViewById(R.id.imgBackInfo);
        mBtnReadBook = (Button)findViewById(R.id.btnReadBook);
    }
}
