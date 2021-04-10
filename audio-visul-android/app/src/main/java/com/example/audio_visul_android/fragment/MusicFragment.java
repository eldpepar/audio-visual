package com.example.audio_visul_android.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.audio_visul_android.R;
import com.example.audio_visul_android.actvity.ClouldActivity;
import com.example.audio_visul_android.adaper.MusicInterAdaper;
import com.example.audio_visul_android.httpservice.API;
import com.example.audio_visul_android.model.MusicInfoListModel;
import com.example.audio_visul_android.model.MusicInfoModel;
import com.example.audio_visul_android.model.MusicItemModel;
import com.example.audio_visul_android.model.MusicModel;
import com.example.audio_visul_android.utils.Constants;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private ListView mListView;
    private MusicInterAdaper mMusicInferdaper;
    private List<MusicInfoModel> mDatas = new ArrayList<>();


    private SwipeRefreshLayout mSwipeLayout;

    //是否刷新中
    private boolean isRefresh = false;

    public MusicFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_music, container, false);

        mListView = view.findViewById(R.id.lv_music);

        //设置SwipeRefreshLayout
        mSwipeLayout = view.findViewById(R.id.swipeLayout);

        //设置下拉刷线的颜色
        mSwipeLayout.setColorSchemeColors(Color.BLUE,
                Color.GREEN,
                Color.YELLOW);

        // 设置手指在屏幕下拉多少距离会触发下拉刷新
        mSwipeLayout.setDistanceToTriggerSync(300);

        // 设定下拉圆圈的背景
        mSwipeLayout.setProgressBackgroundColorSchemeColor(Color.WHITE);

        // 设置圆圈的大小
        mSwipeLayout.setSize(20);

        //设置下拉刷新的监听
        mSwipeLayout.setOnRefreshListener(this);


        //显示获取到的数据
        setData();

        return view;
    }

    private void setData() {
        Call<MusicInfoListModel> task = getCall(Constants.User.URL);
        //获取请求的结果
        task.enqueue(new Callback<MusicInfoListModel>() {
            @Override
            public void onResponse(Call<MusicInfoListModel> call, Response<MusicInfoListModel> response) {
                //清空数据，防止重复
                mDatas.clear();
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        String name = response.body().getData().get(i).getName();
                        String url = response.body().getData().get(i).getUrl();
                        mDatas.add(new MusicInfoModel(name, url));
                        mMusicInferdaper = new MusicInterAdaper(mDatas);
                        mListView.setAdapter(mMusicInferdaper);
                    }

                    Drawable drawable = getResources().getDrawable(R.drawable.green);
                    mListView.setSelector(drawable);
                    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("CLJZ", "选择的为=============" + mDatas.get(position).getName());

                            Intent intent = new Intent(getContext(), ClouldActivity.class);
                            intent.putExtra("url", mDatas.get(position).getUrl());
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<MusicInfoListModel> call, Throwable t) {

            }
        });


        Log.d("CLJZ", "网络请求之后" + mDatas.size());
    }

    private Call<MusicInfoListModel> getCall(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口
        API api = retrofit.create(API.class);
        return api.getMusics();
    }

    @Override
    public void onRefresh() {
        //检查是否处于刷新状态
        if (!isRefresh) {
            isRefresh = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    //显示或隐藏刷新进度条
                    mSwipeLayout.setRefreshing(false);

                    //重新设置数据
                    mDatas.clear();
                    setData();
                    Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();

                    isRefresh = false;
                }
            }, 2000);
        }
    }
}