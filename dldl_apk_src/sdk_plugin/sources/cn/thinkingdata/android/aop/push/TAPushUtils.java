package cn.thinkingdata.android.aop.push;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.thinkingdata.android.ThinkingAnalyticsSDK;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TAPushUtils {
    private static final String TA_PUSH_CLICK_EVENT = "ops_push_click";
    private static List<JSONObject> pushList = new ArrayList();
    public static List<String> gtMsgList = new ArrayList();

    public static void clearPushEvent(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
        if (thinkingAnalyticsSDK == null) {
            return;
        }
        Iterator<JSONObject> it = pushList.iterator();
        while (it.hasNext()) {
            thinkingAnalyticsSDK.track(TA_PUSH_CLICK_EVENT, it.next());
        }
        pushList.clear();
    }

    public static boolean handleBundleExtraData(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                return trackPushClickEvent(extras.getString("te_extras"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void handleExtraReceiverData(String str) {
        try {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            trackPushClickEvent(new JSONObject(str).optString("te_extras"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleGtPushEvent(String str, String str2) {
        if (gtMsgList.remove(str2)) {
            handleExtraReceiverData(str);
        }
    }

    public static boolean handleIntentExtraData(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            return trackPushClickEvent(intent.getStringExtra("te_extras"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private static boolean handleJPushIntentData(Intent intent) {
        if (intent == null) {
            return false;
        }
        try {
            JSONObject jSONObject = null;
            String string = intent.getData() != null ? intent.getData().toString() : null;
            if (TextUtils.isEmpty(string) && intent.getExtras() != null) {
                string = intent.getExtras().getString("JMessageExtra");
            }
            if (string != null && !TextUtils.isEmpty(string)) {
                Object objOpt = new JSONObject(string).opt("n_extras");
                if (objOpt instanceof String) {
                    jSONObject = new JSONObject((String) objOpt);
                } else if (objOpt instanceof JSONObject) {
                    jSONObject = (JSONObject) objOpt;
                }
                if (jSONObject != null) {
                    return trackPushClickEvent(jSONObject.optString("te_extras"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void handleMiPushData(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            Serializable serializableExtra = intent.getSerializableExtra("key_message");
            if (serializableExtra != null) {
                Object objInvoke = serializableExtra.getClass().getMethod("getExtra", new Class[0]).invoke(serializableExtra, new Object[0]);
                if (objInvoke instanceof Map) {
                    trackPushClickEvent((String) ((Map) objInvoke).get("te_extras"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void handleStartIntent(Intent intent) {
        if (handleBundleExtraData(intent) || handleIntentExtraData(intent) || handleJPushIntentData(intent)) {
            return;
        }
        handleMiPushData(intent);
    }

    private static boolean trackPushClickEvent(String str) {
        boolean z = true;
        try {
            if (TextUtils.isEmpty(str)) {
                return false;
            }
            Object objOpt = new JSONObject(str).opt("ops_receipt_properties");
            JSONObject jSONObject = null;
            if (objOpt instanceof String) {
                jSONObject = new JSONObject((String) objOpt);
            } else if (objOpt instanceof JSONObject) {
                jSONObject = (JSONObject) objOpt;
            }
            if (jSONObject == null) {
                return false;
            }
            final JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("ops_receipt_properties", jSONObject);
            try {
                final boolean[] zArr = new boolean[1];
                ThinkingAnalyticsSDK.allInstances(new ThinkingAnalyticsSDK.b() { // from class: cn.thinkingdata.android.aop.push.TAPushUtils.1
                    @Override // cn.thinkingdata.android.ThinkingAnalyticsSDK.b
                    public void process(ThinkingAnalyticsSDK thinkingAnalyticsSDK) {
                        zArr[0] = true;
                        thinkingAnalyticsSDK.track(TAPushUtils.TA_PUSH_CLICK_EVENT, jSONObject2);
                    }
                });
                if (zArr[0]) {
                    return true;
                }
                pushList.add(jSONObject2);
                return true;
            } catch (JSONException e) {
                e = e;
            }
        } catch (JSONException e2) {
            e = e2;
            z = false;
        }
        e.printStackTrace();
        return z;
    }
}
