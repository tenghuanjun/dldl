package com.bytedance.applog.util;

import android.content.Context;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.view.Display;
import android.view.WindowManager;
import androidx.autofill.HintConstants;
import com.bytedance.applog.log.LoggerImpl;
import com.bytedance.bdtracker.v3;
import java.util.Collections;

/* JADX INFO: loaded from: classes2.dex */
public class HardwareUtils {
    public static final String GLOBAL_CACHE_GET_ANDROID_ID = "Secure.getString_android_id";

    public static class a implements v3.a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f195a;

        public a(Context context) {
            this.f195a = context;
        }

        @Override // com.bytedance.bdtracker.v3.a
        public String a() {
            LoggerImpl.global().debug(Collections.singletonList("HardwareUtils"), "[DeviceMeta] Try to get android id by secure.getString", new Object[0]);
            return Settings.Secure.getString(this.f195a.getContentResolver(), "android_id");
        }
    }

    public static String getOperatorMccMnc(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperator();
        }
        return null;
    }

    public static String getOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE);
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }

    public static int getScreenOrientation(Context context) {
        Display defaultDisplay;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        if (windowManager == null || (defaultDisplay = windowManager.getDefaultDisplay()) == null) {
            return 0;
        }
        return defaultDisplay.getWidth() <= defaultDisplay.getHeight() ? 1 : 2;
    }

    public static String getSecureAndroidId(Context context) {
        try {
            return v3.a(context).a(GLOBAL_CACHE_GET_ANDROID_ID, new a(context));
        } catch (Throwable th) {
            LoggerImpl.global().error(Collections.singletonList("HardwareUtils"), "Get androidId failed", th, new Object[0]);
            return null;
        }
    }
}
