package com.duowan.monitor.utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.tools.network.ContentType;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class NetworkUtil {
    private static final String CHARSET = "utf-8";
    private static final String TAG = "Network";
    private static final int TIMEOUT = 10000;

    public static String get(String str, Map<String, String> map, int i) throws Throwable {
        String str2 = "";
        for (int i2 = 0; i2 <= i; i2++) {
            str2 = get(str, map);
            if (!TextUtils.isEmpty(str2)) {
                return str2;
            }
        }
        return str2;
    }

    public static String get(String str, Map<String, String> map) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        byte[] bArrHttpRequest = httpRequest(sb.toString() + parseParams(map, CHARSET), null, false);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException e) {
            MonitorLog.d(TAG, "get", e);
            return "";
        }
    }

    public static String post(String str, Map<String, String> map) throws Throwable {
        byte[] bArrHttpRequest = httpRequest(str, parseParams(map, CHARSET).getBytes(), false);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException e) {
            MonitorLog.d(TAG, "post", e);
            return "";
        }
    }

    public static byte[] post(String str, byte[] bArr) {
        return httpRequest(str, bArr, true);
    }

    private static String parseParams(Map<String, String> map, String str) {
        if (map != null && !map.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            try {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    sb.append(URLEncoder.encode(entry.getKey(), str));
                    sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                    sb.append(URLEncoder.encode(entry.getValue(), str));
                    sb.append("&");
                }
                return sb.substring(0, sb.length() - 1);
            } catch (UnsupportedEncodingException e) {
                MonitorLog.e(TAG, "parseParams", e);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x0075  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] httpRequest(java.lang.String r5, byte[] r6, boolean r7) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Network"
            r1 = 0
            if (r6 == 0) goto Ld
            int r2 = r6.length     // Catch: java.lang.Throwable -> L7 java.lang.Exception -> La
            goto Le
        L7:
            r5 = move-exception
            goto L7b
        La:
            r5 = move-exception
            r6 = r1
            goto L6e
        Ld:
            r2 = 0
        Le:
            r3 = 10000(0x2710, float:1.4013E-41)
            java.net.HttpURLConnection r5 = createConnection(r5, r2, r3, r7)     // Catch: java.lang.Throwable -> L7 java.lang.Exception -> La
            if (r2 <= 0) goto L36
            r7 = 1024(0x400, float:1.435E-42)
            if (r2 <= r7) goto L26
            byte[] r6 = gzip(r6)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            int r2 = r6.length     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.String r7 = "Content-Encoding"
            java.lang.String r3 = "gzip"
            r5.setRequestProperty(r7, r3)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
        L26:
            java.lang.String r7 = "Content-Length"
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r5.setRequestProperty(r7, r2)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.io.OutputStream r7 = r5.getOutputStream()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r7.write(r6)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
        L36:
            int r6 = r5.getResponseCode()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r7.<init>()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.String r2 = "httpRequest response code : "
            r7.append(r2)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r7.append(r6)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            java.lang.String r7 = r7.toString()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            com.duowan.monitor.utility.MonitorLog.d(r0, r7)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            r7 = 200(0xc8, float:2.8E-43)
            if (r6 != r7) goto L60
            java.io.InputStream r6 = r5.getInputStream()     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            byte[] r6 = read(r6)     // Catch: java.lang.Throwable -> L66 java.lang.Exception -> L6a
            if (r5 == 0) goto L5f
            r5.disconnect()
        L5f:
            return r6
        L60:
            if (r5 == 0) goto L78
            r5.disconnect()
            goto L78
        L66:
            r6 = move-exception
            r1 = r5
            r5 = r6
            goto L7b
        L6a:
            r6 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
        L6e:
            java.lang.String r7 = "httpRequest"
            com.duowan.monitor.utility.MonitorLog.d(r0, r7, r5)     // Catch: java.lang.Throwable -> L79
            if (r6 == 0) goto L78
            r6.disconnect()
        L78:
            return r1
        L79:
            r5 = move-exception
            r1 = r6
        L7b:
            if (r1 == 0) goto L80
            r1.disconnect()
        L80:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.duowan.monitor.utility.NetworkUtil.httpRequest(java.lang.String, byte[], boolean):byte[]");
    }

    private static byte[] gzip(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static HttpURLConnection createConnection(String str, int i, int i2, boolean z) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(i2);
        httpURLConnection.setUseCaches(false);
        if (i > 0) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", z ? "application/multipart-formdata" : ContentType.FORM);
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        return httpURLConnection;
    }

    private static byte[] read(InputStream inputStream) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byte[] bArr = new byte[4096];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, i);
            }
        } catch (IOException e) {
            MonitorLog.e(TAG, "read", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return false;
            }
            if (!activeNetworkInfo.isConnected()) {
                if (!activeNetworkInfo.isAvailable()) {
                    return false;
                }
                if (!activeNetworkInfo.isConnectedOrConnecting()) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            MonitorLog.d(TAG, "isNetworkAvailable", e);
            return false;
        }
    }
}
