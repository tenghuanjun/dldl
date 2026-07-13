package androidx.compose.ui.unit;

import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FloatCompanionObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;

/* JADX INFO: compiled from: TextUnit.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0006\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u0011\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0000H\u0086\nø\u0001\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u001e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001dH\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001fJ\u001e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0014H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010 J\u001e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010!J\u001a\u0010\"\u001a\u00020\u00072\b\u0010\u0019\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b#\u0010$J\u0010\u0010%\u001a\u00020\u0018HÖ\u0001¢\u0006\u0004\b&\u0010'J\u001e\u0010(\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u001dH\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010\u001fJ\u001e\u0010(\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0014H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010 J\u001e\u0010(\u001a\u00020\u00002\u0006\u0010\u0019\u001a\u00020\u0018H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b)\u0010!J\u000f\u0010*\u001a\u00020+H\u0016¢\u0006\u0004\b,\u0010-J\u0016\u0010.\u001a\u00020\u0000H\u0086\nø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b/\u0010\u0005R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\f\u001a\u00020\u00038@X\u0081\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0005R\u0017\u0010\u0010\u001a\u00020\u00118Fø\u0001\u0000ø\u0001\u0001¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0005R\u0011\u0010\u0013\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u00061"}, d2 = {"Landroidx/compose/ui/unit/TextUnit;", "", "packedValue", "", "constructor-impl", "(J)J", "isEm", "", "isEm-impl", "(J)Z", "isSp", "isSp-impl", "rawType", "getRawType$annotations", "()V", "getRawType-impl", "type", "Landroidx/compose/ui/unit/TextUnitType;", "getType-UIouoOA", DBHelper.COL_VALUE, "", "getValue-impl", "(J)F", "compareTo", "", "other", "compareTo--R2X_6o", "(JJ)I", "div", "", "div-kPz2Gy4", "(JD)J", "(JF)J", "(JI)J", "equals", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "(J)I", "times", "times-kPz2Gy4", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "unaryMinus", "unaryMinus-XSAIIZE", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class TextUnit {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final TextUnitType[] TextUnitTypes = {TextUnitType.m6102boximpl(TextUnitType.INSTANCE.m6111getUnspecifiedUIouoOA()), TextUnitType.m6102boximpl(TextUnitType.INSTANCE.m6110getSpUIouoOA()), TextUnitType.m6102boximpl(TextUnitType.INSTANCE.m6109getEmUIouoOA())};
    private static final long Unspecified = TextUnitKt.pack(0, Float.NaN);
    private final long packedValue;

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ TextUnit m6067boximpl(long j) {
        return new TextUnit(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m6069constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m6073equalsimpl(long j, Object obj) {
        return (obj instanceof TextUnit) && j == ((TextUnit) obj).getPackedValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m6074equalsimpl0(long j, long j2) {
        return j == j2;
    }

    public static /* synthetic */ void getRawType$annotations() {
    }

    /* JADX INFO: renamed from: getRawType-impl, reason: not valid java name */
    public static final long m6075getRawTypeimpl(long j) {
        return j & 1095216660480L;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m6078hashCodeimpl(long j) {
        return UByte$$ExternalSyntheticBackport0.m(j);
    }

    public boolean equals(Object obj) {
        return m6073equalsimpl(this.packedValue, obj);
    }

    public int hashCode() {
        return m6078hashCodeimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getPackedValue() {
        return this.packedValue;
    }

    private /* synthetic */ TextUnit(long j) {
        this.packedValue = j;
    }

    /* JADX INFO: renamed from: unaryMinus-XSAIIZE, reason: not valid java name */
    public static final long m6085unaryMinusXSAIIZE(long j) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), -m6077getValueimpl(j));
    }

    /* JADX INFO: renamed from: div-kPz2Gy4, reason: not valid java name */
    public static final long m6071divkPz2Gy4(long j, float f) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), m6077getValueimpl(j) / f);
    }

    /* JADX INFO: renamed from: div-kPz2Gy4, reason: not valid java name */
    public static final long m6070divkPz2Gy4(long j, double d) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), (float) (((double) m6077getValueimpl(j)) / d));
    }

    /* JADX INFO: renamed from: div-kPz2Gy4, reason: not valid java name */
    public static final long m6072divkPz2Gy4(long j, int i) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), m6077getValueimpl(j) / i);
    }

    /* JADX INFO: renamed from: times-kPz2Gy4, reason: not valid java name */
    public static final long m6082timeskPz2Gy4(long j, float f) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), m6077getValueimpl(j) * f);
    }

    /* JADX INFO: renamed from: times-kPz2Gy4, reason: not valid java name */
    public static final long m6081timeskPz2Gy4(long j, double d) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), (float) (((double) m6077getValueimpl(j)) * d));
    }

    /* JADX INFO: renamed from: times-kPz2Gy4, reason: not valid java name */
    public static final long m6083timeskPz2Gy4(long j, int i) {
        TextUnitKt.m6090checkArithmeticR2X_6o(j);
        return TextUnitKt.pack(m6075getRawTypeimpl(j), m6077getValueimpl(j) * i);
    }

    /* JADX INFO: renamed from: compareTo--R2X_6o, reason: not valid java name */
    public static final int m6068compareToR2X_6o(long j, long j2) {
        TextUnitKt.m6091checkArithmeticNB67dxo(j, j2);
        return Float.compare(m6077getValueimpl(j), m6077getValueimpl(j2));
    }

    public String toString() {
        return m6084toStringimpl(this.packedValue);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m6084toStringimpl(long j) {
        long jM6076getTypeUIouoOA = m6076getTypeUIouoOA(j);
        if (TextUnitType.m6105equalsimpl0(jM6076getTypeUIouoOA, TextUnitType.INSTANCE.m6111getUnspecifiedUIouoOA())) {
            return "Unspecified";
        }
        if (TextUnitType.m6105equalsimpl0(jM6076getTypeUIouoOA, TextUnitType.INSTANCE.m6110getSpUIouoOA())) {
            return m6077getValueimpl(j) + ".sp";
        }
        if (!TextUnitType.m6105equalsimpl0(jM6076getTypeUIouoOA, TextUnitType.INSTANCE.m6109getEmUIouoOA())) {
            return "Invalid";
        }
        return m6077getValueimpl(j) + ".em";
    }

    /* JADX INFO: compiled from: TextUnit.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0080\u0004¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R$\u0010\t\u001a\u00020\n8\u0006X\u0087\u0004ø\u0001\u0000ø\u0001\u0001¢\u0006\u0010\n\u0002\u0010\u000e\u0012\u0004\b\u000b\u0010\u0002\u001a\u0004\b\f\u0010\r\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/unit/TextUnit$Companion;", "", "()V", "TextUnitTypes", "", "Landroidx/compose/ui/unit/TextUnitType;", "getTextUnitTypes$ui_unit_release", "()[Landroidx/compose/ui/unit/TextUnitType;", "[Landroidx/compose/ui/unit/TextUnitType;", "Unspecified", "Landroidx/compose/ui/unit/TextUnit;", "getUnspecified-XSAIIZE$annotations", "getUnspecified-XSAIIZE", "()J", "J", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getUnspecified-XSAIIZE$annotations, reason: not valid java name */
        public static /* synthetic */ void m6087getUnspecifiedXSAIIZE$annotations() {
        }

        private Companion() {
        }

        public final TextUnitType[] getTextUnitTypes$ui_unit_release() {
            return TextUnit.TextUnitTypes;
        }

        /* JADX INFO: renamed from: getUnspecified-XSAIIZE, reason: not valid java name */
        public final long m6088getUnspecifiedXSAIIZE() {
            return TextUnit.Unspecified;
        }
    }

    /* JADX INFO: renamed from: getType-UIouoOA, reason: not valid java name */
    public static final long m6076getTypeUIouoOA(long j) {
        return TextUnitTypes[(int) (m6075getRawTypeimpl(j) >>> 32)].getType();
    }

    /* JADX INFO: renamed from: isSp-impl, reason: not valid java name */
    public static final boolean m6080isSpimpl(long j) {
        return m6075getRawTypeimpl(j) == IjkMediaMeta.AV_CH_WIDE_RIGHT;
    }

    /* JADX INFO: renamed from: isEm-impl, reason: not valid java name */
    public static final boolean m6079isEmimpl(long j) {
        return m6075getRawTypeimpl(j) == IjkMediaMeta.AV_CH_SURROUND_DIRECT_LEFT;
    }

    /* JADX INFO: renamed from: getValue-impl, reason: not valid java name */
    public static final float m6077getValueimpl(long j) {
        FloatCompanionObject floatCompanionObject = FloatCompanionObject.INSTANCE;
        return Float.intBitsToFloat((int) (j & 4294967295L));
    }
}
