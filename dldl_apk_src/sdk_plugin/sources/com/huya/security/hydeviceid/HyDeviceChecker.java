package com.huya.security.hydeviceid;

import android.content.Context;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huya.security.DeviceFingerprintSDK;
import com.huya.statistics.core.StatisticsContent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.slf4j.Marker;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class HyDeviceChecker {
    private static HyDeviceChecker sHyDeviceChecker = new HyDeviceChecker();
    private static String urlDeviceChecker = "https://udbdf.huya.com/dckey";
    private LocalStorage localStorage = new LocalStorage();
    public Context mContext = null;
    private String sdid = "";
    private String smid = "";
    private String cdid = "";
    private String guid = "";
    private String mid = "";
    private String appid = "";

    public static void setUrlDeviceChecker(String str) {
        urlDeviceChecker = str;
    }

    public void setGuid(String str) {
        this.guid = str;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getMid() {
        return this.mid;
    }

    public String getAppid() {
        return this.appid;
    }

    public static synchronized HyDeviceChecker getInstance() {
        return sHyDeviceChecker;
    }

    public void init(Context context) {
        this.mContext = context;
        this.localStorage.init(context);
    }

    private boolean ready() {
        String str;
        String str2;
        String str3;
        String str4 = this.sdid;
        return (str4 == null || str4.isEmpty() || this.sdid.startsWith(Marker.ANY_MARKER) || (str = this.cdid) == null || str.isEmpty() || this.guid == null || (str2 = this.mid) == null || str2.isEmpty() || (str3 = this.appid) == null || str3.isEmpty()) ? false : true;
    }

    public void check() {
        while (true) {
            try {
                this.cdid = NativeBridge.getCDIDNative();
                this.sdid = NativeBridge.getSDIDNative();
                if (this.guid == null || this.guid.isEmpty()) {
                    this.guid = DeviceInfoCollector.getGuid();
                }
                this.mid = DeviceInfoCollector.getMid();
                this.appid = DeviceInfoCollector.getAppId();
                if (ready()) {
                    break;
                } else {
                    Thread.sleep(200L);
                }
            } catch (InterruptedException unused) {
                return;
            }
        }
        LogBridge.write(4, "check mid=" + this.mid + " guid=" + this.guid);
        String strLoadLocalDcKey = this.localStorage.loadLocalDcKey();
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic", (Object) "dckey");
        jSONObject.put("version", (Object) BuildConfig.VERSION_NAME);
        jSONObject.put("sdid", (Object) this.sdid);
        jSONObject.put("smid", (Object) this.smid);
        jSONObject.put("cdid", (Object) this.cdid);
        jSONObject.put(StatisticsContent.GUID, (Object) this.guid);
        jSONObject.put(StatisticsContent.MID, (Object) this.mid);
        jSONObject.put("appid", (Object) this.appid);
        jSONObject.put("dckey", (Object) strLoadLocalDcKey);
        byte[] bytes = jSONObject.toString().getBytes();
        LogBridge.write(4, "upload check requestData " + new String(bytes));
        int i = 3;
        while (true) {
            i--;
            if (i < 0) {
                break;
            }
            try {
                byte[] bArrPost = NetworkBridge.post(DeviceFingerprintSDK.host + "/dckey/check", bytes);
                if (bArrPost != null) {
                    JSONObject jSONObject2 = (JSONObject) JSON.parse(new String(bArrPost));
                    String string = jSONObject2.getString("status");
                    LogBridge.write(4, "check post return " + jSONObject2);
                    if (string.equals("success")) {
                        this.localStorage.saveLocalDckey(jSONObject2.getString("dckey"));
                        return;
                    }
                    Thread.sleep(200L);
                } else {
                    continue;
                }
            } catch (InterruptedException unused2) {
                if (i < 0) {
                    LogBridge.write(4, "upload check failed after 3 times");
                    return;
                }
                return;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public class LocalStorage {
        File localfile = null;

        public LocalStorage() {
        }

        public boolean init(Context context) {
            File file = new File(context.getFilesDir(), "hydckey");
            this.localfile = file;
            if (file.exists()) {
                return true;
            }
            try {
                this.localfile.createNewFile();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return true;
            }
        }

        public boolean saveLocalDckey(String str) {
            File file = this.localfile;
            boolean z = false;
            if (file == null || !file.canWrite()) {
                return false;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(this.localfile);
                DataOutputStream dataOutputStream = new DataOutputStream(fileOutputStream);
                dataOutputStream.writeUTF(str);
                z = true;
                fileOutputStream.close();
                dataOutputStream.close();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return z;
            }
        }

        public String loadLocalDcKey() {
            File file = this.localfile;
            String utf = "";
            if (file == null || !file.canRead()) {
                return "";
            }
            try {
                FileInputStream fileInputStream = new FileInputStream(this.localfile);
                DataInputStream dataInputStream = new DataInputStream(fileInputStream);
                utf = dataInputStream.readUTF();
                fileInputStream.close();
                dataInputStream.close();
                return utf;
            } catch (IOException e) {
                e.printStackTrace();
                return utf;
            }
        }
    }
}
