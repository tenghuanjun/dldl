package cn.thinkingdata.android.utils;

import android.text.TextUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Pattern;
import kotlin.jvm.internal.ByteCompanionObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class h {
    private static final Pattern a = Pattern.compile("^[a-zA-Z][a-zA-Z\\d_]{0,49}$", 2);
    private static final ArrayList<String> b = new a();

    class a extends ArrayList {
        a() {
            add("#bundle_id");
            add("#duration");
        }
    }

    public static boolean a(String str) {
        return str == null || !a.matcher(str).matches();
    }

    public static boolean a(JSONObject jSONObject) {
        if (jSONObject == null || !TDLog.mEnableLog) {
            return true;
        }
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            if (TextUtils.isEmpty(next)) {
                TDLog.d("ThinkingAnalytics.PropertyUtils", "Empty property name is not allowed.");
            }
            if (!a.matcher(next).matches() && !b.contains(next)) {
                TDLog.d("ThinkingAnalytics.PropertyUtils", "Property name[" + next + "] is not valid. The property KEY must be string that starts with English letter, and contains letter, number, and '_'. The max length of the property KEY is 50. ");
            }
            try {
                Object obj = jSONObject.get(next);
                if (!(obj instanceof String) && !(obj instanceof Number) && !(obj instanceof Boolean) && !(obj instanceof Date) && !(obj instanceof JSONArray) && !(obj instanceof JSONObject)) {
                    TDLog.d("ThinkingAnalytics.PropertyUtils", "Property value must be type String, Number, Boolean, Date, JSONObject or JSONArray");
                }
                if (obj instanceof Number) {
                    double dDoubleValue = ((Number) obj).doubleValue();
                    if (dDoubleValue > 9.999999999999998E12d || dDoubleValue < -9.999999999999998E12d) {
                        TDLog.d("ThinkingAnalytics.PropertyUtils", "The number value [" + obj + "] is invalid.");
                    }
                }
            } catch (JSONException e) {
                TDLog.d("ThinkingAnalytics.PropertyUtils", "Unexpected parameters." + e);
                return false;
            }
        }
        return true;
    }

    public static byte[] a(String str, int i) throws UnsupportedEncodingException {
        int i2;
        int i3;
        byte[] bytes = str.getBytes("UTF-8");
        if (bytes.length <= i) {
            return bytes;
        }
        if ((bytes[i] & ByteCompanionObject.MIN_VALUE) == 0) {
            return Arrays.copyOf(bytes, i);
        }
        int i4 = 0;
        while (true) {
            i2 = i - i4;
            i3 = i2 - 1;
            if ((bytes[i3] & ByteCompanionObject.MIN_VALUE) <= 0 || (bytes[i3] & 64) != 0) {
                break;
            }
            i4++;
        }
        return (bytes[i3] & ByteCompanionObject.MIN_VALUE) > 0 ? Arrays.copyOf(bytes, i3) : Arrays.copyOf(bytes, i2);
    }
}
