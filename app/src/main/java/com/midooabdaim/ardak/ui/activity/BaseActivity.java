package com.midooabdaim.ardak.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.midooabdaim.ardak.ui.fragment.BaseFragment;


public class BaseActivity extends AppCompatActivity {
    public BaseFragment baseFragment;

    @Override
    public void onBackPressed() {
        baseFragment.backPressed();
    }

    public void superOnBackPressed() {
        super.onBackPressed();
    }
}
