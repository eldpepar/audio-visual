package com.cljz.audioVisual.controller.user;

import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.MovieService;
import com.cljz.audioVisual.services.MusicService;
import com.cljz.audioVisual.services.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserVisitApi {
    @Autowired
    private TvService tvService;

    @Autowired
    private MusicService musicService;

    @Autowired
    private MovieService movieService;

    //获取电视列表
    @GetMapping("/tv-list")
    public ResponseResult getTvList() {
        return tvService.listTv();
    }

    //获取音乐列表
    @GetMapping("/music-list")
    public ResponseResult getMusicList() {
        return musicService.listMusic();
    }

    //获取电影解析接口列表
    @GetMapping("/movie-list")
    public ResponseResult getMovieList() {
        return movieService.listMovie();
    }
}
