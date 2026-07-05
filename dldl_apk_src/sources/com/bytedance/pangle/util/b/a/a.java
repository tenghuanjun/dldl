package com.bytedance.pangle.util.b.a;

import com.bytedance.pangle.util.b.b.d;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static d a(String str) throws Throwable {
        RandomAccessFile randomAccessFile;
        RandomAccessFile randomAccessFile2 = null;
        try {
            randomAccessFile = new RandomAccessFile(str, "r");
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (randomAccessFile.length() < 22) {
                throw new IOException("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
            }
            d dVar = new d(str);
            long length = randomAccessFile.length();
            if (length < 22) {
                throw new IOException("Zip file size less than size of zip headers. Probably not a zip file.");
            }
            long jB = length - 22;
            randomAccessFile.seek(jB);
            if (dVar.c.a(randomAccessFile) != 101010256) {
                jB = b(randomAccessFile, dVar);
            }
            randomAccessFile.seek(jB + 4);
            com.bytedance.pangle.util.b.b.b bVar = new com.bytedance.pangle.util.b.b.b();
            randomAccessFile.skipBytes(6);
            bVar.a = dVar.c.b(randomAccessFile);
            randomAccessFile.skipBytes(4);
            bVar.b = dVar.c.a(randomAccessFile);
            dVar.b = bVar;
            if (dVar.b.a != 0) {
                a(randomAccessFile, dVar);
                try {
                    randomAccessFile.close();
                } catch (IOException unused) {
                }
                return dVar;
            }
            try {
                randomAccessFile.close();
            } catch (IOException unused2) {
            }
            return dVar;
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile2 = randomAccessFile;
            if (randomAccessFile2 != null) {
                try {
                    randomAccessFile2.close();
                } catch (IOException unused3) {
                }
            }
            throw th;
        }
    }

    private static void a(RandomAccessFile randomAccessFile, d dVar) throws IOException {
        com.bytedance.pangle.util.b.b.a aVar = new com.bytedance.pangle.util.b.b.a();
        ArrayList arrayList = new ArrayList();
        long j = dVar.b.b;
        long j2 = dVar.b.a;
        randomAccessFile.seek(j);
        for (int i = 0; i < j2; i++) {
            com.bytedance.pangle.util.b.b.c cVar = new com.bytedance.pangle.util.b.b.c();
            if (dVar.c.a(randomAccessFile) != 33639248) {
                throw new IOException("Expected central directory entry not found (#" + (i + 1) + ")");
            }
            randomAccessFile.skipBytes(6);
            cVar.a = dVar.c.b(randomAccessFile);
            randomAccessFile.skipBytes(4);
            cVar.b = dVar.c.a(randomAccessFile);
            cVar.c = dVar.c.a(randomAccessFile);
            cVar.d = dVar.c.a(randomAccessFile);
            int iB = dVar.c.b(randomAccessFile);
            cVar.e = iB;
            cVar.f = dVar.c.b(randomAccessFile);
            int iB2 = dVar.c.b(randomAccessFile);
            randomAccessFile.skipBytes(8);
            cVar.i = dVar.c.a(randomAccessFile);
            if (iB > 0) {
                byte[] bArr = new byte[iB];
                randomAccessFile.readFully(bArr);
                cVar.h = new String(bArr, Charset.forName("UTF-8"));
                randomAccessFile.skipBytes(cVar.f);
                if (iB2 > 0) {
                    randomAccessFile.skipBytes(iB2);
                }
                long filePointer = randomAccessFile.getFilePointer();
                randomAccessFile.seek(cVar.i + 28);
                cVar.g = dVar.c.b(randomAccessFile);
                randomAccessFile.seek(filePointer);
                arrayList.add(cVar);
            } else {
                throw new IOException("Invalid entry name in file header");
            }
        }
        aVar.a = arrayList;
        dVar.a = aVar;
    }

    private static long b(RandomAccessFile randomAccessFile, d dVar) throws IOException {
        long length = randomAccessFile.length() - 22;
        for (long length2 = randomAccessFile.length() < 65536 ? randomAccessFile.length() : 65536L; length2 > 0 && length > 0; length2--) {
            length--;
            randomAccessFile.seek(length);
            if (dVar.c.a(randomAccessFile) == 101010256) {
                return length;
            }
        }
        throw new IOException("Zip headers not found. Probably not a zip file");
    }
}
