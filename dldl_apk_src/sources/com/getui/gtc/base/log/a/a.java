package com.getui.gtc.base.log.a;

import android.content.Context;
import com.getui.gtc.base.log.ILogController;
import com.getui.gtc.base.log.ILogFormatter;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements ILogController {
    private String a = "sdk.debug";
    private ILogFormatter b;
    private boolean c;
    private boolean d;
    private Context e;

    public a(Context context, ILogFormatter iLogFormatter) throws Throwable {
        this.e = context.getApplicationContext();
        this.b = (ILogFormatter) com.getui.gtc.base.log.e.a.a(iLogFormatter);
        a(this.a);
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x00ea A[Catch: all -> 0x0102, Throwable -> 0x010d, TryCatch #22 {all -> 0x0102, Throwable -> 0x010d, blocks: (B:32:0x00de, B:34:0x00ea, B:39:0x00fc), top: B:107:0x00de }] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00fb  */
    /* JADX WARN: Removed duplicated region for block: B:54:0x014a A[Catch: all -> 0x015f, Throwable -> 0x016a, TryCatch #18 {Throwable -> 0x016a, all -> 0x015f, blocks: (B:52:0x013e, B:54:0x014a, B:57:0x0159), top: B:115:0x013e }] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0158  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void a(java.lang.String r8) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.base.log.a.a.a(java.lang.String):void");
    }

    @Override // com.getui.gtc.base.log.ILogController
    public final boolean isLoggable(int i, String str) {
        int i2 = i & 240;
        if (i2 == 0 || i2 == 32) {
            return this.c || this.d;
        }
        return false;
    }

    @Override // com.getui.gtc.base.log.ILogController
    public final void log(int i, String str, String str2, Throwable th) {
        if ((i & 240) != 0) {
            i &= 15;
        }
        this.b.log(i, str, str2, th);
    }
}
