package com.igexin.push.core.stub;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.Menu;
import com.getui.gtc.api.GtcManager;
import com.igexin.push.GtPushInterface;
import com.igexin.push.core.c;
import com.igexin.push.core.d;
import com.igexin.push.core.e;
import com.igexin.push.core.i.a;
import com.igexin.push.core.i.b;
import com.igexin.push.core.p;
import com.igexin.sdk.IPushCore;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PushCore implements IPushCore {
    private d a;
    private Map<Activity, a> b = new HashMap();
    private GtPushInterface.Stub c = new GtPushInterface.Stub() { // from class: com.igexin.push.core.stub.PushCore.1
        @Override // com.igexin.push.GtPushInterface
        public final String getVersion() {
            return "3.2.4.0";
        }

        @Override // com.igexin.push.GtPushInterface
        public final boolean loadSdk(Bundle bundle) {
            try {
                String string = bundle.getString("cn");
                GtcManager.getInstance().loadBundle(p.b, bundle);
                if (!TextUtils.isEmpty(string)) {
                    e.l.put(string.substring(string.indexOf("distribution") + 13, string.indexOf("stub") - 1), GtcManager.getInstance().getClassLoader(bundle));
                }
                return true;
            } catch (Throwable unused) {
                return false;
            }
        }
    };

    @Override // com.igexin.sdk.IPushCore
    public void onActivityConfigurationChanged(Activity activity, Configuration configuration) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean onActivityCreateOptionsMenu(Activity activity, Menu menu) {
        a aVar = this.b.get(activity);
        return aVar != null && aVar.j();
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityDestroy(Activity activity) {
        a aVar = this.b.get(activity);
        if (aVar != null) {
            this.b.remove(activity);
            b.a().a(aVar);
        }
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean onActivityKeyDown(Activity activity, int i, KeyEvent keyEvent) {
        a aVar = this.b.get(activity);
        return aVar != null && aVar.l();
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityNewIntent(Activity activity, Intent intent) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityPause(Activity activity) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityRestart(Activity activity) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityResume(Activity activity) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityStart(Activity activity, Intent intent) {
        if (activity == null || intent == null || !intent.hasExtra("activityid")) {
            return;
        }
        a aVarA = b.a().a(Long.valueOf(intent.getLongExtra("activityid", 0L)));
        if (aVarA == null) {
            activity.finish();
        } else {
            aVarA.a(activity);
            this.b.put(activity, aVarA);
        }
    }

    @Override // com.igexin.sdk.IPushCore
    public void onActivityStop(Activity activity) {
        this.b.get(activity);
    }

    @Override // com.igexin.sdk.IPushCore
    public IBinder onServiceBind(Intent intent) {
        return this.c;
    }

    @Override // com.igexin.sdk.IPushCore
    public void onServiceDestroy() {
    }

    @Override // com.igexin.sdk.IPushCore
    public int onServiceStartCommand(Intent intent, int i, int i2) {
        if (this.a == null) {
            return 2;
        }
        Message messageObtain = Message.obtain();
        messageObtain.what = com.igexin.push.core.b.P;
        messageObtain.obj = intent;
        this.a.a(messageObtain);
        return 2;
    }

    @Override // com.igexin.sdk.IPushCore
    public boolean start(Context context) {
        com.igexin.b.a.c.a.d.a().a("PushCore started");
        this.a = d.a.a;
        d dVar = this.a;
        dVar.a = context.getApplicationContext();
        if (dVar.b != null && dVar.b.isAlive()) {
            com.igexin.b.a.c.a.a("CoreLogic|coreThread is alive +++++", new Object[0]);
        } else if (!dVar.k.getAndSet(true)) {
            com.igexin.b.a.c.a.a("CoreLogic|start coreThread +++++", new Object[0]);
            dVar.b.start();
            dVar.c = new c(dVar.b.getLooper());
            dVar.f = new com.igexin.b.a.b.a.a.c(dVar.b.getLooper());
        }
        return true;
    }
}
