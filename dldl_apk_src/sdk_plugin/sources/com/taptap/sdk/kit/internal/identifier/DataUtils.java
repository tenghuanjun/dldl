package com.taptap.sdk.kit.internal.identifier;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.taptap.sdk.kit.internal.TapLogger;
import com.taptap.sdk.kit.internal.utils.CryptoUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
class DataUtils {
    private static final String ENCODE_PREFIX = "_encode_identify_prefix_";
    private static final String TAG = "DataUtils";

    DataUtils() {
    }

    private static String handleSecurityData(Context context, String str, String str2) {
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return str2;
        }
        CryptoUtils cryptoUtils = CryptoUtils.getInstance(context);
        if (cryptoUtils != null) {
            if (str2.startsWith(ENCODE_PREFIX)) {
                String strAesDecrypt = cryptoUtils.aesDecrypt(str2.substring(24));
                if (strAesDecrypt == null) {
                    PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).apply();
                }
                return strAesDecrypt;
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, ENCODE_PREFIX + cryptoUtils.aesEncrypt(str2)).apply();
            return str2;
        }
        TapLogger.logd(TAG, " data should be decrypt but util is null");
        return str2.startsWith(ENCODE_PREFIX) ? "" : str2;
    }

    public static String getDeviceId(Context context) {
        String strHandleSecurityData;
        String strHandleSecurityData2;
        String string;
        String strHandleSecurityData3;
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences != null) {
            String str = null;
            try {
                string = defaultSharedPreferences.getString("tapdb_game_mobile_identify", null);
                if (string != null) {
                    try {
                        string = handleSecurityData(context, "tapdb_game_mobile_identify", string);
                        if (string != null) {
                            return string;
                        }
                    } catch (Exception e) {
                        e = e;
                        TapLogger.loge(TAG, "getDeviceId", e);
                    }
                }
            } catch (Exception e2) {
                e = e2;
                string = null;
            }
            Map<String, ?> all = defaultSharedPreferences.getAll();
            Iterator<String> it = all.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next = it.next();
                if (next.contains("__game_mobile__0__")) {
                    string = (String) all.get(next);
                    str = next;
                    break;
                }
            }
            if (string != null && (strHandleSecurityData3 = handleSecurityData(context, str, string)) != null) {
                return strHandleSecurityData3;
            }
        }
        String tapDBId = getTapDBId(TapIdentifierUtil.getAndroidID(context));
        if (!TextUtils.isEmpty(tapDBId) && tapDBId.length() <= 256 && (strHandleSecurityData2 = handleSecurityData(context, "tapdb_game_mobile_identify", tapDBId)) != null) {
            return strHandleSecurityData2;
        }
        String string2 = UUID.randomUUID().toString();
        return (TextUtils.isEmpty(string2) || string2.length() > 256 || (strHandleSecurityData = handleSecurityData(context, "tapdb_game_mobile_identify", string2)) == null) ? string2 : strHandleSecurityData;
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String getTapDBId(java.lang.String r7) {
        /*
            java.lang.String r0 = "0000000000000000"
            java.lang.String r1 = "ffffffffffffffff"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1}
            r1 = 0
            if (r7 == 0) goto L41
            java.lang.String r2 = "^[0-9a-zA-Z]{8,16}$"
            boolean r2 = r7.matches(r2)
            if (r2 != 0) goto L15
        L13:
            r7 = r1
            goto L41
        L15:
            int r2 = r7.length()
            r3 = 16
            if (r2 >= r3) goto L36
            r4 = 0
        L1e:
            int r5 = 16 - r2
            if (r4 >= r5) goto L36
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            r5.<init>()
            java.lang.String r6 = "0"
            r5.append(r6)
            r5.append(r7)
            java.lang.String r7 = r5.toString()
            int r4 = r4 + 1
            goto L1e
        L36:
            java.util.List r0 = java.util.Arrays.asList(r0)
            boolean r0 = r0.contains(r7)
            if (r0 == 0) goto L41
            goto L13
        L41:
            if (r7 == 0) goto L44
            goto L4c
        L44:
            java.util.UUID r7 = java.util.UUID.randomUUID()
            java.lang.String r7 = r7.toString()
        L4c:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.taptap.sdk.kit.internal.identifier.DataUtils.getTapDBId(java.lang.String):java.lang.String");
    }
}
