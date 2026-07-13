package com.bytedance.bdtracker;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import java.util.List;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;

/* JADX INFO: loaded from: classes2.dex */
public final class d2 implements z1, Handler.Callback {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Handler f238a;
    public final a2 b;
    public final c0 c;

    public d2(c0 mEngine) {
        Intrinsics.checkParameterIsNotNull(mEngine, "mEngine");
        this.c = mEngine;
        StringBuilder sbA = a.a("bd_tracker_monitor@");
        d dVar = mEngine.d;
        Intrinsics.checkExpressionValueIsNotNull(dVar, "mEngine.appLog");
        sbA.append(dVar.m);
        HandlerThread handlerThread = new HandlerThread(sbA.toString());
        handlerThread.start();
        this.f238a = new Handler(handlerThread.getLooper(), this);
        Looper looper = this.f238a.getLooper();
        Intrinsics.checkExpressionValueIsNotNull(looper, "mHandler.looper");
        this.b = new a2(looper);
    }

    public void a(g2 data) {
        Intrinsics.checkParameterIsNotNull(data, "data");
        i1 i1Var = this.c.e;
        Intrinsics.checkExpressionValueIsNotNull(i1Var, "mEngine.config");
        if (i1Var.i()) {
            d dVar = this.c.d;
            Intrinsics.checkExpressionValueIsNotNull(dVar, "mEngine.appLog");
            dVar.D.debug(8, "Monitor trace:{}", data);
            this.b.a(data).track(data.g(), data.d());
        }
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message msg) {
        Intrinsics.checkParameterIsNotNull(msg, "msg");
        int i = msg.what;
        if (i == 1) {
            d dVar = this.c.d;
            Intrinsics.checkExpressionValueIsNotNull(dVar, "mEngine.appLog");
            dVar.D.debug(8, "Monitor trace save:{}", msg.obj);
            p3 p3VarC = this.c.c();
            Object obj = msg.obj;
            if (!TypeIntrinsics.isMutableList(obj)) {
                obj = null;
            }
            p3VarC.c.a((List) obj);
        } else if (i == 2) {
            k1 k1Var = this.c.i;
            if (k1Var == null || k1Var.i() != 0) {
                d dVar2 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar2, "mEngine.appLog");
                dVar2.D.debug(8, "Monitor report...", new Object[0]);
                p3 p3VarC2 = this.c.c();
                d dVar3 = this.c.d;
                Intrinsics.checkExpressionValueIsNotNull(dVar3, "mEngine.appLog");
                String str = dVar3.m;
                k1 k1Var2 = this.c.i;
                Intrinsics.checkExpressionValueIsNotNull(k1Var2, "mEngine.dm");
                p3VarC2.b(str, k1Var2.e());
                c0 c0Var = this.c;
                c0Var.a(c0Var.l);
            } else {
                this.f238a.sendEmptyMessageDelayed(2, 500L);
            }
        }
        return true;
    }
}
