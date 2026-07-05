package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.openadsdk.mediation.MediationConstant;
import com.ss.android.socialbase.downloader.constants.DbJsonConstants;
import com.ss.android.socialbase.downloader.downloader.Downloader;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.io.File;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class d {
    public static a a(Context context, String str, JSONObject jSONObject, DownloadInfo downloadInfo) {
        if (downloadInfo == null || context == null || jSONObject == null) {
            return null;
        }
        String savePath = downloadInfo.getSavePath();
        if (TextUtils.isEmpty(savePath) || TextUtils.isEmpty(str)) {
            return null;
        }
        File file = new File(savePath);
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(downloadInfo);
        if (str.equals("v1")) {
            return new j(context, downloadSettingObtain, downloadInfo.getTargetFilePath());
        }
        if (str.equals("v2")) {
            return new k(context, downloadSettingObtain, file.getAbsolutePath());
        }
        if (str.equals("v3")) {
            return new l(context, downloadSettingObtain, file.getAbsolutePath());
        }
        if (str.equals("o1")) {
            return new g(context, downloadSettingObtain, file.getAbsolutePath());
        }
        if (str.equals("o2")) {
            return new h(context, downloadSettingObtain, file.getAbsolutePath());
        }
        if (str.equals("o3")) {
            String dBJsonString = downloadInfo.getDBJsonString(DbJsonConstants.CONTENT_URI);
            if (TextUtils.isEmpty(dBJsonString)) {
                return null;
            }
            return new i(context, downloadSettingObtain, file.getAbsolutePath(), dBJsonString, downloadInfo.getName());
        }
        if (str.equals(MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM)) {
            return new c(context, downloadSettingObtain, file.getAbsolutePath(), jSONObject);
        }
        if (!str.equals("vbi")) {
            return null;
        }
        return new m(context, downloadSettingObtain, com.ss.android.socialbase.appdownloader.c.a(downloadInfo.getId(), Downloader.getInstance(context).getDownloadFileUriProvider(downloadInfo.getId()), context, com.ss.android.socialbase.appdownloader.d.j().d(), new File(downloadInfo.getSavePath() + File.separator + downloadInfo.getName())).toString());
    }

    public static boolean a(Context context, String str, JSONObject jSONObject, DownloadSetting downloadSetting) {
        if (context == null || str == null) {
            return false;
        }
        a mVar = null;
        String strB = com.ss.android.socialbase.appdownloader.c.b();
        if (TextUtils.isEmpty(strB) || TextUtils.isEmpty(str)) {
            return false;
        }
        if (com.ss.android.socialbase.appdownloader.f.e.d() && str.equals("v1")) {
            mVar = new j(context, downloadSetting, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.d() && str.equals("v2")) {
            mVar = new k(context, downloadSetting, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.d() && str.equals("v3")) {
            mVar = new l(context, downloadSetting, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.e() && str.equals("o1")) {
            mVar = new g(context, downloadSetting, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.e() && str.equals("o2")) {
            mVar = new h(context, downloadSetting, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.e() && str.equals("o3")) {
            mVar = new i(context, downloadSetting, strB, strB, strB);
        } else if (com.ss.android.socialbase.appdownloader.f.e.d() && str.equals(MediationConstant.KEY_USE_POLICY_OBJ_CUSTOM)) {
            mVar = new c(context, downloadSetting, strB, jSONObject);
        } else if (com.ss.android.socialbase.appdownloader.f.e.d() && str.equals("vbi")) {
            mVar = new m(context, downloadSetting, strB);
        }
        return mVar != null && mVar.a();
    }
}
