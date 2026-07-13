package androidx.compose.runtime;

import androidx.collection.MutableScatterSet;
import androidx.exifinterface.media.ExifInterface;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: SnapshotFlow.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004\u001a?\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\b\b\u0000\u0010\u0002*\u0002H\u0007\"\u0004\b\u0001\u0010\u0007*\b\u0012\u0004\u0012\u0002H\u00020\u00012\u0006\u0010\b\u001a\u0002H\u00072\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\u000b\u001a-\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0006\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\f2\b\b\u0002\u0010\t\u001a\u00020\nH\u0007¢\u0006\u0002\u0010\r\u001a%\u0010\u000e\u001a\u00020\u000f*\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013H\u0002¢\u0006\u0002\b\u0014¨\u0006\u0015"}, d2 = {"snapshotFlow", "Lkotlinx/coroutines/flow/Flow;", ExifInterface.GPS_DIRECTION_TRUE, "block", "Lkotlin/Function0;", "collectAsState", "Landroidx/compose/runtime/State;", "R", "initial", "context", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlinx/coroutines/flow/Flow;Ljava/lang/Object;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "Lkotlinx/coroutines/flow/StateFlow;", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlin/coroutines/CoroutineContext;Landroidx/compose/runtime/Composer;II)Landroidx/compose/runtime/State;", "intersects", "", "Landroidx/collection/MutableScatterSet;", "", "set", "", "intersects$SnapshotStateKt__SnapshotFlowKt", "runtime_release"}, k = 5, mv = {1, 8, 0}, xi = 48, xs = "androidx/compose/runtime/SnapshotStateKt")
final /* synthetic */ class SnapshotStateKt__SnapshotFlowKt {
    public static final <T> State<T> collectAsState(StateFlow<? extends T> stateFlow, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-1439883919);
        ComposerKt.sourceInformation(composer, "C(collectAsState)46@1796L30:SnapshotFlow.kt#9igjgp");
        if ((i2 & 1) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1439883919, i, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:46)");
        }
        State<T> stateCollectAsState = SnapshotStateKt.collectAsState(stateFlow, stateFlow.getValue(), coroutineContext2, composer, 520, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateCollectAsState;
    }

    /* JADX INFO: Add missing generic type declarations: [R] */
    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1, reason: invalid class name */
    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0003\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Landroidx/compose/runtime/ProduceStateScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1", f = "SnapshotFlow.kt", i = {}, l = {64, 65}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1<R> extends SuspendLambda implements Function2<ProduceStateScope<R>, Continuation<? super Unit>, Object> {
        final /* synthetic */ CoroutineContext $context;
        final /* synthetic */ Flow<T> $this_collectAsState;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        AnonymousClass1(CoroutineContext coroutineContext, Flow<? extends T> flow, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$context = coroutineContext;
            this.$this_collectAsState = flow;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$context, this.$this_collectAsState, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ProduceStateScope<R> produceStateScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(produceStateScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Type inference incomplete: some casts might be missing */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final ProduceStateScope produceStateScope = (ProduceStateScope) this.L$0;
                if (Intrinsics.areEqual(this.$context, EmptyCoroutineContext.INSTANCE)) {
                    Flow<T> flow = this.$this_collectAsState;
                    FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.collectAsState.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(T t, Continuation<? super Unit> continuation) {
                            produceStateScope.setValue(t);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flow.collect((FlowCollector<? super T>) flowCollector, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    this.label = 2;
                    if (BuildersKt.withContext(this.$context, new AnonymousClass2(this.$this_collectAsState, produceStateScope, null), this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (i != 1 && i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SnapshotFlow.kt */
        @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u0002H\u0003\"\u0004\b\u0001\u0010\u0003*\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "R", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$collectAsState$1$2", f = "SnapshotFlow.kt", i = {}, l = {66}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ProduceStateScope<R> $$this$produceState;
            final /* synthetic */ Flow<T> $this_collectAsState;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass2(Flow<? extends T> flow, ProduceStateScope<R> produceStateScope, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.$this_collectAsState = flow;
                this.$$this$produceState = produceStateScope;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.$this_collectAsState, this.$$this$produceState, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX WARN: Type inference incomplete: some casts might be missing */
            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    Flow<T> flow = this.$this_collectAsState;
                    final ProduceStateScope<R> produceStateScope = this.$$this$produceState;
                    FlowCollector flowCollector = new FlowCollector() { // from class: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.collectAsState.1.2.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public final Object emit(T t, Continuation<? super Unit> continuation) {
                            produceStateScope.setValue(t);
                            return Unit.INSTANCE;
                        }
                    };
                    this.label = 1;
                    if (flow.collect((FlowCollector<? super T>) flowCollector, this) == coroutine_suspended) {
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

    public static final <T extends R, R> State<R> collectAsState(Flow<? extends T> flow, R r, CoroutineContext coroutineContext, Composer composer, int i, int i2) {
        composer.startReplaceableGroup(-606625098);
        ComposerKt.sourceInformation(composer, "C(collectAsState)P(1)61@2338L186:SnapshotFlow.kt#9igjgp");
        if ((i2 & 2) != 0) {
            coroutineContext = EmptyCoroutineContext.INSTANCE;
        }
        CoroutineContext coroutineContext2 = coroutineContext;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-606625098, i, -1, "androidx.compose.runtime.collectAsState (SnapshotFlow.kt:61)");
        }
        int i3 = i >> 3;
        State<R> stateProduceState = SnapshotStateKt.produceState(r, flow, coroutineContext2, new AnonymousClass1(coroutineContext2, flow, null), composer, (i3 & 8) | 4672 | (i3 & 14));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return stateProduceState;
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    /* JADX INFO: renamed from: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SnapshotFlow.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003H\u008a@"}, d2 = {"<anonymous>", "", ExifInterface.GPS_DIRECTION_TRUE, "Lkotlinx/coroutines/flow/FlowCollector;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt$snapshotFlow$1", f = "SnapshotFlow.kt", i = {0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2}, l = {144, KeyBoardKey.KeyboardKeyOemFjTouroku, KeyBoardKey.KeyboardKeyBrowserSearch}, m = "invokeSuspend", n = {"$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue", "$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue", "found", "$this$flow", "readSet", "readObserver", "appliedChanges", "unregisterApplyObserver", "lastValue"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "I$0", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5"})
    static final class C08111<T> extends SuspendLambda implements Function2<FlowCollector<? super T>, Continuation<? super Unit>, Object> {
        final /* synthetic */ Function0<T> $block;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C08111(Function0<? extends T> function0, Continuation<? super C08111> continuation) {
            super(2, continuation);
            this.$block = function0;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C08111 c08111 = new C08111(this.$block, continuation);
            c08111.L$0 = obj;
            return c08111;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super T> flowCollector, Continuation<? super Unit> continuation) {
            return ((C08111) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Path cross not found for [B:32:0x00eb, B:36:0x00f4], limit reached: 76 */
        /* JADX WARN: Path cross not found for [B:40:0x0103, B:55:0x0144], limit reached: 76 */
        /* JADX WARN: Path cross not found for [B:55:0x0144, B:40:0x0103], limit reached: 76 */
        /* JADX WARN: Removed duplicated region for block: B:28:0x00df A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00e0  */
        /* JADX WARN: Removed duplicated region for block: B:40:0x0103 A[Catch: all -> 0x0052, TRY_LEAVE, TryCatch #1 {all -> 0x0052, blocks: (B:30:0x00e7, B:32:0x00eb, B:37:0x00f5, B:40:0x0103, B:44:0x0119, B:46:0x0122, B:53:0x0140, B:54:0x0143, B:13:0x004d, B:41:0x010e, B:43:0x0116, B:50:0x013b, B:51:0x013e), top: B:67:0x004d, inners: #5 }] */
        /* JADX WARN: Type inference failed for: r10v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r10v14 */
        /* JADX WARN: Type inference failed for: r10v15 */
        /* JADX WARN: Type inference failed for: r10v3 */
        /* JADX WARN: Type inference failed for: r10v4 */
        /* JADX WARN: Type inference failed for: r10v5, types: [java.lang.Object] */
        /* JADX WARN: Type inference failed for: r10v9 */
        /* JADX WARN: Type inference failed for: r11v1, types: [java.lang.Object, kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r11v2 */
        /* JADX WARN: Type inference failed for: r11v5 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) throws java.lang.Throwable {
            /*
                Method dump skipped, instruction units count: 347
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.runtime.SnapshotStateKt__SnapshotFlowKt.C08111.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public static final <T> Flow<T> snapshotFlow(Function0<? extends T> function0) {
        return FlowKt.flow(new C08111(function0, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean intersects$SnapshotStateKt__SnapshotFlowKt(MutableScatterSet<Object> mutableScatterSet, Set<? extends Object> set) {
        MutableScatterSet<Object> mutableScatterSet2 = mutableScatterSet;
        Object[] objArr = mutableScatterSet2.elements;
        long[] jArr = mutableScatterSet2.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return false;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128 && set.contains(objArr[(i << 3) + i3])) {
                        return true;
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return false;
                }
            }
            if (i == length) {
                return false;
            }
            i++;
        }
    }
}
