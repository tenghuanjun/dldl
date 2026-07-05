package com.sq.oaid.sq_oaid;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class IdsBean {
    public String aaid;
    public String oaid;
    public String vaid;

    public IdsBean(String aaid, String oaid, String vaid) {
        this.aaid = aaid;
        this.oaid = oaid;
        this.vaid = vaid;
    }

    public String getAaid() {
        return this.aaid;
    }

    public String getOaid() {
        return this.oaid;
    }

    public String getVaid() {
        return this.vaid;
    }

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("aaid", this.aaid);
            jSONObject.put("oaid", this.oaid);
            jSONObject.put("vaid", this.vaid);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject.toString();
    }
}
