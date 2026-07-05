package com.sq.sdk.tool.ui;

import android.widget.Toast;
import com.sq.sdk.tool.util.SQContextWrapper;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class SqToast {
    public static void show(String str) {
        Toast.makeText(SQContextWrapper.getApplicationContext(), str, 0).show();
    }
}
