package com.cheheihome.easycalendar;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.cheheihome.easycalendar.widget.DateUtils;
import com.cheheihome.easycalendar.widget.DayModel;
import com.cheheihome.easycalendar.widget.EasyCalendar;
import com.cheheihome.easycalendar.widget.MonthArea;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    MonthArea area;
    EasyCalendar easyCalendar;
    TextView hello;
    Calendar b, e;
    DayModel f;
    DayModel l;
    Date temp;

    int leastSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  area = (MonthArea) findViewById(R.id.monthArea);
        easyCalendar = (EasyCalendar) findViewById(R.id.easyCalendar);
        hello = (TextView) findViewById(R.id.hello);
        hello.setOnClickListener(this);
        leastSelect = 2;

        b = Calendar.getInstance();
        e = Calendar.getInstance();
        e.add(Calendar.MONTH, 6);
        // easyCalendar.setRange(b.getTime(), e.getTime());
        Calendar fffff = Calendar.getInstance();
        fffff.set(2015, 5, 3);
        Date fd = fffff.getTime();
        fffff.set(2015, 5, 7);
        Date ld = fffff.getTime();

        easyCalendar.setSelectedRange(fd, ld);

        easyCalendar.setHandler(new EasyCalendar.DayHandler() {
                                    @Override
                                    public DayModel deal(DayModel first, DayModel last, Date date, DayModel model) {
                                        // if before today return directely


                                        if (model.getSTATUS() == DayModel.BEFORETODAY) return null;

                                        if (first != null && DateUtils.sameDate(date, first.getDate())) {
                                            model.setStatus(DayModel.FIRST);
                                            return model;
                                        } else if (last != null && DateUtils.sameDate(date, last.getDate())) {
                                            model.setStatus(DayModel.LAST);
                                            return model;
                                        }


                                        //   selected both
                                        if (first != null && last != null) {
                                            temp = null;
                                            Date f = first.getDate();
                                            Date l = last.getDate();
                                            if (f.before(date) && l.after(date)) {
                                                model.setStatus(DayModel.MID);
                                                return model;
                                            } else {
                                                if (model.getPrice() == 0) {
                                                    model.setStatus(DayModel.DISABLE);
                                                    return model;
                                                }

                                                model.setStatus(DayModel.NORMAL);
                                            }

                                            return model;
                                        }
                                        // only select first
                                        else if (first != null && last == null) {
                                            temp = getTemp(first);
                                            if (temp == null) {
                                                model.setStatus(DayModel.NORMAL);
                                                return model;
                                            } //else
                                            // Toast.makeText(MainActivity.this, temp.getDate() + "", Toast.LENGTH_SHORT).show();
                                            if (first.getDate().before(date) && DateUtils.InafterDays(leastSelect, first.getDate(), date)) {
                                                model.setStatus(DayModel.DISABLE);
                                                return model;
                                            }
                                            if (DateUtils.sameDate(temp, date)) {
                                                model.setStatus(DayModel.NORMAL);
                                                return model;
                                            }

                                            if (date.before(first.getDate())) {
                                                if (model.getPrice() == 0) {
                                                    model.setStatus(DayModel.DISABLE);
                                                } else model.setStatus(DayModel.NORMAL);
                                                return model;

                                            }


                                            if (date.after(temp)) {
                                                model.setStatus(DayModel.DISABLE);
                                                return model;
                                            }

                                            if (model.getPrice() == 0)
                                                model.setStatus(DayModel.DISABLE);
                                            else
                                                model.setStatus(DayModel.NORMAL);

                                        } else {
                                            temp = null;
                                            if (model.getPrice() == 0)
                                                model.setStatus(DayModel.DISABLE);
                                        }


                                        return model;

                                        //select first
//                                        else if (first != null && last == null) {
//                                          model.setStatus(DayModel.NORMAL);
//
//
//                                        }// selected none
//                                        else if (first == null && last == null) {
//
//                                            model.setStatus(DayModel.NORMAL);
//
//
//                                        }

                                    }
                                }

        );
        easyCalendar.setData(getList());
        easyCalendar.setEasyCallBack(new EasyCalendar.EasyCallBack()

                                     {
                                         @Override
                                         public void onSelect(int currentStatus, DayModel selectDate, DayModel
                                                 FirstDate, DayModel lastDate) {

                                             // Toast.makeText(MainActivity.this, selectDate.getDayStr()+"\n"+, Toast.LENGTH_SHORT).show();
                                             f = FirstDate;
                                             l = lastDate;
                                             //   show();

                                             easyCalendar.refresh();
                                             temp = null;
                                         }
                                     }

        );

        easyCalendar.setup();
    }


    private Date getTemp(DayModel first) {
        if (temp != null) return temp;
        Calendar f = Calendar.getInstance();
        f.setTime(first.getDate());

        while (!nopricelist.contains(f.getTime()) && f.getTime().before(lastDay)) {
            f.add(Calendar.DATE, 1);
        }

        return f.getTime();


    }


    private void show() {
        if (f != null && l != null) {
            Toast.makeText(MainActivity.this, f.getDayStr() + "\n" + l.getDayStr(), Toast.LENGTH_SHORT).show();

        }


    }

    Date lastDay;
    List<HashMap<Date, DayModel>> list;

    private List<HashMap<Date, DayModel>> getList() {
        if (list != null) return list;
        list = new ArrayList<>();
        Date begin = b.getTime();
        Date end = e.getTime();
        Calendar b = Calendar.getInstance();
        Calendar e = Calendar.getInstance();
        b.setTime(begin);
        b.set(Calendar.DAY_OF_MONTH, 1);
        e.setTime(end);
        e.set(Calendar.DAY_OF_MONTH, e.getActualMaximum(Calendar.DATE));
        for (; b.before(e); b.add(Calendar.DATE, 1)) {
            HashMap<Date, DayModel> map = new HashMap<>();
            DayModel m = new DayModel(b.getTime());

            map.put(b.getTime(), m);
            int maxday = b.getActualMaximum(Calendar.DATE);
            for (; b.get(Calendar.DAY_OF_MONTH) < maxday; ) {
                Log.d("DAYOFMONTH",b.get(Calendar.DAY_OF_MONTH)+"---:"+maxday);
                DayModel dm = new DayModel(b.getTime());

                //dm.setPrice((int) (Math.random() * 30));

                if (getPriceMap().containsKey(dm.getDate())) {
                    dm.setPrice(getPriceMap().get(dm.getDate()));
                }

                map.put(b.getTime(), dm);
                b.add(Calendar.DAY_OF_MONTH, 1);
                lastDay = b.getTime();
            }
            map.put(b.getTime(),new DayModel(b.getTime()));
            list.add(map);
        }


        return list;
    }

    List<Date> nopricelist;
    private HashMap<Date, Integer> priceMap;

    private HashMap<Date, Integer> getPriceMap() {
        if (priceMap != null) return priceMap;
        priceMap = new HashMap<>();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 3);
        while (b.before(c)) {
            int price = (int) (Math.random() * 30);
            if (nopricelist == null) nopricelist = new ArrayList<>();
            if (price == 0) nopricelist.add(b.getTime());
            priceMap.put(b.getTime(), price);
            b.add(Calendar.DATE, 1);
        }

        for (Date d : nopricelist) {
            Log.d("noprice", d.toString());
        }


        return priceMap;

    }

    ;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hello:
                if (easyCalendar != null) easyCalendar.refresh();
                break;
        }
    }
}
