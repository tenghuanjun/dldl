package androidx.compose.ui.geometry;

import androidx.compose.ui.util.MathHelpersKt;
import com.lzy.okgo.model.Progress;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: Offset.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001d\u0010\r\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0007¢\u0006\u0002\u0010\u0011\u001a*\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u000fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0016\u0010\u0017\u001a%\u0010\u0018\u001a\u00020\u0002*\u00020\u00022\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00020\u001aH\u0086\bø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001c\"\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006\"\u001e\u0010\u0007\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0004\u001a\u0004\b\t\u0010\u0006\"\u001e\u0010\n\u001a\u00020\u0001*\u00020\u00028FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u000b\u0010\u0004\u001a\u0004\b\f\u0010\u0006\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001d"}, d2 = {"isFinite", "", "Landroidx/compose/ui/geometry/Offset;", "isFinite-k-4lQ0M$annotations", "(J)V", "isFinite-k-4lQ0M", "(J)Z", "isSpecified", "isSpecified-k-4lQ0M$annotations", "isSpecified-k-4lQ0M", "isUnspecified", "isUnspecified-k-4lQ0M$annotations", "isUnspecified-k-4lQ0M", "Offset", "x", "", "y", "(FF)J", "lerp", "start", "stop", Progress.FRACTION, "lerp-Wko1d7g", "(JJF)J", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-3MmeM6k", "(JLkotlin/jvm/functions/Function0;)J", "ui-geometry_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class OffsetKt {
    /* JADX INFO: renamed from: isFinite-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3237isFinitek4lQ0M$annotations(long j) {
    }

    /* JADX INFO: renamed from: isSpecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3239isSpecifiedk4lQ0M$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified-k-4lQ0M$annotations, reason: not valid java name */
    public static /* synthetic */ void m3241isUnspecifiedk4lQ0M$annotations(long j) {
    }

    /* JADX INFO: renamed from: lerp-Wko1d7g, reason: not valid java name */
    public static final long m3242lerpWko1d7g(long j, long j2, float f) {
        return Offset(MathHelpersKt.lerp(Offset.m3219getXimpl(j), Offset.m3219getXimpl(j2), f), MathHelpersKt.lerp(Offset.m3220getYimpl(j), Offset.m3220getYimpl(j2), f));
    }

    /* JADX INFO: renamed from: isFinite-k-4lQ0M, reason: not valid java name */
    public static final boolean m3236isFinitek4lQ0M(long j) {
        float fM3219getXimpl = Offset.m3219getXimpl(j);
        if (!Float.isInfinite(fM3219getXimpl) && !Float.isNaN(fM3219getXimpl)) {
            float fM3220getYimpl = Offset.m3220getYimpl(j);
            if (!Float.isInfinite(fM3220getYimpl) && !Float.isNaN(fM3220getYimpl)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: renamed from: isSpecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m3238isSpecifiedk4lQ0M(long j) {
        return j != Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: isUnspecified-k-4lQ0M, reason: not valid java name */
    public static final boolean m3240isUnspecifiedk4lQ0M(long j) {
        return j == Offset.INSTANCE.m3234getUnspecifiedF1C5BW0();
    }

    /* JADX INFO: renamed from: takeOrElse-3MmeM6k, reason: not valid java name */
    public static final long m3243takeOrElse3MmeM6k(long j, Function0<Offset> function0) {
        return m3238isSpecifiedk4lQ0M(j) ? j : function0.invoke().getPackedValue();
    }

    public static final long Offset(float f, float f2) {
        return Offset.m3211constructorimpl((((long) Float.floatToRawIntBits(f2)) & 4294967295L) | (Float.floatToRawIntBits(f) << 32));
    }
}
