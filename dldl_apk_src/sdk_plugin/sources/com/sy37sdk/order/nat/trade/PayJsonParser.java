package com.sy37sdk.order.nat.trade;

import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONObject;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class PayJsonParser {
    private static HashMap<String, String> wayChineseMap;
    private static HashMap<String, String> wayMap;
    private static HashMap<String, String> wayRes;

    static {
        HashMap<String, String> map = new HashMap<>();
        wayMap = map;
        map.put("alipay", "alipay");
        wayMap.put(NativePayWay.PWAY_KEY_HUABEI, NativePayWay.PAY_WAY_HUABEI);
        wayMap.put(NativePayWay.PWAY_KEY_WECHAT, NativePayWay.PAY_WAY_WECHAT);
        wayMap.put(NativePayWay.PWAY_KEY_WALLET, NativePayWay.PAY_WAY_WALLET);
        HashMap<String, String> map2 = new HashMap<>();
        wayChineseMap = map2;
        map2.put("alipay", "支付宝");
        wayChineseMap.put(NativePayWay.PWAY_KEY_HUABEI, "花呗");
        wayChineseMap.put(NativePayWay.PWAY_KEY_WECHAT, "微信支付");
        wayChineseMap.put(NativePayWay.PWAY_KEY_WALLET, "钱包支付");
        HashMap<String, String> map3 = new HashMap<>();
        wayRes = map3;
        map3.put("alipay", "sysq_ic_pay_ali");
        wayRes.put(NativePayWay.PWAY_KEY_HUABEI, "sysq_ic_pay_huabei");
        wayRes.put(NativePayWay.PWAY_KEY_WECHAT, "sysq_ic_pay_wechat");
        wayRes.put(NativePayWay.PWAY_KEY_WALLET, "sysq_ic_pay_wallet");
    }

    public static ArrayList<NativePayWay> parsePWay(String str) {
        ArrayList<NativePayWay> arrayList = new ArrayList<>();
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject jSONObjectOptJSONObject = optJSONObject(jSONObject, "promoteTips");
            optJSONObject(jSONObject, "pwaysDiscount");
            JSONObject jSONObjectOptJSONObject2 = optJSONObject(jSONObject, "pwaysTips");
            JSONObject jSONObjectOptJSONObject3 = optJSONObject(jSONObject, "pways");
            Iterator<String> itKeys = jSONObjectOptJSONObject3.keys();
            if (itKeys != null) {
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    String strOptString = jSONObjectOptJSONObject3.optString(next);
                    if (TextUtils.equals(strOptString, "1") || TextUtils.equals(strOptString, "2")) {
                        String wayByKey = getWayByKey(next);
                        if (!TextUtils.isEmpty(wayByKey)) {
                            NativePayWay nativePayWay = new NativePayWay();
                            nativePayWay.setKey(next);
                            nativePayWay.setOpen(strOptString);
                            nativePayWay.setWayChinese(getWayChineseByKey(next));
                            nativePayWay.setWay(wayByKey);
                            nativePayWay.setPromote(jSONObjectOptJSONObject.optString(next));
                            nativePayWay.setResId(getResByKey(next));
                            nativePayWay.setPWayTip(jSONObjectOptJSONObject2.optString(next));
                            arrayList.add(nativePayWay);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    public static String getWayChineseByKey(String str) {
        return wayChineseMap.get(str);
    }

    public static String getResByKey(String str) {
        return wayRes.get(str);
    }

    public static String getWayByKey(String str) {
        return wayMap.get(str);
    }

    private static JSONObject optJSONObject(JSONObject jSONObject, String str) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
        return jSONObjectOptJSONObject == null ? new JSONObject() : jSONObjectOptJSONObject;
    }
}
