package com.duowan.taf.jce;

import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class JceUtil {
    private static final byte[] highDigits;
    private static final int iConstant = 37;
    private static final int iTotal = 17;
    private static final byte[] lowDigits;

    public static int compareTo(byte b, byte b2) {
        if (b < b2) {
            return -1;
        }
        return b > b2 ? 1 : 0;
    }

    public static int compareTo(char c, char c2) {
        if (c < c2) {
            return -1;
        }
        return c > c2 ? 1 : 0;
    }

    public static int compareTo(double d, double d2) {
        if (d < d2) {
            return -1;
        }
        return d > d2 ? 1 : 0;
    }

    public static int compareTo(float f, float f2) {
        if (f < f2) {
            return -1;
        }
        return f > f2 ? 1 : 0;
    }

    public static int compareTo(int i, int i2) {
        if (i < i2) {
            return -1;
        }
        return i > i2 ? 1 : 0;
    }

    public static int compareTo(long j, long j2) {
        if (j < j2) {
            return -1;
        }
        return j > j2 ? 1 : 0;
    }

    public static int compareTo(short s, short s2) {
        if (s < s2) {
            return -1;
        }
        return s > s2 ? 1 : 0;
    }

    public static int compareTo(boolean z, boolean z2) {
        return (z ? 1 : 0) - (z2 ? 1 : 0);
    }

    public static boolean equals(byte b, byte b2) {
        return b == b2;
    }

    public static boolean equals(char c, char c2) {
        return c == c2;
    }

    public static boolean equals(double d, double d2) {
        return d == d2;
    }

    public static boolean equals(float f, float f2) {
        return f == f2;
    }

    public static boolean equals(int i, int i2) {
        return i == i2;
    }

    public static boolean equals(long j, long j2) {
        return j == j2;
    }

    public static boolean equals(short s, short s2) {
        return s == s2;
    }

    public static boolean equals(boolean z, boolean z2) {
        return z == z2;
    }

    public static int hashCode(byte b) {
        return b + 629;
    }

    public static int hashCode(char c) {
        return c + 629;
    }

    public static int hashCode(int i) {
        return i + 629;
    }

    public static int hashCode(long j) {
        return ((int) (j ^ (j >> 32))) + 629;
    }

    public static int hashCode(short s) {
        return s + 629;
    }

    public static int hashCode(boolean z) {
        return (!z ? 1 : 0) + 629;
    }

    public static boolean equals(Object obj, Object obj2) {
        if (obj == null) {
            return obj == obj2;
        }
        return obj.equals(obj2);
    }

    public static <T extends Comparable<T>> int compareTo(T t, T t2) {
        return t.compareTo(t2);
    }

    public static <T extends Comparable<T>> int compareTo(List<T> list, List<T> list2) {
        Iterator<T> it = list.iterator();
        Iterator<T> it2 = list2.iterator();
        while (it.hasNext() && it2.hasNext()) {
            int iCompareTo = it.next().compareTo(it2.next());
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return compareTo(it.hasNext(), it2.hasNext());
    }

    public static <T extends Comparable<T>> int compareTo(T[] tArr, T[] tArr2) {
        int i = 0;
        for (int i2 = 0; i < tArr.length && i2 < tArr2.length; i2++) {
            int iCompareTo = tArr[i].compareTo(tArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(tArr.length, tArr2.length);
    }

    public static int compareTo(boolean[] zArr, boolean[] zArr2) {
        int i = 0;
        for (int i2 = 0; i < zArr.length && i2 < zArr2.length; i2++) {
            int iCompareTo = compareTo(zArr[i], zArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(zArr.length, zArr2.length);
    }

    public static int compareTo(byte[] bArr, byte[] bArr2) {
        int i = 0;
        for (int i2 = 0; i < bArr.length && i2 < bArr2.length; i2++) {
            int iCompareTo = compareTo(bArr[i], bArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(bArr.length, bArr2.length);
    }

    public static int compareTo(char[] cArr, char[] cArr2) {
        int i = 0;
        for (int i2 = 0; i < cArr.length && i2 < cArr2.length; i2++) {
            int iCompareTo = compareTo(cArr[i], cArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(cArr.length, cArr2.length);
    }

    public static int compareTo(short[] sArr, short[] sArr2) {
        int i = 0;
        for (int i2 = 0; i < sArr.length && i2 < sArr2.length; i2++) {
            int iCompareTo = compareTo(sArr[i], sArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(sArr.length, sArr2.length);
    }

    public static int compareTo(int[] iArr, int[] iArr2) {
        int i = 0;
        for (int i2 = 0; i < iArr.length && i2 < iArr2.length; i2++) {
            int iCompareTo = compareTo(iArr[i], iArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(iArr.length, iArr2.length);
    }

    public static int compareTo(long[] jArr, long[] jArr2) {
        int i = 0;
        for (int i2 = 0; i < jArr.length && i2 < jArr2.length; i2++) {
            int iCompareTo = compareTo(jArr[i], jArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(jArr.length, jArr2.length);
    }

    public static int compareTo(float[] fArr, float[] fArr2) {
        int i = 0;
        for (int i2 = 0; i < fArr.length && i2 < fArr2.length; i2++) {
            int iCompareTo = compareTo(fArr[i], fArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(fArr.length, fArr2.length);
    }

    public static int compareTo(double[] dArr, double[] dArr2) {
        int i = 0;
        for (int i2 = 0; i < dArr.length && i2 < dArr2.length; i2++) {
            int iCompareTo = compareTo(dArr[i], dArr2[i2]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
            i++;
        }
        return compareTo(dArr.length, dArr2.length);
    }

    public static int hashCode(boolean[] zArr) {
        if (zArr == null) {
            return 629;
        }
        int i = 17;
        for (boolean z : zArr) {
            i = (i * 37) + (!z ? 1 : 0);
        }
        return i;
    }

    public static int hashCode(byte[] bArr) {
        if (bArr == null) {
            return 629;
        }
        int i = 17;
        for (byte b : bArr) {
            i = (i * 37) + b;
        }
        return i;
    }

    public static int hashCode(char[] cArr) {
        if (cArr == null) {
            return 629;
        }
        int i = 17;
        for (char c : cArr) {
            i = (i * 37) + c;
        }
        return i;
    }

    public static int hashCode(double d) {
        return hashCode(Double.doubleToLongBits(d));
    }

    public static int hashCode(double[] dArr) {
        if (dArr == null) {
            return 629;
        }
        int iDoubleToLongBits = 17;
        for (int i = 0; i < dArr.length; i++) {
            iDoubleToLongBits = (iDoubleToLongBits * 37) + ((int) (Double.doubleToLongBits(dArr[i]) ^ (Double.doubleToLongBits(dArr[i]) >> 32)));
        }
        return iDoubleToLongBits;
    }

    public static int hashCode(float f) {
        return Float.floatToIntBits(f) + 629;
    }

    public static int hashCode(float[] fArr) {
        if (fArr == null) {
            return 629;
        }
        int iFloatToIntBits = 17;
        for (float f : fArr) {
            iFloatToIntBits = (iFloatToIntBits * 37) + Float.floatToIntBits(f);
        }
        return iFloatToIntBits;
    }

    public static int hashCode(short[] sArr) {
        if (sArr == null) {
            return 629;
        }
        int i = 17;
        for (short s : sArr) {
            i = (i * 37) + s;
        }
        return i;
    }

    public static int hashCode(int[] iArr) {
        if (iArr == null) {
            return 629;
        }
        int i = 17;
        for (int i2 : iArr) {
            i = (i * 37) + i2;
        }
        return i;
    }

    public static int hashCode(long[] jArr) {
        if (jArr == null) {
            return 629;
        }
        int i = 17;
        for (int i2 = 0; i2 < jArr.length; i2++) {
            i = (i * 37) + ((int) (jArr[i2] ^ (jArr[i2] >> 32)));
        }
        return i;
    }

    public static int hashCode(JceStruct[] jceStructArr) {
        if (jceStructArr == null) {
            return 629;
        }
        int iHashCode = 17;
        for (JceStruct jceStruct : jceStructArr) {
            iHashCode = (iHashCode * 37) + jceStruct.hashCode();
        }
        return iHashCode;
    }

    public static int hashCode(Object obj) {
        if (obj == null) {
            return 629;
        }
        if (obj.getClass().isArray()) {
            if (obj instanceof long[]) {
                return hashCode((long[]) obj);
            }
            if (obj instanceof int[]) {
                return hashCode((int[]) obj);
            }
            if (obj instanceof short[]) {
                return hashCode((short[]) obj);
            }
            if (obj instanceof char[]) {
                return hashCode((char[]) obj);
            }
            if (obj instanceof byte[]) {
                return hashCode((byte[]) obj);
            }
            if (obj instanceof double[]) {
                return hashCode((double[]) obj);
            }
            if (obj instanceof float[]) {
                return hashCode((float[]) obj);
            }
            if (obj instanceof boolean[]) {
                return hashCode((boolean[]) obj);
            }
            if (obj instanceof JceStruct[]) {
                return hashCode((JceStruct[]) obj);
            }
            return hashCode((Object[]) obj);
        }
        if (obj instanceof JceStruct) {
            return obj.hashCode();
        }
        return obj.hashCode() + 629;
    }

    public static byte[] getJceBufArray(ByteBuffer byteBuffer) {
        int iPosition = byteBuffer.position();
        byte[] bArr = new byte[iPosition];
        System.arraycopy(byteBuffer.array(), 0, bArr, 0, iPosition);
        return bArr;
    }

    public static String getHexdump(byte[] bArr) {
        return getHexdump(ByteBuffer.wrap(bArr));
    }

    public static String getHexdump(ByteBuffer byteBuffer) {
        int iRemaining = byteBuffer.remaining();
        if (iRemaining == 0) {
            return "empty";
        }
        StringBuffer stringBuffer = new StringBuffer((byteBuffer.remaining() * 3) - 1);
        int iPosition = byteBuffer.position();
        int i = byteBuffer.get() & 255;
        stringBuffer.append((int) highDigits[i]);
        stringBuffer.append((int) lowDigits[i]);
        while (true) {
            iRemaining--;
            if (iRemaining > 0) {
                stringBuffer.append(' ');
                int i2 = byteBuffer.get() & 255;
                stringBuffer.append((int) highDigits[i2]);
                stringBuffer.append((int) lowDigits[i2]);
            } else {
                byteBuffer.position(iPosition);
                return stringBuffer.toString();
            }
        }
    }

    static {
        byte[] bArr = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
        byte[] bArr2 = new byte[256];
        byte[] bArr3 = new byte[256];
        for (int i = 0; i < 256; i++) {
            bArr2[i] = bArr[i >>> 4];
            bArr3[i] = bArr[i & 15];
        }
        highDigits = bArr2;
        lowDigits = bArr3;
    }
}
