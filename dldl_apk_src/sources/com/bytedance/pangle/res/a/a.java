package com.bytedance.pangle.res.a;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.logging.Logger;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class a {
    private static final Logger f = Logger.getLogger(a.class.getName());
    private final byte[] a;
    private final h b;
    private final g c;
    private final e d;
    private C0022a e;

    public a(byte[] bArr, h hVar) {
        e eVar = new e(new ByteArrayInputStream(bArr));
        this.d = eVar;
        this.c = new g(new i(eVar));
        this.a = bArr;
        this.b = hVar;
    }

    private void b() throws IOException {
        b(515);
        int i = this.c.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            this.c.readInt();
            this.c.skipBytes(256);
        }
        while (j().a == 513) {
            c();
        }
    }

    private void c() throws IOException {
        d();
        short s = j().a;
        while (s == 514) {
            d();
            s = j().a;
        }
        while (s == 513) {
            e();
            if (this.d.a() < this.e.e) {
                f.warning("Unknown data detected. Skipping: " + (this.e.e - this.d.a()) + " byte(s)");
                this.d.skip((long) (this.e.e - this.d.a()));
            }
            s = j().a;
        }
    }

    private void d() {
        b(514);
        this.c.readUnsignedByte();
        this.c.skipBytes(3);
        this.c.skipBytes(this.c.readInt() * 4);
    }

    private void e() throws IOException {
        b(513);
        this.c.readUnsignedByte();
        this.c.readByte();
        this.c.skipBytes(2);
        int i = this.c.readInt();
        int i2 = this.c.readInt();
        i();
        int i3 = (this.e.d + i2) - (i * 4);
        if (i3 != this.d.a()) {
            f.warning("Invalid data detected. Skipping: " + (i3 - this.d.a()) + " byte(s)");
            this.c.skipBytes(i3 - this.d.a());
        }
        int[] iArrA = this.c.a(i);
        HashSet hashSet = new HashSet();
        for (int i4 : iArrA) {
            if (i4 != -1 && !hashSet.contains(Integer.valueOf(i4))) {
                f();
                hashSet.add(Integer.valueOf(i4));
            }
        }
    }

    private void f() throws IOException {
        if (this.c.readShort() < 0) {
            throw new RuntimeException("Entry size is under 0 bytes.");
        }
        short s = this.c.readShort();
        this.c.readInt();
        if ((s & 1) == 0) {
            h();
        } else {
            g();
        }
    }

    private void g() throws IOException {
        int iA = k.a(this.c);
        k.a(this.a, this.c.readInt(), iA, this.b);
        int i = this.c.readInt();
        for (int i2 = 0; i2 < i; i2++) {
            int iA2 = k.a(this.c);
            k.a(this.a, this.c.readInt(), iA2, this.b);
            h();
        }
    }

    private void h() throws IOException {
        this.c.a();
        this.c.b();
        byte b = this.c.readByte();
        int iA = k.a(this.c);
        int i = this.c.readInt();
        if (b == 1) {
            k.a(this.a, i, iA, this.b);
        }
        if (b == 2) {
            k.a(this.a, i, iA, this.b);
        }
    }

    private void i() {
        int i = this.c.readInt();
        int i2 = 28;
        if (i < 28) {
            throw new RuntimeException("Config size < 28");
        }
        this.c.readShort();
        this.c.readShort();
        this.c.readByte();
        this.c.readByte();
        this.c.readByte();
        this.c.readByte();
        this.c.readByte();
        this.c.readByte();
        this.c.readUnsignedShort();
        this.c.readByte();
        this.c.readByte();
        this.c.readByte();
        this.c.skipBytes(1);
        this.c.readShort();
        this.c.readShort();
        this.c.readShort();
        this.c.skipBytes(2);
        if (i >= 32) {
            this.c.readByte();
            this.c.readByte();
            this.c.readShort();
            i2 = 32;
        }
        if (i >= 36) {
            this.c.readShort();
            this.c.readShort();
            i2 = 36;
        }
        if (i >= 48) {
            a(4).toCharArray();
            a(8).toCharArray();
            i2 = 48;
        }
        if (i >= 52) {
            this.c.readByte();
            this.c.readByte();
            this.c.skipBytes(2);
            i2 = 52;
        }
        if (i >= 56) {
            this.c.skipBytes(4);
            i2 = 56;
        }
        int i3 = i - 56;
        if (i3 > 0) {
            byte[] bArr = new byte[i3];
            i2 += i3;
            this.c.readFully(bArr);
            BigInteger bigInteger = new BigInteger(1, bArr);
            if (bigInteger.equals(BigInteger.ZERO)) {
                f.fine(String.format("Config flags size > %d, but exceeding bytes are all zero, so it should be ok.", 56));
            } else {
                f.warning(String.format("Config flags size > %d. Size = %d. Exceeding bytes: 0x%X.", 56, Integer.valueOf(i), bigInteger));
            }
        }
        int i4 = i - i2;
        if (i4 > 0) {
            this.c.skipBytes(i4);
        }
    }

    private String a(int i) {
        int i2;
        short s;
        StringBuilder sb = new StringBuilder(16);
        while (true) {
            i2 = i - 1;
            if (i == 0 || this.c.readByte() == 0) {
                break;
            }
            sb.append((char) s);
            i = i2;
        }
        this.c.skipBytes(i2);
        return sb.toString();
    }

    private C0022a j() {
        C0022a c0022aA = C0022a.a(this.c, this.d);
        this.e = c0022aA;
        return c0022aA;
    }

    private void b(int i) {
        if (this.e.a != i) {
            throw new RuntimeException(String.format("Invalid chunk type: expected=0x%08x, got=0x%08x", Integer.valueOf(i), Short.valueOf(this.e.a)));
        }
    }

    /* JADX INFO: renamed from: com.bytedance.pangle.res.a.a$a, reason: collision with other inner class name */
    public static class C0022a {
        public final short a;
        public final int b;
        public final int c;
        public final int d;
        public final int e;

        private C0022a(short s, int i, int i2, int i3) {
            this.a = s;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i3 + i2;
        }

        public static C0022a a(g gVar, e eVar) {
            int iA = eVar.a();
            try {
                return new C0022a(gVar.readShort(), gVar.readShort(), gVar.readInt(), iA);
            } catch (EOFException unused) {
                return new C0022a((short) -1, 0, 0, eVar.a());
            }
        }
    }

    public final void a() throws IOException {
        j();
        b(2);
        int i = this.c.readInt();
        l.a(this.c);
        j();
        for (int i2 = 0; i2 < i; i2++) {
            b(512);
            this.c.readInt();
            this.c.skipBytes(256);
            this.c.skipBytes(4);
            this.c.skipBytes(4);
            this.c.skipBytes(4);
            this.c.skipBytes(4);
            if (this.e.b == 288 && this.c.readInt() > 0) {
                throw new RuntimeException("don't support");
            }
            l.a(this.c);
            l.a(this.c);
            j();
            boolean z = true;
            while (z) {
                short s = this.e.a;
                if (s == 514) {
                    c();
                } else if (s != 515) {
                    z = false;
                } else {
                    b();
                }
            }
        }
    }
}
