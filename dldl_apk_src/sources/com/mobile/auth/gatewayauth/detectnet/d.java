package com.mobile.auth.gatewayauth.detectnet;

import android.text.TextUtils;
import com.mobile.auth.gatewayauth.ExceptionProcessor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import net.security.device.api.SecurityCode;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class d {
    public static c a(c cVar, String str) {
        try {
            String str2 = "unknown host";
            if (str.contains("0% packet loss")) {
                int iIndexOf = str.indexOf("/mdev = ");
                int iIndexOf2 = str.indexOf(" ms\n", iIndexOf);
                if (iIndexOf != -1 && iIndexOf2 != -1) {
                    String[] strArrSplit = str.substring(iIndexOf + 8, iIndexOf2).split("/");
                    cVar.a(true);
                    cVar.a((int) Float.parseFloat(strArrSplit[1]));
                    return cVar;
                }
                str2 = "Error: " + str;
            } else if (str.contains("100% packet loss")) {
                str2 = "100% packet loss";
            } else if (str.contains("% packet loss")) {
                str2 = "partial packet loss";
            } else if (!str.contains("unknown host")) {
                str2 = "unknown error in getPingStats";
            }
            cVar.b(str2);
            cVar.a(SecurityCode.SC_NETWORK_ERROR);
            return cVar;
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }

    public static c a(String str, int i) {
        Process processExec;
        c cVarA;
        String str2;
        try {
            if (TextUtils.isEmpty(str)) {
                c cVar = new c();
                cVar.a(str).b("网络地址为空").a(10001).a(false).a(-1L);
                return cVar;
            }
            try {
                InetAddress byName = InetAddress.getByName(str);
                if (byName == null) {
                    new c().a(str).b("地址初始化失败").a(10001).a(false).a(-1L);
                }
                StringBuilder sb = new StringBuilder();
                Runtime runtime = Runtime.getRuntime();
                String hostAddress = byName.getHostAddress();
                String str3 = "ping";
                if (hostAddress == null) {
                    hostAddress = byName.getHostName();
                } else if (b.c(hostAddress)) {
                    str3 = "ping6";
                }
                try {
                    processExec = runtime.exec(str3 + " -c 1 -W " + i + " " + hostAddress);
                } catch (IOException | InterruptedException e) {
                    e = e;
                    processExec = null;
                }
                try {
                    processExec.waitFor();
                } catch (IOException e2) {
                    e = e2;
                    new c().a(str).b(e.getMessage()).a(SecurityCode.SC_UNKNOWN_ERROR).a(false).a(-1L);
                } catch (InterruptedException e3) {
                    e = e3;
                    new c().a(str).b(e.getMessage()).a(SecurityCode.SC_UNKNOWN_ERROR).a(false).a(-1L);
                }
                int iExitValue = processExec.exitValue();
                c cVar2 = new c();
                cVar2.a(str);
                if (iExitValue != 0) {
                    if (iExitValue != 1) {
                        cVarA = cVar2.a(false).a(SecurityCode.SC_UNKNOWN_ERROR);
                        str2 = "failed, exit = 2";
                    } else {
                        cVarA = cVar2.a(false).a(SecurityCode.SC_UNKNOWN_ERROR);
                        str2 = "failed, exit = 1";
                    }
                    cVarA.b(str2);
                    processExec.destroy();
                    return cVar2;
                }
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(processExec.getInputStream()));
                while (true) {
                    try {
                        String line = bufferedReader.readLine();
                        if (line == null) {
                            break;
                        }
                        sb.append(line);
                        sb.append("\n");
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                return a(cVar2, sb.toString());
            } catch (UnknownHostException e5) {
                c cVar3 = new c();
                cVar3.a(str).b(e5.getMessage()).a(10002).a(false).a(-1L);
                return cVar3;
            }
        } catch (Throwable th) {
            try {
                ExceptionProcessor.processException(th);
                return null;
            } catch (Throwable th2) {
                ExceptionProcessor.processException(th2);
                return null;
            }
        }
    }
}
