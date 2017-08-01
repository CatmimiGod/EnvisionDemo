package com.space.licht.envisiondemo.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;

import java.util.ArrayList;
import java.util.List;

public class ProductAdapter extends BaseAdapter {

    private final List<Boolean> selected = new ArrayList<Boolean>();

    private LayoutInflater inflater;
    private List<Product> list;
    StoreAdapter adapter;
    int storePosition;
    Context context;

    public ProductAdapter(Context context, List<Product> list, StoreAdapter adapter, int storePosition) {
        this.inflater = LayoutInflater.from(context);
        this.list = list;
        this.adapter = adapter;
        this.storePosition = storePosition;
        this.context = context;
        for (int j = 0; j < list.size(); j++) {
            getSelect().add(false);
        }
    }

    public List<Boolean> getSelect() {
        return selected;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.product_item, null);

            holder = new ViewHolder();
            holder.cb_select = (CheckBox) convertView.findViewById(R.id.cb_select);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Product product = list.get(position);
        holder.tv_content.setText("Mother");


        holder.cb_select.setChecked(selected.get(position));
        holder.cb_select.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("selected set position:" + position);
                selected.set(position, !selected.get(position));

                if (selected.contains(false)) {
                    adapter.getSelect().set(storePosition, false);
                } else {
                    adapter.getSelect().set(storePosition, true);
                }
                adapter.notifyDataSetChanged();
            }
        });


        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        CheckBox cb_select;
        TextView tv_content;
    }
}
