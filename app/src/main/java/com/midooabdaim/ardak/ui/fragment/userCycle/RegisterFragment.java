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
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.midooabdaim.ardak.R;
import com.midooabdaim.ardak.data.model.Users;
import com.midooabdaim.ardak.ui.activity.HomeActivity;
import com.midooabdaim.ardak.ui.activity.MainActivity;
import com.midooabdaim.ardak.ui.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

import static com.midooabdaim.ardak.helper.Constant.Default_Image;
import static com.midooabdaim.ardak.helper.Constant.Users_Data;
import static com.midooabdaim.ardak.helper.HelperMethod.cleanError;
import static com.midooabdaim.ardak.helper.HelperMethod.customToast;
import static com.midooabdaim.ardak.helper.HelperMethod.disappearKeypad;
import static com.midooabdaim.ardak.helper.HelperMethod.dismissProgressDialog;
import static com.midooabdaim.ardak.helper.HelperMethod.replaceFragment;
import static com.midooabdaim.ardak.helper.HelperMethod.showProgressDialog;
import static com.midooabdaim.ardak.helper.HelperMethod.validationTextInputLayoutListEmpty;
import static com.midooabdaim.ardak.helper.InternetState.isActive;

public class RegisterFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_register_txt_input_user_name)
    TextInputLayout fragmentRegisterTxtInputUserName;
    @BindView(R.id.fragment_register_txt_input_email)
    TextInputLayout fragmentRegisterTxtInputEmail;
    @BindView(R.id.fragment_register_txt_input_phone)
    TextInputLayout fragmentRegisterTxtInputPhone;
    @BindView(R.id.fragment_register_txt_input_password)
    TextInputLayout fragmentRegisterTxtInputPassword;
    @BindView(R.id.fragment_register_txt_input_confirm_password)
    TextInputLayout fragmentRegisterTxtInputConfirmPassword;
    private List<TextInputLayout> textInputLayoutsList = new ArrayList<>();
    private FirebaseAuth auth;


    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        initialFragment();
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        unbinder = ButterKnife.bind(this, view);
        initView();
        return view;
    }

    private void initView() {
        auth = FirebaseAuth.getInstance();
        textInputLayoutsList.add(fragmentRegisterTxtInputUserName);
        textInputLayoutsList.add(fragmentRegisterTxtInputEmail);
        textInputLayoutsList.add(fragmentRegisterTxtInputPhone);
        textInputLayoutsList.add(fragmentRegisterTxtInputPassword);
        textInputLayoutsList.add(fragmentRegisterTxtInputConfirmPassword);
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

    @OnClick(R.id.fragment_register_btn_sign)
    public void onViewClicked(View view) {
        disappearKeypad(getActivity(), view);
        switch (view.getId()) {
            case R.id.fragment_register_btn_sign:
                register();
                break;
        }
    }

    private void register() {

        try {
            if (!isActive(getActivity())) {
                customToast(getActivity(), getString(R.string.nointernet), true);
                return;
            }
            cleanError(textInputLayoutsList);
            String username = fragmentRegisterTxtInputUserName.getEditText().getText().toString().trim();
            String email = fragmentRegisterTxtInputEmail.getEditText().getText().toString().trim();
            String phone = fragmentRegisterTxtInputPhone.getEditText().getText().toString().trim();
            String password = fragmentRegisterTxtInputPassword.getEditText().getText().toString().trim();
            String passwordconfirm = fragmentRegisterTxtInputConfirmPassword.getEditText().getText().toString().trim();
            if (!validationTextInputLayoutListEmpty(textInputLayoutsList, getString(R.string.empty))) {
                return;
            }
            if (isEmailExists(email)) {
                fragmentRegisterTxtInputEmail.setError(getString(R.string.exist));
                return;
            }
            if (password.length() < 6) {
                fragmentRegisterTxtInputPassword.setError(getString(R.string.week));
                return;
            }

            if (!password.equals(passwordconfirm)) {
                fragmentRegisterTxtInputConfirmPassword.setError(getString(R.string.notmatch));
                return;
            }
           // customToast(getActivity(), getString(R.string.virivi), false);
            addToDataBase2(username, email, phone, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

/*
    private void addToDataBase(String username, String email, String phone, String password) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();

// Configure faking the auto-retrieval with the whitelisted numbers.
        firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber("+201090017688", "123456");

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phone,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                getActivity(),               // Activity (for callback binding)
                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    */
/*@Override
                    public void onCodeSent(String verificationId,
                                           PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        // Save the verification id somewhere
                        // ...

                        // The corresponding whitelisted code above should be used to complete sign-in.

                    }
*//*



                    @Override
                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                        addToDataBase2(username, email, password, phone);

                    }

                    @Override
                    public void onVerificationFailed(@NonNull FirebaseException e) {

                    }
                });


    }
*/

    private void addToDataBase2(String username, String email, String phone, String password) {
        try {
            showProgressDialog(getActivity(), getString(R.string.wait));
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                String userid = firebaseUser.getUid();
                                Users user = new Users(username, email, Default_Image, phone, userid);
                                FirebaseDatabase.getInstance().getReference(Users_Data)
                                        .child(userid)
                                        .setValue(user)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    dismissProgressDialog();
                                                    customToast(getActivity(), getString(R.string.createdaccount), false);
                                                    Intent intent = new Intent(getActivity(), HomeActivity.class);
                                                    startActivity(intent);
                                                    getActivity().finish();
                                                } else {
                                                    dismissProgressDialog();
                                                    customToast(getActivity(), task.getException().getMessage(), true);
                                                }
                                            }
                                        });

                            } else {
                                dismissProgressDialog();
                                customToast(getActivity(), task.getException().getMessage(), true);
                            }
                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private boolean isEmailExists(String email) {
        final boolean[] emailExists = {false};

        try {
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference(Users_Data);

            reference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {

                        Users user = snapshot.getValue(Users.class);

                        assert user != null;

                        if (user.getEmail().trim().equals(email.trim())) {
                            emailExists[0] = true;
                            break;
                        }

                    }


                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        return emailExists[0];
    }
}