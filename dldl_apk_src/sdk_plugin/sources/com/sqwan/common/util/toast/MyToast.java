package com.sqwan.common.util.toast;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.sq.sdk.tool.util.DisplayUtil;
import com.sqwan.common.util.SqResUtils;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class MyToast {
    private static final int LONG_DELAY = 3500;
    private static final int SHORT_DELAY = 2000;
    private static String oldMsg;
    private static long oneTime;
    private static Field sField_TN;
    private static Field sField_TN_Handler;
    protected static Toast toast;
    private static ViewGroup toastView;
    private static TextView tvMsg;
    private static long twoTime;

    static {
        try {
            Field declaredField = Toast.class.getDeclaredField("mTN");
            sField_TN = declaredField;
            declaredField.setAccessible(true);
            Field declaredField2 = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler = declaredField2;
            declaredField2.setAccessible(true);
        } catch (Exception unused) {
        }
    }

    private static void hook(Toast toast2) {
        try {
            Object obj = sField_TN.get(toast2);
            sField_TN_Handler.set(obj, new SafelyHandlerWarpper((Handler) sField_TN_Handler.get(obj)));
        } catch (Exception unused) {
        }
    }

    public static void showToast(Context context, String str, int i, int i2, int i3) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (toast == null) {
            toast = Toast.makeText(context, "", 0);
            ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(SqResUtils.getLayoutId(context, "sy37_s_toast_layout"), (ViewGroup) null, false);
            toastView = viewGroup;
            tvMsg = (TextView) viewGroup.findViewById(SqResUtils.getId(context, "tv_msg"));
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor("#BB000000"));
            gradientDrawable.setShape(0);
            gradientDrawable.setCornerRadius(DisplayUtil.dip2px(context, 6.0f));
            toastView.setBackground(gradientDrawable);
            tvMsg.setText(str);
            toast.setView(toastView);
            hook(toast);
            toast.setGravity(i, i2, i3);
            toast.show();
            oneTime = System.currentTimeMillis();
        } else {
            twoTime = System.currentTimeMillis();
            if (!str.equals(oldMsg)) {
                oldMsg = str;
                tvMsg.setText(str);
                toast.show();
            } else if (twoTime - oneTime > 2000) {
                toast.show();
            }
        }
        oneTime = twoTime;
    }
}
