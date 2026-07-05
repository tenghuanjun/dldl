package com.nirvana.tools.logger.uaid;

import android.net.Network;
import android.os.Build;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import javax.net.ssl.HttpsURLConnection;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class HttpUtils {
    public static String get(String str, int i) {
        return get(str, i, null);
    }

    public static String get(String str, int i, Network network) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (network == null || Build.VERSION.SDK_INT < 21) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) network.openConnection(url);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            if (httpURLConnection == null) {
                throw new IOException("Connection is not established.");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return line;
        } catch (SocketException | UnknownHostException | Exception unused) {
            return null;
        }
    }

    public static String post(String str, String str2, int i) {
        return post(str, str2, i, null);
    }

    public static String post(String str, String str2, int i, Network network) {
        try {
            URL url = new URL(str);
            HttpURLConnection httpURLConnection = (network == null || Build.VERSION.SDK_INT < 21) ? (HttpsURLConnection) url.openConnection() : (HttpURLConnection) network.openConnection(url);
            httpURLConnection.setUseCaches(false);
            httpURLConnection.setConnectTimeout(i);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestProperty("Content-Type", FastJsonJsonView.DEFAULT_CONTENT_TYPE);
            httpURLConnection.setRequestMethod("POST");
            new StringBuffer();
            if (str2 != null && str2.length() > 0) {
                DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                dataOutputStream.writeBytes(str2);
                dataOutputStream.flush();
                dataOutputStream.close();
            }
            if (httpURLConnection == null) {
                throw new IOException("Connection is not established.");
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String line = bufferedReader.readLine();
            bufferedReader.close();
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            return line;
        } catch (SocketException | UnknownHostException | Exception unused) {
            return null;
        }
    }
}
