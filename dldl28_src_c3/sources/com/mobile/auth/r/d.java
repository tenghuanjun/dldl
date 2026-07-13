package com.mobile.auth.r;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.tencent.open.SocialConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class d extends com.mobile.auth.gatewayauth.manager.a {
    private a i;

    public d(Context context, com.mobile.auth.gatewayauth.manager.d dVar) {
        super(context, dVar, Constant.VENDOR_CMCC, null);
        this.i = a.a(this.d);
    }

    static /* synthetic */ com.mobile.auth.p.a a(d dVar) {
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
            dVar.a((RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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

    static /* synthetic */ com.mobile.auth.p.a b(d dVar) {
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
            dVar.a((RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            dVar.a((RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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

    static /* synthetic */ com.mobile.auth.p.a d(d dVar) {
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
            com.mobile.auth.f.a.a(z);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:38:0x0077  */
    @Override // com.mobile.auth.gatewayauth.manager.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String b(java.lang.String r4, java.lang.String r5) {
        /*
            r3 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r4)     // Catch: java.lang.Throwable -> L85
            if (r0 == 0) goto L7
            return r4
        L7:
            int r0 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L85
            java.lang.Integer r1 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L85
            r1.getClass()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L85
            r2 = 10000(0x2710, float:1.4013E-41)
            if (r0 < r2) goto L1f
            r1.getClass()     // Catch: java.lang.Exception -> L1f java.lang.Throwable -> L85
            r1 = 40000(0x9c40, float:5.6052E-41)
            if (r0 > r1) goto L1f
            return r5
        L1f:
            int r0 = r4.hashCode()     // Catch: java.lang.Throwable -> L85
            switch(r0) {
                case 1448695583: goto L6d;
                case 1448695585: goto L63;
                case 1448696546: goto L59;
                case 1448699433: goto L4f;
                case 1477264254: goto L45;
                case 1477264255: goto L3b;
                case 1477264256: goto L31;
                case 1477264259: goto L27;
                default: goto L26;
            }     // Catch: java.lang.Throwable -> L85
        L26:
            goto L77
        L27:
            java.lang.String r0 = "200027"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 3
            goto L78
        L31:
            java.lang.String r0 = "200024"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 7
            goto L78
        L3b:
            java.lang.String r0 = "200023"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 6
            goto L78
        L45:
            java.lang.String r0 = "200022"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 4
            goto L78
        L4f:
            java.lang.String r0 = "102507"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 5
            goto L78
        L59:
            java.lang.String r0 = "102203"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 0
            goto L78
        L63:
            java.lang.String r0 = "102103"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 2
            goto L78
        L6d:
            java.lang.String r0 = "102101"
            boolean r4 = r4.equals(r0)     // Catch: java.lang.Throwable -> L85
            if (r4 == 0) goto L77
            r4 = 1
            goto L78
        L77:
            r4 = -1
        L78:
            switch(r4) {
                case 0: goto L82;
                case 1: goto L7f;
                case 2: goto L7f;
                case 3: goto L7f;
                case 4: goto L7f;
                case 5: goto L7c;
                case 6: goto L7c;
                case 7: goto L7c;
                default: goto L7b;
            }     // Catch: java.lang.Throwable -> L85
        L7b:
            return r5
        L7c:
            java.lang.String r4 = "600015"
            return r4
        L7f:
            java.lang.String r4 = "-10006"
            return r4
        L82:
            java.lang.String r4 = "600025"
            return r4
        L85:
            r4 = move-exception
            r5 = 0
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r4)     // Catch: java.lang.Throwable -> L8b
            return r5
        L8b:
            r4 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.r.d.b(java.lang.String, java.lang.String):java.lang.String");
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
    public synchronized void d(final RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_CODE);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.i.a(new com.mobile.auth.f.b() { // from class: com.mobile.auth.r.d.1
                @Override // com.mobile.auth.f.b
                public void a(int i, JSONObject jSONObject) {
                    try {
                        monitorStruct.setCarrierReturnTime(System.currentTimeMillis());
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        String strOptString = jSONObject.optString("resultCode");
                        String string = "";
                        String strOptString2 = jSONObject.has("resultDesc") ? jSONObject.optString("resultDesc") : jSONObject.has(SocialConstants.PARAM_APP_DESC) ? jSONObject.optString(SocialConstants.PARAM_APP_DESC) : jSONObject.has("resultString") ? jSONObject.optString("resultString") : "";
                        String strOptString3 = jSONObject.optString("traceId");
                        String strOptString4 = jSONObject.optString("securityphone");
                        com.mobile.auth.p.a aVarA = d.a(d.this);
                        if (jSONObject != null) {
                            string = jSONObject.toString();
                        }
                        aVarA.a("cmcc：", "getLoginInfo:code=", strOptString, ",msg=", strOptString2, ",json=", string);
                        monitorStruct.setCarrierTraceId(strOptString3);
                        if (TextUtils.isEmpty(strOptString4)) {
                            d.a(d.this, requestCallback, strOptString, strOptString2, jSONObject.toString(), Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_MASK_FAIL);
                            return;
                        }
                        requestCallback.onSuccess(a.C0182a.a().a(strOptString4).c(Constant.CMCC_PROTOCOL).d(Constant.CMCC_PROTOCOL_URL).a());
                        monitorStruct.setPhoneNumber(strOptString4);
                        d.a(d.this, strOptString, "", "", true, Constant.VENDOR_CMCC, monitorStruct);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            });
            return;
        }
        a(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_MASK_FAIL);
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void e(final RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        try {
            final MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
            this.i.c(new com.mobile.auth.f.b() { // from class: com.mobile.auth.r.d.2
                @Override // com.mobile.auth.f.b
                public void a(int i, JSONObject jSONObject) {
                    try {
                        monitorStruct.setCarrierReturnTime(System.currentTimeMillis());
                        if (jSONObject == null) {
                            jSONObject = new JSONObject();
                        }
                        String strOptString = jSONObject.optString("resultCode");
                        String string = "";
                        String strOptString2 = jSONObject.has("resultDesc") ? jSONObject.optString("resultDesc") : jSONObject.has(SocialConstants.PARAM_APP_DESC) ? jSONObject.optString(SocialConstants.PARAM_APP_DESC) : jSONObject.has("resultString") ? jSONObject.optString("resultString") : "";
                        String strOptString3 = jSONObject.optString("traceId");
                        String strOptString4 = jSONObject.optString("token");
                        monitorStruct.setCarrierTraceId(strOptString3);
                        com.mobile.auth.p.a aVarB = d.b(d.this);
                        if (jSONObject != null) {
                            string = jSONObject.toString();
                        }
                        aVarB.a("cmcc：", "getLoginToken:code=", strOptString, ",msg=", strOptString2, ",json=", string);
                        if (TextUtils.isEmpty(strOptString4)) {
                            d.b(d.this, requestCallback, strOptString, strOptString2, jSONObject.toString(), Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
                            return;
                        }
                        monitorStruct.setAccessCode(jSONObject.optString("token"));
                        d.c(d.this).a();
                        d.b(d.this, strOptString, "", "", true, Constant.VENDOR_CMCC, monitorStruct);
                        requestCallback.onSuccess(a.C0182a.a().b(jSONObject.optString("token")).a(System.currentTimeMillis() + 120000).a());
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            });
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void f(final RequestCallback<a.C0182a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_AUTH_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.i.b(new com.mobile.auth.f.b() { // from class: com.mobile.auth.r.d.3
                /* JADX WARN: Removed duplicated region for block: B:19:0x0053 A[Catch: all -> 0x00b2, TryCatch #0 {all -> 0x00b2, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:17:0x0041, B:19:0x0053, B:20:0x00a0, B:10:0x002a, B:12:0x0030, B:13:0x0035, B:15:0x003b), top: B:28:0x0006 }] */
                /* JADX WARN: Removed duplicated region for block: B:20:0x00a0 A[Catch: all -> 0x00b2, TRY_LEAVE, TryCatch #0 {all -> 0x00b2, blocks: (B:3:0x0006, B:5:0x0011, B:6:0x0016, B:8:0x0024, B:17:0x0041, B:19:0x0053, B:20:0x00a0, B:10:0x002a, B:12:0x0030, B:13:0x0035, B:15:0x003b), top: B:28:0x0006 }] */
                @Override // com.mobile.auth.f.b
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
                        com.mobile.auth.gatewayauth.model.MonitorStruct r2 = r2     // Catch: java.lang.Throwable -> Lb2
                        long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb2
                        r2.setCarrierReturnTime(r3)     // Catch: java.lang.Throwable -> Lb2
                        if (r13 != 0) goto L16
                        org.json.JSONObject r13 = new org.json.JSONObject     // Catch: java.lang.Throwable -> Lb2
                        r13.<init>()     // Catch: java.lang.Throwable -> Lb2
                    L16:
                        java.lang.String r2 = "resultCode"
                        java.lang.String r5 = r13.optString(r2)     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r2 = ""
                        boolean r3 = r13.has(r1)     // Catch: java.lang.Throwable -> Lb2
                        if (r3 == 0) goto L2a
                        java.lang.String r12 = r13.optString(r1)     // Catch: java.lang.Throwable -> Lb2
                    L28:
                        r6 = r12
                        goto L41
                    L2a:
                        boolean r1 = r13.has(r0)     // Catch: java.lang.Throwable -> Lb2
                        if (r1 == 0) goto L35
                        java.lang.String r12 = r13.optString(r0)     // Catch: java.lang.Throwable -> Lb2
                        goto L28
                    L35:
                        boolean r0 = r13.has(r12)     // Catch: java.lang.Throwable -> Lb2
                        if (r0 == 0) goto L40
                        java.lang.String r12 = r13.optString(r12)     // Catch: java.lang.Throwable -> Lb2
                        goto L28
                    L40:
                        r6 = r2
                    L41:
                        java.lang.String r12 = "traceId"
                        java.lang.String r12 = r13.optString(r12)     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r0 = "token"
                        java.lang.String r0 = r13.optString(r0)     // Catch: java.lang.Throwable -> Lb2
                        boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Throwable -> Lb2
                        if (r1 != 0) goto La0
                        com.mobile.auth.r.d r1 = com.mobile.auth.r.d.this     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.p.a r1 = com.mobile.auth.r.d.d(r1)     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r2 = "cmcc："
                        java.lang.String r3 = "getAccessCode:"
                        java.lang.String r13 = r13.toString()     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String[] r13 = new java.lang.String[]{r2, r3, r13}     // Catch: java.lang.Throwable -> Lb2
                        r1.a(r13)     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.model.MonitorStruct r13 = r2     // Catch: java.lang.Throwable -> Lb2
                        r13.setCarrierTraceId(r12)     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.model.MonitorStruct r12 = r2     // Catch: java.lang.Throwable -> Lb2
                        r12.setAccessCode(r0)     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.r.d r3 = com.mobile.auth.r.d.this     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r12 = ""
                        java.lang.String r6 = ""
                        java.lang.String r8 = "cm_zyhl"
                        com.mobile.auth.gatewayauth.model.MonitorStruct r9 = r2     // Catch: java.lang.Throwable -> Lb2
                        r7 = 1
                        r4 = r5
                        r5 = r12
                        com.mobile.auth.r.d.c(r3, r4, r5, r6, r7, r8, r9)     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.manager.RequestCallback r12 = r3     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = com.mobile.auth.gatewayauth.manager.a.C0182a.a()     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = r13.b(r0)     // Catch: java.lang.Throwable -> Lb2
                        long r0 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> Lb2
                        r2 = 120000(0x1d4c0, double:5.9288E-319)
                        long r0 = r0 + r2
                        com.mobile.auth.gatewayauth.manager.a$a$a r13 = r13.a(r0)     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.manager.a$a r13 = r13.a()     // Catch: java.lang.Throwable -> Lb2
                        r12.onSuccess(r13)     // Catch: java.lang.Throwable -> Lb2
                        goto Lbb
                    La0:
                        com.mobile.auth.r.d r3 = com.mobile.auth.r.d.this     // Catch: java.lang.Throwable -> Lb2
                        com.mobile.auth.gatewayauth.manager.RequestCallback r4 = r3     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r7 = r13.toString()     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r8 = "cm_zyhl"
                        com.mobile.auth.gatewayauth.model.MonitorStruct r9 = r2     // Catch: java.lang.Throwable -> Lb2
                        java.lang.String r10 = "600011"
                        com.mobile.auth.r.d.c(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> Lb2
                        goto Lbb
                    Lb2:
                        r12 = move-exception
                        com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r12)     // Catch: java.lang.Throwable -> Lb7
                        goto Lbb
                    Lb7:
                        r12 = move-exception
                        com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r12)
                    Lbb:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.r.d.AnonymousClass3.a(int, org.json.JSONObject):void");
                }
            });
            return;
        }
        a(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", Constant.VENDOR_CMCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
    }
}
