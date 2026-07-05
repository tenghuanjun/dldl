package cn.thinkingdata.android;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDOverWritableEvent extends ThinkingAnalyticsEvent {
    private final String mEventId;

    public TDOverWritableEvent(String str, JSONObject jSONObject, String str2) {
        super(str, jSONObject);
        this.mEventId = str2;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    cn.thinkingdata.android.utils.m getDataType() {
        return cn.thinkingdata.android.utils.m.TRACK_OVERWRITE;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    String getExtraField() {
        return "#event_id";
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    String getExtraValue() {
        return this.mEventId;
    }
}
