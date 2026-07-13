package com.bytedance.bdtracker;

import android.content.Context;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class f1 extends d1 {
    public final d e;

    public f1(d dVar) {
        super(true, false);
        this.e = dVar;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "business_conversion_id";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) {
        try {
            a("com.bytedance.applog.convert.ClickIdProvider", jSONObject);
        } catch (Throwable th) {
            this.e.D.debug("ClickId find error", th);
        }
        try {
            a("com.bytedance.applog.convert.IPIDProvider", jSONObject);
        } catch (Throwable th2) {
            this.e.D.debug("IPID find error", th2);
        }
        return true;
    }

    public final void a(String str, JSONObject jSONObject) {
        Class<?> clsB = n0.b(str);
        if (clsB == null) {
            this.e.D.debug("No " + str + " class, get id error", new Object[0]);
            return;
        }
        try {
            Method declaredMethod = clsB.getDeclaredMethod("getIdAndSetIntoJson", JSONObject.class, Context.class);
            declaredMethod.setAccessible(true);
            declaredMethod.invoke(clsB.newInstance(), jSONObject, this.e.n);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        } catch (InvocationTargetException e4) {
            e4.printStackTrace();
        }
    }
}
