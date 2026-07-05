package com.ss.android.downloadlib.addownload.a;

import android.text.TextUtils;
import com.ss.android.downloadlib.addownload.k;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
class b {
    b() {
    }

    CopyOnWriteArrayList<com.ss.android.downloadlib.addownload.b.a> a(String str, String str2) {
        CopyOnWriteArrayList<com.ss.android.downloadlib.addownload.b.a> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        try {
            String string = k.a().getSharedPreferences(str, 0).getString(str2, "");
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    com.ss.android.downloadlib.addownload.b.a aVarA = com.ss.android.downloadlib.addownload.b.a.a(jSONObject.optJSONObject(itKeys.next()));
                    if (aVarA != null) {
                        copyOnWriteArrayList.add(aVarA);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyOnWriteArrayList;
    }

    void a(String str, String str2, CopyOnWriteArrayList<com.ss.android.downloadlib.addownload.b.a> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            for (com.ss.android.downloadlib.addownload.b.a aVar : copyOnWriteArrayList) {
                if (aVar != null) {
                    jSONObject.put(String.valueOf(aVar.b), aVar.a());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        k.a().getSharedPreferences(str, 0).edit().putString(str2, jSONObject.toString()).apply();
    }

    void b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        k.a().getSharedPreferences(str, 0).edit().putString(str2, "").apply();
    }
}
