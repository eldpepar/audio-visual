package com.example.audio_visul_android.actvity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.audio_visul_android.R;
import com.example.audio_visul_android.adaper.MusicAdapter;
import com.example.audio_visul_android.httpservice.API;
import com.example.audio_visul_android.model.MusicItemModel;
import com.example.audio_visul_android.model.MusicModel;
import com.example.audio_visul_android.utils.CheckNetwork;
import com.example.audio_visul_android.utils.Constants;
import com.google.common.base.Splitter;
import com.quinny898.library.persistentsearch.SearchBox;
import com.quinny898.library.persistentsearch.SearchResult;
import com.syd.oden.circleprogressdialog.core.CircleProgressDialog;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClouldActivity extends AppCompatActivity {

    RecyclerView rvMusic;

    private MusicAdapter adapter;
    private List<MusicModel> mList = new ArrayList<>();
    private List<MusicItemModel.DataBean> mDatas = new ArrayList<>();
    private  String url;

    //搜索框控件，可以自定义style
    private SearchBox sbSearch;

    private  CircleProgressDialog  circleProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clould);
        //圆形滚动
        circleProgressDialog = new CircleProgressDialog(this);
        initView();

        //搜索框的初始化
        sbSearch.enableVoiceRecognition(this);

        sbSearch.setMenuListener(new SearchBox.MenuListener() {
            @Override
            public void onMenuClick() {
                finish();
            }
        });

        sbSearch.setSearchListener(new SearchBox.SearchListener() {
            @Override
            public void onSearchOpened() {

            }

            @Override
            public void onSearchCleared() {

            }

            @Override
            public void onSearchClosed() {

            }

            @Override
            public void onSearchTermChanged(String s) {
               setData(s,url);
            }

            @Override
            public void onSearch(String s) {
                setData(s,url);
            }

            @Override
            public void onResultClick(SearchResult searchResult) {

            }
        });

    }

    private void initView() {
        rvMusic = findViewById(R.id.rv_music);
        sbSearch = findViewById(R.id.searchbox);

        Intent intent = getIntent();
        url = intent.getStringExtra("url");

        url = getParam(url, "type");

        Log.d("CLJZ", "获取到的url值为=============" + url);

        // 模拟从网络获取音乐数据列表
        rvMusic.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new MusicAdapter(this, mList);
        rvMusic.setAdapter(adapter);


        adapter.setItemClikListener(new MusicAdapter.OnItemClikListener() {
            @Override
            public void onItemClik(View view, int position) {
                if (!CheckNetwork.isNetworkConnected(ClouldActivity.this)) {
                    return;
                }
                Intent intent = new Intent(ClouldActivity.this, PlayMusicActivity.class);
                intent.putExtra(PlayMusicActivity.NAME, mList.get(position).getName());
                intent.putExtra(PlayMusicActivity.POSTER, mList.get(position).getPoster());
                intent.putExtra(PlayMusicActivity.PATH, mList.get(position).getPath());
                intent.putExtra(PlayMusicActivity.AUTHOR, mList.get(position).getAuthor());
                startActivity(intent);
            }

            @Override
            public void onItemLongClik(View view, int position) {

            }
        });

    }

    private void setData(String INPUT,String type) {

        circleProgressDialog.showDialog();
        Call<MusicItemModel> task = getCall(Constants.User.URL_MUSIC,type,INPUT);
        //获取请求的结果
        task.enqueue(new Callback<MusicItemModel>() {
            @Override
            public void onResponse(Call<MusicItemModel> call, Response<MusicItemModel> response) {
                //清空之前得到的数据
                mList.clear();
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    for (int i = 0; i <  response.body().getData().size(); i++) {
                        MusicModel musicModel = new MusicModel();
                        musicModel.setName(response.body().getData().get(i).getTitle());
                        musicModel.setAuthor(response.body().getData().get(i).getAuthor());
                        musicModel.setMusicId(response.body().getData().get(i).getSongid());
                        musicModel.setPoster(response.body().getData().get(i).getPic());
                        musicModel.setRemark(response.body().getData().get(i).getTitle());
                        musicModel.setPath(response.body().getData().get(i).getUrl());
                        mList.add(musicModel);
                    }
                } else {
                    Toast.makeText(getBaseContext(), "获取失败", Toast.LENGTH_SHORT).show();
                }
                circleProgressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<MusicItemModel> call, Throwable t) {

            }
        });
    }


    private Call<MusicItemModel> getCall(String url, String type,String input) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口
        API api = retrofit.create(API.class);
        return api.getMusicList("name", type,"1", input);
    }

    public static String getParam(String url, String name) {
        String params = url.substring(url.indexOf("?") + 1, url.length());
        Map<String, String> split = Splitter.on("&").withKeyValueSeparator("=").split(params);
        return split.get(name);
    }
}