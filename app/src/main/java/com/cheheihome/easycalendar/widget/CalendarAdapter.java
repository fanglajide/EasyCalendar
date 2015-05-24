package com.cheheihome.easycalendar.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cheheihome.easycalendar.R;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by chanlevel on 15/5/2.
 */
public class CalendarAdapter extends BaseAdapter {
    private Context context;
    private List<HashMap<Date, DayModel>> list;
    DayCell.DayCallBack callBack;

    public CalendarAdapter(Context context, List<HashMap<Date, DayModel>> list, DayCell.DayCallBack callBack) {
        this.context = context;
        this.list = list;
        this.callBack = callBack;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HashMap<Date, DayModel> map = list.get(position);
        MonthHolder holder;
        if (convertView == null) {
            holder = new MonthHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.monthview, null);
            holder.header = (TextView) convertView.findViewById(R.id.header);
            holder.area = (MonthArea) convertView.findViewById(R.id.monthArea);
            convertView.setTag(holder);
        } else
            holder = (MonthHolder) convertView.getTag();
        holder.area.clean();
        holder.area.setCallBack(callBack);
        holder.area.setData(map);

        Iterator iter = map.keySet().iterator();
        Date date = (Date) iter.next();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        holder.header.setText(c.get(Calendar.YEAR) + "/" + Integer.valueOf(c.get(Calendar.MONTH)+1) );

        return convertView;
    }


    private static class MonthHolder {
        private View convertView;
        private TextView header;
        private MonthArea area;


    }


}
