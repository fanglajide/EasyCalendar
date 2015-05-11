package com.cheheihome.easycalendar.widget;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by chanlevel on 15/5/9.
 */
public interface EasyInterface {
    public void refresh();

    public void refreshRange();

    public void refreshData();

    public void cleanAll();

    public void setRange(Date begin, Date end);

    public void setData(List<HashMap<Date, DayModel>> mapList);


}
