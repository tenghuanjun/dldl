package com.cy.yyjia.zhe28.util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class FileUtil {
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int EOF = -1;
    public static final String FILES_PATH = "CompressHelper";

    private FileUtil() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static File getFileByPath(String filePath) {
        if (StringUtil.isSpace(filePath)) {
            return null;
        }
        return new File(filePath);
    }

    public static boolean isFileExists(String filePath) {
        return isFileExists(getFileByPath(filePath));
    }

    public static boolean isFileExists(File file) {
        return file != null && file.exists();
    }

    public static boolean rename(String filePath, String newName) {
        return rename(getFileByPath(filePath), newName);
    }

    public static boolean rename(File file, String newName) {
        if (file == null || !file.exists() || StringUtil.isSpace(newName)) {
            return false;
        }
        if (newName.equals(file.getName())) {
            return true;
        }
        File file2 = new File(file.getParent() + File.separator + newName);
        return !file2.exists() && file.renameTo(file2);
    }

    public static boolean isDir(String dirPath) {
        return isDir(getFileByPath(dirPath));
    }

    public static boolean isDir(File file) {
        return isFileExists(file) && file.isDirectory();
    }

    public static boolean isFile(String filePath) {
        return isFile(getFileByPath(filePath));
    }

    public static boolean isFile(File file) {
        return isFileExists(file) && file.isFile();
    }

    public static File renameFile(File file, String newName) {
        File file2 = new File(file.getParent(), newName);
        if (!file2.equals(file)) {
            if (file2.exists() && file2.delete()) {
                Log.d("FileUtil", "Delete old " + newName + " file");
            }
            if (file.renameTo(file2)) {
                Log.d("FileUtil", "Rename file to " + newName);
            }
        }
        return file2;
    }

    public static File getTempFile(Context context, Uri uri) throws IOException {
        FileOutputStream fileOutputStream;
        InputStream inputStreamOpenInputStream = context.getContentResolver().openInputStream(uri);
        String fileName = getFileName(context, uri);
        String[] strArrSplitFileName = splitFileName(fileName);
        File fileRenameFile = renameFile(File.createTempFile(strArrSplitFileName[0], strArrSplitFileName[1]), fileName);
        fileRenameFile.deleteOnExit();
        try {
            fileOutputStream = new FileOutputStream(fileRenameFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        if (inputStreamOpenInputStream != null) {
            copy(inputStreamOpenInputStream, fileOutputStream);
            inputStreamOpenInputStream.close();
        }
        if (fileOutputStream != null) {
            fileOutputStream.close();
        }
        return fileRenameFile;
    }

    static String[] splitFileName(String fileName) {
        String strSubstring;
        int iLastIndexOf = fileName.lastIndexOf(".");
        if (iLastIndexOf == -1) {
            strSubstring = "";
        } else {
            String strSubstring2 = fileName.substring(0, iLastIndexOf);
            strSubstring = fileName.substring(iLastIndexOf);
            fileName = strSubstring2;
        }
        return new String[]{fileName, strSubstring};
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x003f A[DONT_GENERATE, PHI: r1
  0x003f: PHI (r1v6 java.lang.String) = (r1v1 java.lang.String), (r1v7 java.lang.String) binds: [B:14:0x0034, B:19:0x003d] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0044  */
    /* JADX WARN: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    static java.lang.String getFileName(android.content.Context r8, android.net.Uri r9) {
        /*
            java.lang.String r0 = r9.getScheme()
            java.lang.String r1 = "content"
            boolean r0 = r0.equals(r1)
            r1 = 0
            if (r0 == 0) goto L42
            android.content.ContentResolver r2 = r8.getContentResolver()
            r6 = 0
            r7 = 0
            r4 = 0
            r5 = 0
            r3 = r9
            android.database.Cursor r8 = r2.query(r3, r4, r5, r6, r7)
            if (r8 == 0) goto L3d
            boolean r0 = r8.moveToFirst()     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            if (r0 == 0) goto L3d
            java.lang.String r0 = "_display_name"
            int r0 = r8.getColumnIndex(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            java.lang.String r0 = r8.getString(r0)     // Catch: java.lang.Throwable -> L2e java.lang.Exception -> L30
            r1 = r0
            goto L3d
        L2e:
            r9 = move-exception
            goto L37
        L30:
            r0 = move-exception
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2e
            if (r8 == 0) goto L42
            goto L3f
        L37:
            if (r8 == 0) goto L3c
            r8.close()
        L3c:
            throw r9
        L3d:
            if (r8 == 0) goto L42
        L3f:
            r8.close()
        L42:
            if (r1 != 0) goto L57
            java.lang.String r1 = r9.getPath()
            java.lang.String r8 = java.io.File.separator
            int r8 = r1.lastIndexOf(r8)
            r9 = -1
            if (r8 == r9) goto L57
            int r8 = r8 + 1
            java.lang.String r1 = r1.substring(r8)
        L57:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.cy.yyjia.zhe28.util.FileUtil.getFileName(android.content.Context, android.net.Uri):java.lang.String");
    }

    static String getRealPathFromURI(Context context, Uri uri) {
        Cursor cursorQuery = context.getContentResolver().query(uri, null, null, null, null);
        if (cursorQuery == null) {
            return uri.getPath();
        }
        cursorQuery.moveToFirst();
        String string = cursorQuery.getString(cursorQuery.getColumnIndex("_data"));
        cursorQuery.close();
        return string;
    }

    static int copy(InputStream input, OutputStream output) throws IOException {
        long jCopyLarge = copyLarge(input, output);
        if (jCopyLarge > 2147483647L) {
            return -1;
        }
        return (int) jCopyLarge;
    }

    static long copyLarge(InputStream input, OutputStream output) throws IOException {
        return copyLarge(input, output, new byte[4096]);
    }

    static long copyLarge(InputStream input, OutputStream output, byte[] buffer) throws IOException {
        long j = 0;
        while (true) {
            int i = input.read(buffer);
            if (-1 == i) {
                return j;
            }
            output.write(buffer, 0, i);
            j += (long) i;
        }
    }
}
