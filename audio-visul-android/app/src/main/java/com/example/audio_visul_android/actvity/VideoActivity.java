package com.example.audio_visul_android.actvity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;

import androidx.appcompat.app.AppCompatActivity;

import com.example.audio_visul_android.R;
import com.tencent.smtt.export.external.interfaces.SslError;
import com.tencent.smtt.export.external.interfaces.SslErrorHandler;
import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.WebSettings;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

public class VideoActivity extends AppCompatActivity {

    private com.tencent.smtt.sdk.WebView wv_video;


    private String base_url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        wv_video = findViewById(R.id.wv_video);

        Intent intent = getIntent();
        base_url = intent.getStringExtra("base_url");
        Log.d("CLJZ", "获取到的网址为:获取到的为===> " + base_url);

        initView();
    }

    private void initView() {
        //非wifi情况下，主动下载x5内核
        QbSdk.setDownloadWithoutWifi(true);
        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
            }
            @Override
            public void onCoreInitFinished() {

            }
        };

        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);


        wv_video = findViewById(R.id.wv_video);
        wv_video.getSettings().setUserAgentString("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36");

        wv_video.getSettings().setBuiltInZoomControls(false); //设置内置的缩放控件。若为false，则该WebView不可缩放
        wv_video.getSettings().setDisplayZoomControls(true); //隐藏原生的缩放控件
        wv_video.getSettings().setBlockNetworkImage(false);//解决图片不显示
        wv_video.getSettings().setLoadsImagesAutomatically(true); //支持自动加载图片
        wv_video.getSettings().setDefaultTextEncodingName("utf-8");//设置编码格式
        wv_video.getSettings().setDomStorageEnabled(true);
        String appCachePath = getApplicationContext().getCacheDir().getAbsolutePath();
        wv_video.getSettings().setAppCachePath(appCachePath);
        wv_video.getSettings().setAllowFileAccess(true);    // 可以读取文件缓存
        wv_video.getSettings().setAppCacheEnabled(true);    //开启H5(APPCache)缓存功能
        wv_video.getSettings().setJavaScriptEnabled(true);
        wv_video.getSettings().setMixedContentMode(WebSettings.LOAD_NORMAL);     // https下访问http资源

        wv_video.loadUrl(base_url);

        wv_video.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                return super.shouldOverrideUrlLoading(webView, s);
            }

            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
            }

            @Override
            public void onReceivedSslError(WebView webView, SslErrorHandler sslErrorHandler, SslError sslError) {
                sslErrorHandler.proceed();// 接受所有网站的证书
            }
        });
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
            finish();
            return true;
        }
        //继续执行父类其他点击事件
        return super.onKeyUp(keyCode, event);
    }
}