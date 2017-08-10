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
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.fragment.OneNumberAdapter;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.List;

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

    private OneNumberAdapter mSgAdapter;
    private List<Collection> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_number);
        ButterKnife.bind(this);

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
     * 初始化控件
     */
    private void initViewI() {
        mData = App.sOneNumberData;
        mSgAdapter = new OneNumberAdapter(this, mData);
        mActivityOneNumber.setAdapter(mSgAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mData = App.sOneNumberData;
        mSgAdapter = new OneNumberAdapter(this, mData);
        mActivityOneNumber.setAdapter(mSgAdapter);
    }

    @OnClick({R.id.activity_one_number_add_more,R.id.activity_one_number_back,R.id.activity_one_number_edit})
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.activity_one_number_back:
                Log.e(TAG, "onItemClick: ");
                finish();
                break;
            case R.id.activity_one_number_edit:
                Log.e(TAG, "onItemClick: ");
                finish();
                break;
            case R.id.activity_one_number_add_more:
                //todo add more
                JumpUtil.jump(this,AddTransferNumberActivity.class);
                break;
        }
    }
}
