package coil.compose;

import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.platform.ViewConfiguration;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import coil.ImageLoader;
import coil.compose.AsyncImagePainter;
import coil.request.ImageRequest;
import coil.size.Dimension;
import coil.size.Dimensions;
import coil.size.Size;
import coil.size.SizeResolver;
import coil.size.SizeResolvers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AsyncImage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000|\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u009d\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u000b2\u0016\b\u0002\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001aÛ\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\n\b\u0002\u0010\u001d\u001a\u0004\u0018\u00010\u001b2\u0016\b\u0002\u0010\u001e\u001a\u0010\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u0016\b\u0002\u0010 \u001a\u0010\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\u0016\b\u0002\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%\u001aI\u0010&\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u001b2\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0001¢\u0006\u0002\u0010(\u001a\u001d\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010\u0010\u001a\u00020\u0011H\u0001¢\u0006\u0002\u0010,\u001a\u0016\u0010\u0004\u001a\u00020\t*\u00020\t2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0003\u001a\u001b\u0010-\u001a\u0004\u0018\u00010.*\u00020/H\u0003ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b0\u00101\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u00062"}, d2 = {"AsyncImage", "", "model", "", "contentDescription", "", "imageLoader", "Lcoil/ImageLoader;", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", "AsyncImage-MvsnxeU", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "placeholder", "Landroidx/compose/ui/graphics/painter/Painter;", "error", "fallback", "onLoading", "Lcoil/compose/AsyncImagePainter$State$Loading;", "onSuccess", "Lcoil/compose/AsyncImagePainter$State$Success;", "onError", "Lcoil/compose/AsyncImagePainter$State$Error;", "AsyncImage-Q4Kwu38", "(Ljava/lang/Object;Ljava/lang/String;Lcoil/ImageLoader;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Landroidx/compose/ui/graphics/painter/Painter;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "Content", "painter", "(Landroidx/compose/ui/Modifier;Landroidx/compose/ui/graphics/painter/Painter;Ljava/lang/String;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;Landroidx/compose/runtime/Composer;I)V", "updateRequest", "Lcoil/request/ImageRequest;", "request", "(Lcoil/request/ImageRequest;Landroidx/compose/ui/layout/ContentScale;Landroidx/compose/runtime/Composer;I)Lcoil/request/ImageRequest;", "toSizeOrNull", "Lcoil/size/Size;", "Landroidx/compose/ui/unit/Constraints;", "toSizeOrNull-BRTryo0", "(J)Lcoil/size/Size;", "coil-compose-base_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AsyncImageKt {
    /* JADX INFO: renamed from: AsyncImage-Q4Kwu38, reason: not valid java name */
    public static final void m6297AsyncImageQ4Kwu38(Object obj, String str, ImageLoader imageLoader, Modifier modifier, Painter painter, Painter painter2, Painter painter3, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, Composer composer, int i2, int i3, int i4) {
        composer.startReplaceableGroup(-245964807);
        ComposerKt.sourceInformation(composer, "C(AsyncImage)P(9,3,8,10,14,5,6,12,13,11!1,4!,7:c#ui.graphics.FilterQuality)");
        Modifier.Companion companion = (i4 & 8) != 0 ? Modifier.INSTANCE : modifier;
        Painter painter4 = (i4 & 16) != 0 ? null : painter;
        Painter painter5 = (i4 & 32) != 0 ? null : painter2;
        Painter painter6 = (i4 & 64) != 0 ? painter5 : painter3;
        Function1<? super AsyncImagePainter.State.Loading, Unit> function14 = (i4 & 128) != 0 ? null : function1;
        Function1<? super AsyncImagePainter.State.Success, Unit> function15 = (i4 & 256) != 0 ? null : function12;
        Function1<? super AsyncImagePainter.State.Error, Unit> function16 = (i4 & 512) != 0 ? null : function13;
        Alignment center = (i4 & 1024) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i4 & 2048) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i4 & 4096) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i4 & 8192) != 0 ? null : colorFilter;
        int iM4057getDefaultFilterQualityfv9h1I = (i4 & 16384) != 0 ? DrawScope.INSTANCE.m4057getDefaultFilterQualityfv9h1I() : i;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-245964807, i2, i3, "coil.compose.AsyncImage (AsyncImage.kt:64)");
        }
        int i5 = i3 << 18;
        m6296AsyncImageMvsnxeU(obj, str, imageLoader, companion, UtilsKt.transformOf(painter4, painter5, painter6), UtilsKt.onStateOf(function14, function15, function16), center, fit, f2, colorFilter2, iM4057getDefaultFilterQualityfv9h1I, composer, (i2 & 7168) | (i2 & 112) | 520 | (3670016 & i5) | (29360128 & i5) | (234881024 & i5) | (i5 & 1879048192), (i3 >> 12) & 14, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
    }

    /* JADX INFO: renamed from: AsyncImage-MvsnxeU, reason: not valid java name */
    public static final void m6296AsyncImageMvsnxeU(final Object obj, final String str, final ImageLoader imageLoader, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, Composer composer, final int i2, final int i3, final int i4) {
        final int iM4057getDefaultFilterQualityfv9h1I;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(-2030202961);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(AsyncImage)P(7,3,6,8,10,9!1,4!,5:c#ui.graphics.FilterQuality)");
        final Modifier modifier2 = (i4 & 8) != 0 ? Modifier.INSTANCE : modifier;
        final Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform = (i4 & 16) != 0 ? AsyncImagePainter.INSTANCE.getDefaultTransform() : function1;
        final Function1<? super AsyncImagePainter.State, Unit> function13 = (i4 & 32) != 0 ? null : function12;
        final Alignment center = (i4 & 64) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        final ContentScale fit = (i4 & 128) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        final float f2 = (i4 & 256) != 0 ? 1.0f : f;
        final ColorFilter colorFilter2 = (i4 & 512) != 0 ? null : colorFilter;
        if ((i4 & 1024) != 0) {
            i5 = i3 & (-15);
            iM4057getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m4057getDefaultFilterQualityfv9h1I();
        } else {
            iM4057getDefaultFilterQualityfv9h1I = i;
            i5 = i3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-2030202961, i2, i5, "coil.compose.AsyncImage (AsyncImage.kt:118)");
        }
        ImageRequest imageRequestUpdateRequest = updateRequest(UtilsKt.requestOf(obj, composerStartRestartGroup, 8), fit, composerStartRestartGroup, 8 | ((i2 >> 18) & 112));
        int i6 = i2 >> 6;
        int i7 = i2 >> 9;
        int i8 = i7 & 57344;
        Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function14 = defaultTransform;
        Function1<? super AsyncImagePainter.State, Unit> function15 = function13;
        ContentScale contentScale2 = fit;
        int i9 = iM4057getDefaultFilterQualityfv9h1I;
        AsyncImagePainter asyncImagePainterM6305rememberAsyncImagePainter5jETZwI = AsyncImagePainterKt.m6305rememberAsyncImagePainter5jETZwI(imageRequestUpdateRequest, imageLoader, function14, function15, contentScale2, i9, composerStartRestartGroup, ((i5 << 15) & 458752) | (i6 & 7168) | (i6 & 896) | 72 | i8, 0);
        SizeResolver sizeResolver = imageRequestUpdateRequest.getSizeResolver();
        Content(sizeResolver instanceof ConstraintsSizeResolver ? modifier2.then((Modifier) sizeResolver) : modifier2, asyncImagePainterM6305rememberAsyncImagePainter5jETZwI, str, center, fit, f2, colorFilter2, composerStartRestartGroup, (i7 & 7168) | ((i2 << 3) & 896) | i8 | (i7 & 458752) | (3670016 & i7));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.AsyncImageKt$AsyncImage$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i10) {
                AsyncImageKt.m6296AsyncImageMvsnxeU(obj, str, imageLoader, modifier2, defaultTransform, function13, center, fit, f2, colorFilter2, iM4057getDefaultFilterQualityfv9h1I, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
            }
        });
    }

    public static final void Content(final Modifier modifier, final Painter painter, final String str, final Alignment alignment, final ContentScale contentScale, final float f, final ColorFilter colorFilter, Composer composer, final int i) {
        Composer composerStartRestartGroup = composer.startRestartGroup(10290533);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(10290533, i, -1, "coil.compose.Content (AsyncImage.kt:156)");
        }
        Modifier modifierThen = ClipKt.clipToBounds(contentDescription(modifier, str)).then(new ContentPainterModifier(painter, alignment, contentScale, f, colorFilter));
        AnonymousClass1 anonymousClass1 = new MeasurePolicy() { // from class: coil.compose.AsyncImageKt.Content.1
            @Override // androidx.compose.ui.layout.MeasurePolicy
            public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                return MeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i2);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                return MeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i2);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                return MeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i2);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i2) {
                return MeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i2);
            }

            @Override // androidx.compose.ui.layout.MeasurePolicy
            /* JADX INFO: renamed from: measure-3p2s80s */
            public final MeasureResult mo69measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
                return MeasureScope.CC.layout$default(measureScope, Constraints.m5830getMinWidthimpl(j), Constraints.m5829getMinHeightimpl(j), null, new Function1<Placeable.PlacementScope, Unit>() { // from class: coil.compose.AsyncImageKt$Content$1$measure$1
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(Placeable.PlacementScope placementScope) {
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(Placeable.PlacementScope placementScope) {
                        invoke2(placementScope);
                        return Unit.INSTANCE;
                    }
                }, 4, null);
            }
        };
        composerStartRestartGroup.startReplaceableGroup(544976794);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(1)119@4537L7,120@4592L7,121@4651L7,123@4724L439:Layout.kt#80mrfh");
        ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume = composerStartRestartGroup.consume(localDensity);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        Density density = (Density) objConsume;
        ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume2 = composerStartRestartGroup.consume(localLayoutDirection);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        LayoutDirection layoutDirection = (LayoutDirection) objConsume2;
        ProvidableCompositionLocal<ViewConfiguration> localViewConfiguration = CompositionLocalsKt.getLocalViewConfiguration();
        ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
        Object objConsume3 = composerStartRestartGroup.consume(localViewConfiguration);
        ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
        ViewConfiguration viewConfiguration = (ViewConfiguration) objConsume3;
        Modifier modifierMaterializeWithCompositionLocalInjectionInternal = ComposedModifierKt.materializeWithCompositionLocalInjectionInternal(composerStartRestartGroup, modifierThen);
        final Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
        composerStartRestartGroup.startReplaceableGroup(1405779621);
        ComposerKt.sourceInformation(composerStartRestartGroup, "CC(ReusableComposeNode):Composables.kt#9igjgp");
        if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
            ComposablesKt.invalidApplier();
        }
        composerStartRestartGroup.startReusableNode();
        if (composerStartRestartGroup.getInserting()) {
            composerStartRestartGroup.createNode(new Function0<ComposeUiNode>() { // from class: coil.compose.AsyncImageKt$Content$$inlined$Layout$1
                {
                    super(0);
                }

                /* JADX WARN: Type inference failed for: r0v1, types: [androidx.compose.ui.node.ComposeUiNode, java.lang.Object] */
                @Override // kotlin.jvm.functions.Function0
                public final ComposeUiNode invoke() {
                    return constructor.invoke();
                }
            });
        } else {
            composerStartRestartGroup.useNode();
        }
        Composer composerM2989constructorimpl = Updater.m2989constructorimpl(composerStartRestartGroup);
        Updater.m2996setimpl(composerM2989constructorimpl, anonymousClass1, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
        Updater.m2996setimpl(composerM2989constructorimpl, density, ComposeUiNode.INSTANCE.getSetDensity());
        Updater.m2996setimpl(composerM2989constructorimpl, layoutDirection, ComposeUiNode.INSTANCE.getSetLayoutDirection());
        Updater.m2996setimpl(composerM2989constructorimpl, viewConfiguration, ComposeUiNode.INSTANCE.getSetViewConfiguration());
        Updater.m2996setimpl(composerM2989constructorimpl, modifierMaterializeWithCompositionLocalInjectionInternal, ComposeUiNode.INSTANCE.getSetModifier());
        composerStartRestartGroup.endNode();
        composerStartRestartGroup.endReplaceableGroup();
        composerStartRestartGroup.endReplaceableGroup();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.AsyncImageKt.Content.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i2) {
                AsyncImageKt.Content(modifier, painter, str, alignment, contentScale, f, colorFilter, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
            }
        });
    }

    public static final ImageRequest updateRequest(ImageRequest imageRequest, ContentScale contentScale, Composer composer, int i) {
        SizeResolver sizeResolverCreate;
        composer.startReplaceableGroup(402368983);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(402368983, i, -1, "coil.compose.updateRequest (AsyncImage.kt:183)");
        }
        if (imageRequest.getDefined().getSizeResolver() == null) {
            if (Intrinsics.areEqual(contentScale, ContentScale.INSTANCE.getNone())) {
                sizeResolverCreate = SizeResolvers.create(Size.ORIGINAL);
            } else {
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = new ConstraintsSizeResolver();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                sizeResolverCreate = (SizeResolver) objRememberedValue;
            }
            imageRequest = ImageRequest.newBuilder$default(imageRequest, null, 1, null).size(sizeResolverCreate).build();
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return imageRequest;
    }

    private static final Modifier contentDescription(Modifier modifier, final String str) {
        return str != null ? SemanticsModifierKt.semantics$default(modifier, false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: coil.compose.AsyncImageKt.contentDescription.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                invoke2(semanticsPropertyReceiver);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SemanticsPropertyReceiver semanticsPropertyReceiver) {
                SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str);
                SemanticsPropertiesKt.m5212setRolekuIjeqM(semanticsPropertyReceiver, Role.INSTANCE.m5199getImageo7Vup1c());
            }
        }, 1, null) : modifier;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: toSizeOrNull-BRTryo0, reason: not valid java name */
    public static final Size m6299toSizeOrNullBRTryo0(long j) {
        if (Constraints.m5832isZeroimpl(j)) {
            return null;
        }
        return new Size(Constraints.m5824getHasBoundedWidthimpl(j) ? Dimensions.Dimension(Constraints.m5828getMaxWidthimpl(j)) : Dimension.Undefined.INSTANCE, Constraints.m5823getHasBoundedHeightimpl(j) ? Dimensions.Dimension(Constraints.m5827getMaxHeightimpl(j)) : Dimension.Undefined.INSTANCE);
    }
}
