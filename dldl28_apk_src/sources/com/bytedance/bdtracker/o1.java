package com.bytedance.bdtracker;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.lang.reflect.Method;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class o1 extends d1 {
    public final Context e;
    public final d f;

    public o1(d dVar, Context context) {
        super(true, false);
        this.f = dVar;
        this.e = context;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Display";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        String str;
        int i;
        int iIntValue;
        int iIntValue2;
        int i2 = this.e.getResources().getDisplayMetrics().densityDpi;
        switch (i2) {
            case 120:
                str = "ldpi";
                break;
            case KeyBoardKey.KeyboardKeyOemAttn /* 240 */:
                str = "hdpi";
                break;
            case 260:
            case 280:
            case 300:
            case 320:
                str = "xhdpi";
                break;
            case 340:
            case 360:
            case 400:
            case 420:
            case 440:
            case 480:
                str = "xxhdpi";
                break;
            case 560:
            case 640:
                str = "xxxhdpi";
                break;
            default:
                str = "mdpi";
                break;
        }
        jSONObject.put("density_dpi", i2);
        jSONObject.put("display_density", str);
        WindowManager windowManager = (WindowManager) this.e.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        Display defaultDisplay = windowManager.getDefaultDisplay();
        try {
        } catch (Throwable th) {
            th = th;
            i = 0;
        }
        if (defaultDisplay != null) {
            defaultDisplay.getRealMetrics(displayMetrics);
            i = displayMetrics.widthPixels;
            try {
                iIntValue2 = displayMetrics.heightPixels;
                iIntValue = i;
            } catch (Throwable th2) {
                th = th2;
                this.f.D.error("Get screen pixels failed", th, new Object[0]);
                iIntValue = i;
                iIntValue2 = 0;
            }
        } else {
            Method method = Display.class.getMethod("getRawHeight", null);
            Method method2 = Display.class.getMethod("getRawWidth", null);
            iIntValue = method2 != null ? ((Integer) method2.invoke(defaultDisplay, null)).intValue() : 0;
            if (method != null) {
                try {
                    iIntValue2 = ((Integer) method.invoke(defaultDisplay, null)).intValue();
                    i = iIntValue;
                    iIntValue = i;
                } catch (Throwable th3) {
                    th = th3;
                    i = iIntValue;
                    this.f.D.error("Get screen pixels failed", th, new Object[0]);
                    iIntValue = i;
                    iIntValue2 = 0;
                }
            }
            iIntValue2 = 0;
        }
        int[] iArr = {iIntValue, iIntValue2};
        jSONObject.put("resolution", iArr[1] + "x" + iArr[0]);
        return true;
    }
}
