package com.igexin.push.core.b;

import com.igexin.push.extension.mod.BaseActionBean;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class l extends BaseActionBean {
    private long a;

    private long a() {
        return this.a;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0060  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.igexin.push.core.b.l a(java.lang.String r5) throws org.json.JSONException {
        /*
            boolean r0 = android.text.TextUtils.isEmpty(r5)
            if (r0 != 0) goto L65
            com.igexin.push.core.b.l r0 = new com.igexin.push.core.b.l
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>(r5)
            java.lang.String r5 = "actionid"
            boolean r5 = r1.has(r5)
            if (r5 == 0) goto L21
            java.lang.String r5 = "actionid"
            java.lang.String r5 = r1.getString(r5)
            r0.setActionId(r5)
        L21:
            java.lang.String r5 = "type"
            boolean r5 = r1.has(r5)
            if (r5 == 0) goto L32
            java.lang.String r5 = "type"
            java.lang.String r5 = r1.getString(r5)
            r0.setType(r5)
        L32:
            java.lang.String r5 = "do"
            boolean r5 = r1.has(r5)
            if (r5 == 0) goto L43
            java.lang.String r5 = "do"
            java.lang.String r5 = r1.getString(r5)
            r0.setDoActionId(r5)
        L43:
            java.lang.String r5 = "delay"
            boolean r5 = r1.has(r5)
            if (r5 == 0) goto L60
            java.lang.String r5 = "delay"
            double r1 = r1.getDouble(r5)
            r3 = 0
            int r5 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r5 <= 0) goto L60
            r3 = 4652007308841189376(0x408f400000000000, double:1000.0)
            double r1 = r1 * r3
            long r1 = (long) r1
            goto L62
        L60:
            r1 = 200(0xc8, double:9.9E-322)
        L62:
            r0.a = r1
            goto L66
        L65:
            r0 = 0
        L66:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.igexin.push.core.b.l.a(java.lang.String):com.igexin.push.core.b.l");
    }

    private void a(long j) {
        this.a = j;
    }
}
