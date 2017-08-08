package com.space.licht.envisiondemo.ui.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.ui.fragment.CallOutAdapter;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.ArrayList;
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

    private String[] timeDatas = {"23:00~06:00", "24:00~08:00", "01:00~10:00", "23:00~07:00", "22:00~09:00", "02:00~06:00"};
    private CallOutAdapter mCallOutAdapter;
    private boolean isEdit = false;
    private List<String> list = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_out);
        ButterKnife.bind(this);
        for (int i = 0; i < timeDatas.length; i++) {
            list.add(timeDatas[i]);
        }
        mCallOutAdapter = new CallOutAdapter(this, list, isEdit);
        mActivityCallOutListview.setAdapter(mCallOutAdapter);
        mActivityCallOutListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (isEdit){
                    JumpUtil.jump(CallOutActivity.this,EditActivity.class);
                }
            }
        });
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
