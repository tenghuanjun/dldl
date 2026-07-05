package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c extends a {
    private final JSONObject d;

    public c(Context context, DownloadSetting downloadSetting, String str, JSONObject jSONObject) {
        super(context, downloadSetting, str);
        this.d = jSONObject;
    }

    @Override // com.ss.android.socialbase.appdownloader.a.e
    public Intent b() {
        String strOptString = this.d.optString("action");
        String strOptString2 = this.d.optString("category");
        int iOptInt = this.d.optInt("flags", 1342210048);
        String strOptString3 = this.d.optString("path_extra_key");
        String strOptString4 = this.d.optString("path_data_key");
        JSONObject jSONObjectOptJSONObject = this.d.optJSONObject("extra");
        JSONObject jSONObjectOptJSONObject2 = this.d.optJSONObject("extra_type");
        if (TextUtils.isEmpty(strOptString)) {
            return null;
        }
        Intent intent = new Intent(strOptString);
        if (!TextUtils.isEmpty(strOptString2)) {
            intent.addCategory(strOptString2);
        }
        if (!TextUtils.isEmpty(strOptString4)) {
            try {
                intent.setData(Uri.parse(String.format(strOptString4, this.c)));
            } catch (Throwable unused) {
            }
        }
        intent.setFlags(iOptInt);
        if (!TextUtils.isEmpty(strOptString3)) {
            intent.putExtra(strOptString3, this.c);
        }
        a(intent, jSONObjectOptJSONObject, jSONObjectOptJSONObject2);
        return intent;
    }

    private static void a(Intent intent, JSONObject jSONObject, JSONObject jSONObject2) {
        Iterator<String> itKeys;
        if (jSONObject == null || jSONObject2 == null || jSONObject.length() != jSONObject2.length() || intent == null || (itKeys = jSONObject.keys()) == null) {
            return;
        }
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString = jSONObject2.optString(next);
            if (strOptString != null) {
                a(jSONObject, next, strOptString, intent);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    /* JADX WARN: Removed duplicated region for block: B:20:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static void a(org.json.JSONObject r5, java.lang.String r6, java.lang.String r7, android.content.Intent r8) {
        /*
            int r0 = r7.hashCode()
            r1 = 4
            r2 = 3
            r3 = 2
            r4 = 1
            switch(r0) {
                case -1325958191: goto L34;
                case -891985903: goto L2a;
                case 104431: goto L20;
                case 3327612: goto L16;
                case 64711720: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L3e
        Lc:
            java.lang.String r0 = "boolean"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L3e
            r7 = 1
            goto L3f
        L16:
            java.lang.String r0 = "long"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L3e
            r7 = 2
            goto L3f
        L20:
            java.lang.String r0 = "int"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L3e
            r7 = 0
            goto L3f
        L2a:
            java.lang.String r0 = "string"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L3e
            r7 = 4
            goto L3f
        L34:
            java.lang.String r0 = "double"
            boolean r7 = r7.equals(r0)
            if (r7 == 0) goto L3e
            r7 = 3
            goto L3f
        L3e:
            r7 = -1
        L3f:
            if (r7 == 0) goto L6a
            if (r7 == r4) goto L62
            if (r7 == r3) goto L5a
            if (r7 == r2) goto L52
            if (r7 == r1) goto L4a
            goto L71
        L4a:
            java.lang.String r5 = r5.optString(r6)
            r8.putExtra(r6, r5)
            goto L71
        L52:
            double r0 = r5.optDouble(r6)
            r8.putExtra(r6, r0)
            goto L71
        L5a:
            long r0 = r5.optLong(r6)
            r8.putExtra(r6, r0)
            goto L71
        L62:
            boolean r5 = r5.optBoolean(r6)
            r8.putExtra(r6, r5)
            goto L71
        L6a:
            int r5 = r5.optInt(r6)
            r8.putExtra(r6, r5)
        L71:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.socialbase.appdownloader.a.c.a(org.json.JSONObject, java.lang.String, java.lang.String, android.content.Intent):void");
    }
}
