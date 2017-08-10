package com.space.licht.envisiondemo.ui.fragment.setting;

/**
 * SanGuoBean
 */
public class TimeBean {
    public TimeBean(){

    }

    public String getsStartDay() {
        return sStartDay;
    }

    public void setsStartDay(String sStartDay) {
        this.sStartDay = sStartDay;
    }

    public String getsStartHour() {
        return sStartHour;
    }

    public void setsStartHour(String sStartHour) {
        this.sStartHour = sStartHour;
    }

    public String getsStartMins() {
        return sStartMins;
    }

    public void setsStartMins(String sStartMins) {
        this.sStartMins = sStartMins;
    }

    public String getsStartAMorPM() {
        return sStartAMorPM;
    }

    public void setsStartAMorPM(String sStartAMorPM) {
        this.sStartAMorPM = sStartAMorPM;
    }

    public String getsStopDay() {
        return sStopDay;
    }

    public void setsStopDay(String sStopDay) {
        this.sStopDay = sStopDay;
    }

    public String getsStopHour() {
        return sStopHour;
    }

    public void setsStopHour(String sStopHour) {
        this.sStopHour = sStopHour;
    }

    public String getsStopMins() {
        return sStopMins;
    }

    public void setsStopMins(String sStopMins) {
        this.sStopMins = sStopMins;
    }

    public String getsStopAMorPM() {
        return sStopAMorPM;
    }

    public void setsStopAMorPM(String sStopAMorPM) {
        this.sStopAMorPM = sStopAMorPM;
    }

    /**
     * 星期几
     */
    private String sStartDay;
    /**
     * 开始的小时
     */
    private  String sStartHour;
    /**
     * 开始的分钟
     */
    private String sStartMins;
    /**
     * 开始的上下午
     */
    private String sStartAMorPM;

    /**
     * 结束的星期几
     */
    private String sStopDay;
    /**
     * 结束的小时
     */
    private  String sStopHour;
    /**
     * 结束的分钟
     */
    private String sStopMins;
    /**
     * 结束的上下午
     */
    private String sStopAMorPM;

}
