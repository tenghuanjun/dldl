package com.duowan.ark.util;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Looper;
import android.os.Process;
import android.support.v4.view.MotionEventCompat;
import android.telephony.TelephonyManager;
import com.huya.mtp.utils.Utils;
import com.sqwan.bugless.util.FileUtil;
import com.sqwan.msdk.utils.AppSigning;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class Utils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final int SHA1_LENGTH = 40;
    public static final String TAG = "Utils";

    public static void dwAssert(boolean z) {
    }

    public static byte[] getIPArray(int i) {
        return new byte[]{(byte) i, (byte) (i >>> 8), (byte) (i >>> 16), (byte) (i >>> 24)};
    }

    public static boolean isSpace(char c) {
        return c == '\n' || c == '\r' || c == '\t' || c == ' ';
    }

    public static long uint2long(int i) {
        return ((long) i) & 4294967295L;
    }

    public static boolean isNotificationEnabled(Context context) {
        if (Build.VERSION.SDK_INT < 19) {
            return true;
        }
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
        ApplicationInfo applicationInfo = context.getApplicationInfo();
        String packageName = context.getApplicationContext().getPackageName();
        int i = applicationInfo.uid;
        try {
            Class<?> cls = Class.forName(AppOpsManager.class.getName());
            return ((Integer) cls.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
        } catch (Throwable unused) {
            return true;
        }
    }

    public static boolean isForeground(Context context) {
        try {
        } catch (Throwable unused) {
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.processName.equals(context.getPackageName())) {
                if (runningAppProcessInfo.importance == 100) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public static String getProcessName(Context context) {
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null || activityManager.getRunningAppProcesses() == null) {
                return "null_name";
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return StringUtils.isNullOrEmpty(runningAppProcessInfo.processName) ? "null_name" : runningAppProcessInfo.processName;
                }
            }
            return "null_name";
        } catch (Throwable unused) {
            return "null_name";
        }
    }

    public static boolean isOverWriteInstall(Context context) {
        if (context == null) {
            throw new NullPointerException("context may not be null");
        }
        File file = new File("/data/data/" + context.getPackageName() + "/shared_prefs/" + context.getPackageName() + ".configuration.configuration.xml");
        return file.exists() && file.length() > 0;
    }

    public static boolean isServiceRunning(Context context, String str) {
        List<ActivityManager.RunningServiceInfo> runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(100);
        if (runningServices == null) {
            return false;
        }
        Iterator<ActivityManager.RunningServiceInfo> it = runningServices.iterator();
        while (it.hasNext()) {
            if (it.next().service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public static String sha1(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(bytesToHexString(MessageDigest.getInstance(AppSigning.SHA1).digest(str.getBytes())));
        } catch (NoSuchAlgorithmException unused) {
        }
        return stringBuffer.toString();
    }

    public static String md5(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(bytesToHexString(MessageDigest.getInstance("MD5").digest(str.getBytes())));
        } catch (NoSuchAlgorithmException unused) {
        }
        return stringBuffer.toString();
    }

    public static boolean isDebugMode(Context context) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            applicationInfo = null;
        }
        return applicationInfo != null && (applicationInfo.flags & 2) > 0;
    }

    public static Object getFieldValue(Object obj, String str) {
        try {
            for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                Field declaredField = superclass.getDeclaredField(str);
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    return declaredField.get(obj);
                }
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public static void setFieldValue(Object obj, String str, Object obj2) {
        try {
            for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                Field declaredField = superclass.getDeclaredField(str);
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    declaredField.set(obj, obj2);
                }
            }
        } catch (Exception unused) {
        }
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

    public static byte[] hexStringToBytes(String str) {
        byte[] bArr = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};
        byte[] bArr2 = new byte[128];
        int i = 0;
        for (int i2 = 0; i2 < 16; i2++) {
            bArr2[bArr[i2]] = (byte) i2;
        }
        bArr2[65] = bArr2[97];
        bArr2[66] = bArr2[98];
        bArr2[67] = bArr2[99];
        bArr2[68] = bArr2[100];
        bArr2[69] = bArr2[101];
        bArr2[70] = bArr2[102];
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = str.length();
        while (length > 0 && isSpace(str.charAt(length - 1))) {
            length--;
        }
        while (i < length) {
            while (i < length && isSpace(str.charAt(i))) {
                i++;
            }
            int i3 = i + 1;
            byte b = bArr2[str.charAt(i)];
            while (i3 < length && isSpace(str.charAt(i3))) {
                i3++;
            }
            byteArrayOutputStream.write((b << 4) | bArr2[str.charAt(i3)]);
            i = i3 + 1;
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static Method getMethod(Object obj, String str, Class<?>... clsArr) {
        dwAssert(obj != null);
        try {
            return obj.getClass().getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean checkThreadSafe(long j, String str, boolean z) {
        return Thread.currentThread().getId() == j;
    }

    public static void assertThreadSafe(long j, String str, boolean z) {
        dwAssert(checkThreadSafe(j, str, z));
    }

    public static void assertInMainThread(String str) {
        assertThreadSafe(Looper.getMainLooper().getThread().getId(), str, true);
    }

    public static void assertNotInMainThread(String str) {
        assertThreadSafe(Looper.getMainLooper().getThread().getId(), str, false);
    }

    public static String readAssets(Context context, String str) {
        String string = "";
        try {
            InputStream inputStreamOpen = context.getAssets().open(str);
            string = readString(inputStreamOpen);
            inputStreamOpen.close();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String readRawRes(Context context, int i) {
        String string = "";
        try {
            InputStream inputStreamOpenRawResource = context.getResources().openRawResource(i);
            string = readString(inputStreamOpenRawResource);
            inputStreamOpenRawResource.close();
            return string;
        } catch (IOException e) {
            e.printStackTrace();
            return string;
        }
    }

    public static String readString(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int i = inputStream.read(bArr);
            if (i != -1) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                byteArrayOutputStream.close();
                inputStream.close();
                return byteArrayOutputStream.toString();
            }
        }
    }

    public static String getSimOperator(Context context) {
        return ((TelephonyManager) context.getSystemService("phone")).getSimOperator();
    }

    public static String getOperator(Context context) {
        String simOperator = getSimOperator(context);
        return (simOperator.startsWith("46003") || simOperator.startsWith("46005")) ? Utils.ChinaOperator.CTL : (simOperator.startsWith("46001") || simOperator.startsWith("46006")) ? Utils.ChinaOperator.UNICOM : (simOperator.startsWith("46000") || simOperator.startsWith("46002") || simOperator.startsWith("46007") || simOperator.startsWith("46020")) ? Utils.ChinaOperator.CMCC : Utils.ChinaOperator.UNKNOWN;
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return Utils.NetworkType.Wifi;
            }
            if (type == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                return (subtype == 7 || subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 10 || subtype == 9) ? Utils.NetworkType.Mobile3G : Utils.NetworkType.Mobile2G;
            }
        }
        return "";
    }

    public static String fileMd5(String str) {
        return fileMd5(new File(str));
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0044, code lost:
    
        if (r0 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0046, code lost:
    
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x004b, code lost:
    
        if (r0 == null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x004f, code lost:
    
        if (r0 == null) goto L34;
     */
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
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L4a java.io.FileNotFoundException -> L4e
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L3c java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L4a java.io.FileNotFoundException -> L4e
            r5 = 8192(0x2000, float:1.148E-41)
            byte[] r5 = new byte[r5]     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            java.lang.String r0 = "MD5"
            java.security.MessageDigest r0 = java.security.MessageDigest.getInstance(r0)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
        L18:
            int r3 = r2.read(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            r4 = -1
            if (r3 == r4) goto L24
            r4 = 0
            r0.update(r5, r4, r3)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            goto L18
        L24:
            byte[] r5 = r0.digest()     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            java.lang.String r5 = bytesToHexString(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            r1.append(r5)     // Catch: java.lang.Throwable -> L33 java.io.IOException -> L36 java.security.NoSuchAlgorithmException -> L38 java.io.FileNotFoundException -> L3a
            r2.close()     // Catch: java.io.IOException -> L52
            goto L52
        L33:
            r5 = move-exception
            r0 = r2
            goto L3d
        L36:
            r0 = r2
            goto L44
        L38:
            r0 = r2
            goto L4b
        L3a:
            r0 = r2
            goto L4f
        L3c:
            r5 = move-exception
        L3d:
            if (r0 == 0) goto L42
            r0.close()     // Catch: java.io.IOException -> L42
        L42:
            throw r5
        L43:
        L44:
            if (r0 == 0) goto L52
        L46:
            r0.close()     // Catch: java.io.IOException -> L52
            goto L52
        L4a:
        L4b:
            if (r0 == 0) goto L52
            goto L46
        L4e:
        L4f:
            if (r0 == 0) goto L52
            goto L46
        L52:
            java.lang.String r5 = r1.toString()
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.ark.util.Utils.fileMd5(java.io.File):java.lang.String");
    }

    public static boolean isPasswordPlainText(String str) {
        return !StringUtils.isNullOrEmpty(str) && str.length() < 40;
    }

    public static String getHashIfPassIsPlainText(String str) {
        return isPasswordPlainText(str) ? sha1(str) : str;
    }

    public static String getIpString(byte[] bArr) {
        return (bArr[0] & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + (bArr[1] & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + (bArr[2] & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + (bArr[3] & 255);
    }

    public static String getIpString(int i) {
        return (i & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >>> 8) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >>> 16) & 255) + FileUtil.FILE_EXTENSION_SEPARATOR + ((i >>> 24) & 255);
    }

    public static int getPort(List<Integer> list) {
        return list.get(new Random(System.currentTimeMillis()).nextInt(list.size())).intValue();
    }

    public static int getLittleEndianInt(byte[] bArr, int i) {
        return ((bArr[i + 3] << 24) & (-16777216)) | (bArr[i + 0] & 255) | ((bArr[i + 1] << 8) & MotionEventCompat.ACTION_POINTER_INDEX_MASK) | ((bArr[i + 2] << 16) & 16711680);
    }

    public static byte[] toBytes(ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return new byte[0];
        }
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        try {
            int iLimit2 = byteBuffer.limit() - byteBuffer.position();
            byte[] bArr = new byte[iLimit2];
            if (!byteBuffer.hasArray()) {
                byteBuffer.get(bArr);
                return bArr;
            }
            System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + iPosition, bArr, 0, iLimit2);
            return bArr;
        } finally {
            byteBuffer.position(iPosition);
            byteBuffer.limit(iLimit);
        }
    }

    public static char[] getChars(byte[] bArr) {
        Charset charsetForName = Charset.forName("UTF-8");
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(bArr.length);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.flip();
        return charsetForName.decode(byteBufferAllocate).array();
    }

    public static byte[] getBytes(char[] cArr) {
        Charset charsetForName = Charset.forName("UTF-8");
        CharBuffer charBufferAllocate = CharBuffer.allocate(cArr.length);
        charBufferAllocate.put(cArr);
        charBufferAllocate.flip();
        return charsetForName.encode(charBufferAllocate).array();
    }

    public static String getFileExt(String str) {
        int iLastIndexOf = str.lastIndexOf(FileUtil.FILE_EXTENSION_SEPARATOR);
        return iLastIndexOf == -1 ? "" : str.toLowerCase().substring(iLastIndexOf);
    }

    public static String getConversionOfUnits(int i) {
        if (i <= 0) {
            return String.valueOf(0);
        }
        if (i < 1000) {
            return String.valueOf(i);
        }
        int i2 = (i % 1000) / 100;
        String strValueOf = String.valueOf(i / 1000);
        StringBuilder sb = new StringBuilder();
        sb.append(strValueOf);
        String str = "K";
        if (i2 != 0) {
            str = FileUtil.FILE_EXTENSION_SEPARATOR + String.valueOf(i2) + "K";
        }
        sb.append(str);
        return sb.toString();
    }
}
