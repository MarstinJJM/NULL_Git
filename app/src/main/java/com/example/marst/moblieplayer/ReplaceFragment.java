package com.example.marst.moblieplayer;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marst.moblieplayer.base.BasePager;

/**
 * Created by Marst on 2019/7/18.
 */

@SuppressLint("ValidFragment")
public class ReplaceFragment extends Fragment {
    private BasePager currPager;

    public ReplaceFragment(BasePager pager) {
        this.currPager = pager;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return currPager.rootView;
    }
}
