package com.mobile.auth.s;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.model.MonitorStruct;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d extends com.mobile.auth.gatewayauth.manager.a {
    private a i;

    public d(Context context, com.mobile.auth.gatewayauth.manager.d dVar) {
        super(context, dVar, Constant.VENDOR_CMCC, null);
        this.i = a.a(this.d);
    }

    static /* synthetic */ com.mobile.auth.q.a a(d dVar) {
        try {
            return dVar.h;
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

    static /* synthetic */ void a(d dVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            dVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void a(d dVar, String str, String str2, String str3, boolean z, String str4, MonitorStruct monitorStruct) {
        try {
            dVar.a(str, str2, str3, z, str4, monitorStruct);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ com.mobile.auth.q.a b(d dVar) {
        try {
            return dVar.h;
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

    static /* synthetic */ void b(d dVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            dVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void b(d dVar, String str, String str2, String str3, boolean z, String str4, MonitorStruct monitorStruct) {
        try {
            dVar.a(str, str2, str3, z, str4, monitorStruct);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ a c(d dVar) {
        try {
            return dVar.i;
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

    static /* synthetic */ void c(d dVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            dVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void c(d dVar, String str, String str2, String str3, boolean z, String str4, MonitorStruct monitorStruct) {
        try {
            dVar.a(str, str2, str3, z, str4, monitorStruct);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ com.mobile.auth.q.a d(d dVar) {
        try {
            return dVar.h;
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

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(long j) {
        try {
            super.a(j);
            this.i.a(this.c);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(String str, String str2) {
        try {
            super.a(str, str2);
            this.i.a(str);
            this.i.b(str2);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(boolean z) {
        try {
            com.mobile.auth.g.a.a(z);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    protected String b(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            try {
                Integer numValueOf = Integer.valueOf(Integer.parseInt(str));
                if (numValueOf.intValue() >= 10000) {
                    if (numValueOf.intValue() <= 40000) {
                        return str2;
                    }
                }
            } catch (Exception unused) {
            }
            switch (str) {
                case "102203":
                    return "600025";
                case "102101":
                case "102103":
                case "200027":
                case "200022":
                    return Constant.CODE_ERROR_NO_MOBILE_NETWORK_FAIL;
                case "102507":
                case "200023":
                case "200024":
                    return ResultCode.CODE_ERROR_FUNCTION_TIME_OUT;
                default:
                    return str2;
            }
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

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void c() {
        try {
            this.i.c();
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void d(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_CODE);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
        } finally {
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.i.a(new com.mobile.auth.g.b() { // from class: com.mobile.auth.s.d.1
                /* JADX WARN: Removed duplicated region for block: B:20:0x0073  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x0074 A[Catch: all -> 0x00cd, TryCatch #1 {all -> 0x00cd, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:9:0x0024, B:18:0x0041, B:22:0x0078, B:24:0x0088, B:25:0x00bb, B:21:0x0074, B:11:0x002a, B:13:0x0030, B:14:0x0035, B:16:0x003b), top: B:35:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:24:0x0088 A[Catch: all -> 0x00cd, TryCatch #1 {all -> 0x00cd, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:9:0x0024, B:18:0x0041, B:22:0x0078, B:24:0x0088, B:25:0x00bb, B:21:0x0074, B:11:0x002a, B:13:0x0030, B:14:0x0035, B:16:0x003b), top: B:35:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x00bb A[Catch: all -> 0x00cd, TRY_LEAVE, TryCatch #1 {all -> 0x00cd, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:9:0x0024, B:18:0x0041, B:22:0x0078, B:24:0x0088, B:25:0x00bb, B:21:0x0074, B:11:0x002a, B:13:0x0030, B:14:0x0035, B:16:0x003b), top: B:35:0x0006 }] */
                @Override // com.mobile.auth.g.b
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void a(int r12, org.json.JSONObject r13) {
                    /*
                        Method dump skipped, instruction units count: 215
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.s.d.AnonymousClass1.a(int, org.json.JSONObject):void");
                }
            });
            return;
        }
        a(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_MASK_FAIL);
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void e(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        try {
            final MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
            this.i.c(new com.mobile.auth.g.b() { // from class: com.mobile.auth.s.d.2
                /* JADX WARN: Removed duplicated region for block: B:20:0x0078  */
                /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[Catch: all -> 0x00de, TryCatch #1 {all -> 0x00de, blocks: (B:3:0x0008, B:5:0x0013, B:6:0x0018, B:9:0x0026, B:18:0x0043, B:22:0x007d, B:24:0x0088, B:25:0x00cc, B:21:0x0079, B:11:0x002c, B:13:0x0032, B:14:0x0037, B:16:0x003d), top: B:35:0x0008 }] */
                /* JADX WARN: Removed duplicated region for block: B:24:0x0088 A[Catch: all -> 0x00de, TryCatch #1 {all -> 0x00de, blocks: (B:3:0x0008, B:5:0x0013, B:6:0x0018, B:9:0x0026, B:18:0x0043, B:22:0x007d, B:24:0x0088, B:25:0x00cc, B:21:0x0079, B:11:0x002c, B:13:0x0032, B:14:0x0037, B:16:0x003d), top: B:35:0x0008 }] */
                /* JADX WARN: Removed duplicated region for block: B:25:0x00cc A[Catch: all -> 0x00de, TRY_LEAVE, TryCatch #1 {all -> 0x00de, blocks: (B:3:0x0008, B:5:0x0013, B:6:0x0018, B:9:0x0026, B:18:0x0043, B:22:0x007d, B:24:0x0088, B:25:0x00cc, B:21:0x0079, B:11:0x002c, B:13:0x0032, B:14:0x0037, B:16:0x003d), top: B:35:0x0008 }] */
                @Override // com.mobile.auth.g.b
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void a(int r13, org.json.JSONObject r14) {
                    /*
                        Method dump skipped, instruction units count: 232
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.s.d.AnonymousClass2.a(int, org.json.JSONObject):void");
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

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void f(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_AUTH_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
        } finally {
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.i.b(new com.mobile.auth.g.b() { // from class: com.mobile.auth.s.d.3
                /* JADX WARN: Removed duplicated region for block: B:19:0x0053 A[Catch: all -> 0x00ba, TryCatch #0 {all -> 0x00ba, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:17:0x0041, B:19:0x0053, B:20:0x00a8, B:10:0x002a, B:12:0x0030, B:13:0x0035, B:15:0x003b), top: B:28:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x00a8 A[Catch: all -> 0x00ba, TRY_LEAVE, TryCatch #0 {all -> 0x00ba, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:17:0x0041, B:19:0x0053, B:20:0x00a8, B:10:0x002a, B:12:0x0030, B:13:0x0035, B:15:0x003b), top: B:28:0x0006 }] */
                @Override // com.mobile.auth.g.b
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public void a(int r12, org.json.JSONObject r13) {
                    /*
                        r11 = this;
                        java.lang.String r12 = "resultString"
                        java.lang.String r0 = "desc"
                        java.lang.String r1 = "resultDesc"
                        com.mobile.auth.gatewayauth.model.MonitorStruct r2 = r2     // Catch: java.lang.Throwable -> Lba
                        long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lba
                        r2.setCarrierReturnTime(r3)     // Catch: java.lang.Throwable -> Lba
                        if (r13 != 0) goto L16
                        org.json.JSONObject r13 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lba
                        r13.<init>()     // Catch: java.lang.Throwable -> Lba
                    L16:
                        java.lang.String r2 = "resultCode"
                        java.lang.String r5 = r13.optString(r2)     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r2 = ""
                        boolean r3 = r13.has(r1)     // Catch: java.lang.Throwable -> Lba
                        if (r3 == 0) goto L2a
                        java.lang.String r12 = r13.optString(r1)     // Catch: java.lang.Throwable -> Lba
                    L28:
                        r6 = r12
                        goto L41
                    L2a:
                        boolean r1 = r13.has(r0)     // Catch: java.lang.Throwable -> Lba
                        if (r1 == 0) goto L35
                        java.lang.String r12 = r13.optString(r0)     // Catch: java.lang.Throwable -> Lba
                        goto L28
                    L35:
                        boolean r0 = r13.has(r12)     // Catch: java.lang.Throwable -> Lba
                        if (r0 == 0) goto L40
                        java.lang.String r12 = r13.optString(r12)     // Catch: java.lang.Throwable -> Lba
                        goto L28
                    L40:
                        r6 = r2
                    L41:
                        java.lang.String r12 = "traceId"
                        java.lang.String r12 = r13.optString(r12)     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r0 = "token"
                        java.lang.String r0 = r13.optString(r0)     // Catch: java.lang.Throwable -> Lba
                        boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lba
                        if (r1 != 0) goto La8
                        com.mobile.auth.s.d r1 = com.mobile.auth.s.d.this     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.q.a r1 = com.mobile.auth.s.d.d(r1)     // Catch: java.lang.Throwable -> Lba
                        r2 = 3
                        java.lang.String[] r2 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> Lba
                        r3 = 0
                        java.lang.String r4 = "cmcc："
                        r2[r3] = r4     // Catch: java.lang.Throwable -> Lba
                        r3 = 1
                        java.lang.String r4 = "getAccessCode:"
                        r2[r3] = r4     // Catch: java.lang.Throwable -> Lba
                        r3 = 2
                        java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> Lba
                        r2[r3] = r13     // Catch: java.lang.Throwable -> Lba
                        r1.a(r2)     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.model.MonitorStruct r13 = r2     // Catch: java.lang.Throwable -> Lba
                        r13.setCarrierTraceId(r12)     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.model.MonitorStruct r12 = r2     // Catch: java.lang.Throwable -> Lba
                        r12.setAccessCode(r0)     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.s.d r3 = com.mobile.auth.s.d.this     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r12 = ""
                        java.lang.String r6 = ""
                        r7 = 1
                        java.lang.String r8 = "cm_zyhl"
                        com.mobile.auth.gatewayauth.model.MonitorStruct r9 = r2     // Catch: java.lang.Throwable -> Lba
                        r4 = r5
                        r5 = r12
                        com.mobile.auth.s.d.c(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.manager.RequestCallback r12 = r3     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = com.mobile.auth.gatewayauth.manager.a.C0070a.a()     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = r13.b(r0)     // Catch: java.lang.Throwable -> Lba
                        long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lba
                        r2 = 120000(0x1d4c0, double:5.9288E-319)
                        long r0 = r0 + r2
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = r13.a(r0)     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.manager.a$a r13 = r13.a()     // Catch: java.lang.Throwable -> Lba
                        r12.onSuccess(r13)     // Catch: java.lang.Throwable -> Lba
                        goto Lc3
                    La8:
                        com.mobile.auth.s.d r3 = com.mobile.auth.s.d.this     // Catch: java.lang.Throwable -> Lba
                        com.mobile.auth.gatewayauth.manager.RequestCallback r4 = r3     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r7 = r13.toString()     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r8 = "cm_zyhl"
                        com.mobile.auth.gatewayauth.model.MonitorStruct r9 = r2     // Catch: java.lang.Throwable -> Lba
                        java.lang.String r10 = "600011"
                        com.mobile.auth.s.d.c(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lba
                        goto Lc3
                    Lba:
                        r12 = move-exception
                        com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r12)     // Catch: java.lang.Throwable -> Lbf
                        goto Lc3
                    Lbf:
                        r12 = move-exception
                        com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r12)
                    Lc3:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.s.d.AnonymousClass3.a(int, org.json.JSONObject):void");
                }
            });
            return;
        }
        a(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
    }
}
