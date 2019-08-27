package com.nhapt.base.util;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.nhapt.base.R;


public class WidgetUtil {

    public static boolean verifyEditText(ViewGroup view) {
        boolean isValid = true;
        for (int i = 0; i < view.getChildCount(); i++) {
            View child = view.getChildAt(i);
            if (child instanceof EditText && child.getVisibility() == View.VISIBLE) {
                EditText editText = (EditText) child;
                if (editText.getText().toString().trim().isEmpty()) {
                    editText.setError(view.getContext().getString(R.string.mess_field_empty));
                    isValid = false;
                }
            }
        }
        return isValid;
    }

    public static boolean verifyEditText(TextView... editTexts) {
        boolean isValid = true;
        for (TextView editText : editTexts) {
            if (editText.getVisibility() == View.VISIBLE && editText.getText().toString().trim().isEmpty()) {
                editText.setError(editText.getContext().getString(R.string.mess_field_empty));
                isValid = false;
            }
        }
        return isValid;
    }
}
