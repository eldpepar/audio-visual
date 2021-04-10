package com.example.audio_visul_android.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.audio_visul_android.R;
import com.example.audio_visul_android.actvity.VideoActivity;
import com.example.audio_visul_android.model.MovieBean;
import com.example.audio_visul_android.httpservice.API;
import com.example.audio_visul_android.utils.Constants;

import java.net.HttpURLConnection;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieFragment extends Fragment {

    private String choseInter = "";
    private String base_url = "https://v.qq.com/";
    private String rold_url = "";

    private String urls[];

    private boolean isCheck = false;
    private Integer viewId = 0;

    private WebView mWebView;


    private String[] mDataItem = {};

    private Handler handler = new Handler();

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Log.d("CLJZ", "当前的网址为: " + mWebView.getUrl());
            if (mWebView.getUrl().contains("/cover/m/")) {

                base_url = rold_url + mWebView.getUrl();
                Log.d("CLJZ", "获取到的网址为: " + base_url);

                Intent intent = new Intent(getContext(), VideoActivity.class);
                intent.putExtra("base_url", base_url);
                startActivity(intent);
                Message message = new Message();
                message.what = 1;
                handlerStop.sendMessage(message);
            }
            handler.postDelayed(this, 500);
        }
    };

    @SuppressLint("HandlerLeak")
    final Handler handlerStop = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    handler.removeCallbacks(runnable);
                    break;
                case 2:
                    Bundle data = msg.getData();
                    String[] item = (String[]) data.get("item");
                    urls = data.getStringArray("item-url");
                    setDialog(getView(), item);

                default:
            }
            super.handleMessage(msg);
        }
    };

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_movie, container, false);


        viewId = view.getId();
        //初始化控件
        initView(view);

        handler.postDelayed(runnable, 500);

        //设置页面
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAllowContentAccess(true);

        //覆盖WebView默认通过第三方或者是系统浏览器打开网页的行为，使得网页可以在WebView中打开
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Uri uri = Uri.parse(url);
                if (!"http".equals(uri.getScheme()) || !"https".equals(uri.getScheme())) {
                    return false;
                } else {
                    view.loadUrl(url);
                    return false;
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
        });

        mWebView.loadUrl(base_url);

        setData();
        return view;
    }

    private void initView(View view) {
        mWebView = view.findViewById(R.id.web_view);

        mWebView.setOnLongClickListener(new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {

            String url = rold_url + mWebView.getUrl();

            url = url.replace("http://m.v.qq.com/x/m/play", "https://v.qq.com/x/cover/");
            url = url.replace("?cid=", "/");
            url = url.replace("&vid=","/");
            url = url + ".html";

            Log.d("CLJZ", "第二次获取的网址为: " + url);

            Intent intent = new Intent(getContext(), VideoActivity.class);
            intent.putExtra("base_url", url);
            startActivity(intent);

            return true;
        }
    });
}

    private void setDialog(View view, final String [] mData) {
        //设置弹出框的样式
        final AlertDialog.Builder bulider = new AlertDialog.Builder(view.getContext());

        //外部区域不允许点击
        bulider.setCancelable(false);

        final View dialogView = View.inflate(this.getContext(), R.layout.dialog_layout, null);
        bulider.setView(dialogView);

        //弹出的菜单
        final ListView mChoses = dialogView.findViewById(R.id.lv_check);

        TextView mTitle = dialogView.findViewById(R.id.tv_titile);
        mTitle.setText("请选择电影解析接口");

        //取消滚动条
        mChoses.setVerticalScrollBarEnabled(false);

        Log.d("CLJZ", "看看有没有数据:+++++++++ " + mDataItem.length);


        ArrayAdapter<String> adapter = new ArrayAdapter<>(this.getContext(), android.R.layout.simple_list_item_1, mData);

        //设置listview选中后的颜色
        Drawable drawable = getResources().getDrawable(R.drawable.blue);
        mChoses.setSelector(drawable);

        //设置适配器
        mChoses.setAdapter(adapter);

        //获取选中的值
        mChoses.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                choseInter = mData[position];
                rold_url = urls[position];
            }
        });

        //确定按钮
        TextView confirm = dialogView.findViewById(R.id.tv_confirm);
        final AlertDialog dialog = bulider.show();

        if (isCheck == true) {
            dialog.dismiss();
        }
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "选中的为===>" + choseInter, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
                isCheck = true;
            }
        });
    }

    private void setData() {
        Call<MovieBean> task = getCall(Constants.User.URL);
        //获取请求的结果
        task.enqueue(new Callback<MovieBean>() {
            @Override
            public void onResponse(Call<MovieBean> call, Response<MovieBean> response) {
                if (response.code() == HttpURLConnection.HTTP_OK) {
                    List<MovieBean.DataBean> dataBeans = response.body().getData();
                   String[] datas = new String[dataBeans.size()];
                    String[] urls = new String[dataBeans.size()];
                    for (int i = 0; i < dataBeans.size(); i++) {
                        datas[i] = dataBeans.get(i).getName();
                        urls[i] = dataBeans.get(i).getUrl();
                    }
                    //设置对话框
                    //setDialog(getView(),mDataItem);
//                    setmDataItem(datas);
                    Log.d("CLJZ", "获取到了列表======================: " + datas.toString());
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("item",datas);
                    bundle.putStringArray("item-url",urls);
                    message.setData(bundle);
                    message.what = 2;
                    handlerStop.sendMessage(message);
                }
            }

            @Override
            public void onFailure(Call<MovieBean> call, Throwable t) {

            }
        });

    }
    private Call<MovieBean> getCall(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //获取接口
        API api = retrofit.create(API.class);
        return api.getMovies();
    }
}