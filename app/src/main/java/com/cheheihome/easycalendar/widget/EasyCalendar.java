package com.cheheihome.easycalendar.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chanlevel on 15/5/1.
 */
public class EasyCalendar extends ListView implements EasyInterface {
    public static final int NOONEPICKED = 1;
    public static final int SELECTEDFIRST = 2;
    public static final int SELECTEDRANGE = 3;

    private CalendarAdapter adapter;
    private int currentStatus;
    private List<HashMap<Date, DayModel>> list;


    private DayModel selectFirst;
    private DayModel selectLast;

    public EasyCalendar(Context context) {
        super(context);
    }

    public EasyCalendar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public EasyCalendar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setSelectedRange(Date firstSelected, Date lastSelected) {
        if (firstSelected != null) setSelectFirst(new DayModel(firstSelected));
        if (lastSelected != null) setSelectLast(new DayModel(lastSelected));
    }


    public void setup() {
        if (list == null) throw new IllegalArgumentException(
                "minDate and maxDate must be non-null.  ");
        dealData();
        adapter = new CalendarAdapter(getContext(), list, new DayCell.DayCallBack() {
            @Override
            public boolean callBack(DayModel model) {
                DayModel first = getSelectFirst();
                DayModel last = getSelectLast();
                if (first == null) {
                    setSelectFirst(model);
                    model.setStatus(DayModel.FIRST);
                    setCurrentStatus(SELECTEDFIRST);
                } else {


                    if (first.getDate().after(model.getDate())) {

//                    first.setStatus(DayModel.NORMAL);
//                    model.setStatus(DayModel.FIRST);
                        setSelectFirst(model);
                        setCurrentStatus(SELECTEDFIRST);
                        if (last != null) {
//                        last.setStatus(DayModel.NORMAL);
                            setSelectLast(null);
                        }


                    } else {

                        if (last == null) {
                            if (first.getDate().before(model.getDate())) {
                                setSelectLast(model);
                                setCurrentStatus(SELECTEDRANGE);
                            } else {
                                setCurrentStatus(SELECTEDFIRST);
                                setSelectFirst(model);
                            }
                            //  model.setStatus(DayModel.LAST);

                        }
                        if (last != null) {
                            //  model.setStatus(DayModel.FIRST);
                            // first.setStatus(DayModel.NORMAL);
                            // last.setStatus(DayModel.NORMAL);
                            setSelectFirst(model);
                            setCurrentStatus(SELECTEDFIRST);
                            setSelectLast(null);

                        }


                    }


                }
                callBack.onSelect(getCurrentStatus(), model, getSelectFirst(), getSelectLast());

                return false;
            }
        });

        this.setAdapter(adapter);

    }

    public DayModel getSelectLast() {
        return selectLast;
    }

    public void setSelectLast(DayModel selectLast) {
        this.selectLast = selectLast;
    }

    public DayModel getSelectFirst() {
        return selectFirst;
    }

    public void setSelectFirst(DayModel selectFirst) {
        this.selectFirst = selectFirst;
    }

    public int getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(int currentStatus) {
        this.currentStatus = currentStatus;
    }

    @Override
    public void refreshRange() {
        setRange(begin, end);
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void refreshData() {
        dealData();
        // setData(list);
        if (adapter != null) adapter.notifyDataSetChanged();

    }

    @Override
    public void refresh() {
        if (list != null) refreshData();
        else refreshRange();
    }

    @Override
    public void cleanAll() {

    }

    Date begin;
    Date end;

    @Override
    public void setRange(Date begin, Date end) {
        this.begin = begin;
        this.end = end;
        if (list == null) list = new ArrayList<>();
        Calendar b = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        b.setTime(begin);
        b.set(Calendar.DAY_OF_MONTH, 1);

        e.setTime(end);
        e.set(Calendar.DAY_OF_MONTH, e.getActualMaximum(Calendar.DATE));
        for (; b.before(e); b.add(Calendar.DATE, 1)) {
            HashMap<Date, DayModel> map = new HashMap<>();
            DayModel m = new DayModel(b.getTime());
            if (handler != null) {
                DayModel f = getSelectFirst();
                DayModel l = getSelectLast();
                if (f != null)
                    Log.d("SELECTED:first---", f.toString());


                if (l != null)
                    Log.d("SELECTED:last---", l.toString());


                m = handler.deal(f, l, b.getTime(), m);
            }
            map.put(b.getTime(), m);
            int maxday = b.getActualMaximum(Calendar.DATE);
            for (; b.get(Calendar.DAY_OF_MONTH) < maxday; ) {
                b.add(Calendar.DAY_OF_MONTH, 1);
                DayModel dm = new DayModel(b.getTime());
                if (handler != null) {
                    DayModel f = getSelectFirst();
                    DayModel l = getSelectLast();
                    dm = handler.deal(f, l, b.getTime(), dm);
                }
                // dm.setDesc((int) (Math.random() * 30) + "");
                map.put(b.getTime(), dm);
            }
            list.add(map);
        }
        // if (adapter != null) adapter.notifyDataSetChanged();

        // refresh();

    }

    // List<HashMap<Date, DayModel>> mapList;
    @Override
    public void setData(List<HashMap<Date, DayModel>> mapList) {
        this.list = mapList;

        if (getSelectFirst() != null || getSelectLast() != null) {
          //  dealInitSelected();
        }

        // if (adapter != null) adapter.notifyDataSetChanged();

    }


    private void dealInitSelected() {
        if (list == null || handler == null) return;

        for (HashMap<Date, DayModel> map : list) {

            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Date, DayModel> entry = (Map.Entry<Date, DayModel>) iterator.next();
                Date date = entry.getKey();
                DayModel model = entry.getValue();
                Log.d("model", model.getDate() + "");
                //  handler.deal(getSelectFirst(), getSelectLast(), date, model);

                if (DateUtils.sameDate(getSelectFirst().getDate(), model.getDate()))
                    model.setStatus(DayModel.FIRST);
                else if (DateUtils.sameDate(getSelectLast().getDate(), model.getDate()))
                    model.setStatus(DayModel.LAST);

            }

        }


    }


    /**
     * deal every daymodel depends EasyHandler
     */
    private void dealData() {
        if (list == null || handler == null) return;

        for (HashMap<Date, DayModel> map : list) {

            Iterator iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<Date, DayModel> entry = (Map.Entry<Date, DayModel>) iterator.next();
                Date date = entry.getKey();
                DayModel model = entry.getValue();
                Log.d("model", model.getDate() + "");
                handler.deal(getSelectFirst(), getSelectLast(), date, model);
            }


        }


    }


    EasyCallBack callBack;

    public void setEasyCallBack(EasyCallBack callBack) {
        this.callBack = callBack;
    }

    /**
     * CallBack when any DayCell be clicked
     */
    public interface EasyCallBack {
        public void onSelect(int currentStatus, DayModel selectDate, DayModel FirstDate, DayModel lastDate);
    }

    DayHandler handler;


    public void setHandler(DayHandler handler) {
        this.handler = handler;
    }

    /**
     * build a rule to define daymodel
     */
    public interface DayHandler {
        public DayModel deal(DayModel first, DayModel last, Date date, DayModel model);
    }


}
