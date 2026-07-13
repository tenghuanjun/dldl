package androidx.compose.foundation.gestures;

import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.node.CompositionLocalConsumerModifierNode;
import androidx.compose.ui.node.DelegatingNode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: Scrollable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\rH\u0016R\u001c\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/foundation/gestures/MouseWheelScrollNode;", "Landroidx/compose/ui/node/DelegatingNode;", "Landroidx/compose/ui/node/CompositionLocalConsumerModifierNode;", "scrollingLogic", "Landroidx/compose/foundation/gestures/ScrollingLogic;", "(Landroidx/compose/foundation/gestures/ScrollingLogic;)V", "scrollConfig", "Landroidx/compose/foundation/gestures/ScrollConfig;", "getScrollConfig", "()Landroidx/compose/foundation/gestures/ScrollConfig;", "setScrollConfig", "(Landroidx/compose/foundation/gestures/ScrollConfig;)V", "onAttach", "", "foundation_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class MouseWheelScrollNode extends DelegatingNode implements CompositionLocalConsumerModifierNode {
    private ScrollConfig scrollConfig;
    private final ScrollingLogic scrollingLogic;

    public MouseWheelScrollNode(ScrollingLogic scrollingLogic) {
        this.scrollingLogic = scrollingLogic;
        delegate(SuspendingPointerInputFilterKt.SuspendingPointerInputModifierNode(new AnonymousClass1(null)));
    }

    public final ScrollConfig getScrollConfig() {
        return this.scrollConfig;
    }

    public final void setScrollConfig(ScrollConfig scrollConfig) {
        this.scrollConfig = scrollConfig;
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onAttach() {
        this.scrollConfig = AndroidScrollable_androidKt.platformScrollConfig(this);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollNode$1, reason: invalid class name */
    /* JADX INFO: compiled from: Scrollable.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollNode$1", f = "Scrollable.kt", i = {}, l = {669}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = MouseWheelScrollNode.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: Scrollable.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1", f = "Scrollable.kt", i = {0}, l = {671}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        static final class C00331 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            private /* synthetic */ Object L$0;
            int label;
            final /* synthetic */ MouseWheelScrollNode this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00331(MouseWheelScrollNode mouseWheelScrollNode, Continuation<? super C00331> continuation) {
                super(2, continuation);
                this.this$0 = mouseWheelScrollNode;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00331 c00331 = new C00331(this.this$0, continuation);
                c00331.L$0 = obj;
                return c00331;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00331) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0030 A[RETURN] */
            /* JADX WARN: Removed duplicated region for block: B:14:0x003f  */
            /* JADX WARN: Removed duplicated region for block: B:21:0x004f A[EDGE_INSN: B:21:0x004f->B:18:0x004f BREAK  A[LOOP:0: B:13:0x003d->B:17:0x004c], SYNTHETIC] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x002e -> B:12:0x0031). Please report as a decompilation issue!!! */
            /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
                jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
                	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
                	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
                	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
                */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r15) {
                /*
                    r14 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r14.label
                    r2 = 1
                    if (r1 == 0) goto L1b
                    if (r1 != r2) goto L13
                    java.lang.Object r1 = r14.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r15)
                    goto L31
                L13:
                    java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r15.<init>(r0)
                    throw r15
                L1b:
                    kotlin.ResultKt.throwOnFailure(r15)
                    java.lang.Object r15 = r14.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r15 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r15
                    r1 = r15
                L23:
                    r15 = r14
                    kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15
                    r14.L$0 = r1
                    r14.label = r2
                    java.lang.Object r15 = androidx.compose.foundation.gestures.ScrollableKt.access$awaitScrollEvent(r1, r15)
                    if (r15 != r0) goto L31
                    return r0
                L31:
                    androidx.compose.ui.input.pointer.PointerEvent r15 = (androidx.compose.ui.input.pointer.PointerEvent) r15
                    java.util.List r3 = r15.getChanges()
                    int r4 = r3.size()
                    r5 = 0
                    r6 = 0
                L3d:
                    if (r6 >= r4) goto L4f
                    java.lang.Object r7 = r3.get(r6)
                    androidx.compose.ui.input.pointer.PointerInputChange r7 = (androidx.compose.ui.input.pointer.PointerInputChange) r7
                    boolean r7 = r7.isConsumed()
                    if (r7 == 0) goto L4c
                    goto L23
                L4c:
                    int r6 = r6 + 1
                    goto L3d
                L4f:
                    androidx.compose.foundation.gestures.MouseWheelScrollNode r3 = r14.this$0
                    androidx.compose.foundation.gestures.ScrollConfig r3 = r3.getScrollConfig()
                    kotlin.jvm.internal.Intrinsics.checkNotNull(r3)
                    androidx.compose.foundation.gestures.MouseWheelScrollNode r4 = r14.this$0
                    r6 = r1
                    androidx.compose.ui.unit.Density r6 = (androidx.compose.ui.unit.Density) r6
                    long r7 = r1.mo4554getSizeYbymL2g()
                    long r6 = r3.mo351calculateMouseWheelScroll8xgXZGE(r6, r15, r7)
                    androidx.compose.foundation.gestures.ScrollingLogic r3 = androidx.compose.foundation.gestures.MouseWheelScrollNode.access$getScrollingLogic$p(r4)
                    kotlinx.coroutines.CoroutineScope r8 = r4.getCoroutineScope()
                    androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1$2$1$1 r4 = new androidx.compose.foundation.gestures.MouseWheelScrollNode$1$1$2$1$1
                    r9 = 0
                    r4.<init>(r3, r6, r9)
                    r11 = r4
                    kotlin.jvm.functions.Function2 r11 = (kotlin.jvm.functions.Function2) r11
                    r12 = 3
                    r13 = 0
                    r10 = 0
                    kotlinx.coroutines.BuildersKt.launch$default(r8, r9, r10, r11, r12, r13)
                    java.util.List r15 = r15.getChanges()
                    int r3 = r15.size()
                L84:
                    if (r5 >= r3) goto L23
                    java.lang.Object r4 = r15.get(r5)
                    androidx.compose.ui.input.pointer.PointerInputChange r4 = (androidx.compose.ui.input.pointer.PointerInputChange) r4
                    r4.consume()
                    int r5 = r5 + 1
                    goto L84
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.gestures.MouseWheelScrollNode.AnonymousClass1.C00331.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00331(MouseWheelScrollNode.this, null), this) == coroutine_suspended) {
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
    }
}
