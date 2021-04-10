package com.cljz.audioVisual.controller.admin;

import com.cljz.audioVisual.pojo.MusicUrl;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("@permission.admin()")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class MusicApi {
    @Autowired
    private MusicService musicService;

    //添加音乐接口
    @PostMapping("/music-add")
    public ResponseResult addMusic(@RequestBody MusicUrl musicUrl) {
        return musicService.addMusic(musicUrl);
    }

    //删除音乐接口
    @DeleteMapping("/music-del")
    public ResponseResult delTv(String id) {
        return musicService.deleteMusic(id);
    }

    //更新音乐接口
    @PutMapping("/music-update")
    public ResponseResult updateTv(String id, @RequestBody MusicUrl musicUrl) {
        return musicService.updateMusic(id, musicUrl);
    }
}
