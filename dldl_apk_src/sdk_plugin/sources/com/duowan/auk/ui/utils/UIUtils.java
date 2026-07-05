package com.duowan.auk.ui.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import com.duowan.auk.util.L;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class UIUtils {
    public static View inflate(Context context, int i) {
        return inflate(context, i, null);
    }

    public static View inflate(Context context, int i, ViewGroup viewGroup) {
        return inflate(context, i, viewGroup, viewGroup != null);
    }

    public static View inflate(Context context, int i, ViewGroup viewGroup, boolean z) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService("layout_inflater");
        if (layoutInflater == null) {
            throw new AssertionError("LayoutInflater not found.");
        }
        return layoutInflater.inflate(i, viewGroup, z);
    }

    public static void removeFormParent(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeView(view);
        }
    }

    public static void getLocationOfView(Activity activity, View view, int[] iArr) {
        view.getLocationInWindow(iArr);
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        iArr[1] = iArr[1] - rect.top;
    }

    public static void setTextViewText(TextView textView, int i, Object... objArr) {
        try {
            textView.setText(textView.getContext().getString(i, objArr));
        } catch (Exception e) {
            L.error(textView, "setText fail: %s", e);
        }
    }

    public static void setTextViewText(TextView textView, String str, Object... objArr) {
        textView.setText(String.format(str, objArr));
    }

    public static void showKeyBoard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 2);
    }

    public static void showKeyBoard(Context context) {
        ((InputMethodManager) context.getSystemService("input_method")).toggleSoftInput(2, 2);
    }

    public static void hideKeyboard(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
