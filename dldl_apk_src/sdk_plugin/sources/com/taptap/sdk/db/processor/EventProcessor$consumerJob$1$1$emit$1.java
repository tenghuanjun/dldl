package com.taptap.sdk.db.processor;

import com.taptap.sdk.db.data.model.EventTrigger;
import com.taptap.sdk.db.processor.EventProcessor$consumerJob$1;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: compiled from: EventProcessor.kt */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
@Metadata(k = 3, mv = {1, 5, 1}, xi = 48)
@DebugMetadata(c = "com.taptap.sdk.db.processor.EventProcessor$consumerJob$1$1", f = "EventProcessor.kt", i = {1}, l = {50, 56, 59}, m = "emit", n = {"this"}, s = {"L$0"})
final class EventProcessor$consumerJob$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ EventProcessor$consumerJob$1.AnonymousClass1<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    EventProcessor$consumerJob$1$1$emit$1(EventProcessor$consumerJob$1.AnonymousClass1<? super T> anonymousClass1, Continuation<? super EventProcessor$consumerJob$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = anonymousClass1;
    }

    /* JADX WARN: Type inference incomplete: some casts might be missing */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((EventTrigger) null, (Continuation<? super Unit>) this);
    }
}
