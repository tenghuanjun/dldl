package com.huya.mtp.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Pair;
import com.huya.mtp.api.MTPApi;
import com.sqwan.bugless.util.FileUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class FileUtils {
    private static final String TAG = "FileUtils";
    private BufferedOutputStream bos;
    private FileOutputStream fos;
    private File mFile;

    public static String getExternalStorageDirectoryAbsolutePath() {
        try {
        } catch (Exception e) {
            MTPApi.LOGGER.error(TAG, e);
        }
        if (!"mounted".equalsIgnoreCase(Environment.getExternalStorageState())) {
            MTPApi.LOGGER.error(TAG, "system storage not mounted");
            return null;
        }
        File externalStorageDirectory = Environment.getExternalStorageDirectory();
        if (externalStorageDirectory == null) {
            MTPApi.LOGGER.error(TAG, "external storage still null");
            return null;
        }
        String absolutePath = externalStorageDirectory.getAbsolutePath();
        String str = absolutePath + "/test";
        if (createDir(str, false)) {
            return absolutePath;
        }
        if (createDir(str.replace("0", "1"), false)) {
            return absolutePath.replace("0", "1");
        }
        return null;
    }

    public static String getExternalStorageState() {
        try {
            return Environment.getExternalStorageState();
        } catch (Exception e) {
            MTPApi.LOGGER.error(TAG, e);
            return "unknown";
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0055 A[Catch: all -> 0x008e, Exception -> 0x0090, TryCatch #0 {Exception -> 0x0090, blocks: (B:5:0x000b, B:7:0x0013, B:14:0x0055, B:15:0x0064, B:17:0x006a, B:18:0x0073, B:8:0x001e, B:10:0x0026, B:11:0x004e), top: B:32:0x000b, outer: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0086  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String getTxtFileContent(android.content.Context r7, java.lang.String r8) {
        /*
            java.lang.String r0 = ""
            boolean r1 = com.huya.mtp.utils.StringUtils.isNullOrEmpty(r8)
            if (r1 != 0) goto Lb9
            r1 = 0
            r2 = 0
            r3 = 1
            java.lang.String r4 = java.io.File.separator     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            boolean r4 = r8.startsWith(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r4 == 0) goto L1e
            java.io.FileInputStream r7 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.<init>(r8)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r7.<init>(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            goto L52
        L1e:
            java.lang.String r4 = java.io.File.separator     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            boolean r4 = r8.contains(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r4 == 0) goto L4e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.<init>()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.File r7 = r7.getFilesDir()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r7 = r7.getAbsolutePath()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.append(r7)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r7 = java.io.File.separator     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.append(r7)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.append(r8)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r7 = r4.toString()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.FileInputStream r8 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.File r4 = new java.io.File     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4.<init>(r7)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r8.<init>(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r1 = r8
            goto L53
        L4e:
            java.io.FileInputStream r7 = r7.openFileInput(r8)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
        L52:
            r1 = r7
        L53:
            if (r1 == 0) goto L86
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r8.<init>(r1)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r8.<init>(r0)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
        L64:
            java.lang.String r4 = r7.readLine()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            if (r4 == 0) goto L73
            r8.append(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r4 = "\n"
            r8.append(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            goto L64
        L73:
            java.io.Closeable[] r4 = new java.io.Closeable[r3]     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            r4[r2] = r7     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            com.huya.mtp.utils.IOUtils.close(r4)     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.lang.String r7 = r8.toString()     // Catch: java.lang.Throwable -> L8e java.lang.Exception -> L90
            java.io.Closeable[] r8 = new java.io.Closeable[r3]
            r8[r2] = r1
            com.huya.mtp.utils.IOUtils.close(r8)
            return r7
        L86:
            java.io.Closeable[] r7 = new java.io.Closeable[r3]
            r7[r2] = r1
            com.huya.mtp.utils.IOUtils.close(r7)
            goto Lb0
        L8e:
            r7 = move-exception
            goto Lb1
        L90:
            r7 = move-exception
            com.huya.mtp.api.LogApi r8 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L8e
            java.lang.String r4 = "getTxtFileContent"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8e
            r5.<init>()     // Catch: java.lang.Throwable -> L8e
            java.lang.String r6 = "read fail, e = "
            r5.append(r6)     // Catch: java.lang.Throwable -> L8e
            r5.append(r7)     // Catch: java.lang.Throwable -> L8e
            java.lang.String r7 = r5.toString()     // Catch: java.lang.Throwable -> L8e
            r8.error(r4, r7)     // Catch: java.lang.Throwable -> L8e
            java.io.Closeable[] r7 = new java.io.Closeable[r3]
            r7[r2] = r1
            com.huya.mtp.utils.IOUtils.close(r7)
        Lb0:
            return r0
        Lb1:
            java.io.Closeable[] r8 = new java.io.Closeable[r3]
            r8[r2] = r1
            com.huya.mtp.utils.IOUtils.close(r8)
            throw r7
        Lb9:
            java.lang.IllegalArgumentException r7 = new java.lang.IllegalArgumentException
            java.lang.String r8 = "path can not be null"
            r7.<init>(r8)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.FileUtils.getTxtFileContent(android.content.Context, java.lang.String):java.lang.String");
    }

    public static File createFileOnSD(String str, String str2) {
        if (!isSDCardMounted()) {
            return null;
        }
        String strReplace = Environment.getExternalStorageDirectory().getPath() + str;
        if (!createDir(strReplace, true)) {
            strReplace = strReplace.replace("0", "1");
            createDir(strReplace, true);
        }
        File file = new File(strReplace + "/" + str2);
        try {
            if (!file.exists()) {
                if (!file.createNewFile()) {
                    return null;
                }
            }
            return file;
        } catch (IOException unused) {
            MTPApi.LOGGER.error(TAG, "can not create file on SD card");
            return null;
        }
    }

    public static String getFileExtension(String str) {
        String fileName = getFileName(str);
        int iLastIndexOf = fileName.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR);
        if (iLastIndexOf != -1) {
            return fileName.substring(iLastIndexOf);
        }
        return null;
    }

    public static String getFileName(String str) {
        int iLastIndexOf;
        if (str == null || (iLastIndexOf = str.lastIndexOf("/") + 1) <= 0) {
            return null;
        }
        return str.substring(iLastIndexOf);
    }

    public static String dropExt(String str) {
        int iLastIndexOf;
        return (FP.empty(str) || (iLastIndexOf = str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR)) == -1) ? str : FP.take(iLastIndexOf, str);
    }

    public static boolean isFileExisted(String str) {
        if (StringUtils.isNullOrEmpty(str)) {
            return false;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                return file.length() > 0;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void renameFile(String str, String str2) {
        try {
            new File(str).renameTo(new File(str2));
        } catch (Exception unused) {
        }
    }

    public static boolean CopyFile(String str, String str2) throws Throwable {
        FileOutputStream fileOutputStream;
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            try {
                File file = new File(str);
                File file2 = new File(str2);
                fileInputStream = new FileInputStream(file);
                try {
                    fileOutputStream = new FileOutputStream(file2);
                } catch (Exception unused) {
                    fileOutputStream = null;
                } catch (Throwable th) {
                    th = th;
                    fileOutputStream = null;
                }
            } catch (Exception unused2) {
                return false;
            }
        } catch (Exception unused3) {
            fileOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileOutputStream = null;
        }
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int i = fileInputStream.read(bArr);
                if (i != -1) {
                    fileOutputStream.write(bArr, 0, i);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception unused4) {
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                fileInputStream2.close();
            }
            if (fileOutputStream == null) {
                return false;
            }
            fileOutputStream.close();
            return false;
        } catch (Throwable th3) {
            th = th3;
            fileInputStream2 = fileInputStream;
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (Exception unused5) {
                    throw th;
                }
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw th;
        }
    }

    public static void removeFiles(List<Pair<Integer, String>> list) {
        for (Pair<Integer, String> pair : list) {
            if (pair.second != null) {
                removeFile((String) pair.second);
            }
        }
    }

    public static void removeFile(String str) {
        if (StringUtils.isNullOrEmpty(str)) {
            return;
        }
        try {
            new File(str).delete();
        } catch (Exception unused) {
        }
    }

    public static void removeDir(String str) {
        removeDirOrFile(new File(str));
    }

    public static int getFileCount(File file) {
        if (file == null || !file.exists()) {
            return 0;
        }
        if (!file.isDirectory()) {
            return 1;
        }
        int fileCount = 0;
        for (File file2 : file.listFiles()) {
            fileCount += getFileCount(file2);
        }
        return fileCount;
    }

    public static String BtoKBMB(long j) {
        if (j < 0) {
            j = 0;
        }
        if (j < PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
            return String.valueOf(j) + "B";
        }
        if (j < PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) {
            return String.valueOf(j / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) + "KB";
        }
        return String.valueOf(j / PlaybackStateCompat.ACTION_SET_CAPTIONING_ENABLED) + "MB";
    }

    public static boolean removeDirOrFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory() && !FP.empty(file.list())) {
            for (String str : file.list()) {
                if (!removeDirOrFile(new File(file, str))) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static File getFileFromURL(String str, String str2) {
        if (StringUtils.isNullOrEmpty(str2)) {
            return null;
        }
        return new File(str, str2.substring(str2.lastIndexOf(47) + 1));
    }

    public static boolean isSDCardMounted() {
        return availableMemInSDcard();
    }

    public static boolean availableMemInSDcard() {
        if (!externalStorageExist()) {
            return false;
        }
        StatFs statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        return (((long) statFs.getAvailableBlocks()) * ((long) statFs.getBlockSize())) / PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID >= 10;
    }

    public static boolean externalStorageExist() {
        return getExternalStorageState().equalsIgnoreCase("mounted");
    }

    public static String dropPrefix(String str, String str2) {
        return str.startsWith(str2) ? FP.drop(FP.length(str2), str) : str;
    }

    public static String concatPath(String str, String str2) {
        StringBuilder sb;
        if (str.endsWith("/")) {
            sb = new StringBuilder();
            sb.append(str);
        } else {
            sb = new StringBuilder();
            sb.append(str);
            sb.append("/");
        }
        sb.append(dropPrefix(str2, "/"));
        return sb.toString();
    }

    public static String concatPaths(String... strArr) {
        String strConcatPath = "";
        for (String str : strArr) {
            strConcatPath = concatPath(strConcatPath, str);
        }
        return strConcatPath;
    }

    public static FileUtils openFile(String str) throws Exception {
        createDir(str.substring(0, str.lastIndexOf("/")), true);
        File file = new File(str);
        if (!file.exists() && !file.createNewFile()) {
            file = null;
        }
        return new FileUtils(file, null);
    }

    private FileUtils(File file, FileOutputStream fileOutputStream) throws Exception {
        this.fos = null;
        this.bos = null;
        this.mFile = file;
        this.fos = fileOutputStream;
        if (file != null) {
            if (fileOutputStream == null) {
                this.fos = new FileOutputStream(this.mFile);
            }
            this.bos = new BufferedOutputStream(this.fos);
            return;
        }
        throw new Exception("YYFileOutput, can not create file output stream");
    }

    public static boolean createDir(String str, boolean z) {
        if (!ensureDirExists(str)) {
            return false;
        }
        if (!z) {
            return true;
        }
        try {
            new File(str + "/.nomedia").createNewFile();
            return true;
        } catch (IOException unused) {
            return true;
        }
    }

    public static boolean ensureDirExists(String str) {
        File file = new File(str);
        if (file.exists()) {
            return true;
        }
        return file.mkdirs();
    }

    public static boolean ensureFileDirExists(String str) {
        String dirOfFilePath = getDirOfFilePath(str);
        if (StringUtils.isNullOrEmpty(dirOfFilePath)) {
            return false;
        }
        ensureDirExists(dirOfFilePath);
        return true;
    }

    public static String getDirOfFilePath(String str) {
        int iLastIndexOf;
        if (StringUtils.isNullOrEmpty(str) || (iLastIndexOf = str.lastIndexOf(File.separatorChar)) == -1) {
            return null;
        }
        return str.substring(0, iLastIndexOf);
    }

    public void write(Bitmap bitmap) {
        write(bitmap, 80);
    }

    public void write(Bitmap bitmap, int i) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, i, this.bos);
    }

    public void write(InputStream inputStream) {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    return;
                } else {
                    this.bos.write(bArr, 0, i);
                }
            } catch (IOException e) {
                MTPApi.LOGGER.error(this, e);
                return;
            }
        }
    }

    public void write(String str) {
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            write(fileInputStream);
            fileInputStream.close();
        } catch (Exception e) {
            MTPApi.LOGGER.error(this, e);
        }
    }

    public void write(byte[] bArr) {
        try {
            this.bos.write(bArr);
        } catch (IOException e) {
            MTPApi.LOGGER.error(this, e);
        }
    }

    public void write(byte[] bArr, int i, int i2) {
        try {
            this.bos.write(bArr, i, i2);
        } catch (IOException e) {
            MTPApi.LOGGER.error(this, e);
        }
    }

    public void close() {
        try {
            this.bos.flush();
            this.bos.close();
            this.fos.close();
        } catch (IOException e) {
            MTPApi.LOGGER.error(this, e);
        }
    }

    public File getFile() {
        return this.mFile;
    }

    public static long getFileLength(String str) {
        return new File(str).length();
    }

    public static long getFileSize(File file) {
        long length;
        long j = 0;
        if (file != null && file.listFiles() != null) {
            for (File file2 : file.listFiles()) {
                if (file2.isDirectory()) {
                    length = getFileSize(file2);
                } else {
                    length = file2.length();
                }
                j += length;
            }
        }
        return j;
    }

    public static byte[] fileToByteArray(File file) {
        if (file.exists() && file.canRead()) {
            try {
                return streamToBytes(new FileInputStream(file));
            } catch (FileNotFoundException e) {
                MTPApi.LOGGER.error(TAG, e);
            }
        }
        return null;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:0|2|45|3|(8:4|(1:6)(1:57)|26|51|27|31|44|32)|7|49|8|(1:11)|12|55|13|17|44|32|(1:(0))) */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0030, code lost:
    
        r7 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x0031, code lost:
    
        com.huya.mtp.api.MTPApi.LOGGER.error(com.huya.mtp.utils.FileUtils.TAG, r7);
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x0041, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:22:0x0042, code lost:
    
        r3 = r7;
        r7 = r3;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] streamToBytes(java.io.InputStream r7) {
        /*
            java.lang.String r0 = "FileUtils"
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream
            r1.<init>()
            java.io.BufferedInputStream r2 = new java.io.BufferedInputStream
            r2.<init>(r7)
            r7 = 1024(0x400, float:1.435E-42)
            r3 = 0
            byte[] r7 = new byte[r7]     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
        L11:
            int r4 = r2.read(r7)     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            r5 = -1
            if (r4 == r5) goto L1d
            r5 = 0
            r1.write(r7, r5, r4)     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            goto L11
        L1d:
            byte[] r7 = r1.toByteArray()     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            int r4 = r7.length     // Catch: java.io.IOException -> L41 java.lang.Throwable -> L46
            if (r4 != 0) goto L25
            goto L26
        L25:
            r3 = r7
        L26:
            r1.close()     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            r2.close()     // Catch: java.lang.Throwable -> L46 java.io.IOException -> L48
            r1.close()     // Catch: java.io.IOException -> L30
            goto L36
        L30:
            r7 = move-exception
            com.huya.mtp.api.LogApi r1 = com.huya.mtp.api.MTPApi.LOGGER
            r1.error(r0, r7)
        L36:
            r2.close()     // Catch: java.io.IOException -> L3a
            goto L5b
        L3a:
            r7 = move-exception
            com.huya.mtp.api.LogApi r1 = com.huya.mtp.api.MTPApi.LOGGER
            r1.error(r0, r7)
            goto L5b
        L41:
            r3 = move-exception
            r6 = r3
            r3 = r7
            r7 = r6
            goto L49
        L46:
            r7 = move-exception
            goto L5c
        L48:
            r7 = move-exception
        L49:
            com.huya.mtp.api.LogApi r4 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L46
            r4.error(r0, r7)     // Catch: java.lang.Throwable -> L46
            r1.close()     // Catch: java.io.IOException -> L52
            goto L58
        L52:
            r7 = move-exception
            com.huya.mtp.api.LogApi r1 = com.huya.mtp.api.MTPApi.LOGGER
            r1.error(r0, r7)
        L58:
            r2.close()     // Catch: java.io.IOException -> L3a
        L5b:
            return r3
        L5c:
            r1.close()     // Catch: java.io.IOException -> L60
            goto L66
        L60:
            r1 = move-exception
            com.huya.mtp.api.LogApi r3 = com.huya.mtp.api.MTPApi.LOGGER
            r3.error(r0, r1)
        L66:
            r2.close()     // Catch: java.io.IOException -> L6a
            goto L70
        L6a:
            r1 = move-exception
            com.huya.mtp.api.LogApi r2 = com.huya.mtp.api.MTPApi.LOGGER
            r2.error(r0, r1)
        L70:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.FileUtils.streamToBytes(java.io.InputStream):byte[]");
    }
}
