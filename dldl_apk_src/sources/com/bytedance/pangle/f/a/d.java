package com.bytedance.pangle.f.a;

import android.content.pm.PackageInfo;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import com.bytedance.pangle.util.g;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.plugin.core.manifest.AndroidManifestConstants;
import java.io.File;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class d {
    private static String a(int i) {
        return (i >>> 24) == 1 ? "android:" : "";
    }

    public static e a(File file) {
        ZipFile zipFile;
        a aVar;
        int iB;
        int i;
        try {
            if (!file.exists()) {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, file.getAbsolutePath() + " not exists!");
                g.a((ZipFile) null);
                return null;
            }
            ZipFile zipFile2 = new ZipFile(file);
            try {
                ZipEntry entry = zipFile2.getEntry(AndroidManifestConstants.ANDROID_MANIFEST_FILE_NAME);
                if (entry == null) {
                    ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "没有找到AndroidManifest.xml entry");
                    g.a(zipFile2);
                    return null;
                }
                aVar = new a();
                try {
                    InputStream inputStream = zipFile2.getInputStream(entry);
                    aVar.a();
                    if (inputStream != null) {
                        aVar.b = new b(inputStream);
                    }
                    do {
                        iB = aVar.b();
                        if (iB == 1) {
                            ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "已达到END_DOCUMENT");
                            try {
                                aVar.a();
                            } catch (Throwable unused) {
                            }
                            g.a(zipFile2);
                            return null;
                        }
                    } while (iB != 2);
                    int length = aVar.a != 2 ? -1 : aVar.c.length / 5;
                    String strA = null;
                    String strA2 = null;
                    for (int i2 = 0; i2 != length; i2++) {
                        if (TTDownloadField.TT_VERSION_CODE.equals(aVar.a(i2))) {
                            strA = a(aVar, i2);
                        } else if ("package".equals(aVar.a(i2))) {
                            strA2 = a(aVar, i2);
                        }
                    }
                    try {
                        i = Integer.parseInt(strA);
                    } catch (Throwable unused2) {
                        i = -1;
                    }
                    if (i == -1) {
                        ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "versionCode获取失败:".concat(String.valueOf(strA)));
                        try {
                            aVar.a();
                        } catch (Throwable unused3) {
                        }
                        g.a(zipFile2);
                        return null;
                    }
                    e eVar = new e(strA2, i);
                    try {
                        aVar.a();
                    } catch (Throwable unused4) {
                    }
                    g.a(zipFile2);
                    return eVar;
                } catch (Throwable th) {
                    th = th;
                    Throwable th2 = th;
                    zipFile = zipFile2;
                    th = th2;
                    try {
                        PackageInfo packageArchiveInfo = Zeus.getAppApplication().getPackageManager().getPackageArchiveInfo(file.getPath(), 0);
                        if (packageArchiveInfo == null) {
                            ZeusLogger.errReport(ZeusLogger.TAG_INSTALL, "packageArchiveInfo == null", th);
                            return null;
                        }
                        e eVar2 = new e(packageArchiveInfo.packageName, packageArchiveInfo.versionCode);
                        if (aVar != null) {
                            try {
                                aVar.a();
                            } catch (Throwable unused5) {
                            }
                        }
                        g.a(zipFile);
                        return eVar2;
                    } finally {
                        if (aVar != null) {
                            try {
                                aVar.a();
                            } catch (Throwable unused6) {
                            }
                        }
                        g.a(zipFile);
                    }
                }
            } catch (Throwable th3) {
                th = th3;
                aVar = null;
            }
        } catch (Throwable th4) {
            th = th4;
            zipFile = null;
            aVar = null;
        }
    }

    private static String a(a aVar, int i) {
        int iB = aVar.b(i);
        int iC = aVar.c(i);
        if (iB == 3) {
            return aVar.d(i);
        }
        return iB == 2 ? String.format("?%s%08X", a(iC), Integer.valueOf(iC)) : (iB < 16 || iB > 31) ? String.format("<0x%X, type 0x%02X>", Integer.valueOf(iC), Integer.valueOf(iB)) : String.valueOf(iC);
    }
}
