package com.sy37sdk.account.floatview.data;

import android.text.TextUtils;
import java.util.ArrayList;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class RedDot {
    private int num;
    private int redDotTpe;
    private String title;

    public int getRedDotTpe() {
        return this.redDotTpe;
    }

    public void setRedDotTpe(int i) {
        this.redDotTpe = i;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getNum() {
        return this.num;
    }

    public void setNum(int i) {
        this.num = i;
    }

    public boolean needShowNum() {
        return this.redDotTpe == 1;
    }

    public String toString() {
        return "RedDot{redDotTpe=" + this.redDotTpe + ", title='" + this.title + "', num=" + this.num + AbstractJsonLexerKt.END_OBJ;
    }

    public static ArrayList<RedDot> getRedDots(String str) {
        ArrayList<RedDot> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    arrayList.add(parse(jSONArray.optString(i)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static RedDot parse(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            int iOptInt = jSONObject.optInt("red_dot_type");
            String strOptString = jSONObject.optString("title");
            int iOptInt2 = jSONObject.optInt("num");
            RedDot redDot = new RedDot();
            redDot.setNum(iOptInt2);
            redDot.setRedDotTpe(iOptInt);
            redDot.setTitle(strOptString);
            return redDot;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String objectToJson(RedDot redDot) {
        if (redDot == null) {
            return "";
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("red_dot_type", redDot.getRedDotTpe());
            jSONObject.put("title", redDot.getTitle());
            jSONObject.put("num", redDot.getNum());
            return jSONObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
