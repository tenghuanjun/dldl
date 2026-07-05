package com.bytedance.pangle.g;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.DigestException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
abstract class g {
    private static final byte[] a = new byte[8];

    static class a {
        public final ByteBuffer a;
        public final byte[] b;

        a(ByteBuffer byteBuffer, byte[] bArr) {
            this.a = byteBuffer;
            this.b = bArr;
        }
    }

    static a a(RandomAccessFile randomAccessFile, m mVar, i iVar) throws DigestException, IOException {
        int[] iArrA = a(randomAccessFile.length() - (mVar.c - mVar.b));
        int i = iArrA[iArrA.length - 1];
        int i2 = i + 4096;
        ByteBuffer byteBufferA = iVar.a(i2);
        byteBufferA.order(ByteOrder.LITTLE_ENDIAN);
        ByteBuffer byteBufferA2 = a(byteBufferA, 0, i);
        int i3 = i + 64;
        ByteBuffer byteBufferA3 = a(byteBufferA, i, i3);
        ByteBuffer byteBufferA4 = a(byteBufferA, i3, i2);
        byte[] bArr = new byte[32];
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.order(ByteOrder.LITTLE_ENDIAN);
        if (mVar.b % 4096 != 0) {
            throw new IllegalArgumentException("APK Signing Block does not start at the page  boundary: " + mVar.b);
        }
        if ((mVar.c - mVar.b) % 4096 == 0) {
            long j = mVar.c - mVar.b;
            int[] iArrA2 = a(randomAccessFile.length() - j);
            if (byteBufferA2 != null) {
                byte[] bArrA = a(randomAccessFile, mVar, a, iArrA2, byteBufferA2);
                if (byteBufferWrap != null) {
                    byteBufferWrap.put(bArrA);
                    byteBufferWrap.flip();
                }
            }
            if (byteBufferA3 != null) {
                byteBufferA3.order(ByteOrder.LITTLE_ENDIAN);
                long length = randomAccessFile.length();
                byte[] bArr2 = a;
                if (bArr2.length != 8) {
                    throw new IllegalArgumentException("salt is not 8 bytes long");
                }
                byteBufferA3.put("TrueBrew".getBytes());
                byteBufferA3.put((byte) 1);
                byteBufferA3.put((byte) 0);
                byteBufferA3.put((byte) 12);
                byteBufferA3.put((byte) 7);
                byteBufferA3.putShort((short) 1);
                byteBufferA3.putShort((short) 1);
                byteBufferA3.putInt(0);
                byteBufferA3.putInt(0);
                byteBufferA3.putLong(length);
                byteBufferA3.put((byte) 2);
                byteBufferA3.put((byte) 0);
                byteBufferA3.put(bArr2);
                a(byteBufferA3, 22);
                byteBufferA3.flip();
            }
            if (byteBufferA4 != null) {
                byteBufferA4.order(ByteOrder.LITTLE_ENDIAN);
                long j2 = mVar.b;
                long j3 = mVar.d;
                byteBufferA4.putInt(24);
                byteBufferA4.putShort((short) 1);
                a(byteBufferA4, 2);
                byteBufferA4.putLong(j2);
                byteBufferA4.putLong(j);
                byteBufferA4.putInt(20);
                byteBufferA4.putShort((short) 2);
                a(byteBufferA4, 2);
                byteBufferA4.putLong(j3 + 16);
                byteBufferA4.putInt(c(j2));
                a(byteBufferA4, 4);
                byteBufferA4.flip();
            }
            byteBufferA.position(i3 + byteBufferA4.limit());
            byteBufferA.putInt(byteBufferA4.limit() + 64 + 4);
            byteBufferA.flip();
            return new a(byteBufferA, bArr);
        }
        throw new IllegalArgumentException("Size of APK Signing Block is not a multiple of 4096: " + (mVar.c - mVar.b));
    }

    static class b implements j {
        private int a;
        private final ByteBuffer b;
        private final MessageDigest c;
        private final byte[] d;
        private final byte[] e;

        /* synthetic */ b(byte[] bArr, ByteBuffer byteBuffer, byte b) {
            this(bArr, byteBuffer);
        }

        private b(byte[] bArr, ByteBuffer byteBuffer) throws NoSuchAlgorithmException {
            this.d = new byte[32];
            this.e = bArr;
            this.b = byteBuffer.slice();
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            this.c = messageDigest;
            messageDigest.update(this.e);
            this.a = 0;
        }

