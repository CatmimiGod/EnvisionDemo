package com.space.licht.envisiondemo.ui.fragment.chart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseFragment;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.db.RealmHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 */
public class DataFragment extends BaseFragment {
    public static final String SET_THEME = "SET_THEME";
    @BindView(R.id.data_pie)
    PieChart mDataPie;
    @BindView(R.id.data_wave)
    MySinkingView mDataWave;
    @BindView(R.id.data_barchart)
    BarChart mDataBarchart;

    private int[] mColors = {0x00a2ff, 0x666666, 0x999999, 0xFF800000, 0xFFFBD05A, 0xFFFA8358, 0x00a2ff, 0x666666, 0x999999, 0xFF800000, 0xFFFBD05A, 0xFFFA8358};
    private List<Collection> mSGDatas;

    @Override
    protected int getLayout() {
        return R.layout.fragment_data;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mSGDatas = RealmHelper.getInstance().getCollectionList();
        mDataPie.setDataList(mSGDatas);

        mDataPie.setOnItemPieClickListener(new PieChart.OnItemPieClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        float percent = 0.56f;
        mDataWave.setPercent(percent);

        mDataBarchart.setData(mSGDatas);
        mDataBarchart.setOnItemBarClickListener(new BarChart.OnItemBarClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "点击了：" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


}