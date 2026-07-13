package com.mobile.auth.h;

import android.text.TextUtils;
import android.util.Log;
import com.lzy.okgo.model.HttpHeaders;
import com.mobile.auth.j.d;
import com.mobile.auth.j.g;
import com.mobile.auth.k.c;
import com.mobile.auth.m.q;
import com.tencent.connect.common.Constants;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class a {
    private String a;
    private String b;

    private c a(String str, String str2, String str3, g gVar) {
        c cVar = new c(str, gVar, str3, str2);
        if (str3.equals(Constants.HTTP_GET)) {
            cVar.a(HttpHeaders.HEAD_KEY_CONTENT_TYPE, "application/x-www-form-urlencoded");
        }
        return cVar;
    }

    public c a(c cVar, com.mobile.auth.l.b bVar, com.cmic.sso.sdk.a aVar) {
        List<String> list;
        Map<String, List<String>> mapB = bVar.b();
        if (TextUtils.isEmpty(this.a) && (list = mapB.get("pplocation")) != null && list.size() > 0) {
            this.a = list.get(0);
        }
        q.b(aVar, String.valueOf(bVar.a()));
        List<String> list2 = mapB.get(HttpHeaders.HEAD_KEY_LOCATION);
        if (list2 == null || list2.isEmpty()) {
            list2 = mapB.get(HttpHeaders.HEAD_KEY_LOCATION.toLowerCase());
        }
        if (list2 != null && list2.size() > 0) {
            String str = list2.get(0);
            this.b = str;
            if (!TextUtils.isEmpty(str)) {
                String strB = aVar.b("operatortype", "0");
                q.a(aVar, "2".equals(strB) ? "getUnicomMobile" : "3".equals(strB) ? "getTelecomMobile" : "NONE");
            }
        }
        Log.d(HttpHeaders.HEAD_KEY_LOCATION, this.b);
        c cVarA = a(this.b, cVar.f(), Constants.HTTP_GET, new com.mobile.auth.j.c(cVar.k().a()));
        cVarA.a(cVar.h());
        return cVarA;
    }

    public String a() {
        return this.a;
    }

    public c b(c cVar, com.mobile.auth.l.b bVar, com.cmic.sso.sdk.a aVar) {
        String strB = aVar.b("operatortype", "0");
        q.a(aVar, "2".equals(strB) ? "getNewUnicomPhoneNumberNotify" : "3".equals(strB) ? "getNewTelecomPhoneNumberNotify" : "NONE");
        q.b(aVar, String.valueOf(bVar.a()));
        d dVar = new d(cVar.k().a(), "1.0", bVar.c());
        dVar.c(aVar.b("userCapaid"));
        dVar.b(aVar.c("logintype") != 3 ? "authz" : "pre");
        c cVarA = a(this.a, cVar.f(), Constants.HTTP_POST, dVar);
        cVarA.a(cVar.h());
        this.a = null;
        return cVarA;
    }
}
