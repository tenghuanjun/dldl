package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.ScrollScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.Dp;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;

/* JADX INFO: compiled from: LazyAnimateScroll.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nH\u0082\b\u001a2\u0010\f\u001a\u00020\b*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0080@¢\u0006\u0002\u0010\u0014\u001a\u0014\u0010\u0015\u001a\u00020\u0004*\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0006\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002¨\u0006\u0016"}, d2 = {"BoundDistance", "Landroidx/compose/ui/unit/Dp;", "F", "DEBUG", "", "MinimumDistance", "TargetDistance", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "animateScrollToItem", "Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;", "index", "", "scrollOffset", "numOfItemsForTeleport", "density", "Landroidx/compose/ui/unit/Density;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutAnimateScrollScope;IIILandroidx/compose/ui/unit/Density;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "isItemVisible", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyAnimateScrollKt {
    private static final boolean DEBUG = false;
    private static final float TargetDistance = Dp.m5882constructorimpl(2500);
    private static final float BoundDistance = Dp.m5882constructorimpl(1500);
    private static final float MinimumDistance = Dp.m5882constructorimpl(50);

    private static final void debugLog(Function0<String> function0) {
    }

    public static final boolean isItemVisible(LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i) {
        return i <= lazyLayoutAnimateScrollScope.getLastVisibleItemIndex() && lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() <= i;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2, reason: invalid class name */
    /* JADX INFO: compiled from: LazyAnimateScroll.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/foundation/gestures/ScrollScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt$animateScrollToItem$2", f = "LazyAnimateScroll.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1}, l = {KeyBoardKey.KeyboardKeyLaunchApp1, 280}, m = "invokeSuspend", n = {"$this$scroll", "loop", "anim", "loops", "targetDistancePx", "boundDistancePx", "minDistancePx", "forward", "$this$scroll"}, s = {"L$0", "L$1", "L$2", "L$3", "F$0", "F$1", "F$2", "I$0", "L$0"})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<ScrollScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Density $density;
        final /* synthetic */ int $index;
        final /* synthetic */ int $numOfItemsForTeleport;
        final /* synthetic */ int $scrollOffset;
        final /* synthetic */ LazyLayoutAnimateScrollScope $this_animateScrollToItem;
        float F$0;
        float F$1;
        float F$2;
        int I$0;
        private /* synthetic */ Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(int i, Density density, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i2, int i3, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$index = i;
            this.$density = density;
            this.$this_animateScrollToItem = lazyLayoutAnimateScrollScope;
            this.$scrollOffset = i2;
            this.$numOfItemsForTeleport = i3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$index, this.$density, this.$this_animateScrollToItem, this.$scrollOffset, this.$numOfItemsForTeleport, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(ScrollScope scrollScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(scrollScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Removed duplicated region for block: B:29:0x00c9 A[Catch: ItemFoundInScroll -> 0x01b6, TryCatch #0 {ItemFoundInScroll -> 0x01b6, blocks: (B:27:0x00c5, B:29:0x00c9, B:31:0x00d1, B:41:0x00f5, B:45:0x0135, B:49:0x0142), top: B:84:0x00c5 }] */
        /* JADX WARN: Removed duplicated region for block: B:34:0x00e3 A[Catch: ItemFoundInScroll -> 0x01ac, TRY_ENTER, TRY_LEAVE, TryCatch #2 {ItemFoundInScroll -> 0x01ac, blocks: (B:57:0x01a1, B:34:0x00e3), top: B:88:0x01a1 }] */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00f0  */
        /* JADX WARN: Removed duplicated region for block: B:43:0x0130  */
        /* JADX WARN: Removed duplicated region for block: B:44:0x0133  */
        /* JADX WARN: Removed duplicated region for block: B:47:0x013d  */
        /* JADX WARN: Removed duplicated region for block: B:48:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:55:0x019b A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:56:0x019c  */
        /* JADX WARN: Removed duplicated region for block: B:74:0x0205  */
        /* JADX WARN: Removed duplicated region for block: B:75:0x0207  */
        /* JADX WARN: Removed duplicated region for block: B:78:0x0230 A[RETURN] */
        /* JADX WARN: Type inference failed for: r12v1, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Type inference failed for: r8v15, types: [T, androidx.compose.animation.core.AnimationState] */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:56:0x019c -> B:88:0x01a1). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r37) {
            /*
                Method dump skipped, instruction units count: 602
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.lazy.layout.LazyAnimateScrollKt.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final boolean invokeSuspend$isOvershot(boolean z, LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i, int i2) {
            if (z) {
                if (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() <= i && (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() != i || lazyLayoutAnimateScrollScope.getFirstVisibleItemScrollOffset() <= i2)) {
                    return false;
                }
            } else if (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() >= i && (lazyLayoutAnimateScrollScope.getFirstVisibleItemIndex() != i || lazyLayoutAnimateScrollScope.getFirstVisibleItemScrollOffset() >= i2)) {
                return false;
            }
            return true;
        }
    }

    public static final Object animateScrollToItem(LazyLayoutAnimateScrollScope lazyLayoutAnimateScrollScope, int i, int i2, int i3, Density density, Continuation<? super Unit> continuation) {
        Object objScroll = lazyLayoutAnimateScrollScope.scroll(new AnonymousClass2(i, density, lazyLayoutAnimateScrollScope, i2, i3, null), continuation);
        return objScroll == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objScroll : Unit.INSTANCE;
    }
}
