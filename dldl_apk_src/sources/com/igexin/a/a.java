package com.igexin.a;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import com.igexin.a.c;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class a implements c.a {
    private static final int a = 5;
    private static final int b = 4096;

    /* JADX INFO: renamed from: com.igexin.a.a$a, reason: collision with other inner class name */
    static class C0052a {
        public ZipFile a;
        public ZipEntry b;

        public C0052a(ZipFile zipFile, ZipEntry zipEntry) {
            this.a = zipFile;
            this.b = zipEntry;
        }
    }

    private static long a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i == -1) {
                outputStream.flush();
                return j;
            }
            outputStream.write(bArr, 0, i);
            j += (long) i;
        }
    }

    private static C0052a a(Context context, String[] strArr, String str) {
        String[] strArr2;
        int i;
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            strArr2 = new String[]{applicationInfo.sourceDir};
        } else {
            strArr2 = new String[applicationInfo.splitSourceDirs.length + 1];
            strArr2[0] = applicationInfo.sourceDir;
            System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr2, 1, applicationInfo.splitSourceDirs.length);
        }
        ZipFile zipFile = null;
        for (String str2 : strArr2) {
            int i2 = 0;
            while (true) {
                int i3 = i2 + 1;
                i = 5;
                if (i2 >= 5) {
                    break;
                }
                try {
                    zipFile = new ZipFile(new File(str2), 1);
                    break;
                } catch (IOException unused) {
                    i2 = i3;
                }
            }
            if (zipFile != null) {
                int i4 = 0;
                while (true) {
                    int i5 = i4 + 1;
                    if (i4 < i) {
                        int length = strArr.length;
                        int i6 = 0;
                        while (i6 < length) {
                            String str3 = "lib" + File.separatorChar + strArr[i6] + File.separatorChar + str;
                            d.a("Looking for %s in APK %s...", str3, str2);
                            ZipEntry entry = zipFile.getEntry(str3);
                            if (entry != null) {
                                return new C0052a(zipFile, entry);
                            }
                            i6++;
                            i = 5;
                        }
                        i4 = i5;
                    } else {
                        try {
                            zipFile.close();
                            break;
                        } catch (IOException unused2) {
                        }
                    }
                }
            }
        }
        return null;
    }

    private static void a(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }

    private static String[] a(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    @Override // com.igexin.a.c.a
    public final void a(Context context, String[] strArr, String str, File file) throws Throwable {
        C0052a c0052aA;
        InputStream inputStream;
        FileOutputStream fileOutputStream;
        long jA;
        FileOutputStream fileOutputStream2 = null;
        try {
            c0052aA = a(context, strArr, str);
            try {
                if (c0052aA == null) {
                    throw new b(str);
                }
                int i = 0;
                while (true) {
                    int i2 = i + 1;
                    if (i >= 5) {
                        if (c0052aA != null) {
                            try {
                                if (c0052aA.a != null) {
                                    c0052aA.a.close();
                                    return;
                                }
                                return;
                            } catch (IOException unused) {
                                return;
                            }
                        }
                        return;
                    }
                    d.a("Found %s! Extracting...", str);
                    try {
                        if (file.exists() || file.createNewFile()) {
                            try {
                                inputStream = c0052aA.a.getInputStream(c0052aA.b);
                                try {
                                    fileOutputStream = new FileOutputStream(file);
                                } catch (FileNotFoundException unused2) {
                                    fileOutputStream = null;
                                } catch (IOException unused3) {
                                    fileOutputStream = null;
                                } catch (Throwable th) {
                                    th = th;
                                }
                                try {
                                    jA = a(inputStream, fileOutputStream);
                                    fileOutputStream.getFD().sync();
                                } catch (FileNotFoundException unused4) {
                                    a(inputStream);
                                } catch (IOException unused5) {
                                    a(inputStream);
                                } catch (Throwable th2) {
                                    th = th2;
                                    fileOutputStream2 = fileOutputStream;
                                    a(inputStream);
                                    a(fileOutputStream2);
                                    throw th;
                                }
                            } catch (FileNotFoundException unused6) {
                                inputStream = null;
                                fileOutputStream = null;
                            } catch (IOException unused7) {
                                inputStream = null;
                                fileOutputStream = null;
                            } catch (Throwable th3) {
                                th = th3;
                                inputStream = null;
                            }
                            if (jA == file.length()) {
                                a(inputStream);
                                a(fileOutputStream);
                                file.setReadable(true, false);
                                file.setExecutable(true, false);
                                file.setWritable(true);
                                if (c0052aA != null) {
                                    try {
                                        if (c0052aA.a != null) {
                                            c0052aA.a.close();
                                            return;
                                        }
                                        return;
                                    } catch (IOException unused8) {
                                        return;
                                    }
                                }
                                return;
                            }
                            a(inputStream);
                            a(fileOutputStream);
                        }
                    } catch (IOException unused9) {
                    }
                    i = i2;
                }
            } catch (Throwable th4) {
                th = th4;
                if (c0052aA != null) {
                    try {
                        if (c0052aA.a != null) {
                            c0052aA.a.close();
                        }
                    } catch (IOException unused10) {
                    }
                }
                throw th;
            }
        } catch (Throwable th5) {
            th = th5;
            c0052aA = null;
        }
    }
}
