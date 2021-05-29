package com.tac.pickapp.ui.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.tac.pickapp.ui.auth.AuthActivity;
import com.tac.pickapp.util.Message;


public class Helper {

    public static void enableView(View view, boolean enabled) {
        view.setEnabled(enabled);

        if ( view instanceof ViewGroup) {
            ViewGroup group = (ViewGroup)view;

            for ( int idx = 0 ; idx < group.getChildCount() ; idx++ ) {
                enableView(group.getChildAt(idx), enabled);
            }
        }
    }

    public static void addTextWatcher(TextInputEditText editText, TextInputLayout editTextLayout) {
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editTextLayout.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    public static void resolveError(Activity activity, Throwable e) {
        String errMsg = "";
        DialogInterface.OnClickListener callback = null;

        if (e.getMessage().contains("Unable to resolve host")) {
            errMsg = Message.CANNOT_RESOLVE_HOST_ERROR;
        } else if (e.getMessage().contains("401 Unauthorized")) {
            errMsg = "Session expired. Please login again to continue.";
            callback = (dialog, which) -> {
                // PickApp.getInstance().logout();
                activity.startActivity(new Intent(activity, AuthActivity.class));
                activity.finish();
            };
        } else {
            errMsg = Message.SOMETHING_WENT_WRONG;
        }

        Helper.dialogAlert(activity, "", errMsg, callback);
    }

    /**
     * Dialog alerts
     */

    public static void dialogAlert(Context context, String title, String msg, DialogInterface.OnClickListener callback) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", callback)
                .create()
                .show();
    }

    public static void dialogAlert(Context context, String title, String msg) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(msg)
                .setCancelable(false)
                .setPositiveButton("OK", null)
                .create()
                .show();
    }



}
