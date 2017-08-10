package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.view.View;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class AddMoreActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_member_cancel, R.id.add_member_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_member_cancel:
            case R.id.add_member_done:
                finish();
                break;
        }
    }
}
