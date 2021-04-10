package com.example.audio_visul_android.model;

public class MusicInfoModel {
    private String name;
    private String url;

    public MusicInfoModel(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public MusicInfoModel() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