        @Override // com.bytedance.pangle.g.j
        public final void a(ByteBuffer byteBuffer) throws DigestException {
            byteBuffer.position();
            int iRemaining = byteBuffer.remaining();
            while (iRemaining > 0) {
                int iMin = Math.min(iRemaining, 4096 - this.a);
                byteBuffer.limit(byteBuffer.position() + iMin);
                this.c.update(byteBuffer);
                iRemaining -= iMin;
                int i = this.a + iMin;
                this.a = i;
                if (i == 4096) {
                    MessageDigest messageDigest = this.c;
                    byte[] bArr = this.d;
                    messageDigest.digest(bArr, 0, bArr.length);
                    this.b.put(this.d);
                    this.c.update(this.e);
                    this.a = 0;
                }
            }
        }

        public final void a() {
            if (this.a == 0) {
                return;
            }
            throw new IllegalStateException("Buffer is not empty: " + this.a);
        }

        final void b() {
            int iPosition = this.b.position() % 4096;
            if (iPosition == 0) {
                return;
            }
            this.b.put(ByteBuffer.allocate(4096 - iPosition));
        }
    }

    private static void a(j jVar, k kVar, int i) {
        long jA = kVar.a();
        long j = 0;
        while (jA > 0) {
            int iMin = (int) Math.min(jA, i);
            kVar.a(jVar, j, iMin);
            long j2 = iMin;
            j += j2;
            jA -= j2;
        }
    }

    private static byte[] a(RandomAccessFile randomAccessFile, m mVar, byte[] bArr, int[] iArr, ByteBuffer byteBuffer) throws DigestException {
        byte b2 = 0;
        b bVar = new b(bArr, a(byteBuffer, iArr[iArr.length - 2], iArr[iArr.length - 1]), b2);
        a(bVar, new l(randomAccessFile.getFD(), 0L, mVar.b), 1048576);
        long j = mVar.d + 16;
        a(bVar, new l(randomAccessFile.getFD(), mVar.c, j - mVar.c), 1048576);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putInt(c(mVar.b));
        byteBufferOrder.flip();
        bVar.a(byteBufferOrder);
        long j2 = j + 4;
        a(bVar, new l(randomAccessFile.getFD(), j2, randomAccessFile.length() - j2), 1048576);
        int length = (int) (randomAccessFile.length() % 4096);
        if (length != 0) {
            bVar.a(ByteBuffer.allocate(4096 - length));
        }
        bVar.a();
        bVar.b();
        for (int length2 = iArr.length - 3; length2 >= 0; length2--) {
            int i = length2 + 1;
            ByteBuffer byteBufferA = a(byteBuffer, iArr[i], iArr[length2 + 2]);
            ByteBuffer byteBufferA2 = a(byteBuffer, iArr[length2], iArr[i]);
            h hVar = new h(byteBufferA);
            b bVar2 = new b(bArr, byteBufferA2, b2);
            a(bVar2, hVar, 4096);
            bVar2.a();
            bVar2.b();
        }
        byte[] bArr2 = new byte[32];
        b bVar3 = new b(bArr, ByteBuffer.wrap(bArr2), b2);
        bVar3.a(a(byteBuffer, 0, 4096));
        bVar3.a();
        return bArr2;
    }

    private static int[] a(long j) {
        ArrayList arrayList = new ArrayList();
        do {
            j = b(j) * 32;
            arrayList.add(Long.valueOf(b(j) * 4096));
        } while (j > 4096);
        int[] iArr = new int[arrayList.size() + 1];
        int i = 0;
        iArr[0] = 0;
        while (i < arrayList.size()) {
            int i2 = i + 1;
            iArr[i2] = iArr[i] + c(((Long) arrayList.get((arrayList.size() - i) - 1)).longValue());
            i = i2;
        }
        return iArr;
    }

    private static ByteBuffer a(ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer byteBufferDuplicate = byteBuffer.duplicate();
        byteBufferDuplicate.position(0);
        byteBufferDuplicate.limit(i2);
        byteBufferDuplicate.position(i);
        return byteBufferDuplicate.slice();
    }

    private static void a(ByteBuffer byteBuffer, int i) {
        byteBuffer.position(byteBuffer.position() + i);
    }

    private static long b(long j) {
        return ((j + 4096) - 1) / 4096;
    }

    private static int c(long j) {
        int i = (int) j;
        if (i == j) {
            return i;
        }
        throw new ArithmeticException("integer overflow");
    }
}
