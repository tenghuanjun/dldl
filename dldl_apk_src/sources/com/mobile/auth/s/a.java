package com.mobile.auth.s;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import com.igexin.assist.sdk.AssistPushConsts;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.n.h;
import com.mobile.auth.n.n;
import com.mobile.auth.n.q;
import com.unionpay.tsmservice.mini.data.Constant;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import layaair.game.conch.LayaConch5;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private static volatile a b;
    private com.mobile.auth.g.a a;
    private Context c;
    private com.mobile.auth.g.c d;
    private com.cmic.sso.sdk.a e;
    private String f = "";
    private Handler g;
    private String h;
    private String i;

    /* JADX INFO: renamed from: com.mobile.auth.s.a$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        final /* synthetic */ MonitorStruct a;

        AnonymousClass4(MonitorStruct monitorStruct) {
            this.a = monitorStruct;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.a(a.this).a(a.b(a.this).a(this.a), this.a.getUrgency());
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.s.a$a, reason: collision with other inner class name */
    private class RunnableC0083a implements Runnable {
        private com.cmic.sso.sdk.a b;
        private volatile boolean c = false;

        RunnableC0083a(com.cmic.sso.sdk.a aVar) {
            this.b = aVar;
        }

        private synchronized boolean a() {
            boolean z;
            try {
                z = this.c;
                this.c = true;
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return false;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return false;
                }
            }
            return !z;
        }

        static /* synthetic */ boolean a(RunnableC0083a runnableC0083a) {
            try {
                return runnableC0083a.a();
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return false;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                    return false;
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a()) {
                    JSONObject jSONObject = new JSONObject();
                    try {
                        jSONObject.put(Constant.KEY_RESULT_CODE, "200023");
                        jSONObject.put("resultString", "登录超时");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    a.a(a.this).a("200023", "登录超时", this.b, jSONObject);
                }
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
    }

    public a(Context context) {
        this.a = com.mobile.auth.g.a.a(context);
        this.c = context.getApplicationContext();
        this.g = new Handler(this.c.getMainLooper());
    }

    static /* synthetic */ com.mobile.auth.g.a a(a aVar) {
        try {
            return aVar.a;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static a a(Context context) {
        try {
            if (b == null) {
                synchronized (a.class) {
                    if (b == null) {
                        b = new a(context);
                    }
                }
            }
            return b;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ String a(a aVar, String str) {
        try {
            aVar.f = str;
            return str;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    private void a(com.mobile.auth.g.c cVar, com.cmic.sso.sdk.a aVar, com.mobile.auth.g.d dVar) {
        Method declaredMethod = null;
        try {
            try {
                try {
                    try {
                        declaredMethod = com.mobile.auth.g.c.class.getDeclaredMethod("a", com.cmic.sso.sdk.a.class, com.mobile.auth.g.d.class);
                        declaredMethod.setAccessible(true);
                        declaredMethod.invoke(cVar, aVar, dVar);
                    } catch (Throwable th) {
                        declaredMethod.setAccessible(false);
                        throw th;
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
            declaredMethod.setAccessible(false);
        } catch (Throwable th2) {
            try {
                ExceptionProcessor.processException(th2);
            } catch (Throwable th3) {
                ExceptionProcessor.processException(th3);
            }
        }
    }

    static /* synthetic */ void a(a aVar, String str, com.cmic.sso.sdk.a aVar2, c cVar) {
        try {
            aVar.a(str, aVar2, cVar);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    private void a(String str, com.cmic.sso.sdk.a aVar, final c cVar) {
        try {
            final RunnableC0083a runnableC0083a = new RunnableC0083a(aVar);
            this.g.postDelayed(runnableC0083a, b());
            aVar.b("authTypeInput", str);
            a(d(), aVar, new com.mobile.auth.g.d() { // from class: com.mobile.auth.s.a.3
                @Override // com.mobile.auth.g.d
                public void a(String str2, String str3, com.cmic.sso.sdk.a aVar2, JSONObject jSONObject) {
                    try {
                        if (RunnableC0083a.a(runnableC0083a)) {
                            a.f(a.this).removeCallbacks(runnableC0083a);
                            cVar.a().put("securityphone", aVar2.b("securityphone", ""));
                            if (1 != aVar2.c(LayaConch5.MARKET_LOGINTYPE) || !"显示登录取号成功".equals(str3) || com.mobile.auth.n.e.a(aVar2.b("traceId"))) {
                                a.a(a.this).a(str2, str3, aVar2, jSONObject);
                                return;
                            }
                            String strB = aVar2.b("traceId");
                            a.a(a.this, strB);
                            com.mobile.auth.n.e.a(strB, aVar2);
                            JSONObject jSONObject2 = new JSONObject();
                            try {
                                jSONObject2.put(Constant.KEY_RESULT_CODE, str2);
                                jSONObject2.put("authType", aVar2.b("authType", ""));
                                jSONObject2.put("authTypeDes", aVar2.b("authTypeDes", ""));
                                jSONObject2.put("openId", aVar2.b("openId", ""));
                                jSONObject2.put(AssistPushConsts.MSG_TYPE_TOKEN, aVar2.b(AssistPushConsts.MSG_TYPE_TOKEN, ""));
                                jSONObject2.put("traceId", aVar2.b("traceId", ""));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            com.mobile.auth.g.b bVarC = com.mobile.auth.n.e.c(strB);
                            if (bVarC != null) {
                                bVarC.a(aVar2.c("SDKRequestCode"), jSONObject2);
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
            });
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x005f A[Catch: all -> 0x006a, PHI: r2
  0x005f: PHI (r2v4 java.lang.reflect.Method) = (r2v2 java.lang.reflect.Method), (r2v3 java.lang.reflect.Method), (r2v5 java.lang.reflect.Method) binds: [B:15:0x005d, B:20:0x0067, B:12:0x0058] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x006a, blocks: (B:5:0x004c, B:26:0x006f, B:27:0x0072, B:16:0x005f, B:3:0x0002, B:10:0x0053, B:14:0x005a, B:19:0x0064), top: B:37:0x0002, inners: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(com.mobile.auth.g.a r13, com.cmic.sso.sdk.a r14, java.lang.String r15, java.lang.String r16, java.lang.String r17, int r18, com.mobile.auth.g.b r19) {
        /*
            r12 = this;
            r1 = 0
            r2 = 0
            java.lang.Class<com.mobile.auth.g.e> r0 = com.mobile.auth.g.e.class
            java.lang.String r3 = "a"
            r4 = 6
            java.lang.Class[] r5 = new java.lang.Class[r4]     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class<com.cmic.sso.sdk.a> r6 = com.cmic.sso.sdk.a.class
            r5[r1] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r7 = 1
            r5[r7] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r8 = 2
            r5[r8] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class<java.lang.String> r6 = java.lang.String.class
            r9 = 3
            r5[r9] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class r6 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r10 = 4
            r5[r10] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Class<com.mobile.auth.g.b> r6 = com.mobile.auth.g.b.class
            r11 = 5
            r5[r11] = r6     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.reflect.Method r2 = r0.getDeclaredMethod(r3, r5)     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r2.setAccessible(r7)     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Object[] r0 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r1] = r14     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r7] = r15     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r8] = r16     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r9] = r17     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Integer r3 = java.lang.Integer.valueOf(r18)     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r10] = r3     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r0[r11] = r19     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            r3 = r13
            java.lang.Object r0 = r2.invoke(r13, r0)     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            java.lang.Boolean r0 = (java.lang.Boolean) r0     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            boolean r0 = r0.booleanValue()     // Catch: java.lang.Throwable -> L50 java.lang.reflect.InvocationTargetException -> L52 java.lang.IllegalAccessException -> L59 java.lang.NoSuchMethodException -> L63
            if (r2 == 0) goto L4f
            r2.setAccessible(r1)     // Catch: java.lang.Throwable -> L6a
        L4f:
            return r0
        L50:
            r0 = move-exception
            goto L6d
        L52:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L6c
            goto L69
        L59:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L6c
        L5f:
            r2.setAccessible(r1)     // Catch: java.lang.Throwable -> L6a
            goto L6c
        L63:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L50
            if (r2 == 0) goto L6c
        L69:
            goto L5f
        L6a:
            r0 = move-exception
            goto L73
        L6c:
            return r1
        L6d:
            if (r2 == 0) goto L72
            r2.setAccessible(r1)     // Catch: java.lang.Throwable -> L6a
        L72:
            throw r0     // Catch: java.lang.Throwable -> L6a
        L73:
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r0)     // Catch: java.lang.Throwable -> L77
            return r1
        L77:
            r0 = move-exception
            r2 = r0
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r2)
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.s.a.a(com.mobile.auth.g.a, com.cmic.sso.sdk.a, java.lang.String, java.lang.String, java.lang.String, int, com.mobile.auth.g.b):boolean");
    }

    static /* synthetic */ boolean a(a aVar, com.mobile.auth.g.a aVar2, com.cmic.sso.sdk.a aVar3, String str, String str2, String str3, int i, com.mobile.auth.g.b bVar) {
        try {
            return aVar.a(aVar2, aVar3, str, str2, str3, i, bVar);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return false;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return false;
            }
        }
    }

    static /* synthetic */ com.cmic.sso.sdk.a b(a aVar) {
        try {
            return aVar.e;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ String c(a aVar) {
        try {
            return aVar.h;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x003c A[Catch: all -> 0x0043, TRY_ENTER, TryCatch #0 {all -> 0x0043, blocks: (B:3:0x0001, B:10:0x001e, B:30:0x003c, B:31:0x003f, B:32:0x0040), top: B:40:0x0001 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private com.mobile.auth.g.c d() {
        /*
            r4 = this;
            r0 = 0
            com.mobile.auth.g.c r1 = r4.d     // Catch: java.lang.Throwable -> L43
            if (r1 != 0) goto L40
            r1 = 0
            java.lang.Class<com.mobile.auth.g.e> r2 = com.mobile.auth.g.e.class
            java.lang.String r3 = "a"
            java.lang.reflect.Field r2 = r2.getDeclaredField(r3)     // Catch: java.lang.Throwable -> L26 java.lang.IllegalAccessException -> L29 java.lang.NoSuchFieldException -> L31
            r3 = 1
            r2.setAccessible(r3)     // Catch: java.lang.IllegalAccessException -> L22 java.lang.NoSuchFieldException -> L24 java.lang.Throwable -> L39
            com.mobile.auth.g.a r3 = r4.a     // Catch: java.lang.IllegalAccessException -> L22 java.lang.NoSuchFieldException -> L24 java.lang.Throwable -> L39
            java.lang.Object r3 = r2.get(r3)     // Catch: java.lang.IllegalAccessException -> L22 java.lang.NoSuchFieldException -> L24 java.lang.Throwable -> L39
            com.mobile.auth.g.c r3 = (com.mobile.auth.g.c) r3     // Catch: java.lang.IllegalAccessException -> L22 java.lang.NoSuchFieldException -> L24 java.lang.Throwable -> L39
            r4.d = r3     // Catch: java.lang.IllegalAccessException -> L22 java.lang.NoSuchFieldException -> L24 java.lang.Throwable -> L39
            if (r2 == 0) goto L40
        L1e:
            r2.setAccessible(r1)     // Catch: java.lang.Throwable -> L43
            goto L40
        L22:
            r3 = move-exception
            goto L2b
        L24:
            r3 = move-exception
            goto L33
        L26:
            r3 = move-exception
            r2 = r0
            goto L3a
        L29:
            r3 = move-exception
            r2 = r0
        L2b:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L39
            if (r2 == 0) goto L40
            goto L1e
        L31:
            r3 = move-exception
            r2 = r0
        L33:
            r3.printStackTrace()     // Catch: java.lang.Throwable -> L39
            if (r2 == 0) goto L40
            goto L1e
        L39:
            r3 = move-exception
        L3a:
            if (r2 == 0) goto L3f
            r2.setAccessible(r1)     // Catch: java.lang.Throwable -> L43
        L3f:
            throw r3     // Catch: java.lang.Throwable -> L43
        L40:
            com.mobile.auth.g.c r0 = r4.d     // Catch: java.lang.Throwable -> L43
            return r0
        L43:
            r1 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r1)     // Catch: java.lang.Throwable -> L48
            return r0
        L48:
            r1 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.s.a.d():com.mobile.auth.g.c");
    }

    static /* synthetic */ String d(a aVar) {
        try {
            return aVar.i;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ Context e(a aVar) {
        try {
            return aVar.c;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    static /* synthetic */ Handler f(a aVar) {
        try {
            return aVar.g;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public void a() {
        try {
            this.a.b();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(long j) {
        try {
            this.a.a(j);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(com.mobile.auth.g.b bVar) {
        try {
            final c cVar = new c(bVar);
            this.e = new com.cmic.sso.sdk.a(64);
            String strC = q.c();
            this.e.a(new com.cmic.sso.sdk.d.b());
            this.e.a("traceId", strC);
            com.mobile.auth.n.c.a("traceId", strC);
            com.mobile.auth.n.e.a(strC, cVar);
            this.e.a("SDKRequestCode", -1);
            n.a(new n.a(this.c, this.e) { // from class: com.mobile.auth.s.a.1
                @Override // com.mobile.auth.n.n.a
                protected void a() {
                    try {
                        if (a.a(a.this, a.a(a.this), a.b(a.this), a.c(a.this), a.d(a.this), "preGetMobile", 3, cVar)) {
                            a.a(a.this, String.valueOf(3), a.b(a.this), cVar);
                        }
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(String str) {
        try {
            this.h = str;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public long b() {
        try {
            return this.a.c();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return -1L;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return -1L;
            }
        }
    }

    public void b(com.mobile.auth.g.b bVar) {
        try {
            this.a.a(this.h, this.i, new c(bVar));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void b(String str) {
        try {
            this.i = str;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void c() {
        try {
            this.a.d();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void c(com.mobile.auth.g.b bVar) {
        try {
            final c cVar = new c(bVar);
            this.e = new com.cmic.sso.sdk.a(64);
            String strC = q.c();
            this.e.a(new com.cmic.sso.sdk.d.b());
            this.e.a("traceId", strC);
            com.mobile.auth.n.c.a("traceId", strC);
            com.mobile.auth.n.e.a(strC, cVar);
            this.e.a("SDKRequestCode", -1);
            n.a(new n.a(this.c, this.e) { // from class: com.mobile.auth.s.a.2
                @Override // com.mobile.auth.n.n.a
                protected void a() {
                    try {
                        if (a.a(a.this, a.a(a.this), a.b(a.this), a.c(a.this), a.d(a.this), "loginAuth", 1, cVar)) {
                            String strA = h.a(a.e(a.this));
                            if (!TextUtils.isEmpty(strA)) {
                                a.b(a.this).a("phonescrip", strA);
                            }
                            h.a(true, false);
                            a.a(a.this, String.valueOf(3), a.b(a.this), cVar);
                        }
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }
}
