package com.duowan.live.one.module.report;

import android.content.Context;
import android.content.SharedPreferences;
import com.duowan.auk.ArkValue;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes.dex */
public class HuyaStatisAgent {
    private static final String PREF_NAME = "huyastatispref";
    private static HuyaStatisAgent instance = new HuyaStatisAgent();
    private HuyaStatisApi mApi = new HuyaStatisApi();
    private boolean mIsStart = false;
    private String mPrePage = null;
    private Long mOnResumeTime = null;

    private HuyaStatisAgent() {
    }

    public static HuyaStatisAgent getInstance() {
        return instance;
    }

    public void init(Context context, String str, String str2, String str3, String str4, String str5) {
        this.mApi.init(context, str, str2, str3, str4, str5);
    }

    private void reportInstall() {
        SharedPreferences sharedPreferences = ArkValue.gContext.getSharedPreferences(PREF_NAME, 0);
        int i = sharedPreferences.getInt("reportVer", -1);
        int versionNo = Util.getVersionNo(ArkValue.gContext);
        if (i == -1 || i != versionNo) {
            this.mApi.install();
            sharedPreferences.edit().putInt("reportVer", versionNo).commit();
        }
    }

    private void reportInstallApps() {
        SharedPreferences sharedPreferences = ArkValue.gContext.getSharedPreferences(PREF_NAME, 0);
        String string = sharedPreferences.getString("reportAppsDate", null);
        String str = Util.format(System.currentTimeMillis(), "yyyyMMdd");
        if (string == null || !string.equals(str)) {
            this.mApi.installApps();
            sharedPreferences.edit().putString("reportAppsDate", str).commit();
        }
    }

    public HuyaStatisApi getHuyaStatisApi() {
        return this.mApi;
    }

    public boolean isStart() {
        return this.mIsStart;
    }
}
