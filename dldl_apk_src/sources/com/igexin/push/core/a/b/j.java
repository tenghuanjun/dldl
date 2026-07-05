package com.igexin.push.core.a.b;

import com.igexin.push.core.m;
import com.ss.android.socialbase.downloader.constants.MonitorConstants;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes7.dex */
public final class j extends a {
    private static final String a = com.igexin.push.config.c.a + "_SetTagResultAction";

    @Override // com.igexin.push.core.a.b.a
    public final boolean a(Object obj, JSONObject jSONObject) {
        com.igexin.b.a.c.a.a(a + "|set tag result resp data = " + jSONObject, new Object[0]);
        try {
            if (!jSONObject.has("action") || !jSONObject.getString("action").equals("settag_result")) {
                return true;
            }
            m.a().a(jSONObject.getString("sn"), jSONObject.getString(MonitorConstants.EXTRA_DOWNLOAD_ERROR_CODE));
            return true;
        } catch (Exception e) {
            com.igexin.b.a.c.a.a(a + "|" + e.toString(), new Object[0]);
            return true;
        }
    }
}
