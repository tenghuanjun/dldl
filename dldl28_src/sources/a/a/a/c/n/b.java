package a.a.a.c.n;

import java.nio.charset.Charset;
import java.util.Arrays;
import org.apache.commons.lang3.CharEncoding;

/* JADX INFO: compiled from: Base64.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: compiled from: Base64.java */
    public static class a {
        public static final a c = new a(false, false);
        public static final int[] d;
        public static final int[] e;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final boolean f97a;
        public final boolean b;

        static {
            int i = 0;
            new a(true, false);
            int[] iArr = new int[256];
            d = iArr;
            e = new int[256];
            Arrays.fill(iArr, -1);
            int i2 = 0;
            while (true) {
                char[] cArr = C0003b.f98a;
                if (i2 >= cArr.length) {
                    break;
                }
                d[cArr[i2]] = i2;
                i2++;
            }
            d[61] = -2;
            Arrays.fill(e, -1);
            while (true) {
                char[] cArr2 = C0003b.b;
                if (i >= cArr2.length) {
                    e[61] = -2;
                    return;
                } else {
                    e[cArr2[i]] = i;
                    i++;
                }
            }
        }

        public a(boolean z, boolean z2) {
            this.f97a = z;
            this.b = z2;
        }

        /* JADX WARN: Code restructure failed: missing block: B:59:0x0098, code lost:
        
            if (r11 != 18) goto L72;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public byte[] a(byte[] r17) {
            /*
                Method dump skipped, instruction units count: 315
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: a.a.a.c.n.b.a.a(byte[]):byte[]");
        }
    }

    /* JADX INFO: renamed from: a.a.a.c.n.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Base64.java */
    public static class C0003b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final char[] f98a = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'};
        public static final char[] b = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'};
    }

    static {
        Charset.forName(CharEncoding.ISO_8859_1);
        Charset.forName("UTF-8");
    }

    public static a a() {
        return a.c;
    }
}
