package com.midooabdaim.ardak.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.helper.HelperMethod;
import com.midooabdaim.ardak.helper.TimeTXT;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.midooabdaim.ardak.helper.HelperMethod.convertStringToDateTxtModel;
import static com.midooabdaim.ardak.helper.HelperMethod.isSameTime;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.home_activity_txt_gawafaa_time)
    TextView homeActivityTxtGawafaaTime;
    @BindView(R.id.home_activity_txt_roman_time)
    TextView homeActivityTxtRomanTime;
    @BindView(R.id.home_activity_sw_gawafaa)
    SwitchCompat homeActivitySwGawafaa;
    @BindView(R.id.home_activity_sw_roman)
    SwitchCompat homeActivitySwRoman;

    TimeTXT currentTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        intialView();
    }

    private void intialView() {
        Calendar instance = Calendar.getInstance();
        int hour = instance.get(Calendar.HOUR_OF_DAY);
        int minute = instance.get(Calendar.MINUTE);
        currentTime = new TimeTXT(String.valueOf(hour), String.valueOf(minute), hour + ":" + minute);
        homeActivityTxtGawafaaTime.setText("00:00");
        homeActivityTxtRomanTime.setText("00:00");
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.US);
        DecimalFormat mFormat = new DecimalFormat("00", symbols);

        homeActivitySwGawafaa.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Handler handler = new Handler();
                int delay = 5000; //milliseconds
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Calendar instance = Calendar.getInstance();
                        int hour = instance.get(Calendar.HOUR_OF_DAY);
                        int minute = instance.get(Calendar.MINUTE);
                        TimeTXT txt;
                        if (hour > 12) {
                            txt = new TimeTXT(String.valueOf(mFormat.format(Double.valueOf(hour - 12))), String.valueOf(mFormat.format(Double.valueOf(minute))), mFormat.format(Double.valueOf(hour - 12))+ ":" + mFormat.format(Double.valueOf(minute)) + " PM");
                        } else {
                            txt = new TimeTXT(String.valueOf(mFormat.format(Double.valueOf(hour - 12))), String.valueOf(mFormat.format(Double.valueOf(minute))), mFormat.format(Double.valueOf(hour))+ ":" + mFormat.format(Double.valueOf(minute)) + " AM");
                        }
                        if (isSameTime(txt, convertStringToDateTxtModel(homeActivityTxtGawafaaTime.getText().toString().trim()))) {
                            homeActivitySwGawafaa.setChecked(false);
                        }
                        handler.postDelayed(this::run, delay);
                    }
                };

                if (isChecked) {
                    handler.postDelayed(runnable, delay);
                } else {
                    handler.removeCallbacks(runnable);
                    homeActivityTxtGawafaaTime.setText("00:00");
                }
            }
        });
        homeActivitySwRoman.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Handler handler = new Handler();
                int delay = 5000; //milliseconds
                final Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        Calendar instance = Calendar.getInstance();
                        int hour = instance.get(Calendar.HOUR_OF_DAY);
                        int minute = instance.get(Calendar.MINUTE);

                        TimeTXT txt;
                        if (hour > 12) {
                            txt = new TimeTXT(String.valueOf(mFormat.format(Double.valueOf(hour - 12))), String.valueOf(mFormat.format(Double.valueOf(minute))), mFormat.format(Double.valueOf(hour - 12))+ ":" + mFormat.format(Double.valueOf(minute)) + " PM");
                        } else {
                            txt = new TimeTXT(String.valueOf(mFormat.format(Double.valueOf(hour - 12))), String.valueOf(mFormat.format(Double.valueOf(minute))), mFormat.format(Double.valueOf(hour))+ ":" + mFormat.format(Double.valueOf(minute)) + " AM");
                        }
                        if (isSameTime(txt, convertStringToDateTxtModel(homeActivityTxtRomanTime.getText().toString().trim()))) {
                            homeActivitySwRoman.setChecked(false);
                        }
                        handler.postDelayed(this::run, delay);
                    }
                };

                if (isChecked) {
                    handler.postDelayed(runnable, delay);
                } else {
                    handler.removeCallbacks(runnable);
                    homeActivityTxtRomanTime.setText("00:00");
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        this.finish();
    }

    @OnClick({R.id.home_activity_txt_gawafaa_time, R.id.home_activity_txt_roman_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.home_activity_txt_gawafaa_time:
                HelperMethod.showTimePicker(this, getString(R.string.gawafa), homeActivityTxtGawafaaTime, currentTime, homeActivitySwGawafaa);
                break;
            case R.id.home_activity_txt_roman_time:
                HelperMethod.showTimePicker(this, getString(R.string.roman), homeActivityTxtRomanTime, currentTime, homeActivitySwRoman);

                break;
        }
    }
}