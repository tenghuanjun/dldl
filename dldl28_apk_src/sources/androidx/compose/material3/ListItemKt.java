package androidx.compose.material3;

import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.material3.tokens.ListTokens;
import androidx.compose.material3.tokens.TypographyKeyTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.LayoutDirection;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: compiled from: ListItem.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\u001a©\u0001\u0010\u0016\u001a\u00020\u00172\u0011\u0010\u0018\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010\u001b\u001a\u00020\u001c2\u0015\b\u0002\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001e\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010\u001f\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0015\b\u0002\u0010 \u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\u00012\b\b\u0002\u0010$\u001a\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0004\b%\u0010&\u001at\u0010'\u001a\u00020\u00172\u0013\u0010(\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010)\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0011\u0010*\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001a2\u0013\u0010+\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001a2\u0013\u0010,\u001a\u000f\u0012\u0004\u0012\u00020\u0017\u0018\u00010\u0019¢\u0006\u0002\b\u001aH\u0003¢\u0006\u0002\u0010-\u001a5\u0010.\u001a\u00020\u00172\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u0002022\u0011\u00103\u001a\r\u0012\u0004\u0012\u00020\u00170\u0019¢\u0006\u0002\b\u001aH\u0003ø\u0001\u0000¢\u0006\u0004\b4\u00105\u001a`\u00106\u001a\u000207*\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010<\u001a\u0004\u0018\u00010:2\b\u0010=\u001a\u0004\u0018\u00010:2\b\u0010>\u001a\u0004\u0018\u00010:2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bE\u0010F\u001a`\u0010G\u001a\u000207*\u0002082\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010<\u001a\u0004\u0018\u00010:2\b\u0010=\u001a\u0004\u0018\u00010:2\b\u0010>\u001a\u0004\u0018\u00010:2\u0006\u0010H\u001a\u00020I2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020DH\u0002ø\u0001\u0000¢\u0006\u0004\bJ\u0010K\u001af\u0010L\u001a\u00020M*\u0002082\u0006\u0010N\u001a\u0002072\u0006\u0010O\u001a\u0002072\b\u00109\u001a\u0004\u0018\u00010:2\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010<\u001a\u0004\u0018\u00010:2\b\u0010=\u001a\u0004\u0018\u00010:2\b\u0010>\u001a\u0004\u0018\u00010:2\u0006\u0010P\u001a\u00020Q2\u0006\u0010H\u001a\u00020I2\u0006\u0010A\u001a\u00020BH\u0002\"\u001e\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0002\u0010\u0003\u001a\u0004\b\u0004\u0010\u0005\"\u001e\u0010\u0007\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\b\u0010\u0003\u001a\u0004\b\t\u0010\u0005\"\u001e\u0010\n\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000b\u0010\u0003\u001a\u0004\b\f\u0010\u0005\"\u001e\u0010\r\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u000e\u0010\u0003\u001a\u0004\b\u000f\u0010\u0005\"\u001e\u0010\u0010\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\u0012\u0010\u0005\"\u001e\u0010\u0013\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0010\n\u0002\u0010\u0006\u0012\u0004\b\u0014\u0010\u0003\u001a\u0004\b\u0015\u0010\u0005\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006R"}, d2 = {"LeadingContentEndPadding", "Landroidx/compose/ui/unit/Dp;", "getLeadingContentEndPadding$annotations", "()V", "getLeadingContentEndPadding", "()F", "F", "ListItemEndPadding", "getListItemEndPadding$annotations", "getListItemEndPadding", "ListItemStartPadding", "getListItemStartPadding$annotations", "getListItemStartPadding", "ListItemThreeLineVerticalPadding", "getListItemThreeLineVerticalPadding$annotations", "getListItemThreeLineVerticalPadding", "ListItemVerticalPadding", "getListItemVerticalPadding$annotations", "getListItemVerticalPadding", "TrailingContentStartPadding", "getTrailingContentStartPadding$annotations", "getTrailingContentStartPadding", "ListItem", "", "headlineContent", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "modifier", "Landroidx/compose/ui/Modifier;", "overlineContent", "supportingContent", "leadingContent", "trailingContent", "colors", "Landroidx/compose/material3/ListItemColors;", "tonalElevation", "shadowElevation", "ListItem-HXNGIdc", "(Lkotlin/jvm/functions/Function2;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/ListItemColors;FFLandroidx/compose/runtime/Composer;II)V", "ListItemLayout", "leading", "trailing", "headline", "overline", "supporting", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "ProvideTextStyleFromToken", "color", "Landroidx/compose/ui/graphics/Color;", "textToken", "Landroidx/compose/material3/tokens/TypographyKeyTokens;", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "ProvideTextStyleFromToken-3J-VO9M", "(JLandroidx/compose/material3/tokens/TypographyKeyTokens;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "calculateHeight", "", "Landroidx/compose/ui/layout/MeasureScope;", "leadingPlaceable", "Landroidx/compose/ui/layout/Placeable;", "trailingPlaceable", "headlinePlaceable", "overlinePlaceable", "supportingPlaceable", "listItemType", "Landroidx/compose/material3/ListItemType;", "paddingValues", "Landroidx/compose/foundation/layout/PaddingValues;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "calculateHeight-N4Jib3Y", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;ILandroidx/compose/foundation/layout/PaddingValues;J)I", "calculateWidth", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "calculateWidth-xygx4p4", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/unit/LayoutDirection;Landroidx/compose/foundation/layout/PaddingValues;J)I", "place", "Landroidx/compose/ui/layout/MeasureResult;", "width", "height", "isThreeLine", "", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ListItemKt {
    private static final float LeadingContentEndPadding;
    private static final float ListItemEndPadding;
    private static final float ListItemStartPadding;
    private static final float TrailingContentStartPadding;
    private static final float ListItemVerticalPadding = Dp.m5882constructorimpl(8);
    private static final float ListItemThreeLineVerticalPadding = Dp.m5882constructorimpl(12);

    public static /* synthetic */ void getLeadingContentEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemEndPadding$annotations() {
    }

    public static /* synthetic */ void getListItemStartPadding$annotations() {
    }

    public static /* synthetic */ void getListItemThreeLineVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getListItemVerticalPadding$annotations() {
    }

    public static /* synthetic */ void getTrailingContentStartPadding$annotations() {
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x0124  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x0162  */
    /* JADX WARN: Removed duplicated region for block: B:116:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x0169  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0178  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0184  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x01b2  */
    /* JADX WARN: Removed duplicated region for block: B:134:0x01b9  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x01bd  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01c4  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:145:0x01fd  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x0201  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x0212  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0229  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x0242  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x02bc  */
    /* JADX WARN: Removed duplicated region for block: B:163:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x004b  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0067  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x0083  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00a3  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00be  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00da  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0100  */
    /* JADX INFO: renamed from: ListItem-HXNGIdc, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void m1683ListItemHXNGIdc(final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, androidx.compose.ui.Modifier r39, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r40, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r41, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r42, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r43, androidx.compose.material3.ListItemColors r44, float r45, float r46, androidx.compose.runtime.Composer r47, final int r48, final int r49) {
        /*
            Method dump skipped, instruction units count: 723
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.ListItemKt.m1683ListItemHXNGIdc(kotlin.jvm.functions.Function2, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.material3.ListItemColors, float, float, androidx.compose.runtime.Composer, int, int):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void ListItemLayout(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final Function2<? super Composer, ? super Integer, Unit> function24, final Function2<? super Composer, ? super Integer, Unit> function25, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(2052297037);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ListItemLayout)P(1,4)168@6954L7,177@7152L3807,169@6966L3993:ListItem.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function22) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function23) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function24) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function25) ? 16384 : 8192;
        }
        if ((i2 & 9363) != 9362 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2052297037, i2, -1, "androidx.compose.material3.ListItemLayout (ListItem.kt:167)");
            }
            ProvidableCompositionLocal<LayoutDirection> localLayoutDirection = CompositionLocalsKt.getLocalLayoutDirection();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localLayoutDirection);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final LayoutDirection layoutDirection = (LayoutDirection) objConsume;
            Function2[] function2Arr = new Function2[5];
            function2Arr[0] = function23;
            function2Arr[1] = function24 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1466getLambda1$material3_release() : function24;
            function2Arr[2] = function25 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1467getLambda2$material3_release() : function25;
            function2Arr[3] = function2 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1468getLambda3$material3_release() : function2;
            function2Arr[4] = function22 == null ? ComposableSingletons$ListItemKt.INSTANCE.m1469getLambda4$material3_release() : function22;
            List listListOf = CollectionsKt.listOf((Object[]) function2Arr);
            composerStartRestartGroup.startReplaceableGroup(1361340338);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):ListItem.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(layoutDirection);
            MultiContentMeasurePolicy multiContentMeasurePolicyRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || multiContentMeasurePolicyRememberedValue == Composer.INSTANCE.getEmpty()) {
                multiContentMeasurePolicyRememberedValue = new MultiContentMeasurePolicy() { // from class: androidx.compose.material3.ListItemKt$ListItemLayout$1$1
                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public /* synthetic */ int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MultiContentMeasurePolicy.CC.$default$maxIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public /* synthetic */ int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MultiContentMeasurePolicy.CC.$default$maxIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public /* synthetic */ int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MultiContentMeasurePolicy.CC.$default$minIntrinsicHeight(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    public /* synthetic */ int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List list, int i3) {
                        return MultiContentMeasurePolicy.CC.$default$minIntrinsicWidth(this, intrinsicMeasureScope, list, i3);
                    }

                    @Override // androidx.compose.ui.layout.MultiContentMeasurePolicy
                    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
                    public final MeasureResult mo1690measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
                        List<? extends Measurable> list2 = list.get(0);
                        List<? extends Measurable> list3 = list.get(1);
                        List<? extends Measurable> list4 = list.get(2);
                        List<? extends Measurable> list5 = list.get(3);
                        List<? extends Measurable> list6 = list.get(4);
                        long jM5844offsetNN6EwU = ConstraintsKt.m5844offsetNN6EwU(Constraints.m5819copyZbe2FdA$default(j, 0, 0, 0, 0, 10, null), -measureScope.mo339roundToPx0680j_4(Dp.m5882constructorimpl(ListItemKt.getListItemStartPadding() + ListItemKt.getListItemEndPadding())), -measureScope.mo339roundToPx0680j_4(Dp.m5882constructorimpl(ListItemKt.getListItemVerticalPadding() * 2)));
                        Measurable measurable = (Measurable) CollectionsKt.firstOrNull((List) list5);
                        Placeable placeableMo4783measureBRTryo0 = measurable != null ? measurable.mo4783measureBRTryo0(jM5844offsetNN6EwU) : null;
                        int iWidthOrZero = TextFieldImplKt.widthOrZero(placeableMo4783measureBRTryo0);
                        Measurable measurable2 = (Measurable) CollectionsKt.firstOrNull((List) list6);
                        Placeable placeableMo4783measureBRTryo02 = measurable2 != null ? measurable2.mo4783measureBRTryo0(ConstraintsKt.m5845offsetNN6EwU$default(jM5844offsetNN6EwU, -iWidthOrZero, 0, 2, null)) : null;
                        int iWidthOrZero2 = iWidthOrZero + TextFieldImplKt.widthOrZero(placeableMo4783measureBRTryo02);
                        Measurable measurable3 = (Measurable) CollectionsKt.firstOrNull((List) list2);
                        Placeable placeableMo4783measureBRTryo03 = measurable3 != null ? measurable3.mo4783measureBRTryo0(ConstraintsKt.m5845offsetNN6EwU$default(jM5844offsetNN6EwU, -iWidthOrZero2, 0, 2, null)) : null;
                        int iHeightOrZero = TextFieldImplKt.heightOrZero(placeableMo4783measureBRTryo03);
                        Measurable measurable4 = (Measurable) CollectionsKt.firstOrNull((List) list4);
                        Placeable placeableMo4783measureBRTryo04 = measurable4 != null ? measurable4.mo4783measureBRTryo0(ConstraintsKt.m5844offsetNN6EwU(jM5844offsetNN6EwU, -iWidthOrZero2, -iHeightOrZero)) : null;
                        int iHeightOrZero2 = iHeightOrZero + TextFieldImplKt.heightOrZero(placeableMo4783measureBRTryo04);
                        boolean z = (placeableMo4783measureBRTryo04 == null || placeableMo4783measureBRTryo04.get(AlignmentLineKt.getFirstBaseline()) == placeableMo4783measureBRTryo04.get(AlignmentLineKt.getLastBaseline())) ? false : true;
                        Measurable measurable5 = (Measurable) CollectionsKt.firstOrNull((List) list3);
                        Placeable placeableMo4783measureBRTryo05 = measurable5 != null ? measurable5.mo4783measureBRTryo0(ConstraintsKt.m5844offsetNN6EwU(jM5844offsetNN6EwU, -iWidthOrZero2, -iHeightOrZero2)) : null;
                        int iM1700getListItemTypeZLSjz4$material3_release = ListItemType.INSTANCE.m1700getListItemTypeZLSjz4$material3_release(placeableMo4783measureBRTryo05 != null, placeableMo4783measureBRTryo04 != null, z);
                        boolean zM1695equalsimpl0 = ListItemType.m1695equalsimpl0(iM1700getListItemTypeZLSjz4$material3_release, ListItemType.INSTANCE.m1702getThreeLineAlXitO8());
                        PaddingValues paddingValuesM594PaddingValuesa9UjIt4 = PaddingKt.m594PaddingValuesa9UjIt4(ListItemKt.getListItemStartPadding(), zM1695equalsimpl0 ? ListItemKt.getListItemThreeLineVerticalPadding() : ListItemKt.getListItemVerticalPadding(), ListItemKt.getListItemEndPadding(), zM1695equalsimpl0 ? ListItemKt.getListItemThreeLineVerticalPadding() : ListItemKt.getListItemVerticalPadding());
                        Placeable placeable = placeableMo4783measureBRTryo0;
                        Placeable placeable2 = placeableMo4783measureBRTryo02;
                        Placeable placeable3 = placeableMo4783measureBRTryo03;
                        Placeable placeable4 = placeableMo4783measureBRTryo05;
                        Placeable placeable5 = placeableMo4783measureBRTryo04;
                        return ListItemKt.place(measureScope, ListItemKt.m1689calculateWidthxygx4p4(measureScope, placeable, placeable2, placeable3, placeable4, placeable5, layoutDirection, paddingValuesM594PaddingValuesa9UjIt4, j), ListItemKt.m1688calculateHeightN4Jib3Y(measureScope, placeable, placeable2, placeable3, placeable4, placeable5, iM1700getListItemTypeZLSjz4$material3_release, paddingValuesM594PaddingValuesa9UjIt4, j), placeableMo4783measureBRTryo0, placeableMo4783measureBRTryo02, placeableMo4783measureBRTryo03, placeableMo4783measureBRTryo05, placeableMo4783measureBRTryo04, zM1695equalsimpl0, layoutDirection, paddingValuesM594PaddingValuesa9UjIt4);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(multiContentMeasurePolicyRememberedValue);
            }
            MultiContentMeasurePolicy multiContentMeasurePolicy = (MultiContentMeasurePolicy) multiContentMeasurePolicyRememberedValue;
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.startReplaceableGroup(1399185516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)171@6874L62,168@6760L182:Layout.kt#80mrfh");
            Modifier.Companion companion = Modifier.INSTANCE;
            Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
            composerStartRestartGroup.startReplaceableGroup(1157296644);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1):Composables.kt#9igjgp");
            boolean zChanged2 = composerStartRestartGroup.changed(multiContentMeasurePolicy);
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = MultiContentMeasurePolicyKt.createMeasurePolicy(multiContentMeasurePolicy);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue;
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
            if (!(composerStartRestartGroup.getApplier() instanceof Applier)) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2989constructorimpl = Updater.m2989constructorimpl(composerStartRestartGroup);
            Updater.m2996setimpl(composerM2989constructorimpl, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            function2CombineAsVirtualLayouts.invoke(composerStartRestartGroup, 0);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt.ListItemLayout.2
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

                public final void invoke(Composer composer2, int i3) {
                    ListItemKt.ListItemLayout(function2, function22, function23, function24, function25, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateWidth-xygx4p4, reason: not valid java name */
    public static final int m1689calculateWidthxygx4p4(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, LayoutDirection layoutDirection, PaddingValues paddingValues, long j) {
        if (Constraints.m5824getHasBoundedWidthimpl(j)) {
            return Constraints.m5828getMaxWidthimpl(j);
        }
        int i = measureScope.mo339roundToPx0680j_4(Dp.m5882constructorimpl(paddingValues.mo549calculateLeftPaddingu2uoSUM(layoutDirection) + paddingValues.mo550calculateRightPaddingu2uoSUM(layoutDirection)));
        return i + TextFieldImplKt.widthOrZero(placeable) + Math.max(TextFieldImplKt.widthOrZero(placeable3), Math.max(TextFieldImplKt.widthOrZero(placeable4), TextFieldImplKt.widthOrZero(placeable5))) + TextFieldImplKt.widthOrZero(placeable2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: calculateHeight-N4Jib3Y, reason: not valid java name */
    public static final int m1688calculateHeightN4Jib3Y(MeasureScope measureScope, Placeable placeable, Placeable placeable2, Placeable placeable3, Placeable placeable4, Placeable placeable5, int i, PaddingValues paddingValues, long j) {
        float fM2659getListItemTwoLineContainerHeightD9Ej5fM;
        if (ListItemType.m1695equalsimpl0(i, ListItemType.INSTANCE.m1701getOneLineAlXitO8())) {
            fM2659getListItemTwoLineContainerHeightD9Ej5fM = ListTokens.INSTANCE.m2655getListItemOneLineContainerHeightD9Ej5fM();
        } else {
            fM2659getListItemTwoLineContainerHeightD9Ej5fM = ListItemType.m1695equalsimpl0(i, ListItemType.INSTANCE.m1703getTwoLineAlXitO8()) ? ListTokens.INSTANCE.m2659getListItemTwoLineContainerHeightD9Ej5fM() : ListTokens.INSTANCE.m2657getListItemThreeLineContainerHeightD9Ej5fM();
        }
        return RangesKt.coerceAtMost(Math.max(Math.max(Constraints.m5829getMinHeightimpl(j), measureScope.mo339roundToPx0680j_4(fM2659getListItemTwoLineContainerHeightD9Ej5fM)), measureScope.mo339roundToPx0680j_4(Dp.m5882constructorimpl(paddingValues.getTop() + paddingValues.getBottom())) + Math.max(TextFieldImplKt.heightOrZero(placeable), Math.max(TextFieldImplKt.heightOrZero(placeable3) + TextFieldImplKt.heightOrZero(placeable4) + TextFieldImplKt.heightOrZero(placeable5), TextFieldImplKt.heightOrZero(placeable2)))), Constraints.m5827getMaxHeightimpl(j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final MeasureResult place(final MeasureScope measureScope, final int i, final int i2, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final Placeable placeable4, final Placeable placeable5, final boolean z, final LayoutDirection layoutDirection, final PaddingValues paddingValues) {
        return MeasureScope.CC.layout$default(measureScope, i, i2, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.ListItemKt.place.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                int i3 = measureScope.mo339roundToPx0680j_4(PaddingKt.calculateStartPadding(paddingValues, layoutDirection));
                int i4 = measureScope.mo339roundToPx0680j_4(PaddingKt.calculateEndPadding(paddingValues, layoutDirection));
                int iAlign = measureScope.mo339roundToPx0680j_4(paddingValues.getTop());
                Placeable placeable6 = placeable;
                if (placeable6 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable6, i3, z ? iAlign : Alignment.INSTANCE.getCenterVertically().align(placeable6.getHeight(), i2), 0.0f, 4, null);
                }
                Placeable placeable7 = placeable2;
                if (placeable7 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable7, (i - i4) - placeable7.getWidth(), z ? iAlign : Alignment.INSTANCE.getCenterVertically().align(placeable7.getHeight(), i2), 0.0f, 4, null);
                }
                int iWidthOrZero = i3 + TextFieldImplKt.widthOrZero(placeable);
                if (!z) {
                    iAlign = Alignment.INSTANCE.getCenterVertically().align(TextFieldImplKt.heightOrZero(placeable3) + TextFieldImplKt.heightOrZero(placeable4) + TextFieldImplKt.heightOrZero(placeable5), i2);
                }
                Placeable placeable8 = placeable4;
                if (placeable8 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable8, iWidthOrZero, iAlign, 0.0f, 4, null);
                }
                int iHeightOrZero = iAlign + TextFieldImplKt.heightOrZero(placeable4);
                Placeable placeable9 = placeable3;
                if (placeable9 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable9, iWidthOrZero, iHeightOrZero, 0.0f, 4, null);
                }
                int iHeightOrZero2 = iHeightOrZero + TextFieldImplKt.heightOrZero(placeable3);
                Placeable placeable10 = placeable5;
                if (placeable10 != null) {
                    Placeable.PlacementScope.placeRelative$default(placementScope, placeable10, iWidthOrZero, iHeightOrZero2, 0.0f, 4, null);
                }
            }
        }, 4, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: ProvideTextStyleFromToken-3J-VO9M, reason: not valid java name */
    public static final void m1684ProvideTextStyleFromToken3JVO9M(final long j, final TypographyKeyTokens typographyKeyTokens, final Function2<? super Composer, ? super Integer, Unit> function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1133967795);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(ProvideTextStyleFromToken)P(0:c#ui.graphics.Color,2)520@20833L10,518@20747L142:ListItem.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(j) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(typographyKeyTokens) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i2 & KeyBoardKey.KeyboardKeyOemFjMasshou) != 146 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1133967795, i2, -1, "androidx.compose.material3.ProvideTextStyleFromToken (ListItem.kt:518)");
            }
            ProvideContentColorTextStyleKt.m1808ProvideContentColorTextStyle3JVO9M(j, TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), typographyKeyTokens), function2, composerStartRestartGroup, i2 & 910);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.ListItemKt$ProvideTextStyleFromToken$1
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

                public final void invoke(Composer composer2, int i3) {
                    ListItemKt.m1684ProvideTextStyleFromToken3JVO9M(j, typographyKeyTokens, function2, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    public static final float getListItemVerticalPadding() {
        return ListItemVerticalPadding;
    }

    public static final float getListItemThreeLineVerticalPadding() {
        return ListItemThreeLineVerticalPadding;
    }

    public static final float getListItemStartPadding() {
        return ListItemStartPadding;
    }

    public static final float getListItemEndPadding() {
        return ListItemEndPadding;
    }

    public static final float getLeadingContentEndPadding() {
        return LeadingContentEndPadding;
    }

    public static final float getTrailingContentStartPadding() {
        return TrailingContentStartPadding;
    }

    static {
        float f = 16;
        ListItemStartPadding = Dp.m5882constructorimpl(f);
        ListItemEndPadding = Dp.m5882constructorimpl(f);
        LeadingContentEndPadding = Dp.m5882constructorimpl(f);
        TrailingContentStartPadding = Dp.m5882constructorimpl(f);
    }
}
