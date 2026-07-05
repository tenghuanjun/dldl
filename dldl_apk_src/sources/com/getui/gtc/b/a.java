package com.getui.gtc.b;

import android.content.Context;
import com.getui.gtc.base.GtcProvider;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a {
    public static String a(Context context) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        FileInputStream fileInputStream;
        if (!new File(b(context)).exists()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            fileInputStream = new FileInputStream(b(context));
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e) {
                e = e;
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
        } catch (Exception e2) {
            e = e2;
            byteArrayOutputStream = null;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream = null;
            fileInputStream = null;
        }
        while (true) {
            try {
                try {
                    int i = fileInputStream.read(bArr);
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i);
                } catch (Exception e3) {
                    e = e3;
                    com.getui.gtc.i.c.a.c(e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e4) {
                            com.getui.gtc.i.c.a.c(e4);
                        }
                    }
                    if (byteArrayOutputStream == null) {
                        return null;
                    }
                    try {
                        byteArrayOutputStream.close();
                        return null;
                    } catch (Exception e5) {
                        com.getui.gtc.i.c.a.c(e5);
                        return null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e6) {
                    com.getui.gtc.i.c.a.c(e6);
                }
            }
            if (byteArrayOutputStream == null) {
                throw th;
            }
            try {
                byteArrayOutputStream.close();
                throw th;
            } catch (Exception e7) {
                com.getui.gtc.i.c.a.c(e7);
                throw th;
            }
        }
        String str = new String(com.getui.gtc.i.a.a.b(byteArrayOutputStream.toByteArray()), "utf-8");
        try {
            fileInputStream.close();
        } catch (Exception e8) {
            com.getui.gtc.i.c.a.c(e8);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception e9) {
            com.getui.gtc.i.c.a.c(e9);
        }
        return str;
    }

    private static String b(Context context) {
        if (context == null) {
            return null;
        }
        File file = new File(GtcProvider.getSdcardPath() + "/libs");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + context.getPackageName() + "_.db";
    }
}
