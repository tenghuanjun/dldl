package cn.thinkingdata.android;

import android.content.Context;
import android.content.Intent;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.utils.r;
import com.sqwan.bugless.util.FileUtil;
import com.taptap.sdk.db.constant.Common;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
class j extends ThinkingAnalyticsSDK {
    Context a;
    String b;
    private final JSONObject c;

    static /* synthetic */ class a {
        static final /* synthetic */ int[] a;

        static {
            int[] iArr = new int[cn.thinkingdata.android.utils.m.values().length];
            a = iArr;
            try {
                iArr[cn.thinkingdata.android.utils.m.TRACK_OVERWRITE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                a[cn.thinkingdata.android.utils.m.TRACK_UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                a[cn.thinkingdata.android.utils.m.TRACK.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public j(TDConfig tDConfig) {
        super(tDConfig, new boolean[0]);
        this.a = tDConfig.mContext;
        this.c = new JSONObject();
        this.b = r.b(this.a);
    }

    double a(String str) {
        d dVar;
        synchronized (this.mTrackTimer) {
            dVar = this.mTrackTimer.get(str);
            this.mTrackTimer.remove(str);
        }
        if (dVar != null) {
            return Double.parseDouble(dVar.b());
        }
        return 0.0d;
    }

    public Intent a() {
        Intent intent = new Intent();
        String strD = r.d(this.a);
        String str = "cn.thinkingdata.receiver";
        if (strD.length() != 0) {
            str = strD + FileUtil.FILE_EXTENSION_SEPARATOR + "cn.thinkingdata.receiver";
        }
        intent.setAction(str);
        intent.putExtra("#app_id", this.mConfig.getName());
        return intent;
    }

    public JSONObject a(String str, JSONObject jSONObject) {
        JSONObject dynamicSuperProperties;
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (!TDPresetProperties.disableList.contains("#bundle_id")) {
                jSONObject2.put("#bundle_id", this.b);
            }
            double dA = a(str);
            if (dA > 0.0d && !TDPresetProperties.disableList.contains("#duration")) {
                jSONObject2.put("#duration", dA);
            }
        } catch (JSONException unused) {
        }
        if (getDynamicSuperPropertiesTracker() != null && (dynamicSuperProperties = getDynamicSuperPropertiesTracker().getDynamicSuperProperties()) != null) {
            try {
                r.a(dynamicSuperProperties, jSONObject2, this.mConfig.getDefaultTimeZone());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            r.a(jSONObject, jSONObject2, this.mConfig.getDefaultTimeZone());
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject2;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    void autoTrack(String str, JSONObject jSONObject) {
        Intent intentA = a();
        intentA.putExtra("#event_name", str);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        JSONObject jSONObjectA = a(str, jSONObject);
        try {
            JSONObject jSONObjectOptJSONObject = getAutoTrackProperties().optJSONObject(str);
            if (jSONObjectOptJSONObject != null) {
                r.a(jSONObjectOptJSONObject, jSONObjectA, this.mConfig.getDefaultTimeZone());
            }
            intentA.putExtra(Common.Predefined.PROPERTIES, jSONObjectA.toString());
            intentA.putExtra("TD_ACTION", 1048582);
            if (this.a != null) {
                this.a.sendBroadcast(intentA);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void clearSuperProperties() {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097159);
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void enableAutoTrack(List<ThinkingAnalyticsSDK.AutoTrackEventType> list, ThinkingAnalyticsSDK.AutoTrackEventListener autoTrackEventListener) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void enableTracking(boolean z) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void flush() {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097157);
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public JSONObject getAutoTrackProperties() {
        return this.c;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public boolean hasOptOut() {
        return false;
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void identify(String str) {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097156);
        if (str == null || str.length() <= 0) {
            str = "";
        }
        intentA.putExtra("#distinct_id", str);
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void login(String str) {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097154);
        if (str == null || str.length() <= 0) {
            str = "";
        }
        intentA.putExtra("#account_id", str);
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void logout() {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097155);
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optInTracking() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optOutTracking() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void optOutTrackingAndDeleteUser() {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setAutoTrackProperties(List<ThinkingAnalyticsSDK.AutoTrackEventType> list, JSONObject jSONObject) {
        if (hasDisabled()) {
            return;
        }
        if (jSONObject != null) {
            try {
                if (cn.thinkingdata.android.utils.h.a(jSONObject)) {
                    JSONObject jSONObject2 = new JSONObject();
                    for (ThinkingAnalyticsSDK.AutoTrackEventType autoTrackEventType : list) {
                        JSONObject jSONObject3 = new JSONObject();
                        r.a(jSONObject, jSONObject3, this.mConfig.getDefaultTimeZone());
                        jSONObject2.put(autoTrackEventType.getEventName(), jSONObject3);
                    }
                    synchronized (this.c) {
                        r.b(jSONObject2, this.c, this.mConfig.getDefaultTimeZone());
                    }
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.mConfig.shouldThrowException()) {
            throw new n("Set autoTrackEvent properties failed. Please refer to the SDK debug log for details.");
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setNetworkType(ThinkingAnalyticsSDK.ThinkingdataNetworkType thinkingdataNetworkType) {
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setSuperProperties(JSONObject jSONObject) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            r.a(jSONObject, jSONObject2, this.mConfig.getDefaultTimeZone());
            Intent intentA = a();
            intentA.putExtra("TD_ACTION", 2097153);
            if (jSONObject != null) {
                intentA.putExtra(Common.Predefined.PROPERTIES, jSONObject2.toString());
            }
            if (this.a != null) {
                this.a.sendBroadcast(intentA);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void setTrackStatus(ThinkingAnalyticsSDK.TATrackStatus tATrackStatus) {
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0039  */
    /* JADX WARN: Removed duplicated region for block: B:16:0x003f  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:27:? A[RETURN, SYNTHETIC] */
    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void track(cn.thinkingdata.android.ThinkingAnalyticsEvent r5) {
        /*
            r4 = this;
            android.content.Intent r0 = r4.a()
            int[] r1 = cn.thinkingdata.android.j.a.a
            cn.thinkingdata.android.utils.m r2 = r5.getDataType()
            int r2 = r2.ordinal()
            r1 = r1[r2]
            java.lang.String r2 = "TD_ACTION"
            r3 = 1
            if (r1 == r3) goto L24
            r3 = 2
            if (r1 == r3) goto L20
            r3 = 3
            if (r1 == r3) goto L1c
            goto L2a
        L1c:
            r1 = 1048579(0x100003, float:1.469372E-39)
            goto L27
        L20:
            r1 = 1048580(0x100004, float:1.469374E-39)
            goto L27
        L24:
            r1 = 1048581(0x100005, float:1.469375E-39)
        L27:
            r0.putExtra(r2, r1)
        L2a:
            java.lang.String r1 = r5.getEventName()
            java.lang.String r2 = "#event_name"
            r0.putExtra(r2, r1)
            org.json.JSONObject r1 = r5.getProperties()
            if (r1 != 0) goto L3f
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            goto L43
        L3f:
            org.json.JSONObject r1 = r5.getProperties()
        L43:
            java.lang.String r2 = r5.getEventName()
            org.json.JSONObject r1 = r4.a(r2, r1)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = "properties"
            r0.putExtra(r2, r1)
            java.util.Date r1 = r5.getEventTime()
            if (r1 == 0) goto L67
            java.util.Date r1 = r5.getEventTime()
            long r1 = r1.getTime()
            java.lang.String r3 = "TD_DATE"
            r0.putExtra(r3, r1)
        L67:
            java.util.TimeZone r1 = r5.getTimeZone()
            if (r1 == 0) goto L7a
            java.util.TimeZone r1 = r5.getTimeZone()
            java.lang.String r1 = r1.getID()
            java.lang.String r2 = "TD_KEY_TIMEZONE"
            r0.putExtra(r2, r1)
        L7a:
            java.lang.String r5 = r5.getExtraValue()
            java.lang.String r1 = "TD_KEY_EXTRA_FIELD"
            r0.putExtra(r1, r5)
            android.content.Context r5 = r4.a
            if (r5 == 0) goto L8a
            r5.sendBroadcast(r0)
        L8a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.thinkingdata.android.j.track(cn.thinkingdata.android.ThinkingAnalyticsEvent):void");
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void track(String str) {
        track(str, (JSONObject) null, (Date) null, (TimeZone) null);
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void track(String str, JSONObject jSONObject) {
        track(str, jSONObject, (Date) null, (TimeZone) null);
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void track(String str, JSONObject jSONObject, Date date) {
        track(str, jSONObject, date, (TimeZone) null);
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void track(String str, JSONObject jSONObject, Date date, TimeZone timeZone) {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 1048578);
        intentA.putExtra("#event_name", str);
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        intentA.putExtra(Common.Predefined.PROPERTIES, a(str, jSONObject).toString());
        if (date != null) {
            intentA.putExtra("TD_DATE", date.getTime());
        }
        if (timeZone != null) {
            intentA.putExtra("TD_KEY_TIMEZONE", timeZone.getID());
        }
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    public void unsetSuperProperty(String str) {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097158);
        if (str != null) {
            intentA.putExtra(Common.Predefined.PROPERTIES, str);
        }
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }

    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK
    void user_operations(cn.thinkingdata.android.utils.m mVar, JSONObject jSONObject, Date date) {
        Intent intentA = a();
        intentA.putExtra("TD_ACTION", 2097152);
        intentA.putExtra("TD_KEY_USER_PROPERTY_SET_TYPE", mVar.a());
        if (jSONObject != null) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                r.a(jSONObject, jSONObject2, this.mConfig.getDefaultTimeZone());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            intentA.putExtra(Common.Predefined.PROPERTIES, jSONObject2.toString());
        }
        if (date != null) {
            intentA.putExtra("TD_DATE", date.getTime());
        }
        Context context = this.a;
        if (context != null) {
            context.sendBroadcast(intentA);
        }
    }
}
