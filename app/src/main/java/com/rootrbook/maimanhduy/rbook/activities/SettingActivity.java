package com.rootrbook.maimanhduy.rbook.activities;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.rootrbook.maimanhduy.rbook.R;
import com.rootrbook.maimanhduy.rbook.service.ListenerNewBook;

public class SettingActivity extends AppCompatActivity {
     ImageView mImgBack;
    private Switch mSwitchNotification;
    SharedPreferences pre;
     RelativeLayout rlChangeLanguage;
     private String[] language = {"English", "Vietnamese"};
     TextView tvSubLanguage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(ContextCompat.getColor(this,R.color.colorMain));
        }
        tvSubLanguage = (TextView)findViewById(R.id.tvSubLaguageSettingActivity);
        mImgBack = (ImageView) findViewById(R.id.imgBackFormSetting);
        mSwitchNotification = (Switch) findViewById(R.id.switchNotificationSetting);
        rlChangeLanguage = (RelativeLayout) findViewById(R.id.relativeLanguage);
        pre = getSharedPreferences(getString(R.string.status_service), MODE_PRIVATE);
        if ("vi".equals(pre.getString("laguage","en"))){
            tvSubLanguage.setText(getString(R.string.vietnamese));
        }else {
            tvSubLanguage.setText(getString(R.string.english));
        }
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
                SharedPreferences.Editor editor = pre.edit();
                if (b) {
                    startService(new Intent(SettingActivity.this, ListenerNewBook.class));
                    editor.putBoolean("status",true);
                } else {
                    Toast.makeText(SettingActivity.this, "Stop", Toast.LENGTH_SHORT).show();
                    stopService(new Intent(SettingActivity.this, ListenerNewBook.class));
                    editor.putBoolean("status",false);
                }
                editor.apply();
            }
        });
        rlChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(SettingActivity.this);
                dialog.setContentView(R.layout.list_language);
                ListView lv = (ListView) dialog.findViewById(R.id.listViewLanguage);
                ArrayAdapter<String> adapter = new ArrayAdapter<>(SettingActivity.this, android.R.layout.simple_list_item_1, language);
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
                                dialog.dismiss();
                                restartApp();
                                finish();
                                break;
                            case 1:
                                lg = "vi";
                                edit.putString("laguage",lg);
                                edit.apply();
                                dialog.dismiss();
                                restartApp();
                                finish();
                                break;
                            default:
                                lg = "en";
                                edit.putString("laguage",lg);
                                edit.apply();
                                dialog.dismiss();
                                restartApp();
                                finish();
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
    }
}
