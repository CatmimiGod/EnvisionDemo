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
import com.space.licht.envisiondemo.widget.CustomListView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StoreAdapter extends BaseAdapter {

    private final LinkedList<Boolean> selected = new LinkedList<Boolean>();

    private LayoutInflater inflater;
    List<ProductAdapter> pAdapterList = new ArrayList<ProductAdapter>();
    Context context;

    public StoreAdapter(Context context, List<Store> list) {
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        for (int i = 0; i < 5; i++) {
            getSelect().add(false);
            ProductAdapter pAdapter = new ProductAdapter(context,
                    this, i);
            pAdapterList.add(pAdapter);
        }
    }

    public List<Boolean> getSelect() {
        return selected;
    }

    public ProductAdapter getPAdapter(int position) {
        return pAdapterList.get(position);
    }

    public List<ProductAdapter> getPAdapterList() {
        return pAdapterList;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.store_item, null);

            holder = new ViewHolder();
            holder.cb_select = (CheckBox) convertView.findViewById(R.id.cb_select);
            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.lv_product = (CustomListView) convertView.findViewById(R.id.lv_product);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.lv_product.setAdapter(pAdapterList.get(position));


        holder.tv_content.setText("Select all");

        holder.cb_select.setChecked(selected.get(position));
        holder.cb_select.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                System.out.println("selected set position:" + position);
                selected.set(position, !selected.get(position));
                for (int i = 0; i < pAdapterList.get(position).getSelect().size(); i++) {
                    pAdapterList.get(position).getSelect().set(i, selected.get(position));
                    System.out.println("selected " + i + ":" + pAdapterList.get(position).getSelect().get(i));
                }
                notifyDataSetChanged();
            }
        });

        return convertView;
    }

    @Override
    public int getCount() {
        return 5 ;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        public CheckBox cb_select;
        public TextView tv_content;
        public CustomListView lv_product;
    }
}
