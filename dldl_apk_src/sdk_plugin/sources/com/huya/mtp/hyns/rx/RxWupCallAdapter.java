package com.huya.mtp.hyns.rx;

import com.huya.mtp.hyns.NSCall;
import com.huya.mtp.hyns.NSCallAdapter;
import io.reactivex.Scheduler;
import java.lang.reflect.Type;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class RxWupCallAdapter<R> implements NSCallAdapter<Object, NSCall> {
    private final boolean isBody;
    private final boolean isCompletable;
    private final boolean isFlowable;
    private final boolean isMaybe;
    private final boolean isResult;
    private final boolean isSingle;
    private final Type responseType;
    private final Scheduler scheduler = null;
    private final boolean isAsync = false;

    public RxWupCallAdapter(Type type, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        this.responseType = type;
        this.isResult = z;
        this.isBody = z2;
        this.isFlowable = z3;
        this.isSingle = z4;
        this.isMaybe = z5;
        this.isCompletable = z6;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:14:0x0026  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x002d  */
    @Override // com.huya.mtp.hyns.NSCallAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.lang.Object adapt(com.huya.mtp.hyns.NSCall r2) {
        /*
            r1 = this;
            com.huya.mtp.hyns.rx.CallExecuteObservable r0 = new com.huya.mtp.hyns.rx.CallExecuteObservable
            r0.<init>(r2)
            boolean r2 = r1.isResult
            if (r2 == 0) goto L10
            com.huya.mtp.hyns.rx.ResultObservable r2 = new com.huya.mtp.hyns.rx.ResultObservable
            r2.<init>(r0)
        Le:
            r0 = r2
            goto L1a
        L10:
            boolean r2 = r1.isBody
            if (r2 == 0) goto L1a
            com.huya.mtp.hyns.rx.BodyObservable r2 = new com.huya.mtp.hyns.rx.BodyObservable
            r2.<init>(r0)
            goto Le
        L1a:
            io.reactivex.Scheduler r2 = r1.scheduler
            if (r2 == 0) goto L22
            io.reactivex.Observable r0 = r0.subscribeOn(r2)
        L22:
            boolean r2 = r1.isFlowable
            if (r2 == 0) goto L2d
            io.reactivex.BackpressureStrategy r2 = io.reactivex.BackpressureStrategy.LATEST
            io.reactivex.Flowable r2 = r0.toFlowable(r2)
            return r2
        L2d:
            boolean r2 = r1.isSingle
            if (r2 == 0) goto L36
            io.reactivex.Single r2 = r0.singleOrError()
            return r2
        L36:
            boolean r2 = r1.isMaybe
            if (r2 == 0) goto L3f
            io.reactivex.Maybe r2 = r0.singleElement()
            return r2
        L3f:
            boolean r2 = r1.isCompletable
            if (r2 == 0) goto L48
            io.reactivex.Completable r2 = r0.ignoreElements()
            return r2
        L48:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.hyns.rx.RxWupCallAdapter.adapt(com.huya.mtp.hyns.NSCall):java.lang.Object");
    }
}
