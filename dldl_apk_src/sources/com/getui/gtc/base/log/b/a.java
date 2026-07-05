package com.getui.gtc.base.log.b;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.crypt.CryptTools;
import com.getui.gtc.base.log.ILogDestination;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a implements ILogDestination {
    private static Map<File, Handler> d = new ConcurrentHashMap();
    private static Map<File, SecretKey> e = new ConcurrentHashMap();
    private static Map<File, IvParameterSpec> f = new ConcurrentHashMap();
    public File a;
    public String b;
    public Context c;

    public a(Context context) {
        this.c = context.getApplicationContext();
        this.b = context.getPackageName() + "-" + new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()) + ".log";
        StringBuilder sb = new StringBuilder();
        sb.append(GtcProvider.getSdcardPath());
        sb.append("/libs");
        this.a = new File(sb.toString(), this.b);
    }

    private void a(final File file) {
        d.get(file).post(new Runnable() { // from class: com.getui.gtc.base.log.b.a.1
            /* JADX WARN: Removed duplicated region for block: B:69:0x00f1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:84:0x00da A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:97:? A[SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 245
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.base.log.b.a.AnonymousClass1.run():void");
            }
        });
    }

    @Override // com.getui.gtc.base.log.ILogDestination
    public void log(int i, String str, final String str2) {
        if (!this.a.exists() || !this.a.canWrite()) {
            try {
                this.a.getParentFile().mkdirs();
                this.a.createNewFile();
            } catch (Throwable unused) {
            }
            if (!this.a.exists() || !this.a.canWrite()) {
                this.a = new File(this.c.getExternalFilesDir(null), this.b);
                try {
                    this.a.createNewFile();
                } catch (Throwable unused2) {
                }
            }
            if (!this.a.exists() || !this.a.canWrite()) {
                this.a = new File(this.c.getFilesDir(), this.b);
                try {
                    this.a.createNewFile();
                } catch (Throwable th) {
                    th.printStackTrace();
                    return;
                }
            }
            if (d.get(this.a) != null) {
                a(this.a);
            }
        }
        if (d.get(this.a) == null || e.get(this.a) == null || f.get(this.a) == null) {
            synchronized (a.class) {
                if (d.get(this.a) == null) {
                    HandlerThread handlerThread = new HandlerThread("File-Log-Thread");
                    handlerThread.start();
                    d.put(this.a, new Handler(handlerThread.getLooper()));
                }
                if (e.get(this.a) == null) {
                    try {
                        e.put(this.a, CryptTools.generateKey("AES", 128));
                    } catch (NoSuchAlgorithmException e2) {
                        e2.printStackTrace();
                    }
                }
                if (f.get(this.a) == null) {
                    f.put(this.a, new IvParameterSpec(new SecureRandom().generateSeed(16)));
                }
            }
            a(this.a);
        }
        final File file = this.a;
        d.get(file).post(new Runnable() { // from class: com.getui.gtc.base.log.b.a.2
            /* JADX WARN: Removed duplicated region for block: B:69:0x00f0 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:84:0x00d9 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:97:? A[SYNTHETIC] */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final void run() throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 244
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.base.log.b.a.AnonymousClass2.run():void");
            }
        });
    }
}
