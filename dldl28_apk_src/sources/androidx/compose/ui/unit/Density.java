package androidx.compose.ui.unit;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.unit.FontScaling;
import kotlin.Metadata;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: Density.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bg\u0018\u00002\u00020\u0001J\u0016\u0010\b\u001a\u00020\t*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\b\u001a\u00020\t*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\u0019\u0010\u0010\u001a\u00020\n*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0012J\u0019\u0010\u0010\u001a\u00020\n*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0017ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\nH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u0012J\u0016\u0010\u0019\u001a\u00020\u0003*\u00020\rH\u0017ø\u0001\u0000¢\u0006\u0004\b\u001b\u0010\u001cJ\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH\u0017J\u0016\u0010 \u001a\u00020\u0016*\u00020\u0015H\u0017ø\u0001\u0000¢\u0006\u0004\b!\u0010\u0018J\u0019\u0010\"\u001a\u00020\r*\u00020\u0003H\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010$J\u0019\u0010\"\u001a\u00020\r*\u00020\tH\u0017ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b#\u0010%R\u001a\u0010\u0002\u001a\u00020\u00038&X§\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007ø\u0001\u0002\u0082\u0002\u0011\n\u0005\b¡\u001e0\u0001\n\u0002\b!\n\u0004\b!0\u0001¨\u0006&À\u0006\u0003"}, d2 = {"Landroidx/compose/ui/unit/Density;", "Landroidx/compose/ui/unit/FontScaling;", "density", "", "getDensity$annotations", "()V", "getDensity", "()F", "roundToPx", "", "Landroidx/compose/ui/unit/Dp;", "roundToPx-0680j_4", "(F)I", "Landroidx/compose/ui/unit/TextUnit;", "roundToPx--R2X_6o", "(J)I", "toDp", "toDp-u2uoSUM", "(F)F", "(I)F", "toDpSize", "Landroidx/compose/ui/unit/DpSize;", "Landroidx/compose/ui/geometry/Size;", "toDpSize-k-rfVVM", "(J)J", "toPx", "toPx-0680j_4", "toPx--R2X_6o", "(J)F", "toRect", "Landroidx/compose/ui/geometry/Rect;", "Landroidx/compose/ui/unit/DpRect;", "toSize", "toSize-XkaWNTQ", "toSp", "toSp-kPz2Gy4", "(F)J", "(I)J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface Density extends FontScaling {
    float getDensity();

    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    int mo338roundToPxR2X_6o(long j);

    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    int mo339roundToPx0680j_4(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo341toDpu2uoSUM(float f);

    /* JADX INFO: renamed from: toDp-u2uoSUM */
    float mo342toDpu2uoSUM(int i);

    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    long mo343toDpSizekrfVVM(long j);

    /* JADX INFO: renamed from: toPx--R2X_6o */
    float mo344toPxR2X_6o(long j);

    /* JADX INFO: renamed from: toPx-0680j_4 */
    float mo345toPx0680j_4(float f);

    Rect toRect(DpRect dpRect);

    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    long mo346toSizeXkaWNTQ(long j);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo348toSpkPz2Gy4(float f);

    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    long mo349toSpkPz2Gy4(int i);

    /* JADX INFO: renamed from: androidx.compose.ui.unit.Density$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: Density.kt */
    public final /* synthetic */ class CC {
        /* JADX INFO: renamed from: $default$toPx-0680j_4, reason: not valid java name */
        public static float m5852$default$toPx0680j_4(Density _this, float f) {
            return f * _this.getDensity();
        }

        /* JADX INFO: renamed from: $default$roundToPx-0680j_4, reason: not valid java name */
        public static int m5847$default$roundToPx0680j_4(Density _this, float f) {
            float fMo345toPx0680j_4 = _this.mo345toPx0680j_4(f);
            if (Float.isInfinite(fMo345toPx0680j_4)) {
                return Integer.MAX_VALUE;
            }
            return MathKt.roundToInt(fMo345toPx0680j_4);
        }

        /* JADX INFO: renamed from: $default$toPx--R2X_6o, reason: not valid java name */
        public static float m5851$default$toPxR2X_6o(Density _this, long j) {
            if (!TextUnitType.m6105equalsimpl0(TextUnit.m6076getTypeUIouoOA(j), TextUnitType.INSTANCE.m6110getSpUIouoOA())) {
                throw new IllegalStateException("Only Sp can convert to Px".toString());
            }
            return _this.mo345toPx0680j_4(_this.mo340toDpGaN1DYA(j));
        }

        /* JADX INFO: renamed from: $default$roundToPx--R2X_6o, reason: not valid java name */
        public static int m5846$default$roundToPxR2X_6o(Density _this, long j) {
            return MathKt.roundToInt(_this.mo344toPxR2X_6o(j));
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m5849$default$toDpu2uoSUM(Density _this, int i) {
            return Dp.m5882constructorimpl(i / _this.getDensity());
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m5855$default$toSpkPz2Gy4(Density _this, int i) {
            return _this.mo347toSp0xMU5do(_this.mo342toDpu2uoSUM(i));
        }

        /* JADX INFO: renamed from: $default$toDp-u2uoSUM, reason: not valid java name */
        public static float m5848$default$toDpu2uoSUM(Density _this, float f) {
            return Dp.m5882constructorimpl(f / _this.getDensity());
        }

        /* JADX INFO: renamed from: $default$toSp-kPz2Gy4, reason: not valid java name */
        public static long m5854$default$toSpkPz2Gy4(Density _this, float f) {
            return _this.mo347toSp0xMU5do(_this.mo341toDpu2uoSUM(f));
        }

        public static Rect $default$toRect(Density _this, DpRect dpRect) {
            return new Rect(_this.mo345toPx0680j_4(dpRect.m5965getLeftD9Ej5fM()), _this.mo345toPx0680j_4(dpRect.m5967getTopD9Ej5fM()), _this.mo345toPx0680j_4(dpRect.m5966getRightD9Ej5fM()), _this.mo345toPx0680j_4(dpRect.m5964getBottomD9Ej5fM()));
        }

        /* JADX INFO: renamed from: $default$toSize-XkaWNTQ, reason: not valid java name */
        public static long m5853$default$toSizeXkaWNTQ(Density _this, long j) {
            if (j != DpSize.INSTANCE.m5989getUnspecifiedMYxV2XQ()) {
                return SizeKt.Size(_this.mo345toPx0680j_4(DpSize.m5980getWidthD9Ej5fM(j)), _this.mo345toPx0680j_4(DpSize.m5978getHeightD9Ej5fM(j)));
            }
            return Size.INSTANCE.m3296getUnspecifiedNHjbRc();
        }

        /* JADX INFO: renamed from: $default$toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5850$default$toDpSizekrfVVM(Density _this, long j) {
            if (j != Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
                return DpKt.m5904DpSizeYgX7TsA(_this.mo341toDpu2uoSUM(Size.m3288getWidthimpl(j)), _this.mo341toDpu2uoSUM(Size.m3285getHeightimpl(j)));
            }
            return DpSize.INSTANCE.m5989getUnspecifiedMYxV2XQ();
        }
    }

    /* JADX INFO: compiled from: Density.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        public static /* synthetic */ void getDensity$annotations() {
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m5870toDpGaN1DYA(Density density, long j) {
            return FontScaling.CC.m5991$default$toDpGaN1DYA(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m5877toSp0xMU5do(Density density, float f) {
            return FontScaling.CC.m5992$default$toSp0xMU5do(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m5875toPx0680j_4(Density density, float f) {
            return CC.m5852$default$toPx0680j_4(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m5869roundToPx0680j_4(Density density, float f) {
            return CC.m5847$default$roundToPx0680j_4(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m5874toPxR2X_6o(Density density, long j) {
            return CC.m5851$default$toPxR2X_6o(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m5868roundToPxR2X_6o(Density density, long j) {
            return CC.m5846$default$roundToPxR2X_6o(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5872toDpu2uoSUM(Density density, int i) {
            return CC.m5849$default$toDpu2uoSUM(density, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5879toSpkPz2Gy4(Density density, int i) {
            return CC.m5855$default$toSpkPz2Gy4(density, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m5871toDpu2uoSUM(Density density, float f) {
            return CC.m5848$default$toDpu2uoSUM(density, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m5878toSpkPz2Gy4(Density density, float f) {
            return CC.m5854$default$toSpkPz2Gy4(density, f);
        }

        @Deprecated
        public static Rect toRect(Density density, DpRect dpRect) {
            return CC.$default$toRect(density, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m5876toSizeXkaWNTQ(Density density, long j) {
            return CC.m5853$default$toSizeXkaWNTQ(density, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m5873toDpSizekrfVVM(Density density, long j) {
            return CC.m5850$default$toDpSizekrfVVM(density, j);
        }
    }
}
