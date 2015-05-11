package com.cheheihome.easycalendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by chanlevel on 15/5/3.
 */
public class MonthArea extends GridLayout {
    DayCell[][] cells = new DayCell[6][7];
    HashMap<Date, DayModel> dataMap;
    Calendar c = Calendar.getInstance();


    public MonthArea(Context context) {
        super(context);
        initView();
    }

    public MonthArea(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MonthArea(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setData(HashMap<Date, DayModel> dataMap) {
        if (dataMap != null) {
            this.dataMap = dataMap;

            show(dataMap);
        } else return;

    }

    DayCell.DayCallBack callBack;

    public void setCallBack(DayCell.DayCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * clean the first and last rows
     */
    public void clean() {
        //for(DayCell c:cells)c.setText("");
        Calendar c = Calendar.getInstance();
        int temp = c.get(Calendar.WEEK_OF_MONTH) - 1;
        for (int i = 0; i < 7; i++) {
            int t = 0;
//            for (; t < 1; t++) {
//                cells[t][i].clean();
//            }
//            if (temp < 5)
            cells[0][i].clean();
            cells[4][i].clean();
            cells[5][i].clean();
        }

    }


    public void initView() {
        //    View.inflate(getContext(), R.layout.montharea, null);
        int count = getChildCount();
        //cells[0][0]= (DayCell) getChildAt(0);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int nn = 0;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 6; j++) {
                cells[j][i] = (DayCell) getChildAt(nn++);
                // cells[j][i].setText(nn+"");
            }
        }

        // show();
    }

    void show() {
        Calendar cc = Calendar.getInstance();
        cc.add(Calendar.MONTH, 1);
        cc.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat sf = new SimpleDateFormat("MM-dd");
        c.set(Calendar.DAY_OF_MONTH, 1);
        while (c.before(cc)) {

            int i = c.get(Calendar.WEEK_OF_MONTH) - 1;
            int j = c.get(Calendar.DAY_OF_WEEK) - 1;
         //   Log.d("daycell:", i + ":" + j);

            //  cells[j][i].setText(i + ":" + j));
            cells[i][j].setDate(c.getTime());
            cells[i][j].setCallBack(new DayCell.DayCallBack() {
                @Override
                public boolean callBack(DayModel model) {
                    callBack.callBack(model);
                    return false;
                }
            });
            c.add(Calendar.DAY_OF_MONTH, 1);

        }
    }

    private void show(HashMap<Date, DayModel> dataMap) {
        // if(true)return;
        Iterator iter = dataMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            Date date = (Date) entry.getKey();
            DayModel value = (DayModel) entry.getValue();
            c.setTime(date);
            int i = c.get(Calendar.WEEK_OF_MONTH) - 1;
            int j = c.get(Calendar.DAY_OF_WEEK) - 1;
          //  Log.d("ij", "date:" + date + "---i:" + i + "---j:" + j);
            // cells[i][j].setDate(date);
            cells[i][j].setCallBack(new DayCell.DayCallBack() {
                @Override
                public boolean callBack(DayModel model) {
                    callBack.callBack(model);
                    return false;
                }
            });
            cells[i][j].setModel(value);


        }
    }





}
