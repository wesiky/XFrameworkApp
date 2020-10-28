package com.xframework.util;

import android.os.Build;
import android.text.InputType;
import android.widget.EditText;
import java.lang.reflect.Method;

public class EditTextHelper {
    public static void CloseKeyBoard(EditText editText) {
        if (Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
            return;
        }
        try {
            Method method = EditText.class.getMethod("setShowSoftInputOnFocus", boolean.class);
            method.setAccessible(true);
            method.invoke(editText, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
