package com.alibaba.fastjson.util;

import java.lang.reflect.Array;
import java.math.BigInteger;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class RyuDouble {
    private static final int[][] POW5_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 326, 4);
    private static final int[][] POW5_INV_SPLIT = (int[][]) Array.newInstance((Class<?>) int.class, 291, 4);

    static {
        BigInteger bigIntegerSubtract = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        BigInteger bigIntegerSubtract2 = BigInteger.ONE.shiftLeft(31).subtract(BigInteger.ONE);
        int i = 0;
        while (i < 326) {
            BigInteger bigIntegerPow = BigInteger.valueOf(5L).pow(i);
            int iBitLength = bigIntegerPow.bitLength();
            int i2 = i == 0 ? 1 : (int) ((((((long) i) * 23219280) + 10000000) - 1) / 10000000);
            if (i2 != iBitLength) {
                throw new IllegalStateException(iBitLength + " != " + i2);
            }
            if (i < POW5_SPLIT.length) {
                for (int i3 = 0; i3 < 4; i3++) {
                    POW5_SPLIT[i][i3] = bigIntegerPow.shiftRight((iBitLength - 121) + ((3 - i3) * 31)).and(bigIntegerSubtract).intValue();
                }
            }
            if (i < POW5_INV_SPLIT.length) {
                BigInteger bigIntegerAdd = BigInteger.ONE.shiftLeft(iBitLength + 121).divide(bigIntegerPow).add(BigInteger.ONE);
                for (int i4 = 0; i4 < 4; i4++) {
                    if (i4 == 0) {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).intValue();
                    } else {
                        POW5_INV_SPLIT[i][i4] = bigIntegerAdd.shiftRight((3 - i4) * 31).and(bigIntegerSubtract2).intValue();
                    }
                }
            }
            i++;
        }
    }

    public static String toString(double d) {
        char[] cArr = new char[24];
        return new String(cArr, 0, toString(d, cArr, 0));
    }

    public static int toString(double d, char[] cArr, int i) {
        int i2;
        boolean z;
        boolean z2;
        long j;
        long j2;
        int i3;
        long j3;
        boolean z3;
        boolean z4;
        long j4;
        int i4;
        int i5;
        int i6;
        int i7;
        int i8;
        boolean z5;
        int i9;
        int i10;
        int i11;
        int i12;
        if (!Double.isNaN(d)) {
            if (d == Double.POSITIVE_INFINITY) {
                int i13 = i + 1;
                cArr[i] = 'I';
                int i14 = i13 + 1;
                cArr[i13] = 'n';
                int i15 = i14 + 1;
                cArr[i14] = 'f';
                int i16 = i15 + 1;
                cArr[i15] = 'i';
                int i17 = i16 + 1;
                cArr[i16] = 'n';
                int i18 = i17 + 1;
                cArr[i17] = 'i';
                int i19 = i18 + 1;
                cArr[i18] = 't';
                i8 = i19 + 1;
                cArr[i19] = 'y';
            } else if (d == Double.NEGATIVE_INFINITY) {
                int i20 = i + 1;
                cArr[i] = '-';
                int i21 = i20 + 1;
                cArr[i20] = 'I';
                int i22 = i21 + 1;
                cArr[i21] = 'n';
                int i23 = i22 + 1;
                cArr[i22] = 'f';
                int i24 = i23 + 1;
                cArr[i23] = 'i';
                int i25 = i24 + 1;
                cArr[i24] = 'n';
                int i26 = i25 + 1;
                cArr[i25] = 'i';
                int i27 = i26 + 1;
                cArr[i26] = 't';
                i12 = i27 + 1;
                cArr[i27] = 'y';
            } else {
                long jDoubleToLongBits = Double.doubleToLongBits(d);
                if (jDoubleToLongBits == 0) {
                    int i28 = i + 1;
                    cArr[i] = '0';
                    int i29 = i28 + 1;
                    cArr[i28] = '.';
                    i12 = i29 + 1;
                    cArr[i29] = '0';
                } else if (jDoubleToLongBits == Long.MIN_VALUE) {
                    int i30 = i + 1;
                    cArr[i] = '-';
                    int i31 = i30 + 1;
                    cArr[i30] = '0';
                    int i32 = i31 + 1;
                    cArr[i31] = '.';
                    i8 = i32 + 1;
                    cArr[i32] = '0';
                } else {
                    int i33 = (int) ((jDoubleToLongBits >>> 52) & 2047);
                    long j5 = jDoubleToLongBits & 4503599627370495L;
                    if (i33 == 0) {
                        i2 = -1074;
                    } else {
                        i2 = (i33 - 1023) - 52;
                        j5 |= 4503599627370496L;
                    }
                    boolean z6 = jDoubleToLongBits < 0;
                    boolean z7 = (j5 & 1) == 0;
                    long j6 = 4 * j5;
                    long j7 = j6 + 2;
                    int i34 = (j5 != 4503599627370496L || i33 <= 1) ? 1 : 0;
                    long j8 = (j6 - 1) - ((long) i34);
                    int i35 = i2 - 2;
                    int i36 = 3;
                    if (i35 >= 0) {
                        int iMax = Math.max(0, ((int) ((((long) i35) * 3010299) / 10000000)) - 1);
                        int i37 = ((((-i35) + iMax) + (((iMax == 0 ? 1 : (int) ((((((long) iMax) * 23219280) + 10000000) - 1) / 10000000)) + 122) - 1)) - 93) - 21;
                        if (i37 < 0) {
                            throw new IllegalArgumentException("" + i37);
                        }
                        int[] iArr = POW5_INV_SPLIT[iMax];
                        long j9 = j6 >>> 31;
                        long j10 = j6 & 2147483647L;
                        z = z6;
                        long j11 = ((long) iArr[0]) * j9;
                        boolean z8 = z7;
                        long j12 = ((((((((((((j10 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j10)) + (j9 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j10)) + (((long) iArr[2]) * j9)) >>> 31) + (((long) iArr[0]) * j10)) + (((long) iArr[1]) * j9)) >>> 21) + (j11 << 10)) >>> i37;
                        long j13 = j7 >>> 31;
                        long j14 = j7 & 2147483647L;
                        long j15 = ((long) iArr[0]) * j13;
                        z2 = z8;
                        long j16 = ((((((((((((j14 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j14)) + (j13 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j14)) + (((long) iArr[2]) * j13)) >>> 31) + (((long) iArr[0]) * j14)) + (((long) iArr[1]) * j13)) >>> 21) + (j15 << 10)) >>> i37;
                        long j17 = j8 >>> 31;
                        long j18 = j8 & 2147483647L;
                        long j19 = ((long) iArr[0]) * j17;
                        j3 = j16;
                        j2 = ((((((((((((j18 * ((long) iArr[3])) >>> 31) + (((long) iArr[2]) * j18)) + (j17 * ((long) iArr[3]))) >>> 31) + (((long) iArr[1]) * j18)) + (((long) iArr[2]) * j17)) >>> 31) + (((long) iArr[0]) * j18)) + (((long) iArr[1]) * j17)) >>> 21) + (j19 << 10)) >>> i37;
                        if (iMax <= 21) {
                            long j20 = j6 % 5;
                            if (j20 == 0) {
                                if (j20 != 0) {
                                    i11 = 0;
                                } else if (j6 % 25 != 0) {
                                    i11 = 1;
                                } else if (j6 % 125 != 0) {
                                    i11 = 2;
                                } else if (j6 % 625 != 0) {
                                    i11 = 3;
                                } else {
                                    long j21 = j6 / 625;
                                    i11 = 4;
                                    for (long j22 = 0; j21 > j22 && j21 % 5 == j22; j22 = 0) {
                                        j21 /= 5;
                                        i11++;
                                    }
                                }
                                z4 = i11 >= iMax;
                                z5 = false;
                                j = j12;
                                z3 = z5;
                                i3 = iMax;
                            } else {
                                if (z2) {
                                    if (j8 % 5 != 0) {
                                        i10 = 0;
                                    } else if (j8 % 25 != 0) {
                                        i10 = 1;
                                    } else if (j8 % 125 != 0) {
                                        i10 = 2;
                                    } else if (j8 % 625 != 0) {
                                        i10 = 3;
                                    } else {
                                        long j23 = j8 / 625;
                                        i10 = 4;
                                        for (long j24 = 0; j23 > j24 && j23 % 5 == j24; j24 = 0) {
                                            j23 /= 5;
                                            i10++;
                                        }
                                    }
                                    z5 = i10 >= iMax;
                                    z4 = false;
                                    j = j12;
                                    z3 = z5;
                                    i3 = iMax;
                                } else {
                                    if (j7 % 5 != 0) {
                                        i9 = 0;
                                    } else if (j7 % 25 != 0) {
                                        i9 = 1;
                                    } else if (j7 % 125 != 0) {
                                        i9 = 2;
                                    } else if (j7 % 625 != 0) {
                                        i9 = 3;
                                    } else {
                                        long j25 = j7 / 625;
                                        i9 = 4;
                                        for (long j26 = 0; j25 > j26 && j25 % 5 == j26; j26 = 0) {
                                            j25 /= 5;
                                            i9++;
                                        }
                                    }
                                    if (i9 >= iMax) {
                                        j3--;
                                    }
                                }
                                z4 = false;
                                j = j12;
                                z3 = z5;
                                i3 = iMax;
                            }
                        } else {
                            z4 = false;
                            j = j12;
                            z3 = z5;
                            i3 = iMax;
                        }
                    } else {
                        z = z6;
                        z2 = z7;
                        int i38 = -i35;
                        int iMax2 = Math.max(0, ((int) ((((long) i38) * 6989700) / 10000000)) - 1);
                        int i39 = i38 - iMax2;
                        int i40 = ((iMax2 - ((i39 == 0 ? 1 : (int) ((((((long) i39) * 23219280) + 10000000) - 1) / 10000000)) - 121)) - 93) - 21;
                        if (i40 < 0) {
                            throw new IllegalArgumentException("" + i40);
                        }
                        int[] iArr2 = POW5_SPLIT[i39];
                        long j27 = j6 >>> 31;
                        long j28 = j6 & 2147483647L;
                        long j29 = ((long) iArr2[0]) * j27;
                        int i41 = i34;
                        long j30 = ((((((((((((j28 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j28)) + (j27 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j28)) + (((long) iArr2[2]) * j27)) >>> 31) + (((long) iArr2[0]) * j28)) + (((long) iArr2[1]) * j27)) >>> 21) + (j29 << 10)) >>> i40;
                        long j31 = j7 >>> 31;
                        long j32 = j7 & 2147483647L;
                        long j33 = ((long) iArr2[0]) * j31;
                        j = j30;
                        long j34 = ((((((((((((j32 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j32)) + (j31 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j32)) + (((long) iArr2[2]) * j31)) >>> 31) + (((long) iArr2[0]) * j32)) + (((long) iArr2[1]) * j31)) >>> 21) + (j33 << 10)) >>> i40;
                        long j35 = j8 >>> 31;
                        long j36 = j8 & 2147483647L;
                        long j37 = ((long) iArr2[0]) * j35;
                        j2 = ((((((((((((j36 * ((long) iArr2[3])) >>> 31) + (((long) iArr2[2]) * j36)) + (j35 * ((long) iArr2[3]))) >>> 31) + (((long) iArr2[1]) * j36)) + (((long) iArr2[2]) * j35)) >>> 31) + (((long) iArr2[0]) * j36)) + (((long) iArr2[1]) * j35)) >>> 21) + (j37 << 10)) >>> i40;
                        i3 = iMax2 + i35;
                        if (iMax2 <= 1) {
                            if (z2) {
                                j3 = j34;
                                z3 = i41 == 1;
                            } else {
                                j3 = j34 - 1;
                                z3 = false;
                            }
                            z4 = true;
                        } else if (iMax2 < 63) {
                            z4 = (j6 & ((1 << (iMax2 - 1)) - 1)) == 0;
                            j3 = j34;
                            z3 = false;
                        } else {
                            j3 = j34;
                            z3 = false;
                            z4 = false;
                        }
                    }
                    if (j3 >= 1000000000000000000L) {
                        i36 = 19;
                    } else if (j3 >= 100000000000000000L) {
                        i36 = 18;
                    } else if (j3 >= 10000000000000000L) {
                        i36 = 17;
                    } else if (j3 >= 1000000000000000L) {
                        i36 = 16;
                    } else if (j3 >= 100000000000000L) {
                        i36 = 15;
                    } else if (j3 >= 10000000000000L) {
                        i36 = 14;
                    } else if (j3 >= 1000000000000L) {
                        i36 = 13;
                    } else if (j3 >= 100000000000L) {
                        i36 = 12;
                    } else if (j3 >= 10000000000L) {
                        i36 = 11;
                    } else if (j3 >= 1000000000) {
                        i36 = 10;
                    } else if (j3 >= 100000000) {
                        i36 = 9;
                    } else if (j3 >= 10000000) {
                        i36 = 8;
                    } else if (j3 >= 1000000) {
                        i36 = 7;
                    } else if (j3 >= 100000) {
                        i36 = 6;
                    } else if (j3 >= 10000) {
                        i36 = 5;
                    } else if (j3 >= 1000) {
                        i36 = 4;
                    } else if (j3 < 100) {
                        i36 = j3 >= 10 ? 2 : 1;
                    }
                    int i42 = (i3 + i36) - 1;
                    boolean z9 = i42 < -3 || i42 >= 7;
                    if (z3 || z4) {
                        boolean z10 = z4;
                        int i43 = 0;
                        int i44 = 0;
                        while (true) {
                            long j38 = j3 / 10;
                            long j39 = j2 / 10;
                            if (j38 <= j39 || (j3 < 100 && z9)) {
                                break;
                            }
                            z3 &= j2 % 10 == 0;
                            z10 &= i43 == 0;
                            i43 = (int) (j % 10);
                            j /= 10;
                            i44++;
                            j3 = j38;
                            j2 = j39;
                        }
                        if (z3 && z2) {
                            while (j2 % 10 == 0 && (j3 >= 100 || !z9)) {
                                z10 &= i43 == 0;
                                i43 = (int) (j % 10);
                                j3 /= 10;
                                j /= 10;
                                j2 /= 10;
                                i44++;
                            }
                        }
                        if (z10 && i43 == 5 && j % 2 == 0) {
                            i43 = 4;
                        }
                        j4 = j + ((long) (((j != j2 || (z3 && z2)) && i43 < 5) ? 0 : 1));
                        i4 = i44;
                    } else {
                        i4 = 0;
                        int i45 = 0;
                        while (true) {
                            long j40 = j3 / 10;
                            long j41 = j2 / 10;
                            if (j40 <= j41 || (j3 < 100 && z9)) {
                                break;
                            }
                            i45 = (int) (j % 10);
                            j /= 10;
                            i4++;
                            j3 = j40;
                            j2 = j41;
                        }
                        j4 = j + ((long) ((j == j2 || i45 >= 5) ? 1 : 0));
                    }
                    int i46 = i36 - i4;
                    if (z) {
                        i5 = i + 1;
                        cArr[i] = '-';
                    } else {
                        i5 = i;
                    }
                    if (!z9) {
                        char c = '0';
                        if (i42 < 0) {
                            int i47 = i5 + 1;
                            cArr[i5] = '0';
                            int i48 = i47 + 1;
                            cArr[i47] = '.';
                            int i49 = -1;
                            while (i49 > i42) {
                                cArr[i48] = c;
                                i49--;
                                i48++;
                                c = '0';
                            }
                            i6 = i48;
                            for (int i50 = 0; i50 < i46; i50++) {
                                cArr[((i48 + i46) - i50) - 1] = (char) ((j4 % 10) + 48);
                                j4 /= 10;
                                i6++;
                            }
                        } else {
                            int i51 = i42 + 1;
                            if (i51 >= i46) {
                                for (int i52 = 0; i52 < i46; i52++) {
                                    cArr[((i5 + i46) - i52) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                int i53 = i5 + i46;
                                while (i46 < i51) {
                                    cArr[i53] = '0';
                                    i46++;
                                    i53++;
                                }
                                int i54 = i53 + 1;
                                cArr[i53] = '.';
                                i6 = i54 + 1;
                                cArr[i54] = '0';
                            } else {
                                int i55 = i5 + 1;
                                for (int i56 = 0; i56 < i46; i56++) {
                                    if ((i46 - i56) - 1 == i42) {
                                        cArr[((i55 + i46) - i56) - 1] = '.';
                                        i55--;
                                    }
                                    cArr[((i55 + i46) - i56) - 1] = (char) ((j4 % 10) + 48);
                                    j4 /= 10;
                                }
                                i6 = i5 + i46 + 1;
                            }
                        }
                        return i6 - i;
                    }
                    for (int i57 = 0; i57 < i46 - 1; i57++) {
                        int i58 = (int) (j4 % 10);
                        j4 /= 10;
                        cArr[(i5 + i46) - i57] = (char) (i58 + 48);
                    }
                    cArr[i5] = (char) ((j4 % 10) + 48);
                    cArr[i5 + 1] = '.';
                    int i59 = i5 + i46 + 1;
                    if (i46 == 1) {
                        cArr[i59] = '0';
                        i59++;
                    }
                    int i60 = i59 + 1;
                    cArr[i59] = 'E';
                    if (i42 < 0) {
                        cArr[i60] = '-';
                        i42 = -i42;
                        i60++;
                    }
                    if (i42 >= 100) {
                        int i61 = i60 + 1;
                        i7 = 48;
                        cArr[i60] = (char) ((i42 / 100) + 48);
                        i42 %= 100;
                        i60 = i61 + 1;
                        cArr[i61] = (char) ((i42 / 10) + 48);
                    } else {
                        i7 = 48;
                        if (i42 >= 10) {
                            cArr[i60] = (char) ((i42 / 10) + 48);
                            i60++;
                        }
                    }
                    i8 = i60 + 1;
                    cArr[i60] = (char) ((i42 % 10) + i7);
                }
            }
            return i8 - i;
        }
        int i62 = i + 1;
        cArr[i] = 'N';
        int i63 = i62 + 1;
        cArr[i62] = 'a';
        i12 = i63 + 1;
        cArr[i63] = 'N';
        return i12 - i;
    }
}
