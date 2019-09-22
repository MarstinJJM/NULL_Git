package com.example.marst.moblieplayer.domain;

/**
 * Created by Marst on 2019/7/20.
 * 作用：代表视频或音频
 */

public class MediaItem {
    private String name;//在SD卡现实的名称
    private Long duration;//视频长度
    private Long size; //视频文件大小
    private String data;//视频地址

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
