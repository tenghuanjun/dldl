package com.igexin.a.a;

import com.igexin.a.a.c;
import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import kotlin.UByte;
import kotlin.UShort;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class f implements c, Closeable {
    private final int a = 1179403647;
    private final FileChannel b;

    public f(File file) throws FileNotFoundException {
        if (file == null || !file.exists()) {
            throw new IllegalArgumentException("File is null or does not exist");
        }
        this.b = new FileInputStream(file).getChannel();
    }

    private static long a(c.b bVar, long j, long j2) throws IOException {
        for (long j3 = 0; j3 < j; j3++) {
            c.AbstractC0053c abstractC0053cA = bVar.a(j3);
            if (abstractC0053cA.c == 1 && abstractC0053cA.e <= j2 && j2 <= abstractC0053cA.e + abstractC0053cA.f) {
                return (j2 - abstractC0053cA.e) + abstractC0053cA.d;
            }
        }
        throw new IllegalStateException("Could not map vma to file offset!");
    }

    private void a(ByteBuffer byteBuffer, long j, int i) throws IOException {
        byteBuffer.position(0);
        byteBuffer.limit(i);
        long j2 = 0;
        while (j2 < i) {
            int i2 = this.b.read(byteBuffer, j + j2);
            if (i2 == -1) {
                throw new EOFException();
            }
            j2 += (long) i2;
        }
        byteBuffer.position(0);
    }

    private c.b b() throws IOException {
        this.b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (b(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sE = e(byteBufferAllocate, 4L);
        boolean z = e(byteBufferAllocate, 5L) == 2;
        if (sE == 1) {
            return new d(z, this);
        }
        if (sE == 2) {
            return new e(z, this);
        }
        throw new IllegalStateException("Invalid class type!");
    }

    private String d(ByteBuffer byteBuffer, long j) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (true) {
            long j2 = 1 + j;
            short sE = e(byteBuffer, j);
            if (sE == 0) {
                return sb.toString();
            }
            sb.append((char) sE);
            j = j2;
        }
    }

    private short e(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 1);
        return (short) (byteBuffer.get() & UByte.MAX_VALUE);
    }

    protected final long a(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 8);
        return byteBuffer.getLong();
    }

    public final List<String> a() throws IOException {
        c.b eVar;
        long j;
        long j2;
        c.a aVarA;
        long j3;
        this.b.position(0L);
        ArrayList arrayList = new ArrayList();
        this.b.position(0L);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(8);
        byteBufferAllocate.order(ByteOrder.LITTLE_ENDIAN);
        if (b(byteBufferAllocate, 0L) != 1179403647) {
            throw new IllegalArgumentException("Invalid ELF Magic!");
        }
        short sE = e(byteBufferAllocate, 4L);
        boolean z = e(byteBufferAllocate, 5L) == 2;
        if (sE == 1) {
            eVar = new d(z, this);
        } else {
            if (sE != 2) {
                throw new IllegalStateException("Invalid class type!");
            }
            eVar = new e(z, this);
        }
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(8);
        byteBufferAllocate2.order(eVar.d ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN);
        long j4 = eVar.i;
        if (j4 == 65535) {
            j4 = eVar.a().a;
        }
        long j5 = 0;
        while (true) {
            if (j5 >= j4) {
                j = j4;
                j2 = 0;
                break;
            }
            c.AbstractC0053c abstractC0053cA = eVar.a(j5);
            j = j4;
            if (abstractC0053cA.c == 2) {
                j2 = abstractC0053cA.d;
                break;
            }
            j5++;
            j4 = j;
        }
        if (j2 == 0) {
            return Collections.unmodifiableList(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        long j6 = 0;
        int i = 0;
        do {
            aVarA = eVar.a(j2, i);
            if (aVarA.d == 1) {
                arrayList2.add(Long.valueOf(aVarA.e));
                j3 = 5;
            } else {
                j3 = 5;
                if (aVarA.d == 5) {
                    j6 = aVarA.e;
                }
            }
            i++;
        } while (aVarA.d != 0);
        if (j6 == 0) {
            throw new IllegalStateException("String table offset not found!");
        }
        long jA = a(eVar, j, j6);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(d(byteBufferAllocate2, ((Long) it.next()).longValue() + jA));
        }
        return arrayList;
    }

    protected final long b(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 4);
        return ((long) byteBuffer.getInt()) & 4294967295L;
    }

    protected final int c(ByteBuffer byteBuffer, long j) throws IOException {
        a(byteBuffer, j, 2);
        return byteBuffer.getShort() & UShort.MAX_VALUE;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() throws IOException {
        this.b.close();
    }
}
