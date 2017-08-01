package com.space.licht.envisiondemo.ui.fragment.classification;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.widget.InnerListview;

import java.util.ArrayList;
import java.util.List;


/**
 * SGAdapter
 */
public class SGAdapter extends BaseSwipListAdapter{
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<DataBean> mDatas;
    private boolean isClose = false;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public SGAdapter(Context context, List<DataBean> datas) {
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
            vh.mPetNmaeTv = (TextView) view.findViewById(R.id.tv_sgpetname);
            vh.mHeadImg = (ImageView) view.findViewById(R.id.iv_sghead);
            vh.mListview = (InnerListview) view.findViewById(R.id.lv_start_aoc);
            vh.mLinearLaout2 = (LinearLayout) view.findViewById(R.id.ll_start_inform_aviation_content);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        DataBean bean = (DataBean) getItem(position);
        if (null != bean) {
            vh.mNameTv.setText(bean.getSgName());
            vh.mPetNmaeTv.setText(bean.getSgPetName());
            vh.mHeadImg.setImageResource(bean.getSgHeadBp());
//            if (position == 1){
                initDatas();
                ProcessAdp adp = new ProcessAdp(mContext, mTimeList,
                        mContentList);
                vh.mListview.setAdapter(adp);
//            }

            final ViewHolder finalVh = vh;
            vh.mSgIV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (isClose) {
                        finalVh.mSgIV.setImageResource(R.drawable.icon_arrow_down);
                        finalVh.mLinearLaout2.setVisibility(View.VISIBLE);
                        isClose = false;
                    }else{
                        isClose = true;
                        finalVh.mSgIV.setImageResource(R.drawable.icon_arrow_right);
                        finalVh.mLinearLaout2.setVisibility(View.GONE);
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
        /**
         * 头像
         */
        ImageView mHeadImg;

        InnerListview mListview;

        LinearLayout mLinearLaout1;
        LinearLayout mLinearLaout2;
    }

    private List<String> mTimeList = new ArrayList<String>();
    private List<String> mContentList = new ArrayList<String>();

    private void initDatas() {

        mContentList.clear();
        mContentList.add("希望此demo对您有帮助");
        mContentList.add("欢迎下载");
        mContentList.add("请提出更好的建议");
        mContentList.add("如果能够修改更好的请更新到github上面");
        mContentList.add("欢迎前去本人的csdn技术博客");
        mContentList.add("欢迎前去本人的github");
        mContentList.add("谢谢您的配合！！");

        mTimeList.clear();
        mTimeList.add("2016-07-18 09:23:14");
        mTimeList.add("2016-07-19 09:23:14");
        mTimeList.add("2016-07-20 09:23:14");
        mTimeList.add("2016-07-21 09:23:14");
        mTimeList.add("2016-07-22 09:23:14");
        mTimeList.add("2016-07-23 09:23:14");
        mTimeList.add("2016-07-25 09:23:14");

    }
}
