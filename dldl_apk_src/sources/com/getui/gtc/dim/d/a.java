package com.getui.gtc.dim.d;

import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.log.Logger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public Logger a;

    /* JADX INFO: renamed from: com.getui.gtc.dim.d.a$a, reason: collision with other inner class name */
    public static class C0041a {
        private static a a = new a(0);
    }

    private a() {
        this.a = new Logger(GtcProvider.context());
        this.a.setGlobalTag("gtc.dim");
        this.a.setFileEnableProperty("dim.fileLog");
        this.a.setLogcatEnable(false);
        this.a.setLogFileNameSuffix("gtc");
        this.a.setStackOffset(1);
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public static void a(String str) {
        C0041a.a.a.d(str);
    }

    public static void a(Throwable th) {
        C0041a.a.a.w(th);
    }

    public static void b(String str) {
        C0041a.a.a.w(str);
    }
}
