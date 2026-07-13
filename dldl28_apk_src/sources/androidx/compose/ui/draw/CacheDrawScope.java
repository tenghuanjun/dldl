package androidx.compose.ui.draw;

import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpRect;
import androidx.compose.ui.unit.FontScaling;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX INFO: compiled from: DrawModifier.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0000¢\u0006\u0002\u0010\u0002J\u001f\u0010\u001d\u001a\u00020\u000e2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020!0\u001f¢\u0006\u0002\b\"J\u001f\u0010#\u001a\u00020\u000e2\u0017\u0010\u001e\u001a\u0013\u0012\u0004\u0012\u00020$\u0012\u0004\u0012\u00020!0\u001f¢\u0006\u0002\b\"R\u001a\u0010\u0003\u001a\u00020\u0004X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001c\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0013\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\fR\u0011\u0010\u0015\u001a\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\u00020\u001a8Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006%"}, d2 = {"Landroidx/compose/ui/draw/CacheDrawScope;", "Landroidx/compose/ui/unit/Density;", "()V", "cacheParams", "Landroidx/compose/ui/draw/BuildDrawCacheParams;", "getCacheParams$ui_release", "()Landroidx/compose/ui/draw/BuildDrawCacheParams;", "setCacheParams$ui_release", "(Landroidx/compose/ui/draw/BuildDrawCacheParams;)V", "density", "", "getDensity", "()F", "drawResult", "Landroidx/compose/ui/draw/DrawResult;", "getDrawResult$ui_release", "()Landroidx/compose/ui/draw/DrawResult;", "setDrawResult$ui_release", "(Landroidx/compose/ui/draw/DrawResult;)V", "fontScale", "getFontScale", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getLayoutDirection", "()Landroidx/compose/ui/unit/LayoutDirection;", "size", "Landroidx/compose/ui/geometry/Size;", "getSize-NH-jbRc", "()J", "onDrawBehind", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "", "Lkotlin/ExtensionFunctionType;", "onDrawWithContent", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class CacheDrawScope implements Density {
    public static final int $stable = 0;
    private BuildDrawCacheParams cacheParams = EmptyBuildDrawCacheParams.INSTANCE;
    private DrawResult drawResult;

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx--R2X_6o */
    public /* synthetic */ int mo338roundToPxR2X_6o(long j) {
        return Density.CC.m5846$default$roundToPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: roundToPx-0680j_4 */
    public /* synthetic */ int mo339roundToPx0680j_4(float f) {
        return Density.CC.m5847$default$roundToPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toDp-GaN1DYA */
    public /* synthetic */ float mo340toDpGaN1DYA(long j) {
        return FontScaling.CC.m5991$default$toDpGaN1DYA(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo341toDpu2uoSUM(float f) {
        return Density.CC.m5848$default$toDpu2uoSUM(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDp-u2uoSUM */
    public /* synthetic */ float mo342toDpu2uoSUM(int i) {
        return Density.CC.m5849$default$toDpu2uoSUM((Density) this, i);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toDpSize-k-rfVVM */
    public /* synthetic */ long mo343toDpSizekrfVVM(long j) {
        return Density.CC.m5850$default$toDpSizekrfVVM(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx--R2X_6o */
    public /* synthetic */ float mo344toPxR2X_6o(long j) {
        return Density.CC.m5851$default$toPxR2X_6o(this, j);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toPx-0680j_4 */
    public /* synthetic */ float mo345toPx0680j_4(float f) {
        return Density.CC.m5852$default$toPx0680j_4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    public /* synthetic */ Rect toRect(DpRect dpRect) {
        return Density.CC.$default$toRect(this, dpRect);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSize-XkaWNTQ */
    public /* synthetic */ long mo346toSizeXkaWNTQ(long j) {
        return Density.CC.m5853$default$toSizeXkaWNTQ(this, j);
    }

    @Override // androidx.compose.ui.unit.FontScaling
    /* JADX INFO: renamed from: toSp-0xMU5do */
    public /* synthetic */ long mo347toSp0xMU5do(float f) {
        return FontScaling.CC.m5992$default$toSp0xMU5do(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo348toSpkPz2Gy4(float f) {
        return Density.CC.m5854$default$toSpkPz2Gy4(this, f);
    }

    @Override // androidx.compose.ui.unit.Density
    /* JADX INFO: renamed from: toSp-kPz2Gy4 */
    public /* synthetic */ long mo349toSpkPz2Gy4(int i) {
        return Density.CC.m5855$default$toSpkPz2Gy4((Density) this, i);
    }

    /* JADX INFO: renamed from: getCacheParams$ui_release, reason: from getter */
    public final BuildDrawCacheParams getCacheParams() {
        return this.cacheParams;
    }

    public final void setCacheParams$ui_release(BuildDrawCacheParams buildDrawCacheParams) {
        this.cacheParams = buildDrawCacheParams;
    }

    /* JADX INFO: renamed from: getDrawResult$ui_release, reason: from getter */
    public final DrawResult getDrawResult() {
        return this.drawResult;
    }

    public final void setDrawResult$ui_release(DrawResult drawResult) {
        this.drawResult = drawResult;
    }

    /* JADX INFO: renamed from: getSize-NH-jbRc, reason: not valid java name */
    public final long m3126getSizeNHjbRc() {
        return this.cacheParams.mo3125getSizeNHjbRc();
    }

    public final LayoutDirection getLayoutDirection() {
        return this.cacheParams.getLayoutDirection();
    }

    public final DrawResult onDrawBehind(final Function1<? super DrawScope, Unit> block) {
        return onDrawWithContent(new Function1<ContentDrawScope, Unit>() { // from class: androidx.compose.ui.draw.CacheDrawScope.onDrawBehind.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ContentDrawScope contentDrawScope) {
                invoke2(contentDrawScope);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContentDrawScope contentDrawScope) {
                block.invoke(contentDrawScope);
                contentDrawScope.drawContent();
            }
        });
    }

    public final DrawResult onDrawWithContent(Function1<? super ContentDrawScope, Unit> block) {
        DrawResult drawResult = new DrawResult(block);
        this.drawResult = drawResult;
        return drawResult;
    }

    @Override // androidx.compose.ui.unit.Density
    public float getDensity() {
        return this.cacheParams.getDensity().getDensity();
    }

    @Override // androidx.compose.ui.unit.FontScaling
    public float getFontScale() {
        return this.cacheParams.getDensity().getFontScale();
    }
}
