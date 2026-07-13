package com.bytedance.applog.convert;

import android.content.Context;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IIdProvider {
    void getIdAndSetIntoJson(JSONObject jSONObject, Context context) throws JSONException;
}
