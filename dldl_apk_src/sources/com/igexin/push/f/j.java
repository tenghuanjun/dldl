package com.igexin.push.f;

import com.alibaba.sdk.android.oss.common.OSSConstants;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class j {
    String a;
    String b;
    File c;
    private byte[] d;

    private j(File file) {
        this.c = file;
    }

    private j(String str) {
        this(new File(str));
    }

    private j(String str, byte[] bArr) {
        this.a = str;
        this.d = bArr;
    }

    private j(String str, byte[] bArr, String str2) {
        this(str, bArr);
        this.b = str2;
    }

    private j(byte[] bArr) {
        this.d = bArr;
    }

    private static String a(byte[] bArr) {
        String str = null;
        if (bArr != null && bArr.length >= 10) {
            if (bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70) {
                str = "GIF";
            } else if (bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
                str = "PNG";
            } else if (bArr[6] == 74 && bArr[7] == 70 && bArr[8] == 73 && bArr[9] == 70) {
                str = "JPG";
            } else if (bArr[0] == 66 && bArr[1] == 77) {
                str = "BMP";
            }
        }
        return "JPG".equals(str) ? "image/jpeg" : "GIF".equals(str) ? "image/gif" : "PNG".equals(str) ? "image/png" : "BMP".equals(str) ? "image/bmp" : OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
    }

    private String b() {
        File file;
        if (this.a == null && (file = this.c) != null && file.exists()) {
            this.a = this.c.getName();
        }
        return this.a;
    }

    private static String b(byte[] bArr) {
        if (bArr != null && bArr.length >= 10) {
            if (bArr[0] == 71 && bArr[1] == 73 && bArr[2] == 70) {
                return "GIF";
            }
            if (bArr[1] == 80 && bArr[2] == 78 && bArr[3] == 71) {
                return "PNG";
            }
            if (bArr[6] == 74 && bArr[7] == 70 && bArr[8] == 73 && bArr[9] == 70) {
                return "JPG";
            }
            if (bArr[0] == 66 && bArr[1] == 77) {
                return "BMP";
            }
        }
        return null;
    }

    private String c() throws Throwable {
        if (this.b == null) {
            byte[] bArrA = a();
            String str = null;
            if (bArrA != null && bArrA.length >= 10) {
                if (bArrA[0] == 71 && bArrA[1] == 73 && bArrA[2] == 70) {
                    str = "GIF";
                } else if (bArrA[1] == 80 && bArrA[2] == 78 && bArrA[3] == 71) {
                    str = "PNG";
                } else if (bArrA[6] == 74 && bArrA[7] == 70 && bArrA[8] == 73 && bArrA[9] == 70) {
                    str = "JPG";
                } else if (bArrA[0] == 66 && bArrA[1] == 77) {
                    str = "BMP";
                }
            }
            this.b = "JPG".equals(str) ? "image/jpeg" : "GIF".equals(str) ? "image/gif" : "PNG".equals(str) ? "image/png" : "BMP".equals(str) ? "image/bmp" : OSSConstants.DEFAULT_OBJECT_CONTENT_TYPE;
        }
        return this.b;
    }

    public final byte[] a() throws Throwable {
        File file;
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        FileInputStream fileInputStream;
        if (this.d == null && (file = this.c) != null && file.exists()) {
            try {
                fileInputStream = new FileInputStream(this.c);
                try {
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    while (true) {
                        try {
                            int i = fileInputStream.read();
                            if (i == -1) {
                                break;
                            }
                            byteArrayOutputStream.write(i);
                        } catch (Throwable th2) {
                            th = th2;
                            if (byteArrayOutputStream != null) {
                                byteArrayOutputStream.close();
                            }
                            if (fileInputStream != null) {
                                fileInputStream.close();
                            }
                            throw th;
                        }
                    }
                    this.d = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.close();
                    fileInputStream.close();
                } catch (Throwable th3) {
                    byteArrayOutputStream = null;
                    th = th3;
                }
            } catch (Throwable th4) {
                byteArrayOutputStream = null;
                th = th4;
                fileInputStream = null;
            }
        }
        return this.d;
    }
}
