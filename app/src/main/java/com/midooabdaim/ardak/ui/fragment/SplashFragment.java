package com.midooabdaim.ardak.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.ui.activity.HomeActivity;
import com.midooabdaim.ardak.ui.activity.MainActivity;
import com.midooabdaim.ardak.ui.fragment.userCycle.LoginFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.midooabdaim.ardak.helper.HelperMethod.customToast;
import static com.midooabdaim.ardak.helper.HelperMethod.replaceFragment;
import static com.midooabdaim.ardak.helper.InternetState.isActive;

public class SplashFragment extends BaseFragment {

    Unbinder unbinder;
    private FirebaseAuth mAuth;


    public SplashFragment() {
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
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser == null) {
            // No user is signed in
            replaceFragment(getActivity().getSupportFragmentManager(), R.id.main_activity_fl_id, new LoginFragment());
        } else {
            if (!isActive(getActivity())) {
                customToast(getActivity(), getString(R.string.nointernet), true);
            }
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void backPressed() {
        getActivity().finish();
    }
}