package com.midooabdaim.ardak.helper;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.text.format.DateFormat;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.widget.SwitchCompat;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static android.text.format.DateFormat.is24HourFormat;

public class GetDayAndTime {


    public static void showCalender(Context context, String title, final TextView text_view_data, final DateTxt data1) {
        DatePickerDialog mDatePicker = new DatePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedYear, int selectedMonth, int selectedDay) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat mFormat = new DecimalFormat("00", symbols);
                String data = selectedYear + "-" + mFormat.format(Double.valueOf((selectedMonth + 1))) + "-" + mFormat.format(Double.valueOf(selectedDay));
                data1.setDay(mFormat.format(Double.valueOf(selectedDay)));
                data1.setMonth(mFormat.format(Double.valueOf(selectedMonth + 1)));
                data1.setYear(String.valueOf(selectedYear));
                text_view_data.setText(data);
                showTimePicker(context, title, text_view_data, data1, null);
            }
        }, Integer.parseInt(data1.getYear()), Integer.parseInt(data1.getMonth()) - 1, Integer.parseInt(data1.getDay()));
        mDatePicker.setTitle(title);
        mDatePicker.show();
        mDatePicker.setCanceledOnTouchOutside(false);
    }

    public static void showTimePicker(Context context, String title, final TextView text_view_time, final DateTxt dateTxt, SwitchCompat switchCompat) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(context, AlertDialog.THEME_HOLO_DARK, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
                DecimalFormat mFormat = new DecimalFormat("00", symbols);
                String time;
                if (hourOfDay > 12) {
                    time = mFormat.format(Double.valueOf(hourOfDay - 12)) + ":" + mFormat.format(Double.valueOf(minute)) + " PM";
                    dateTxt.setHour(mFormat.format(Double.valueOf(hourOfDay - 12)));
                } else {
                    time = mFormat.format(Double.valueOf(hourOfDay)) + ":" + mFormat.format(Double.valueOf(minute)) + " AM";
                    dateTxt.setHour(mFormat.format(Double.valueOf(hourOfDay)));
                }
                dateTxt.setMint(mFormat.format(Double.valueOf(minute)));
                String text = text_view_time.getText().toString().trim() + " " + time;
                dateTxt.setDate_txt(text);
                text_view_time.setText(text);

                // switchCompat.setChecked(true);

            }
        }, Integer.parseInt(dateTxt.getHour()), Integer.parseInt(dateTxt.getMint()), is24HourFormat(context));
        timePickerDialog.setTitle(title);
        timePickerDialog.show();
        timePickerDialog.setCanceledOnTouchOutside(false);

    }

    public static Date convertDateString(String date) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm a");

            Date parse = format.parse(date);

            return parse;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static DateTxt convertStringToDateTxtModel(String date) {
        try {
            Date date1 = convertDateString(date);
            String day = (String) DateFormat.format("dd", date1); // 20
            String monthNumber = (String) DateFormat.format("MM", date1); // 06
            String year = (String) DateFormat.format("yyyy", date1); // 2013
            String hour = (String) DateFormat.format("hh", date1); // 05
            String mint = (String) DateFormat.format("mm", date1); // 23
            return new DateTxt(day, monthNumber, year, hour, mint, date);

        } catch (Exception e) {
            return null;
        }
    }

}
