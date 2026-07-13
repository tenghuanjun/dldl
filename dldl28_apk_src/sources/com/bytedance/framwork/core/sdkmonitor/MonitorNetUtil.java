package com.bytedance.framwork.core.sdkmonitor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.autofill.HintConstants;
import com.bytedance.frameworks.core.encrypt.TTEncryptUtils;
import com.tencent.connect.common.Constants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Deflater;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class MonitorNetUtil {
    private static final boolean DEBUG_MOBILE = false;
    private static IRequestIntercept sRequestIntercept;

    public enum CompressType {
        NONE(0),
        GZIP(1),
        DEFLATER(2);

        final int nativeInt;

        CompressType(int i) {
            this.nativeInt = i;
        }
    }

    public interface IRequestIntercept {
        String addRequestVerifyParams(String str, byte[] bArr);
    }

    public enum NetworkType {
        NONE(0),
        MOBILE(1),
        MOBILE_2G(2),
        MOBILE_3G(3),
        WIFI(4),
        MOBILE_4G(5);

        final int nativeInt;

        NetworkType(int i) {
            this.nativeInt = i;
        }

        public int getValue() {
            return this.nativeInt;
        }
    }

    public static byte[] excutePost(long j, String str, byte[] bArr, CompressType compressType, String str2, boolean z) {
        String str3 = null;
        if (str == null) {
            return null;
        }
        if (bArr == null) {
            bArr = new byte[0];
        }
        int length = bArr.length;
        if (CompressType.GZIP == compressType && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(8192);
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            try {
                gZIPOutputStream.write(bArr);
                gZIPOutputStream.close();
                bArr = byteArrayOutputStream.toByteArray();
                str3 = "gzip";
            } catch (Throwable unused) {
                gZIPOutputStream.close();
                return null;
            }
        } else if (CompressType.DEFLATER == compressType && length > 128) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream(8192);
            Deflater deflater = new Deflater();
            deflater.setInput(bArr);
            deflater.finish();
            byte[] bArr2 = new byte[8192];
            while (!deflater.finished()) {
                byteArrayOutputStream2.write(bArr2, 0, deflater.deflate(bArr2));
            }
            deflater.end();
            bArr = byteArrayOutputStream2.toByteArray();
            str3 = "deflate";
        }
        String str4 = str3;
        byte[] bArr3 = bArr;
        if (!z) {
            return excuteRequest(str, bArr3, str2, str4, Constants.HTTP_POST, true, false);
        }
        byte[] bArrEncrypt = TTEncryptUtils.encrypt(bArr3, bArr3.length);
        if (bArrEncrypt != null) {
            str = str + "&tt_data=a";
            str2 = "application/octet-stream;tt-data=a";
            bArr3 = bArrEncrypt;
        }
        return excuteRequest(str, bArr3, str2, str4, Constants.HTTP_POST, true, true);
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x0057 A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:25:0x005c A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0062 A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0069 A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x007c A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00ea A[Catch: all -> 0x004e, TryCatch #9 {all -> 0x004e, blocks: (B:12:0x0029, B:14:0x002f, B:15:0x0033, B:17:0x0039, B:20:0x0042, B:24:0x0057, B:27:0x0062, B:29:0x0069, B:30:0x006e, B:32:0x007c, B:34:0x0081, B:38:0x0093, B:43:0x009b, B:44:0x009e, B:45:0x009f, B:47:0x00a7, B:67:0x00e0, B:68:0x00e9, B:69:0x00ea, B:70:0x00f1, B:25:0x005c), top: B:98:0x0029 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static byte[] excuteRequest(java.lang.String r5, byte[] r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, boolean r10, boolean r11) {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.framwork.core.sdkmonitor.MonitorNetUtil.excuteRequest(java.lang.String, byte[], java.lang.String, java.lang.String, java.lang.String, boolean, boolean):byte[]");
    }

    public static String getNetWorkType(Context context) {
        if (context == null) {
            return null;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return activeNetworkInfo.getTypeName();
            }
        } catch (Throwable unused) {
        }
        return null;
    }

    public static NetworkType getNetworkType(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                int type = activeNetworkInfo.getType();
                if (1 == type) {
                    return NetworkType.WIFI;
                }
                if (type != 0) {
                    return NetworkType.MOBILE;
                }
                switch (((TelephonyManager) context.getSystemService(HintConstants.AUTOFILL_HINT_PHONE)).getNetworkType()) {
                    case 3:
                    case 5:
                    case 6:
                    case 8:
                    case 9:
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                        return NetworkType.MOBILE_3G;
                    case 4:
                    case 7:
                    case 11:
                    default:
                        return NetworkType.MOBILE;
                    case 13:
                        return NetworkType.MOBILE_4G;
                }
            }
            return NetworkType.NONE;
        } catch (Throwable unused) {
            return NetworkType.MOBILE;
        }
    }

    public static byte[] getRequest(String str, String str2, boolean z) {
        if (TextUtils.isDigitsOnly(str)) {
            return null;
        }
        return excuteRequest(str, null, str2, null, Constants.HTTP_GET, false, z);
    }

    public static boolean isNetworkAvailable(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return activeNetworkInfo.isConnected();
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean isWifi(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isAvailable()) {
                return 1 == activeNetworkInfo.getType();
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    public static void setRequestIntercept(IRequestIntercept iRequestIntercept) {
        sRequestIntercept = iRequestIntercept;
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[8192];
        while (true) {
            int i = inputStream.read(bArr);
            if (-1 == i) {
                inputStream.close();
                return byteArrayOutputStream.toByteArray();
            }
            byteArrayOutputStream.write(bArr, 0, i);
        }
    }
}
