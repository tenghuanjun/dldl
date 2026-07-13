package com.bytedance.bdtracker;

import com.bytedance.applog.exposure.ViewExposureData;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final class o0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ViewExposureData f300a;
    public boolean b;

    public /* synthetic */ o0(ViewExposureData data, boolean z, int i) {
        z = (i & 2) != 0 ? false : z;
        Intrinsics.checkParameterIsNotNull(data, "data");
        this.f300a = data;
        this.b = z;
    }

    public final ViewExposureData a() {
        return this.f300a;
    }
}
