package com.ishumei.O000O0000OOoO;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Patterns;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O000O0000OoO {
    /* JADX WARN: Removed duplicated region for block: B:34:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String O0000O000000oO(java.io.File r9) throws java.lang.Throwable {
        /*
            if (r9 == 0) goto L62
            boolean r0 = r9.exists()
            if (r0 == 0) goto L62
            r0 = 0
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L47
            r1.<init>(r9)     // Catch: java.lang.Throwable -> L43 java.lang.Exception -> L47
            java.nio.channels.FileChannel r9 = r1.getChannel()     // Catch: java.lang.Throwable -> L3c java.lang.Exception -> L3f
            r3 = 0
            r5 = 63
            r7 = 1
            r2 = r9
            java.nio.channels.FileLock r0 = r2.lock(r3, r5, r7)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            byte[] r2 = O0000O000000oO(r9)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            java.lang.String r3 = new java.lang.String     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            java.lang.String r4 = "utf-8"
            r3.<init>(r2, r4)     // Catch: java.lang.Throwable -> L35 java.lang.Exception -> L37
            if (r0 == 0) goto L2c
            r0.release()
        L2c:
            if (r9 == 0) goto L31
            r9.close()
        L31:
            O0000O000000oO(r1)
            return r3
        L35:
            r2 = move-exception
            goto L54
        L37:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L4a
        L3c:
            r2 = move-exception
            r9 = r0
            goto L54
        L3f:
            r2 = move-exception
            r9 = r0
            r0 = r1
            goto L49
        L43:
            r2 = move-exception
            r9 = r0
            r1 = r9
            goto L54
        L47:
            r2 = move-exception
            r9 = r0
        L49:
            r1 = r9
        L4a:
            java.io.IOException r3 = new java.io.IOException     // Catch: java.lang.Throwable -> L50
            r3.<init>(r2)     // Catch: java.lang.Throwable -> L50
            throw r3     // Catch: java.lang.Throwable -> L50
        L50:
            r2 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
        L54:
            if (r0 == 0) goto L59
            r0.release()
        L59:
            if (r9 == 0) goto L5e
            r9.close()
        L5e:
            O0000O000000oO(r1)
            throw r2
        L62:
            java.io.IOException r9 = new java.io.IOException
            java.lang.String r0 = "not exist"
            r9.<init>(r0)
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(java.io.File):java.lang.String");
    }

    public static String O0000O000000oO(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            if (stringBuffer.length() > 0) {
                stringBuffer.append(":");
            }
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                hexString = "0" + hexString;
            }
            stringBuffer.append(hexString);
        }
        return stringBuffer.toString();
    }

    public static JSONArray O0000O000000oO(Object obj) throws JSONException {
        if (!obj.getClass().isArray()) {
            throw new JSONException("Not a primitive data: " + obj.getClass());
        }
        int length = Array.getLength(obj);
        JSONArray jSONArray = new JSONArray();
        for (int i = 0; i < length; i++) {
            jSONArray.put(O000O00000OoO(Array.get(obj, i)));
        }
        return jSONArray;
    }

    public static JSONArray O0000O000000oO(Collection collection) {
        JSONArray jSONArray = new JSONArray();
        if (collection != null) {
            Iterator it = collection.iterator();
            while (it.hasNext()) {
                jSONArray.put(O000O00000OoO(it.next()));
            }
        }
        return jSONArray;
    }

    public static JSONObject O0000O000000oO(Map<?, ?> map) {
        JSONObject jSONObject = new JSONObject();
        try {
        } catch (Exception unused) {
        }
        for (Map.Entry<?, ?> entry : map.entrySet()) {
            String str = (String) entry.getKey();
            if (str == null) {
                throw new NullPointerException("key == null");
            }
            try {
                jSONObject.put(str, O000O00000OoO(entry.getValue()));
            } catch (JSONException unused2) {
            }
            return jSONObject;
        }
        return jSONObject;
    }

    public static void O0000O000000oO(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception unused) {
            }
        }
    }

    public static void O0000O000000oO(File file, String str) throws Throwable {
        if (file == null || O000O0000Oo0O.O0000O000000oO(str)) {
            throw new IOException("file or bytes empty");
        }
        O0000O000000oO(file, str.getBytes("utf-8"));
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0057  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void O0000O000000oO(java.io.File r4, byte[] r5) throws java.lang.Throwable {
        /*
            if (r4 == 0) goto L5e
            if (r5 == 0) goto L5e
            r0 = 0
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L43
            r1.<init>(r4)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L43
            java.nio.channels.FileChannel r4 = r1.getChannel()     // Catch: java.lang.Throwable -> L38 java.lang.Exception -> L3b
            java.nio.channels.FileLock r0 = r4.lock()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            java.nio.ByteBuffer r5 = java.nio.ByteBuffer.wrap(r5)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
        L16:
            boolean r2 = r5.hasRemaining()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            if (r2 == 0) goto L20
            r4.write(r5)     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            goto L16
        L20:
            r1.flush()     // Catch: java.lang.Throwable -> L31 java.lang.Exception -> L33
            if (r0 == 0) goto L28
            r0.release()
        L28:
            if (r4 == 0) goto L2d
            r4.close()
        L2d:
            O0000O000000oO(r1)
            return
        L31:
            r5 = move-exception
            goto L50
        L33:
            r5 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
            goto L46
        L38:
            r5 = move-exception
            r4 = r0
            goto L50
        L3b:
            r5 = move-exception
            r4 = r0
            r0 = r1
            goto L45
        L3f:
            r5 = move-exception
            r4 = r0
            r1 = r4
            goto L50
        L43:
            r5 = move-exception
            r4 = r0
        L45:
            r1 = r4
        L46:
            java.io.IOException r2 = new java.io.IOException     // Catch: java.lang.Throwable -> L4c
            r2.<init>(r5)     // Catch: java.lang.Throwable -> L4c
            throw r2     // Catch: java.lang.Throwable -> L4c
        L4c:
            r5 = move-exception
            r3 = r1
            r1 = r0
            r0 = r3
        L50:
            if (r0 == 0) goto L55
            r0.release()
        L55:
            if (r4 == 0) goto L5a
            r4.close()
        L5a:
            O0000O000000oO(r1)
            throw r5
        L5e:
            java.io.IOException r4 = new java.io.IOException
            java.lang.String r5 = "file or bytes empty"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.ishumei.O000O0000OOoO.O000O0000OoO.O0000O000000oO(java.io.File, byte[]):void");
    }

    public static void O0000O000000oO(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception unused) {
            }
        }
    }

    public static boolean O0000O000000oO(String str) {
        try {
            return new File(Environment.getExternalStorageDirectory() + "/" + str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    private static byte[] O0000O000000oO(FileChannel fileChannel) throws Throwable {
        ByteArrayOutputStream byteArrayOutputStream;
        ByteArrayOutputStream byteArrayOutputStream2 = null;
        try {
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (Throwable th) {
                th = th;
            }
        } catch (Exception e) {
            e = e;
        }
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(100);
            int i = 0;
            int i2 = 0;
            while (true) {
                int i3 = fileChannel.read(byteBufferAllocate, i);
                if (i3 <= 0) {
                    break;
                }
                i += i3;
                i2 += i3;
            }
            byte[] bArrArray = byteBufferAllocate.array();
            if (i2 >= 4 && (bArrArray[0] & 255) == 0 && (bArrArray[1] & 255) == 0 && (bArrArray[2] & 255) == 0 && (bArrArray[3] & 255) == 0) {
                throw new IOException("read bytes not utf-8");
            }
            byteArrayOutputStream.write(bArrArray, 0, i2);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            O0000O000000oO((Closeable) byteArrayOutputStream);
            return byteArray;
        } catch (Exception e2) {
            e = e2;
            throw new IOException(e);
        } catch (Throwable th2) {
            th = th2;
            byteArrayOutputStream2 = byteArrayOutputStream;
            O0000O000000oO((Closeable) byteArrayOutputStream2);
            throw th;
        }
    }

    private static Object O000O00000OoO(Object obj) {
        if (obj == null) {
            return null;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject)) {
            return obj;
        }
        if (obj instanceof Collection) {
            return O0000O000000oO((Collection) obj);
        }
        if (obj.getClass().isArray()) {
            return O0000O000000oO(obj);
        }
        if (obj instanceof Map) {
            return O0000O000000oO((Map<?, ?>) obj);
        }
        if (!(obj instanceof Boolean) && !(obj instanceof Byte) && !(obj instanceof Character) && !(obj instanceof Double) && !(obj instanceof Float) && !(obj instanceof Integer) && !(obj instanceof Long) && !(obj instanceof Short) && !(obj instanceof String)) {
            if (obj.getClass().getPackage().getName().startsWith("java.")) {
                return obj.toString();
            }
            return null;
        }
        return obj;
    }

    public static String O000O00000OoO(byte[] bArr) throws IOException {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            byte[] bArrDigest = MessageDigest.getInstance("MD5").digest(bArr);
            StringBuilder sb = new StringBuilder(bArrDigest.length * 2);
            for (byte b : bArrDigest) {
                int i = b & 255;
                if (i < 16) {
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            O000O00000oO.O0000O000000oO(e);
            throw new IOException("fail to md5 data");
        }
    }

    public static boolean O000O00000OoO(String str) {
        try {
            return new File(str).exists();
        } catch (Exception unused) {
            return false;
        }
    }

    public static List<String> O000O00000o0O(String str) throws Throwable {
        BufferedReader bufferedReader;
        Throwable th;
        Exception e;
        ArrayList arrayList = new ArrayList();
        try {
            bufferedReader = new BufferedReader(new FileReader(new File(str)));
            while (true) {
                try {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            O0000O000000oO((Closeable) bufferedReader);
                            return arrayList;
                        }
                        if (!O000O0000Oo0O.O0000O000000oO(line)) {
                            arrayList.add(line);
                        }
                    } catch (Exception e2) {
                        e = e2;
                        throw new IOException(e);
                    }
                } catch (Throwable th2) {
                    th = th2;
                    O0000O000000oO((Closeable) bufferedReader);
                    throw th;
                }
            }
        } catch (Exception e3) {
            bufferedReader = null;
            e = e3;
        } catch (Throwable th3) {
            bufferedReader = null;
            th = th3;
            O0000O000000oO((Closeable) bufferedReader);
            throw th;
        }
    }

    public static String O000O00000oO(String str) {
        return (str == null || str.isEmpty()) ? "" : str.replaceAll(":", "").toLowerCase();
    }

    public static String O000O0000O0oO(String str) {
        if (str != null && str.length() != 0) {
            try {
                return URLEncoder.encode(str, "UTF-8");
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static String O000O0000OOoO(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return O000O00000OoO(str.getBytes("utf-8"));
        } catch (Exception unused) {
            return "";
        }
    }

    public static byte[] O000O0000Oo0O(String str) throws IOException {
        try {
            return Base64.decode(str.getBytes("utf-8"), 0);
        } catch (Exception e) {
            throw new IOException(e);
        }
    }

    public static String O000O0000OoO(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Matcher matcher = Patterns.DOMAIN_NAME.matcher(str);
        if (matcher.find()) {
            return matcher.group(0);
        }
        return null;
    }

    public static boolean O00O0000OooO(String str) {
        if (str == null) {
            return false;
        }
        return Patterns.IP_ADDRESS.matcher(str).matches();
    }
}
