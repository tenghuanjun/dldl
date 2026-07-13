package androidx.compose.ui.window;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntRect;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/* JADX INFO: compiled from: Popup.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J2\u0010\f\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H\u0016ø\u0001\u0000¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\u0004\u001a\u00020\u0005ø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\n\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0016"}, d2 = {"Landroidx/compose/ui/window/AlignmentOffsetPositionProvider;", "Landroidx/compose/ui/window/PopupPositionProvider;", "alignment", "Landroidx/compose/ui/Alignment;", IjkMediaPlayer.OnNativeInvokeListener.ARG_OFFSET, "Landroidx/compose/ui/unit/IntOffset;", "(Landroidx/compose/ui/Alignment;JLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getAlignment", "()Landroidx/compose/ui/Alignment;", "getOffset-nOcc-ac", "()J", "J", "calculatePosition", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "windowSize", "Landroidx/compose/ui/unit/IntSize;", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "popupContentSize", "calculatePosition-llwVHH4", "(Landroidx/compose/ui/unit/IntRect;JLandroidx/compose/ui/unit/LayoutDirection;J)J", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class AlignmentOffsetPositionProvider implements PopupPositionProvider {
    public static final int $stable = 0;
    private final Alignment alignment;
    private final long offset;

    public /* synthetic */ AlignmentOffsetPositionProvider(Alignment alignment, long j, DefaultConstructorMarker defaultConstructorMarker) {
        this(alignment, j);
    }

    private AlignmentOffsetPositionProvider(Alignment alignment, long j) {
        this.alignment = alignment;
        this.offset = j;
    }

    public final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: getOffset-nOcc-ac, reason: not valid java name and from getter */
    public final long getOffset() {
        return this.offset;
    }

    @Override // androidx.compose.ui.window.PopupPositionProvider
    /* JADX INFO: renamed from: calculatePosition-llwVHH4 */
    public long mo1032calculatePositionllwVHH4(IntRect anchorBounds, long windowSize, LayoutDirection layoutDirection, long popupContentSize) {
        long jMo3096alignKFBX0sM = this.alignment.mo3096alignKFBX0sM(IntSize.INSTANCE.m6061getZeroYbymL2g(), anchorBounds.m6040getSizeYbymL2g(), layoutDirection);
        long jMo3096alignKFBX0sM2 = this.alignment.mo3096alignKFBX0sM(IntSize.INSTANCE.m6061getZeroYbymL2g(), popupContentSize, layoutDirection);
        long jIntOffset = IntOffsetKt.IntOffset(-IntOffset.m6014getXimpl(jMo3096alignKFBX0sM2), -IntOffset.m6015getYimpl(jMo3096alignKFBX0sM2));
        long jIntOffset2 = IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(this.offset) * (layoutDirection == LayoutDirection.Ltr ? 1 : -1), IntOffset.m6015getYimpl(this.offset));
        long jM6042getTopLeftnOccac = anchorBounds.m6042getTopLeftnOccac();
        long jIntOffset3 = IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(jM6042getTopLeftnOccac) + IntOffset.m6014getXimpl(jMo3096alignKFBX0sM), IntOffset.m6015getYimpl(jM6042getTopLeftnOccac) + IntOffset.m6015getYimpl(jMo3096alignKFBX0sM));
        long jIntOffset4 = IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(jIntOffset3) + IntOffset.m6014getXimpl(jIntOffset), IntOffset.m6015getYimpl(jIntOffset3) + IntOffset.m6015getYimpl(jIntOffset));
        return IntOffsetKt.IntOffset(IntOffset.m6014getXimpl(jIntOffset4) + IntOffset.m6014getXimpl(jIntOffset2), IntOffset.m6015getYimpl(jIntOffset4) + IntOffset.m6015getYimpl(jIntOffset2));
    }
}
