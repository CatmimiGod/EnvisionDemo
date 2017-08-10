package com.space.licht.envisiondemo.ui.fragment.chart;

import android.os.Bundle;
import android.support.v7.widget.CardView;
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
public class VoiceFragment extends BaseFragment {
    public static final String SET_THEME = "SET_THEME";
    @BindView(R.id.cardview)
    CardView mCardview;
    @BindView(R.id.chart_pie)
    PieChart mChartPie;
    @BindView(R.id.chart_wave)
    MySinkingView mChartWave;
    @BindView(R.id.chart_barchart)
    BarChart mChartBarchart;
    private List<Collection> mSGDatas;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);

        mSGDatas = RealmHelper.getInstance().getCollectionList();

        mChartPie.setDataList(mSGDatas);

        mChartPie.setOnItemPieClickListener(new PieChart.OnItemPieClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        float percent = 1-mSGDatas.get(0).getVoice()/100f;
        mChartWave.setPercent(percent);


        mChartBarchart.setData(mSGDatas);
        mChartBarchart.setOnItemBarClickListener(new BarChart.OnItemBarClickListener() {
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


    public void updataVoicePage(List<Collection> data) {
        mChartPie.setDataList(data);
        mChartWave.setPercent(1-data.get(0).getVoice()/100f);
        mChartBarchart.setData(data);
    }
}
