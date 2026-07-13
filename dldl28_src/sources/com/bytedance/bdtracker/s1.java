package com.bytedance.bdtracker;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class s1 extends d1 {
    public final Context e;
    public final d f;
    public final i1 g;

    public s1(d dVar, Context context, i1 i1Var) {
        super(false, false);
        this.f = dVar;
        this.e = context;
        this.g = i1Var;
    }

    @Override // com.bytedance.bdtracker.d1
    public String a() {
        return "Package";
    }

    @Override // com.bytedance.bdtracker.d1
    public boolean a(JSONObject jSONObject) throws JSONException {
        ApplicationInfo applicationInfo;
        String packageName = this.e.getPackageName();
        if (TextUtils.isEmpty(this.g.c.getZiJieCloudPkg())) {
            jSONObject.put("package", packageName);
        } else {
            this.f.D.debug("has zijie pkg", new Object[0]);
            jSONObject.put("package", this.g.c.getZiJieCloudPkg());
            jSONObject.put("real_package_name", packageName);
        }
        try {
            PackageInfo packageInfoA = j4.a(this.e, packageName, 0);
            int manifestVersionCode = packageInfoA != null ? packageInfoA.versionCode : 0;
            jSONObject.put("app_version", !TextUtils.isEmpty(this.g.c.getVersion()) ? this.g.c.getVersion() : packageInfoA != null ? packageInfoA.versionName : "");
            if (TextUtils.isEmpty(this.g.c.getVersionMinor())) {
                jSONObject.put("app_version_minor", "");
            } else {
                jSONObject.put("app_version_minor", this.g.c.getVersionMinor());
            }
            if (this.g.c.getVersionCode() != 0) {
                jSONObject.put("version_code", this.g.c.getVersionCode());
            } else {
                jSONObject.put("version_code", manifestVersionCode);
            }
            if (this.g.c.getUpdateVersionCode() != 0) {
                jSONObject.put("update_version_code", this.g.c.getUpdateVersionCode());
            } else {
                jSONObject.put("update_version_code", manifestVersionCode);
            }
            if (this.g.c.getManifestVersionCode() != 0) {
                manifestVersionCode = this.g.c.getManifestVersionCode();
            }
            jSONObject.put("manifest_version_code", manifestVersionCode);
            if (!TextUtils.isEmpty(this.g.c.getAppName())) {
                jSONObject.put("app_name", this.g.c.getAppName());
            }
            if (!TextUtils.isEmpty(this.g.c.getTweakedChannel())) {
                jSONObject.put("tweaked_channel", this.g.c.getTweakedChannel());
            }
            if (packageInfoA == null || (applicationInfo = packageInfoA.applicationInfo) == null) {
                return true;
            }
            int i = applicationInfo.labelRes;
            if (i <= 0) {
                return true;
            }
            try {
                jSONObject.put("display_name", this.e.getString(i));
                return true;
            } catch (Throwable unused) {
                return true;
            }
        } catch (Throwable th) {
            this.f.D.error("Load package info failed.", th, new Object[0]);
            return false;
        }
    }
}
