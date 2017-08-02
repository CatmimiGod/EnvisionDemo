package com.space.licht.envisiondemo.ui.activitys;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.ui.fragment.OneNumberAdapter;
import com.space.licht.envisiondemo.ui.fragment.classification.DataBean;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.space.licht.envisiondemo.utils.SystemUtil.dp2px;

/**
 * Created by licht
 * 2017/8/1 0001.
 */
public class OneNumberActivity extends BaseActivity {

    private static final String TAG = "OneNumberActivity";
    @BindView(R.id.activity_one_number_back)
    ImageView mActivityOneNumberBack;
    @BindView(R.id.activity_one_number_edit)
    TextView mActivityOneNumberEdit;
    @BindView(R.id.activity_one_number_relativelayout)
    RelativeLayout mActivityOneNumberRelativelayout;
    @BindView(R.id.activity_one_number)
    SwipeMenuListView mActivityOneNumber;
    @BindView(R.id.activity_one_number_add_more)
    LinearLayout mActivityOneNumberAddMore;

    private ArrayList mSGDatas;
    private OneNumberAdapter mSgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_number);
        ButterKnife.bind(this);

        initDataI();
        initViewI();
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        OneNumberActivity.this);
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(60));
                // set a icon
                deleteItem.setIcon(R.mipmap.left_slip_icon_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mActivityOneNumber.setMenuCreator(creator);
    }

    /**
     * 初始化数据
     */
    private void initDataI() {
        mSGDatas = new ArrayList<DataBean>();
        DataBean sgbean1 = new DataBean();
        sgbean1.setSgName("刘备");
        sgbean1.setSgPetName("玄德");
        sgbean1.setSgHeadBp(R.drawable.father);
        sgbean1.setSgDescribe("刘备");
        DataBean sgbean2 = new DataBean();
        sgbean2.setSgName("关羽");
        sgbean2.setSgPetName("云长");
        sgbean2.setSgHeadBp(R.drawable.father_large);
        sgbean2.setSgDescribe("关羽");
        DataBean sgbean3 = new DataBean();
        sgbean3.setSgName("张飞");
        sgbean3.setSgPetName("翼德");
        sgbean3.setSgHeadBp(R.drawable.mother);
        sgbean3.setSgDescribe("张飞");
        DataBean sgbean4 = new DataBean();
        sgbean4.setSgName("赵云");
        sgbean4.setSgPetName("子龙");
        sgbean4.setSgHeadBp(R.drawable.son);
        sgbean4.setSgDescribe("赵云");
        DataBean sgbean5 = new DataBean();
        sgbean5.setSgName("马超");
        sgbean5.setSgPetName("孟起");
        sgbean5.setSgHeadBp(R.drawable.daughter);
        sgbean5.setSgDescribe("马超");
        mSGDatas.add(sgbean1);
        mSGDatas.add(sgbean2);
        mSGDatas.add(sgbean3);
        mSGDatas.add(sgbean4);
        mSGDatas.add(sgbean5);
    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        mSgAdapter = new OneNumberAdapter(this, mSGDatas);
        mActivityOneNumber.setAdapter(mSgAdapter);
    }


    @OnClick({R.id.activity_one_number_add_more})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.activity_one_number:
                Log.e(TAG, "onItemClick: ");
                break;
            case R.id.activity_one_number_add_more:
                //todo add more
                JumpUtil.jump(this,AddTransferNumberActivity.class);
                break;
        }
    }
}
