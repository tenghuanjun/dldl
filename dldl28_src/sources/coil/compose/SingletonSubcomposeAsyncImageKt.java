package coil.compose;

import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.layout.ContentScale;
import coil.compose.AsyncImagePainter;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;

/* JADX INFO: compiled from: SingletonSubcomposeAsyncImage.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000l\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001a³\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\t2\u0016\b\u0002\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u00152\u001c\u0010\u0016\u001a\u0018\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00010\t¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u0019H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001a\u0010\u001b\u001a§\u0002\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072&\b\u0002\u0010\u001c\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001d¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010\u001f\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001d¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192&\b\u0002\u0010!\u001a \u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001d¢\u0006\u0002\b\u0018¢\u0006\u0002\b\u00192\u0016\b\u0002\u0010#\u001a\u0010\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010$\u001a\u0010\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\"\u0012\u0004\u0012\u00020\u0001\u0018\u00010\t2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010\u0014\u001a\u00020\u0015H\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010'\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006("}, d2 = {"SubcomposeAsyncImage", "", "model", "", "contentDescription", "", "modifier", "Landroidx/compose/ui/Modifier;", "transform", "Lkotlin/Function1;", "Lcoil/compose/AsyncImagePainter$State;", "onState", "alignment", "Landroidx/compose/ui/Alignment;", "contentScale", "Landroidx/compose/ui/layout/ContentScale;", "alpha", "", "colorFilter", "Landroidx/compose/ui/graphics/ColorFilter;", "filterQuality", "Landroidx/compose/ui/graphics/FilterQuality;", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Lcoil/compose/SubcomposeAsyncImageScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "SubcomposeAsyncImage-10Xjiaw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;III)V", "loading", "Lkotlin/Function2;", "Lcoil/compose/AsyncImagePainter$State$Loading;", "success", "Lcoil/compose/AsyncImagePainter$State$Success;", "error", "Lcoil/compose/AsyncImagePainter$State$Error;", "onLoading", "onSuccess", "onError", "SubcomposeAsyncImage-ylYTKUw", "(Ljava/lang/Object;Ljava/lang/String;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function4;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/ui/Alignment;Landroidx/compose/ui/layout/ContentScale;FLandroidx/compose/ui/graphics/ColorFilter;ILandroidx/compose/runtime/Composer;III)V", "coil-compose-singleton_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SingletonSubcomposeAsyncImageKt {
    /* JADX INFO: renamed from: SubcomposeAsyncImage-ylYTKUw, reason: not valid java name */
    public static final void m6328SubcomposeAsyncImageylYTKUw(final Object obj, final String str, Modifier modifier, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function4, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function42, Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function43, Function1<? super AsyncImagePainter.State.Loading, Unit> function1, Function1<? super AsyncImagePainter.State.Success, Unit> function12, Function1<? super AsyncImagePainter.State.Error, Unit> function13, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, Composer composer, final int i2, final int i3, final int i4) {
        int iM4057getDefaultFilterQualityfv9h1I;
        int i5;
        Composer composerStartRestartGroup = composer.startRestartGroup(1047090393);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SubcomposeAsyncImage)P(8,3,9,7,13,5,11,12,10!1,4!,6:c#ui.graphics.FilterQuality)");
        final Modifier modifier2 = (i4 & 4) != 0 ? Modifier.INSTANCE : modifier;
        Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function44 = (i4 & 8) != 0 ? null : function4;
        Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function45 = (i4 & 16) != 0 ? null : function42;
        Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function46 = (i4 & 32) != 0 ? null : function43;
        Function1<? super AsyncImagePainter.State.Loading, Unit> function14 = (i4 & 64) != 0 ? null : function1;
        Function1<? super AsyncImagePainter.State.Success, Unit> function15 = (i4 & 128) != 0 ? null : function12;
        Function1<? super AsyncImagePainter.State.Error, Unit> function16 = (i4 & 256) != 0 ? null : function13;
        Alignment center = (i4 & 512) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i4 & 1024) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i4 & 2048) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i4 & 4096) != 0 ? null : colorFilter;
        if ((i4 & 8192) != 0) {
            i5 = i3 & (-7169);
            iM4057getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m4057getDefaultFilterQualityfv9h1I();
        } else {
            iM4057getDefaultFilterQualityfv9h1I = i;
            i5 = i3;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1047090393, i2, i5, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:43)");
        }
        int i6 = i2 << 3;
        int i7 = i5 << 3;
        SubcomposeAsyncImageKt.m6329SubcomposeAsyncImageQ4Kwu38(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composerStartRestartGroup, 6), modifier2, function44, function45, function46, function14, function15, function16, center, fit, f2, colorFilter2, iM4057getDefaultFilterQualityfv9h1I, composerStartRestartGroup, (i2 & 112) | 520 | (i6 & 7168) | (i6 & 57344) | (i6 & 458752) | (i6 & 3670016) | (i6 & 29360128) | (i6 & 234881024) | (i6 & 1879048192), ((i2 >> 27) & 14) | (i7 & 112) | (i7 & 896) | (i7 & 7168) | (i7 & 57344), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Loading, ? super Composer, ? super Integer, Unit> function47 = function44;
        final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Success, ? super Composer, ? super Integer, Unit> function48 = function45;
        final Function4<? super SubcomposeAsyncImageScope, ? super AsyncImagePainter.State.Error, ? super Composer, ? super Integer, Unit> function49 = function46;
        final Function1<? super AsyncImagePainter.State.Loading, Unit> function17 = function14;
        final Function1<? super AsyncImagePainter.State.Success, Unit> function18 = function15;
        final Function1<? super AsyncImagePainter.State.Error, Unit> function19 = function16;
        final Alignment alignment2 = center;
        final ContentScale contentScale2 = fit;
        final float f3 = f2;
        final ColorFilter colorFilter3 = colorFilter2;
        final int i8 = iM4057getDefaultFilterQualityfv9h1I;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$1
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

            public final void invoke(Composer composer2, int i9) {
                SingletonSubcomposeAsyncImageKt.m6328SubcomposeAsyncImageylYTKUw(obj, str, modifier2, function47, function48, function49, function17, function18, function19, alignment2, contentScale2, f3, colorFilter3, i8, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
            }
        });
    }

    /* JADX INFO: renamed from: SubcomposeAsyncImage-10Xjiaw, reason: not valid java name */
    public static final void m6327SubcomposeAsyncImage10Xjiaw(final Object obj, final String str, Modifier modifier, Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function1, Function1<? super AsyncImagePainter.State, Unit> function12, Alignment alignment, ContentScale contentScale, float f, ColorFilter colorFilter, int i, final Function3<? super SubcomposeAsyncImageScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i2, final int i3, final int i4) {
        Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> defaultTransform;
        int i5;
        int iM4057getDefaultFilterQualityfv9h1I;
        Composer composerStartRestartGroup = composer.startRestartGroup(1571662781);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SubcomposeAsyncImage)P(7,4,8,10,9!1,5!2,6:c#ui.graphics.FilterQuality)");
        final Modifier modifier2 = (i4 & 4) != 0 ? Modifier.INSTANCE : modifier;
        if ((i4 & 8) != 0) {
            i5 = i2 & (-7169);
            defaultTransform = AsyncImagePainter.INSTANCE.getDefaultTransform();
        } else {
            defaultTransform = function1;
            i5 = i2;
        }
        Function1<? super AsyncImagePainter.State, Unit> function13 = (i4 & 16) != 0 ? null : function12;
        Alignment center = (i4 & 32) != 0 ? Alignment.INSTANCE.getCenter() : alignment;
        ContentScale fit = (i4 & 64) != 0 ? ContentScale.INSTANCE.getFit() : contentScale;
        float f2 = (i4 & 128) != 0 ? 1.0f : f;
        ColorFilter colorFilter2 = (i4 & 256) != 0 ? null : colorFilter;
        if ((i4 & 512) != 0) {
            i5 &= -1879048193;
            iM4057getDefaultFilterQualityfv9h1I = DrawScope.INSTANCE.m4057getDefaultFilterQualityfv9h1I();
        } else {
            iM4057getDefaultFilterQualityfv9h1I = i;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1571662781, i5, i3, "coil.compose.SubcomposeAsyncImage (SingletonSubcomposeAsyncImage.kt:100)");
        }
        int i6 = i5 << 3;
        SubcomposeAsyncImageKt.m6330SubcomposeAsyncImagesKDTAoQ(obj, str, ImageLoaderProvidableCompositionLocal.getCurrent(LocalImageLoaderKt.getLocalImageLoader(), composerStartRestartGroup, 6), modifier2, defaultTransform, function13, center, fit, f2, colorFilter2, iM4057getDefaultFilterQualityfv9h1I, function3, composerStartRestartGroup, (i5 & 112) | 520 | (i6 & 7168) | (57344 & i6) | (458752 & i6) | (3670016 & i6) | (29360128 & i6) | (234881024 & i6) | (i6 & 1879048192), ((i5 >> 27) & 14) | ((i3 << 3) & 112), 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        final Function1<? super AsyncImagePainter.State, ? extends AsyncImagePainter.State> function14 = defaultTransform;
        final Function1<? super AsyncImagePainter.State, Unit> function15 = function13;
        final Alignment alignment2 = center;
        final ContentScale contentScale2 = fit;
        final float f3 = f2;
        final ColorFilter colorFilter3 = colorFilter2;
        final int i7 = iM4057getDefaultFilterQualityfv9h1I;
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: coil.compose.SingletonSubcomposeAsyncImageKt$SubcomposeAsyncImage$2
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

            public final void invoke(Composer composer2, int i8) {
                SingletonSubcomposeAsyncImageKt.m6327SubcomposeAsyncImage10Xjiaw(obj, str, modifier2, function14, function15, alignment2, contentScale2, f3, colorFilter3, i7, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3), i4);
            }
        });
    }
}
