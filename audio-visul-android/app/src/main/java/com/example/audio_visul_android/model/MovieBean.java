package com.example.audio_visul_android.model;

import java.util.List;

public class MovieBean {


    /**
     * success : true
     * code : 20000
     * message : 获取成功
     * data : [{"id":"825052208415375360","name":"线路一","url":"https://www.h8jx.com/jiexi.php?url=","user":"光子影音"},{"id":"825052266296770560","name":"线路二","url":"https://z1.m1907.cn/?jx=","user":"光子影音"},{"id":"825052350581309440","name":"线路三","url":"https://api.tv920.com/jx/?url=","user":"光子影音"},{"id":"825052420806541312","name":"线路四","url":"https://jx.youyitv.com/?url=","user":"光子影音"},{"id":"825052469024260096","name":"线路五","url":"https://api.lhh.la/vip/?url=","user":"光子影音"},{"id":"825052525638975488","name":"线路六","url":"https://jx.m3u8.tv/jiexi/?url=","user":"光子影音"},{"id":"825052570585137152","name":"线路七","url":"https://www.41478.net/?url=","user":"光子影音"},{"id":"825052697316032512","name":"线路九","url":"https://jiexi.q-q.wang/?url=","user":"光子影音"}]
     */

    private boolean success;
    private int code;
    private String message;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 825052208415375360
         * name : 线路一
         * url : https://www.h8jx.com/jiexi.php?url=
         * user : 光子影音
         */

        private String id;
        private String name;
        private String url;
        private String user;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }
}
