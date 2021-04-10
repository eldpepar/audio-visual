package com.example.audio_visul_android.adaper;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.audio_visul_android.R;
import com.example.audio_visul_android.model.MusicInfoModel;

import java.util.List;

public class MusicInterAdaper extends BaseAdapter {

    private List<MusicInfoModel> mData;

    public MusicInterAdaper() {

    }

    public MusicInterAdaper(List<MusicInfoModel> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(parent.getContext(), R.layout.item_music_inter, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.item_nama.setText(mData.get(position).getName());
        return convertView;
    }

    public class  ViewHolder {
        private TextView item_nama;

        ViewHolder(View view) {
            item_nama = view.findViewById(R.id.music_item_title);
        }
    }
}