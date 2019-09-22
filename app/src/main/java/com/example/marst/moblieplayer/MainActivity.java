package com.example.marst.moblieplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marst.moblieplayer.base.BasePager;
import com.example.marst.moblieplayer.pager.AudioPager;
import com.example.marst.moblieplayer.pager.NetAudioPager;
import com.example.marst.moblieplayer.pager.NetVideoPager;
import com.example.marst.moblieplayer.pager.VideoPager;

import java.util.ArrayList;

/**
 * Created by Marst on 2019/7/17.
 * 主页面
 */

public class MainActivity extends AppCompatActivity{
    //在AndroidManfest注册
    private RadioGroup rg_main;
    private ArrayList<BasePager> basePagers;
    private int position;//页面位置
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rg_main = findViewById(R.id.rg_main);
        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));
        basePagers.add(new AudioPager(this));
        basePagers.add(new NetVideoPager(this));
        basePagers.add(new NetAudioPager(this));

        rg_main.setOnCheckedChangeListener(new MyOnCheckedChangeListener());
        rg_main.check(R.id.rb_video);
        textView = findViewById(R.id.tv_search);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"dianji",Toast.LENGTH_SHORT).show();
//            }
//        });

    }
    class MyOnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch(i){
                default:
                    position = 0;
                    break;
                case R.id.rb_caidan:
                    position = 1;
                    break;
                case R.id.rb_shequ:
                    position = 2;
                    break;
                case R.id.rb_geren:
                    position = 3;
                    break;
            }
            setFragment();
        }
    }

    private void setFragment() {
        //得到fragmentManger
        FragmentManager fm = getSupportFragmentManager();
        //开启事务
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(R.id.fl_main,new MyFragment().newInstance(getBasePager()));
        //提交
        ft.commit();
}

    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);
        if (basePager != null && !basePager.isInitData){
            basePager.isInitData = true;
            basePager.initData();
        }
        return basePager;
    }
}
