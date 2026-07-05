package com.igexin.push.core;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import com.igexin.push.core.d;
import com.igexin.sdk.PushConsts;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class j extends BroadcastReceiver {
    public static final String a = "GTReceiver";
    private static volatile j b;

    private j() {
    }

    public static j a() {
        if (b == null) {
            synchronized (j.class) {
                if (b == null) {
                    b = new j();
                }
            }
        }
        return b;
    }

    private static void a(Intent intent) {
        try {
            intent.getAction();
            intent.getComponent();
            com.igexin.b.a.c.a.a("----------------------------------------------------------------------------------", new Object[0]);
            com.igexin.b.a.c.a.a("GTReceiver|action = " + intent.getAction() + ", component = " + intent.getComponent(), new Object[0]);
            Bundle extras = intent.getExtras();
            if (extras == null) {
                com.igexin.b.a.c.a.a("GTReceiver|no extras", new Object[0]);
                return;
            }
            for (String str : extras.keySet()) {
                extras.get(str);
                com.igexin.b.a.c.a.a("GTReceiver|key [" + str + "]: " + extras.get(str), new Object[0]);
            }
        } catch (Exception unused) {
        }
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        if (intent != null && intent.getAction() != null && intent.getAction().equals(PushConsts.ACTION_BROADCAST_NETWORK_CHANGE)) {
            try {
                intent.getAction();
                intent.getComponent();
                com.igexin.b.a.c.a.a("----------------------------------------------------------------------------------", new Object[0]);
                com.igexin.b.a.c.a.a("GTReceiver|action = " + intent.getAction() + ", component = " + intent.getComponent(), new Object[0]);
                Bundle extras = intent.getExtras();
                if (extras != null) {
                    for (String str : extras.keySet()) {
                        extras.get(str);
                        com.igexin.b.a.c.a.a("GTReceiver|key [" + str + "]: " + extras.get(str), new Object[0]);
                    }
                } else {
                    com.igexin.b.a.c.a.a("GTReceiver|no extras", new Object[0]);
                }
            } catch (Exception unused) {
            }
        }
        StringBuilder sb = new StringBuilder("GTReceiver InternalPublicReceiver:");
        sb.append(intent != null ? intent.getAction() : "null");
        com.igexin.b.a.c.a.a(sb.toString(), new Object[0]);
        Message messageObtain = Message.obtain();
        messageObtain.what = b.Q;
        messageObtain.obj = intent;
        d.a.a.a(messageObtain);
    }
}
