package com.space.licht.envisiondemo.ui.activitys;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.ui.fragment.classification.SGAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.space.licht.envisiondemo.utils.SystemUtil.dp2px;

/**
 * Created by licht
 * 2017/8/2 0002.
 */
public class BlackListActivity extends BaseActivity {
    private static final String TAG = "BlackListActivity";
    @BindView(R.id.activity_black_list_back)
    ImageView mActivityBlackListBack;
    @BindView(R.id.activity_black_list_add_icon)
    ImageView mActivityBlackListAddIcon;
    @BindView(R.id.activity_Black_list_swipelv)
    SwipeMenuListView mActivityBlackListSwipelv;
    private List mSGDatas;
    private SGAdapter mSgAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_black_list);
        ButterKnife.bind(this);
        initDataI();
        initViewI();
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(BlackListActivity.this);
                // set item background
                openItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                // set item width
                openItem.setWidth(dp2px(80));
                // set item title
                openItem.setIcon(R.mipmap.left_slip_icon_edit);
                openItem.setTitle("Open");
                // set item title fontsize
                openItem.setTitleSize(18);
                // set item title font color
                openItem.setTitleColor(Color.GRAY);
                // add to menu
                menu.addMenuItem(openItem);

                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        BlackListActivity.this);
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
        mActivityBlackListSwipelv.setMenuCreator(creator);

    }

    /**
     * 初始化数据
     */
    private void initDataI() {
        mSGDatas = RealmHelper.getInstance().getCollectionList();
    }

    /**
     * 初始化控件
     */
    private void initViewI() {
        mSgAdapter = new SGAdapter(this, mSGDatas);
        mActivityBlackListSwipelv.setAdapter(mSgAdapter);
    }

    @OnClick({R.id.activity_black_list_back, R.id.activity_black_list_add_icon, R.id.activity_Black_list_swipelv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_black_list_back:
                break;
            case R.id.activity_black_list_add_icon:
                break;
            case R.id.activity_Black_list_swipelv:
                break;
        }
    }
}
