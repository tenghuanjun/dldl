package com.cy.yyjia.zhe28.ui.theme;

import android.content.Context;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.compose.ui.res.PainterResources_androidKt;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import androidx.compose.ui.viewinterop.AndroidView_androidKt;
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.util.Util;
import com.cy.yyjia.zhe28.view.CountdownView;
import com.mobile.auth.gatewayauth.Constant;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Component.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001ag\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\t2\b\b\u0002\u0010\n\u001a\u00020\u00072\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000e2\b\b\u0002\u0010\u000f\u001a\u00020\u00102\b\b\u0002\u0010\u0011\u001a\u00020\u0012H\u0007¢\u0006\u0002\u0010\u0013\u001a+\u0010\u0014\u001a\u00020\u00012\b\b\u0002\u0010\u0015\u001a\u00020\f2\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u00010\tH\u0007¢\u0006\u0002\u0010\u0016\u001a\u001f\u0010\u0017\u001a\u00020\u0003*\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\u0018H\u0007¢\u0006\u0002\u0010\u0019¨\u0006\u001a²\u0006\n\u0010\u001b\u001a\u00020\fX\u008a\u008e\u0002"}, d2 = {"CountdownView", "", "modifier", "Landroidx/compose/ui/Modifier;", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;II)V", "Input", "text", "", "onValueChange", "Lkotlin/Function1;", "hint", "singleLine", "", "inputType", "Landroidx/compose/ui/text/input/VisualTransformation;", "keyboardOptions", "Landroidx/compose/foundation/text/KeyboardOptions;", "textStyle", "Landroidx/compose/ui/text/TextStyle;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function1;Ljava/lang/String;Landroidx/compose/ui/Modifier;ZLandroidx/compose/ui/text/input/VisualTransformation;Landroidx/compose/foundation/text/KeyboardOptions;Landroidx/compose/ui/text/TextStyle;Landroidx/compose/runtime/Composer;II)V", "PolicyAgreeView", "agree", "(ZLkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "onClick", "Lkotlin/Function0;", "(Landroidx/compose/ui/Modifier;Lkotlin/jvm/functions/Function0;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;", "app_zhe28Release", "state"}, k = 2, mv = {1, 9, 0}, xi = 48)
public final class ComponentKt {
    public static final Modifier onClick(Modifier modifier, Function0<Unit> onClick, Composer composer, int i) {
        Intrinsics.checkNotNullParameter(modifier, "<this>");
        Intrinsics.checkNotNullParameter(onClick, "onClick");
        composer.startReplaceableGroup(-893151597);
        ComposerKt.sourceInformation(composer, "C(onClick)");
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-893151597, i, -1, "com.cy.yyjia.zhe28.ui.theme.onClick (Component.kt:42)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        composer.startReplaceableGroup(-492369756);
        ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
        Object objRememberedValue = composer.rememberedValue();
        if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = InteractionSourceKt.MutableInteractionSource();
            composer.updateRememberedValue(objRememberedValue);
        }
        composer.endReplaceableGroup();
        Modifier modifierThen = modifier.then(ClickableKt.m273clickableO2vRcR0(companion, (MutableInteractionSource) objRememberedValue, null, (8 & 4) != 0, (8 & 8) != 0 ? null : null, (8 & 16) != 0 ? null : null, onClick));
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceableGroup();
        return modifierThen;
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:102:0x013a  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x013d  */
    /* JADX WARN: Removed duplicated region for block: B:105:0x0141  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x014e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x0152  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0168  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x016c  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:117:0x017d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0190  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x01bb  */
    /* JADX WARN: Removed duplicated region for block: B:124:0x0218  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x0230  */
    /* JADX WARN: Removed duplicated region for block: B:131:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0071  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008d  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00ac  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00af  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00d2  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x00e9  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00f0  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x0127  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0132  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final void Input(java.lang.String r31, final kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> r32, java.lang.String r33, androidx.compose.ui.Modifier r34, boolean r35, androidx.compose.ui.text.input.VisualTransformation r36, androidx.compose.foundation.text.KeyboardOptions r37, androidx.compose.ui.text.TextStyle r38, androidx.compose.runtime.Composer r39, final int r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 578
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.ui.theme.ComponentKt.Input(java.lang.String, kotlin.jvm.functions.Function1, java.lang.String, androidx.compose.ui.Modifier, boolean, androidx.compose.ui.text.input.VisualTransformation, androidx.compose.foundation.text.KeyboardOptions, androidx.compose.ui.text.TextStyle, androidx.compose.runtime.Composer, int, int):void");
    }

    public static final void PolicyAgreeView(boolean z, final Function1<? super Boolean, Unit> onValueChange, Composer composer, final int i, final int i2) {
        final boolean z2;
        int i3;
        Composer composer2;
        Intrinsics.checkNotNullParameter(onValueChange, "onValueChange");
        Composer composerStartRestartGroup = composer.startRestartGroup(1698010930);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(PolicyAgreeView)");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
            z2 = z;
        } else if ((i & 14) == 0) {
            z2 = z;
            i3 = (composerStartRestartGroup.changed(z2) ? 4 : 2) | i;
        } else {
            z2 = z;
            i3 = i;
        }
        if ((i2 & 2) != 0) {
            i3 |= 48;
        } else if ((i & 112) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(onValueChange) ? 32 : 16;
        }
        if ((i3 & 91) != 18 || !composerStartRestartGroup.getSkipping()) {
            boolean z3 = i4 != 0 ? false : z2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1698010930, i3, -1, "com.cy.yyjia.zhe28.ui.theme.PolicyAgreeView (Component.kt:93)");
            }
            composerStartRestartGroup.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(z3), null, 2, null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            composerStartRestartGroup.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue;
            ProvidableCompositionLocal<Context> localContext = AndroidCompositionLocals_androidKt.getLocalContext();
            ComposerKt.sourceInformationMarkerStart(composerStartRestartGroup, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composerStartRestartGroup.consume(localContext);
            ComposerKt.sourceInformationMarkerEnd(composerStartRestartGroup);
            final Context context = (Context) objConsume;
            Alignment.Vertical centerVertically = Alignment.INSTANCE.getCenterVertically();
            Modifier.Companion companion = Modifier.INSTANCE;
            composerStartRestartGroup.startReplaceableGroup(511388516);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(remember)P(1,2):Composables.kt#9igjgp");
            boolean zChanged = composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(onValueChange);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt$PolicyAgreeView$1$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    /* JADX WARN: Multi-variable type inference failed */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        ComponentKt.PolicyAgreeView$lambda$3(mutableState, !ComponentKt.PolicyAgreeView$lambda$2(r0));
                        onValueChange.invoke(Boolean.valueOf(ComponentKt.PolicyAgreeView$lambda$2(mutableState)));
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            composerStartRestartGroup.endReplaceableGroup();
            Modifier modifierOnClick = onClick(companion, (Function0) objRememberedValue2, composerStartRestartGroup, 6);
            composerStartRestartGroup.startReplaceableGroup(693286680);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), centerVertically, composerStartRestartGroup, 48);
            composerStartRestartGroup.startReplaceableGroup(-1323940314);
            ComposerKt.sourceInformation(composerStartRestartGroup, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(modifierOnClick);
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
            ImageKt.Image(PainterResources_androidKt.painterResource(PolicyAgreeView$lambda$2(mutableState) ? R.mipmap.checkbox_checked2 : R.mipmap.checkbox_unchecked2, composerStartRestartGroup, 0), "", (Modifier) null, (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composerStartRestartGroup, 56, 124);
            SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(8)), composerStartRestartGroup, 6);
            TextKt.m2169Text4IGK_g(Constant.CHECKBOX_NO_HIDDEN_PROTOCOL_PREFIX, (Modifier) null, ColorKt.getText2(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3078, 0, 131058);
            TextKt.m2169Text4IGK_g("用户协议", onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt$PolicyAgreeView$2$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Util.openProtocol(context, "用户协议", "userAgreement");
                }
            }, composerStartRestartGroup, 6), ColorKt.getPrimary(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3078, 0, 131056);
            TextKt.m2169Text4IGK_g("和", (Modifier) null, ColorKt.getText2(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composerStartRestartGroup, 3078, 0, 131058);
            composer2 = composerStartRestartGroup;
            TextKt.m2169Text4IGK_g("隐私政策", onClick(Modifier.INSTANCE, new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt$PolicyAgreeView$2$2
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Unit invoke() {
                    invoke2();
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2() {
                    Util.openProtocol(context, "隐私政策", "privacyPolicy");
                }
            }, composerStartRestartGroup, 6), ColorKt.getPrimary(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer2, 3078, 0, 131056);
            ComposerKt.sourceInformationMarkerEnd(composer2);
            composer2.endReplaceableGroup();
            composer2.endNode();
            composer2.endReplaceableGroup();
            composer2.endReplaceableGroup();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            z2 = z3;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            composer2 = composerStartRestartGroup;
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt.PolicyAgreeView.3
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

            public final void invoke(Composer composer3, int i5) {
                ComponentKt.PolicyAgreeView(z2, onValueChange, composer3, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean PolicyAgreeView$lambda$2(MutableState<Boolean> mutableState) {
        return mutableState.getValue().booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void PolicyAgreeView$lambda$3(MutableState<Boolean> mutableState, boolean z) {
        mutableState.setValue(Boolean.valueOf(z));
    }

    public static final void CountdownView(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1701058919);
        ComposerKt.sourceInformation(composerStartRestartGroup, "C(CountdownView)");
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 14) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i3 & 11) != 2 || !composerStartRestartGroup.getSkipping()) {
            if (i4 != 0) {
                modifier = Modifier.INSTANCE;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1701058919, i3, -1, "com.cy.yyjia.zhe28.ui.theme.CountdownView (Component.kt:117)");
            }
            AndroidView_androidKt.AndroidView(new Function1<Context, CountdownView>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt.CountdownView.1
                @Override // kotlin.jvm.functions.Function1
                public final CountdownView invoke(Context context) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    return new CountdownView(context);
                }
            }, modifier, null, composerStartRestartGroup, ((i3 << 3) & 112) | 6, 4);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup == null) {
            return;
        }
        scopeUpdateScopeEndRestartGroup.updateScope(new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.theme.ComponentKt.CountdownView.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                invoke(composer2, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer2, int i5) {
                ComponentKt.CountdownView(modifier, composer2, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
            }
        });
    }
}
