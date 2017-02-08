package com.rootrbook.maimanhduy.rbook.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.rootrbook.maimanhduy.rbook.R;

/**
 * Created by MaiManhDuy on 12/24/2016.
 */

public class ItemNew extends RelativeLayout {
    public ItemNew(Context context) {
        super(context);
    }

    public ItemNew(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.itemNew, 0, 0);
        String titleText = a.getString(R.styleable.itemNew_titleText);
        String textSize = a.getString(R.styleable.itemNew_textSize);

        String color = a.getString(R.styleable.itemNew_valueColor);
        a.recycle();
        setGravity(Gravity.CENTER_VERTICAL);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_new, this, true);
        RelativeLayout relativeLayout = (RelativeLayout)getChildAt(0);
        TextView itemTitle = (TextView)((RelativeLayout) getChildAt(0)).getChildAt(0);
        GradientDrawable sd = (GradientDrawable) relativeLayout.getBackground();
        sd.setColor(Color.parseColor(color));
        sd.invalidateSelf();
        relativeLayout.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.item_new));
        itemTitle.setText(titleText);
        itemTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX,Float.parseFloat(textSize));
    }

    public ItemNew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
