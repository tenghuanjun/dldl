package com.mobile.auth.c;

import android.content.Context;
import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import com.tencent.connect.common.Constants;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes3.dex */
public class a {
    public static String a(Context context, String str, String str2) {
        InputStream inputStream;
        BufferedReader bufferedReader;
        HttpURLConnection httpURLConnection;
        InputStream inputStream2;
        StringBuilder sb;
        String string = "";
        try {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                httpURLConnection.setRequestProperty("accept", "*/*");
                httpURLConnection.setRequestMethod(Constants.HTTP_POST);
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setUseCaches(false);
                httpURLConnection.addRequestProperty("Accept-Charset", "UTF-8");
                if (TextUtils.isEmpty(str2)) {
                    httpURLConnection.connect();
                } else {
                    DataOutputStream dataOutputStream = new DataOutputStream(new BufferedOutputStream(httpURLConnection.getOutputStream()));
                    dataOutputStream.write(str2.getBytes("UTF-8"));
                    dataOutputStream.flush();
                    dataOutputStream.close();
                }
            } catch (Throwable th) {
                th = th;
                inputStream = null;
                bufferedReader = null;
            }
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream2 = httpURLConnection.getInputStream();
                try {
                    sb = new StringBuilder();
                    bufferedReader = new BufferedReader(new InputStreamReader(inputStream2));
                } catch (Throwable th2) {
                    th = th2;
                    bufferedReader = null;
                }
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append(StringUtils.LF);
                    } catch (Throwable th3) {
                        th = th3;
                        Throwable th4 = th;
                        inputStream = inputStream2;
                        th = th4;
                        try {
                            th.printStackTrace();
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e) {
                                    e = e;
                                    e.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                            return string;
                        } finally {
                            if (bufferedReader != null) {
                                try {
                                    bufferedReader.close();
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                }
                            }
                            if (inputStream != null) {
                                inputStream.close();
                            }
                        }
                    }
                    e.printStackTrace();
                    return string;
                }
                string = sb.toString();
            } else {
                inputStream2 = null;
                bufferedReader = null;
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (Exception e3) {
                    e = e3;
                    e.printStackTrace();
                }
            }
            return string;
        } catch (Throwable th5) {
            try {
                ExceptionProcessor.processException(th5);
                return null;
            } catch (Throwable th6) {
                ExceptionProcessor.processException(th6);
                return null;
            }
        }
    }
}
