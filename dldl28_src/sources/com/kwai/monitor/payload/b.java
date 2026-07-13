package com.kwai.monitor.payload;

import java.util.logging.Logger;

/* JADX INFO: compiled from: ChannelReader.java */
/* JADX INFO: loaded from: classes3.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f495a = Logger.getLogger("ChannelReader");

    /* JADX WARN: Removed duplicated region for block: B:15:0x004d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:16:0x004e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String a(java.io.File r5) {
        /*
            r0 = 0
            int[] r1 = com.kwai.monitor.payload.a.f494a     // Catch: org.json.JSONException -> L37
            java.lang.String r5 = com.kwai.monitor.payload.d.a(r5, r1)     // Catch: org.json.JSONException -> L37
            if (r5 != 0) goto L13
            java.util.logging.Logger r5 = com.kwai.monitor.payload.b.f495a     // Catch: org.json.JSONException -> L37
            java.util.logging.Level r1 = java.util.logging.Level.WARNING     // Catch: org.json.JSONException -> L37
            java.lang.String r2 = "channel not found"
            r5.log(r1, r2)     // Catch: org.json.JSONException -> L37
            goto L4a
        L13:
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L37
            r1.<init>(r5)     // Catch: org.json.JSONException -> L37
            java.util.Iterator r5 = r1.keys()     // Catch: org.json.JSONException -> L37
            java.util.HashMap r2 = new java.util.HashMap     // Catch: org.json.JSONException -> L37
            r2.<init>()     // Catch: org.json.JSONException -> L37
        L21:
            boolean r3 = r5.hasNext()     // Catch: org.json.JSONException -> L37
            if (r3 == 0) goto L4b
            java.lang.Object r3 = r5.next()     // Catch: org.json.JSONException -> L37
            java.lang.String r3 = r3.toString()     // Catch: org.json.JSONException -> L37
            java.lang.String r4 = r1.getString(r3)     // Catch: org.json.JSONException -> L37
            r2.put(r3, r4)     // Catch: org.json.JSONException -> L37
            goto L21
        L37:
            r5 = move-exception
            java.util.logging.Logger r1 = com.kwai.monitor.payload.b.f495a
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r3 = "read channel failed"
            r1.log(r2, r3)
            java.util.logging.Level r2 = java.util.logging.Level.SEVERE
            java.lang.String r5 = r5.getMessage()
            r1.log(r2, r5)
        L4a:
            r2 = r0
        L4b:
            if (r2 != 0) goto L4e
            return r0
        L4e:
            java.lang.String r5 = "kwaiChannel"
            java.lang.Object r5 = r2.get(r5)
            java.lang.String r5 = (java.lang.String) r5
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.monitor.payload.b.a(java.io.File):java.lang.String");
    }
}
