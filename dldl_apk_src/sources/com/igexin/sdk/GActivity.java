package com.igexin.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.igexin.b.a.c.a;
import com.igexin.push.core.a.b;
import com.igexin.push.core.p;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class GActivity extends Activity {
    public static final String TAG = "com.igexin.sdk.GActivity";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        b.d();
        Intent intent2 = new Intent(this, (Class<?>) b.a((Context) this));
        if (intent != null) {
            try {
                if (intent.hasExtra("action") && intent.hasExtra("isSlave")) {
                    intent2.putExtra("action", intent.getStringExtra("action"));
                    intent2.putExtra("isSlave", intent.getBooleanExtra("isSlave", false));
                    if (intent.hasExtra("op_app")) {
                        intent2.putExtra("op_app", intent.getStringExtra("op_app"));
                    }
                    intent.getStringExtra("action");
                    intent.getBooleanExtra("isSlave", false);
                    a.a("GActivity action = " + intent.getStringExtra("action") + ", isSlave = " + intent.getBooleanExtra("isSlave", false), new Object[0]);
                }
            } catch (Exception e) {
                a.a(TAG + "|put extra exception" + e.toString(), new Object[0]);
            }
        }
        p.a.a.a(this, intent2);
        a.a(TAG + "|start PushService from GActivity", new Object[0]);
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
