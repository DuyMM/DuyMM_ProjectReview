package com.example.maimanhduy.rbook.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.maimanhduy.rbook.R;
import com.example.maimanhduy.rbook.service.ListenerNewBook;

public class SettingActivity extends AppCompatActivity {
    private ImageView mImgBack;
    private Switch mSwitchNotification;
    SharedPreferences pre;
    private RelativeLayout rlChangeLanguage;
    private String[] language = {"English", "Vietnamese"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mImgBack = (ImageView) findViewById(R.id.imgBackFormSetting);
        mSwitchNotification = (Switch) findViewById(R.id.switchNotificationSetting);
        rlChangeLanguage = (RelativeLayout) findViewById(R.id.relativeLanguage);
        pre = getSharedPreferences(getString(R.string.status_service), MODE_PRIVATE);
        checkStatusNotify();
        mImgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mSwitchNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    startService(new Intent(SettingActivity.this, ListenerNewBook.class));
                } else {
                    Toast.makeText(SettingActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    stopService(new Intent(SettingActivity.this, ListenerNewBook.class));
                }
            }
        });
        rlChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.list_language);
                ListView lv = (ListView) dialog.findViewById(R.id.listViewLanguage);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(SettingActivity.this, android.R.layout.simple_list_item_1, language);
                lv.setAdapter(adapter);
                dialog.setCancelable(true);
                dialog.setTitle(getString(R.string.choose_your_language));
                dialog.show();
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String lg;
                        SharedPreferences.Editor edit = pre.edit();
                        switch (i) {
                            case 0:
                                lg = "en";
                                edit.putString("laguage",lg);
                                edit.apply();
                                dialog.cancel();
                                restartApp();
                                break;
                            case 1:
                                lg = "vi";
                                edit.putString("laguage",lg);
                                edit.apply();
                                dialog.cancel();
                                restartApp();
                                break;
                            default:
                                lg = "en";
                                edit.putString("laguage",lg);
                                edit.apply();
                                dialog.cancel();
                                restartApp();
                                break;
                        }
                    }
                });
            }
        });
    }

    public void checkStatusNotify() {
        boolean isCheck = pre.getBoolean("status", false);
        mSwitchNotification.setChecked(isCheck);
    }
    public void restartApp(){
      Intent intent = new Intent(SettingActivity.this,SplashActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        //  startActivity(new Intent(SettingActivity.this,SplashActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
        //finish();
    }
}
