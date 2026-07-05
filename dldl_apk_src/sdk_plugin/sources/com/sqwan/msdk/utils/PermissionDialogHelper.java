package com.sqwan.msdk.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.sqwan.common.dialog.CommonAlertDialog;
import com.sqwan.common.util.SpanUtil;
import com.sy37sdk.utils.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PermissionDialogHelper {

    public interface CheckPermissionCallback {
        void toCheck();
    }

    public static void showPermissionPreviewDialog(final Context context, final CheckPermissionCallback checkPermissionCallback) {
        post(new Runnable() { // from class: com.sqwan.msdk.utils.PermissionDialogHelper.1
            @Override // java.lang.Runnable
            public void run() {
                int iDip2px = DensityUtil.dip2px(context, 18.0f);
                int iDip2px2 = DensityUtil.dip2px(context, 14.0f);
                new CommonAlertDialog.Builder(context).setTitle(SpanUtil.concat(SpanUtil.getFontString("将向你申请以下权限和信息：\n\n", iDip2px, -16777216, true), SpanUtil.getFontString("·IMEI权限：", iDip2px2, -16777216, true), SpanUtil.getFontString("手机标识用于保护账号安全\n", iDip2px2, -16777216, false), SpanUtil.getFontString("·存储权限：", iDip2px2, -16777216, true), SpanUtil.getFontString("实现账号、图片的缓存和使用，快速登录、降低流量消耗\n\n", iDip2px2, -16777216, false), SpanUtil.getFontString("·其他信息：Android ID、MAC地址、IMEI、IMSI", iDip2px2, -7829368, false))).setNegativeButton(SpanUtil.getFontString("好的", iDip2px2, Color.parseColor("#2A72FF")), new View.OnClickListener() { // from class: com.sqwan.msdk.utils.PermissionDialogHelper.1.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        if (checkPermissionCallback != null) {
                            checkPermissionCallback.toCheck();
                        }
                    }
                }).setPositiveButton(SpanUtil.getFontString("退出游戏", iDip2px2, -16777216), new View.OnClickListener() { // from class: com.sqwan.msdk.utils.PermissionDialogHelper.1.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        PermissionDialogHelper.exit(context);
                    }
                }).setCancelable(false).show();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void exit(Context context) {
        ((Activity) context).finish();
        System.exit(0);
    }

    private static void post(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }
}
