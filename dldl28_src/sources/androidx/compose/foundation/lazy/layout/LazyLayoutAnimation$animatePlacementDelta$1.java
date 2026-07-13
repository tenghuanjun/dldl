package androidx.compose.foundation.lazy.layout;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector2D;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.SpringSpec;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: LazyLayoutAnimation.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1", f = "LazyLayoutAnimation.kt", i = {0}, l = {127, KeyBoardKey.KeyboardKeyF22}, m = "invokeSuspend", n = {"finalSpec"}, s = {"L$0"})
final class LazyLayoutAnimation$animatePlacementDelta$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ FiniteAnimationSpec<IntOffset> $spec;
    final /* synthetic */ long $totalDelta;
    Object L$0;
    int label;
    final /* synthetic */ LazyLayoutAnimation this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    LazyLayoutAnimation$animatePlacementDelta$1(LazyLayoutAnimation lazyLayoutAnimation, FiniteAnimationSpec<IntOffset> finiteAnimationSpec, long j, Continuation<? super LazyLayoutAnimation$animatePlacementDelta$1> continuation) {
        super(2, continuation);
        this.this$0 = lazyLayoutAnimation;
        this.$spec = finiteAnimationSpec;
        this.$totalDelta = j;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new LazyLayoutAnimation$animatePlacementDelta$1(this.this$0, this.$spec, this.$totalDelta, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LazyLayoutAnimation$animatePlacementDelta$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        SpringSpec springSpec;
        FiniteAnimationSpec<IntOffset> finiteAnimationSpec;
        SpringSpec springSpec2;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.placementDeltaAnimation.isRunning()) {
                FiniteAnimationSpec<IntOffset> finiteAnimationSpec2 = this.$spec;
                if (!(finiteAnimationSpec2 instanceof SpringSpec)) {
                    springSpec2 = LazyLayoutAnimationKt.InterruptionSpec;
                } else {
                    springSpec2 = (SpringSpec) finiteAnimationSpec2;
                }
                springSpec = springSpec2;
            } else {
                springSpec = this.$spec;
            }
            finiteAnimationSpec = springSpec;
            if (!this.this$0.placementDeltaAnimation.isRunning()) {
                this.L$0 = finiteAnimationSpec;
                this.label = 1;
                if (this.this$0.placementDeltaAnimation.snapTo(IntOffset.m6005boximpl(this.$totalDelta), this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else {
            if (i != 1) {
                if (i != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.setPlacementAnimationInProgress(false);
                return Unit.INSTANCE;
            }
            finiteAnimationSpec = (FiniteAnimationSpec) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        long packedValue = ((IntOffset) this.this$0.placementDeltaAnimation.getValue()).getPackedValue();
        long j = this.$totalDelta;
        final long jIntOffset = IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(packedValue) - IntOffset.m6014getXimpl(j), IntOffset.m6015getYimpl(packedValue) - IntOffset.m6015getYimpl(j));
        Animatable animatable = this.this$0.placementDeltaAnimation;
        IntOffset intOffsetM6005boximpl = IntOffset.m6005boximpl(jIntOffset);
        FiniteAnimationSpec<IntOffset> finiteAnimationSpec3 = finiteAnimationSpec;
        final LazyLayoutAnimation lazyLayoutAnimation = this.this$0;
        this.L$0 = null;
        this.label = 2;
        if (Animatable.animateTo$default(animatable, intOffsetM6005boximpl, finiteAnimationSpec3, null, new Function1<Animatable<IntOffset, AnimationVector2D>, Unit>() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutAnimation$animatePlacementDelta$1.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Animatable<IntOffset, AnimationVector2D> animatable2) {
                invoke2(animatable2);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Animatable<IntOffset, AnimationVector2D> animatable2) {
                LazyLayoutAnimation lazyLayoutAnimation2 = lazyLayoutAnimation;
                long packedValue2 = animatable2.getValue().getPackedValue();
                long j2 = jIntOffset;
                lazyLayoutAnimation2.m745setPlacementDeltagyyYBs(IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(packedValue2) - IntOffset.m6014getXimpl(j2), IntOffset.m6015getYimpl(packedValue2) - IntOffset.m6015getYimpl(j2)));
            }
        }, this, 4, null) == coroutine_suspended) {
            return coroutine_suspended;
        }
        this.this$0.setPlacementAnimationInProgress(false);
        return Unit.INSTANCE;
    }
}
