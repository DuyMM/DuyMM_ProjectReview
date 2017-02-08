package com.rootrbook.maimanhduy.rbook.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.adapter.ListBookOnCardAdapter;
import com.rootrbook.maimanhduy.rbook.database.DatabaseHanderSDCard;
import com.rootrbook.maimanhduy.rbook.model.BookOnSdCard;

import java.util.ArrayList;

public class BookInSdCardActivity extends AppCompatActivity implements ListBookOnCardAdapter.onCallBackFormListBookSDCardAdapter {
    RecyclerView mRecyclerViewBookInCard;
    ImageView mImgBack;
    private ArrayList<BookOnSdCard> arrBookOnSDcard = new ArrayList<>();
    private ListBookOnCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_in_sd_card);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        DatabaseHanderSDCard db = new DatabaseHanderSDCard(this);
        ArrayList<BookOnSdCard> list = db.getAllBookOnSdCard();
        arrBookOnSDcard.addAll(list);
        mRecyclerViewBookInCard = (RecyclerView) findViewById(R.id.recycerViewSDCard);
        mImgBack = (ImageView) findViewById(R.id.imgBackFormSDcard);
        adapter = new ListBookOnCardAdapter(arrBookOnSDcard, this);
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

    @Override
    public void openBookSdCard(String linkBook) {
        Intent intent = new Intent(BookInSdCardActivity.this, ReadBookActivity.class);
        intent.putExtra("linkBook", linkBook);
        intent.putExtra("status", "sd");
        startActivity(intent);
    }

    @Override
    public void updateRecylerView(final int position) {
        final DatabaseHanderSDCard db = new DatabaseHanderSDCard(this);
        AlertDialog.Builder showDialogDelete = new AlertDialog.Builder(this);
        showDialogDelete.setTitle(getString(R.string.dialog_delete_title));
        showDialogDelete.setMessage(arrBookOnSDcard.get(position).getTitle());
        showDialogDelete.setNegativeButton(getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                db.deleteBookOnSdCard(arrBookOnSDcard.get(position).getId());
                arrBookOnSDcard.remove(position);
              if (position!=0){
                  adapter.notifyDataSetChanged();
              }else {
                  mRecyclerViewBookInCard.setLayoutManager(new LinearLayoutManager(BookInSdCardActivity.this));
                  mRecyclerViewBookInCard.setAdapter(adapter);
              }
            }
        });
        showDialogDelete.setPositiveButton(getString(R.string.no), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        showDialogDelete.create().show();
    }
}
