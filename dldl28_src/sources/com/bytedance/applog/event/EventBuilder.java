package com.bytedance.applog.event;

import com.bytedance.applog.log.IAppLogLogger;
import com.bytedance.bdtracker.a;
import com.bytedance.bdtracker.d;
import com.bytedance.bdtracker.q3;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class EventBuilder {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final d f184a;
    public String b;
    public String c;
    public JSONObject d;

    public EventBuilder(d dVar) {
        this.f184a = dVar;
    }

    public EventBuilder addParam(String str, Object obj) {
        if (this.d == null) {
            this.d = new JSONObject();
        }
        try {
            this.d.put(str, obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    public q3 build() {
        String str = this.f184a.m;
        String str2 = this.b;
        JSONObject jSONObject = this.d;
        q3 q3Var = new q3(str, str2, false, jSONObject != null ? jSONObject.toString() : null, 0);
        q3Var.j = this.c;
        this.f184a.D.debug(4, "EventBuilder build: {}", q3Var);
        return q3Var;
    }

    public EventBuilder setAbSdkVersion(String str) {
        this.c = str;
        return this;
    }

    public EventBuilder setEvent(String str) {
        this.b = str;
        return this;
    }

    public void track() {
        q3 q3VarBuild = build();
        IAppLogLogger iAppLogLogger = this.f184a.D;
        StringBuilder sbA = a.a("EventBuilder track: ");
        sbA.append(this.b);
        iAppLogLogger.debug(4, sbA.toString(), new Object[0]);
        this.f184a.receive(q3VarBuild);
    }
}
