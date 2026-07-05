package com.igexin.push.f;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.igexin.push.config.SDKUrlConfig;
import com.tencent.connect.common.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class i {
    private static final int a = 10000;
    private static final String b = "ErrorReport";

    public interface a {
        void a(boolean z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static String a(Context context) {
        String packageName = context.getPackageName();
        String string = null;
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName, 128);
            if (applicationInfo != null && applicationInfo.metaData != null) {
                string = applicationInfo.metaData.getString("GETUI_APPID");
            }
        } catch (Exception unused) {
        }
        String str = Build.VERSION.SDK;
        String str2 = Build.VERSION.RELEASE;
        File file = new File(context.getApplicationInfo().nativeLibraryDir + File.separator + "libgetuiext3.so");
        StringBuilder sb = new StringBuilder();
        sb.append(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date()));
        sb.append("|");
        sb.append(string);
        sb.append("|");
        sb.append("3.2.4.0");
        sb.append("|");
        sb.append(file.exists());
        sb.append("|");
        sb.append(n.e() == null ? "" : n.e());
        sb.append("|");
        sb.append(Build.MODEL);
        sb.append("|");
        sb.append(str);
        sb.append("|");
        sb.append(str2);
        sb.append("|");
        sb.append(n.a(context));
        sb.append("|");
        sb.append(n.j());
        sb.append("|");
        sb.append(packageName);
        if (h.a != null) {
            sb.append("|");
            sb.append(h.a);
        }
        com.igexin.b.a.c.a.a("ErrorReport|" + sb.toString(), new Object[0]);
        return sb.toString();
    }

    public static void a(final a aVar, final Context context) {
        new Thread(new Runnable() { // from class: com.igexin.push.f.i.1
            @Override // java.lang.Runnable
            public final void run() {
                boolean z = false;
                try {
                    if (i.a()) {
                        com.igexin.push.core.d.c.a().a(com.igexin.push.core.d.c.d, Long.valueOf(System.currentTimeMillis()));
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("action", "upload_BI");
                        jSONObject.put("BIType", Constants.VIA_REPORT_TYPE_CHAT_AUDIO);
                        jSONObject.put("cid", "0");
                        jSONObject.put("BIData", new String(com.igexin.b.a.b.g.c(i.a(context).getBytes()), "UTF-8"));
                        byte[] bArrA = q.a(SDKUrlConfig.getBiUploadServiceUrl(), com.igexin.b.b.a.b(jSONObject.toString().getBytes()));
                        if (bArrA != null) {
                            new String(bArrA);
                        }
                        z = true;
                    }
                } catch (Throwable th) {
                    com.igexin.b.a.c.a.a("ErrorReport|report 25 ex = " + th.toString(), new Object[0]);
                }
                a aVar2 = aVar;
                if (aVar2 != null) {
                    aVar2.a(z);
                }
            }
        }).start();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean a() {
        try {
            return System.currentTimeMillis() - com.igexin.push.core.d.c.a().b(com.igexin.push.core.d.c.d) > 86400000;
        } catch (Exception unused) {
            return false;
        }
    }
}
