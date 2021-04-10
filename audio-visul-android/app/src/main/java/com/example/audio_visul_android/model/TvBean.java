package com.example.audio_visul_android.model;

public class TvBean {
    private String tv_name;
    private String tv_icon;
    private String  tv_url;



    public TvBean() {
    }

    public TvBean(String tv_name, String tv_url, String tv_icon) {
        this.tv_name = tv_name;
        this.tv_url = tv_url;
        this.tv_icon = tv_icon;
    }

    public String getTv_name() {
        return tv_name;
    }

    public void setTv_name(String tv_name) {
        this.tv_name = tv_name;
    }


    public String getTv_url() {
        return tv_url;
    }

    public void setTv_url(String tv_url) {
        this.tv_url = tv_url;
    }

    public String getTv_icon() {
        return tv_icon;
    }

    public void setTv_icon(String tv_icon) {
        this.tv_icon = tv_icon;
    }
}
