package com.space.licht.envisiondemo.ui.fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.ui.fragment.classification.BaseSwipListAdapter;
import com.space.licht.envisiondemo.ui.fragment.setting.TimeBean;
import com.space.licht.envisiondemo.widget.view.ToggleButton;

import java.util.List;


/**
 * SGAdapter
 */
public class CallOutAdapter extends BaseSwipListAdapter {
    private static final String TAG = "CallOutAdapter";
    /**
     * 上下文
     */
    private Context mContext;

    /**
     * 数据源
     */
    private List<TimeBean> mDatas;
    private boolean mIsEdit;

    /**
     * 构造函数
     *
     * @param context
     * @param datas
     */
    public CallOutAdapter(Context context, List<TimeBean> datas, boolean isEdit) {
        mContext = context;
        mDatas = datas;
        mIsEdit = isEdit;
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
    public View getView(final int position, View view, ViewGroup viewGroup) {
        ViewHolder vh = null;
        if (null == view) {
            vh = new ViewHolder();
            LayoutInflater mInflater = LayoutInflater.from(mContext);
            view = mInflater.inflate(R.layout.call_out_time_item, null);
            vh.date = (TextView) view.findViewById(R.id.call_out_date);
            vh.time = (TextView) view.findViewById(R.id.call_out_time);
            vh.delete = (ImageView) view.findViewById(R.id.call_out_delete);
            vh.arrowRight = (ImageView) view.findViewById(R.id.call_out_arrow_right);
            vh.mToggleButton = (ToggleButton) view.findViewById(R.id.call_out_togglebutton);

            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.time.setText(mDatas.get(position).getsStartHour()+":"+mDatas.get(position).getsStartMins()+"~"+
                mDatas.get(position).getsStopHour()+":"+mDatas.get(position).getsStopMins());
        vh.date.setText(mDatas.get(position).getsStartDay() + "~" +mDatas.get(position).getsStopDay() );

        if (mIsEdit) {
            vh.delete.setVisibility(View.VISIBLE);
            vh.arrowRight.setVisibility(View.VISIBLE);
            vh.mToggleButton.setVisibility(View.GONE);
        } else {
            vh.delete.setVisibility(View.GONE);
            vh.arrowRight.setVisibility(View.GONE);
            vh.mToggleButton.setVisibility(View.VISIBLE);
        }

        vh.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mDatas.remove(position);
                notifyDataSetChanged();
            }
        });

        final ViewHolder finalVh = vh;
        vh.mToggleButton.setOnToggleChanged(new ToggleButton.OnToggleChanged() {
            @Override
            public void onToggle(boolean on) {
                if (on) {
                    Log.e(TAG, "onToggle: " + position);
                    finalVh.time.setTextColor(0xff333333);

                } else {
                    Log.e(TAG, "onToggle: " + position);
                    finalVh.time.setTextColor(0xff999999);
                }
            }
        });


        if (position % 2 == 0) {
            vh.date.setText("Tuesday,Wednesday");
            vh.time.setTextColor(0xff333333);
            vh.mToggleButton.setToggleOn();
        }
        return view;
    }


    /**
     * vh
     */
    class ViewHolder {
        public TextView date;
        public TextView time;
        public ToggleButton mToggleButton;
        public ImageView delete;
        public ImageView arrowRight;
    }

    public void setIsEdit(boolean b) {
        mIsEdit = b;
    }

}
