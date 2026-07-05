package com.huya.mtp.utils;

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
import com.huya.mtp.api.MTPApi;
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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Utils {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";
    private static final int SHA1_LENGTH = 40;
    public static final String TAG = "Utils";

    public static class ChinaOperator {
        public static final String CMCC = "CMCC";
        public static final String CTL = "CTL";
        public static final String UNICOM = "UNICOM";
        public static final String UNKNOWN = "Unknown";
    }

    public static class NetworkType {
        public static final String Mobile2G = ",2";
        public static final String Mobile3G = ",3";
        public static final String Unknown = "";
        public static final String Wifi = ",w";
    }

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
        if (Build.VERSION.SDK_INT >= 19) {
            AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService("appops");
            ApplicationInfo applicationInfo = context.getApplicationInfo();
            String packageName = context.getApplicationContext().getPackageName();
            int i = applicationInfo.uid;
            try {
                Class<?> cls = Class.forName(AppOpsManager.class.getName());
                return ((Integer) cls.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class).invoke(appOpsManager, Integer.valueOf(((Integer) cls.getDeclaredField(OP_POST_NOTIFICATION).get(Integer.class)).intValue()), Integer.valueOf(i), packageName)).intValue() == 0;
            } catch (Throwable th) {
                MTPApi.LOGGER.error("Utils", th);
            }
        }
        return true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x002d, code lost:
    
        if (r2.importance != 100) goto L13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x002f, code lost:
    
        return true;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean isForeground(android.content.Context r5) {
        /*
            r0 = 0
            java.lang.String r1 = "activity"
            java.lang.Object r1 = r5.getSystemService(r1)     // Catch: java.lang.Throwable -> L32
            android.app.ActivityManager r1 = (android.app.ActivityManager) r1     // Catch: java.lang.Throwable -> L32
            java.util.List r1 = r1.getRunningAppProcesses()     // Catch: java.lang.Throwable -> L32
            java.util.Iterator r1 = r1.iterator()     // Catch: java.lang.Throwable -> L32
        L11:
            boolean r2 = r1.hasNext()     // Catch: java.lang.Throwable -> L32
            if (r2 == 0) goto L31
            java.lang.Object r2 = r1.next()     // Catch: java.lang.Throwable -> L32
            android.app.ActivityManager$RunningAppProcessInfo r2 = (android.app.ActivityManager.RunningAppProcessInfo) r2     // Catch: java.lang.Throwable -> L32
            java.lang.String r3 = r2.processName     // Catch: java.lang.Throwable -> L32
            java.lang.String r4 = r5.getPackageName()     // Catch: java.lang.Throwable -> L32
            boolean r3 = r3.equals(r4)     // Catch: java.lang.Throwable -> L32
            if (r3 == 0) goto L11
            int r5 = r2.importance     // Catch: java.lang.Throwable -> L32
            r1 = 100
            if (r5 != r1) goto L31
            r5 = 1
            return r5
        L31:
            return r0
        L32:
            r5 = move-exception
            com.huya.mtp.api.LogApi r1 = com.huya.mtp.api.MTPApi.LOGGER
            java.lang.String r2 = "Utils"
            r1.error(r2, r5)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.Utils.isForeground(android.content.Context):boolean");
    }

    public static String getProcessName(Context context) {
        try {
            int iMyPid = Process.myPid();
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            if (activityManager == null) {
                MTPApi.LOGGER.error("Utils", "ActivityManager got null!");
                return "null_name";
            }
            if (activityManager.getRunningAppProcesses() == null) {
                MTPApi.LOGGER.error("Utils", "getRunningAppProcesses got null!");
                return "null_name";
            }
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : activityManager.getRunningAppProcesses()) {
                if (runningAppProcessInfo.pid == iMyPid) {
                    return StringUtils.isNullOrEmpty(runningAppProcessInfo.processName) ? "null_name" : runningAppProcessInfo.processName;
                }
            }
            return "null_name";
        } catch (Throwable th) {
            MTPApi.LOGGER.error("Utils", th);
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
        } catch (NoSuchAlgorithmException e) {
            MTPApi.LOGGER.error(Utils.class, e);
        }
        return stringBuffer.toString();
    }

    public static String md5(String str) {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(bytesToHexString(MessageDigest.getInstance("MD5").digest(str.getBytes())));
        } catch (NoSuchAlgorithmException e) {
            MTPApi.LOGGER.error(Utils.class, e);
        }
        return stringBuffer.toString();
    }

    public static boolean isDebugMode(Context context) {
        ApplicationInfo applicationInfo;
        try {
            applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
        } catch (PackageManager.NameNotFoundException e) {
            MTPApi.LOGGER.error("Utils", e);
            e.printStackTrace();
            applicationInfo = null;
        }
        boolean z = false;
        if (applicationInfo != null && (applicationInfo.flags & 2) > 0) {
            z = true;
        }
        MTPApi.LOGGER.verbose("Utils", "isDebugMode debuggable: " + z);
        return z;
    }

    public static Object getFieldValue(Object obj, String str) {
        try {
            for (Class<?> superclass = obj.getClass(); superclass != null; superclass = superclass.getSuperclass()) {
                Field declaredField = superclass.getDeclaredField(str);
                if (declaredField != null) {
                    declaredField.setAccessible(true);
                    obj = declaredField.get(obj);
                    return obj;
                }
            }
            return null;
        } catch (Exception e) {
            MTPApi.LOGGER.error(obj, "setFieldValue fail : %s %s", str, e);
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
        } catch (Exception e) {
            MTPApi.LOGGER.error(obj, "setFieldValue fail : %s %s", str, e);
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
        Method method;
        dwAssert(obj != null);
        try {
            method = obj.getClass().getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            method = null;
        }
        if (method == null) {
            MTPApi.LOGGER.error((Object) null, "getDeclaredMethod return null.%s, %s", obj, str);
        }
        return method;
    }

    public static boolean checkThreadSafe(long j, String str, boolean z) {
        boolean z2 = Thread.currentThread().getId() == j;
        if (z2 != z) {
            MTPApi.LOGGER.error("ThreadSafeCheck", str);
        }
        return z2;
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
        return (simOperator.startsWith("46003") || simOperator.startsWith("46005")) ? ChinaOperator.CTL : (simOperator.startsWith("46001") || simOperator.startsWith("46006")) ? ChinaOperator.UNICOM : (simOperator.startsWith("46000") || simOperator.startsWith("46002") || simOperator.startsWith("46007") || simOperator.startsWith("46020")) ? ChinaOperator.CMCC : ChinaOperator.UNKNOWN;
    }

    public static String getNetworkType(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo != null) {
            int type = activeNetworkInfo.getType();
            if (type == 1) {
                return NetworkType.Wifi;
            }
            if (type == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                return (subtype == 7 || subtype == 3 || subtype == 5 || subtype == 6 || subtype == 8 || subtype == 10 || subtype == 9) ? NetworkType.Mobile3G : NetworkType.Mobile2G;
            }
        }
        return "";
    }

    public static String fileMd5(String str) {
        return fileMd5(new File(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:28:0x004b A[Catch: IOException -> 0x0061, PHI: r1
  0x004b: PHI (r1v5 java.io.FileInputStream) = (r1v22 java.io.FileInputStream), (r1v23 java.io.FileInputStream), (r1v24 java.io.FileInputStream) binds: [B:33:0x0057, B:27:0x0049, B:36:0x005e] A[DONT_GENERATE, DONT_INLINE], TRY_ENTER, TRY_LEAVE, TryCatch #5 {IOException -> 0x0061, blocks: (B:13:0x0031, B:28:0x004b), top: B:49:0x000b }] */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v17 */
    /* JADX WARN: Type inference failed for: r1v18 */
    /* JADX WARN: Type inference failed for: r1v19 */
    /* JADX WARN: Type inference failed for: r1v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r1v20 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String fileMd5(java.io.File r6) throws java.lang.Throwable {
        /*
            java.lang.Class<com.huya.mtp.utils.Utils> r0 = com.huya.mtp.utils.Utils.class
            r1 = 0
            if (r6 != 0) goto L6
            return r1
        L6:
            java.lang.StringBuffer r2 = new java.lang.StringBuffer
            r2.<init>()
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L4f java.io.FileNotFoundException -> L58
            r3.<init>(r6)     // Catch: java.lang.Throwable -> L41 java.io.IOException -> L43 java.security.NoSuchAlgorithmException -> L4f java.io.FileNotFoundException -> L58
            r6 = 8192(0x2000, float:1.148E-41)
            byte[] r6 = new byte[r6]     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            java.lang.String r1 = "MD5"
            java.security.MessageDigest r1 = java.security.MessageDigest.getInstance(r1)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
        L1a:
            int r4 = r3.read(r6)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            r5 = -1
            if (r4 == r5) goto L26
            r5 = 0
            r1.update(r6, r5, r4)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            goto L1a
        L26:
            byte[] r6 = r1.digest()     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            java.lang.String r6 = bytesToHexString(r6)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            r2.append(r6)     // Catch: java.lang.Throwable -> L35 java.io.IOException -> L38 java.security.NoSuchAlgorithmException -> L3b java.io.FileNotFoundException -> L3e
            r3.close()     // Catch: java.io.IOException -> L61
            goto L61
        L35:
            r6 = move-exception
            r1 = r3
            goto L66
        L38:
            r6 = move-exception
            r1 = r3
            goto L44
        L3b:
            r6 = move-exception
            r1 = r3
            goto L50
        L3e:
            r6 = move-exception
            r1 = r3
            goto L59
        L41:
            r6 = move-exception
            goto L66
        L43:
            r6 = move-exception
        L44:
            com.huya.mtp.api.LogApi r3 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L41
            r3.error(r0, r6)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L61
        L4b:
            r1.close()     // Catch: java.io.IOException -> L61
            goto L61
        L4f:
            r6 = move-exception
        L50:
            com.huya.mtp.api.LogApi r3 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L41
            r3.error(r0, r6)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L61
            goto L4b
        L58:
            r6 = move-exception
        L59:
            com.huya.mtp.api.LogApi r3 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L41
            r3.error(r0, r6)     // Catch: java.lang.Throwable -> L41
            if (r1 == 0) goto L61
            goto L4b
        L61:
            java.lang.String r6 = r2.toString()
            return r6
        L66:
            if (r1 == 0) goto L6b
            r1.close()     // Catch: java.io.IOException -> L6b
        L6b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.utils.Utils.fileMd5(java.io.File):java.lang.String");
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
            if (byteBuffer.hasArray()) {
                System.arraycopy(byteBuffer.array(), byteBuffer.arrayOffset() + iPosition, bArr, 0, iLimit2);
            } else {
                byteBuffer.get(bArr);
            }
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
