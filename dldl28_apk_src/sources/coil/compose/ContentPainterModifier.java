package coil.compose;

import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.DrawModifier;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.geometry.SizeKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutModifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactorKt;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.platform.InspectorValueInfo;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;

/* JADX INFO: compiled from: ContentPainterModifier.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0082\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0080\b\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u0003B/\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0010H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013J\t\u0010\u0014\u001a\u00020\u0005HÂ\u0003J\t\u0010\u0015\u001a\u00020\u0007HÂ\u0003J\t\u0010\u0016\u001a\u00020\tHÂ\u0003J\t\u0010\u0017\u001a\u00020\u000bHÂ\u0003J\u000b\u0010\u0018\u001a\u0004\u0018\u00010\rHÂ\u0003J=\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\rHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dHÖ\u0003J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001J\u001d\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020!H\u0002ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b#\u0010\u0013J\t\u0010$\u001a\u00020%HÖ\u0001J\f\u0010&\u001a\u00020'*\u00020(H\u0016J\u001c\u0010)\u001a\u00020\u001f*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001fH\u0016J\u001c\u0010.\u001a\u00020\u001f*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010/\u001a\u00020\u001fH\u0016J)\u00100\u001a\u000201*\u0002022\u0006\u0010+\u001a\u0002032\u0006\u0010\"\u001a\u00020!H\u0016ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b4\u00105J\u001c\u00106\u001a\u00020\u001f*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001fH\u0016J\u001c\u00107\u001a\u00020\u001f*\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010/\u001a\u00020\u001fH\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00068"}, d2 = {"Lcoil/compose/ContentPainterModifier;", "Landroidx/compose/ui/layout/LayoutModifier;", "Landroidx/compose/ui/draw/DrawModifier;", "Landroidx/compose/ui/platform/InspectorValueInfo;", "painter", "Landroidx/compose/ui/graphics/painter/Painter;", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "(Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;)V", "calculateScaledSize", "Landroidx/compose/ui/geometry/Size;", "dstSize", "calculateScaledSize-E7KxVPU", "(J)J", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "modifyConstraints", "Landroidx/compose/ui/unit/Constraints;", "constraints", "modifyConstraints-ZezNO4M", "toString", "", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "maxIntrinsicHeight", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "measurable", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "width", "maxIntrinsicWidth", "height", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicHeight", "minIntrinsicWidth", "coil-compose-base_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ContentPainterModifier extends InspectorValueInfo implements LayoutModifier, DrawModifier {
    private final Alignment alignment;
    private final float alpha;
    private final ColorFilter colorFilter;
    private final ContentScale contentScale;
    private final Painter painter;

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final Painter getPainter() {
        return this.painter;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final Alignment getAlignment() {
        return this.alignment;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    private final ContentScale getContentScale() {
        return this.contentScale;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    private final float getAlpha() {
        return this.alpha;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    private final ColorFilter getColorFilter() {
        return this.colorFilter;
    }

    public static /* synthetic */ ContentPainterModifier copy$default(ContentPainterModifier contentPainterModifier, Painter painter, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, Object obj) {
        if ((i & 1) != 0) {
            painter = contentPainterModifier.painter;
        }
        if ((i & 2) != 0) {
            alignment = contentPainterModifier.alignment;
        }
        Alignment alignment2 = alignment;
        if ((i & 4) != 0) {
            contentScale = contentPainterModifier.contentScale;
        }
        ContentScale contentScale2 = contentScale;
        if ((i & 8) != 0) {
            f = contentPainterModifier.alpha;
        }
        float f2 = f;
        if ((i & 16) != 0) {
            colorFilter = contentPainterModifier.colorFilter;
        }
        return contentPainterModifier.copy(painter, alignment2, contentScale2, f2, colorFilter);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public /* synthetic */ boolean all(Function1 function1) {
        return Modifier.Element.CC.$default$all(this, function1);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public /* synthetic */ boolean any(Function1 function1) {
        return Modifier.Element.CC.$default$any(this, function1);
    }

    public final ContentPainterModifier copy(Painter painter, Alignment alignment, ContentScale contentScale, float alpha, ColorFilter colorFilter) {
        return new ContentPainterModifier(painter, alignment, contentScale, alpha, colorFilter);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContentPainterModifier)) {
            return false;
        }
        ContentPainterModifier contentPainterModifier = (ContentPainterModifier) other;
        return Intrinsics.areEqual(this.painter, contentPainterModifier.painter) && Intrinsics.areEqual(this.alignment, contentPainterModifier.alignment) && Intrinsics.areEqual(this.contentScale, contentPainterModifier.contentScale) && Float.compare(this.alpha, contentPainterModifier.alpha) == 0 && Intrinsics.areEqual(this.colorFilter, contentPainterModifier.colorFilter);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public /* synthetic */ Object foldIn(Object obj, Function2 function2) {
        return Modifier.Element.CC.$default$foldIn(this, obj, function2);
    }

    @Override // androidx.compose.ui.Modifier.Element, androidx.compose.ui.Modifier
    public /* synthetic */ Object foldOut(Object obj, Function2 function2) {
        return Modifier.Element.CC.$default$foldOut(this, obj, function2);
    }

    public int hashCode() {
        int iHashCode = ((((((this.painter.hashCode() * 31) + this.alignment.hashCode()) * 31) + this.contentScale.hashCode()) * 31) + Float.floatToIntBits(this.alpha)) * 31;
        ColorFilter colorFilter = this.colorFilter;
        return iHashCode + (colorFilter == null ? 0 : colorFilter.hashCode());
    }

    @Override // androidx.compose.ui.Modifier
    public /* synthetic */ Modifier then(Modifier modifier) {
        return Modifier.CC.$default$then(this, modifier);
    }

    public String toString() {
        return "ContentPainterModifier(painter=" + this.painter + ", alignment=" + this.alignment + ", contentScale=" + this.contentScale + ", alpha=" + this.alpha + ", colorFilter=" + this.colorFilter + ')';
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo95measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(m6311modifyConstraintsZezNO4M(j));
        return MeasureScope.CC.layout$default(measureScope, placeableMo4783measureBRTryo0.getWidth(), placeableMo4783measureBRTryo0.getHeight(), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: coil.compose.ContentPainterModifier$measure$1
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
                Placeable.PlacementScope.placeRelative$default(placementScope, placeableMo4783measureBRTryo0, 0, 0, 0.0f, 4, null);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            int iMinIntrinsicWidth = intrinsicMeasurable.minIntrinsicWidth(Constraints.m5827getMaxHeightimpl(m6311modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null))));
            return Math.max(MathKt.roundToInt(Size.m3288getWidthimpl(m6310calculateScaledSizeE7KxVPU(SizeKt.Size(iMinIntrinsicWidth, i)))), iMinIntrinsicWidth);
        }
        return intrinsicMeasurable.minIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            int iMaxIntrinsicWidth = intrinsicMeasurable.maxIntrinsicWidth(Constraints.m5827getMaxHeightimpl(m6311modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, 0, 0, i, 7, null))));
            return Math.max(MathKt.roundToInt(Size.m3288getWidthimpl(m6310calculateScaledSizeE7KxVPU(SizeKt.Size(iMaxIntrinsicWidth, i)))), iMaxIntrinsicWidth);
        }
        return intrinsicMeasurable.maxIntrinsicWidth(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            int iMinIntrinsicHeight = intrinsicMeasurable.minIntrinsicHeight(Constraints.m5828getMaxWidthimpl(m6311modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null))));
            return Math.max(MathKt.roundToInt(Size.m3285getHeightimpl(m6310calculateScaledSizeE7KxVPU(SizeKt.Size(i, iMinIntrinsicHeight)))), iMinIntrinsicHeight);
        }
        return intrinsicMeasurable.minIntrinsicHeight(i);
    }

    @Override // androidx.compose.ui.layout.LayoutModifier
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        if (this.painter.getIntrinsicSize() != Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            int iMaxIntrinsicHeight = intrinsicMeasurable.maxIntrinsicHeight(Constraints.m5828getMaxWidthimpl(m6311modifyConstraintsZezNO4M(ConstraintsKt.Constraints$default(0, i, 0, 0, 13, null))));
            return Math.max(MathKt.roundToInt(Size.m3285getHeightimpl(m6310calculateScaledSizeE7KxVPU(SizeKt.Size(i, iMaxIntrinsicHeight)))), iMaxIntrinsicHeight);
        }
        return intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* JADX INFO: renamed from: calculateScaledSize-E7KxVPU, reason: not valid java name */
    private final long m6310calculateScaledSizeE7KxVPU(long dstSize) {
        if (Size.m3290isEmptyimpl(dstSize)) {
            return Size.INSTANCE.m3297getZeroNHjbRc();
        }
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            return dstSize;
        }
        float fM3288getWidthimpl = Size.m3288getWidthimpl(intrinsicSize);
        if (Float.isInfinite(fM3288getWidthimpl) || Float.isNaN(fM3288getWidthimpl)) {
            fM3288getWidthimpl = Size.m3288getWidthimpl(dstSize);
        }
        float fM3285getHeightimpl = Size.m3285getHeightimpl(intrinsicSize);
        if (Float.isInfinite(fM3285getHeightimpl) || Float.isNaN(fM3285getHeightimpl)) {
            fM3285getHeightimpl = Size.m3285getHeightimpl(dstSize);
        }
        long jSize = SizeKt.Size(fM3288getWidthimpl, fM3285getHeightimpl);
        return ScaleFactorKt.m4870timesUQTWf7w(jSize, this.contentScale.mo4774computeScaleFactorH7hwNQA(jSize, dstSize));
    }

    /* JADX INFO: renamed from: modifyConstraints-ZezNO4M, reason: not valid java name */
    private final long m6311modifyConstraintsZezNO4M(long constraints) {
        float fM5830getMinWidthimpl;
        int iM5829getMinHeightimpl;
        float fM6331constrainHeightK40F9xA;
        boolean zM5826getHasFixedWidthimpl = Constraints.m5826getHasFixedWidthimpl(constraints);
        boolean zM5825getHasFixedHeightimpl = Constraints.m5825getHasFixedHeightimpl(constraints);
        if (zM5826getHasFixedWidthimpl && zM5825getHasFixedHeightimpl) {
            return constraints;
        }
        boolean z = Constraints.m5824getHasBoundedWidthimpl(constraints) && Constraints.m5823getHasBoundedHeightimpl(constraints);
        long intrinsicSize = this.painter.getIntrinsicSize();
        if (intrinsicSize == Size.INSTANCE.m3296getUnspecifiedNHjbRc()) {
            return z ? Constraints.m5819copyZbe2FdA$default(constraints, Constraints.m5828getMaxWidthimpl(constraints), 0, Constraints.m5827getMaxHeightimpl(constraints), 0, 10, null) : constraints;
        }
        if (z && (zM5826getHasFixedWidthimpl || zM5825getHasFixedHeightimpl)) {
            fM5830getMinWidthimpl = Constraints.m5828getMaxWidthimpl(constraints);
            iM5829getMinHeightimpl = Constraints.m5827getMaxHeightimpl(constraints);
        } else {
            float fM3288getWidthimpl = Size.m3288getWidthimpl(intrinsicSize);
            float fM3285getHeightimpl = Size.m3285getHeightimpl(intrinsicSize);
            fM5830getMinWidthimpl = (Float.isInfinite(fM3288getWidthimpl) || Float.isNaN(fM3288getWidthimpl)) ? Constraints.m5830getMinWidthimpl(constraints) : UtilsKt.m6332constrainWidthK40F9xA(constraints, fM3288getWidthimpl);
            if (Float.isInfinite(fM3285getHeightimpl) || Float.isNaN(fM3285getHeightimpl)) {
                iM5829getMinHeightimpl = Constraints.m5829getMinHeightimpl(constraints);
            } else {
                fM6331constrainHeightK40F9xA = UtilsKt.m6331constrainHeightK40F9xA(constraints, fM3285getHeightimpl);
                long jM6310calculateScaledSizeE7KxVPU = m6310calculateScaledSizeE7KxVPU(SizeKt.Size(fM5830getMinWidthimpl, fM6331constrainHeightK40F9xA));
                return Constraints.m5819copyZbe2FdA$default(constraints, ConstraintsKt.m5842constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m3288getWidthimpl(jM6310calculateScaledSizeE7KxVPU))), 0, ConstraintsKt.m5841constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m3285getHeightimpl(jM6310calculateScaledSizeE7KxVPU))), 0, 10, null);
            }
        }
        fM6331constrainHeightK40F9xA = iM5829getMinHeightimpl;
        long jM6310calculateScaledSizeE7KxVPU2 = m6310calculateScaledSizeE7KxVPU(SizeKt.Size(fM5830getMinWidthimpl, fM6331constrainHeightK40F9xA));
        return Constraints.m5819copyZbe2FdA$default(constraints, ConstraintsKt.m5842constrainWidthK40F9xA(constraints, MathKt.roundToInt(Size.m3288getWidthimpl(jM6310calculateScaledSizeE7KxVPU2))), 0, ConstraintsKt.m5841constrainHeightK40F9xA(constraints, MathKt.roundToInt(Size.m3285getHeightimpl(jM6310calculateScaledSizeE7KxVPU2))), 0, 10, null);
    }

    @Override // androidx.compose.ui.draw.DrawModifier
    public void draw(ContentDrawScope contentDrawScope) {
        long jM6310calculateScaledSizeE7KxVPU = m6310calculateScaledSizeE7KxVPU(contentDrawScope.mo3973getSizeNHjbRc());
        long jMo3096alignKFBX0sM = this.alignment.mo3096alignKFBX0sM(UtilsKt.m6333toIntSizeuvyYCjk(jM6310calculateScaledSizeE7KxVPU), UtilsKt.m6333toIntSizeuvyYCjk(contentDrawScope.mo3973getSizeNHjbRc()), contentDrawScope.getLayoutDirection());
        ContentDrawScope contentDrawScope2 = contentDrawScope;
        float fM6006component1impl = IntOffset.m6006component1impl(jMo3096alignKFBX0sM);
        float fM6007component2impl = IntOffset.m6007component2impl(jMo3096alignKFBX0sM);
        contentDrawScope2.getDrawContext().getTransform().translate(fM6006component1impl, fM6007component2impl);
        this.painter.m4132drawx_KDEd0(contentDrawScope2, jM6310calculateScaledSizeE7KxVPU, this.alpha, this.colorFilter);
        contentDrawScope2.getDrawContext().getTransform().translate(-fM6006component1impl, -fM6007component2impl);
        contentDrawScope.drawContent();
    }

    public ContentPainterModifier(final Painter painter, final Alignment alignment, final ContentScale contentScale, final float f, final ColorFilter colorFilter) {
        super(InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: coil.compose.ContentPainterModifier$special$$inlined$debugInspectorInfo$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(InspectorInfo inspectorInfo) {
                invoke2(inspectorInfo);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(InspectorInfo inspectorInfo) {
                Intrinsics.checkNotNullParameter(inspectorInfo, "$this$null");
                inspectorInfo.setName(DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT);
                inspectorInfo.getProperties().set("painter", painter);
                inspectorInfo.getProperties().set("alignment", alignment);
                inspectorInfo.getProperties().set("contentScale", contentScale);
                inspectorInfo.getProperties().set("alpha", Float.valueOf(f));
                inspectorInfo.getProperties().set("colorFilter", colorFilter);
            }
        } : InspectableValueKt.getNoInspectorInfo());
        this.painter = painter;
        this.alignment = alignment;
        this.contentScale = contentScale;
        this.alpha = f;
        this.colorFilter = colorFilter;
    }
}
