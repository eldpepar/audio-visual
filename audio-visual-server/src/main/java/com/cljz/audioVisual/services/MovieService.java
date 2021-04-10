package com.cljz.audioVisual.services;

import com.cljz.audioVisual.pojo.MovieUrl;
import com.cljz.audioVisual.response.ResponseResult;

public interface MovieService {
    //添加音乐地址
    ResponseResult addMovie(MovieUrl movieUrl);

    //删除音乐地址(根据id)
    ResponseResult deleteMovie(String id);

    //修改音乐地址
    ResponseResult updateMovie(String id,  MovieUrl movieUrl);

    //显示音乐列表
    ResponseResult listMovie();
}
