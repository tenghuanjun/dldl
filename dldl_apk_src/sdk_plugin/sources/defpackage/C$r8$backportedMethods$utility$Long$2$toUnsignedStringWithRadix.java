package defpackage;

/* JADX INFO: renamed from: $r8$backportedMethods$utility$Long$2$toUnsignedStringWithRadix, reason: invalid class name */
/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public /* synthetic */ class C$r8$backportedMethods$utility$Long$2$toUnsignedStringWithRadix {
    public static /* synthetic */ String toUnsignedString(long j, int i) {
        int i2;
        if (j == 0) {
            return "0";
        }
        if (j > 0) {
            return Long.toString(j, i);
        }
        if (i < 2 || i > 36) {
            i = 10;
        }
        char[] cArr = new char[64];
        int i3 = i - 1;
        if ((i & i3) == 0) {
            int iNumberOfTrailingZeros = Integer.numberOfTrailingZeros(i);
            i2 = 64;
            do {
                i2--;
                cArr[i2] = Character.forDigit(((int) j) & i3, i);
                j >>>= iNumberOfTrailingZeros;
            } while (j != 0);
        } else {
            long jDivideUnsigned = (i & 1) == 0 ? (j >>> 1) / ((long) (i >>> 1)) : C$r8$backportedMethods$utility$Long$2$divideUnsigned.divideUnsigned(j, i);
            long j2 = i;
            int i4 = 63;
            cArr[63] = Character.forDigit((int) (j - (jDivideUnsigned * j2)), i);
            while (jDivideUnsigned > 0) {
                i4--;
                cArr[i4] = Character.forDigit((int) (jDivideUnsigned % j2), i);
                jDivideUnsigned /= j2;
            }
            i2 = i4;
        }
        return new String(cArr, i2, 64 - i2);
    }
}
