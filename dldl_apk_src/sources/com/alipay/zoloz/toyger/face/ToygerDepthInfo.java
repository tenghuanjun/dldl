package com.alipay.zoloz.toyger.face;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes4.dex */
public class ToygerDepthInfo {
    public byte[] depthInfo = null;

    public void depthBlobInfo(byte[] bArr) throws Throwable {
        GZIPOutputStream gZIPOutputStream = null;
        try {
            GZIPOutputStream gZIPOutputStream2 = new GZIPOutputStream(new ByteArrayOutputStream());
            try {
                gZIPOutputStream2.write(bArr);
                try {
                    gZIPOutputStream2.close();
                } catch (IOException unused) {
                } finally {
                }
            } catch (IOException unused2) {
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (IOException unused3) {
                    } finally {
                    }
                }
            } catch (Throwable th) {
                th = th;
                gZIPOutputStream = gZIPOutputStream2;
                if (gZIPOutputStream != null) {
                    try {
                        gZIPOutputStream.close();
                    } catch (IOException unused4) {
                    } finally {
                    }
                }
                throw th;
            }
        } catch (IOException unused5) {
        } catch (Throwable th2) {
            th = th2;
        }
    }
}
