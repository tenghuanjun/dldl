package com.sq.diagnostic.assistant.http;

import android.accounts.NetworkErrorException;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.sq.diagnostic.assistant.other.ThreadPoolManager;
import com.sq.tools.network.ContentType;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HttpUtils {

    public interface HttpCallback {
        void onHttpFail(Exception exc);

        void onHttpSuccess(JSONObject jSONObject);
    }

    public static void request(final HttpMethod httpMethod, final String str, final Map<String, Object> map, final HttpCallback httpCallback) {
        ThreadPoolManager.getInstance().execute(new Runnable() { // from class: com.sq.diagnostic.assistant.http.HttpUtils.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    String str2 = str;
                    if (httpMethod == HttpMethod.GET) {
                        Set<String> setKeySet = map.keySet();
                        StringBuilder sb = new StringBuilder();
                        int i = 0;
                        for (String str3 : setKeySet) {
                            String strValueOf = String.valueOf(map.get(str3));
                            if (i == 0) {
                                sb.append("?");
                            }
                            sb.append(str3);
                            sb.append(SimpleComparison.EQUAL_TO_OPERATION);
                            sb.append(strValueOf);
                            if (i < setKeySet.size() - 1) {
                                sb.append("&");
                            }
                            i++;
                        }
                        str2 = str2 + ((Object) sb);
                    }
                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                    httpURLConnection.setConnectTimeout(3000);
                    httpURLConnection.setReadTimeout(60000);
                    httpURLConnection.setDoInput(true);
                    httpURLConnection.setRequestProperty("Cache-Control", "no-cache");
                    httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
                    httpURLConnection.setRequestProperty("Charset", "UTF-8");
                    httpURLConnection.setRequestProperty("Accept-Charset", "utf-8");
                    int i2 = AnonymousClass2.$SwitchMap$com$sq$diagnostic$assistant$http$HttpMethod[httpMethod.ordinal()];
                    if (i2 == 1) {
                        httpURLConnection.setRequestMethod("POST");
                        httpURLConnection.setUseCaches(false);
                        httpURLConnection.setDoOutput(true);
                        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data; charset=UTF-8; boundary=#################################################");
                        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                        if (map != null && map.size() != 0) {
                            HashMap map2 = null;
                            StringBuilder sb2 = new StringBuilder();
                            Iterator it = map.entrySet().iterator();
                            while (it.hasNext()) {
                                String str4 = (String) ((Map.Entry) it.next()).getKey();
                                Object obj = map.get(str4);
                                if (obj != null) {
                                    if (obj instanceof File) {
                                        if (map2 == null) {
                                            map2 = new HashMap(1);
                                        }
                                        map2.put(str4, (File) obj);
                                    } else {
                                        sb2.append("--");
                                        sb2.append("#################################################");
                                        sb2.append("\r\n");
                                        sb2.append("Content-Disposition: form-data; charset=UTF-8; name=\"");
                                        sb2.append(str4);
                                        sb2.append("\"");
                                        sb2.append("\r\n");
                                        sb2.append("\r\n");
                                        sb2.append(obj);
                                        sb2.append("\r\n");
                                    }
                                }
                            }
                            dataOutputStream.write(sb2.toString().getBytes());
                            if (map2 != null && map2.size() > 0) {
                                for (String str5 : map2.keySet()) {
                                    File file = (File) map2.get(str5);
                                    if (file != null && file.exists() && !file.isDirectory()) {
                                        byte[] bArrInputStreamToByteArray = HttpUtils.inputStreamToByteArray(new FileInputStream(file));
                                        String name = file.getName();
                                        dataOutputStream.write("--#################################################\r\n".getBytes());
                                        dataOutputStream.writeBytes("Content-Disposition: form-data; name=\"" + URLEncoder.encode(str5, "utf-8") + "\"; filename=\"" + URLEncoder.encode(name, "utf-8") + "\"\r\n");
                                        dataOutputStream.writeBytes("\r\n");
                                        dataOutputStream.write(bArrInputStreamToByteArray);
                                        dataOutputStream.writeBytes("\r\n");
                                    }
                                }
                            }
                        }
                        dataOutputStream.writeBytes("--#################################################--\r\n");
                        dataOutputStream.flush();
                        dataOutputStream.close();
                    } else if (i2 == 2) {
                        httpURLConnection.setRequestMethod("GET");
                        httpURLConnection.setDoOutput(false);
                        httpURLConnection.setRequestProperty("Content-Type", ContentType.FORM);
                    }
                    if (httpURLConnection.getResponseCode() != 200) {
                        throw new NetworkErrorException("请求异常：" + str + "   错误代码为：" + httpURLConnection.getResponseCode() + "   错误消息为：" + httpURLConnection.getResponseMessage());
                    }
                    InputStream inputStream = httpURLConnection.getInputStream();
                    JSONObject jSONObject = new JSONObject(HttpUtils.inputStreamToString(inputStream));
                    inputStream.close();
                    if (httpCallback != null) {
                        httpCallback.onHttpSuccess(jSONObject);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    HttpCallback httpCallback2 = httpCallback;
                    if (httpCallback2 != null) {
                        httpCallback2.onHttpFail(e);
                    }
                }
            }
        });
    }

    /* JADX INFO: renamed from: com.sq.diagnostic.assistant.http.HttpUtils$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$sq$diagnostic$assistant$http$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$sq$diagnostic$assistant$http$HttpMethod = iArr;
            try {
                iArr[HttpMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sq$diagnostic$assistant$http$HttpMethod[HttpMethod.GET.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();
        try {
            byte[] bArr = new byte[1024];
            while (true) {
                int i = inputStream.read(bArr);
                if (i == -1) {
                    break;
                }
                sb.append(new String(bArr, 0, i, "UTF-8"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] inputStreamToByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[2048];
        while (true) {
            int i = inputStream.read(bArr, 0, 2048);
            if (i > 0) {
                byteArrayOutputStream.write(bArr, 0, i);
            } else {
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
