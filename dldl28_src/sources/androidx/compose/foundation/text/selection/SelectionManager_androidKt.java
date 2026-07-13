package androidx.compose.foundation.text.selection;

import android.view.KeyEvent;
import androidx.compose.foundation.Magnifier_androidKt;
import androidx.compose.foundation.PlatformMagnifierFactory;
import androidx.compose.foundation.text.KeyCommand;
import androidx.compose.foundation.text.KeyMapping_androidKt;
import androidx.compose.foundation.text.selection.SelectionManager_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.ProvidableCompositionLocal;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.DpSize;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Lambda;

/* JADX INFO: compiled from: SelectionManager.android.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000ø\u0001\u0000¢\u0006\u0004\b\u0004\u0010\u0005\u001a\u0014\u0010\u0006\u001a\u00020\u0007*\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0000\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\n²\u0006\n\u0010\u000b\u001a\u00020\fX\u008a\u008e\u0002"}, d2 = {"isCopyKeyEvent", "", "keyEvent", "Landroidx/compose/ui/input/key/KeyEvent;", "isCopyKeyEvent-ZmokQxo", "(Landroid/view/KeyEvent;)Z", "selectionMagnifier", "Landroidx/compose/ui/Modifier;", "manager", "Landroidx/compose/foundation/text/selection/SelectionManager;", "foundation_release", "magnifierSize", "Landroidx/compose/ui/unit/IntSize;"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SelectionManager_androidKt {
    /* JADX INFO: renamed from: isCopyKeyEvent-ZmokQxo, reason: not valid java name */
    public static final boolean m1087isCopyKeyEventZmokQxo(KeyEvent keyEvent) {
        return KeyMapping_androidKt.getPlatformDefaultKeyMapping().mo904mapZmokQxo(keyEvent) == KeyCommand.COPY;
    }

    public static final Modifier selectionMagnifier(Modifier modifier, SelectionManager selectionManager) {
        return !Magnifier_androidKt.isPlatformMagnifierSupported$default(0, 1, null) ? modifier : ComposedModifierKt.composed$default(modifier, null, new AnonymousClass1(selectionManager), 1, null);
    }

    /* JADX INFO: renamed from: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1, reason: invalid class name */
    /* JADX INFO: compiled from: SelectionManager.android.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u000b¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"<anonymous>", "Landroidx/compose/ui/Modifier;", "invoke", "(Landroidx/compose/ui/Modifier;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/Modifier;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    static final class AnonymousClass1 extends Lambda implements Function3<Modifier, Composer, Integer, Modifier> {
        final /* synthetic */ SelectionManager $manager;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(SelectionManager selectionManager) {
            super(3);
            this.$manager = selectionManager;
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Modifier invoke(Modifier modifier, Composer composer, Integer num) {
            return invoke(modifier, composer, num.intValue());
        }

        public final Modifier invoke(Modifier modifier, Composer composer, int i) {
            composer.startReplaceableGroup(-1914520728);
            ComposerKt.sourceInformation(composer, "C45@1920L7,46@1957L41:SelectionManager.android.kt#eksfi3");
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1914520728, i, -1, "androidx.compose.foundation.text.selection.selectionMagnifier.<anonymous> (SelectionManager.android.kt:45)");
            }
            ProvidableCompositionLocal<Density> localDensity = CompositionLocalsKt.getLocalDensity();
            ComposerKt.sourceInformationMarkerStart(composer, 2023513938, "CC:CompositionLocal.kt#9igjgp");
            Object objConsume = composer.consume(localDensity);
            ComposerKt.sourceInformationMarkerEnd(composer);
            final Density density = (Density) objConsume;
            composer.startReplaceableGroup(-492369756);
            ComposerKt.sourceInformation(composer, "CC(remember):Composables.kt#9igjgp");
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(IntSize.m6048boximpl(IntSize.INSTANCE.m6061getZeroYbymL2g()), null, 2, null);
                composer.updateRememberedValue(objRememberedValue);
            }
            composer.endReplaceableGroup();
            final MutableState mutableState = (MutableState) objRememberedValue;
            final SelectionManager selectionManager = this.$manager;
            Function0<Offset> function0 = new Function0<Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt.selectionMagnifier.1.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public /* bridge */ /* synthetic */ Offset invoke() {
                    return Offset.m3208boximpl(m1088invokeF1C5BW0());
                }

                /* JADX INFO: renamed from: invoke-F1C5BW0, reason: not valid java name */
                public final long m1088invokeF1C5BW0() {
                    return SelectionManagerKt.m1084calculateSelectionMagnifierCenterAndroidO0kMr_c(selectionManager, AnonymousClass1.invoke$lambda$1(mutableState));
                }
            };
            composer.startReplaceableGroup(-1347892975);
            boolean zChanged = composer.changed(mutableState) | composer.changed(density);
            Object objRememberedValue2 = composer.rememberedValue();
            if (zChanged || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue2 = (Function1) new Function1<Function0<? extends Offset>, Modifier>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Modifier invoke(Function0<? extends Offset> function02) {
                        return invoke2((Function0<Offset>) function02);
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final Modifier invoke2(final Function0<Offset> function02) {
                        Modifier.Companion companion = Modifier.INSTANCE;
                        Function1<Density, Offset> function1 = new Function1<Density, Offset>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Offset invoke(Density density2) {
                                return Offset.m3208boximpl(m1089invoketuRUvjQ(density2));
                            }

                            /* JADX INFO: renamed from: invoke-tuRUvjQ, reason: not valid java name */
                            public final long m1089invoketuRUvjQ(Density density2) {
                                return function02.invoke().getPackedValue();
                            }
                        };
                        final Density density2 = density;
                        final MutableState<IntSize> mutableState2 = mutableState;
                        return Magnifier_androidKt.m313magnifierjPUL71Q$default(companion, function1, null, new Function1<DpSize, Unit>() { // from class: androidx.compose.foundation.text.selection.SelectionManager_androidKt$selectionMagnifier$1$2$1.2
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(DpSize dpSize) {
                                m1090invokeEaSLcWc(dpSize.getPackedValue());
                                return Unit.INSTANCE;
                            }

                            /* JADX INFO: renamed from: invoke-EaSLcWc, reason: not valid java name */
                            public final void m1090invokeEaSLcWc(long j) {
                                MutableState<IntSize> mutableState3 = mutableState2;
                                Density density3 = density2;
                                SelectionManager_androidKt.AnonymousClass1.invoke$lambda$2(mutableState3, IntSizeKt.IntSize(density3.mo339roundToPx0680j_4(DpSize.m5980getWidthD9Ej5fM(j)), density3.mo339roundToPx0680j_4(DpSize.m5978getHeightD9Ej5fM(j))));
                            }
                        }, 0.0f, true, 0L, 0.0f, 0.0f, false, PlatformMagnifierFactory.INSTANCE.getForCurrentPlatform(), 490, null);
                    }
                };
                composer.updateRememberedValue(objRememberedValue2);
            }
            composer.endReplaceableGroup();
            Modifier modifierAnimatedSelectionMagnifier = SelectionMagnifierKt.animatedSelectionMagnifier(modifier, function0, (Function1) objRememberedValue2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer.endReplaceableGroup();
            return modifierAnimatedSelectionMagnifier;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final long invoke$lambda$1(MutableState<IntSize> mutableState) {
            return mutableState.getValue().getPackedValue();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final void invoke$lambda$2(MutableState<IntSize> mutableState, long j) {
            mutableState.setValue(IntSize.m6048boximpl(j));
        }
    }
}
