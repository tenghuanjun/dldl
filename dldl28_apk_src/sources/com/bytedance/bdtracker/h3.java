package com.bytedance.bdtracker;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;
import com.bytedance.applog.IPicker;
import com.bytedance.applog.simulate.SimulateLaunchActivity;
import com.bytedance.framwork.core.sdkmonitor.MonitorConstants;
import com.lzy.okgo.model.HttpHeaders;
import com.mobile.auth.BuildConfig;
import com.volcengine.common.contant.CommonConstants;
import java.util.Collections;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class h3 extends AsyncTask<Void, Void, JSONObject> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f258a;
    public int b;
    public String c;
    public String d;
    public String e;
    public String f;
    public final d g;

    public static void a(d dVar) {
        new h3(dVar).execute(new Void[0]);
    }

    @Override // android.os.AsyncTask
    public JSONObject doInBackground(Void[] voidArr) throws JSONException {
        String str;
        JSONObject jSONObject;
        int i;
        if (SimulateLaunchActivity.entryMode == 0) {
            d dVar = this.g;
            e3 e3Var = dVar.k;
            String str2 = dVar.m;
            String str3 = this.d;
            int i2 = this.f258a;
            int i3 = this.b;
            String str4 = this.e;
            String str5 = this.c;
            e3Var.b.D.debug(11, "Start to login simulator with device id:{} and qrParam:{}...", str4, str5);
            JSONObject jSONObject2 = new JSONObject();
            try {
                JSONObject jSONObjectA = e3.a(str2, str3);
                jSONObjectA.put("width", i2);
                jSONObjectA.put("height", i3);
                jSONObjectA.put(MonitorConstants.KEY_DEVICE_ID, str4);
                jSONObject2.put("header", jSONObjectA);
                jSONObject2.put("qr_param", str5);
                HashMap<String, String> mapA = e3Var.a();
                try {
                    String str6 = new String(e3Var.b.getNetClient().execute((byte) 1, e3Var.f245a + "/simulator/mobile/login", jSONObject2, mapA, (byte) 0, true, 60000));
                    e3Var.b.D.debug(11, "Login simulator with response:{}", str6);
                    if (!n0.c(str6)) {
                        return new JSONObject(str6);
                    }
                } catch (Throwable th) {
                    e3Var.b.D.error(11, "Login simulator failed", th, new Object[0]);
                }
            } catch (Throwable th2) {
                e3Var.b.D.error(11, "JSON handle failed", th2, new Object[0]);
            }
        } else {
            d dVar2 = this.g;
            e3 e3Var2 = dVar2.k;
            String str7 = dVar2.m;
            String str8 = this.d;
            int i4 = this.f258a;
            int i5 = this.b;
            String str9 = this.e;
            e3Var2.b.D.debug(11, "Start to login simulator with device id:{}...", str9);
            JSONObject jSONObject3 = new JSONObject();
            try {
                JSONObject jSONObjectA2 = e3.a(str7, str8);
                n0.a(jSONObjectA2, e3Var2.b.getHeader());
                jSONObjectA2.put("width", i4);
                jSONObjectA2.put("height", i5);
                jSONObjectA2.put(MonitorConstants.KEY_DEVICE_ID, str9);
                jSONObjectA2.put("device_model", Build.MODEL);
                jSONObject3.put("header", jSONObjectA2);
                HashMap<String, String> mapA2 = e3Var2.a();
                String string = "";
                String str10 = null;
                while (true) {
                    if (!isCancelled()) {
                        long jCurrentTimeMillis = System.currentTimeMillis();
                        try {
                            jSONObject3.put("sync_id", string);
                            str = new String(e3Var2.b.getNetClient().execute((byte) 1, e3Var2.f245a + "/simulator/limited_mobile/try_link", jSONObject3, mapA2, (byte) 0, true, 60000));
                            try {
                                jSONObject = new JSONObject(str);
                                i = jSONObject.getJSONObject("data").getInt("retry");
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            th = th4;
                        }
                        if (i == 0) {
                            break;
                        }
                        if (i == 2) {
                            str10 = str;
                            break;
                        }
                        try {
                            string = jSONObject.getJSONObject("data").getString("sync_id");
                            str10 = str;
                        } catch (Throwable th5) {
                            th = th5;
                            str10 = str;
                            e3Var2.b.D.error(11, "Post to simulate login failed", th, new Object[0]);
                        }
                        long jCurrentTimeMillis2 = System.currentTimeMillis() - jCurrentTimeMillis;
                        if (jCurrentTimeMillis2 < 1000) {
                            try {
                                Thread.sleep(1000 - jCurrentTimeMillis2);
                            } catch (InterruptedException e) {
                                e3Var2.b.D.error(11, "Sleep interrupted", e, new Object[0]);
                            }
                        }
                    } else {
                        break;
                    }
                }
                e3Var2.b.D.debug(11, "Login simulator with response:{}", str10);
                if (!n0.c(str10)) {
                    try {
                        return new JSONObject(str10);
                    } catch (Throwable th6) {
                        e3Var2.b.D.error(11, "JSON handle failed", th6, new Object[0]);
                    }
                }
            } catch (Throwable th7) {
                e3Var2.b.D.error(11, "JSON handle failed", th7, new Object[0]);
            }
        }
        return null;
    }

    @Override // android.os.AsyncTask
    public void onPostExecute(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        int iIndexOf;
        JSONObject jSONObject2 = jSONObject;
        this.g.D.debug(Collections.singletonList("SimulateLoginTask"), "Simulate login with response: {}", jSONObject2);
        if (jSONObject2 == null) {
            Toast.makeText(this.g.n, "启动埋点验证|圈选失败，服务端无响应", 1).show();
            return;
        }
        String strOptString = jSONObject2.optString(CommonConstants.KEY_MESSAGE);
        String strOptString2 = jSONObject2.optString(HttpHeaders.HEAD_KEY_SET_COOKIE);
        int iOptInt = jSONObject2.optInt("status");
        if (n0.d(strOptString2) && (iIndexOf = strOptString2.indexOf(";")) >= 0) {
            strOptString2 = strOptString2.substring(0, iIndexOf);
        }
        if (SimulateLaunchActivity.entryMode == 1 && (jSONObjectOptJSONObject = jSONObject2.optJSONObject("data")) != null) {
            this.f = jSONObjectOptJSONObject.optString("mode", "").equals(BuildConfig.FLAVOR_type) ? SimulateLaunchActivity.DEBUG_LOG : SimulateLaunchActivity.BIND_QUERY;
        }
        if (iOptInt == 0 && "OK".equals(strOptString)) {
            if (SimulateLaunchActivity.DEBUG_LOG.equals(this.f)) {
                this.g.setRangersEventVerifyEnable(true, strOptString2);
                return;
            }
            IPicker picker = (this.g.getInitConfig() == null || this.g.getInitConfig().getPicker() == null) ? null : this.g.getInitConfig().getPicker();
            if (picker != null) {
                picker.setMarqueeCookie(strOptString2);
            }
            this.g.startSimulator(strOptString2);
            return;
        }
        if (iOptInt == 0 || !n0.d(jSONObject2.optString(CommonConstants.KEY_MESSAGE))) {
            this.g.D.warn(Collections.singletonList("SimulateLoginTask"), "Start simulator failed, please check server response: {}", jSONObject2);
            return;
        }
        Application application = this.g.n;
        StringBuilder sbA = a.a("启动埋点验证|圈选失败: ");
        sbA.append(jSONObject2.optString(CommonConstants.KEY_MESSAGE));
        Toast.makeText(application, sbA.toString(), 1).show();
    }

    public h3(d dVar) {
        this.g = dVar;
        dVar.k.f245a = SimulateLaunchActivity.entryUrlPrefix;
        this.f = SimulateLaunchActivity.entryType;
        this.c = SimulateLaunchActivity.entryQrParam;
        this.e = dVar.getDid();
        String str = (String) (dVar.a() ? null : dVar.p.a("resolution", (Object) null, (Class<Object>) String.class));
        if (n0.d(str)) {
            String[] strArrSplit = str.split("x");
            this.b = Integer.parseInt(strArrSplit[0]);
            this.f258a = Integer.parseInt(strArrSplit[1]);
        }
        PackageInfo packageInfoA = j4.a(dVar.n, dVar.n.getApplicationInfo().packageName, 0);
        this.d = packageInfoA != null ? packageInfoA.versionName : "1.0.0";
        dVar.D.debug(Collections.singletonList("SimulateLoginTask"), "Simulate task init success", new Object[0]);
    }
}
