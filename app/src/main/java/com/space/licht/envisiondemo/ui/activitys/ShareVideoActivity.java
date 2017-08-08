package com.space.licht.envisiondemo.ui.activitys;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.space.licht.envisiondemo.R;
import com.space.licht.envisiondemo.base.BaseActivity;
import com.space.licht.envisiondemo.widget.FullVideoView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by licht
 * 2017/8/3 0003.
 */

public class ShareVideoActivity extends BaseActivity {
    @BindView(R.id.activity_play_video)
    FullVideoView mActivityPlayVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_video);
        ButterKnife.bind(this);

        //播放完成回调
        mActivityPlayVideo.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                onBackPressedSupport();
            }
        });

        //设置视频路径
        String uri = "android.resource://" + getPackageName() + "/" + R.raw.share;
        mActivityPlayVideo.setVideoPath(uri);
        //开始播放视频

        mActivityPlayVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
            }
        });
    }
}
