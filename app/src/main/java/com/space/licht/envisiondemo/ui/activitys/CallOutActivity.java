package com.space.licht.envisiondemo.ui.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.ui.fragment.CallOutAdapter;
import com.space.licht.envisiondemo.ui.fragment.setting.TimeBean;
import com.space.licht.envisiondemo.utils.JumpUtil;
import com.space.licht.envisiondemo.utils.PreUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class CallOutActivity extends BaseActivity {

    @BindView(R.id.activity_call_out_back)
    ImageView mActivityCallOutBack;
    @BindView(R.id.activity_call_out_edit)
    TextView mActivityCallOutEdit;
    @BindView(R.id.activity_call_out_listview)
    ListView mActivityCallOutListview;
    @BindView(R.id.activity_call_out_relativelayout)
    RelativeLayout mActivityCallOutRelativelayout;

    private CallOutAdapter mCallOutAdapter;
    private boolean isEdit = false;
    private List<TimeBean> list = App.sTimeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_out);
        ButterKnife.bind(this);

        mCallOutAdapter = new CallOutAdapter(this, list, isEdit);
        mActivityCallOutListview.setAdapter(mCallOutAdapter);
        mActivityCallOutListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isEdit) {
                    Intent intent = new Intent(CallOutActivity.this,  EditActivity.class);
                    intent.putExtra("selected",position+"");
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        String time = PreUtils.getString(this, "time", "");
        if (!time.isEmpty()) {
            String[] split = time.split("-");
            if (split.length > 3) {
                String ss = split[1] + "~" + split[3];
//                list.add(ss);
            }
        }
        mCallOutAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.activity_call_out_back, R.id.activity_call_out_edit, R.id.activity_call_out_add_more})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_call_out_back:
                finish();
                break;
            case R.id.activity_call_out_edit:
                //todo edit
                if (isEdit) {
                    isEdit = false;
                    mCallOutAdapter.setIsEdit(isEdit);
                    mCallOutAdapter.notifyDataSetChanged();
                } else {
                    isEdit = true;
                    mCallOutAdapter.setIsEdit(isEdit);
                    mCallOutAdapter.notifyDataSetChanged();
                }

                break;
            case R.id.activity_call_out_add_more:
                //Todo add more
                JumpUtil.jump(this, AddMoreActivity.class);
                break;
        }
    }
}
