package com.example.maimanhduy.rbook.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.maimanhduy.rbook.database.DatabaseHanderSDCard;
import com.example.maimanhduy.rbook.model.BookOnSdCard;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class InfoBookActivity extends AppCompatActivity {
    private RecyclerView mRecylerViewMore;
    private DatabaseReference databaseReference;
    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageRef = storage.getReferenceFromUrl("gs://rbook-d86d7.appspot.com");
    private StorageReference stogareReference;
    private TextView mTvInfoTitle;
    private ImageView mImgBook;
    private TextView mTvInfoDescription;
    private Button mBtnReadBook;
    private Button mBtnDownLoad;
    private ImageView mImgBack;
    private String mLinkBook;
    private String mLinkImage;
    private String mLinkTitle;
    private String mId;
    private String mCategory;
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
                    //Toast.makeText(InfoBookActivity.this, mLinkBook+"", Toast.LENGTH_SHORT).show();
                    Intent openBook = new Intent(InfoBookActivity.this,ReadBookActivity.class);
                    openBook.putExtra("linkBook",mLinkBook);
                    openBook.putExtra("linkImage",mLinkImage);
                    openBook.putExtra("id", mId);
                    openBook.putExtra("title",mLinkTitle);
                    openBook.putExtra("category",mCategory);
                    startActivity(openBook);
                }
            }
        });
        mBtnDownLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                downloadBook();
            }
        });
    }
    public void loadBook(String child,String position){
        mCategory = child;
        databaseReference.child(child).child(position).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mTvInfoTitle.setText(dataSnapshot.child(getString(R.string.title)).getValue().toString());
                mTvInfoDescription.setText(dataSnapshot.child(getString(R.string.author)).getValue().toString());
                stogareReference = storage.getReference(dataSnapshot.child(getString(R.string.linkimage)).getValue().toString());
                Glide.with(InfoBookActivity.this).using(new FirebaseImageLoader()).load(stogareReference).centerCrop().crossFade().into(mImgBook);
                mLinkBook = dataSnapshot.child(getString(R.string.linkbook)).getValue().toString();
                mLinkImage = dataSnapshot.child(getString(R.string.linkimage)).getValue().toString();
                mLinkTitle = dataSnapshot.child(getString(R.string.title)).getValue().toString();
                mId = dataSnapshot.getKey();
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
        mBtnDownLoad = (Button)findViewById(R.id.btnDownLoad);
    }
    public void downloadBook(){
        final String[] linkBooks = new String[1];
        final String[] linkImages = new String[1];
        try {
            final File localFile = File.createTempFile("book", ".txt", getCacheDir());
            final StorageReference islandRef = storageRef.child(mLinkBook);
            islandRef.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
/*                    Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                    mImgBook.setImageBitmap(bitmap);*/
                    linkBooks[0] = localFile.getAbsolutePath().toString();
                    try {
                        final File localFile2 = File.createTempFile("images",".jpg", getCacheDir());
                        StorageReference imagesRef = storageRef.child(mLinkImage);
                        imagesRef.getFile(localFile2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                linkImages[0] = localFile2.getAbsolutePath().toString();
                                DatabaseHanderSDCard db = new DatabaseHanderSDCard(InfoBookActivity.this);
                                db.addNewBookOnSdCard(new BookOnSdCard(mId,mLinkTitle,linkBooks[0],linkImages[0],mTvInfoDescription.getText().toString()));
                                Toast.makeText(InfoBookActivity.this, "Success", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(InfoBookActivity.this, "DownLoad That Bai", Toast.LENGTH_SHORT).show();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
