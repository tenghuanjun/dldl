package com.sq.diagnostic.assistant.tcping;

import com.sq.diagnostic.assistant.log.SQLogUtils;
import java.net.InetAddress;
import java.net.Socket;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class TcpingManager {
    private static final String TAG = "TCPingManager";

    public static TcpingResult tcping(String str, int i) {
        String hostAddress;
        long jCurrentTimeMillis = System.currentTimeMillis();
        TcpingResult tcpingResult = new TcpingResult();
        try {
            hostAddress = InetAddress.getByName(str).getHostAddress();
        } catch (Exception e) {
            e.printStackTrace();
            hostAddress = str;
        }
        try {
            new Socket(hostAddress, i).close();
            tcpingResult.code = 0;
        } catch (Exception e2) {
            SQLogUtils.e(TAG, e2.toString());
            tcpingResult.exception = e2;
            tcpingResult.code = -1;
        }
        tcpingResult.host = str;
        tcpingResult.port = i;
        tcpingResult.ip = hostAddress;
        tcpingResult.latency = System.currentTimeMillis() - jCurrentTimeMillis;
        return tcpingResult;
    }
}
