package cn.thinkingdata.android;

import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import cn.thinkingdata.android.utils.TDLog;
import cn.thinkingdata.android.utils.q;
import com.sqwan.common.route.FunctionRouter;
import com.taptap.sdk.db.constant.Common;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDWebAppInterface {
    private static final String TAG = "ThinkingAnalytics.TDWebAppInterface";
    private final ThinkingAnalyticsSDK defaultInstance;
    private Map<String, Object> deviceInfoMap;

    class a implements ThinkingAnalyticsSDK.b {
        final /* synthetic */ String a;
        final /* synthetic */ b b;
        final /* synthetic */ String c;

        a(String str, b bVar, String str2) {
            this.a = str;
            this.b = bVar;
            this.c = str2;
        }

        @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
        public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
            if (thinkingAnalyticsSDK.getToken().equals(this.a)) {
                this.b.b();
                TDWebAppInterface.this.trackFromH5(this.c, thinkingAnalyticsSDK);
            }
        }
    }

    private class b {
        private boolean a;

        private b(TDWebAppInterface tDWebAppInterface) {
        }

        /* synthetic */ b(TDWebAppInterface tDWebAppInterface, a aVar) {
            this(tDWebAppInterface);
        }

        boolean a() {
            return !this.a;
        }

        void b() {
            this.a = true;
        }
    }

    TDWebAppInterface(ThinkingAnalyticsSDK thinkingAnalyticsSDK, Map<String, Object> map) {
        this.defaultInstance = thinkingAnalyticsSDK;
        this.deviceInfoMap = map;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void trackFromH5(String str, ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
        JSONArray jSONArray;
        int i;
        if (thinkingAnalyticsSDK.hasDisabled() || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            jSONArray = new JSONObject(str).getJSONArray(FunctionRouter.KEY_DATA);
        } catch (Exception e) {
            e = e;
        }
        for (i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            String string = jSONObject.getString("#time");
            Double dValueOf = null;
            if (jSONObject.has("#zone_offset") && !TDPresetProperties.disableList.contains("#zone_offset")) {
                dValueOf = Double.valueOf(jSONObject.getDouble("#zone_offset"));
            }
            q qVar = new q(string, dValueOf);
            cn.thinkingdata.android.utils.m mVarA = cn.thinkingdata.android.utils.m.a(jSONObject.getString("#type"));
            if (mVarA == null) {
                TDLog.w(TAG, "Unknown data type from H5. ignoring...");
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject(Common.Predefined.PROPERTIES);
            Iterator<String> itKeys = jSONObject2.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                if (next.equals("#account_id") || next.equals("#distinct_id")) {
                    itKeys.remove();
                } else {
                    try {
                        if (this.deviceInfoMap.containsKey(next)) {
                            itKeys.remove();
                        }
                    } catch (Exception e2) {
                        e = e2;
                    }
                }
            }
            if (mVarA.b()) {
                String string2 = jSONObject.getString("#event_name");
                HashMap map = new HashMap();
                if (jSONObject.has("#first_check_id")) {
                    map.put("#first_check_id", jSONObject.getString("#first_check_id"));
                }
                if (jSONObject.has("#event_id")) {
                    map.put("#event_id", jSONObject.getString("#event_id"));
                }
                thinkingAnalyticsSDK.track(string2, jSONObject2, qVar, false, map, mVarA);
            } else {
                thinkingAnalyticsSDK.trackInternal(new cn.thinkingdata.android.a(thinkingAnalyticsSDK, mVarA, jSONObject2, qVar));
            }
            TDLog.w(TAG, "Exception occurred when track data from H5.");
            e.printStackTrace();
            return;
        }
    }

    @JavascriptInterface
    public void thinkingdata_track(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        TDLog.d(TAG, str);
        try {
            String string = new JSONObject(str).getString("#app_id");
            b bVar = new b(this, null);
            ThinkingAnalyticsSDK.allInstances(new a(string, bVar, str));
            if (bVar.a()) {
                trackFromH5(str, this.defaultInstance);
            }
        } catch (JSONException e) {
            TDLog.w(TAG, "Unexpected exception occurred: " + e.toString());
        }
    }
}
