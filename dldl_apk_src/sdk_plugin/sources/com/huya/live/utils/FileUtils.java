package com.huya.live.utils;

import android.content.Context;
import android.os.Environment;
import com.duowan.auk.ArkValue;
import com.duowan.auk.util.L;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private static int sBufferSize = 8192;

    private FileUtils() {
    }

    public static boolean removeDirOrFile(File file) {
        if (file == null || !file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            String[] list = file.list();
            if (list == null) {
                return file.delete();
            }
            for (String str : list) {
                if (!removeDirOrFile(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static boolean unZipResFile(File file, String str) throws Throwable {
        ZipFile zipFile;
        BufferedOutputStream bufferedOutputStream;
        BufferedInputStream bufferedInputStream;
        BufferedInputStream bufferedInputStream2 = null;
        try {
            zipFile = new ZipFile(file);
            try {
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                byte[] bArr = new byte[1024];
                while (enumerationEntries.hasMoreElements()) {
                    ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                    if (zipEntryNextElement.isDirectory()) {
                        new File(str + File.separator + zipEntryNextElement.getName()).mkdirs();
                    } else {
                        try {
                            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(new File(str + File.separator + zipEntryNextElement.getName())));
                            try {
                                bufferedInputStream = new BufferedInputStream(zipFile.getInputStream(zipEntryNextElement));
                                while (true) {
                                    try {
                                        int i = bufferedInputStream.read(bArr, 0, 1024);
                                        if (i != -1) {
                                            bufferedOutputStream.write(bArr, 0, i);
                                        } else {
                                            try {
                                                break;
                                            } catch (Exception e) {
                                                e = e;
                                                bufferedInputStream2 = bufferedInputStream;
                                                try {
                                                    L.error(TAG, "unZipResFile error:" + e);
                                                    close(bufferedInputStream2);
                                                    close(bufferedOutputStream);
                                                    close(zipFile);
                                                    file.delete();
                                                    return false;
                                                } catch (Throwable th) {
                                                    th = th;
                                                    close(bufferedInputStream2);
                                                    close(bufferedOutputStream);
                                                    close(zipFile);
                                                    file.delete();
                                                    throw th;
                                                }
                                            } catch (Throwable th2) {
                                                th = th2;
                                                bufferedInputStream2 = bufferedInputStream;
                                                close(bufferedInputStream2);
                                                close(bufferedOutputStream);
                                                close(zipFile);
                                                file.delete();
                                                throw th;
                                            }
                                        }
                                    } catch (Throwable th3) {
                                        th = th3;
                                        close(bufferedInputStream);
                                        close(bufferedOutputStream);
                                        throw th;
                                    }
                                }
                                close(bufferedInputStream);
                                close(bufferedOutputStream);
                            } catch (Throwable th4) {
                                th = th4;
                                bufferedInputStream = null;
                            }
                        } catch (Throwable th5) {
                            th = th5;
                            bufferedOutputStream = null;
                            bufferedInputStream = null;
                        }
                    }
                }
                close(null);
                close(null);
                close(zipFile);
                file.delete();
                return true;
            } catch (Exception e2) {
                e = e2;
                bufferedOutputStream = null;
            } catch (Throwable th6) {
                th = th6;
                bufferedOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            zipFile = null;
            bufferedOutputStream = null;
        } catch (Throwable th7) {
            th = th7;
            zipFile = null;
            bufferedOutputStream = null;
        }
    }

    public static boolean isExists(String str) {
        return new File(str).exists();
    }

    public static String readResourceFile(Context context, int i) {
        try {
            return convertStreamToString(context.getResources().openRawResource(i));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String convertStreamToString(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = bufferedReader.readLine();
            if (line != null) {
                sb.append(line);
            } else {
                bufferedReader.close();
                return sb.toString();
            }
        }
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveFile(String str, String str2) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                try {
                    bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + File.separator + str));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e2) {
                e = e2;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            bufferedOutputStream.write(str2.getBytes());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (IOException e3) {
            e = e3;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            if (bufferedOutputStream2 != null) {
                bufferedOutputStream2.close();
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            if (bufferedOutputStream2 != null) {
                try {
                    bufferedOutputStream2.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            throw th;
        }
    }

    public static boolean isSDMounted() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static final String getPrefix(String str) {
        return str.substring(0, str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR));
    }

    public static final String getSuffix(String str) {
        return str.substring(str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR) + 1);
    }

    /* JADX WARN: Code restructure failed: missing block: B:27:0x0045, code lost:
    
        if (r0 == 0) goto L50;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0056, code lost:
    
        if (r0 == 0) goto L50;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v11 */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v15 */
    /* JADX WARN: Type inference failed for: r0v16 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARN: Type inference failed for: r0v19 */
    /* JADX WARN: Type inference failed for: r0v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v20 */
    /* JADX WARN: Type inference failed for: r0v21 */
    /* JADX WARN: Type inference failed for: r0v22 */
    /* JADX WARN: Type inference failed for: r0v3 */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r0v5, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r0v6 */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r0v8 */
    /* JADX WARN: Type inference failed for: r0v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String fileMd5(java.io.File r5) throws java.lang.Throwable {
        /*
            r0 = 0
            if (r5 != 0) goto L4
            return r0
        L4:
            java.lang.StringBuffer r1 = new java.lang.StringBuffer
            r1.<init>()
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41 java.security.NoSuchAlgorithmException -> L4b java.io.FileNotFoundException -> L52
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L3f java.io.IOException -> L41 java.security.NoSuchAlgorithmException -> L4b java.io.FileNotFoundException -> L52
            r5 = 8192(0x2000, float:1.148E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
        L18:
            int r3 = r2.read(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r4 = -1
            if (r3 == r4) goto L24
            r4 = 0
            r0.update(r5, r4, r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            goto L18
        L24:
            byte[] r5 = r0.digest()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            java.lang.String r5 = bytesToHexString(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r1.append(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L39 java.io.FileNotFoundException -> L3c
            r2.close()     // Catch: java.io.IOException -> L59
            goto L59
        L33:
            r5 = move-exception
            r0 = r2
            goto L5e
        L36:
            r5 = move-exception
            r0 = r2
            goto L42
        L39:
            r5 = move-exception
            r0 = r2
            goto L4c
        L3c:
            r5 = move-exception
            r0 = r2
            goto L53
        L3f:
            r5 = move-exception
            goto L5e
        L41:
            r5 = move-exception
        L42:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
        L47:
            r0.close()     // Catch: java.io.IOException -> L59
            goto L59
        L4b:
            r5 = move-exception
        L4c:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
            goto L47
        L52:
            r5 = move-exception
        L53:
            r5.printStackTrace()     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L59
            goto L47
        L59:
            java.lang.String r5 = r1.toString()
            return r5
        L5e:
            if (r0 == 0) goto L63
            r0.close()     // Catch: java.io.IOException -> L63
        L63:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.live.utils.FileUtils.fileMd5(java.io.File):java.lang.String");
    }

    public static String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                stringBuffer.append("0");
            }
            stringBuffer.append(Integer.toHexString(i));
        }
        return stringBuffer.toString();
    }

    public static String getFileChunkMd5(File file, long j, int i) throws Throwable {
        RandomAccessFile randomAccessFile = null;
        try {
            try {
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("MD5");
                    if (file == null || !file.exists()) {
                        return "";
                    }
                    RandomAccessFile randomAccessFile2 = new RandomAccessFile(file, "r");
                    try {
                        byte[] bArr = new byte[i];
                        randomAccessFile2.seek(j);
                        int i2 = randomAccessFile2.read(bArr);
                        if (i2 != -1) {
                            messageDigest.update(bArr, 0, i2);
                        }
                        String string = new BigInteger(1, messageDigest.digest()).toString(16);
                        while (string.length() < 32) {
                            string = "0" + string;
                        }
                        try {
                            randomAccessFile2.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        return string;
                    } catch (FileNotFoundException e2) {
                        e = e2;
                        randomAccessFile = randomAccessFile2;
                    } catch (IOException e3) {
                        e = e3;
                        randomAccessFile = randomAccessFile2;
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        return "";
                    } catch (NoSuchAlgorithmException e4) {
                        e = e4;
                        randomAccessFile = randomAccessFile2;
                        e.printStackTrace();
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        randomAccessFile = randomAccessFile2;
                        if (randomAccessFile != null) {
                            try {
                                randomAccessFile.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
            } catch (FileNotFoundException e7) {
                e = e7;
            } catch (IOException e8) {
                e = e8;
            } catch (NoSuchAlgorithmException e9) {
                e = e9;
            }
            e.printStackTrace();
            if (randomAccessFile != null) {
                randomAccessFile.close();
            }
            return "";
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static String getVideoCoverPath(Context context, String str) {
        String path;
        String str2 = "";
        if ("mounted".equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            if (context.getExternalCacheDir() == null) {
                return "";
            }
            path = context.getExternalCacheDir().getPath();
        } else {
            path = context.getCacheDir().getPath();
        }
        if (ArkValue.debuggable()) {
            str2 = "testEnv" + File.separator;
        }
        return path + File.separator + str2 + str;
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String getFileNameWithoutExt(File file) {
        int iLastIndexOf;
        String name = file.getName();
        return (name == null || name.length() <= 0 || (iLastIndexOf = name.lastIndexOf(46)) <= -1 || iLastIndexOf >= name.length()) ? name : name.substring(0, iLastIndexOf);
    }

    public static boolean isSDCardEnable() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v6, types: [java.io.Closeable[]] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.StringBuilder] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v2 */
    /* JADX WARN: Type inference failed for: r1v6, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.Closeable[]] */
    public static String getSDCardPath() throws Throwable {
        ?? r1 = 0;
        BufferedReader bufferedReader = null;
        if (!isSDCardEnable()) {
            return null;
        }
        try {
            try {
                Process processExec = Runtime.getRuntime().exec("cat /proc/mounts");
                BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(new BufferedInputStream(processExec.getInputStream())));
                while (true) {
                    try {
                        String line = bufferedReader2.readLine();
                        if (line != null) {
                            if (line.contains("sdcard") && line.contains(".android_secure")) {
                                String[] strArrSplit = line.split(" ");
                                if (strArrSplit.length >= 5) {
                                    String str = strArrSplit[1].replace("/.android_secure", "") + File.separator;
                                    CloseUtils.closeIO(bufferedReader2);
                                    return str;
                                }
                            }
                            if (processExec.waitFor() != 0 && processExec.exitValue() == 1) {
                                break;
                            }
                        } else {
                            break;
                        }
                    } catch (Exception e) {
                        e = e;
                        bufferedReader = bufferedReader2;
                        e.printStackTrace();
                        CloseUtils.closeIO(new Closeable[]{bufferedReader});
                    } catch (Throwable th) {
                        th = th;
                        r1 = bufferedReader2;
                        CloseUtils.closeIO(new Closeable[]{r1});
                        throw th;
                    }
                }
                CloseUtils.closeIO(bufferedReader2);
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e2) {
            e = e2;
        }
        ?? sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getPath());
        r1 = File.separator;
        sb.append(r1);
        return sb.toString();
    }

    private static boolean createOrExistsFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.exists()) {
            return file.isFile();
        }
        if (!createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            return file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean copyFile(String str, String str2) {
        return copyFile(getFileByPath(str), getFileByPath(str2));
    }

    public static boolean createOrExistsDir(File file) {
        return file != null && (!file.exists() ? !file.mkdirs() : !file.isDirectory());
    }

    private static boolean copyFile(File file, File file2) {
        if (file != null && file2 != null && file.exists() && file.isFile()) {
            if ((file2.exists() && file2.isFile()) || !createOrExistsDir(file2.getParentFile())) {
                return false;
            }
            try {
                return writeFileFromIS(file2, new FileInputStream(file), false);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean writeFileFromIS(File file, InputStream inputStream, boolean z) throws Throwable {
        BufferedOutputStream bufferedOutputStream;
        if (!createOrExistsFile(file) || inputStream == null) {
            return false;
        }
        BufferedOutputStream bufferedOutputStream2 = null;
        try {
            try {
                bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file, z));
            } catch (IOException e) {
                e = e;
            }
        } catch (Throwable th) {
            th = th;
        }
        try {
            byte[] bArr = new byte[sBufferSize];
            while (true) {
                int i = inputStream.read(bArr, 0, sBufferSize);
                if (i != -1) {
                    bufferedOutputStream.write(bArr, 0, i);
                } else {
                    CloseUtils.closeIO(inputStream, bufferedOutputStream);
                    return true;
                }
            }
        } catch (IOException e2) {
            e = e2;
            bufferedOutputStream2 = bufferedOutputStream;
            e.printStackTrace();
            CloseUtils.closeIO(inputStream, bufferedOutputStream2);
            return false;
        } catch (Throwable th2) {
            th = th2;
            bufferedOutputStream2 = bufferedOutputStream;
            CloseUtils.closeIO(inputStream, bufferedOutputStream2);
            throw th;
        }
    }

    public static boolean isEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }
}
