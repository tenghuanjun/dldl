package com.sy37sdk.order.nat;

import android.text.TextUtils;
import com.sqwan.common.track.SqTrackAction2;
import com.sqwan.common.track.SqTrackActionManager2;
import com.sqwan.common.track.SqTrackKey;
import java.util.HashMap;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class NatPayReporter {
    public static void trackPayInit(String str, String str2, String str3, String str4) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.order_id, str);
        map.put(SqTrackKey.order_amount, str2);
        map.put(SqTrackKey.pay_channels, str3);
        map.put("pay_version", str4);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.pay_init, map);
    }

    public static void trackPayClick(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.order_id, str);
        map.put(SqTrackKey.order_amount, str2);
        map.put("pay_amount", str3);
        map.put(SqTrackKey.pay_channels, str4);
        map.put(SqTrackKey.pay_method, str5);
        map.put("pay_version", str6);
        map.put(SqTrackKey.is_vouchers, str7);
        if (TextUtils.isEmpty(str8) || str8.equals("[]")) {
            str8 = "0";
        }
        map.put(SqTrackKey.vouchers_id, str8);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.pay_click, map);
    }

    public static void trackPayClose(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        HashMap map = new HashMap();
        map.put(SqTrackKey.order_id, str);
        map.put(SqTrackKey.order_amount, str2);
        map.put("pay_amount", str3);
        map.put(SqTrackKey.pay_channels, str4);
        map.put(SqTrackKey.pay_method, str5);
        map.put("pay_version", str6);
        map.put(SqTrackKey.is_vouchers, str7);
        if (TextUtils.isEmpty(str8) || str8.equals("[]")) {
            str8 = "0";
        }
        map.put(SqTrackKey.vouchers_id, str8);
        SqTrackActionManager2.getInstance().trackAction(SqTrackAction2.pay_close_click, map);
    }
}
