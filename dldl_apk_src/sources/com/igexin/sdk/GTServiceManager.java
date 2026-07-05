package com.igexin.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.igexin.push.core.a.b;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class GTServiceManager {

    static class a {
        private static final GTServiceManager a = new GTServiceManager();

        private a() {
        }
    }

    private GTServiceManager() {
    }

    public static GTServiceManager getInstance() {
        return a.a;
    }

    public void onActivityCreate(Activity activity) {
        p pVar = p.a.a;
        try {
            Intent intent = activity.getIntent();
            b.d();
            Intent intent2 = new Intent(activity, (Class<?>) b.a((Context) activity));
            if (intent != null) {
                try {
                    if (intent.hasExtra("action") && intent.hasExtra("isSlave")) {
                        intent2.putExtra("action", intent.getStringExtra("action"));
                        intent2.putExtra("isSlave", intent.getBooleanExtra("isSlave", false));
                        if (intent.hasExtra("op_app")) {
                            intent2.putExtra("op_app", intent.getStringExtra("op_app"));
                        }
                    }
                } catch (Exception unused) {
                }
            }
            pVar.a(activity, intent2);
            com.igexin.b.a.c.a.a("ServiceManager|start PushService from da", new Object[0]);
        } catch (Throwable th) {
            com.igexin.b.a.c.a.a("ServiceManager" + th.toString(), new Object[0]);
        } finally {
            activity.finish();
        }
    }
}
