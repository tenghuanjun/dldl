package com.volcengine.i;

import com.volcengine.common.contant.CommonConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Map<String, Object> f1143a = new HashMap();

    public b a(int i) {
        this.f1143a.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        return this;
    }

    public b a(String str) {
        this.f1143a.put(CommonConstants.KEY_ERR_MSG, str);
        return this;
    }

    public b a(String str, Object obj) {
        this.f1143a.put(str, obj);
        return this;
    }

    public Map<String, Object> a() {
        return this.f1143a;
    }

    public b b(int i) {
        this.f1143a.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(i));
        return this;
    }

    public b b(String str) {
        this.f1143a.put("level", str);
        return this;
    }

    public b c(String str) {
        this.f1143a.put(CommonConstants.KEY_ORIGIN_ERR_MSG, str);
        return this;
    }
}
