package com.plugin.core;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ResultCode;
import com.plugin.core.tool.PluginLog;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public class SoLibUtil {
    private static final byte[] BYTES = new byte[1024];
    private static final String TAG = "[So]";

    public static boolean releaseSoFile(Context context, File file, String str) throws Throwable {
        int i;
        int i2;
        File file2 = new File(str);
        int i3 = 0;
        if (!file2.exists()) {
            file2.mkdirs();
        } else {
            String[] list = file2.list();
            int length = list.length;
            i = 0;
            while (i < length) {
                new File(list[i]).delete();
                i++;
            }
        }
        String strChooseByX86andArm = ApkInfoUtil.chooseByX86andArm(context);
        PluginLog.d("[So]选择架构: " + strChooseByX86andArm);
        try {
            try {
                ZipFile zipFile = new ZipFile(file);
                Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
                i2 = 0;
                boolean zReleaseSoFile = true;
                while (enumerationEntries.hasMoreElements()) {
                    try {
                        ZipEntry zipEntryNextElement = enumerationEntries.nextElement();
                        if (!zipEntryNextElement.isDirectory()) {
                            String name = zipEntryNextElement.getName();
                            if (name.endsWith(".so")) {
                                String[] strArrSplit = name.split("/");
                                if (strArrSplit.length > 2 && TextUtils.equals(strArrSplit[1], strChooseByX86andArm)) {
                                    i2++;
                                    zReleaseSoFile = releaseSoFile(str, zipFile, zipEntryNextElement);
                                }
                            }
                        }
                    } catch (IOException e) {
                        e = e;
                        PluginLog.e("[So]so处理异常", e);
                        PluginLog.d("[So]共处理" + i2 + "个So文件");
                        return false;
                    }
                }
                PluginLog.d("[So]共处理" + i2 + "个So文件");
                return zReleaseSoFile;
            } catch (Throwable th) {
                th = th;
                i3 = i;
                PluginLog.d("[So]共处理" + i3 + "个So文件");
                throw th;
            }
        } catch (IOException e2) {
            e = e2;
            i2 = 0;
        } catch (Throwable th2) {
            th = th2;
            PluginLog.d("[So]共处理" + i3 + "个So文件");
            throw th;
        }
    }

    private static boolean releaseSoFile(String str, ZipFile zipFile, ZipEntry zipEntry) throws Throwable {
        String name = zipEntry.getName();
        InputStream inputStream = zipFile.getInputStream(zipEntry);
        File file = new File(str, parseSoFileName(name));
        if (file.exists()) {
            String strMd5 = md5(file);
            String strMd52 = md5(inputStream);
            if (!strMd5.isEmpty() && strMd5.equalsIgnoreCase(strMd52)) {
                PluginLog.v(TAG + name + "已存在, 无需复制, " + strMd5);
                return true;
            }
        }
        boolean zCopytoFile = FileUtil.copytoFile(inputStream, file);
        if (zCopytoFile) {
            PluginLog.v("[So]复制 " + name);
        } else {
            PluginLog.e("[So]复制 " + name + ResultCode.MSG_FAILED);
        }
        return zCopytoFile;
    }

    private static String parseSoFileName(String str) {
        return str.substring(str.lastIndexOf("/") + 1);
    }

    private static String md5(File file) throws Throwable {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (Exception unused) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            String strMd5 = md5(fileInputStream);
            close(fileInputStream);
            return strMd5;
        } catch (Exception unused2) {
            fileInputStream2 = fileInputStream;
            close(fileInputStream2);
            return "";
        } catch (Throwable th2) {
            th = th2;
            fileInputStream2 = fileInputStream;
            close(fileInputStream2);
            throw th;
        }
    }

    public static String md5(InputStream inputStream) throws Throwable {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = null;
            try {
                DigestInputStream digestInputStream2 = new DigestInputStream(inputStream, messageDigest);
                do {
                    try {
                    } catch (IOException unused) {
                        digestInputStream = digestInputStream2;
                        close(digestInputStream);
                        return "";
                    } catch (Throwable th) {
                        th = th;
                        digestInputStream = digestInputStream2;
                        close(digestInputStream);
                        throw th;
                    }
                } while (digestInputStream2.read(BYTES) != -1);
                close(digestInputStream2);
                byte[] bArrDigest = messageDigest.digest();
                StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
                for (byte b : bArrDigest) {
                    int i = b & UByte.MAX_VALUE;
                    if (i < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(i));
                }
                return sb.toString();
            } catch (IOException unused2) {
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Huh, MD5 should be supported?", e);
        }
    }

    private static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException unused) {
            }
        }
    }
}
