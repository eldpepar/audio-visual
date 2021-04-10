package com.example.audio_visul_android.model;

import java.util.List;

public class MusicInfoListModel {

    /**
     * success : true
     * code : 20000
     * message : 获取成功
     * data : [{"id":"826510252919750656","name":"万能-网易云","url":"https://api.berfen.com/api/music?filter=name&type=netease&page=1&input=","user":"光子影音"},{"id":"826510446595932160","name":"万能-QQ音乐","url":"https://api.berfen.com/api/music?filter=name&type=qq&page=1&input=","user":"光子影音"},{"id":"826510599830634496","name":"万能-酷狗","url":"https://api.berfen.com/api/music?filter=name&type=kugou&page=1&input=","user":"光子影音"},{"id":"826510708010123264","name":"万能-虾米","url":"https://api.berfen.com/api/music?filter=name&type=xiami&page=1&input=","user":"光子影音"},{"id":"826510796723847168","name":"万能-咪咕","url":"https://api.berfen.com/api/music?filter=name&type=migu&page=1&input=","user":"光子影音"},{"id":"826510880215662592","name":"万能-百度","url":"https://api.berfen.com/api/music?filter=name&type=baidu&page=1&input=","user":"光子影音"},{"id":"826510969130713088","name":"万能-酷我","url":"https://api.berfen.com/api/music?filter=name&type=kuwo&page=1&input=","user":"光子影音"}]
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
         * id : 826510252919750656
         * name : 万能-网易云
         * url : https://api.berfen.com/api/music?filter=name&type=netease&page=1&input=
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
