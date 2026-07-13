package androidx.compose.ui.input.pointer;

import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.unit.IntSize;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

/* JADX INFO: compiled from: PointerEvent.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\n\u0010\u0003\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0004\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0001*\u00020\u0002\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0002H\u0007\u001a\f\u0010\t\u001a\u00020\b*\u00020\u0002H\u0007\u001a\f\u0010\n\u001a\u00020\b*\u00020\u0002H\u0007\u001a\u001e\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\rH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a$\u0010\u000b\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013\u001a\u000f\u0010\u0014\u001a\u00020\u0015*\u00020\u0002¢\u0006\u0002\u0010\u0016\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0002H\u0007\u001a\u000f\u0010\u0018\u001a\u00020\u0015*\u00020\u0002¢\u0006\u0002\u0010\u0016\u001a\u001b\u0010\u0019\u001a\u00020\u0015*\u00020\u00022\b\b\u0002\u0010\u001a\u001a\u00020\u0001H\u0002¢\u0006\u0002\u0010\u001b\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u001d\u001a\u00020\u0001*\u00020\u0002\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001e"}, d2 = {"anyChangeConsumed", "", "Landroidx/compose/ui/input/pointer/PointerInputChange;", "changedToDown", "changedToDownIgnoreConsumed", "changedToUp", "changedToUpIgnoreConsumed", "consumeAllChanges", "", "consumeDownChange", "consumePositionChange", "isOutOfBounds", "size", "Landroidx/compose/ui/unit/IntSize;", "isOutOfBounds-O0kMr_c", "(Landroidx/compose/ui/input/pointer/PointerInputChange;J)Z", "extendedTouchPadding", "Landroidx/compose/ui/geometry/Size;", "isOutOfBounds-jwHxaWs", "(Landroidx/compose/ui/input/pointer/PointerInputChange;JJ)Z", "positionChange", "Landroidx/compose/ui/geometry/Offset;", "(Landroidx/compose/ui/input/pointer/PointerInputChange;)J", "positionChangeConsumed", "positionChangeIgnoreConsumed", "positionChangeInternal", "ignoreConsumed", "(Landroidx/compose/ui/input/pointer/PointerInputChange;Z)J", "positionChanged", "positionChangedIgnoreConsumed", "ui_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PointerEventKt {
    public static final boolean changedToDown(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || pointerInputChange.getPreviousPressed() || !pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToDownIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !pointerInputChange.getPreviousPressed() && pointerInputChange.getPressed();
    }

    public static final boolean changedToUp(PointerInputChange pointerInputChange) {
        return (pointerInputChange.isConsumed() || !pointerInputChange.getPreviousPressed() || pointerInputChange.getPressed()) ? false : true;
    }

    public static final boolean changedToUpIgnoreConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.getPreviousPressed() && !pointerInputChange.getPressed();
    }

    public static final boolean positionChanged(PointerInputChange pointerInputChange) {
        return !Offset.m3216equalsimpl0(positionChangeInternal(pointerInputChange, false), Offset.INSTANCE.m3235getZeroF1C5BW0());
    }

    public static final boolean positionChangedIgnoreConsumed(PointerInputChange pointerInputChange) {
        return !Offset.m3216equalsimpl0(positionChangeInternal(pointerInputChange, true), Offset.INSTANCE.m3235getZeroF1C5BW0());
    }

    public static final long positionChange(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, false);
    }

    public static final long positionChangeIgnoreConsumed(PointerInputChange pointerInputChange) {
        return positionChangeInternal(pointerInputChange, true);
    }

    static /* synthetic */ long positionChangeInternal$default(PointerInputChange pointerInputChange, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        return positionChangeInternal(pointerInputChange, z);
    }

    private static final long positionChangeInternal(PointerInputChange pointerInputChange, boolean z) {
        long jM3223minusMKHz9U = Offset.m3223minusMKHz9U(pointerInputChange.getPosition(), pointerInputChange.getPreviousPosition());
        return (z || !pointerInputChange.isConsumed()) ? jM3223minusMKHz9U : Offset.INSTANCE.m3235getZeroF1C5BW0();
    }

    @Deprecated(message = "Partial consumption has been deprecated. Use isConsumed instead", replaceWith = @ReplaceWith(expression = "isConsumed", imports = {}))
    public static final boolean positionChangeConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.isConsumed();
    }

    @Deprecated(message = "Partial consumption has been deprecated. Use isConsumed instead", replaceWith = @ReplaceWith(expression = "isConsumed", imports = {}))
    public static final boolean anyChangeConsumed(PointerInputChange pointerInputChange) {
        return pointerInputChange.isConsumed();
    }

    @Deprecated(message = "Partial consumption has been deprecated. Use consume() instead.", replaceWith = @ReplaceWith(expression = "if (pressed != previousPressed) consume()", imports = {}))
    public static final void consumeDownChange(PointerInputChange pointerInputChange) {
        if (pointerInputChange.getPressed() != pointerInputChange.getPreviousPressed()) {
            pointerInputChange.consume();
        }
    }

    @Deprecated(message = "Partial consumption has been deprecated. Use consume() instead.", replaceWith = @ReplaceWith(expression = "if (positionChange() != Offset.Zero) consume()", imports = {}))
    public static final void consumePositionChange(PointerInputChange pointerInputChange) {
        if (Offset.m3216equalsimpl0(positionChange(pointerInputChange), Offset.INSTANCE.m3235getZeroF1C5BW0())) {
            return;
        }
        pointerInputChange.consume();
    }

    @Deprecated(message = "Use consume() instead", replaceWith = @ReplaceWith(expression = "consume()", imports = {}))
    public static final void consumeAllChanges(PointerInputChange pointerInputChange) {
        pointerInputChange.consume();
    }

    @Deprecated(message = "Use isOutOfBounds() that supports minimum touch target", replaceWith = @ReplaceWith(expression = "this.isOutOfBounds(size, extendedTouchPadding)", imports = {}))
    /* JADX INFO: renamed from: isOutOfBounds-O0kMr_c, reason: not valid java name */
    public static final boolean m4600isOutOfBoundsO0kMr_c(PointerInputChange pointerInputChange, long j) {
        long position = pointerInputChange.getPosition();
        float fM3219getXimpl = Offset.m3219getXimpl(position);
        float fM3220getYimpl = Offset.m3220getYimpl(position);
        return fM3219getXimpl < 0.0f || fM3219getXimpl > ((float) IntSize.m6056getWidthimpl(j)) || fM3220getYimpl < 0.0f || fM3220getYimpl > ((float) IntSize.m6055getHeightimpl(j));
    }

    /* JADX INFO: renamed from: isOutOfBounds-jwHxaWs, reason: not valid java name */
    public static final boolean m4601isOutOfBoundsjwHxaWs(PointerInputChange pointerInputChange, long j, long j2) {
        if (!PointerType.m4729equalsimpl0(pointerInputChange.getType(), PointerType.INSTANCE.m4736getTouchT8wyACA())) {
            return m4600isOutOfBoundsO0kMr_c(pointerInputChange, j);
        }
        long position = pointerInputChange.getPosition();
        float fM3219getXimpl = Offset.m3219getXimpl(position);
        float fM3220getYimpl = Offset.m3220getYimpl(position);
        return fM3219getXimpl < (-Size.m3288getWidthimpl(j2)) || fM3219getXimpl > ((float) IntSize.m6056getWidthimpl(j)) + Size.m3288getWidthimpl(j2) || fM3220getYimpl < (-Size.m3285getHeightimpl(j2)) || fM3220getYimpl > ((float) IntSize.m6055getHeightimpl(j)) + Size.m3285getHeightimpl(j2);
    }
}
