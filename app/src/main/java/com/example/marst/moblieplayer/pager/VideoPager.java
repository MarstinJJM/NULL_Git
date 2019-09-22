package com.example.marst.moblieplayer.pager;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.format.Formatter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.marst.moblieplayer.R;
import com.example.marst.moblieplayer.base.BasePager;
import com.example.marst.moblieplayer.domain.MediaItem;
import com.example.marst.moblieplayer.utils.Utils;

import java.util.ArrayList;

/**
 * Created by Marst on 2019/7/18.
 * 作用：本地视频
 */

public class VideoPager extends BasePager {
    private TextView textView;
    private ListView lv_video_pager;
    private TextView tv_nomedia;
    private ProgressBar pb_loading;
    private ArrayList<MediaItem >mediaItems;
    private Utils utils;

    public VideoPager(Context context) {
        super(context);
        utils = new Utils();
    }

    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.video_pager,null);
        lv_video_pager = view.findViewById(R.id.lv_video_pager);
        tv_nomedia = view.findViewById(R.id.tv_nomedia);
        pb_loading = view.findViewById(R.id.pb_loading);
        
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        System.out.println("本地视频数据初始化");
        getData();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //主线程
            if (mediaItems != null && mediaItems.size() > 0 ){
                //有内容，隐藏
                tv_nomedia.setVisibility(View.GONE);
                pb_loading.setVisibility(View.GONE);
                //设置适配器
                lv_video_pager.setAdapter(new VideoPaferAdapter());
            }else {

            }
        }
    };
    class VideoPaferAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mediaItems.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHodler viewHodler;
            if (view == null){
                view = View.inflate(context,R.layout.item_video_pager,null);
                viewHodler = new ViewHodler();
                viewHodler.tv_name = view.findViewById(R.id.tv_name);
                viewHodler.tv_duration = view.findViewById(R.id.tv_duration);
                viewHodler.tv_size = view.findViewById(R.id.tv_size);
                view.setTag(viewHodler);//
            }else {
                viewHodler = (ViewHodler) view.getTag();
            }
            MediaItem mediaItem = mediaItems.get(i);
            viewHodler.tv_name.setText(mediaItem.getName());
            viewHodler.tv_size.setText(Formatter.formatFileSize(context,mediaItem.getSize()));
            viewHodler.tv_duration.setText(utils.stringForTime(mediaItem.getDuration()));
            return null;
        }
    }
    static class ViewHodler{
        TextView tv_name;
        TextView tv_duration;
        TextView tv_size;
    }
    //资源很多，不能在主线程
    private void getData() {
        new Thread(){
            @Override
            public void run() {
                super.run();
                mediaItems = new ArrayList<MediaItem>();
                ContentResolver contentResolver = context.getContentResolver();
                Uri uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;//存储
                String[] objects = {
                        MediaStore.Video.Media.DISPLAY_NAME,//在SD卡现实的名称
                        MediaStore.Video.Media.DURATION,//视频长度
                        MediaStore.Video.Media.SIZE,//视频文件大小
                        MediaStore.Video.Media.DATA//视频地址
                };
                Cursor cursor = contentResolver.query(uri,objects,null,null,null);
                if (cursor != null){
                    while(cursor.moveToNext()){
                        MediaItem mediaItem = new MediaItem();
                        String name = cursor.getString(0);
                        mediaItem.setName(name);
                        Long duration = cursor.getLong(1);
                        mediaItem.setDuration(duration);
                        Long size = cursor.getLong(2);
                        mediaItem.setSize(size);
                        String data = cursor.getString(3);
                        mediaItem.setData(data);
                        //把视频添加到列表
                        mediaItems.add(mediaItem);
                    }
                    cursor.close();
                }
                handler.sendEmptyMessage(1);
            }
        }.start();
    }

}
