package com.bytedance.applog.convert;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.applog.convert.hume.readapk.HumeSDK;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public class ClickIdProvider implements IIdProvider {
    @Override // com.bytedance.applog.convert.IIdProvider
    public void getIdAndSetIntoJson(JSONObject jSONObject, Context context) throws Throwable {
        String extra = HumeSDK.getExtra(context);
        if (TextUtils.isEmpty(extra)) {
            return;
        }
        JSONObject jSONObject2 = new JSONObject(extra);
        String strOptString = jSONObject2.optString(BusinessConstant.KEY_CLICK_ID);
        if (!TextUtils.isEmpty(strOptString)) {
            jSONObject.put(BusinessConstant.KEY_CLICK_ID, strOptString);
        }
        String strOptString2 = jSONObject2.optString(BusinessConstant.KEY_CLICK_ID_NATURE);
        if (TextUtils.isEmpty(strOptString2)) {
            return;
        }
        jSONObject.put(BusinessConstant.KEY_CLICK_ID_NATURE, strOptString2);
    }
}
