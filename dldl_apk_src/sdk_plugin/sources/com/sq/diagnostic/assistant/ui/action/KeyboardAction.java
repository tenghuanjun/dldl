package com.sq.diagnostic.assistant.ui.action;

import android.view.View;
import android.view.inputmethod.InputMethodManager;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public interface KeyboardAction {
    void hideKeyboard(View view);

    void showKeyboard(View view);

    /* JADX INFO: renamed from: com.sq.diagnostic.assistant.ui.action.KeyboardAction$-CC, reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$showKeyboard(KeyboardAction _this, View view) {
            InputMethodManager inputMethodManager;
            if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.showSoftInput(view, 2);
        }

        public static void $default$hideKeyboard(KeyboardAction _this, View view) {
            InputMethodManager inputMethodManager;
            if (view == null || (inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method")) == null) {
                return;
            }
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
