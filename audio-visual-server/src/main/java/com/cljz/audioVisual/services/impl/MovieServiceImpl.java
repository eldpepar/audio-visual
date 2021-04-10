package com.cljz.audioVisual.services.impl;

import com.cljz.audioVisual.dao.MovieDao;
import com.cljz.audioVisual.pojo.MovieUrl;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.MovieService;
import com.cljz.audioVisual.utils.IdWorker;
import com.cljz.audioVisual.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public ResponseResult addMovie(MovieUrl movieUrl) {
        //检查用户名格式
        String name = movieUrl.getName();
        if (TextUtils.isEmply(name)) {
            return ResponseResult.FAILED("电影接口不可以为空.");
        }

        //检查当前用户名是否已经注册
        MovieUrl musicOld = movieDao.findOneByName(name);
        if (musicOld != null) {
            return ResponseResult.FAILED("电影接口已经存在.");
        }

        //补全数据
        movieUrl.setId(idWorker.nextId() + "");

        //保存数据
        movieDao.save(movieUrl);

        //返回结果
        return ResponseResult.SUCCESS("添加成功").setData(movieUrl);
    }

    @Override
    public ResponseResult deleteMovie(String id) {
        MovieUrl oldMovie = movieDao.findOneById(id);
        if (oldMovie == null) {
            return ResponseResult.FAILED("没有这个频道");
        } else {
            movieDao.deleteById(id);
            return ResponseResult.SUCCESS("删除成功").setData(oldMovie.getName());
        }
    }

    @Override
    public ResponseResult updateMovie(String id, MovieUrl movieUrl) {
        MovieUrl oldMovie = movieDao.findOneById(id);
        if (oldMovie == null) {
            return ResponseResult.FAILED("没有这个频道");
        } else {
            oldMovie.setName(movieUrl.getName());
            oldMovie.setUrl(movieUrl.getUrl());
            oldMovie.setUser(movieUrl.getUser());
            movieDao.save(oldMovie);
            return ResponseResult.SUCCESS("修改成功").setData(oldMovie);
        }
    }

    @Override
    public ResponseResult listMovie() {
        List<MovieUrl> movieUrlList = movieDao.findAll();
        if (movieUrlList.size() < 1) {
            return ResponseResult.FAILED("没有音乐接口");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(movieUrlList);
        }
    }
}