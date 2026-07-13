package com.mobile.auth.j;

import com.nirvana.tools.logger.cache.db.DBHelpTool;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class h extends a {
    protected String x = "";
    protected String y = "";

    @Override // com.mobile.auth.j.g
    protected String a(String str) {
        return this.b + this.c + this.d + this.e + this.f + this.g + this.h + this.i + this.j + this.m + this.n + str + this.o + this.q + this.r + this.s + this.t + this.u + this.v + this.x + this.y + this.w;
    }

    @Override // com.mobile.auth.j.a
    public void a_(String str) {
        this.v = t(str);
    }

    @Override // com.mobile.auth.j.g
    public JSONObject b() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("ver", this.a);
            jSONObject.put("sdkver", this.b);
            jSONObject.put("appid", this.c);
            jSONObject.put("imsi", this.d);
            jSONObject.put("operatortype", this.e);
            jSONObject.put("networktype", this.f);
            jSONObject.put("mobilebrand", this.g);
            jSONObject.put("mobilemodel", this.h);
            jSONObject.put("mobilesystem", this.i);
            jSONObject.put("clienttype", this.j);
            jSONObject.put("interfacever", this.k);
            jSONObject.put("expandparams", this.l);
            jSONObject.put("msgid", this.m);
            jSONObject.put(DBHelpTool.RecordEntry.COLUMN_NAME_TIMESTAMP, this.n);
            jSONObject.put("subimsi", this.o);
            jSONObject.put("sign", this.p);
            jSONObject.put("apppackage", this.q);
            jSONObject.put("appsign", this.r);
            jSONObject.put("ipv4_list", this.s);
            jSONObject.put("ipv6_list", this.t);
            jSONObject.put("sdkType", this.u);
            jSONObject.put("tempPDR", this.v);
            jSONObject.put("scrip", this.x);
            jSONObject.put("userCapaid", this.y);
            jSONObject.put("funcType", this.w);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String toString() {
        return this.a + "&" + this.b + "&" + this.c + "&" + this.d + "&" + this.e + "&" + this.f + "&" + this.g + "&" + this.h + "&" + this.i + "&" + this.j + "&" + this.k + "&" + this.l + "&" + this.m + "&" + this.n + "&" + this.o + "&" + this.p + "&" + this.q + "&" + this.r + "&&" + this.s + "&" + this.t + "&" + this.u + "&" + this.v + "&" + this.x + "&" + this.y + "&" + this.w;
    }

    public void v(String str) {
        this.x = t(str);
    }

    public void w(String str) {
        this.y = t(str);
    }
}
