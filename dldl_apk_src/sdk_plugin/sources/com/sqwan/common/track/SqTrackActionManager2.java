package com.sqwan.common.track;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import com.sq.tool.logger.SQLog;
import com.sqwan.common.mod.track.TrackModManager2;
import com.sqwan.common.util.LogUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class SqTrackActionManager2 {
    private static String TAG = "SqTrackActionManager2";
    private static SqTrackActionManager2 sInstance;
    public static boolean sIsCpTest = Log.isLoggable("sysdk.cptest.track", 3);
    private boolean isAuthCheck = false;
    private Context mContext;

    private SqTrackActionManager2() {
    }

    public static SqTrackActionManager2 getInstance() {
        if (sInstance == null) {
            sInstance = new SqTrackActionManager2();
        }
        return sInstance;
    }

    public void init(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void setAuthCheck(boolean z) {
        this.isAuthCheck = z;
    }

    public void trackAction(String str, String str2) {
        trackAction(str, str2, (HashMap<String, String>) null);
    }

    public void trackAction(String str, String str2, HashMap<String, String> map) {
        SqTrackAction2.sdk_expand.construct(str, str2);
        trackAction(SqTrackAction2.sdk_expand, map);
    }

    public void trackAction(SqTrackAction2 sqTrackAction2) {
        trackAction(sqTrackAction2, (Map<String, String>) null, (HashMap<String, String>) null);
    }

    public void trackAction(SqTrackAction2 sqTrackAction2, Map<String, String> map) {
        trackAction(sqTrackAction2, map, (HashMap<String, String>) null);
    }

    public void trackActionExt(SqTrackAction2 sqTrackAction2, HashMap<String, String> map) {
        trackAction(sqTrackAction2, (Map<String, String>) null, map);
    }

    public void trackActionCPTest(SqTrackAction2 sqTrackAction2, Map<String, String> map) {
        if (sIsCpTest) {
            trackAction(sqTrackAction2, map);
        }
    }

    public void trackActionCPTest(SqTrackAction2 sqTrackAction2) {
        trackActionCPTest(sqTrackAction2, null);
    }

    public void trackErrorReport(Object obj, String str, Throwable th) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        HashMap map = new HashMap();
        map.put(SqTrackKey.error_type, str);
        map.put(SqTrackKey.error_class_name, name);
        map.put(SqTrackKey.error_stack_trace, string);
        trackAction(SqTrackAction2.error_report, map);
    }

    public void trackDeprecatedCall(Object obj) {
        if (obj == null) {
            return;
        }
        String name = obj.getClass().getName();
        Throwable th = new Throwable();
        StringWriter stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        String string = stringWriter.toString();
        HashMap map = new HashMap();
        map.put(SqTrackKey.deprecated_class_name, name);
        map.put(SqTrackKey.deprecated_stack_trace, string);
        trackAction(SqTrackAction2.deprecated_call, map);
    }

    public void trackAction(SqTrackAction2 sqTrackAction2, Map<String, String> map, HashMap<String, String> map2) {
        if (sqTrackAction2 == null) {
            return;
        }
        trackAction(sqTrackAction2.getEvent(), map, map2);
    }

    public void trackAction(String str, Map<String, String> map, HashMap<String, String> map2) {
        if (this.mContext == null) {
            LogUtil.d(TAG, "please call init first!");
            return;
        }
        if (str == null) {
            LogUtil.d(TAG, "the track action is null");
            return;
        }
        HashMap map3 = new HashMap();
        map3.put("event", str);
        if (map != null && !map.isEmpty()) {
            map3.putAll(map);
        }
        if (map2 != null && !map2.isEmpty()) {
            JSONObject jSONObject = new JSONObject();
            for (Map.Entry<String, String> entry : map2.entrySet()) {
                if (!TextUtils.isEmpty(entry.getKey())) {
                    try {
                        jSONObject.put(entry.getKey(), TextUtils.isEmpty(entry.getValue()) ? "" : entry.getValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            map3.put("ext", jSONObject.toString());
        }
        try {
            TrackModManager2.track(str, map3);
        } catch (Throwable th) {
            SQLog.e("上报异常, event=" + str, th);
        }
    }

    public void flush() {
        try {
            TrackModManager2.flush();
        } catch (Throwable unused) {
        }
    }

    public void trackBtn(String str, String str2) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.btn_id, str);
        map.put(SqTrackKey.btn_ext, str2);
        trackAction(SqTrackAction2.sdk_btn_click, map);
    }

    public void trackBtn(String str, String str2, Map<String, String> map) {
        HashMap map2 = new HashMap();
        map2.put(SqTrackKey.btn_id, str);
        map2.put(SqTrackKey.btn_ext, str2);
        map2.putAll(map);
        trackAction(SqTrackAction2.sdk_btn_click, map2);
    }
}
