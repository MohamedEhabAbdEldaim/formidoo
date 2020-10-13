package com.midooabdaim.ardak.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SwitchCompat;

import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.data.service.Alarm;
import com.midooabdaim.ardak.helper.DateTxt;
import com.midooabdaim.ardak.helper.GetDayAndTime;
import com.midooabdaim.ardak.helper.TimeTXT;

import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.midooabdaim.ardak.helper.GetDayAndTime.convertDateString;
import static com.midooabdaim.ardak.helper.GetDayAndTime.convertStringToDateTxtModel;
import static java.util.Calendar.AM;

public class HomeActivity extends BaseActivity {


    @BindView(R.id.home_activity_sw_gawafaa)
    SwitchCompat homeActivitySwGawafaa;
    @BindView(R.id.home_activity_sw_roman)
    SwitchCompat homeActivitySwRoman;
    @BindView(R.id.home_activity_txt_gawafaa_time_from)
    TextView homeActivityTxtGawafaaTimeFrom;
    @BindView(R.id.home_activity_txt_gawafaa_time_to)
    TextView homeActivityTxtGawafaaTimeTo;
    @BindView(R.id.home_activity_txt_roman_time_from)
    TextView homeActivityTxtRomanTimeFrom;
    @BindView(R.id.home_activity_txt_roman_time_to)
    TextView homeActivityTxtRomanTimeTo;

    DateTxt dateTxtGawafaa, dateTxtRoman;
    Alarm alarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        intialView();
        getIntentValue();
    }

    private void getIntentValue() {
    //    Intent intent = getIntent();
       // boolean message = intent.getBooleanExtra("MessageFragment", false);
    }

    private void intialView() {
        alarm = new Alarm();
        Date date = new Date();
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        int hour = instance.get(Calendar.HOUR);
        int minute = instance.get(Calendar.MINUTE);
        int year = instance.get(Calendar.YEAR);
        int month = instance.get(Calendar.MONTH);
        int day = instance.get(Calendar.DAY_OF_MONTH);
        int am_pm = instance.get(Calendar.AM_PM);
        dateTxtGawafaa = new DateTxt(String.valueOf(day), String.valueOf(month), String.valueOf(year), String.valueOf(hour), String.valueOf(minute), day + "-" + month + "-" + year + " " + hour + ":" + minute + " " + am_pm);
        dateTxtRoman = new DateTxt(String.valueOf(day), String.valueOf(month), String.valueOf(year), String.valueOf(hour), String.valueOf(minute), day + "-" + month + "-" + year + " " + hour + ":" + minute + " " + am_pm);

        // switchCheckedChanged(homeActivitySwGawafaa, homeActivityTxtGawafaaTime, this, getString(R.string.gawafa), currentTime);
        // switchCheckedChanged(homeActivitySwRoman, homeActivityTxtRomanTime, this, getString(R.string.roman), currentTime);
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @OnClick({R.id.home_activity_txt_gawafaa_time_from, R.id.home_activity_txt_gawafaa_time_to, R.id.home_activity_txt_roman_time_from, R.id.home_activity_txt_roman_time_to})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_activity_txt_gawafaa_time_from:
                GetDayAndTime.showCalender(this, getString(R.string.choose), homeActivityTxtGawafaaTimeFrom, dateTxtGawafaa);
                alarm.setAlarm(this, convertDateString(homeActivityTxtGawafaaTimeFrom.getText().toString().trim()), getString(R.string.gawafafrom));
                break;
            case R.id.home_activity_txt_gawafaa_time_to:
                GetDayAndTime.showCalender(this, getString(R.string.choose), homeActivityTxtGawafaaTimeTo, dateTxtGawafaa);
                alarm.setAlarm(this, convertDateString(homeActivityTxtGawafaaTimeFrom.getText().toString().trim()), getString(R.string.gawafato));
                break;
            case R.id.home_activity_txt_roman_time_from:
                GetDayAndTime.showCalender(this, getString(R.string.choose), homeActivityTxtRomanTimeFrom, dateTxtRoman);
                break;
            case R.id.home_activity_txt_roman_time_to:
                GetDayAndTime.showCalender(this, getString(R.string.choose), homeActivityTxtRomanTimeTo, dateTxtRoman);
                break;
        }
    }


}