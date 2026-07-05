package com.unicom.online.account.shield;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.unicom.online.account.kernel.ab;
import com.unicom.online.account.kernel.ae;
import com.unicom.online.account.kernel.c;
import com.unicom.online.account.kernel.d;
import com.unicom.online.account.kernel.e;
import com.unicom.online.account.kernel.i;
import com.unicom.online.account.kernel.k;
import com.unicom.online.account.kernel.m;
import com.unicom.online.account.kernel.n;
import com.unicom.online.account.kernel.w;
import com.unicom.online.account.kernel.x;
import com.unicom.online.account.kernel.y;
import com.unionpay.tsmservice.mini.data.Constant;
import java.net.URLDecoder;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class UniAccountHelper {
    private static final int ID_0_STOP_ONCE_SUCCESS = 0;
    private static final int ID_1_STOP_ALL_SEND = 1;
    private static final int LoopMaxNum = 5;
    public static final int SUCCESS = 100;
    private static volatile UniAccountHelper s_instance;
    private Context mContext = null;
    int loopNum = 0;

    public enum Language {
        SIMPLECHINESE(0),
        ENGLISH(1);

        private int value;

        Language(int i) {
            this.value = i;
        }

        public final int getValue() {
            return this.value;
        }
    }

    private UniAccountHelper() {
    }

    private void cuGetTokenLoop(int i, int i2, int i3, ResultListener resultListener) {
        int i4 = this.loopNum;
        if (i4 > 1) {
            return;
        }
        this.loopNum = i > 5 ? i4 + 5 : i > 1 ? i4 + i : i4 + 1;
        culoop(this.loopNum, i2, i3, resultListener);
    }

    private void cuPreGetToken(int i, final int i2, final String str, final ResultListener resultListener) {
        String str2;
        Context context = this.mContext;
        if (context == null) {
            initFail(resultListener, "sdk未初始化");
            return;
        }
        if (!n.d(context.getApplicationContext())) {
            initFail(resultListener, "数据网络未开启");
        } else if (getUseCacheFlag()) {
            n.a();
            String strA = n.a(this.mContext, "type".concat(String.valueOf(i2)), str);
            if (k.e(strA).booleanValue()) {
                try {
                    JSONObject jSONObject = new JSONObject(strA);
                    int i3 = jSONObject.getInt(Constant.KEY_RESULT_CODE);
                    long j = jSONObject.getJSONObject("resultData").getLong("exp");
                    if (i3 == 100 && j > System.currentTimeMillis()) {
                        resultListener.onResult(strA);
                        return;
                    }
                } catch (Exception unused) {
                }
            }
            n.a();
            n.e(this.mContext);
        }
        n.a();
        if (!n.a(this.mContext)) {
            str2 = "操作频繁,请稍后再试";
        } else if (!str.equals("cuPreGetToken")) {
            str2 = "sdk参数错误";
        } else {
            if (i2 == 2 || i2 == 3 || i2 == 4 || i2 == 5) {
                n nVarA = n.a();
                m mVar = new m() { // from class: com.unicom.online.account.shield.UniAccountHelper.2
                    @Override // com.unicom.online.account.kernel.m
                    public void onResult(String str3) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str3);
                            k.d(jSONObject2.optString("seq"));
                            if (jSONObject2.getInt(Constant.KEY_RESULT_CODE) == 100) {
                                JSONObject jSONObject3 = jSONObject2.getJSONObject("resultData");
                                k.b(jSONObject3.optString("fakeMobile"));
                                k.c(jSONObject3.optString("accessCode"));
                                k.b(jSONObject3.getLong("exp"));
                                k.a(System.currentTimeMillis());
                                String strOptString = jSONObject2.optString("operator");
                                if (!TextUtils.isEmpty(strOptString)) {
                                    k.a(strOptString);
                                }
                                if (4 == i2 || 2 == i2) {
                                    jSONObject3.put("fakeMobile", (Object) null);
                                }
                                if (UniAccountHelper.this.getUseCacheFlag()) {
                                    n.a();
                                    n.e(UniAccountHelper.this.mContext);
                                    n.a();
                                    n.a(UniAccountHelper.this.mContext, "type" + i2, str, jSONObject2.toString());
                                }
                                n.a();
                                n.b(UniAccountHelper.this.mContext);
                            } else {
                                n.a();
                                n.c(UniAccountHelper.this.mContext);
                            }
                            resultListener.onResult(jSONObject2.toString());
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                if (nVarA.a == null || TextUtils.isEmpty(d.c()) || TextUtils.isEmpty(d.d())) {
                    n.a(mVar, "sdk未初始化");
                    return;
                }
                e.b();
                e.e("cuPreGetToken");
                e.c();
                d.a(i);
                final w wVar = new w();
                final Context context2 = nVarA.a;
                wVar.b = new x();
                wVar.b.a = mVar;
                try {
                    wVar.a.schedule(new Runnable() { // from class: com.unicom.online.account.kernel.w.1
                        @Override // java.lang.Runnable
                        public final void run() {
                            synchronized (w.this) {
                                if (w.this.b != null) {
                                    w.this.b.a(410000, "请求超时");
                                    w.this.b = null;
                                    w.a(w.this);
                                }
                            }
                        }
                    }, i, TimeUnit.MILLISECONDS);
                    final y yVar = new y() { // from class: com.unicom.online.account.kernel.w.2
                        @Override // com.unicom.online.account.kernel.y
                        public final void a(int i4, String str3) {
                            synchronized (w.this) {
                                if (w.this.b == null) {
                                    return;
                                }
                                if (i4 == 1) {
                                    try {
                                        JSONObject jSONObject2 = new JSONObject(str3);
                                        int iOptInt = jSONObject2.optInt("code");
                                        String strOptString = jSONObject2.optString("msg");
                                        String strOptString2 = jSONObject2.optString(com.alipay.sdk.packet.e.k);
                                        String strOptString3 = jSONObject2.optString("seq");
                                        if (iOptInt == 100) {
                                            String strA2 = e.a();
                                            String strSubstring = strA2.substring(0, 16);
                                            String strSubstring2 = strA2.substring(16, 32);
                                            String str4 = ab.a ? new String(s.b(q.b(strOptString2), strSubstring.getBytes(), strSubstring2.getBytes())) : URLDecoder.decode(e.b(strOptString2, strSubstring, strSubstring2), "UTF-8");
                                            if (TextUtils.isEmpty(str4)) {
                                                c.a(2, "\nmsg=" + strOptString + "\ndata=" + strOptString2 + "\nseq=" + strOptString3 + "\n");
                                                w.this.b.a(410002, "数据异常", strOptString2, strOptString3);
                                            } else {
                                                c.a(2, "\nmsg=" + strOptString + "\ncontent=" + str4 + "\nseq=" + strOptString3 + "\n");
                                                x xVar = w.this.b;
                                                try {
                                                    if (xVar.a != null) {
                                                        JSONObject jSONObject3 = new JSONObject();
                                                        jSONObject3.put(Constant.KEY_RESULT_CODE, 100);
                                                        jSONObject3.put("resultMsg", strOptString);
                                                        jSONObject3.put("seq", strOptString3);
                                                        if (TextUtils.isEmpty(str4)) {
                                                            jSONObject3.put("resultData", "");
                                                        } else {
                                                            jSONObject3.put("resultData", new JSONObject(str4));
                                                        }
                                                        xVar.a.onResult(jSONObject3.toString());
                                                        xVar.a = null;
                                                    }
                                                } catch (Exception e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        } else {
                                            if (iOptInt == -2 && !TextUtils.isEmpty(d.f())) {
                                                strOptString = strOptString + "apn is " + d.f();
                                            }
                                            c.a(2, "\nmsg=" + strOptString + "\ndata=" + strOptString2 + "\nseq=" + strOptString3 + "\n");
                                            x xVar2 = w.this.b;
                                            StringBuilder sb = new StringBuilder("code:");
                                            sb.append(iOptInt);
                                            sb.append("msg:");
                                            sb.append(strOptString);
                                            xVar2.a(410002, sb.toString(), strOptString2, strOptString3);
                                        }
                                    } catch (Exception e2) {
                                        c.a(2, "\nresponse=" + str3 + "\n");
                                        w.this.b.a(410002, "异常" + e2.getMessage(), str3, "");
                                    }
                                } else {
                                    c.a(2, "\nresponse=" + str3 + "\n");
                                    w.this.b.a(410002, str3);
                                }
                                w.this.b = null;
                                w.a(w.this);
                            }
                        }
                    };
                    c.b("\n■★■★■★■★■★■★■★■★■★■\nrequestPreCheck()\n■★■★■★■★■★■★■★■★■★■\n");
                    try {
                        int iB = e.b(context2.getApplicationContext());
                        d.b(iB);
                        c.b("-1=NULL; 0=流量; 1=双开; 2=WIFI; networkType = ".concat(String.valueOf(iB)));
                        if (iB != 1) {
                            if (iB == 0) {
                                wVar.a(context2, i2, null, yVar);
                                return;
                            } else {
                                yVar.a(410004, "数据网络未开启");
                                return;
                            }
                        }
                        final long jCurrentTimeMillis = System.currentTimeMillis();
                        ae aeVarA = ae.a();
                        ae.a aVar = new ae.a() { // from class: com.unicom.online.account.kernel.w.3
                            @Override // com.unicom.online.account.kernel.ae.a
                            public final void a(boolean z, Object obj) {
                                if (!z) {
                                    yVar.a(410003, "无法切换至数据网络");
                                    return;
                                }
                                c.b("selectDataChannel:" + (System.currentTimeMillis() - jCurrentTimeMillis));
                                w.this.a(context2, i2, obj, yVar);
                            }
                        };
                        if (Build.VERSION.SDK_INT >= 21) {
                            aeVarA.a(context2, aVar);
                            return;
                        } else {
                            aVar.a(true, null);
                            return;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        yVar.a(410005, "网络判断异常" + e.getMessage());
                        return;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            str2 = "sdk type 参数错误";
        }
        initFail(resultListener, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void culoop(int i, final int i2, final int i3, final ResultListener resultListener) {
        if (this.loopNum == 0) {
            return;
        }
        cuPreGetToken(i3, ab.a ? 3 : 5, "cuPreGetToken", new ResultListener() { // from class: com.unicom.online.account.shield.UniAccountHelper.1
            /* JADX WARN: Removed duplicated region for block: B:26:0x0066 A[Catch: JSONException -> 0x0076, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0076, blocks: (B:2:0x0000, B:4:0x0008, B:9:0x0021, B:11:0x0039, B:13:0x003d, B:15:0x0047, B:16:0x0049, B:24:0x0060, B:26:0x0066, B:17:0x004d, B:19:0x0051, B:21:0x0057, B:23:0x005d, B:5:0x000e, B:7:0x0014, B:8:0x001d), top: B:31:0x0000 }] */
            /* JADX WARN: Removed duplicated region for block: B:32:? A[RETURN, SYNTHETIC] */
            @Override // com.unicom.online.account.shield.ResultListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onResult(java.lang.String r5) {
                /*
                    r4 = this;
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r0 = r0.loopNum     // Catch: org.json.JSONException -> L76
                    r1 = 5
                    r2 = 0
                    if (r0 <= r1) goto Le
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    r1 = 4
                    r0.loopNum = r1     // Catch: org.json.JSONException -> L76
                    goto L21
                Le:
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r0 = r0.loopNum     // Catch: org.json.JSONException -> L76
                    if (r0 <= 0) goto L1d
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r1 = r0.loopNum     // Catch: org.json.JSONException -> L76
                    int r1 = r1 + (-1)
                    r0.loopNum = r1     // Catch: org.json.JSONException -> L76
                    goto L21
                L1d:
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    r0.loopNum = r2     // Catch: org.json.JSONException -> L76
                L21:
                    org.json.JSONObject r0 = new org.json.JSONObject     // Catch: org.json.JSONException -> L76
                    r0.<init>(r5)     // Catch: org.json.JSONException -> L76
                    java.lang.String r1 = "seq"
                    java.lang.String r1 = r0.optString(r1)     // Catch: org.json.JSONException -> L76
                    com.unicom.online.account.kernel.k.d(r1)     // Catch: org.json.JSONException -> L76
                    java.lang.String r1 = "resultCode"
                    int r0 = r0.getInt(r1)     // Catch: org.json.JSONException -> L76
                    r1 = 100
                    if (r0 != r1) goto L4d
                    int r0 = r2     // Catch: org.json.JSONException -> L76
                    if (r0 != 0) goto L47
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    r0.loopNum = r2     // Catch: org.json.JSONException -> L76
                    com.unicom.online.account.shield.ResultListener r0 = r3     // Catch: org.json.JSONException -> L76
                    r0.onResult(r5)     // Catch: org.json.JSONException -> L76
                    return
                L47:
                    com.unicom.online.account.shield.ResultListener r0 = r3     // Catch: org.json.JSONException -> L76
                L49:
                    r0.onResult(r5)     // Catch: org.json.JSONException -> L76
                    goto L60
                L4d:
                    int r0 = r2     // Catch: org.json.JSONException -> L76
                    if (r0 != 0) goto L5d
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r0 = r0.loopNum     // Catch: org.json.JSONException -> L76
                    if (r0 != 0) goto L60
                    com.unicom.online.account.shield.ResultListener r0 = r3     // Catch: org.json.JSONException -> L76
                    r0.onResult(r5)     // Catch: org.json.JSONException -> L76
                    return
                L5d:
                    com.unicom.online.account.shield.ResultListener r0 = r3     // Catch: org.json.JSONException -> L76
                    goto L49
                L60:
                    com.unicom.online.account.shield.UniAccountHelper r5 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r5 = r5.loopNum     // Catch: org.json.JSONException -> L76
                    if (r5 <= 0) goto L75
                    com.unicom.online.account.shield.UniAccountHelper r5 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    com.unicom.online.account.shield.UniAccountHelper r0 = com.unicom.online.account.shield.UniAccountHelper.this     // Catch: org.json.JSONException -> L76
                    int r0 = r0.loopNum     // Catch: org.json.JSONException -> L76
                    int r1 = r2     // Catch: org.json.JSONException -> L76
                    int r2 = r4     // Catch: org.json.JSONException -> L76
                    com.unicom.online.account.shield.ResultListener r3 = r3     // Catch: org.json.JSONException -> L76
                    com.unicom.online.account.shield.UniAccountHelper.access$000(r5, r0, r1, r2, r3)     // Catch: org.json.JSONException -> L76
                L75:
                    return
                L76:
                    r5 = move-exception
                    r5.printStackTrace()
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.unicom.online.account.shield.UniAccountHelper.AnonymousClass1.onResult(java.lang.String):void");
            }
        });
    }

    public static String getCertFingerType() {
        return ab.d;
    }

    private String getHostName() {
        n.a();
        return n.e();
    }

    public static UniAccountHelper getInstance() {
        if (s_instance == null) {
            synchronized (UniAccountHelper.class) {
                if (s_instance == null) {
                    s_instance = new UniAccountHelper();
                }
            }
        }
        return s_instance;
    }

    private void initFail(ResultListener resultListener, String str) {
        i.a(str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constant.KEY_RESULT_CODE, 410021);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("seq", "");
            this.loopNum = 0;
            if (resultListener != null) {
                resultListener.onResult(jSONObject.toString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendFail(ResultListener resultListener, int i, String str) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(Constant.KEY_RESULT_CODE, i);
            jSONObject.put("resultMsg", str);
            jSONObject.put("resultData", "");
            jSONObject.put("seq", "");
            jSONObject.put("operatorType", k.a());
            resultListener.onResult(jSONObject.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public UniAccountHelper clearCache() {
        k.b();
        n.a();
        n.e(this.mContext);
        return s_instance;
    }

    public String cuDebugInfo(String str) {
        if (this.mContext == null) {
            return "sdk 未初始化, context 为空";
        }
        n nVarA = n.a();
        if (nVarA.a == null) {
            return "sdk 未初始化, context 为空";
        }
        switch (str.toLowerCase()) {
        }
        return "sdk 未初始化, context 为空";
    }

    public void cuGetToken(int i, ResultListener resultListener) {
        cuPreGetToken(i, ab.a ? 3 : 5, "cuPreGetToken", resultListener);
    }

    public void cuGetTokenLoop(int i, int i2, ResultListener resultListener) {
        cuGetTokenLoop(i, 0, i2, resultListener);
    }

    public void cuMobileAuth(int i, ResultListener resultListener) {
        cuPreGetToken(i, ab.a ? 2 : 4, "cuPreGetToken", resultListener);
    }

    public String getSdkVersion() {
        n.a();
        return n.b();
    }

    public boolean getUseCacheFlag() {
        return ab.c;
    }

    public UniAccountHelper init(Context context, String str) {
        return init(context, str, false);
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x00de A[Catch: Exception -> 0x00dc, TRY_LEAVE, TryCatch #0 {Exception -> 0x00dc, blocks: (B:15:0x0039, B:17:0x003f, B:20:0x0047, B:22:0x0051, B:23:0x0058, B:25:0x0063, B:26:0x0068, B:28:0x0089, B:29:0x00b3, B:32:0x00de), top: B:39:0x0039 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.unicom.online.account.shield.UniAccountHelper init(android.content.Context r6, java.lang.String r7, boolean r8) {
        /*
            Method dump skipped, instruction units count: 238
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unicom.online.account.shield.UniAccountHelper.init(android.content.Context, java.lang.String, boolean):com.unicom.online.account.shield.UniAccountHelper");
    }

    public UniAccountHelper initHostName(String str) {
        n.a();
        if (n.a(str)) {
            return s_instance;
        }
        i.a("初始化参数错误");
        return null;
    }

    public void releaseNetwork() {
        n.a();
        n.h();
    }

    public UniAccountHelper setCertFingerType(String str) {
        if (!str.equalsIgnoreCase("MD5") && !str.equalsIgnoreCase("SHA1") && !str.equalsIgnoreCase("SHA256") && !str.equalsIgnoreCase("sm3")) {
            return null;
        }
        ab.d = str.toLowerCase();
        return s_instance;
    }

    public UniAccountHelper setCryptoGM(boolean z) {
        ab.a = z;
        return s_instance;
    }

    public void setDefaultLanguage(Language language) {
        k.b = language;
    }

    public void setLogEnable(boolean z) {
        i.a(z);
        n.a();
        n.a(z);
    }

    public UniAccountHelper setUseCacheFlag(boolean z) {
        ab.c = z;
        return s_instance;
    }
}
