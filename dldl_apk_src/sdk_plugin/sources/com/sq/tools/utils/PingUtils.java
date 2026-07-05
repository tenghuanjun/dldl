package com.sq.tools.utils;

import android.text.TextUtils;
import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class PingUtils {
    private static final int DEFAULT_COUNT = 3;
    private static final int TIME_OUT = 5;
    private static final String ipRegex = "((?:(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d)))\\.){3}(?:25[0-5]|2[0-4]\\d|((1\\d{2})|([1-9]?\\d))))";

    private static String createSimplePingCommand(int i, int i2, String str) {
        return "/system/bin/ping -c " + i + " -w " + i2 + " " + str;
    }

    private static String ping(String str) {
        Process processExec = null;
        try {
            try {
                processExec = Runtime.getRuntime().exec(str);
                InputStream inputStream = processExec.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    }
                    sb.append(line);
                    sb.append(ShellAdbUtils.COMMAND_LINE_END);
                }
                bufferedReader.close();
                inputStream.close();
                String string = sb.toString();
                if (processExec != null) {
                    processExec.destroy();
                }
                return string;
            } catch (IOException e) {
                e.printStackTrace();
                if (processExec == null) {
                    return "";
                }
                processExec.destroy();
                return "";
            }
        } catch (Throwable th) {
            if (processExec != null) {
                processExec.destroy();
            }
            throw th;
        }
    }

    public static PingResult getPingResult(String str) {
        return getPingResult(str, 3, 5);
    }

    public static PingResult getPingResult(String str, int i, int i2) {
        String domain = getDomain(str);
        if (domain == null) {
            return null;
        }
        PingResult pingResult = new PingResult();
        pingResult.setDomain(domain);
        String strPing = ping(createSimplePingCommand(i, i2, domain));
        if (TextUtils.isEmpty(strPing)) {
            return pingResult;
        }
        pingResult.setPingDesc(strPing);
        try {
            if (!strPing.contains("min/avg/max/mdev")) {
                return pingResult;
            }
            String[] strArrSplit = strPing.substring(strPing.indexOf("min/avg/max/mdev") + 19).split("/");
            float f = Float.parseFloat(strArrSplit[0]);
            float f2 = Float.parseFloat(strArrSplit[1]);
            float f3 = Float.parseFloat(strArrSplit[2]);
            float f4 = Float.parseFloat(strArrSplit[3].substring(0, r4.length() - 4));
            pingResult.setMin(f);
            pingResult.setAvg(f2);
            pingResult.setMax(f3);
            pingResult.setMdev(f4);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pingResult;
    }

    private static String getDomain(String str) {
        String host = null;
        try {
            host = URI.create(str).getHost();
            if (host == null) {
                if (isMatch(ipRegex, str)) {
                    return str;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return host;
    }

    private static boolean isMatch(String str, String str2) {
        return Pattern.matches(str, str2);
    }
}
