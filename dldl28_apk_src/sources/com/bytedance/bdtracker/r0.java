package com.bytedance.bdtracker;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0019\u001a\u0004\u0018\u00010\tJ\b\u0010\u001a\u001a\u00020\fH\u0002J\u001a\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\t2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0016J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\u0010\u0010 \u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\u0010\u0010!\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\u0018\u0010\"\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u001eH\u0016J\u0010\u0010$\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\u0010\u0010%\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\u001c\u0010&\u001a\u00020\f2\u0014\u0010'\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\f0\u000bJ\u001a\u0010(\u001a\u00020\f2\u0012\u0010'\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f0\u000bJ\u0006\u0010)\u001a\u00020\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u0012\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0016\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bj\u0004\u0018\u0001`\u0018X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/bytedance/applog/exposure/ViewTreeChangeObserver;", "Landroid/app/Application$ActivityLifecycleCallbacks;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "getApplication", "()Landroid/app/Application;", "currentActivityRef", "Ljava/lang/ref/WeakReference;", "Landroid/app/Activity;", "onActivityStoppedCallback", "Lkotlin/Function1;", "", "onDrawListener", "Landroid/view/ViewTreeObserver$OnDrawListener;", "onGlobalFocusChangeListener", "Landroid/view/ViewTreeObserver$OnGlobalFocusChangeListener;", "onGlobalLayoutListener", "Landroid/view/ViewTreeObserver$OnGlobalLayoutListener;", "onScrollChangedListener", "Landroid/view/ViewTreeObserver$OnScrollChangedListener;", "onWindowFocusChangeListener", "Landroid/view/ViewTreeObserver$OnWindowFocusChangeListener;", "viewTreeChangeCallback", "Lcom/bytedance/applog/exposure/ViewTreeChangeCallback;", "getCurrentActivity", "invokeCallback", "onActivityCreated", "activity", "savedInstanceState", "Landroid/os/Bundle;", "onActivityDestroyed", "onActivityPaused", "onActivityResumed", "onActivitySaveInstanceState", "outState", "onActivityStarted", "onActivityStopped", "registerActivityStoppedCallback", "callback", "subscribe", "unsubscribe", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
public final class r0 implements Application.ActivityLifecycleCallbacks {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public WeakReference<Activity> f313a;
    public ViewTreeObserver.OnDrawListener b;
    public ViewTreeObserver.OnGlobalLayoutListener c;
    public ViewTreeObserver.OnGlobalFocusChangeListener d;
    public ViewTreeObserver.OnScrollChangedListener e;
    public ViewTreeObserver.OnWindowFocusChangeListener f;
    public Function1<? super Activity, Unit> g;
    public Function1<? super Activity, Unit> h;
    public final Application i;

    public static final class a implements ViewTreeObserver.OnDrawListener {
        public a() {
        }

        @Override // android.view.ViewTreeObserver.OnDrawListener
        public final void onDraw() {
            r0.a(r0.this);
        }
    }

    public static final class b implements ViewTreeObserver.OnGlobalFocusChangeListener {
        public b() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
        public final void onGlobalFocusChanged(View view, View view2) {
            r0.a(r0.this);
        }
    }

    public static final class c implements ViewTreeObserver.OnGlobalLayoutListener {
        public c() {
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public final void onGlobalLayout() {
            r0.a(r0.this);
        }
    }

    public static final class d implements ViewTreeObserver.OnScrollChangedListener {
        public d() {
        }

        @Override // android.view.ViewTreeObserver.OnScrollChangedListener
        public final void onScrollChanged() {
            r0.a(r0.this);
        }
    }

    public static final class e implements ViewTreeObserver.OnWindowFocusChangeListener {
        public e() {
        }

        @Override // android.view.ViewTreeObserver.OnWindowFocusChangeListener
        public final void onWindowFocusChanged(boolean z) {
            r0.a(r0.this);
        }
    }

    public r0(Application application) {
        Intrinsics.checkParameterIsNotNull(application, "application");
        this.i = application;
        this.f313a = new WeakReference<>(null);
        this.b = new a();
        this.c = new c();
        this.d = new b();
        this.e = new d();
        this.f = new e();
    }

    public static final /* synthetic */ void a(r0 r0Var) {
        Activity activity = r0Var.f313a.get();
        if (activity != null) {
            Intrinsics.checkExpressionValueIsNotNull(activity, "currentActivityRef.get() ?: return");
            Function1<? super Activity, Unit> function1 = r0Var.g;
            if (function1 != null) {
                function1.invoke(activity);
            }
        }
    }

    public final void a(Function1<? super Activity, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        this.h = callback;
    }

    public final void b(Function1<? super Activity, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        if (this.g == null) {
            this.g = callback;
            this.i.registerActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "activity.window.decorView");
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.removeOnGlobalFocusChangeListener(this.d);
        viewTreeObserver.removeOnScrollChangedListener(this.e);
        viewTreeObserver.removeOnDrawListener(this.b);
        viewTreeObserver.removeOnGlobalLayoutListener(this.c);
        viewTreeObserver.removeOnWindowFocusChangeListener(this.f);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        this.f313a = new WeakReference<>(activity);
        Window window = activity.getWindow();
        Intrinsics.checkExpressionValueIsNotNull(window, "activity.window");
        View decorView = window.getDecorView();
        Intrinsics.checkExpressionValueIsNotNull(decorView, "activity.window.decorView");
        ViewTreeObserver viewTreeObserver = decorView.getViewTreeObserver();
        viewTreeObserver.addOnGlobalFocusChangeListener(this.d);
        viewTreeObserver.addOnScrollChangedListener(this.e);
        viewTreeObserver.addOnDrawListener(this.b);
        viewTreeObserver.addOnGlobalLayoutListener(this.c);
        viewTreeObserver.addOnWindowFocusChangeListener(this.f);
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Intrinsics.checkParameterIsNotNull(outState, "outState");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
        Intrinsics.checkParameterIsNotNull(activity, "activity");
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
        Function1<? super Activity, Unit> function1;
        Intrinsics.checkParameterIsNotNull(activity, "activity");
        Activity activity2 = this.f313a.get();
        if (activity2 == null || Intrinsics.areEqual(activity2, activity) || (function1 = this.h) == null) {
            return;
        }
        function1.invoke(activity);
    }
}
