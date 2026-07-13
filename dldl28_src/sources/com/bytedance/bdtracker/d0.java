package com.bytedance.bdtracker;

import com.bytedance.applog.InitConfig;
import com.bytedance.applog.util.HardwareUtils;
import java.util.Collections;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c0 f236a;

    public d0(c0 c0Var) {
        this.f236a = c0Var;
    }

    public void a(j3 j3Var) {
        try {
            JSONObject jSONObject = j3Var.o != null ? j3Var.o : new JSONObject();
            InitConfig initConfig = this.f236a.e.c;
            if (initConfig != null && initConfig.isScreenOrientationEnabled()) {
                jSONObject.put("$screen_orientation", HardwareUtils.getScreenOrientation(this.f236a.d.n) == 2 ? "landscape" : "portrait");
            }
            r rVar = this.f236a.d.B;
            if (rVar != null) {
                jSONObject.put("$longitude", rVar.f312a);
                jSONObject.put("$latitude", rVar.b);
                jSONObject.put("$geo_coordinate_system", rVar.c);
            }
            if (jSONObject.length() > 0) {
                j3Var.o = jSONObject;
            }
        } catch (Throwable th) {
            this.f236a.d.D.error(4, Collections.singletonList("LifeHook"), "Do beforeEventSave failed", th, new Object[0]);
        }
    }
}
