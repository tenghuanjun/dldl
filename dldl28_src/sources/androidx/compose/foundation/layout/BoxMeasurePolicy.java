package androidx.compose.foundation.layout;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: Box.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u00052\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001J,\u0010\u0011\u001a\u00020\u0012*\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001aR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b"}, d2 = {"Landroidx/compose/foundation/layout/BoxMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "alignment", "Landroidx/compose/ui/Alignment;", "propagateMinConstraints", "", "(Landroidx/compose/ui/Alignment;Z)V", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "foundation-layout_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class BoxMeasurePolicy implements MeasurePolicy {
    private final Alignment alignment;
    private final boolean propagateMinConstraints;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final boolean getPropagateMinConstraints() {
        return this.propagateMinConstraints;
    }

    public static /* synthetic */ BoxMeasurePolicy copy$default(BoxMeasurePolicy boxMeasurePolicy, Alignment alignment, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            alignment = boxMeasurePolicy.alignment;
        }
        if ((i & 2) != 0) {
            z = boxMeasurePolicy.propagateMinConstraints;
        }
        return boxMeasurePolicy.copy(alignment, z);
    }

    public final BoxMeasurePolicy copy(Alignment alignment, boolean propagateMinConstraints) {
        return new BoxMeasurePolicy(alignment, propagateMinConstraints);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof BoxMeasurePolicy)) {
            return false;
        }
        BoxMeasurePolicy boxMeasurePolicy = (BoxMeasurePolicy) other;
        return Intrinsics.areEqual(this.alignment, boxMeasurePolicy.alignment) && this.propagateMinConstraints == boxMeasurePolicy.propagateMinConstraints;
    }

    public int hashCode() {
        return (this.alignment.hashCode() * 31) + UByte$$ExternalSyntheticBackport0.m(this.propagateMinConstraints);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i) {
        return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i);
    }

    public String toString() {
        return "BoxMeasurePolicy(alignment=" + this.alignment + ", propagateMinConstraints=" + this.propagateMinConstraints + ')';
    }

    public BoxMeasurePolicy(Alignment alignment, boolean z) {
        this.alignment = alignment;
        this.propagateMinConstraints = z;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo69measure3p2s80s(final MeasureScope measureScope, final List<? extends Measurable> list, long j) {
        int iM5830getMinWidthimpl;
        int iM5829getMinHeightimpl;
        Placeable placeableMo4783measureBRTryo0;
        if (list.isEmpty()) {
            return MeasureScope.CC.layout$default(measureScope, Constraints.m5830getMinWidthimpl(j), Constraints.m5829getMinHeightimpl(j), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$1
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Placeable.PlacementScope placementScope) {
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                    invoke2(placementScope);
                    return Unit.INSTANCE;
                }
            }, 4, null);
        }
        long jM5819copyZbe2FdA$default = this.propagateMinConstraints ? j : Constraints.m5819copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null);
        if (list.size() == 1) {
            final Measurable measurable = list.get(0);
            if (!BoxKt.getMatchesParentSize(measurable)) {
                placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(jM5819copyZbe2FdA$default);
                iM5830getMinWidthimpl = Math.max(Constraints.m5830getMinWidthimpl(j), placeableMo4783measureBRTryo0.getWidth());
                iM5829getMinHeightimpl = Math.max(Constraints.m5829getMinHeightimpl(j), placeableMo4783measureBRTryo0.getHeight());
            } else {
                iM5830getMinWidthimpl = Constraints.m5830getMinWidthimpl(j);
                iM5829getMinHeightimpl = Constraints.m5829getMinHeightimpl(j);
                placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(Constraints.INSTANCE.m5836fixedJhjzzOo(Constraints.m5830getMinWidthimpl(j), Constraints.m5829getMinHeightimpl(j)));
            }
            final int i = iM5830getMinWidthimpl;
            final int i2 = iM5829getMinHeightimpl;
            final Placeable placeable = placeableMo4783measureBRTryo0;
            return MeasureScope.CC.layout$default(measureScope, i, i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$2
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
                    BoxKt.placeInBox(placementScope, placeable, measurable, measureScope.getLayoutDirection(), i, i2, this.alignment);
                }
            }, 4, null);
        }
        final Placeable[] placeableArr = new Placeable[list.size()];
        final Ref.IntRef intRef = new Ref.IntRef();
        intRef.element = Constraints.m5830getMinWidthimpl(j);
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = Constraints.m5829getMinHeightimpl(j);
        int size = list.size();
        boolean z = false;
        for (int i3 = 0; i3 < size; i3++) {
            Measurable measurable2 = list.get(i3);
            if (BoxKt.getMatchesParentSize(measurable2)) {
                z = true;
            } else {
                Placeable placeableMo4783measureBRTryo02 = measurable2.mo4783measureBRTryo0(jM5819copyZbe2FdA$default);
                placeableArr[i3] = placeableMo4783measureBRTryo02;
                intRef.element = Math.max(intRef.element, placeableMo4783measureBRTryo02.getWidth());
                intRef2.element = Math.max(intRef2.element, placeableMo4783measureBRTryo02.getHeight());
            }
        }
        if (z) {
            long jConstraints = ConstraintsKt.Constraints(intRef.element != Integer.MAX_VALUE ? intRef.element : 0, intRef.element, intRef2.element != Integer.MAX_VALUE ? intRef2.element : 0, intRef2.element);
            int size2 = list.size();
            for (int i4 = 0; i4 < size2; i4++) {
                Measurable measurable3 = list.get(i4);
                if (BoxKt.getMatchesParentSize(measurable3)) {
                    placeableArr[i4] = measurable3.mo4783measureBRTryo0(jConstraints);
                }
            }
        }
        return MeasureScope.CC.layout$default(measureScope, intRef.element, intRef2.element, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.foundation.layout.BoxMeasurePolicy$measure$5
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
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
                Placeable[] placeableArr2 = placeableArr;
                List<Measurable> list2 = list;
                MeasureScope measureScope2 = measureScope;
                Ref.IntRef intRef3 = intRef;
                Ref.IntRef intRef4 = intRef2;
                BoxMeasurePolicy boxMeasurePolicy = this;
                int length = placeableArr2.length;
                int i5 = 0;
                int i6 = 0;
                while (i5 < length) {
                    Placeable placeable2 = placeableArr2[i5];
                    Intrinsics.checkNotNull(placeable2, "null cannot be cast to non-null type androidx.compose.ui.layout.Placeable");
                    BoxKt.placeInBox(placementScope, placeable2, list2.get(i6), measureScope2.getLayoutDirection(), intRef3.element, intRef4.element, boxMeasurePolicy.alignment);
                    i5++;
                    i6++;
                }
            }
        }, 4, null);
    }
}
