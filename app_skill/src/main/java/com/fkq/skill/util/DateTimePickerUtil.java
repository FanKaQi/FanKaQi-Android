package com.fkq.skill.util;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;


import com.fkq.skill.R;

import java.util.Calendar;
import java.util.Locale;

public class DateTimePickerUtil {
    /**
     * 日期选择
     *
     * @param activity
     * @param tv
     */
    public static void showDatePickerDialog(Context activity, final TextView tv) {
        // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
        new DatePickerDialog(activity, R.style.ThemeDialog, new DatePickerDialog.OnDateSetListener() {
            // 绑定监听器(How the parent is notified that the date is set.)
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // 此处得到选择的时间，可以进行你想要的操作
                String month = monthOfYear + 1 + "";
                if (month.length() == 1) {
                    month = "0" + month;
                }
                String day = dayOfMonth + "";
                if (day.length() == 1) {
                    day = "0" + day;
                }
                tv.setText(year + "-" + month + "-" + day);
            }
        }
                // 设置初始日期
                , Calendar.getInstance(Locale.CHINA).get(Calendar.YEAR)
                , Calendar.getInstance(Locale.CHINA).get(Calendar.MONTH)
                , Calendar.getInstance(Locale.CHINA).get(Calendar.DAY_OF_MONTH)).show();
    }

    /**
     * 时间选择
     *
     * @param activity
     * @param tv
     */
    public static void showTimePickerDialog(Context activity, final TextView tv) {
        new TimePickerDialog(activity, R.style.ThemeDialog, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                tv.setText(hourOfDay + ":" + minute);
            }
        }
                // 设置初始时间
                , Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
                , Calendar.getInstance().get(Calendar.MINUTE)
                // true表示采用24小时制
                , true).show();
    }

}
