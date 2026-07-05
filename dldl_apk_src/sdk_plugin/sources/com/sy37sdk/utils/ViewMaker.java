package com.sy37sdk.utils;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import java.util.Calendar;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class ViewMaker {
    static DatePickerDialog datedialog;
    static int day;
    static int month;
    static int year;

    public static void showDateSetDialog(Context context, final EditText editText) {
        Calendar.getInstance();
        String string = editText.getText().toString();
        if (!string.equals("")) {
            inputBrithday(context, string);
            month--;
        } else {
            year = Util.getYear(context);
            month = Util.getMonth(context) - 1;
            day = Util.getMonthOfday(context);
        }
        DatePickerDialog datePickerDialog = new DatePickerDialog(context, new DatePickerDialog.OnDateSetListener() { // from class: com.sy37sdk.utils.ViewMaker.1
            @Override // android.app.DatePickerDialog.OnDateSetListener
            public void onDateSet(DatePicker datePicker, int i, int i2, int i3) {
                String time = ViewMaker.toTime(i3);
                int i4 = i2 + 1;
                String time2 = ViewMaker.toTime(i4);
                editText.setText(i + "-" + time2 + "-" + time);
                ViewMaker.year = i;
                ViewMaker.month = i4 + (-1);
                ViewMaker.day = i3;
            }
        }, year, month, day);
        datedialog = datePickerDialog;
        datePickerDialog.setCancelable(false);
        datedialog.setCanceledOnTouchOutside(false);
        datedialog.show();
    }

    public static void showTimeSetDialog(Context context, final EditText editText) {
        Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(context, new TimePickerDialog.OnTimeSetListener() { // from class: com.sy37sdk.utils.ViewMaker.2
            @Override // android.app.TimePickerDialog.OnTimeSetListener
            public void onTimeSet(TimePicker timePicker, int i, int i2) {
                editText.setText(i + ":" + i2);
            }
        }, calendar.get(11), calendar.get(12), true).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String toTime(int i) {
        if (i > 0 && i < 10) {
            return "0" + i;
        }
        return "" + i;
    }

    public static void inputBrithday(Context context, String str) {
        String[] strArrSplit = str.split("-");
        if (strArrSplit == null || strArrSplit.length < 3) {
            return;
        }
        year = Integer.parseInt(strArrSplit[0]);
        month = Integer.parseInt(strArrSplit[1]);
        day = Integer.parseInt(strArrSplit[2]);
        Util.putYear(context, year);
        Util.putMonth(context, month);
        Util.putMonthOfday(context, day);
    }
}
