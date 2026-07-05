package com.alipay.deviceid.module.x;

import android.content.Context;
import com.alipay.deviceid.module.rpc.deviceFp.BugTrackMessageService;
import com.alipay.deviceid.module.rpc.report.open.OpenReportService;
import com.alipay.deviceid.module.rpc.report.open.model.ReportRequest;
import com.alipay.deviceid.module.rpc.report.open.model.ReportResult;
import java2jni_do_not_delete_this.java2jni_do_not_delete_this_library_deviceid_1607;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public final class q {
    private static q a;
    private static ReportResult e;
    private bd b;
    private BugTrackMessageService c;
    private OpenReportService d;

    /* JADX INFO: renamed from: com.alipay.deviceid.module.x.q$1, reason: invalid class name */
    final class AnonymousClass1 implements Runnable {
        final /* synthetic */ ReportRequest a;

        AnonymousClass1(ReportRequest reportRequest) {
            this.a = reportRequest;
        }

        @Override // java.lang.Runnable
        public final void run() {
            try {
                ReportResult unused = q.e = q.this.d.reportData(this.a);
            } catch (Throwable th) {
                ReportResult unused2 = q.e = new ReportResult();
                q.e.success = false;
                q.e.resultCode = "static data rpc upload error, " + e.a(th);
                v.a("Rpc failed.");
                v.a(e.a(th));
            }
        }
    }

    static {
        java2jni_do_not_delete_this_library_deviceid_1607.loadLibrary();
        a = null;
    }

    private q(Context context, String str) {
        this.b = null;
        this.c = null;
        this.d = null;
        bh bhVar = new bh();
        bhVar.a = str;
        ar arVar = new ar(context);
        this.b = arVar;
        this.c = (BugTrackMessageService) arVar.a(BugTrackMessageService.class, bhVar);
        this.d = (OpenReportService) this.b.a(OpenReportService.class, bhVar);
    }

    public static native q a(Context context, String str);

    public final native o a(Context context, p pVar);

    public final native boolean a(String str);
}
