package com.cheheihome.easycalendar.widget;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by chanlevel on 15/5/1.
 */
public class DateUtils {
    static Calendar today = Calendar.getInstance();

    public static boolean sameDate(Calendar cal, Calendar selectedDate) {
        return cal.get(Calendar.MONTH) == selectedDate.get(Calendar.MONTH)
                && cal.get(Calendar.YEAR) == selectedDate.get(Calendar.YEAR)
                && cal.get(Calendar.DAY_OF_MONTH) == selectedDate.get(Calendar.DAY_OF_MONTH);
    }


    public static boolean sameDate(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);
        return sameDate(c2, c1);

    }


    public static boolean SelectedBefore(Calendar cal, Calendar selectedDate) {
        return cal.get(Calendar.YEAR) < selectedDate.get(Calendar.YEAR)
                || cal.get(Calendar.MONTH) < selectedDate.get(Calendar.MONTH)
                || cal.get(Calendar.DAY_OF_MONTH) < selectedDate.get(Calendar.DAY_OF_MONTH);
    }

    public static boolean isToday(Date date) {
        Calendar selected = Calendar.getInstance();
        selected.setTime(date);
        return sameDate(selected, today);

    }

    public static boolean beforeToday(Date date) {
        Calendar selected = Calendar.getInstance();
        selected.setTime(date);
        return selected.before(today) && !sameDate(today, selected);

    }


    public static boolean InafterDays(int days, Date dahead, Date dafter) {
        Calendar da = Calendar.getInstance();
        da.setTime(dahead);
        da.add(Calendar.DATE, days);
        Calendar df = Calendar.getInstance();
        df.setTime(dafter);

        return df.before(da);

    }


}
