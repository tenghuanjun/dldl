package com.sy37sdk.order.nat.trade;

import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class Wallet {
    private int ub;
    private int up;

    public int getUb() {
        return this.ub;
    }

    public int getUp() {
        return this.up;
    }

    public String toString() {
        return "ub: " + this.ub + ", up: " + this.up;
    }

    public static Wallet fromJson(String str) throws JSONException {
        Wallet wallet = new Wallet();
        JSONObject jSONObject = new JSONObject(str);
        wallet.ub = jSONObject.optInt("ub");
        wallet.up = jSONObject.optInt("up");
        return wallet;
    }
}
