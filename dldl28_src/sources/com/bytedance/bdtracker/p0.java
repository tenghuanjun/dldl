package com.bytedance.bdtracker;

import android.app.Activity;
import com.bytedance.applog.exposure.ViewExposureManager;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class p0 extends Lambda implements Function1<Activity, Unit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewExposureManager f304a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public p0(ViewExposureManager viewExposureManager) {
        super(1);
        this.f304a = viewExposureManager;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Activity activity) {
        Activity it = activity;
        Intrinsics.checkParameterIsNotNull(it, "it");
        this.f304a.e.removeCallbacks(this.f304a.f);
        this.f304a.e.postDelayed(this.f304a.f, 100L);
        return Unit.INSTANCE;
    }
}
