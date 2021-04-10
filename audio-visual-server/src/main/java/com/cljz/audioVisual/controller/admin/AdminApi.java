package com.cljz.audioVisual.controller.admin;

import com.cljz.audioVisual.dao.*;
import com.cljz.audioVisual.pojo.DayVisit;
import com.cljz.audioVisual.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import oshi.SystemInfo;
import oshi.hardware.GlobalMemory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/admin")
@PreAuthorize("@permission.admin()")
@CrossOrigin(methods = { RequestMethod.GET, RequestMethod.POST }, origins = "*")
public class AdminApi {

    @Autowired
    private UserDao userDao;

    @Autowired
    private TvDao tvDao;

    @Autowired
    private MusicDao musicDao;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private DayVisitDao dayVisitDao;

    @Autowired
    private SystemInfo systemInfo;

    //获取用户数量
    @GetMapping("/user-count")
    public ResponseResult getUserCount() {
        Integer count = Math.toIntExact(userDao.count());
        if (count < 1) {
            return ResponseResult.FAILED("没有用户");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(count);
        }
    }

    //获取电视直播源数量
    @GetMapping("/tv-count")
    public ResponseResult getTvCount() {
        Integer count = Math.toIntExact(tvDao.count());
        if (count < 1) {
            return ResponseResult.FAILED("没有电视直播源");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(count);
        }
    }

    //获取音乐接口数量
    @GetMapping("/music-count")
    public ResponseResult getMusicCount() {
        Integer count = Math.toIntExact(musicDao.count());
        if (count < 1) {
            return ResponseResult.FAILED("没有音乐接口");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(count);
        }
    }

    //获取视频解析接口数量
    @GetMapping("/movie-count")
    public ResponseResult getMovieCount() {
        Integer count = Math.toIntExact(movieDao.count());
        if (count < 1) {
            return ResponseResult.FAILED("没有视频解析接口");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(count);
        }
    }

    //获取周访问量
    @GetMapping("/week-visit")
    public ResponseResult getWeekVisit() {
        List<DayVisit> dayVisitList = dayVisitDao.findAll();
        return ResponseResult.SUCCESS("获取成功").setData(dayVisitList);
    }

    //获取系统内存使用率
    @GetMapping("/memInfo")
    public ResponseResult getMemTotal()  {
        GlobalMemory memory = systemInfo.getHardware().getMemory();
        long totalByte = memory.getTotal();
        long acaliableByte = memory.getAvailable();
        double count = (1 - (double)acaliableByte/(double)totalByte)*100;
        int use = (int) count;
        if (use < 0) {
            return ResponseResult.FAILED("获取失败");
        } else {
            return ResponseResult.SUCCESS("获取成功").setData(use);
        }
    }
}