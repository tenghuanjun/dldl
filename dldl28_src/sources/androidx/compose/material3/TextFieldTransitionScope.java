package androidx.compose.material3;

import androidx.compose.runtime.State;
import androidx.compose.ui.graphics.Color;
import com.nirvana.tools.logger.cache.db.DBHelpTool;
import kotlin.Metadata;

/* JADX INFO: compiled from: TextFieldImpl.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\u0018\u0002\n\u0002\b\t\bÂ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JÇ\u0001\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\b2\u0017\u0010\n\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u000b¢\u0006\u0002\b\f2\u0006\u0010\r\u001a\u00020\u000e2z\u0010\u000f\u001av\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0014\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0015\u0012\u0013\u0012\u00110\b¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0016\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0017\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\u0012\u0012\b\b\u0013\u0012\u0004\b\b(\u0018\u0012\u0004\u0012\u00020\u00040\u0010¢\u0006\u0002\b\fH\u0007ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006\u001b²\u0006\n\u0010\u0014\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0017\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0018\u001a\u00020\u0011X\u008a\u0084\u0002²\u0006\n\u0010\u0015\u001a\u00020\bX\u008a\u0084\u0002²\u0006\n\u0010\u0016\u001a\u00020\bX\u008a\u0084\u0002"}, d2 = {"Landroidx/compose/material3/TextFieldTransitionScope;", "", "()V", "Transition", "", "inputState", "Landroidx/compose/material3/InputPhase;", "focusedTextStyleColor", "Landroidx/compose/ui/graphics/Color;", "unfocusedTextStyleColor", "contentColor", "Lkotlin/Function1;", "Landroidx/compose/runtime/Composable;", "showLabel", "", DBHelpTool.RecordEntry.COLUMN_NAME_CONTENT, "Lkotlin/Function5;", "", "Lkotlin/ParameterName;", "name", "labelProgress", "labelTextStyleColor", "labelContentColor", "placeholderOpacity", "prefixSuffixOpacity", "Transition-DTcfvLk", "(Landroidx/compose/material3/InputPhase;JJLkotlin/jvm/functions/Function3;ZLkotlin/jvm/functions/Function7;Landroidx/compose/runtime/Composer;I)V", "material3_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final class TextFieldTransitionScope {
    public static final TextFieldTransitionScope INSTANCE = new TextFieldTransitionScope();

    /* JADX INFO: compiled from: TextFieldImpl.kt */
    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InputPhase.values().length];
            try {
                iArr[InputPhase.Focused.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InputPhase.UnfocusedEmpty.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InputPhase.UnfocusedNotEmpty.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private TextFieldTransitionScope() {
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x01ee  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:111:0x0214  */
    /* JADX WARN: Removed duplicated region for block: B:114:0x0224  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0235  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x023d  */
    /* JADX WARN: Removed duplicated region for block: B:129:0x029c  */
    /* JADX WARN: Removed duplicated region for block: B:132:0x02ac  */
    /* JADX WARN: Removed duplicated region for block: B:141:0x02bd  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x02c5  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x02e1  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x02f1  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x0308  */
    /* JADX WARN: Removed duplicated region for block: B:164:0x0359  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0367  */
    /* JADX WARN: Removed duplicated region for block: B:168:0x036a  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:174:0x0391  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0399  */
    /* JADX WARN: Removed duplicated region for block: B:179:0x03c8  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x03d7  */
    /* JADX WARN: Removed duplicated region for block: B:183:0x03da  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x03e2  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x03fe  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x040d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0410  */
    /* JADX WARN: Removed duplicated region for block: B:196:0x0418  */
    /* JADX WARN: Removed duplicated region for block: B:201:0x048f  */
    /* JADX WARN: Removed duplicated region for block: B:204:0x052f  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0114  */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:75:0x013b  */
    /* JADX WARN: Removed duplicated region for block: B:78:0x0147  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0156  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x01c8  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01d0  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x01dd  */
    /* JADX INFO: renamed from: Transition-DTcfvLk, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public final void m2167TransitionDTcfvLk(final androidx.compose.material3.InputPhase r25, final long r26, final long r28, final kotlin.jvm.functions.Function3<? super androidx.compose.material3.InputPhase, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, androidx.compose.ui.graphics.Color> r30, final boolean r31, final kotlin.jvm.functions.Function7<? super java.lang.Float, ? super androidx.compose.ui.graphics.Color, ? super androidx.compose.ui.graphics.Color, ? super java.lang.Float, ? super java.lang.Float, ? super androidx.compose.runtime.Composer, ? super java.lang.Integer, kotlin.Unit> r32, androidx.compose.runtime.Composer r33, final int r34) {
        /*
            Method dump skipped, instruction units count: 1364
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.compose.material3.TextFieldTransitionScope.m2167TransitionDTcfvLk(androidx.compose.material3.InputPhase, long, long, kotlin.jvm.functions.Function3, boolean, kotlin.jvm.functions.Function7, androidx.compose.runtime.Composer, int):void");
    }

    private static final float Transition_DTcfvLk$lambda$1(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float Transition_DTcfvLk$lambda$3(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final float Transition_DTcfvLk$lambda$5(State<Float> state) {
        return state.getValue().floatValue();
    }

    private static final long Transition_DTcfvLk$lambda$7(State<Color> state) {
        return state.getValue().m3504unboximpl();
    }

    private static final long Transition_DTcfvLk$lambda$8(State<Color> state) {
        return state.getValue().m3504unboximpl();
    }
}
