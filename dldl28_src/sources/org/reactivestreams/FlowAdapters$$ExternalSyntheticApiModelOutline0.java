package org.reactivestreams;

import java.time.Duration;
import java.util.concurrent.Flow;
import java.util.concurrent.locks.StampedLock;
import java.util.function.Supplier;

/* JADX INFO: compiled from: D8$$SyntheticClass */
/* JADX INFO: loaded from: classes4.dex */
public final /* synthetic */ class FlowAdapters$$ExternalSyntheticApiModelOutline0 {
    public static /* bridge */ /* synthetic */ Duration m(Object obj) {
        return (Duration) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Flow.Processor m8510m(Object obj) {
        return (Flow.Processor) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Flow.Publisher m8511m(Object obj) {
        return (Flow.Publisher) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Flow.Subscriber m8512m(Object obj) {
        return (Flow.Subscriber) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* synthetic */ StampedLock m8513m() {
        return new StampedLock();
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ Supplier m8514m(Object obj) {
        return (Supplier) obj;
    }

    /* JADX INFO: renamed from: m, reason: collision with other method in class */
    public static /* bridge */ /* synthetic */ boolean m8515m(Object obj) {
        return obj instanceof Flow.Publisher;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof Flow.Subscriber;
    }

    public static /* bridge */ /* synthetic */ boolean m$2(Object obj) {
        return obj instanceof Flow.Processor;
    }
}
