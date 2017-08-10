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
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.model.bean.Collection;
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
    private MemberListAdapter sgAdapter;

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
        mLvShow.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        // open
                        Toast.makeText(getActivity(),"Temporarily not opened",Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        // delete
                        Toast.makeText(getActivity(),"Temporarily not opened",Toast.LENGTH_SHORT).show();
//                        App.sData.remove(index);
//                        mSGDatas = App.sData;
//                        sgAdapter = new MemberListAdapter(getContext(), mSGDatas);
//                        mLvShow.setAdapter(sgAdapter);
                        break;
                }
                // false : close the menu; true : not close the menu
                return false;
            }
        });

    }

    /**
     * 初始化数据
     */
    private void initDataI() {
        mSGDatas = App.sData;
    }

    @Override
    public void onResume() {
        super.onResume();
        mSGDatas = App.sData;
        sgAdapter = new MemberListAdapter(getContext(), mSGDatas);
        mLvShow.setAdapter(sgAdapter);
    }

    /**
     * 初始化控件
     */

    private void initViewI() {
        sgAdapter = new MemberListAdapter(getContext(), mSGDatas);
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
