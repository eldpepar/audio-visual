package com.cljz.audioVisual.services;

import com.cljz.audioVisual.pojo.MusicUrl;
import com.cljz.audioVisual.response.ResponseResult;

public interface MusicService {
    //添加音乐地址
    ResponseResult addMusic(MusicUrl musicUrl);

    //删除音乐地址(根据id)
    ResponseResult deleteMusic(String id);

    //修改音乐地址
    ResponseResult updateMusic(String id,  MusicUrl musicUrl);

    //显示音乐列表
    ResponseResult listMusic();
}