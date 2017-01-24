package com.example.maimanhduy.rbook.activities;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.database.DatabaseHanderHelper;
import com.example.maimanhduy.rbook.fragments.SettingFragment;
import com.example.maimanhduy.rbook.model.BookInFireBase;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

public class ReadBookActivity extends AppCompatActivity implements SettingFragment.OnCallBackFormSettingFragment {
    private ProgressBar mProgressBar;
    private TextView mTvReadBook;
    private ScrollView mScrollView;
    private int max;
    private Fragment fragment;
    private DatabaseHanderHelper db;
    private float textSize = 15;
    private float lineSpacing = 0;
    private ImageView mImgShowSettingFragment;
    private ImageView mImgBackFormReadBook;
    private String[] mFont = {"fonts/Roboto-Medium.ttf",
            "fonts/ArimaMadurai-Medium.ttf",
            "fonts/BalooChettan-Regular.ttf",
            "fonts/Lemonada-Light.ttf",
            "fonts/OpenSans-CondLight.ttf"};
    private Typeface mTypeFace;
    private FragmentTransaction ft;
    private View mThisFragment;
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef;
    private String linkBook;
    private String title;
    private String linkImage;
    private String id;
    private String category;
    private String mStatus;
    private InterstitialAd mInterstitialAd;
    private TextView mTvPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_book);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.colorMain));
        }
        mTvPercent = (TextView) findViewById(R.id.tvTitleBookOfReadBook);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        AdRequest adRequest = new AdRequest.Builder()
                .build();
        mInterstitialAd.loadAd(adRequest);
        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });
        mProgressBar = (ProgressBar) findViewById(R.id.progressBarReadBook);
        mTvReadBook = (TextView) findViewById(R.id.tvReadBook);
        db = new DatabaseHanderHelper(this);
        mScrollView = (ScrollView) findViewById(R.id.scrollViewReadBook);
        ft = getSupportFragmentManager().beginTransaction();
        fragment = getSupportFragmentManager().findFragmentById(R.id.fragmentSetting);
        mThisFragment = findViewById(R.id.fragmentSetting);
        mThisFragment.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        linkBook = intent.getStringExtra("linkBook");
        linkImage = intent.getStringExtra("linkImage");
        id = intent.getStringExtra("id");
        title = intent.getStringExtra("title");
        category = intent.getStringExtra("category");
        mStatus = intent.getStringExtra("status");
        storageRef = storage.getReference(linkBook);
        //mProgressBar.getIndeterminateDrawable().setColorFilter(getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_IN);
        // mProgressBar.setProgressBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.colorMain)));
        // mProgressBar.getThumb().setColorFilter(getResources().getColor(R.color.colorMain), PorterDuff.Mode.SRC_IN);
        if ("sd".equals(mStatus)) {
            readBookOnSDcard(linkBook);
        } else {
            readBookOnFirebase();
        }
        mTvPercent.setText(0 + "%");
        mScrollView.setVerticalScrollBarEnabled(false);
        mScrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (max == 0) {
                    max = mScrollView.getChildAt(0).getHeight() - mScrollView.getHeight();
                    mProgressBar.setMax(max);

                }
                // if(System.currentTimeMillis()-mTimeCurrent>200){
                mProgressBar.setProgress(mScrollView.getScrollY());
                //}
                long precent = (mScrollView.getScrollY() * 100) / max;
                mTvPercent.setText(precent + "%");
            }
        });
        mImgShowSettingFragment = (ImageView) findViewById(R.id.imgShowSettingFragment);
        mImgShowSettingFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mThisFragment.setVisibility(View.VISIBLE);
                //fragment.setVisibility(View.VISIBLE);
            }
        });
        mImgBackFormReadBook = (ImageView) findViewById(R.id.imgBackFormReadBook);
        mImgBackFormReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void onCloseFragmentSetting() {
        max = mScrollView.getChildAt(0).getHeight() - mScrollView.getHeight();
        mProgressBar.setMax(max);
        mThisFragment.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onFontSizePlus() {
        textSize = textSize + 1;
        mTvReadBook.setTextSize(textSize);
        ((SettingFragment) fragment).setTextSizeS(String.valueOf(textSize));
    }

    @Override
    public void onFontSizeMinus() {
        textSize = textSize - 1;
        mTvReadBook.setTextSize(textSize);
        ((SettingFragment) fragment).setTextSizeS(String.valueOf(textSize));
    }

    @Override
    public void onMarginPlus() {
        lineSpacing = lineSpacing + 1;
        mTvReadBook.setLineSpacing(lineSpacing, 1);
        ((SettingFragment) fragment).setTextMarginS(String.valueOf(lineSpacing));
    }

    @Override
    public void onMarginMinus() {
        lineSpacing = lineSpacing - 1;
        mTvReadBook.setLineSpacing(lineSpacing, 1);
        ((SettingFragment) fragment).setTextMarginS(String.valueOf(lineSpacing));
    }

    @Override
    public void onNightMode() {
        mScrollView.setBackgroundColor(getResources().getColor(R.color.colorGrey));
        mTvReadBook.setTextColor(getResources().getColor(R.color.colorDarkWhite));
    }

    @Override
    public void onNormalMode() {
        mScrollView.setBackgroundColor(getResources().getColor(R.color.colorDarkWhite));
        mTvReadBook.setTextColor(getResources().getColor(R.color.colorGrey));
    }

    @Override
    public void onChangeFont(int i) {
        mTypeFace = Typeface.createFromAsset(getAssets(), mFont[i]);
        mTvReadBook.setTypeface(mTypeFace);
    }

    @Override
    public void addFavoriteBook() {
        if (!db.checkFavoriteBookHaveInPhone(id)) {
            db.addNewFavoriteBook(new BookInFireBase(title + "", "", linkImage + "", linkBook + "", id + "", "0", category + ""));
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }


    }

    public void readBookOnSDcard(String position) {
        File yourfile = new File(position);
        StringBuilder text = new StringBuilder();
        try {
            BufferedReader br = new BufferedReader(new FileReader(yourfile));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mTvReadBook.setText(text);
    }

    public void readBookOnFirebase() {
        final long ONE_MEGABYTE = 1024 * 1024;
        storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                // Data for "images/island.jpg" is returns, use this as needed
                String str = new String(bytes, Charset.forName("utf-8"));
                mTvReadBook.setText(str);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle any errors
            }
        });
    }

    private void showInterstitial() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
    }
}
