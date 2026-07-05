package com.ss.android.downloadlib.addownload;

import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.download.api.config.IDownloadButtonClickListener;
import com.ss.android.download.api.download.DownloadModel;
import com.ss.android.downloadad.api.constant.AdBaseConstants;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.ss.android.socialbase.downloader.model.DownloadInfo;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import java.io.File;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class i {
    public static boolean a(int i) {
        return i == 0 || i == 1;
    }

    public static boolean b(int i) {
        return i == 2 || i == 1;
    }

    public static boolean a(DownloadModel downloadModel) {
        return downloadModel.isAd() && (downloadModel instanceof AdDownloadModel) && downloadModel.getModelType() == 1;
    }

    public static boolean b(DownloadModel downloadModel) {
        return downloadModel != null && downloadModel.getModelType() == 2;
    }

    public static boolean a(DownloadModel downloadModel, IDownloadButtonClickListener iDownloadButtonClickListener) {
        return downloadModel.isAd() && iDownloadButtonClickListener != null;
    }

    public static int a(com.ss.android.downloadlib.addownload.b.e eVar, boolean z, com.ss.android.socialbase.appdownloader.f fVar) {
        int iA;
        if (fVar == null || TextUtils.isEmpty(fVar.c()) || fVar.b() == null) {
            return 0;
        }
        try {
            iA = a(fVar, fVar.c());
        } catch (Throwable th) {
            k.u().a(th, "redirectSavePathIfPossible");
            iA = 4;
        }
        fVar.a(iA);
        if (iA == 0) {
            fVar.a(new com.ss.android.downloadlib.c.a());
        }
        if (!fVar.ac()) {
            fVar.a(new com.ss.android.downloadlib.c.b());
        }
        int iA2 = com.ss.android.socialbase.appdownloader.d.j().a(fVar);
        com.ss.android.downloadad.api.a.b bVarA = a(eVar, iA2);
        com.ss.android.downloadlib.addownload.b.f.a().a(bVarA);
        bVarA.g(iA2);
        bVarA.h(System.currentTimeMillis());
        bVarA.i(0L);
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(fVar.ag());
        if (!a(fVar, downloadSettingObtain, iA2) && eVar.b.isShowToast()) {
            String startToast = eVar.b.getStartToast();
            if (TextUtils.isEmpty(startToast)) {
                startToast = downloadSettingObtain.optString("download_start_toast_text");
            }
            if (TextUtils.isEmpty(startToast)) {
                startToast = z ? "已开始下载，可在\"我的\"里查看管理" : "已开始下载";
            }
            k.d().a(2, fVar.b(), eVar.b, startToast, null, 0);
        }
        return iA2;
    }

    private static com.ss.android.downloadad.api.a.b a(com.ss.android.downloadlib.addownload.b.e eVar, int i) {
        com.ss.android.downloadad.api.a.b bVar = new com.ss.android.downloadad.api.a.b(eVar.b, eVar.c, eVar.d, i);
        boolean z = true;
        if (DownloadSetting.obtain(i).optInt("download_event_opt", 1) > 1) {
            try {
                String packageName = eVar.b.getPackageName();
                if (!TextUtils.isEmpty(packageName)) {
                    if (k.a().getPackageManager().getPackageInfo(packageName, 0) == null) {
                        z = false;
                    }
                    bVar.h(z);
                }
            } catch (Throwable unused) {
            }
        }
        return bVar;
    }

    private static boolean a(com.ss.android.socialbase.appdownloader.f fVar, DownloadSetting downloadSetting, int i) {
        String strOptString;
        JSONArray jSONArrayOptJSONArray = downloadSetting.optJSONArray(DownloadSettingKeys.KEY_AH_PLANS);
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() != 0) {
            int length = jSONArrayOptJSONArray.length();
            JSONObject jSONObject = null;
            int i2 = 0;
            while (true) {
                if (i2 < length) {
                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                    if (jSONObjectOptJSONObject != null && ((strOptString = jSONObjectOptJSONObject.optString("type")) == "plan_c" || com.ss.android.socialbase.appdownloader.f.a.a(jSONObjectOptJSONObject))) {
                        switch (strOptString) {
                            case "plan_a":
                            case "plan_b":
                            case "plan_e":
                            case "plan_f":
                                if (com.ss.android.socialbase.appdownloader.b.a(jSONObjectOptJSONObject, downloadSetting).b != 0) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case "plan_g":
                                if (com.ss.android.socialbase.appdownloader.b.b(jSONObjectOptJSONObject, downloadSetting).b != 0) {
                                    break;
                                } else {
                                    break;
                                }
                                break;
                            case "plan_c":
                                jSONObject = jSONObjectOptJSONObject;
                                continue;
                                break;
                        }
                    }
                    i2++;
                }
            }
            if (jSONObject != null) {
                if (jSONObject.optInt(DownloadSettingKeys.AhPlans.KEY_ALLOW_UNKNOWN_SOURCE_ON_STARTUP) == 1) {
                    return com.ss.android.socialbase.appdownloader.b.a(DownloadComponentManager.getAppContext(), (Intent) null, jSONObject, i, new com.ss.android.socialbase.appdownloader.a());
                }
            }
        }
        return false;
    }

    public static String a(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return null;
        }
        try {
            String extra = downloadInfo.getExtra();
            if (!TextUtils.isEmpty(extra)) {
                return new JSONObject(extra).optString("notification_jump_url", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static int a(com.ss.android.socialbase.appdownloader.f fVar, String str) {
        DownloadSetting downloadSettingObtain = DownloadSetting.obtain(fVar.ag());
        JSONObject jSONObjectOptJSONObject = downloadSettingObtain.optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
        if (jSONObjectOptJSONObject == null || TextUtils.isEmpty(jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME))) {
            return -1;
        }
        String strD = fVar.d();
        String strN = fVar.N();
        if (TextUtils.isEmpty(strN)) {
            strN = com.ss.android.socialbase.appdownloader.c.a(str, strD, fVar.n(), true);
        }
        if (strN.length() > 255) {
            strN = strN.substring(strN.length() - 255);
        }
        if (TextUtils.isEmpty(strD)) {
            strD = strN;
        }
        String strE = fVar.e();
        if (TextUtils.isEmpty(strE)) {
            strE = com.ss.android.socialbase.appdownloader.c.b();
        }
        String str2 = strE + File.separator + com.ss.android.socialbase.appdownloader.c.a(strD, downloadSettingObtain);
        DownloadInfo downloadInfoA = com.ss.android.socialbase.appdownloader.d.j().a(fVar.b(), str);
        if (downloadInfoA != null && downloadInfoA.isSavePathRedirected()) {
            fVar.c(downloadInfoA.getSavePath());
            try {
                fVar.a(new JSONObject(downloadInfoA.getDownloadSettingString()));
                return 0;
            } catch (Throwable unused) {
                return 0;
            }
        }
        if (downloadInfoA != null || !"application/vnd.android.package-archive".equalsIgnoreCase(com.ss.android.socialbase.appdownloader.d.j().a(strN, fVar.n()))) {
            return downloadInfoA != null ? 8 : 9;
        }
        int iA = com.ss.android.socialbase.appdownloader.b.a(downloadSettingObtain);
        if (iA != 0) {
            return iA;
        }
        fVar.c(str2);
        return iA;
    }

    public static String c(DownloadModel downloadModel) {
        try {
            if (TextUtils.isEmpty(downloadModel.getLogExtra())) {
                return null;
            }
            return new JSONObject(downloadModel.getLogExtra()).optString("clickid");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String d(DownloadModel downloadModel) {
        try {
            if (TextUtils.isEmpty(downloadModel.getLogExtra())) {
                return null;
            }
            return new JSONObject(downloadModel.getLogExtra()).optString(AdBaseConstants.MARKET_OPEN_INTENT_EXTRA);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
