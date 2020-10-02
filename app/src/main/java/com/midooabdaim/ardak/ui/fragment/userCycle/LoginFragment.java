package com.midooabdaim.ardak.ui.fragment.userCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.ui.activity.HomeActivity;
import com.midooabdaim.ardak.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.midooabdaim.ardak.helper.HelperMethod.cleanError;
import static com.midooabdaim.ardak.helper.HelperMethod.customToast;
import static com.midooabdaim.ardak.helper.HelperMethod.disappearKeypad;
import static com.midooabdaim.ardak.helper.HelperMethod.dismissProgressDialog;
import static com.midooabdaim.ardak.helper.HelperMethod.replaceFragment;
import static com.midooabdaim.ardak.helper.HelperMethod.showProgressDialog;
import static com.midooabdaim.ardak.helper.HelperMethod.validationTextInputLayoutListEmpty;
import static com.midooabdaim.ardak.helper.InternetState.isActive;

public class LoginFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_login_txt_input_email)
    TextInputLayout fragmentLoginTxtInputEmail;
    @BindView(R.id.fragment_login_txt_input_password)
    TextInputLayout fragmentLoginTxtInputPassword;

    private List<TextInputLayout> textInputLayoutsList = new ArrayList<>();
    private FirebaseAuth auth;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initialFragment();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        auth = FirebaseAuth.getInstance();
        textInputLayoutsList.add(fragmentLoginTxtInputEmail);
        textInputLayoutsList.add(fragmentLoginTxtInputPassword);
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void backPressed() {
        getActivity().finish();
    }

    @OnClick({R.id.fragment_login_text_view_forget_pass, R.id.fragment_login_btn_login, R.id.fragment_login_text_view_create_account})
    public void onViewClicked(View view) {
        disappearKeypad(getActivity(), view);
        switch (view.getId()) {
            case R.id.fragment_login_text_view_forget_pass:
                break;
            case R.id.fragment_login_btn_login:
                login();
                break;
            case R.id.fragment_login_text_view_create_account:
                replaceFragment(getActivity().getSupportFragmentManager(), R.id.main_activity_fl_id, new RegisterFragment());
                break;
        }
    }

    private void login() {

        try {
            if (!isActive(getActivity())) {
                customToast(getActivity(), getString(R.string.nointernet), true);
                return;
            } else {
                cleanError(textInputLayoutsList);
                String email = fragmentLoginTxtInputEmail.getEditText().getText().toString().trim();
                String password = fragmentLoginTxtInputPassword.getEditText().getText().toString().trim();
                if (!validationTextInputLayoutListEmpty(textInputLayoutsList, getString(R.string.empty))) {
                    return;
                }
                checkInDataBase(email, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkInDataBase(String email, String password) {
        showProgressDialog(getActivity(), getString(R.string.wait));
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            dismissProgressDialog();
                            customToast(getActivity(), getString(R.string.welcome), false);
                            Intent intent = new Intent(getActivity(), HomeActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            dismissProgressDialog();
                            customToast(getActivity(), task.getException().getMessage(), true);
                        }
                    }
                });

    }


}