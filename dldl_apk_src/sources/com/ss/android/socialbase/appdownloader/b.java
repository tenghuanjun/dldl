package com.ss.android.socialbase.appdownloader;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.Settings;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.TTAdConstant;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity;
import com.ss.android.socialbase.downloader.common.AppStatusManager;
import com.ss.android.socialbase.downloader.constants.DownloadConstants;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import com.ss.android.socialbase.downloader.constants.SpJsonConstants;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import com.ss.android.socialbase.downloader.thread.WeakDownloadHandler;
import com.ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class b {
    private static final String a = b.class.getSimpleName();
    private static c b;
    private static a c;

    /* JADX INFO: renamed from: com.ss.android.socialbase.appdownloader.b$b, reason: collision with other inner class name */
    interface InterfaceC0102b {
        boolean a(Context context);
    }

    public interface c {
        void a(DownloadInfo downloadInfo, com.ss.android.socialbase.appdownloader.a aVar);
    }

    public static boolean a(Context context, DownloadInfo downloadInfo, Intent intent, boolean z) {
        JSONArray jSONArrayOptJSONArray = DownloadSetting.obtain(downloadInfo.getId()).optJSONArray(DownloadSettingKeys.KEY_AH_PLANS);
        if (jSONArrayOptJSONArray == null) {
            return false;
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (com.ss.android.socialbase.appdownloader.f.a.a(jSONObjectOptJSONObject) && a(context, downloadInfo, intent, jSONObjectOptJSONObject, z)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:100:0x0188  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0199  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x015c  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0164 A[PHI: r15
  0x0164: PHI (r15v10 com.ss.android.socialbase.appdownloader.a) = 
  (r15v2 com.ss.android.socialbase.appdownloader.a)
  (r15v8 com.ss.android.socialbase.appdownloader.a)
  (r15v11 com.ss.android.socialbase.appdownloader.a)
 binds: [B:91:0x0162, B:81:0x013a, B:69:0x010d] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static boolean a(android.content.Context r11, com.ss.android.socialbase.downloader.model.DownloadInfo r12, android.content.Intent r13, org.json.JSONObject r14, boolean r15) {
        /*
            Method dump skipped, instruction units count: 464
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.b.a(android.content.Context, com.ss.android.socialbase.downloader.model.DownloadInfo, android.content.Intent, org.json.JSONObject, boolean):boolean");
    }

    private static boolean a(Context context, DownloadInfo downloadInfo, JSONObject jSONObject, com.ss.android.socialbase.appdownloader.a aVar, DownloadSetting downloadSetting) {
        boolean zB;
        String strOptString = jSONObject.optString("type");
        aVar.a = strOptString;
        Intent intentB = com.ss.android.socialbase.appdownloader.a.d.a(context, "vbi", jSONObject, downloadInfo).b();
        StringBuilder sb = new StringBuilder();
        try {
            zB = b(context, intentB);
        } catch (Throwable th) {
            sb.append(strOptString);
            sb.append(" startActivity failed : ");
            sb.append(a(th));
            a(aVar, 1);
            zB = false;
        }
        if (!zB) {
            aVar.c = sb.toString();
        } else {
            aVar.b = 0;
        }
        return true;
    }

    private static boolean a(Context context, DownloadInfo downloadInfo, JSONObject jSONObject, com.ss.android.socialbase.appdownloader.a aVar) {
        boolean z;
        if (context != null && jSONObject != null) {
            String strOptString = jSONObject.optString(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_PLANS);
            aVar.e = strOptString;
            if (!TextUtils.isEmpty(strOptString)) {
                String[] strArrSplit = strOptString.split(com.igexin.push.core.b.aj);
                String savePath = downloadInfo.getSavePath();
                if (TextUtils.isEmpty(savePath)) {
                    return false;
                }
                File file = new File(savePath);
                StringBuilder sb = new StringBuilder();
                String str = null;
                int length = strArrSplit.length;
                int i = 0;
                while (true) {
                    z = true;
                    if (i >= length) {
                        z = false;
                        break;
                    }
                    String str2 = strArrSplit[i];
                    com.ss.android.socialbase.appdownloader.a.a aVarA = com.ss.android.socialbase.appdownloader.a.d.a(context, str2, jSONObject, downloadInfo);
                    if (aVarA != null) {
                        Intent intentB = aVarA.b();
                        if (intentB != null) {
                            if (a(file, downloadInfo, jSONObject)) {
                                try {
                                    a(context, intentB, false);
                                    str = str2;
                                    break;
                                } catch (Throwable th) {
                                    sb.append(str2);
                                    sb.append(" startActivity failed : ");
                                    sb.append(a(th));
                                    a(aVar, 1);
                                }
                            } else {
                                a(aVar, 6);
                                sb.append(str2);
                                sb.append(" createDescFile failed! ");
                            }
                        } else {
                            a(aVar, 3);
                            sb.append(str2);
                            sb.append(" resolveActivity failed! ");
                        }
                    }
                    sb.append("  ");
                    i++;
                }
                if (!z) {
                    aVar.c = sb.toString();
                } else {
                    aVar.d = str;
                    aVar.b = 0;
                }
                return z;
            }
        }
        return false;
    }

    private static boolean b(Context context, DownloadInfo downloadInfo, JSONObject jSONObject, com.ss.android.socialbase.appdownloader.a aVar) {
        if (context != null && jSONObject != null) {
            String savePath = downloadInfo.getSavePath();
            if (TextUtils.isEmpty(savePath)) {
                return false;
            }
            aVar.d = MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM;
            com.ss.android.socialbase.appdownloader.a.a aVarA = com.ss.android.socialbase.appdownloader.a.d.a(context, MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM, jSONObject, downloadInfo);
            if (aVarA == null || !aVarA.a()) {
                aVar.b = 3;
            } else {
                Intent intentB = aVarA.b();
                if (intentB == null) {
                    return false;
                }
                if (a(new File(savePath), downloadInfo, jSONObject)) {
                    if (b(context, intentB)) {
                        aVar.b = 0;
                        return true;
                    }
                    aVar.b = 1;
                } else {
                    aVar.b = 6;
                }
                return false;
            }
        }
        return false;
    }

    public static int a(DownloadSetting downloadSetting) {
        int i;
        if (!(downloadSetting.optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR) != null ? !TextUtils.isEmpty(r0.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME)) : false)) {
            return 5;
        }
        if (!DownloadSetting.obtainGlobal().optBugFix(DownloadSettingKeys.BugFix.BUGFIX_GET_DOWNLOAD_INFO_BY_LIST)) {
            return 4;
        }
        JSONArray jSONArrayOptJSONArray = downloadSetting.optJSONArray(DownloadSettingKeys.KEY_AH_PLANS);
        int i2 = -1;
        if (jSONArrayOptJSONArray != null) {
            int length = jSONArrayOptJSONArray.length();
            for (int i3 = 0; i3 < length; i3++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i3);
                if (com.ss.android.socialbase.appdownloader.f.a.a(jSONObjectOptJSONObject)) {
                    String strOptString = jSONObjectOptJSONObject.optString("type");
                    if ("plan_a".equals(strOptString) || "plan_b".equals(strOptString) || "plan_e".equals(strOptString) || "plan_f".equals(strOptString)) {
                        com.ss.android.socialbase.appdownloader.a aVarA = a(jSONObjectOptJSONObject, downloadSetting);
                        i = aVarA.b;
                        if (aVarA.b == 0) {
                            return 0;
                        }
                    } else {
                        if (!"plan_d".equalsIgnoreCase(strOptString) && !"plan_h".equalsIgnoreCase(strOptString)) {
                            if ("plan_g".equalsIgnoreCase(strOptString)) {
                                com.ss.android.socialbase.appdownloader.a aVarB = b(jSONObjectOptJSONObject, downloadSetting);
                                i = aVarB.b;
                                if (aVarB.b == 0) {
                                }
                            } else {
                                continue;
                            }
                        }
                        return 0;
                    }
                    i2 = i;
                }
            }
        }
        return i2;
    }

    public static com.ss.android.socialbase.appdownloader.a a(JSONObject jSONObject, DownloadSetting downloadSetting) {
        com.ss.android.socialbase.appdownloader.a aVar = new com.ss.android.socialbase.appdownloader.a();
        if (jSONObject == null) {
            return aVar;
        }
        String strOptString = jSONObject.optString("type");
        aVar.a = strOptString;
        if ("plan_b".equals(strOptString)) {
            aVar.e = MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM;
            if (com.ss.android.socialbase.appdownloader.a.d.a(DownloadComponentManager.getAppContext(), MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM, jSONObject, downloadSetting)) {
                aVar.b = 0;
                return aVar;
            }
            a(aVar, 3);
        } else {
            String strOptString2 = jSONObject.optString(DownloadSettingKeys.AhPlans.KEY_AH_DEVICE_PLANS);
            aVar.e = strOptString2;
            if (!TextUtils.isEmpty(strOptString2)) {
                for (String str : strOptString2.split(com.igexin.push.core.b.aj)) {
                    if (com.ss.android.socialbase.appdownloader.a.d.a(DownloadComponentManager.getAppContext(), str, jSONObject, downloadSetting)) {
                        aVar.b = 0;
                        return aVar;
                    }
                    a(aVar, 3);
                }
            }
        }
        return aVar;
    }

    public static com.ss.android.socialbase.appdownloader.a b(JSONObject jSONObject, DownloadSetting downloadSetting) {
        com.ss.android.socialbase.appdownloader.a aVar = new com.ss.android.socialbase.appdownloader.a();
        if (jSONObject == null) {
            return aVar;
        }
        aVar.a = jSONObject.optString("type");
        aVar.e = "vbi";
        if (com.ss.android.socialbase.appdownloader.a.d.a(DownloadComponentManager.getAppContext(), "vbi", jSONObject, downloadSetting)) {
            aVar.b = 0;
        } else {
            a(aVar, 3);
        }
        return aVar;
    }

    public static com.ss.android.socialbase.appdownloader.a a(JSONObject jSONObject, String str, Context context, DownloadSetting downloadSetting) {
        com.ss.android.socialbase.appdownloader.a aVar = new com.ss.android.socialbase.appdownloader.a();
        if (jSONObject != null && com.ss.android.socialbase.appdownloader.f.e.c()) {
            aVar.a = jSONObject.optString("type");
            if (downloadSetting.optInt(com.igexin.push.core.b.ac, 0) == 1) {
                aVar.b = 0;
                return aVar;
            }
            if (a(context)) {
                aVar.b = 2;
            } else if (com.ss.android.socialbase.appdownloader.f.a.a(str) != null) {
                aVar.b = 0;
            } else {
                aVar.b = 9;
            }
        }
        return aVar;
    }

    private static void a(com.ss.android.socialbase.appdownloader.a aVar, int i) {
        if (aVar.b != -1) {
            aVar.b = (aVar.b * 10) + i;
        } else {
            aVar.b = i;
        }
    }

    private static boolean a(File file, DownloadInfo downloadInfo, JSONObject jSONObject) {
        if (file == null) {
            return false;
        }
        String path = file.getPath();
        JSONObject jSONObjectOptJSONObject = DownloadSetting.obtain(downloadInfo.getId()).optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
        File file2 = null;
        String strOptString = jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_INSTALL_DESC) : null;
        if (!TextUtils.isEmpty(strOptString) && !TextUtils.isEmpty(strOptString)) {
            file2 = new File(path + File.separator + strOptString);
        }
        if (file2 == null) {
            return true;
        }
        try {
            if (!file2.createNewFile()) {
                return true;
            }
            file2.deleteOnExit();
            return true;
        } catch (IOException unused) {
            return false;
        }
    }

    public static boolean a(Context context, Intent intent, JSONObject jSONObject, int i, com.ss.android.socialbase.appdownloader.a aVar) {
        if (context != null && jSONObject != null) {
            long jOptLong = jSONObject.optLong(DownloadSettingKeys.AhPlans.KEY_JUMP_INTERVAL, 0L);
            if (jOptLong <= 0) {
                return false;
            }
            SharedPreferences sharedPreferences = context.getSharedPreferences(DownloadConstants.SP_ANTI_HIJACK_CONFIG, 0);
            if ((System.currentTimeMillis() - sharedPreferences.getLong(SpJsonConstants.KEY_LAST_JUMP_UNKNOWN_SOURCE_TIME, 0L)) / 60000 >= jOptLong && !a(context)) {
                sharedPreferences.edit().putLong(SpJsonConstants.KEY_LAST_JUMP_UNKNOWN_SOURCE_TIME, System.currentTimeMillis()).apply();
                if (jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_SHOW_UNKNOWN_SOURCE_DIALOG, 0) == 1) {
                    Intent intent2 = new Intent(context, (Class<?>) JumpUnknownSourceActivity.class);
                    intent2.addFlags(DownloadExpSwitchCode.BUGFIX_GETPACKAGEINFO_BY_UNZIP);
                    intent2.putExtra("intent", intent);
                    intent2.putExtra(com.igexin.push.core.b.X, jSONObject.toString());
                    intent2.putExtra("id", i);
                    try {
                        if (a(context, intent2, false)) {
                            d(i, jSONObject);
                        }
                        return true;
                    } catch (Throwable th) {
                        if (aVar != null) {
                            aVar.b = 1;
                            aVar.c = "tryShowUnknownSourceDialog" + a(th);
                        }
                        return false;
                    }
                }
                if (a(context, intent, i, jSONObject)) {
                    c(i, jSONObject);
                }
                return true;
            }
        }
        return false;
    }

    public static boolean a(Context context, Intent intent, int i, JSONObject jSONObject) {
        try {
            if (com.ss.android.socialbase.appdownloader.f.e.c() && Build.VERSION.SDK_INT < 26 && !d(context)) {
                com.ss.android.socialbase.appdownloader.a.f fVar = new com.ss.android.socialbase.appdownloader.a.f(context);
                if (fVar.a()) {
                    a(context, intent, i, jSONObject, new InterfaceC0102b() { // from class: com.ss.android.socialbase.appdownloader.b.1
                        @Override // com.ss.android.socialbase.appdownloader.b.InterfaceC0102b
                        public boolean a(Context context2) {
                            return b.d(context2);
                        }
                    });
                    return b(context, fVar.b());
                }
            } else if (Build.VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26 && !e(context)) {
                com.ss.android.socialbase.appdownloader.a.b bVar = new com.ss.android.socialbase.appdownloader.a.b(context);
                if (bVar.a()) {
                    a(context, intent, i, jSONObject, new InterfaceC0102b() { // from class: com.ss.android.socialbase.appdownloader.b.2
                        @Override // com.ss.android.socialbase.appdownloader.b.InterfaceC0102b
                        public boolean a(Context context2) {
                            return b.e(context2);
                        }
                    });
                    return b(context, bVar.b());
                }
            }
        } catch (Throwable unused) {
        }
        return false;
    }

    public static boolean a(Context context) {
        if (context == null) {
            return true;
        }
        if (com.ss.android.socialbase.appdownloader.f.e.c() && Build.VERSION.SDK_INT < 26) {
            return d(context);
        }
        if (Build.VERSION.SDK_INT >= 26 && context.getApplicationInfo().targetSdkVersion >= 26) {
            return e(context);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return Settings.Secure.getInt(context.getContentResolver(), "install_non_market_apps", 1) > 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean e(Context context) {
        if (context == null) {
            return true;
        }
        try {
            return context.getPackageManager().canRequestPackageInstalls();
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean a() {
        return e.a == 1;
    }

    private static void d(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_SHOW, jSONObject2);
    }

    public static void a(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_CONFIRM, jSONObject2);
    }

    public static void b(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_DIALOG_CANCEL, jSONObject2);
    }

    public static void c(int i, JSONObject jSONObject) {
        int i2 = 1;
        boolean z = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1;
        JSONObject jSONObject2 = new JSONObject();
        if (!z) {
            i2 = 2;
        }
        try {
            jSONObject2.put("scene", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_OPEN_SETTING, jSONObject2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(int i, boolean z, boolean z2) {
        JSONObject jSONObject = new JSONObject();
        int i2 = 1;
        try {
            jSONObject.put("scene", z ? 1 : 2);
            if (!z2) {
                i2 = 2;
            }
            jSONObject.put("result_code", i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        DownloadComponentManager.getEventListener().onUnityEvent(i, MonitorConstants.UnityLabel.GUIDE_AUTH_RESULT, jSONObject);
    }

    private static void a(Context context, Intent intent, int i, JSONObject jSONObject, InterfaceC0102b interfaceC0102b) {
        if (c != null) {
            AppStatusManager.getInstance().unregisterAppSwitchListener(c);
            c = null;
        }
        c = new a(context, intent, i, jSONObject, interfaceC0102b);
        AppStatusManager.getInstance().registerAppSwitchListener(c);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(Context context, Intent intent) {
        return a(context, intent, true);
    }

    public static boolean a(Context context, Intent intent, boolean z) {
        if (context == null || intent == null) {
            return false;
        }
        if (z) {
            try {
                intent.putExtra("start_only_for_android", true);
                context.startActivity(intent);
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
        intent.putExtra("start_only_for_android", true);
        context.startActivity(intent);
        return true;
    }

    public static String a(Throwable th) {
        String string = th.toString();
        return string.length() > 800 ? string.substring(0, TTAdConstant.SHOW_POLL_TIME_SPLASH_DEFAULT) : string;
    }

    private static class d implements Callable<Boolean> {
        private final Context a;
        private final InterfaceC0102b b;
        private final Handler c;
        private final long d;

        public d(Handler handler, Context context, InterfaceC0102b interfaceC0102b, long j) {
            this.a = context;
            this.b = interfaceC0102b;
            this.c = handler;
            this.d = j;
        }

        @Override // java.util.concurrent.Callable
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public Boolean call() throws Exception {
            if (this.b != null && this.d > 0 && this.d <= com.igexin.push.config.c.i) {
                Context context = this.a;
                boolean zA = context != null ? this.b.a(context) : false;
                Message messageObtain = Message.obtain();
                if (zA) {
                    messageObtain.what = 2;
                    this.c.sendMessage(messageObtain);
                } else {
                    messageObtain.what = 1;
                    this.c.sendMessageDelayed(messageObtain, this.d);
                }
                return false;
            }
            return false;
        }
    }

    public static void a(c cVar) {
        b = cVar;
    }

    private static class a implements AppStatusManager.AppStatusChangeListener {
        private final e a;
        private final int b;
        private JSONObject c;

        public a(Context context, Intent intent, int i, JSONObject jSONObject, InterfaceC0102b interfaceC0102b) {
            this.c = jSONObject;
            int iOptInt = jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNWON_SOURCE_QUERY_INTERVAL, 1000);
            this.b = iOptInt;
            this.a = new e(context, intent, i, interfaceC0102b, iOptInt);
        }

        @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppForeground() {
            if (!this.a.i) {
                Message messageObtain = Message.obtain();
                messageObtain.what = 2;
                this.a.f.sendMessage(messageObtain);
            }
            AppStatusManager.getInstance().unregisterAppSwitchListener(this);
            a unused = b.c = null;
        }

        @Override // com.ss.android.socialbase.downloader.common.AppStatusManager.AppStatusChangeListener
        public void onAppBackground() {
            int iOptInt = this.c.optInt(DownloadSettingKeys.AhPlans.KEY_JUMP_UNKNWON_SOURCE_WAIT_TIME_OUT, 20);
            Message messageObtain = Message.obtain();
            messageObtain.what = 1;
            this.a.f.sendMessage(messageObtain);
            if (iOptInt <= 0 || iOptInt >= 60) {
                return;
            }
            Message messageObtain2 = Message.obtain();
            messageObtain2.what = 2;
            this.a.f.sendMessageDelayed(messageObtain2, iOptInt * 1000);
        }
    }

    private static class e implements WeakDownloadHandler.IHandler {
        public static int a;
        private static int b;
        private final Context c;
        private final Intent d;
        private final InterfaceC0102b e;
        private final Handler f;
        private final long g;
        private Future<Boolean> h;
        private boolean i = false;

        public e(Context context, Intent intent, int i, InterfaceC0102b interfaceC0102b, long j) {
            this.c = context;
            this.d = intent;
            b = i;
            this.e = interfaceC0102b;
            this.f = new WeakDownloadHandler(Looper.getMainLooper(), this);
            this.g = j;
        }

        @Override // com.ss.android.socialbase.downloader.thread.WeakDownloadHandler.IHandler
        public void handleMsg(Message message) {
            if (message != null) {
                if (message.what == 1) {
                    long j = this.g;
                    if (j <= 0 || j > com.igexin.push.config.c.i) {
                        return;
                    }
                    a = 1;
                    this.h = DownloadComponentManager.getCPUThreadExecutor().submit(new d(this.f, this.c, this.e, this.g));
                    return;
                }
                if (message.what == 2) {
                    a = 2;
                    this.f.removeMessages(2);
                    this.f.removeMessages(1);
                    Future<Boolean> future = this.h;
                    if (future != null) {
                        future.cancel(true);
                    }
                    if (!this.i && (Build.VERSION.SDK_INT < 29 || AppStatusManager.getInstance().isAppForeground())) {
                        Intent intent = this.d;
                        if (intent != null) {
                            b.b(this.c, intent);
                        } else {
                            DownloadInfo downloadInfo = Downloader.getInstance(this.c).getDownloadInfo(b);
                            if (downloadInfo != null && downloadInfo.isDownloadOverStatus()) {
                                com.ss.android.socialbase.appdownloader.c.b(this.c, b, false);
                            }
                        }
                        this.i = true;
                    }
                    b.b(b, this.d == null, b.a(this.c));
                }
            }
        }
    }
}
