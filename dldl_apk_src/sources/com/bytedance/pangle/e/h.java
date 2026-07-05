package com.bytedance.pangle.e;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class h implements Closeable {
    private final FileInputStream a;
    private a b;
    private b[] c;
    private c[] d;
    private final Map<String, c> e = new HashMap();

    public static boolean a(File file) {
        try {
            com.bytedance.pangle.util.g.a(new h(file));
            return true;
        } catch (IOException unused) {
            com.bytedance.pangle.util.g.a((Closeable) null);
            return false;
        } catch (Throwable th) {
            com.bytedance.pangle.util.g.a((Closeable) null);
            throw th;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private h(File file) throws IOException {
        this.b = null;
        this.c = null;
        this.d = null;
        FileInputStream fileInputStream = new FileInputStream(file);
        this.a = fileInputStream;
        FileChannel channel = fileInputStream.getChannel();
        Object[] objArr = 0;
        Object[] objArr2 = 0;
        this.b = new a(channel, 0 == true ? 1 : 0);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(128);
        byteBufferAllocate.limit(this.b.j);
        byteBufferAllocate.order(this.b.a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
        channel.position(this.b.f);
        this.c = new b[this.b.k];
        for (int i = 0; i < this.c.length; i++) {
            b(channel, byteBufferAllocate, "failed to read phdr.");
            this.c[i] = new b(byteBufferAllocate, this.b.a[4], objArr2 == true ? 1 : 0);
        }
        channel.position(this.b.g);
        byteBufferAllocate.limit(this.b.l);
        this.d = new c[this.b.m];
        for (int i2 = 0; i2 < this.d.length; i2++) {
            b(channel, byteBufferAllocate, "failed to read shdr.");
            this.d[i2] = new c(byteBufferAllocate, this.b.a[4], objArr == true ? 1 : 0);
        }
        if (this.b.n > 0) {
            c cVar = this.d[this.b.n];
            ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate((int) cVar.f);
            this.a.getChannel().position(cVar.e);
            b(this.a.getChannel(), byteBufferAllocate2, "failed to read section: " + cVar.k);
            for (c cVar2 : this.d) {
                byteBufferAllocate2.position(cVar2.a);
                cVar2.k = a(byteBufferAllocate2);
                this.e.put(cVar2.k, cVar2);
            }
        }
    }

    private static String a(ByteBuffer byteBuffer) {
        byte[] bArrArray = byteBuffer.array();
        int iPosition = byteBuffer.position();
        while (byteBuffer.hasRemaining() && bArrArray[byteBuffer.position()] != 0) {
            byteBuffer.position(byteBuffer.position() + 1);
        }
        byteBuffer.position(byteBuffer.position() + 1);
        return new String(bArrArray, iPosition, (byteBuffer.position() - iPosition) - 1, Charset.forName("ASCII"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(FileChannel fileChannel, ByteBuffer byteBuffer, String str) throws IOException {
        byteBuffer.rewind();
        int i = fileChannel.read(byteBuffer);
        if (i != byteBuffer.limit()) {
            throw new IOException(str + " Rest bytes insufficient, expect to read " + byteBuffer.limit() + " bytes but only " + i + " bytes were read.");
        }
        byteBuffer.flip();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.a.close();
        this.e.clear();
        this.c = null;
        this.d = null;
    }

    static class a {
        public final byte[] a;
        public final short b;
        public final short c;
        public final int d;
        public final long e;
        public final long f;
        public final long g;
        public final int h;
        public final short i;
        public final short j;
        public final short k;
        public final short l;
        public final short m;
        public final short n;

        /* synthetic */ a(FileChannel fileChannel, byte b) {
            this(fileChannel);
        }

        private a(FileChannel fileChannel) throws IOException {
            this.a = new byte[16];
            fileChannel.position(0L);
            fileChannel.read(ByteBuffer.wrap(this.a));
            byte[] bArr = this.a;
            if (bArr[0] != 127 || bArr[1] != 69 || bArr[2] != 76 || bArr[3] != 70) {
                throw new IOException(String.format("bad elf magic: %x %x %x %x.", Byte.valueOf(this.a[0]), Byte.valueOf(this.a[1]), Byte.valueOf(this.a[2]), Byte.valueOf(this.a[3])));
            }
            h.a(bArr[4], 2, "bad elf class: " + ((int) this.a[4]));
            h.a(this.a[5], 2, "bad elf data encoding: " + ((int) this.a[5]));
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(this.a[4] == 1 ? 36 : 48);
            byteBufferAllocate.order(this.a[5] == 1 ? ByteOrder.LITTLE_ENDIAN : ByteOrder.BIG_ENDIAN);
            h.b(fileChannel, byteBufferAllocate, "failed to read rest part of ehdr.");
            this.b = byteBufferAllocate.getShort();
            this.c = byteBufferAllocate.getShort();
            int i = byteBufferAllocate.getInt();
            this.d = i;
            h.a(i, 1, "bad elf version: " + this.d);
            byte b = this.a[4];
            if (b == 1) {
                this.e = byteBufferAllocate.getInt();
                this.f = byteBufferAllocate.getInt();
                this.g = byteBufferAllocate.getInt();
            } else if (b == 2) {
                this.e = byteBufferAllocate.getLong();
                this.f = byteBufferAllocate.getLong();
                this.g = byteBufferAllocate.getLong();
            } else {
                throw new IOException("Unexpected elf class: " + ((int) this.a[4]));
            }
            this.h = byteBufferAllocate.getInt();
            this.i = byteBufferAllocate.getShort();
            this.j = byteBufferAllocate.getShort();
            this.k = byteBufferAllocate.getShort();
            this.l = byteBufferAllocate.getShort();
            this.m = byteBufferAllocate.getShort();
            this.n = byteBufferAllocate.getShort();
        }
    }

    static class b {
        public final int a;
        public final int b;
        public final long c;
        public final long d;
        public final long e;
        public final long f;
        public final long g;
        public final long h;

        /* synthetic */ b(ByteBuffer byteBuffer, int i, byte b) {
            this(byteBuffer, i);
        }

        private b(ByteBuffer byteBuffer, int i) throws IOException {
            if (i == 1) {
                this.a = byteBuffer.getInt();
                this.c = byteBuffer.getInt();
                this.d = byteBuffer.getInt();
                this.e = byteBuffer.getInt();
                this.f = byteBuffer.getInt();
                this.g = byteBuffer.getInt();
                this.b = byteBuffer.getInt();
                this.h = byteBuffer.getInt();
                return;
            }
            if (i == 2) {
                this.a = byteBuffer.getInt();
                this.b = byteBuffer.getInt();
                this.c = byteBuffer.getLong();
                this.d = byteBuffer.getLong();
                this.e = byteBuffer.getLong();
                this.f = byteBuffer.getLong();
                this.g = byteBuffer.getLong();
                this.h = byteBuffer.getLong();
                return;
            }
            throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
        }
    }

    static class c {
        public final int a;
        public final int b;
        public final long c;
        public final long d;
        public final long e;
        public final long f;
        public final int g;
        public final int h;
        public final long i;
        public final long j;
        public String k;

        /* synthetic */ c(ByteBuffer byteBuffer, int i, byte b) {
            this(byteBuffer, i);
        }

        private c(ByteBuffer byteBuffer, int i) throws IOException {
            if (i == 1) {
                this.a = byteBuffer.getInt();
                this.b = byteBuffer.getInt();
                this.c = byteBuffer.getInt();
                this.d = byteBuffer.getInt();
                this.e = byteBuffer.getInt();
                this.f = byteBuffer.getInt();
                this.g = byteBuffer.getInt();
                this.h = byteBuffer.getInt();
                this.i = byteBuffer.getInt();
                this.j = byteBuffer.getInt();
            } else if (i == 2) {
                this.a = byteBuffer.getInt();
                this.b = byteBuffer.getInt();
                this.c = byteBuffer.getLong();
                this.d = byteBuffer.getLong();
                this.e = byteBuffer.getLong();
                this.f = byteBuffer.getLong();
                this.g = byteBuffer.getInt();
                this.h = byteBuffer.getInt();
                this.i = byteBuffer.getLong();
                this.j = byteBuffer.getLong();
            } else {
                throw new IOException("Unexpected elf class: ".concat(String.valueOf(i)));
            }
            this.k = null;
        }
    }

    static /* synthetic */ void a(int i, int i2, String str) throws IOException {
        if (i <= 0 || i > i2) {
            throw new IOException(str);
        }
    }
}
