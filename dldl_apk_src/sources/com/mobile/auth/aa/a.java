package com.mobile.auth.aa;

import android.net.Network;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class a {
    private ExecutorService a = Executors.newFixedThreadPool(5);

    /* JADX INFO: renamed from: com.mobile.auth.aa.a$a, reason: collision with other inner class name */
    public interface InterfaceC0062a {
        void a(String str);
    }

    public void a(final String str, final HashMap<String, String> map, final Network network, final InterfaceC0062a interfaceC0062a) {
        try {
            this.a.submit(new Runnable() { // from class: com.mobile.auth.aa.a.1
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        try {
                            interfaceC0062a.a(new d().a(str, map, network));
                        } catch (Exception e) {
                            com.mobile.auth.ab.d.b(e.getMessage());
                        }
                    } catch (Throwable th) {
                        try {
                            com.mobile.auth.gatewayauth.a.a(th);
                        } catch (Throwable th2) {
                            com.mobile.auth.gatewayauth.a.a(th2);
                        }
                    }
                }
            });
        } catch (Throwable th) {
            try {
                com.mobile.auth.gatewayauth.a.a(th);
            } catch (Throwable th2) {
                com.mobile.auth.gatewayauth.a.a(th2);
            }
        }
    }
}
