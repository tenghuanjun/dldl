package com.duowan.auk.ui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.duowan.auk.ArkValue;
import com.duowan.auk.ui.toast.ToastCompat;
import com.duowan.auk.util.L;
import com.huya.live.common.api.BaseApi;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ArkToast {
    private static final String TAG = "ArkToast";
    private static String defaultText = "";
    private static int sLayoutId;
    private static Toast sToast;

    public static void setDefaultText(String str) {
    }

    public static void setLayoutId(int i) {
        sLayoutId = i;
    }

    public static void show(int i) {
        show(ArkValue.gContext.getString(i));
    }

    public static void show(CharSequence charSequence) {
        show(charSequence, 0);
    }

    public static void show(int i, int i2) {
        show(ArkValue.gContext.getString(i), i2);
    }

    public static void show(CharSequence charSequence, int i) {
        show(charSequence, i, 0, 0);
    }

    public static void show(final CharSequence charSequence, final int i, final int i2, final int i3) {
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.duowan.auk.ui.widget.ArkToast.1
            @Override // java.lang.Runnable
            public void run() {
                BaseApi.crashIfNotInMainThreadDebug("must in main thread!", new Object[0]);
                if (ArkToast.sToast == null) {
                    ArkToast.createToast();
                }
                try {
                    ArkToast.sToast.setGravity(17, i2, i3);
                    ArkToast.sToast.setText(charSequence);
                    ArkToast.sToast.setDuration(i);
                    ArkToast.sToast.show();
                } catch (Exception e) {
                    L.error(ArkToast.TAG, (Throwable) e);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void createToast() {
        L.info(TAG, "createToast");
        ToastCompat toastCompatMakeText = ToastCompat.makeText((Context) ArkValue.gContext, (CharSequence) defaultText, 0);
        sToast = toastCompatMakeText;
        if (sLayoutId != 0) {
            toastCompatMakeText.setView(createToastView());
        }
    }

    private static View createToastView() {
        return LayoutInflater.from(ArkValue.gContext).inflate(sLayoutId, (ViewGroup) null);
    }
}
