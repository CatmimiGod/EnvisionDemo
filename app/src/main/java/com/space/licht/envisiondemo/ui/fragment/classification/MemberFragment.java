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
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.db.RealmHelper;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.List;

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
    private List<Collection> mSGDatas;
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
                deleteItem.setWidth(dp2px(60));
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
//初始化假数据
//        Collection bean = new Collection();
//        bean.setNamed("Mother");
//        bean.setHeadImg(R.drawable.mother);
//        bean.setTel("628-458-5869");
//        bean.setVoice(15);
//        bean.setDataTime(20);
//        RealmHelper.getInstance().insertCollection(bean);
//        Collection bean2 = new Collection();
//        bean2.setNamed("Father");
//        bean2.setHeadImg(R.drawable.father);
//        bean2.setTel("628-458-5876");
//        bean2.setVoice(30);
//        bean2.setDataTime(30);
//        RealmHelper.getInstance().insertCollection(bean2);
//        Collection bean3 = new Collection();
//        bean3.setNamed("Son");
//        bean3.setHeadImg(R.drawable.son);
//        bean3.setTel("628-458-4826");
//        bean3.setVoice(20);
//        bean3.setDataTime(15);
//        RealmHelper.getInstance().insertCollection(bean3);
//        Collection bean4 = new Collection();
//        bean4.setNamed("Daughter");
//        bean4.setHeadImg(R.drawable.daughter);
//        bean4.setTel("628-458-2896");
//        bean4.setVoice(15);
//        bean4.setDataTime(18);
//        RealmHelper.getInstance().insertCollection(bean4);
//        Collection bean5 = new Collection();
//        bean5.setNamed("Pad");
//        bean5.setHeadImg(R.drawable.ipad);
//        bean5.setTel("628-458-5869");
//        bean5.setVoice(0);
//        bean5.setDataTime(0);
//        RealmHelper.getInstance().insertCollection(bean5);
        mSGDatas = RealmHelper.getInstance().getCollectionList();
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
