package com.getui.gtc.dim.d;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcel;
import android.os.Parcelable;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.base.annotation.MutableMethod;
import com.igexin.base.util.IOUtils;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class b {
    private static final Map<String, String> a = new HashMap<String, String>() { // from class: com.getui.gtc.dim.d.b.1
        {
            put("46000", "中国移动");
            put("46002", "中国移动");
            put("46007", "中国移动");
            put("46008", "中国移动");
            put("46001", "中国联通");
            put("46006", "中国联通");
            put("46009", "中国联通");
            put("46003", "中国电信");
            put("46005", "中国电信");
            put("46011", "中国电信");
            put("46004", "中国卫通");
            put("46020", "中国铁通");
        }
    };

    @MutableMethod(name = "imei,imsi,iccid")
    public static Object a(int i, String str, Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (telephonyManager == null || i < 0) {
                return null;
            }
            return telephonyManager.getClass().getMethod(str, c(str)).invoke(telephonyManager, Integer.valueOf(i));
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }

    public static Object a(byte[] bArr) throws Exception {
        byte b = bArr[0];
        byte[] bArr2 = new byte[bArr.length - 1];
        System.arraycopy(bArr, 1, bArr2, 0, bArr.length - 1);
        switch (b) {
            case 0:
                return b(bArr2);
            case 1:
                Object objC = c(bArr2);
                return objC instanceof c ? ((c) objC).a : objC;
            default:
                throw new RuntimeException("bytesToObject failed, invalid type");
        }
    }

    public static String a(int i) {
        return (i & 255) + "." + ((i >> 8) & 255) + "." + ((i >> 16) & 255) + "." + ((i >> 24) & 255);
    }

    public static String a(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return "其他";
            }
            for (Map.Entry<String, String> entry : a.entrySet()) {
                if (str.startsWith(entry.getKey())) {
                    return entry.getValue();
                }
            }
            return "其他";
        } catch (Throwable th) {
            a.a(th);
            return "其他";
        }
    }

    public static String a(String str, String str2) {
        String str3 = "";
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop " + str).getInputStream()));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    return str3;
                }
                str3 = str3 + line;
            }
        } catch (Exception unused) {
            return str2;
        }
    }

    public static void a(Context context, String str, boolean z) {
        try {
            z = context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a.a(th);
        }
        if (z) {
            return;
        }
        throw new IllegalStateException("permission " + str + " not granted");
    }

    @MutableMethod(name = "networkActive")
    @SuppressLint({"MissingPermission"})
    public static boolean a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isAvailable()) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            a.a(th);
            return false;
        }
    }

    public static boolean a(Context context, String str) {
        try {
            return context.getPackageManager().checkPermission(str, context.getPackageName()) == 0;
        } catch (Throwable th) {
            a.a(th);
            return true;
        }
    }

    public static boolean a(byte[] bArr, File file) throws Exception {
        FileOutputStream fileOutputStream;
        ByteArrayInputStream byteArrayInputStream;
        if (bArr == null) {
            return false;
        }
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    byte[] bArr2 = new byte[521];
                    while (true) {
                        int i = byteArrayInputStream.read(bArr2);
                        if (i == -1) {
                            fileOutputStream.flush();
                            IOUtils.close(byteArrayInputStream);
                            IOUtils.close(fileOutputStream);
                            return true;
                        }
                        fileOutputStream.write(bArr2, 0, i);
                    }
                } catch (Throwable th) {
                    th = th;
                    IOUtils.close(byteArrayInputStream);
                    IOUtils.close(fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = null;
            }
        } catch (Throwable th3) {
            th = th3;
            fileOutputStream = null;
            byteArrayInputStream = null;
        }
    }

    private static byte[] a(Parcelable parcelable) throws Throwable {
        Parcel parcelObtain;
        try {
            parcelObtain = Parcel.obtain();
            try {
                parcelObtain.writeParcelable(parcelable, 0);
                byte[] bArrMarshall = parcelObtain.marshall();
                byte[] bArr = new byte[bArrMarshall.length + 1];
                bArr[0] = 1;
                System.arraycopy(bArrMarshall, 0, bArr, 1, bArrMarshall.length);
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
                return bArr;
            } catch (Throwable th) {
                th = th;
                if (parcelObtain != null) {
                    parcelObtain.recycle();
                }
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            parcelObtain = null;
        }
    }

    public static byte[] a(File file) throws Exception {
        FileInputStream fileInputStream;
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        try {
            fileInputStream = new FileInputStream(file);
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
                try {
                    byte[] bArr = new byte[521];
                    while (true) {
                        int i = fileInputStream.read(bArr);
                        if (i == -1) {
                            byteArrayOutputStream.flush();
                            byte[] byteArray = byteArrayOutputStream.toByteArray();
                            IOUtils.close(fileInputStream);
                            IOUtils.close(byteArrayOutputStream);
                            return byteArray;
                        }
                        byteArrayOutputStream.write(bArr, 0, i);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    IOUtils.close(fileInputStream);
                    IOUtils.close(byteArrayOutputStream);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                byteArrayOutputStream = null;
            }
        } catch (Throwable th4) {
            fileInputStream = null;
            th = th4;
            byteArrayOutputStream = null;
        }
    }

    private static byte[] a(Serializable serializable) throws Throwable {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(serializable);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                byte[] bArr = new byte[byteArray.length + 1];
                bArr[0] = 0;
                System.arraycopy(byteArray, 0, bArr, 1, byteArray.length);
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused) {
                }
                try {
                    objectOutputStream.close();
                } catch (Throwable unused2) {
                }
                return bArr;
            } catch (Throwable th) {
                th = th;
                try {
                    byteArrayOutputStream.close();
                } catch (Throwable unused3) {
                }
                if (objectOutputStream == null) {
                    throw th;
                }
                try {
                    objectOutputStream.close();
                    throw th;
                } catch (Throwable unused4) {
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream = null;
        }
    }

    public static byte[] a(Object obj) throws Exception {
        if (obj instanceof Parcelable) {
            return a((Parcelable) obj);
        }
        if (obj instanceof List) {
            List list = (List) obj;
            if (list.get(0) instanceof Parcelable) {
                return a((Parcelable) new c((List<Parcelable>) list));
            }
        }
        if (obj instanceof Serializable) {
            return a((Serializable) obj);
        }
        throw new IllegalArgumentException("objectToBytes failed, object type is not support: " + obj.getClass().getName());
    }

    public static PackageInfo b(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, TTDownloadField.CALL_DOWNLOAD_MODEL_SET_ID);
        } catch (Throwable th) {
            a.a(th);
            return null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x0031 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:46:? A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.lang.Object b(byte[] r4) throws java.lang.Throwable {
        /*
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r4)
            r4 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L18 java.lang.Throwable -> L1d
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L18 java.lang.Throwable -> L1d
            java.lang.Object r4 = r1.readObject()     // Catch: java.lang.Throwable -> L16 java.lang.Throwable -> L2b
            r0.close()     // Catch: java.lang.Throwable -> L12
        L12:
            r1.close()     // Catch: java.lang.Throwable -> L15
        L15:
            return r4
        L16:
            r2 = move-exception
            goto L1f
        L18:
            r1 = move-exception
            r3 = r1
            r1 = r4
            r4 = r3
            goto L2c
        L1d:
            r2 = move-exception
            r1 = r4
        L1f:
            com.getui.gtc.dim.d.a.a(r2)     // Catch: java.lang.Throwable -> L2b
            r0.close()     // Catch: java.lang.Throwable -> L25
        L25:
            if (r1 == 0) goto L2a
            r1.close()     // Catch: java.lang.Throwable -> L2a
        L2a:
            return r4
        L2b:
            r4 = move-exception
        L2c:
            r0.close()     // Catch: java.lang.Throwable -> L2f
        L2f:
            if (r1 == 0) goto L34
            r1.close()     // Catch: java.lang.Throwable -> L34
        L34:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.getui.gtc.dim.d.b.b(byte[]):java.lang.Object");
    }

    public static String b(String str) throws Throwable {
        Process processExec;
        BufferedReader bufferedReader;
        if (TextUtils.isEmpty(str) || "0.0.0.0".equalsIgnoreCase(str)) {
            return "";
        }
        BufferedReader bufferedReader2 = null;
        try {
            try {
                processExec = Runtime.getRuntime().exec("ip neighbour");
                try {
                    try {
                        bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                        int i = 0;
                        while (true) {
                            try {
                                try {
                                    String line = bufferedReader.readLine();
                                    if (line != null) {
                                        try {
                                        } catch (Throwable th) {
                                            th = th;
                                        }
                                        if (line.contains("192.168") || line.contains("wlan0")) {
                                            if (line.contains("FAILED")) {
                                                continue;
                                            } else {
                                                String[] strArrSplit = line.split(" +");
                                                if (strArrSplit.length < 6) {
                                                    continue;
                                                } else {
                                                    int i2 = i + 1;
                                                    if (i <= 256) {
                                                        try {
                                                            String strReplaceAll = strArrSplit[4].replaceAll(":", "");
                                                            if (str.equalsIgnoreCase(strArrSplit[0])) {
                                                                try {
                                                                    bufferedReader.close();
                                                                } catch (IOException e) {
                                                                    a.a(e);
                                                                }
                                                                if (processExec != null) {
                                                                    try {
                                                                        processExec.destroy();
                                                                    } catch (Throwable th2) {
                                                                        a.a(th2);
                                                                    }
                                                                }
                                                                return strReplaceAll;
                                                            }
                                                            i = i2;
                                                        } catch (Throwable th3) {
                                                            th = th3;
                                                            i = i2;
                                                            a.a(th);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e2) {
                                        a.a(e2);
                                    }
                                    if (processExec == null) {
                                        return "";
                                    }
                                    processExec.destroy();
                                } catch (Throwable th4) {
                                    th = th4;
                                    bufferedReader2 = bufferedReader;
                                    a.a(th);
                                    if (bufferedReader2 != null) {
                                        try {
                                            bufferedReader2.close();
                                        } catch (IOException e3) {
                                            a.a(e3);
                                        }
                                    }
                                    if (processExec == null) {
                                        return "";
                                    }
                                    processExec.destroy();
                                    return "";
                                }
                            } catch (Throwable th5) {
                                th = th5;
                                if (bufferedReader != null) {
                                    try {
                                        bufferedReader.close();
                                    } catch (IOException e4) {
                                        a.a(e4);
                                    }
                                }
                                if (processExec == null) {
                                    throw th;
                                }
                                try {
                                    processExec.destroy();
                                    throw th;
                                } catch (Throwable th6) {
                                    a.a(th6);
                                    throw th;
                                }
                            }
                        }
                    } catch (Throwable th7) {
                        th = th7;
                    }
                } catch (Throwable th8) {
                    th = th8;
                    bufferedReader = bufferedReader2;
                }
            } catch (Throwable th9) {
                a.a(th9);
                return "";
            }
        } catch (Throwable th10) {
            th = th10;
            processExec = null;
            bufferedReader = null;
        }
    }

    @MutableMethod(name = "networkType")
    @SuppressLint({"MissingPermission"})
    public static boolean b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 0) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            a.a(th);
            return false;
        }
    }

    private static Object c(byte[] bArr) throws Throwable {
        Parcel parcelObtain;
        try {
            parcelObtain = Parcel.obtain();
        } catch (Throwable th) {
            th = th;
            parcelObtain = null;
        }
        try {
            parcelObtain.unmarshall(bArr, 0, bArr.length);
            parcelObtain.setDataPosition(0);
            Parcelable parcelable = parcelObtain.readParcelable(GtcProvider.context().getClassLoader());
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            return parcelable;
        } catch (Throwable th2) {
            th = th2;
            if (parcelObtain != null) {
                parcelObtain.recycle();
            }
            throw th;
        }
    }

    @MutableMethod(name = "networkType")
    @SuppressLint({"MissingPermission"})
    public static boolean c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.getType() == 1) {
                    return true;
                }
            }
            return false;
        } catch (Throwable th) {
            a.a(th);
            return false;
        }
    }

    @MutableMethod(name = "imei,imsi,iccid")
    private static Class[] c(String str) {
        Class<?>[] parameterTypes = null;
        try {
            Method[] declaredMethods = TelephonyManager.class.getDeclaredMethods();
            for (int i = 0; i < declaredMethods.length; i++) {
                if (str.equals(declaredMethods[i].getName())) {
                    parameterTypes = declaredMethods[i].getParameterTypes();
                    if (parameterTypes.length > 0) {
                        break;
                    }
                }
            }
        } catch (Throwable th) {
            a.a(th);
        }
        return parameterTypes;
    }
}
