package com.mobile.auth.a;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.c.e;
import com.mobile.auth.d.f;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class a {
    public static c a;
    private static final String e = a.class.getSimpleName();
    public static int b = 0;
    public static int c = 0;
    public static int d = 0;

    /* JADX INFO: renamed from: com.mobile.auth.a.a$1, reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ b a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;
        final /* synthetic */ Context d;

        AnonymousClass1(b bVar, String str, String str2, Context context) {
            this.a = bVar;
            this.b = str;
            this.c = str2;
            this.d = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(this.b);
                        jSONObject.put("reqId", this.c);
                        this.a.a(jSONObject.toString());
                        a.a(a.a(), "callback result : " + jSONObject.toString());
                    } catch (Exception unused) {
                        this.a.a(this.b);
                        a.a(a.a(), "Exception callback result : " + this.b);
                    }
                    a.b = null;
                    e.a(this.d, this.c);
                }
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    public static void a(int i, int i2, int i3, c cVar) {
        try {
            c = i;
            d = i2;
            b = i3;
            a = cVar;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public static void a(Context context, String str, String str2, b bVar) {
        try {
            a(e, "called requestPreAuthCode()   appId：" + str + ",appSecret:" + str2);
            a(context, str, str2, "qhx", bVar);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private static void a(Context context, String str, String str2, String str3, b bVar) {
        try {
            if (bVar == null) {
                a = null;
                return;
            }
            if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                if (!f.b(context)) {
                    bVar.a("{\"result\":80003,\"msg\":\"网络无连接\"}");
                    a = null;
                    return;
                } else if (f.c(context)) {
                    new com.mobile.auth.d.a().a(context, str, str2, str3, bVar);
                    return;
                } else if (f.d(context)) {
                    new com.mobile.auth.d.a().b(context, str, str2, str3, bVar);
                    return;
                } else {
                    bVar.a("{\"result\":80004,\"msg\":\"移动网络未开启\"}");
                    a = null;
                    return;
                }
            }
            bVar.a("{\"result\":80106,\"msg\":\"请求参数异常\"}");
            a = null;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public static void a(String str, String str2) {
        try {
            if (a != null) {
                try {
                    a.a("CT_" + str, str2);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public static void a(String str, String str2, Throwable th) {
        try {
            if (a != null) {
                try {
                    a.a("CT_" + str, str2, th);
                } catch (Throwable unused) {
                }
            }
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
            }
        }
    }

    public static void b(Context context, String str, String str2, b bVar) {
        if (bVar != null) {
            try {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    jSONObject.put("reqId", str2);
                    bVar.a(jSONObject.toString());
                    a(e, "callback result : " + jSONObject.toString());
                } catch (Exception unused) {
                    bVar.a(str);
                    a(e, "Exception callback result : " + str);
                }
                a = null;
                e.a(context, str2);
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
    }
}
