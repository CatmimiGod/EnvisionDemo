package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class BlockCellularDataActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_block_cellular_data);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.activity_Block_cellular_data_back)
    public void onClick() {
        finish();
    }
}
