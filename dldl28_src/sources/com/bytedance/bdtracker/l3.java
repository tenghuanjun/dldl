package com.bytedance.bdtracker;

import java.util.ArrayList;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class l3 extends q3 {
    public ArrayList<String> A;
    public ArrayList<String> B;
    public int C;
    public int D;
    public int E;
    public int F;
    public String v;
    public String w;
    public String x;
    public String y;
    public String z;

    public l3(String str, String str2, String str3, String str4, String str5, int i, int i2, int i3, int i4, ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        super(null, "bav2b_click", true, null);
        this.v = str;
        this.w = str2;
        this.x = str3;
        this.y = str4;
        this.z = str5;
        this.A = arrayList;
        this.B = arrayList2;
        this.C = i;
        this.D = i2;
        this.E = i3;
        this.F = i4;
    }

    @Override // com.bytedance.bdtracker.q3
    public void k() throws JSONException {
        if (this.s == null) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("element_path", this.x);
            jSONObject.put("page_key", this.v);
            ArrayList<String> arrayList = this.B;
            if (arrayList != null && arrayList.size() > 0) {
                jSONObject.put("positions", new JSONArray((Collection) this.B));
            }
            ArrayList<String> arrayList2 = this.A;
            if (arrayList2 != null && arrayList2.size() > 0) {
                jSONObject.put("texts", new JSONArray((Collection) this.A));
            }
            jSONObject.put("element_width", this.C);
            jSONObject.put("element_height", this.D);
            jSONObject.put("touch_x", this.E);
            jSONObject.put("touch_y", this.F);
            jSONObject.put("page_title", this.w);
            jSONObject.put("element_id", this.y);
            jSONObject.put("element_type", this.z);
            this.s = jSONObject.toString();
        }
    }
}
