package com.volcengine.androidcloud.common.api;

import android.content.Context;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public interface MountService {
    void init(Context context, Map<String, Object> map);

    void release();
}
