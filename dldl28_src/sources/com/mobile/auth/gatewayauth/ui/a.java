package com.mobile.auth.gatewayauth.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes3.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private View f702a;
    private WeakReference<Context> b;
    private AbstractPnsViewDelegate c;
    private boolean d;

    public a(Context context, int i, AbstractPnsViewDelegate abstractPnsViewDelegate) {
        this(LayoutInflater.from(context).inflate(i, (ViewGroup) new FrameLayout(context), false), abstractPnsViewDelegate);
    }

    public a(View view, AbstractPnsViewDelegate abstractPnsViewDelegate) {
        this.d = true;
        this.c = abstractPnsViewDelegate;
        this.f702a = view;
        this.b = new WeakReference<>(view.getContext());
        AbstractPnsViewDelegate abstractPnsViewDelegate2 = this.c;
        if (abstractPnsViewDelegate2 != null) {
            abstractPnsViewDelegate2.setPnsView(this);
            this.c.onViewCreated(this.f702a);
        }
    }

    public Context a() {
        Context context;
        try {
            WeakReference<Context> weakReference = this.b;
            return (weakReference == null || (context = weakReference.get()) == null) ? this.f702a.getContext() : context;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public View a(int i) {
        try {
            return this.f702a.findViewById(i);
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }

    public View b() {
        try {
            return this.f702a;
        } catch (Throwable th) {
            ExceptionProcessor.processException(th);
            return null;
        }
    }
}
