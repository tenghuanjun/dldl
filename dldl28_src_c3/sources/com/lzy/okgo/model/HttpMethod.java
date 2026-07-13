package com.lzy.okgo.model;

import com.tencent.connect.common.Constants;

/* JADX INFO: loaded from: d:\dldl\dldl28_apk_extract\classes3.dex */
public enum HttpMethod {
    GET(Constants.HTTP_GET),
    POST(Constants.HTTP_POST),
    PUT("PUT"),
    DELETE("DELETE"),
    HEAD("HEAD"),
    PATCH("PATCH"),
    OPTIONS("OPTIONS"),
    TRACE("TRACE");

    private final String value;

    HttpMethod(String str) {
        this.value = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.value;
    }

    /* JADX INFO: renamed from: com.lzy.okgo.model.HttpMethod$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$lzy$okgo$model$HttpMethod;

        static {
            int[] iArr = new int[HttpMethod.values().length];
            $SwitchMap$com$lzy$okgo$model$HttpMethod = iArr;
            try {
                iArr[HttpMethod.POST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$lzy$okgo$model$HttpMethod[HttpMethod.PUT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$lzy$okgo$model$HttpMethod[HttpMethod.PATCH.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$lzy$okgo$model$HttpMethod[HttpMethod.DELETE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$lzy$okgo$model$HttpMethod[HttpMethod.OPTIONS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
        }
    }

    public boolean hasBody() {
        int i = AnonymousClass1.$SwitchMap$com$lzy$okgo$model$HttpMethod[ordinal()];
        return i == 1 || i == 2 || i == 3 || i == 4 || i == 5;
    }
}
