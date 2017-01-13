package com.example.maimanhduy.rbook.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.maimanhduy.rbook.R;

public class SettingActivity extends AppCompatActivity {
    private ImageView mImgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mImgBack = (ImageView)findViewById(R.id.imgBackFormSetting);
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
