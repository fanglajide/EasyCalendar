package com.cheheihome.easycalendar.widget;

import java.util.Calendar;
import java.util.Date;

public class DayModel {

    public final static int NORMAL = 0x01;
    public final static int FIRST = 0x02;
    public final static int MID = 0x03;
    public final static int LAST = 0x04;
    public final static int DISABLE = 0x05;
    public final static int BEFORETODAY = 0x06;
//
//    public int DefaultTextColor = Color.BLACK;
//    public int TextWhite = Color.WHITE;
//    public int DisColor = Color.GRAY;
//    public int FirstColor = Color.RED;
//    public int LastColor = Color.RED;
//    public int MidColor = Color.YELLOW;
//    public int NormalColor = Color.WHITE;

    public int STATUS = NORMAL;
    //    public int day;
//    public int month;
//    public int year;
    public boolean isToday;
    public Date date;
    public String dayStr;
    public String desc;
    public int price;
    public boolean enable = true;

    public DayModel(Date date) {
        this.date = date;
        setDate(date);
    }

    public DayModel() {

    }

    public int getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(int STATUS) {
        this.STATUS = STATUS;
    }

//    public int getDay() {
//        return day;
//    }
//
//    public void setDay(int day) {
//        this.day = day;
//    }
//
//    public int getMonth() {
//        return month;
//    }
//
//    public void setMonth(int month) {
//        this.month = month;
//    }
//
//    public int getYear() {
//        return year;
//    }
//
//    public void setYear(int year) {
//        this.year = year;
//    }

    public boolean isToday() {
        return isToday;
    }

    public void setIsToday(boolean isToday) {
        this.isToday = isToday;
    }

    public Date getDate() {
        return date;
    }

    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        this.dayStr = dayStr;
    }

    public String getDesc() {
        return desc;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
        if (price == 0) setStatus(DISABLE);
        // if (price == 0) enable = false;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public void setStatus(int status) {
        // if (price == 0) return;
        STATUS = status;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setDate(Date date) {
        this.date = date;
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        this.dayStr = c.get(Calendar.DAY_OF_MONTH) + "";
        this.isToday = DateUtils.isToday(date);
        if (DateUtils.beforeToday(date)) setStatus(BEFORETODAY);
    }

    @Override
    public String toString() {
        return "DayModel{" +
                "STATUS=" + STATUS +
                ", isToday=" + isToday +
                ", date=" + date +
                ", dayStr='" + dayStr + '\'' +
                ", desc='" + desc + '\'' +
                ", price=" + price +
                ", enable=" + enable +
                '}';
    }
}