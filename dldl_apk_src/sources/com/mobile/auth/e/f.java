package com.mobile.auth.e;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.SystemClock;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.aliyun.aliyunface.api.ZIMFacade;
import com.mobile.auth.k.l;
import com.mobile.auth.k.p;
import com.mobile.auth.k.q;
import com.mobile.auth.k.t;
import com.tencent.open.SocialConstants;
import com.unionpay.tsmservice.mini.data.Constant;
import java.util.UUID;
import layaair.game.conch.LayaConch5;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes2.dex */
public class f {

    @SuppressLint({"StaticFieldLeak"})
    private static f a;
    private com.mobile.auth.i.a b;
    private Context c;

    class a implements com.mobile.auth.i.b {
        final /* synthetic */ com.cmic.sso.sdk.a a;
        final /* synthetic */ g b;

        a(com.cmic.sso.sdk.a aVar, g gVar) {
            this.a = aVar;
            this.b = gVar;
        }

        /* JADX WARN: Removed duplicated region for block: B:39:0x0140  */
        /* JADX WARN: Removed duplicated region for block: B:45:0x017b  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(java.lang.String r9, java.lang.String r10, org.json.JSONObject r11) {
            /*
                Method dump skipped, instruction units count: 518
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.mobile.auth.e.f.a.a(java.lang.String, java.lang.String, org.json.JSONObject):void");
        }
    }

    class b implements com.mobile.auth.i.b {
        final /* synthetic */ com.cmic.sso.sdk.a a;
        final /* synthetic */ long b;
        final /* synthetic */ g c;

        b(com.cmic.sso.sdk.a aVar, long j, g gVar) {
            this.a = aVar;
            this.b = j;
            this.c = gVar;
        }

        public void a(String str, String str2, JSONObject jSONObject) {
            com.mobile.auth.k.f.c("AuthBusiness", "获取平台token 》》》》" + jSONObject.toString());
            String strA = this.a.a("interfacecode", "");
            this.a.b("interfacecode", strA + str + i.b);
            if (str.equals("103000")) {
                String strOptString = jSONObject.optString("phonescrip");
                this.a.b("phonescrip", strOptString);
                f.this.a(jSONObject, strOptString, this.a);
                this.a.b("openId", jSONObject.optString("openId"));
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() - this.b;
            String strA2 = this.a.a("interfaceelasped", "");
            this.a.b("interfaceelasped", strA2 + jElapsedRealtime + i.b);
            this.c.a(str, str2, this.a, jSONObject);
        }
    }

    private f(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.c = applicationContext;
        this.b = com.mobile.auth.i.a.a(applicationContext);
    }

    public static f a(Context context) {
        if (a == null) {
            synchronized (f.class) {
                if (a == null) {
                    a = new f(context);
                }
            }
        }
        return a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(JSONObject jSONObject, String str, com.cmic.sso.sdk.a aVar) {
        l.a(this.c, str, jSONObject.optLong("phonescripED"), aVar.a(aVar.a("keyIsSimKeyICCID", false) ? "iccid" : "imsi", ""));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(com.cmic.sso.sdk.a aVar, g gVar) {
        aVar.b("request_start_time", SystemClock.elapsedRealtime());
        this.b.a(this.c, aVar, new a(aVar, gVar));
    }

    public void a(com.cmic.sso.sdk.a aVar, g gVar) {
        p.a("tokentimes", System.currentTimeMillis());
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        com.mobile.auth.k.f.c("AuthBusiness", "获取平台token》》》》");
        l.a(true);
        if (aVar.a(LayaConch5.MARKET_LOGINTYPE) == 1) {
            aVar.b("userCapaid", "200");
        }
        this.b.a(aVar, new b(aVar, jElapsedRealtime, gVar));
    }

    public void a(com.cmic.sso.sdk.a aVar, String str, g gVar) {
        String str2;
        com.mobile.auth.k.f.c("AuthBusiness", "进行取号查询》》》》authtype=" + str);
        String packageName = this.c.getPackageName();
        String strA = com.mobile.auth.k.h.a(q.a(this.c));
        aVar.b("apppackage", packageName);
        aVar.b("appsign", strA);
        boolean z = false;
        aVar.b(com.cmic.sso.sdk.b.a, UUID.randomUUID().toString().substring(0, 16));
        aVar.b("networkType", t.a(this.c));
        aVar.b("authtype", "3");
        String strC = aVar.c("operatorType");
        if (aVar.a().n() || !("1".equals(strC) || "0".equals(strC))) {
            str2 = "use_http_get_phone_scrip";
            z = true;
        } else {
            str2 = "use_http_get_phone_scrip";
        }
        aVar.b(str2, z);
        aVar.b("degrade", com.igexin.push.a.i);
        b(aVar, gVar);
    }

    void a(String str, com.cmic.sso.sdk.a aVar, g gVar) {
        int iA = aVar.a(LayaConch5.MARKET_LOGINTYPE, 0);
        if (!aVar.a("isCacheScrip", false)) {
            a(aVar, str, gVar);
            return;
        }
        if (iA == 3) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(Constant.KEY_RESULT_CODE, "103000");
                jSONObject.put(SocialConstants.PARAM_APP_DESC, ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            gVar.a("103000", ZIMFacade.ZIM_EXT_PARAMS_VAL_USE_VIDEO_TRUE, aVar, jSONObject);
            return;
        }
        String strA = l.a(this.c);
        if (TextUtils.isEmpty(strA)) {
            aVar.b("isCacheScrip", false);
            if (aVar.a("networkType") == 2) {
                gVar.a("200027", "无数据网络", aVar, null);
                return;
            } else {
                a(aVar, str, gVar);
                return;
            }
        }
        aVar.b("sourceid", p.b("sourceid", ""));
        aVar.b("phonescrip", strA);
        if (1 != iA) {
            a(aVar, gVar);
        } else {
            aVar.b("securityphone", p.b("securityphone", ""));
            gVar.a("103000", "显示登录取号成功", aVar, null);
        }
    }
}
