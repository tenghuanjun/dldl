package com.duowan.live.one.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;
import com.duowan.auk.ArkValue;
import com.duowan.auk.ui.toast.ToastCompat;
import com.huya.mtp.utils.DensityUtil;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class ToastUtil {
    private static final int DURATION_CUSTOM = 3000;
    private static final int DURATION_SYSTEM = 1;
    private static boolean sIsToastVisible;
    private static String sLastShowString;
    private static Toast sToast;
    private static final Runnable sToastHideRunnable = new Runnable() { // from class: com.duowan.live.one.util.ToastUtil.1
        @Override // java.lang.Runnable
        public void run() {
            boolean unused = ToastUtil.sIsToastVisible = false;
            if (ToastUtil.sToast != null) {
                ToastUtil.sToast.cancel();
            }
        }
    };

    private static class ToastShowRunnable implements Runnable {
        private boolean mIsCenter;
        private String mText;

        public ToastShowRunnable(String str, boolean z) {
            this.mText = str;
            this.mIsCenter = z;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.mText.equals(ToastUtil.sLastShowString) && ToastUtil.sIsToastVisible) {
                return;
            }
            if (ToastUtil.sToast != null) {
                ToastUtil.sToast.cancel();
            }
            boolean unused = ToastUtil.sIsToastVisible = true;
            Toast unused2 = ToastUtil.sToast = makeToast(this.mText);
            if (this.mIsCenter) {
                ToastUtil.sToast.setGravity(17, 0, DensityUtil.dip2px(ArkValue.gContext, -50.0f));
            }
            ToastUtil.sToast.show();
            ArkValue.gMainHandler.postDelayed(ToastUtil.sToastHideRunnable, 3000L);
        }

        private static Toast makeToast(String str) {
            String unused = ToastUtil.sLastShowString = str;
            Toast unused2 = ToastUtil.sToast = ToastCompat.makeText((Context) ArkValue.gContext, (CharSequence) str, 1);
            return ToastUtil.sToast;
        }
    }

    public static void showToast(int i, boolean z) {
        showToast(ArkValue.gContext.getString(i), z);
    }

    public static void showToast(String str, boolean z) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        ArkValue.gMainHandler.post(new ToastShowRunnable(str, z));
    }

    public static void showToast(int i) {
        showToast(ArkValue.gContext.getString(i));
    }

    public static void showToast(String str) {
        showToast(str, false);
    }
}
