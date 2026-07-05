package com.sq.diagnostic.assistant.zip;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class ZipUtil {
    private static final int BUFFER_SIZE = 4096;
    private static final String TAG = "ZipUtils:";

    public static synchronized boolean zipFiles(Queue<String> queue, String str) {
        File[] fileArrListFiles;
        if (queue != null) {
            if (queue.size() != 0) {
                File parentFile = new File(str).getParentFile();
                if ((!parentFile.exists() || !parentFile.isDirectory()) && !parentFile.mkdirs()) {
                    Log.e(TAG, "Can't make dir: " + parentFile);
                    return false;
                }
                LinkedList linkedList = new LinkedList(queue);
                try {
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(str));
                    while (!linkedList.isEmpty()) {
                        File file = new File((String) linkedList.poll());
                        if (file.isFile()) {
                            writeFileToZip(file, zipOutputStream);
                        } else if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
                            for (File file2 : fileArrListFiles) {
                                linkedList.add(file2.getAbsolutePath());
                            }
                        }
                    }
                    zipOutputStream.finish();
                    zipOutputStream.close();
                    return true;
                } catch (IOException e) {
                    Log.e(TAG, "zip files failed: " + e);
                    if (!new File(str).delete()) {
                        Log.e(TAG, "Delete zip files failed: ");
                    }
                    return false;
                }
            }
        }
        Log.e(TAG, "No files to zip!");
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v12 */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v14 */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3 */
    /* JADX WARN: Type inference failed for: r2v4, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r2v5 */
    /* JADX WARN: Type inference failed for: r2v7 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:11:0x002c -> B:29:0x0056). Please report as a decompilation issue!!! */
    private static void writeFileToZip(File file, ZipOutputStream zipOutputStream) throws Throwable {
        byte[] bArr;
        FileInputStream fileInputStream;
        ?? r2 = 0;
        FileInputStream fileInputStream2 = null;
        r2 = 0;
        try {
            try {
                try {
                    zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                    bArr = new byte[4096];
                    fileInputStream = new FileInputStream(file);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (IOException e) {
                e = e;
            }
        } catch (IOException e2) {
            Log.e(TAG, e2.toString());
            r2 = r2;
        }
        while (true) {
            try {
                int i = fileInputStream.read(bArr);
                r2 = -1;
                if (i == -1) {
                    break;
                } else {
                    zipOutputStream.write(bArr, 0, i);
                }
            } catch (IOException e3) {
                e = e3;
                fileInputStream2 = fileInputStream;
                Log.e(TAG, "Write file to zip failed: " + e);
                r2 = fileInputStream2;
                if (fileInputStream2 != null) {
                    fileInputStream2.close();
                    r2 = fileInputStream2;
                }
            } catch (Throwable th2) {
                th = th2;
                r2 = fileInputStream;
                if (r2 != 0) {
                    try {
                        r2.close();
                    } catch (IOException e4) {
                        Log.e(TAG, e4.toString());
                    }
                }
                throw th;
            }
        }
        zipOutputStream.closeEntry();
        fileInputStream.close();
    }
}
