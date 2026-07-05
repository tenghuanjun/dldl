package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class e {
    private static boolean a;

    static {
        a = Build.VERSION.SDK_INT >= 11;
    }

    public static Dialog a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builderA = a(context, str, str3, onClickListener, str4, onClickListener2);
        builderA.setTitle(str);
        builderA.setMessage(str2);
        AlertDialog alertDialogCreate = builderA.create();
        alertDialogCreate.setCanceledOnTouchOutside(false);
        alertDialogCreate.setOnKeyListener(new f());
        try {
            alertDialogCreate.show();
        } catch (Throwable th) {
            com.alipay.sdk.util.c.a("msp", th);
        }
        return alertDialogCreate;
    }

    private static AlertDialog.Builder a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (a) {
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setPositiveButton(str3, onClickListener2);
            }
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setNegativeButton(str2, onClickListener);
            }
        } else {
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setPositiveButton(str2, onClickListener);
            }
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setNegativeButton(str3, onClickListener2);
            }
        }
        return builder;
    }
}
