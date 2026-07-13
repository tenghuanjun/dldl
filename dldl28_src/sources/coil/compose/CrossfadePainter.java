package coil.compose;

import android.os.SystemClock;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.ScaleFactorKt;
import kotlin.Metadata;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: CrossfadePainter.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B9\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bJ\u0010\u0010)\u001a\u00020\t2\u0006\u0010*\u001a\u00020 H\u0014J\u0012\u0010+\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0014J%\u0010,\u001a\u00020\u00162\u0006\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020\u0016H\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u00100J\u0018\u00101\u001a\u00020\u0016H\u0002ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b2\u0010\u0018J\u001e\u00103\u001a\u000204*\u0002052\b\u00106\u001a\u0004\u0018\u00010\u00012\u0006\u0010*\u001a\u00020 H\u0002J\f\u00107\u001a\u000204*\u000205H\u0014R/\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010\f\u001a\u0004\u0018\u00010\r8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u00020\u00168VX\u0096\u0004ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R+\u0010\u0019\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00078B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u001e\u0010\u0014\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001f\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010!\u001a\u00020 2\u0006\u0010\f\u001a\u00020 8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b&\u0010\u0014\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010'\u001a\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00068"}, d2 = {"Lcoil/compose/CrossfadePainter;", "Landroidx/compose/ui/graphics/painter/Painter;", "start", "end", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "durationMillis", "", "fadeStart", "", "preferExactIntrinsicSize", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/layout/ContentScale;IZZ)V", "<set-?>", "Landroidx/compose/ui/graphics/ColorFilter;", "colorFilter", "getColorFilter", "()Landroidx/compose/ui/graphics/ColorFilter;", "setColorFilter", "(Landroidx/compose/ui/graphics/ColorFilter;)V", "colorFilter$delegate", "Landroidx/compose/runtime/MutableState;", "intrinsicSize", "Landroidx/compose/ui/geometry/Size;", "getIntrinsicSize-NH-jbRc", "()J", "invalidateTick", "getInvalidateTick", "()I", "setInvalidateTick", "(I)V", "invalidateTick$delegate", "isDone", "", "maxAlpha", "getMaxAlpha", "()F", "setMaxAlpha", "(F)V", "maxAlpha$delegate", "startTimeMillis", "", "applyAlpha", "alpha", "applyColorFilter", "computeDrawSize", "srcSize", "dstSize", "computeDrawSize-x8L_9b0", "(JJ)J", "computeIntrinsicSize", "computeIntrinsicSize-NH-jbRc", "drawPainter", "", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "painter", "onDraw", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CrossfadePainter extends Painter {
    private final ContentScale contentScale;
    private final int durationMillis;
    private final Painter end;
    private final boolean fadeStart;
    private boolean isDone;
    private final boolean preferExactIntrinsicSize;
    private Painter start;

    /* JADX INFO: renamed from: invalidateTick$delegate, reason: from kotlin metadata */
    private final MutableState invalidateTick = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(0, null, 2, null);
    private long startTimeMillis = -1;

    /* JADX INFO: renamed from: maxAlpha$delegate, reason: from kotlin metadata */
    private final MutableState maxAlpha = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Float.valueOf(1.0f), null, 2, null);

    /* JADX INFO: renamed from: colorFilter$delegate, reason: from kotlin metadata */
    private final MutableState colorFilter = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(null, null, 2, null);

    public CrossfadePainter(Painter painter, Painter painter2, ContentScale contentScale, int i, boolean z, boolean z2) {
        this.start = painter;
        this.end = painter2;
        this.contentScale = contentScale;
        this.durationMillis = i;
        this.fadeStart = z;
        this.preferExactIntrinsicSize = z2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int getInvalidateTick() {
        return ((Number) this.invalidateTick.getValue()).intValue();
    }

    private final void setInvalidateTick(int i) {
        this.invalidateTick.setValue(Integer.valueOf(i));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final float getMaxAlpha() {
        return ((Number) this.maxAlpha.getValue()).floatValue();
    }

    private final void setMaxAlpha(float f) {
        this.maxAlpha.setValue(Float.valueOf(f));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ColorFilter getColorFilter() {
        return (ColorFilter) this.colorFilter.getValue();
    }

    private final void setColorFilter(ColorFilter colorFilter) {
        this.colorFilter.setValue(colorFilter);
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    /* JADX INFO: renamed from: getIntrinsicSize-NH-jbRc */
    public long getIntrinsicSize() {
        return m6313computeIntrinsicSizeNHjbRc();
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected void onDraw(DrawScope drawScope) {
        if (this.isDone) {
            drawPainter(drawScope, this.end, getMaxAlpha());
            return;
        }
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (this.startTimeMillis == -1) {
            this.startTimeMillis = jUptimeMillis;
        }
        float f = (jUptimeMillis - this.startTimeMillis) / this.durationMillis;
        float fCoerceIn = RangesKt.coerceIn(f, 0.0f, 1.0f) * getMaxAlpha();
        float maxAlpha = this.fadeStart ? getMaxAlpha() - fCoerceIn : getMaxAlpha();
        this.isDone = f >= 1.0f;
        drawPainter(drawScope, this.start, maxAlpha);
        drawPainter(drawScope, this.end, fCoerceIn);
        if (this.isDone) {
            this.start = null;
        } else {
            setInvalidateTick(getInvalidateTick() + 1);
        }
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyAlpha(float alpha) {
        setMaxAlpha(alpha);
        return true;
    }

    @Override // androidx.compose.ui.graphics.painter.Painter
    protected boolean applyColorFilter(ColorFilter colorFilter) {
        setColorFilter(colorFilter);
        return true;
    }

    /* JADX INFO: renamed from: computeIntrinsicSize-NH-jbRc, reason: not valid java name */
    private final long m6313computeIntrinsicSizeNHjbRc() {
        Painter painter = this.start;
        long intrinsicSize = painter != null ? painter.getIntrinsicSize() : Size.INSTANCE.m3297getZeroNHjbRc();
        Painter painter2 = this.end;
        long intrinsicSize2 = painter2 != null ? painter2.getIntrinsicSize() : Size.INSTANCE.m3297getZeroNHjbRc();
        boolean z = intrinsicSize != Size.INSTANCE.m3296getUnspecifiedNHjbRc();
        boolean z2 = intrinsicSize2 != Size.INSTANCE.m3296getUnspecifiedNHjbRc();
        if (z && z2) {
            return SizeKt.Size(Math.max(Size.m3288getWidthimpl(intrinsicSize), Size.m3288getWidthimpl(intrinsicSize2)), Math.max(Size.m3285getHeightimpl(intrinsicSize), Size.m3285getHeightimpl(intrinsicSize2)));
        }
        if (this.preferExactIntrinsicSize) {
            if (z) {
                return intrinsicSize;
            }
            if (z2) {
                return intrinsicSize2;
            }
        }
        return Size.INSTANCE.m3296getUnspecifiedNHjbRc();
    }

    private final void drawPainter(DrawScope drawScope, Painter painter, float f) {
        if (painter == null || f <= 0.0f) {
            return;
        }
        long jMo3973getSizeNHjbRc = drawScope.mo3973getSizeNHjbRc();
        long jM6312computeDrawSizex8L_9b0 = m6312computeDrawSizex8L_9b0(painter.getIntrinsicSize(), jMo3973getSizeNHjbRc);
        if (jMo3973getSizeNHjbRc == Size.INSTANCE.m3296getUnspecifiedNHjbRc() || Size.m3290isEmptyimpl(jMo3973getSizeNHjbRc)) {
            painter.m4132drawx_KDEd0(drawScope, jM6312computeDrawSizex8L_9b0, f, getColorFilter());
            return;
        }
        float f2 = 2;
        float fM3288getWidthimpl = (Size.m3288getWidthimpl(jMo3973getSizeNHjbRc) - Size.m3288getWidthimpl(jM6312computeDrawSizex8L_9b0)) / f2;
        float fM3285getHeightimpl = (Size.m3285getHeightimpl(jMo3973getSizeNHjbRc) - Size.m3285getHeightimpl(jM6312computeDrawSizex8L_9b0)) / f2;
        drawScope.getDrawContext().getTransform().inset(fM3288getWidthimpl, fM3285getHeightimpl, fM3288getWidthimpl, fM3285getHeightimpl);
        painter.m4132drawx_KDEd0(drawScope, jM6312computeDrawSizex8L_9b0, f, getColorFilter());
        float f3 = -fM3288getWidthimpl;
        float f4 = -fM3285getHeightimpl;
        drawScope.getDrawContext().getTransform().inset(f3, f4, f3, f4);
    }

    /* JADX INFO: renamed from: computeDrawSize-x8L_9b0, reason: not valid java name */
    private final long m6312computeDrawSizex8L_9b0(long srcSize, long dstSize) {
        return (srcSize == Size.INSTANCE.m3296getUnspecifiedNHjbRc() || Size.m3290isEmptyimpl(srcSize) || dstSize == Size.INSTANCE.m3296getUnspecifiedNHjbRc() || Size.m3290isEmptyimpl(dstSize)) ? dstSize : ScaleFactorKt.m4870timesUQTWf7w(srcSize, this.contentScale.mo4774computeScaleFactorH7hwNQA(srcSize, dstSize));
    }
}
