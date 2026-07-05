package com.duowan.monitor.core;

import android.os.Build;
import com.duowan.monitor.jce.UserId;
import com.duowan.monitor.utility.FileUtils;
import com.duowan.monitor.utility.MonitorLog;
import com.duowan.monitor.utility.MonitorThread;
import com.duowan.monitor.utility.NetworkUtil;
import com.duowan.monitor.utility.StringUtil;
import com.sqwan.bugless.core.Constant;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class NetworkConfigLoader {
    private static final String FILE_CACHE = "file_cache";
    private static final String TAG = "NetworkConfigLoader";
    private long mCacheTime;
    private JSONObject mConfig;
    private long mLastTime;
    private UserId mLastUserId;
    private Monitor mMonitor;
    private String mUrl;
    private Map<String, String> mDefaultParams = new HashMap();
    private Map<String, String> mUserParams = Collections.emptyMap();
    private Map<String, String> mLastUserParams = Collections.emptyMap();

    public NetworkConfigLoader(Monitor monitor, String str, long j) {
        if (monitor == null) {
            throw new NullPointerException("monitor can't be null");
        }
        this.mMonitor = monitor;
        this.mCacheTime = j;
        this.mUrl = str;
        UserId userId = monitor.getUserInfoProvider().getUserId();
        if (userId == null) {
            throw new NullPointerException("UserId can't be null");
        }
        String sHuYaUA = userId.getSHuYaUA();
        this.mDefaultParams.put("platform", sHuYaUA.substring(0, sHuYaUA.indexOf("&")));
        this.mDefaultParams.put("android_version", String.valueOf(Build.VERSION.SDK_INT));
        this.mDefaultParams.put("android_product", String.valueOf(Build.PRODUCT));
        this.mDefaultParams.put("android_brand", Build.BRAND);
        this.mDefaultParams.put("android_model", Build.MODEL);
        this.mDefaultParams.put("android_manu", Build.MANUFACTURER);
        this.mDefaultParams.put("android_release", Build.VERSION.RELEASE);
        this.mDefaultParams.put("android_app_version", String.valueOf(DeviceInfo.getInstance().getVersionCode()));
        String deviceId = DeviceInfo.getInstance().getDeviceId();
        if (StringUtil.isEmpty(deviceId)) {
            return;
        }
        this.mDefaultParams.put(Constant.DEVICE_ID, deviceId);
    }

    public JSONObject getConfig() {
        return this.mConfig;
    }

    public void setParams(final Map<String, String> map) {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.NetworkConfigLoader.1
            @Override // java.lang.Runnable
            public void run() {
                if (map == null) {
                    NetworkConfigLoader.this.mUserParams = Collections.emptyMap();
                } else {
                    NetworkConfigLoader.this.mUserParams = new HashMap();
                    NetworkConfigLoader.this.mUserParams.putAll(map);
                }
                NetworkConfigLoader.this.updateConfig();
            }
        });
    }

    public void updateConfig() {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.NetworkConfigLoader.2
            /* JADX WARN: Removed duplicated region for block: B:12:0x0034  */
            @Override // java.lang.Runnable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void run() {
                /*
                    r8 = this;
                    com.duowan.monitor.core.NetworkConfigLoader r0 = com.duowan.monitor.core.NetworkConfigLoader.this
                    com.duowan.monitor.core.Monitor r0 = com.duowan.monitor.core.NetworkConfigLoader.access$100(r0)
                    com.duowan.monitor.core.UserInfoProvider r0 = r0.getUserInfoProvider()
                    com.duowan.monitor.jce.UserId r0 = r0.getUserId()
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    com.duowan.monitor.jce.UserId r1 = com.duowan.monitor.core.NetworkConfigLoader.access$200(r1)
                    if (r0 != 0) goto L19
                    if (r1 == 0) goto L1f
                    goto L34
                L19:
                    boolean r1 = r0.equals(r1)
                    if (r1 == 0) goto L34
                L1f:
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    java.util.Map r1 = com.duowan.monitor.core.NetworkConfigLoader.access$000(r1)
                    com.duowan.monitor.core.NetworkConfigLoader r2 = com.duowan.monitor.core.NetworkConfigLoader.this
                    java.util.Map r2 = com.duowan.monitor.core.NetworkConfigLoader.access$300(r2)
                    boolean r1 = r1.equals(r2)
                    if (r1 != 0) goto L32
                    goto L34
                L32:
                    r1 = 0
                    goto L35
                L34:
                    r1 = 1
                L35:
                    long r2 = android.os.SystemClock.uptimeMillis()
                    if (r1 != 0) goto L4e
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    long r4 = com.duowan.monitor.core.NetworkConfigLoader.access$400(r1)
                    long r4 = r2 - r4
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    long r6 = com.duowan.monitor.core.NetworkConfigLoader.access$500(r1)
                    int r1 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
                    if (r1 >= 0) goto L4e
                    return
                L4e:
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    com.duowan.monitor.core.NetworkConfigLoader.access$402(r1, r2)
                    com.duowan.monitor.core.NetworkConfigLoader r1 = com.duowan.monitor.core.NetworkConfigLoader.this
                    com.duowan.monitor.core.NetworkConfigLoader.access$202(r1, r0)
                    com.duowan.monitor.core.NetworkConfigLoader r0 = com.duowan.monitor.core.NetworkConfigLoader.this
                    java.util.Map r1 = com.duowan.monitor.core.NetworkConfigLoader.access$000(r0)
                    com.duowan.monitor.core.NetworkConfigLoader.access$302(r0, r1)
                    com.duowan.monitor.core.NetworkConfigLoader r0 = com.duowan.monitor.core.NetworkConfigLoader.this
                    com.duowan.monitor.core.NetworkConfigLoader.access$600(r0)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.duowan.monitor.core.NetworkConfigLoader.AnonymousClass2.run():void");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestConfig() {
        final HashMap map = new HashMap();
        map.putAll(this.mDefaultParams);
        UserId userId = this.mLastUserId;
        if (userId != null) {
            map.put("uid", String.valueOf(userId.getLUid()));
            map.put("huya_ua", String.valueOf(this.mLastUserId.getSHuYaUA()));
        }
        map.putAll(this.mUserParams);
        MonitorThread.execute(new Runnable() { // from class: com.duowan.monitor.core.NetworkConfigLoader.3
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                File file = new File(NetworkConfigLoader.this.mMonitor.getContext().getFilesDir(), NetworkConfigLoader.FILE_CACHE);
                if (NetworkConfigLoader.this.mConfig == null) {
                    String strFileToString = FileUtils.fileToString(file);
                    if (!StringUtil.isEmpty(strFileToString)) {
                        NetworkConfigLoader.this.onConfig(strFileToString);
                    }
                }
                if (NetworkUtil.isNetworkAvailable(NetworkConfigLoader.this.mMonitor.getContext())) {
                    String str = NetworkUtil.get(String.format("%s/%s/config?", NetworkConfigLoader.this.mUrl, NetworkConfigLoader.this.mMonitor.getAppId()), map, 1);
                    if (StringUtil.isEmpty(str)) {
                        return;
                    }
                    MonitorLog.d(NetworkConfigLoader.TAG, str);
                    NetworkConfigLoader.this.onConfig(str);
                    FileUtils.stringToFile(file, str);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfig(final String str) {
        MonitorThread.runOnMonitorThread(new Runnable() { // from class: com.duowan.monitor.core.NetworkConfigLoader.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    NetworkConfigLoader.this.mConfig = new JSONObject(str);
                    NetworkConfigLoader.this.mMonitor.onConfig(NetworkConfigLoader.this.mConfig);
                } catch (JSONException e) {
                    MonitorLog.e(NetworkConfigLoader.TAG, "requestConfig", e);
                }
            }
        });
    }
}
