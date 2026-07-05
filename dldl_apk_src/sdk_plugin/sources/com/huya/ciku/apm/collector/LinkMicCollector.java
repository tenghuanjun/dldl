package com.huya.ciku.apm.collector;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.duowan.monitor.core.OnStatusChangeListener;
import com.duowan.monitor.jce.Dimension;
import com.duowan.monitor.jce.EUnit;
import com.huya.ciku.apm.MonitorCenter;
import com.huya.ciku.apm.model.LinkMicData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class LinkMicCollector implements OnStatusChangeListener {
    private static int LINK_MIC_TIME_OUT = 120000;
    private static final String METRIC_NAME_LINK_MIC = "linkmic";
    protected boolean mEnabled;
    private String mReceiver;
    private String mScene;
    private String mSender;
    protected boolean mStarted;
    protected boolean isValidCanceled = false;
    private boolean mStopInBackground = false;
    private long mInterval = LINK_MIC_TIME_OUT;
    private HashMap<String, String> mMap = new HashMap<>();
    private HashMap<String, LinkMicData> mDataMap = new HashMap<>();
    private Handler mHandler = new Handler() { // from class: com.huya.ciku.apm.collector.LinkMicCollector.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            LinkMicData linkMicData = (LinkMicData) message.obj;
            if (linkMicData == null || !LinkMicCollector.this.mDataMap.containsKey(linkMicData.linkMicId)) {
                return;
            }
            linkMicData.mark("end").step("reportLastEnd");
            linkMicData.stateCode("1");
            LinkMicCollector.this.reportLinkMicData(linkMicData);
            LinkMicCollector.this.mDataMap.remove(linkMicData.linkMicId);
        }
    };

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStart() {
        this.mStarted = true;
    }

    @Override // com.duowan.monitor.core.OnStatusChangeListener
    public void onStop() {
        if (this.mStopInBackground || !MonitorCenter.getInstance().isBackground()) {
            this.mStarted = false;
        }
    }

    @Override // com.duowan.monitor.core.OnConfigListener
    public void onConfig(JSONObject jSONObject) {
        boolean zOptBoolean;
        if (jSONObject != null) {
            zOptBoolean = jSONObject.optBoolean("enabled");
            this.mInterval = jSONObject.optLong("interval");
            this.isValidCanceled = jSONObject.optBoolean("isValidCanceled");
        } else {
            zOptBoolean = false;
        }
        if (this.mInterval <= 0) {
            this.mInterval = LINK_MIC_TIME_OUT;
        }
        this.mEnabled = zOptBoolean;
    }

    protected boolean isEnabled() {
        return this.mEnabled && this.mStarted;
    }

    public void reportLinkMicData(LinkMicData linkMicData) {
        if (!isEnabled() || linkMicData == null) {
            return;
        }
        String str = TextUtils.isEmpty(linkMicData.sender) ? this.mSender : linkMicData.sender;
        String str2 = TextUtils.isEmpty(linkMicData.receiver) ? this.mReceiver : linkMicData.receiver;
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        String str3 = TextUtils.isEmpty(linkMicData.scene) ? this.mScene : linkMicData.scene;
        ArrayList arrayList = new ArrayList();
        String sessionId = TextUtils.isEmpty(linkMicData.sessionId) ? getSessionId(str, str2) : linkMicData.sessionId;
        if ("start".equals(linkMicData.mark)) {
            this.mMap.remove(sessionId);
        }
        String linkMicId = TextUtils.isEmpty(linkMicData.linkMicId) ? getLinkMicId(sessionId) : linkMicData.linkMicId;
        this.mMap.put(sessionId, linkMicId);
        boolean z = !TextUtils.isEmpty(this.mMap.get(linkMicId));
        arrayList.add(new Dimension("sessionId", sessionId));
        arrayList.add(new Dimension("sender", str));
        arrayList.add(new Dimension("receiver", str2));
        arrayList.add(new Dimension("stage", linkMicData.stage));
        arrayList.add(new Dimension("step", linkMicData.step));
        arrayList.add(new Dimension("stateCode", linkMicData.stateCode));
        arrayList.add(new Dimension("respCode", linkMicData.respCode));
        arrayList.add(new Dimension("reason", linkMicData.reason));
        arrayList.add(new Dimension("scene", str3));
        if (z && "end".equals(linkMicData.mark)) {
            arrayList.add(new Dimension("mark", null));
        } else {
            arrayList.add(new Dimension("mark", linkMicData.mark));
        }
        arrayList.add(new Dimension(LinkMicData.DIMENSION_NAME_LINK_MIC_ID, linkMicId));
        arrayList.add(new Dimension("serverSessionId", linkMicData.serverSessionId));
        arrayList.add(new Dimension("appLocalTime", String.valueOf(System.currentTimeMillis())));
        MonitorCenter.getInstance().request(METRIC_NAME_LINK_MIC, 0.0d, EUnit.EUnit_Count, arrayList);
        if ("end".equals(linkMicData.mark)) {
            this.mMap.put(linkMicId, "end");
            removeMessagesAndPostEnd(null, sessionId, linkMicId);
        }
        linkMicData.stateCode("1").scene(str3).sessionId(sessionId).sender(str).receiver(str2).linkMicId(linkMicId);
        if ("start".equals(linkMicData.mark)) {
            postLinkMicEnd(linkMicData);
        }
        removeMessagesAndPostEnd(linkMicData, sessionId, linkMicId);
    }

    private void removeMessagesAndPostEnd(LinkMicData linkMicData, String str, String str2) {
        int iHashCode = str.hashCode();
        LinkMicData linkMicData2 = this.mDataMap.get(str2);
        if (linkMicData2 != null) {
            int i = linkMicData2.status;
            this.mHandler.removeMessages(iHashCode);
            this.mDataMap.remove(str2);
            if (linkMicData == null || "end".equals(linkMicData.mark)) {
                return;
            }
            linkMicData.status(i);
            postLinkMicEnd(linkMicData);
        }
    }

    public void updateStatus(String str, String str2, int i) {
        LinkMicData linkMicData = this.mDataMap.get(getLinkMicId(getSessionId(str, str2)));
        if (linkMicData != null) {
            linkMicData.status(i);
        }
    }

    public void markLinkMic(String str, String str2) {
        this.mSender = str;
        this.mReceiver = str2;
    }

    public void reportPKStartData(String str, String str2, String str3) {
        LinkMicData linkMicData = new LinkMicData();
        linkMicData.stateCode(str).stage(LinkMicData.STAGE_P).step(LinkMicData.PK_STEP_START_MODE).mark("start").respCode(str2).reason(str3);
        reportLinkMicData(linkMicData);
    }

    public void scene(String str) {
        this.mScene = str;
    }

    public void postLinkMicEnd(LinkMicData linkMicData) {
        if (linkMicData == null) {
            return;
        }
        Message message = new Message();
        message.obj = linkMicData;
        message.what = linkMicData.sessionId.hashCode();
        this.mHandler.sendMessageDelayed(message, this.mInterval + 10000);
        this.mDataMap.put(linkMicData.linkMicId, linkMicData);
    }

    public String getLinkMicId(String str) {
        String str2 = this.mMap.get(str);
        if (!TextUtils.isEmpty(str2)) {
            return str2;
        }
        String string = UUID.randomUUID().toString();
        this.mMap.put(str, string);
        return string;
    }

    public String getSessionId(String str, String str2) {
        return String.format("%s&%s", str, str2);
    }

    public boolean isValidCanceled() {
        return this.isValidCanceled;
    }

    public void clearOnEndLive() {
        this.mMap.clear();
        this.mDataMap.clear();
    }
}
