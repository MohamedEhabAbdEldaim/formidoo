package com.midooabdaim.ardak.ui.fragment;

import androidx.fragment.app.Fragment;

import com.midooabdaim.ardak.ui.activity.BaseActivity;


public class BaseFragment extends Fragment {
    public BaseActivity baseActivity;

    public void backPressed() {
        baseActivity.superOnBackPressed();
    }

    public void initialFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;

    }


}
