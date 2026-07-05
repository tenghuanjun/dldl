package com.unicom.online.account.kernel;

import android.content.Context;
import android.text.TextUtils;
import com.alicom.tools.networking.RSA;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import javax.crypto.Cipher;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class w {
    public x b;
    private ExecutorService c = Executors.newSingleThreadExecutor();
    public ScheduledExecutorService a = Executors.newScheduledThreadPool(1);

    static String a(Context context) {
        String str;
        String strA;
        String strA2;
        String string = "";
        try {
            String strC = d.c();
            StringBuilder sb = new StringBuilder();
            sb.append(System.currentTimeMillis());
            String string2 = sb.toString();
            String strA3 = ab.a();
            String packageName = context.getPackageName();
            String strG = e.g();
            String strA4 = e.a();
            String strSubstring = strA4.substring(0, 16);
            String strSubstring2 = strA4.substring(16, 32);
            if (ab.a) {
                str = "3.1";
                strA = q.a(s.a(strG.getBytes("Utf-8"), strSubstring.getBytes(), strSubstring2.getBytes()));
                strA2 = q.a(s.a(strA4.getBytes(), r.a("045C5DD4890819CEB16B0A66ED62B2FFA29B08F3CBF344A52A3A100ECB271BBEF3A9BC3743E753CA16EF238A1E55B72E95659A70425064D506B48F8EE3442786F7")));
            } else {
                str = "2.1";
                strA = e.a(strG, strSubstring, strSubstring2);
                PublicKey publicKeyGeneratePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(q.b("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVc1ecjpc5k7TkabF935iQONDZ0/E5XWPVv9FEsI59XTRW0+BCMK1MODRSWMvHFrPMh9ZilnRr7qXuAKCBEynQEghmpIVvMYhFu48FAI9bKfkI5lKuQK+tc4X0+zTbNrpedNoKXK4C7dDjTETBH6prwWE9j5WsAf0gbjUbIs3FxwIDAQAB")));
                Cipher cipher = Cipher.getInstance(RSA.RSA_ALGORITHM);
                cipher.init(1, publicKeyGeneratePublic);
                strA2 = q.a(cipher.doFinal(strA4.getBytes()));
            }
            String strA5 = ab.d.equalsIgnoreCase("sm3") ? e.a(context, context.getPackageName()) : e.a(context, context.getPackageName(), ab.d);
            String strB = d.b();
            if (!TextUtils.isEmpty(strB)) {
                strB = "0";
            }
            String str2 = strA5 + "\n" + strC + "\n" + str + "\njson\n" + strB + "\n" + packageName + "\n" + strA + "\n" + strA3 + "\n" + strA2 + "\n" + string2;
            String strReplaceAll = str2.replaceAll("\n", "");
            String strC2 = ab.a ? e.c(strReplaceAll) : e.a(strReplaceAll);
            c.a("unSignDebugInfo=".concat(String.valueOf(str2)));
            String strA6 = q.a(strA);
            String strA7 = q.a(strA2);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("androidMd5", strA5);
            jSONObject.put("apiKey", strC);
            jSONObject.put(com.alipay.sdk.cons.c.m, str);
            jSONObject.put("format", "json");
            jSONObject.put("operator", strB);
            jSONObject.put("packName", packageName);
            jSONObject.put("privateIp", strA6);
            jSONObject.put("sdkVersion", strA3);
            jSONObject.put("secretKey", strA7);
            jSONObject.put("timeStamp", string2);
            jSONObject.put("sign", strC2);
            string = jSONObject.toString();
            c.b("getPreCheckParam_CU_Oath: param ok  \n");
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    static /* synthetic */ void a(w wVar) {
        try {
            if (wVar.a != null) {
                wVar.a.shutdownNow();
                wVar.a = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void a(final Context context, final int i, final Object obj, final y yVar) {
        synchronized (this) {
            try {
                this.c.submit(new Runnable() { // from class: com.unicom.online.account.kernel.w.4
                    /* JADX WARN: Removed duplicated region for block: B:21:0x008b A[Catch: Exception -> 0x009c, TryCatch #0 {Exception -> 0x009c, blocks: (B:2:0x0000, B:10:0x0014, B:13:0x0040, B:19:0x0085, B:21:0x008b, B:23:0x0096, B:18:0x0082, B:12:0x0021, B:11:0x001f, B:15:0x0074), top: B:28:0x0000, inners: #1 }] */
                    /* JADX WARN: Removed duplicated region for block: B:23:0x0096 A[Catch: Exception -> 0x009c, TRY_LEAVE, TryCatch #0 {Exception -> 0x009c, blocks: (B:2:0x0000, B:10:0x0014, B:13:0x0040, B:19:0x0085, B:21:0x008b, B:23:0x0096, B:18:0x0082, B:12:0x0021, B:11:0x001f, B:15:0x0074), top: B:28:0x0000, inners: #1 }] */
                    /* JADX WARN: Removed duplicated region for block: B:29:0x0074 A[EXC_TOP_SPLITTER, SYNTHETIC] */
                    @Override // java.lang.Runnable
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                        To view partially-correct add '--show-bad-code' argument
                    */
                    public final void run() {
                        /*
                            r7 = this;
                            java.lang.String r0 = ""
                            r1 = 0
                            com.unicom.online.account.kernel.ab.a = r1     // Catch: java.lang.Exception -> L9c
                            int r1 = r2     // Catch: java.lang.Exception -> L9c
                            r2 = 2
                            r3 = 1
                            if (r1 == r2) goto L1f
                            r2 = 3
                            if (r1 == r2) goto L1f
                            r2 = 4
                            if (r1 == r2) goto L21
                            r2 = 5
                            if (r1 == r2) goto L21
                            com.unicom.online.account.kernel.y r1 = r4     // Catch: java.lang.Exception -> L9c
                            r2 = 410009(0x64199, float:5.74545E-40)
                            java.lang.String r4 = "410009no this type"
                            r1.a(r2, r4)     // Catch: java.lang.Exception -> L9c
                            goto L40
                        L1f:
                            com.unicom.online.account.kernel.ab.a = r3     // Catch: java.lang.Exception -> L9c
                        L21:
                            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L9c
                            r0.<init>()     // Catch: java.lang.Exception -> L9c
                            java.lang.String r1 = com.unicom.online.account.kernel.d.a()     // Catch: java.lang.Exception -> L9c
                            r0.append(r1)     // Catch: java.lang.Exception -> L9c
                            android.content.Context r1 = r3     // Catch: java.lang.Exception -> L9c
                            java.lang.String r1 = com.unicom.online.account.kernel.w.a(r1)     // Catch: java.lang.Exception -> L9c
                            java.lang.String r2 = "&"
                            java.lang.String r1 = com.unicom.online.account.kernel.ag.a(r1, r2)     // Catch: java.lang.Exception -> L9c
                            r0.append(r1)     // Catch: java.lang.Exception -> L9c
                            java.lang.String r0 = r0.toString()     // Catch: java.lang.Exception -> L9c
                        L40:
                            com.unicom.online.account.kernel.z r1 = new com.unicom.online.account.kernel.z     // Catch: java.lang.Exception -> L9c
                            r1.<init>()     // Catch: java.lang.Exception -> L9c
                            android.content.Context r2 = r3     // Catch: java.lang.Exception -> L9c
                            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Exception -> L9c
                            r4.<init>()     // Catch: java.lang.Exception -> L9c
                            java.lang.String r5 = "Mozilla/5.0 (Linux; U; Android %s) AppleWebKit/533.1 (KHTML, like Gecko) Version/4.0 %sSafari/533.1"
                            java.lang.String r6 = "user-agent"
                            r4.put(r6, r5)     // Catch: java.lang.Exception -> L9c
                            java.lang.String r5 = "netType"
                            java.lang.String r6 = "2"
                            r4.put(r5, r6)     // Catch: java.lang.Exception -> L9c
                            java.lang.String r5 = "os"
                            java.lang.String r6 = "android"
                            r4.put(r5, r6)     // Catch: java.lang.Exception -> L9c
                            java.lang.String r5 = "Accept"
                        */
                        //  java.lang.String r6 = "*/*"
                        /*
                            r4.put(r5, r6)     // Catch: java.lang.Exception -> L9c
                            java.lang.Object r5 = r5     // Catch: java.lang.Exception -> L9c
                            java.lang.String r0 = r1.a(r2, r0, r4, r5)     // Catch: java.lang.Exception -> L9c
                            int r1 = com.unicom.online.account.kernel.d.h()     // Catch: java.lang.Exception -> L9c
                            if (r1 != r3) goto L85
                            com.unicom.online.account.kernel.ae r1 = com.unicom.online.account.kernel.ae.a()     // Catch: java.lang.Exception -> L81
                            r1.b()     // Catch: java.lang.Exception -> L81
                            java.lang.String r1 = "\n  WIFI + 流量 \n call releaseNetwork() \n"
                            com.unicom.online.account.kernel.c.b(r1)     // Catch: java.lang.Exception -> L81
                            goto L85
                        L81:
                            r1 = move-exception
                            r1.printStackTrace()     // Catch: java.lang.Exception -> L9c
                        L85:
                            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L9c
                            if (r1 == 0) goto L96
                            com.unicom.online.account.kernel.y r0 = r4     // Catch: java.lang.Exception -> L9c
                            r1 = 410022(0x641a6, float:5.74563E-40)
                            java.lang.String r2 = "网络请求响应为空"
                            r0.a(r1, r2)     // Catch: java.lang.Exception -> L9c
                            return
                        L96:
                            com.unicom.online.account.kernel.y r1 = r4     // Catch: java.lang.Exception -> L9c
                            r1.a(r3, r0)     // Catch: java.lang.Exception -> L9c
                            return
                        L9c:
                            r0 = move-exception
                            r0.printStackTrace()
                            return
                        */
                        throw new UnsupportedOperationException("Method not decompiled: com.unicom.online.account.kernel.w.AnonymousClass4.run():void");
                    }
                });
            } catch (Exception e) {
                yVar.a(410009, "410009" + e.getMessage());
            }
        }
    }
}
