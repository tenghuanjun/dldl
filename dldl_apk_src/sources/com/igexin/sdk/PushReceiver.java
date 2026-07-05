package com.igexin.sdk;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.igexin.b.a.c.a;
import com.igexin.push.core.a.b;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class PushReceiver extends BroadcastReceiver {
    private static final String TAG = "com.igexin.sdk.PushReceiver";

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent == null || intent.getAction() == null) {
            return;
        }
        try {
            String action = intent.getAction();
            if (PushConsts.ACTION_BROADCAST_NETWORK_CHANGE.equals(action) || PushConsts.ACTION_BROADCAST_USER_PRESENT.equals(action)) {
                Context applicationContext = context.getApplicationContext();
                b.d();
                Intent intent2 = new Intent(applicationContext, (Class<?>) b.a(context));
                intent2.putExtra("action", action);
                p.a.a.a(context, intent2);
            }
        } catch (Throwable th) {
            a.a(TAG + "|" + th.toString(), new Object[0]);
        }
    }
}
