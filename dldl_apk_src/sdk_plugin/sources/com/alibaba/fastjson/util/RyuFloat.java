package com.alibaba.fastjson.util;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class RyuFloat {
    private static final int[][] POW5_SPLIT = {new int[]{536870912, 0}, new int[]{671088640, 0}, new int[]{838860800, 0}, new int[]{1048576000, 0}, new int[]{655360000, 0}, new int[]{819200000, 0}, new int[]{1024000000, 0}, new int[]{640000000, 0}, new int[]{800000000, 0}, new int[]{1000000000, 0}, new int[]{625000000, 0}, new int[]{781250000, 0}, new int[]{976562500, 0}, new int[]{610351562, 1073741824}, new int[]{762939453, 268435456}, new int[]{953674316, 872415232}, new int[]{596046447, 1619001344}, new int[]{745058059, 1486880768}, new int[]{931322574, 1321730048}, new int[]{582076609, 289210368}, new int[]{727595761, 898383872}, new int[]{909494701, 1659850752}, new int[]{568434188, 1305842176}, new int[]{710542735, 1632302720}, new int[]{888178419, 1503507488}, new int[]{555111512, 671256724}, new int[]{693889390, 839070905}, new int[]{867361737, 2122580455}, new int[]{542101086, 521306416}, new int[]{677626357, 1725374844}, new int[]{847032947, 546105819}, new int[]{1058791184, 145761362}, new int[]{661744490, 91100851}, new int[]{827180612, 1187617888}, new int[]{1033975765, 1484522360}, new int[]{646234853, 1196261931}, new int[]{807793566, 2032198326}, new int[]{1009741958, 1466506084}, new int[]{631088724, 379695390}, new int[]{788860905, 474619238}, new int[]{986076131, 1130144959}, new int[]{616297582, 437905143}, new int[]{770371977, 1621123253}, new int[]{962964972, 415791331}, new int[]{601853107, 1333611405}, new int[]{752316384, 1130143345}, new int[]{940395480, 1412679181}};
    private static final int[][] POW5_INV_SPLIT = {new int[]{268435456, 1}, new int[]{214748364, 1717986919}, new int[]{171798691, 1803886265}, new int[]{137438953, 1013612282}, new int[]{219902325, 1192282922}, new int[]{175921860, 953826338}, new int[]{140737488, 763061070}, new int[]{225179981, 791400982}, new int[]{180143985, 203624056}, new int[]{144115188, 162899245}, new int[]{230584300, 1978625710}, new int[]{184467440, 1582900568}, new int[]{147573952, 1266320455}, new int[]{236118324, 308125809}, new int[]{188894659, 675997377}, new int[]{151115727, 970294631}, new int[]{241785163, 1981968139}, new int[]{193428131, 297084323}, new int[]{154742504, 1955654377}, new int[]{247588007, 1840556814}, new int[]{198070406, 613451992}, new int[]{158456325, 61264864}, new int[]{253530120, 98023782}, new int[]{202824096, 78419026}, new int[]{162259276, 1780722139}, new int[]{259614842, 1990161963}, new int[]{207691874, 733136111}, new int[]{166153499, 1016005619}, new int[]{265845599, 337118801}, new int[]{212676479, 699191770}, new int[]{170141183, 988850146}};

    public static String toString(float f) {
        char[] cArr = new char[15];
        return new String(cArr, 0, toString(f, cArr, 0));
    }

    public static int toString(float f, char[] cArr, int i) {
        int i2;
        boolean z;
        int i3;
        int i4;
        int i5;
        boolean z2;
        int i6;
        int i7;
        boolean z3;
        int i8;
        boolean z4;
        int i9;
        int i10;
        int i11;
        int i12;
        if (Float.isNaN(f)) {
            int i13 = i + 1;
            cArr[i] = 'N';
            int i14 = i13 + 1;
            cArr[i13] = 'a';
            i12 = i14 + 1;
            cArr[i14] = 'N';
        } else {
            if (f == Float.POSITIVE_INFINITY) {
                int i15 = i + 1;
                cArr[i] = 'I';
                int i16 = i15 + 1;
                cArr[i15] = 'n';
                int i17 = i16 + 1;
                cArr[i16] = 'f';
                int i18 = i17 + 1;
                cArr[i17] = 'i';
                int i19 = i18 + 1;
                cArr[i18] = 'n';
                int i20 = i19 + 1;
                cArr[i19] = 'i';
                int i21 = i20 + 1;
                cArr[i20] = 't';
                cArr[i21] = 'y';
                return (i21 + 1) - i;
            }
            if (f == Float.NEGATIVE_INFINITY) {
                int i22 = i + 1;
                cArr[i] = '-';
                int i23 = i22 + 1;
                cArr[i22] = 'I';
                int i24 = i23 + 1;
                cArr[i23] = 'n';
                int i25 = i24 + 1;
                cArr[i24] = 'f';
                int i26 = i25 + 1;
                cArr[i25] = 'i';
                int i27 = i26 + 1;
                cArr[i26] = 'n';
                int i28 = i27 + 1;
                cArr[i27] = 'i';
                int i29 = i28 + 1;
                cArr[i28] = 't';
                i12 = i29 + 1;
                cArr[i29] = 'y';
            } else {
                int iFloatToIntBits = Float.floatToIntBits(f);
                if (iFloatToIntBits != 0) {
                    if (iFloatToIntBits == Integer.MIN_VALUE) {
                        int i30 = i + 1;
                        cArr[i] = '-';
                        int i31 = i30 + 1;
                        cArr[i30] = '0';
                        int i32 = i31 + 1;
                        cArr[i31] = '.';
                        cArr[i32] = '0';
                        return (i32 + 1) - i;
                    }
                    int i33 = (iFloatToIntBits >> 23) & 255;
                    int i34 = 8388607 & iFloatToIntBits;
                    if (i33 == 0) {
                        i2 = -149;
                    } else {
                        i2 = (i33 - 127) - 23;
                        i34 |= 8388608;
                    }
                    boolean z5 = iFloatToIntBits < 0;
                    boolean z6 = (i34 & 1) == 0;
                    int i35 = i34 * 4;
                    int i36 = i35 + 2;
                    int i37 = i35 - ((((long) i34) != 8388608 || i33 <= 1) ? 2 : 1);
                    int i38 = i2 - 2;
                    if (i38 >= 0) {
                        i5 = (int) ((((long) i38) * 3010299) / 10000000);
                        int i39 = i5 == 0 ? 1 : (int) ((((((long) i5) * 23219280) + 10000000) - 1) / 10000000);
                        int i40 = (-i38) + i5;
                        int[][] iArr = POW5_INV_SPLIT;
                        long j = iArr[i5][0];
                        z = z6;
                        long j2 = iArr[i5][1];
                        long j3 = i35;
                        int i41 = (((i39 + 59) - 1) + i40) - 31;
                        i6 = (int) (((j3 * j) + ((j3 * j2) >> 31)) >> i41);
                        long j4 = i36;
                        i7 = (int) (((j4 * j) + ((j4 * j2) >> 31)) >> i41);
                        long j5 = i37;
                        i8 = (int) (((j * j5) + ((j5 * j2) >> 31)) >> i41);
                        if (i5 == 0 || (i7 - 1) / 10 > i8 / 10) {
                            i4 = 0;
                        } else {
                            int i42 = i5 - 1;
                            int i43 = (i40 - 1) + (((i42 == 0 ? 1 : (int) ((((((long) i42) * 23219280) + 10000000) - 1) / 10000000)) + 59) - 1);
                            int[][] iArr2 = POW5_INV_SPLIT;
                            i4 = (int) ((((((long) iArr2[i42][0]) * j3) + ((((long) iArr2[i42][1]) * j3) >> 31)) >> (i43 - 31)) % 10);
                        }
                        int i44 = 0;
                        while (i36 > 0 && i36 % 5 == 0) {
                            i36 /= 5;
                            i44++;
                        }
                        int i45 = 0;
                        while (i35 > 0 && i35 % 5 == 0) {
                            i35 /= 5;
                            i45++;
                        }
                        int i46 = 0;
                        while (i37 > 0 && i37 % 5 == 0) {
                            i37 /= 5;
                            i46++;
                        }
                        z2 = i44 >= i5;
                        z4 = i45 >= i5;
                        z3 = i46 >= i5;
                    } else {
                        z = z6;
                        int i47 = -i38;
                        int i48 = (int) ((((long) i47) * 6989700) / 10000000);
                        int i49 = i47 - i48;
                        int[][] iArr3 = POW5_SPLIT;
                        long j6 = iArr3[i49][0];
                        long j7 = iArr3[i49][1];
                        int i50 = (i48 - ((i49 == 0 ? 1 : (int) ((((((long) i49) * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31;
                        long j8 = i35;
                        int i51 = (int) (((j8 * j6) + ((j8 * j7) >> 31)) >> i50);
                        long j9 = i36;
                        int i52 = (int) (((j9 * j6) + ((j9 * j7) >> 31)) >> i50);
                        long j10 = i37;
                        int i53 = (int) (((j10 * j6) + ((j10 * j7) >> 31)) >> i50);
                        if (i48 == 0 || (i52 - 1) / 10 > i53 / 10) {
                            i3 = i52;
                            i4 = 0;
                        } else {
                            int i54 = i49 + 1;
                            int[][] iArr4 = POW5_SPLIT;
                            i3 = i52;
                            i4 = (int) ((((((long) iArr4[i54][0]) * j8) + ((j8 * ((long) iArr4[i54][1])) >> 31)) >> (((i48 - 1) - ((i54 == 0 ? 1 : (int) ((((((long) i54) * 23219280) + 10000000) - 1) / 10000000)) - 61)) - 31)) % 10);
                        }
                        i5 = i48 + i38;
                        boolean z7 = 1 >= i48;
                        boolean z8 = i48 < 23 && (i35 & ((1 << (i48 + (-1))) - 1)) == 0;
                        boolean z9 = (i37 % 2 == 1 ? 0 : 1) >= i48;
                        z2 = z7;
                        i6 = i51;
                        i7 = i3;
                        boolean z10 = z8;
                        z3 = z9;
                        i8 = i53;
                        z4 = z10;
                    }
                    int i55 = 1000000000;
                    int i56 = 10;
                    while (i56 > 0 && i7 < i55) {
                        i55 /= 10;
                        i56--;
                    }
                    int i57 = (i5 + i56) - 1;
                    boolean z11 = i57 < -3 || i57 >= 7;
                    if (z2 && !z) {
                        i7--;
                    }
                    int i58 = 0;
                    while (true) {
                        int i59 = i7 / 10;
                        int i60 = i8 / 10;
                        if (i59 <= i60 || (i7 < 100 && z11)) {
                            break;
                        }
                        z3 &= i8 % 10 == 0;
                        i4 = i6 % 10;
                        i6 /= 10;
                        i58++;
                        i7 = i59;
                        i8 = i60;
                    }
                    if (z3 && z) {
                        while (i8 % 10 == 0 && (i7 >= 100 || !z11)) {
                            i7 /= 10;
                            i4 = i6 % 10;
                            i6 /= 10;
                            i8 /= 10;
                            i58++;
                        }
                    }
                    if (z4 && i4 == 5 && i6 % 2 == 0) {
                        i4 = 4;
                    }
                    int i61 = i6 + (((i6 != i8 || (z3 && z)) && i4 < 5) ? 0 : 1);
                    int i62 = i56 - i58;
                    if (z5) {
                        i9 = i + 1;
                        cArr[i] = '-';
                    } else {
                        i9 = i;
                    }
                    if (z11) {
                        for (int i63 = 0; i63 < i62 - 1; i63++) {
                            int i64 = i61 % 10;
                            i61 /= 10;
                            cArr[(i9 + i62) - i63] = (char) (i64 + 48);
                        }
                        cArr[i9] = (char) ((i61 % 10) + 48);
                        cArr[i9 + 1] = '.';
                        int i65 = i9 + i62 + 1;
                        if (i62 == 1) {
                            cArr[i65] = '0';
                            i65++;
                        }
                        int i66 = i65 + 1;
                        cArr[i65] = 'E';
                        if (i57 < 0) {
                            cArr[i66] = '-';
                            i57 = -i57;
                            i66++;
                        }
                        if (i57 >= 10) {
                            i11 = 48;
                            cArr[i66] = (char) ((i57 / 10) + 48);
                            i66++;
                        } else {
                            i11 = 48;
                        }
                        i10 = i66 + 1;
                        cArr[i66] = (char) ((i57 % 10) + i11);
                    } else {
                        int i67 = 48;
                        if (i57 < 0) {
                            int i68 = i9 + 1;
                            cArr[i9] = '0';
                            int i69 = i68 + 1;
                            cArr[i68] = '.';
                            int i70 = -1;
                            while (i70 > i57) {
                                cArr[i69] = '0';
                                i70--;
                                i69++;
                            }
                            int i71 = i69;
                            int i72 = 0;
                            while (i72 < i62) {
                                cArr[((i69 + i62) - i72) - 1] = (char) ((i61 % 10) + i67);
                                i61 /= 10;
                                i71++;
                                i72++;
                                i67 = 48;
                            }
                            i10 = i71;
                        } else {
                            int i73 = i57 + 1;
                            if (i73 >= i62) {
                                for (int i74 = 0; i74 < i62; i74++) {
                                    cArr[((i9 + i62) - i74) - 1] = (char) ((i61 % 10) + 48);
                                    i61 /= 10;
                                }
                                int i75 = i9 + i62;
                                while (i62 < i73) {
                                    cArr[i75] = '0';
                                    i62++;
                                    i75++;
                                }
                                int i76 = i75 + 1;
                                cArr[i75] = '.';
                                i10 = i76 + 1;
                                cArr[i76] = '0';
                            } else {
                                int i77 = i9 + 1;
                                for (int i78 = 0; i78 < i62; i78++) {
                                    if ((i62 - i78) - 1 == i57) {
                                        cArr[((i77 + i62) - i78) - 1] = '.';
                                        i77--;
                                    }
                                    cArr[((i77 + i62) - i78) - 1] = (char) ((i61 % 10) + 48);
                                    i61 /= 10;
                                }
                                i10 = i9 + i62 + 1;
                            }
                        }
                    }
                    return i10 - i;
                }
                int i79 = i + 1;
                cArr[i] = '0';
                int i80 = i79 + 1;
                cArr[i79] = '.';
                i12 = i80 + 1;
                cArr[i80] = '0';
            }
        }
        return i12 - i;
    }
}
