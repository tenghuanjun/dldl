package com.duowan.monitor.core;

import com.duowan.jce.wup.UniPacket;
import com.duowan.monitor.jce.MetricDetail;
import com.duowan.monitor.jce.MetricDetailSet;
import com.duowan.monitor.utility.MonitorThread;
import com.duowan.monitor.utility.NetworkUtil;
import com.duowan.taf.jce.JceStruct;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public final class WupWriter implements Dispatcher {
    private static final long CALLBACK_DURATION = 20000;
    private static final String FUNC_NAME = "reportDetailV2";
    private static final String REQUEST_KEY = "tReq";
    private static final String SERVANT_NAME = "metric";
    private boolean mHasPendingWork;
    private final String mUrl;
    private UserInfoProvider mUserInfoProvider;
    private List<MetricDetail> mBuffer = new ArrayList();
    private boolean mEnabled = true;
    private Runnable mRunnable = new Runnable() { // from class: com.duowan.monitor.core.WupWriter.1
        @Override // java.lang.Runnable
        public void run() {
            ArrayList<MetricDetail> buffer = WupWriter.this.getBuffer();
            if (buffer == null) {
                return;
            }
            final MetricDetailSet metricDetailSet = new MetricDetailSet();
            metricDetailSet.vMetricDetail = buffer;
            metricDetailSet.tId = WupWriter.this.mUserInfoProvider.getUserId();
            MonitorThread.execute(new Runnable() { // from class: com.duowan.monitor.core.WupWriter.1.1
                @Override // java.lang.Runnable
                public void run() {
                    NetworkUtil.post(WupWriter.this.mUrl, WupWriter.this.create("metric", WupWriter.FUNC_NAME, WupWriter.REQUEST_KEY, metricDetailSet).encode());
                }
            });
            MonitorThread.postDelayed(WupWriter.this.mRunnable, WupWriter.CALLBACK_DURATION);
        }
    };

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
    }

    public WupWriter(UserInfoProvider userInfoProvider, String str) {
        this.mUserInfoProvider = userInfoProvider;
        this.mUrl = str;
    }

    @Override // com.duowan.monitor.core.Dispatcher
    public void request(MetricDetail metricDetail) {
        if (metricDetail != null && this.mEnabled) {
            addBuffer(metricDetail);
        }
    }

    private synchronized void addBuffer(MetricDetail metricDetail) {
        this.mBuffer.add(metricDetail);
        if (!this.mHasPendingWork) {
            MonitorThread.postDelayed(this.mRunnable, CALLBACK_DURATION);
            this.mHasPendingWork = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized ArrayList<MetricDetail> getBuffer() {
        ArrayList<MetricDetail> arrayList = new ArrayList<>();
        arrayList.addAll(this.mBuffer);
        this.mBuffer.clear();
        if (!arrayList.isEmpty()) {
            return arrayList;
        }
        this.mHasPendingWork = false;
        return null;
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.mEnabled = jSONObject.optBoolean("enabled");
        } else {
            this.mEnabled = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public UniPacket create(String str, String str2, String str3, JceStruct jceStruct) {
        UniPacket uniPacket = new UniPacket(true);
        uniPacket.setServantName(str);
        uniPacket.setFuncName(str2);
        uniPacket.put(str3, jceStruct);
        return uniPacket;
    }
}
