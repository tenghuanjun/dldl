package com.igexin.push.core.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.view.KeyEvent;
import android.view.Menu;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.api.GtcManager;
import com.igexin.b.a.c.a;
import com.igexin.b.a.d.f;
import com.igexin.base.api.GTBase;
import com.igexin.push.core.a.b;
import com.igexin.push.core.e;
import com.igexin.push.core.e.e.AnonymousClass27;
import com.igexin.sdk.IPushCore;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GtcCore implements IPushCore {
    private static final String a = "GtcCore";

    static /* synthetic */ void a(String str) {
        try {
            if (str.equals(e.z)) {
                return;
            }
            com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
            e.z = str;
            com.igexin.b.a.b.e.a().a((f) eVarA.new AnonymousClass27(), false, true);
            if (e.r) {
                b.d().i();
            }
        } catch (Exception e) {
            a.a("GtcCore|init gtc error =  " + e.toString(), new Object[0]);
        }
    }

    private static void b(String str) {
        try {
            if (str.equals(e.z)) {
                return;
            }
            com.igexin.push.core.e.e eVarA = com.igexin.push.core.e.e.a();
            e.z = str;
            com.igexin.b.a.b.e.a().a((f) eVarA.new AnonymousClass27(), false, true);
            if (e.r) {
                b.d().i();
            }
        } catch (Exception e) {
            a.a("GtcCore|init gtc error =  " + e.toString(), new Object[0]);
        }
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityConfigurationChanged(Activity activity, Configuration configuration) {
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean onActivityCreateOptionsMenu(Activity activity, Menu menu) {
        return false;
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityDestroy(Activity activity) {
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean onActivityKeyDown(Activity activity, int i, KeyEvent keyEvent) {
        return false;
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityNewIntent(Activity activity, Intent intent) {
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityPause(Activity activity) {
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityRestart(Activity activity) {
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityResume(Activity activity) {
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityStart(Activity activity, Intent intent) {
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityStop(Activity activity) {
    }

    @Override // com.igexin.sdk.IPushCore
    public IBinder onServiceBind(Intent intent) {
        return null;
    }

    @Override // com.igexin.sdk.IPushCore
    public void onServiceDestroy() {
    }

    @Override // com.igexin.sdk.IPushCore
    public int onServiceStartCommand(Intent intent, int i, int i2) {
        return 2;
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean start(Context context) {
        a.a("GtcCore | ready to start gtc ", new Object[0]);
        final long jCurrentTimeMillis = System.currentTimeMillis();
        GTBase.init(context);
        try {
            String strInitialize = GtcManager.getInstance().initialize(context, new GtcIdCallback.Stub() { // from class: com.igexin.push.core.stub.GtcCore.1
                @Override // com.getui.gtc.api.GtcIdCallback
                public final void onFailure(String str) throws RemoteException {
                    a.a("GtcCore|gtcid callback error ,error info is :".concat(String.valueOf(str)), new Object[0]);
                }

                @Override // com.getui.gtc.api.GtcIdCallback
                public final void onSuccess(final String str) throws RemoteException {
                    System.currentTimeMillis();
                    a.a("GtcCore|gtcid = ".concat(String.valueOf(str)), new Object[0]);
                    if (e.j.get()) {
                        GtcCore.a(str);
                    } else {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.igexin.push.core.stub.GtcCore.1.1
                            @Override // java.lang.Runnable
                            public final void run() {
                                if (e.j.get()) {
                                    GtcCore.a(str);
                                }
                            }
                        }, 300L);
                    }
                }
            });
            System.currentTimeMillis();
            a.a("GtcCore|gtcid = ".concat(String.valueOf(strInitialize)), new Object[0]);
        } catch (Throwable th) {
            a.a("GtcCore|init gtc error =  " + th.toString(), new Object[0]);
        }
        System.currentTimeMillis();
        return true;
    }
}
