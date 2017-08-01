package com.space.licht.envisiondemo.ui.fragment;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.ui.fragment.classification.BaseSwipListAdapter;
import com.space.licht.envisiondemo.ui.fragment.classification.DataBean;

import java.util.List;


/**
 * SGAdapter
 */
public class CommunityAdapter extends BaseSwipListAdapter {

    private final Handler mHandler;
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<DataBean> mDatas;

    boolean mIsgone = false;
    private final int width;


    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public CommunityAdapter(Context context, List<DataBean> datas, boolean isgone, Handler handler) {
        mContext = context;
        mDatas = datas;
        mIsgone = isgone;
        width = DensityUtil.dip2px(mContext, 270);
        mHandler = handler;
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
            view = mInflater.inflate(R.layout.community_item, null);
            vh.name = (TextView) view.findViewById(R.id.community_name);
            vh.tel = (TextView) view.findViewById(R.id.community_tel);
            vh.mHeadImg = (ImageView) view.findViewById(R.id.iv_sghead);
            vh.llGone = (LinearLayout) view.findViewById(R.id.community_progress_gone);
            vh.mDataSeekBar = (SeekBar) view.findViewById(R.id.data_allocation_seekbar);
            vh.mVoiceSeekBar = (SeekBar) view.findViewById(R.id.voice_allocation_seekbar);
            vh.dataProportion = (TextView) view.findViewById(R.id.data_allocation_proportion);
            vh.voiceProportion = (TextView) view.findViewById(R.id.voice_allocation_proportion);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        DataBean bean = (DataBean) getItem(position);
        if (null != bean) {
            vh.name.setText(bean.getSgName());
            vh.tel.setText(bean.getSgPetName());
            vh.mHeadImg.setImageResource(bean.getSgHeadBp());
        }

        if (mIsgone) {
            vh.llGone.setVisibility(View.GONE);
        } else {
            vh.llGone.setVisibility(View.VISIBLE);
        }

        final ViewHolder finalVh = vh;
        SeekBar.OnSeekBarChangeListener dataSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                dataMove(progress, finalVh);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        SeekBar.OnSeekBarChangeListener voiceSeekBarListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                voiceMove(progress,finalVh);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };
        vh.mDataSeekBar.setOnSeekBarChangeListener(dataSeekBarListener);
        vh.mVoiceSeekBar.setOnSeekBarChangeListener(voiceSeekBarListener);
        return view;
    }

    private void dataMove(final int progress, final ViewHolder vh) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //每一段要移动的距离
                final float scrollDistance = (float) ((1.0 / 100) * width);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        vh.dataProportion.setText(progress + "%");
                        vh.dataProportion.setTranslationX(progress * scrollDistance);
                    }
                });
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void voiceMove(final int progress, final ViewHolder vh) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //每一段要移动的距离
                final float scrollDistance = (float) ((1.0 / 100) * width);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        vh.voiceProportion.setText(progress + "%");
                        vh.voiceProportion.setTranslationX(progress * scrollDistance);
                    }
                });
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    /**
     * vh
     */
    class ViewHolder {
        /**
         * 姓名
         */
        TextView name;
        /**
         * 电话
         */
        TextView tel;
        /**
         * 头像
         */
        ImageView mHeadImg;

        //隐藏的ll
        LinearLayout llGone;
        //data seekbar
        SeekBar mDataSeekBar;
        //voice seekbar
        SeekBar mVoiceSeekBar;

        //比例
        TextView dataProportion;
        TextView voiceProportion;
    }

    public void setIsgone(boolean b) {
        mIsgone = b;
    }


}
