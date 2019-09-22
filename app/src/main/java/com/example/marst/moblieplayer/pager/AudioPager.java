package com.example.marst.moblieplayer.pager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.marst.moblieplayer.base.BasePager;

/**
 * Created by Marst on 2019/7/18.
 * 作用：本地音乐
 */

public class AudioPager extends BasePager {
    private TextView textView;

    public AudioPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);
        textView.setText("本地音乐");
        textView.setTextColor(Color.RED);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(30);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("本地音乐");
    }
}
