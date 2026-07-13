package androidx.compose.foundation.pager;

import androidx.compose.foundation.ClipScrollableContainerKt;
import androidx.compose.foundation.OverscrollEffect;
import androidx.compose.foundation.OverscrollKt;
import androidx.compose.foundation.gestures.ForEachGestureKt;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.ScrollableDefaults;
import androidx.compose.foundation.gestures.ScrollableKt;
import androidx.compose.foundation.gestures.snapping.SnapFlingBehavior;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsInfo;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsModifierLocalKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsState;
import androidx.compose.foundation.lazy.layout.LazyLayoutKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticState;
import androidx.compose.foundation.lazy.layout.LazyLayoutSemanticsKt;
import androidx.compose.foundation.lazy.layout.NearestRangeKeyIndexMap;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.collection.MutableVector;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection;
import androidx.compose.ui.input.nestedscroll.NestedScrollModifierKt;
import androidx.compose.ui.input.pointer.AwaitPointerEventScope;
import androidx.compose.ui.input.pointer.PointerInputScope;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.lzy.okgo.cache.CacheEntity;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.reflect.KProperty0;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;

/* JADX INFO: compiled from: LazyLayoutPager.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aÖ\u0001\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 21\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&H\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010(\u001a\u0081\u0001\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u0010\u0004\u001a\u00020\u000521\u0010!\u001a-\u0012\u0004\u0012\u00020#\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u00010\"¢\u0006\u0002\b%¢\u0006\u0002\b&2#\u0010\u0017\u001a\u001f\u0012\u0013\u0012\u00110\u0010¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\u0004\u0012\u00020\u001c\u0018\u00010\u00182\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00100*H\u0003¢\u0006\u0002\u0010-\u001a\u0014\u0010.\u001a\u00020\u0003*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006/"}, d2 = {"Pager", "", "modifier", "Landroidx/compose/ui/Modifier;", "state", "Landroidx/compose/foundation/pager/PagerState;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "flingBehavior", "Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;", "userScrollEnabled", "beyondBoundsPageCount", "", "pageSpacing", "Landroidx/compose/ui/unit/Dp;", "pageSize", "Landroidx/compose/foundation/pager/PageSize;", "pageNestedScrollConnection", "Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;", CacheEntity.KEY, "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "pageContent", "Lkotlin/Function2;", "Landroidx/compose/foundation/pager/PagerScope;", "page", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "Pager-fs30GE4", "(Landroidx/compose/ui/Modifier;Landroidx/compose/foundation/pager/PagerState;Landroidx/compose/foundation/layout/PaddingValues;ZLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/foundation/gestures/snapping/SnapFlingBehavior;ZIFLandroidx/compose/foundation/pager/PageSize;Landroidx/compose/ui/input/nestedscroll/NestedScrollConnection;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Lkotlin/jvm/functions/Function4;Landroidx/compose/runtime/Composer;III)V", "rememberPagerItemProviderLambda", "Lkotlin/Function0;", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "pageCount", "(Landroidx/compose/foundation/pager/PagerState;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Lkotlin/jvm/functions/Function0;", "dragDirectionDetector", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LazyLayoutPagerKt {
    /* JADX INFO: renamed from: Pager-fs30GE4, reason: not valid java name */
    public static final void m829Pagerfs30GE4(final Modifier modifier, final PagerState pagerState, final PaddingValues paddingValues, final boolean z, final Orientation orientation, final SnapFlingBehavior snapFlingBehavior, final boolean z2, int i, float f, final PageSize pageSize, final NestedScrollConnection nestedScrollConnection, final Function1<? super Integer, ? extends Object> function1, final Alignment.Horizontal horizontal, final Alignment.Vertical vertical, final Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Composer composer, final int i2, final int i3, final int i4) {
        Composer composerStartRestartGroup = composer.startRestartGroup(-301644943);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(Pager)P(5,12,1,11,6,2,13!1,10:c#ui.unit.Dp,9,8,4!1,14)101@4846L18,103@4894L134,109@5054L534,124@5619L94,128@5739L115,134@5889L49,140@6089L276,149@6485L150,155@6798L7,148@6429L478,163@7131L7,136@5944L1832:LazyLayoutPager.kt#g6yjnt");
        int i5 = (i4 & 128) != 0 ? 0 : i;
        float fM5882constructorimpl = (i4 & 256) != 0 ? Dp.m5882constructorimpl(0) : f;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-301644943, i2, i3, "androidx.compose.foundation.pager.Pager (LazyLayoutPager.kt:95)");
        }
        if (i5 < 0) {
            throw new IllegalArgumentException(("beyondBoundsPageCount should be greater than or equal to 0, you selected " + i5).toString());
        }
        OverscrollEffect overscrollEffect = ScrollableDefaults.INSTANCE.overscrollEffect(composerStartRestartGroup, 6);
        composerStartRestartGroup.startReplaceableGroup(-735094232);
        boolean zChanged = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue = composerStartRestartGroup.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$pagerItemProvider$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(pagerState.getPageCount());
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
        }
        composerStartRestartGroup.endReplaceableGroup();
        int i6 = i2 >> 3;
        int i7 = i6 & 14;
        Function0<PagerLazyLayoutItemProvider> function0RememberPagerItemProviderLambda = rememberPagerItemProviderLambda(pagerState, function4, function1, (Function0) objRememberedValue, composerStartRestartGroup, i7 | ((i3 >> 9) & 112) | ((i3 << 3) & 896));
        SnapPositionInLayout snapAlignmentStartToStart = PagerStateKt.getSnapAlignmentStartToStart();
        composerStartRestartGroup.startReplaceableGroup(-735093678);
        boolean zChanged2 = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
        if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = (Function0) new Function0<Integer>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$measurePolicy$1$1
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final Integer invoke() {
                    return Integer.valueOf(pagerState.getPageCount());
                }
            };
            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
        }
        composerStartRestartGroup.endReplaceableGroup();
        int i8 = i2 & 7168;
        int i9 = i2 >> 6;
        int i10 = i3 << 18;
        final int i11 = i5;
        Function2<LazyLayoutMeasureScope, Constraints, MeasureResult> function2M840rememberPagerMeasurePolicy121YqSk = PagerMeasurePolicyKt.m840rememberPagerMeasurePolicy121YqSk(function0RememberPagerItemProviderLambda, pagerState, paddingValues, z, orientation, i5, fM5882constructorimpl, pageSize, horizontal, vertical, snapAlignmentStartToStart, (Function0) objRememberedValue2, composerStartRestartGroup, (65520 & i2) | (i9 & 458752) | (i9 & 3670016) | (i9 & 29360128) | (234881024 & i10) | (i10 & 1879048192), 0);
        composerStartRestartGroup.startReplaceableGroup(511388516);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
        boolean zChanged3 = composerStartRestartGroup.changed(snapFlingBehavior) | composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
        if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new PagerWrapperFlingBehavior(snapFlingBehavior, pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
        }
        composerStartRestartGroup.endReplaceableGroup();
        PagerWrapperFlingBehavior pagerWrapperFlingBehavior = (PagerWrapperFlingBehavior) objRememberedValue3;
        LazyLayoutSemanticState lazyLayoutSemanticStateRememberPagerSemanticState = PagerSemanticsKt.rememberPagerSemanticState(pagerState, z, orientation == Orientation.Vertical, composerStartRestartGroup, i7 | (i9 & 112));
        composerStartRestartGroup.startReplaceableGroup(1157296644);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged4 = composerStartRestartGroup.changed(pagerState);
        Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
        if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new PagerBringIntoViewSpec(pagerState);
            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
        }
        composerStartRestartGroup.endReplaceableGroup();
        PagerBringIntoViewSpec pagerBringIntoViewSpec = (PagerBringIntoViewSpec) objRememberedValue4;
        Modifier modifierClipScrollableContainer = ClipScrollableContainerKt.clipScrollableContainer(LazyLayoutSemanticsKt.lazyLayoutSemantics(modifier.then(pagerState.getRemeasurementModifier()).then(pagerState.getAwaitLayoutModifier()), function0RememberPagerItemProviderLambda, lazyLayoutSemanticStateRememberPagerSemanticState, orientation, z2, z, composerStartRestartGroup, (i6 & 7168) | (i9 & 57344) | ((i2 << 6) & 458752)), orientation);
        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState = PagerBeyondBoundsModifierKt.rememberPagerBeyondBoundsState(pagerState, i11, composerStartRestartGroup, i7 | ((i2 >> 18) & 112));
        LazyLayoutBeyondBoundsInfo beyondBoundsInfo = pagerState.getBeyondBoundsInfo();
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Modifier modifierOverscroll = OverscrollKt.overscroll(LazyLayoutBeyondBoundsModifierLocalKt.lazyLayoutBeyondBoundsModifier(modifierClipScrollableContainer, lazyLayoutBeyondBoundsStateRememberPagerBeyondBoundsState, beyondBoundsInfo, z, (LayoutDirection) objConsume, orientation, z2, composerStartRestartGroup, (MutableVector.$stable << 6) | i8 | ((i2 << 3) & 458752) | (i2 & 3670016)), overscrollEffect);
        ScrollableDefaults scrollableDefaults = ScrollableDefaults.INSTANCE;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection2 = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection2);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LazyLayoutKt.LazyLayout(function0RememberPagerItemProviderLambda, NestedScrollModifierKt.nestedScroll$default(dragDirectionDetector(ScrollableKt.scrollable(modifierOverscroll, pagerState, orientation, overscrollEffect, z2, scrollableDefaults.reverseDirection((LayoutDirection) objConsume2, orientation, z), pagerWrapperFlingBehavior, pagerState.getInternalInteractionSource(), pagerBringIntoViewSpec), pagerState), nestedScrollConnection, null, 2, null), pagerState.getPrefetchState(), function2M840rememberPagerMeasurePolicy121YqSk, composerStartRestartGroup, 0, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final float f2 = fM5882constructorimpl;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$Pager$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                    invoke(composer2, num.intValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Composer composer2, int i12) {
                    LazyLayoutPagerKt.m829Pagerfs30GE4(modifier, pagerState, paddingValues, z, orientation, snapFlingBehavior, z2, i11, f2, pageSize, nestedScrollConnection, function1, horizontal, vertical, function4, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
                }
            });
        }
    }

    private static final Function0<PagerLazyLayoutItemProvider> rememberPagerItemProviderLambda(final PagerState pagerState, Function4<? super PagerScope, ? super Integer, ? super Composer, ? super Integer, Unit> function4, Function1<? super Integer, ? extends Object> function1, final Function0<Integer> function0, Composer composer, int i) {
        composer.startReplaceableGroup(-1372505274);
        ComposerKt.sourceInformation(composer, "C(rememberPagerItemProviderLambda)P(3,1)248@10137L33,249@10191L25,250@10228L677:LazyLayoutPager.kt#g6yjnt");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1372505274, i, -1, "androidx.compose.foundation.pager.rememberPagerItemProviderLambda (LazyLayoutPager.kt:247)");
        }
        final State stateRememberUpdatedState = SnapshotStateKt.rememberUpdatedState(function4, composer, (i >> 3) & 14);
        final State stateRememberUpdatedState2 = SnapshotStateKt.rememberUpdatedState(function1, composer, (i >> 6) & 14);
        Object[] objArr = {pagerState, stateRememberUpdatedState, stateRememberUpdatedState2, function0};
        composer.startReplaceableGroup(-568225417);
        ComposerKt.sourceInformation(composer, "CC(remember)P(1):Composables.kt#9igjgp");
        boolean zChanged = false;
        for (int i2 = 0; i2 < 4; i2++) {
            zChanged |= composer.changed(objArr[i2]);
        }
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            final State stateDerivedStateOf = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLayoutIntervalContent>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$intervalContentState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                /* JADX WARN: Multi-variable type inference failed */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLayoutIntervalContent invoke() {
                    return new PagerLayoutIntervalContent(stateRememberUpdatedState.getValue(), stateRememberUpdatedState2.getValue(), function0.invoke().intValue());
                }
            });
            final State stateDerivedStateOf2 = SnapshotStateKt.derivedStateOf(SnapshotStateKt.referentialEqualityPolicy(), new Function0<PagerLazyLayoutItemProvider>() { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$itemProviderState$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // kotlin.jvm.functions.Function0
                public final PagerLazyLayoutItemProvider invoke() {
                    PagerLayoutIntervalContent value = stateDerivedStateOf.getValue();
                    return new PagerLazyLayoutItemProvider(pagerState, value, new NearestRangeKeyIndexMap(pagerState.getNearestRange$foundation_release(), value));
                }
            });
            objRememberedValue = (KProperty0) new PropertyReference0Impl(stateDerivedStateOf2) { // from class: androidx.compose.foundation.pager.LazyLayoutPagerKt$rememberPagerItemProviderLambda$1$1
                @Override // kotlin.jvm.internal.PropertyReference0Impl, kotlin.reflect.KProperty0
                public Object get() {
                    return ((State) this.receiver).getValue();
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        KProperty0 kProperty0 = (KProperty0) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return kProperty0;
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1, reason: invalid class name */
    /* JADX INFO: compiled from: LazyLayoutPager.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/PointerInputScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1", f = "LazyLayoutPager.kt", i = {}, l = {274}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<PointerInputScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ PagerState $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(PagerState pagerState, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$state = pagerState;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$state, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(PointerInputScope pointerInputScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(pointerInputScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: LazyLayoutPager.kt */
        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
        @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1", f = "LazyLayoutPager.kt", i = {}, l = {275}, m = "invokeSuspend", n = {}, s = {})
        static final class C00451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ PointerInputScope $$this$pointerInput;
            final /* synthetic */ PagerState $state;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C00451(PointerInputScope pointerInputScope, PagerState pagerState, Continuation<? super C00451> continuation) {
                super(2, continuation);
                this.$$this$pointerInput = pointerInputScope;
                this.$state = pagerState;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C00451(this.$$this$pointerInput, this.$state, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C00451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: LazyLayoutPager.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Landroidx/compose/ui/input/pointer/AwaitPointerEventScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
            @DebugMetadata(c = "androidx.compose.foundation.pager.LazyLayoutPagerKt$dragDirectionDetector$1$1$1", f = "LazyLayoutPager.kt", i = {0, 1, 1, 1}, l = {277, 281}, m = "invokeSuspend", n = {"$this$awaitEachGesture", "$this$awaitEachGesture", "downEvent", "upEventOrCancellation"}, s = {"L$0", "L$0", "L$1", "L$2"})
            static final class C00461 extends RestrictedSuspendLambda implements Function2<AwaitPointerEventScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ PagerState $state;
                private /* synthetic */ Object L$0;
                Object L$1;
                Object L$2;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C00461(PagerState pagerState, Continuation<? super C00461> continuation) {
                    super(2, continuation);
                    this.$state = pagerState;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    C00461 c00461 = new C00461(this.$state, continuation);
                    c00461.L$0 = obj;
                    return c00461;
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(AwaitPointerEventScope awaitPointerEventScope, Continuation<? super Unit> continuation) {
                    return ((C00461) create(awaitPointerEventScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:15:0x005a  */
                /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
                /* JADX WARN: Removed duplicated region for block: B:25:0x0097  */
                /* JADX WARN: Removed duplicated region for block: B:27:0x008b A[SYNTHETIC] */
                /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006b -> B:18:0x006e). Please report as a decompilation issue!!! */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct add '--show-bad-code' argument
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r11) {
                    /*
                        r10 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r10.label
                        r2 = 2
                        r3 = 0
                        r4 = 1
                        if (r1 == 0) goto L2f
                        if (r1 == r4) goto L27
                        if (r1 != r2) goto L1f
                        java.lang.Object r1 = r10.L$2
                        androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
                        java.lang.Object r4 = r10.L$1
                        androidx.compose.ui.input.pointer.PointerInputChange r4 = (androidx.compose.ui.input.pointer.PointerInputChange) r4
                        java.lang.Object r5 = r10.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r5 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r5
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L6e
                    L1f:
                        java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r11.<init>(r0)
                        throw r11
                    L27:
                        java.lang.Object r1 = r10.L$0
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        kotlin.ResultKt.throwOnFailure(r11)
                        goto L47
                    L2f:
                        kotlin.ResultKt.throwOnFailure(r11)
                        java.lang.Object r11 = r10.L$0
                        r1 = r11
                        androidx.compose.ui.input.pointer.AwaitPointerEventScope r1 = (androidx.compose.ui.input.pointer.AwaitPointerEventScope) r1
                        androidx.compose.ui.input.pointer.PointerEventPass r11 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r5 = r10
                        kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                        r10.L$0 = r1
                        r10.label = r4
                        java.lang.Object r11 = androidx.compose.foundation.gestures.TapGestureDetectorKt.awaitFirstDown(r1, r3, r11, r5)
                        if (r11 != r0) goto L47
                        return r0
                    L47:
                        androidx.compose.ui.input.pointer.PointerInputChange r11 = (androidx.compose.ui.input.pointer.PointerInputChange) r11
                        androidx.compose.foundation.pager.PagerState r4 = r10.$state
                        androidx.compose.ui.geometry.Offset$Companion r5 = androidx.compose.ui.geometry.Offset.INSTANCE
                        long r5 = r5.m3235getZeroF1C5BW0()
                        r4.m846setUpDownDifferencek4lQ0M$foundation_release(r5)
                        r4 = 0
                        r5 = r1
                        r1 = r4
                        r4 = r11
                    L58:
                        if (r1 != 0) goto L97
                        androidx.compose.ui.input.pointer.PointerEventPass r11 = androidx.compose.ui.input.pointer.PointerEventPass.Initial
                        r6 = r10
                        kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                        r10.L$0 = r5
                        r10.L$1 = r4
                        r10.L$2 = r1
                        r10.label = r2
                        java.lang.Object r11 = r5.awaitPointerEvent(r11, r6)
                        if (r11 != r0) goto L6e
                        return r0
                    L6e:
                        androidx.compose.ui.input.pointer.PointerEvent r11 = (androidx.compose.ui.input.pointer.PointerEvent) r11
                        java.util.List r6 = r11.getChanges()
                        int r7 = r6.size()
                        r8 = 0
                    L79:
                        if (r8 >= r7) goto L8b
                        java.lang.Object r9 = r6.get(r8)
                        androidx.compose.ui.input.pointer.PointerInputChange r9 = (androidx.compose.ui.input.pointer.PointerInputChange) r9
                        boolean r9 = androidx.compose.ui.input.pointer.PointerEventKt.changedToUp(r9)
                        if (r9 != 0) goto L88
                        goto L58
                    L88:
                        int r8 = r8 + 1
                        goto L79
                    L8b:
                        java.util.List r11 = r11.getChanges()
                        java.lang.Object r11 = r11.get(r3)
                        r1 = r11
                        androidx.compose.ui.input.pointer.PointerInputChange r1 = (androidx.compose.ui.input.pointer.PointerInputChange) r1
                        goto L58
                    L97:
                        androidx.compose.foundation.pager.PagerState r11 = r10.$state
                        long r0 = r1.getPosition()
                        long r2 = r4.getPosition()
                        long r0 = androidx.compose.ui.geometry.Offset.m3223minusMKHz9U(r0, r2)
                        r11.m846setUpDownDifferencek4lQ0M$foundation_release(r0)
                        kotlin.Unit r11 = kotlin.Unit.INSTANCE
                        return r11
                    */
                    throw new UnsupportedOperationException("Method not decompiled: androidx.compose.foundation.pager.LazyLayoutPagerKt.AnonymousClass1.C00451.C00461.invokeSuspend(java.lang.Object):java.lang.Object");
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (ForEachGestureKt.awaitEachGesture(this.$$this$pointerInput, new C00461(this.$state, null), this) == coroutine_suspended) {
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

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (CoroutineScopeKt.coroutineScope(new C00451((PointerInputScope) this.L$0, this.$state, null), this) == coroutine_suspended) {
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

    private static final Modifier dragDirectionDetector(Modifier modifier, PagerState pagerState) {
        return modifier.then(SuspendingPointerInputFilterKt.pointerInput(Modifier.INSTANCE, pagerState, new AnonymousClass1(pagerState, null)));
    }
}
