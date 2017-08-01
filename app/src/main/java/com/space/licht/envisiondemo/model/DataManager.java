package com.space.licht.envisiondemo.model;


import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.bean.GankHttpResponse;
import com.space.licht.envisiondemo.model.bean.GankItemBean;
import com.space.licht.envisiondemo.model.bean.Record;
import com.space.licht.envisiondemo.model.bean.SearchKey;
import com.space.licht.envisiondemo.model.bean.VideoRes;
import com.space.licht.envisiondemo.model.db.DBHelper;
import com.space.licht.envisiondemo.model.http.HttpHelper;
import com.space.licht.envisiondemo.model.http.response.VideoHttpResponse;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * @desciption: app数据管理器  realm 替代 sqlite数据库
 */

public class DataManager implements HttpHelper, DBHelper {

    HttpHelper mHttpHelper;
    DBHelper mDbHelper;

    public DataManager(HttpHelper httpHelper, DBHelper dbHelper) {
        mHttpHelper = httpHelper;
        mDbHelper = dbHelper;
    }


    @Override
    public Realm getRealm() {
        return mDbHelper.getRealm();
    }

    @Override
    public void insertCollection(Collection bean) {
        mDbHelper.insertCollection(bean);
    }

    @Override
    public void deleteCollection(String id) {
        mDbHelper.deleteCollection(id);
    }

    @Override
    public void deleteAllCollection() {
        mDbHelper.deleteAllCollection();
    }

    @Override
    public boolean queryCollectionId(String id) {
        return mDbHelper.queryCollectionId(id);
    }

    @Override
    public List<Collection> getCollectionList() {
        return mDbHelper.getCollectionList();
    }

    @Override
    public void insertRecord(Record bean, int maxSize) {
        mDbHelper.insertRecord(bean, maxSize);
    }

    @Override
    public void deleteRecord(String id) {
        mDbHelper.deleteRecord(id);
    }

    @Override
    public boolean queryRecordId(String id) {
        return mDbHelper.queryRecordId(id);
    }

    @Override
    public List<Record> getRecordList() {
        return mDbHelper.getRecordList();
    }

    @Override
    public void deleteAllRecord() {
        mDbHelper.deleteAllRecord();
    }

    @Override
    public void insertSearchHistory(SearchKey bean) {
        mDbHelper.insertSearchHistory(bean);
    }

    @Override
    public List<SearchKey> getSearchHistoryList(String value) {
        return mDbHelper.getSearchHistoryList(value);
    }

    @Override
    public void deleteSearchHistoryList(String value) {
        mDbHelper.deleteSearchHistoryList(value);
    }

    @Override
    public void deleteSearchHistoryAll() {
        mDbHelper.deleteSearchHistoryAll();
    }

    @Override
    public List<SearchKey> getSearchHistoryListAll() {
        return mDbHelper.getSearchHistoryListAll();
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchHomePage() {
        return mHttpHelper.fetchHomePage();
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoInfo(String mediaId) {
        return mHttpHelper.fetchVideoInfo(mediaId);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoList(String catalogId, String pnum) {
        return mHttpHelper.fetchVideoList(catalogId, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchVideoListByKeyWord(String keyword, String pnum) {
        return mHttpHelper.fetchVideoListByKeyWord(keyword, pnum);
    }

    @Override
    public Observable<VideoHttpResponse<VideoRes>> fetchCommentList(String mediaId, String pnum) {
        return mHttpHelper.fetchCommentList(mediaId, pnum);
    }

    @Override
    public Observable<GankHttpResponse<List<GankItemBean>>> fetchGirlList(int num, int page) {
        return mHttpHelper.fetchGirlList(num, page);
    }
}
