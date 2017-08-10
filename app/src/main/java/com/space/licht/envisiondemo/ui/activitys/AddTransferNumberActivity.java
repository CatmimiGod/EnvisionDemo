package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.model.bean.Collection;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class AddTransferNumberActivity extends BaseActivity {
    @BindView(R.id.add_transfer_name)
    EditText mAddTransferName;
    @BindView(R.id.home_tel_number)
    TextView mHomeTel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transfer_number);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_transfer_cancel, R.id.add_transfer_done})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_transfer_cancel:
                finish();
                break;
            case R.id.add_transfer_done:
                //获取文本数据保存
                Collection bean2 = new Collection();
                bean2.setNamed(mAddTransferName.getText().toString());
                bean2.setHeadImg(R.mipmap.fancy);
                bean2.setTel(mHomeTel.getText().toString());
                App.sOneNumberData.add(bean2);
                finish();
                break;
        }
    }
}
