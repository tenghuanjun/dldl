package com.cy.yyjia.zhe28.ui.activity;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.layout.LayoutKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
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
import com.cy.yyjia.zhe28.R;
import com.cy.yyjia.zhe28.ui.theme.ColorKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SettingActivity2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
public final class ComposableSingletons$SettingActivity2Kt {
    public static final ComposableSingletons$SettingActivity2Kt INSTANCE = new ComposableSingletons$SettingActivity2Kt();

    /* JADX INFO: renamed from: lambda-1, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f57lambda1 = ComposableLambdaKt.composableLambdaInstance(-1219225337, false, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt$lambda-1$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 11) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1219225337, i, -1, "com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt.lambda-1.<anonymous> (SettingActivity2.kt:227)");
                }
                TextKt.m2169Text4IGK_g("修改头像、用户昵称", (Modifier) null, ColorKt.getText2(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3078, 0, 131058);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-2, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f58lambda2 = ComposableLambdaKt.composableLambdaInstance(589048422, false, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt$lambda-2$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 11) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(589048422, i, -1, "com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt.lambda-2.<anonymous> (SettingActivity2.kt:290)");
                }
                composer.startReplaceableGroup(693286680);
                ComposerKt.sourceInformation(composer, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                Modifier.Companion companion = Modifier.INSTANCE;
                MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
                composer.startReplaceableGroup(-1323940314);
                ComposerKt.sourceInformation(composer, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
                CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
                Function0<ComposeUiNode> constructor = ComposeUiNode.Companion.getConstructor();
                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
                if (!(composer.getApplier() instanceof Applier)) {
                    ComposablesKt.invalidApplier();
                }
                composer.startReusableNode();
                if (composer.getInserting()) {
                    composer.createNode(constructor);
                } else {
                    composer.useNode();
                }
                Composer composerM2989constructorimpl = Updater.m2989constructorimpl(composer);
                Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.Companion.getSetMeasurePolicy());
                Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.Companion.getSetResolvedCompositionLocals());
                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.Companion.getSetCompositeKeyHash();
                if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                    composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                    composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                }
                function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer)), composer, 0);
                composer.startReplaceableGroup(2058660585);
                ComposerKt.sourceInformationMarkerStart(composer, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                TextKt.m2169Text4IGK_g("V4.2.9", (Modifier) null, ColorKt.getText2(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer, 3072, 0, 131058);
                SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(4)), composer, 6);
                IconKt.m1641Iconww6aTOc(PainterResources_androidKt.painterResource(R.mipmap.ic_go16, composer, 6), "", rowScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterVertically()), Color.INSTANCE.m3530getUnspecified0d7_KjU(), composer, 3128, 0);
                ComposerKt.sourceInformationMarkerEnd(composer);
                composer.endReplaceableGroup();
                composer.endNode();
                composer.endReplaceableGroup();
                composer.endReplaceableGroup();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: lambda-3, reason: not valid java name */
    public static Function2<Composer, Integer, Unit> f59lambda3 = ComposableLambdaKt.composableLambdaInstance(428467721, false, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt$lambda-3$1
        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
            invoke(composer, num.intValue());
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if ((i & 11) != 2 || !composer.getSkipping()) {
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(428467721, i, -1, "com.cy.yyjia.zhe28.ui.activity.ComposableSingletons$SettingActivity2Kt.lambda-3.<anonymous> (SettingActivity2.kt:438)");
                }
                IconKt.m1641Iconww6aTOc(PainterResources_androidKt.painterResource(R.mipmap.ic_go16, composer, 6), "", (Modifier) null, Color.INSTANCE.m3530getUnspecified0d7_KjU(), composer, 3128, 4);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                    return;
                }
                return;
            }
            composer.skipToGroupEnd();
        }
    });

    /* JADX INFO: renamed from: getLambda-1$app_zhe28Release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6468getLambda1$app_zhe28Release() {
        return f57lambda1;
    }

    /* JADX INFO: renamed from: getLambda-2$app_zhe28Release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6469getLambda2$app_zhe28Release() {
        return f58lambda2;
    }

    /* JADX INFO: renamed from: getLambda-3$app_zhe28Release, reason: not valid java name */
    public final Function2<Composer, Integer, Unit> m6470getLambda3$app_zhe28Release() {
        return f59lambda3;
    }
}
