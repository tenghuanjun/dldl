package com.tencent.mars.app;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes4.dex */
public class AppLogic {
    public static final String TAG = "mars.AppLogic";
    private static ICallBack callBack;

    public interface ICallBack {
        AccountInfo getAccountInfo();

        String getAppFilePath();

        int getClientVersion();

        DeviceInfo getDeviceType();
    }

    public static class AccountInfo {
        public long uin;
        public String userName;

        public AccountInfo() {
            this.uin = 0L;
            this.userName = "";
        }

        public AccountInfo(long j, String str) {
            this.uin = 0L;
            this.userName = "";
            this.uin = j;
            this.userName = str;
        }
    }

    public static class DeviceInfo {
        public String devicename;
        public String devicetype;

        public DeviceInfo(String str, String str2) {
            this.devicename = "";
            this.devicetype = "";
            this.devicename = str;
            this.devicetype = str2;
        }
    }

    public static void setCallBack(ICallBack iCallBack) {
        callBack = iCallBack;
    }

    public static String getAppFilePath() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return null;
            }
            return callBack.getAppFilePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static AccountInfo getAccountInfo() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return null;
            }
            return callBack.getAccountInfo();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int getClientVersion() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return 0;
            }
            return callBack.getClientVersion();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static DeviceInfo getDeviceType() {
        try {
            if (callBack == null) {
                new NullPointerException("callback is null").printStackTrace();
                return null;
            }
            return callBack.getDeviceType();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
