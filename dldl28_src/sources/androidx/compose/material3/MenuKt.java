package androidx.compose.material3;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.material.ripple.RippleKt;
import androidx.compose.material3.tokens.MenuTokens;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.ProvidedValue;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Menu.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000v\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0000\u001aY\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u00132\u0006\u0010\u0015\u001a\u00020\u00162\b\b\u0002\u0010\u0017\u001a\u00020\u00182\u001c\u0010\u0019\u001a\u0018\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u000e0\u001a¢\u0006\u0002\b\u001c¢\u0006\u0002\b\u001dH\u0001¢\u0006\u0002\u0010\u001e\u001a\u0080\u0001\u0010\u001f\u001a\u00020\u000e2\u0011\u0010 \u001a\r\u0012\u0004\u0012\u00020\u000e0!¢\u0006\u0002\b\u001c2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u000e0!2\u0006\u0010\u0017\u001a\u00020\u00182\u0013\u0010#\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010!¢\u0006\u0002\b\u001c2\u0013\u0010$\u001a\u000f\u0012\u0004\u0012\u00020\u000e\u0018\u00010!¢\u0006\u0002\b\u001c2\u0006\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0001¢\u0006\u0002\u0010,\u001a\u001d\u0010-\u001a\u00020\u00142\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0000¢\u0006\u0002\u00101\"\u0010\u0010\u0000\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0003\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0010\u0010\u0004\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0002\"\u0016\u0010\u0005\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u0006\u0010\u0007\"\u000e\u0010\b\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000\"\u0016\u0010\n\u001a\u00020\u0001X\u0080\u0004¢\u0006\n\n\u0002\u0010\u0002\u001a\u0004\b\u000b\u0010\u0007\"\u000e\u0010\f\u001a\u00020\tX\u0080T¢\u0006\u0002\n\u0000¨\u00062²\u0006\n\u00103\u001a\u000204X\u008a\u0084\u0002²\u0006\n\u00105\u001a\u000204X\u008a\u0084\u0002"}, d2 = {"DropdownMenuItemDefaultMaxWidth", "Landroidx/compose/ui/unit/Dp;", "F", "DropdownMenuItemDefaultMinWidth", "DropdownMenuItemHorizontalPadding", "DropdownMenuVerticalPadding", "getDropdownMenuVerticalPadding", "()F", "InTransitionDuration", "", "MenuVerticalMargin", "getMenuVerticalMargin", "OutTransitionDuration", "DropdownMenuContent", "", "expandedState", "Landroidx/compose/animation/core/MutableTransitionState;", "", "transformOriginState", "Landroidx/compose/runtime/MutableState;", "Landroidx/compose/ui/graphics/TransformOrigin;", "scrollState", "Landroidx/compose/foundation/ScrollState;", "modifier", "Landroidx/compose/ui/Modifier;", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Lkotlin/Function1;", "Landroidx/compose/foundation/layout/ColumnScope;", "Landroidx/compose/runtime/Composable;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/animation/core/MutableTransitionState;Landroidx/compose/runtime/MutableState;Landroidx/compose/foundation/ScrollState;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function3;Landroidx/compose/runtime/Composer;II)V", "DropdownMenuItemContent", "text", "Lkotlin/Function0;", "onClick", "leadingIcon", "trailingIcon", "enabled", "colors", "Landroidx/compose/material3/MenuItemColors;", "contentPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function0;Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ZLandroidx/compose/material3/MenuItemColors;Landroidx/compose/foundation/layout/PaddingValues;Landroidx/compose/foundation/interaction/MutableInteractionSource;Landroidx/compose/runtime/Composer;I)V", "calculateTransformOrigin", "anchorBounds", "Landroidx/compose/ui/unit/IntRect;", "menuBounds", "(Landroidx/compose/ui/unit/IntRect;Landroidx/compose/ui/unit/IntRect;)J", "material3_release", "scale", "", "alpha"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MenuKt {
    public static final int InTransitionDuration = 120;
    public static final int OutTransitionDuration = 75;
    private static final float MenuVerticalMargin = Dp.m5882constructorimpl(48);
    private static final float DropdownMenuItemHorizontalPadding = Dp.m5882constructorimpl(12);
    private static final float DropdownMenuVerticalPadding = Dp.m5882constructorimpl(8);
    private static final float DropdownMenuItemDefaultMinWidth = Dp.m5882constructorimpl(112);
    private static final float DropdownMenuItemDefaultMaxWidth = Dp.m5882constructorimpl(280);

    /* JADX WARN: Removed duplicated region for block: B:100:0x01f8  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0200  */
    /* JADX WARN: Removed duplicated region for block: B:106:0x0208  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x0252  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0262  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x02c2  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x02cc  */
    /* JADX WARN: Removed duplicated region for block: B:123:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x0089  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:64:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00b4  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00bc  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x010c  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0115  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x0118  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0121  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0148  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x014b  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01c2  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01ca  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01ce  */
    /* JADX WARN: Removed duplicated region for block: B:94:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x01d8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void DropdownMenuContent(final androidx.compose.animation.core.MutableTransitionState<java.lang.Boolean> r23, final androidx.compose.runtime.MutableState<androidx.compose.ui.graphics.TransformOrigin> r24, final androidx.compose.foundation.ScrollState r25, androidx.compose.ui.Modifier r26, final kotlin.jvm.functions.Function3<? super androidx.compose.foundation.layout.ColumnScope, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r27, androidx.compose.runtime.Composer r28, final int r29, final int r30) {
        /*
            Method dump skipped, instruction units count: 741
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MenuKt.DropdownMenuContent(androidx.compose.animation.core.MutableTransitionState, androidx.compose.runtime.MutableState, androidx.compose.foundation.ScrollState, androidx.compose.ui.Modifier, kotlin.jvm.functions.Function3, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void DropdownMenuItemContent(final Function2<? super Composer, ? super Integer, Unit> function2, final Function0<Unit> function0, final Modifier modifier, final Function2<? super Composer, ? super Integer, Unit> function22, final Function2<? super Composer, ? super Integer, Unit> function23, final boolean z, final MenuItemColors menuItemColors, final PaddingValues paddingValues, final MutableInteractionSource mutableInteractionSource, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1564716777);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(DropdownMenuItemContent)P(7,6,5,4,8,2)312@12376L20,306@12132L2357:Menu.kt#uh7d8r");
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function2) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function0) ? 32 : 16;
        }
        if ((i & 384) == 0) {
            i2 |= composerStartRestartGroup.changed(modifier) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function22) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function23) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(z) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(menuItemColors) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(paddingValues) ? 8388608 : 4194304;
        }
        if ((100663296 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(mutableInteractionSource) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((38347923 & i2) != 38347922 || !composerStartRestartGroup.getSkipping()) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1564716777, i2, -1, "androidx.compose.material3.DropdownMenuItemContent (Menu.kt:304)");
            }
            Modifier modifierPadding = PaddingKt.padding(SizeKt.m651sizeInqDBjuR0$default(SizeKt.fillMaxWidth$default(ClickableKt.m273clickableO2vRcR0(modifier, mutableInteractionSource, RippleKt.m1258rememberRipple9IZ8Weo(true, 0.0f, 0L, composerStartRestartGroup, 6, 6), (8 & 4) != 0 ? true : z, (8 & 8) != 0 ? null : null, (8 & 16) != 0 ? null : null, function0), 0.0f, 1, null), DropdownMenuItemDefaultMinWidth, MenuTokens.INSTANCE.m2661getListItemContainerHeightD9Ej5fM(), DropdownMenuItemDefaultMaxWidth, 0.0f, 8, null), paddingValues);
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            composerStartRestartGroup.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
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
            final RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 1557325246, "C324@12879L10,324@12848L1635:Menu.kt#uh7d8r");
            TextKt.ProvideTextStyle(TypographyKt.fromToken(MaterialTheme.INSTANCE.getTypography(composerStartRestartGroup, 6), MenuTokens.INSTANCE.getListItemLabelTextFont()), ComposableLambdaKt.composableLambda(composerStartRestartGroup, 1065051884, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuItemContent$1$1
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
                    ComposerKt.sourceInformation(composer2, "C334@13380L18,334@13321L777,356@14235L26,355@14155L304:Menu.kt#uh7d8r");
                    if ((i3 & 3) != 2 || !composer2.getSkipping()) {
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1065051884, i3, -1, "androidx.compose.material3.DropdownMenuItemContent.<anonymous>.<anonymous> (Menu.kt:325)");
                        }
                        composer2.startReplaceableGroup(1426260804);
                        ComposerKt.sourceInformation(composer2, "327@13072L25,326@12992L302");
                        if (function22 != null) {
                            ProvidedValue<Color> providedValueProvides = ContentColorKt.getLocalContentColor().provides(Color.m3484boximpl(menuItemColors.m1713leadingIconColorXeAY9LY$material3_release(z, composer2, 0)));
                            final Function2<Composer, Integer, Unit> function24 = function22;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides, ComposableLambdaKt.composableLambda(composer2, 2035552199, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuItemContent$1$1.1
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i4) {
                                    ComposerKt.sourceInformation(composer3, "C329@13139L137:Menu.kt#uh7d8r");
                                    if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(2035552199, i4, -1, "androidx.compose.material3.DropdownMenuItemContent.<anonymous>.<anonymous>.<anonymous> (Menu.kt:329)");
                                        }
                                        Modifier modifierM632defaultMinSizeVpY3zN4$default = SizeKt.m632defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, MenuTokens.INSTANCE.m2662getListItemLeadingIconSizeD9Ej5fM(), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function25 = function24;
                                        composer3.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation(composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                        composer3.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM632defaultMinSizeVpY3zN4$default);
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM2989constructorimpl2 = Updater.m2989constructorimpl(composer3);
                                        Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                        composer3.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 116818474, "C330@13241L13:Menu.kt#uh7d8r");
                                        function25.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endReplaceableGroup();
                                        composer3.endNode();
                                        composer3.endReplaceableGroup();
                                        composer3.endReplaceableGroup();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }), composer2, ProvidedValue.$stable | 48);
                        }
                        composer2.endReplaceableGroup();
                        ProvidedValue<Color> providedValueProvides2 = ContentColorKt.getLocalContentColor().provides(menuItemColors.textColor$material3_release(z, composer2, 0).getValue());
                        final RowScope rowScope = rowScopeInstance;
                        final Function2<Composer, Integer, Unit> function25 = function22;
                        final Function2<Composer, Integer, Unit> function26 = function23;
                        final Function2<Composer, Integer, Unit> function27 = function2;
                        CompositionLocalKt.CompositionLocalProvider(providedValueProvides2, ComposableLambdaKt.composableLambda(composer2, -1728894036, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuItemContent$1$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(2);
                            }

                            @Override // kotlin.jvm.functions.Function2
                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                invoke(composer3, num.intValue());
                                return Unit.INSTANCE;
                            }

                            public final void invoke(Composer composer3, int i4) {
                                float fM5882constructorimpl;
                                float fM5882constructorimpl2;
                                ComposerKt.sourceInformation(composer3, "C335@13424L660:Menu.kt#uh7d8r");
                                if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventStart(-1728894036, i4, -1, "androidx.compose.material3.DropdownMenuItemContent.<anonymous>.<anonymous>.<anonymous> (Menu.kt:335)");
                                    }
                                    Modifier modifierWeight$default = RowScope.CC.weight$default(rowScope, Modifier.INSTANCE, 1.0f, false, 2, null);
                                    if (function25 != null) {
                                        fM5882constructorimpl = MenuKt.DropdownMenuItemHorizontalPadding;
                                    } else {
                                        fM5882constructorimpl = Dp.m5882constructorimpl(0);
                                    }
                                    float f = fM5882constructorimpl;
                                    if (function26 != null) {
                                        fM5882constructorimpl2 = MenuKt.DropdownMenuItemHorizontalPadding;
                                    } else {
                                        fM5882constructorimpl2 = Dp.m5882constructorimpl(0);
                                    }
                                    Modifier modifierM602paddingqDBjuR0$default = PaddingKt.m602paddingqDBjuR0$default(modifierWeight$default, f, 0.0f, fM5882constructorimpl2, 0.0f, 10, null);
                                    Function2<Composer, Integer, Unit> function28 = function27;
                                    composer3.startReplaceableGroup(733328855);
                                    ComposerKt.sourceInformation(composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                    MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                    composer3.startReplaceableGroup(-1323940314);
                                    ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                    int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                    Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM602paddingqDBjuR0$default);
                                    if (!(composer3.getApplier() instanceof Applier)) {
                                        ComposablesKt.invalidApplier();
                                    }
                                    composer3.startReusableNode();
                                    if (composer3.getInserting()) {
                                        composer3.createNode(constructor2);
                                    } else {
                                        composer3.useNode();
                                    }
                                    Composer composerM2989constructorimpl2 = Updater.m2989constructorimpl(composer3);
                                    Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                    Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                    if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                        composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                        composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                    }
                                    function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                    composer3.startReplaceableGroup(2058660585);
                                    ComposerKt.sourceInformationMarkerStart(composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                    ComposerKt.sourceInformationMarkerStart(composer3, 116819293, "C351@14060L6:Menu.kt#uh7d8r");
                                    function28.invoke(composer3, 0);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    ComposerKt.sourceInformationMarkerEnd(composer3);
                                    composer3.endReplaceableGroup();
                                    composer3.endNode();
                                    composer3.endReplaceableGroup();
                                    composer3.endReplaceableGroup();
                                    if (ComposerKt.isTraceInProgress()) {
                                        ComposerKt.traceEventEnd();
                                        return;
                                    }
                                    return;
                                }
                                composer3.skipToGroupEnd();
                            }
                        }), composer2, ProvidedValue.$stable | 48);
                        if (function23 != null) {
                            ProvidedValue<Color> providedValueProvides3 = ContentColorKt.getLocalContentColor().provides(Color.m3484boximpl(menuItemColors.m1714trailingIconColorXeAY9LY$material3_release(z, composer2, 0)));
                            final Function2<Composer, Integer, Unit> function28 = function23;
                            CompositionLocalKt.CompositionLocalProvider(providedValueProvides3, ComposableLambdaKt.composableLambda(composer2, 580312062, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt$DropdownMenuItemContent$1$1.3
                                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                /* JADX WARN: Multi-variable type inference failed */
                                {
                                    super(2);
                                }

                                @Override // kotlin.jvm.functions.Function2
                                public /* bridge */ /* synthetic */ Unit invoke(Composer composer3, Integer num) {
                                    invoke(composer3, num.intValue());
                                    return Unit.INSTANCE;
                                }

                                public final void invoke(Composer composer3, int i4) {
                                    ComposerKt.sourceInformation(composer3, "C358@14302L139:Menu.kt#uh7d8r");
                                    if ((i4 & 3) != 2 || !composer3.getSkipping()) {
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventStart(580312062, i4, -1, "androidx.compose.material3.DropdownMenuItemContent.<anonymous>.<anonymous>.<anonymous> (Menu.kt:358)");
                                        }
                                        Modifier modifierM632defaultMinSizeVpY3zN4$default = SizeKt.m632defaultMinSizeVpY3zN4$default(Modifier.INSTANCE, MenuTokens.INSTANCE.m2663getListItemTrailingIconSizeD9Ej5fM(), 0.0f, 2, null);
                                        Function2<Composer, Integer, Unit> function29 = function28;
                                        composer3.startReplaceableGroup(733328855);
                                        ComposerKt.sourceInformation(composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                        MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                        composer3.startReplaceableGroup(-1323940314);
                                        ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)77@3132L23,79@3222L420:Layout.kt#80mrfh");
                                        int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                        CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                        Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM632defaultMinSizeVpY3zN4$default);
                                        if (!(composer3.getApplier() instanceof Applier)) {
                                            ComposablesKt.invalidApplier();
                                        }
                                        composer3.startReusableNode();
                                        if (composer3.getInserting()) {
                                            composer3.createNode(constructor2);
                                        } else {
                                            composer3.useNode();
                                        }
                                        Composer composerM2989constructorimpl2 = Updater.m2989constructorimpl(composer3);
                                        Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                        Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                        if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                            composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                            composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                        }
                                        function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                        composer3.startReplaceableGroup(2058660585);
                                        ComposerKt.sourceInformationMarkerStart(composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                        BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                        ComposerKt.sourceInformationMarkerStart(composer3, 116819638, "C359@14405L14:Menu.kt#uh7d8r");
                                        function29.invoke(composer3, 0);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        ComposerKt.sourceInformationMarkerEnd(composer3);
                                        composer3.endReplaceableGroup();
                                        composer3.endNode();
                                        composer3.endReplaceableGroup();
                                        composer3.endReplaceableGroup();
                                        if (ComposerKt.isTraceInProgress()) {
                                            ComposerKt.traceEventEnd();
                                            return;
                                        }
                                        return;
                                    }
                                    composer3.skipToGroupEnd();
                                }
                            }), composer2, ProvidedValue.$stable | 48);
                        }
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.MenuKt.DropdownMenuItemContent.2
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
                    MenuKt.DropdownMenuItemContent(function2, function0, modifier, function22, function23, z, menuItemColors, paddingValues, mutableInteractionSource, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1));
                }
            });
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:4:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final long calculateTransformOrigin(androidx.compose.ui.unit.IntRect r5, androidx.compose.ui.unit.IntRect r6) {
        /*
            int r0 = r6.getLeft()
            int r1 = r5.getRight()
            r2 = 1065353216(0x3f800000, float:1.0)
            r3 = 0
            if (r0 < r1) goto Lf
        Ld:
            r0 = 0
            goto L4a
        Lf:
            int r0 = r6.getRight()
            int r1 = r5.getLeft()
            if (r0 > r1) goto L1c
            r0 = 1065353216(0x3f800000, float:1.0)
            goto L4a
        L1c:
            int r0 = r6.getWidth()
            if (r0 != 0) goto L23
            goto Ld
        L23:
            int r0 = r5.getLeft()
            int r1 = r6.getLeft()
            int r0 = java.lang.Math.max(r0, r1)
            int r1 = r5.getRight()
            int r4 = r6.getRight()
            int r1 = java.lang.Math.min(r1, r4)
            int r0 = r0 + r1
            int r0 = r0 / 2
            int r1 = r6.getLeft()
            int r0 = r0 - r1
            float r0 = (float) r0
            int r1 = r6.getWidth()
            float r1 = (float) r1
            float r0 = r0 / r1
        L4a:
            int r1 = r6.getTop()
            int r4 = r5.getBottom()
            if (r1 < r4) goto L56
        L54:
            r2 = 0
            goto L90
        L56:
            int r1 = r6.getBottom()
            int r4 = r5.getTop()
            if (r1 > r4) goto L61
            goto L90
        L61:
            int r1 = r6.getHeight()
            if (r1 != 0) goto L68
            goto L54
        L68:
            int r1 = r5.getTop()
            int r2 = r6.getTop()
            int r1 = java.lang.Math.max(r1, r2)
            int r5 = r5.getBottom()
            int r2 = r6.getBottom()
            int r5 = java.lang.Math.min(r5, r2)
            int r1 = r1 + r5
            int r1 = r1 / 2
            int r5 = r6.getTop()
            int r1 = r1 - r5
            float r5 = (float) r1
            int r6 = r6.getHeight()
            float r6 = (float) r6
            float r2 = r5 / r6
        L90:
            long r5 = androidx.compose.ui.graphics.TransformOriginKt.TransformOrigin(r0, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.MenuKt.calculateTransformOrigin(androidx.compose.ui.unit.IntRect, androidx.compose.ui.unit.IntRect):long");
    }

    public static final float getMenuVerticalMargin() {
        return MenuVerticalMargin;
    }

    public static final float getDropdownMenuVerticalPadding() {
        return DropdownMenuVerticalPadding;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final float DropdownMenuContent$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }
}
