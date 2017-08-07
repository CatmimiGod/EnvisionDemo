package com.space.licht.envisiondemo.ui.fragment.member;

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
import com.space.licht.envisiondemo.widget.InnerListview;

import java.util.List;


/**
 * SGAdapter
 */
public class SGAdapter extends BaseSwipListAdapter {
    private static final String TAG = "SGAdapter";
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<Collection> mDatas;
    private boolean isClose = false;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public SGAdapter(Context context, List<Collection> datas) {
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
            view = mInflater.inflate(R.layout.sg_item, null);
            vh.mNameTv = (TextView) view.findViewById(R.id.tv_sgname);
            vh.mSgIV = (ImageView) view.findViewById(R.id.sgadpter_iv);
            vh.mLinearLaout1 = (LinearLayout) view.findViewById(R.id.member_ll);
            vh.mRecentLinearLaout = (LinearLayout) view.findViewById(R.id.recents_calls_ll);
            vh.mPetNmaeTv = (TextView) view.findViewById(R.id.tv_sgpetname);
            vh.mHeadImg = (ImageView) view.findViewById(R.id.iv_sghead);
            vh.mListview = (InnerListview) view.findViewById(R.id.lv_start_aoc);
            vh.mLinearLaout2 = (LinearLayout) view.findViewById(R.id.ll_start_inform_aviation_content);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Collection bean = (Collection) getItem(position);
        if (null != bean) {
            vh.mNameTv.setText(bean.getNamed());
            vh.mPetNmaeTv.setText(bean.getTel());
            vh.mHeadImg.setImageResource(bean.getHeadImg());
            ProcessAdp adp = new ProcessAdp(mContext);
            vh.mListview.setAdapter(adp);

            final ViewHolder finalVh = vh;
            vh.mLinearLaout2.setVisibility(View.GONE);
            vh.mRecentLinearLaout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isClose) {
                        finalVh.mSgIV.setImageResource(R.drawable.icon_arrow_right);
                        finalVh.mLinearLaout2.setVisibility(View.GONE);
                        isClose = false;
                    } else {
                        isClose = true;
                        finalVh.mSgIV.setImageResource(R.drawable.icon_arrow_down);
                        finalVh.mLinearLaout2.setVisibility(View.VISIBLE);
                    }
                }
            });

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
        TextView mRecentTv;
        /**
         * 头像
         */
        ImageView mHeadImg;

        InnerListview mListview;

        LinearLayout mLinearLaout1;
        LinearLayout mLinearLaout2;
        LinearLayout mRecentLinearLaout;
    }

}
