package com.getui.gtc.c;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import com.getui.gtc.api.GtcIdCallback;
import com.getui.gtc.base.GtcProvider;
import com.getui.gtc.e.c;
import com.getui.gtc.e.d;
import com.getui.gtc.h.c;
import com.getui.gtc.server.ServerManager;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public final class b {
    public static String b;
    public static String c;
    public static volatile String d;
    public static String e;
    public static String f;
    public static String g;
    public static String a = GtcProvider.context().getPackageName();
    private static final List<GtcIdCallback> j = new ArrayList();
    public static String h = "MHwwDQYJKoZIhvcNAQEBBQADawAwaAJhAJp1rROuvBF7sBSnvLaesj2iFhMcY8aXyLvpnNLKs2wjL3JmEnyr++SlVa35liUlzi83tnAFkn3A9GB7pHBNzawyUkBh8WUhq5bnFIkk2RaDa6+5MpG84DEv52p7RR+aWwIDAQAB";
    public static String i = "69d747c4b9f641baf4004be4297e9f3b";

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.String[]] */
    /* JADX WARN: Type inference failed for: r2v17, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.String[]] */
    public static void a() {
        String[] strArrSplit;
        String[] strArrSplit2;
        String[] strArrSplit3;
        String str;
        try {
            ApplicationInfo applicationInfo = GtcProvider.context().getPackageManager().getApplicationInfo(GtcProvider.context().getPackageName(), 128);
            if (applicationInfo.metaData != null) {
                String string = applicationInfo.metaData.getString("GTC_C");
                if (!TextUtils.isEmpty(string)) {
                    e = string;
                }
                String string2 = applicationInfo.metaData.getString("GTC_B");
                if (!TextUtils.isEmpty(string2)) {
                    f = string2;
                }
                String string3 = applicationInfo.metaData.getString("GTC_A");
                if (!TextUtils.isEmpty(string3)) {
                    g = string3;
                }
                String string4 = applicationInfo.metaData.getString("GTC_P");
                if (!TextUtils.isEmpty(string4)) {
                    h = string4;
                }
                String string5 = applicationInfo.metaData.getString("GTC_K");
                if (!TextUtils.isEmpty(string5)) {
                    i = string5;
                }
                String string6 = applicationInfo.metaData.getString("GETUI_APPID");
                if (TextUtils.isEmpty(string6)) {
                    string6 = applicationInfo.metaData.getString("GETUI_APP_ID");
                    if (TextUtils.isEmpty(string6)) {
                        string6 = applicationInfo.metaData.getString(com.igexin.push.core.b.a);
                        if (TextUtils.isEmpty(string6)) {
                            string6 = applicationInfo.metaData.getString("GI_APPID");
                            if (TextUtils.isEmpty(string6)) {
                                string6 = applicationInfo.metaData.getString("GI_APP_ID");
                                if (TextUtils.isEmpty(string6)) {
                                    string6 = applicationInfo.metaData.getString("GS_APPID");
                                    if (TextUtils.isEmpty(string6)) {
                                        string6 = applicationInfo.metaData.getString("GS_APP_ID");
                                        if (TextUtils.isEmpty(string6)) {
                                            string6 = applicationInfo.metaData.getString("GY_APPID");
                                            if (TextUtils.isEmpty(string6)) {
                                                String string7 = applicationInfo.metaData.getString("GY_APP_ID");
                                                if (!TextUtils.isEmpty(string7)) {
                                                    a = string7;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    a = string6;
                } else {
                    a = string6;
                }
            }
        } catch (Throwable th) {
            com.getui.gtc.i.c.a.b(th);
        }
        HashMap map = new HashMap();
        Object[] objArrAsList = new String[0];
        try {
            try {
                strArrSplit = e.split(com.igexin.push.core.b.aj);
            } catch (Throwable th2) {
                map.put("gtc.cs", Arrays.asList(objArrAsList));
                throw th2;
            }
        } catch (Throwable unused) {
            strArrSplit = "https://c-gtc.getui.net,https://c-gtc.gepush.com".split(com.igexin.push.core.b.aj);
        }
        objArrAsList = Arrays.asList(strArrSplit);
        map.put("gtc.cs", objArrAsList);
        Object[] objArrAsList2 = new String[0];
        try {
            try {
                strArrSplit2 = g.split(com.igexin.push.core.b.aj);
            } catch (Throwable th3) {
                map.put("gtc.as", Arrays.asList(objArrAsList2));
                throw th3;
            }
        } catch (Throwable unused2) {
            strArrSplit2 = "https://gtc.getui.net,https://gtc.gepush.com".split(com.igexin.push.core.b.aj);
        }
        objArrAsList2 = Arrays.asList(strArrSplit2);
        map.put("gtc.as", objArrAsList2);
        Object[] objArrAsList3 = new String[0];
        try {
            try {
                strArrSplit3 = f.split(com.igexin.push.core.b.aj);
            } catch (Throwable th4) {
                map.put("gtc.bs", Arrays.asList(objArrAsList3));
                throw th4;
            }
        } catch (Throwable unused3) {
            strArrSplit3 = "https://b-gtc.getui.net,https://b-gtc.gepush.com".split(com.igexin.push.core.b.aj);
        }
        objArrAsList3 = Arrays.asList(strArrSplit3);
        map.put("gtc.bs", objArrAsList3);
        ServerManager.addBuildInServerMap(map);
        ServerManager.updateConfigServerMap();
        if (TextUtils.isEmpty(d)) {
            String str2 = c.a.a.a.c;
            d = str2;
            if (TextUtils.isEmpty(str2)) {
                d = c();
            }
        }
        if (TextUtils.isEmpty(d)) {
            if (TextUtils.isEmpty(c)) {
                c = c.a.a.a.d;
            }
            if (TextUtils.isEmpty(c)) {
                if (TextUtils.isEmpty(b)) {
                    Context context = GtcProvider.context();
                    String strA = com.getui.gtc.b.b.a(context);
                    if (TextUtils.isEmpty(strA)) {
                        strA = context.getSharedPreferences("GINSIGHT-SDK-PREFERENCE", 0).getString("gicid", null);
                    }
                    if (TextUtils.isEmpty(strA)) {
                        strA = com.getui.gtc.b.a.a(context);
                    }
                    b = strA;
                }
                if (TextUtils.isEmpty(b)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("gtc_");
                    String strA2 = com.getui.gtc.i.a.a.a(UUID.randomUUID().toString() + "-" + System.currentTimeMillis() + "-" + GtcProvider.context().getPackageName());
                    sb.append(strA2);
                    char cCharAt = strA2.charAt(strA2.length() + (-1));
                    if (cCharAt < 16) {
                        sb.append("0");
                    }
                    sb.append(Integer.toHexString(cCharAt));
                    c = sb.toString();
                    d dVar = c.a.a.a;
                    String str3 = c;
                    if (dVar.a(9, str3)) {
                        dVar.d = str3;
                    }
                    str = c;
                    d = str;
                    b(str);
                } else {
                    str = b;
                    d = str;
                    b(str);
                }
            } else {
                str = c;
                d = str;
                b(str);
            }
        }
        Log.d("GTC", "gtcid is " + d);
    }

    public static void a(GtcIdCallback gtcIdCallback) throws RemoteException {
        if (gtcIdCallback != null) {
            gtcIdCallback.onSuccess(d);
            j.add(gtcIdCallback);
        }
    }

    static /* synthetic */ void a(String str) throws Throwable {
        ReentrantReadWriteLock.WriteLock writeLock = new ReentrantReadWriteLock().writeLock();
        FileOutputStream fileOutputStream = null;
        try {
            try {
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
        }
        if (writeLock.tryLock()) {
            File file = new File(d());
            if (!file.exists() && !file.createNewFile()) {
                writeLock.unlock();
                return;
            }
            FileOutputStream fileOutputStream2 = new FileOutputStream(d());
            try {
                fileOutputStream2.write(com.getui.gtc.i.a.a.a(str.getBytes("utf-8")));
                fileOutputStream = fileOutputStream2;
            } catch (Throwable th3) {
                th = th3;
                fileOutputStream = fileOutputStream2;
            }
            com.getui.gtc.i.c.a.c(th);
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (Exception e2) {
                    com.getui.gtc.i.c.a.c(e2);
                }
            }
            writeLock.unlock();
            return;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (Exception e3) {
                com.getui.gtc.i.c.a.c(e3);
            }
        }
        writeLock.unlock();
    }

    private static void b(final String str) {
        com.getui.gtc.h.c.a(str, new c.a() { // from class: com.getui.gtc.c.b.1
            @Override // com.getui.gtc.h.c.a
            public final void a(String str2) throws Throwable {
                b.d = str2;
                if (!TextUtils.equals(str, str2)) {
                    try {
                        Log.d("GTC", "gtcid changed to " + b.d);
                        Iterator it = b.j.iterator();
                        while (it.hasNext()) {
                            ((GtcIdCallback) it.next()).onSuccess(b.d);
                        }
                    } catch (Throwable th) {
                        com.getui.gtc.i.c.a.a(th);
                    }
                }
                b.j.clear();
                d dVar = c.a.a.a;
                String str3 = b.d;
                if (dVar.a(4, str3)) {
                    dVar.c = str3;
                }
                b.a(b.d);
            }
        });
    }

    private static String c() throws Throwable {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        if (!new File(d()).exists()) {
            return null;
        }
        byte[] bArr = new byte[1024];
        try {
            fileInputStream = new FileInputStream(d());
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Exception e2) {
                e = e2;
                byteArrayOutputStream = null;
            } catch (Throwable th) {
                th = th;
                byteArrayOutputStream = null;
            }
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            byteArrayOutputStream = null;
        }
        while (true) {
            try {
                try {
                    int i2 = fileInputStream.read(bArr);
                    if (i2 == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(bArr, 0, i2);
                } catch (Exception e4) {
                    e = e4;
                    com.getui.gtc.i.c.a.c(e);
                    if (fileInputStream != null) {
                        try {
                            fileInputStream.close();
                        } catch (Exception e5) {
                            com.getui.gtc.i.c.a.c(e5);
                        }
                    }
                    if (byteArrayOutputStream == null) {
                        return null;
                    }
                    try {
                        byteArrayOutputStream.close();
                        return null;
                    } catch (Exception e6) {
                        com.getui.gtc.i.c.a.c(e6);
                        return null;
                    }
                }
            } catch (Throwable th3) {
                th = th3;
            }
            th = th3;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (Exception e7) {
                    com.getui.gtc.i.c.a.c(e7);
                }
            }
            if (byteArrayOutputStream == null) {
                throw th;
            }
            try {
                byteArrayOutputStream.close();
                throw th;
            } catch (Exception e8) {
                com.getui.gtc.i.c.a.c(e8);
                throw th;
            }
        }
        String str = new String(com.getui.gtc.i.a.a.b(byteArrayOutputStream.toByteArray()), "utf-8");
        try {
            fileInputStream.close();
        } catch (Exception e9) {
            com.getui.gtc.i.c.a.c(e9);
        }
        try {
            byteArrayOutputStream.close();
        } catch (Exception e10) {
            com.getui.gtc.i.c.a.c(e10);
        }
        return str;
    }

    private static String d() {
        Context context = GtcProvider.context();
        if (context == null) {
            return null;
        }
        File file = new File(GtcProvider.getSdcardPath() + "/libs");
        if (!file.exists()) {
            file.mkdirs();
        }
        return file.getAbsolutePath() + File.separator + context.getPackageName() + "_new.db";
    }
}
