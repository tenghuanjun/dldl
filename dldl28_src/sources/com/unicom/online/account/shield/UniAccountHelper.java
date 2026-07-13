package com.unicom.online.account.shield;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.z.a;
import com.mobile.auth.z.b;
import com.mobile.auth.z.d;
import com.mobile.auth.z.e;
import com.mobile.auth.z.k;
import com.mobile.auth.z.l;
import com.mobile.auth.z.m;
import com.mobile.auth.z.p;
import com.mobile.auth.z.q;
import com.mobile.auth.z.t;
import com.mobile.auth.z.u;
import com.mobile.auth.z.v;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes3.dex */
public class UniAccountHelper {
    public static final int CU_GET_TOKEN_IT = 2;
    private static final int SUCCESS = 100;
    private static volatile UniAccountHelper s_instance;
    private Context mContext = null;

    private UniAccountHelper() {
    }

    static /* synthetic */ boolean access$000(UniAccountHelper uniAccountHelper) {
        try {
            return uniAccountHelper.getUseCacheFlag();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    static /* synthetic */ Context access$100(UniAccountHelper uniAccountHelper) {
        try {
            return uniAccountHelper.mContext;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private void cuPreGetToken(int i, final int i2, final String str, final ResultListener resultListener) {
        try {
            Context context = this.mContext;
            if (context == null) {
                initFail(i2, resultListener, "sdk未初始化");
                return;
            }
            if (!e.d(context.getApplicationContext())) {
                initFail(i2, resultListener, "数据网络未开启");
            } else if (getUseCacheFlag()) {
                e.a();
                String strA = e.a(this.mContext, "type".concat(String.valueOf(i2)), str);
                if (b.e(strA).booleanValue()) {
                    try {
                        JSONObject jSONObject = new JSONObject(strA);
                        int i3 = jSONObject.getInt("resultCode");
                        jSONObject.getInt("type");
                        long j = jSONObject.getJSONObject("resultData").getLong("exp");
                        if (i3 == 100 && j > System.currentTimeMillis()) {
                            resultListener.onResult(strA);
                            return;
                        }
                    } catch (Exception unused) {
                    }
                }
                e.a();
                e.e(this.mContext);
            }
            e.a();
            if (!e.a(this.mContext)) {
                initFail(i2, resultListener, "操作频繁,请稍后再试");
                return;
            }
            if (!str.equals("cuPreGetToken")) {
                initFail(i2, resultListener, "sdk参数错误");
                return;
            }
            if (i2 != 2) {
                initFail(i2, resultListener, "sdk type 参数错误");
                return;
            }
            e eVarA = e.a();
            d dVar = new d() { // from class: com.unicom.online.account.shield.UniAccountHelper.1
                @Override // com.mobile.auth.z.d
                public void onResult(String str2) {
                    try {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str2);
                            b.d(jSONObject2.optString("seq"));
                            int i4 = jSONObject2.getInt("resultCode");
                            jSONObject2.getString("resultMsg");
                            if (i4 == 100) {
                                JSONObject jSONObject3 = jSONObject2.getJSONObject("resultData");
                                b.b(jSONObject3.optString("fakeMobile"));
                                b.c(jSONObject3.optString("accessCode"));
                                b.b(jSONObject3.getLong("exp"));
                                b.a(System.currentTimeMillis());
                                String strOptString = jSONObject2.optString("operator");
                                if (!TextUtils.isEmpty(strOptString)) {
                                    b.a(strOptString);
                                }
                                if (UniAccountHelper.access$000(UniAccountHelper.this)) {
                                    e.a();
                                    e.e(UniAccountHelper.access$100(UniAccountHelper.this));
                                    e.a();
                                    e.a(UniAccountHelper.access$100(UniAccountHelper.this), "type" + i2, str, jSONObject2.toString());
                                }
                                e.a();
                                e.b(UniAccountHelper.access$100(UniAccountHelper.this));
                            } else {
                                e.a();
                                e.c(UniAccountHelper.access$100(UniAccountHelper.this));
                            }
                            resultListener.onResult(jSONObject2.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } catch (Throwable th) {
                        ExceptionProcessor.processException(th);
                    }
                }
            };
            if (eVarA.f817a != null && !TextUtils.isEmpty(u.c()) && !TextUtils.isEmpty(u.d())) {
                v.b();
                v.c("cuPreGetToken");
                v.c();
                u.b(i);
                k kVar = new k();
                Context context2 = eVarA.f817a;
                kVar.b = new l();
                kVar.b.f826a = dVar;
                try {
                    kVar.f821a.schedule(new Runnable() { // from class: com.mobile.auth.z.k.1

                        /* JADX INFO: renamed from: a */
                        final /* synthetic */ int f822a;

                        public AnonymousClass1(final int i22) {
                            i = i22;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            try {
                                synchronized (k.this) {
                                    if (k.this.b != null) {
                                        k.this.b.a(i, 410000, "请求超时", "", "");
                                        k.this.b = null;
                                        k.a(k.this);
                                    }
                                }
                            } catch (Throwable th) {
                                ExceptionProcessor.processException(th);
                            }
                        }
                    }, i, TimeUnit.MILLISECONDS);
                    k.AnonymousClass2 anonymousClass2 = new m() { // from class: com.mobile.auth.z.k.2
                        public AnonymousClass2() {
                        }

                        @Override // com.mobile.auth.z.m
                        public final void a(int i4, int i5, String str2) {
                            String str3;
                            try {
                                synchronized (k.this) {
                                    if (k.this.b == null) {
                                        return;
                                    }
                                    if (i5 == 1) {
                                        try {
                                            JSONObject jSONObject2 = new JSONObject(str2);
                                            int iOptInt = jSONObject2.optInt("code");
                                            String strOptString = jSONObject2.optString("msg");
                                            String strOptString2 = jSONObject2.optString("data");
                                            String strOptString3 = jSONObject2.optString("seq");
                                            if (iOptInt == 100) {
                                                String strA2 = v.a();
                                                String strDecode = URLDecoder.decode(v.b(strOptString2, strA2.substring(0, 16), strA2.substring(16, 32)), "UTF-8");
                                                if (TextUtils.isEmpty(strDecode)) {
                                                    t.b("\nmsg=" + strOptString + "\ndata=" + strOptString2 + "\nseq=" + strOptString3 + StringUtils.LF);
                                                    k.this.b.a(i4, 410002, "数据异常", strOptString2, strOptString3);
                                                } else {
                                                    t.b("\nmsg=" + strOptString + "\ncontent=" + strDecode + "\nseq=" + strOptString3 + StringUtils.LF);
                                                    l lVar = k.this.b;
                                                    try {
                                                        if (lVar.f826a != null) {
                                                            JSONObject jSONObject3 = new JSONObject();
                                                            jSONObject3.put("resultCode", 100);
                                                            jSONObject3.put("resultMsg", strOptString);
                                                            jSONObject3.put("seq", strOptString3);
                                                            if (TextUtils.isEmpty(strDecode)) {
                                                                jSONObject3.put("resultData", "");
                                                            } else {
                                                                try {
                                                                    jSONObject3.put("resultData", new JSONObject(strDecode));
                                                                } catch (JSONException unused2) {
                                                                    jSONObject3.put("resultData", strDecode);
                                                                }
                                                            }
                                                            lVar.f826a.onResult(jSONObject3.toString());
                                                            lVar.f826a = null;
                                                        }
                                                    } catch (Exception e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            } else {
                                                if (iOptInt != -2 || TextUtils.isEmpty(u.f())) {
                                                    str3 = strOptString;
                                                } else {
                                                    str3 = strOptString + "apn is " + u.f();
                                                }
                                                t.b("\nmsg=" + str3 + "\ndata=" + strOptString2 + "\nseq=" + strOptString3 + StringUtils.LF);
                                                k.this.b.a(i4, iOptInt, str3, strOptString2, strOptString3);
                                            }
                                        } catch (Exception e2) {
                                            t.b("\nresponse=" + str2 + StringUtils.LF);
                                            k.this.b.a(i4, 410002, "异常" + e2.getMessage(), str2, "");
                                        }
                                    } else {
                                        t.b("\nresponse=" + str2 + StringUtils.LF);
                                        k.this.b.a(i4, i5, str2, "", "seqAndroidEmpty");
                                    }
                                    k.this.b = null;
                                    k.a(k.this);
                                }
                            } catch (Throwable th) {
                                ExceptionProcessor.processException(th);
                            }
                        }
                    };
                    t.c("\n■★■★■★■★■★■★■★■★■★■\nrequestPreCheck()\n■★■★■★■★■★■★■★■★■★■\n");
                    try {
                        int iB = v.b(context2.getApplicationContext());
                        u.c(iB);
                        t.c("-1=NULL; 0=流量; 1=双开; 2=WIFI; networkType = ".concat(String.valueOf(iB)));
                        if (iB == 1) {
                            q.a().a(context2, new q.a() { // from class: com.mobile.auth.z.k.3

                                /* JADX INFO: renamed from: a */
                                final /* synthetic */ long f824a;
                                final /* synthetic */ Context b;
                                final /* synthetic */ int c;
                                final /* synthetic */ m d;

                                public AnonymousClass3(long j2, Context context22, final int i22, m anonymousClass22) {
                                    j = j2;
                                    context = context22;
                                    i = i22;
                                    mVar = anonymousClass22;
                                }

                                @Override // com.mobile.auth.z.q.a
                                public final void a(boolean z, Object obj) {
                                    try {
                                        if (!z) {
                                            mVar.a(i, 410003, "无法切换至数据网络");
                                        } else {
                                            t.c("selectDataChannel:".concat(String.valueOf(System.currentTimeMillis() - j)));
                                            k.this.a(context, i, obj, mVar);
                                        }
                                    } catch (Throwable th) {
                                        ExceptionProcessor.processException(th);
                                    }
                                }
                            });
                            return;
                        } else if (iB == 0) {
                            kVar.a(context22, i22, null, anonymousClass22);
                            return;
                        } else {
                            anonymousClass22.a(i22, 410004, "数据网络未开启");
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        anonymousClass22.a(i22, 410005, "网络判断异常" + e.getMessage());
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            e.a(i22, dVar, "sdk未初始化");
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private String getHostName() {
        try {
            e.a();
            return e.d();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public static UniAccountHelper getInstance() {
        try {
            if (s_instance == null) {
                synchronized (UniAccountHelper.class) {
                    if (s_instance == null) {
                        s_instance = new UniAccountHelper();
                    }
                }
            }
            return s_instance;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    private boolean getUseCacheFlag() {
        try {
            return p.f830a;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return false;
        }
    }

    private void initFail(int i, ResultListener resultListener, String str) {
        try {
            a.a("type:" + i + "\nmsg:" + str);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("resultCode", 410021);
                jSONObject.put("resultMsg", str);
                jSONObject.put("resultData", "");
                jSONObject.put("seq", "");
                if (resultListener != null) {
                    resultListener.onResult(jSONObject.toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    private UniAccountHelper setUseCacheFlag(boolean z) {
        try {
            p.f830a = z;
            return s_instance;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public String cuDebugInfo(String str) {
        try {
            return this.mContext == null ? "sdk 未初始化, context 为空" : e.a().a(str);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void cuGetToken(int i, ResultListener resultListener) {
        try {
            cuPreGetToken(i, 2, "cuPreGetToken", resultListener);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public String getSdkVersion() {
        try {
            e.a();
            return e.b();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public UniAccountHelper init(Context context, String str) {
        try {
            return init(context, str, false);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x008d A[Catch: Exception -> 0x008b, all -> 0x0099, TRY_LEAVE, TryCatch #0 {Exception -> 0x008b, blocks: (B:15:0x0034, B:17:0x003a, B:20:0x0041, B:22:0x004b, B:23:0x0051, B:26:0x008d), top: B:38:0x0034, outer: #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.unicom.online.account.shield.UniAccountHelper init(android.content.Context r4, java.lang.String r5, boolean r6) {
        /*
            r3 = this;
            java.lang.String r6 = "初始化参数不能为空"
            r0 = 0
            if (r4 == 0) goto L9b
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L99
            if (r1 != 0) goto L9b
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Throwable -> L99
            if (r1 == 0) goto L13
            goto L9b
        L13:
            android.content.Context r1 = r3.mContext     // Catch: java.lang.Throwable -> L99
            if (r1 == 0) goto L1d
            java.lang.String r4 = "重复初始化"
            com.mobile.auth.z.a.a(r4)     // Catch: java.lang.Throwable -> L99
            return r0
        L1d:
            android.content.Context r1 = r4.getApplicationContext()     // Catch: java.lang.Throwable -> L99
            r3.mContext = r1     // Catch: java.lang.Throwable -> L99
            com.mobile.auth.z.e r1 = com.mobile.auth.z.e.a()     // Catch: java.lang.Throwable -> L99
            com.mobile.auth.z.v.b()     // Catch: java.lang.Throwable -> L99
            java.lang.String r2 = "cuPreGetToken"
            com.mobile.auth.z.v.c(r2)     // Catch: java.lang.Throwable -> L99
            com.mobile.auth.z.v.c()     // Catch: java.lang.Throwable -> L99
            if (r4 == 0) goto L8d
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            if (r2 != 0) goto L8d
            boolean r2 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            if (r2 == 0) goto L41
            goto L8d
        L41:
            java.lang.String r6 = com.mobile.auth.z.u.c()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            if (r6 != 0) goto L51
            java.lang.String r4 = "不可重复初始化"
            com.mobile.auth.z.t.e(r4)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            goto L94
        L51:
            r6 = 0
            com.mobile.auth.z.p.f830a = r6     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            android.content.Context r4 = r4.getApplicationContext()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            r1.f817a = r4     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.u.b(r5)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.u.c(r5)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            android.content.Context r4 = r1.f817a     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.String r4 = com.mobile.auth.z.v.c(r4)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.u.f(r4)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.u.e()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.String r6 = "backupIp="
            r4.<init>(r6)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.String r6 = com.mobile.auth.z.u.f835a     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            r4.append(r6)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.String r4 = r4.toString()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.t.c(r4)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            java.lang.String r4 = "ali.wosms.cn"
            com.mobile.auth.z.e.b(r4)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.e.f()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            com.mobile.auth.z.e.c()     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            goto L94
        L8b:
            r4 = move-exception
            goto L91
        L8d:
            com.mobile.auth.z.t.e(r6)     // Catch: java.lang.Exception -> L8b java.lang.Throwable -> L99
            goto L94
        L91:
            r4.printStackTrace()     // Catch: java.lang.Throwable -> L99
        L94:
            com.mobile.auth.z.b.f816a = r5     // Catch: java.lang.Throwable -> L99
            com.unicom.online.account.shield.UniAccountHelper r4 = com.unicom.online.account.shield.UniAccountHelper.s_instance     // Catch: java.lang.Throwable -> L99
            return r4
        L99:
            r4 = move-exception
            goto L9f
        L9b:
            com.mobile.auth.z.a.a(r6)     // Catch: java.lang.Throwable -> L99
            return r0
        L9f:
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r4)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.online.account.shield.UniAccountHelper.init(android.content.Context, java.lang.String, boolean):com.unicom.online.account.shield.UniAccountHelper");
    }

    public UniAccountHelper initHostName(String str) {
        try {
            e.a();
            if (e.b(str)) {
                return s_instance;
            }
            a.a("初始化参数错误");
            return null;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void releaseNetwork() {
        try {
            e.a();
            e.g();
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }

    public UniAccountHelper setCertFingerType(String str) {
        try {
            if (!str.equalsIgnoreCase("MD5") && !str.equalsIgnoreCase("SHA1") && !str.equalsIgnoreCase("SHA256") && !str.equalsIgnoreCase("sm3")) {
                return null;
            }
            p.b = str.toLowerCase();
            return s_instance;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public void setLogEnable(boolean z) {
        try {
            a.a(z);
            e.a();
            e.a(z);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
        }
    }
}
