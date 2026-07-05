package com.ishumei.O000O00000OoO;

import android.content.Context;
import android.content.SharedPreferences;
import com.duowan.live.one.module.uploadLog.FeedBackConstants;
import com.huya.mtp.hyns.report.NSPushReporter;
import com.huya.statistics.core.StatisticsContent;
import com.ishumei.O000O0000O0oO.O000O00000OoO;
import com.ishumei.O000O0000OOoO.O000O0000Oo0O;
import com.ishumei.O000O0000OOoO.O000O0000OoO;
import com.ishumei.O000O0000OOoO.O00O0000OooO;
import com.sqwan.common.route.FunctionRouter;
import com.sqwan.msdk.api.IMUrl;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class O0000O000000oO {
    private O000O00000OoO O0000O000000oO;
    private String O000O00000OoO;
    private String O000O00000o0O;
    private Context O000O00000oO;
    private O000O00000OoO.AbstractC0045O000O00000OoO O000O0000O0oO;

    /* JADX INFO: renamed from: com.ishumei.O000O00000OoO.O0000O000000oO$O0000O000000oO, reason: collision with other inner class name */
    private static class C0040O0000O000000oO {
        private static final O0000O000000oO O0000O000000oO = new O0000O000000oO();
    }

    private O0000O000000oO() {
        this.O000O00000oO = null;
        this.O000O0000O0oO = new O000O00000OoO.AbstractC0045O000O00000OoO(false, 2) { // from class: com.ishumei.O000O00000OoO.O0000O000000oO.1
            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public void O0000O000000oO(String str) {
                O000O00000OoO O000O00000OoO = O0000O000000oO.this.O000O00000OoO(str);
                if (O000O00000OoO == null) {
                    return;
                }
                O0000O000000oO.this.O0000O000000oO = O000O00000OoO;
            }

            @Override // com.ishumei.O000O0000O0oO.O000O00000OoO.AbstractC0045O000O00000OoO
            public boolean O0000O000000oO(String str, int i) {
                super.O0000O000000oO(str, i);
                return false;
            }
        };
        this.O000O00000oO = O000O00000oO.O0000O000000oO;
    }

    public static O0000O000000oO O0000O000000oO() {
        return C0040O0000O000000oO.O0000O000000oO;
    }

    private O000O00000OoO O0000O000000oO(Context context) {
        String strO000O00000OoO = O000O00000OoO(context);
        if (O000O0000Oo0O.O000O00000oO(strO000O00000OoO)) {
            return null;
        }
        JSONObject jSONObject = new JSONObject(strO000O00000OoO);
        O000O00000OoO o000O00000OoOO0000O000000oO = O0000O000000oO(jSONObject.getString(FunctionRouter.KEY_DATA), jSONObject.getInt(NSPushReporter.NS_PUSH_LENGTH_KEY), jSONObject.has("enc") ? jSONObject.getInt("enc") : 0, jSONObject.has(StatisticsContent.VER) ? jSONObject.getInt(StatisticsContent.VER) : 0);
        o000O00000OoOO0000O000000oO.O0000O000000oO("local");
        return o000O00000OoOO0000O000000oO;
    }

    private O000O00000OoO O0000O000000oO(String str, int i, int i2, int i3) {
        String strO0000O000000oO;
        if (str == null) {
            return null;
        }
        try {
            byte[] bArrO000O0000Oo0O = O000O0000OoO.O000O0000Oo0O(str);
            if (i2 == 1) {
                byte[] bArrO0000O000000oO = O00O0000OooO.O0000O000000oO(com.ishumei.O000O0000OOoO.O000O00000OoO.O000O00000OoO("zaq1mko0", bArrO000O0000Oo0O, i));
                strO0000O000000oO = new String(bArrO0000O000000oO, 0, bArrO0000O000000oO.length, "utf-8");
            } else {
                strO0000O000000oO = com.ishumei.O000O0000OOoO.O000O00000OoO.O0000O000000oO("zaq1mko0", bArrO000O0000Oo0O, i);
            }
            return i3 == 1 ? O000O00000OoO.O000O00000oO(strO0000O000000oO) : O000O00000OoO.O000O0000O0oO(strO0000O000000oO);
        } catch (Exception unused) {
            return null;
        }
    }

    private void O0000O000000oO(Context context, String str) throws IOException {
        SharedPreferences.Editor editorEdit = context.getSharedPreferences("cloudms.conf", 0).edit();
        editorEdit.putString("conf", str);
        if (!editorEdit.commit()) {
            throw new IOException("editor commit failed");
        }
    }

    private void O0000O000000oO(String str, O000O00000OoO.AbstractC0045O000O00000OoO abstractC0045O000O00000OoO, com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO) {
        try {
            HashMap map = new HashMap();
            map.put("organization", this.O000O00000OoO);
            HashMap map2 = new HashMap();
            map2.put("os", IMUrl.OS);
            map2.put(StatisticsContent.SDKVER, "2.8.4");
            map2.put(FeedBackConstants.KEY_LOG_MD5, str);
            map2.put("enc", new Integer(1));
            map2.put("smid", com.ishumei.O0000O000000oO.O000O0000OoO.O0000O000000oO().O000O00000o0O());
            map.put(FunctionRouter.KEY_DATA, map2);
            new com.ishumei.O000O0000O0oO.O000O00000OoO().O0000O000000oO(o0000O000000oO).O0000O000000oO(O000O0000OoO.O0000O000000oO((Map<?, ?>) map).toString().getBytes("utf-8"), (Map<String, String>) null, O000O00000OoO(), (O000O00000OoO.AbstractC0045O000O00000OoO<?>) abstractC0045O000O00000OoO);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public O000O00000OoO O000O00000OoO(String str) {
        JSONObject jSONObject;
        try {
            JSONObject jSONObject2 = new JSONObject(str);
            if (1100 != jSONObject2.getInt("code") || (jSONObject = jSONObject2.getJSONObject("detail")) == null || jSONObject.getInt("code") != 0) {
                return null;
            }
            int i = jSONObject.getInt(NSPushReporter.NS_PUSH_LENGTH_KEY);
            int i2 = jSONObject.has("enc") ? jSONObject.getInt("enc") : 0;
            int i3 = jSONObject.has(StatisticsContent.VER) ? jSONObject.getInt(StatisticsContent.VER) : 0;
            String string = jSONObject.getString(FunctionRouter.KEY_DATA);
            O000O00000OoO o000O00000OoOO0000O000000oO = O0000O000000oO(string, i, i2, i3);
            o000O00000OoOO0000O000000oO.O0000O000000oO("cloud");
            HashMap map = new HashMap();
            map.put(FunctionRouter.KEY_DATA, string);
            map.put(NSPushReporter.NS_PUSH_LENGTH_KEY, Integer.valueOf(i));
            map.put("enc", Integer.valueOf(i2));
            map.put(StatisticsContent.VER, Integer.valueOf(i3));
            O0000O000000oO(this.O000O00000oO, O000O0000OoO.O0000O000000oO((Map<?, ?>) map).toString());
            return o000O00000OoOO0000O000000oO;
        } catch (Exception unused) {
            return null;
        }
    }

    private String O000O00000OoO(Context context) {
        String string = context.getSharedPreferences("cloudms.conf", 0).getString("conf", null);
        O000O0000Oo0O.O0000O000000oO(string);
        return string;
    }

    private synchronized O000O00000OoO O000O00000oO() {
        O000O00000OoO o000O00000OoOO000O0000O0oO;
        try {
            O000O00000OoO o000O00000OoOO0000O000000oO = O0000O000000oO(this.O000O00000oO);
            if (o000O00000OoOO0000O000000oO != null) {
                return o000O00000OoOO0000O000000oO;
            }
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000oO("CloudConfiguration", "load local cloud conf failed: " + e);
        }
        try {
            o000O00000OoOO000O0000O0oO = O000O0000O0oO();
            if (o000O00000OoOO000O0000O0oO != null) {
                return o000O00000OoOO000O0000O0oO;
            }
        } catch (Exception unused) {
            o000O00000OoOO000O0000O0oO = null;
        }
        return o000O00000OoOO000O0000O0oO;
    }

    private O000O00000OoO O000O0000O0oO() {
        JSONObject jSONObject = new JSONObject("{\"code\":0,\"data\":\"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\",\"enc\":1,\"length\":4323,\"ver\":1}");
        O000O00000OoO o000O00000OoOO0000O000000oO = O0000O000000oO(jSONObject.getString(FunctionRouter.KEY_DATA), jSONObject.getInt(NSPushReporter.NS_PUSH_LENGTH_KEY), jSONObject.has("enc") ? jSONObject.getInt("enc") : 0, jSONObject.has(StatisticsContent.VER) ? jSONObject.getInt(StatisticsContent.VER) : 0);
        o000O00000OoOO0000O000000oO.O0000O000000oO("code");
        return o000O00000OoOO0000O000000oO;
    }

    public void O0000O000000oO(com.ishumei.O000O0000O0oO.O0000O000000oO o0000O000000oO) {
        try {
            O000O00000OoO o000O00000OoOO000O00000o0O = O000O00000o0O();
            O0000O000000oO(o000O00000OoOO000O00000o0O == null ? "" : o000O00000OoOO000O00000o0O.O00O0000oO0O(), this.O000O0000O0oO, o0000O000000oO);
        } catch (Exception e) {
            com.ishumei.O000O0000OOoO.O000O00000oO.O000O00000OoO("CloudConfiguration", e.getMessage());
        }
    }

    public void O0000O000000oO(String str, String str2) {
        this.O000O00000OoO = str;
        this.O000O00000o0O = str2;
    }

    public boolean O0000O000000oO(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.getInt("code") != 0) {
                return false;
            }
            int i = jSONObject.getInt(NSPushReporter.NS_PUSH_LENGTH_KEY);
            int i2 = jSONObject.has("enc") ? jSONObject.getInt("enc") : 0;
            int i3 = jSONObject.has(StatisticsContent.VER) ? jSONObject.getInt(StatisticsContent.VER) : 0;
            String string = jSONObject.getString(FunctionRouter.KEY_DATA);
            O0000O000000oO(string, i, i2, i3).O0000O000000oO("cloud");
            HashMap map = new HashMap();
            map.put(FunctionRouter.KEY_DATA, string);
            map.put(NSPushReporter.NS_PUSH_LENGTH_KEY, Integer.valueOf(i));
            map.put("enc", Integer.valueOf(i2));
            map.put(StatisticsContent.VER, Integer.valueOf(i3));
            O0000O000000oO(this.O000O00000oO, O000O0000OoO.O0000O000000oO((Map<?, ?>) map).toString());
            return true;
        } catch (Exception unused) {
            return false;
        }
    }

    public String O000O00000OoO() {
        return this.O000O00000o0O;
    }

    public synchronized O000O00000OoO O000O00000o0O() {
        if (this.O0000O000000oO == null) {
            this.O0000O000000oO = O000O00000oO();
        }
        return O000O00000OoO.O0000O000000oO(this.O0000O000000oO);
    }
}
