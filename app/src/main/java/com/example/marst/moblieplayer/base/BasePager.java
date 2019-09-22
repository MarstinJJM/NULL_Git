package com.example.marst.moblieplayer.base;

import android.content.Context;
import android.view.View;

/**
 * Created by Marst on 2019/7/18.
 *基类、公共类　菜单栏的基类
 */

public abstract class BasePager {
    //上下文
    public Context context;
    public View rootView;//视图，由各个子页面实例化结果
    public boolean isInitData = false;

    public BasePager(Context context){
        this.context = context;
        rootView = initView();//调用时候孩子的initView();
        isInitData = false;
    }
    /**
     * 强制孩子实现该方法，实现特定效果
     * @return
     */
    public abstract View initView();
    //当孩子初始化数据时，重写方法，用以请求数据或者显示数据
    public void initData(){

    }
}
