package cn.thinkingdata.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.thinkingdata.android.utils.r;
import com.sqwan.bugless.util.FileUtil;
import com.taptap.sdk.db.constant.Common;
import java.util.Date;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class TDReceiver extends BroadcastReceiver {
    private static volatile TDReceiver a;

    public static synchronized TDReceiver a() {
        if (a == null) {
            synchronized (TDReceiver.class) {
                if (a == null) {
                    a = new TDReceiver();
                }
            }
        }
        return a;
    }

    public static void a(Context context) {
        IntentFilter intentFilter = new IntentFilter();
        String strD = r.d(context);
        String str = "cn.thinkingdata.receiver";
        if (strD.length() != 0) {
            str = strD + FileUtil.FILE_EXTENSION_SEPARATOR + "cn.thinkingdata.receiver";
        }
        intentFilter.addAction(str);
        context.registerReceiver(a(), intentFilter);
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        ThinkingAnalyticsSDK thinkingAnalyticsSDKSharedInstance;
        JSONObject jSONObject;
        JSONObject jSONObject2;
        JSONObject jSONObject3;
        int intExtra = intent.getIntExtra("TD_ACTION", 0);
        String stringExtra = intent.getStringExtra("#app_id");
        if (stringExtra == null || stringExtra.length() <= 0 || (thinkingAnalyticsSDKSharedInstance = ThinkingAnalyticsSDK.sharedInstance(context, stringExtra)) == null) {
            return;
        }
        jSONObject = null;
        JSONObject jSONObject4 = null;
        jSONObject = null;
        JSONObject jSONObject5 = null;
        ThinkingAnalyticsEvent tDUpdatableEvent = null;
        switch (intExtra) {
            case 1048578:
                String stringExtra2 = intent.getStringExtra(Common.Predefined.PROPERTIES);
                long longExtra = intent.getLongExtra("TD_DATE", 0L);
                String stringExtra3 = intent.getStringExtra("TD_KEY_TIMEZONE");
                if (stringExtra2 != null) {
                    try {
                        jSONObject = new JSONObject(stringExtra2);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        jSONObject = null;
                    }
                } else {
                    jSONObject = null;
                }
                Date date = longExtra != 0 ? new Date(longExtra) : null;
                TimeZone defaultTimeZone = thinkingAnalyticsSDKSharedInstance.mConfig.getDefaultTimeZone();
                if (stringExtra3 != null) {
                    defaultTimeZone = TimeZone.getTimeZone(stringExtra3);
                }
                thinkingAnalyticsSDKSharedInstance.track(intent.getStringExtra("#event_name"), jSONObject, date, defaultTimeZone);
                break;
            case 1048579:
            case 1048580:
            case 1048581:
                String stringExtra4 = intent.getStringExtra("#event_name");
                String stringExtra5 = intent.getStringExtra(Common.Predefined.PROPERTIES);
                long longExtra2 = intent.getLongExtra("TD_DATE", 0L);
                String stringExtra6 = intent.getStringExtra("TD_KEY_TIMEZONE");
                if (stringExtra5 != null) {
                    try {
                        jSONObject2 = new JSONObject(stringExtra5);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                        jSONObject2 = null;
                    }
                } else {
                    jSONObject2 = null;
                }
                Date date2 = longExtra2 != 0 ? new Date(longExtra2) : null;
                TimeZone timeZone = stringExtra6 != null ? TimeZone.getTimeZone(stringExtra6) : null;
                String stringExtra7 = intent.getStringExtra("TD_KEY_EXTRA_FIELD");
                if (intExtra == 1048579) {
                    TDFirstEvent tDFirstEvent = new TDFirstEvent(stringExtra4, jSONObject2);
                    tDUpdatableEvent = tDFirstEvent;
                    if (stringExtra7 != null) {
                        tDUpdatableEvent = tDFirstEvent;
                        if (stringExtra7.length() > 0) {
                            tDFirstEvent.setFirstCheckId(stringExtra7);
                            tDUpdatableEvent = tDFirstEvent;
                        }
                    }
                } else if (intExtra == 1048581) {
                    tDUpdatableEvent = new TDOverWritableEvent(stringExtra4, jSONObject2, stringExtra7);
                } else if (intExtra == 1048580) {
                    tDUpdatableEvent = new TDUpdatableEvent(stringExtra4, jSONObject2, stringExtra7);
                }
                if (tDUpdatableEvent != null) {
                    tDUpdatableEvent.setEventTime(date2, timeZone);
                    thinkingAnalyticsSDKSharedInstance.track(tDUpdatableEvent);
                }
                break;
            case 1048582:
                String stringExtra8 = intent.getStringExtra(Common.Predefined.PROPERTIES);
                if (stringExtra8 != null) {
                    try {
                        jSONObject5 = new JSONObject(stringExtra8);
                    } catch (JSONException e3) {
                        e3.printStackTrace();
                    }
                }
                thinkingAnalyticsSDKSharedInstance.setFromSubProcess(true);
                thinkingAnalyticsSDKSharedInstance.autoTrack(intent.getStringExtra("#event_name"), jSONObject5);
                break;
            default:
                switch (intExtra) {
                    case 2097152:
                        String stringExtra9 = intent.getStringExtra(Common.Predefined.PROPERTIES);
                        long longExtra3 = intent.getLongExtra("TD_DATE", 0L);
                        if (stringExtra9 != null) {
                            try {
                                jSONObject3 = new JSONObject(stringExtra9);
                            } catch (JSONException e4) {
                                e4.printStackTrace();
                                jSONObject3 = null;
                            }
                        } else {
                            jSONObject3 = null;
                        }
                        thinkingAnalyticsSDKSharedInstance.user_operations(cn.thinkingdata.android.utils.m.a(intent.getStringExtra("TD_KEY_USER_PROPERTY_SET_TYPE")), jSONObject3, longExtra3 != 0 ? new Date(longExtra3) : null);
                        break;
                    case 2097153:
                        String stringExtra10 = intent.getStringExtra(Common.Predefined.PROPERTIES);
                        if (stringExtra10 != null) {
                            try {
                                jSONObject4 = new JSONObject(stringExtra10);
                            } catch (JSONException e5) {
                                e5.printStackTrace();
                            }
                        }
                        thinkingAnalyticsSDKSharedInstance.setSuperProperties(jSONObject4);
                        break;
                    case 2097154:
                        thinkingAnalyticsSDKSharedInstance.login(intent.getStringExtra("#account_id"));
                        break;
                    case 2097155:
                        thinkingAnalyticsSDKSharedInstance.logout();
                        break;
                    case 2097156:
                        thinkingAnalyticsSDKSharedInstance.identify(intent.getStringExtra("#distinct_id"));
                        break;
                    case 2097157:
                        thinkingAnalyticsSDKSharedInstance.flush();
                        break;
                    case 2097158:
                        thinkingAnalyticsSDKSharedInstance.unsetSuperProperty(intent.getStringExtra(Common.Predefined.PROPERTIES));
                        break;
                    case 2097159:
                        thinkingAnalyticsSDKSharedInstance.clearSuperProperties();
                        break;
                }
                break;
        }
    }
}
