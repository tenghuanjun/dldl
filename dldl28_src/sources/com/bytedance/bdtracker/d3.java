package com.bytedance.bdtracker;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.bytedance.applog.profile.UserProfileCallback;
import com.lzy.okgo.model.HttpHeaders;
import java.util.HashMap;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d3 implements Runnable {
    public static final Handler g = new Handler(Looper.getMainLooper());

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f239a;
    public final String b;
    public final JSONObject c;
    public final UserProfileCallback d;
    public final Context e;
    public final d f;

    public d3(d dVar, String str, String str2, JSONObject jSONObject, UserProfileCallback userProfileCallback, Context context) {
        this.f = dVar;
        this.f239a = str;
        this.b = str2;
        this.c = jSONObject;
        this.d = userProfileCallback;
        this.e = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (!i4.b(this.e)) {
                g.post(new b3(this, 0));
                return;
            }
            HashMap map = new HashMap();
            map.put(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/json");
            map.put("X-APIKEY", this.b);
            this.f.getNetClient().execute((byte) 1, this.f239a, this.c, map, (byte) 0, false, 60000);
            g.post(new c3(this));
        } catch (Throwable th) {
            this.f.D.error(9, "Report profile failed", th, new Object[0]);
            g.post(new b3(this, 1));
        }
    }
}
