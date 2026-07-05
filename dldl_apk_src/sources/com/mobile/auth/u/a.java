package com.mobile.auth.u;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.gatewayauth.utils.i;
import com.unicom.online.account.shield.ResultListener;
import com.unicom.online.account.shield.UniAccountHelper;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a extends com.mobile.auth.gatewayauth.manager.a {
    public a(Context context, com.mobile.auth.gatewayauth.manager.d dVar) {
        super(context, dVar, Constant.VENDOR_CUCC, null);
    }

    private synchronized void a(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, final MonitorStruct monitorStruct, final String str) {
        try {
            UniAccountHelper.getInstance().cuGetToken((int) this.c, new ResultListener() { // from class: com.mobile.auth.u.a.1
                @Override // com.unicom.online.account.shield.ResultListener
                public void onResult(String str2) {
                    try {
                        try {
                            if (!TextUtils.isEmpty("")) {
                                Log.i("cuzx login result:", str2);
                            }
                            JSONObject jSONObject = new JSONObject(str2);
                            int iOptInt = jSONObject.optInt(com.unionpay.tsmservice.mini.data.Constant.KEY_RESULT_CODE);
                            String strOptString = jSONObject.optString("resultMsg");
                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("resultData");
                            if (iOptInt != 100) {
                                if ("1202".equals(Integer.valueOf(iOptInt))) {
                                    a.c(a.this, requestCallback, String.valueOf(iOptInt), ResultCode.MSG_ERROR_ANALYZE_SDK_BLACKLIST_INFO, str2, Constant.VENDOR_CUCC, monitorStruct, str);
                                    return;
                                } else {
                                    a.d(a.this, requestCallback, String.valueOf(iOptInt), strOptString, str2, Constant.VENDOR_CUCC, monitorStruct, str);
                                    return;
                                }
                            }
                            if (jSONObjectOptJSONObject != null) {
                                String strOptString2 = jSONObjectOptJSONObject.optString("fakeMobile");
                                String strOptString3 = jSONObjectOptJSONObject.optString("accessCode");
                                long jOptLong = jSONObjectOptJSONObject.optLong("exp");
                                if (TextUtils.isEmpty(strOptString2) || TextUtils.isEmpty(strOptString3)) {
                                    if ("1202".equals(Integer.valueOf(iOptInt))) {
                                        a.a(a.this, requestCallback, String.valueOf(iOptInt), ResultCode.MSG_ERROR_ANALYZE_SDK_BLACKLIST_INFO, str2, Constant.VENDOR_CUCC, monitorStruct, str);
                                        return;
                                    } else {
                                        a.b(a.this, requestCallback, String.valueOf(iOptInt), strOptString, str2, Constant.VENDOR_CUCC, monitorStruct, str);
                                        return;
                                    }
                                }
                                requestCallback.onSuccess(a.C0070a.a().a(strOptString2).c(Constant.CUCC_WOPROTOCOL).d(Constant.CUCC_WOPROTOCOL_URL).b(strOptString3).a(jOptLong).a());
                                monitorStruct.setAccessCode(strOptString3);
                                monitorStruct.setPhoneNumber(strOptString2);
                                a.a(a.this, String.valueOf(iOptInt), "", "", true, Constant.VENDOR_CUCC, monitorStruct);
                            }
                        } catch (Exception e) {
                            a.e(a.this, requestCallback, Constant.CODE_ERROR_UNKNOWN_FAIL, "JSON转换失败", e.toString(), Constant.VENDOR_CUCC, monitorStruct, str);
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

    static /* synthetic */ void a(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void a(a aVar, String str, String str2, String str3, boolean z, String str4, MonitorStruct monitorStruct) {
        try {
            aVar.a(str, str2, str3, z, str4, monitorStruct);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void b(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void b(a aVar, String str, String str2, String str3, boolean z, String str4, MonitorStruct monitorStruct) {
        try {
            aVar.a(str, str2, str3, z, str4, monitorStruct);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void c(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void d(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void e(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void f(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void g(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void h(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void i(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    static /* synthetic */ void j(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            UniAccountHelper.getInstance().setLogEnable(i.b());
            UniAccountHelper.getInstance().init(this.d, str);
            UniAccountHelper.getInstance().setUseCacheFlag(false);
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
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.mobile.auth.gatewayauth.manager.a
    protected String b(String str, String str2) {
        try {
            if (TextUtils.isEmpty(str)) {
                return str;
            }
            byte b = -1;
            switch (str.hashCode()) {
                case 1535446013:
                    if (str.equals("410000")) {
                        b = 0;
                    }
                    break;
                case 1535446014:
                    if (str.equals("410001")) {
                        b = 1;
                    }
                    break;
                case 1535446015:
                    if (str.equals("410002")) {
                        b = 2;
                    }
                    break;
                case 1535446016:
                    if (str.equals("410003")) {
                        b = 3;
                    }
                    break;
                case 1535446017:
                    if (str.equals("410004")) {
                        b = 4;
                    }
                    break;
                case 1535446018:
                    if (str.equals("410005")) {
                        b = 5;
                    }
                    break;
            }
            return b != 0 ? (b == 1 || b == 2) ? "600025" : (b == 3 || b == 4 || b == 5) ? Constant.CODE_ERROR_NO_MOBILE_NETWORK_FAIL : str2 : ResultCode.CODE_ERROR_FUNCTION_TIME_OUT;
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
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void d(RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        try {
            MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_LOGIN_CODE);
            monitorStruct.setUrgency(1);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
            a(requestCallback, monitorStruct, ResultCode.CODE_GET_MASK_FAIL);
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void e(final RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b> requestCallback, a.b bVar) {
        try {
            MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_LOGIN_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
            a(new RequestCallback<a.C0070a, com.mobile.auth.gatewayauth.manager.base.b>() { // from class: com.mobile.auth.u.a.2
                public void a(a.C0070a c0070a) {
                    try {
                        requestCallback.onSuccess(c0070a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                public void a(com.mobile.auth.gatewayauth.manager.base.b bVar2) {
                    try {
                        requestCallback.onError(bVar2);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onError(com.mobile.auth.gatewayauth.manager.base.b bVar2) {
                    try {
                        a(bVar2);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                @Override // com.mobile.auth.gatewayauth.manager.RequestCallback
                public /* synthetic */ void onSuccess(a.C0070a c0070a) {
                    try {
                        a(c0070a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
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
        try {
            final MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam("timeout", String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_AUTH_TOKEN);
            monitorStruct.setNetworkType(com.mobile.auth.gatewayauth.utils.c.a(this.d, true));
            UniAccountHelper.getInstance().cuMobileAuth((int) this.c, new ResultListener() { // from class: com.mobile.auth.u.a.3
                @Override // com.unicom.online.account.shield.ResultListener
                public void onResult(String str) {
                    try {
                        try {
                            if (!TextUtils.isEmpty("")) {
                                Log.i("cuzx verify result:", str);
                            }
                            JSONObject jSONObject = new JSONObject(str);
                            int iOptInt = jSONObject.optInt(com.unionpay.tsmservice.mini.data.Constant.KEY_RESULT_CODE);
                            String strOptString = jSONObject.optString("resultMsg");
                            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("resultData");
                            if (iOptInt != 100) {
                                if ("1202".equals(Integer.valueOf(iOptInt))) {
                                    a.h(a.this, requestCallback, String.valueOf(iOptInt), ResultCode.MSG_ERROR_ANALYZE_SDK_BLACKLIST_INFO, str, Constant.VENDOR_CUCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
                                    return;
                                } else {
                                    a.i(a.this, requestCallback, String.valueOf(iOptInt), strOptString, str, Constant.VENDOR_CUCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
                                    return;
                                }
                            }
                            if (jSONObjectOptJSONObject != null) {
                                String strOptString2 = jSONObjectOptJSONObject.optString("accessCode");
                                long jOptLong = jSONObjectOptJSONObject.optLong("exp");
                                if (!TextUtils.isEmpty(strOptString2)) {
                                    requestCallback.onSuccess(a.C0070a.a().c(Constant.CUCC_WOPROTOCOL).d(Constant.CUCC_WOPROTOCOL_URL).b(strOptString2).a(jOptLong).a());
                                    monitorStruct.setAccessCode(strOptString2);
                                    a.b(a.this, String.valueOf(iOptInt), "", "", true, Constant.VENDOR_CUCC, monitorStruct);
                                } else if ("1202".equals(Integer.valueOf(iOptInt))) {
                                    a.f(a.this, requestCallback, String.valueOf(iOptInt), ResultCode.MSG_ERROR_ANALYZE_SDK_BLACKLIST_INFO, str, Constant.VENDOR_CUCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
                                } else {
                                    a.g(a.this, requestCallback, String.valueOf(iOptInt), strOptString, str, Constant.VENDOR_CUCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
                                }
                            }
                        } catch (Exception e) {
                            a.j(a.this, requestCallback, Constant.CODE_ERROR_UNKNOWN_FAIL, "JSON转换失败", e.toString(), Constant.VENDOR_CUCC, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
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
