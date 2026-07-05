package com.mobile.auth.r;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.Constant;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.manager.RequestCallback;
import com.mobile.auth.gatewayauth.manager.a;
import com.mobile.auth.gatewayauth.model.MonitorStruct;
import com.mobile.auth.gatewayauth.model.ctcctoken.CTCCTokenRet;
import com.mobile.auth.gatewayauth.model.ctcctoken.Data;
import com.mobile.auth.gatewayauth.utils.EncryptUtils;
import com.mobile.auth.w.a;
import com.nirvana.tools.requestqueue.TimeoutCallable;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public abstract class a<T extends com.mobile.auth.w.a> implements TimeoutCallable<com.mobile.auth.w.a> {
    private Context a;
    private com.mobile.auth.gatewayauth.manager.b b;

    /* JADX INFO: renamed from: com.mobile.auth.r.a$1, reason: invalid class name */
    class AnonymousClass1 implements com.mobile.auth.a.c {
        AnonymousClass1() {
        }

        @Override // com.mobile.auth.a.c
        public void a(String str, String str2) {
            try {
                a.a(a.this).a(new String[]{str, str2});
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }

        @Override // com.mobile.auth.a.c
        public void a(String str, String str2, Throwable th) {
            try {
                if (th != null) {
                    a.a(a.this).c(new String[]{str, str2, com.mobile.auth.gatewayauth.utils.b.b(th)});
                } else {
                    a.a(a.this).c(new String[]{str, str2});
                }
            } catch (Throwable th2) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th2);
                } catch (Throwable th3) {
                    com.mobile.auth.gatewayauth.a.a(th3);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.r.a$2, reason: invalid class name */
    class AnonymousClass2 implements com.mobile.auth.a.b {
        final /* synthetic */ RequestCallback a;
        final /* synthetic */ MonitorStruct b;
        final /* synthetic */ String c;

        AnonymousClass2(RequestCallback requestCallback, MonitorStruct monitorStruct, String str) {
            this.a = requestCallback;
            this.b = monitorStruct;
            this.c = str;
        }

        @Override // com.mobile.auth.a.b
        public void a(String str) {
            a aVar;
            RequestCallback requestCallback;
            String strValueOf;
            String msg;
            MonitorStruct monitorStruct;
            String str2;
            try {
                a.a(a.this).a(new String[]{"ctcc：", "getLoginInfo:", str});
                if (TextUtils.isEmpty(str)) {
                    a.a(a.this, this.a, Constant.CODE_ERROR_UNKNOWN_FAIL, "CTCC 获得的手机授权码结果为空", "", this.b, this.c);
                    return;
                }
                try {
                    CTCCTokenRet cTCCTokenRetFromJson = CTCCTokenRet.fromJson(str);
                    if (cTCCTokenRetFromJson != null) {
                        this.b.setCarrierTraceId(cTCCTokenRetFromJson.getReqId());
                        if (cTCCTokenRetFromJson.getResult() != 0 || cTCCTokenRetFromJson.getData() == null) {
                            aVar = a.this;
                            requestCallback = this.a;
                            strValueOf = String.valueOf(cTCCTokenRetFromJson.getResult());
                            msg = cTCCTokenRetFromJson.getMsg();
                            monitorStruct = this.b;
                            str2 = this.c;
                        } else {
                            Data data = cTCCTokenRetFromJson.getData();
                            String number = data.getNumber();
                            String accessCode = data.getAccessCode();
                            if (!TextUtils.isEmpty(number)) {
                                this.a.onSuccess(a.C0070a.a().a(number).c(Constant.CTCC_PROTOCOL).d("https://e.189.cn/sdk/agreement/detail.do?isWap=true&hidetop=true&appKey=8138111118").b(accessCode).a(System.currentTimeMillis() + ((long) (data.getExpiredTime() * 1000))).a());
                                this.b.setPhoneNumber(number);
                                this.b.setAccessCode(accessCode);
                                a.a(a.this, String.valueOf(cTCCTokenRetFromJson.getResult()), "", "", true, this.b);
                                return;
                            }
                            aVar = a.this;
                            requestCallback = this.a;
                            strValueOf = String.valueOf(cTCCTokenRetFromJson.getResult());
                            msg = cTCCTokenRetFromJson.getMsg();
                            monitorStruct = this.b;
                            str2 = this.c;
                        }
                        a.a(aVar, requestCallback, strValueOf, msg, str, monitorStruct, str2);
                    }
                } catch (Exception e) {
                    a.a(a.this).d(new String[]{"CTCCValidManager init exception:", com.mobile.auth.gatewayauth.utils.b.b(e)});
                    a.a(a.this, this.a, Constant.CODE_ERROR_UNKNOWN_FAIL, "JSON转换失败", str, this.b, this.c);
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

    /* JADX INFO: renamed from: com.mobile.auth.r.a$3, reason: invalid class name */
    class AnonymousClass3 implements com.mobile.auth.a.b {
        final /* synthetic */ RequestCallback a;
        final /* synthetic */ MonitorStruct b;

        AnonymousClass3(RequestCallback requestCallback, MonitorStruct monitorStruct) {
            this.a = requestCallback;
            this.b = monitorStruct;
        }

        @Override // com.mobile.auth.a.b
        public void a(String str) {
            a aVar;
            RequestCallback requestCallback;
            String strValueOf;
            String msg;
            MonitorStruct monitorStruct;
            try {
                a.a(a.this).a(new String[]{"ctcc：", "getVerifyInfo:", str});
                if (TextUtils.isEmpty(str)) {
                    a.a(a.this, this.a, Constant.CODE_ERROR_UNKNOWN_FAIL, "CTCC 获得认证Token结果为空", "", this.b);
                    return;
                }
                try {
                    CTCCTokenRet cTCCTokenRetFromJson = CTCCTokenRet.fromJson(str);
                    if (cTCCTokenRetFromJson != null) {
                        this.b.setCarrierTraceId(cTCCTokenRetFromJson.getReqId());
                        if (cTCCTokenRetFromJson.getResult() != 0 || cTCCTokenRetFromJson.getData() == null) {
                            aVar = a.this;
                            requestCallback = this.a;
                            strValueOf = String.valueOf(cTCCTokenRetFromJson.getResult());
                            msg = cTCCTokenRetFromJson.getMsg();
                            monitorStruct = this.b;
                        } else {
                            Data data = cTCCTokenRetFromJson.getData();
                            String accessCode = data.getAccessCode();
                            if (!TextUtils.isEmpty(accessCode)) {
                                this.b.setAccessCode(accessCode);
                                a.a(a.this, String.valueOf(cTCCTokenRetFromJson.getResult()), cTCCTokenRetFromJson.getMsg(), "", true, this.b);
                                this.a.onSuccess(a.c.a().a(accessCode).a(System.currentTimeMillis() + ((long) (data.getExpiredTime() * 1000))).a());
                                return;
                            } else {
                                aVar = a.this;
                                requestCallback = this.a;
                                strValueOf = String.valueOf(cTCCTokenRetFromJson.getResult());
                                msg = cTCCTokenRetFromJson.getMsg();
                                monitorStruct = this.b;
                            }
                        }
                        a.a(aVar, requestCallback, strValueOf, msg, str, monitorStruct);
                    }
                } catch (Exception e) {
                    a.a(a.this).d(new String[]{"CTCCValidManager init exception:", com.mobile.auth.gatewayauth.utils.b.b(e)});
                    a.a(a.this, this.a, Constant.CODE_ERROR_UNKNOWN_FAIL, "JSON转换失败", str, this.b);
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

    /* JADX INFO: renamed from: com.mobile.auth.r.a$4, reason: invalid class name */
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

    public a(Context context, com.mobile.auth.gatewayauth.manager.b bVar) {
        this.a = context.getApplicationContext();
        this.b = bVar;
    }

    public abstract T a();

    public abstract T a(String str);

    public com.mobile.auth.w.a b() {
        try {
            if (this.b.j()) {
                return a();
            }
            this.b.k();
            return a(EncryptUtils.generateAesKey());
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

    public Context c() {
        try {
            return this.a;
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

    @Override // java.util.concurrent.Callable
    public /* synthetic */ Object call() throws Exception {
        try {
            return b();
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
}
