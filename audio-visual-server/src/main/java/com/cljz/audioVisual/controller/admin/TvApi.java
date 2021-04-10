package com.cljz.audioVisual.controller.admin;

import com.cljz.audioVisual.pojo.Tv;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@PreAuthorize("@permission.admin()")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class TvApi {

    @Autowired
    private TvService tvService;

    //添加电视直播接口
    @PostMapping("/tv-add")
    public ResponseResult addTv(@RequestBody Tv tv) {
        return tvService.addTv(tv);
    }

    //删除电视直播接口
    @DeleteMapping("/tv-del")
    public ResponseResult delTv(String id) {
        return tvService.deleteTv(id);
    }

    //更新电视频道
    @PutMapping("/tv-update")
    public ResponseResult updateTv(String id, @RequestBody Tv tv) {
        return tvService.updateTvr(id, tv);
    }
}