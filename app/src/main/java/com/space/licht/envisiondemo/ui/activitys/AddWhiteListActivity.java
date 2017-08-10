package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
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
 * 2017/7/31 0031.
 */

public class AddWhiteListActivity extends BaseActivity {

    @BindView(R.id.add_member_cancel)
    TextView mAddMemberCancel;
    @BindView(R.id.add_member_done)
    TextView mAddMemberDone;
    @BindView(R.id.add_member_photo)
    ImageView mAddMemberPhoto;
    @BindView(R.id.add_member_name)
    TextView mAddMemberName;
    @BindView(R.id.add_member_telmember)
    TextView mAddMemberTelmember;
    @BindView(R.id.add_member_privatenumber)
    TextView mAddMemberPrivatenumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addmember);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.add_member_cancel, R.id.add_member_done, R.id.add_member_photo, R.id.add_member_name, R.id.add_member_telmember, R.id.add_member_privatenumber})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_member_cancel:
                finish();
                break;
            case R.id.add_member_done:
                //获取文本数据保存
                Collection bean2 = new Collection();
                bean2.setNamed(mAddMemberName.getText().toString());
                bean2.setHeadImg(R.mipmap.fancy);
                bean2.setTel(mAddMemberTelmember.getText().toString());
                App.sWhitelistData.add(bean2);
                finish();
                break;
            case R.id.add_member_photo:
                break;
            case R.id.add_member_name:
                break;
            case R.id.add_member_telmember:
                break;
            case R.id.add_member_privatenumber:
                break;
        }
    }
}
