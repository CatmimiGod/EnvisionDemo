package com.space.licht.envisiondemo.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Description: VideoType
 */
public class VideoType {
    public String title;
    public String moreURL;
    public String pic;
    public String dataId;
    public String airTime;
    public String score;
    public String description;
    public String msg;
    public String phoneNumber;
    public String userPic;
    public String time;
    public String likeNum;
    public
    @SerializedName("childList")
    List<VideoInfo> childList;
}