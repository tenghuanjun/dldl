package com.ss.android.downloadlib.addownload.c;

import android.content.Context;
import com.ss.android.downloadlib.addownload.k;
import com.ss.android.downloadlib.g.m;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import java.util.List;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class c {
    public static void a() throws Throwable {
        List<DownloadInfo> listA = com.ss.android.socialbase.appdownloader.d.j().a(k.a());
        if (listA == null || listA.size() <= 0) {
            return;
        }
        for (int i = 0; i < listA.size(); i++) {
            DownloadInfo downloadInfo = listA.get(i);
            File file = new File(downloadInfo.getTempPath(), downloadInfo.getTempName());
            long jLastModified = file.lastModified();
            long jOptInt = ((long) DownloadSetting.obtain(downloadInfo.getId()).optInt("download_file_expire_hours", 0)) * 3600000;
            if (jOptInt <= 0) {
                jOptInt = com.igexin.push.e.b.d.b;
            }
            if (file.isFile() && file.exists() && System.currentTimeMillis() - jLastModified >= jOptInt) {
                a(file);
                Downloader.getInstance(k.a()).clearDownloadData(downloadInfo.getId());
            }
        }
    }

    public static void b() throws Throwable {
        List successedDownloadInfosWithMimeType = Downloader.getInstance(k.a()).getSuccessedDownloadInfosWithMimeType("application/vnd.android.package-archive");
        if (successedDownloadInfosWithMimeType == null || successedDownloadInfosWithMimeType.isEmpty()) {
            return;
        }
        for (int i = 0; i < successedDownloadInfosWithMimeType.size(); i++) {
            DownloadInfo downloadInfo = (DownloadInfo) successedDownloadInfosWithMimeType.get(i);
            if (downloadInfo != null) {
                String str = downloadInfo.getSavePath() + File.separator + downloadInfo.getName();
                File file = new File(str);
                if (file.exists()) {
                    long jCurrentTimeMillis = System.currentTimeMillis() - file.lastModified();
                    long jOptInt = ((long) DownloadSetting.obtain(downloadInfo.getId()).optInt("download_complete_file_expire_hours", 0)) * 3600000;
                    if (jOptInt <= 0) {
                        jOptInt = com.igexin.push.e.b.d.b;
                    }
                    boolean z = true;
                    if (jCurrentTimeMillis < jOptInt && !m.f(k.a(), str)) {
                        z = false;
                    }
                    if (z) {
                        a(file);
                    }
                }
            }
        }
    }

    public static void a(Context context) {
        File externalCacheDir;
        if (context == null || (externalCacheDir = context.getExternalCacheDir()) == null) {
            return;
        }
        try {
            a(externalCacheDir.getPath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0030 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void a(java.io.File r3) throws java.lang.Throwable {
        /*
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            r1.<init>(r3)     // Catch: java.lang.Throwable -> L15 java.lang.Exception -> L17
            java.lang.String r0 = "1"
            byte[] r0 = r0.getBytes()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2c
            r1.write(r0)     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2c
            r1.close()     // Catch: java.lang.Exception -> L13 java.lang.Throwable -> L2c
            goto L28
        L13:
            r0 = move-exception
            goto L1b
        L15:
            r3 = move-exception
            goto L2e
        L17:
            r1 = move-exception
            r2 = r1
            r1 = r0
            r0 = r2
        L1b:
            r0.printStackTrace()     // Catch: java.lang.Throwable -> L2c
            if (r1 == 0) goto L28
            r1.close()     // Catch: java.lang.Exception -> L24
            goto L28
        L24:
            r0 = move-exception
            r0.printStackTrace()
        L28:
            r3.delete()
            return
        L2c:
            r3 = move-exception
            r0 = r1
        L2e:
            if (r0 == 0) goto L38
            r0.close()     // Catch: java.lang.Exception -> L34
            goto L38
        L34:
            r0 = move-exception
            r0.printStackTrace()
        L38:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ss.android.downloadlib.addownload.c.c.a(java.io.File):void");
    }

    private static void a(String str) {
        File file = new File(str);
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
                return;
            }
            String[] list = file.list();
            if (list == null) {
                return;
            }
            for (String str2 : list) {
                if (str2 != null) {
                    String str3 = str.endsWith(File.separator) ? str + str2 : str + File.separator + str2;
                    File file2 = new File(str3);
                    if (file2.isFile()) {
                        file2.delete();
                    }
                    if (file2.isDirectory()) {
                        a(str3);
                    }
                }
            }
            file.delete();
        }
    }
}
