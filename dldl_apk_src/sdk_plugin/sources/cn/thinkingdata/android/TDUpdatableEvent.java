package cn.thinkingdata.android;

import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDUpdatableEvent extends ThinkingAnalyticsEvent {
    private final String mEventId;

    public TDUpdatableEvent(String str, JSONObject jSONObject, String str2) {
        super(str, jSONObject);
        this.mEventId = str2;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    cn.thinkingdata.android.utils.m getDataType() {
        return cn.thinkingdata.android.utils.m.TRACK_UPDATE;
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
