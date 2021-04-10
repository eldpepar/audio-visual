package com.example.audio_visul_android.adaper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.audio_visul_android.R;
import com.example.audio_visul_android.model.TvBean;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TvAdaper extends BaseAdapter {

    private List<TvBean> datas;

    public TvAdaper() {
    }

    public TvAdaper(List<TvBean> datas) {
        this.datas = datas;
    }


    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {

        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_layout_tv, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.item_tv_name.setText(datas.get(position).getTv_name());

        //设置电视图片
        String icon_img = datas.get(position).getTv_icon();
        Picasso.with(parent.getContext()).load(icon_img).into(viewHolder.item_tv_icon);

        //增加title
        viewHolder.item_video.getTitleTextView().setVisibility(View.GONE);

        //设置返回键
        viewHolder.item_video.getBackButton().setVisibility(View.GONE);

        //设置全屏按键功能
        final ViewHolder finalViewHolder = viewHolder;
        viewHolder.item_video.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //开启全屏按钮
                finalViewHolder.item_video.startWindowFullscreen(parent.getContext(), true, true);

                //开启自动旋转
                finalViewHolder.item_video.setRotateViewAuto(true);

                //一全屏就锁屏横屏
                finalViewHolder.item_video.setLockLand(true);

                //全屏锁定
                finalViewHolder.item_video.setNeedLockFull(true);
            }
        });

        //取消流量提示
        viewHolder.item_video.setNeedShowWifiTip(false);

        //设置播放地址
        viewHolder.item_video.setUpLazy(datas.get(position).getTv_url(), true, null, null, null);

        //防止错位设置
        viewHolder.item_video.setPlayTag(position+"");
        viewHolder.item_video.setPlayPosition(position);

        //音频焦点冲突时是否释放
        viewHolder.item_video.setReleaseWhenLossAudio(false);

        //全屏动画
        viewHolder.item_video.setShowFullAnimation(true);

        //小屏时不触摸滑动
        viewHolder.item_video.setIsTouchWiget(false);


        return convertView;
    }

     public class  ViewHolder {
        private ImageView item_tv_icon;
        private TextView item_tv_name;

        private StandardGSYVideoPlayer item_video;


        ViewHolder(View view) {
            item_tv_icon = view.findViewById(R.id.item_tv_icon);
            item_tv_name = view.findViewById(R.id.item_tv_name);
            item_video = view.findViewById(R.id.item_video);
        }
    }
}