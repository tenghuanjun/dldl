package com.sq.diagnostic.assistant.mtr;

import android.text.TextUtils;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sq.diagnostic.assistant.log.SQLogUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class MtrManager {
    private static final String TAG = "MtrManager";

    public interface OnMtrCallback {
        void onComplete();

        void onResult(MtrResult mtrResult);
    }

    public static boolean mtr(InetAddress inetAddress, int i, int i2, OnMtrCallback onMtrCallback) {
        return mtr(inetAddress, null, inetAddress, i, i2, onMtrCallback);
    }

    public static boolean mtr(InetAddress inetAddress, InetAddress inetAddress2, InetAddress inetAddress3, int i, int i2, OnMtrCallback onMtrCallback) {
        String str;
        Process processExec;
        BufferedReader bufferedReader;
        StringBuilder sb;
        try {
            double dDoubleValue = new BigDecimal(String.valueOf(0.2f)).doubleValue();
            StringBuilder sb2 = new StringBuilder();
            sb2.append(inetAddress3 instanceof Inet6Address ? "ping6" : "ping");
            sb2.append(" -c %d -i %f -t %d ");
            String str2 = String.format(sb2.toString(), 3, Double.valueOf(dDoubleValue), Integer.valueOf(i));
            String inetAddressIp = getInetAddressIp(inetAddress3);
            System.nanoTime();
            str = str2 + inetAddressIp;
            processExec = Runtime.getRuntime().exec(str);
            SQLogUtils.i(TAG, "MTR 命令行：" + str);
            bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
            sb = new StringBuilder();
        } catch (Exception e) {
            e.printStackTrace();
            MtrResult mtrResult = new MtrResult();
            mtrResult.code = 1024;
            mtrResult.content = e.getMessage();
            if (onMtrCallback == null) {
                return true;
            }
            onMtrCallback.onResult(mtrResult);
            if (i == i2) {
                onMtrCallback.onComplete();
                return true;
            }
        }
        while (true) {
            String line = bufferedReader.readLine();
            if (line == null) {
                break;
            }
            sb.append(line);
            sb.append(ShellAdbUtils.COMMAND_LINE_END);
            return false;
        }
        SQLogUtils.i(TAG, "MTR 命令行结果：" + ((Object) sb));
        MtrResult mtrResult2 = new MtrResult();
        mtrResult2.cmd = str;
        mtrResult2.code = processExec.waitFor();
        mtrResult2.content = sb.toString();
        if (sb.toString().contains("From")) {
            mtrResult2.fromAddress = extractFromAddress(sb.toString());
        }
        if (onMtrCallback != null) {
            onMtrCallback.onResult(mtrResult2);
        }
        if (mtrResult2.content != null) {
            if (mtrResult2.content.contains("bytes from " + getInetAddressIp(inetAddress))) {
                if (onMtrCallback != null) {
                    onMtrCallback.onComplete();
                }
                return true;
            }
        }
        if (!TextUtils.isEmpty(mtrResult2.fromAddress) && !TextUtils.equals(mtrResult2.fromAddress, getInetAddressIp(inetAddress2))) {
            try {
                mtr(inetAddress, inetAddress3, InetAddress.getByName(mtrResult2.fromAddress), i, i2, onMtrCallback);
            } catch (UnknownHostException e2) {
                e2.printStackTrace();
            }
        }
        if (onMtrCallback == null) {
            return true;
        }
        if (i == i2) {
            onMtrCallback.onComplete();
            return true;
        }
        return false;
    }

    public static String extractFromAddress(String str) {
        if (str.contains("From")) {
            int iIndexOf = str.indexOf("From");
            String strSubstring = str.substring(iIndexOf + 5);
            if (strSubstring.contains("(")) {
                return strSubstring.substring(strSubstring.indexOf("(") + 1, strSubstring.indexOf(")"));
            }
            String strSubstring2 = strSubstring.substring(0, strSubstring.indexOf(ShellAdbUtils.COMMAND_LINE_END));
            if (strSubstring2.contains(" ")) {
                iIndexOf = strSubstring2.indexOf(" ");
            }
            String strSubstring3 = strSubstring2.substring(0, iIndexOf);
            return strSubstring3.substring(strSubstring3.length() + (-1)).equals(":") ? strSubstring3.substring(0, strSubstring3.length() - 1) : strSubstring3;
        }
        return str.substring(str.indexOf("(") + 1, str.indexOf(")"));
    }

    public static String getInetAddressIp(InetAddress inetAddress) {
        return inetAddress == null ? "" : inetAddress.toString().split("/")[1];
    }
}
