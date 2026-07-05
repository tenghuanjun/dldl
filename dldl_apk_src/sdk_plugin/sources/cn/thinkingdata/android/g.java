package cn.thinkingdata.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class g extends BroadcastReceiver {
    private a a;

    public interface a {
        void a();
    }

    g(a aVar) {
        a(aVar);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        a aVar;
        if (!intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE") || (aVar = this.a) == null) {
            return;
        }
        aVar.a();
    }
}
