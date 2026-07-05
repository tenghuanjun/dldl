package com.bytedance.pangle.util.b;

import com.bytedance.pangle.util.b.b.d;
import com.bytedance.pangle.util.g;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    final d a;
    final com.bytedance.pangle.util.b.a.b b = new com.bytedance.pangle.util.b.a.b();

    public a(d dVar) {
        this.a = dVar;
    }

    static void a(File file) throws ZipException {
        if (file.exists() && !file.delete()) {
            throw new ZipException("Could not delete temporary file");
        }
    }

    static void a(RandomAccessFile randomAccessFile, RandomAccessFile randomAccessFile2, long j, long j2, String str) throws IOException {
        g.a(randomAccessFile, randomAccessFile2, j, j + j2, str);
    }
}
