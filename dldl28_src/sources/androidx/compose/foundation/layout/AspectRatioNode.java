package androidx.compose.foundation.layout;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: AspectRatio.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0016\u0010\u0010\u001a\u00020\u0011*\u00020\u0012H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u001c\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u001c\u0010\u001b\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J&\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0006\u0010\u0018\u001a\u00020 2\u0006\u0010!\u001a\u00020\u0012H\u0016ø\u0001\u0000¢\u0006\u0004\b\"\u0010#J\u001c\u0010$\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u001c\u0010%\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u0016H\u0016J \u0010&\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b(\u0010)J \u0010*\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b+\u0010)J \u0010,\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010)J \u0010.\u001a\u00020\u0011*\u00020\u00122\b\b\u0002\u0010'\u001a\u00020\u0006H\u0002ø\u0001\u0000¢\u0006\u0004\b/\u0010)R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00060"}, d2 = {"Landroidx/compose/foundation/layout/AspectRatioNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "aspectRatio", "", "matchHeightConstraintsFirst", "", "(FZ)V", "getAspectRatio", "()F", "setAspectRatio", "(F)V", "getMatchHeightConstraintsFirst", "()Z", "setMatchHeightConstraintsFirst", "(Z)V", "findSize", "Landroidx/compose/ui/unit/IntSize;", "Landroidx/compose/ui/unit/Constraints;", "findSize-ToXhtMw", "(J)J", "maxIntrinsicHeight", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "tryMaxHeight", "enforceConstraints", "tryMaxHeight-JN-0ABg", "(JZ)J", "tryMaxWidth", "tryMaxWidth-JN-0ABg", "tryMinHeight", "tryMinHeight-JN-0ABg", "tryMinWidth", "tryMinWidth-JN-0ABg", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class AspectRatioNode extends Modifier.Node implements LayoutModifierNode {
    private float aspectRatio;
    private boolean matchHeightConstraintsFirst;

    public final float getAspectRatio() {
        return this.aspectRatio;
    }

    public final void setAspectRatio(float f) {
        this.aspectRatio = f;
    }

    public final boolean getMatchHeightConstraintsFirst() {
        return this.matchHeightConstraintsFirst;
    }

    public final void setMatchHeightConstraintsFirst(boolean z) {
        this.matchHeightConstraintsFirst = z;
    }

    public AspectRatioNode(float f, boolean z) {
        this.aspectRatio = f;
        this.matchHeightConstraintsFirst = z;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo121measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        long jM524findSizeToXhtMw = m524findSizeToXhtMw(j);
        if (!IntSize.m6054equalsimpl0(jM524findSizeToXhtMw, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
            j = Constraints.INSTANCE.m5836fixedJhjzzOo(IntSize.m6056getWidthimpl(jM524findSizeToXhtMw), IntSize.m6055getHeightimpl(jM524findSizeToXhtMw));
        }
        final Placeable placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(j);
        return MeasureScope.CC.layout$default(measureScope, placeableMo4783measureBRTryo0.getWidth(), placeableMo4783measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.AspectRatioNode$measure$1
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
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4783measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i * this.aspectRatio);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i * this.aspectRatio);
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i / this.aspectRatio);
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (i != Integer.MAX_VALUE) {
            return MathKt.roundToInt(i / this.aspectRatio);
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* JADX INFO: renamed from: findSize-ToXhtMw, reason: not valid java name */
    private final long m524findSizeToXhtMw(long j) {
        if (!this.matchHeightConstraintsFirst) {
            long jM528tryMaxWidthJN0ABg$default = m528tryMaxWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM528tryMaxWidthJN0ABg$default, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM528tryMaxWidthJN0ABg$default;
            }
            long jM526tryMaxHeightJN0ABg$default = m526tryMaxHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM526tryMaxHeightJN0ABg$default, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM526tryMaxHeightJN0ABg$default;
            }
            long jM532tryMinWidthJN0ABg$default = m532tryMinWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM532tryMinWidthJN0ABg$default, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM532tryMinWidthJN0ABg$default;
            }
            long jM530tryMinHeightJN0ABg$default = m530tryMinHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM530tryMinHeightJN0ABg$default, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM530tryMinHeightJN0ABg$default;
            }
            long jM527tryMaxWidthJN0ABg = m527tryMaxWidthJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM527tryMaxWidthJN0ABg, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM527tryMaxWidthJN0ABg;
            }
            long jM525tryMaxHeightJN0ABg = m525tryMaxHeightJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM525tryMaxHeightJN0ABg, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM525tryMaxHeightJN0ABg;
            }
            long jM531tryMinWidthJN0ABg = m531tryMinWidthJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM531tryMinWidthJN0ABg, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM531tryMinWidthJN0ABg;
            }
            long jM529tryMinHeightJN0ABg = m529tryMinHeightJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM529tryMinHeightJN0ABg, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM529tryMinHeightJN0ABg;
            }
        } else {
            long jM526tryMaxHeightJN0ABg$default2 = m526tryMaxHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM526tryMaxHeightJN0ABg$default2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM526tryMaxHeightJN0ABg$default2;
            }
            long jM528tryMaxWidthJN0ABg$default2 = m528tryMaxWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM528tryMaxWidthJN0ABg$default2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM528tryMaxWidthJN0ABg$default2;
            }
            long jM530tryMinHeightJN0ABg$default2 = m530tryMinHeightJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM530tryMinHeightJN0ABg$default2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM530tryMinHeightJN0ABg$default2;
            }
            long jM532tryMinWidthJN0ABg$default2 = m532tryMinWidthJN0ABg$default(this, j, false, 1, null);
            if (!IntSize.m6054equalsimpl0(jM532tryMinWidthJN0ABg$default2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM532tryMinWidthJN0ABg$default2;
            }
            long jM525tryMaxHeightJN0ABg2 = m525tryMaxHeightJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM525tryMaxHeightJN0ABg2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM525tryMaxHeightJN0ABg2;
            }
            long jM527tryMaxWidthJN0ABg2 = m527tryMaxWidthJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM527tryMaxWidthJN0ABg2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM527tryMaxWidthJN0ABg2;
            }
            long jM529tryMinHeightJN0ABg2 = m529tryMinHeightJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM529tryMinHeightJN0ABg2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM529tryMinHeightJN0ABg2;
            }
            long jM531tryMinWidthJN0ABg2 = m531tryMinWidthJN0ABg(j, false);
            if (!IntSize.m6054equalsimpl0(jM531tryMinWidthJN0ABg2, IntSize.INSTANCE.m6061getZeroYbymL2g())) {
                return jM531tryMinWidthJN0ABg2;
            }
        }
        return IntSize.INSTANCE.m6061getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxWidth-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m528tryMaxWidthJN0ABg$default(AspectRatioNode aspectRatioNode, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m527tryMaxWidthJN0ABg(j, z);
    }

    /* JADX INFO: renamed from: tryMaxWidth-JN-0ABg, reason: not valid java name */
    private final long m527tryMaxWidthJN0ABg(long j, boolean z) {
        int iRoundToInt;
        int iM5828getMaxWidthimpl = Constraints.m5828getMaxWidthimpl(j);
        if (iM5828getMaxWidthimpl != Integer.MAX_VALUE && (iRoundToInt = MathKt.roundToInt(iM5828getMaxWidthimpl / this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iM5828getMaxWidthimpl, iRoundToInt);
            if (!z || ConstraintsKt.m5843isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m6061getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMaxHeight-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m526tryMaxHeightJN0ABg$default(AspectRatioNode aspectRatioNode, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m525tryMaxHeightJN0ABg(j, z);
    }

    /* JADX INFO: renamed from: tryMaxHeight-JN-0ABg, reason: not valid java name */
    private final long m525tryMaxHeightJN0ABg(long j, boolean z) {
        int iRoundToInt;
        int iM5827getMaxHeightimpl = Constraints.m5827getMaxHeightimpl(j);
        if (iM5827getMaxHeightimpl != Integer.MAX_VALUE && (iRoundToInt = MathKt.roundToInt(iM5827getMaxHeightimpl * this.aspectRatio)) > 0) {
            long jIntSize = IntSizeKt.IntSize(iRoundToInt, iM5827getMaxHeightimpl);
            if (!z || ConstraintsKt.m5843isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m6061getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMinWidth-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m532tryMinWidthJN0ABg$default(AspectRatioNode aspectRatioNode, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m531tryMinWidthJN0ABg(j, z);
    }

    /* JADX INFO: renamed from: tryMinWidth-JN-0ABg, reason: not valid java name */
    private final long m531tryMinWidthJN0ABg(long j, boolean z) {
        int iM5830getMinWidthimpl = Constraints.m5830getMinWidthimpl(j);
        int iRoundToInt = MathKt.roundToInt(iM5830getMinWidthimpl / this.aspectRatio);
        if (iRoundToInt > 0) {
            long jIntSize = IntSizeKt.IntSize(iM5830getMinWidthimpl, iRoundToInt);
            if (!z || ConstraintsKt.m5843isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m6061getZeroYbymL2g();
    }

    /* JADX INFO: renamed from: tryMinHeight-JN-0ABg$default, reason: not valid java name */
    static /* synthetic */ long m530tryMinHeightJN0ABg$default(AspectRatioNode aspectRatioNode, long j, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        return aspectRatioNode.m529tryMinHeightJN0ABg(j, z);
    }

    /* JADX INFO: renamed from: tryMinHeight-JN-0ABg, reason: not valid java name */
    private final long m529tryMinHeightJN0ABg(long j, boolean z) {
        int iM5829getMinHeightimpl = Constraints.m5829getMinHeightimpl(j);
        int iRoundToInt = MathKt.roundToInt(iM5829getMinHeightimpl * this.aspectRatio);
        if (iRoundToInt > 0) {
            long jIntSize = IntSizeKt.IntSize(iRoundToInt, iM5829getMinHeightimpl);
            if (!z || ConstraintsKt.m5843isSatisfiedBy4WqzIAM(j, jIntSize)) {
                return jIntSize;
            }
        }
        return IntSize.INSTANCE.m6061getZeroYbymL2g();
    }
}
