package com.cheheihome.easycalendar.widget;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import java.util.Date;

/**
 * Created by chanlevel on 15/5/1.
 */
public class DayCell extends TextView implements View.OnClickListener {

    private int DefaultBackgroud = Color.TRANSPARENT;
    private int DefaultTextColor = Color.BLACK;
    private int TextWhite = Color.WHITE;
    private int DisColor = Color.GRAY;
    private int FirstColor = Color.RED;
    private int LastColor = Color.RED;
    private int MidColor = Color.YELLOW;
    //  private int NormalColor = Color.WHITE;
    private int BeforeBackground = Color.BLACK;
    private int TodayBack = Color.GREEN;

   // private int STATUS = DayModel.NORMAL;

    private boolean isToday;
    private Date date;
    private String dayStr;
    private String desc;
    private String price;

    private DayModel model;

    private DayCallBack callBack;


    public DayCell(Context context) {
        super(context);
    }

    public DayCell(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DayCell(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public DayCell(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void setCallBack(DayCallBack callBack) {
        this.callBack = callBack;
    }


    public DayModel getModel() {
        return model;
    }

    public void setModel(DayModel model) {
        this.model = model;
        if (model == null) return;
        setDate(model.date);
        setPrice(model.price + "");
        setDesc(model.desc);
        setIsToday(model.isToday);

        setDayStr(model.dayStr);
        //setEnable(model.enable);
        setStatus(model.STATUS);
        display();
    }

    public void display() {

        if (!TextUtils.isEmpty(desc))
            setText(dayStr + "\n" + desc);
        else {
            setText(dayStr + "\n" + price);
        }

    }

    public void setStatus(int status) {

//        if (model.getPrice() == 0 && model.getSTATUS() != DayModel.BEFORETODAY) {
//            model.setStatus(DayModel.DISABLE);
//            setDiasble();
//            // setEnable(false);
//            return;
//        }


        switch (status) {
            case DayModel.FIRST:
                setFirst();
                break;
            case DayModel.MID:
                setMid();
                break;
            case DayModel.LAST:
                setLast();
                break;
            case DayModel.NORMAL:
                setNormal();
                break;
            case DayModel.BEFORETODAY:
                setBeforeToday();
                break;
            case DayModel.DISABLE:
                //setEnable(false);
                setDiasble();
                break;
        }
    }


    public void setIsToday(boolean today) {
//        if (!today) return;
//        setBackgroundColor(Color.GRAY);
//        setTextColor(TextWhite);
//        dayStr = "今天";
//        // setText(dayStr);
        isToday = today;
    }


    public boolean isToday() {

        return isToday;
    }

    public void setNormal() {
        if (isToday) {
            setTextColor(TextWhite);
            setBackgroundColor(TodayBack);

        } else {
            setTextColor(DefaultTextColor);
            setBackgroundColor(DefaultBackgroud);

        }
        setEnabled(true);

        // if (STATUS != DayModel.NORMAL)
      //  STATUS = DayModel.NORMAL;
    }

    public void setFirst() {
        // if (STATUS == DayModel.NORMAL)
        setTextColor(TextWhite);
        setBackgroundColor(FirstColor);
        dayStr = "入住";
       // STATUS = DayModel.FIRST;


    }

    public void setMid() {
        // if (STATUS == DayModel.NORMAL)
        setTextColor(TextWhite);
        setBackgroundColor(MidColor);
     //   STATUS = DayModel.MID;
        setEnabled(true);
    }

    public void setLast() {
        //if (STATUS == DayModel.NORMAL)
        setTextColor(TextWhite);
        setBackgroundColor(LastColor);
        dayStr = "离开";
      //  STATUS = DayModel.LAST;

    }

    public void setBeforeToday() {
        // if (STATUS != DayModel.BEFORETODAY) {
        setTextColor(TextWhite);
        setBackgroundColor(BeforeBackground);
       // STATUS = DayModel.BEFORETODAY;
        setEnabled(false);
        // }
    }

//    public void setEnable(boolean able) {
//        if (!able) return;
//        //  if (STATUS == DayModel.DISABLE) return;
//        // if (STATUS == DayModel.NORMAL)
//        setTextColor(TextWhite);
//        setBackgroundColor(DisColor);
//        setEnabled(able);
//        STATUS = DayModel.DISABLE;
//
//    }

    public void setDiasble() {
        setTextColor(TextWhite);
        setBackgroundColor(DisColor);
        setEnabled(false);
       // STATUS = DayModel.DISABLE;


    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
        if (date == null) return;
        this.date = date;
        // setTextColor(model.DefaultTextColor);
        //setBackgroundColor(DefaultBackgroud);
        //  Calendar c = Calendar.getInstance();
        //  c.setTime(date);
        // dayStr = c.get(Calendar.DAY_OF_MONTH) + "";
        // setText(dayStr);
        //  setIsToday(DateUtils.isToday(date));
        // boolean before = DateUtils.beforeToday(date);
        // Log.d("before", date.toString() + ":" + before);

        // if (before) {
        //     setEnable(false);
        // }
        setOnClickListener(this);
    }

    public void clean() {
        setDate(null);
        setModel(null);
        setText("");
        setBackgroundColor(Color.TRANSPARENT);

        //setEnabled(false);
    }


    public String getDayStr() {
        return dayStr;
    }

    public void setDayStr(String dayStr) {
        if (isToday) {
            dayStr = "今天";
            this.dayStr = dayStr;
            return;
        }

        if (TextUtils.isEmpty(dayStr) || date != null) {
            dayStr = "" + date.getDate();
        }
        this.dayStr = dayStr;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    @Override
    public void onClick(View v) {

        // Toast.makeText(getContext(), dayStr, Toast.LENGTH_SHORT).show();

        if (model == null) return;
        if (callBack != null)
            if (callBack.callBack(model)) {

            }

    }


    public interface DayCallBack {
        public boolean callBack(DayModel model);
    }


}
