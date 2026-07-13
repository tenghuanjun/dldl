package androidx.compose.foundation.selection;

import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.Indication;
import androidx.compose.foundation.IndicationKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.platform.InspectableValueKt;
import androidx.compose.ui.platform.InspectorInfo;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.state.ToggleableState;
import androidx.compose.ui.state.ToggleableStateKt;
import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: compiled from: Toggleable.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aV\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fø\u0001\u0000¢\u0006\u0002\b\u000e\u001aD\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\r0\fø\u0001\u0000¢\u0006\u0002\b\u000f\u001aP\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014ø\u0001\u0000¢\u0006\u0002\b\u0015\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00122\b\b\u0002\u0010\b\u001a\u00020\u00032\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\r0\u0014ø\u0001\u0000¢\u0006\u0002\b\u0016\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u0017"}, d2 = {"toggleable", "Landroidx/compose/ui/Modifier;", DBHelper.COL_VALUE, "", "interactionSource", "Landroidx/compose/foundation/interaction/MutableInteractionSource;", "indication", "Landroidx/compose/foundation/Indication;", "enabled", "role", "Landroidx/compose/ui/semantics/Role;", "onValueChange", "Lkotlin/Function1;", "", "toggleable-O2vRcR0", "toggleable-XHw0xAI", "triStateToggleable", "state", "Landroidx/compose/ui/state/ToggleableState;", "onClick", "Lkotlin/Function0;", "triStateToggleable-O2vRcR0", "triStateToggleable-XHw0xAI", "foundation_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ToggleableKt {
    /* JADX INFO: renamed from: toggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m854toggleableXHw0xAI$default(Modifier modifier, boolean z, boolean z2, Role role, Function1 function1, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = true;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m853toggleableXHw0xAI(modifier, z, z2, role, function1);
    }

    /* JADX INFO: renamed from: toggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m852toggleableO2vRcR0$default(Modifier modifier, boolean z, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z2, Role role, Function1 function1, int i, Object obj) {
        boolean z3 = (i & 8) != 0 ? true : z2;
        if ((i & 16) != 0) {
            role = null;
        }
        return m851toggleableO2vRcR0(modifier, z, mutableInteractionSource, indication, z3, role, function1);
    }

    /* JADX INFO: renamed from: triStateToggleable-XHw0xAI$default, reason: not valid java name */
    public static /* synthetic */ Modifier m858triStateToggleableXHw0xAI$default(Modifier modifier, ToggleableState toggleableState, boolean z, Role role, Function0 function0, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            role = null;
        }
        return m857triStateToggleableXHw0xAI(modifier, toggleableState, z, role, function0);
    }

    /* JADX INFO: renamed from: triStateToggleable-O2vRcR0$default, reason: not valid java name */
    public static /* synthetic */ Modifier m856triStateToggleableO2vRcR0$default(Modifier modifier, ToggleableState toggleableState, MutableInteractionSource mutableInteractionSource, Indication indication, boolean z, Role role, Function0 function0, int i, Object obj) {
        boolean z2 = (i & 8) != 0 ? true : z;
        if ((i & 16) != 0) {
            role = null;
        }
        return m855triStateToggleableO2vRcR0(modifier, toggleableState, mutableInteractionSource, indication, z2, role, function0);
    }

    /* JADX INFO: renamed from: toggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m853toggleableXHw0xAI(Modifier modifier, final boolean z, final boolean z2, final Role role, final Function1<? super Boolean, Unit> function1) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable-XHw0xAI$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("toggleable");
                inspectorInfo.getProperties().set(DBHelper.COL_VALUE, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onValueChange", function1);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceableGroup(290332169);
                ComposerKt.sourceInformation(composer, "C68@2718L39,69@2796L7:Toggleable.kt#gro6r2");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(290332169, i, -1, "androidx.compose.foundation.selection.toggleable.<anonymous> (Toggleable.kt:66)");
                }
                Modifier.Companion companion = Modifier.INSTANCE;
                boolean z3 = z;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM851toggleableO2vRcR0 = ToggleableKt.m851toggleableO2vRcR0(companion, z3, mutableInteractionSource, (Indication) objConsume, z2, role, function1);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM851toggleableO2vRcR0;
            }
        });
    }

    /* JADX INFO: renamed from: toggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m851toggleableO2vRcR0(Modifier modifier, final boolean z, final MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z2, final Role role, final Function1<? super Boolean, Unit> function1) {
        Function1<InspectorInfo, Unit> noInspectorInfo = InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable-O2vRcR0$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("toggleable");
                inspectorInfo.getProperties().set(DBHelper.COL_VALUE, Boolean.valueOf(z));
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("indication", indication);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z2));
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onValueChange", function1);
            }
        } : InspectableValueKt.getNoInspectorInfo();
        Modifier.Companion companion = Modifier.INSTANCE;
        return InspectableValueKt.inspectableWrapper(modifier, noInspectorInfo, m855triStateToggleableO2vRcR0(Modifier.INSTANCE, ToggleableStateKt.ToggleableState(z), mutableInteractionSource, indication, z2, role, new Function0<Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$toggleable$4$1
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
                function1.invoke(Boolean.valueOf(!z));
            }
        }));
    }

    /* JADX INFO: renamed from: triStateToggleable-XHw0xAI, reason: not valid java name */
    public static final Modifier m857triStateToggleableXHw0xAI(Modifier modifier, final ToggleableState toggleableState, final boolean z, final Role role, final Function0<Unit> function0) {
        return ComposedModifierKt.composed(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable-XHw0xAI$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("triStateToggleable");
                inspectorInfo.getProperties().set("state", toggleableState);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("onClick", function0);
            }
        } : InspectableValueKt.getNoInspectorInfo(), new Function3<Modifier, Composer, Integer, Modifier>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(3);
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier2, Composer composer, Integer num) {
                return invoke(modifier2, composer, num.intValue());
            }

            public final Modifier invoke(Modifier modifier2, Composer composer, int i) {
                composer.startReplaceableGroup(-1808118329);
                ComposerKt.sourceInformation(composer, "C165@6476L39,166@6554L7:Toggleable.kt#gro6r2");
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1808118329, i, -1, "androidx.compose.foundation.selection.triStateToggleable.<anonymous> (Toggleable.kt:163)");
                }
                Modifier.Companion companion = Modifier.INSTANCE;
                ToggleableState toggleableState2 = toggleableState;
                composer.startReplaceableGroup(-492369756);
                ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
                Object objRememberedValue = composer.rememberedValue();
                if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composer.updateRememberedValue(objRememberedValue);
                }
                composer.endReplaceableGroup();
                MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                ProvidableCompositionLocal<Indication> localIndication = IndicationKt.getLocalIndication();
                ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
                Object objConsume = composer.consume(localIndication);
                ComposerKt.sourceInformationMarkerEnd(composer);
                Modifier modifierM855triStateToggleableO2vRcR0 = ToggleableKt.m855triStateToggleableO2vRcR0(companion, toggleableState2, mutableInteractionSource, (Indication) objConsume, z, role, function0);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer.endReplaceableGroup();
                return modifierM855triStateToggleableO2vRcR0;
            }
        });
    }

    /* JADX INFO: renamed from: triStateToggleable-O2vRcR0, reason: not valid java name */
    public static final Modifier m855triStateToggleableO2vRcR0(Modifier modifier, final ToggleableState toggleableState, final MutableInteractionSource mutableInteractionSource, final Indication indication, final boolean z, final Role role, final Function0<Unit> function0) {
        return InspectableValueKt.inspectableWrapper(modifier, InspectableValueKt.isDebugInspectorInfoEnabled() ? new Function1<InspectorInfo, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable-O2vRcR0$$inlined$debugInspectorInfo$1
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
                inspectorInfo.setName("triStateToggleable");
                inspectorInfo.getProperties().set("state", toggleableState);
                inspectorInfo.getProperties().set("enabled", Boolean.valueOf(z));
                inspectorInfo.getProperties().set("role", role);
                inspectorInfo.getProperties().set("interactionSource", mutableInteractionSource);
                inspectorInfo.getProperties().set("indication", indication);
                inspectorInfo.getProperties().set("onClick", function0);
            }
        } : InspectableValueKt.getNoInspectorInfo(), SemanticsModifierKt.semantics$default(ClickableKt.m273clickableO2vRcR0(Modifier.INSTANCE, mutableInteractionSource, indication, (8 & 4) != 0 ? true : z, (8 & 8) != 0 ? null : null, (8 & 16) != 0 ? null : role, function0), false, new Function1<SemanticsPropertyReceiver, Unit>() { // from class: androidx.compose.foundation.selection.ToggleableKt$triStateToggleable$4$1
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
                SemanticsPropertiesKt.setToggleableState(semanticsPropertyReceiver, toggleableState);
            }
        }, 1, null));
    }
}
