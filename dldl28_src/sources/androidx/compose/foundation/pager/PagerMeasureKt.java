package androidx.compose.foundation.pager;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayout;
import androidx.compose.foundation.gestures.snapping.SnapPositionInLayoutKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: PagerMeasure.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000¨\u0001\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a@\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u000e\u001a\u00020\u000fH\u0002\u001aH\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0016H\u0002\u001a@\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00060\u0016H\u0002\u001a\u0017\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082\b\u001a\u008c\u0001\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00060\u001f*\u00020 2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00060\n2\u0006\u0010$\u001a\u00020\b2\u0006\u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\b2\u0006\u0010'\u001a\u00020\b2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\b2\u0006\u0010/\u001a\u00020\bH\u0002\u001aj\u0010\u0015\u001a\u00020\u0006*\u00020 2\u0006\u00100\u001a\u00020\b2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u0010)\u001a\u00020*2\b\u00107\u001a\u0004\u0018\u0001082\b\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010;\u001a\u00020<2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010/\u001a\u00020\bH\u0002ø\u0001\u0000¢\u0006\u0004\b=\u0010>\u001aé\u0001\u0010?\u001a\u00020@*\u00020 2\u0006\u0010A\u001a\u00020\b2\u0006\u00103\u001a\u0002042\u0006\u0010B\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\u0006\u0010C\u001a\u00020\b2\u0006\u0010D\u001a\u00020\b2\u0006\u0010E\u001a\u0002022\u0006\u0010)\u001a\u00020*2\b\u00109\u001a\u0004\u0018\u00010:2\b\u00107\u001a\u0004\u0018\u0001082\u0006\u0010+\u001a\u00020\u00012\u0006\u00105\u001a\u0002062\u0006\u0010/\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\b2\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\n2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010F\u001a\u00020G2/\u0010H\u001a+\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020J\u0012\u0004\u0012\u00020\u001a0\u0016¢\u0006\u0002\bK\u0012\u0004\u0012\u00020L0IH\u0000ø\u0001\u0000¢\u0006\u0004\bM\u0010N\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003X\u0080T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006O"}, d2 = {"DEBUG", "", "MaxPageOffset", "", "MinPageOffset", "calculateNewCurrentPage", "Landroidx/compose/foundation/pager/MeasuredPage;", "viewportSize", "", "visiblePagesInfo", "", "beforeContentPadding", "afterContentPadding", "itemSize", "snapPositionInLayout", "Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;", "createPagesAfterList", "currentLastPage", "pagesCount", "beyondBoundsPageCount", "pinnedPages", "getAndMeasure", "Lkotlin/Function1;", "createPagesBeforeList", "currentFirstPage", "debugLog", "", "generateMsg", "Lkotlin/Function0;", "", "calculatePagesOffsets", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "pages", "extraPagesBefore", "extraPagesAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "pagesScrollOffset", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "spaceBetweenPages", "pageAvailableSize", "index", "childConstraints", "Landroidx/compose/ui/unit/Constraints;", "pagerItemProvider", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "visualPageOffset", "Landroidx/compose/ui/unit/IntOffset;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getAndMeasure-SGf7dI0", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJLandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZI)Landroidx/compose/foundation/pager/MeasuredPage;", "measurePager", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "pageCount", "mainAxisAvailableSize", "currentPage", "currentPageOffset", "constraints", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measurePager-_JDW0YA", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;IIIIIIJLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;ZJIILjava/util/List;Landroidx/compose/foundation/gestures/snapping/SnapPositionInLayout;Landroidx/compose/runtime/MutableState;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/pager/PagerMeasureResult;", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PagerMeasureKt {
    private static final boolean DEBUG = false;
    public static final float MaxPageOffset = 0.5f;
    public static final float MinPageOffset = -0.5f;

    private static final int calculatePagesOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: renamed from: measurePager-_JDW0YA, reason: not valid java name */
    public static final PagerMeasureResult m839measurePager_JDW0YA(final LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, final PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, long j, final Orientation orientation, final Alignment.Vertical vertical, final Alignment.Horizontal horizontal, final boolean z, final long j2, final int i8, int i9, List<Integer> list, SnapPositionInLayout snapPositionInLayout, final MutableState<Unit> mutableState, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        long j3;
        int i16;
        List<MeasuredPage> list2;
        int i17;
        if (i3 < 0) {
            throw new IllegalArgumentException("negative beforeContentPadding".toString());
        }
        if (i4 < 0) {
            throw new IllegalArgumentException("negative afterContentPadding".toString());
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i8 + i5, 0);
        if (i <= 0) {
            return new PagerMeasureResult(CollectionsKt.emptyList(), i8, i5, i4, orientation, -i3, i2 + i4, false, i9, null, null, 0.0f, 0, false, function3.invoke(Integer.valueOf(Constraints.m5830getMinWidthimpl(j)), Integer.valueOf(Constraints.m5829getMinHeightimpl(j)), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$4
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }), false);
        }
        final long jConstraints$default = ConstraintsKt.Constraints$default(0, orientation == Orientation.Vertical ? Constraints.m5828getMaxWidthimpl(j) : i8, 0, orientation != Orientation.Vertical ? Constraints.m5827getMaxHeightimpl(j) : i8, 5, null);
        int i18 = i6;
        int i19 = i7;
        while (i18 > 0 && i19 > 0) {
            i18--;
            i19 -= iCoerceAtLeast;
        }
        int i20 = i19 * (-1);
        if (i18 >= i) {
            i18 = i - 1;
            i20 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i21 = -i3;
        if (i5 < 0) {
            i11 = i5;
            i10 = i18;
        } else {
            i10 = i18;
            i11 = 0;
        }
        int i22 = i21 + i11;
        int iMax = 0;
        int i23 = i20 + i22;
        int i24 = i10;
        while (i23 < 0 && i24 > 0) {
            int i25 = i24 - 1;
            MeasuredPage measuredPageM838getAndMeasureSGf7dI0 = m838getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i25, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8);
            arrayDeque.add(0, measuredPageM838getAndMeasureSGf7dI0);
            iMax = Math.max(iMax, measuredPageM838getAndMeasureSGf7dI0.getCrossAxisSize());
            i23 += iCoerceAtLeast;
            i24 = i25;
        }
        if (i23 < i22) {
            i23 = i22;
        }
        int i26 = i23 - i22;
        int i27 = i2 + i4;
        int i28 = i24;
        int iCoerceAtLeast2 = RangesKt.coerceAtLeast(i27, 0);
        int i29 = i28;
        boolean z2 = false;
        int i30 = -i26;
        int i31 = 0;
        while (i31 < arrayDeque.size()) {
            if (i30 >= iCoerceAtLeast2) {
                arrayDeque.remove(i31);
                z2 = true;
            } else {
                i29++;
                i30 += iCoerceAtLeast;
                i31++;
            }
        }
        boolean z3 = z2;
        int i32 = i29;
        int i33 = i26;
        while (i32 < i && (i30 < iCoerceAtLeast2 || i30 <= 0 || arrayDeque.isEmpty())) {
            int i34 = iCoerceAtLeast2;
            MeasuredPage measuredPageM838getAndMeasureSGf7dI02 = m838getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i32, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8);
            int i35 = i - 1;
            i30 += i32 == i35 ? i8 : iCoerceAtLeast;
            if (i30 > i22 || i32 == i35) {
                iMax = Math.max(iMax, measuredPageM838getAndMeasureSGf7dI02.getCrossAxisSize());
                arrayDeque.add(measuredPageM838getAndMeasureSGf7dI02);
                i17 = i28;
            } else {
                i17 = i32 + 1;
                i33 -= iCoerceAtLeast;
                z3 = true;
            }
            i32++;
            i28 = i17;
            iCoerceAtLeast2 = i34;
        }
        if (i30 < i2) {
            int i36 = i2 - i30;
            i33 -= i36;
            i30 += i36;
            i12 = i28;
            while (i33 < i3 && i12 > 0) {
                i12--;
                MeasuredPage measuredPageM838getAndMeasureSGf7dI03 = m838getAndMeasureSGf7dI0(lazyLayoutMeasureScope, i12, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8);
                arrayDeque.add(0, measuredPageM838getAndMeasureSGf7dI03);
                iMax = Math.max(iMax, measuredPageM838getAndMeasureSGf7dI03.getCrossAxisSize());
                i33 += iCoerceAtLeast;
            }
            if (i33 < 0) {
                i30 += i33;
                i33 = 0;
            }
        } else {
            i12 = i28;
        }
        int i37 = iMax;
        int i38 = i30;
        if (i33 < 0) {
            throw new IllegalArgumentException("invalid currentFirstPageScrollOffset".toString());
        }
        int i39 = -i33;
        MeasuredPage measuredPage = (MeasuredPage) arrayDeque.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque.size();
            i13 = i37;
            int i40 = i33;
            int i41 = 0;
            while (i41 < size && i40 != 0 && iCoerceAtLeast <= i40) {
                i14 = i39;
                if (i41 == CollectionsKt.getLastIndex(arrayDeque)) {
                    break;
                }
                i40 -= iCoerceAtLeast;
                i41++;
                measuredPage = (MeasuredPage) arrayDeque.get(i41);
                i39 = i14;
            }
            i14 = i39;
            i15 = i40;
        } else {
            i15 = i33;
            i13 = i37;
            i14 = i39;
        }
        MeasuredPage measuredPage2 = measuredPage;
        List<MeasuredPage> listCreatePagesBeforeList = createPagesBeforeList(i12, i9, list, new Function1<Integer, MeasuredPage>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$extraPagesBefore$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ MeasuredPage invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final MeasuredPage invoke(int i42) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
                return PagerMeasureKt.m838getAndMeasureSGf7dI0(lazyLayoutMeasureScope2, i42, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope2.getLayoutDirection(), z, i8);
            }
        });
        int iMax2 = i13;
        int i42 = 0;
        for (int size2 = listCreatePagesBeforeList.size(); i42 < size2; size2 = size2) {
            iMax2 = Math.max(iMax2, listCreatePagesBeforeList.get(i42).getCrossAxisSize());
            i42++;
        }
        List<MeasuredPage> listCreatePagesAfterList = createPagesAfterList(((MeasuredPage) arrayDeque.last()).getIndex(), i, i9, list, new Function1<Integer, MeasuredPage>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$extraPagesAfter$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ MeasuredPage invoke(Integer num) {
                return invoke(num.intValue());
            }

            public final MeasuredPage invoke(int i43) {
                LazyLayoutMeasureScope lazyLayoutMeasureScope2 = lazyLayoutMeasureScope;
                return PagerMeasureKt.m838getAndMeasureSGf7dI0(lazyLayoutMeasureScope2, i43, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope2.getLayoutDirection(), z, i8);
            }
        });
        int size3 = listCreatePagesAfterList.size();
        for (int i43 = 0; i43 < size3; i43++) {
            iMax2 = Math.max(iMax2, listCreatePagesAfterList.get(i43).getCrossAxisSize());
        }
        boolean z4 = Intrinsics.areEqual(measuredPage2, arrayDeque.first()) && listCreatePagesBeforeList.isEmpty() && listCreatePagesAfterList.isEmpty();
        if (orientation == Orientation.Vertical) {
            j3 = j;
            i16 = iMax2;
        } else {
            j3 = j;
            i16 = i38;
        }
        int iM5842constrainWidthK40F9xA = ConstraintsKt.m5842constrainWidthK40F9xA(j3, i16);
        if (orientation == Orientation.Vertical) {
            iMax2 = i38;
        }
        int iM5841constrainHeightK40F9xA = ConstraintsKt.m5841constrainHeightK40F9xA(j3, iMax2);
        int i44 = i32;
        final List<MeasuredPage> listCalculatePagesOffsets = calculatePagesOffsets(lazyLayoutMeasureScope, arrayDeque, listCreatePagesBeforeList, listCreatePagesAfterList, iM5842constrainWidthK40F9xA, iM5841constrainHeightK40F9xA, i38, i2, i14, orientation, z, lazyLayoutMeasureScope, i5, i8);
        if (z4) {
            list2 = listCalculatePagesOffsets;
        } else {
            ArrayList arrayList = new ArrayList(listCalculatePagesOffsets.size());
            int size4 = listCalculatePagesOffsets.size();
            for (int i45 = 0; i45 < size4; i45++) {
                MeasuredPage measuredPage3 = listCalculatePagesOffsets.get(i45);
                MeasuredPage measuredPage4 = measuredPage3;
                if (measuredPage4.getIndex() >= ((MeasuredPage) arrayDeque.first()).getIndex() && measuredPage4.getIndex() <= ((MeasuredPage) arrayDeque.last()).getIndex()) {
                    arrayList.add(measuredPage3);
                }
            }
            list2 = arrayList;
        }
        MeasuredPage measuredPageCalculateNewCurrentPage = calculateNewCurrentPage(orientation == Orientation.Vertical ? iM5841constrainHeightK40F9xA : iM5842constrainWidthK40F9xA, list2, i3, i4, iCoerceAtLeast, snapPositionInLayout);
        return new PagerMeasureResult(list2, i8, i5, i4, orientation, i21, i27, z, i9, measuredPage2, measuredPageCalculateNewCurrentPage, iCoerceAtLeast == 0 ? 0.0f : RangesKt.coerceIn((-(measuredPageCalculateNewCurrentPage != null ? measuredPageCalculateNewCurrentPage.getOffset() : 0)) / iCoerceAtLeast, -0.5f, 0.5f), i15, i44 < i || i38 > i2, function3.invoke(Integer.valueOf(iM5842constrainWidthK40F9xA), Integer.valueOf(iM5841constrainHeightK40F9xA), new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.pager.PagerMeasureKt$measurePager$9
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                invoke2(placementScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Placeable.PlacementScope placementScope) {
                List<MeasuredPage> list3 = listCalculatePagesOffsets;
                int size5 = list3.size();
                for (int i46 = 0; i46 < size5; i46++) {
                    list3.get(i46).place(placementScope);
                }
                ObservableScopeInvalidator.m774attachToScopeimpl(mutableState);
            }
        }), z3);
    }

    private static final List<MeasuredPage> createPagesAfterList(int i, int i2, int i3, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        int iMin = Math.min(i3 + i, i2 - 1);
        int i4 = i + 1;
        ArrayList arrayList = null;
        if (i4 <= iMin) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(i4)));
                if (i4 == iMin) {
                    break;
                }
                i4++;
            }
        }
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            int iIntValue = list.get(i5).intValue();
            if (iMin + 1 <= iIntValue && iIntValue < i2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<MeasuredPage> createPagesBeforeList(int i, int i2, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(i3)));
                if (i3 == iMax) {
                    break;
                }
                i3--;
            }
        }
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            int iIntValue = list.get(i4).intValue();
            if (iIntValue < iMax) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: getAndMeasure-SGf7dI0, reason: not valid java name */
    public static final MeasuredPage m838getAndMeasureSGf7dI0(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, long j, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, long j2, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z, int i2) {
        return new MeasuredPage(i, i2, lazyLayoutMeasureScope.mo762measure0kLqBqw(i, j), j2, pagerLazyLayoutItemProvider.getKey(i), orientation, horizontal, vertical, layoutDirection, z, null);
    }

    private static final List<MeasuredPage> calculatePagesOffsets(LazyLayoutMeasureScope lazyLayoutMeasureScope, List<MeasuredPage> list, List<MeasuredPage> list2, List<MeasuredPage> list3, int i, int i2, int i3, int i4, int i5, Orientation orientation, boolean z, Density density, int i6, int i7) {
        int i8;
        int i9;
        int i10 = i5;
        int i11 = i7 + i6;
        if (orientation == Orientation.Vertical) {
            i8 = i4;
            i9 = i2;
        } else {
            i8 = i4;
            i9 = i;
        }
        boolean z2 = i3 < Math.min(i9, i8);
        if (z2 && i10 != 0) {
            throw new IllegalStateException(("non-zero pagesScrollOffset=" + i10).toString());
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z2) {
            if (!list2.isEmpty() || !list3.isEmpty()) {
                throw new IllegalArgumentException("No extra pages".toString());
            }
            int size = list.size();
            int[] iArr = new int[size];
            for (int i12 = 0; i12 < size; i12++) {
                iArr[i12] = i7;
            }
            int[] iArr2 = new int[size];
            for (int i13 = 0; i13 < size; i13++) {
                iArr2[i13] = 0;
            }
            Arrangement.HorizontalOrVertical horizontalOrVerticalM507spacedBy0680j_4 = Arrangement.Absolute.INSTANCE.m507spacedBy0680j_4(lazyLayoutMeasureScope.mo342toDpu2uoSUM(i6));
            if (orientation == Orientation.Vertical) {
                horizontalOrVerticalM507spacedBy0680j_4.arrange(density, i9, iArr, iArr2);
            } else {
                horizontalOrVerticalM507spacedBy0680j_4.arrange(density, i9, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntRange indices = ArraysKt.getIndices(iArr2);
            if (z) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int size2 = iArr2[first];
                    MeasuredPage measuredPage = list.get(calculatePagesOffsets$reverseAware(first, z, size));
                    if (z) {
                        size2 = (i9 - size2) - measuredPage.getSize();
                    }
                    measuredPage.position(size2, i, i2);
                    arrayList.add(measuredPage);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int i14 = i10;
            for (int i15 = 0; i15 < size3; i15++) {
                MeasuredPage measuredPage2 = list2.get(i15);
                i14 -= i11;
                measuredPage2.position(i14, i, i2);
                arrayList.add(measuredPage2);
            }
            int size4 = list.size();
            for (int i16 = 0; i16 < size4; i16++) {
                MeasuredPage measuredPage3 = list.get(i16);
                measuredPage3.position(i10, i, i2);
                arrayList.add(measuredPage3);
                i10 += i11;
            }
            int size5 = list3.size();
            for (int i17 = 0; i17 < size5; i17++) {
                MeasuredPage measuredPage4 = list3.get(i17);
                measuredPage4.position(i10, i, i2);
                arrayList.add(measuredPage4);
                i10 += i11;
            }
        }
        return arrayList;
    }

    private static final MeasuredPage calculateNewCurrentPage(int i, List<MeasuredPage> list, int i2, int i3, int i4, SnapPositionInLayout snapPositionInLayout) {
        MeasuredPage measuredPage;
        if (list.isEmpty()) {
            measuredPage = null;
        } else {
            MeasuredPage measuredPage2 = list.get(0);
            MeasuredPage measuredPage3 = measuredPage2;
            float f = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(i, i2, i3, i4, measuredPage3.getOffset(), measuredPage3.getIndex(), snapPositionInLayout));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i5 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    MeasuredPage measuredPage4 = list.get(i5);
                    MeasuredPage measuredPage5 = measuredPage4;
                    float f2 = -Math.abs(SnapPositionInLayoutKt.calculateDistanceToDesiredSnapPosition(i, i2, i3, i4, measuredPage5.getOffset(), measuredPage5.getIndex(), snapPositionInLayout));
                    if (Float.compare(f, f2) < 0) {
                        measuredPage2 = measuredPage4;
                        f = f2;
                    }
                    if (i5 == lastIndex) {
                        break;
                    }
                    i5++;
                }
            }
            measuredPage = measuredPage2;
        }
        return measuredPage;
    }
}
