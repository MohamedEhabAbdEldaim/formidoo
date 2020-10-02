package com.midooabdaim.ardak.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.ui.fragment.userCycle.LoginFragment;
import com.midooabdaim.ardak.ui.fragment.userCycle.RegisterFragment;

import static com.midooabdaim.ardak.helper.HelperMethod.replaceFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(getSupportFragmentManager(), R.id.main_activity_fl_id, new LoginFragment());

    }
}