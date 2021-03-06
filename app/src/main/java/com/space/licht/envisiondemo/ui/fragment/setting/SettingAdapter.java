package com.space.licht.envisiondemo.ui.fragment.setting;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.fragment.classification.BaseSwipListAdapter;

import java.util.List;


/**
 * SGAdapter
 */
public class SettingAdapter extends BaseSwipListAdapter{
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<Collection> mDatas;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public SettingAdapter(Context context, List<Collection> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size()-1;
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i+1);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (null == view) {
            vh = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.setting_item, null);
            vh.mNameTv = (TextView) view.findViewById(R.id.setting_name);
            vh.mPetNmaeTv = (TextView) view.findViewById(R.id.setting_tel);
            vh.mHeadImg = (ImageView) view.findViewById(R.id.setting_image_head);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Collection bean = (Collection) getItem(position);
        if (null != bean) {
            vh.mNameTv.setText(bean.getNamed());
            vh.mPetNmaeTv.setText(bean.getTel());
            vh.mHeadImg.setImageResource(bean.getHeadImg());
        }

        return view;
    }


    /**
     * vh
     */
    class ViewHolder {
        /**
         * 姓名
         */
        TextView mNameTv;
        /**
         * 字
         */
        TextView mPetNmaeTv;
        /**
         * 头像
         */
        ImageView mHeadImg;

    }
}
