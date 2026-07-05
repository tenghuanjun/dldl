package androidx.core.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import com.igexin.push.core.d.c;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: Point.kt */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0000\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0001*\u00020\u0002H\u0086\n\u001a\r\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\u0006\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0002H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0002*\u00020\u00022\u0006\u0010\b\u001a\u00020\u0001H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0004H\u0086\n\u001a\u0015\u0010\t\u001a\u00020\u0004*\u00020\u00042\u0006\u0010\b\u001a\u00020\u0003H\u0086\n\u001a\r\u0010\n\u001a\u00020\u0002*\u00020\u0004H\u0086\b\u001a\r\u0010\u000b\u001a\u00020\u0004*\u00020\u0002H\u0086\b\u001a\r\u0010\f\u001a\u00020\u0002*\u00020\u0002H\u0086\n\u001a\r\u0010\f\u001a\u00020\u0004*\u00020\u0004H\u0086\n¨\u0006\r"}, d2 = {"component1", "", "Landroid/graphics/Point;", "", "Landroid/graphics/PointF;", "component2", "minus", c.c, "xy", "plus", "toPoint", "toPointF", "unaryMinus", "core-ktx_release"}, k = 2, mv = {1, 1, 16})
public final class PointKt {
    public static final int component1(@NotNull Point component1) {
        Intrinsics.checkParameterIsNotNull(component1, "$this$component1");
        return component1.x;
    }

    public static final int component2(@NotNull Point component2) {
        Intrinsics.checkParameterIsNotNull(component2, "$this$component2");
        return component2.y;
    }

    public static final float component1(@NotNull PointF component1) {
        Intrinsics.checkParameterIsNotNull(component1, "$this$component1");
        return component1.x;
    }

    public static final float component2(@NotNull PointF component2) {
        Intrinsics.checkParameterIsNotNull(component2, "$this$component2");
        return component2.y;
    }

    @NotNull
    public static final Point plus(@NotNull Point plus, @NotNull Point p) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        Intrinsics.checkParameterIsNotNull(p, "p");
        Point point = new Point(plus.x, plus.y);
        point.offset(p.x, p.y);
        return point;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF plus, @NotNull PointF p) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        Intrinsics.checkParameterIsNotNull(p, "p");
        PointF pointF = new PointF(plus.x, plus.y);
        pointF.offset(p.x, p.y);
        return pointF;
    }

    @NotNull
    public static final Point plus(@NotNull Point plus, int i) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        Point point = new Point(plus.x, plus.y);
        point.offset(i, i);
        return point;
    }

    @NotNull
    public static final PointF plus(@NotNull PointF plus, float f) {
        Intrinsics.checkParameterIsNotNull(plus, "$this$plus");
        PointF pointF = new PointF(plus.x, plus.y);
        pointF.offset(f, f);
        return pointF;
    }

    @NotNull
    public static final Point minus(@NotNull Point minus, @NotNull Point p) {
        Intrinsics.checkParameterIsNotNull(minus, "$this$minus");
        Intrinsics.checkParameterIsNotNull(p, "p");
        Point point = new Point(minus.x, minus.y);
        point.offset(-p.x, -p.y);
        return point;
    }

    @NotNull
    public static final PointF minus(@NotNull PointF minus, @NotNull PointF p) {
        Intrinsics.checkParameterIsNotNull(minus, "$this$minus");
        Intrinsics.checkParameterIsNotNull(p, "p");
        PointF pointF = new PointF(minus.x, minus.y);
        pointF.offset(-p.x, -p.y);
        return pointF;
    }

    @NotNull
    public static final Point minus(@NotNull Point minus, int i) {
        Intrinsics.checkParameterIsNotNull(minus, "$this$minus");
        Point point = new Point(minus.x, minus.y);
        int i2 = -i;
        point.offset(i2, i2);
        return point;
    }

    @NotNull
    public static final PointF minus(@NotNull PointF minus, float f) {
        Intrinsics.checkParameterIsNotNull(minus, "$this$minus");
        PointF pointF = new PointF(minus.x, minus.y);
        float f2 = -f;
        pointF.offset(f2, f2);
        return pointF;
    }

    @NotNull
    public static final Point unaryMinus(@NotNull Point unaryMinus) {
        Intrinsics.checkParameterIsNotNull(unaryMinus, "$this$unaryMinus");
        return new Point(-unaryMinus.x, -unaryMinus.y);
    }

    @NotNull
    public static final PointF unaryMinus(@NotNull PointF unaryMinus) {
        Intrinsics.checkParameterIsNotNull(unaryMinus, "$this$unaryMinus");
        return new PointF(-unaryMinus.x, -unaryMinus.y);
    }

    @NotNull
    public static final PointF toPointF(@NotNull Point toPointF) {
        Intrinsics.checkParameterIsNotNull(toPointF, "$this$toPointF");
        return new PointF(toPointF);
    }

    @NotNull
    public static final Point toPoint(@NotNull PointF toPoint) {
        Intrinsics.checkParameterIsNotNull(toPoint, "$this$toPoint");
        return new Point((int) toPoint.x, (int) toPoint.y);
    }
}
