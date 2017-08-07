package com.space.licht.envisiondemo.ui.fragment.member;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;

public class ProcessAdp extends BaseAdapter {
    private Context context;

    public ProcessAdp(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(
                    R.layout.item_order_process, null);
            holder.tv_tel = (TextView) convertView.findViewById(R.id.member_tel_number);
            holder.tv_time = (TextView) convertView.findViewById(R.id.member_time_date);
            holder.iv_icon = (ImageView) convertView.findViewById(R.id.member_process_iv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

    public static class ViewHolder {
        TextView tv_time;
        TextView tv_tel;
        ImageView iv_icon;
    }
}
