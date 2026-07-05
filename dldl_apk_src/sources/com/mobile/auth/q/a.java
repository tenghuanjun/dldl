package com.mobile.auth.q;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import android.util.Log;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.mobile.auth.e.g;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.mobile.auth.gatewayauth.PnsLoggerHandler;
import com.mobile.auth.gatewayauth.model.ConfigRule;
import com.mobile.auth.gatewayauth.model.pns_vendor_query.LimitedInfo;
import com.mobile.auth.gatewayauth.model.pns_vendor_query.UploadRB;
import com.mobile.auth.k.i;
import com.nirvana.tools.logger.ACMLogger;
import com.nirvana.tools.logger.ACMMonitor;
import com.nirvana.tools.logger.model.ACMLimitConfig;
import com.nirvana.tools.logger.model.ACMMonitorRecord;
import com.nirvana.tools.logger.utils.ConsoleLogUtils;
import com.unionpay.tsmservice.mini.data.Constant;
import java.lang.Thread;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private static volatile a a;
    private ACMLogger b;
    private ACMMonitor c;
    private boolean d;
    private boolean e;
    private com.mobile.auth.x.a f;
    private com.mobile.auth.x.a g;
    private com.mobile.auth.gatewayauth.manager.d h;
    private HandlerThread i;
    private Handler j;
    private volatile PnsLoggerHandler k;
    private List<ACMMonitorRecord> l;

    /* JADX INFO: renamed from: com.mobile.auth.q.a$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ ConfigRule a;

        /* JADX INFO: renamed from: com.mobile.auth.q.a$3$1, reason: invalid class name */
        class AnonymousClass1 implements g {
            AnonymousClass1() {
            }

            @Override // com.mobile.auth.e.g
            public void a(final String str, String str2, final com.cmic.sso.sdk.a aVar, JSONObject jSONObject) {
                try {
                    if ("103000".equals(str)) {
                        AnonymousClass3.this.a.a(a.d(a.this), new g() { // from class: com.mobile.auth.q.a.3.1.1
                            @Override // com.mobile.auth.e.g
                            public void a(String str3, String str4, com.cmic.sso.sdk.a aVar2, JSONObject jSONObject2) {
                                try {
                                    long jB = aVar.b("loginTime");
                                    if (jB != 0) {
                                        aVar.b("loginTime", System.currentTimeMillis() - jB);
                                    }
                                    com.mobile.auth.k.c.a((!"103000".equals(str) || TextUtils.isEmpty(aVar.c("phonescrip"))) ? "authClickFailed" : "authClickSuccess");
                                    a.a(a.this, str3, str4, aVar2, jSONObject2);
                                    i.b(a.e(a.this));
                                } catch (Throwable th) {
                                    try {
                                        com.mobile.auth.gatewayauth.a.a(th);
                                    } catch (Throwable th2) {
                                        com.mobile.auth.gatewayauth.a.a(th2);
                                    }
                                }
                            }
                        });
                    } else {
                        a.a(a.this, str, str2, aVar, jSONObject);
                        i.b(a.e(a.this));
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

        AnonymousClass3(ConfigRule configRule) {
            this.a = configRule;
        }

        /* JADX WARN: Removed duplicated region for block: B:37:0x00e0 A[Catch: all -> 0x0126, TryCatch #0 {all -> 0x0126, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0010, B:10:0x0050, B:11:0x0059, B:31:0x00c1, B:33:0x00cd, B:36:0x00da, B:38:0x00e5, B:40:0x00ed, B:42:0x0112, B:41:0x0100, B:37:0x00e0, B:12:0x005d, B:14:0x0063, B:17:0x006a, B:18:0x007a, B:20:0x0080, B:22:0x0092, B:24:0x0098, B:25:0x009d, B:27:0x00a9, B:29:0x00af, B:30:0x00b7), top: B:51:0x0002 }] */
        /* JADX WARN: Removed duplicated region for block: B:40:0x00ed A[Catch: all -> 0x0126, TryCatch #0 {all -> 0x0126, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0010, B:10:0x0050, B:11:0x0059, B:31:0x00c1, B:33:0x00cd, B:36:0x00da, B:38:0x00e5, B:40:0x00ed, B:42:0x0112, B:41:0x0100, B:37:0x00e0, B:12:0x005d, B:14:0x0063, B:17:0x006a, B:18:0x007a, B:20:0x0080, B:22:0x0092, B:24:0x0098, B:25:0x009d, B:27:0x00a9, B:29:0x00af, B:30:0x00b7), top: B:51:0x0002 }] */
        /* JADX WARN: Removed duplicated region for block: B:41:0x0100 A[Catch: all -> 0x0126, TryCatch #0 {all -> 0x0126, blocks: (B:3:0x0002, B:5:0x0006, B:8:0x0010, B:10:0x0050, B:11:0x0059, B:31:0x00c1, B:33:0x00cd, B:36:0x00da, B:38:0x00e5, B:40:0x00ed, B:42:0x0112, B:41:0x0100, B:37:0x00e0, B:12:0x005d, B:14:0x0063, B:17:0x006a, B:18:0x007a, B:20:0x0080, B:22:0x0092, B:24:0x0098, B:25:0x009d, B:27:0x00a9, B:29:0x00af, B:30:0x00b7), top: B:51:0x0002 }] */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 304
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.q.a.AnonymousClass3.run():void");
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.q.a$a, reason: collision with other inner class name */
    private class RunnableC0082a implements Runnable {
        private com.cmic.sso.sdk.a b;
        private volatile boolean c = false;

        RunnableC0082a(com.cmic.sso.sdk.a aVar) {
            this.b = aVar;
        }

        private synchronized boolean a() {
            boolean z;
            try {
                z = this.c;
                this.c = true;
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                    return false;
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                    return false;
                }
            }
            return !z;
        }

        static /* synthetic */ boolean a(RunnableC0082a runnableC0082a) {
            try {
                return runnableC0082a.a();
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                    return false;
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
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
                    a.a(a.this).a("200023", "登录超时", this.b, jSONObject, (Throwable) null);
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

    private a() {
        this.d = false;
        this.e = false;
        this.i = null;
        this.j = null;
        this.l = new ArrayList();
        HandlerThread handlerThread = new HandlerThread("PnsLoggerThread");
        this.i = handlerThread;
        handlerThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() { // from class: com.mobile.auth.q.a.1
            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
            }
        });
        this.i.start();
        this.j = new Handler(this.i.getLooper());
    }

    public a(final Context context) {
        this();
        this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.8
            @Override // java.lang.Runnable
            public void run() {
                try {
                    a.a(a.this, new com.mobile.auth.x.c());
                    com.mobile.auth.x.b bVar = new com.mobile.auth.x.b();
                    a.a(a.this).a(bVar);
                    a.b(a.this, new com.mobile.auth.x.d());
                    a.b(a.this).a(bVar);
                    a.a(a.this, new ACMLogger(context, a.a(a.this)));
                    a.a(a.this, new ACMMonitor(context, a.b(a.this)));
                    a.c(a.this).setUploadType(1);
                    a.c(a.this).setMaxUploadRetry(1L);
                    a.c(a.this).setMaxUploadSize(100);
                    a.c(a.this).setRetryCount(0);
                    ConsoleLogUtils.setLoggerEnable(true);
                } catch (Throwable th) {
                    try {
                        ExceptionProcessor.processException(th);
                    } catch (Throwable th2) {
                        ExceptionProcessor.processException(th2);
                    }
                }
            }
        });
    }

    public static a a(Context context) {
        try {
            if (a == null) {
                synchronized (a.class) {
                    if (a == null && context != null) {
                        a = new a(context);
                    }
                }
            }
            return a;
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

    static /* synthetic */ com.mobile.auth.x.a a(a aVar) {
        try {
            return aVar.f;
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

    static /* synthetic */ com.mobile.auth.x.a a(a aVar, com.mobile.auth.x.a aVar2) {
        try {
            aVar.f = aVar2;
            return aVar2;
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

    static /* synthetic */ ACMLogger a(a aVar, ACMLogger aCMLogger) {
        try {
            aVar.b = aCMLogger;
            return aCMLogger;
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

    static /* synthetic */ ACMMonitor a(a aVar, ACMMonitor aCMMonitor) {
        try {
            aVar.c = aCMMonitor;
            return aCMMonitor;
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

    static /* synthetic */ String a(a aVar, String[] strArr) {
        try {
            return aVar.f(strArr);
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

    static /* synthetic */ List a(a aVar, List list) {
        try {
            aVar.l = list;
            return list;
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

    static /* synthetic */ boolean a(a aVar, boolean z) {
        try {
            aVar.e = z;
            return z;
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

    static /* synthetic */ com.mobile.auth.x.a b(a aVar) {
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

    static /* synthetic */ com.mobile.auth.x.a b(a aVar, com.mobile.auth.x.a aVar2) {
        try {
            aVar.g = aVar2;
            return aVar2;
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

    static /* synthetic */ boolean b(a aVar, boolean z) {
        try {
            aVar.d = z;
            return z;
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

    static /* synthetic */ ACMMonitor c(a aVar) {
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

    static /* synthetic */ PnsLoggerHandler d(a aVar) {
        try {
            return aVar.k;
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

    static /* synthetic */ boolean e(a aVar) {
        try {
            return aVar.d;
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

    static /* synthetic */ ACMLogger f(a aVar) {
        try {
            return aVar.b;
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

    private String f(String... strArr) {
        if (strArr != null) {
            try {
                if (strArr.length != 0) {
                    if (strArr.length == 1) {
                        return strArr[0];
                    }
                    StringBuilder sb = new StringBuilder();
                    for (String str : strArr) {
                        sb.append(str);
                    }
                    return sb.toString();
                }
            } catch (Throwable th) {
                try {
                    ExceptionProcessor.processException(th);
                    return null;
                } catch (Throwable th2) {
                    ExceptionProcessor.processException(th2);
                }
            }
        }
        return null;
    }

    static /* synthetic */ List g(a aVar) {
        try {
            return aVar.l;
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

    public void a(PnsLoggerHandler pnsLoggerHandler) {
        try {
            this.k = pnsLoggerHandler;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(com.mobile.auth.gatewayauth.manager.d dVar) {
        try {
            this.h = dVar;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(ConfigRule configRule) {
        try {
            this.j.post(new AnonymousClass3(configRule));
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
            }
        }
    }

    public void a(final UploadRB uploadRB) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.2
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (uploadRB != null && uploadRB.getAlibaba_aliqin_psc_info_upload_response() != null && uploadRB.getAlibaba_aliqin_psc_info_upload_response().getResult() != null && uploadRB.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule() != null && uploadRB.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule().getLimited_info() != null) {
                            LimitedInfo limited_info = uploadRB.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule().getLimited_info();
                            ACMLimitConfig aCMLimitConfigBuild = ACMLimitConfig.newACMLimitConfig().isLimited(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(limited_info.getIs_limited())).limitCount(limited_info.getLimit_count()).limitHours(limited_info.getLimit_time_hour()).build();
                            a.c(a.this).setLimitConfig(aCMLimitConfigBuild);
                            a.f(a.this).setLimitConfig(aCMLimitConfigBuild);
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

    public void a(final String str, final int i) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.7
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        PnsLoggerHandler pnsLoggerHandlerD = a.d(a.this);
                        if (pnsLoggerHandlerD != null) {
                            pnsLoggerHandlerD.monitor(str);
                        }
                        a.this.b("CacheMonitor:", str, "\n Urgency ", String.valueOf(i));
                        if (a.e(a.this)) {
                            return;
                        }
                        if (a.g(a.this) == null) {
                            a.a(a.this, new ArrayList());
                        }
                        ACMMonitorRecord aCMMonitorRecord = new ACMMonitorRecord(i);
                        aCMMonitorRecord.setContent(str);
                        a.g(a.this).add(aCMMonitorRecord);
                        if (a.g(a.this).size() >= 5) {
                            a.c(a.this).monitorRecords(a.g(a.this));
                            a.g(a.this).clear();
                            Log.d("CacheMonitor", "cache and clear");
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

    public void a(final String... strArr) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.9
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strA = a.a(a.this, strArr);
                        PnsLoggerHandler pnsLoggerHandlerD = a.d(a.this);
                        if (pnsLoggerHandlerD != null) {
                            pnsLoggerHandlerD.info(strA);
                        }
                        com.mobile.auth.gatewayauth.utils.i.d(strA);
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

    public boolean a() {
        try {
            return this.e;
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

    public void b() {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (a.e(a.this)) {
                            return;
                        }
                        if (a.g(a.this) != null && a.g(a.this).size() > 0) {
                            a.c(a.this).monitorRecords(a.g(a.this));
                            a.g(a.this).clear();
                            a.this.b("CacheMonitor:", "uploadMonitor and clear");
                        }
                        a.c(a.this).uploadManual();
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

    public void b(final String... strArr) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.10
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strA = a.a(a.this, strArr);
                        PnsLoggerHandler pnsLoggerHandlerD = a.d(a.this);
                        if (pnsLoggerHandlerD != null) {
                            pnsLoggerHandlerD.debug(strA);
                        }
                        com.mobile.auth.gatewayauth.utils.i.a(strA);
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

    public void c() {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.5
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (a.e(a.this)) {
                            return;
                        }
                        if (a.g(a.this) != null && a.g(a.this).size() > 0) {
                            a.c(a.this).monitorRecords(a.g(a.this));
                            a.g(a.this).clear();
                            a.this.b("CacheMonitor:", "uploadFailedMonitor and clear");
                        }
                        a.c(a.this).uploadFailed();
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

    public void c(final String... strArr) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.11
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strA = a.a(a.this, strArr);
                        PnsLoggerHandler pnsLoggerHandlerD = a.d(a.this);
                        if (pnsLoggerHandlerD != null) {
                            pnsLoggerHandlerD.warning(strA);
                        }
                        com.mobile.auth.gatewayauth.utils.i.b(strA);
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

    public void d() {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.6
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        a.this.b("deleteMonitor:", "delete unupload Monitor");
                        a.c(a.this).deleteRecordsByFlag(2);
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

    public void d(final String... strArr) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.12
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        com.mobile.auth.gatewayauth.utils.i.b(a.a(a.this, strArr));
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

    public void e(final String... strArr) {
        try {
            this.j.post(new Runnable() { // from class: com.mobile.auth.q.a.13
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        String strA = a.a(a.this, strArr);
                        PnsLoggerHandler pnsLoggerHandlerD = a.d(a.this);
                        if (pnsLoggerHandlerD != null) {
                            pnsLoggerHandlerD.error(strA);
                        }
                        com.mobile.auth.gatewayauth.utils.i.c(strA);
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
