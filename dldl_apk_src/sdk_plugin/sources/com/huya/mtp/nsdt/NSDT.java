package com.huya.mtp.nsdt;

import android.util.Log;
import com.huya.mtp.utils.NetworkUtils;
import java.util.List;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class NSDT {
    private static final String TAG = "NSDT";
    private static NSDTCallBack sCallBack;

    public interface NSDTCallBack {
        void pingReturnFromNative(int i, String str, String str2, int i2, long j, double d);

        void planReturnFromNative(String str, int i, int i2, int i3);

        void reportPlanResult(String str);

        void tcpReturnFromNative(int i, List<TCPResult> list);

        void traceReturnFromNative(int i, boolean z, String[] strArr, String str);
    }

    public static native int detectIP(String[] strArr, int[] iArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, String str);

    public static native int getIPRtt(String str);

    public static native int getIPStatus(String str);

    public static native void init();

    public static native int ping(int i, String str, String str2, int i2, int i3, int i4);

    public static native int tcp(int i, String str, String str2, int i2, int i3, String str3);

    public static native void test();

    public static native int traceroute(int i, String[] strArr);

    static {
        System.loadLibrary("nsdt");
    }

    public static void setCallBack(NSDTCallBack nSDTCallBack) {
        sCallBack = nSDTCallBack;
    }

    static void traceReturnFromNative(int i, boolean z, String[] strArr, String str) {
        NSDTCallBack nSDTCallBack = sCallBack;
        if (nSDTCallBack != null) {
            nSDTCallBack.traceReturnFromNative(i, z, strArr, str);
        }
    }

    static void pingReturnFromNative(int i, String str, String str2, int i2, long j, double d) {
        NSDTCallBack nSDTCallBack = sCallBack;
        if (nSDTCallBack != null) {
            nSDTCallBack.pingReturnFromNative(i, str, str2, i2, j, d);
        }
    }

    static void tcpReturnFromNative(int i, List<TCPResult> list) {
        NSDTCallBack nSDTCallBack = sCallBack;
        if (nSDTCallBack != null) {
            nSDTCallBack.tcpReturnFromNative(i, list);
        }
    }

    static void planReturnFromNative(String str, int i, int i2, int i3) {
        NSDTCallBack nSDTCallBack = sCallBack;
        if (nSDTCallBack != null) {
            nSDTCallBack.planReturnFromNative(str, i, i2, i3);
        }
    }

    public static String getNetType() {
        String netWorkType = NetworkUtils.getNetWorkType();
        Log.e("NSDT", "getNetType: " + netWorkType);
        return netWorkType;
    }

    public static boolean isNetworkAvailable() {
        Log.e("NSDT", "isNetworkAvailable: " + NetworkUtils.isNetworkAvailable());
        return NetworkUtils.isNetworkAvailable();
    }

    public static void reportDetectResult(String str) {
        Log.d("NSDT", "reportDetectResult: " + str);
        NSDTCallBack nSDTCallBack = sCallBack;
        if (nSDTCallBack != null) {
            nSDTCallBack.reportPlanResult(str);
        }
    }

    public static class TCPResult {
        long allCost;
        long connectTime;
        long dnsCost = 0;
        int errCode;
        int errType;
        long firstRecvCost;
        String host;
        String ip;
        int port;
        long recvCost;
        long rtt;
        long sendCost;

        public TCPResult(String str, String str2, int i, long j, long j2, long j3, long j4, long j5, long j6, int i2, int i3) {
            this.host = str;
            this.ip = str2;
            this.port = i;
            this.rtt = j;
            this.connectTime = j2;
            this.sendCost = j3;
            this.recvCost = j4;
            this.allCost = j6;
            this.firstRecvCost = j5;
            this.errCode = i2;
            this.errType = i3;
        }

        public String getIp() {
            return this.ip;
        }

        public long getRtt() {
            return this.rtt;
        }

        public long getConnectTime() {
            return this.connectTime;
        }

        public int getErrCode() {
            return this.errCode;
        }

        public String getHost() {
            return this.host;
        }

        public void setHost(String str) {
            this.host = str;
        }

        public void setIp(String str) {
            this.ip = str;
        }

        public int getPort() {
            return this.port;
        }

        public void setPort(int i) {
            this.port = i;
        }

        public void setRtt(long j) {
            this.rtt = j;
        }

        public void setConnectTime(long j) {
            this.connectTime = j;
        }

        public long getSendCost() {
            return this.sendCost;
        }

        public void setSendCost(long j) {
            this.sendCost = j;
        }

        public long getRecvCost() {
            return this.recvCost;
        }

        public void setRecvCost(long j) {
            this.recvCost = j;
        }

        public long getFirstRecvCost() {
            return this.firstRecvCost;
        }

        public void setFirstRecvCost(long j) {
            this.firstRecvCost = j;
        }

        public long getAllCost() {
            return this.allCost;
        }

        public void setAllCost(long j) {
            this.allCost = j;
        }

        public long getDnsCost() {
            return this.dnsCost;
        }

        public void setErrCode(int i) {
            this.errCode = i;
        }

        public int getErrType() {
            return this.errType;
        }

        public void setErrType(int i) {
            this.errType = i;
        }

        public void setDnsCost(long j) {
            this.dnsCost = j;
        }

        public String toString() {
            return "TCPResult{host=" + this.host + ", ip=" + this.ip + ", port=" + this.port + ", rtt=" + this.rtt + ", dnsCost=" + this.dnsCost + ", connectTime=" + this.connectTime + ", sendCost=" + this.sendCost + ", firstRecvCost=" + this.firstRecvCost + ", recvCost=" + this.recvCost + ", allCost=" + this.allCost + ", errType=" + this.errType + ", errCode=" + this.errCode + AbstractJsonLexerKt.END_OBJ;
        }
    }
}
