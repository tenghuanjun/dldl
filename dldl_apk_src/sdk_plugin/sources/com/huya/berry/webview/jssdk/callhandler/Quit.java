package com.huya.berry.webview.jssdk.callhandler;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import com.duowan.auk.ArkUtils;
import com.duowan.live.common.webview.jssdk.callhandler.base.HandlerBase;
import com.google.gson.Gson;
import com.huya.component.user.api.UserCallback;
import java.util.Map;

/* JADX INFO: loaded from: D:\dldl\sq_plugin_extract\classes2.dex */
public class Quit extends HandlerBase {
    private static String DATA_KEY = "data";
    private static String SOURCE_KEY = "source";
    private static String VALUE_SOURCE_MODIFYNICK = "modifynick";

    public String getFuncName() {
        return "quit";
    }

    public Object call(Object obj, Context context) {
        if (!(obj instanceof Map)) {
            return null;
        }
        if (TextUtils.equals(VALUE_SOURCE_MODIFYNICK, (String) ((Map) obj).get(SOURCE_KEY))) {
            ArkUtils.send(new UserCallback.OnSaveUserNickName(new Gson().toJson(obj)));
        }
        if (context instanceof Activity) {
            ((Activity) context).finish();
        }
        return null;
    }
}
