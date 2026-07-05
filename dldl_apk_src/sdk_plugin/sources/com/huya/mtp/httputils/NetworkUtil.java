package com.huya.mtp.httputils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.huya.mtp.api.MTPApi;
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
    public static final String TEA_DEFAULT_KEY = "ABCDEFGHIJKLMNOP";
    private static final String TEA_ENCRYPT_HEADER_KEY = "Content-Encrypt";
    private static final String TEA_ENCRYPT_HEADER_VALUE = "yyencrypt";
    private static final int TIMEOUT = 10000;

    public static String get(String str, Map<String, String> map) throws Throwable {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&" : "?");
        byte[] bArrHttpRequest = httpRequest(sb.toString() + parseParams(map, CHARSET), null, false, null);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException e) {
            MTPApi.LOGGER.debug(TAG, "get", e);
            return "";
        }
    }

    public static String post(String str, Map<String, String> map, NetConfig netConfig) throws Throwable {
        byte[] bArrHttpRequest = httpRequest(str, parseParams(map, CHARSET).getBytes(), false, netConfig);
        if (bArrHttpRequest == null) {
            return "";
        }
        try {
            return new String(bArrHttpRequest, CHARSET);
        } catch (UnsupportedEncodingException e) {
            MTPApi.LOGGER.debug(TAG, "post", e);
            return "";
        }
    }

    public static byte[] post(String str, byte[] bArr, int i, NetConfig netConfig) {
        for (int i2 = 0; i2 <= i; i2++) {
            byte[] bArrPost = post(str, bArr, netConfig);
            if (bArrPost != null) {
                return bArrPost;
            }
        }
        return null;
    }

    public static byte[] post(String str, byte[] bArr, NetConfig netConfig) {
        return httpRequest(str, bArr, true, netConfig);
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
                MTPApi.LOGGER.debug(TAG, "parseParams", e);
            }
        }
        return "";
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static byte[] httpRequest(java.lang.String r6, byte[] r7, boolean r8, com.huya.mtp.httputils.NetConfig r9) throws java.lang.Throwable {
        /*
            java.lang.String r0 = "Network"
            r1 = 0
            r2 = 0
            if (r7 == 0) goto Lf
            int r3 = r7.length     // Catch: java.lang.Throwable -> L8 java.lang.Exception -> Lb
            goto L10
        L8:
            r6 = move-exception
            goto La1
        Lb:
            r6 = move-exception
            r7 = r2
            goto L92
        Lf:
            r3 = 0
        L10:
            r4 = 10000(0x2710, float:1.4013E-41)
            java.net.HttpURLConnection r6 = createConnection(r6, r3, r4, r8, r9)     // Catch: java.lang.Throwable -> L8 java.lang.Exception -> Lb
            if (r3 <= 0) goto L58
            if (r9 == 0) goto L1f
            int r8 = r9.getConcentLengthGzip()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            goto L21
        L1f:
            r8 = 1024(0x400, float:1.435E-42)
        L21:
            if (r3 <= r8) goto L2f
            byte[] r7 = gzip(r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            int r3 = r7.length     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.lang.String r8 = "Content-Encoding"
            java.lang.String r4 = "gzip"
            r6.setRequestProperty(r8, r4)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
        L2f:
            if (r9 == 0) goto L48
            boolean r8 = r9.isEncryption()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            if (r8 == 0) goto L48
            java.lang.String r8 = "Content-Encrypt"
            java.lang.String r9 = "yyencrypt"
            r6.setRequestProperty(r8, r9)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.lang.String r8 = "ABCDEFGHIJKLMNOP"
            byte[] r7 = com.huya.mtp.encrypt.HyEncrypt.encrypt(r2, r8, r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            if (r7 == 0) goto L47
            int r1 = r7.length     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
        L47:
            r3 = r1
        L48:
            java.lang.String r8 = "Content-Length"
            java.lang.String r9 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r6.setRequestProperty(r8, r9)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.io.OutputStream r8 = r6.getOutputStream()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r8.write(r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
        L58:
            int r7 = r6.getResponseCode()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            com.huya.mtp.api.LogApi r8 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r9.<init>()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.lang.String r1 = "httpRequest response code : "
            r9.append(r1)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r9.append(r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            java.lang.String r9 = r9.toString()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r8.debug(r0, r9)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            r8 = 200(0xc8, float:2.8E-43)
            if (r7 != r8) goto L84
            java.io.InputStream r7 = r6.getInputStream()     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            byte[] r7 = read(r7)     // Catch: java.lang.Throwable -> L8a java.lang.Exception -> L8e
            if (r6 == 0) goto L83
            r6.disconnect()
        L83:
            return r7
        L84:
            if (r6 == 0) goto L9e
            r6.disconnect()
            goto L9e
        L8a:
            r7 = move-exception
            r2 = r6
            r6 = r7
            goto La1
        L8e:
            r7 = move-exception
            r5 = r7
            r7 = r6
            r6 = r5
        L92:
            com.huya.mtp.api.LogApi r8 = com.huya.mtp.api.MTPApi.LOGGER     // Catch: java.lang.Throwable -> L9f
            java.lang.String r9 = "httpRequest"
            r8.debug(r0, r9, r6)     // Catch: java.lang.Throwable -> L9f
            if (r7 == 0) goto L9e
            r7.disconnect()
        L9e:
            return r2
        L9f:
            r6 = move-exception
            r2 = r7
        La1:
            if (r2 == 0) goto La6
            r2.disconnect()
        La6:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huya.mtp.httputils.NetworkUtil.httpRequest(java.lang.String, byte[], boolean, com.huya.mtp.httputils.NetConfig):byte[]");
    }

    private static byte[] gzip(byte[] bArr) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static HttpURLConnection createConnection(String str, int i, int i2, boolean z, NetConfig netConfig) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setConnectTimeout(i2);
        if (netConfig != null && netConfig.getReadTimeOut() != 0) {
            httpURLConnection.setReadTimeout(netConfig.getReadTimeOut());
        }
        httpURLConnection.setUseCaches(false);
        if (i > 0) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", z ? "application/multipart-formdata" : ContentType.FORM);
        } else {
            httpURLConnection.setRequestMethod("GET");
        }
        httpURLConnection.setRequestProperty("ns_v", BuildConfig.VERSION);
        httpURLConnection.setRequestProperty("ns_pf", "adr");
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
            MTPApi.LOGGER.debug(TAG, "isNetworkAvailable", e);
            return false;
        }
    }
}
