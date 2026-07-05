package com.bykv.a.a.a.a;

import com.bykv.vk.openvk.api.proto.Result;
import com.bykv.vk.openvk.api.proto.ValueSet;

/* JADX INFO: loaded from: D:\dldl\dldl_apk_extract\classes.dex */
public class a {
    private boolean a = false;
    private int b = -1;
    private String c = null;
    private ValueSet d = null;

    private a() {
    }

    public static final a a() {
        return new a();
    }

    public a a(boolean z) {
        this.a = z;
        return this;
    }

    public a a(int i) {
        this.b = i;
        return this;
    }

    public a a(String str) {
        this.c = str;
        return this;
    }

    public a a(ValueSet valueSet) {
        this.d = valueSet;
        return this;
    }

    public Result b() {
        boolean z = this.a;
        int i = this.b;
        String str = this.c;
        ValueSet valueSetB = this.d;
        if (valueSetB == null) {
            valueSetB = b.a().b();
        }
        return new C0011a(z, i, str, valueSetB);
    }

    /* JADX INFO: renamed from: com.bykv.a.a.a.a.a$a, reason: collision with other inner class name */
    private static final class C0011a implements Result {
        private final boolean a;
        private final int b;
        private final String c;
        private final ValueSet d;

        private C0011a(boolean z, int i, String str, ValueSet valueSet) {
            this.a = z;
            this.b = i;
            this.c = str;
            this.d = valueSet;
        }

        @Override // com.bykv.vk.openvk.api.proto.Result
        public boolean isSuccess() {
            return this.a;
        }

        @Override // com.bykv.vk.openvk.api.proto.Result
        public int code() {
            return this.b;
        }

        @Override // com.bykv.vk.openvk.api.proto.Result
        public String message() {
            return this.c;
        }

        @Override // com.bykv.vk.openvk.api.proto.Result
        public ValueSet values() {
            return this.d;
        }
    }
}
