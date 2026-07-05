package com.bytedance.pangle.util.b.a;

import com.bytedance.pangle.util.b.b.d;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    public static void a(d dVar, ByteArrayOutputStream byteArrayOutputStream) throws IOException {
        if (dVar.a == null || dVar.a.a == null || dVar.a.a.size() <= 0) {
            return;
        }
        for (com.bytedance.pangle.util.b.b.c cVar : dVar.a.a) {
            c cVar2 = dVar.c;
            if (cVar == null) {
                throw new IOException("input parameters is null, cannot write local file header");
            }
            byte[] bArr = {0, 0};
            cVar2.a((OutputStream) byteArrayOutputStream, 33639248);
            cVar2.a(byteArrayOutputStream, 0);
            cVar2.a(byteArrayOutputStream, 0);
            cVar2.a(byteArrayOutputStream, 0);
            cVar2.a(byteArrayOutputStream, cVar.a);
            cVar2.a(byteArrayOutputStream, 2081);
            cVar2.a(byteArrayOutputStream, 545);
            cVar2.a((OutputStream) byteArrayOutputStream, (int) cVar.b);
            cVar2.a((OutputStream) byteArrayOutputStream, (int) cVar.c);
            cVar2.a((OutputStream) byteArrayOutputStream, (int) cVar.d);
            byte[] bytes = new byte[0];
            if (cVar.h != null && cVar.h.trim().length() > 0) {
                bytes = cVar.h.getBytes(Charset.forName("UTF-8"));
            }
            cVar2.a(byteArrayOutputStream, bytes.length);
            int i = cVar.f;
            cVar2.a(byteArrayOutputStream, i);
            cVar2.a(byteArrayOutputStream, 0);
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr);
            byteArrayOutputStream.write(bArr);
            cVar2.a((OutputStream) byteArrayOutputStream, (int) cVar.i);
            if (bytes.length > 0) {
                byteArrayOutputStream.write(bytes);
            }
            if (i > 0) {
                byteArrayOutputStream.write(new byte[i]);
            }
        }
    }
}
