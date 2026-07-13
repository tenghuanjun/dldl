package com.bytedance.bdtracker;

import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import org.json.JSONException;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: loaded from: classes2.dex */
public final class n extends o {
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public boolean h;
    public boolean i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String q;
    public String r;
    public String s;

    @Override // com.bytedance.bdtracker.o
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aid", this.b);
        jSONObject.put(MonitorConstants.KEY_DEVICE_ID, this.c);
        jSONObject.put("bd_did", this.d);
        jSONObject.put("install_id", this.e);
        jSONObject.put("os", this.f);
        jSONObject.put("caid", this.g);
        jSONObject.put("androidid", this.l);
        jSONObject.put("imei", this.m);
        jSONObject.put("oaid", this.n);
        jSONObject.put("google_aid", this.o);
        jSONObject.put(IjkMediaPlayer.OnNativeInvokeListener.ARG_IP, this.p);
        jSONObject.put("ua", this.q);
        jSONObject.put("device_model", this.r);
        jSONObject.put("os_version", this.s);
        jSONObject.put("is_new_user", this.h);
        jSONObject.put("exist_app_cache", this.i);
        jSONObject.put("app_version", this.j);
        jSONObject.put("channel", this.k);
        return jSONObject;
    }

    @Override // com.bytedance.bdtracker.o
    public void a(JSONObject jSONObject) {
    }
}
