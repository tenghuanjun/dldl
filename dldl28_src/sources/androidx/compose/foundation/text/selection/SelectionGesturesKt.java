package androidx.compose.foundation.text.selection;

import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.text.TextDragObserver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerEvent;
import androidx.compose.ui.input.pointer.PointerInputChange;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.PointerType;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: SelectionGestures.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u001a\u0012\u0010\b\u001a\u00020\u0006*\u00020\tH\u0082@¢\u0006\u0002\u0010\n\u001a&\u0010\u000b\u001a\u00020\u0005*\u00020\t2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u0012*\u00020\t2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010\u0018\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0000\u001a\"\u0010\u001e\u001a\u00020\u0012*\u00020\t2\u0006\u0010\u0013\u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020\u0006H\u0082@¢\u0006\u0002\u0010\u001f\u001a \u0010 \u001a\u00020\u001a*\u00020\u001a2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00120\"H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082T¢\u0006\u0002\n\u0000\"\u0018\u0010\u0004\u001a\u00020\u0005*\u00020\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0007\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006#"}, d2 = {"ClicksSlop", "", "STATIC_KEY", "", "isPrecisePointer", "", "Landroidx/compose/ui/input/pointer/PointerEvent;", "(Landroidx/compose/ui/input/pointer/PointerEvent;)Z", "awaitDown", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "distanceIsTolerable", "offset1", "Landroidx/compose/ui/geometry/Offset;", "offset2", "distanceIsTolerable-2x9bVx0", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;JJ)Z", "mouseSelection", "", "observer", "Landroidx/compose/foundation/text/selection/MouseSelectionObserver;", "clicksCounter", "Landroidx/compose/foundation/text/selection/ClicksCounter;", "down", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/selection/MouseSelectionObserver;Landroidx/compose/foundation/text/selection/ClicksCounter;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectionGestureInput", "Landroidx/compose/ui/Modifier;", "mouseSelectionObserver", "textDragObserver", "Landroidx/compose/foundation/text/TextDragObserver;", "touchSelection", "(Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;Landroidx/compose/foundation/text/TextDragObserver;Landroidx/compose/ui/input/pointer/PointerEvent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateSelectionTouchMode", "updateTouchMode", "Lkotlin/Function1;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionGesturesKt {
    public static final double ClicksSlop = 100.0d;
    private static final int STATIC_KEY = 8675309;

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0}, l = {KeyBoardKey.KeyboardKeyOemAx}, m = "awaitDown", n = {"$this$awaitDown"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.awaitDown(null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$mouseSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 1, 1}, l = {153, KeyBoardKey.KeyboardKeyMediaNextTrack}, m = "mouseSelection", n = {"$this$mouseSelection", "observer", "$this$mouseSelection", "observer"}, s = {"L$0", "L$1", "L$0", "L$1"})
    static final class C06061 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;

        C06061(Continuation<? super C06061> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.mouseSelection(null, null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$touchSelection$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt", f = "SelectionGestures.kt", i = {0, 0, 0, 1, 1}, l = {119, 123}, m = "touchSelection", n = {"$this$touchSelection", "observer", "firstDown", "$this$touchSelection", "observer"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1"})
    static final class C06081 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;

        C06081(Continuation<? super C06081> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return SelectionGesturesKt.touchSelection(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1", f = "SelectionGestures.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
    static final class C06091 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C06091(Function1<? super Boolean, Unit> function1, Continuation<? super C06091> continuation) {
            super(2, continuation);
            this.$updateTouchMode = function1;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06091 c06091 = new C06091(this.$updateTouchMode, continuation);
            c06091.L$0 = obj;
            return c06091;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C06091) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$updateSelectionTouchMode$1$1", f = "SelectionGestures.kt", i = {0}, l = {88}, m = "invokeSuspend", n = {"$this$awaitPointerEventScope"}, s = {"L$0"})
        static final class C00681 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function1<Boolean, Unit> $updateTouchMode;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00681(Function1<? super Boolean, Unit> function1, Continuation<? super C00681> continuation) {
                super(2, continuation);
                this.$updateTouchMode = function1;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00681 c00681 = new C00681(this.$updateTouchMode, continuation);
                c00681.L$0 = obj;
                return c00681;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00681) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:11:0x0032 A[RETURN] */
            /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:10:0x0030 -> B:12:0x0033). Please report as a decompilation issue!!! */
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
            public final java.lang.Object invokeSuspend(java.lang.Object r5) {
                /*
                    r4 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r4.label
                    r2 = 1
                    if (r1 == 0) goto L1b
                    if (r1 != r2) goto L13
                    java.lang.Object r1 = r4.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r5)
                    goto L33
                L13:
                    java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r5.<init>(r0)
                    throw r5
                L1b:
                    kotlin.ResultKt.throwOnFailure(r5)
                    java.lang.Object r5 = r4.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                    r1 = r5
                L23:
                    androidx.compose.ui.input.pointer.PointerEventPass r5 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                    r3 = r4
                    kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
                    r4.L$0 = r1
                    r4.label = r2
                    java.lang.Object r5 = r1.awaitPointerEvent(r5, r3)
                    if (r5 != r0) goto L33
                    return r0
                L33:
                    androidx.compose.ui.input.pointer.PointerEvent r5 = (androidx.compose.ui.input.pointer.PointerEvent) r5
                    kotlin.jvm.functions.Function1<java.lang.Boolean, kotlin.Unit> r3 = r4.$updateTouchMode
                    boolean r5 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r5)
                    r5 = r5 ^ r2
                    java.lang.Boolean r5 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
                    r3.invoke(r5)
                    goto L23
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C06091.C00681.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((PointerInputScope) this.L$0).awaitPointerEventScope(new C00681(this.$updateTouchMode, null), this) == coroutine_suspended) {
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

    public static final Modifier updateSelectionTouchMode(Modifier modifier, Function1<? super Boolean, Unit> function1) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, Integer.valueOf(STATIC_KEY), new C06091(function1, null));
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SelectionGestures.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1", f = "SelectionGestures.kt", i = {}, l = {99}, m = "invokeSuspend", n = {}, s = {})
    static final class C06071 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
        final /* synthetic */ TextDragObserver $textDragObserver;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06071(MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver, Continuation<? super C06071> continuation) {
            super(2, continuation);
            this.$mouseSelectionObserver = mouseSelectionObserver;
            this.$textDragObserver = textDragObserver;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C06071 c06071 = new C06071(this.$mouseSelectionObserver, this.$textDragObserver, continuation);
            c06071.L$0 = obj;
            return c06071;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((C06071) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PointerInputScope pointerInputScope = (PointerInputScope) this.L$0;
                ClicksCounter clicksCounter = new ClicksCounter(pointerInputScope.getViewConfiguration());
                this.label = 1;
                if (ForEachGestureKt.awaitEachGesture(pointerInputScope, new C00671(this.$mouseSelectionObserver, clicksCounter, this.$textDragObserver, null), this) == coroutine_suspended) {
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

        /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SelectionGestures.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.text.selection.SelectionGesturesKt$selectionGestureInput$1$1", f = "SelectionGestures.kt", i = {0}, l = {100, 106, 108}, m = "invokeSuspend", n = {"$this$awaitEachGesture"}, s = {"L$0"})
        static final class C00671 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ClicksCounter $clicksCounter;
            final /* synthetic */ MouseSelectionObserver $mouseSelectionObserver;
            final /* synthetic */ TextDragObserver $textDragObserver;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00671(MouseSelectionObserver mouseSelectionObserver, ClicksCounter clicksCounter, TextDragObserver textDragObserver, Continuation<? super C00671> continuation) {
                super(2, continuation);
                this.$mouseSelectionObserver = mouseSelectionObserver;
                this.$clicksCounter = clicksCounter;
                this.$textDragObserver = textDragObserver;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                C00671 c00671 = new C00671(this.$mouseSelectionObserver, this.$clicksCounter, this.$textDragObserver, continuation);
                c00671.L$0 = obj;
                return c00671;
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                return ((C00671) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Removed duplicated region for block: B:30:0x0083  */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public final java.lang.Object invokeSuspend(java.lang.Object r10) {
                /*
                    r9 = this;
                    java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                    int r1 = r9.label
                    r2 = 3
                    r3 = 2
                    r4 = 1
                    if (r1 == 0) goto L27
                    if (r1 == r4) goto L1f
                    if (r1 == r3) goto L1a
                    if (r1 != r2) goto L12
                    goto L1a
                L12:
                    java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                    java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                    r10.<init>(r0)
                    throw r10
                L1a:
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L93
                L1f:
                    java.lang.Object r1 = r9.L$0
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    kotlin.ResultKt.throwOnFailure(r10)
                    goto L3d
                L27:
                    kotlin.ResultKt.throwOnFailure(r10)
                    java.lang.Object r10 = r9.L$0
                    r1 = r10
                    androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                    r10 = r9
                    kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
                    r9.L$0 = r1
                    r9.label = r4
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$awaitDown(r1, r10)
                    if (r10 != r0) goto L3d
                    return r0
                L3d:
                    androidx.compose.ui.input.pointer.PointerEvent r10 = (androidx.compose.ui.input.pointer.PointerEvent) r10
                    boolean r4 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r10)
                    r5 = 0
                    if (r4 == 0) goto L7d
                    int r4 = r10.getButtons()
                    boolean r4 = androidx.compose.ui.input.pointer.PointerEvent_androidKt.m4629isPrimaryPressedaHzCxE(r4)
                    if (r4 == 0) goto L7d
                    java.util.List r4 = r10.getChanges()
                    int r6 = r4.size()
                    r7 = 0
                L59:
                    if (r7 >= r6) goto L6b
                    java.lang.Object r8 = r4.get(r7)
                    androidx.compose.ui.input.pointer.PointerInputChange r8 = (androidx.compose.ui.input.pointer.PointerInputChange) r8
                    boolean r8 = r8.isConsumed()
                    if (r8 == 0) goto L68
                    goto L7d
                L68:
                    int r7 = r7 + 1
                    goto L59
                L6b:
                    androidx.compose.foundation.text.selection.MouseSelectionObserver r2 = r9.$mouseSelectionObserver
                    androidx.compose.foundation.text.selection.ClicksCounter r4 = r9.$clicksCounter
                    r6 = r9
                    kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                    r9.L$0 = r5
                    r9.label = r3
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$mouseSelection(r1, r2, r4, r10, r6)
                    if (r10 != r0) goto L93
                    return r0
                L7d:
                    boolean r3 = androidx.compose.foundation.text.selection.SelectionGesturesKt.isPrecisePointer(r10)
                    if (r3 != 0) goto L93
                    androidx.compose.foundation.text.TextDragObserver r3 = r9.$textDragObserver
                    r4 = r9
                    kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
                    r9.L$0 = r5
                    r9.label = r2
                    java.lang.Object r10 = androidx.compose.foundation.text.selection.SelectionGesturesKt.access$touchSelection(r1, r3, r10, r4)
                    if (r10 != r0) goto L93
                    return r0
                L93:
                    kotlin.Unit r10 = kotlin.Unit.INSTANCE
                    return r10
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.C06071.C00671.invokeSuspend(java.lang.Object):java.lang.Object");
            }
        }
    }

    public static final Modifier selectionGestureInput(Modifier modifier, MouseSelectionObserver mouseSelectionObserver, TextDragObserver textDragObserver) {
        return SuspendingPointerInputFilterKt.pointerInput(modifier, mouseSelectionObserver, textDragObserver, new C06071(mouseSelectionObserver, textDragObserver, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00ad A[Catch: CancellationException -> 0x00d8, TryCatch #0 {CancellationException -> 0x00d8, blocks: (B:13:0x0032, B:31:0x00a5, B:33:0x00ad, B:35:0x00bc, B:37:0x00c8, B:38:0x00cb, B:39:0x00ce, B:40:0x00d2, B:18:0x004a, B:24:0x0071, B:26:0x0075, B:28:0x0083, B:21:0x0054), top: B:46:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00d2 A[Catch: CancellationException -> 0x00d8, TRY_LEAVE, TryCatch #0 {CancellationException -> 0x00d8, blocks: (B:13:0x0032, B:31:0x00a5, B:33:0x00ad, B:35:0x00bc, B:37:0x00c8, B:38:0x00cb, B:39:0x00ce, B:40:0x00d2, B:18:0x004a, B:24:0x0071, B:26:0x0075, B:28:0x0083, B:21:0x0054), top: B:46:0x0023 }] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r9, final androidx.compose.foundation.text.TextDragObserver r10, androidx.compose.ui.input.pointer.PointerEvent r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            Method dump skipped, instruction units count: 221
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.touchSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.TextDragObserver, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:25:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00f7  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, final androidx.compose.foundation.text.selection.MouseSelectionObserver r8, androidx.compose.foundation.text.selection.ClicksCounter r9, androidx.compose.ui.input.pointer.PointerEvent r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            Method dump skipped, instruction units count: 285
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.mouseSelection(androidx.compose.ui.input.pointer.AwaitPointerEventScope, androidx.compose.foundation.text.selection.MouseSelectionObserver, androidx.compose.foundation.text.selection.ClicksCounter, androidx.compose.ui.input.pointer.PointerEvent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0045 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0053  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0014  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x0043 -> B:18:0x0046). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final java.lang.Object awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope r7, kotlin.coroutines.Continuation<? super androidx.compose.ui.input.pointer.PointerEvent> r8) {
        /*
            boolean r0 = r8 instanceof androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1
            if (r0 == 0) goto L14
            r0 = r8
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = (androidx.compose.foundation.text.selection.SelectionGesturesKt.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L19
        L14:
            androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1 r0 = new androidx.compose.foundation.text.selection.SelectionGesturesKt$awaitDown$1
            r0.<init>(r8)
        L19:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L36
            if (r2 != r3) goto L2e
            java.lang.Object r7 = r0.L$0
            androidx.compose.ui.input.pointer.AwaitPointerEventScope r7 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L46
        L2e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L36:
            kotlin.ResultKt.throwOnFailure(r8)
        L39:
            androidx.compose.ui.input.pointer.PointerEventPass r8 = androidx.compose.ui.input.pointer.PointerEventPass.Main
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r7.awaitPointerEvent(r8, r0)
            if (r8 != r1) goto L46
            return r1
        L46:
            androidx.compose.ui.input.pointer.PointerEvent r8 = (androidx.compose.ui.input.pointer.PointerEvent) r8
            java.util.List r2 = r8.getChanges()
            int r4 = r2.size()
            r5 = 0
        L51:
            if (r5 >= r4) goto L63
            java.lang.Object r6 = r2.get(r5)
            androidx.compose.ui.input.pointer.PointerInputChange r6 = (androidx.compose.ui.input.pointer.PointerInputChange) r6
            boolean r6 = androidx.compose.ui.input.pointer.PointerEventKt.changedToDownIgnoreConsumed(r6)
            if (r6 != 0) goto L60
            goto L39
        L60:
            int r5 = r5 + 1
            goto L51
        L63:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.text.selection.SelectionGesturesKt.awaitDown(androidx.compose.ui.input.pointer.AwaitPointerEventScope, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: renamed from: distanceIsTolerable-2x9bVx0, reason: not valid java name */
    private static final boolean m1043distanceIsTolerable2x9bVx0(AwaitPointerEventScope awaitPointerEventScope, long j, long j2) {
        return Offset.m3217getDistanceimpl(Offset.m3223minusMKHz9U(j, j2)) < awaitPointerEventScope.getViewConfiguration().getTouchSlop();
    }

    public static final boolean isPrecisePointer(PointerEvent pointerEvent) {
        List<PointerInputChange> changes = pointerEvent.getChanges();
        int size = changes.size();
        for (int i = 0; i < size; i++) {
            if (!PointerType.m4729equalsimpl0(changes.get(i).getType(), PointerType.INSTANCE.m4734getMouseT8wyACA())) {
                return false;
            }
        }
        return true;
    }
}
