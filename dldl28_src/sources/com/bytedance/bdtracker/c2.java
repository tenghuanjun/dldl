package com.bytedance.bdtracker;

import android.os.Handler;
import com.bytedance.applog.aggregation.Metrics;
import java.util.ArrayList;
import java.util.List;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: loaded from: classes2.dex */
public final class c2 extends Lambda implements Function1<List<? extends Metrics>, Unit> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d2 f228a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c2(d2 d2Var) {
        super(1);
        this.f228a = d2Var;
    }

    @Override // kotlin.jvm.functions.Function1
    public Unit invoke(List<? extends Metrics> list) {
        List<? extends Metrics> list2 = list;
        Intrinsics.checkParameterIsNotNull(list2, "list");
        ArrayList arrayList = new ArrayList();
        for (Metrics metrics : list2) {
            x3 x3Var = new x3();
            this.f228a.c.n.a(this.f228a.c.d, x3Var);
            x3Var.o = metrics.toParams();
            arrayList.add(x3Var);
        }
        Handler handler = this.f228a.f238a;
        handler.sendMessage(handler.obtainMessage(1, arrayList));
        this.f228a.f238a.sendEmptyMessage(2);
        return Unit.INSTANCE;
    }
}
