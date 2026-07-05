package com.mobile.auth.q;

import android.content.Context;
import android.text.TextUtils;
import com.igexin.assist.sdk.AssistPushConsts;
import com.mobile.auth.e.e;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.gatewayauth.utils.b;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class c extends com.mobile.auth.gatewayauth.manager.a {
    private a d;
    private com.mobile.auth.gatewayauth.manager.d e;
    private com.mobile.auth.o.a f;
    private Context g;

    public c(Context context, com.mobile.auth.gatewayauth.manager.d dVar) {
        this.g = context.getApplicationContext();
        this.d = a.a(this.g);
        this.e = dVar;
        this.f = dVar.a();
    }

    static /* synthetic */ com.mobile.auth.o.a a(c cVar) {
        try {
            return cVar.f;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private void a(RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            a(str, str2, str3, false, monitorStruct);
            requestCallback.onError(com.mobile.auth.gatewayauth.manager.base.c.a().d(com.mobile.auth.gatewayauth.utils.a.a(str, str2)).a(b(str, ResultCode.CODE_GET_MASK_FAIL)).b(str2).a());
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ void a(c cVar, RequestCallback requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            cVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c>) requestCallback, str, str2, str3, monitorStruct);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ void a(c cVar, String str, String str2, String str3, boolean z, MonitorStruct monitorStruct) {
        try {
            cVar.a(str, str2, str3, z, monitorStruct);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    private void a(String str, String str2, String str3, boolean z, final MonitorStruct monitorStruct) {
        if (monitorStruct != null) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                monitorStruct.setCarrierSdkCode(str);
                monitorStruct.setSuccess(z);
                monitorStruct.setEndTime(jCurrentTimeMillis);
                if (!z) {
                    monitorStruct.setCarrierSdkMsg(str2);
                    monitorStruct.setFailRet(com.mobile.auth.gatewayauth.utils.a.a(str, str2));
                    monitorStruct.setCarrierFailedResultData(str3);
                }
                monitorStruct.setUrgency(1);
                monitorStruct.setVendorKey(Constant.VENDOR_CMCC);
                com.mobile.auth.gatewayauth.utils.b.a(new Runnable() { // from class: com.mobile.auth.q.c.4
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            c.a(c.this).a(c.d(c.this).a(monitorStruct), monitorStruct.getUrgency());
                        } catch (Throwable th) {
                            try {
                                com.mobile.auth.gatewayauth.a.a(th);
                            } catch (Throwable th2) {
                                com.mobile.auth.gatewayauth.a.a(th2);
                            }
                        }
                    }
                });
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    static /* synthetic */ long b(c cVar) {
        try {
            return cVar.c;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return -1L;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return -1L;
            }
        }
    }

    private String b(String str, String str2) {
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
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private void b(RequestCallback<a.c, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            a(str, str2, str3, false, monitorStruct);
            requestCallback.onError(com.mobile.auth.gatewayauth.manager.base.c.a().d(com.mobile.auth.gatewayauth.utils.a.a(str, str2)).a(b(str, ResultCode.CODE_GET_TOKEN_FAIL)).b(str2).c(str3).a());
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ void b(c cVar, RequestCallback requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            cVar.c(requestCallback, str, str2, str3, monitorStruct);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ a c(c cVar) {
        try {
            return cVar.d;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    private void c(RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            a(str, str2, str3, false, monitorStruct);
            requestCallback.onError(com.mobile.auth.gatewayauth.manager.base.c.a().d(com.mobile.auth.gatewayauth.utils.a.a(str, str2)).a(b(str, ResultCode.CODE_GET_TOKEN_FAIL)).b(str2).c(str3).a());
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ void c(c cVar, RequestCallback requestCallback, String str, String str2, String str3, MonitorStruct monitorStruct) {
        try {
            cVar.b(requestCallback, str, str2, str3, monitorStruct);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    static /* synthetic */ com.mobile.auth.gatewayauth.manager.d d(c cVar) {
        try {
            return cVar.e;
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
                return null;
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
                return null;
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(long j) {
        try {
            super.a(j);
            this.d.a(this.c);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(String str, String str2) {
        try {
            super.a(str, str2);
            this.d.a(str);
            this.d.b(str2);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void a(boolean z) {
        try {
            com.mobile.auth.e.a.a(z);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void c() {
        try {
            this.d.b();
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void d(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_CODE);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.d.a(new e() { // from class: com.mobile.auth.q.c.1
                @Override // com.mobile.auth.e.e
                public void a(int i, JSONObject jSONObject) {
                    if (jSONObject == null) {
                        try {
                            jSONObject = new JSONObject();
                        } catch (Throwable th3) {
                            try {
                                com.mobile.auth.gatewayauth.a.a(th3);
                                return;
                            } catch (Throwable th4) {
                                com.mobile.auth.gatewayauth.a.a(th4);
                                return;
                            }
                        }
                    }
                    String strOptString = jSONObject.optString(com.unionpay.tsmservice.mini.data.Constant.KEY_RESULT_CODE);
                    String strOptString2 = jSONObject.optString("resultDes");
                    String strOptString3 = jSONObject.optString("traceId");
                    String strOptString4 = jSONObject.optString("securityphone");
                    com.mobile.auth.o.a aVarA = c.a(c.this);
                    String[] strArr = new String[7];
                    strArr[0] = "cmcc：";
                    strArr[1] = "getLoginInfo:code=";
                    strArr[2] = strOptString;
                    strArr[3] = ",msg=";
                    strArr[4] = strOptString2;
                    strArr[5] = ",json=";
                    strArr[6] = jSONObject == null ? "" : jSONObject.toString();
                    aVarA.a(strArr);
                    monitorStruct.setCarrierTraceId(strOptString3);
                    if (TextUtils.isEmpty(strOptString4)) {
                        c.a(c.this, requestCallback, strOptString, strOptString2, jSONObject.toString(), monitorStruct);
                        return;
                    }
                    requestCallback.onSuccess(a.C0070a.a().a(strOptString4).a());
                    monitorStruct.setPhoneNumber(strOptString4);
                    c.a(c.this, strOptString, "", "", true, monitorStruct);
                }
            }, -1);
            return;
        }
        a(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", monitorStruct);
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void e(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, final a.b bVar) {
        try {
            com.mobile.auth.gatewayauth.utils.b.a(new b.AbstractRunnableC0075b() { // from class: com.mobile.auth.q.c.2
                @Override // com.mobile.auth.gatewayauth.utils.b.AbstractRunnableC0075b
                protected void a() {
                    try {
                        final MonitorStruct monitorStruct = new MonitorStruct();
                        monitorStruct.putApiParam("timeout", String.valueOf(c.b(c.this)));
                        monitorStruct.setSessionId(bVar.c());
                        monitorStruct.setRequestId(bVar.b());
                        monitorStruct.setStartTime(System.currentTimeMillis());
                        monitorStruct.setAction(Constant.ACTION_CMCC_LOGIN_TOKEN);
                        c.c(c.this).a(new e() { // from class: com.mobile.auth.q.c.2.1
                            @Override // com.mobile.auth.e.e
                            public void a(int i, JSONObject jSONObject) {
                                if (jSONObject == null) {
                                    try {
                                        jSONObject = new JSONObject();
                                    } catch (Throwable th) {
                                        try {
                                            com.mobile.auth.gatewayauth.a.a(th);
                                            return;
                                        } catch (Throwable th2) {
                                            com.mobile.auth.gatewayauth.a.a(th2);
                                            return;
                                        }
                                    }
                                }
                                String strOptString = jSONObject.optString(com.unionpay.tsmservice.mini.data.Constant.KEY_RESULT_CODE);
                                String strOptString2 = jSONObject.optString("resultDes");
                                String strOptString3 = jSONObject.optString("traceId");
                                String strOptString4 = jSONObject.optString(AssistPushConsts.MSG_TYPE_TOKEN);
                                monitorStruct.setCarrierTraceId(strOptString3);
                                com.mobile.auth.o.a aVarA = c.a(c.this);
                                String[] strArr = new String[7];
                                strArr[0] = "cmcc：";
                                strArr[1] = "getLoginToken:code=";
                                strArr[2] = strOptString;
                                strArr[3] = ",msg=";
                                strArr[4] = strOptString2;
                                strArr[5] = ",json=";
                                strArr[6] = jSONObject == null ? "" : jSONObject.toString();
                                aVarA.a(strArr);
                                if (TextUtils.isEmpty(strOptString4)) {
                                    c.b(c.this, requestCallback, strOptString, strOptString2, jSONObject.toString(), monitorStruct);
                                    return;
                                }
                                monitorStruct.setAccessCode(jSONObject.optString(AssistPushConsts.MSG_TYPE_TOKEN));
                                c.a(c.this, strOptString, "", "", true, monitorStruct);
                                requestCallback.onSuccess(a.C0070a.a().b(jSONObject.optString(AssistPushConsts.MSG_TYPE_TOKEN)).a(System.currentTimeMillis() + com.igexin.push.config.c.l).a());
                            }
                        });
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void f(final RequestCallback<a.c, com.mobile.auth.gatewayauth.manager.base.c> requestCallback, a.b bVar) {
        final MonitorStruct monitorStruct;
        try {
            monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CMCC_AUTH_TOKEN);
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
        if (!TextUtils.isEmpty(this.a) && !TextUtils.isEmpty(this.b)) {
            this.d.b(new e() { // from class: com.mobile.auth.q.c.3
                @Override // com.mobile.auth.e.e
                public void a(int i, JSONObject jSONObject) {
                    if (jSONObject == null) {
                        try {
                            jSONObject = new JSONObject();
                        } catch (Throwable th3) {
                            try {
                                com.mobile.auth.gatewayauth.a.a(th3);
                                return;
                            } catch (Throwable th4) {
                                com.mobile.auth.gatewayauth.a.a(th4);
                                return;
                            }
                        }
                    }
                    String strOptString = jSONObject.optString(com.unionpay.tsmservice.mini.data.Constant.KEY_RESULT_CODE);
                    String strOptString2 = jSONObject.optString("resultDes");
                    String strOptString3 = jSONObject.optString("traceId");
                    String strOptString4 = jSONObject.optString(AssistPushConsts.MSG_TYPE_TOKEN);
                    if (TextUtils.isEmpty(strOptString4)) {
                        c.c(c.this, requestCallback, strOptString, strOptString2, jSONObject.toString(), monitorStruct);
                        return;
                    }
                    c.a(c.this).a(new String[]{"cmcc：", "getAccessCode:", jSONObject.toString()});
                    monitorStruct.setCarrierTraceId(strOptString3);
                    monitorStruct.setAccessCode(strOptString4);
                    c.a(c.this, strOptString, "", "", true, monitorStruct);
                    requestCallback.onSuccess(a.c.a().a(strOptString4).a(System.currentTimeMillis() + com.igexin.push.config.c.l).a());
                }
            }, -1);
            return;
        }
        b(requestCallback, ResultCode.CODE_ERROR_ANALYZE_SDK_INFO, ResultCode.MSG_ERROR_ANALYZE_SDK_INFO, "", monitorStruct);
    }
}
