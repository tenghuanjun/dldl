package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import com.ss.android.socialbase.downloader.setting.DownloadSettingKeys;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class i extends a {
    private String d;
    private String e;

    public i(Context context, DownloadSetting downloadSetting, String str, String str2, String str3) {
        super(context, downloadSetting, str);
        this.d = str2;
        this.e = str3;
    }

    @Override // com.ss.android.socialbase.appdownloader.a.e
    public Intent b() {
        String str;
        String strOptString = this.b.optString(com.igexin.push.core.d.c.d);
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("ak"), strOptString);
        String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("am"), strOptString);
        String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString(com.alipay.sdk.sys.a.i), strOptString);
        String strSubstring = null;
        if (!TextUtils.isEmpty(strA3) && strA3.split(com.igexin.push.core.b.aj).length == 2) {
            String[] strArrSplit = strA3.split(com.igexin.push.core.b.aj);
            String strA4 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("al"), strOptString);
            String strA5 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("ao"), strOptString);
            if (!TextUtils.isEmpty(strA5) && strA5.split(com.igexin.push.core.b.aj).length == 2) {
                String[] strArrSplit2 = strA5.split(com.igexin.push.core.b.aj);
                JSONObject jSONObjectOptJSONObject = this.b.optJSONObject(DownloadSettingKeys.KEY_ANTI_HIJACK_DIR);
                if (jSONObjectOptJSONObject != null) {
                    String strOptString2 = jSONObjectOptJSONObject.optString(DownloadSettingKeys.AntiHijackDir.KEY_ANTI_HIJACK_DIR_NAME);
                    if (!TextUtils.isEmpty(strOptString2) && strOptString2.contains("%s")) {
                        try {
                            str = String.format(strOptString2, this.e);
                        } catch (Throwable unused) {
                            str = this.e;
                        }
                    } else {
                        str = this.e;
                    }
                    strSubstring = str;
                    if (strSubstring.length() > 255) {
                        strSubstring = strA4.substring(strSubstring.length() - 255);
                    }
                }
                Intent intent = new Intent(strA);
                intent.putExtra(strArrSplit2[0], strArrSplit2[1]);
                intent.putExtra(strA2, this.d);
                intent.putExtra(strA4, strSubstring);
                intent.putExtra(strArrSplit[0], Integer.parseInt(strArrSplit[1]));
                intent.addFlags(268468224);
                return intent;
            }
        }
        return null;
    }
}
