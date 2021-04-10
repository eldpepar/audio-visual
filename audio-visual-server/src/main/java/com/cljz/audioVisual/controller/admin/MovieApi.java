package com.cljz.audioVisual.controller.admin;

import com.cljz.audioVisual.pojo.MovieUrl;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("@permission.admin()")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class MovieApi {
    @Autowired
    private MovieService movieService;

    //添加电影解析接口
    @PostMapping("/movie-add")
    public ResponseResult addMovie(@RequestBody MovieUrl movieUrl) {
        return movieService.addMovie(movieUrl);
    }

    //删除电影解析接口
    @DeleteMapping("/movie-del")
    public ResponseResult delTv(String id) {
        return movieService.deleteMovie(id);
    }

    //更新电影解析接口
    @PutMapping("/movie-update")
    public ResponseResult updateTv(String id, @RequestBody MovieUrl movieUrl) {
        return movieService.updateMovie(id, movieUrl);
    }
}