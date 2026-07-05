package cn.thinkingdata.android;

import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.r;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class p {
    private final ThinkingAnalyticsSDK a;
    private final TDConfig b;

    public p(ThinkingAnalyticsSDK thinkingAnalyticsSDK, TDConfig tDConfig) {
        this.a = thinkingAnalyticsSDK;
        this.b = tDConfig;
    }

    public void a(cn.thinkingdata.android.utils.m mVar, JSONObject jSONObject, Date date) {
        if (this.a.hasDisabled()) {
            return;
        }
        if (!cn.thinkingdata.android.utils.h.a(jSONObject)) {
            TDLog.w("ThinkingAnalytics.UserOperation", "The data contains invalid key or value: " + jSONObject.toString());
            if (this.b.shouldThrowException()) {
                throw new n("Invalid properties. Please refer to SDK debug log for detail reasons.");
            }
        }
        try {
            cn.thinkingdata.android.utils.f fVarA = date == null ? this.a.mCalibratedTimeManager.a() : this.a.mCalibratedTimeManager.a(date, null);
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject != null) {
                r.a(jSONObject, jSONObject2, this.b.getDefaultTimeZone());
            }
            this.a.trackInternal(new a(this.a, mVar, jSONObject2, fVarA));
        } catch (Exception e) {
            TDLog.w("ThinkingAnalytics.UserOperation", e.getMessage());
        }
    }

    public void a(String str, Number number) {
        if (this.a.hasDisabled()) {
            return;
        }
        try {
            if (number == null) {
                TDLog.d("ThinkingAnalytics.UserOperation", "user_add value must be Number");
                if (this.b.shouldThrowException()) {
                    throw new n("Invalid property values for user add.");
                }
            } else {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(str, number);
                a(jSONObject, (Date) null);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            if (this.b.shouldThrowException()) {
                throw new n(e);
            }
        }
    }

    public void a(Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_DEL, null, date);
    }

    public void a(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_ADD, jSONObject, date);
    }

    public void a(String... strArr) {
        if (this.a.hasDisabled() || strArr == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        for (String str : strArr) {
            try {
                jSONObject.put(str, 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jSONObject.length() > 0) {
            f(jSONObject, null);
        }
    }

    public void b(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_APPEND, jSONObject, date);
    }

    public void c(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_SET, jSONObject, date);
    }

    public void d(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_SET_ONCE, jSONObject, date);
    }

    public void e(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_UNIQ_APPEND, jSONObject, date);
    }

    public void f(JSONObject jSONObject, Date date) {
        this.a.user_operations(cn.thinkingdata.android.utils.m.USER_UNSET, jSONObject, date);
    }
}
