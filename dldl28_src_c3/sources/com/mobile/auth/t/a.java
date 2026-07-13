package com.mobile.auth.t;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.ResultCode;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.manager.base.b;
import com.mobile.auth.gatewayauth.manager.d;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.gatewayauth.utils.c;
import com.mobile.auth.gatewayauth.utils.i;
import com.unicom.online.account.shield.ResultListener;
import com.unicom.online.account.shield.UniAccountHelper;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a extends com.mobile.auth.gatewayauth.manager.a {
    public a(Context context, d dVar) {
        super(context, dVar, Constant.VENDOR_CUCC, null);
    }

    private synchronized void a(final RequestCallback<a.C0182a, b> requestCallback, final MonitorStruct monitorStruct, final String str) {
        try {
            UniAccountHelper.getInstance().cuGetToken((int) this.c, new ResultListener() { // from class: com.mobile.auth.t.a.1
                @Override // com.unicom.online.account.shield.ResultListener
                public void onResult(String str2) {
                    try {
                        try {
                            if (!TextUtils.isEmpty("")) {
                                Log.i("cuzx login result:", str2);
                            }
                            JSONObject jSONObject = new JSONObject(str2);
                            int iOptInt = jSONObject.optInt("resultCode");
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
                                requestCallback.onSuccess(a.C0182a.a().a(strOptString2).c(Constant.CUCC_WOPROTOCOL).d(Constant.CUCC_WOPROTOCOL_URL).b(strOptString3).a(jOptLong).a());
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
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    static /* synthetic */ void a(a aVar, RequestCallback requestCallback, String str, String str2, String str3, String str4, MonitorStruct monitorStruct, String str5) {
        try {
            aVar.a((RequestCallback<a.C0182a, b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            aVar.a((RequestCallback<a.C0182a, b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            aVar.a((RequestCallback<a.C0182a, b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            aVar.a((RequestCallback<a.C0182a, b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
            aVar.a((RequestCallback<a.C0182a, b>) requestCallback, str, str2, str3, str4, monitorStruct, str5);
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
    /* JADX WARN: Removed duplicated region for block: B:26:0x0050  */
    @Override // com.mobile.auth.gatewayauth.manager.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected java.lang.String b(java.lang.String r7, java.lang.String r8) {
        /*
            r6 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r7)     // Catch: java.lang.Throwable -> L67
            if (r0 == 0) goto L7
            return r7
        L7:
            int r0 = r7.hashCode()     // Catch: java.lang.Throwable -> L67
            r1 = 5
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            switch(r0) {
                case 1535446013: goto L46;
                case 1535446014: goto L3c;
                case 1535446015: goto L32;
                case 1535446016: goto L28;
                case 1535446017: goto L1e;
                case 1535446018: goto L14;
                default: goto L13;
            }     // Catch: java.lang.Throwable -> L67
        L13:
            goto L50
        L14:
            java.lang.String r0 = "410005"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 5
            goto L51
        L1e:
            java.lang.String r0 = "410004"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 4
            goto L51
        L28:
            java.lang.String r0 = "410003"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 3
            goto L51
        L32:
            java.lang.String r0 = "410002"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 2
            goto L51
        L3c:
            java.lang.String r0 = "410001"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 1
            goto L51
        L46:
            java.lang.String r0 = "410000"
            boolean r7 = r7.equals(r0)     // Catch: java.lang.Throwable -> L67
            if (r7 == 0) goto L50
            r7 = 0
            goto L51
        L50:
            r7 = -1
        L51:
            if (r7 == 0) goto L64
            if (r7 == r5) goto L61
            if (r7 == r4) goto L61
            if (r7 == r3) goto L5e
            if (r7 == r2) goto L5e
            if (r7 == r1) goto L5e
            return r8
        L5e:
            java.lang.String r7 = "-10006"
            return r7
        L61:
            java.lang.String r7 = "600025"
            return r7
        L64:
            java.lang.String r7 = "600015"
            return r7
        L67:
            r7 = move-exception
            r8 = 0
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r7)     // Catch: java.lang.Throwable -> L6d
            return r8
        L6d:
            r7 = move-exception
            com.mobile.auth.gatewayauth.ExceptionProcessor.processException(r7)
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.t.a.b(java.lang.String, java.lang.String):java.lang.String");
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public void c() {
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void d(RequestCallback<a.C0182a, b> requestCallback, a.b bVar) {
        try {
            MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_LOGIN_CODE);
            monitorStruct.setUrgency(1);
            monitorStruct.setNetworkType(c.a(this.d, true));
            a(requestCallback, monitorStruct, ResultCode.CODE_GET_MASK_FAIL);
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void e(final RequestCallback<a.C0182a, b> requestCallback, a.b bVar) {
        try {
            MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_LOGIN_TOKEN);
            monitorStruct.setNetworkType(c.a(this.d, true));
            a(new RequestCallback<a.C0182a, b>() { // from class: com.mobile.auth.t.a.2
                public void a(a.C0182a c0182a) {
                    try {
                        requestCallback.onSuccess(c0182a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                public void a(b bVar2) {
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
                public /* synthetic */ void onError(b bVar2) {
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
                public /* synthetic */ void onSuccess(a.C0182a c0182a) {
                    try {
                        a(c0182a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }

    @Override // com.mobile.auth.gatewayauth.manager.a
    public synchronized void f(final RequestCallback<a.C0182a, b> requestCallback, a.b bVar) {
        try {
            MonitorStruct monitorStruct = new MonitorStruct();
            monitorStruct.putApiParam(Constant.API_PARAMS_KEY_TIMEOUT, String.valueOf(this.c));
            monitorStruct.setSessionId(bVar.c());
            monitorStruct.setRequestId(bVar.b());
            monitorStruct.setStartTime(System.currentTimeMillis());
            monitorStruct.setAction(Constant.ACTION_CUCC_AUTH_TOKEN);
            monitorStruct.setNetworkType(c.a(this.d, true));
            a(new RequestCallback<a.C0182a, b>() { // from class: com.mobile.auth.t.a.3
                public void a(a.C0182a c0182a) {
                    try {
                        requestCallback.onSuccess(c0182a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }

                public void a(b bVar2) {
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
                public /* synthetic */ void onError(b bVar2) {
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
                public /* synthetic */ void onSuccess(a.C0182a c0182a) {
                    try {
                        a(c0182a);
                    } catch (Throwable th) {
                        try {
                            ExceptionProcessor.processException(th);
                        } catch (Throwable th2) {
                            ExceptionProcessor.processException(th2);
                        }
                    }
                }
            }, monitorStruct, ResultCode.CODE_GET_TOKEN_FAIL);
        } finally {
            try {
                ExceptionProcessor.processException(th);
            } finally {
            }
        }
    }
}
