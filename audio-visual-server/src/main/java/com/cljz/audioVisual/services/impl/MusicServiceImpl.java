package com.cljz.audioVisual.services.impl;

import com.cljz.audioVisual.dao.MusicDao;
import com.cljz.audioVisual.pojo.MusicUrl;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.MusicService;
import com.cljz.audioVisual.utils.IdWorker;
import com.cljz.audioVisual.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MusicServiceImpl implements MusicService {

    @Autowired
    private MusicDao musicDao;

    @Autowired
    private IdWorker idWorker;

    @Override
    public ResponseResult addMusic(MusicUrl musicUrl) {
        //检查用户名格式
        String name = musicUrl.getName();
        if (TextUtils.isEmply(name)) {
            return ResponseResult.FAILED("音乐接口不可以为空.");
        }

        //检查当前用户名是否已经注册
        MusicUrl musicOld = musicDao.findOneByName(name);
        if (musicOld != null) {
            return ResponseResult.FAILED("音乐接口已经存在.");
        }

        //补全数据
        musicUrl.setId(idWorker.nextId() + "");

        //保存数据
        musicDao.save(musicUrl);

        //返回结果
        return ResponseResult.SUCCESS("添加成功").setData(musicUrl);
    }

    @Override
    public ResponseResult deleteMusic(String id) {
        MusicUrl oldMusic = musicDao.findOneById(id);
        if (oldMusic == null) {
            return ResponseResult.FAILED("没有这个频道");
        } else {
            musicDao.deleteById(id);
            return ResponseResult.SUCCESS("删除成功").setData(oldMusic.getName());
        }
    }

    @Override
    public ResponseResult updateMusic(String id, MusicUrl musicUrl) {
        MusicUrl oldMusic = musicDao.findOneById(id);
        if (oldMusic == null) {
            return ResponseResult.FAILED("没有这个音乐接口");
        } else {
            oldMusic.setName(musicUrl.getName());
            oldMusic.setUrl(musicUrl.getUrl());
            oldMusic.setUser(musicUrl.getUser());
            musicDao.save(oldMusic);
            return ResponseResult.SUCCESS("修改成功").setData(oldMusic);
        }
    }

    @Override
    public ResponseResult listMusic() {
        List<MusicUrl> musicUrlList = musicDao.findAll();
        if (musicUrlList.size() < 1) {
            return ResponseResult.FAILED("没有音乐接口");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(musicUrlList);
        }
    }
}