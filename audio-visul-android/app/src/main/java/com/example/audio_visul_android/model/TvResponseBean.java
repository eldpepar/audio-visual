package com.example.audio_visul_android.model;

import java.util.List;

public class TvResponseBean {


    /**
     * success : true
     * code : 20000
     * message : 获取成功
     * data : [{"id":"825029213491822592","name":"CCTV1","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://112.50.243.8/PLTV/88888888/224/3221225815/1.m3u8","user":"光子影音"},{"id":"825029547551358976","name":"CCTV2","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://112.50.243.10/PLTV/88888888/224/3221225800/1.m3u8","user":"光子影音"},{"id":"825029597757177856","name":"CCTV3","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://183.207.249.14/PLTV/3/224/3221225588/index.m3u8","user":"光子影音"},{"id":"825029644666273792","name":"CCTV4","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://223.110.242.130:6610/gitv/live1/G_CCTV-4-HQ/1.m3u8","user":"光子影音"},{"id":"825029696432373760","name":"CCTV5","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://223.110.243.172/PLTV/3/224/3221227166/index.m3u8","user":"光子影音"},{"id":"825029802644733952","name":"CCTV7","icon":"https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg","url":"http://112.50.243.8/PLTV/88888888/224/3221225805/1.m3u8","user":"光子影音"}]
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
         * id : 825029213491822592
         * name : CCTV1
         * icon : https://img0.baidu.com/it/u=1635666334,682636565&fm=26&fmt=auto&gp=0.jpg
         * url : http://112.50.243.8/PLTV/88888888/224/3221225815/1.m3u8
         * user : 光子影音
         */

        private String id;
        private String name;
        private String icon;
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

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
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
