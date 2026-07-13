package com.bytedance.bdtracker;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.applog.InitConfig;
import com.bytedance.applog.convert.BuildConfig;
import com.tencent.connect.common.Constants;
import io.reactivex.rxjava3.annotations.SchedulerSupport;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class h1 extends d1 {
    public final Context e;
    public final d f;
    public final i1 g;

    public h1(d dVar, Context context, i1 i1Var) {
        super(false, false);
        this.f = dVar;
        this.e = context;
        this.g = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Config";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        jSONObject.put(Constants.JumpUrlConstants.URL_KEY_SDK_VERSION, BuildConfig.VERSION_CODE);
        jSONObject.put("sdk_version_code", 16139989);
        jSONObject.put("sdk_version_name", BuildConfig.VERSION_NAME);
        jSONObject.put("channel", this.g.b());
        jSONObject.put("not_request_sender", this.g.c.getNotReuqestSender() ? 1 : 0);
        k1.a(jSONObject, "aid", this.g.c.getAid());
        k1.a(jSONObject, "release_build", this.g.c.getReleaseBuild());
        k1.a(jSONObject, "user_agent", this.g.f.getString("user_agent", null));
        k1.a(jSONObject, "ab_sdk_version", this.g.d.getString("ab_sdk_version", ""));
        InitConfig initConfig = this.g.c;
        if (initConfig == null || initConfig.isGaidEnabled()) {
            String googleAid = this.g.c.getGoogleAid();
            if (TextUtils.isEmpty(googleAid)) {
                googleAid = f4.a(this.e, this.g);
            }
            k1.a(jSONObject, "google_aid", googleAid);
        }
        String language = this.g.c.getLanguage();
        if (TextUtils.isEmpty(language)) {
            language = this.g.f.getString("app_language", null);
        }
        k1.a(jSONObject, "app_language", language);
        String region = this.g.c.getRegion();
        if (TextUtils.isEmpty(region)) {
            region = this.g.f.getString("app_region", null);
        }
        k1.a(jSONObject, "app_region", region);
        String string = this.g.d.getString("app_track", null);
        if (!TextUtils.isEmpty(string)) {
            try {
                jSONObject.put("app_track", new JSONObject(string));
            } catch (Throwable th) {
                this.f.D.error("JSON handle appTrack failed", th, new Object[0]);
            }
        }
        String string2 = this.g.d.getString("header_custom_info", null);
        if (string2 != null && string2.length() > 0) {
            try {
                JSONObject jSONObject2 = new JSONObject(string2);
                jSONObject2.remove("_debug_flag");
                jSONObject.put(SchedulerSupport.CUSTOM, jSONObject2);
            } catch (Throwable th2) {
                this.f.D.error("JSON handle failed", th2, new Object[0]);
            }
        }
        String strG = this.g.g();
        if (TextUtils.isEmpty(strG)) {
            return true;
        }
        k1.a(jSONObject, "user_unique_id", strG);
        return true;
    }
}
