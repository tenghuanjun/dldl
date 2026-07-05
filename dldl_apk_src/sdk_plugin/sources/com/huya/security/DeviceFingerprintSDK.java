package com.huya.security;

import com.huya.security.hydeviceid.BuildConfig;
import com.huya.security.hydeviceid.DeviceInfoCollector;
import com.huya.security.hydeviceid.HyDeviceChecker;
import com.huya.security.hydeviceid.LogBridge;
import com.huya.security.hydeviceid.NativeBridge;
import com.huya.security.hydeviceid.NetworkBridge;
import com.sqwan.liveshow.huya.SqR;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class DeviceFingerprintSDK {
    public static final String TAG = "DeviceFingerprintSDK";
    boolean inited = false;
    public ArrayList<SdidHandler> sdidHandlers = new ArrayList<>();
    static DeviceFingerprintSDK sInstance = new DeviceFingerprintSDK();
    public static String kiwiHost = "https://udbdf.huya.com";
    public static String nimoHost = "https://udbdf-v2.nimo.tv";
    public static String openApiHost = "https://api-cloud.master.live/v1/fingerprint";
    public static String urlCheck = "/device/fingerprint/check";
    public static String urlLog = "/device/fingerprint/log";
    public static String urlLink = "/device/fingerprint/link";
    public static String host = "https://udbdf.huya.com";
    static boolean sdidRecieve = false;
    public static Thread workThread = new Thread() { // from class: com.huya.security.DeviceFingerprintSDK.1
        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            LogBridge.write(4, "begin workThread upload log");
            int i2 = 10;
            long j = 300;
            try {
                byte[] oldLog = DeviceFingerprintSDK.getOldLog();
                long j2 = 300;
                int i3 = 10;
                while (true) {
                    i = i3 - 1;
                    if (i3 <= 0) {
                        break;
                    }
                    byte[] bArrPost = NetworkBridge.post(DeviceFingerprintSDK.host + DeviceFingerprintSDK.urlLog, oldLog);
                    if (bArrPost != null) {
                        String str = new String(bArrPost);
                        LogBridge.write(4, "post log return " + str);
                        if (str.startsWith(SqR.string.ok)) {
                            break;
                        }
                    }
                    Thread.sleep(j2);
                    j2 *= 2;
                    i3 = i;
                }
                if (i < 0) {
                    LogBridge.write(4, "update log failed after 3 times, data size=" + oldLog.length);
                }
            } catch (Exception e) {
                LogBridge.write(4, "upload log failed with exception" + e.getMessage());
            }
            LogBridge.write(4, "begin upload deviceInfo");
            DeviceInfoCollector.collect();
            byte[] uploadBody = NativeBridge.getUploadBody();
            while (true) {
                int i4 = i2 - 1;
                if (i2 <= 0) {
                    return;
                }
                byte[] bArrPost2 = NetworkBridge.post(DeviceFingerprintSDK.host + DeviceFingerprintSDK.urlCheck, uploadBody);
                if (bArrPost2 != null) {
                    LogBridge.write(4, "post deviceInfo return " + new String(bArrPost2));
                    NativeBridge.afterUpload(bArrPost2);
                    DeviceFingerprintSDK.sdidRecieve = true;
                    String sDIDNative = NativeBridge.getSDIDNative();
                    for (SdidHandler sdidHandler : DeviceFingerprintSDK.getInstance().sdidHandlers) {
                        LogBridge.write(4, "call handler " + sdidHandler.getClass().getName());
                        sdidHandler.onSdid(sDIDNative);
                    }
                    LogBridge.write(4, "begin check");
                    HyDeviceChecker hyDeviceChecker = HyDeviceChecker.getInstance();
                    hyDeviceChecker.init(NativeBridge.getContext());
                    hyDeviceChecker.check();
                    LogBridge.write(4, "finish check");
                    return;
                }
                try {
                    Thread.sleep(j);
                    j *= 2;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                i2 = i4;
            }
        }
    };

    public static DeviceFingerprintSDK getInstance() {
        sInstance.init();
        return sInstance;
    }

    public static DeviceFingerprintSDK getInstance(String str) {
        if (str.equals("nimo")) {
            host = nimoHost;
        } else if (str.equals("kiwi")) {
            host = kiwiHost;
        }
        sInstance.init();
        return sInstance;
    }

    public static byte[] getOldLog() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(NativeBridge.getContext().getFilesDir().getAbsolutePath() + File.separator + "hydevice" + File.separator + "deviceid_old.log");
        byte[] bArr = new byte[fileInputStream.available()];
        fileInputStream.read(bArr);
        fileInputStream.close();
        return bArr;
    }

    public void init() {
        if (this.inited) {
            return;
        }
        this.inited = true;
        workThread.start();
    }

    public String getCDID() {
        return NativeBridge.nativeModuleLoaded ? NativeBridge.getCDIDNative() : "";
    }

    public String getSDID() {
        return NativeBridge.nativeModuleLoaded ? NativeBridge.getSDIDNative() : "";
    }

    public void addSdidHandler(SdidHandler sdidHandler) {
        this.sdidHandlers.add(sdidHandler);
        if (sdidRecieve) {
            sdidHandler.onSdid(NativeBridge.getSDIDNative());
        }
    }

    public void link(String str, String str2, String str3) {
        DeviceInfoCollector.appid = str;
        DeviceInfoCollector.mid = str2;
        DeviceInfoCollector.guid = str3;
        if (sdidRecieve) {
            new Thread(new Runnable() { // from class: com.huya.security.DeviceFingerprintSDK.2
                @Override // java.lang.Runnable
                public void run() {
                    int i;
                    try {
                        LogBridge.write(4, "begin link " + DeviceInfoCollector.appid + " " + DeviceInfoCollector.mid + " " + DeviceInfoCollector.guid + " " + BuildConfig.VERSION_NAME);
                        byte[] linkBody = NativeBridge.getLinkBody(DeviceInfoCollector.appid, DeviceInfoCollector.mid, DeviceInfoCollector.guid, BuildConfig.VERSION_NAME);
                        int i2 = 3;
                        while (true) {
                            i = i2 - 1;
                            if (i2 <= 0) {
                                break;
                            }
                            byte[] bArrPost = NetworkBridge.post(DeviceFingerprintSDK.host + DeviceFingerprintSDK.urlLink, linkBody);
                            if (bArrPost != null) {
                                String str4 = new String(bArrPost);
                                LogBridge.write(4, "link post return " + str4);
                                if (str4.startsWith(SqR.string.ok)) {
                                    break;
                                }
                            }
                            Thread.sleep(300L);
                            i2 = i;
                        }
                        if (i < 0) {
                            LogBridge.write(4, "link failed after 3 times, data size=" + linkBody.length);
                        }
                    } catch (Exception e) {
                        LogBridge.write(4, "link failed with exception" + e.getMessage());
                    }
                }
            }).start();
        }
    }
}
