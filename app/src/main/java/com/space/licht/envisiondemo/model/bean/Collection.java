package com.space.licht.envisiondemo.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Description: 收藏
 */
public class Collection extends RealmObject implements Serializable {
    String id;
    long time;
    public String title;
    public String pic;
    public String airTime;
    public String named;
    public String tel;
    public int voice;
    public int dataTime;
    public String score;
    public int headImg;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String score) {
        this.tel = score;
    }

    public String getNamed() {
        return named;
    }

    public void setNamed(String named) {
        this.named = named;
    }

    public int getHeadImg() {
        return headImg;
    }

    public void setHeadImg(int named) {
        this.headImg = named;
    } public int getDataTime() {
        return dataTime;
    }

    public void setDataTime(int named) {
        this.dataTime = named;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int named) {
        this.voice = named;
    }
}
