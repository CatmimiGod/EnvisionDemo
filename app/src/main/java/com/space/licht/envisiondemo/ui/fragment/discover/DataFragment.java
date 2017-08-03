package com.space.licht.envisiondemo.ui.fragment.discover;

import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseFragment;
import com.space.licht.envisiondemo.ui.fragment.BarChart;
import com.space.licht.envisiondemo.ui.fragment.ChartEntity;
import com.space.licht.envisiondemo.ui.fragment.MySinkingView;
import com.space.licht.envisiondemo.ui.fragment.PieChart;
import com.space.licht.envisiondemo.ui.fragment.PieDataEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 */
public class DataFragment extends BaseFragment {
    public static final String SET_THEME = "SET_THEME";
    @BindView(R.id.cardview)
    CardView mCardview;
    @BindView(R.id.chart_pie)
    PieChart mChartPie;
    @BindView(R.id.chart_wave)
    MySinkingView mChartWave;
    @BindView(R.id.chart_barchart)
    BarChart mChartBarchart;
    private int[] mColors = {0x00a2ff, 0x666666, 0x999999, 0xFF800000, 0xFFFBD05A, 0xFFFA8358, 0x00a2ff, 0x666666, 0x999999, 0xFF800000, 0xFFFBD05A, 0xFFFA8358};

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        List<PieDataEntity> dataEntities = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            PieDataEntity entity = new PieDataEntity("name" + i, i + 1, mColors[i]);
            dataEntities.add(entity);
        }
        mChartPie.setDataList(dataEntities);

        mChartPie.setOnItemPieClickListener(new PieChart.OnItemPieClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getActivity(), "点击了" + position, Toast.LENGTH_SHORT).show();
            }
        });
        float percent = 0.56f;
        mChartWave.setPercent(percent);

        List<ChartEntity> data = new ArrayList<>();
//        for (int i = 0; i < 5; i++) {
//            data.add(new ChartEntity(String.valueOf(i), (float) (Math.random() * 700)));
//        }
        mChartBarchart.setData(data);
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


}
