package com.bytedance.bdtracker;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Rect;
import android.os.Process;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.applog.log.LoggerImpl;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f294a;

    public static Activity a(Context context) {
        if (!(context instanceof ContextWrapper)) {
            return null;
        }
        do {
            ContextWrapper contextWrapper = (ContextWrapper) context;
            if (contextWrapper instanceof Activity) {
                return (Activity) contextWrapper;
            }
            context = contextWrapper.getBaseContext();
        } while (context instanceof ContextWrapper);
        return null;
    }

    public static Activity a(View view) {
        if (view == null) {
            return null;
        }
        return a(view.getContext());
    }

    public static Class<?> a(String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                Class<?> clsB = b(str);
                if (clsB != null) {
                    return clsB;
                }
            }
        }
        return null;
    }

    public static String a(Object obj) {
        return obj != null ? obj.toString() : "";
    }

    public static String a(boolean z) {
        return z ? BooleanUtils.YES : BooleanUtils.NO;
    }

    public static List a() {
        return CollectionsKt.listOf((Object[]) new String[]{"metrics_category", "metrics_name"});
    }

    public static JSONObject a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject2 != null) {
            try {
                Iterator<String> itKeys = jSONObject2.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    jSONObject.put(next, jSONObject2.opt(next));
                }
            } catch (Throwable th) {
                LoggerImpl.global().error("copy json error", th, new Object[0]);
            }
        }
        return jSONObject;
    }

    public static void a(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Throwable th) {
                LoggerImpl.global().error("closeSafely error", th, new Object[0]);
            }
        }
    }

    public static void a(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase != null) {
            try {
                sQLiteDatabase.endTransaction();
            } catch (Throwable th) {
                LoggerImpl.global().error("endDbTransactionSafely error", th, new Object[0]);
            }
        }
    }

    public static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                LoggerImpl.global().error("closeSafely error", th, new Object[0]);
            }
        }
    }

    public static final /* synthetic */ boolean a(View view, Float f) {
        if (e(view)) {
            if (view.getLocalVisibleRect(new Rect())) {
                if (r0.height() * r0.width() >= view.getMeasuredWidth() * view.getMeasuredHeight() * (f != null ? f.floatValue() : 0.0f)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(Object obj, Object obj2) {
        return (obj != null || obj2 == null) && (obj == null || obj2 != null);
    }

    public static boolean a(Object obj, String str) {
        return a(obj == null, str);
    }

    public static boolean a(Object obj, String... strArr) {
        if (strArr != null && strArr.length != 0) {
            for (String str : strArr) {
                Class<?> clsB = b(str);
                if (clsB != null && clsB.isInstance(obj)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean a(String str) {
        boolean z = false;
        if (TextUtils.isEmpty(str) || "unknown".equalsIgnoreCase(str) || "Null".equalsIgnoreCase(str)) {
            return false;
        }
        int i = 0;
        while (true) {
            if (i >= str.length()) {
                z = true;
                break;
            }
            if (str.charAt(i) != '0') {
                break;
            }
            i++;
        }
        return !z;
    }

    public static boolean a(String str, String str2) {
        return (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) || (str != null && str.equals(str2));
    }

    public static boolean a(JSONObject jSONObject, JSONObject jSONObject2, String str) throws JSONException {
        if (!a((Object) jSONObject, (Object) jSONObject2)) {
            return false;
        }
        if (jSONObject != null && jSONObject.length() != jSONObject2.length()) {
            return false;
        }
        Iterator<String> itKeys = jSONObject.keys();
        boolean zA = true;
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            zA = a(jSONObject.get(next), jSONObject2.get(next), next);
            if (!zA) {
                break;
            }
        }
        return zA;
    }

    public static boolean a(JSONObject jSONObject, Class<?>[] clsArr, Class<?>[] clsArr2) throws JSONException {
        if (jSONObject == null) {
            return false;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            Object obj = jSONObject.get(itKeys.next());
            if (obj == null) {
                return false;
            }
            if (obj instanceof JSONArray) {
                JSONArray jSONArray = (JSONArray) obj;
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object obj2 = jSONArray.get(i);
                    if (clsArr2 != null && !a(clsArr2, obj2.getClass())) {
                        return false;
                    }
                }
            } else if (clsArr != null && !a(clsArr, obj.getClass())) {
                return false;
            }
        }
        return true;
    }

    public static <T> boolean a(T[] tArr, T t) {
        for (T t2 : tArr) {
            if (t2 == t) {
                return true;
            }
        }
        return false;
    }

    public static Class<?> b(String str) {
        try {
            return Class.forName(str);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }

    public static String b() {
        BufferedReader bufferedReader;
        StringBuilder sb;
        String str = f294a;
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        String string = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + Process.myPid() + "/cmdline"), "iso-8859-1"));
            try {
                sb = new StringBuilder();
            } catch (Throwable unused) {
            }
        } catch (Throwable unused2) {
            bufferedReader = null;
        }
        while (true) {
            int i = bufferedReader.read();
            if (i <= 0) {
                break;
            }
            sb.append((char) i);
            a((Closeable) bufferedReader);
            f294a = string;
            IAppLogLogger iAppLogLoggerGlobal = LoggerImpl.global();
            StringBuilder sbA = a.a("getProcessName: ");
            sbA.append(f294a);
            iAppLogLoggerGlobal.debug(sbA.toString(), new Object[0]);
            return f294a;
        }
        string = sb.toString();
        a((Closeable) bufferedReader);
        f294a = string;
        IAppLogLogger iAppLogLoggerGlobal2 = LoggerImpl.global();
        StringBuilder sbA2 = a.a("getProcessName: ");
        sbA2.append(f294a);
        iAppLogLoggerGlobal2.debug(sbA2.toString(), new Object[0]);
        return f294a;
    }

    public static String b(View view) {
        if (view == null) {
            return null;
        }
        return c(view) + "$$" + view.hashCode();
    }

    public static JSONObject b(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject == null) {
            return jSONObject2;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            try {
                String next = itKeys.next();
                jSONObject2.put(next, jSONObject.opt(next));
            } catch (JSONException e) {
                LoggerImpl.global().error(Collections.singletonList("JsonUtils"), "Merge json interrupted.", e, new Object[0]);
            }
        }
        return jSONObject2;
    }

    public static boolean b(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    public static synchronized String c() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase() + System.currentTimeMillis();
    }

    public static String c(View view) {
        if (view == null) {
            return "";
        }
        if (view instanceof CheckBox) {
            return "CheckBox";
        }
        if (view instanceof RadioButton) {
            return "RadioButton";
        }
        if (view instanceof ToggleButton) {
            return "ToggleButton";
        }
        if (view instanceof CompoundButton) {
            return a((Object) view, "android.widget.Switch") ? "Switch" : a((Object) view, "android.support.v7.widget.SwitchCompat", "androidx.appcompat.widget.SwitchCompat") ? "SwitchCompat" : "";
        }
        if (view instanceof Button) {
            return "Button";
        }
        if (view instanceof CheckedTextView) {
            return "CheckedTextView";
        }
        if (view instanceof TextView) {
            return "TextView";
        }
        if (view instanceof ImageView) {
            return "ImageView";
        }
        if (view instanceof RatingBar) {
            return "RatingBar";
        }
        if (view instanceof SeekBar) {
            return "SeekBar";
        }
        if (view instanceof Spinner) {
            return "Spinner";
        }
        try {
            Class<?> clsA = a("android.support.design.widget.TabLayout$TabView", "com.google.android.material.tabs.TabLayout$TabView");
            if (clsA != null) {
                if (clsA.isAssignableFrom(view.getClass())) {
                    return "TabLayout";
                }
            }
        } catch (Throwable th) {
            LoggerImpl.global().error(Collections.singletonList("WidgetUtils"), "Check isTabView failed", th, new Object[0]);
        }
        if (a((Object) view, "android.support.design.widget.NavigationView", "com.google.android.material.navigation.NavigationView")) {
            return "NavigationView";
        }
        if (view instanceof ViewGroup) {
            if (a((Object) view, "android.support.v7.widget.CardView", "androidx.cardview.widget.CardView")) {
                return "CardView";
            }
            if (a((Object) view, "android.support.design.widget.NavigationView", "com.google.android.material.navigation.NavigationView")) {
                return "NavigationView";
            }
        }
        try {
            return view.getClass().getCanonicalName();
        } catch (Throwable th2) {
            LoggerImpl.global().error(Collections.singletonList("WidgetUtils"), "getCanonicalName failed", th2, new Object[0]);
            return "";
        }
    }

    public static boolean c(String str) {
        return !d(str);
    }

    public static List d() {
        return CollectionsKt.emptyList();
    }

    public static boolean d(View view) {
        if (view == null) {
            return false;
        }
        if (u4.a(view)) {
            return true;
        }
        if (view.getWidth() <= 0 || view.getHeight() <= 0 || view.getAlpha() <= 0.0f || !view.getLocalVisibleRect(new Rect())) {
            return false;
        }
        return !(view.getVisibility() == 0 || view.getAnimation() == null || !view.getAnimation().getFillAfter()) || view.getVisibility() == 0;
    }

    public static boolean d(String str) {
        return str != null && str.length() > 0;
    }

    public static boolean e(View view) {
        boolean zD = d(view);
        if (!zD) {
            return zD;
        }
        for (ViewParent parent = view.getParent(); parent != null; parent = parent.getParent()) {
            if (!(parent instanceof View)) {
                return zD;
            }
            if (!d((View) parent)) {
                break;
            }
        }
        return false;
    }

    public static boolean e(String str) {
        int length = str != null ? str.length() : 0;
        if (length < 13 || length > 128) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            char cCharAt = str.charAt(i);
            if ((cCharAt < '0' || cCharAt > '9') && ((cCharAt < 'a' || cCharAt > 'f') && ((cCharAt < 'A' || cCharAt > 'F') && cCharAt != '-'))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(Object obj, Object obj2, String str) throws JSONException {
        if (!a(obj, obj2)) {
            return false;
        }
        if (obj instanceof JSONObject) {
            return a((JSONObject) obj, (JSONObject) obj2, str);
        }
        if (!(obj instanceof JSONArray)) {
            if (obj.getClass() != obj2.getClass()) {
                return false;
            }
            String string = obj.toString();
            String string2 = obj2.toString();
            return a((Object) string, (Object) string2) && string.equals(string2);
        }
        JSONArray jSONArray = (JSONArray) obj;
        JSONArray jSONArray2 = (JSONArray) obj2;
        if (!a(jSONArray, jSONArray2)) {
            return false;
        }
        HashMap map = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object obj3 = jSONArray.get(i);
            map.put(obj3, (!map.containsKey(obj3) || map.get(obj3) == null) ? 1 : Integer.valueOf(((Integer) map.get(obj3)).intValue() + 1));
        }
        HashMap map2 = new HashMap();
        for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
            Object obj4 = jSONArray2.get(i2);
            map2.put(obj4, (!map2.containsKey(obj4) || map2.get(obj4) == null) ? 1 : Integer.valueOf(((Integer) map2.get(obj4)).intValue() + 1));
        }
        if (map.size() != map2.size()) {
            return false;
        }
        for (Map.Entry entry : map.entrySet()) {
            if (!((Integer) entry.getValue()).equals((Integer) map2.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    public static boolean a(boolean z, String str) {
        if (!z) {
            return false;
        }
        LoggerImpl.global().ast("[Assert failed] {}", null, str);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x020a  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x033a  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x0451  */
    /* JADX WARN: Removed duplicated region for block: B:188:0x04de  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0557  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:52:0x00e9 A[PHI: r6
  0x00e9: PHI (r6v6 java.lang.String) = (r6v5 java.lang.String), (r6v5 java.lang.String), (r6v36 java.lang.String) binds: [B:42:0x00a3, B:44:0x00ab, B:46:0x00cb] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static com.bytedance.bdtracker.l3 a(android.view.View r36, boolean r37) {
        /*
            Method dump skipped, instruction units count: 1426
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.bdtracker.n0.a(android.view.View, boolean):com.bytedance.bdtracker.l3");
    }

    public static List<Number> a(o2 o2Var) {
        return d();
    }

    public static void a(o2 o2Var, JSONObject params) {
        Intrinsics.checkParameterIsNotNull(params, "params");
        Intrinsics.checkParameterIsNotNull(params, "params");
    }
}
