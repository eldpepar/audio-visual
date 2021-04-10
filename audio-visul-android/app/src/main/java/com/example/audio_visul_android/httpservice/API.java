package com.example.audio_visul_android.httpservice;

import com.example.audio_visul_android.model.MovieBean;
import com.example.audio_visul_android.model.MusicInfoListModel;
import com.example.audio_visul_android.model.MusicItemModel;
import com.example.audio_visul_android.model.MusicModel;
import com.example.audio_visul_android.model.TvResponseBean;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    //获取电视直播接口
    @GET("/user/tv-list")
    Call<TvResponseBean> getTvs();

    //获取音乐直播接口
    @GET("/user/music-list")
    Call<MusicInfoListModel> getMusics();

    //获取电影直播接口
    @GET("/user/movie-list")
    Call<MovieBean> getMovies();

    @GET("/api/music")
    Call<MusicItemModel> getMusicList(@Query("filter") String filter,
                                      @Query("type") String type,
                                      @Query("page") String page,
                                      @Query("input") String input);

}
