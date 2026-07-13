package com.tencent.open.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.ProtocolException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Properties;
import java.util.zip.ZipException;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public final class b {
    private static final n a = new n(101010256);
    private static final o b = new o(38651);

    /* JADX INFO: compiled from: ProGuard */
    private static class a {
        Properties a;
        byte[] b;

        private a() {
            this.a = new Properties();
        }

        void a(byte[] bArr) throws IOException {
            if (bArr == null) {
                return;
            }
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            int length = b.b.a().length;
            byte[] bArr2 = new byte[length];
            byteBufferWrap.get(bArr2);
            if (!b.b.equals(new o(bArr2))) {
                throw new ProtocolException("unknow protocl [" + Arrays.toString(bArr) + "]");
            }
            if (bArr.length - length <= 2) {
                return;
            }
            byte[] bArr3 = new byte[2];
            byteBufferWrap.get(bArr3);
            int iB = new o(bArr3).b();
            if ((bArr.length - length) - 2 < iB) {
                return;
            }
            byte[] bArr4 = new byte[iB];
            byteBufferWrap.get(bArr4);
            this.a.load(new ByteArrayInputStream(bArr4));
            int length2 = ((bArr.length - length) - iB) - 2;
            if (length2 > 0) {
                byte[] bArr5 = new byte[length2];
                this.b = bArr5;
                byteBufferWrap.get(bArr5);
            }
        }

        public String toString() {
            return "ApkExternalInfo [p=" + this.a + ", otherData=" + Arrays.toString(this.b) + "]";
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static String a(File file, String str) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        Object[] objArr = 0;
        try {
            RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
            try {
                byte[] bArrA = a(randomAccessFile2);
                if (bArrA != null) {
                    a aVar = new a();
                    aVar.a(bArrA);
                    String property = aVar.a.getProperty(str);
                    randomAccessFile2.close();
                    return property;
                }
                randomAccessFile2.close();
                return null;
            } catch (Throwable th) {
                th = th;
                randomAccessFile = randomAccessFile2;
                if (randomAccessFile != null) {
                    randomAccessFile.close();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String a(File file) throws IOException {
        return a(file, "channelNo");
    }

    private static byte[] a(RandomAccessFile randomAccessFile) throws IOException {
        long length = randomAccessFile.length() - 22;
        randomAccessFile.seek(length);
        byte[] bArrA = a.a();
        int i = randomAccessFile.read();
        while (i != -1) {
            if (i != bArrA[0] || randomAccessFile.read() != bArrA[1] || randomAccessFile.read() != bArrA[2] || randomAccessFile.read() != bArrA[3]) {
                length--;
                randomAccessFile.seek(length);
                i = randomAccessFile.read();
            } else {
                randomAccessFile.seek(length + 20);
                byte[] bArr = new byte[2];
                randomAccessFile.readFully(bArr);
                int iB = new o(bArr).b();
                if (iB == 0) {
                    return null;
                }
                byte[] bArr2 = new byte[iB];
                randomAccessFile.read(bArr2);
                return bArr2;
            }
        }
        throw new ZipException("archive is not a ZIP archive");
    }
}
