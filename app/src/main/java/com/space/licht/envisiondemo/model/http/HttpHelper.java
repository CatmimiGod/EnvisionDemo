package com.space.licht.envisiondemo.model.http;


import com.space.licht.envisiondemo.model.bean.GankHttpResponse;
import com.space.licht.envisiondemo.model.bean.GankItemBean;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;

import java.util.List;

import rx.Observable;

/**
 * description: HttpHelper
 */

public interface HttpHelper {

    Observable<VideoHttpResponse<VideoRes>> fetchHomePage();

    Observable<VideoHttpResponse<VideoRes>> fetchVideoInfo(String mediaId);

    Observable<VideoHttpResponse<VideoRes>> fetchVideoList(String catalogId, String pnum);

    Observable<VideoHttpResponse<VideoRes>> fetchVideoListByKeyWord(String keyword, String pnum);

    Observable<VideoHttpResponse<VideoRes>> fetchCommentList(String mediaId, String pnum);

    Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page);
}
