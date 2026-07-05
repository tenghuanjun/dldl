package com.google.zxing.oned;

import com.alibaba.fastjson.asm.Opcodes;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class Code39Reader extends OneDReader {
    static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. $/+%";
    static final int ASTERISK_ENCODING = 148;
    static final int[] CHARACTER_ENCODINGS = {52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, Opcodes.INSTANCEOF, 448, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_SHOW_NOTIFICATION, 400, 208, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_IS_AD, 388, 196, 168, Opcodes.IF_ICMPGE, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_DEEP_LINK, 42};
    private final int[] counters;
    private final StringBuilder decodeRowResult;
    private final boolean extendedMode;
    private final boolean usingCheckDigit;

    public Code39Reader() {
        this(false);
    }

    public Code39Reader(boolean z) {
        this(z, false);
    }

    public Code39Reader(boolean z, boolean z2) {
        this.usingCheckDigit = z;
        this.extendedMode = z2;
        this.decodeRowResult = new StringBuilder(20);
        this.counters = new int[9];
    }

    @Override // com.google.zxing.oned.OneDReader
    public Result decodeRow(int i, BitArray bitArray, Map<DecodeHintType, ?> map) throws NotFoundException, ChecksumException, FormatException {
        String string;
        int[] iArr = this.counters;
        Arrays.fill(iArr, 0);
        StringBuilder sb = this.decodeRowResult;
        sb.setLength(0);
        int nextSet = bitArray.getNextSet(findAsteriskPattern(bitArray, iArr)[1]);
        int size = bitArray.getSize();
        while (true) {
            recordPattern(bitArray, nextSet, iArr);
            int narrowWidePattern = toNarrowWidePattern(iArr);
            if (narrowWidePattern < 0) {
                throw NotFoundException.getNotFoundInstance();
            }
            char cPatternToChar = patternToChar(narrowWidePattern);
            sb.append(cPatternToChar);
            int i2 = nextSet;
            for (int i3 : iArr) {
                i2 += i3;
            }
            int nextSet2 = bitArray.getNextSet(i2);
            if (cPatternToChar == '*') {
                sb.setLength(sb.length() - 1);
                int i4 = 0;
                for (int i5 : iArr) {
                    i4 += i5;
                }
                int i6 = (nextSet2 - nextSet) - i4;
                if (nextSet2 != size && (i6 << 1) < i4) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (this.usingCheckDigit) {
                    int length = sb.length() - 1;
                    int iIndexOf = 0;
                    for (int i7 = 0; i7 < length; i7++) {
                        iIndexOf += ALPHABET_STRING.indexOf(this.decodeRowResult.charAt(i7));
                    }
                    if (sb.charAt(length) != ALPHABET_STRING.charAt(iIndexOf % 43)) {
                        throw ChecksumException.getChecksumInstance();
                    }
                    sb.setLength(length);
                }
                if (sb.length() == 0) {
                    throw NotFoundException.getNotFoundInstance();
                }
                if (this.extendedMode) {
                    string = decodeExtended(sb);
                } else {
                    string = sb.toString();
                }
                float f = i;
                return new Result(string, null, new ResultPoint[]{new ResultPoint((r2[1] + r2[0]) / 2.0f, f), new ResultPoint(nextSet + (i4 / 2.0f), f)}, BarcodeFormat.CODE_39);
            }
            nextSet = nextSet2;
        }
    }

    private static int[] findAsteriskPattern(BitArray bitArray, int[] iArr) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        int length = iArr.length;
        int i = nextSet;
        boolean z = false;
        int i2 = 0;
        while (nextSet < size) {
            if (bitArray.get(nextSet) != z) {
                iArr[i2] = iArr[i2] + 1;
            } else {
                if (i2 != length - 1) {
                    i2++;
                } else {
                    if (toNarrowWidePattern(iArr) == 148 && bitArray.isRange(Math.max(0, i - ((nextSet - i) / 2)), i, false)) {
                        return new int[]{i, nextSet};
                    }
                    i += iArr[0] + iArr[1];
                    int i3 = i2 - 1;
                    System.arraycopy(iArr, 2, iArr, 0, i3);
                    iArr[i3] = 0;
                    iArr[i2] = 0;
                    i2--;
                }
                iArr[i2] = 1;
                z = !z;
            }
            nextSet++;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int toNarrowWidePattern(int[] iArr) {
        int length = iArr.length;
        int i = 0;
        while (true) {
            int i2 = Integer.MAX_VALUE;
            for (int i3 : iArr) {
                if (i3 < i2 && i3 > i) {
                    i2 = i3;
                }
            }
            int i4 = 0;
            int i5 = 0;
            int i6 = 0;
            for (int i7 = 0; i7 < length; i7++) {
                int i8 = iArr[i7];
                if (i8 > i2) {
                    i5 |= 1 << ((length - 1) - i7);
                    i4++;
                    i6 += i8;
                }
            }
            if (i4 == 3) {
                for (int i9 = 0; i9 < length && i4 > 0; i9++) {
                    int i10 = iArr[i9];
                    if (i10 > i2) {
                        i4--;
                        if ((i10 << 1) >= i6) {
                            return -1;
                        }
                    }
                }
                return i5;
            }
            if (i4 <= 3) {
                return -1;
            }
            i = i2;
        }
    }

    private static char patternToChar(int i) throws NotFoundException {
        int i2 = 0;
        while (true) {
            int[] iArr = CHARACTER_ENCODINGS;
            if (i2 >= iArr.length) {
                if (i == 148) {
                    return '*';
                }
                throw NotFoundException.getNotFoundInstance();
            }
            if (iArr[i2] == i) {
                return ALPHABET_STRING.charAt(i2);
            }
            i2++;
        }
    }

    private static String decodeExtended(CharSequence charSequence) throws FormatException {
        char c;
        int length = charSequence.length();
        StringBuilder sb = new StringBuilder(length);
        int i = 0;
        while (i < length) {
            char cCharAt = charSequence.charAt(i);
            if (cCharAt == '+' || cCharAt == '$' || cCharAt == '%' || cCharAt == '/') {
                i++;
                char cCharAt2 = charSequence.charAt(i);
                if (cCharAt == '+') {
                    if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                        throw FormatException.getFormatInstance();
                    }
                    c = (char) (cCharAt2 + ' ');
                } else if (cCharAt != '/') {
                    switch (cCharAt) {
                        case '$':
                            if (cCharAt2 < 'A' || cCharAt2 > 'Z') {
                                throw FormatException.getFormatInstance();
                            }
                            c = (char) (cCharAt2 - '@');
                            break;
                        case '%':
                            if (cCharAt2 >= 'A' && cCharAt2 <= 'E') {
                                c = (char) (cCharAt2 - '&');
                            } else if (cCharAt2 >= 'F' && cCharAt2 <= 'J') {
                                c = (char) (cCharAt2 - 11);
                            } else if (cCharAt2 >= 'K' && cCharAt2 <= 'O') {
                                c = (char) (cCharAt2 + 16);
                            } else if (cCharAt2 >= 'P' && cCharAt2 <= 'T') {
                                c = (char) (cCharAt2 + '+');
                            } else if (cCharAt2 == 'U') {
                                c = 0;
                            } else if (cCharAt2 == 'V') {
                                c = '@';
                            } else if (cCharAt2 == 'W') {
                                c = '`';
                            } else {
                                if (cCharAt2 != 'X' && cCharAt2 != 'Y' && cCharAt2 != 'Z') {
                                    throw FormatException.getFormatInstance();
                                }
                                c = 127;
                            }
                            break;
                        default:
                            c = 0;
                            break;
                    }
                } else if (cCharAt2 >= 'A' && cCharAt2 <= 'O') {
                    c = (char) (cCharAt2 - ' ');
                } else {
                    if (cCharAt2 != 'Z') {
                        throw FormatException.getFormatInstance();
                    }
                    c = ':';
                }
                sb.append(c);
            } else {
                sb.append(cCharAt);
            }
            i++;
        }
        return sb.toString();
    }
}
