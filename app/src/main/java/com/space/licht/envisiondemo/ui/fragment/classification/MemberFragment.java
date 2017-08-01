package com.space.licht.envisiondemo.ui.fragment.classification;


import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.space.licht.envisiondemo.utils.SystemUtil.dp2px;


/**
 * Description: MemberFragment
 */
public class MemberFragment extends Fragment {

    private static final String TAG = "classfication";

    @BindView(R.id.lv_show)
    SwipeMenuListView mLvShow;
    @BindView(R.id.member_add_icon)
    ImageView mMemberAddIcon;
    private ArrayList<DataBean> mSGDatas;
    private SGAdapter sgAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View mView = inflater.inflate(R.layout.fragment_classification_view, null);
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initDataI();
        initViewI();
        // step 1. create a MenuCreator
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // create "open" item
                SwipeMenuItem openItem = new SwipeMenuItem(
                        getContext());
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
                        getContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
                // set item width
                deleteItem.setWidth(dp2px(40));
                // set a icon
                deleteItem.setIcon(R.mipmap.left_slip_icon_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        // set creator
        mLvShow.setMenuCreator(creator);

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
        sgAdapter = new SGAdapter(getContext(), mSGDatas);
        mLvShow.setAdapter(sgAdapter);

        mLvShow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: ");
            }
        });
    }

    @OnClick(R.id.member_add_icon)
    public void onClick() {
        JumpUtil.go2AddMemberActivity(getContext());
    }
}
