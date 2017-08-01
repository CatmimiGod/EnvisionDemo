package com.space.licht.envisiondemo.ui.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.space.licht.envisiondemo.model.bean.VideoInfo;
import com.space.licht.envisiondemo.ui.adapter.viewholder.ClassificationViewHolder;

/**
 * Description: 专题
 */
public class ClassificationAdapter extends RecyclerArrayAdapter<VideoInfo> {

    public ClassificationAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassificationViewHolder(parent);
    }

}
