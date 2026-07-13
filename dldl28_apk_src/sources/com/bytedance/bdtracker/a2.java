package com.bytedance.bdtracker;

import android.content.Context;
import android.os.Looper;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.aggregation.IAggregation;
import com.bytedance.applog.aggregation.IAggregationFlushCallback;
import com.bytedance.applog.aggregation.IMetricsTracker;
import com.bytedance.applog.aggregation.Metrics;
import com.bytedance.applog.aggregation.MetricsSQLiteCache;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;

/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u0012\u001a\u00020\u00132\u0018\u0010\u0014\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u0016\u0012\u0004\u0012\u00020\u00130\u0015J\u000e\u0010\u0018\u001a\u00020\u000e2\u0006\u0010\u0019\u001a\u00020\u001aR\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR'\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\n\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/bytedance/applog/monitor/MetricsTrackerHelper;", "", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "aggregation", "Lcom/bytedance/applog/aggregation/IAggregation;", "getAggregation", "()Lcom/bytedance/applog/aggregation/IAggregation;", "aggregation$delegate", "Lkotlin/Lazy;", "trackMap", "", "", "Lcom/bytedance/applog/aggregation/IMetricsTracker;", "getTrackMap", "()Ljava/util/Map;", "trackMap$delegate", "flush", "", "callback", "Lkotlin/Function1;", "", "Lcom/bytedance/applog/aggregation/Metrics;", "metricsTracker", "data", "Lcom/bytedance/applog/monitor/model/BaseTrace;", "agent_liteChinaRelease"}, k = 1, mv = {1, 1, 16})
public final class a2 {
    public static final /* synthetic */ KProperty[] c = {Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(a2.class), "aggregation", "getAggregation()Lcom/bytedance/applog/aggregation/IAggregation;")), Reflection.property1(new PropertyReference1Impl(Reflection.getOrCreateKotlinClass(a2.class), "trackMap", "getTrackMap()Ljava/util/Map;"))};

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Lazy f208a;
    public final Lazy b;

    public static final class a extends Lambda implements Function0<IAggregation> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Looper f209a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(Looper looper) {
            super(0);
            this.f209a = looper;
        }

        @Override // kotlin.jvm.functions.Function0
        public IAggregation invoke() {
            IAggregation.Companion companion = IAggregation.INSTANCE;
            Context context = AppLog.getContext();
            Intrinsics.checkExpressionValueIsNotNull(context, "AppLog.getContext()");
            return companion.newInstance(new MetricsSQLiteCache(context, "applog-aggregation"), this.f209a);
        }
    }

    public static final class b implements IAggregationFlushCallback {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Function1 f210a;

        public b(Function1 function1) {
            this.f210a = function1;
        }

        @Override // com.bytedance.applog.aggregation.IAggregationFlushCallback
        public void onFinish(List<Metrics> metrics) {
            Intrinsics.checkParameterIsNotNull(metrics, "metrics");
            this.f210a.invoke(metrics);
        }
    }

    public static final class c extends Lambda implements Function0<Map<String, IMetricsTracker>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final c f211a = new c();

        public c() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public Map<String, IMetricsTracker> invoke() {
            return new LinkedHashMap();
        }
    }

    public a2(Looper looper) {
        Intrinsics.checkParameterIsNotNull(looper, "looper");
        this.f208a = LazyKt.lazy(new a(looper));
        this.b = LazyKt.lazy(c.f211a);
    }

    public final void a(Function1<? super List<Metrics>, Unit> callback) {
        Intrinsics.checkParameterIsNotNull(callback, "callback");
        Lazy lazy = this.f208a;
        KProperty kProperty = c[0];
        ((IAggregation) lazy.getValue()).flush(new b(callback));
    }

    public final IMetricsTracker a(g2 data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        Lazy lazy = this.b;
        KProperty[] kPropertyArr = c;
        KProperty kProperty = kPropertyArr[1];
        IMetricsTracker iMetricsTracker = (IMetricsTracker) ((Map) lazy.getValue()).get(Intrinsics.stringPlus(Reflection.getOrCreateKotlinClass(data.getClass()).getSimpleName(), data.a()));
        if (iMetricsTracker != null) {
            return iMetricsTracker;
        }
        Lazy lazy2 = this.f208a;
        KProperty kProperty2 = kPropertyArr[0];
        IAggregation iAggregation = (IAggregation) lazy2.getValue();
        String simpleName = data.getClass().getSimpleName();
        Intrinsics.checkExpressionValueIsNotNull(simpleName, "data::class.java.simpleName");
        IMetricsTracker iMetricsTrackerNewMetricsTracker = iAggregation.newMetricsTracker(simpleName, data.c(), data.a(), data.f());
        Lazy lazy3 = this.b;
        KProperty kProperty3 = kPropertyArr[1];
        ((Map) lazy3.getValue()).put(Intrinsics.stringPlus(Reflection.getOrCreateKotlinClass(data.getClass()).getSimpleName(), data.a()), iMetricsTrackerNewMetricsTracker);
        return iMetricsTrackerNewMetricsTracker;
    }
}
