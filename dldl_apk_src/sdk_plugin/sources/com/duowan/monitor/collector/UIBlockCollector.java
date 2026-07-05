package com.duowan.monitor.collector;

import android.os.Build;
import android.os.Looper;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.duowan.monitor.collector.LooperBlockCollector;
import com.duowan.monitor.core.DeviceInfo;
import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.core.UserInfoProvider;
import com.duowan.monitor.jce.UserId;
import com.duowan.monitor.utility.MonitorThread;
import com.duowan.monitor.utility.NetworkUtil;
import com.snail.antifake.deviceid.ShellAdbUtils;
import com.sqwan.bugless.core.Constant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class UIBlockCollector implements OnStatusChangeListener, LooperBlockCollector.BlockListener {
    private String mAppId;
    private boolean mEnabled;
    private final boolean mTest;
    private String mUrl;
    private UserInfoProvider mUserInfoProvider;
    private boolean mStopped = true;
    private final LooperBlockCollector mLooperBlockCollector = new LooperBlockCollector(Looper.getMainLooper(), this);

    public UIBlockCollector(String str, String str2, UserInfoProvider userInfoProvider, boolean z) {
        this.mAppId = str;
        this.mTest = z;
        this.mUserInfoProvider = userInfoProvider;
        this.mUrl = str2;
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
        if (this.mStopped) {
            this.mStopped = false;
            update();
        }
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        if (this.mStopped) {
            return;
        }
        this.mStopped = true;
        update();
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        boolean zOptBoolean;
        long jOptLong;
        if (jSONObject != null) {
            zOptBoolean = jSONObject.optBoolean("enabled");
            jOptLong = jSONObject.optLong("threshold");
        } else {
            zOptBoolean = false;
            jOptLong = 0;
        }
        this.mLooperBlockCollector.setThreshold(jOptLong);
        this.mEnabled = zOptBoolean;
        update();
    }

    private void update() {
        if (!this.mStopped && this.mEnabled) {
            this.mLooperBlockCollector.start();
        } else {
            this.mLooperBlockCollector.stop();
        }
    }

    @Override // com.duowan.monitor.collector.LooperBlockCollector.BlockListener
    public void onBlockEvent(final long j, final long j2) {
        MonitorThread.execute(new Runnable() { // from class: com.duowan.monitor.collector.UIBlockCollector.1
            @Override // java.lang.Runnable
            public void run() throws Throwable {
                ArrayList<String> threadStackEntries = UIBlockCollector.this.mLooperBlockCollector.getThreadStackEntries(j, j2);
                if (threadStackEntries.isEmpty()) {
                    return;
                }
                UIBlockCollector uIBlockCollector = UIBlockCollector.this;
                uIBlockCollector.report(uIBlockCollector.mAppId, UIBlockCollector.this.mUrl, UIBlockCollector.this.getStackString(threadStackEntries), DeviceInfo.getInstance().getVersionName(), String.valueOf(DeviceInfo.getInstance().getVersionCode()), j2 - j, "cpu-rate = " + UIBlockCollector.this.mLooperBlockCollector.getCpuRateInfo(j) + ShellAdbUtils.COMMAND_LINE_END, Build.MODEL, Build.VERSION.SDK_INT + " " + Build.VERSION.RELEASE, UIBlockCollector.this.mTest);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getStackString(ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder();
        sb.append(Constant.CRASH_STACK);
        sb.append(" = ");
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                sb.append(it.next());
                sb.append(ShellAdbUtils.COMMAND_LINE_END);
            }
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void report(String str, String str2, String str3, String str4, String str5, long j, String str6, String str7, String str8, boolean z) throws Throwable {
        UserId userId;
        HashMap map = new HashMap();
        map.put(FeedBackConstants.KEY_FB_APPID, str);
        UserInfoProvider userInfoProvider = this.mUserInfoProvider;
        if (userInfoProvider != null && (userId = userInfoProvider.getUserId()) != null) {
            String sHuYaUA = userId.getSHuYaUA();
            map.put("platform", sHuYaUA.substring(0, sHuYaUA.indexOf("&")));
        }
        map.put("debug", String.valueOf(z));
        map.put(Constant.PKG_VERSION_NAME, str4);
        map.put(Constant.PKG_VERSION_CODE, str5);
        map.put("time", j + "");
        map.put("performanceInfo", str6);
        map.put(Constant.DEV_MODEL, str7);
        map.put("os", str8);
        map.put("content", str3);
        map.put("version", "2");
        NetworkUtil.post(str2, map);
    }
}
