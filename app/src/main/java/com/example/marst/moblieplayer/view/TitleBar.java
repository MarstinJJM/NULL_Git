package com.example.marst.moblieplayer.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.marst.moblieplayer.R;

/**
 * Created by Marst on 2019/7/20.
 */

public class TitleBar extends LinearLayout{

    private final Context context;
    private View search;
    private View game;
    private View history;

    public TitleBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        search = getChildAt(1);
        game = getChildAt(2);
        history = getChildAt(3);
        MyOnclickListener myOnclickListener = new MyOnclickListener();
        search.setOnClickListener(myOnclickListener);
        game.setOnClickListener(myOnclickListener);
        history.setOnClickListener(myOnclickListener);
    }
    class MyOnclickListener implements OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.tv_search:
                    Toast.makeText(context,"SEARCH....",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.rl_game:
                    Toast.makeText(context,"GAME....",Toast.LENGTH_SHORT).show();
                    break;
                case R.id.iv_history:
                    Toast.makeText(context,"HISTORY....",Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }
}
