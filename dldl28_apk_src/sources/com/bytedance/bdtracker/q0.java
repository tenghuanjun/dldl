package com.bytedance.bdtracker;

import android.app.Activity;
import com.bytedance.applog.exposure.ViewExposureManager;
import java.util.Iterator;
import java.util.Map;
import java.util.WeakHashMap;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class q0 extends Lambda implements Function1<Activity, Unit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ViewExposureManager f309a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public q0(ViewExposureManager viewExposureManager) {
        super(1);
        this.f309a = viewExposureManager;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(Activity activity) {
        WeakHashMap weakHashMap;
        Activity activity2 = activity;
        if (activity2 != null && (weakHashMap = (WeakHashMap) this.f309a.f187a.get(activity2)) != null) {
            Intrinsics.checkExpressionValueIsNotNull(weakHashMap, "activitiesMap[activity] …erActivityStoppedCallback");
            Iterator it = weakHashMap.entrySet().iterator();
            while (it.hasNext()) {
                ((o0) ((Map.Entry) it.next()).getValue()).b = false;
            }
        }
        return Unit.INSTANCE;
    }
}
