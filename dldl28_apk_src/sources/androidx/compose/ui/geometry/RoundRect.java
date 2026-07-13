package androidx.compose.ui.geometry;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: RoundRect.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0087\b\u0018\u0000 >2\u00020\u0001:\u0001>BM\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\b\u0012\b\b\u0002\u0010\n\u001a\u00020\b\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0002\u0010\fJ\t\u0010\u001d\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001f\u001a\u00020\u0003HÆ\u0003J\t\u0010 \u001a\u00020\u0003HÆ\u0003J\u0016\u0010!\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010\u0011J\u0016\u0010#\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b$\u0010\u0011J\u0016\u0010%\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b&\u0010\u0011J\u0016\u0010'\u001a\u00020\bHÆ\u0003ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b(\u0010\u0011J\u001b\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020,H\u0086\u0002ø\u0001\u0000¢\u0006\u0004\b-\u0010.Jc\u0010/\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\bHÆ\u0001ø\u0001\u0000¢\u0006\u0004\b0\u00101J\u0013\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u00104\u001a\u000205HÖ\u0001J(\u00106\u001a\u00020\u00032\u0006\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u00020\u00032\u0006\u00109\u001a\u00020\u00032\u0006\u0010:\u001a\u00020\u0003H\u0002J\b\u0010;\u001a\u00020\u0000H\u0002J\b\u0010<\u001a\u00020=H\u0016R\u0010\u0010\r\u001a\u0004\u0018\u00010\u0000X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0019\u0010\u000b\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\n\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0013\u0010\u0011R\u0011\u0010\u0014\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000fR\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u000fR\u0019\u0010\u0007\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u0019\u0010\u0011R\u0019\u0010\t\u001a\u00020\bø\u0001\u0000ø\u0001\u0001¢\u0006\n\n\u0002\u0010\u0012\u001a\u0004\b\u001a\u0010\u0011R\u0011\u0010\u001b\u001a\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u000f\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006?"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect;", "", "left", "", "top", "right", "bottom", "topLeftCornerRadius", "Landroidx/compose/ui/geometry/CornerRadius;", "topRightCornerRadius", "bottomRightCornerRadius", "bottomLeftCornerRadius", "(FFFFJJJJLkotlin/jvm/internal/DefaultConstructorMarker;)V", "_scaledRadiiRect", "getBottom", "()F", "getBottomLeftCornerRadius-kKHJgLs", "()J", "J", "getBottomRightCornerRadius-kKHJgLs", "height", "getHeight", "getLeft", "getRight", "getTop", "getTopLeftCornerRadius-kKHJgLs", "getTopRightCornerRadius-kKHJgLs", "width", "getWidth", "component1", "component2", "component3", "component4", "component5", "component5-kKHJgLs", "component6", "component6-kKHJgLs", "component7", "component7-kKHJgLs", "component8", "component8-kKHJgLs", "contains", "", "point", "Landroidx/compose/ui/geometry/Offset;", "contains-k-4lQ0M", "(J)Z", "copy", "copy-MDFrsts", "(FFFFJJJJ)Landroidx/compose/ui/geometry/RoundRect;", "equals", "other", "hashCode", "", "minRadius", "min", "radius1", "radius2", "limit", "scaledRadiiRect", "toString", "", "Companion", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class RoundRect {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final RoundRect Zero = RoundRectKt.m3273RoundRectgG7oq9Y(0.0f, 0.0f, 0.0f, 0.0f, CornerRadius.INSTANCE.m3204getZerokKHJgLs());
    private RoundRect _scaledRadiiRect;
    private final float bottom;
    private final long bottomLeftCornerRadius;
    private final long bottomRightCornerRadius;
    private final float left;
    private final float right;
    private final float top;
    private final long topLeftCornerRadius;
    private final long topRightCornerRadius;

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, j, j2, j3, j4);
    }

    public static final RoundRect getZero() {
        return INSTANCE.getZero();
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final float getLeft() {
        return this.left;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final float getTop() {
        return this.top;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final float getRight() {
        return this.right;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final float getBottom() {
        return this.bottom;
    }

    /* JADX INFO: renamed from: component5-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopLeftCornerRadius() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: component6-kKHJgLs, reason: not valid java name and from getter */
    public final long getTopRightCornerRadius() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: component7-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomRightCornerRadius() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: component8-kKHJgLs, reason: not valid java name and from getter */
    public final long getBottomLeftCornerRadius() {
        return this.bottomLeftCornerRadius;
    }

    /* JADX INFO: renamed from: copy-MDFrsts, reason: not valid java name */
    public final RoundRect m3266copyMDFrsts(float left, float top, float right, float bottom, long topLeftCornerRadius, long topRightCornerRadius, long bottomRightCornerRadius, long bottomLeftCornerRadius) {
        return new RoundRect(left, top, right, bottom, topLeftCornerRadius, topRightCornerRadius, bottomRightCornerRadius, bottomLeftCornerRadius, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof RoundRect)) {
            return false;
        }
        RoundRect roundRect = (RoundRect) other;
        return Float.compare(this.left, roundRect.left) == 0 && Float.compare(this.top, roundRect.top) == 0 && Float.compare(this.right, roundRect.right) == 0 && Float.compare(this.bottom, roundRect.bottom) == 0 && CornerRadius.m3193equalsimpl0(this.topLeftCornerRadius, roundRect.topLeftCornerRadius) && CornerRadius.m3193equalsimpl0(this.topRightCornerRadius, roundRect.topRightCornerRadius) && CornerRadius.m3193equalsimpl0(this.bottomRightCornerRadius, roundRect.bottomRightCornerRadius) && CornerRadius.m3193equalsimpl0(this.bottomLeftCornerRadius, roundRect.bottomLeftCornerRadius);
    }

    public int hashCode() {
        return (((((((((((((Float.floatToIntBits(this.left) * 31) + Float.floatToIntBits(this.top)) * 31) + Float.floatToIntBits(this.right)) * 31) + Float.floatToIntBits(this.bottom)) * 31) + CornerRadius.m3196hashCodeimpl(this.topLeftCornerRadius)) * 31) + CornerRadius.m3196hashCodeimpl(this.topRightCornerRadius)) * 31) + CornerRadius.m3196hashCodeimpl(this.bottomRightCornerRadius)) * 31) + CornerRadius.m3196hashCodeimpl(this.bottomLeftCornerRadius);
    }

    private RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4) {
        this.left = f;
        this.top = f2;
        this.right = f3;
        this.bottom = f4;
        this.topLeftCornerRadius = j;
        this.topRightCornerRadius = j2;
        this.bottomRightCornerRadius = j3;
        this.bottomLeftCornerRadius = j4;
    }

    public final float getLeft() {
        return this.left;
    }

    public final float getTop() {
        return this.top;
    }

    public final float getRight() {
        return this.right;
    }

    public final float getBottom() {
        return this.bottom;
    }

    public /* synthetic */ RoundRect(float f, float f2, float f3, float f4, long j, long j2, long j3, long j4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(f, f2, f3, f4, (i & 16) != 0 ? CornerRadius.INSTANCE.m3204getZerokKHJgLs() : j, (i & 32) != 0 ? CornerRadius.INSTANCE.m3204getZerokKHJgLs() : j2, (i & 64) != 0 ? CornerRadius.INSTANCE.m3204getZerokKHJgLs() : j3, (i & 128) != 0 ? CornerRadius.INSTANCE.m3204getZerokKHJgLs() : j4, null);
    }

    /* JADX INFO: renamed from: getTopLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3269getTopLeftCornerRadiuskKHJgLs() {
        return this.topLeftCornerRadius;
    }

    /* JADX INFO: renamed from: getTopRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3270getTopRightCornerRadiuskKHJgLs() {
        return this.topRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomRightCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3268getBottomRightCornerRadiuskKHJgLs() {
        return this.bottomRightCornerRadius;
    }

    /* JADX INFO: renamed from: getBottomLeftCornerRadius-kKHJgLs, reason: not valid java name */
    public final long m3267getBottomLeftCornerRadiuskKHJgLs() {
        return this.bottomLeftCornerRadius;
    }

    public final float getWidth() {
        return this.right - this.left;
    }

    public final float getHeight() {
        return this.bottom - this.top;
    }

    private final RoundRect scaledRadiiRect() {
        RoundRect roundRect = this._scaledRadiiRect;
        if (roundRect != null) {
            return roundRect;
        }
        float fMinRadius = minRadius(minRadius(minRadius(minRadius(1.0f, CornerRadius.m3195getYimpl(this.bottomLeftCornerRadius), CornerRadius.m3195getYimpl(this.topLeftCornerRadius), getHeight()), CornerRadius.m3194getXimpl(this.topLeftCornerRadius), CornerRadius.m3194getXimpl(this.topRightCornerRadius), getWidth()), CornerRadius.m3195getYimpl(this.topRightCornerRadius), CornerRadius.m3195getYimpl(this.bottomRightCornerRadius), getHeight()), CornerRadius.m3194getXimpl(this.bottomRightCornerRadius), CornerRadius.m3194getXimpl(this.bottomLeftCornerRadius), getWidth());
        RoundRect roundRect2 = new RoundRect(this.left * fMinRadius, this.top * fMinRadius, this.right * fMinRadius, this.bottom * fMinRadius, CornerRadiusKt.CornerRadius(CornerRadius.m3194getXimpl(this.topLeftCornerRadius) * fMinRadius, CornerRadius.m3195getYimpl(this.topLeftCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3194getXimpl(this.topRightCornerRadius) * fMinRadius, CornerRadius.m3195getYimpl(this.topRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3194getXimpl(this.bottomRightCornerRadius) * fMinRadius, CornerRadius.m3195getYimpl(this.bottomRightCornerRadius) * fMinRadius), CornerRadiusKt.CornerRadius(CornerRadius.m3194getXimpl(this.bottomLeftCornerRadius) * fMinRadius, CornerRadius.m3195getYimpl(this.bottomLeftCornerRadius) * fMinRadius), null);
        this._scaledRadiiRect = roundRect2;
        return roundRect2;
    }

    private final float minRadius(float min, float radius1, float radius2, float limit) {
        float f = radius1 + radius2;
        return (f <= limit || f == 0.0f) ? min : Math.min(min, limit / f);
    }

    /* JADX INFO: renamed from: contains-k-4lQ0M, reason: not valid java name */
    public final boolean m3265containsk4lQ0M(long point) {
        float fM3219getXimpl;
        float fM3220getYimpl;
        float fM3194getXimpl;
        float fM3195getYimpl;
        if (Offset.m3219getXimpl(point) < this.left || Offset.m3219getXimpl(point) >= this.right || Offset.m3220getYimpl(point) < this.top || Offset.m3220getYimpl(point) >= this.bottom) {
            return false;
        }
        RoundRect roundRectScaledRadiiRect = scaledRadiiRect();
        if (Offset.m3219getXimpl(point) < this.left + CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius) && Offset.m3220getYimpl(point) < this.top + CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius)) {
            fM3219getXimpl = (Offset.m3219getXimpl(point) - this.left) - CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM3220getYimpl = (Offset.m3220getYimpl(point) - this.top) - CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM3194getXimpl = CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
            fM3195getYimpl = CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topLeftCornerRadius);
        } else if (Offset.m3219getXimpl(point) > this.right - CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topRightCornerRadius) && Offset.m3220getYimpl(point) < this.top + CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topRightCornerRadius)) {
            fM3219getXimpl = (Offset.m3219getXimpl(point) - this.right) + CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM3220getYimpl = (Offset.m3220getYimpl(point) - this.top) - CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM3194getXimpl = CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.topRightCornerRadius);
            fM3195getYimpl = CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.topRightCornerRadius);
        } else if (Offset.m3219getXimpl(point) > this.right - CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius) && Offset.m3220getYimpl(point) > this.bottom - CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius)) {
            fM3219getXimpl = (Offset.m3219getXimpl(point) - this.right) + CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM3220getYimpl = (Offset.m3220getYimpl(point) - this.bottom) + CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM3194getXimpl = CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
            fM3195getYimpl = CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomRightCornerRadius);
        } else {
            if (Offset.m3219getXimpl(point) >= this.left + CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius) || Offset.m3220getYimpl(point) <= this.bottom - CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius)) {
                return true;
            }
            fM3219getXimpl = (Offset.m3219getXimpl(point) - this.left) - CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM3220getYimpl = (Offset.m3220getYimpl(point) - this.bottom) + CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM3194getXimpl = CornerRadius.m3194getXimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
            fM3195getYimpl = CornerRadius.m3195getYimpl(roundRectScaledRadiiRect.bottomLeftCornerRadius);
        }
        float f = fM3219getXimpl / fM3194getXimpl;
        float f2 = fM3220getYimpl / fM3195getYimpl;
        return (f * f) + (f2 * f2) <= 1.0f;
    }

    public String toString() {
        long j = this.topLeftCornerRadius;
        long j2 = this.topRightCornerRadius;
        long j3 = this.bottomRightCornerRadius;
        long j4 = this.bottomLeftCornerRadius;
        String str = GeometryUtilsKt.toStringAsFixed(this.left, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.top, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.right, 1) + ", " + GeometryUtilsKt.toStringAsFixed(this.bottom, 1);
        if (!CornerRadius.m3193equalsimpl0(j, j2) || !CornerRadius.m3193equalsimpl0(j2, j3) || !CornerRadius.m3193equalsimpl0(j3, j4)) {
            return "RoundRect(rect=" + str + ", topLeft=" + ((Object) CornerRadius.m3200toStringimpl(j)) + ", topRight=" + ((Object) CornerRadius.m3200toStringimpl(j2)) + ", bottomRight=" + ((Object) CornerRadius.m3200toStringimpl(j3)) + ", bottomLeft=" + ((Object) CornerRadius.m3200toStringimpl(j4)) + ')';
        }
        if (CornerRadius.m3194getXimpl(j) == CornerRadius.m3195getYimpl(j)) {
            return "RoundRect(rect=" + str + ", radius=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m3194getXimpl(j), 1) + ')';
        }
        return "RoundRect(rect=" + str + ", x=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m3194getXimpl(j), 1) + ", y=" + GeometryUtilsKt.toStringAsFixed(CornerRadius.m3195getYimpl(j), 1) + ')';
    }

    /* JADX INFO: compiled from: RoundRect.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/compose/ui/geometry/RoundRect$Companion;", "", "()V", "Zero", "Landroidx/compose/ui/geometry/RoundRect;", "getZero$annotations", "getZero", "()Landroidx/compose/ui/geometry/RoundRect;", "ui-geometry_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @JvmStatic
        public static /* synthetic */ void getZero$annotations() {
        }

        private Companion() {
        }

        public final RoundRect getZero() {
            return RoundRect.Zero;
        }
    }
}
