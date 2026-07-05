package com.getui.gtc.d;

import android.os.Handler;
import android.os.HandlerThread;
import com.getui.gtc.BuildConfig;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.api.SdkInfo;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.c.b;
import com.getui.gtc.entity.a;
import com.getui.gtc.g.c;
import com.getui.gtc.i.d.a;
import com.getui.gtc.server.ServerManager;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class a {
    final c a;
    private final Handler b;

    /* JADX INFO: renamed from: com.getui.gtc.d.a$a, reason: collision with other inner class name */
    public static class C0036a {
        private static final a a = new a(0);
    }

    private a() {
        HandlerThread handlerThread = new HandlerThread("Gtc HandlerThread");
        handlerThread.start();
        this.b = new Handler(handlerThread.getLooper());
        this.a = c.a.a;
        b.a();
        com.getui.gtc.i.d.a unused = a.C0048a.a;
        com.getui.gtc.a.a.a();
        this.b.post(new Runnable() { // from class: com.getui.gtc.d.a.3
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    SdkInfo.Builder builderPsUrl = new SdkInfo.Builder().moduleName("GTC").version(BuildConfig.VERSION_NAME).appid(b.a).cid(b.d).psUrl(String.format("%s/api.php?format=json&t=1", ServerManager.getServer("gtc.cs")));
                    try {
                        Class.forName("com.igexin.push.extension.distribution.gbd.stub.PushExtension");
                        builderPsUrl.addStub("com.igexin.push.extension.distribution.gbd.stub.PushExtension", false);
                        Class.forName("com.igexin.push.extension.distribution.gws.stub.PushExtension");
                        builderPsUrl.addStub("com.igexin.push.extension.distribution.gws.stub.PushExtension", false);
                    } catch (ClassNotFoundException unused2) {
                    }
                    a.this.a.a(builderPsUrl.build());
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.a(th);
                }
            }
        });
    }

    /* synthetic */ a(byte b) {
        this();
    }

    public final String a(final GtcIdCallback gtcIdCallback) {
        this.b.post(new Runnable() { // from class: com.getui.gtc.d.a.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    b.a(gtcIdCallback);
                } catch (Exception e) {
                    com.getui.gtc.i.c.a.b(e);
                }
            }
        });
        return b.d;
    }

    public final void a(final SdkInfo sdkInfo) {
        this.b.post(new Runnable() { // from class: com.getui.gtc.d.a.2
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    a.this.a.a(sdkInfo);
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.a(th);
                }
            }
        });
    }

    public final void a(final int[] iArr) {
        this.b.post(new Runnable() { // from class: com.getui.gtc.d.a.4
            @Override // java.lang.Runnable
            public final void run() {
                com.getui.gtc.entity.a aVarA;
                a.C0046a c0046aB;
                try {
                    if (iArr == null) {
                        return;
                    }
                    com.getui.gtc.i.c.a.a("remove ext id: " + Arrays.toString(iArr));
                    for (int i : iArr) {
                        Map<String, Map<String, String>> mapA = com.getui.gtc.dyc.b.a.a(GtcProvider.context());
                        if (mapA == null) {
                            return;
                        }
                        Iterator<Map.Entry<String, Map<String, String>>> it = mapA.entrySet().iterator();
                        while (true) {
                            if (!it.hasNext()) {
                                break;
                            }
                            Map.Entry<String, Map<String, String>> next = it.next();
                            Map<String, String> value = next.getValue();
                            if (value != null && (aVarA = com.getui.gtc.entity.a.a(value)) != null && (c0046aB = aVarA.b(i)) != null) {
                                com.getui.gtc.i.c.a.a("found ext id: " + i + ", remove it");
                                a.this.a.a(c0046aB.c);
                                aVarA.a.remove(i);
                                value.put("ext_infos", aVarA.a());
                                com.getui.gtc.dyc.b.a.a(GtcProvider.context(), next.getKey(), value);
                                break;
                            }
                        }
                    }
                } catch (Throwable th) {
                    com.getui.gtc.i.c.a.a(th);
                }
            }
        });
    }
}
