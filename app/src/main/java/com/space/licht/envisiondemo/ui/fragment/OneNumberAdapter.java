package com.space.licht.envisiondemo.ui.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.fragment.classification.BaseSwipListAdapter;

import java.util.List;


/**
 * SGAdapter
 */
public class OneNumberAdapter extends BaseSwipListAdapter{
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
    public OneNumberAdapter(Context context, List<Collection> datas) {
        mContext = context;
        mDatas = datas;
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
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
            view = mInflater.inflate(R.layout.one_number_item, null);
            vh.mNameTv = (TextView) view.findViewById(R.id.tv_sgname);
            vh.mSgIV = (ImageView) view.findViewById(R.id.sgadpter_iv);
            vh.mLinearLaout1 = (LinearLayout) view.findViewById(R.id.member_ll);
            vh.mPetNmaeTv = (TextView) view.findViewById(R.id.tv_sgpetname);
            vh.mHeadImg = (ImageView) view.findViewById(R.id.iv_sghead);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Collection bean = (Collection) getItem(position);
        if (null != bean) {
            vh.mNameTv.setText(bean.getNamed());
            vh.mPetNmaeTv.setText(bean.getTel());
            vh.mHeadImg.setImageResource(bean.getHeadImg());
            final ViewHolder finalVh = vh;
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
        ImageView mSgIV;
        /**
         * 字
         */
        TextView mPetNmaeTv;
        /**
         * 头像
         */
        ImageView mHeadImg;


        LinearLayout mLinearLaout1;
    }

}
