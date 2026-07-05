package com.mobile.auth.i;

import android.text.TextUtils;
import android.util.Log;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.mobile.auth.k.d;
import com.mobile.auth.k.f;
import com.mobile.auth.k.g;
import com.mobile.auth.k.i;
import com.mobile.auth.l.c;
import com.mobile.auth.n.q;
import com.tencent.open.SocialConstants;
import com.unionpay.tsmservice.mini.data.Constant;
import java.util.List;
import java.util.Map;
import layaair.game.conch.LayaConch5;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes6.dex */
public class a {
    private String a;
    private String b;

    /* JADX INFO: renamed from: com.mobile.auth.i.a$a, reason: collision with other inner class name */
    class C0077a implements d.b {
        final /* synthetic */ String a;
        final /* synthetic */ com.cmic.sso.sdk.a b;
        final /* synthetic */ b c;
        private boolean d = false;

        C0077a(a aVar, String str, com.cmic.sso.sdk.a aVar2, b bVar) {
            this.a = str;
            this.b = aVar2;
            this.c = bVar;
        }

        @Override // com.mobile.auth.k.d.b
        public void a(String str, String str2) {
            if (this.d) {
                return;
            }
            this.d = true;
            f.a("BaseRequest", "request success , url : " + this.a + ">>>>result : " + str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                String string = jSONObject.getString(jSONObject.has(Constant.KEY_RESULT_CODE) ? Constant.KEY_RESULT_CODE : "resultcode");
                if (!i.a(this.b.c("traceId")) || this.a.contains("Config")) {
                    this.c.a(string, jSONObject.optString(SocialConstants.PARAM_APP_DESC), jSONObject);
                }
            } catch (Exception e) {
                e.printStackTrace();
                a("200021", "数据解析异常", str2);
            }
        }

        @Override // com.mobile.auth.k.d.b
        public void a(String str, String str2, String str3) {
            if (this.d) {
                return;
            }
            this.d = true;
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constant.KEY_RESULT_CODE, str);
                jSONObject.put(SocialConstants.PARAM_APP_DESC, str2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            f.a("BaseRequest", "request failed , url : " + this.a + ">>>>>errorMsg : " + jSONObject.toString());
            if (this.c != null) {
                if (!i.a(this.b.c("traceId")) || this.a.contains("Config")) {
                    this.c.a(str, str2, jSONObject);
                }
            }
        }
    }

    private c a(String str, String str2, String str3, g gVar) {
        c cVar = new c(str, gVar, str3, str2);
        if (str3.equals("GET")) {
            cVar.a("Content-Type", "application/x-www-form-urlencoded");
        }
        return cVar;
    }

    public c a(c cVar, com.mobile.auth.m.b bVar, com.cmic.sso.sdk.a aVar) {
        List<String> list;
        Map<String, List<String>> mapB = bVar.b();
        if (TextUtils.isEmpty(this.a) && (list = mapB.get("pplocation")) != null && list.size() > 0) {
            this.a = list.get(0);
        }
        q.b(aVar, String.valueOf(bVar.a()));
        List<String> list2 = mapB.get(HttpHeaders.LOCATION);
        if (list2 == null || list2.isEmpty()) {
            list2 = mapB.get(HttpHeaders.LOCATION.toLowerCase());
        }
        if (list2 != null && list2.size() > 0) {
            String str = list2.get(0);
            this.b = str;
            if (!TextUtils.isEmpty(str)) {
                String strB = aVar.b("operatortype", "0");
                q.a(aVar, "2".equals(strB) ? "getUnicomMobile" : "3".equals(strB) ? "getTelecomMobile" : "NONE");
            }
        }
        Log.d(HttpHeaders.LOCATION, this.b);
        c cVarA = a(this.b, cVar.f(), "GET", new com.mobile.auth.k.c(cVar.k().a()));
        cVarA.a(cVar.h());
        return cVarA;
    }

    public String a() {
        return this.a;
    }

    public c b(c cVar, com.mobile.auth.m.b bVar, com.cmic.sso.sdk.a aVar) {
        String strB = aVar.b("operatortype", "0");
        q.a(aVar, "2".equals(strB) ? "getNewUnicomPhoneNumberNotify" : "3".equals(strB) ? "getNewTelecomPhoneNumberNotify" : "NONE");
        q.b(aVar, String.valueOf(bVar.a()));
        d dVar = new d(cVar.k().a(), "1.0", bVar.c());
        dVar.c(aVar.b("userCapaid"));
        dVar.b(aVar.c(LayaConch5.MARKET_LOGINTYPE) != 3 ? "authz" : "pre");
        c cVarA = a(this.a, cVar.f(), "POST", dVar);
        cVarA.a(cVar.h());
        this.a = null;
        return cVarA;
    }
}
