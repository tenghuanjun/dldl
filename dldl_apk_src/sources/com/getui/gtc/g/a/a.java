package com.getui.gtc.g.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Pair;
import com.getui.gtc.i.c.a;
import com.igexin.push.GtPushInterface;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class a {
    private static PackageInfo c;
    Handler a;
    public GtPushInterface b;

    public a(final b bVar) {
        final HandlerThread handlerThread = new HandlerThread("Plugin Handler Thread");
        handlerThread.start();
        this.a = new Handler(handlerThread.getLooper()) { // from class: com.getui.gtc.g.a.a.1
            @Override // android.os.Handler
            public final void handleMessage(Message message) {
                try {
                } catch (Exception e) {
                    com.getui.gtc.i.c.a.a(e);
                    b bVar2 = bVar;
                    if (bVar2 != null) {
                        bVar2.a(false);
                    }
                }
                if (message.what < 0) {
                    if (bVar != null) {
                        bVar.a(false);
                    }
                } else {
                    boolean zLoadSdk = a.this.b.loadSdk(message.getData());
                    if (bVar != null) {
                        bVar.a(zLoadSdk);
                    }
                    handlerThread.quit();
                    a.this.a = null;
                }
            }
        };
    }

    public static Pair<ServiceInfo, Class> a(Context context, Class cls) {
        boolean z;
        try {
            if (c == null) {
                c = context.getPackageManager().getPackageInfo(context.getPackageName(), 4);
            }
            ServiceInfo[] serviceInfoArr = c.services;
            if (serviceInfoArr != null && serviceInfoArr.length > 0) {
                int length = serviceInfoArr.length;
                for (int i = 0; i < length; i++) {
                    ServiceInfo serviceInfo = serviceInfoArr[i];
                    try {
                        Class<?> cls2 = Class.forName(serviceInfo.name);
                        if (cls2 != cls) {
                            Class<?> superclass = cls2;
                            for (int i2 = 5; superclass != null && cls != null && i2 > 0; i2--) {
                                if (superclass == cls) {
                                    z = true;
                                    break;
                                }
                                if (superclass.getSuperclass() == null) {
                                    break;
                                }
                                superclass = superclass.getSuperclass();
                            }
                            z = false;
                            if (z) {
                                return Pair.create(serviceInfo, cls2);
                            }
                            continue;
                        }
                    } catch (Throwable unused) {
                    }
                }
            }
        } catch (Throwable th) {
            a.C0047a.a.a.e(" findGtImplClassInManifest error", th);
        }
        return Pair.create(null, null);
    }

    public final void a(String str, String str2, String str3, String str4, String str5) {
        Bundle bundle = new Bundle();
        bundle.putString("dp", str);
        bundle.putString("od", str2);
        bundle.putString("cn", str3);
        bundle.putString("ad", str4);
        bundle.putString("gd", str5);
        Message messageObtain = Message.obtain();
        messageObtain.setData(bundle);
        messageObtain.what = 0;
        Handler handler = this.a;
        if (handler != null) {
            messageObtain.setTarget(handler);
            messageObtain.sendToTarget();
        }
    }
}
