package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.OffsetKt;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.node.NodeCoordinator;
import androidx.compose.ui.unit.IntSize;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: LayoutCoordinates.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0002*\u00020\u0002\u001a\u000f\u0010\u0006\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\t\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b\u001a\u000f\u0010\n\u001a\u00020\u0007*\u00020\u0002¢\u0006\u0002\u0010\b¨\u0006\u000b"}, d2 = {"boundsInParent", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/layout/LayoutCoordinates;", "boundsInRoot", "boundsInWindow", "findRootCoordinates", "positionInParent", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/layout/LayoutCoordinates;)J", "positionInRoot", "positionInWindow", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LayoutCoordinatesKt {
    public static final long positionInRoot(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4792localToRootMKHz9U(Offset.INSTANCE.m3235getZeroF1C5BW0());
    }

    public static final long positionInWindow(LayoutCoordinates layoutCoordinates) {
        return layoutCoordinates.mo4793localToWindowMKHz9U(Offset.INSTANCE.m3235getZeroF1C5BW0());
    }

    public static final Rect boundsInRoot(LayoutCoordinates layoutCoordinates) {
        return LayoutCoordinates.CC.localBoundingBoxOf$default(findRootCoordinates(layoutCoordinates), layoutCoordinates, false, 2, null);
    }

    public static final Rect boundsInWindow(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinatesFindRootCoordinates = findRootCoordinates(layoutCoordinates);
        Rect rectBoundsInRoot = boundsInRoot(layoutCoordinates);
        float fM6056getWidthimpl = IntSize.m6056getWidthimpl(layoutCoordinatesFindRootCoordinates.mo4790getSizeYbymL2g());
        float fM6055getHeightimpl = IntSize.m6055getHeightimpl(layoutCoordinatesFindRootCoordinates.mo4790getSizeYbymL2g());
        float fCoerceIn = RangesKt.coerceIn(rectBoundsInRoot.getLeft(), 0.0f, fM6056getWidthimpl);
        float fCoerceIn2 = RangesKt.coerceIn(rectBoundsInRoot.getTop(), 0.0f, fM6055getHeightimpl);
        float fCoerceIn3 = RangesKt.coerceIn(rectBoundsInRoot.getRight(), 0.0f, fM6056getWidthimpl);
        float fCoerceIn4 = RangesKt.coerceIn(rectBoundsInRoot.getBottom(), 0.0f, fM6055getHeightimpl);
        if (fCoerceIn == fCoerceIn3 || fCoerceIn2 == fCoerceIn4) {
            return Rect.INSTANCE.getZero();
        }
        long jMo4793localToWindowMKHz9U = layoutCoordinatesFindRootCoordinates.mo4793localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn, fCoerceIn2));
        long jMo4793localToWindowMKHz9U2 = layoutCoordinatesFindRootCoordinates.mo4793localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn3, fCoerceIn2));
        long jMo4793localToWindowMKHz9U3 = layoutCoordinatesFindRootCoordinates.mo4793localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn3, fCoerceIn4));
        long jMo4793localToWindowMKHz9U4 = layoutCoordinatesFindRootCoordinates.mo4793localToWindowMKHz9U(OffsetKt.Offset(fCoerceIn, fCoerceIn4));
        return new Rect(ComparisonsKt.minOf(Offset.m3219getXimpl(jMo4793localToWindowMKHz9U), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U2), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U4), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U3)), ComparisonsKt.minOf(Offset.m3220getYimpl(jMo4793localToWindowMKHz9U), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U2), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U4), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m3219getXimpl(jMo4793localToWindowMKHz9U), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U2), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U4), Offset.m3219getXimpl(jMo4793localToWindowMKHz9U3)), ComparisonsKt.maxOf(Offset.m3220getYimpl(jMo4793localToWindowMKHz9U), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U2), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U4), Offset.m3220getYimpl(jMo4793localToWindowMKHz9U3)));
    }

    public static final long positionInParent(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return parentLayoutCoordinates != null ? parentLayoutCoordinates.mo4791localPositionOfR5De75A(layoutCoordinates, Offset.INSTANCE.m3235getZeroF1C5BW0()) : Offset.INSTANCE.m3235getZeroF1C5BW0();
    }

    public static final Rect boundsInParent(LayoutCoordinates layoutCoordinates) {
        Rect rectLocalBoundingBoxOf$default;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        return (parentLayoutCoordinates == null || (rectLocalBoundingBoxOf$default = LayoutCoordinates.CC.localBoundingBoxOf$default(parentLayoutCoordinates, layoutCoordinates, false, 2, null)) == null) ? new Rect(0.0f, 0.0f, IntSize.m6056getWidthimpl(layoutCoordinates.mo4790getSizeYbymL2g()), IntSize.m6055getHeightimpl(layoutCoordinates.mo4790getSizeYbymL2g())) : rectLocalBoundingBoxOf$default;
    }

    public static final LayoutCoordinates findRootCoordinates(LayoutCoordinates layoutCoordinates) {
        LayoutCoordinates layoutCoordinates2;
        LayoutCoordinates parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        while (true) {
            LayoutCoordinates layoutCoordinates3 = parentLayoutCoordinates;
            layoutCoordinates2 = layoutCoordinates;
            layoutCoordinates = layoutCoordinates3;
            if (layoutCoordinates == null) {
                break;
            }
            parentLayoutCoordinates = layoutCoordinates.getParentLayoutCoordinates();
        }
        NodeCoordinator nodeCoordinator = layoutCoordinates2 instanceof NodeCoordinator ? (NodeCoordinator) layoutCoordinates2 : null;
        if (nodeCoordinator == null) {
            return layoutCoordinates2;
        }
        NodeCoordinator wrappedBy = nodeCoordinator.getWrappedBy();
        while (true) {
            NodeCoordinator nodeCoordinator2 = wrappedBy;
            NodeCoordinator nodeCoordinator3 = nodeCoordinator;
            nodeCoordinator = nodeCoordinator2;
            if (nodeCoordinator != null) {
                wrappedBy = nodeCoordinator.getWrappedBy();
            } else {
                return nodeCoordinator3;
            }
        }
    }
}
