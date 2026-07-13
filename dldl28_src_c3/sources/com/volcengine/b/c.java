package com.volcengine.b;

import android.content.Context;
import android.text.TextUtils;
import android.util.Pair;
import com.mobile.auth.gatewayauth.Constant;
import com.volcengine.b.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class c extends com.volcengine.b.a {
    private final ExecutorService j;
    private CountDownLatch k;

    private static class a implements Runnable {
        private final String a;
        private final String b;
        private final Map<String, String> c;
        private final CountDownLatch d;

        public a(String str, String str2, Map<String, String> map, CountDownLatch countDownLatch) {
            this.b = str;
            this.a = str2;
            this.c = map;
            this.d = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            this.c.put(this.a, c.b(this.b) ? "0" : "1");
            this.d.countDown();
        }
    }

    private static class b {
        private String a;

        public b(String str) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            this.a = str;
            Matcher matcher = Pattern.compile("(?<=\\().*?(?=\\))").matcher(str);
            if (matcher.find()) {
                this.a = matcher.group();
            }
        }

        public String a() {
            return this.a;
        }
    }

    public c(Context context, Map<String, String> map, Map<String, String> map2, ExecutorService executorService, a.b bVar) {
        super(context, map, map2, bVar, 10);
        this.j = executorService;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:41:0x008c A[Catch: Exception -> 0x0088, TRY_LEAVE, TryCatch #4 {Exception -> 0x0088, blocks: (B:37:0x0084, B:41:0x008c), top: B:46:0x0084 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0084 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r5v0 */
    /* JADX WARN: Type inference failed for: r6v0, types: [com.volcengine.b.c$b] */
    /* JADX WARN: Type inference failed for: r6v1 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Exception] */
    /* JADX WARN: Type inference failed for: r6v11, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v12 */
    /* JADX WARN: Type inference failed for: r6v15, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v3 */
    /* JADX WARN: Type inference failed for: r6v4 */
    /* JADX WARN: Type inference failed for: r6v5, types: [java.lang.Process] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9 */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r7v6, types: [java.io.BufferedReader] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.String a(com.volcengine.b.c.b r6, boolean r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "1 "
            if (r7 == 0) goto L7
            java.lang.String r7 = "ping -s 8185 -c  "
            goto L9
        L7:
            java.lang.String r7 = "ping -c "
        L9:
            java.lang.String r1 = ""
            r2 = 0
            java.lang.Runtime r3 = java.lang.Runtime.getRuntime()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            r4.<init>()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            r4.append(r7)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            r4.append(r0)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            java.lang.String r6 = r6.a()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            r4.append(r6)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            java.lang.String r6 = r4.toString()     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            java.lang.Process r6 = r3.exec(r6)     // Catch: java.lang.Throwable -> L67 java.lang.Exception -> L6b
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L7b
            java.io.InputStreamReader r0 = new java.io.InputStreamReader     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L7b
            java.io.InputStream r3 = r6.getInputStream()     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L7b
            r0.<init>(r3)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L7b
            r7.<init>(r0)     // Catch: java.lang.Exception -> L65 java.lang.Throwable -> L7b
        L38:
            java.lang.String r0 = r7.readLine()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            if (r0 == 0) goto L4e
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r2.<init>()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r2.append(r1)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r2.append(r0)     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            java.lang.String r1 = r2.toString()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            goto L38
        L4e:
            r7.close()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r6.waitFor()     // Catch: java.lang.Throwable -> L60 java.lang.Exception -> L62
            r7.close()     // Catch: java.lang.Exception -> L5b
            r6.destroy()     // Catch: java.lang.Exception -> L5b
            goto L7a
        L5b:
            r6 = move-exception
            r6.printStackTrace()
            goto L7a
        L60:
            r0 = move-exception
            goto L82
        L62:
            r0 = move-exception
            r2 = r7
            goto L6d
        L65:
            r0 = move-exception
            goto L6d
        L67:
            r6 = move-exception
            r0 = r6
            r6 = r2
            goto L80
        L6b:
            r0 = move-exception
            r6 = r2
        L6d:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L7b
            if (r2 == 0) goto L75
            r2.close()     // Catch: java.lang.Exception -> L5b
        L75:
            if (r6 == 0) goto L7a
            r6.destroy()     // Catch: java.lang.Exception -> L5b
        L7a:
            return r1
        L7b:
            r7 = move-exception
            r0 = r7
            r5 = r2
            r2 = r6
            r6 = r5
        L80:
            r7 = r6
            r6 = r2
        L82:
            if (r7 == 0) goto L8a
            r7.close()     // Catch: java.lang.Exception -> L88
            goto L8a
        L88:
            r6 = move-exception
            goto L90
        L8a:
            if (r6 == 0) goto L93
            r6.destroy()     // Catch: java.lang.Exception -> L88
            goto L93
        L90:
            r6.printStackTrace()
        L93:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.b.c.a(com.volcengine.b.c$b, boolean):java.lang.String");
    }

    private void a(List<Pair<String, String>> list, Map<String, String> map) throws InterruptedException {
        if (list.size() > 0) {
            this.k = new CountDownLatch(list.size());
            for (int i = 0; i < list.size(); i++) {
                Pair<String, String> pair = list.get(i);
                this.j.execute(new a((String) pair.first, (String) pair.second, map, this.k));
            }
            this.k.await(9L, TimeUnit.SECONDS);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(java.util.List<android.util.Pair<java.lang.String, java.lang.String>> r6, java.util.Map<java.lang.String, java.lang.String> r7) {
        /*
            Method dump skipped, instruction units count: 268
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.volcengine.b.c.b(java.util.List, java.util.Map):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean b(String str) throws Throwable {
        b bVar = new b(str);
        StringBuilder sb = new StringBuilder(256);
        String strA = a(bVar, false);
        if (!Pattern.compile("(?<=from ).*(?=: icmp_seq=1 ttl=)").matcher(strA).find()) {
            return false;
        }
        sb.append("\t");
        sb.append(strA);
        String lowerCase = sb.toString().toLowerCase();
        return (lowerCase.contains(Constant.API_PARAMS_KEY_TIMEOUT) || lowerCase.contains("timed out") || lowerCase.contains(Constant.VENDOR_UNKNOWN)) ? false : true;
    }

    @Override // com.volcengine.b.a
    public void a() {
        super.a();
        if (this.k != null) {
            while (this.k.getCount() > 0) {
                this.k.countDown();
            }
        }
    }

    @Override // com.volcengine.b.a
    protected void a(Map<String, String> map) throws InterruptedException {
        ArrayList arrayList = new ArrayList();
        b(arrayList, map);
        a(arrayList, map);
    }

    @Override // com.volcengine.b.a
    protected String c() {
        return "PingTest";
    }
}
