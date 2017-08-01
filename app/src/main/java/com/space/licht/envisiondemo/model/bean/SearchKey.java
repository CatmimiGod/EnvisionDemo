package com.space.licht.envisiondemo.model.bean;

import io.realm.RealmObject;

/**
 * 搜索關鍵字查詢
 */

public class SearchKey extends RealmObject {
    public String searchKey;
    public long insertTime;//插入时间

    public SearchKey() {
    }

    public SearchKey(String suggestion, long insertTime) {
        this.searchKey = suggestion;
        this.insertTime = insertTime;
    }

    public String getSearchKey() {
        return searchKey;
    }
}
