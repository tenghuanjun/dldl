package com.sy37sdk.account.age;

import android.content.Context;
import com.sqwan.common.util.SpUtils;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes3.dex */
public class AppropriateAgeCacheHelper {
    private static final String SP_KEY_APPROPRIATE_AGE = "sp_key_appropriate_age";

    public static void saveAppropriateAge(Context context, AppropriateAge appropriateAge) {
        if (appropriateAge == null) {
            return;
        }
        SpUtils.get(context).put(SP_KEY_APPROPRIATE_AGE, AppropriateAge.objectToJson(appropriateAge));
    }

    public static AppropriateAge getAppropriateAge(Context context) {
        return AppropriateAge.jsonToObject(SpUtils.get(context).getString(SP_KEY_APPROPRIATE_AGE));
    }
}
