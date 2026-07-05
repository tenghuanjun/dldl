package com.huya.mtp.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.text.TextUtils;
import com.huya.mtp.api.MTPApi;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ApkLibraryInstaller {
    private static final int COPY_BUFFER_SIZE = 4096;
    private static final int MAX_TRIES = 5;

    private static String[] sourceDirectories(Context context) {
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        if (Build.VERSION.SDK_INT < 21 || applicationInfo.splitSourceDirs == null || applicationInfo.splitSourceDirs.length == 0) {
            return new String[]{applicationInfo.sourceDir};
        }
        String[] strArr = new String[applicationInfo.splitSourceDirs.length + 1];
        strArr[0] = applicationInfo.sourceDir;
        System.arraycopy(applicationInfo.splitSourceDirs, 0, strArr, 1, applicationInfo.splitSourceDirs.length);
        return strArr;
    }

    private static class ZipFileInZipEntry {
        public ZipEntry zipEntry;
        public ZipFile zipFile;

        public ZipFileInZipEntry(ZipFile zipFile, ZipEntry zipEntry) {
            this.zipFile = zipFile;
            this.zipEntry = zipEntry;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:29:0x005f, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static com.huya.mtp.utils.ApkLibraryInstaller.ZipFileInZipEntry findAPKWithLibrary(android.content.Context r12, java.lang.String[] r13, java.lang.String r14) {
        /*
            java.lang.String[] r12 = sourceDirectories(r12)
            int r0 = r12.length
            r1 = 0
            r2 = 0
            r4 = r1
            r3 = 0
        L9:
            if (r3 >= r0) goto L62
            r5 = r12[r3]
            r6 = 0
        Le:
            int r7 = r6 + 1
            r8 = 5
            if (r6 >= r8) goto L22
            java.util.zip.ZipFile r6 = new java.util.zip.ZipFile     // Catch: java.io.IOException -> L20
            java.io.File r9 = new java.io.File     // Catch: java.io.IOException -> L20
            r9.<init>(r5)     // Catch: java.io.IOException -> L20
            r10 = 1
            r6.<init>(r9, r10)     // Catch: java.io.IOException -> L20
            r4 = r6
            goto L22
        L20:
            r6 = r7
            goto Le
        L22:
            if (r4 != 0) goto L25
            goto L5f
        L25:
            r5 = 0
        L26:
            int r6 = r5 + 1
            if (r5 >= r8) goto L5f
            int r5 = r13.length
            r7 = 0
        L2c:
            if (r7 >= r5) goto L5d
            r9 = r13[r7]
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = "lib"
            r10.append(r11)
            char r11 = java.io.File.separatorChar
            r10.append(r11)
            r10.append(r9)
            char r9 = java.io.File.separatorChar
            r10.append(r9)
            r10.append(r14)
            java.lang.String r9 = r10.toString()
            java.util.zip.ZipEntry r9 = r4.getEntry(r9)
            if (r9 == 0) goto L5a
            com.huya.mtp.utils.ApkLibraryInstaller$ZipFileInZipEntry r12 = new com.huya.mtp.utils.ApkLibraryInstaller$ZipFileInZipEntry
            r12.<init>(r4, r9)
            return r12
        L5a:
            int r7 = r7 + 1
            goto L2c
        L5d:
            r5 = r6
            goto L26
        L5f:
            int r3 = r3 + 1
            goto L9
        L62:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.ApkLibraryInstaller.findAPKWithLibrary(android.content.Context, java.lang.String[], java.lang.String):com.huya.mtp.utils.ApkLibraryInstaller$ZipFileInZipEntry");
    }

    public static void installLibrary(Context context, String str) throws Throwable {
        ZipFileInZipEntry zipFileInZipEntryFindAPKWithLibrary;
        FileOutputStream fileOutputStream;
        InputStream inputStream;
        long jCopy;
        String strMapLibraryName = System.mapLibraryName(str);
        File file = new File(context.getDir("lib", 0), strMapLibraryName);
        file.delete();
        ZipFileInZipEntry zipFileInZipEntry = null;
        InputStream inputStream2 = null;
        try {
            zipFileInZipEntryFindAPKWithLibrary = findAPKWithLibrary(context, supportedAbis(), strMapLibraryName);
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (zipFileInZipEntryFindAPKWithLibrary == null) {
                MTPApi.LOGGER.error("ApkLibraryInstaller", "Does not exist in any APK");
                if (zipFileInZipEntryFindAPKWithLibrary != null) {
                    try {
                        if (zipFileInZipEntryFindAPKWithLibrary.zipFile != null) {
                            zipFileInZipEntryFindAPKWithLibrary.zipFile.close();
                        }
                    } catch (IOException unused) {
                        return;
                    }
                }
                System.load(file.getAbsolutePath());
                return;
            }
            int i = 0;
            while (true) {
                int i2 = i + 1;
                if (i >= 5) {
                    if (zipFileInZipEntryFindAPKWithLibrary != null) {
                        try {
                            if (zipFileInZipEntryFindAPKWithLibrary.zipFile != null) {
                                zipFileInZipEntryFindAPKWithLibrary.zipFile.close();
                            }
                        } catch (IOException unused2) {
                            return;
                        }
                    }
                    System.load(file.getAbsolutePath());
                    return;
                }
                try {
                    if (file.exists() || file.createNewFile()) {
                        try {
                            inputStream = zipFileInZipEntryFindAPKWithLibrary.zipFile.getInputStream(zipFileInZipEntryFindAPKWithLibrary.zipEntry);
                            try {
                                fileOutputStream = new FileOutputStream(file);
                                try {
                                    jCopy = copy(inputStream, fileOutputStream);
                                    fileOutputStream.getFD().sync();
                                } catch (FileNotFoundException unused3) {
                                    closeSilently(inputStream);
                                } catch (IOException unused4) {
                                    closeSilently(inputStream);
                                } catch (Throwable th2) {
                                    th = th2;
                                    inputStream2 = inputStream;
                                    closeSilently(inputStream2);
                                    closeSilently(fileOutputStream);
                                    throw th;
                                }
                            } catch (FileNotFoundException unused5) {
                                fileOutputStream = null;
                            } catch (IOException unused6) {
                                fileOutputStream = null;
                            } catch (Throwable th3) {
                                th = th3;
                                fileOutputStream = null;
                            }
                        } catch (FileNotFoundException unused7) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (IOException unused8) {
                            inputStream = null;
                            fileOutputStream = null;
                        } catch (Throwable th4) {
                            th = th4;
                            fileOutputStream = null;
                        }
                        if (jCopy == file.length()) {
                            closeSilently(inputStream);
                            closeSilently(fileOutputStream);
                            file.setReadable(true, false);
                            file.setExecutable(true, false);
                            file.setWritable(true);
                            if (zipFileInZipEntryFindAPKWithLibrary != null) {
                                try {
                                    if (zipFileInZipEntryFindAPKWithLibrary.zipFile != null) {
                                        zipFileInZipEntryFindAPKWithLibrary.zipFile.close();
                                    }
                                } catch (IOException unused9) {
                                    return;
                                }
                            }
                            System.load(file.getAbsolutePath());
                            return;
                        }
                        closeSilently(inputStream);
                        closeSilently(fileOutputStream);
                    }
                } catch (IOException unused10) {
                }
                i = i2;
            }
        } catch (Throwable th5) {
            th = th5;
            zipFileInZipEntry = zipFileInZipEntryFindAPKWithLibrary;
            if (zipFileInZipEntry != null) {
                try {
                    if (zipFileInZipEntry.zipFile != null) {
                        zipFileInZipEntry.zipFile.close();
                    }
                } catch (IOException unused11) {
                    throw th;
                }
            }
            System.load(file.getAbsolutePath());
            throw th;
        }
    }

    private static String[] supportedAbis() {
        if (Build.VERSION.SDK_INT < 21 || Build.SUPPORTED_ABIS.length <= 0) {
            return !TextUtils.isEmpty(Build.CPU_ABI2) ? new String[]{Build.CPU_ABI, Build.CPU_ABI2} : new String[]{Build.CPU_ABI};
        }
        return Build.SUPPORTED_ABIS;
    }

    private static long copy(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[4096];
        long j = 0;
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                outputStream.write(bArr, 0, i);
                j += (long) i;
            } else {
                outputStream.flush();
                return j;
            }
        }
    }

    private static void closeSilently(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
