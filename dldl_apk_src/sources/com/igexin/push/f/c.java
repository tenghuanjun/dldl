package com.igexin.push.f;

import android.app.AppOpsManager;
import android.app.NotificationManager;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.PowerManager;
import android.text.TextUtils;
import android.util.Log;
import com.igexin.sdk.GActivity;
import com.igexin.sdk.GTIntentService;
import com.igexin.sdk.GetuiPushException;
import com.igexin.sdk.PushActivity;
import com.igexin.sdk.PushReceiver;
import com.igexin.sdk.PushService;
import com.jiguang.h5.PermissionUtils;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class c {
    private static final String a = "com.igexin.push.f.c";
    private static Integer b = null;
    private static final String c = "checkOpNoThrow";
    private static final String d = "OP_POST_NOTIFICATION";

    private static String a(Context context, String str) {
        Intent intent = new Intent();
        intent.setAction(str);
        intent.setPackage(context.getPackageName());
        try {
        } catch (Throwable unused) {
        }
        for (ResolveInfo resolveInfo : context.getPackageManager().queryIntentServices(intent, 0)) {
            if (resolveInfo.serviceInfo != null && !TextUtils.isEmpty(resolveInfo.serviceInfo.name)) {
                return resolveInfo.serviceInfo.name;
            }
            return null;
        }
        return null;
    }

    private static void a(Map<String, com.igexin.push.core.b.d> map, String str) {
        com.igexin.push.core.b.d dVar = map.get(str);
        map.remove(str);
        for (String str2 : dVar.b) {
            com.igexin.push.core.b.d dVar2 = map.get(str2);
            if (dVar2 != null) {
                dVar2.c--;
                if (dVar2.c == 0) {
                    a(map, str2);
                }
            }
        }
    }

    public static boolean a() {
        try {
            if (com.igexin.push.a.i.equals(com.igexin.push.config.d.q)) {
                return false;
            }
            for (String str : com.igexin.push.config.d.q.split(com.igexin.push.core.b.aj)) {
                if (e(str)) {
                    return false;
                }
            }
            if (com.igexin.push.a.i.equals(com.igexin.push.config.d.r)) {
                return false;
            }
            String[] strArrSplit = com.igexin.push.config.d.r.split(com.igexin.push.core.b.aj);
            Class<?> cls = Class.forName("android.os.ServiceManager");
            Method method = cls.getMethod("getService", String.class);
            method.setAccessible(true);
            for (String str2 : strArrSplit) {
                if (a(cls, method, str2)) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static boolean a(long j) {
        if (com.igexin.push.config.d.b == 0) {
            return false;
        }
        Date date = new Date(j);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int i = calendar.get(11);
        int i2 = com.igexin.push.config.d.a + com.igexin.push.config.d.b;
        if (i2 >= 24) {
            i2 -= 24;
        }
        if (com.igexin.push.config.d.a < i2) {
            if (i >= com.igexin.push.config.d.a && i < i2) {
                return true;
            }
        } else if (com.igexin.push.config.d.a > i2) {
            if (i >= 0 && i < i2) {
                return true;
            }
            if (i >= com.igexin.push.config.d.a && i < 24) {
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context) {
        if (b == null) {
            b = (context.getApplicationInfo().flags & 2) == 0 ? -1 : 1;
        }
        return b.intValue() > 0;
    }

    public static boolean a(Context context, Class cls) {
        try {
            if (context == null) {
                Log.e(a, "context can not set null ");
                return false;
            }
            PackageManager packageManager = context.getPackageManager();
            List<ResolveInfo> listQueryIntentActivities = packageManager.queryIntentActivities(new Intent(context, (Class<?>) cls), 0);
            if (listQueryIntentActivities != null && listQueryIntentActivities.size() > 0) {
                if (packageManager.getActivityInfo(new ComponentName(context.getPackageName(), cls.getName()), 128).theme == 16973840) {
                    return true;
                }
                Log.e(a, cls.getName() + " need set theme Theme.Translucent.NoTitleBar");
                return false;
            }
            Log.e(a, "not regist " + cls.getName() + "in manifest");
            return false;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
            return false;
        }
    }

    public static boolean a(Intent intent, Context context) {
        if (context == null) {
            return false;
        }
        try {
            List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
            if (listQueryIntentServices != null) {
                if (listQueryIntentServices.size() > 0) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
            return false;
        }
    }

    private static boolean a(Class<?> cls, Method method, String str) {
        try {
            return method.invoke(cls, str) != null;
        } catch (Exception unused) {
            return true;
        }
    }

    public static boolean a(String str) {
        return com.igexin.push.core.e.i.getPackageManager().getLaunchIntentForPackage(str) != null;
    }

    private static <T extends Service> boolean a(String str, Context context, Class<T> cls) {
        if (cls == null) {
            try {
                if (!a(new Intent(context, Class.forName(com.igexin.push.core.b.ak)), context)) {
                    Log.e(str, "call - > initialize, parameter [userServiceName] is null use default PushService, but didn't find class \"com.igexin.sdk.PushService\", please check your AndroidManifest");
                    return false;
                }
            } catch (Throwable th) {
                Log.e(a, th.toString());
                com.igexin.b.a.c.a.a(a + "|" + th.toString(), new Object[0]);
                return false;
            }
        }
        if (cls != null && com.igexin.push.core.b.ak.equals(cls.getName()) && !a(new Intent(context, (Class<?>) cls), context)) {
            Log.e(str, "call - > initialize, parameter [userServiceName] is default PushService, but didn't find class \"com.igexin.sdk.PushService\", please check your AndroidManifest");
            return false;
        }
        if (cls == null || a(new Intent(context, (Class<?>) cls), context)) {
            if (cls == null) {
                return true;
            }
            Class.forName(cls.getName());
            return true;
        }
        Log.e(str, "call - > initialize, parameter [userServiceName] is set, but didn't find class \"" + cls.getName() + "\", please check your AndroidManifest");
        return false;
    }

    public static boolean a(JSONObject jSONObject) {
        String string;
        try {
            HashMap map = new HashMap();
            JSONArray jSONArray = jSONObject.getJSONArray("action_chains");
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) jSONArray.get(i);
                com.igexin.push.core.b.d dVar = new com.igexin.push.core.b.d();
                if (jSONObject2.has("actionid")) {
                    if (map.containsKey(dVar.a)) {
                        return true;
                    }
                    dVar.a = jSONObject2.getString("actionid");
                    ArrayList arrayList = new ArrayList();
                    if (jSONObject2.has("type")) {
                        String string2 = jSONObject2.getString("type");
                        if ("popup".equals(string2)) {
                            if (jSONObject2.has("buttons")) {
                                JSONArray jSONArray2 = jSONObject2.getJSONArray("buttons");
                                for (int i2 = 0; i2 < jSONArray2.length(); i2++) {
                                    if (((JSONObject) jSONArray2.get(i2)).has("do")) {
                                        arrayList.add(((JSONObject) jSONArray2.get(i2)).getString("do"));
                                    }
                                }
                            }
                            if (jSONObject2.has("do")) {
                                string = jSONObject2.getString("do");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        } else if (com.igexin.push.core.b.o.equals(string2)) {
                            if (jSONObject2.has("noinstall_action")) {
                                arrayList.add(jSONObject2.getString("noinstall_action"));
                            }
                            if (jSONObject2.has("do")) {
                                string = jSONObject2.getString("do");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        } else if (com.igexin.push.core.b.r.equals(string2)) {
                            if (jSONObject2.has("do_installed")) {
                                arrayList.add(jSONObject2.getString("do_installed"));
                            }
                            if (jSONObject2.has("do_uninstalled")) {
                                string = jSONObject2.getString("do_uninstalled");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        } else if ("checkversions".equals(string2)) {
                            if (jSONObject2.has("do_match")) {
                                arrayList.add(jSONObject2.getString("do_match"));
                            }
                            if (jSONObject2.has("do_dismatch")) {
                                arrayList.add(jSONObject2.getString("do_dismatch"));
                            }
                            if (jSONObject2.has("do")) {
                                string = jSONObject2.getString("do");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        } else if ("startintent".equals(string2)) {
                            if (jSONObject2.has("do_failed")) {
                                arrayList.add(jSONObject2.getString("do_failed"));
                            }
                            if (jSONObject2.has("do")) {
                                string = jSONObject2.getString("do");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        } else {
                            if (!"null".equals(string2) && jSONObject2.has("do")) {
                                string = jSONObject2.getString("do");
                                arrayList.add(string);
                            }
                            dVar.b = arrayList;
                            map.put(dVar.a, dVar);
                        }
                    }
                }
            }
            ArrayList arrayList2 = new ArrayList(map.values());
            Iterator it = map.entrySet().iterator();
            while (it.hasNext()) {
                List<String> list = ((com.igexin.push.core.b.d) ((Map.Entry) it.next()).getValue()).b;
                if (list != null) {
                    Iterator<String> it2 = list.iterator();
                    while (it2.hasNext()) {
                        com.igexin.push.core.b.d dVar2 = (com.igexin.push.core.b.d) map.get(it2.next());
                        if (dVar2 != null) {
                            dVar2.c++;
                            if (arrayList2.contains(dVar2)) {
                                arrayList2.remove(dVar2);
                            }
                        }
                    }
                }
            }
            Iterator it3 = arrayList2.iterator();
            while (it3.hasNext()) {
                a(map, ((com.igexin.push.core.b.d) it3.next()).a);
            }
            if (map.size() > 0) {
                com.igexin.b.a.c.a.a(a + "|action_chains have loop nodeMap not empty", new Object[0]);
                return true;
            }
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|isHaveLoop exception :" + th.toString(), new Object[0]);
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0052 A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(boolean r4, boolean r5, java.lang.String r6) {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r6)
            r1 = 0
            if (r0 == 0) goto L8
            return r1
        L8:
            r0 = -1
            int r2 = r6.hashCode()
            r3 = 1
            switch(r2) {
                case -1655966961: goto L3a;
                case -109592092: goto L30;
                case 105888445: goto L26;
                case 110331239: goto L1c;
                case 1984153269: goto L12;
                default: goto L11;
            }
        L11:
            goto L44
        L12:
            java.lang.String r2 = "service"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L44
            r6 = 1
            goto L45
        L1c:
            java.lang.String r2 = "third"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L44
            r6 = 2
            goto L45
        L26:
            java.lang.String r2 = "oneof"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L44
            r6 = 4
            goto L45
        L30:
            java.lang.String r2 = "transmission"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L44
            r6 = 3
            goto L45
        L3a:
            java.lang.String r2 = "activity"
            boolean r6 = r6.equals(r2)
            if (r6 == 0) goto L44
            r6 = 0
            goto L45
        L44:
            r6 = -1
        L45:
            switch(r6) {
                case 0: goto L56;
                case 1: goto L54;
                case 2: goto L4e;
                case 3: goto L4e;
                case 4: goto L49;
                default: goto L48;
            }
        L48:
            goto L57
        L49:
            if (r4 != 0) goto L52
            if (r5 == 0) goto L57
            goto L52
        L4e:
            if (r4 == 0) goto L57
            if (r5 == 0) goto L57
        L52:
            r1 = 1
            goto L57
        L54:
            r1 = r5
            goto L57
        L56:
            r1 = r4
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.f.c.a(boolean, boolean, java.lang.String):boolean");
    }

    public static boolean b() {
        return System.currentTimeMillis() > com.igexin.push.config.d.c;
    }

    public static boolean b(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return ((Boolean) NotificationManager.class.getDeclaredMethod("areNotificationsEnabled", new Class[0]).invoke((NotificationManager) context.getSystemService(com.igexin.push.core.b.l), new Object[0])).booleanValue();
        }
        if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod(c, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(d).get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        }
        return true;
    }

    public static boolean b(Intent intent, Context context) {
        if (intent != null && context != null) {
            try {
                List<ResolveInfo> listQueryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
                if (listQueryIntentActivities != null) {
                    if (listQueryIntentActivities.size() > 0) {
                        return true;
                    }
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public static boolean b(String str) {
        try {
            if (!TextUtils.isEmpty(com.igexin.push.config.d.G) && !com.igexin.push.a.i.equals(com.igexin.push.config.d.G)) {
                List listAsList = Arrays.asList(com.igexin.push.config.d.G.split(com.igexin.push.core.b.aj));
                if (listAsList.isEmpty()) {
                    return false;
                }
                Iterator it = listAsList.iterator();
                while (it.hasNext()) {
                    if (str.startsWith((String) it.next())) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static void c(Context context) throws GetuiPushException {
        boolean z;
        ApplicationInfo applicationInfo;
        if (context == null) {
            throw new GetuiPushException("传入的context为空");
        }
        Context applicationContext = context.getApplicationContext();
        if (a(applicationContext)) {
            File file = new File(applicationContext.getApplicationInfo().nativeLibraryDir, "libgetuiext3.so");
            if (file.exists()) {
                z = true;
            } else {
                String str = "libgetuiext3.so not found in path: " + file.getAbsolutePath() + " please check!";
                com.igexin.b.a.c.a.c.a().a("[" + a + "] " + str);
                z = false;
            }
            if (!z) {
                throw new GetuiPushException("libgetuiext3.so不存在");
            }
            try {
                applicationInfo = applicationContext.getPackageManager().getApplicationInfo(applicationContext.getPackageName(), 128);
            } catch (PackageManager.NameNotFoundException unused) {
            }
            if (applicationInfo == null || applicationInfo.metaData == null) {
                throw new GetuiPushException("未配置META-DATA");
            }
            if (TextUtils.isEmpty(applicationInfo.metaData.getString(com.igexin.push.core.b.a)) && TextUtils.isEmpty(applicationInfo.metaData.getString("GETUI_APPID"))) {
                throw new GetuiPushException("未配置个推APPID");
            }
            List<ResolveInfo> listQueryIntentServices = applicationContext.getPackageManager().queryIntentServices(new Intent(applicationContext, (Class<?>) PushService.class), 0);
            if (listQueryIntentServices == null || listQueryIntentServices.size() == 0) {
                throw new GetuiPushException("未集成com.igexin.sdk.PushService");
            }
            ServiceInfo serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            if (serviceInfo != null && !serviceInfo.processName.endsWith(":pushservice")) {
                throw new GetuiPushException("PushService需配置在pushservice进程");
            }
            List<ResolveInfo> listQueryIntentActivities = applicationContext.getPackageManager().queryIntentActivities(new Intent(applicationContext, (Class<?>) GActivity.class), 0);
            if (listQueryIntentActivities == null || listQueryIntentActivities.size() == 0) {
                throw new GetuiPushException("未集成com.igexin.sdk.GActivity");
            }
            ActivityInfo activityInfo = listQueryIntentActivities.get(0).activityInfo;
            if (activityInfo != null) {
                if (!activityInfo.processName.endsWith(":pushservice")) {
                    throw new GetuiPushException("GActivity需配置在pushservice进程");
                }
                if (activityInfo.theme != 16973840) {
                    throw new GetuiPushException("GActivity未配置正确theme");
                }
                if (!activityInfo.exported) {
                    throw new GetuiPushException("GActivity.exported属性需配置为true");
                }
            }
            List<ResolveInfo> listQueryIntentActivities2 = applicationContext.getPackageManager().queryIntentActivities(new Intent(applicationContext, (Class<?>) PushActivity.class), 0);
            if (listQueryIntentActivities2 == null || listQueryIntentActivities2.size() == 0) {
                throw new GetuiPushException("未集成 com.igexin.sdk.PushActivity");
            }
            ActivityInfo activityInfo2 = listQueryIntentActivities2.get(0).activityInfo;
            if (activityInfo2 != null) {
                if (!activityInfo2.processName.endsWith(":pushservice")) {
                    throw new GetuiPushException("PushActivity需配置在pushservice进程");
                }
                if (activityInfo2.theme != 16973840) {
                    throw new GetuiPushException("PushActivity未配置正确theme");
                }
            }
            List<ResolveInfo> listQueryBroadcastReceivers = applicationContext.getPackageManager().queryBroadcastReceivers(new Intent(applicationContext, (Class<?>) PushReceiver.class), 0);
            if (listQueryBroadcastReceivers == null || listQueryBroadcastReceivers.size() == 0) {
                throw new GetuiPushException("未集成com.igexin.sdk.PushReceiver");
            }
            try {
                PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4096);
                if (packageInfo != null) {
                    String[] strArr = packageInfo.requestedPermissions;
                    if (strArr == null || strArr.length == 0) {
                        throw new GetuiPushException("Manifest中无权限配置");
                    }
                    List listAsList = Arrays.asList(strArr);
                    if (!listAsList.contains("android.permission.INTERNET")) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.INTERNET");
                    }
                    if (!listAsList.contains(PermissionUtils.PERMISSION_READ_PHONE_STATE)) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.READ_PHONE_STATE");
                    }
                    if (!listAsList.contains("android.permission.ACCESS_NETWORK_STATE")) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.ACCESS_NETWORK_STATE");
                    }
                    if (!listAsList.contains("android.permission.ACCESS_WIFI_STATE")) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.ACCESS_WIFI_STATE");
                    }
                    if (!listAsList.contains(PermissionUtils.PERMISSION_WRITE_EXTERNAL_STORAGE)) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.WRITE_EXTERNAL_STORAGE");
                    }
                    if (!listAsList.contains("android.permission.VIBRATE")) {
                        throw new GetuiPushException("未在Manifest中配置所需权限：android.permission.VIBRATE");
                    }
                }
            } catch (PackageManager.NameNotFoundException unused2) {
            }
            ServiceInfo serviceInfo2 = (ServiceInfo) d.a(applicationContext, PushService.class).first;
            if (serviceInfo2 == null) {
                throw new GetuiPushException("未找到继承 com.igexin.sdk.PushService 的子类");
            }
            if (!serviceInfo2.processName.endsWith(":pushservice")) {
                throw new GetuiPushException("自定义推送服务(Service)需配置在pushservice进程");
            }
            if (!serviceInfo2.exported) {
                throw new GetuiPushException("自定义推送服务(Service).exported需配置为true");
            }
            if (!TextUtils.isEmpty(serviceInfo2.permission)) {
                throw new GetuiPushException("自定义推送服务(Service)不能配置android:permission");
            }
            if (((ServiceInfo) d.a(applicationContext, GTIntentService.class).first) == null) {
                throw new GetuiPushException("未找到继承 com.igexin.sdk.GTIntentService 的子类");
            }
            try {
                Class.forName("com.igexin.base.api.Logger");
            } catch (ClassNotFoundException unused3) {
                throw new GetuiPushException("未配置 com.getui:gtc:version 依赖");
            }
        }
    }

    public static boolean c() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) com.igexin.push.core.e.i.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.getType() == 1;
    }

    public static boolean c(String str) {
        try {
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(com.igexin.push.config.d.J) && !com.igexin.push.a.i.equals(com.igexin.push.config.d.J)) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(Arrays.asList(com.igexin.push.config.d.J.split(com.igexin.push.core.b.aj)));
                if (arrayList.isEmpty()) {
                    return false;
                }
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    if (str.contains((String) it.next())) {
                        return true;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static void d() {
        NetworkInfo.State state = ((ConnectivityManager) com.igexin.push.core.e.i.getSystemService("connectivity")).getNetworkInfo(1).getState();
        if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
            com.igexin.push.core.e.u = 1;
        } else {
            com.igexin.push.core.e.u = 0;
        }
    }

    private static boolean d(Context context) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                if (!TextUtils.isEmpty(applicationInfo.metaData.getString("GETUI_APPID"))) {
                    return true;
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean d(String str) {
        try {
            if (!TextUtils.isEmpty(com.igexin.push.config.d.U) && !com.igexin.push.a.i.equalsIgnoreCase(com.igexin.push.config.d.U)) {
                String[] strArrSplit = com.igexin.push.config.d.U.split(com.igexin.push.core.b.aj);
                if (strArrSplit.length > 0) {
                    String str2 = Build.BRAND;
                    int i = Build.VERSION.SDK_INT;
                    String lowerCase = n.k().toLowerCase();
                    for (String str3 : strArrSplit) {
                        String[] strArrSplit2 = str3.split(":");
                        if (strArrSplit2.length == 4) {
                            String str4 = strArrSplit2[0];
                            int i2 = Integer.parseInt(strArrSplit2[1]);
                            String str5 = strArrSplit2[2];
                            String[] strArrSplit3 = strArrSplit2[3].split(com.alipay.sdk.sys.a.b);
                            if (str2.equalsIgnoreCase(str4) && i >= i2 && strArrSplit3.length == 2) {
                                boolean z = Boolean.parseBoolean(strArrSplit3[0]);
                                boolean z2 = Boolean.parseBoolean(strArrSplit3[1]);
                                if ("*".equals(str5)) {
                                    return a(z, z2, str);
                                }
                                String[] strArrSplit4 = str5.split(com.alipay.sdk.sys.a.b);
                                if (strArrSplit4.length > 0) {
                                    for (String str6 : strArrSplit4) {
                                        if (lowerCase.contains(str6.toLowerCase())) {
                                            return a(z, z2, str);
                                        }
                                    }
                                } else {
                                    continue;
                                }
                            }
                        }
                    }
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    public static void e() {
        com.igexin.push.core.e.v = ((PowerManager) com.igexin.push.core.e.i.getSystemService("power")).isScreenOn() ? 1 : 0;
    }

    private static boolean e(Context context) {
        File file = new File(context.getApplicationInfo().nativeLibraryDir, "libgetuiext3.so");
        if (file.exists()) {
            return true;
        }
        String str = "libgetuiext3.so not found in path: " + file.getAbsolutePath() + " please check!";
        com.igexin.b.a.c.a.c.a().a("[" + a + "] " + str);
        return false;
    }

    private static boolean e(String str) {
        try {
            com.igexin.push.core.e.i.getPackageManager().getPackageInfo(str, 0);
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public static boolean f() {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) com.igexin.push.core.e.i.getSystemService("connectivity");
            if (connectivityManager == null) {
                com.igexin.b.a.c.a.a(a + "|ConnectivityManager is null", new Object[0]);
                return false;
            }
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            com.igexin.b.a.c.a.a(a + "|activeNetworkInfo = " + activeNetworkInfo, new Object[0]);
            if (activeNetworkInfo == null || activeNetworkInfo.getState() != NetworkInfo.State.CONNECTED) {
                com.igexin.b.a.c.a.a(a + "|network available = false", new Object[0]);
                return false;
            }
            com.igexin.b.a.c.a.a(a + (activeNetworkInfo.getType() == 0 ? "mobile" : activeNetworkInfo.getType() == 1 ? "wifi" : com.igexin.push.a.i) + "|connected", new Object[0]);
            return true;
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a(a + "|network available ex =" + th.toString(), new Object[0]);
            return false;
        }
    }

    public static boolean g() {
        return System.currentTimeMillis() >= 1182566108138L;
    }

    public static boolean h() {
        String str = com.igexin.push.config.d.T;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            for (String str2 : str.split(com.igexin.push.core.b.aj)) {
                if (str2.contains("|") && str2.contains("~")) {
                    String strSubstring = str2.substring(0, str2.indexOf("|"));
                    String[] strArrSplit = str2.substring(str2.indexOf("|") + 1).split("~");
                    if (strArrSplit.length == 2) {
                        int i = Integer.parseInt(strArrSplit[0]);
                        int i2 = Integer.parseInt(strArrSplit[1]);
                        if (Build.BRAND.equalsIgnoreCase(strSubstring) && Build.VERSION.SDK_INT >= i && Build.VERSION.SDK_INT <= i2) {
                            return true;
                        }
                    } else {
                        continue;
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }
}
