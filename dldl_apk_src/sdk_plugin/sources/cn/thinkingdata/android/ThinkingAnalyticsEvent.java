package cn.thinkingdata.android;

import java.util.Date;
import java.util.TimeZone;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public abstract class ThinkingAnalyticsEvent {
    private final String mEventName;
    private Date mEventTime;
    private final JSONObject mProperties;
    private TimeZone mTimeZone;

    ThinkingAnalyticsEvent(String str, JSONObject jSONObject) {
        this.mEventName = str;
        this.mProperties = jSONObject;
    }

    abstract cn.thinkingdata.android.utils.m getDataType();

    String getEventName() {
        return this.mEventName;
    }

    Date getEventTime() {
        return this.mEventTime;
    }

    abstract String getExtraField();

    abstract String getExtraValue();

    JSONObject getProperties() {
        return this.mProperties;
    }

    TimeZone getTimeZone() {
        return this.mTimeZone;
    }

    public void setEventTime(Date date) {
        this.mEventTime = date;
    }

    public void setEventTime(Date date, TimeZone timeZone) {
        this.mEventTime = date;
        this.mTimeZone = timeZone;
    }
}
