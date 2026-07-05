package com.alicom.tools.networking;

import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alipay.sdk.cons.c;
import com.alipay.sdk.util.i;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.GZIPInputStream;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class AlicomHttpUtils {
    private static ConcurrentHashMap<String, HostnameVerifier> mHostnameVerifiers = new ConcurrentHashMap<>();

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0, types: [java.net.URL] */
    /* JADX WARN: Type inference failed for: r0v1 */
    /* JADX WARN: Type inference failed for: r0v10 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r0v17 */
    /* JADX WARN: Type inference failed for: r0v4, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r0v7 */
    /* JADX WARN: Type inference failed for: r7v0, types: [com.alicom.tools.networking.Request] */
    /* JADX WARN: Type inference failed for: r7v11, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v14 */
    /* JADX WARN: Type inference failed for: r7v16 */
    /* JADX WARN: Type inference failed for: r7v18 */
    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v24, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v4 */
    /* JADX WARN: Type inference failed for: r7v6 */
    /* JADX WARN: Type inference failed for: r7v7, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r7v9, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r8v0, types: [int] */
    /* JADX WARN: Type inference failed for: r8v1 */
    /* JADX WARN: Type inference failed for: r8v15 */
    /* JADX WARN: Type inference failed for: r8v17 */
    /* JADX WARN: Type inference failed for: r8v19 */
    /* JADX WARN: Type inference failed for: r8v2 */
    /* JADX WARN: Type inference failed for: r8v20, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v3 */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v5, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r8v7 */
    /* JADX WARN: Type inference failed for: r8v8 */
    /* JADX WARN: Type inference failed for: r8v9 */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v11 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v19, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r9v2 */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r9v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r9v7 */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    public static String callApi(Request request, int i, int i2, int i3) throws Throwable {
        HttpURLConnection httpURLConnection;
        Throwable th;
        BufferedReader bufferedReader;
        IOException e;
        SocketTimeoutException e2;
        ?? url = new URL(request.getBaseUrl());
        String strBuildTopRequestParamas = i3 != 0 ? request.buildTopRequestParamas() : request.buildPopRequestParamas();
        byte[] bytes = new byte[0];
        if (strBuildTopRequestParamas != null) {
            bytes = strBuildTopRequestParamas.getBytes("utf-8");
        }
        try {
            try {
                httpURLConnection = (HttpURLConnection) url.openConnection();
                try {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setUseCaches(false);
                    httpURLConnection.setRequestMethod(request.getRequestMethod());
                    httpURLConnection.setConnectTimeout(i);
                    httpURLConnection.setReadTimeout(i2);
                    httpURLConnection.setRequestProperty(HttpHeaders.HOST, url.getHost());
                    httpURLConnection.setRequestProperty("Accept", "text/xml,text/javascript");
                    httpURLConnection.setRequestProperty("User-Agent", "top-sdk-java");
                    httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
                    httpURLConnection.connect();
                    request = httpURLConnection.getOutputStream();
                    try {
                        request.write(bytes);
                        i = httpURLConnection.getInputStream();
                        try {
                            i2 = new InputStreamReader((InputStream) i, "utf-8");
                            try {
                                bufferedReader = new BufferedReader(i2);
                                try {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        stringBuffer.append(line);
                                    }
                                    String str = new String(stringBuffer);
                                    if (i != 0) {
                                        try {
                                            i.close();
                                        } catch (Throwable th2) {
                                            th2.printStackTrace();
                                        }
                                    }
                                    i2.close();
                                    bufferedReader.close();
                                    if (request != 0) {
                                        request.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return str;
                                } catch (SocketTimeoutException e3) {
                                    e2 = e3;
                                    String stackTraceString = Log.getStackTraceString(e2);
                                    if (i != 0) {
                                        try {
                                            i.close();
                                        } catch (Throwable th3) {
                                            th3.printStackTrace();
                                            return stackTraceString;
                                        }
                                    }
                                    if (i2 != 0) {
                                        i2.close();
                                    }
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                    if (request != 0) {
                                        request.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return stackTraceString;
                                } catch (IOException e4) {
                                    e = e4;
                                    String stackTraceString2 = Log.getStackTraceString(e);
                                    if (i != 0) {
                                        try {
                                            i.close();
                                        } catch (Throwable th4) {
                                            th4.printStackTrace();
                                            return stackTraceString2;
                                        }
                                    }
                                    if (i2 != 0) {
                                        i2.close();
                                    }
                                    if (bufferedReader != null) {
                                        bufferedReader.close();
                                    }
                                    if (request != 0) {
                                        request.close();
                                    }
                                    if (httpURLConnection != null) {
                                        httpURLConnection.disconnect();
                                    }
                                    return stackTraceString2;
                                }
                            } catch (SocketTimeoutException e5) {
                                bufferedReader = null;
                                e2 = e5;
                            } catch (IOException e6) {
                                bufferedReader = null;
                                e = e6;
                            } catch (Throwable th5) {
                                url = 0;
                                th = th5;
                                if (i != 0) {
                                    try {
                                        i.close();
                                    } catch (Throwable th6) {
                                        th6.printStackTrace();
                                        throw th;
                                    }
                                }
                                if (i2 != 0) {
                                    i2.close();
                                }
                                if (url != 0) {
                                    url.close();
                                }
                                if (request != 0) {
                                    request.close();
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                }
                                throw th;
                            }
                        } catch (SocketTimeoutException e7) {
                            bufferedReader = null;
                            e2 = e7;
                            i2 = 0;
                        } catch (IOException e8) {
                            bufferedReader = null;
                            e = e8;
                            i2 = 0;
                        } catch (Throwable th7) {
                            url = 0;
                            th = th7;
                            i2 = 0;
                        }
                    } catch (SocketTimeoutException e9) {
                        i2 = 0;
                        bufferedReader = null;
                        e2 = e9;
                        i = 0;
                    } catch (IOException e10) {
                        i2 = 0;
                        bufferedReader = null;
                        e = e10;
                        i = 0;
                    } catch (Throwable th8) {
                        i2 = 0;
                        url = 0;
                        th = th8;
                        i = 0;
                    }
                } catch (SocketTimeoutException e11) {
                    i = 0;
                    i2 = 0;
                    bufferedReader = null;
                    e2 = e11;
                    request = 0;
                } catch (IOException e12) {
                    i = 0;
                    i2 = 0;
                    bufferedReader = null;
                    e = e12;
                    request = 0;
                } catch (Throwable th9) {
                    i = 0;
                    i2 = 0;
                    url = 0;
                    th = th9;
                    request = 0;
                }
            } catch (Throwable th10) {
                th = th10;
            }
        } catch (SocketTimeoutException e13) {
            i = 0;
            i2 = 0;
            bufferedReader = null;
            httpURLConnection = null;
            e2 = e13;
            request = 0;
        } catch (IOException e14) {
            i = 0;
            i2 = 0;
            bufferedReader = null;
            httpURLConnection = null;
            e = e14;
            request = 0;
        } catch (Throwable th11) {
            i = 0;
            i2 = 0;
            url = 0;
            httpURLConnection = null;
            th = th11;
            request = 0;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v25 */
    /* JADX WARN: Type inference failed for: r10v26 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v14, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v32, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v35 */
    /* JADX WARN: Type inference failed for: r11v36 */
    /* JADX WARN: Type inference failed for: r11v37 */
    /* JADX WARN: Type inference failed for: r11v38 */
    /* JADX WARN: Type inference failed for: r11v39 */
    /* JADX WARN: Type inference failed for: r11v40 */
    /* JADX WARN: Type inference failed for: r11v41 */
    /* JADX WARN: Type inference failed for: r11v7, types: [javax.net.ssl.SSLContext] */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r5v12, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.security.SecureRandom] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v8, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v9, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v24 */
    /* JADX WARN: Type inference failed for: r9v32 */
    /* JADX WARN: Type inference failed for: r9v35 */
    /* JADX WARN: Type inference failed for: r9v38 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.OutputStream] */
    public static String callHttpsApi(Request request, int i, int i2, int i3) throws Throwable {
        ?? secureRandom;
        Tls12Factory tls12Factory;
        ?? bufferedReader;
        OutputStream outputStream;
        ?? r10;
        ?? r11;
        ?? r5;
        OutputStream outputStream2;
        ?? r102;
        ?? r112;
        ?? r52;
        String strBuildTopRequestParamas = i3 != 0 ? request.buildTopRequestParamas() : request.buildPopRequestParamas();
        byte[] bytes = new byte[0];
        if (strBuildTopRequestParamas != null) {
            bytes = strBuildTopRequestParamas.getBytes("utf-8");
        }
        int i4 = Build.VERSION.SDK_INT;
        InputStream inputStream = null;
        ?? r113 = i4;
        if (i4 < 21) {
            try {
                ?? sSLContext = SSLContext.getInstance("TLS");
                secureRandom = new SecureRandom();
                sSLContext.init((KeyManager[]) null, null, secureRandom);
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                tls12Factory = new Tls12Factory(socketFactory);
                bufferedReader = socketFactory;
            } catch (KeyManagementException e) {
                e.printStackTrace();
                r113 = e;
                tls12Factory = null;
                bufferedReader = r113;
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                r113 = e2;
                tls12Factory = null;
                bufferedReader = r113;
            }
        } else {
            tls12Factory = null;
            bufferedReader = r113;
        }
        try {
            try {
                URL url = new URL(request.getBaseUrl());
                secureRandom = (HttpsURLConnection) url.openConnection();
                try {
                    secureRandom.setDoOutput(true);
                    secureRandom.setDoInput(true);
                    secureRandom.setUseCaches(false);
                    secureRandom.setHostnameVerifier(getHostnameVerifierByUrl(request.getBaseUrl()));
                    secureRandom.setRequestMethod(request.getRequestMethod());
                    secureRandom.setConnectTimeout(i);
                    secureRandom.setReadTimeout(i2);
                    secureRandom.setRequestProperty(c.f, url.getHost());
                    secureRandom.setRequestProperty("Accept", "application/json");
                    secureRandom.setRequestProperty("x-acs-action", request.getAction());
                    secureRandom.setRequestProperty("x-sdk-invoke-type", "common");
                    secureRandom.setRequestProperty("x-sdk-client", "Java/2.0.0");
                    secureRandom.setRequestProperty("traceparent", UUID.randomUUID().toString());
                    secureRandom.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    secureRandom.setRequestProperty("User-Agent", "AlibabaCloud (Linux; amd64) Java/1.8.0_152-b187 Core/4.5.26 HTTPClient/ApacheHttpClient");
                    secureRandom.setRequestProperty("x-acs-version", "2017-05-25");
                    if (tls12Factory != null) {
                        secureRandom.setSSLSocketFactory(tls12Factory);
                    }
                    secureRandom.connect();
                    OutputStream outputStream3 = secureRandom.getOutputStream();
                    try {
                        outputStream3.write(bytes);
                        InputStream inputStream2 = secureRandom.getResponseCode() == 200 ? secureRandom.getInputStream() : secureRandom.getErrorStream();
                        try {
                            i2 = new InputStreamReader(inputStream2, "utf-8");
                            try {
                                bufferedReader = new BufferedReader(i2);
                                try {
                                    StringBuffer stringBuffer = new StringBuffer();
                                    while (true) {
                                        String line = bufferedReader.readLine();
                                        if (line == null) {
                                            break;
                                        }
                                        stringBuffer.append(line);
                                    }
                                    String str = new String(stringBuffer);
                                    if (inputStream2 != null) {
                                        try {
                                            inputStream2.close();
                                        } catch (Throwable th) {
                                            th.printStackTrace();
                                        }
                                    }
                                    i2.close();
                                    bufferedReader.close();
                                    if (outputStream3 != null) {
                                        outputStream3.close();
                                    }
                                    if (secureRandom != 0) {
                                        secureRandom.disconnect();
                                    }
                                    return str;
                                } catch (SocketTimeoutException e3) {
                                    inputStream = inputStream2;
                                    outputStream2 = outputStream3;
                                    e = e3;
                                    r52 = secureRandom;
                                    r102 = i2;
                                    r112 = bufferedReader;
                                    e.printStackTrace();
                                    String stackTraceString = Log.getStackTraceString(e);
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Throwable th2) {
                                            th2.printStackTrace();
                                            return stackTraceString;
                                        }
                                    }
                                    if (r102 != 0) {
                                        r102.close();
                                    }
                                    if (r112 != 0) {
                                        r112.close();
                                    }
                                    if (outputStream2 != null) {
                                        outputStream2.close();
                                    }
                                    if (r52 != 0) {
                                        r52.disconnect();
                                    }
                                    return stackTraceString;
                                } catch (IOException e4) {
                                    inputStream = inputStream2;
                                    outputStream = outputStream3;
                                    e = e4;
                                    r5 = secureRandom;
                                    r10 = i2;
                                    r11 = bufferedReader;
                                    e.printStackTrace();
                                    String stackTraceString2 = Log.getStackTraceString(e);
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Throwable th3) {
                                            th3.printStackTrace();
                                            return stackTraceString2;
                                        }
                                    }
                                    if (r10 != 0) {
                                        r10.close();
                                    }
                                    if (r11 != 0) {
                                        r11.close();
                                    }
                                    if (outputStream != null) {
                                        outputStream.close();
                                    }
                                    if (r5 != 0) {
                                        r5.disconnect();
                                    }
                                    return stackTraceString2;
                                } catch (Throwable th4) {
                                    inputStream = inputStream2;
                                    i = outputStream3;
                                    th = th4;
                                    if (inputStream != null) {
                                        try {
                                            inputStream.close();
                                        } catch (Throwable th5) {
                                            th5.printStackTrace();
                                            throw th;
                                        }
                                    }
                                    if (i2 != 0) {
                                        i2.close();
                                    }
                                    if (bufferedReader != 0) {
                                        bufferedReader.close();
                                    }
                                    if (i != 0) {
                                        i.close();
                                    }
                                    if (secureRandom != 0) {
                                        secureRandom.disconnect();
                                    }
                                    throw th;
                                }
                            } catch (SocketTimeoutException e5) {
                                InputStream inputStream3 = inputStream2;
                                outputStream2 = outputStream3;
                                e = e5;
                                r112 = 0;
                                inputStream = inputStream3;
                                r52 = secureRandom;
                                r102 = i2;
                            } catch (IOException e6) {
                                InputStream inputStream4 = inputStream2;
                                outputStream = outputStream3;
                                e = e6;
                                r11 = 0;
                                inputStream = inputStream4;
                                r5 = secureRandom;
                                r10 = i2;
                            } catch (Throwable th6) {
                                InputStream inputStream5 = inputStream2;
                                i = outputStream3;
                                th = th6;
                                bufferedReader = 0;
                                inputStream = inputStream5;
                            }
                        } catch (SocketTimeoutException e7) {
                            r112 = 0;
                            inputStream = inputStream2;
                            outputStream2 = outputStream3;
                            e = e7;
                            r102 = 0;
                            r52 = secureRandom;
                        } catch (IOException e8) {
                            r11 = 0;
                            inputStream = inputStream2;
                            outputStream = outputStream3;
                            e = e8;
                            r10 = 0;
                            r5 = secureRandom;
                        } catch (Throwable th7) {
                            bufferedReader = 0;
                            inputStream = inputStream2;
                            i = outputStream3;
                            th = th7;
                            i2 = 0;
                        }
                    } catch (SocketTimeoutException e9) {
                        r102 = 0;
                        r112 = 0;
                        outputStream2 = outputStream3;
                        e = e9;
                        r52 = secureRandom;
                    } catch (IOException e10) {
                        r10 = 0;
                        r11 = 0;
                        outputStream = outputStream3;
                        e = e10;
                        r5 = secureRandom;
                    } catch (Throwable th8) {
                        i2 = 0;
                        bufferedReader = 0;
                        i = outputStream3;
                        th = th8;
                    }
                } catch (SocketTimeoutException e11) {
                    e = e11;
                    outputStream2 = null;
                    r102 = 0;
                    r112 = 0;
                    r52 = secureRandom;
                } catch (IOException e12) {
                    e = e12;
                    outputStream = null;
                    r10 = 0;
                    r11 = 0;
                    r5 = secureRandom;
                } catch (Throwable th9) {
                    th = th9;
                    i = 0;
                    i2 = 0;
                    bufferedReader = 0;
                }
            } catch (Throwable th10) {
                th = th10;
            }
        } catch (SocketTimeoutException e13) {
            e = e13;
            outputStream2 = null;
            r102 = 0;
            r112 = 0;
            r52 = 0;
        } catch (IOException e14) {
            e = e14;
            outputStream = null;
            r10 = 0;
            r11 = 0;
            r5 = 0;
        } catch (Throwable th11) {
            th = th11;
            i = 0;
            i2 = 0;
            bufferedReader = 0;
            secureRandom = 0;
        }
    }

    public static String getHostnameFromUrl(String str) {
        try {
            return new URL(str).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static HostnameVerifier getHostnameVerifierByUrl(String str) {
        String hostnameFromUrl = getHostnameFromUrl(str);
        if (mHostnameVerifiers != null && !TextUtils.isEmpty(hostnameFromUrl) && mHostnameVerifiers.containsKey(hostnameFromUrl)) {
            return mHostnameVerifiers.get(hostnameFromUrl);
        }
        HostnameVerifier hostnameVerifier = new HostnameVerifier() { // from class: com.alicom.tools.networking.AlicomHttpUtils.1
            @Override // javax.net.ssl.HostnameVerifier
            public boolean verify(String str2, SSLSession sSLSession) {
                return HttpsURLConnection.getDefaultHostnameVerifier().verify(str2, sSLSession);
            }
        };
        if (mHostnameVerifiers == null) {
            mHostnameVerifiers = new ConcurrentHashMap<>();
        }
        mHostnameVerifiers.put(hostnameFromUrl, hostnameVerifier);
        return hostnameVerifier;
    }

    private static String getResponseAsString(HttpURLConnection httpURLConnection) throws IOException {
        String responseCharset = getResponseCharset(httpURLConnection.getContentType());
        if (httpURLConnection.getResponseCode() < 400) {
            return "gzip".equalsIgnoreCase(httpURLConnection.getContentEncoding()) ? getStreamAsString(new GZIPInputStream(httpURLConnection.getInputStream()), responseCharset) : getStreamAsString(httpURLConnection.getInputStream(), responseCharset);
        }
        throw new IOException(httpURLConnection.getResponseCode() + " " + httpURLConnection.getResponseMessage());
    }

    private static String getResponseCharset(String str) {
        if (isNotEmpty(str)) {
            String[] strArrSplit = str.split(i.b);
            int length = strArrSplit.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    break;
                }
                String strTrim = strArrSplit[i].trim();
                if (strTrim.startsWith("charset")) {
                    String[] strArrSplit2 = strTrim.split("=", 2);
                    if (strArrSplit2.length == 2 && isNotEmpty(strArrSplit2[1])) {
                        return strArrSplit2[1].trim();
                    }
                } else {
                    i++;
                }
            }
        }
        return "utf-8";
    }

    private static String getStreamAsString(InputStream inputStream, String str) throws IOException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, str);
            StringBuilder sb = new StringBuilder();
            char[] cArr = new char[1024];
            while (true) {
                int i = inputStreamReader.read(cArr);
                if (i <= 0) {
                    break;
                }
                sb.append(cArr, 0, i);
            }
            return sb.toString();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    private static boolean isNotEmpty(String str) {
        int length;
        if (str != null && (length = str.length()) != 0) {
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [int] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10 */
    /* JADX WARN: Type inference failed for: r10v11 */
    /* JADX WARN: Type inference failed for: r10v12 */
    /* JADX WARN: Type inference failed for: r10v15 */
    /* JADX WARN: Type inference failed for: r10v17 */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v2 */
    /* JADX WARN: Type inference failed for: r10v20, types: [java.io.InputStreamReader, java.io.Reader] */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /* JADX WARN: Type inference failed for: r10v24 */
    /* JADX WARN: Type inference failed for: r10v3 */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v5, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v6, types: [java.io.InputStreamReader] */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r10v9 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v14, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v15, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v17 */
    /* JADX WARN: Type inference failed for: r11v18 */
    /* JADX WARN: Type inference failed for: r11v19 */
    /* JADX WARN: Type inference failed for: r11v20 */
    /* JADX WARN: Type inference failed for: r11v21 */
    /* JADX WARN: Type inference failed for: r11v22 */
    /* JADX WARN: Type inference failed for: r11v23 */
    /* JADX WARN: Type inference failed for: r11v24 */
    /* JADX WARN: Type inference failed for: r11v25 */
    /* JADX WARN: Type inference failed for: r11v27 */
    /* JADX WARN: Type inference failed for: r11v29 */
    /* JADX WARN: Type inference failed for: r11v31 */
    /* JADX WARN: Type inference failed for: r11v32, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r11v33 */
    /* JADX WARN: Type inference failed for: r11v35 */
    /* JADX WARN: Type inference failed for: r11v36 */
    /* JADX WARN: Type inference failed for: r11v37 */
    /* JADX WARN: Type inference failed for: r11v38 */
    /* JADX WARN: Type inference failed for: r11v39 */
    /* JADX WARN: Type inference failed for: r11v40 */
    /* JADX WARN: Type inference failed for: r11v41 */
    /* JADX WARN: Type inference failed for: r11v7, types: [javax.net.ssl.SSLContext] */
    /* JADX WARN: Type inference failed for: r11v9 */
    /* JADX WARN: Type inference failed for: r5v12, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v13 */
    /* JADX WARN: Type inference failed for: r5v14 */
    /* JADX WARN: Type inference failed for: r5v15 */
    /* JADX WARN: Type inference failed for: r5v16 */
    /* JADX WARN: Type inference failed for: r5v17 */
    /* JADX WARN: Type inference failed for: r5v18 */
    /* JADX WARN: Type inference failed for: r5v19 */
    /* JADX WARN: Type inference failed for: r5v2, types: [java.security.SecureRandom] */
    /* JADX WARN: Type inference failed for: r5v20 */
    /* JADX WARN: Type inference failed for: r5v21 */
    /* JADX WARN: Type inference failed for: r5v22 */
    /* JADX WARN: Type inference failed for: r5v4 */
    /* JADX WARN: Type inference failed for: r5v5 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v8, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r5v9, types: [javax.net.ssl.HttpsURLConnection] */
    /* JADX WARN: Type inference failed for: r9v0, types: [int] */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v10 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v25 */
    /* JADX WARN: Type inference failed for: r9v28 */
    /* JADX WARN: Type inference failed for: r9v31 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.io.OutputStream] */
    public static String postHttps(Request request, int i, int i2, int i3) throws Throwable {
        ?? secureRandom;
        Tls12Factory tls12Factory;
        ?? bufferedReader;
        OutputStream outputStream;
        ?? r10;
        ?? r11;
        ?? r5;
        OutputStream outputStream2;
        ?? r102;
        ?? r112;
        ?? r52;
        String strBuildTopRequestParamas = i3 != 0 ? request.buildTopRequestParamas() : request.buildPopRequestParamas();
        byte[] bytes = new byte[0];
        if (strBuildTopRequestParamas != null) {
            bytes = strBuildTopRequestParamas.getBytes("utf-8");
        }
        int i4 = Build.VERSION.SDK_INT;
        InputStream inputStream = null;
        ?? r113 = i4;
        if (i4 < 21) {
            try {
                ?? sSLContext = SSLContext.getInstance("TLS");
                secureRandom = new SecureRandom();
                sSLContext.init((KeyManager[]) null, null, secureRandom);
                SSLSocketFactory socketFactory = sSLContext.getSocketFactory();
                tls12Factory = new Tls12Factory(socketFactory);
                bufferedReader = socketFactory;
            } catch (KeyManagementException e) {
                e.printStackTrace();
                r113 = e;
                tls12Factory = null;
                bufferedReader = r113;
            } catch (NoSuchAlgorithmException e2) {
                e2.printStackTrace();
                r113 = e2;
                tls12Factory = null;
                bufferedReader = r113;
            }
        } else {
            tls12Factory = null;
            bufferedReader = r113;
        }
        try {
            try {
                URL url = new URL(request.getBaseUrl());
                secureRandom = (HttpsURLConnection) url.openConnection();
                try {
                    secureRandom.setDoOutput(true);
                    secureRandom.setDoInput(true);
                    secureRandom.setUseCaches(false);
                    secureRandom.setRequestMethod(request.getRequestMethod());
                    secureRandom.setConnectTimeout(i);
                    secureRandom.setReadTimeout(i2);
                    secureRandom.setRequestProperty(HttpHeaders.HOST, url.getHost());
                    secureRandom.setRequestProperty("Accept", "text/text,text/javascript");
                    secureRandom.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    if (tls12Factory != null) {
                        secureRandom.setSSLSocketFactory(tls12Factory);
                    }
                    secureRandom.connect();
                    OutputStream outputStream3 = secureRandom.getOutputStream();
                    try {
                        outputStream3.write(bytes);
                        InputStream inputStream2 = secureRandom.getResponseCode() == 200 ? secureRandom.getInputStream() : secureRandom.getErrorStream();
                        try {
                            i2 = new InputStreamReader(inputStream2, "utf-8");
                            try {
                                bufferedReader = new BufferedReader(i2);
                            } catch (SocketTimeoutException e3) {
                                InputStream inputStream3 = inputStream2;
                                outputStream2 = outputStream3;
                                e = e3;
                                r112 = 0;
                                inputStream = inputStream3;
                                r52 = secureRandom;
                                r102 = i2;
                            } catch (IOException e4) {
                                InputStream inputStream4 = inputStream2;
                                outputStream = outputStream3;
                                e = e4;
                                r11 = 0;
                                inputStream = inputStream4;
                                r5 = secureRandom;
                                r10 = i2;
                            } catch (Throwable th) {
                                InputStream inputStream5 = inputStream2;
                                i = outputStream3;
                                th = th;
                                bufferedReader = 0;
                                inputStream = inputStream5;
                            }
                        } catch (SocketTimeoutException e5) {
                            r112 = 0;
                            inputStream = inputStream2;
                            outputStream2 = outputStream3;
                            e = e5;
                            r102 = 0;
                            r52 = secureRandom;
                        } catch (IOException e6) {
                            r11 = 0;
                            inputStream = inputStream2;
                            outputStream = outputStream3;
                            e = e6;
                            r10 = 0;
                            r5 = secureRandom;
                        } catch (Throwable th2) {
                            bufferedReader = 0;
                            inputStream = inputStream2;
                            i = outputStream3;
                            th = th2;
                            i2 = 0;
                        }
                        try {
                            StringBuffer stringBuffer = new StringBuffer();
                            while (true) {
                                String line = bufferedReader.readLine();
                                if (line == null) {
                                    break;
                                }
                                stringBuffer.append(line);
                            }
                            String str = new String(stringBuffer);
                            if (inputStream2 != null) {
                                try {
                                    inputStream2.close();
                                } catch (Throwable th3) {
                                    th3.printStackTrace();
                                }
                            }
                            i2.close();
                            bufferedReader.close();
                            if (outputStream3 != null) {
                                outputStream3.close();
                            }
                            if (secureRandom != 0) {
                                secureRandom.disconnect();
                            }
                            return str;
                        } catch (SocketTimeoutException e7) {
                            inputStream = inputStream2;
                            outputStream2 = outputStream3;
                            e = e7;
                            r52 = secureRandom;
                            r102 = i2;
                            r112 = bufferedReader;
                            e.printStackTrace();
                            String stackTraceString = Log.getStackTraceString(e);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th4) {
                                    th4.printStackTrace();
                                    return stackTraceString;
                                }
                            }
                            if (r102 != 0) {
                                r102.close();
                            }
                            if (r112 != 0) {
                                r112.close();
                            }
                            if (outputStream2 != null) {
                                outputStream2.close();
                            }
                            if (r52 != 0) {
                                r52.disconnect();
                            }
                            return stackTraceString;
                        } catch (IOException e8) {
                            inputStream = inputStream2;
                            outputStream = outputStream3;
                            e = e8;
                            r5 = secureRandom;
                            r10 = i2;
                            r11 = bufferedReader;
                            e.printStackTrace();
                            String stackTraceString2 = Log.getStackTraceString(e);
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                    return stackTraceString2;
                                }
                            }
                            if (r10 != 0) {
                                r10.close();
                            }
                            if (r11 != 0) {
                                r11.close();
                            }
                            if (outputStream != null) {
                                outputStream.close();
                            }
                            if (r5 != 0) {
                                r5.disconnect();
                            }
                            return stackTraceString2;
                        } catch (Throwable th6) {
                            inputStream = inputStream2;
                            i = outputStream3;
                            th = th6;
                            if (inputStream != null) {
                                try {
                                    inputStream.close();
                                } catch (Throwable th7) {
                                    th7.printStackTrace();
                                    throw th;
                                }
                            }
                            if (i2 != 0) {
                                i2.close();
                            }
                            if (bufferedReader != 0) {
                                bufferedReader.close();
                            }
                            if (i != 0) {
                                i.close();
                            }
                            if (secureRandom != 0) {
                                secureRandom.disconnect();
                            }
                            throw th;
                        }
                    } catch (SocketTimeoutException e9) {
                        r102 = 0;
                        r112 = 0;
                        outputStream2 = outputStream3;
                        e = e9;
                        r52 = secureRandom;
                    } catch (IOException e10) {
                        r10 = 0;
                        r11 = 0;
                        outputStream = outputStream3;
                        e = e10;
                        r5 = secureRandom;
                    } catch (Throwable th8) {
                        i2 = 0;
                        bufferedReader = 0;
                        i = outputStream3;
                        th = th8;
                    }
                } catch (SocketTimeoutException e11) {
                    e = e11;
                    outputStream2 = null;
                    r102 = 0;
                    r112 = 0;
                    r52 = secureRandom;
                } catch (IOException e12) {
                    e = e12;
                    outputStream = null;
                    r10 = 0;
                    r11 = 0;
                    r5 = secureRandom;
                } catch (Throwable th9) {
                    th = th9;
                    i = 0;
                    i2 = 0;
                    bufferedReader = 0;
                }
            } catch (Throwable th10) {
                th = th10;
            }
        } catch (SocketTimeoutException e13) {
            e = e13;
            outputStream2 = null;
            r102 = 0;
            r112 = 0;
            r52 = 0;
        } catch (IOException e14) {
            e = e14;
            outputStream = null;
            r10 = 0;
            r11 = 0;
            r5 = 0;
        } catch (Throwable th11) {
            th = th11;
            i = 0;
            i2 = 0;
            bufferedReader = 0;
            secureRandom = 0;
        }
    }
}
