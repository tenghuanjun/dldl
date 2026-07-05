package com.nirvana.tools.crash;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.bytedance.sdk.openadsdk.downloadnew.core.TTDownloadField;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import kotlin.UByte;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
class CrashUploadUtils {
    CrashUploadUtils() {
    }

    public static String apkInDebugRelease(Context context) {
        try {
            return (context.getApplicationInfo().flags & 2) != 0 ? "debug" : "release";
        } catch (Exception unused) {
            return "release";
        }
    }

    private static byte[] getBytesByInputStream(InputStream inputStream) {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
        byte[] bArr = new byte[8192];
        while (true) {
            try {
                try {
                    try {
                        int i = bufferedInputStream.read(bArr);
                        if (i <= 0) {
                            break;
                        }
                        bufferedOutputStream.write(bArr, 0, i);
                    } catch (IOException e) {
                        e.printStackTrace();
                        try {
                            bufferedOutputStream.close();
                        } catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        try {
                            bufferedInputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                        return null;
                    }
                } finally {
                }
            } catch (IOException e4) {
            }
        }
        bufferedOutputStream.flush();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        try {
            bufferedOutputStream.close();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
        try {
            bufferedInputStream.close();
            return byteArray;
        } catch (IOException e6) {
            e6.printStackTrace();
            return byteArray;
        }
    }

    public static String getCurProcessName(Context context) {
        int iMyPid = Process.myPid();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getSystemService(TTDownloadField.TT_ACTIVITY)).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == iMyPid) {
                return runningAppProcessInfo.processName;
            }
        }
        return "";
    }

    public static String getFieldFromCpuinfo(String str) throws IOException {
        Matcher matcher;
        BufferedReader bufferedReader = new BufferedReader(new FileReader("/proc/cpuinfo"));
        Pattern patternCompile = Pattern.compile(str + "\\s*:\\s*(.*)");
        do {
            try {
                String line = bufferedReader.readLine();
                if (line != null) {
                    matcher = patternCompile.matcher(line);
                }
            } catch (Exception unused) {
            } catch (Throwable th) {
                bufferedReader.close();
                throw th;
            }
            bufferedReader.close();
            return "";
        } while (!matcher.matches());
        String strGroup = matcher.group(1);
        bufferedReader.close();
        return strGroup;
    }

    public static String getMeminfo() {
        try {
            StringBuilder sb = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/meminfo")));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    bufferedReader.close();
                    return sb.toString();
                }
                sb.append(line);
                sb.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String getStringByBytes(byte[] bArr, String str) {
        try {
            return new String(bArr, str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "-1";
        }
    }

    public static boolean isGzip(byte[] bArr) {
        return ((bArr[1] & UByte.MAX_VALUE) | (bArr[0] << 8)) == 8075;
    }

    public static boolean post(String str, Map<String, String> map, Map<String, File> map2) throws Exception {
        String string = UUID.randomUUID().toString();
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("connection", "keep-alive");
        httpURLConnection.setRequestProperty("Charsert", "UTF-8");
        httpURLConnection.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpURLConnection.setRequestProperty(HttpHeaders.CONTENT_ENCODING, "gzip");
        httpURLConnection.setRequestProperty("http.protocol.content-charset", "UTF-8");
        httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + string);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            sb.append("--");
            sb.append(string);
            sb.append("\r\n");
            sb.append("Content-Disposition: form-data; name=\"" + entry.getKey() + "\"\r\n");
            StringBuilder sb2 = new StringBuilder("Content-Type: text/plain; charset=");
            sb2.append("UTF-8");
            sb2.append("\r\n");
            sb.append(sb2.toString());
            sb.append("\r\n");
            sb.append(entry.getValue());
            sb.append("\r\n");
        }
        DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
        dataOutputStream.write(sb.toString().getBytes());
        if (map2 != null) {
            for (Map.Entry<String, File> entry2 : map2.entrySet()) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append("--");
                sb3.append(string);
                sb3.append("\r\n");
                sb3.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + entry2.getKey() + "\"\r\n");
                sb3.append("\r\n");
                dataOutputStream.write(sb3.toString().getBytes());
                FileInputStream fileInputStream = new FileInputStream(entry2.getValue());
                GZIPInputStream gZIPInputStream = new GZIPInputStream(new BufferedInputStream(fileInputStream));
                byte[] bArr = new byte[1024];
                while (true) {
                    int i = gZIPInputStream.read(bArr);
                    if (i != -1) {
                        dataOutputStream.write(bArr, 0, i);
                    }
                }
                fileInputStream.close();
                dataOutputStream.write("\r\n".getBytes());
            }
        }
        dataOutputStream.write(("--" + string + "--\r\n").getBytes());
        dataOutputStream.flush();
        dataOutputStream.close();
        httpURLConnection.getResponseCode();
        byte[] bytesByInputStream = getBytesByInputStream(httpURLConnection.getInputStream());
        boolean zContains = (isGzip(bytesByInputStream) ? uncompressToString(bytesByInputStream, "UTF-8") : getStringByBytes(bytesByInputStream, "UTF-8")).contains("200");
        httpURLConnection.disconnect();
        return zContains;
    }

    public static String uncompressToString(byte[] bArr, String str) {
        String string = null;
        if (bArr != null && bArr.length != 0) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                try {
                    GZIPInputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
                    byte[] bArr2 = new byte[256];
                    while (true) {
                        int i = gZIPInputStream.read(bArr2);
                        if (i < 0) {
                            break;
                        }
                        byteArrayOutputStream.write(bArr2, 0, i);
                    }
                    string = byteArrayOutputStream.toString(str);
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException unused) {
                byteArrayInputStream.close();
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                try {
                    byteArrayInputStream.close();
                    byteArrayOutputStream.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
                throw th;
            }
        }
        return string;
    }
}
