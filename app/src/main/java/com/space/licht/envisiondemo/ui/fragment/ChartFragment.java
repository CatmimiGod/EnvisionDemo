package com.space.licht.envisiondemo.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseFragment;
import com.space.licht.envisiondemo.ui.fragment.discover.DataFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 */
public class ChartFragment extends BaseFragment {
    @BindView(R.id.pager)
    ViewPager pager;
    @BindView(R.id.dot_1)
    ImageView mDot1;
    @BindView(R.id.dot_2)
    ImageView mDot2;
    private DataFragment twoFragment;
    private VoiceFragment oneFragment;


    @Override
    protected int getLayout() {
        return R.layout.fragment_chat;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);

        pager.setAdapter(new MyPagerAdapter(getActivity().getSupportFragmentManager()));
        pager.setOffscreenPageLimit(2);
        pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                if (position == 0){
                    mDot1.setEnabled(true);
                    mDot2.setEnabled(false);
                }else{
                    mDot2.setEnabled(true);
                    mDot1.setEnabled(false);
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        pager.setPageTransformer(false, new AlphaTransformer());
        pager.setPageMargin(30);
        mDot2.setEnabled(false);
    }


    public class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        private final String[] titles = {"1", "2"};


        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }


        @Override
        public int getCount() {
            return titles.length;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (oneFragment == null) {
                        oneFragment = new VoiceFragment();
                    }
                    return oneFragment;
                case 1:
                    if (twoFragment == null) {
                        twoFragment = new DataFragment();
                    }
                    return twoFragment;
                default:

                    return null;
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    public class AlphaTransformer implements ViewPager.PageTransformer {
        private float MINALPHA = 0.5f;

        @Override
        public void transformPage(View page, float position) {
            if (position < -1 || position > 1) {
                page.setAlpha(MINALPHA);
            } else {
                //不透明->半透明
                if (position < 0) {//[0,-1]
                    page.setAlpha(MINALPHA + (1 + position) * (1 - MINALPHA));
                } else {//[1,0]
                    //半透明->不透明
                    page.setAlpha(MINALPHA + (1 - position) * (1 - MINALPHA));
                }
            }
        }
    }
}
