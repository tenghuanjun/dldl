package com.sqwan.common.util;

import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class ClickUtils {
    private static long fastClickTime = 500;

    public static void stePreviewClickListener(final View view, final View.OnClickListener onClickListener) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.util.ClickUtils.1
            long lastTime = 0;

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (Math.abs(SystemClock.elapsedRealtime() - this.lastTime) > ClickUtils.fastClickTime) {
                    this.lastTime = SystemClock.elapsedRealtime();
                    onClickListener.onClick(view);
                }
            }
        });
    }

    public static void stePreviewClickListener(final View view, final long j, final String str, final View.OnClickListener onClickListener) {
        view.setOnClickListener(new View.OnClickListener() { // from class: com.sqwan.common.util.ClickUtils.2
            long lastTime = 0;

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (Math.abs(SystemClock.elapsedRealtime() - this.lastTime) > j) {
                    this.lastTime = SystemClock.elapsedRealtime();
                    onClickListener.onClick(view);
                } else {
                    if (TextUtils.isEmpty(str)) {
                        return;
                    }
                    ToastUtil.showToast(str);
                }
            }
        });
    }

    public static void stePreviewEditorActionListener(EditText editText, final long j, final TextView.OnEditorActionListener onEditorActionListener) {
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() { // from class: com.sqwan.common.util.ClickUtils.3
            long lastTime = 0;

            @Override // android.widget.TextView.OnEditorActionListener
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return false;
                }
                if (Math.abs(SystemClock.elapsedRealtime() - this.lastTime) <= j) {
                    return true;
                }
                this.lastTime = SystemClock.elapsedRealtime();
                onEditorActionListener.onEditorAction(textView, i, keyEvent);
                return true;
            }
        });
    }
}
