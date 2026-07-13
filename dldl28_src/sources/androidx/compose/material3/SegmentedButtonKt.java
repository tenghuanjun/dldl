package androidx.compose.material3;

import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.IntrinsicKt;
import androidx.compose.foundation.layout.IntrinsicSize;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.selection.SelectableGroupKt;
import androidx.compose.material3.tokens.OutlinedSegmentedButtonTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.CompositionScopedCoroutineScopeCanceller;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableIntState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotIntStateKt;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.LayoutModifierKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicyKt;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.Dp;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: compiled from: SegmentedButton.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0004\u001aD\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a3\u0010\u0011\u001a\u00020\u00062\u0011\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010\n\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0003¢\u0006\u0002\u0010\u0014\u001aD\u0010\u0015\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\u00032\u001c\u0010\n\u001a\u0018\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00060\u000b¢\u0006\u0002\b\r¢\u0006\u0002\b\u000eH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0010\u001a\u008f\u0001\u0010\u0018\u001a\u00020\u0006*\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001a2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00060\u000b2\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u001a2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\u0013\b\u0002\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010&\u001a\u0089\u0001\u0010\u0018\u001a\u00020\u0006*\u00020\u00162\u0006\u0010'\u001a\u00020\u001a2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020\u00060\u00132\u0006\u0010\u001c\u001a\u00020\u001d2\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u001a2\b\b\u0002\u0010\u001f\u001a\u00020 2\b\b\u0002\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020$2\u0013\b\u0002\u0010\u0012\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\r2\u0011\u0010%\u001a\r\u0012\u0004\u0012\u00020\u00060\u0013¢\u0006\u0002\b\rH\u0007¢\u0006\u0002\u0010)\u001a\u0017\u0010*\u001a\b\u0012\u0004\u0012\u00020,0+*\u00020-H\u0003¢\u0006\u0002\u0010.\u001a\"\u0010/\u001a\u00020\b*\u00020\b2\u0006\u0010\u0019\u001a\u00020\u001a2\f\u00100\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0004\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"CheckedZIndexFactor", "", "IconSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "MultiChoiceSegmentedButtonRow", "", "modifier", "Landroidx/compose/ui/Modifier;", "space", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Lkotlin/Function1;", "Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "MultiChoiceSegmentedButtonRow-uFdPcIQ", "(Landroidx/compose/ui/Modifier;FLkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "SegmentedButtonContent", "icon", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;I)V", "SingleChoiceSegmentedButtonRow", "Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;", "SingleChoiceSegmentedButtonRow-uFdPcIQ", "SegmentedButton", "checked", "", "onCheckedChange", "shape", "Landroidx/compose/ui/graphics/Shape;", "enabled", "colors", "Landroidx/compose/material3/SegmentedButtonColors;", "border", "Landroidx/compose/foundation/BorderStroke;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "label", "(Landroidx/compose/material3/MultiChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function1;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "selected", "onClick", "(Landroidx/compose/material3/SingleChoiceSegmentedButtonRowScope;ZLkotlin/jvm/functions/Function0;Landroidx/compose/ui/graphics/Shape;Landroidx/compose/ui/Modifier;ZLandroidx/compose/material3/SegmentedButtonColors;Landroidx/compose/foundation/BorderStroke;Landroidx/compose/foundation/interaction/MutableInteractionSource;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;Landroidx/compose/runtime/Composer;III)V", "interactionCountAsState", "Landroidx/compose/runtime/State;", "", "Landroidx/compose/foundation/interaction/InteractionSource;", "(Landroidx/compose/foundation/interaction/InteractionSource;Landroidx/compose/runtime/Composer;I)Landroidx/compose/runtime/State;", "interactionZIndex", "interactionCount", "material3_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SegmentedButtonKt {
    private static final float CheckedZIndexFactor = 5.0f;
    private static final float IconSpacing = Dp.m5882constructorimpl(8);

    /* JADX WARN: Removed duplicated region for block: B:107:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02c0  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02d3  */
    /* JADX WARN: Removed duplicated region for block: B:170:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SegmentedButton(final androidx.compose.material3.MultiChoiceSegmentedButtonRowScope r29, final boolean r30, final kotlin.jvm.functions.Function1<? super java.lang.Boolean, kotlin.Unit> r31, final androidx.compose.ui.graphics.Shape r32, androidx.compose.ui.Modifier r33, boolean r34, androidx.compose.material3.SegmentedButtonColors r35, androidx.compose.foundation.BorderStroke r36, androidx.compose.foundation.interaction.MutableInteractionSource r37, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 754
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonKt.SegmentedButton(androidx.compose.material3.MultiChoiceSegmentedButtonRowScope, boolean, kotlin.jvm.functions.Function1, androidx.compose.ui.graphics.Shape, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SegmentedButtonColors, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:107:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x0131  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x014d  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x016b  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x019f  */
    /* JADX WARN: Removed duplicated region for block: B:137:0x01a1  */
    /* JADX WARN: Removed duplicated region for block: B:138:0x01a6  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x01aa  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x01af  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x01b8  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x01be  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01d4  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01d8  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x01fc  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:157:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x022a  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x02c7  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x02da  */
    /* JADX WARN: Removed duplicated region for block: B:170:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x00d0  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x00d8  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00ec  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00f4  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00f9  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:98:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void SegmentedButton(final androidx.compose.material3.SingleChoiceSegmentedButtonRowScope r29, final boolean r30, final kotlin.jvm.functions.Function0<kotlin.Unit> r31, final androidx.compose.ui.graphics.Shape r32, androidx.compose.ui.Modifier r33, boolean r34, androidx.compose.material3.SegmentedButtonColors r35, androidx.compose.foundation.BorderStroke r36, androidx.compose.foundation.interaction.MutableInteractionSource r37, kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r38, final kotlin.jvm.functions.Function2<? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r39, androidx.compose.runtime.Composer r40, final int r41, final int r42, final int r43) {
        /*
            Method dump skipped, instruction units count: 761
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.SegmentedButtonKt.SegmentedButton(androidx.compose.material3.SingleChoiceSegmentedButtonRowScope, boolean, kotlin.jvm.functions.Function0, androidx.compose.ui.graphics.Shape, androidx.compose.ui.Modifier, boolean, androidx.compose.material3.SegmentedButtonColors, androidx.compose.foundation.BorderStroke, androidx.compose.foundation.interaction.MutableInteractionSource, kotlin.jvm.functions.Function2, kotlin.jvm.functions.Function2, androidx.compose.runtime.Composer, int, int, int):void");
    }

    /* JADX INFO: renamed from: SingleChoiceSegmentedButtonRow-uFdPcIQ, reason: not valid java name */
    public static final void m1870SingleChoiceSegmentedButtonRowuFdPcIQ(Modifier modifier, float f, final Function3<? super SingleChoiceSegmentedButtonRowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1520863498);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SingleChoiceSegmentedButtonRow)P(1,2:c#ui.unit.Dp)266@12418L423:SegmentedButton.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i3 & KeyBoardKey.KeyboardKeyOemFjMasshou) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i5 != 0) {
                f = SegmentedButtonDefaults.INSTANCE.m1867getBorderWidthD9Ej5fM();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1520863498, i3, -1, "androidx.compose.material3.SingleChoiceSegmentedButtonRow (SegmentedButton.kt:265)");
            }
            Modifier modifierWidth = IntrinsicKt.width(SizeKt.m632defaultMinSizeVpY3zN4$default(SelectableGroupKt.selectableGroup(modifier), 0.0f, OutlinedSegmentedButtonTokens.INSTANCE.m2706getContainerHeightD9Ej5fM(), 1, null), IntrinsicSize.Min);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM504spacedBy0680j_4 = Arrangement.INSTANCE.m504spacedBy0680j_4(Dp.m5882constructorimpl(-f));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM504spacedBy0680j_4, centerVertically, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierWidth);
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
            Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -181589436, "C274@12753L58,275@12826L9:SegmentedButton.kt#uh7d8r");
            composerStartRestartGroup.startReplaceableGroup(-181589424);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):SegmentedButton.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new SingleChoiceSegmentedButtonScopeWrapper(rowScopeInstance);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            function3.invoke((SingleChoiceSegmentedButtonScopeWrapper) objRememberedValue, composerStartRestartGroup, Integer.valueOf(((i3 >> 3) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        final Modifier modifier2 = modifier;
        final float f2 = f;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SingleChoiceSegmentedButtonRow$2
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

                public final void invoke(Composer composer2, int i6) {
                    SegmentedButtonKt.m1870SingleChoiceSegmentedButtonRowuFdPcIQ(modifier2, f2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: renamed from: MultiChoiceSegmentedButtonRow-uFdPcIQ, reason: not valid java name */
    public static final void m1869MultiChoiceSegmentedButtonRowuFdPcIQ(Modifier modifier, float f, final Function3<? super MultiChoiceSegmentedButtonRowScope, ? super Composer, ? super Integer, Unit> function3, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(155922315);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(MultiChoiceSegmentedButtonRow)P(1,2:c#ui.unit.Dp)304@13910L391:SegmentedButton.kt#uh7d8r");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i5 = i2 & 2;
        if (i5 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(f) ? 32 : 16;
        }
        if ((i2 & 4) != 0) {
            i3 |= 384;
        } else if ((i & 384) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function3) ? 256 : 128;
        }
        if ((i3 & KeyBoardKey.KeyboardKeyOemFjMasshou) == 146 && composerStartRestartGroup.getSkipping()) {
            composerStartRestartGroup.skipToGroupEnd();
        } else {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (i5 != 0) {
                f = SegmentedButtonDefaults.INSTANCE.m1867getBorderWidthD9Ej5fM();
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(155922315, i3, -1, "androidx.compose.material3.MultiChoiceSegmentedButtonRow (SegmentedButton.kt:303)");
            }
            Modifier modifierWidth = IntrinsicKt.width(SizeKt.m632defaultMinSizeVpY3zN4$default(modifier, 0.0f, OutlinedSegmentedButtonTokens.INSTANCE.m2706getContainerHeightD9Ej5fM(), 1, null), IntrinsicSize.Min);
            Arrangement.HorizontalOrVertical horizontalOrVerticalM504spacedBy0680j_4 = Arrangement.INSTANCE.m504spacedBy0680j_4(Dp.m5882constructorimpl(-f));
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(horizontalOrVerticalM504spacedBy0680j_4, centerVertically, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierWidth);
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
            Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -326681643, "C92@4661L9:Row.kt#2w3rfo");
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 573415822, "C311@14214L57,312@14286L9:SegmentedButton.kt#uh7d8r");
            composerStartRestartGroup.startReplaceableGroup(573415834);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):SegmentedButton.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new MultiChoiceSegmentedButtonScopeWrapper(rowScopeInstance);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            function3.invoke((MultiChoiceSegmentedButtonScopeWrapper) objRememberedValue, composerStartRestartGroup, Integer.valueOf(((i3 >> 3) & 112) | 6));
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endNode();
            composerStartRestartGroup.endReplaceableGroup();
            composerStartRestartGroup.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }
        final Modifier modifier2 = modifier;
        final float f2 = f;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$MultiChoiceSegmentedButtonRow$2
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

                public final void invoke(Composer composer2, int i6) {
                    SegmentedButtonKt.m1869MultiChoiceSegmentedButtonRowuFdPcIQ(modifier2, f2, function3, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void SegmentedButtonContent(final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function22, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(1464121570);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(SegmentedButtonContent)P(1)322@14458L637:SegmentedButton.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function22) ? 32 : 16;
        }
        if ((i2 & 19) != 18 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1464121570, i2, -1, "androidx.compose.material3.SegmentedButtonContent (SegmentedButton.kt:321)");
            }
            Alignment center = Alignment.INSTANCE.getCenter();
            Modifier modifierPadding = PaddingKt.padding(Modifier.INSTANCE, ButtonDefaults.INSTANCE.getTextButtonContentPadding());
            composerStartRestartGroup.startReplaceableGroup(733328855);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
            MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(center, false, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierPadding);
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
            Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
            Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
            if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composerStartRestartGroup)), composerStartRestartGroup, 0);
            composerStartRestartGroup.startReplaceableGroup(2058660585);
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 372432274, "C327@14644L10,328@14718L371:SegmentedButton.kt#uh7d8r");
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), OutlinedSegmentedButtonTokens.INSTANCE.getLabelTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1420592651, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt$SegmentedButtonContent$1$1
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
                    ComposerKt.sourceInformation(composer2, "C329@14773L24,330@14830L55,332@14899L180:SegmentedButton.kt#uh7d8r");
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1420592651, i3, -1, "androidx.compose.material3.SegmentedButtonContent.<anonymous>.<anonymous> (SegmentedButton.kt:329)");
                        }
                        composer2.startReplaceableGroup(773894976);
                        ComposerKt.sourceInformation(composer2, "CC(rememberCoroutineScope)489@20472L144:Effects.kt#9igjgp");
                        composer2.startReplaceableGroup(-492369756);
                        ComposerKt.sourceInformation(composer2, "CC(remember):Composables.kt#9igjgp");
                        Object objRememberedValue = composer2.rememberedValue();
                        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                            Object compositionScopedCoroutineScopeCanceller = new CompositionScopedCoroutineScopeCanceller(EffectsKt.createCompositionCoroutineScope(EmptyCoroutineContext.INSTANCE, composer2));
                            composer2.updateRememberedValue(compositionScopedCoroutineScopeCanceller);
                            objRememberedValue = compositionScopedCoroutineScopeCanceller;
                        }
                        composer2.endReplaceableGroup();
                        CoroutineScope coroutineScope = ((CompositionScopedCoroutineScopeCanceller) objRememberedValue).getCoroutineScope();
                        composer2.endReplaceableGroup();
                        composer2.startReplaceableGroup(-1468900584);
                        ComposerKt.sourceInformation(composer2, "CC(remember):SegmentedButton.kt#9igjgp");
                        Object objRememberedValue2 = composer2.rememberedValue();
                        if (objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue2 = new SegmentedButtonContentMeasurePolicy(coroutineScope);
                            composer2.updateRememberedValue(objRememberedValue2);
                        }
                        composer2.endReplaceableGroup();
                        Modifier modifierHeight = IntrinsicKt.height(Modifier.INSTANCE, IntrinsicSize.Min);
                        List listListOf = CollectionsKt.listOf((Object[]) new Function2[]{function2, function22});
                        composer2.startReplaceableGroup(1399185516);
                        ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)171@6874L62,168@6760L182:Layout.kt#80mrfh");
                        Function2<Composer, Integer, Unit> function2CombineAsVirtualLayouts = LayoutKt.combineAsVirtualLayouts(listListOf);
                        composer2.startReplaceableGroup(1157296644);
                        ComposerKt.sourceInformation(composer2, "CC(remember)P(1):Composables.kt#9igjgp");
                        SegmentedButtonContentMeasurePolicy segmentedButtonContentMeasurePolicy = (SegmentedButtonContentMeasurePolicy) objRememberedValue2;
                        boolean zChanged = composer2.changed(segmentedButtonContentMeasurePolicy);
                        Object objRememberedValue3 = composer2.rememberedValue();
                        if (zChanged || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                            objRememberedValue3 = MultiContentMeasurePolicyKt.createMeasurePolicy(segmentedButtonContentMeasurePolicy);
                            composer2.updateRememberedValue(objRememberedValue3);
                        }
                        composer2.endReplaceableGroup();
                        MeasurePolicy measurePolicy = (MeasurePolicy) objRememberedValue3;
                        composer2.startReplaceableGroup(-1323940314);
                        ComposerKt.sourceInformation(composer2, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer2, 0);
                        CompositionLocalMap currentCompositionLocalMap2 = composer2.getCurrentCompositionLocalMap();
                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierHeight);
                        if (!(composer2.getApplier() instanceof Applier)) {
                            ComposablesKt.invalidApplier();
                        }
                        composer2.startReusableNode();
                        if (composer2.getInserting()) {
                            composer2.createNode(constructor2);
                        } else {
                            composer2.useNode();
                        }
                        Composer composerM2989constructorimpl2 = Updater.m2989constructorimpl(composer2);
                        Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                        Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                        if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                            composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                            composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                        }
                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer2)), composer2, 0);
                        composer2.startReplaceableGroup(2058660585);
                        function2CombineAsVirtualLayouts.invoke(composer2, 0);
                        composer2.endReplaceableGroup();
                        composer2.endNode();
                        composer2.endReplaceableGroup();
                        composer2.endReplaceableGroup();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                            return;
                        }
                        return;
                    }
                    composer2.skipToGroupEnd();
                }
            }), composerStartRestartGroup, 48);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.SegmentedButtonContent.2
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
                    SegmentedButtonKt.SegmentedButtonContent(function2, function22, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    private static final State<Integer> interactionCountAsState(InteractionSource interactionSource, Composer composer, int i) {
        composer.startReplaceableGroup(281890131);
        ComposerKt.sourceInformation(composer, "C(interactionCountAsState)399@17334L33,400@17393L500,400@17372L521:SegmentedButton.kt#uh7d8r");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(281890131, i, -1, "androidx.compose.material3.interactionCountAsState (SegmentedButton.kt:398)");
        }
        composer.startReplaceableGroup(-1372284393);
        ComposerKt.sourceInformation(composer, "CC(remember):SegmentedButton.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = SnapshotIntStateKt.mutableIntStateOf(0);
            composer.updateRememberedValue(objRememberedValue);
        }
        MutableIntState mutableIntState = (MutableIntState) objRememberedValue;
        composer.endReplaceableGroup();
        composer.startReplaceableGroup(-1372284334);
        ComposerKt.sourceInformation(composer, "CC(remember):SegmentedButton.kt#9igjgp");
        int i2 = i & 14;
        boolean z = ((i2 ^ 6) > 4 && composer.changed(interactionSource)) || (i & 6) == 4;
        SegmentedButtonKt$interactionCountAsState$1$1 segmentedButtonKt$interactionCountAsState$1$1RememberedValue = composer.rememberedValue();
        if (z || segmentedButtonKt$interactionCountAsState$1$1RememberedValue == Composer.INSTANCE.getEmpty()) {
            segmentedButtonKt$interactionCountAsState$1$1RememberedValue = new SegmentedButtonKt$interactionCountAsState$1$1(interactionSource, mutableIntState, null);
            composer.updateRememberedValue(segmentedButtonKt$interactionCountAsState$1$1RememberedValue);
        }
        composer.endReplaceableGroup();
        EffectsKt.LaunchedEffect(interactionSource, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) segmentedButtonKt$interactionCountAsState$1$1RememberedValue, composer, i2);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return mutableIntState;
    }

    private static final Modifier interactionZIndex(Modifier modifier, final boolean z, final State<Integer> state) {
        return LayoutModifierKt.layout(modifier, new Function3<MeasureScope, Measurable, Constraints, MeasureResult>() { // from class: androidx.compose.material3.SegmentedButtonKt.interactionZIndex.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ MeasureResult invoke(MeasureScope measureScope, Measurable measurable, Constraints constraints) {
                return m1871invoke3p2s80s(measureScope, measurable, constraints.getValue());
            }

            /* JADX INFO: renamed from: invoke-3p2s80s, reason: not valid java name */
            public final MeasureResult m1871invoke3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
                final Placeable placeableMo4783measureBRTryo0 = measurable.mo4783measureBRTryo0(j);
                int width = placeableMo4783measureBRTryo0.getWidth();
                int height = placeableMo4783measureBRTryo0.getHeight();
                final State<Integer> state2 = state;
                final boolean z2 = z;
                return MeasureScope.CC.layout$default(measureScope, width, height, null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.material3.SegmentedButtonKt.interactionZIndex.1.1
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
                        placementScope.place(placeableMo4783measureBRTryo0, 0, 0, state2.getValue().floatValue() + (z2 ? SegmentedButtonKt.CheckedZIndexFactor : 0.0f));
                    }
                }, 4, null);
            }
        });
    }
}
