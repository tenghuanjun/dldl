package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: Draggable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1", f = "Draggable.kt", i = {}, l = {456}, m = "invokeSuspend", n = {}, s = {})
final class AbstractDraggableNode$pointerInputNode$1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ AbstractDraggableNode this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    AbstractDraggableNode$pointerInputNode$1(AbstractDraggableNode abstractDraggableNode, Continuation<? super AbstractDraggableNode$pointerInputNode$1> continuation) {
        super(2, continuation);
        this.this$0 = abstractDraggableNode;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        AbstractDraggableNode$pointerInputNode$1 abstractDraggableNode$pointerInputNode$1 = new AbstractDraggableNode$pointerInputNode$1(this.this$0, continuation);
        abstractDraggableNode$pointerInputNode$1.L$0 = obj;
        return abstractDraggableNode$pointerInputNode$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
        return ((AbstractDraggableNode$pointerInputNode$1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
            if (!this.this$0.getEnabled()) {
                return Unit.INSTANCE;
            }
            this.label = 1;
            if (CoroutineScopeKt.coroutineScope(new AnonymousClass1(pointerInputScope, this.this$0, null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1, reason: invalid class name */
    /* JADX INFO: compiled from: Draggable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1", f = "Draggable.kt", i = {0}, l = {458}, m = "invokeSuspend", n = {"$this$coroutineScope"}, s = {"L$0"})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PointerInputScope $$this$SuspendingPointerInputModifierNode;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ AbstractDraggableNode this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PointerInputScope pointerInputScope, AbstractDraggableNode abstractDraggableNode, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$$this$SuspendingPointerInputModifierNode = pointerInputScope;
            this.this$0 = abstractDraggableNode;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$SuspendingPointerInputModifierNode, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x004b  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r8) {
            /*
                r7 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r7.label
                r2 = 1
                if (r1 == 0) goto L1d
                if (r1 != r2) goto L15
                java.lang.Object r0 = r7.L$0
                kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
                kotlin.ResultKt.throwOnFailure(r8)     // Catch: java.util.concurrent.CancellationException -> L13
                goto L48
            L13:
                r8 = move-exception
                goto L42
            L15:
                java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r8.<init>(r0)
                throw r8
            L1d:
                kotlin.ResultKt.throwOnFailure(r8)
                java.lang.Object r8 = r7.L$0
                kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
                androidx.compose.ui.input.pointer.PointerInputScope r1 = r7.$$this$SuspendingPointerInputModifierNode     // Catch: java.util.concurrent.CancellationException -> L3e
                androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1 r3 = new androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1     // Catch: java.util.concurrent.CancellationException -> L3e
                androidx.compose.foundation.gestures.AbstractDraggableNode r4 = r7.this$0     // Catch: java.util.concurrent.CancellationException -> L3e
                r5 = 0
                r3.<init>(r8, r4, r5)     // Catch: java.util.concurrent.CancellationException -> L3e
                kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3     // Catch: java.util.concurrent.CancellationException -> L3e
                r4 = r7
                kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4     // Catch: java.util.concurrent.CancellationException -> L3e
                r7.L$0 = r8     // Catch: java.util.concurrent.CancellationException -> L3e
                r7.label = r2     // Catch: java.util.concurrent.CancellationException -> L3e
                java.lang.Object r8 = r1.awaitPointerEventScope(r3, r4)     // Catch: java.util.concurrent.CancellationException -> L3e
                if (r8 != r0) goto L48
                return r0
            L3e:
                r0 = move-exception
                r6 = r0
                r0 = r8
                r8 = r6
            L42:
                boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
                if (r0 == 0) goto L4b
            L48:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            L4b:
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Draggable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1$1$1", f = "Draggable.kt", i = {0, 1, 1}, l = {460, 475}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope", "$this$awaitPointerEventScope", "isDragSuccessful"}, s = {"L$0", "L$0", "I$0"})
        static final class C00261 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ CoroutineScope $$this$coroutineScope;
            int I$0;
            private /* synthetic */ Object L$0;
            Object L$1;
            Object L$2;
            int label;
            final /* synthetic */ AbstractDraggableNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00261(CoroutineScope coroutineScope, AbstractDraggableNode abstractDraggableNode, Continuation<? super C00261> continuation) {
                super(2, continuation);
                this.$$this$coroutineScope = coroutineScope;
                this.this$0 = abstractDraggableNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00261 c00261 = new C00261(this.$$this$coroutineScope, this.this$0, continuation);
                c00261.L$0 = obj;
                return c00261;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00261) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Can't wrap try/catch for region: R(8:25|(1:27)|78|28|29|82|30|(1:32)(9:33|80|34|35|(0)(0)|43|61|18|(2:74|75)(0))) */
            /* JADX WARN: Code restructure failed: missing block: B:48:0x0144, code lost:
            
                r0 = th;
             */
            /* JADX WARN: Code restructure failed: missing block: B:50:0x0146, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:52:0x0148, code lost:
            
                r0 = th;
             */
            /* JADX WARN: Code restructure failed: missing block: B:53:0x0149, code lost:
            
                r20 = r6;
                r12 = r8;
             */
            /* JADX WARN: Code restructure failed: missing block: B:54:0x014c, code lost:
            
                r3 = r20;
             */
            /* JADX WARN: Code restructure failed: missing block: B:55:0x014f, code lost:
            
                r0 = e;
             */
            /* JADX WARN: Code restructure failed: missing block: B:56:0x0150, code lost:
            
                r19 = r5;
                r20 = r6;
                r16 = r7;
                r12 = r8;
             */
            /* JADX WARN: Code restructure failed: missing block: B:57:0x0159, code lost:
            
                r4 = r16;
                r2 = r19;
                r3 = r20;
             */
            /* JADX WARN: Removed duplicated region for block: B:20:0x005c  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x008b  */
            /* JADX WARN: Removed duplicated region for block: B:37:0x0108  */
            /* JADX WARN: Removed duplicated region for block: B:42:0x0132  */
            /* JADX WARN: Removed duplicated region for block: B:60:0x0165  */
            /* JADX WARN: Removed duplicated region for block: B:62:0x0187 A[Catch: all -> 0x0188, TRY_ENTER, TRY_LEAVE, TryCatch #3 {all -> 0x0188, blocks: (B:58:0x015f, B:62:0x0187), top: B:76:0x015f }] */
            /* JADX WARN: Removed duplicated region for block: B:66:0x019e  */
            /* JADX WARN: Removed duplicated region for block: B:70:0x01c5  */
            /* JADX WARN: Removed duplicated region for block: B:73:0x01d2  */
            /* JADX WARN: Removed duplicated region for block: B:74:0x01d8  */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:33:0x00e6 -> B:80:0x00ed). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:60:0x0165 -> B:61:0x0182). Please report as a decompilation issue!!! */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:73:0x01d2 -> B:18:0x0054). Please report as a decompilation issue!!! */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r22) throws java.lang.Throwable {
                /*
                    Method dump skipped, instruction units count: 475
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.AbstractDraggableNode$pointerInputNode$1.AnonymousClass1.C00261.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }
}
