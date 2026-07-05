package cn.thinkingdata.android;

import android.text.TextUtils;
import cn.thinkingdata.android.utils.TDLog;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDFirstEvent extends ThinkingAnalyticsEvent {
    private static final String TAG = "ThinkingAnalytics.TDUniqueEvent";
    private String mExtraValue;

    public TDFirstEvent(String str, JSONObject jSONObject) {
        super(str, jSONObject);
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    cn.thinkingdata.android.utils.m getDataType() {
        return cn.thinkingdata.android.utils.m.TRACK;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    String getExtraField() {
        return "#first_check_id";
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsEvent
    String getExtraValue() {
        return this.mExtraValue;
    }

    public void setFirstCheckId(String str) {
        if (TextUtils.isEmpty(str)) {
            TDLog.w(TAG, "Invalid firstCheckId. Use device Id");
        } else {
            this.mExtraValue = str;
        }
    }
}
