package com.space.licht.envisiondemo.ui.fragment.community;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.ui.fragment.DensityUtil;
import com.space.licht.envisiondemo.ui.fragment.Model;
import com.space.licht.envisiondemo.ui.fragment.classification.BaseSwipListAdapter;

import java.text.DecimalFormat;
import java.util.List;

import static android.content.ContentValues.TAG;


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
    private List<Collection> mDatas;

    boolean mIsgone = false;
    private final int width;


    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public CommunityAdapter(Context context, List<Collection> datas, boolean isgone, Handler handler) {
        mContext = context;
        mDatas = datas;
        mIsgone = isgone;
        width = DensityUtil.dip2px(mContext, 265);
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
            vh.dataAllocationAmount = (TextView) view.findViewById(R.id.data_allocation_amount);
            vh.voiceAllocationAmount = (TextView) view.findViewById(R.id.voice_allocation_amount);
            vh.dataPercent = (TextView) view.findViewById(R.id.data_percent);
            vh.voicePercent = (TextView) view.findViewById(R.id.voice_percent);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        final Collection bean = (Collection) getItem(position);
        if (null != bean) {
            vh.name.setText(bean.getNamed());
            vh.tel.setText(bean.getTel());
            vh.mHeadImg.setImageResource(bean.getHeadImg());
            dataMove(bean.getDataTime(),vh,bean);
            vh.mDataSeekBar.setProgress(bean.getDataTime());
            voiceMove(bean.getVoice(),vh,bean);
            vh.mVoiceSeekBar.setProgress(bean.getVoice());
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
                dataMove(progress, finalVh , bean);
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
                voiceMove(progress,finalVh,bean);
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

    private void dataMove(final int progress, final ViewHolder vh , final Collection bean ) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //每一段要移动的距离
                final float scrollDistance = (float) ((1.0 / 100) * width);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        vh.dataProportion.setText(progress + "%");

                        float text = progress;
                        float right = (text/100)*3200;
                        DecimalFormat df = new DecimalFormat("#0.0");
                        String format = df.format(right);
                        Log.e(TAG, "text : " + text );
                        vh.dataAllocationAmount.setText(format+"G");
                        vh.dataProportion.setTranslationX(progress * scrollDistance);
                        vh.dataPercent.setText("Data   : " + progress + "%");
                        bean.setDataTime(progress);
                        Log.e(TAG, "id data: " + bean.getNamed() );
                        RealmHelper.getInstance().updateDataCollection(bean.getNamed(),progress);
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

    private void voiceMove(final int progress, final ViewHolder vh, final Collection bean) {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                //每一段要移动的距离
                final float scrollDistance = (float) ((1.0 / 100) * width);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        vh.voiceProportion.setText(progress + "%");
                        float text = progress;
                        float right = (text/100)*75;
                        DecimalFormat df = new DecimalFormat("#0.0");
                        String format = df.format(right);
                        vh.voiceAllocationAmount.setText(format+"Hours");
                        vh.voiceProportion.setTranslationX(progress * scrollDistance);
                        vh.voicePercent.setText("Voice  : " + progress + "%");
                        bean.setVoice(progress);
                        Log.e(TAG, "id: voice " + bean.getNamed() );
                        RealmHelper.getInstance().updateVoiceCollection(bean.getNamed(),progress);
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

        public TextView dataAllocationAmount;
        public TextView voiceAllocationAmount;
        //百分比
        public TextView dataPercent;
        public TextView voicePercent;
    }

    public void setIsgone(boolean b) {
        mIsgone = b;
    }


}