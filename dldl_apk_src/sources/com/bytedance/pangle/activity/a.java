package com.bytedance.pangle.activity;

import android.app.Activity;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements View.OnClickListener {
    private final Activity a;
    private final String b;
    private final int c;
    private Method d;

    public a(Activity activity, int i, String str) {
        this.a = activity;
        this.b = str;
        this.c = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (this.d == null) {
            try {
                Method method = this.a.getClass().getMethod(this.b, View.class);
                if (method != null) {
                    this.d = method;
                }
            } catch (NoSuchMethodException unused) {
            }
            throw new IllegalStateException("Could not find method " + this.b + "(View) in a parent or ancestor Context for android:onClick attribute defined on view " + this.c);
        }
        try {
            this.d.invoke(this.a, view);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException("Could not execute non-public method for android:onClick", e);
        } catch (InvocationTargetException e2) {
            throw new IllegalStateException("Could not execute method for android:onClick", e2);
        }
    }
}
