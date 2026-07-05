package com.huya.statistics.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.huya.statistics.log.SLog;
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

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public final class NetworkUtil {
    private static final String CHARSET = "utf-8";
    private static final String TAG = "Network";
    private static final int TIMEOUT = 10000;

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
            SLog.debug(TAG, "get", e);
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
            SLog.debug(TAG, "post", e);
            return "";
        }
    }

    public static byte[] post(String str, byte[] bArr, int i) {
        for (int i2 = 0; i2 <= i; i2++) {
            byte[] bArrPost = post(str, bArr);
            if (bArrPost != null) {
                return bArrPost;
            }
        }
        return null;
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
                SLog.debug(TAG, "parseParams", e);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:31:0x007d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0085  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] httpRequest(java.lang.String r6, byte[] r7, boolean r8) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Network"
            r1 = 0
            r2 = 0
            if (r7 == 0) goto Le
            int r3 = r7.length     // Catch: java.lang.Throwable -> L8 java.lang.Exception -> Lb
            goto Lf
        L8:
            r6 = move-exception
            goto L83
        Lb:
            r6 = move-exception
            r7 = r1
            goto L71
        Le:
            r3 = 0
        Lf:
            r4 = 10000(0x2710, float:1.4013E-41)
            java.net.HttpURLConnection r6 = createConnection(r6, r3, r4, r8)     // Catch: java.lang.Throwable -> L8 java.lang.Exception -> Lb
            if (r3 <= 0) goto L37
            r8 = 10240(0x2800, float:1.4349E-41)
            if (r3 <= r8) goto L27
            byte[] r7 = gzip(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            int r3 = r7.length     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.String r8 = "Content-Encoding"
            java.lang.String r4 = "gzip"
            r6.setRequestProperty(r8, r4)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
        L27:
            java.lang.String r8 = "Content-Length"
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r6.setRequestProperty(r8, r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.io.OutputStream r8 = r6.getOutputStream()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r8.write(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
        L37:
            int r7 = r6.getResponseCode()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L4d
            java.io.InputStream r7 = r6.getInputStream()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            byte[] r7 = read(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            if (r6 == 0) goto L4c
            r6.disconnect()
        L4c:
            return r7
        L4d:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r8.<init>()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.String r3 = "httpRequest response code : "
            r8.append(r3)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            r8.append(r7)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.String r7 = r8.toString()     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            java.lang.Object[] r8 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            com.huya.statistics.log.SLog.debug(r0, r7, r8)     // Catch: java.lang.Throwable -> L69 java.lang.Exception -> L6d
            if (r6 == 0) goto L80
            r6.disconnect()
            goto L80
        L69:
            r7 = move-exception
            r1 = r6
            r6 = r7
            goto L83
        L6d:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L71:
            java.lang.String r8 = "httpRequest"
            r3 = 1
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L81
            r3[r2] = r6     // Catch: java.lang.Throwable -> L81
            com.huya.statistics.log.SLog.debug(r0, r8, r3)     // Catch: java.lang.Throwable -> L81
            if (r7 == 0) goto L80
            r7.disconnect()
        L80:
            return r1
        L81:
            r6 = move-exception
            r1 = r7
        L83:
            if (r1 == 0) goto L88
            r1.disconnect()
        L88:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.statistics.util.NetworkUtil.httpRequest(java.lang.String, byte[], boolean):byte[]");
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
        httpURLConnection.setReadTimeout(60000);
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
            Log.e(TAG, "read", e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    public static boolean isNetworkAvailable(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                if (activeNetworkInfo.isConnected()) {
                    return true;
                }
                if (activeNetworkInfo.isAvailable()) {
                    if (activeNetworkInfo.isConnectedOrConnecting()) {
                        return true;
                    }
                }
            }
            return false;
        } catch (Exception e) {
            SLog.debug(TAG, "isNetworkAvailable", e);
            return false;
        }
    }
}
