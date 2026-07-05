package com.ss.android.socialbase.appdownloader.a;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.ss.android.socialbase.downloader.setting.DownloadSetting;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class m extends a {
    public m(Context context, DownloadSetting downloadSetting, String str) {
        super(context, downloadSetting, str);
    }

    @Override // com.ss.android.socialbase.appdownloader.a.e
    public Intent b() {
        String strOptString = this.b.optString(com.igexin.push.core.d.c.d);
        String strA = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("bb"), strOptString);
        if (!TextUtils.isEmpty(strA) && strA.split(com.igexin.push.core.b.aj).length == 2) {
            String strA2 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("bc"), strOptString);
            if (!TextUtils.isEmpty(strA2) && strA2.split(com.igexin.push.core.b.aj).length == 2) {
                String[] strArrSplit = strA.split(com.igexin.push.core.b.aj);
                String[] strArrSplit2 = strA2.split(com.igexin.push.core.b.aj);
                String strA3 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("bd"), strOptString);
                String strA4 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("be"), strOptString);
                String strA5 = com.ss.android.socialbase.appdownloader.f.c.a(this.b.optString("bf"), strOptString);
                HashMap map = new HashMap();
                map.put(strArrSplit[0], strArrSplit[1]);
                map.put(strArrSplit2[0], strArrSplit2[1]);
                map.put(strA3, this.c);
                Intent intent = new Intent();
                intent.setAction(strA5);
                intent.setData(Uri.parse(strA4 + a(map)));
                intent.addFlags(268468224);
                return intent;
            }
        }
        return null;
    }

    public static String a(Map<String, String> map) {
        if (map == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuffer.append(entry.getKey());
            stringBuffer.append("=");
            stringBuffer.append(URLEncoder.encode(entry.getValue()));
            stringBuffer.append(com.alipay.sdk.sys.a.b);
        }
        String string = stringBuffer.toString();
        return string.endsWith(com.alipay.sdk.sys.a.b) ? string.substring(0, string.length() - 1) : string;
    }
}
