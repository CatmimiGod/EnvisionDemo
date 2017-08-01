package com.space.licht.envisiondemo.ui.adapter.viewholder;

import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.component.ImageLoader;
import com.space.licht.envisiondemo.model.bean.VideoInfo;


/**
 * Description: RecommendViewHolder
 */

public class RecommendViewHolder extends BaseViewHolder<VideoInfo> {
    ImageView imgPicture;
    TextView tv_title;

    public RecommendViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_video);
        imgPicture = $(R.id.img_video);
        tv_title = $(R.id.tv_title);
        imgPicture.setScaleType(ImageView.ScaleType.CENTER_CROP);
    }

    @Override
    public void setData(VideoInfo data) {
        tv_title.setText(data.title);
        ImageLoader.load(getContext(),data.pic,imgPicture);
    }
}
