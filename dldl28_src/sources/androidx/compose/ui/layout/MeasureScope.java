package androidx.compose.ui.layout;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.LookaheadCapablePlaceable;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import androidx.core.view.ViewCompat;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: MeasureScope.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001JG\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0014\b\u0002\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00050\b2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b¢\u0006\u0002\b\u000eH\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "layout", "Landroidx/compose/ui/layout/MeasureResult;", "width", "", "height", "alignmentLines", "", "Landroidx/compose/ui/layout/AlignmentLine;", "placementBlock", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public interface MeasureScope extends IntrinsicMeasureScope {
    MeasureResult layout(int width, int height, Map<AlignmentLine, Integer> alignmentLines, Function1<? super Placeable.PlacementScope, Unit> placementBlock);

    /* JADX INFO: renamed from: androidx.compose.ui.layout.MeasureScope$-CC, reason: invalid class name */
    /* JADX INFO: compiled from: MeasureScope.kt */
    public final /* synthetic */ class CC {
        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MeasureResult layout$default(MeasureScope measureScope, int i, int i2, Map map, Function1 function1, int i3, Object obj) {
            if (obj != null) {
                throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: layout");
            }
            if ((i3 & 4) != 0) {
                map = MapsKt.emptyMap();
            }
            return measureScope.layout(i, i2, map, function1);
        }

        public static MeasureResult $default$layout(MeasureScope _this, int i, int i2, Map map, Function1 function1) {
            if ((i & ViewCompat.MEASURED_STATE_MASK) == 0 && ((-16777216) & i2) == 0) {
                return new MeasureResult(i, i2, map, _this, function1) { // from class: androidx.compose.ui.layout.MeasureScope.layout.1
                    final /* synthetic */ Function1<Placeable.PlacementScope, Unit> $placementBlock;
                    final /* synthetic */ int $width;
                    private final Map<AlignmentLine, Integer> alignmentLines;
                    private final int height;
                    final /* synthetic */ MeasureScope this$0;
                    private final int width;

                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        this.$width = i;
                        this.this$0 = _this;
                        this.$placementBlock = function1;
                        this.width = i;
                        this.height = i2;
                        this.alignmentLines = map;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getWidth, reason: from getter */
                    public int get$width() {
                        return this.width;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    /* JADX INFO: renamed from: getHeight, reason: from getter */
                    public int get$height() {
                        return this.height;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public Map<AlignmentLine, Integer> getAlignmentLines() {
                        return this.alignmentLines;
                    }

                    @Override // androidx.compose.ui.layout.MeasureResult
                    public void placeChildren() {
                        MeasureScope measureScope = this.this$0;
                        if (measureScope instanceof LookaheadCapablePlaceable) {
                            this.$placementBlock.invoke(((LookaheadCapablePlaceable) measureScope).getPlacementScope());
                        } else {
                            this.$placementBlock.invoke(new SimplePlacementScope(this.$width, this.this$0.getLayoutDirection()));
                        }
                    }
                };
            }
            throw new IllegalStateException(("Size(" + i + " x " + i2 + ") is out of range. Each dimension must be between 0 and 16777215.").toString());
        }
    }

    /* JADX INFO: compiled from: MeasureScope.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public static final class DefaultImpls {
        @Deprecated
        public static boolean isLookingAhead(MeasureScope measureScope) {
            return IntrinsicMeasureScope.CC.$default$isLookingAhead(measureScope);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx--R2X_6o, reason: not valid java name */
        public static int m4816roundToPxR2X_6o(MeasureScope measureScope, long j) {
            return Density.CC.m5846$default$roundToPxR2X_6o(measureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: roundToPx-0680j_4, reason: not valid java name */
        public static int m4817roundToPx0680j_4(MeasureScope measureScope, float f) {
            return Density.CC.m5847$default$roundToPx0680j_4(measureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-GaN1DYA, reason: not valid java name */
        public static float m4818toDpGaN1DYA(MeasureScope measureScope, long j) {
            return FontScaling.CC.m5991$default$toDpGaN1DYA(measureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4819toDpu2uoSUM(MeasureScope measureScope, float f) {
            return Density.CC.m5848$default$toDpu2uoSUM(measureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDp-u2uoSUM, reason: not valid java name */
        public static float m4820toDpu2uoSUM(MeasureScope measureScope, int i) {
            return Density.CC.m5849$default$toDpu2uoSUM((Density) measureScope, i);
        }

        @Deprecated
        /* JADX INFO: renamed from: toDpSize-k-rfVVM, reason: not valid java name */
        public static long m4821toDpSizekrfVVM(MeasureScope measureScope, long j) {
            return Density.CC.m5850$default$toDpSizekrfVVM(measureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx--R2X_6o, reason: not valid java name */
        public static float m4822toPxR2X_6o(MeasureScope measureScope, long j) {
            return Density.CC.m5851$default$toPxR2X_6o(measureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toPx-0680j_4, reason: not valid java name */
        public static float m4823toPx0680j_4(MeasureScope measureScope, float f) {
            return Density.CC.m5852$default$toPx0680j_4(measureScope, f);
        }

        @Deprecated
        public static Rect toRect(MeasureScope measureScope, DpRect dpRect) {
            return Density.CC.$default$toRect(measureScope, dpRect);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSize-XkaWNTQ, reason: not valid java name */
        public static long m4824toSizeXkaWNTQ(MeasureScope measureScope, long j) {
            return Density.CC.m5853$default$toSizeXkaWNTQ(measureScope, j);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-0xMU5do, reason: not valid java name */
        public static long m4825toSp0xMU5do(MeasureScope measureScope, float f) {
            return FontScaling.CC.m5992$default$toSp0xMU5do(measureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4826toSpkPz2Gy4(MeasureScope measureScope, float f) {
            return Density.CC.m5854$default$toSpkPz2Gy4(measureScope, f);
        }

        @Deprecated
        /* JADX INFO: renamed from: toSp-kPz2Gy4, reason: not valid java name */
        public static long m4827toSpkPz2Gy4(MeasureScope measureScope, int i) {
            return Density.CC.m5855$default$toSpkPz2Gy4((Density) measureScope, i);
        }

        @Deprecated
        public static MeasureResult layout(MeasureScope measureScope, int i, int i2, Map<AlignmentLine, Integer> map, Function1<? super Placeable.PlacementScope, Unit> function1) {
            return CC.$default$layout(measureScope, i, i2, map, function1);
        }
    }
}
