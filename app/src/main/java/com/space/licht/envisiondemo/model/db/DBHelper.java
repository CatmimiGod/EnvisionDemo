package com.space.licht.envisiondemo.model.db;


import com.space.licht.envisiondemo.model.bean.Collection;
import com.space.licht.envisiondemo.model.bean.Record;
import com.space.licht.envisiondemo.model.bean.SearchKey;

import java.util.List;

import io.realm.Realm;


/**
 * description: realm   数据库接口
 */

public interface DBHelper {
    Realm getRealm();

    void insertCollection(Collection bean);

    void deleteCollection(String id);

    void deleteAllCollection();

    boolean queryCollectionId(String id);

    List<Collection> getCollectionList();

    void insertRecord(Record bean, int maxSize);

    void deleteRecord(String id);

    boolean queryRecordId(String id);

    List<Record> getRecordList();

    void deleteAllRecord();

    void insertSearchHistory(SearchKey bean);

    List<SearchKey> getSearchHistoryList(String value);

    void deleteSearchHistoryList(String value);

    void deleteSearchHistoryAll();

    List<SearchKey> getSearchHistoryListAll();
}
