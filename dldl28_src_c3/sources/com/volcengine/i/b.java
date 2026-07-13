package com.volcengine.i;

import com.volcengine.common.contant.CommonConstants;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public class b {
    private Map<String, Object> a = new HashMap();

    public b a(int i) {
        this.a.put(CommonConstants.KEY_ERROR_CODE, Integer.valueOf(i));
        return this;
    }

    public b a(String str) {
        this.a.put(CommonConstants.KEY_ERR_MSG, str);
        return this;
    }

    public b a(String str, Object obj) {
        this.a.put(str, obj);
        return this;
    }

    public Map<String, Object> a() {
        return this.a;
    }

    public b b(int i) {
        this.a.put(CommonConstants.KEY_ORIGIN_ERR_CODE, Integer.valueOf(i));
        return this;
    }

    public b b(String str) {
        this.a.put("level", str);
        return this;
    }

    public b c(String str) {
        this.a.put(CommonConstants.KEY_ORIGIN_ERR_MSG, str);
        return this;
    }
}
