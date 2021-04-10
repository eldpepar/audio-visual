package com.cljz.audioVisual.services;

import com.cljz.audioVisual.pojo.Tv;
import com.cljz.audioVisual.pojo.User;
import com.cljz.audioVisual.response.ResponseResult;

public interface TvService {

    //添加电视直播源
    ResponseResult addTv(Tv tv);

    //删除电视频道(根据id)
    ResponseResult deleteTv(String id);

    //修改电视地址
    ResponseResult updateTvr(String id,  Tv tv);

    //显示电视列表
    ResponseResult listTv();

}
