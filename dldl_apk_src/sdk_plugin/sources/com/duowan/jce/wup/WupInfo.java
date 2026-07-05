package com.duowan.jce.wup;

import com.snail.antifake.deviceid.ShellAdbUtils;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class WupInfo {
    private static String clientBuilt;
    private static String clientInfo;
    private static String clientNumber;

    public static String getClientInfo() {
        return clientInfo;
    }

    public static String getClientBuilt() {
        return clientBuilt;
    }

    public static String getClientNumber() {
        return clientNumber;
    }

    public static String showString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Client version: " + getClientInfo() + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("Client built:   " + getClientBuilt() + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("Client number:  " + getClientNumber() + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("OS Name:        " + System.getProperty("os.name") + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("OS Version:     " + System.getProperty("os.version") + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("Architecture:   " + System.getProperty("os.arch") + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("JVM Version:    " + System.getProperty("java.runtime.version") + ShellAdbUtils.COMMAND_LINE_END);
        stringBuffer.append("JVM Vendor:     " + System.getProperty("java.vm.vendor") + ShellAdbUtils.COMMAND_LINE_END);
        return stringBuffer.toString();
    }

    public static void main(String[] strArr) {
        System.out.println(showString());
        System.out.println("Client version: " + getClientInfo());
        System.out.println("Client built:   " + getClientBuilt());
        System.out.println("Client number:  " + getClientNumber());
        System.out.println("OS Name:        " + System.getProperty("os.name"));
        System.out.println("OS Version:     " + System.getProperty("os.version"));
        System.out.println("Architecture:   " + System.getProperty("os.arch"));
        System.out.println("JVM Version:    " + System.getProperty("java.runtime.version"));
        System.out.println("JVM Vendor:     " + System.getProperty("java.vm.vendor"));
    }

    static {
        try {
            InputStream resourceAsStream = WupInfo.class.getResourceAsStream("/com/duowan/jce/wup/wup.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            resourceAsStream.close();
            clientInfo = properties.getProperty("client.info");
            clientBuilt = properties.getProperty("client.built");
            clientNumber = properties.getProperty("client.number");
        } catch (Throwable unused) {
        }
        if (clientInfo == null) {
            clientInfo = "Tencent Taf";
        }
        if (clientBuilt == null) {
            clientBuilt = "unknown";
        }
        if (clientNumber == null) {
            clientNumber = "unknown";
        }
    }
}
