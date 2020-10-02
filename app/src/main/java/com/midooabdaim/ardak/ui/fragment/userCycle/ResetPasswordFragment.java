package com.midooabdaim.ardak.ui.fragment.userCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.ui.fragment.BaseFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ResetPasswordFragment extends BaseFragment {

    Unbinder unbinder;

    public ResetPasswordFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initialFragment();
        View view = inflater.inflate(R.layout.fragment_reset_and_verification, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void backPressed() {
        super.backPressed();
    }
}