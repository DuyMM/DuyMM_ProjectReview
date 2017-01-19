package com.example.maimanhduy.rbook.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;

import com.example.maimanhduy.rbook.MainActivity;
import com.example.maimanhduy.rbook.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        CreateDb async = new CreateDb();
        async.execute();

    }
    public void onMainActivity(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
    public class CreateDb extends AsyncTask<Void,Integer,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            SystemClock.sleep(3000);
            onMainActivity();
            finish();
            return null;
        }
    }
}
