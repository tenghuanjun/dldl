package com.tencent.mars.sdt;

import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class SdtLogic {
    public static final String TAG = "mars.SdtLogic";
    private static ICallBack callBack;

    public interface ICallBack {
        void reportSignalDetectResults(String str);
    }

    public interface NetCheckType {
        public static final int kDnsCheck = 1;
        public static final int kHttpCheck = 4;
        public static final int kNewDnsCheck = 2;
        public static final int kPingCheck = 0;
        public static final int kTcpCheck = 3;
    }

    public interface TcpCheckErrCode {
        public static final int kAssertErr = -4;
        public static final int kConnectErr = -8;
        public static final int kPipeExp = -7;
        public static final int kPipeIntr = -2;
        public static final int kSelectErr = -1;
        public static final int kSelectExpErr = -6;
        public static final int kSndRcvErr = -3;
        public static final int kTcpNonErr = 1;
        public static final int kTcpRespErr = -9;
        public static final int kTcpSucc = 0;
        public static final int kTimeoutErr = -5;
    }

    private static native ArrayList<String> getLoadLibraries();

    public static native void setHttpNetcheckCGI(String str);

    public static void setCallBack(ICallBack iCallBack) {
        callBack = iCallBack;
    }

    private static void reportSignalDetectResults(String str) {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
            } else {
                callBack.reportSignalDetectResults(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
