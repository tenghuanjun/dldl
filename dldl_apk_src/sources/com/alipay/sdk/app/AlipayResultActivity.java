package com.alipay.sdk.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import com.alipay.sdk.util.l;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class AlipayResultActivity extends Activity {
    public static final ConcurrentHashMap<String, a> a = new ConcurrentHashMap<>();

    public interface a {
        void a(int i, String str, String str2);
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPReturned", "");
            Intent intent = getIntent();
            try {
                String stringExtra = intent.getStringExtra("session");
                Bundle bundleExtra = intent.getBundleExtra("result");
                String stringExtra2 = intent.getStringExtra("scene");
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPSession", stringExtra);
                if (TextUtils.equals("mqpSchemePay", stringExtra2)) {
                    a(stringExtra, bundleExtra);
                    return;
                }
                if ((TextUtils.isEmpty(stringExtra) || bundleExtra == null) && intent.getData() != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(new String(Base64.decode(intent.getData().getQuery(), 2), "UTF-8"));
                        JSONObject jSONObject2 = jSONObject.getJSONObject("result");
                        stringExtra = jSONObject.getString("session");
                        com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPUriSession", stringExtra);
                        Bundle bundle2 = new Bundle();
                        try {
                            Iterator<String> itKeys = jSONObject2.keys();
                            while (itKeys.hasNext()) {
                                String next = itKeys.next();
                                bundle2.putString(next, jSONObject2.getString(next));
                            }
                            bundleExtra = bundle2;
                        } catch (Throwable th) {
                            th = th;
                            bundleExtra = bundle2;
                            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPResEx", th);
                            com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.Z, th);
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                if (TextUtils.isEmpty(stringExtra) || bundleExtra == null) {
                    com.alipay.sdk.app.statistic.a.b(this, "");
                    finish();
                    return;
                }
                try {
                    a(stringExtra, bundleExtra);
                    com.alipay.sdk.app.statistic.a.b(this, "");
                    finish();
                } catch (Throwable th3) {
                    com.alipay.sdk.app.statistic.a.b(this, "");
                    finish();
                    throw th3;
                }
            } catch (Throwable th4) {
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, "BSPSerError", th4);
                com.alipay.sdk.app.statistic.a.a(com.alipay.sdk.app.statistic.c.b, com.alipay.sdk.app.statistic.c.Y, th4);
                finish();
            }
        } catch (Throwable unused) {
            finish();
        }
    }

    private void a(String str, Bundle bundle) {
        a aVarRemove = a.remove(str);
        if (aVarRemove == null) {
            return;
        }
        try {
            aVarRemove.a(bundle.getInt("endCode"), bundle.getString(l.b), bundle.getString("result"));
        } finally {
            finish();
        }
    }
}
