package a.a.a.g;

import android.content.Context;
import android.os.Process;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.hjq.permissions.Permission;
import org.json.JSONArray;

/* JADX INFO: compiled from: SystemUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f136a = "";
    public static String b = "";
    public static String c;
    public static String d;
    public static String e;
    public static String f;
    public static JSONArray g;

    public static String a(Context context) {
        if (context == null || !TextUtils.isEmpty(f136a)) {
            return f136a;
        }
        try {
            if (context.checkPermission(Permission.READ_PHONE_STATE, Process.myPid(), Process.myUid()) == 0) {
                f136a = ((TelephonyManager) context.getApplicationContext().getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getDeviceId();
            }
        } catch (Exception e2) {
            b.c("SystemUtils", "getImei exception:" + e2.toString());
        }
        return a(f136a);
    }

    public static String a(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }
}
