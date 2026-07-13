package com.cy.yyjia.zhe28.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import androidx.activity.compose.ComponentActivityKt;
import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.ImageKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.lazy.LazyDslKt;
import androidx.compose.foundation.lazy.LazyItemScope;
import androidx.compose.foundation.lazy.LazyListScope;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.ScaffoldKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.Applier;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SkippableUpdater;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.painter.Painter;
import androidx.compose.ui.layout.ContentScale;
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
import com.cy.yyjia.zhe28.base.BaseComponentActivity;
import com.cy.yyjia.zhe28.base.BaseDialog;
import com.cy.yyjia.zhe28.domain.LoginChangeBean;
import com.cy.yyjia.zhe28.domain.UpdateBean;
import com.cy.yyjia.zhe28.domain.UserBean;
import com.cy.yyjia.zhe28.ui.dialog.UpdateDialog;
import com.cy.yyjia.zhe28.ui.theme.ComponentKt;
import com.cy.yyjia.zhe28.ui.theme.ThemeKt;
import com.cy.yyjia.zhe28.util.Constant;
import com.cy.yyjia.zhe28.util.Repository;
import com.cy.yyjia.zhe28.util.Util;
import com.lzy.okserver.OkDownload;
import com.volcengine.cloudcore.common.mode.KeyBoardKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.apache.commons.lang3.BooleanUtils;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: SettingActivity2.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0006\u001a\u00020\"J\b\u0010#\u001a\u00020\"H\u0016J\b\u0010$\u001a\u00020\"H\u0016J\u0006\u0010%\u001a\u00020\"J\b\u0010&\u001a\u00020\"H\u0016J\b\u0010'\u001a\u00020\"H\u0014J\u0006\u0010(\u001a\u00020\"R+\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\n\u0010\u000b\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR+\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R+\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u000b\u001a\u0004\b\u0014\u0010\u000f\"\u0004\b\u0015\u0010\u0011R+\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001a\u0010\u000b\u001a\u0004\b\u0018\u0010\u000f\"\u0004\b\u0019\u0010\u0011R+\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0003\u001a\u00020\u001b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b!\u0010\u000b\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 ¨\u0006)"}, d2 = {"Lcom/cy/yyjia/zhe28/ui/activity/SettingActivity2;", "Lcom/cy/yyjia/zhe28/base/BaseComponentActivity;", "()V", "<set-?>", "", "cache", "getCache", "()Ljava/lang/String;", "setCache", "(Ljava/lang/String;)V", "cache$delegate", "Landroidx/compose/runtime/MutableState;", "", "deleteApk", "getDeleteApk", "()Z", "setDeleteApk", "(Z)V", "deleteApk$delegate", "privacy", "getPrivacy", "setPrivacy", "privacy$delegate", "setting", "getSetting", "setSetting", "setting$delegate", "Lcom/cy/yyjia/zhe28/domain/UserBean;", "userData", "getUserData", "()Lcom/cy/yyjia/zhe28/domain/UserBean;", "setUserData", "(Lcom/cy/yyjia/zhe28/domain/UserBean;)V", "userData$delegate", "", "init", "initView", "logout", "onBackPressed", "onResume", "update", "app_zhe28Release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class SettingActivity2 extends BaseComponentActivity {
    public static final int $stable = 0;

    /* JADX INFO: renamed from: cache$delegate, reason: from kotlin metadata */
    private final MutableState cache;

    /* JADX INFO: renamed from: deleteApk$delegate, reason: from kotlin metadata */
    private final MutableState deleteApk;

    /* JADX INFO: renamed from: privacy$delegate, reason: from kotlin metadata */
    private final MutableState privacy;

    /* JADX INFO: renamed from: setting$delegate, reason: from kotlin metadata */
    private final MutableState setting;

    /* JADX INFO: renamed from: userData$delegate, reason: from kotlin metadata */
    private final MutableState userData;

    public SettingActivity2() {
        super(-1);
        this.userData = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new UserBean(), null, 2, null);
        this.setting = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.privacy = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(false, null, 2, null);
        this.deleteApk = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(Boolean.valueOf(Constant.INSTANCE.getDeleteApk()), null, 2, null);
        this.cache = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default("", null, 2, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final UserBean getUserData() {
        return (UserBean) this.userData.getValue();
    }

    public final void setUserData(UserBean userBean) {
        Intrinsics.checkNotNullParameter(userBean, "<set-?>");
        this.userData.setValue(userBean);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getSetting() {
        return ((Boolean) this.setting.getValue()).booleanValue();
    }

    public final void setSetting(boolean z) {
        this.setting.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getPrivacy() {
        return ((Boolean) this.privacy.getValue()).booleanValue();
    }

    public final void setPrivacy(boolean z) {
        this.privacy.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean getDeleteApk() {
        return ((Boolean) this.deleteApk.getValue()).booleanValue();
    }

    public final void setDeleteApk(boolean z) {
        this.deleteApk.setValue(Boolean.valueOf(z));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final String getCache() {
        return (String) this.cache.getValue();
    }

    public final void setCache(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.cache.setValue(str);
    }

    @Override // com.cy.yyjia.zhe28.base.BaseComponentActivity
    public void init() throws Exception {
        m6551getCache();
    }

    @Override // com.cy.yyjia.zhe28.base.BaseComponentActivity
    public void initView() {
        ComponentActivityKt.setContent$default(this, null, ComposableLambdaKt.composableLambdaInstance(914773029, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(Composer composer, Integer num) {
                invoke(composer, num.intValue());
                return Unit.INSTANCE;
            }

            public final void invoke(Composer composer, int i) {
                if ((i & 11) != 2 || !composer.getSkipping()) {
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(914773029, i, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous> (SettingActivity2.kt:70)");
                    }
                    final SettingActivity2 settingActivity2 = SettingActivity2.this;
                    ThemeKt.TransparentTheme(ComposableLambdaKt.composableLambda(composer, 1989500489, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.1.1
                        {
                            super(2);
                        }

                        @Override // kotlin.jvm.functions.Function2
                        public /* bridge */ /* synthetic */ Unit invoke(Composer composer2, Integer num) {
                            invoke(composer2, num.intValue());
                            return Unit.INSTANCE;
                        }

                        public final void invoke(Composer composer2, int i2) {
                            if ((i2 & 11) != 2 || !composer2.getSkipping()) {
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventStart(1989500489, i2, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous> (SettingActivity2.kt:71)");
                                }
                                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(Modifier.INSTANCE, 0.0f, 1, null);
                                final SettingActivity2 settingActivity22 = settingActivity2;
                                ScaffoldKt.m1824ScaffoldTvnljyQ(modifierFillMaxSize$default, null, null, null, null, 0, 0L, 0L, null, ComposableLambdaKt.composableLambda(composer2, -2050625000, true, new Function3<PaddingValues, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.1.1.1
                                    {
                                        super(3);
                                    }

                                    @Override // kotlin.jvm.functions.Function3
                                    public /* bridge */ /* synthetic */ Unit invoke(PaddingValues paddingValues, Composer composer3, Integer num) {
                                        invoke(paddingValues, composer3, num.intValue());
                                        return Unit.INSTANCE;
                                    }

                                    public final void invoke(PaddingValues innerPadding, Composer composer3, int i3) {
                                        Intrinsics.checkNotNullParameter(innerPadding, "innerPadding");
                                        if ((i3 & 81) != 16 || !composer3.getSkipping()) {
                                            if (ComposerKt.isTraceInProgress()) {
                                                ComposerKt.traceEventStart(-2050625000, i3, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:74)");
                                            }
                                            final SettingActivity2 settingActivity23 = settingActivity22;
                                            composer3.startReplaceableGroup(693286680);
                                            ComposerKt.sourceInformation(composer3, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                            Modifier.Companion companion = Modifier.INSTANCE;
                                            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer3, 0);
                                            composer3.startReplaceableGroup(-1323940314);
                                            ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                            CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                                            Function0<ComposeUiNode> constructor = ComposeUiNode.INSTANCE.getConstructor();
                                            Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf = LayoutKt.modifierMaterializerOf(companion);
                                            if (!(composer3.getApplier() instanceof Applier)) {
                                                ComposablesKt.invalidApplier();
                                            }
                                            composer3.startReusableNode();
                                            if (composer3.getInserting()) {
                                                composer3.createNode(constructor);
                                            } else {
                                                composer3.useNode();
                                            }
                                            Composer composerM2989constructorimpl = Updater.m2989constructorimpl(composer3);
                                            Updater.m2996setimpl(composerM2989constructorimpl, measurePolicyRowMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                            Updater.m2996setimpl(composerM2989constructorimpl, currentCompositionLocalMap, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                            if (composerM2989constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                                                composerM2989constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                                                composerM2989constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
                                            }
                                            function3ModifierMaterializerOf.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                            composer3.startReplaceableGroup(2058660585);
                                            ComposerKt.sourceInformationMarkerStart(composer3, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                            Modifier modifierM242backgroundbw27NRU$default = BackgroundKt.m242backgroundbw27NRU$default(SizeKt.fillMaxHeight$default(RowScope.CC.weight$default(RowScopeInstance.INSTANCE, Modifier.INSTANCE, 1.0f, false, 2, null), 0.0f, 1, null), ColorKt.Color(1711276032), null, 2, null);
                                            composer3.startReplaceableGroup(1157296644);
                                            ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                            boolean zChanged = composer3.changed(settingActivity23);
                                            Object objRememberedValue = composer3.rememberedValue();
                                            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                                                objRememberedValue = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$1$1
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
                                                        settingActivity23.finish();
                                                    }
                                                };
                                                composer3.updateRememberedValue(objRememberedValue);
                                            }
                                            composer3.endReplaceableGroup();
                                            BoxKt.Box(ComponentKt.onClick(modifierM242backgroundbw27NRU$default, (Function0) objRememberedValue, composer3, 0), composer3, 0);
                                            if (settingActivity23.getSetting()) {
                                                composer3.startReplaceableGroup(-914653873);
                                                Modifier modifierM652width3ABfNKs = SizeKt.m652width3ABfNKs(SizeKt.fillMaxHeight$default(BackgroundKt.m242backgroundbw27NRU$default(Modifier.INSTANCE, Color.INSTANCE.m3531getWhite0d7_KjU(), null, 2, null), 0.0f, 1, null), Dp.m5882constructorimpl(325));
                                                composer3.startReplaceableGroup(-483455358);
                                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash2 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor2 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf2 = LayoutKt.modifierMaterializerOf(modifierM652width3ABfNKs);
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
                                                Updater.m2996setimpl(composerM2989constructorimpl2, measurePolicyColumnMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl2, currentCompositionLocalMap2, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash2 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl2.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl2.rememberedValue(), Integer.valueOf(currentCompositeKeyHash2))) {
                                                    composerM2989constructorimpl2.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash2));
                                                    composerM2989constructorimpl2.apply(Integer.valueOf(currentCompositeKeyHash2), setCompositeKeyHash2);
                                                }
                                                function3ModifierMaterializerOf2.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                                                Modifier modifierM602paddingqDBjuR0$default = PaddingKt.m602paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(SizeKt.m633height3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(58)), 0.0f, 1, null), Dp.m5882constructorimpl(16), 0.0f, 0.0f, 0.0f, 14, null);
                                                composer3.startReplaceableGroup(733328855);
                                                ComposerKt.sourceInformation(composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                                MeasurePolicy measurePolicyRememberBoxMeasurePolicy = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash3 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor3 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf3 = LayoutKt.modifierMaterializerOf(modifierM602paddingqDBjuR0$default);
                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer3.startReusableNode();
                                                if (composer3.getInserting()) {
                                                    composer3.createNode(constructor3);
                                                } else {
                                                    composer3.useNode();
                                                }
                                                Composer composerM2989constructorimpl3 = Updater.m2989constructorimpl(composer3);
                                                Updater.m2996setimpl(composerM2989constructorimpl3, measurePolicyRememberBoxMeasurePolicy, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl3, currentCompositionLocalMap3, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash3 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl3.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl3.rememberedValue(), Integer.valueOf(currentCompositeKeyHash3))) {
                                                    composerM2989constructorimpl3.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash3));
                                                    composerM2989constructorimpl3.apply(Integer.valueOf(currentCompositeKeyHash3), setCompositeKeyHash3);
                                                }
                                                function3ModifierMaterializerOf3.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                                                Painter painterPainterResource = PainterResources_androidKt.painterResource(R.mipmap.ic_back24, composer3, 6);
                                                long jM3530getUnspecified0d7_KjU = Color.INSTANCE.m3530getUnspecified0d7_KjU();
                                                Modifier modifierAlign = boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged2 = composer3.changed(settingActivity23);
                                                Object objRememberedValue2 = composer3.rememberedValue();
                                                if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue2 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$2$1$1$1
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
                                                            settingActivity23.onBackPressed();
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue2);
                                                }
                                                composer3.endReplaceableGroup();
                                                IconKt.m1641Iconww6aTOc(painterPainterResource, "", ComponentKt.onClick(modifierAlign, (Function0) objRememberedValue2, composer3, 0), jM3530getUnspecified0d7_KjU, composer3, 3128, 0);
                                                TextKt.m2169Text4IGK_g("设置", boxScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 199686, 0, 131024);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                Modifier modifierM598padding3ABfNKs = PaddingKt.m598padding3ABfNKs(BackgroundKt.m241backgroundbw27NRU(PaddingKt.m598padding3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(24)), ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(12))), Dp.m5882constructorimpl(0));
                                                composer3.startReplaceableGroup(-483455358);
                                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash4 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor4 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf4 = LayoutKt.modifierMaterializerOf(modifierM598padding3ABfNKs);
                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer3.startReusableNode();
                                                if (composer3.getInserting()) {
                                                    composer3.createNode(constructor4);
                                                } else {
                                                    composer3.useNode();
                                                }
                                                Composer composerM2989constructorimpl4 = Updater.m2989constructorimpl(composer3);
                                                Updater.m2996setimpl(composerM2989constructorimpl4, measurePolicyColumnMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl4, currentCompositionLocalMap4, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash4 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl4.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl4.rememberedValue(), Integer.valueOf(currentCompositeKeyHash4))) {
                                                    composerM2989constructorimpl4.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash4));
                                                    composerM2989constructorimpl4.apply(Integer.valueOf(currentCompositeKeyHash4), setCompositeKeyHash4);
                                                }
                                                function3ModifierMaterializerOf4.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged3 = composer3.changed(settingActivity23);
                                                Object objRememberedValue3 = composer3.rememberedValue();
                                                if (zChanged3 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue3 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$2$2$1$1
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
                                                            settingActivity23.setDeleteApk(!r0.getDeleteApk());
                                                            Constant.INSTANCE.setDeleteApk(settingActivity23.getDeleteApk());
                                                            SharedPreferences.Editor editorEdit = settingActivity23.getSharedPreferences("user", 0).edit();
                                                            editorEdit.putBoolean("deleteApk", Constant.INSTANCE.getDeleteApk());
                                                            editorEdit.apply();
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue3);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_delete, "安装完删除安装包", (Function0) objRememberedValue3, ComposableLambdaKt.composableLambda(composer3, -1962890139, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$2$2$2
                                                    {
                                                        super(2);
                                                    }

                                                    @Override // kotlin.jvm.functions.Function2
                                                    public /* bridge */ /* synthetic */ Unit invoke(Composer composer4, Integer num) {
                                                        invoke(composer4, num.intValue());
                                                        return Unit.INSTANCE;
                                                    }

                                                    public final void invoke(Composer composer4, int i4) {
                                                        if ((i4 & 11) != 2 || !composer4.getSkipping()) {
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventStart(-1962890139, i4, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:124)");
                                                            }
                                                            ImageKt.Image(PainterResources_androidKt.painterResource(settingActivity23.getDeleteApk() ? R.mipmap.ic_setting_switch_on : R.mipmap.ic_setting_switch_off, composer4, 0), "", (Modifier) null, (Alignment) null, (ContentScale) null, 0.0f, (ColorFilter) null, composer4, 56, 124);
                                                            if (ComposerKt.isTraceInProgress()) {
                                                                ComposerKt.traceEventEnd();
                                                                return;
                                                            }
                                                            return;
                                                        }
                                                        composer4.skipToGroupEnd();
                                                    }
                                                }), composer3, 3126, 0);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged4 = composer3.changed(settingActivity23);
                                                Object objRememberedValue4 = composer3.rememberedValue();
                                                if (zChanged4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue4 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$2$2$3$1
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
                                                            settingActivity23.toast("已清除错误文件，请重新下载");
                                                            OkDownload.getInstance().removeAll(true);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue4);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_repair, "下载修复", (Function0) objRememberedValue4, null, composer3, 54, 8);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged5 = composer3.changed(settingActivity23);
                                                Object objRememberedValue5 = composer3.rememberedValue();
                                                if (zChanged5 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue5 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$2$2$4$1
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
                                                            Util.openProtocol(settingActivity23.getMContext(), "第三方SDK列表", "thirdInfoShareList");
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue5);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_sdk, "第三方SDK列表", (Function0) objRememberedValue5, null, composer3, 54, 8);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                            } else if (settingActivity23.getPrivacy()) {
                                                composer3.startReplaceableGroup(-914650385);
                                                Modifier modifierM652width3ABfNKs2 = SizeKt.m652width3ABfNKs(SizeKt.fillMaxHeight$default(BackgroundKt.m242backgroundbw27NRU$default(Modifier.INSTANCE, Color.INSTANCE.m3531getWhite0d7_KjU(), null, 2, null), 0.0f, 1, null), Dp.m5882constructorimpl(325));
                                                composer3.startReplaceableGroup(-483455358);
                                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash5 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap5 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor5 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf5 = LayoutKt.modifierMaterializerOf(modifierM652width3ABfNKs2);
                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer3.startReusableNode();
                                                if (composer3.getInserting()) {
                                                    composer3.createNode(constructor5);
                                                } else {
                                                    composer3.useNode();
                                                }
                                                Composer composerM2989constructorimpl5 = Updater.m2989constructorimpl(composer3);
                                                Updater.m2996setimpl(composerM2989constructorimpl5, measurePolicyColumnMeasurePolicy3, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl5, currentCompositionLocalMap5, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash5 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl5.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl5.rememberedValue(), Integer.valueOf(currentCompositeKeyHash5))) {
                                                    composerM2989constructorimpl5.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash5));
                                                    composerM2989constructorimpl5.apply(Integer.valueOf(currentCompositeKeyHash5), setCompositeKeyHash5);
                                                }
                                                function3ModifierMaterializerOf5.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                                                Modifier modifierM602paddingqDBjuR0$default2 = PaddingKt.m602paddingqDBjuR0$default(SizeKt.fillMaxWidth$default(SizeKt.m633height3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(58)), 0.0f, 1, null), Dp.m5882constructorimpl(16), 0.0f, 0.0f, 0.0f, 14, null);
                                                composer3.startReplaceableGroup(733328855);
                                                ComposerKt.sourceInformation(composer3, "CC(Box)P(2,1,3)71@3309L67,72@3381L130:Box.kt#2w3rfo");
                                                MeasurePolicy measurePolicyRememberBoxMeasurePolicy2 = BoxKt.rememberBoxMeasurePolicy(Alignment.INSTANCE.getTopStart(), false, composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash6 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap6 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor6 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf6 = LayoutKt.modifierMaterializerOf(modifierM602paddingqDBjuR0$default2);
                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer3.startReusableNode();
                                                if (composer3.getInserting()) {
                                                    composer3.createNode(constructor6);
                                                } else {
                                                    composer3.useNode();
                                                }
                                                Composer composerM2989constructorimpl6 = Updater.m2989constructorimpl(composer3);
                                                Updater.m2996setimpl(composerM2989constructorimpl6, measurePolicyRememberBoxMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl6, currentCompositionLocalMap6, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash6 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl6.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl6.rememberedValue(), Integer.valueOf(currentCompositeKeyHash6))) {
                                                    composerM2989constructorimpl6.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash6));
                                                    composerM2989constructorimpl6.apply(Integer.valueOf(currentCompositeKeyHash6), setCompositeKeyHash6);
                                                }
                                                function3ModifierMaterializerOf6.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, -1253629263, "C73@3426L9:Box.kt#2w3rfo");
                                                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                                                Painter painterPainterResource2 = PainterResources_androidKt.painterResource(R.mipmap.ic_back24, composer3, 6);
                                                long jM3530getUnspecified0d7_KjU2 = Color.INSTANCE.m3530getUnspecified0d7_KjU();
                                                Modifier modifierAlign2 = boxScopeInstance2.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterStart());
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged6 = composer3.changed(settingActivity23);
                                                Object objRememberedValue6 = composer3.rememberedValue();
                                                if (zChanged6 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue6 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$1$1$1
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
                                                            settingActivity23.setPrivacy(false);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue6);
                                                }
                                                composer3.endReplaceableGroup();
                                                IconKt.m1641Iconww6aTOc(painterPainterResource2, "", ComponentKt.onClick(modifierAlign2, (Function0) objRememberedValue6, composer3, 0), jM3530getUnspecified0d7_KjU2, composer3, 3128, 0);
                                                TextKt.m2169Text4IGK_g("隐私与权限", boxScopeInstance2.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenter()), com.cy.yyjia.zhe28.ui.theme.ColorKt.getText1(), TextUnitKt.getSp(16), (FontStyle) null, FontWeight.INSTANCE.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer3, 199686, 0, 131024);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                Modifier modifierM598padding3ABfNKs2 = PaddingKt.m598padding3ABfNKs(BackgroundKt.m241backgroundbw27NRU(PaddingKt.m598padding3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(24)), ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(12))), Dp.m5882constructorimpl(0));
                                                composer3.startReplaceableGroup(-483455358);
                                                ComposerKt.sourceInformation(composer3, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer3, 0);
                                                composer3.startReplaceableGroup(-1323940314);
                                                ComposerKt.sourceInformation(composer3, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                int currentCompositeKeyHash7 = ComposablesKt.getCurrentCompositeKeyHash(composer3, 0);
                                                CompositionLocalMap currentCompositionLocalMap7 = composer3.getCurrentCompositionLocalMap();
                                                Function0<ComposeUiNode> constructor7 = ComposeUiNode.INSTANCE.getConstructor();
                                                Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf7 = LayoutKt.modifierMaterializerOf(modifierM598padding3ABfNKs2);
                                                if (!(composer3.getApplier() instanceof Applier)) {
                                                    ComposablesKt.invalidApplier();
                                                }
                                                composer3.startReusableNode();
                                                if (composer3.getInserting()) {
                                                    composer3.createNode(constructor7);
                                                } else {
                                                    composer3.useNode();
                                                }
                                                Composer composerM2989constructorimpl7 = Updater.m2989constructorimpl(composer3);
                                                Updater.m2996setimpl(composerM2989constructorimpl7, measurePolicyColumnMeasurePolicy4, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                Updater.m2996setimpl(composerM2989constructorimpl7, currentCompositionLocalMap7, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash7 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                if (composerM2989constructorimpl7.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl7.rememberedValue(), Integer.valueOf(currentCompositeKeyHash7))) {
                                                    composerM2989constructorimpl7.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash7));
                                                    composerM2989constructorimpl7.apply(Integer.valueOf(currentCompositeKeyHash7), setCompositeKeyHash7);
                                                }
                                                function3ModifierMaterializerOf7.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer3)), composer3, 0);
                                                composer3.startReplaceableGroup(2058660585);
                                                ComposerKt.sourceInformationMarkerStart(composer3, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged7 = composer3.changed(settingActivity23);
                                                Object objRememberedValue7 = composer3.rememberedValue();
                                                if (zChanged7 || objRememberedValue7 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue7 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$2$1$1
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
                                                            Util.openWeb(settingActivity23.getMContext(), "用户协议", "https://www.28zhe.com/help/agreement.htm", false);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue7);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_agreement, "用户协议", (Function0) objRememberedValue7, null, composer3, 54, 8);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged8 = composer3.changed(settingActivity23);
                                                Object objRememberedValue8 = composer3.rememberedValue();
                                                if (zChanged8 || objRememberedValue8 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue8 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$2$2$1
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
                                                            Util.openWeb(settingActivity23.getMContext(), "隐私政策", "https://www.28zhe.com/help/privacyPolicy.htm", false);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue8);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_policy, "隐私政策", (Function0) objRememberedValue8, null, composer3, 54, 8);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged9 = composer3.changed(settingActivity23);
                                                Object objRememberedValue9 = composer3.rememberedValue();
                                                if (zChanged9 || objRememberedValue9 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue9 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$2$3$1
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
                                                            Util.openWeb(settingActivity23.getMContext(), "第三方信息共享清单", "https://28zhe.com/help/thirdinfo.htm", false);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue9);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_third, "第三方信息共享清单", (Function0) objRememberedValue9, null, composer3, 54, 8);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged10 = composer3.changed(settingActivity23);
                                                Object objRememberedValue10 = composer3.rememberedValue();
                                                if (zChanged10 || objRememberedValue10 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue10 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$2$4$1
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
                                                            Util.openWeb(settingActivity23.getMContext(), "软件使用许可协议", "https://www.28zhe.com/help/softLicenseAgreement.html", false);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue10);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_soft, "软件使用许可协议", (Function0) objRememberedValue10, null, composer3, 54, 8);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged11 = composer3.changed(settingActivity23);
                                                Object objRememberedValue11 = composer3.rememberedValue();
                                                if (zChanged11 || objRememberedValue11 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue11 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$3$2$5$1
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
                                                            settingActivity23.startActivity(PermissionListActivity.class);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue11);
                                                }
                                                composer3.endReplaceableGroup();
                                                SettingActivity2Kt.Function(R.mipmap.ic_setting_permission, "权限管理", (Function0) objRememberedValue11, null, composer3, 54, 8);
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                ComposerKt.sourceInformationMarkerEnd(composer3);
                                                composer3.endReplaceableGroup();
                                                composer3.endNode();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                                composer3.endReplaceableGroup();
                                            } else {
                                                composer3.startReplaceableGroup(-914646834);
                                                Modifier modifierM600paddingVpY3zN4$default = PaddingKt.m600paddingVpY3zN4$default(SizeKt.m652width3ABfNKs(SizeKt.fillMaxHeight$default(BackgroundKt.m242backgroundbw27NRU$default(Modifier.INSTANCE, Color.INSTANCE.m3531getWhite0d7_KjU(), null, 2, null), 0.0f, 1, null), Dp.m5882constructorimpl(325)), Dp.m5882constructorimpl(24), 0.0f, 2, null);
                                                composer3.startReplaceableGroup(1157296644);
                                                ComposerKt.sourceInformation(composer3, "CC(remember)P(1):Composables.kt#9igjgp");
                                                boolean zChanged12 = composer3.changed(settingActivity23);
                                                Object objRememberedValue12 = composer3.rememberedValue();
                                                if (zChanged12 || objRememberedValue12 == Composer.INSTANCE.getEmpty()) {
                                                    objRememberedValue12 = (Function1) new Function1<LazyListScope, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1
                                                        {
                                                            super(1);
                                                        }

                                                        @Override // kotlin.jvm.functions.Function1
                                                        public /* bridge */ /* synthetic */ Unit invoke(LazyListScope lazyListScope) {
                                                            invoke2(lazyListScope);
                                                            return Unit.INSTANCE;
                                                        }

                                                        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                        public final void invoke2(LazyListScope LazyColumn) {
                                                            Intrinsics.checkNotNullParameter(LazyColumn, "$this$LazyColumn");
                                                            final SettingActivity2 settingActivity24 = settingActivity23;
                                                            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(1013737468, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1.1
                                                                {
                                                                    super(3);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function3
                                                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer4, Integer num) {
                                                                    invoke(lazyItemScope, composer4, num.intValue());
                                                                    return Unit.INSTANCE;
                                                                }

                                                                public final void invoke(LazyItemScope item, Composer composer4, int i4) {
                                                                    Intrinsics.checkNotNullParameter(item, "$this$item");
                                                                    if ((i4 & 81) != 16 || !composer4.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(1013737468, i4, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:210)");
                                                                        }
                                                                        BoxKt.Box(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(55)), composer4, 6);
                                                                        SettingActivity2Kt.Head(settingActivity24.getUserData(), composer4, UserBean.$stable);
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    composer4.skipToGroupEnd();
                                                                }
                                                            }), 3, null);
                                                            final SettingActivity2 settingActivity25 = settingActivity23;
                                                            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(-1504925595, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1.2
                                                                {
                                                                    super(3);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function3
                                                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer4, Integer num) {
                                                                    invoke(lazyItemScope, composer4, num.intValue());
                                                                    return Unit.INSTANCE;
                                                                }

                                                                public final void invoke(LazyItemScope item, Composer composer4, int i4) {
                                                                    Intrinsics.checkNotNullParameter(item, "$this$item");
                                                                    if ((i4 & 81) != 16 || !composer4.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(-1504925595, i4, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:214)");
                                                                        }
                                                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(26)), composer4, 6);
                                                                        Modifier modifierM598padding3ABfNKs3 = PaddingKt.m598padding3ABfNKs(BackgroundKt.m241backgroundbw27NRU(Modifier.INSTANCE, ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(12))), Dp.m5882constructorimpl(0));
                                                                        final SettingActivity2 settingActivity26 = settingActivity25;
                                                                        composer4.startReplaceableGroup(-483455358);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                                        MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                                                        composer4.startReplaceableGroup(-1323940314);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                        int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                                        CompositionLocalMap currentCompositionLocalMap8 = composer4.getCurrentCompositionLocalMap();
                                                                        Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierM598padding3ABfNKs3);
                                                                        if (!(composer4.getApplier() instanceof Applier)) {
                                                                            ComposablesKt.invalidApplier();
                                                                        }
                                                                        composer4.startReusableNode();
                                                                        if (composer4.getInserting()) {
                                                                            composer4.createNode(constructor8);
                                                                        } else {
                                                                            composer4.useNode();
                                                                        }
                                                                        Composer composerM2989constructorimpl8 = Updater.m2989constructorimpl(composer4);
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                        if (composerM2989constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                                                                            composerM2989constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                                                                            composerM2989constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                                                                        }
                                                                        function3ModifierMaterializerOf8.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer4)), composer4, 0);
                                                                        composer4.startReplaceableGroup(2058660585);
                                                                        ComposerKt.sourceInformationMarkerStart(composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                        ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged13 = composer4.changed(settingActivity26);
                                                                        Object objRememberedValue13 = composer4.rememberedValue();
                                                                        if (zChanged13 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue13 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$2$1$1$1
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
                                                                                    Util.skipWithLogin(settingActivity26.getMContext(), InfoActivity.class);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue13);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_info, "个人信息", (Function0) objRememberedValue13, ComposableSingletons$SettingActivity2Kt.INSTANCE.m6468getLambda1$app_zhe28Release(), composer4, 3126, 0);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged14 = composer4.changed(settingActivity26);
                                                                        Object objRememberedValue14 = composer4.rememberedValue();
                                                                        if (zChanged14 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue14 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$2$1$2$1
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
                                                                                    Util.skipWithLogin(settingActivity26.getMContext(), TrumpetActivity.class);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue14);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_trumpet, "小号管理", (Function0) objRememberedValue14, null, composer4, 54, 8);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged15 = composer4.changed(settingActivity26);
                                                                        Object objRememberedValue15 = composer4.rememberedValue();
                                                                        if (zChanged15 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue15 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$2$1$3$1
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
                                                                                    Util.skipWithLogin(settingActivity26.getMContext(), SafeActivity.class);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue15);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_safe, "安全与绑定", (Function0) objRememberedValue15, null, composer4, 54, 8);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged16 = composer4.changed(settingActivity26);
                                                                        Object objRememberedValue16 = composer4.rememberedValue();
                                                                        if (zChanged16 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue16 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$2$1$4$1
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
                                                                                    settingActivity26.startActivity(new Intent(settingActivity26.getMContext(), (Class<?>) PhoneActivity.class).putExtra("type", !TextUtils.isEmpty(settingActivity26.getUserData().getTelphone()) ? 1 : 0));
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue16);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_safe, "手机绑定", (Function0) objRememberedValue16, null, composer4, 54, 8);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged17 = composer4.changed(settingActivity26);
                                                                        Object objRememberedValue17 = composer4.rememberedValue();
                                                                        if (zChanged17 || objRememberedValue17 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue17 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$2$1$5$1
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
                                                                                    Util.skipWithLogin(settingActivity26.getMContext(), ChangePasswordActivity.class);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue17);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_safe, "修改密码", (Function0) objRememberedValue17, null, composer4, 54, 8);
                                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endNode();
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endReplaceableGroup();
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    composer4.skipToGroupEnd();
                                                                }
                                                            }), 3, null);
                                                            final SettingActivity2 settingActivity26 = settingActivity23;
                                                            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(303348164, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1.3
                                                                {
                                                                    super(3);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function3
                                                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer4, Integer num) {
                                                                    invoke(lazyItemScope, composer4, num.intValue());
                                                                    return Unit.INSTANCE;
                                                                }

                                                                public final void invoke(LazyItemScope item, Composer composer4, int i4) {
                                                                    Intrinsics.checkNotNullParameter(item, "$this$item");
                                                                    if ((i4 & 81) != 16 || !composer4.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(303348164, i4, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:278)");
                                                                        }
                                                                        float f = 12;
                                                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f)), composer4, 6);
                                                                        Modifier modifierM598padding3ABfNKs3 = PaddingKt.m598padding3ABfNKs(BackgroundKt.m241backgroundbw27NRU(Modifier.INSTANCE, ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(f))), Dp.m5882constructorimpl(0));
                                                                        final SettingActivity2 settingActivity27 = settingActivity26;
                                                                        composer4.startReplaceableGroup(-483455358);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                                        MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                                                        composer4.startReplaceableGroup(-1323940314);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                        int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                                        CompositionLocalMap currentCompositionLocalMap8 = composer4.getCurrentCompositionLocalMap();
                                                                        Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierM598padding3ABfNKs3);
                                                                        if (!(composer4.getApplier() instanceof Applier)) {
                                                                            ComposablesKt.invalidApplier();
                                                                        }
                                                                        composer4.startReusableNode();
                                                                        if (composer4.getInserting()) {
                                                                            composer4.createNode(constructor8);
                                                                        } else {
                                                                            composer4.useNode();
                                                                        }
                                                                        Composer composerM2989constructorimpl8 = Updater.m2989constructorimpl(composer4);
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                        if (composerM2989constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                                                                            composerM2989constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                                                                            composerM2989constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                                                                        }
                                                                        function3ModifierMaterializerOf8.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer4)), composer4, 0);
                                                                        composer4.startReplaceableGroup(2058660585);
                                                                        ComposerKt.sourceInformationMarkerStart(composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                        ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged13 = composer4.changed(settingActivity27);
                                                                        Object objRememberedValue13 = composer4.rememberedValue();
                                                                        if (zChanged13 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue13 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$3$1$1$1
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
                                                                                    settingActivity27.update();
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue13);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_version, "检查更新", (Function0) objRememberedValue13, ComposableSingletons$SettingActivity2Kt.INSTANCE.m6469getLambda2$app_zhe28Release(), composer4, 3126, 0);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged14 = composer4.changed(settingActivity27);
                                                                        Object objRememberedValue14 = composer4.rememberedValue();
                                                                        if (zChanged14 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue14 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$3$1$2$1
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
                                                                                    Util.skipWithLogin(settingActivity27.getMContext(), FeedbackActivity.class);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue14);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_feedback, "意见反馈", (Function0) objRememberedValue14, null, composer4, 54, 8);
                                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endNode();
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endReplaceableGroup();
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    composer4.skipToGroupEnd();
                                                                }
                                                            }), 3, null);
                                                            final SettingActivity2 settingActivity27 = settingActivity23;
                                                            LazyListScope.CC.item$default(LazyColumn, null, null, ComposableLambdaKt.composableLambdaInstance(2111621923, true, new Function3<LazyItemScope, Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1.4
                                                                {
                                                                    super(3);
                                                                }

                                                                @Override // kotlin.jvm.functions.Function3
                                                                public /* bridge */ /* synthetic */ Unit invoke(LazyItemScope lazyItemScope, Composer composer4, Integer num) {
                                                                    invoke(lazyItemScope, composer4, num.intValue());
                                                                    return Unit.INSTANCE;
                                                                }

                                                                public final void invoke(LazyItemScope item, Composer composer4, int i4) {
                                                                    Intrinsics.checkNotNullParameter(item, "$this$item");
                                                                    if ((i4 & 81) != 16 || !composer4.getSkipping()) {
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventStart(2111621923, i4, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:313)");
                                                                        }
                                                                        float f = 12;
                                                                        SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(f)), composer4, 6);
                                                                        Modifier modifierM598padding3ABfNKs3 = PaddingKt.m598padding3ABfNKs(BackgroundKt.m241backgroundbw27NRU(Modifier.INSTANCE, ColorKt.Color(4294506744L), RoundedCornerShapeKt.m876RoundedCornerShape0680j_4(Dp.m5882constructorimpl(f))), Dp.m5882constructorimpl(0));
                                                                        final SettingActivity2 settingActivity28 = settingActivity27;
                                                                        composer4.startReplaceableGroup(-483455358);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Column)P(2,3,1)77@3865L61,78@3931L133:Column.kt#2w3rfo");
                                                                        MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(Arrangement.INSTANCE.getTop(), Alignment.INSTANCE.getStart(), composer4, 0);
                                                                        composer4.startReplaceableGroup(-1323940314);
                                                                        ComposerKt.sourceInformation(composer4, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                        int currentCompositeKeyHash8 = ComposablesKt.getCurrentCompositeKeyHash(composer4, 0);
                                                                        CompositionLocalMap currentCompositionLocalMap8 = composer4.getCurrentCompositionLocalMap();
                                                                        Function0<ComposeUiNode> constructor8 = ComposeUiNode.INSTANCE.getConstructor();
                                                                        Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf8 = LayoutKt.modifierMaterializerOf(modifierM598padding3ABfNKs3);
                                                                        if (!(composer4.getApplier() instanceof Applier)) {
                                                                            ComposablesKt.invalidApplier();
                                                                        }
                                                                        composer4.startReusableNode();
                                                                        if (composer4.getInserting()) {
                                                                            composer4.createNode(constructor8);
                                                                        } else {
                                                                            composer4.useNode();
                                                                        }
                                                                        Composer composerM2989constructorimpl8 = Updater.m2989constructorimpl(composer4);
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, measurePolicyColumnMeasurePolicy5, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                        Updater.m2996setimpl(composerM2989constructorimpl8, currentCompositionLocalMap8, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash8 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                        if (composerM2989constructorimpl8.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl8.rememberedValue(), Integer.valueOf(currentCompositeKeyHash8))) {
                                                                            composerM2989constructorimpl8.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash8));
                                                                            composerM2989constructorimpl8.apply(Integer.valueOf(currentCompositeKeyHash8), setCompositeKeyHash8);
                                                                        }
                                                                        function3ModifierMaterializerOf8.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer4)), composer4, 0);
                                                                        composer4.startReplaceableGroup(2058660585);
                                                                        ComposerKt.sourceInformationMarkerStart(composer4, 276693656, "C79@3979L9:Column.kt#2w3rfo");
                                                                        ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged13 = composer4.changed(settingActivity28);
                                                                        Object objRememberedValue13 = composer4.rememberedValue();
                                                                        if (zChanged13 || objRememberedValue13 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue13 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$4$1$1$1
                                                                                {
                                                                                    super(0);
                                                                                }

                                                                                @Override // kotlin.jvm.functions.Function0
                                                                                public /* bridge */ /* synthetic */ Unit invoke() throws Exception {
                                                                                    invoke2();
                                                                                    return Unit.INSTANCE;
                                                                                }

                                                                                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                                                                                public final void invoke2() throws Exception {
                                                                                    Util.clearAllCache(settingActivity28.getMContext());
                                                                                    OkDownload.getInstance().removeAll(true);
                                                                                    settingActivity28.m6551getCache();
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue13);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_cache, "清理缓存", (Function0) objRememberedValue13, ComposableLambdaKt.composableLambda(composer4, -1897645115, true, new Function2<Composer, Integer, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$4$1$2
                                                                            {
                                                                                super(2);
                                                                            }

                                                                            @Override // kotlin.jvm.functions.Function2
                                                                            public /* bridge */ /* synthetic */ Unit invoke(Composer composer5, Integer num) {
                                                                                invoke(composer5, num.intValue());
                                                                                return Unit.INSTANCE;
                                                                            }

                                                                            public final void invoke(Composer composer5, int i5) {
                                                                                if ((i5 & 11) != 2 || !composer5.getSkipping()) {
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventStart(-1897645115, i5, -1, "com.cy.yyjia.zhe28.ui.activity.SettingActivity2.initView.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous>.<anonymous> (SettingActivity2.kt:326)");
                                                                                    }
                                                                                    SettingActivity2 settingActivity29 = settingActivity28;
                                                                                    composer5.startReplaceableGroup(693286680);
                                                                                    ComposerKt.sourceInformation(composer5, "CC(Row)P(2,1,3)90@4553L58,91@4616L130:Row.kt#2w3rfo");
                                                                                    Modifier.Companion companion2 = Modifier.INSTANCE;
                                                                                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer5, 0);
                                                                                    composer5.startReplaceableGroup(-1323940314);
                                                                                    ComposerKt.sourceInformation(composer5, "CC(Layout)P(!1,2)78@3182L23,80@3272L420:Layout.kt#80mrfh");
                                                                                    int currentCompositeKeyHash9 = ComposablesKt.getCurrentCompositeKeyHash(composer5, 0);
                                                                                    CompositionLocalMap currentCompositionLocalMap9 = composer5.getCurrentCompositionLocalMap();
                                                                                    Function0<ComposeUiNode> constructor9 = ComposeUiNode.INSTANCE.getConstructor();
                                                                                    Function3<SkippableUpdater<ComposeUiNode>, Composer, Integer, Unit> function3ModifierMaterializerOf9 = LayoutKt.modifierMaterializerOf(companion2);
                                                                                    if (!(composer5.getApplier() instanceof Applier)) {
                                                                                        ComposablesKt.invalidApplier();
                                                                                    }
                                                                                    composer5.startReusableNode();
                                                                                    if (composer5.getInserting()) {
                                                                                        composer5.createNode(constructor9);
                                                                                    } else {
                                                                                        composer5.useNode();
                                                                                    }
                                                                                    Composer composerM2989constructorimpl9 = Updater.m2989constructorimpl(composer5);
                                                                                    Updater.m2996setimpl(composerM2989constructorimpl9, measurePolicyRowMeasurePolicy2, ComposeUiNode.INSTANCE.getSetMeasurePolicy());
                                                                                    Updater.m2996setimpl(composerM2989constructorimpl9, currentCompositionLocalMap9, ComposeUiNode.INSTANCE.getSetResolvedCompositionLocals());
                                                                                    Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash9 = ComposeUiNode.INSTANCE.getSetCompositeKeyHash();
                                                                                    if (composerM2989constructorimpl9.getInserting() || !Intrinsics.areEqual(composerM2989constructorimpl9.rememberedValue(), Integer.valueOf(currentCompositeKeyHash9))) {
                                                                                        composerM2989constructorimpl9.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash9));
                                                                                        composerM2989constructorimpl9.apply(Integer.valueOf(currentCompositeKeyHash9), setCompositeKeyHash9);
                                                                                    }
                                                                                    function3ModifierMaterializerOf9.invoke(SkippableUpdater.m2980boximpl(SkippableUpdater.m2981constructorimpl(composer5)), composer5, 0);
                                                                                    composer5.startReplaceableGroup(2058660585);
                                                                                    ComposerKt.sourceInformationMarkerStart(composer5, -326681643, "C92@4661L9:Row.kt#2w3rfo");
                                                                                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                                                                                    TextKt.m2169Text4IGK_g(settingActivity29.getCache(), (Modifier) null, com.cy.yyjia.zhe28.ui.theme.ColorKt.getText2(), TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1<? super TextLayoutResult, Unit>) null, (TextStyle) null, composer5, 3072, 0, 131058);
                                                                                    SpacerKt.Spacer(SizeKt.m647size3ABfNKs(Modifier.INSTANCE, Dp.m5882constructorimpl(4)), composer5, 6);
                                                                                    IconKt.m1641Iconww6aTOc(PainterResources_androidKt.painterResource(R.mipmap.ic_go16, composer5, 6), "", rowScopeInstance.align(Modifier.INSTANCE, Alignment.INSTANCE.getCenterVertically()), Color.INSTANCE.m3530getUnspecified0d7_KjU(), composer5, 3128, 0);
                                                                                    ComposerKt.sourceInformationMarkerEnd(composer5);
                                                                                    composer5.endReplaceableGroup();
                                                                                    composer5.endNode();
                                                                                    composer5.endReplaceableGroup();
                                                                                    composer5.endReplaceableGroup();
                                                                                    if (ComposerKt.isTraceInProgress()) {
                                                                                        ComposerKt.traceEventEnd();
                                                                                        return;
                                                                                    }
                                                                                    return;
                                                                                }
                                                                                composer5.skipToGroupEnd();
                                                                            }
                                                                        }), composer4, 3126, 0);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged14 = composer4.changed(settingActivity28);
                                                                        Object objRememberedValue14 = composer4.rememberedValue();
                                                                        if (zChanged14 || objRememberedValue14 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue14 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$4$1$3$1
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
                                                                                    settingActivity28.setSetting(true);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue14);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_setting, "设置", (Function0) objRememberedValue14, null, composer4, 54, 8);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged15 = composer4.changed(settingActivity28);
                                                                        Object objRememberedValue15 = composer4.rememberedValue();
                                                                        if (zChanged15 || objRememberedValue15 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue15 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$4$1$4$1
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
                                                                                    settingActivity28.setPrivacy(true);
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue15);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_safe, "隐私与权限", (Function0) objRememberedValue15, null, composer4, 54, 8);
                                                                        composer4.startReplaceableGroup(1157296644);
                                                                        ComposerKt.sourceInformation(composer4, "CC(remember)P(1):Composables.kt#9igjgp");
                                                                        boolean zChanged16 = composer4.changed(settingActivity28);
                                                                        Object objRememberedValue16 = composer4.rememberedValue();
                                                                        if (zChanged16 || objRememberedValue16 == Composer.INSTANCE.getEmpty()) {
                                                                            objRememberedValue16 = (Function0) new Function0<Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$initView$1$1$1$1$4$1$4$1$5$1
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
                                                                                    settingActivity28.logout();
                                                                                }
                                                                            };
                                                                            composer4.updateRememberedValue(objRememberedValue16);
                                                                        }
                                                                        composer4.endReplaceableGroup();
                                                                        SettingActivity2Kt.Function(R.mipmap.ic_setting_logout, "退出登录", (Function0) objRememberedValue16, null, composer4, 54, 8);
                                                                        ComposerKt.sourceInformationMarkerEnd(composer4);
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endNode();
                                                                        composer4.endReplaceableGroup();
                                                                        composer4.endReplaceableGroup();
                                                                        if (ComposerKt.isTraceInProgress()) {
                                                                            ComposerKt.traceEventEnd();
                                                                            return;
                                                                        }
                                                                        return;
                                                                    }
                                                                    composer4.skipToGroupEnd();
                                                                }
                                                            }), 3, null);
                                                        }
                                                    };
                                                    composer3.updateRememberedValue(objRememberedValue12);
                                                }
                                                composer3.endReplaceableGroup();
                                                LazyDslKt.LazyColumn(modifierM600paddingVpY3zN4$default, null, null, false, null, null, null, false, (Function1) objRememberedValue12, composer3, 6, KeyBoardKey.KeyboardKeyOemClear);
                                                composer3.endReplaceableGroup();
                                            }
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
                                }), composer2, 805306374, 510);
                                if (ComposerKt.isTraceInProgress()) {
                                    ComposerKt.traceEventEnd();
                                    return;
                                }
                                return;
                            }
                            composer2.skipToGroupEnd();
                        }
                    }), composer, 6);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                        return;
                    }
                    return;
                }
                composer.skipToGroupEnd();
            }
        }), 1, null);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (Constant.INSTANCE.getLogged()) {
            Repository.INSTANCE.getUserData(new Function1<UserBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.onResume.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(UserBean userBean) {
                    invoke2(userBean);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(UserBean it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    SettingActivity2.this.setUserData(it);
                }
            }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.onResume.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                    invoke2(exc);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Exception it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    SettingActivity2.this.netFail(it);
                }
            });
        }
    }

    public final void update() {
        Repository.INSTANCE.update(new Function1<UpdateBean, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.update.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(UpdateBean updateBean) {
                invoke2(updateBean);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(UpdateBean it) {
                Intrinsics.checkNotNullParameter(it, "it");
                if (Intrinsics.areEqual(it.getIsUpdate(), BooleanUtils.YES) && it.getVersionCode() > 429) {
                    new UpdateDialog(SettingActivity2.this.getMContext(), it).show();
                } else {
                    SettingActivity2.this.toast("已经是最新版本，不需要更新");
                }
            }
        }, new Function1<Exception, Unit>() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2.update.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Exception exc) {
                invoke2(exc);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Exception it) {
                Intrinsics.checkNotNullParameter(it, "it");
                SettingActivity2.this.netFail(it);
            }
        });
    }

    /* JADX INFO: renamed from: getCache, reason: collision with other method in class */
    public final void m6551getCache() throws Exception {
        String totalCacheSize = Util.getTotalCacheSize(this);
        Intrinsics.checkNotNullExpressionValue(totalCacheSize, "getTotalCacheSize(...)");
        setCache(totalCacheSize);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (getSetting()) {
            setSetting(false);
        } else {
            super.onBackPressed();
        }
    }

    public final void logout() {
        new BaseDialog.Builder(this).setContentView(R.layout.dialog_logout).setOnClickListener(R.id.btn, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$$ExternalSyntheticLambda0
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                SettingActivity2.logout$lambda$0(this.f$0, baseDialog, view);
            }
        }).setOnClickListener(R.id.f438tv, new BaseDialog.OnClickListener() { // from class: com.cy.yyjia.zhe28.ui.activity.SettingActivity2$$ExternalSyntheticLambda1
            @Override // com.cy.yyjia.zhe28.base.BaseDialog.OnClickListener
            public final void onClick(BaseDialog baseDialog, View view) {
                baseDialog.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void logout$lambda$0(SettingActivity2 this$0, BaseDialog baseDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        baseDialog.dismiss();
        EventBus.getDefault().post(new LoginChangeBean());
        Constant.INSTANCE.logout(this$0.getMContext());
        this$0.finish();
    }
}
