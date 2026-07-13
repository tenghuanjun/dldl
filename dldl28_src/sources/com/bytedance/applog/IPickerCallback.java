package com.bytedance.applog;

import org.json.JSONObject;

/* JADX INFO: loaded from: classes2.dex */
public interface IPickerCallback {
    void failed(String str);

    void success(JSONObject jSONObject);
}
