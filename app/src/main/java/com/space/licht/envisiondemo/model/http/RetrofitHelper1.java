package com.space.licht.envisiondemo.model.http;


import com.space.licht.envisiondemo.model.bean.GankHttpResponse;
import com.space.licht.envisiondemo.model.bean.GankItemBean;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.http.api.GankApis;
import com.space.licht.envisiondemo.model.http.api.VideoApis;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;


/**
 * RetrofitHelper
 */
public class RetrofitHelper1 implements HttpHelper {

    private VideoApis mVideoApis;
    private GankApis mGankApis;
    
    @Inject
    public RetrofitHelper1(VideoApis videoApis, GankApis gankApis) {
        this.mVideoApis = videoApis;
        this.mGankApis = gankApis;
    }


    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchHomePage() {
        return mVideoApis.getHomePage();
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoInfo(String mediaId) {
        return mVideoApis.getVideoInfo(mediaId);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoList(String catalogId, String pnum) {
        return mVideoApis.getVideoList(catalogId, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoListByKeyWord(String keyword, String pnum) {
        return mVideoApis.getVideoListByKeyWord(keyword, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchCommentList(String mediaId, String pnum) {
        return mVideoApis.getCommentList(mediaId, pnum);
    }

    @Override
    public Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mGankApis.getGirlList(num, page);
    }
}
