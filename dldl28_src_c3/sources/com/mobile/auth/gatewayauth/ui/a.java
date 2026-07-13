package com.mobile.auth.gatewayauth.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    private View a;
    private WeakReference<Context> b;
    private AbstractPnsViewDelegate c;
    private boolean d;

    public a(Context context, int i, AbstractPnsViewDelegate abstractPnsViewDelegate) {
        this(LayoutInflater.from(context).inflate(i, (ViewGroup) new FrameLayout(context), false), abstractPnsViewDelegate);
    }

    public a(View view, AbstractPnsViewDelegate abstractPnsViewDelegate) {
        this.d = true;
        this.c = abstractPnsViewDelegate;
        this.a = view;
        this.b = new WeakReference<>(view.getContext());
        AbstractPnsViewDelegate abstractPnsViewDelegate2 = this.c;
        if (abstractPnsViewDelegate2 != null) {
            abstractPnsViewDelegate2.setPnsView(this);
            this.c.onViewCreated(this.a);
        }
    }

    public Context a() {
        Context context;
        try {
            WeakReference<Context> weakReference = this.b;
            return (weakReference == null || (context = weakReference.get()) == null) ? this.a.getContext() : context;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public View a(int i) {
        try {
            return this.a.findViewById(i);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public View b() {
        try {
            return this.a;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }
}
