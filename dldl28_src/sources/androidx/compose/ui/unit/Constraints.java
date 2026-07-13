package androidx.compose.ui.unit;

import com.bytedance.framwork.core.sdklib.DBHelper;
import kotlin.Metadata;
import kotlin.UByte$$ExternalSyntheticBackport0;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Constraints.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b!\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J;\u0010#\u001a\u00020\u00002\b\b\u0002\u0010 \u001a\u00020\u00072\b\b\u0002\u0010\u001c\u001a\u00020\u00072\b\b\u0002\u0010\u001e\u001a\u00020\u00072\b\b\u0002\u0010\u001a\u001a\u00020\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u001a\u0010&\u001a\u00020\u000b2\b\u0010'\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b(\u0010)J\u0010\u0010*\u001a\u00020\u0007HÖ\u0001¢\u0006\u0004\b+\u0010\tJ\u000f\u0010,\u001a\u00020-H\u0016¢\u0006\u0004\b.\u0010/R\u0014\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0015\u0010\u0012\u001a\u0004\b\u0016\u0010\rR\u001a\u0010\u0017\u001a\u00020\u000b8FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0019\u0010\rR\u0011\u0010\u001a\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\tR\u0011\u0010\u001c\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\tR\u0011\u0010\u001e\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\tR\u0011\u0010 \u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b!\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0000X\u0081\u0004¢\u0006\b\n\u0000\u0012\u0004\b\"\u0010\u0012\u0088\u0001\u0002\u0092\u0001\u00020\u0003\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u00061"}, d2 = {"Landroidx/compose/ui/unit/Constraints;", "", DBHelper.COL_VALUE, "", "constructor-impl", "(J)J", "focusIndex", "", "getFocusIndex-impl", "(J)I", "hasBoundedHeight", "", "getHasBoundedHeight-impl", "(J)Z", "hasBoundedWidth", "getHasBoundedWidth-impl", "hasFixedHeight", "getHasFixedHeight$annotations", "()V", "getHasFixedHeight-impl", "hasFixedWidth", "getHasFixedWidth$annotations", "getHasFixedWidth-impl", "isZero", "isZero$annotations", "isZero-impl", "maxHeight", "getMaxHeight-impl", "maxWidth", "getMaxWidth-impl", "minHeight", "getMinHeight-impl", "minWidth", "getMinWidth-impl", "getValue$annotations", "copy", "copy-Zbe2FdA", "(JIIII)J", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
@JvmInline
public final class Constraints {
    private static final long FocusMask = 3;
    public static final int Infinity = Integer.MAX_VALUE;
    private static final int MaxFocusBits = 18;
    private static final long MaxFocusHeight = 3;
    private static final long MaxFocusWidth = 1;
    private static final int MaxNonFocusBits = 13;
    private static final int MinFocusBits = 16;
    private static final long MinFocusHeight = 2;
    private static final int MinFocusMask = 65535;
    private static final long MinFocusWidth = 0;
    private static final int MinNonFocusBits = 15;
    private static final int MinNonFocusMask = 32767;
    private final long value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int[] MinHeightOffsets = {18, 20, 17, 15};
    private static final int MaxFocusMask = 262143;
    private static final int MaxNonFocusMask = 8191;
    private static final int[] WidthMask = {65535, MaxFocusMask, 32767, MaxNonFocusMask};
    private static final int[] HeightMask = {32767, MaxNonFocusMask, 65535, MaxFocusMask};

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Constraints m5816boximpl(long j) {
        return new Constraints(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m5817constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5820equalsimpl(long j, Object obj) {
        return (obj instanceof Constraints) && j == ((Constraints) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5821equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getFocusIndex-impl, reason: not valid java name */
    private static final int m5822getFocusIndeximpl(long j) {
        return (int) (j & 3);
    }

    public static /* synthetic */ void getHasFixedHeight$annotations() {
    }

    public static /* synthetic */ void getHasFixedWidth$annotations() {
    }

    public static /* synthetic */ void getValue$annotations() {
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5831hashCodeimpl(long j) {
        return UByte$$ExternalSyntheticBackport0.m(j);
    }

    public static /* synthetic */ void isZero$annotations() {
    }

    public boolean equals(Object obj) {
        return m5820equalsimpl(this.value, obj);
    }

    public int hashCode() {
        return m5831hashCodeimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getValue() {
        return this.value;
    }

    private /* synthetic */ Constraints(long j) {
        this.value = j;
    }

    /* JADX INFO: renamed from: getMinWidth-impl, reason: not valid java name */
    public static final int m5830getMinWidthimpl(long j) {
        return ((int) (j >> 2)) & WidthMask[m5822getFocusIndeximpl(j)];
    }

    /* JADX INFO: renamed from: getMaxWidth-impl, reason: not valid java name */
    public static final int m5828getMaxWidthimpl(long j) {
        int i = ((int) (j >> 33)) & WidthMask[m5822getFocusIndeximpl(j)];
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        return i - 1;
    }

    /* JADX INFO: renamed from: getMinHeight-impl, reason: not valid java name */
    public static final int m5829getMinHeightimpl(long j) {
        int iM5822getFocusIndeximpl = m5822getFocusIndeximpl(j);
        return ((int) (j >> MinHeightOffsets[iM5822getFocusIndeximpl])) & HeightMask[iM5822getFocusIndeximpl];
    }

    /* JADX INFO: renamed from: getMaxHeight-impl, reason: not valid java name */
    public static final int m5827getMaxHeightimpl(long j) {
        int iM5822getFocusIndeximpl = m5822getFocusIndeximpl(j);
        int i = ((int) (j >> (MinHeightOffsets[iM5822getFocusIndeximpl] + 31))) & HeightMask[iM5822getFocusIndeximpl];
        if (i == 0) {
            return Integer.MAX_VALUE;
        }
        return i - 1;
    }

    /* JADX INFO: renamed from: getHasBoundedWidth-impl, reason: not valid java name */
    public static final boolean m5824getHasBoundedWidthimpl(long j) {
        return (((int) (j >> 33)) & WidthMask[m5822getFocusIndeximpl(j)]) != 0;
    }

    /* JADX INFO: renamed from: getHasBoundedHeight-impl, reason: not valid java name */
    public static final boolean m5823getHasBoundedHeightimpl(long j) {
        int iM5822getFocusIndeximpl = m5822getFocusIndeximpl(j);
        return (((int) (j >> (MinHeightOffsets[iM5822getFocusIndeximpl] + 31))) & HeightMask[iM5822getFocusIndeximpl]) != 0;
    }

    /* JADX INFO: renamed from: getHasFixedWidth-impl, reason: not valid java name */
    public static final boolean m5826getHasFixedWidthimpl(long j) {
        return m5828getMaxWidthimpl(j) == m5830getMinWidthimpl(j);
    }

    /* JADX INFO: renamed from: getHasFixedHeight-impl, reason: not valid java name */
    public static final boolean m5825getHasFixedHeightimpl(long j) {
        return m5827getMaxHeightimpl(j) == m5829getMinHeightimpl(j);
    }

    /* JADX INFO: renamed from: isZero-impl, reason: not valid java name */
    public static final boolean m5832isZeroimpl(long j) {
        return m5828getMaxWidthimpl(j) == 0 || m5827getMaxHeightimpl(j) == 0;
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA$default, reason: not valid java name */
    public static /* synthetic */ long m5819copyZbe2FdA$default(long j, int i, int i2, int i3, int i4, int i5, Object obj) {
        if ((i5 & 1) != 0) {
            i = m5830getMinWidthimpl(j);
        }
        int i6 = i;
        if ((i5 & 2) != 0) {
            i2 = m5828getMaxWidthimpl(j);
        }
        int i7 = i2;
        if ((i5 & 4) != 0) {
            i3 = m5829getMinHeightimpl(j);
        }
        int i8 = i3;
        if ((i5 & 8) != 0) {
            i4 = m5827getMaxHeightimpl(j);
        }
        return m5818copyZbe2FdA(j, i6, i7, i8, i4);
    }

    /* JADX INFO: renamed from: copy-Zbe2FdA, reason: not valid java name */
    public static final long m5818copyZbe2FdA(long j, int i, int i2, int i3, int i4) {
        if (i3 < 0 || i < 0) {
            throw new IllegalArgumentException(("minHeight(" + i3 + ") and minWidth(" + i + ") must be >= 0").toString());
        }
        if (i2 < i && i2 != Integer.MAX_VALUE) {
            throw new IllegalArgumentException(("maxWidth(" + i2 + ") must be >= minWidth(" + i + ')').toString());
        }
        if (i4 < i3 && i4 != Integer.MAX_VALUE) {
            throw new IllegalArgumentException(("maxHeight(" + i4 + ") must be >= minHeight(" + i3 + ')').toString());
        }
        return INSTANCE.m5835createConstraintsZbe2FdA$ui_unit_release(i, i2, i3, i4);
    }

    public String toString() {
        return m5833toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5833toStringimpl(long j) {
        int iM5828getMaxWidthimpl = m5828getMaxWidthimpl(j);
        String strValueOf = "Infinity";
        String strValueOf2 = iM5828getMaxWidthimpl == Integer.MAX_VALUE ? "Infinity" : String.valueOf(iM5828getMaxWidthimpl);
        int iM5827getMaxHeightimpl = m5827getMaxHeightimpl(j);
        if (iM5827getMaxHeightimpl != Integer.MAX_VALUE) {
            strValueOf = String.valueOf(iM5827getMaxHeightimpl);
        }
        return "Constraints(minWidth = " + m5830getMinWidthimpl(j) + ", maxWidth = " + strValueOf2 + ", minHeight = " + m5829getMinHeightimpl(j) + ", maxHeight = " + strValueOf + ')';
    }

    /* JADX INFO: compiled from: Constraints.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0011\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0017\u001a\u00020\b2\u0006\u0010\u0018\u001a\u00020\bH\u0002J5\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\b2\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001e\u001a\u00020\bH\u0000ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001f\u0010 J%\u0010!\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\b2\u0006\u0010#\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010%J\u001d\u0010&\u001a\u00020\u001a2\u0006\u0010#\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b'\u0010(J\u001d\u0010)\u001a\u00020\u001a2\u0006\u0010\"\u001a\u00020\bH\u0007ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010(R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b¡\u001e0\u0001¨\u0006+"}, d2 = {"Landroidx/compose/ui/unit/Constraints$Companion;", "", "()V", "FocusMask", "", "HeightMask", "", "Infinity", "", "MaxFocusBits", "MaxFocusHeight", "MaxFocusMask", "MaxFocusWidth", "MaxNonFocusBits", "MaxNonFocusMask", "MinFocusBits", "MinFocusHeight", "MinFocusMask", "MinFocusWidth", "MinHeightOffsets", "MinNonFocusBits", "MinNonFocusMask", "WidthMask", "bitsNeedForSize", "size", "createConstraints", "Landroidx/compose/ui/unit/Constraints;", "minWidth", "maxWidth", "minHeight", "maxHeight", "createConstraints-Zbe2FdA$ui_unit_release", "(IIII)J", "fixed", "width", "height", "fixed-JhjzzOo", "(II)J", "fixedHeight", "fixedHeight-OenEA2s", "(I)J", "fixedWidth", "fixedWidth-OenEA2s", "ui-unit_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX INFO: renamed from: fixed-JhjzzOo, reason: not valid java name */
        public final long m5836fixedJhjzzOo(int width, int height) {
            if (width < 0 || height < 0) {
                throw new IllegalArgumentException(("width(" + width + ") and height(" + height + ") must be >= 0").toString());
            }
            return m5835createConstraintsZbe2FdA$ui_unit_release(width, width, height, height);
        }

        /* JADX INFO: renamed from: fixedWidth-OenEA2s, reason: not valid java name */
        public final long m5838fixedWidthOenEA2s(int width) {
            if (width < 0) {
                throw new IllegalArgumentException(("width(" + width + ") must be >= 0").toString());
            }
            return m5835createConstraintsZbe2FdA$ui_unit_release(width, width, 0, Integer.MAX_VALUE);
        }

        /* JADX INFO: renamed from: fixedHeight-OenEA2s, reason: not valid java name */
        public final long m5837fixedHeightOenEA2s(int height) {
            if (height < 0) {
                throw new IllegalArgumentException(("height(" + height + ") must be >= 0").toString());
            }
            return m5835createConstraintsZbe2FdA$ui_unit_release(0, Integer.MAX_VALUE, height, height);
        }

        /* JADX INFO: renamed from: createConstraints-Zbe2FdA$ui_unit_release, reason: not valid java name */
        public final long m5835createConstraintsZbe2FdA$ui_unit_release(int minWidth, int maxWidth, int minHeight, int maxHeight) {
            long j;
            int i = maxHeight == Integer.MAX_VALUE ? minHeight : maxHeight;
            int iBitsNeedForSize = bitsNeedForSize(i);
            int i2 = maxWidth == Integer.MAX_VALUE ? minWidth : maxWidth;
            int iBitsNeedForSize2 = bitsNeedForSize(i2);
            if (iBitsNeedForSize + iBitsNeedForSize2 <= 31) {
                if (iBitsNeedForSize2 == 13) {
                    j = 3;
                } else if (iBitsNeedForSize2 == 18) {
                    j = 1;
                } else if (iBitsNeedForSize2 == 15) {
                    j = 2;
                } else {
                    if (iBitsNeedForSize2 != 16) {
                        throw new IllegalStateException("Should only have the provided constants.");
                    }
                    j = 0;
                }
                int i3 = maxWidth == Integer.MAX_VALUE ? 0 : maxWidth + 1;
                int i4 = maxHeight != Integer.MAX_VALUE ? maxHeight + 1 : 0;
                int i5 = Constraints.MinHeightOffsets[(int) j];
                return Constraints.m5817constructorimpl((((long) i3) << 33) | j | (((long) minWidth) << 2) | (((long) minHeight) << i5) | (((long) i4) << (i5 + 31)));
            }
            throw new IllegalArgumentException("Can't represent a width of " + i2 + " and height of " + i + " in Constraints");
        }

        private final int bitsNeedForSize(int size) {
            if (size < Constraints.MaxNonFocusMask) {
                return 13;
            }
            if (size < 32767) {
                return 15;
            }
            if (size < 65535) {
                return 16;
            }
            if (size < Constraints.MaxFocusMask) {
                return 18;
            }
            throw new IllegalArgumentException("Can't represent a size of " + size + " in Constraints");
        }
    }
}
