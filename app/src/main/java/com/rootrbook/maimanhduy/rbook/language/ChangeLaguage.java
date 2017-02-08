package com.rootrbook.maimanhduy.rbook.language;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import com.rootrbook.maimanhduy.rbook.R;

import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by MaiManhDuy on 1/19/2017.
 */

public class ChangeLaguage {
    SharedPreferences pre;
    public void localLanguage(Context context){
        pre = context.getSharedPreferences(context.getString(R.string.status_service), MODE_PRIVATE);
        String lg = pre.getString("laguage","en");
        Locale locale = new Locale(lg);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getResources().updateConfiguration(config, context.getResources().getDisplayMetrics());
    }
}
