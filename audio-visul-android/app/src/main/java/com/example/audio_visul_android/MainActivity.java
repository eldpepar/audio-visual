package com.example.audio_visul_android;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.audio_visul_android.adaper.FragmentAdapter;
import com.example.audio_visul_android.fragment.MovieFragment;
import com.example.audio_visul_android.fragment.MusicFragment;
import com.example.audio_visul_android.fragment.TvFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mPager;
    private TabLayout mTabLayout;
    private List<Fragment> mFragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void setTabs(String[] tab_titles, int[] tab_imgs) {
        for (int i = 0; i < tab_titles.length; i++) {
            //获取TabLayout的tab
            TabLayout.Tab tab = mTabLayout.newTab();
            //初始化条目布局view
            View view = getLayoutInflater().inflate(R.layout.tab_item, null);
            tab.setCustomView(view);
            //tab的文字
            TextView tvTitle = view.findViewById(R.id.tv_des);
            tvTitle.setText(tab_titles[i]);
            //tab的图片
            ImageView imgTab = view.findViewById(R.id.iv_top);
            imgTab.setImageResource(tab_imgs[i]);
            if (i == 0) {
                //设置第一个默认选中
                mTabLayout.addTab(tab, true);
            } else {
                mTabLayout.addTab(tab, false);
            }
        }
    }

    private void initView() {
        mPager =  findViewById(R.id.pager);
        mTabLayout = findViewById(R.id.tab_layout);
        mFragments.add(new TvFragment());
        mFragments.add(new MusicFragment());
        mFragments.add(new MovieFragment());
        String tab_titles[] = new String[]{"电视", "音乐", "电影"};
        int tab_imgs[] = new int[] {R.drawable.tab_tv,R.drawable.tab_music,R.drawable.tab_movie};
        setTabs(tab_titles,tab_imgs);

        //设置viewpager的adapter
        mPager.setAdapter(new FragmentAdapter(getSupportFragmentManager(), mFragments));

        //TabLayout与ViewPager的绑定
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mPager));
    }
}
