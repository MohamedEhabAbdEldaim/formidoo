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
import static com.midooabdaim.ardak.helper.HelperMethod.switchCheckedChanged;

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
        switchCheckedChanged(homeActivitySwGawafaa, homeActivityTxtGawafaaTime, this, getString(R.string.gawafa), currentTime);
        switchCheckedChanged(homeActivitySwRoman, homeActivityTxtRomanTime, this, getString(R.string.roman), currentTime);
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