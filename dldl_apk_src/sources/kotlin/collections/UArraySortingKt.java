package kotlin.collections;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShort;
import kotlin.UShortArray;
import kotlin.UnsignedKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: UArraySorting.kt */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes3.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0012\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0006\u0010\u0007\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\t\u0010\n\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\f\u0010\r\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0010\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0015\u0010\u0016\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000b2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0017\u0010\u0018\u001a*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0001H\u0003ø\u0001\u0000¢\u0006\u0004\b\u0019\u0010\u001a\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u0003H\u0001ø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001d\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\bH\u0001ø\u0001\u0000¢\u0006\u0004\b\u001e\u0010\u001f\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000bH\u0001ø\u0001\u0000¢\u0006\u0004\b \u0010!\u001a\u001a\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0002\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\"\u0010#\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"partition", "", "array", "Lkotlin/UByteArray;", "left", "right", "partition-4UcCI2c", "([BII)I", "Lkotlin/UIntArray;", "partition-oBK06Vg", "([III)I", "Lkotlin/ULongArray;", "partition--nroSd4", "([JII)I", "Lkotlin/UShortArray;", "partition-Aa5vz7o", "([SII)I", "quickSort", "", "quickSort-4UcCI2c", "([BII)V", "quickSort-oBK06Vg", "([III)V", "quickSort--nroSd4", "([JII)V", "quickSort-Aa5vz7o", "([SII)V", "sortArray", "sortArray-GBYM_sE", "([B)V", "sortArray--ajY-9A", "([I)V", "sortArray-QwZRm1k", "([J)V", "sortArray-rL5Bavg", "([S)V", "kotlin-stdlib"}, k = 2, mv = {1, 1, 16})
public final class UArraySortingKt {
    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-4UcCI2c, reason: not valid java name */
    private static final int m338partition4UcCI2c(byte[] bArr, int i, int i2) {
        int i3;
        byte bM85getimpl = UByteArray.m85getimpl(bArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM85getimpl = UByteArray.m85getimpl(bArr, i) & UByte.MAX_VALUE;
                i3 = bM85getimpl & UByte.MAX_VALUE;
                if (Intrinsics.compare(iM85getimpl, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UByteArray.m85getimpl(bArr, i2) & UByte.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                byte bM85getimpl2 = UByteArray.m85getimpl(bArr, i);
                UByteArray.m90setVurrAj0(bArr, i, UByteArray.m85getimpl(bArr, i2));
                UByteArray.m90setVurrAj0(bArr, i2, bM85getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-4UcCI2c, reason: not valid java name */
    private static final void m342quickSort4UcCI2c(byte[] bArr, int i, int i2) {
        int iM338partition4UcCI2c = m338partition4UcCI2c(bArr, i, i2);
        int i3 = iM338partition4UcCI2c - 1;
        if (i < i3) {
            m342quickSort4UcCI2c(bArr, i, i3);
        }
        if (iM338partition4UcCI2c < i2) {
            m342quickSort4UcCI2c(bArr, iM338partition4UcCI2c, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-Aa5vz7o, reason: not valid java name */
    private static final int m339partitionAa5vz7o(short[] sArr, int i, int i2) {
        int i3;
        short sM318getimpl = UShortArray.m318getimpl(sArr, (i + i2) / 2);
        while (i <= i2) {
            while (true) {
                int iM318getimpl = UShortArray.m318getimpl(sArr, i) & UShort.MAX_VALUE;
                i3 = sM318getimpl & UShort.MAX_VALUE;
                if (Intrinsics.compare(iM318getimpl, i3) >= 0) {
                    break;
                }
                i++;
            }
            while (Intrinsics.compare(UShortArray.m318getimpl(sArr, i2) & UShort.MAX_VALUE, i3) > 0) {
                i2--;
            }
            if (i <= i2) {
                short sM318getimpl2 = UShortArray.m318getimpl(sArr, i);
                UShortArray.m323set01HTLdE(sArr, i, UShortArray.m318getimpl(sArr, i2));
                UShortArray.m323set01HTLdE(sArr, i2, sM318getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-Aa5vz7o, reason: not valid java name */
    private static final void m343quickSortAa5vz7o(short[] sArr, int i, int i2) {
        int iM339partitionAa5vz7o = m339partitionAa5vz7o(sArr, i, i2);
        int i3 = iM339partitionAa5vz7o - 1;
        if (i < i3) {
            m343quickSortAa5vz7o(sArr, i, i3);
        }
        if (iM339partitionAa5vz7o < i2) {
            m343quickSortAa5vz7o(sArr, iM339partitionAa5vz7o, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition-oBK06Vg, reason: not valid java name */
    private static final int m340partitionoBK06Vg(int[] iArr, int i, int i2) {
        int iM154getimpl = UIntArray.m154getimpl(iArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.uintCompare(UIntArray.m154getimpl(iArr, i), iM154getimpl) < 0) {
                i++;
            }
            while (UnsignedKt.uintCompare(UIntArray.m154getimpl(iArr, i2), iM154getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                int iM154getimpl2 = UIntArray.m154getimpl(iArr, i);
                UIntArray.m159setVXSXFK8(iArr, i, UIntArray.m154getimpl(iArr, i2));
                UIntArray.m159setVXSXFK8(iArr, i2, iM154getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort-oBK06Vg, reason: not valid java name */
    private static final void m344quickSortoBK06Vg(int[] iArr, int i, int i2) {
        int iM340partitionoBK06Vg = m340partitionoBK06Vg(iArr, i, i2);
        int i3 = iM340partitionoBK06Vg - 1;
        if (i < i3) {
            m344quickSortoBK06Vg(iArr, i, i3);
        }
        if (iM340partitionoBK06Vg < i2) {
            m344quickSortoBK06Vg(iArr, iM340partitionoBK06Vg, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: partition--nroSd4, reason: not valid java name */
    private static final int m337partitionnroSd4(long[] jArr, int i, int i2) {
        long jM223getimpl = ULongArray.m223getimpl(jArr, (i + i2) / 2);
        while (i <= i2) {
            while (UnsignedKt.ulongCompare(ULongArray.m223getimpl(jArr, i), jM223getimpl) < 0) {
                i++;
            }
            while (UnsignedKt.ulongCompare(ULongArray.m223getimpl(jArr, i2), jM223getimpl) > 0) {
                i2--;
            }
            if (i <= i2) {
                long jM223getimpl2 = ULongArray.m223getimpl(jArr, i);
                ULongArray.m228setk8EXiF4(jArr, i, ULongArray.m223getimpl(jArr, i2));
                ULongArray.m228setk8EXiF4(jArr, i2, jM223getimpl2);
                i++;
                i2--;
            }
        }
        return i;
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: quickSort--nroSd4, reason: not valid java name */
    private static final void m341quickSortnroSd4(long[] jArr, int i, int i2) {
        int iM337partitionnroSd4 = m337partitionnroSd4(jArr, i, i2);
        int i3 = iM337partitionnroSd4 - 1;
        if (i < i3) {
            m341quickSortnroSd4(jArr, i, i3);
        }
        if (iM337partitionnroSd4 < i2) {
            m341quickSortnroSd4(jArr, iM337partitionnroSd4, i2);
        }
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-GBYM_sE, reason: not valid java name */
    public static final void m346sortArrayGBYM_sE(@NotNull byte[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m342quickSort4UcCI2c(array, 0, UByteArray.m86getSizeimpl(array) - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-rL5Bavg, reason: not valid java name */
    public static final void m348sortArrayrL5Bavg(@NotNull short[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m343quickSortAa5vz7o(array, 0, UShortArray.m319getSizeimpl(array) - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray--ajY-9A, reason: not valid java name */
    public static final void m345sortArrayajY9A(@NotNull int[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m344quickSortoBK06Vg(array, 0, UIntArray.m155getSizeimpl(array) - 1);
    }

    @ExperimentalUnsignedTypes
    /* JADX INFO: renamed from: sortArray-QwZRm1k, reason: not valid java name */
    public static final void m347sortArrayQwZRm1k(@NotNull long[] array) {
        Intrinsics.checkParameterIsNotNull(array, "array");
        m341quickSortnroSd4(array, 0, ULongArray.m224getSizeimpl(array) - 1);
    }
}
