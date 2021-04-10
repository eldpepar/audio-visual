package com.cljz.audioVisual.services.impl;

import com.cljz.audioVisual.dao.TvDao;
import com.cljz.audioVisual.pojo.Tv;
import com.cljz.audioVisual.response.ResponseResult;
import com.cljz.audioVisual.services.TvService;
import com.cljz.audioVisual.utils.Constants;
import com.cljz.audioVisual.utils.IdWorker;
import com.cljz.audioVisual.utils.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvServiceImpl implements TvService {

    @Autowired
    private TvDao tvDao;

    @Autowired
    private IdWorker idWorker;

    //添加频道
    @Override
    public ResponseResult addTv(Tv tv) {
        //检查用户名格式
        String name = tv.getName();
        if (TextUtils.isEmply(name)) {
            return ResponseResult.FAILED("频道名不可以为空.");
        }

        //检查当前用户名是否已经注册
        Tv tvOld = tvDao.findOneByName(name);
        if (tvOld != null) {
            return ResponseResult.FAILED("该频道已经存在.");
        }

        //补全数据
        //tv.setIcon(Constants.User.DEFAULT_AVATAR);
        tv.setId(idWorker.nextId() + "");

        //保存数据
        tvDao.save(tv);

        //返回结果
        return ResponseResult.SUCCESS("添加成功").setData(tv);
    }

    //删除频道
    @Override
    public ResponseResult deleteTv(String id) {
        Tv oldTv = tvDao.findOneById(id);
        if (oldTv == null) {
            return ResponseResult.FAILED("没有这个频道");
        } else {
            tvDao.deleteById(id);
            return ResponseResult.SUCCESS("删除成功").setData(oldTv.getName());
        }
    }

    //修改频道
    @Override
    public ResponseResult updateTvr(String id, Tv tv) {
        Tv oldTv = tvDao.findOneById(id);
        if (oldTv == null) {
            return ResponseResult.FAILED("没有这个频道");
        } else {
            oldTv.setName(tv.getName());
            oldTv.setUrl(tv.getUrl());
            oldTv.setIcon(tv.getIcon());
            oldTv.setUser(tv.getUser());
            tvDao.save(oldTv);
            return ResponseResult.SUCCESS("修改成功").setData(oldTv);
        }
    }

    //显示频道列表
    @Override
    public ResponseResult listTv() {
        List<Tv> tvList = tvDao.findAll();
        if (tvList.size() < 1) {
            return ResponseResult.FAILED("没有电视接口");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(tvList);
        }
    }
}
