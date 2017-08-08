package com.space.licht.envisiondemo.ui.fragment.community;


import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.app.App;
import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.ui.activitys.PlayVideoActivity;
import com.space.licht.envisiondemo.ui.fragment.DensityUtil;
import com.space.licht.envisiondemo.ui.fragment.Model;
import com.space.licht.envisiondemo.utils.JumpUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Description: MemberFragment
 */
public class CommunityFragment extends Fragment {

    private static final String TAG = "CommunityFragment";
    @BindView(R.id.community_lv)
    ListView mCommunityLv;
    @BindView(R.id.progress_precent)
    TextView progressPrecent;
    @BindView(R.id.pb_progressbar)
    ProgressBar pbProgressbar;
    @BindView(R.id.package_allocation)
    TextView mPackageAllocation;
    @BindView(R.id.progress_precent_voice)
    TextView mProgressPrecentVoice;
    @BindView(R.id.pb_progressbar_voice)
    ProgressBar mPbProgressbarVoice;
    @BindView(R.id.progress_total)
    TextView mProgressTotal;
    @BindView(R.id.progress_total_voice)
    TextView mProgressTotalVoice;

    private List<Collection> mSGDatas;
    private CommunityAdapter mCommunityAdapter;
    //假设的总进度，最多为100，可自行调整
    private int status = 21;
    private int voiceStatus = 50;
    //当前进度
    private int currentStatue = 0;
    private int voiceCurrentStatue = 0;
    /**
     * 当前位置
     */
    private float currentPosition;

    //得到屏幕的总宽度
    private static int width;

    boolean isgone = true;

    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_community_view, null);
        //得到屏幕的宽
        width = DensityUtil.dip2px(getContext(), 255);
//        width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        ButterKnife.bind(this, mView);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //获取数据
        mSGDatas = App.sData;
        initViewI();
        initProgress();
    }

    /**
     * 初始化数据
     */
    private void initDataI() {

    }

    /**
     * 初始化控件
     */
    private void initViewI() {

        mCommunityAdapter = new CommunityAdapter(getContext(), mSGDatas, isgone , handler,progressPrecent,pbProgressbar);
        mCommunityLv.setAdapter(mCommunityAdapter);

        mCommunityLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e(TAG, "onItemClick: ");
            }
        });
    }

    private void initProgress() {
        //开始按钮的点击事件

        currentStatue = 0;
        currentPosition = 0;
        pbProgressbar.setProgress(0);

        //效果的实现
        initAchieve();
    }

    private void initAchieve() {
        Model.getInstance().getGlobalThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                pbProgressbar.setMax(3200);
                pbProgressbar.setProgress(2100);
                try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        Model.getInstance().getGlobalThreadPool().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        //每一段要移动的距离
                        final float scrollDistance = (float) ((1.0 / 75) * width);
                        mPbProgressbarVoice.setMax(75);

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                Log.e(TAG, "run: ");
                                mProgressPrecentVoice.setText("50Hours");
                                mProgressPrecentVoice.setTranslationX(31 * scrollDistance);
                                mPbProgressbarVoice.incrementProgressBy(50);
                            }
                        });
                        try {
                            Thread.sleep(30);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

        );
    }

    @OnClick(R.id.package_allocation)
    public void onClick() {
        if (isgone){
            isgone = false;
        }else{
            isgone = true;
        }

        mCommunityAdapter.setIsgone(isgone);
        mCommunityAdapter.notifyDataSetChanged();
        if (isgone){
            JumpUtil.jump(getContext(),PlayVideoActivity.class);
        }
    }
}
