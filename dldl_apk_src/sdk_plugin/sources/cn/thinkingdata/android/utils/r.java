package cn.thinkingdata.android.utils;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.text.TextUtils;
import android.view.Choreographer;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import cn.thinkingdata.android.R;
import cn.thinkingdata.android.ScreenAutoTracker;
import cn.thinkingdata.android.TDPresetProperties;
import cn.thinkingdata.android.ThinkingDataFragmentTitle;
import com.sq.tools.network.Network;
import com.taptap.sdk.db.biz.iap.lib2plus.BillingClientConstants;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class r {
    static long a;
    static long b;
    static volatile int c;
    static final Object d = new Object();

    class a implements Choreographer.FrameCallback {
        a() {
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            synchronized (r.d) {
                r.b = j;
                if (j <= r.a) {
                    r.c = 60;
                } else {
                    try {
                        long j2 = 1000000000 / (r.b - r.a);
                        if (j2 > 70) {
                            r.c = 60;
                        } else {
                            r.c = (int) j2;
                        }
                    } catch (Exception unused) {
                        r.c = 60;
                    }
                }
            }
        }
    }

    class b implements Choreographer.FrameCallback {
        final /* synthetic */ Choreographer.FrameCallback a;

        b(Choreographer.FrameCallback frameCallback) {
            this.a = frameCallback;
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            synchronized (r.d) {
                r.a = j;
                Choreographer.getInstance().postFrameCallback(this.a);
            }
        }
    }

    class c implements Runnable {
        final /* synthetic */ Handler a;
        final /* synthetic */ Choreographer.FrameCallback b;

        c(Handler handler, Choreographer.FrameCallback frameCallback) {
            this.a = handler;
            this.b = frameCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.postDelayed(this, 500L);
            Choreographer.getInstance().postFrameCallback(this.b);
        }
    }

    public static double a(double d2) {
        return Math.round(d2 * 10.0d) / 10.0d;
    }

    public static double a(long j, TimeZone timeZone) {
        if (timeZone == null) {
            timeZone = TimeZone.getDefault();
        }
        return ((double) timeZone.getOffset(j)) / 3600000.0d;
    }

    public static int a() {
        if (c == 0) {
            c = 60;
        }
        return c;
    }

    private static int a(ViewParent viewParent, View view) {
        try {
            if (!(viewParent instanceof ViewGroup)) {
                return -1;
            }
            ViewGroup viewGroup = (ViewGroup) viewParent;
            String strA = a(view);
            String canonicalName = view.getClass().getCanonicalName();
            int i = 0;
            for (int i2 = 0; i2 < viewGroup.getChildCount(); i2++) {
                View childAt = viewGroup.getChildAt(i2);
                if (cn.thinkingdata.android.h.a(childAt, canonicalName)) {
                    String strA2 = a(childAt);
                    if ((strA == null || strA.equals(strA2)) && childAt == view) {
                        return i;
                    }
                    i++;
                }
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static int a(String str) {
        if ("NULL".equals(str)) {
            return 255;
        }
        if ("WIFI".equals(str)) {
            return 8;
        }
        if ("2G".equals(str)) {
            return 1;
        }
        if ("3G".equals(str)) {
            return 2;
        }
        if ("4G".equals(str)) {
            return 4;
        }
        return Network.MOBILE_5G.equals(str) ? 16 : 255;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x001c, code lost:
    
        if ((r1 instanceof android.app.Activity) != false) goto L15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static android.app.Activity a(android.content.Context r1) {
        /*
            if (r1 == 0) goto L25
            boolean r0 = r1 instanceof android.app.Activity     // Catch: java.lang.Exception -> L21
            if (r0 == 0) goto L7
            goto L1e
        L7:
            boolean r0 = r1 instanceof android.content.ContextWrapper     // Catch: java.lang.Exception -> L21
            if (r0 == 0) goto L25
        Lb:
            boolean r0 = r1 instanceof android.app.Activity     // Catch: java.lang.Exception -> L21
            if (r0 != 0) goto L1a
            boolean r0 = r1 instanceof android.content.ContextWrapper     // Catch: java.lang.Exception -> L21
            if (r0 == 0) goto L1a
            android.content.ContextWrapper r1 = (android.content.ContextWrapper) r1     // Catch: java.lang.Exception -> L21
            android.content.Context r1 = r1.getBaseContext()     // Catch: java.lang.Exception -> L21
            goto Lb
        L1a:
            boolean r0 = r1 instanceof android.app.Activity     // Catch: java.lang.Exception -> L21
            if (r0 == 0) goto L25
        L1e:
            android.app.Activity r1 = (android.app.Activity) r1     // Catch: java.lang.Exception -> L21
            goto L26
        L21:
            r1 = move-exception
            r1.printStackTrace()
        L25:
            r1 = 0
        L26:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.utils.r.a(android.content.Context):android.app.Activity");
    }

    public static synchronized Object a(String str, View view, int i) {
        HashMap map = (HashMap) view.getTag(i);
        if (map == null) {
            return null;
        }
        return map.get(str);
    }

    public static String a(int i) {
        double dRandom;
        double d2;
        char c2;
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            int iRandom = (int) (Math.random() * 2.0d);
            if (iRandom == 0) {
                dRandom = Math.random() * 10.0d;
                d2 = 48.0d;
            } else if (iRandom != 1) {
                c2 = 0;
                sb.append(c2);
            } else {
                dRandom = Math.random() * 6.0d;
                d2 = 97.0d;
            }
            c2 = (char) (dRandom + d2);
            sb.append(c2);
        }
        return sb.toString();
    }

    public static String a(Activity activity) {
        PackageManager packageManager;
        if (activity != null) {
            try {
                String string = !TextUtils.isEmpty(activity.getTitle()) ? activity.getTitle().toString() : null;
                if (Build.VERSION.SDK_INT >= 11) {
                    String strB = b(activity);
                    if (!TextUtils.isEmpty(strB)) {
                        string = strB;
                    }
                }
                if (!TextUtils.isEmpty(string) || (packageManager = activity.getPackageManager()) == null) {
                    return string;
                }
                ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 0);
                return !TextUtils.isEmpty(activityInfo.loadLabel(packageManager)) ? activityInfo.loadLabel(packageManager).toString() : string;
            } catch (Exception unused) {
            }
        }
        return null;
    }

    public static String a(View view) {
        return a(view, (String) null);
    }

    public static String a(View view, String str) {
        try {
            String str2 = (String) a(str, view, R.id.thinking_analytics_tag_view_id);
            try {
                return (!TextUtils.isEmpty(str2) || view.getId() == -1) ? str2 : view.getContext().getResources().getResourceEntryName(view.getId());
            } catch (Exception unused) {
                return str2;
            }
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String a(Object obj, String str) {
        ThinkingDataFragmentTitle thinkingDataFragmentTitle;
        JSONObject trackProperties;
        String strOptString = null;
        try {
            if ((obj instanceof ScreenAutoTracker) && (trackProperties = ((ScreenAutoTracker) obj).getTrackProperties()) != null && trackProperties.has("#title")) {
                strOptString = trackProperties.optString("#title");
            }
            return (TextUtils.isEmpty(strOptString) && obj.getClass().isAnnotationPresent(ThinkingDataFragmentTitle.class) && (thinkingDataFragmentTitle = (ThinkingDataFragmentTitle) obj.getClass().getAnnotation(ThinkingDataFragmentTitle.class)) != null) ? (TextUtils.isEmpty(thinkingDataFragmentTitle.appId()) || str.equals(thinkingDataFragmentTitle.appId())) ? thinkingDataFragmentTitle.title() : strOptString : strOptString;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(String str, int i) {
        return (!TextUtils.isEmpty(str) && str.length() > i) ? str.substring(str.length() - 4) : str;
    }

    private static String a(String str, String str2) {
        try {
            Class<?> cls = Class.forName("android.os.SystemProperties");
            String str3 = (String) cls.getDeclaredMethod("get", String.class).invoke(cls, str);
            return TextUtils.isEmpty(str3) ? str2 : str3;
        } catch (Throwable th) {
            TDLog.i("TA.SystemProperties", th.getMessage());
            return str2;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00da A[Catch: Exception -> 0x00ef, TryCatch #0 {Exception -> 0x00ef, blocks: (B:3:0x0002, B:5:0x0007, B:7:0x000f, B:63:0x00e6, B:10:0x001b, B:12:0x001f, B:20:0x0037, B:22:0x003b, B:60:0x00d4, B:62:0x00da, B:25:0x0046, B:27:0x004c, B:29:0x0055, B:31:0x005b, B:35:0x006e, B:32:0x0062, B:34:0x0068, B:36:0x0078, B:38:0x007c, B:39:0x0083, B:41:0x0087, B:43:0x008f, B:44:0x0094, B:45:0x0099, B:47:0x009d, B:48:0x00a4, B:50:0x00a8, B:51:0x00af, B:53:0x00b3, B:54:0x00ba, B:56:0x00be, B:58:0x00ca, B:64:0x00ea), top: B:70:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:78:0x00e6 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.lang.StringBuilder r7, android.view.ViewGroup r8) {
        /*
            Method dump skipped, instruction units count: 248
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.utils.r.a(java.lang.StringBuilder, android.view.ViewGroup):java.lang.String");
    }

    public static String a(Date date, TimeZone timeZone) {
        Calendar calendar = Calendar.getInstance(Locale.CHINA);
        calendar.setTimeZone(timeZone);
        calendar.setTime(date);
        Locale locale = Locale.CHINA;
        Object[] objArr = new Object[7];
        objArr[0] = Integer.valueOf(calendar.get(1));
        objArr[1] = Integer.valueOf(calendar.get(2) + 1);
        objArr[2] = Integer.valueOf(calendar.get(5));
        objArr[3] = Integer.valueOf(calendar.get(9) == 0 ? calendar.get(10) : calendar.get(10) + 12);
        objArr[4] = Integer.valueOf(calendar.get(12));
        objArr[5] = Integer.valueOf(calendar.get(13));
        objArr[6] = Integer.valueOf(calendar.get(14));
        return String.format(locale, "%04d-%02d-%02d %02d:%02d:%02d.%3d", objArr);
    }

    public static JSONArray a(JSONArray jSONArray, TimeZone timeZone) {
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            Object objOpt = jSONArray.opt(i);
            if (objOpt != null) {
                if (objOpt instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
                    if (timeZone != null) {
                        simpleDateFormat.setTimeZone(timeZone);
                    }
                    Date date = (Date) objOpt;
                    String strA = simpleDateFormat.format(date);
                    if (!Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}").matcher(strA).find()) {
                        strA = a(date, timeZone);
                    }
                    jSONArray2.put(strA);
                } else {
                    if (objOpt instanceof JSONArray) {
                        objOpt = a((JSONArray) objOpt, timeZone);
                    } else if (objOpt instanceof JSONObject) {
                        objOpt = a((JSONObject) objOpt, timeZone);
                    }
                    jSONArray2.put(objOpt);
                }
            }
        }
        return jSONArray2;
    }

    public static JSONObject a(JSONObject jSONObject, TimeZone timeZone) {
        JSONObject jSONObject2 = new JSONObject();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                Object objA = jSONObject.get(next);
                if (objA instanceof Date) {
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
                    if (timeZone != null) {
                        simpleDateFormat.setTimeZone(timeZone);
                    }
                    String strA = simpleDateFormat.format((Date) objA);
                    if (!Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}").matcher(strA).find()) {
                        strA = a((Date) objA, timeZone);
                    }
                    jSONObject2.put(next, strA);
                } else {
                    if (objA instanceof JSONArray) {
                        objA = a((JSONArray) objA, timeZone);
                    } else if (objA instanceof JSONObject) {
                        objA = a((JSONObject) objA, timeZone);
                    }
                    jSONObject2.put(next, objA);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject2;
    }

    public static void a(Activity activity, View view, JSONObject jSONObject) {
        ViewParent parent;
        if (view == null) {
            return;
        }
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        ArrayList arrayList = new ArrayList();
        do {
            parent = view.getParent();
            arrayList.add(view.getClass().getCanonicalName() + "[" + a(parent, view) + "]");
            if (parent instanceof ViewGroup) {
                view = (ViewGroup) parent;
            }
        } while (parent instanceof ViewGroup);
        Collections.reverse(arrayList);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arrayList.size(); i++) {
            sb.append((String) arrayList.get(i));
            if (i != arrayList.size() - 1) {
                sb.append("/");
            }
        }
        if (TDPresetProperties.disableList.contains("#element_selector")) {
            return;
        }
        jSONObject.put("#element_selector", sb.toString());
    }

    public static void a(View view, JSONObject jSONObject) {
        if (view != null) {
            try {
                String str = (String) view.getTag(R.id.thinking_analytics_tag_view_fragment_name);
                if (TextUtils.isEmpty(str) && view.getParent() != null && (view.getParent() instanceof View)) {
                    str = (String) ((View) view.getParent()).getTag(R.id.thinking_analytics_tag_view_fragment_name);
                }
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                String strOptString = jSONObject.optString("#screen_name");
                if (TextUtils.isEmpty(str)) {
                    if (TDPresetProperties.disableList.contains("#screen_name")) {
                        return;
                    }
                    jSONObject.put("#screen_name", str);
                } else {
                    if (TDPresetProperties.disableList.contains("#screen_name")) {
                        return;
                    }
                    jSONObject.put("#screen_name", String.format(Locale.CHINA, "%s|%s", strOptString, str));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized void a(String str, View view, int i, Object obj) {
        if (str == null) {
            return;
        }
        HashMap map = (HashMap) view.getTag(i);
        if (map == null) {
            map = new HashMap();
        }
        map.put(str, obj);
        view.setTag(i, map);
    }

    public static void a(JSONObject jSONObject, Activity activity) {
        PackageManager packageManager;
        if (activity == null || jSONObject == null) {
            return;
        }
        try {
            if (!TDPresetProperties.disableList.contains("#screen_name")) {
                jSONObject.put("#screen_name", activity.getClass().getCanonicalName());
            }
            String string = activity.getTitle().toString();
            if (Build.VERSION.SDK_INT >= 11) {
                String strB = b(activity);
                if (!TextUtils.isEmpty(strB)) {
                    string = strB;
                }
            }
            if (TextUtils.isEmpty(string) && (packageManager = activity.getPackageManager()) != null) {
                string = packageManager.getActivityInfo(activity.getComponentName(), 0).loadLabel(packageManager).toString();
            }
            if (TextUtils.isEmpty(string) || TDPresetProperties.disableList.contains("#title")) {
                return;
            }
            jSONObject.put("#title", string);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2, TimeZone timeZone) {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            Object objA = jSONObject.get(next);
            if (objA instanceof Date) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.CHINA);
                if (timeZone != null) {
                    simpleDateFormat.setTimeZone(timeZone);
                }
                Date date = (Date) objA;
                String strA = simpleDateFormat.format(date);
                if (!Pattern.compile("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}\\.\\d{3}").matcher(strA).find()) {
                    strA = a(date, timeZone);
                }
                jSONObject2.put(next, strA);
            } else {
                if (objA instanceof JSONArray) {
                    objA = a((JSONArray) objA, timeZone);
                } else if (objA instanceof JSONObject) {
                    objA = a((JSONObject) objA, timeZone);
                }
                jSONObject2.put(next, objA);
            }
        }
    }

    public static String b() {
        if (!c()) {
            return null;
        }
        String strA = a("hw_sc.build.platform.version", "");
        return TextUtils.isEmpty(strA) ? b("getprop hw_sc.build.platform.version") : strA;
    }

    public static String b(Activity activity) {
        Class<?> cls;
        Object objInvoke;
        CharSequence charSequence;
        ActionBar actionBar = activity.getActionBar();
        if (actionBar == null) {
            try {
                cls = Class.forName("android.support.v7.app.AppCompatActivity");
            } catch (Exception unused) {
                cls = null;
            }
            if (cls == null) {
                try {
                    cls = Class.forName("androidx.appcompat.app.AppCompatActivity");
                } catch (Exception unused2) {
                }
            }
            if (cls != null) {
                try {
                    if (cls.isInstance(activity) && (objInvoke = activity.getClass().getMethod("getSupportActionBar", new Class[0]).invoke(activity, new Object[0])) != null && (charSequence = (CharSequence) objInvoke.getClass().getMethod(BillingClientConstants.METHOD_GET_PRODUCT_TITLE, new Class[0]).invoke(objInvoke, new Object[0])) != null) {
                        return charSequence.toString();
                    }
                } catch (Exception unused3) {
                }
            }
        } else if (!TextUtils.isEmpty(actionBar.getTitle())) {
            return actionBar.getTitle().toString();
        }
        return null;
    }

    public static String b(Context context) {
        try {
            return g.a(context);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String b(String str) {
        Throwable th;
        BufferedReader bufferedReader;
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(Runtime.getRuntime().exec(str).getInputStream());
            try {
                bufferedReader = new BufferedReader(inputStreamReader);
                try {
                    StringBuilder sb = new StringBuilder();
                    while (true) {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                    }
                    String string = sb.toString();
                    try {
                        bufferedReader.close();
                    } catch (Throwable th2) {
                        TDLog.i("TDExec", th2.getMessage());
                    }
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        TDLog.i("TDExec", e.getMessage());
                    }
                    return string;
                } catch (Throwable th3) {
                    th = th3;
                    try {
                        TDLog.i("TDExec", th.getMessage());
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Throwable th4) {
                                TDLog.i("TDExec", th4.getMessage());
                            }
                        }
                        if (inputStreamReader != null) {
                            try {
                                inputStreamReader.close();
                            } catch (IOException e2) {
                                TDLog.i("TDExec", e2.getMessage());
                            }
                        }
                        return null;
                    } finally {
                    }
                }
            } catch (Throwable th5) {
                th = th5;
                bufferedReader = null;
            }
        } catch (Throwable th6) {
            th = th6;
            bufferedReader = null;
            inputStreamReader = null;
        }
    }

    public static void b(JSONObject jSONObject, JSONObject jSONObject2, TimeZone timeZone) throws JSONException {
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(next);
            JSONObject jSONObjectOptJSONObject2 = jSONObject2.optJSONObject(next);
            if (jSONObjectOptJSONObject != null) {
                if (jSONObjectOptJSONObject2 == null) {
                    JSONObject jSONObject3 = new JSONObject();
                    a(jSONObjectOptJSONObject, jSONObject3, timeZone);
                    jSONObject2.put(next, jSONObject3);
                } else {
                    a(jSONObjectOptJSONObject, jSONObjectOptJSONObject2, timeZone);
                }
            }
        }
    }

    public static String c(Context context) {
        return (context.getResources().getConfiguration().screenLayout & 15) < 3 ? "Phone" : "Tablet";
    }

    public static boolean c() {
        try {
            Class<?> cls = Class.forName("com.huawei.system.BuildEx");
            Object objInvoke = cls.getMethod("getOsBrand", new Class[0]).invoke(cls, new Object[0]);
            if (objInvoke == null) {
                return false;
            }
            return "harmony".equalsIgnoreCase(objInvoke.toString());
        } catch (Throwable th) {
            TDLog.i("HasHarmonyOS", th.getMessage());
            return false;
        }
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        String strB = cn.thinkingdata.android.m.a(context).b();
        if (strB.length() != 0) {
            return strB;
        }
        try {
            return context.getApplicationInfo().processName;
        } catch (Exception unused) {
            return strB;
        }
    }

    public static boolean d() {
        return new File("/storage/emulated/0/Download/ta_log_controller").exists();
    }

    public static void e() {
        if (Build.VERSION.SDK_INT >= 16) {
            b bVar = new b(new a());
            Handler handler = new Handler();
            handler.postDelayed(new c(handler, bVar), 500L);
        }
    }

    public static boolean e(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
        if (g.b == null) {
            g.b = activityManager.getRunningAppProcesses();
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : g.b) {
            String strSubstring = runningAppProcessInfo.processName;
            int iIndexOf = strSubstring.indexOf(":");
            if (iIndexOf != -1) {
                strSubstring = strSubstring.substring(0, iIndexOf);
            }
            if (strSubstring.equals(context.getPackageName())) {
                int i = runningAppProcessInfo.importance;
                return i == 100 || i == 200;
            }
        }
        return false;
    }

    public static boolean f(Context context) {
        if (context == null) {
            return true;
        }
        String strB = b(context.getApplicationContext());
        return !TextUtils.isEmpty(strB) && d(context).equals(strB);
    }
}
