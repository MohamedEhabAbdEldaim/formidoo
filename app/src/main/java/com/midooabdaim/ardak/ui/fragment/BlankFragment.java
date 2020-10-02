package com.midooabdaim.ardak.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.midooabdaim.ardak.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BlankFragment extends BaseFragment {

    Unbinder unbinder;

    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initialFragment();
        View view = inflater.inflate(R.layout.fragment_blank, container, false);
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