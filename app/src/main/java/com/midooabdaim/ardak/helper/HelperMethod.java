package com.midooabdaim.ardak.helper;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import android.text.Html;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.bumptech.glide.Glide;
import com.google.android.material.textfield.TextInputLayout;
import com.midooabdaim.ardak.R;

import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static com.google.android.gms.common.util.Base64Utils.decode;
import static com.google.android.gms.common.util.Base64Utils.encode;

public class HelperMethod {
    private static ProgressDialog checkDialog;

    public static void replaceFragment(FragmentManager getChildFragmentManager, int id, Fragment fragment) {
        FragmentTransaction transaction = getChildFragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public static void onLoadImageFromUrl(ImageView imageView, String URl, Context context) {
        Glide.with(context)
                .load(URl)
                .into(imageView);
    }

    public static void showProgressDialog(Activity activity, String title) {
        try {

            checkDialog = new ProgressDialog(activity);
            checkDialog.setMessage(title);
            checkDialog.setIndeterminate(false);
            checkDialog.setCancelable(false);

            checkDialog.show();

        } catch (Exception e) {

        }
    }

    public static void dismissProgressDialog() {
        try {

            checkDialog.dismiss();

        } catch (Exception e) {

        }
    }

    public static void disappearKeypad(Activity activity, View v) {
        try {
            if (v != null) {
                InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                //  this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            }
        } catch (Exception e) {

        }
    }

    public static void customToast(Activity activity, String ToastTitle, boolean failed) {

        LayoutInflater inflater = activity.getLayoutInflater();

        int layout_id;

        if (failed) {
            layout_id = R.layout.toast;
        } else {
            layout_id = R.layout.success_toast;
        }

        View layout = inflater.inflate(layout_id,
                (ViewGroup) activity.findViewById(R.id.toast_layout_root));

        TextView text = layout.findViewById(R.id.text);
        text.setText(ToastTitle);

        Toast toast = new Toast(activity);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM, 0, 100);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    public static void onPermission(Activity activity) {
        String[] perms = {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.ACCESS_COARSE_LOCATION
        };

        ActivityCompat.requestPermissions(activity,
                perms,
                100);

    }

    public static void cleanError(List<TextInputLayout> textInputLayoutList) {
        for (int i = 0; i < textInputLayoutList.size(); i++) {
            textInputLayoutList.get(i).setErrorEnabled(false);
        }
    }

    public static boolean validationTextInputLayoutListEmpty(List<TextInputLayout> textInputLayoutList, String errorText) {

        List<Boolean> booleans = new ArrayList<>();

        for (int i = 0; i < textInputLayoutList.size(); i++) {
            if (!validationLength(textInputLayoutList.get(i), errorText)) {
                booleans.add(false);
            } else {
                booleans.add(true);
            }
        }

        if (booleans.contains(false) && !booleans.contains(true)) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean validationLength(TextInputLayout text, String errorText) {
        if (text.getEditText().length() <= 0) {
            text.setError(errorText);
            return false;
        } else {
            return true;
        }
    }

    public static String encrypt(String value) {
        String key = "aesEncryptionKey";
        String initVector = "encryptionIntVec";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            return encode(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String value) {
        String key = "aesEncryptionKey";
        String initVector = "encryptionIntVec";
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(decode(value));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    public static String AssetJSONFile(String filename, Context context) throws IOException {
        AssetManager manager = context.getAssets();
        InputStream file = manager.open(filename);
        byte[] formArray = new byte[file.available()];
        file.read(formArray);
        file.close();
        return new String(formArray, "UTF-8");
    }

}
