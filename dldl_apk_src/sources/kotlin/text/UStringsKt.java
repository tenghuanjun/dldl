package kotlin.text;

import kotlin.ExperimentalUnsignedTypes;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: UStrings.kt */
/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes5.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, k = 2, mv = {1, 1, 16})
@JvmName(name = "UStringsKt")
public final class UStringsKt {
    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toString-LxnNnR4, reason: not valid java name */
    public static final String m965toStringLxnNnR4(byte b, int i) {
        String string = Integer.toString(b & UByte.MAX_VALUE, CharsKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toString-olVBNx4, reason: not valid java name */
    public static final String m967toStringolVBNx4(short s, int i) {
        String string = Integer.toString(s & UShort.MAX_VALUE, CharsKt.checkRadix(i));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toString-V7xB4Y4, reason: not valid java name */
    public static final String m966toStringV7xB4Y4(int i, int i2) {
        String string = Long.toString(((long) i) & 4294967295L, CharsKt.checkRadix(i2));
        Intrinsics.checkExpressionValueIsNotNull(string, "java.lang.Long.toString(this, checkRadix(radix))");
        return string;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @NotNull
    /* JADX INFO: renamed from: toString-JSWoG40, reason: not valid java name */
    public static final String m964toStringJSWoG40(long j, int i) {
        return UnsignedKt.ulongToString(j, CharsKt.checkRadix(i));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String toUByte) {
        Intrinsics.checkParameterIsNotNull(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt.numberFormatError(toUByte);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final byte toUByte(@NotNull String toUByte, int i) {
        Intrinsics.checkParameterIsNotNull(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte, i);
        if (uByteOrNull != null) {
            return uByteOrNull.getData();
        }
        StringsKt.numberFormatError(toUByte);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String toUShort) {
        Intrinsics.checkParameterIsNotNull(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt.numberFormatError(toUShort);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final short toUShort(@NotNull String toUShort, int i) {
        Intrinsics.checkParameterIsNotNull(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort, i);
        if (uShortOrNull != null) {
            return uShortOrNull.getData();
        }
        StringsKt.numberFormatError(toUShort);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String toUInt) {
        Intrinsics.checkParameterIsNotNull(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt.numberFormatError(toUInt);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final int toUInt(@NotNull String toUInt, int i) {
        Intrinsics.checkParameterIsNotNull(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt, i);
        if (uIntOrNull != null) {
            return uIntOrNull.getData();
        }
        StringsKt.numberFormatError(toUInt);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String toULong) {
        Intrinsics.checkParameterIsNotNull(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt.numberFormatError(toULong);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    public static final long toULong(@NotNull String toULong, int i) {
        Intrinsics.checkParameterIsNotNull(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong, i);
        if (uLongOrNull != null) {
            return uLongOrNull.getData();
        }
        StringsKt.numberFormatError(toULong);
        throw null;
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull) {
        Intrinsics.checkParameterIsNotNull(toUByteOrNull, "$this$toUByteOrNull");
        return toUByteOrNull(toUByteOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UByte toUByteOrNull(@NotNull String toUByteOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUByteOrNull, "$this$toUByteOrNull");
        UInt uIntOrNull = toUIntOrNull(toUByteOrNull, i);
        if (uIntOrNull == null) {
            return null;
        }
        int data = uIntOrNull.getData();
        if (UnsignedKt.uintCompare(data, UInt.m103constructorimpl(255)) > 0) {
            return null;
        }
        return UByte.m30boximpl(UByte.m36constructorimpl((byte) data));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull) {
        Intrinsics.checkParameterIsNotNull(toUShortOrNull, "$this$toUShortOrNull");
        return toUShortOrNull(toUShortOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UShort toUShortOrNull(@NotNull String toUShortOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUShortOrNull, "$this$toUShortOrNull");
        UInt uIntOrNull = toUIntOrNull(toUShortOrNull, i);
        if (uIntOrNull == null) {
            return null;
        }
        int data = uIntOrNull.getData();
        if (UnsignedKt.uintCompare(data, UInt.m103constructorimpl(65535)) > 0) {
            return null;
        }
        return UShort.m263boximpl(UShort.m269constructorimpl((short) data));
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull) {
        Intrinsics.checkParameterIsNotNull(toUIntOrNull, "$this$toUIntOrNull");
        return toUIntOrNull(toUIntOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final UInt toUIntOrNull(@NotNull String toUIntOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(i);
        int length = toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        int i2 = 0;
        char cCharAt = toUIntOrNull.charAt(0);
        int i3 = 1;
        if (cCharAt >= '0') {
            i3 = 0;
        } else if (length == 1 || cCharAt != '+') {
            return null;
        }
        int iM103constructorimpl = UInt.m103constructorimpl(i);
        int iM329uintDivideJ1ME1BU = 119304647;
        while (i3 < length) {
            int iDigitOf = CharsKt.digitOf(toUIntOrNull.charAt(i3), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (UnsignedKt.uintCompare(i2, iM329uintDivideJ1ME1BU) > 0) {
                if (iM329uintDivideJ1ME1BU != 119304647) {
                    return null;
                }
                iM329uintDivideJ1ME1BU = UnsignedKt.m329uintDivideJ1ME1BU(-1, iM103constructorimpl);
                if (UnsignedKt.uintCompare(i2, iM329uintDivideJ1ME1BU) > 0) {
                    return null;
                }
            }
            int iM103constructorimpl2 = UInt.m103constructorimpl(i2 * iM103constructorimpl);
            int iM103constructorimpl3 = UInt.m103constructorimpl(UInt.m103constructorimpl(iDigitOf) + iM103constructorimpl2);
            if (UnsignedKt.uintCompare(iM103constructorimpl3, iM103constructorimpl2) < 0) {
                return null;
            }
            i3++;
            i2 = iM103constructorimpl3;
        }
        return UInt.m97boximpl(i2);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull) {
        Intrinsics.checkParameterIsNotNull(toULongOrNull, "$this$toULongOrNull");
        return toULongOrNull(toULongOrNull, 10);
    }

    @SinceKotlin(version = "1.3")
    @ExperimentalUnsignedTypes
    @Nullable
    public static final ULong toULongOrNull(@NotNull String toULongOrNull, int i) {
        Intrinsics.checkParameterIsNotNull(toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(i);
        int length = toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        long j = -1;
        int i2 = 0;
        char cCharAt = toULongOrNull.charAt(0);
        if (cCharAt < '0') {
            if (length == 1 || cCharAt != '+') {
                return null;
            }
            i2 = 1;
        }
        long jM172constructorimpl = ULong.m172constructorimpl(i);
        long j2 = 0;
        long jM331ulongDivideeb3DHEI = 512409557603043100L;
        while (i2 < length) {
            int iDigitOf = CharsKt.digitOf(toULongOrNull.charAt(i2), i);
            if (iDigitOf < 0) {
                return null;
            }
            if (UnsignedKt.ulongCompare(j2, jM331ulongDivideeb3DHEI) > 0) {
                if (jM331ulongDivideeb3DHEI != 512409557603043100L) {
                    return null;
                }
                jM331ulongDivideeb3DHEI = UnsignedKt.m331ulongDivideeb3DHEI(j, jM172constructorimpl);
                if (UnsignedKt.ulongCompare(j2, jM331ulongDivideeb3DHEI) > 0) {
                    return null;
                }
            }
            long jM172constructorimpl2 = ULong.m172constructorimpl(j2 * jM172constructorimpl);
            long jM172constructorimpl3 = ULong.m172constructorimpl(ULong.m172constructorimpl(((long) UInt.m103constructorimpl(iDigitOf)) & 4294967295L) + jM172constructorimpl2);
            if (UnsignedKt.ulongCompare(jM172constructorimpl3, jM172constructorimpl2) < 0) {
                return null;
            }
            i2++;
            j2 = jM172constructorimpl3;
            j = -1;
        }
        return ULong.m166boximpl(j2);
    }
}
