package cn.thinkingdata.android.aop.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.sqwan.common.track.SqTrackNetDnsKey;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TAPushTrackHelper {
    public static void onCreateIntent(Object obj) {
        try {
            if (obj instanceof Activity) {
                TAPushUtils.handleStartIntent(((Activity) obj).getIntent());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onNewIntent(Object obj, Intent intent) {
        try {
            if (obj instanceof Activity) {
                TAPushUtils.handleStartIntent(intent);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackBroadcastReceiverNotification(BroadcastReceiver broadcastReceiver, Context context, Intent intent) {
        if (intent != null && "cn.jpush.android.intent.NOTIFICATION_OPENED".equals(intent.getAction())) {
            TAPushUtils.handleExtraReceiverData(intent.getStringExtra("cn.jpush.android.EXTRA"));
        }
    }

    public static void trackGeTuiNotification(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            String str = (String) obj.getClass().getMethod("getMessageId", new Class[0]).invoke(obj, new Object[0]);
            if (TextUtils.isEmpty(str)) {
                return;
            }
            TAPushUtils.gtMsgList.add(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackGeTuiReceiveMessageData(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            byte[] bArr = (byte[]) obj.getClass().getMethod("getPayload", new Class[0]).invoke(obj, new Object[0]);
            String str = (String) obj.getClass().getMethod("getMessageId", new Class[0]).invoke(obj, new Object[0]);
            if (bArr == null || TextUtils.isEmpty(str)) {
                return;
            }
            TAPushUtils.handleGtPushEvent(new String(bArr), str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackJPushClickNotification(String str, String str2, String str3, String str4) {
        TAPushUtils.handleExtraReceiverData(str);
    }

    public static void trackMeizuNotification(String str, String str2, String str3, String str4) {
    }

    public static void trackUMengActivityNotification(Intent intent) {
        if (intent == null) {
            return;
        }
        try {
            String stringExtra = intent.getStringExtra("body");
            if (TextUtils.isEmpty(stringExtra)) {
                return;
            }
            TAPushUtils.handleExtraReceiverData(new JSONObject(stringExtra).optString(SqTrackNetDnsKey.extra));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackUMengCallBackNotification(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            Object objInvoke = obj.getClass().getMethod("getRaw", new Class[0]).invoke(obj, new Object[0]);
            if (objInvoke instanceof JSONObject) {
                TAPushUtils.handleExtraReceiverData(((JSONObject) objInvoke).optString(SqTrackNetDnsKey.extra));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void trackUmengClickNotification(Object obj) {
        if (obj == null) {
            return;
        }
        try {
            JSONObject jSONObject = (JSONObject) obj.getClass().getDeclaredMethod("getRaw", new Class[0]).invoke(obj, new Object[0]);
            if (jSONObject == null || jSONObject.optJSONObject("body") == null) {
                return;
            }
            TAPushUtils.handleExtraReceiverData(jSONObject.optString(SqTrackNetDnsKey.extra));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
