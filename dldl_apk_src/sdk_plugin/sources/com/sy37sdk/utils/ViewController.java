package com.sy37sdk.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.sqwan.common.util.ToastUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Deprecated
public class ViewController {
    public static View inflate(Activity activity, int i) {
        return activity.getLayoutInflater().inflate(i, (ViewGroup) null);
    }

    public static View inflate(Context context, int i) {
        return ((Activity) context).getLayoutInflater().inflate(i, (ViewGroup) null);
    }

    @Deprecated
    public static void showToast(Context context, String str) {
        try {
            ToastUtil.showToast(context, str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
