package com.bytedance.http.a;

import com.bytedance.http.HttpDispatcher;
import com.bytedance.http.HttpExtra;
import com.bytedance.http.HttpResponse;
import com.bytedance.http.Interceptor;

/* JADX INFO: loaded from: classes2.dex */
public class f implements Interceptor {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f386a;
    private final int b;
    private final int c;
    private volatile boolean d;
    private int e;

    public f(HttpDispatcher httpDispatcher) {
        this.f386a = Math.max(0, httpDispatcher.retryCount());
        int iMax = Math.max(0, httpDispatcher.retryInterval());
        this.b = iMax;
        this.c = Math.max(0, httpDispatcher.retryMode());
        this.e = iMax;
    }

    private static boolean a(HttpResponse httpResponse) {
        try {
            if (httpResponse.extras().containsKey(HttpExtra.HTTP_EXTRA_IS_WEBSOCKET) && httpResponse.extras().containsKey(HttpExtra.HTTP_EXTRA_WEBSOCKET_CONNECTED)) {
                return Boolean.parseBoolean((String) httpResponse.extras().get(HttpExtra.HTTP_EXTRA_WEBSOCKET_CONNECTED));
            }
            return false;
        } catch (Exception e) {
            com.bytedance.http.b.c.c(e.getMessage());
            return false;
        }
    }

    public final void a() {
        this.d = true;
    }

    public final boolean b() {
        return this.d;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(12:(3:147|12|13)|(3:139|15|16)(2:21|(2:30|(3:152|32|(4:159|37|165|163)(4:162|36|166|163))(4:161|38|(7:40|41|137|42|43|131|44)(1:50)|51))(6:160|25|135|26|27|16))|52|(1:54)(1:55)|145|56|(2:58|(1:155)(9:63|149|64|(4:(7:141|67|(1:69)(1:70)|71|72|73|157)(1:156)|(1:111)(1:112)|(1:114)|115)(18:133|76|(1:78)|(1:80)|81|(3:83|143|84)(1:85)|86|87|127|88|(1:90)|91|(4:93|94|129|95)(1:96)|97|98|124|164|163)|118|123|124|164|163))(1:153)|109|(0)(0)|(0)|115|10) */
    /* JADX WARN: Code restructure failed: missing block: B:116:0x02f7, code lost:
    
        r0 = e;
     */
    /* JADX WARN: Code restructure failed: missing block: B:117:0x02f8, code lost:
    
        r3 = r27;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:111:0x02d7  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02e6  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x02e9  */
    /* JADX WARN: Type inference failed for: r0v49, types: [com.bytedance.http.HttpResponse$Builder] */
    /* JADX WARN: Type inference failed for: r20v0 */
    /* JADX WARN: Type inference failed for: r20v1 */
    /* JADX WARN: Type inference failed for: r20v10 */
    /* JADX WARN: Type inference failed for: r20v11 */
    /* JADX WARN: Type inference failed for: r20v12 */
    /* JADX WARN: Type inference failed for: r20v13 */
    /* JADX WARN: Type inference failed for: r20v14 */
    /* JADX WARN: Type inference failed for: r20v2 */
    /* JADX WARN: Type inference failed for: r20v3 */
    /* JADX WARN: Type inference failed for: r20v4 */
    /* JADX WARN: Type inference failed for: r20v5 */
    /* JADX WARN: Type inference failed for: r20v6 */
    /* JADX WARN: Type inference failed for: r20v7 */
    /* JADX WARN: Type inference failed for: r20v8 */
    /* JADX WARN: Type inference failed for: r20v9 */
    /* JADX WARN: Type inference failed for: r3v0, types: [com.bytedance.http.HttpRequest] */
    /* JADX WARN: Type inference failed for: r3v1, types: [com.bytedance.http.HttpRequest] */
    /* JADX WARN: Type inference failed for: r3v23 */
    /* JADX WARN: Type inference failed for: r3v24 */
    /* JADX WARN: Type inference failed for: r3v4 */
    /* JADX WARN: Type inference failed for: r3v6 */
    /* JADX WARN: Type inference failed for: r4v14 */
    /* JADX WARN: Type inference failed for: r4v22 */
    /* JADX WARN: Type inference failed for: r4v27, types: [com.bytedance.http.HttpRequest] */
    /* JADX WARN: Unreachable blocks removed: 2, instructions: 2 */
    @Override // com.bytedance.http.Interceptor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.bytedance.http.HttpResponse intercept(com.bytedance.http.Interceptor.Chain r28) {
        /*
            Method dump skipped, instruction units count: 815
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.http.a.f.intercept(com.bytedance.http.Interceptor$Chain):com.bytedance.http.HttpResponse");
    }

    @Override // com.bytedance.http.Interceptor
    public String name() {
        return "retry_int";
    }
}
