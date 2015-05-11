package com.cheheihome.easycalendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.cheheihome.easycalendar.R;

/**
 * Created by chanlevel on 15/5/2.
 */
public class MonthView extends LinearLayout {


    public MonthView(Context context) {
        super(context);
    }

    public MonthView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MonthView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setData(){

    }
    public void setDesc(){

    }



    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.monthview, null);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();




    }
}
