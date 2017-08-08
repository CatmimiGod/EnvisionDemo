package com.space.licht.envisiondemo.ui.fragment.member;


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
import com.space.licht.envisiondemo.app.App;
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
        mSGDatas = App.sData;
        if (mSGDatas.size() == 0) {
            //初始化假数据

            Collection bean6 = new Collection();
            bean6.setNamed("Unused");
            bean6.setId("Unused");
            bean6.setVoice(24);
            bean6.setDataTime(34);
            bean6.setUsedColor(0xff00a2ff);
            RealmHelper.getInstance().insertCollection(bean6);


            Collection bean5 = new Collection();
            bean5.setNamed("ipad");
            bean5.setId("ipad");
            bean5.setHeadImg(R.drawable.ipad);
            bean5.setTel("628-458-5869");
            bean5.setVoice(9);
            bean5.setDataTime(7);

            bean5.setTotalColor(0xfff2f7dd);
            bean5.setUsedColor(0xFFBDD757);
            bean5.setDataUsed(200);
            bean5.setVoiceUsed(320);
            RealmHelper.getInstance().insertCollection(bean5);


            Collection bean4 = new Collection();
            bean4.setNamed("Daughter");
            bean4.setId("Daughter");
            bean4.setHeadImg(R.drawable.daughter);
            bean4.setTel("628-458-2896");
            bean4.setVoice(12);
            bean4.setDataTime(13);
            bean4.setTotalColor(0xfffcf9de);
            bean4.setUsedColor(0xFFF1E15A);
            bean4.setDataUsed(220);
            bean4.setVoiceUsed(350);
            RealmHelper.getInstance().insertCollection(bean4);

            Collection bean3 = new Collection();
            bean3.setNamed("Son");
            bean3.setId("Son");
            bean3.setHeadImg(R.drawable.son);
            bean3.setTel("628-458-4826");
            bean3.setVoice(10);
            bean3.setDataTime(10);
            bean3.setTotalColor(0xfffff6de);
            bean3.setUsedColor(0xFFFBD05A);
            bean3.setDataUsed(200);
            bean3.setVoiceUsed(310);
            RealmHelper.getInstance().insertCollection(bean3);

            Collection bean = new Collection();
            bean.setNamed("Mother");
            bean.setId("Mother");
            bean.setHeadImg(R.drawable.mother);
            bean.setTel("628-458-5869");
            bean.setVoice(16);
            bean.setDataTime(16);

            bean.setTotalColor(0xfffeefdf);
            bean.setUsedColor(0xfffcad5d);
            bean.setDataUsed(380);
            bean.setVoiceUsed(420);
            RealmHelper.getInstance().insertCollection(bean);


            Collection bean2 = new Collection();
            bean2.setNamed("Father");
            bean2.setId("Father");
            bean2.setHeadImg(R.drawable.father);
            bean2.setTel("628-458-5876");
            bean2.setVoice(20);
            bean2.setDataTime(20);
            bean2.setTotalColor(0xfffee6de);
            bean2.setUsedColor(0xFFFA8358);
            bean2.setDataUsed(400);
            bean2.setVoiceUsed(700);
            RealmHelper.getInstance().insertCollection(bean2);
            mSGDatas = App.sData;
        }
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
