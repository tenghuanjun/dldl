package androidx.compose.ui.unit;

import androidx.compose.ui.util.MathHelpersKt;
import com.bytedance.framwork.core.sdklib.DBHelper;
import com.lzy.okgo.model.Progress;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: compiled from: TextUnit.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000F\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\b\u001a \u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\f2\u0006\u0010 \u001a\u00020!ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u001a\u001a\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b'\u0010\u0015\u001a\"\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010*\u001a*\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u00062\u0006\u0010(\u001a\u00020\u00062\u0006\u0010+\u001a\u00020\u0006H\u0001ø\u0001\u0000¢\u0006\u0004\b,\u0010-\u001a*\u0010.\u001a\u00020\u00062\u0006\u0010/\u001a\u00020\u00062\u0006\u00100\u001a\u00020\u00062\u0006\u00101\u001a\u00020\fH\u0007ø\u0001\u0000¢\u0006\u0004\b2\u00103\u001a\u001d\u00104\u001a\u00020\u00062\u0006\u00105\u001a\u00020\u00012\u0006\u00106\u001a\u00020\fH\u0001¢\u0006\u0002\u00107\u001a%\u00108\u001a\u00020\u0006*\u00020\u00062\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u00060:H\u0086\bø\u0001\u0000¢\u0006\u0004\b;\u0010<\u001a\u001f\u0010=\u001a\u00020\u0006*\u00020\u00072\u0006\u0010>\u001a\u00020\u0006H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010@\u001a\u001f\u0010=\u001a\u00020\u0006*\u00020\f2\u0006\u0010>\u001a\u00020\u0006H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010#\u001a\u001f\u0010=\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010>\u001a\u00020\u0006H\u0087\nø\u0001\u0000¢\u0006\u0004\b?\u0010A\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\r\u001a\u0004\b\n\u0010\u000e\"\u001e\u0010\u0005\u001a\u00020\u0006*\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\b\u0010\u0010\u001a\u0004\b\n\u0010\u0011\"\u001f\u0010\u0012\u001a\u00020\u0013*\u00020\u00068Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u001e\u0010\u0018\u001a\u00020\u0013*\u00020\u00068FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0019\u0010\u0015\u001a\u0004\b\u001a\u0010\u0017\"\u001e\u0010\u001b\u001a\u00020\u0006*\u00020\u00078FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\t\u001a\u0004\b\u001d\u0010\u000b\"\u001e\u0010\u001b\u001a\u00020\u0006*\u00020\f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\r\u001a\u0004\b\u001d\u0010\u000e\"\u001e\u0010\u001b\u001a\u00020\u0006*\u00020\u000f8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u0010\u001a\u0004\b\u001d\u0010\u0011\u0082\u0002\u0007\n\u0005\b¡\u001e0\u0001¨\u0006B"}, d2 = {"UNIT_MASK", "", "UNIT_TYPE_EM", "UNIT_TYPE_SP", "UNIT_TYPE_UNSPECIFIED", "em", "Landroidx/compose/ui/unit/TextUnit;", "", "getEm$annotations", "(D)V", "getEm", "(D)J", "", "(F)V", "(F)J", "", "(I)V", "(I)J", "isSpecified", "", "isSpecified--R2X_6o$annotations", "(J)V", "isSpecified--R2X_6o", "(J)Z", "isUnspecified", "isUnspecified--R2X_6o$annotations", "isUnspecified--R2X_6o", "sp", "getSp$annotations", "getSp", "TextUnit", DBHelper.COL_VALUE, "type", "Landroidx/compose/ui/unit/TextUnitType;", "TextUnit-anM5pPY", "(FJ)J", "checkArithmetic", "", "a", "checkArithmetic--R2X_6o", "b", "checkArithmetic-NB67dxo", "(JJ)V", "c", "checkArithmetic-vU-0ePk", "(JJJ)V", "lerp", "start", "stop", Progress.FRACTION, "lerp-C3pnCVY", "(JJF)J", "pack", "unitType", "v", "(JF)J", "takeOrElse", "block", "Lkotlin/Function0;", "takeOrElse-eAf_CNQ", "(JLkotlin/jvm/functions/Function0;)J", "times", "other", "times-mpE4wyQ", "(DJ)J", "(IJ)J", "ui-unit_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TextUnitKt {
    private static final long UNIT_MASK = 1095216660480L;
    private static final long UNIT_TYPE_EM = 8589934592L;
    private static final long UNIT_TYPE_SP = 4294967296L;
    private static final long UNIT_TYPE_UNSPECIFIED = 0;

    public static /* synthetic */ void getEm$annotations(double d) {
    }

    public static /* synthetic */ void getEm$annotations(float f) {
    }

    public static /* synthetic */ void getEm$annotations(int i) {
    }

    public static /* synthetic */ void getSp$annotations(double d) {
    }

    public static /* synthetic */ void getSp$annotations(float f) {
    }

    public static /* synthetic */ void getSp$annotations(int i) {
    }

    /* JADX INFO: renamed from: isSpecified--R2X_6o$annotations, reason: not valid java name */
    public static /* synthetic */ void m6094isSpecifiedR2X_6o$annotations(long j) {
    }

    /* JADX INFO: renamed from: isUnspecified--R2X_6o$annotations, reason: not valid java name */
    public static /* synthetic */ void m6096isUnspecifiedR2X_6o$annotations(long j) {
    }

    /* JADX INFO: renamed from: TextUnit-anM5pPY, reason: not valid java name */
    public static final long m6089TextUnitanM5pPY(float f, long j) {
        return pack(j, f);
    }

    /* JADX INFO: renamed from: isSpecified--R2X_6o, reason: not valid java name */
    public static final boolean m6093isSpecifiedR2X_6o(long j) {
        return !m6095isUnspecifiedR2X_6o(j);
    }

    /* JADX INFO: renamed from: isUnspecified--R2X_6o, reason: not valid java name */
    public static final boolean m6095isUnspecifiedR2X_6o(long j) {
        return TextUnit.m6075getRawTypeimpl(j) == 0;
    }

    public static final long getSp(float f) {
        return pack(4294967296L, f);
    }

    public static final long getEm(float f) {
        return pack(8589934592L, f);
    }

    public static final long getSp(double d) {
        return pack(4294967296L, (float) d);
    }

    public static final long getEm(double d) {
        return pack(8589934592L, (float) d);
    }

    public static final long getSp(int i) {
        return pack(4294967296L, i);
    }

    public static final long getEm(int i) {
        return pack(8589934592L, i);
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m6100timesmpE4wyQ(float f, long j) {
        m6090checkArithmeticR2X_6o(j);
        return pack(TextUnit.m6075getRawTypeimpl(j), f * TextUnit.m6077getValueimpl(j));
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m6099timesmpE4wyQ(double d, long j) {
        m6090checkArithmeticR2X_6o(j);
        return pack(TextUnit.m6075getRawTypeimpl(j), ((float) d) * TextUnit.m6077getValueimpl(j));
    }

    /* JADX INFO: renamed from: times-mpE4wyQ, reason: not valid java name */
    public static final long m6101timesmpE4wyQ(int i, long j) {
        m6090checkArithmeticR2X_6o(j);
        return pack(TextUnit.m6075getRawTypeimpl(j), i * TextUnit.m6077getValueimpl(j));
    }

    public static final long pack(long j, float f) {
        return TextUnit.m6069constructorimpl(j | (((long) Float.floatToIntBits(f)) & 4294967295L));
    }

    /* JADX INFO: renamed from: checkArithmetic--R2X_6o, reason: not valid java name */
    public static final void m6090checkArithmeticR2X_6o(long j) {
        if (m6095isUnspecifiedR2X_6o(j)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.".toString());
        }
    }

    /* JADX INFO: renamed from: checkArithmetic-NB67dxo, reason: not valid java name */
    public static final void m6091checkArithmeticNB67dxo(long j, long j2) {
        if (m6095isUnspecifiedR2X_6o(j) || m6095isUnspecifiedR2X_6o(j2)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.".toString());
        }
        if (TextUnitType.m6105equalsimpl0(TextUnit.m6076getTypeUIouoOA(j), TextUnit.m6076getTypeUIouoOA(j2))) {
            return;
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) TextUnitType.m6107toStringimpl(TextUnit.m6076getTypeUIouoOA(j))) + " and " + ((Object) TextUnitType.m6107toStringimpl(TextUnit.m6076getTypeUIouoOA(j2)))).toString());
    }

    /* JADX INFO: renamed from: checkArithmetic-vU-0ePk, reason: not valid java name */
    public static final void m6092checkArithmeticvU0ePk(long j, long j2, long j3) {
        if (m6095isUnspecifiedR2X_6o(j) || m6095isUnspecifiedR2X_6o(j2) || m6095isUnspecifiedR2X_6o(j3)) {
            throw new IllegalArgumentException("Cannot perform operation for Unspecified type.".toString());
        }
        if (TextUnitType.m6105equalsimpl0(TextUnit.m6076getTypeUIouoOA(j), TextUnit.m6076getTypeUIouoOA(j2)) && TextUnitType.m6105equalsimpl0(TextUnit.m6076getTypeUIouoOA(j2), TextUnit.m6076getTypeUIouoOA(j3))) {
            return;
        }
        throw new IllegalArgumentException(("Cannot perform operation for " + ((Object) TextUnitType.m6107toStringimpl(TextUnit.m6076getTypeUIouoOA(j))) + " and " + ((Object) TextUnitType.m6107toStringimpl(TextUnit.m6076getTypeUIouoOA(j2)))).toString());
    }

    /* JADX INFO: renamed from: lerp-C3pnCVY, reason: not valid java name */
    public static final long m6097lerpC3pnCVY(long j, long j2, float f) {
        m6091checkArithmeticNB67dxo(j, j2);
        return pack(TextUnit.m6075getRawTypeimpl(j), MathHelpersKt.lerp(TextUnit.m6077getValueimpl(j), TextUnit.m6077getValueimpl(j2), f));
    }

    /* JADX INFO: renamed from: takeOrElse-eAf_CNQ, reason: not valid java name */
    public static final long m6098takeOrElseeAf_CNQ(long j, Function0<TextUnit> function0) {
        return !m6095isUnspecifiedR2X_6o(j) ? j : function0.invoke().getPackedValue();
    }
}
