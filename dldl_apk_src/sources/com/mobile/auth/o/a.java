package com.mobile.auth.o;

import android.content.Context;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.mobile.auth.gatewayauth.model.ConfigRule;
import com.mobile.auth.gatewayauth.model.pns_vendor_query.LimitedInfo;
import com.mobile.auth.gatewayauth.model.pns_vendor_query.UploadRB;
import com.mobile.auth.gatewayauth.utils.f;
import com.mobile.auth.v.b;
import com.mobile.auth.v.c;
import com.mobile.auth.v.d;
import com.nirvana.tools.logger.ACMLogger;
import com.nirvana.tools.logger.ACMMonitor;
import com.nirvana.tools.logger.model.ACMLimitConfig;
import java.lang.Thread;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public final class a {
    public static final String a = null;

    /* JADX INFO: renamed from: com.mobile.auth.o.a$1, reason: invalid class name */
    class AnonymousClass1 implements Thread.UncaughtExceptionHandler {
        AnonymousClass1() {
        }

        @Override // java.lang.Thread.UncaughtExceptionHandler
        public void uncaughtException(Thread thread, Throwable th) {
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$10, reason: invalid class name */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ String[] a;

        AnonymousClass10(String[] strArr) {
            this.a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = a.a(a.this, this.a);
                f.a(strA);
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).debug(a.e(a.this).a(strA, 2));
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$11, reason: invalid class name */
    class AnonymousClass11 implements Runnable {
        final /* synthetic */ String[] a;

        AnonymousClass11(String[] strArr) {
            this.a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = a.a(a.this, this.a);
                f.b(strA);
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).warning(a.e(a.this).a(strA, 4));
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$12, reason: invalid class name */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String[] a;

        AnonymousClass12(String[] strArr) {
            this.a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = a.a(a.this, this.a);
                f.c(strA);
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).error(a.e(a.this).a(strA, 5));
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$13, reason: invalid class name */
    class AnonymousClass13 implements Runnable {
        final /* synthetic */ String[] a;

        AnonymousClass13(String[] strArr) {
            this.a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = a.a(a.this, this.a);
                f.c(strA);
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).crash(a.e(a.this).a(strA, 6));
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$14, reason: invalid class name */
    class AnonymousClass14 implements Runnable {
        final /* synthetic */ long a;
        final /* synthetic */ long b;
        final /* synthetic */ int c;

        AnonymousClass14(long j, long j2, int i) {
            this.a = j;
            this.b = j2;
            this.c = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).uploadLog(this.a, this.b, this.c);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        final /* synthetic */ UploadRB a;

        AnonymousClass2(UploadRB uploadRB) {
            this.a = uploadRB;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a != null && this.a.getAlibaba_aliqin_psc_info_upload_response() != null && this.a.getAlibaba_aliqin_psc_info_upload_response().getResult() != null && this.a.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule() != null && this.a.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule().getLimited_info() != null) {
                    LimitedInfo limited_info = this.a.getAlibaba_aliqin_psc_info_upload_response().getResult().getModule().getLimited_info();
                    ACMLimitConfig aCMLimitConfigBuild = ACMLimitConfig.newACMLimitConfig().isLimited(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(limited_info.getIs_limited())).limitCount(limited_info.getLimit_count()).limitHours(limited_info.getLimit_time_hour()).build();
                    a.c(a.this).setLimitConfig(aCMLimitConfigBuild);
                    a.f(a.this).setLimitConfig(aCMLimitConfigBuild);
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

    /* JADX INFO: renamed from: com.mobile.auth.o.a$3, reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ ConfigRule a;

        AnonymousClass3(ConfigRule configRule) {
            this.a = configRule;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (this.a != null && this.a.getSls() != null) {
                    ACMLimitConfig aCMLimitConfigBuild = ACMLimitConfig.newACMLimitConfig().isLimited(ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(this.a.getSls().getIs_limited())).limitCount(this.a.getSls().getLimit_count()).limitHours(this.a.getSls().getLimit_time_hour()).build();
                    if (ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(this.a.getIs_sls_demoted()) || ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE.equals(this.a.getIs_demoted())) {
                        a.a(a.this, true);
                    } else {
                        a.a(a.this, false);
                    }
                    if (a.d(a.this)) {
                        a.c(a.this).setUploadEnabled(false);
                        a.f(a.this).setUploadEnabled(false);
                    } else {
                        a.c(a.this).setUploadEnabled(true);
                        a.f(a.this).setUploadEnabled(true);
                    }
                    a.c(a.this).setLimitConfig(aCMLimitConfigBuild);
                    a.f(a.this).setLimitConfig(aCMLimitConfigBuild);
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

    /* JADX INFO: renamed from: com.mobile.auth.o.a$4, reason: invalid class name */
    class AnonymousClass4 implements Runnable {
        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.d(a.this)) {
                    return;
                }
                a.c(a.this).uploadManual();
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$5, reason: invalid class name */
    class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.d(a.this)) {
                    return;
                }
                a.c(a.this).uploadFailed();
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$6, reason: invalid class name */
    class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).uploadFailed();
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$7, reason: invalid class name */
    class AnonymousClass7 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;

        AnonymousClass7(String str, int i) {
            this.a = str;
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                a.this.b(new String[]{"CacheMonitor:", this.a, "\n Urgency ", String.valueOf(this.b)});
                if (a.d(a.this)) {
                    return;
                }
                a.c(a.this).monitor(this.a, this.b);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$8, reason: invalid class name */
    class AnonymousClass8 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass8(Context context) {
            this.a = context;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v3, types: [com.mobile.auth.gatewayauth.b, com.mobile.auth.v.b] */
        /* JADX WARN: Type inference failed for: r1v0, types: [com.mobile.auth.v.a, com.mobile.auth.v.c] */
        /* JADX WARN: Type inference failed for: r2v0, types: [com.mobile.auth.v.a, com.mobile.auth.v.d] */
        /* JADX WARN: Type inference failed for: r3v1, types: [com.mobile.auth.v.a, com.nirvana.tools.logger.upload.ACMUpload] */
        /* JADX WARN: Type inference failed for: r3v3, types: [com.mobile.auth.v.a, com.nirvana.tools.logger.upload.ACMUpload] */
        @Override // java.lang.Runnable
        public void run() {
            try {
                a.a(a.this, new c());
                ?? bVar = new b();
                a.a(a.this).a(bVar);
                a.b(a.this, new d());
                a.b(a.this).a(bVar);
                a.a(a.this, new ACMLogger(this.a, a.a(a.this)));
                a.a(a.this, new ACMMonitor(this.a, a.b(a.this)));
                a.c(a.this).setUploadType(1);
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }

    /* JADX INFO: renamed from: com.mobile.auth.o.a$9, reason: invalid class name */
    class AnonymousClass9 implements Runnable {
        final /* synthetic */ String[] a;

        AnonymousClass9(String[] strArr) {
            this.a = strArr;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String strA = a.a(a.this, this.a);
                f.d(strA);
                if (a.d(a.this)) {
                    return;
                }
                a.f(a.this).info(a.e(a.this).a(strA, 3));
            } catch (Throwable th) {
                try {
                    com.mobile.auth.gatewayauth.a.a(th);
                } catch (Throwable th2) {
                    com.mobile.auth.gatewayauth.a.a(th2);
                }
            }
        }
    }
}
